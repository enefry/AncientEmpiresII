#!/usr/bin/env python3
"""Research renderer for the original title animation; does not modify the game.

Runs selected, unchanged Code attributes from the supplied JAR in a small JVM
harness. Platform stubs record drawing calls rather than emulate a full phone.
"""
import argparse
import hashlib
import json
from pathlib import Path
import struct
import statistics
import subprocess
import zipfile

from PIL import Image, ImageChops

ROOT = Path(__file__).resolve().parents[1]
WORK = ROOT / '.noindex/artifacts/startup'
OUT = ROOT / 'reference/reconstruction/startup'
JAR = ROOT / 'reference/inputs/Ancient-Empires-II_J2ME_EN_v25/Ancient Empires II (2012)(Macrospace, SilentKnight)(v2.5).jar'


def sha(blob):
    return hashlib.sha256(blob).hexdigest()


def jdiv(a, b):
    """Java signed integer division truncates toward zero."""
    return (abs(a) // abs(b)) * (-1 if (a < 0) != (b < 0) else 1)


def sine_table():
    step = 360 * 10000 // 2 // 31415
    velocity, position, result = 1024 * step, 0, []
    for _ in range(360):
        value = jdiv(position, step)
        result.append(value)
        velocity -= value
        position += jdiv(velocity, step)
    result[180], result[270] = 0, -1024
    return result


def wave_commands(w, h, p, waves=1, x=0, y=0):
    lut = sine_table()
    visible = w * p // 40
    amplitude = w * (40 - p) // 160
    angle, increment = p * 360 // 40, waves * 360 // h
    commands = []
    for row in range(h):
        offset = amplitude * lut[angle % 360] >> 10
        commands.append(['clip', x + w // 2 - visible // 2 + offset, y + row, visible, 1])
        commands.append(['image', 'logo', x + offset, y])
        angle += increment
    return commands


def slice_class(blob, fields, methods):
    """Keep original CP and selected members verbatim, dropping unused members.

    In particular no instruction, exception table or Code attribute is rewritten.
    This avoids loading the unrelated J2ME/Siemens/Motorola game code.
    """
    offset, cp = 10, {}
    count = struct.unpack_from('>H', blob, 8)[0]
    index = 1
    while index < count:
        tag = blob[offset]; offset += 1
        if tag == 1:
            size = struct.unpack_from('>H', blob, offset)[0]; offset += 2
            cp[index] = blob[offset:offset + size].decode('utf-8', errors='replace'); offset += size
        elif tag in (3, 4, 9, 10, 11, 12, 18): offset += 4
        elif tag in (5, 6): offset += 8; index += 1
        elif tag in (7, 8, 16): offset += 2
        elif tag == 15: offset += 3
        else: raise ValueError(f'Unsupported constant pool tag: {tag}')
        index += 1
    pool_end = offset
    interfaces = struct.unpack_from('>H', blob, offset + 6)[0]
    offset += 8 + interfaces * 2
    header = blob[:offset]
    def members():
        nonlocal offset
        count = struct.unpack_from('>H', blob, offset)[0]; offset += 2
        result = []
        for _ in range(count):
            start = offset
            _, name, desc, attrs = struct.unpack_from('>HHHH', blob, offset); offset += 8
            code = None
            for _ in range(attrs):
                attr, size = struct.unpack_from('>HI', blob, offset); offset += 6
                if cp[attr] == 'Code': code = blob[offset:offset + size]
                offset += size
            result.append(((cp[name], cp[desc]), blob[start:offset], code))
        return result
    f, m = members(), members()
    selected_f = [v for v in f if v[0] in fields]
    selected_m = [v for v in m if v[0] in methods]
    assert {v[0] for v in selected_f} == fields
    assert {v[0] for v in selected_m} == methods
    packed = header + struct.pack('>H', len(selected_f)) + b''.join(v[1] for v in selected_f)
    packed += struct.pack('>H', len(selected_m)) + b''.join(v[1] for v in selected_m)
    packed += b'\0\0'  # no class-level attributes or static initialization unless retained above
    evidence = [dict(name=k[0], descriptor=k[1], code_attribute_sha256=sha(code)) for k, _, code in selected_m]
    assert packed[:pool_end] == blob[:pool_end]
    return packed, evidence


STUBS = {
    'javax/microedition/lcdui/CommandListener.java': 'package javax.microedition.lcdui; public interface CommandListener {}',
    'javax/microedition/lcdui/ItemStateListener.java': 'package javax.microedition.lcdui; public interface ItemStateListener {}',
    'javax/microedition/lcdui/Font.java': 'package javax.microedition.lcdui; public class Font {}',
    'javax/microedition/lcdui/Graphics.java': '''package javax.microedition.lcdui;
import java.util.*;
public class Graphics {
 public List<String> ops=new ArrayList<>();
 public void setClip(int x,int y,int w,int h){ops.add("clip,"+x+","+y+","+w+","+h);}
 public void setColor(int color){ops.add("color,"+color);}
 public void fillRect(int x,int y,int w,int h){ops.add("fill,"+x+","+y+","+w+","+h);}
 public void setFont(Font font){}
}''',
    'aeii/c.java': '''package aeii;
import javax.microedition.lcdui.Graphics;
public class c {
 public static boolean b=false;
 public void b(){}
 public boolean a(){return false;}
 public static void a(Graphics g,int color,int x,int y,int w,int h){g.ops.add("alpha,"+(color>>>24)+","+x+","+y+","+w+","+h);}
}''',
    'aeii/d.java': '''package aeii;
import java.io.*; import javax.imageio.ImageIO; import java.awt.image.BufferedImage;
import javax.microedition.lcdui.Graphics;
public class d {
 public int a,b; public String name;
 public d(String n){this(n,-1,-1);}
 public d(String n,int width,int height){
  name=n;
  try {BufferedImage im=ImageIO.read(new File(System.getProperty("resources"),n+".png"));
   a=width<0?im.getWidth():width; b=im.getHeight()*a/im.getWidth();
  } catch(Exception e){throw new RuntimeException(e);}
 }
 public void a(Graphics g,int x,int y){g.ops.add("image,"+name+","+x+","+y);}
}''',
    'StartupHarness.java': '''import java.lang.reflect.*; import java.util.*;
import javax.microedition.lcdui.Graphics;
public class StartupHarness {
 static Field field(Class<?> c,String name,Class<?> type)throws Exception{
  for(Field f:c.getDeclaredFields())if(f.getName().equals(name)&&f.getType()==type){f.setAccessible(true);return f;}
  throw new Exception(name+" "+type);
 }
 public static void main(String[] args)throws Exception{
  Class<?> f=Class.forName("aeii.f"); f.getMethod("c").invoke(null);
  System.out.println("LUT,"+Arrays.toString((short[])field(f,"a",short[].class).get(null)));
  field(f,"a",aeii.c.class).set(null,new aeii.c());
  Class<?> g=Class.forName("aeii.g");
  Method wave=g.getDeclaredMethod("a",Graphics.class,int.class,int.class,int.class,aeii.d.class,int.class,int.class,int.class);wave.setAccessible(true);
  for(int w:new int[]{176,240,320})for(int cycles:new int[]{1,2,4})for(int p=0;p<=40;p++){
   aeii.d im=new aeii.d("logo",w,-1);Graphics ctx=new Graphics();
   wave.invoke(null,ctx,p,40,0,im,-3,7,cycles);
   System.out.println("WAVE,"+w+","+im.b+","+cycles+","+p+"|"+String.join(";",ctx.ops));
  }
  Class<?> u=Class.forName("sun.misc.Unsafe");Field uf=u.getDeclaredField("theUnsafe");uf.setAccessible(true);
  Object obj=u.getMethod("allocateInstance",Class.class).invoke(uf.get(null),g);
  field(f,"j",int.class).setInt(obj,240);field(f,"k",int.class).setInt(obj,320);
  field(g,"a",int.class).setInt(obj,240);
  field(g,"F",int.class).setInt(obj,3);field(g,"d",aeii.d.class).set(obj,new aeii.d("logo",240,-1));
  Method update=g.getDeclaredMethod("l");update.setAccessible(true);
  Method paint=g.getDeclaredMethod("e",Graphics.class);paint.setAccessible(true);
  for(int i=0;i<96;i++){
   boolean blink=field(g,"k",boolean.class).getBoolean(obj);
   field(g,"k",boolean.class).setBoolean(obj,false); // phone font intentionally outside this raster harness
   Graphics ctx=new Graphics();paint.invoke(obj,ctx);field(g,"k",boolean.class).setBoolean(obj,blink);
   System.out.println("FRAME,"+i+","+field(g,"F",int.class).getInt(obj)+","+field(g,"G",int.class).getInt(obj)+","+field(g,"P",int.class).getInt(obj)+","+blink+"|"+String.join(";",ctx.ops));
   field(g,"a",long.class).setLong(obj,field(g,"a",long.class).getLong(obj)+50);update.invoke(obj);
  }
  aeii.c.b=true;field(g,"F",int.class).setInt(obj,0);update.invoke(obj);
  System.out.println("SKIP,"+field(g,"F",int.class).getInt(obj)+","+field(g,"G",int.class).getInt(obj));
 }
}'''
}


def run_bytecode():
    classes, src = WORK / 'jvm/classes', WORK / 'jvm/src'
    classes.mkdir(parents=True, exist_ok=True)
    methods = {}
    with zipfile.ZipFile(JAR) as jar:
        definitions = {
            'f': ({('a','[S'),('a','I'),('b','I'),('c','I'),('a','Laeii/c;'),('j','I'),('k','I'),('l','I'),('m','I')},
                  {('c','()V'),('a','(I)S'),('<clinit>','()V')}),
            'g': ({('F','I'),('G','I'),('H','I'),('P','I'),('a','J'),('a','I'),('b','Laeii/d;'),('c','Laeii/d;'),('d','Laeii/d;'),('e','Laeii/d;'),('k','Z')},
                  {('l','()V'),('e','(Ljavax/microedition/lcdui/Graphics;)V'),('a','(Ljavax/microedition/lcdui/Graphics;IIILaeii/d;III)V')})
        }
        for name,(fields,keep) in definitions.items():
            blob=jar.read(f'aeii/{name}.class'); clipped,evidence=slice_class(blob,fields,keep)
            target=classes / f'aeii/{name}.class';target.parent.mkdir(parents=True,exist_ok=True);target.write_bytes(clipped)
            methods[name]=dict(original_class_sha256=sha(blob),selected_methods=evidence)
    sources=[]
    for name,code in STUBS.items():
        path=src/name;path.parent.mkdir(parents=True,exist_ok=True);path.write_text(code);sources.append(str(path))
    subprocess.run(['javac','-d',str(classes),*sources],check=True)
    result=subprocess.run(['java','-cp',str(classes),f'-Dresources={ROOT / "reference/original/res"}', 'StartupHarness'],capture_output=True,text=True)
    if result.returncode: raise RuntimeError(result.stderr)
    return result.stdout,methods


def parse_commands(text):
    result=[]
    for op in text.split(';'):
        if not op: continue
        args=op.split(',')
        result.append([args[0], *[int(v) if v.lstrip('-').isdigit() else v for v in args[1:]]])
    return result


def render(commands, assets):
    canvas=Image.new('RGBA',(240,320),(0,0,0,255));clip=(0,0,240,320);color=(0,0,0,255)
    for op in commands:
        if op[0]=='clip':
            _,x,y,w,h=op;clip=(max(0,x),max(0,y),min(240,x+w),min(320,y+h))
        elif op[0]=='color':
            v=op[1];color=(v>>16&255,v>>8&255,v&255,255)
        elif op[0] in ('image','fill','alpha'):
            if op[0]=='image':
                _,name,x,y=op;im=assets[name]
            elif op[0]=='fill':
                _,x,y,w,h=op;im=Image.new('RGBA',(w,h),color)
            else:
                _,alpha,x,y,w,h=op;im=Image.new('RGBA',(w,h),(0,0,0,alpha))
            left,top=max(clip[0],x),max(clip[1],y)
            right,bottom=min(clip[2],x+im.width),min(clip[3],y+im.height)
            if left<right and top<bottom:
                region=im.crop((left-x,top-y,right-x,bottom-y));canvas.alpha_composite(region,(left,top))
    return canvas


def compare_recording(recording, assets):
    expected=[render(wave_commands(240,85,p),assets).crop((4,0,240,85)).convert('L').point(lambda v:255 if v>=55 else 0) for p in range(41)]
    result=subprocess.run(['ffmpeg','-v','error','-i',str(recording),'-t','2.45','-vf',
                           'fps=30,crop=976:346:2:8,scale=240:85:flags=bilinear,format=gray','-f','rawvideo','-'],capture_output=True,check=True)
    raw,size=result.stdout,240*85
    assert len(raw)%size==0
    rows=[]
    for index in range(len(raw)//size):
        mask=Image.frombytes('L',(240,85),raw[index*size:(index+1)*size]).crop((4,0,240,85)).point(lambda v:255 if v>=55 else 0)
        scores=[]
        for candidate in expected:
            union=ImageChops.lighter(mask,candidate).histogram()[255]
            intersection=ImageChops.darker(mask,candidate).histogram()[255]
            scores.append(intersection/max(union,1))
        p=max(range(41),key=lambda p:scores[p])
        rows.append(dict(time_seconds=round(index/30,4),progress=p,foreground_iou=round(scores[p],4)))
    first={r['progress']:r['time_seconds'] for r in reversed(rows) if r['foreground_iou']>.7 and r['progress']<40}
    n=len(first);mean_p=sum(first)/n;mean_t=sum(first.values())/n
    slope=sum((p-mean_p)*(t-mean_t) for p,t in first.items())/sum((p-mean_p)**2 for p in first)
    report=dict(recording=str(recording),recording_sha256=sha(recording.read_bytes()),
                method='Thresholded foreground shape comparison after viewport resampling; not pixel equality',
                crop_xywh=[2,8,976,346],resampled_size=[240,85],excluded_left_columns=4,threshold=55,sample_fps=30,
                matched_progress_range=[min(first),max(first)],matched_progress_count=n,
                estimated_ms_per_progress=round(slope*1000,3),
                median_foreground_iou=round(statistics.median(r['foreground_iou'] for r in rows if r['progress']<40),4),frames=rows)
    (OUT/'recording-comparison.json').write_text(json.dumps(report,ensure_ascii=False,indent=2)+'\n')
    print(json.dumps({k:v for k,v in report.items() if k!='frames'},ensure_ascii=False,indent=2))


def main():
    parser=argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--recording',type=Path,help='Optional supplied 978×1294 recording for visual comparison (fixed visible viewport crop)')
    args=parser.parse_args()
    OUT.mkdir(parents=True,exist_ok=True);WORK.mkdir(parents=True,exist_ok=True)
    stdout,method_evidence=run_bytecode()
    frames,commands,checks=[],[],0
    for line in stdout.splitlines():
        if line.startswith('LUT,'): assert json.loads(line[4:])==sine_table()
        elif line.startswith('WAVE,'):
            header,ops=line.split('|');_,w,h,cycles,p=header.split(',')
            assert parse_commands(ops)==wave_commands(int(w),int(h),int(p),int(cycles),-3,7)
            checks+=int(h)
        elif line.startswith('FRAME,'):
            header,ops=line.split('|');_,index,mode,p,glow,blink=header.split(',')
            frames.append(dict(index=int(index),phase=int(mode),progress=int(p),glow_x=int(glow),press_any_key=blink=='true'))
            commands.append(parse_commands(ops))
        elif line.startswith('SKIP,'): assert line=='SKIP,4,0'
        else: raise AssertionError(line)
    assert [(f['phase'],f['progress']) for f in frames[:41]]==[(3,p) for p in range(41)]
    assert [(f['phase'],f['progress']) for f in frames[41:58]]==[(4,q) for q in range(17)]
    assert all(f['glow_x']==-67 for f in frames[42:58])
    assert frames[58]['glow_x']==-27 and frames[83]['glow_x']==973 and frames[84]['glow_x']==-67
    assets={n:Image.open(ROOT/f'reference/original/res/{n}.png').convert('RGBA') for n in ['logo','splash','glow']}
    images=[render(ops,assets) for ops in commands]
    preview=[im.resize((480,640),Image.Resampling.NEAREST) for im in images[:86]]
    preview[0].save(OUT/'title-animation.png',save_all=True,append_images=preview[1:],duration=66,loop=0,disposal=0,blend=0)
    # GIF is a convenient preview; its 10 ms time resolution and palette are not evidence.
    gif_durations=[(int((i+1)*6.6)-int(i*6.6))*10 for i in range(len(preview))]
    preview[0].save(OUT/'title-animation.gif',save_all=True,append_images=preview[1:],duration=gif_durations,loop=0,disposal=2)
    for p in range(41): images[p].save(OUT/f'wave-{p:02}.png')
    selected=[images[p] for p in [5,10,15,20,25,30,35,40]]
    sheet=Image.new('RGB',(240*4,100*2),'black')
    from PIL import ImageDraw
    draw=ImageDraw.Draw(sheet)
    for i,im in enumerate(selected):
        x,y=(i%4)*240,(i//4)*100;sheet.paste(im.crop((0,0,240,85)),(x,y));draw.text((x+4,y+85),f'p={[5,10,15,20,25,30,35,40][i]}',fill='white')
    sheet.save(OUT/'wave-contact.png')
    report=dict(jar_sha256=sha(JAR.read_bytes()),scope='Unmodified selected JVM Code attributes, recorded Graphics calls; not a full J2ME runtime',
                methods=method_evidence,lut=sine_table(),wave_cases=3*3*41,wave_rows_compared=checks,wave_mismatches=0,
                dimensions=[176,240,320],wave_counts=[1,2,4],phases_verified='wave p=0..40, fade q=0..16, glow increment/reset, blink flags, Disable-Intro skip',
                frame_duration_ms=66,frames=frames,
                original_images={n:dict(width=im.width,height=im.height,sha256=sha((ROOT/f'reference/original/res/{n}.png').read_bytes())) for n,im in assets.items()},
                limitations=['No J2ME device/font rendering','Pillow alpha compositing is a reference, not device drawRGB verification','No full intro/story screens or circular menu emulation','Recording version/configuration and playback clock require independent alignment'])
    (OUT/'algorithm-verification.json').write_text(json.dumps(report,ensure_ascii=False,indent=2)+'\n')
    print(json.dumps({k:report[k] for k in ['wave_cases','wave_rows_compared','wave_mismatches','phases_verified']},ensure_ascii=False,indent=2))
    if args.recording: compare_recording(args.recording,assets)


if __name__=='__main__': main()
