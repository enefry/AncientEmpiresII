#!/usr/bin/env python3
"""Decode the selected AEII JAR into research data, without changing game files.

Run from any directory with Python 3 and Pillow. No emulator or app code is built.
Generated files live in reference/reconstruction; original bytes stay untouched.
"""
from pathlib import Path
import base64
import collections
import hashlib
import io
import json
import re
import struct
import textwrap
import zipfile
import zlib
from PIL import Image, ImageDraw

ROOT = Path(__file__).resolve().parents[1]
ORIGINAL = ROOT / 'reference/original'
OUT = ROOT / 'reference/reconstruction'
RES = ORIGINAL / 'res'
JAR = ROOT / 'reference/inputs/Ancient-Empires-II_J2ME_EN_v25/Ancient Empires II (2012)(Macrospace, SilentKnight)(v2.5).jar'
BASELINE_SHA = 'f2b3e06190014940a04da70677d6504dd3fd3d9fbc39a49192926293f830f5ae'
NAMES = 'soldier archer lizard wizard wisp spider golem catapult wyvern king skeleton crystall'.split()
ZH = '士兵 弓箭手 元素生物 女术士 幽灵 凶狼 魔像 投石车 巨龙 指挥官 骷髅 水晶'.split()
PROPS = ['flying', 'amphibious', 'reserved_commander_bit', 'occupy_town_and_repair',
         'occupy_castle', 'raise_skeleton', 'anti_air', 'poison_on_initiated_attack',
         'aura_on_finish_action', 'move_or_attack']


def sha(data):
    return hashlib.sha256(data).hexdigest()


def write_json(path, value):
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(value, ensure_ascii=False, indent=2) + '\n', encoding='utf-8')


class Reader:
    def __init__(self, data):
        self.data, self.pos = data, 0

    def take(self, size):
        assert size >= 0 and self.pos + size <= len(self.data), (self.pos, size, len(self.data))
        value = self.data[self.pos:self.pos + size]
        self.pos += size
        return value

    def unpack(self, fmt):
        return struct.unpack('>' + fmt, self.take(struct.calcsize('>' + fmt)))

    def done(self):
        assert self.pos == len(self.data), (self.pos, len(self.data))


def modified_utf8(data):
    # Java readUTF stores UTF-16 code units as 1/2/3-byte sequences, including
    # C0 80 for NUL and surrogate pairs as two separate three-byte sequences.
    units = []
    r = Reader(data)
    while r.pos < len(data):
        a = r.take(1)[0]
        if a < 0x80:
            assert a != 0
            value = a
        elif a & 0xe0 == 0xc0:
            b = r.take(1)[0]
            assert b & 0xc0 == 0x80
            value = ((a & 31) << 6) | (b & 63)
            assert value >= 128 or (a == 0xc0 and b == 0x80)
        else:
            assert a & 0xf0 == 0xe0
            b, c = r.take(2)
            assert b & 0xc0 == c & 0xc0 == 0x80
            value = ((a & 15) << 12) | ((b & 63) << 6) | (c & 63)
            assert value >= 2048
        units.append(value)
    return b''.join(struct.pack('>H', value) for value in units).decode('utf-16-be')


def encode_modified_utf8(text):
    encoded = bytearray()
    raw = text.encode('utf-16-be')
    for i in range(0, len(raw), 2):
        value = (raw[i] << 8) | raw[i+1]
        if 1 <= value < 128:
            encoded.append(value)
        elif value < 2048:
            encoded.extend((0xc0 | (value >> 6), 0x80 | (value & 63)))
        else:
            encoded.extend((0xe0 | (value >> 12), 0x80 | ((value >> 6) & 63), 0x80 | (value & 63)))
    return bytes(encoded)


def config(path):
    for number, line in enumerate(path.read_text().splitlines(), 1):
        tokens = line.split(';', 1)[0].split()
        if tokens:
            yield number, tokens


def png_info(path):
    data = path.read_bytes()
    r = Reader(data)
    assert r.take(8) == b'\x89PNG\r\n\x1a\n'
    chunks, palette = [], []
    while r.pos < len(data):
        size, = r.unpack('I')
        kind = r.take(4)
        payload = r.take(size)
        crc, = r.unpack('I')
        assert zlib.crc32(kind + payload) & 0xffffffff == crc, path
        chunks.append(kind.decode('ascii'))
        if kind == b'IHDR':
            width, height, depth, color, *_ = struct.unpack('>IIBBBBB', payload)
        if kind == b'PLTE':
            palette = [list(payload[i:i+3]) for i in range(0, size, 3)]
        if kind == b'IEND':
            break
    r.done()
    with Image.open(path) as image:
        image.load()  # validate compressed image data too
        assert image.size == (width, height)
    return dict(width=width, height=height, bit_depth=depth, color_type=color,
                chunks=chunks, palette=palette)


def midi_info(path):
    r = Reader(path.read_bytes())
    assert r.take(4) == b'MThd'
    header_size, = r.unpack('I')
    header = r.take(header_size)
    fmt, track_count, division = struct.unpack('>HHH', header[:6])
    assert division & 0x8000 == 0, 'SMPTE MIDI needs another clock decoder'
    tracks, tempos = [], []
    end_tick = 0
    for index in range(track_count):
        assert r.take(4) == b'MTrk'
        length, = r.unpack('I')
        tr = Reader(r.take(length))
        tick, running, notes, programs, texts = 0, None, 0, [], []

        def vlq():
            value = 0
            for _ in range(4):
                byte = tr.take(1)[0]
                value = (value << 7) | (byte & 127)
                if byte < 128:
                    return value
            raise ValueError('invalid MIDI VLQ')

        while tr.pos < len(tr.data):
            tick += vlq()
            status = tr.take(1)[0]
            first = None
            if status < 128:
                assert running is not None
                first, status = status, running
            if status == 255:
                kind = tr.take(1)[0]
                payload = tr.take(vlq())
                if kind == 81:
                    assert len(payload) == 3
                    tempos.append(dict(tick=tick, microseconds_per_quarter=int.from_bytes(payload, 'big'), track=index))
                if kind in (1, 2, 3):
                    texts.append(payload.decode('latin-1'))
                if kind == 47:
                    assert not payload
                    tr.done()
                    break
            elif status in (240, 247):
                tr.take(vlq())
                running = None
            else:
                assert 128 <= status < 240
                running = status
                count = 1 if status >> 4 in (12, 13) else 2
                payload = ([first] if first is not None else []) + list(tr.take(count - (first is not None)))
                assert all(value < 128 for value in payload)
                if status >> 4 == 9 and payload[1] > 0:
                    notes += 1
                if status >> 4 == 12:
                    programs.append(dict(channel=status & 15, program_zero_based=payload[0], tick=tick))
        end_tick = max(end_tick, tick)
        tracks.append(dict(index=index, end_tick=tick, note_on_count=notes, programs=programs, texts=texts))
    r.done()
    us, previous, tempo = 0, 0, 500000
    for event in sorted(tempos, key=lambda item: (item['tick'], item['track'])):
        us += (event['tick'] - previous) * tempo / division
        previous, tempo = event['tick'], event['microseconds_per_quarter']
    us += (end_tick - previous) * tempo / division
    return dict(format=fmt, track_count=track_count, ticks_per_quarter=division,
                end_tick=end_tick, duration_seconds=round(us / 1e6, 6), tempos=tempos, tracks=tracks)


def main():
    assert sha(JAR.read_bytes()) == BASELINE_SHA
    OUT.mkdir(parents=True, exist_ok=True)
    with zipfile.ZipFile(JAR) as archive:
        entries = [item for item in archive.infolist() if not item.is_dir()]
        for item in entries:
            assert (ORIGINAL / item.filename).read_bytes() == archive.read(item)
    source_root = ROOT / 'reference/inputs/AEIIRM_src'
    source_hashes = {str(p.relative_to(ROOT)): sha(p.read_bytes())
                     for p in sorted(source_root.rglob('*'))
                     if p.is_file() and not p.relative_to(source_root).as_posix().startswith('nbproject/private/')}

    lang_reader = Reader((ORIGINAL / 'lang.dat').read_bytes())
    count, = lang_reader.unpack('I')
    strings = []
    for index in range(count):
        size, = lang_reader.unpack('H')
        strings.append(dict(id=index, text=modified_utf8(lang_reader.take(size))))
    lang_reader.done()
    texts = [item['text'] for item in strings]
    rebuilt_lang = struct.pack('>I', count)
    for text in texts:
        encoded = encode_modified_utf8(text)
        rebuilt_lang += struct.pack('>H', len(encoded)) + encoded
    assert rebuilt_lang == (ORIGINAL/'lang.dat').read_bytes()
    write_json(OUT / 'data/strings.json', strings)
    (OUT / 'TEXTS.md').write_text('# 原包英文文本：完整 ID 表\n\n直接解码 lang.dat，保留原文；不是新翻译。\n\n' + '\n\n'.join(
        f"## {item['id']}\n\n" + item['text'] for item in strings) + '\n')

    units = []
    for index, name in enumerate(NAMES):
        u = dict(id=index, resource_name=name, chinese_reference_name=ZH[index],
                 display_name=('Commander' if index == 9 else texts[139 + index * 4]),
                 description_text_id=184+index, description=texts[184+index],
                 rank_name_text_ids=([139+index*4+j for j in range(4)] if index != 9 and index != 11 else []),
                 source=f'res/{name}.unit', properties=[], character_positions=[])
        for line, tokens in config(RES / f'{name}.unit'):
            key, *values = tokens
            args = list(map(int, values))
            if key == 'MoveRange': u['move_budget'] = args[0]
            elif key == 'Attack': u['attack_min'], u['attack_max_exclusive'] = args
            elif key == 'Defence': u['defence'] = args[0]
            elif key == 'AttackRange': u['range_max'], u['range_min'] = args
            elif key == 'Cost': u['cost'] = args[0]
            elif key == 'CharCount': u['character_count'] = args[0]
            elif key == 'CharPos': u['character_positions'].append(dict(index=args[0], x=args[1], y=args[2]))
            elif key == 'HasProperty': u['properties'].append(dict(bit=args[0], mask=1<<args[0], interpretation=PROPS[args[0]]))
            else: raise ValueError((name, line, key))
        assert len(u['character_positions']) == u['character_count']
        u['property_mask'] = sum(p['mask'] for p in u['properties'])
        units.append(u)
    write_json(OUT / 'data/units.json', units)

    terrain, tiles, tile_settings = [], [], {}
    for line, tokens in config(RES / 'tiles0.prop'):
        key, *v = tokens
        if key == 'TypeCount': tile_settings['terrain_count'] = int(v[0])
        elif key == 'TileCount': tile_settings['tile_count'] = int(v[0])
        elif key == 'TypeDef': terrain.append(dict(id=int(v[0]), steps=int(v[1]), defence=int(v[2]), battle_background=v[3], battle_foreground=v[4]))
        elif key == 'TileDef': tiles.append(dict(id=int(v[0]), terrain=int(v[1]), minimap_frame=int(v[2]), image=f'res/tiles0_{int(v[0]):02}.png'))
        else: raise ValueError((line, key))
    assert len(terrain) == tile_settings['terrain_count'] and len(tiles) == tile_settings['tile_count']
    assert [t['id'] for t in tiles] == list(range(len(tiles)))
    for tile in tiles: assert 0 <= tile['terrain'] < len(terrain)
    write_json(OUT / 'data/terrain.json', dict(**tile_settings, types=terrain, tiles=tiles))

    assets = []
    for path in sorted(ORIGINAL.rglob('*')):
        if not path.is_file() or path.suffix == '.class' or 'META-INF' in path.parts:
            continue
        entry = dict(path=str(path.relative_to(ORIGINAL)), size=path.stat().st_size,
                     sha256=sha(path.read_bytes()), extension=path.suffix)
        if path.suffix == '.png': entry.update(png_info(path))
        if path.suffix == '.mid': entry.update(midi_info(path))
        assets.append(entry)
    write_json(OUT / 'data/assets.json', assets)

    sprites = []
    for path in sorted(RES.glob('*.sprite')):
        sprite = dict(name=path.stem, source=f'res/{path.name}', frames=[], sequences=[], align=0,
                      frame_delay_ticks=0, frame_delay_logical_ms=0)
        frames, translates, sequences = {}, {}, {}
        for line, tokens in config(path):
            key, *v = tokens
            args = list(map(int, v))
            if key == 'FrameCount': sprite['frame_count'] = args[0]
            elif key == 'FrameWidth': sprite['frame_width'] = args[0]
            elif key == 'FrameHeight': sprite['frame_height'] = args[0]
            elif key == 'FrameDef': frames[args[0]] = dict(index=args[0], source_index=args[1], transform_bits=args[2])
            elif key == 'Align': sprite['align'] = args[0]
            elif key == 'FrameDelay': sprite['frame_delay_ticks'] = args[0]; sprite['frame_delay_logical_ms'] = args[0]*50
            elif key == 'Translate': translates[args[0]] = args[1:]
            elif key == 'FrameSeqCount': sprite['declared_sequence_count'] = args[0]
            elif key == 'FrameSeq':
                assert args[1] == len(args[2:])
                sequences[args[0]] = dict(index=args[0], frames=args[2:])
            else: raise ValueError((path, line, key))
        assert set(frames) == set(range(sprite['frame_count'])), path
        for f in frames.values():
            f['translate'] = translates.get(f['index'], [0, 0])
            assert 0 <= f['source_index'] < sprite['frame_count']
        sprite['frames'] = list(frames.values())
        sprite['sequences'] = list(sequences.values())
        assert len(sequences) == max(0, sprite.get('declared_sequence_count', 0))
        for seq in sequences.values():
            assert all(0 <= f < sprite['frame_count'] for f in seq['frames'])
        sprite['image_variants'] = []
        for prefix in ('blue/', 'red/', 'green/', 'black/'):
            atlas = RES / (prefix + path.stem + '.png')
            if not atlas.exists() and prefix: atlas = RES / (path.stem + '.png')
            if atlas.exists():
                with Image.open(atlas) as im:
                    capacity = (im.width // sprite['frame_width']) * (im.height // sprite['frame_height'])
                assert max(f['source_index'] for f in frames.values()) < capacity
                sources = [str(atlas.relative_to(ORIGINAL))]
                mode = 'atlas_row_major'
            else:
                sources = []
                missing = []
                for source_index in sorted(set(f['source_index'] for f in frames.values())):
                    image_path = RES / f'{prefix}{path.stem}_{source_index:02}.png'
                    if not image_path.exists(): image_path = RES / f'{path.stem}_{source_index:02}.png'
                    if image_path.exists():
                        sources.append(str(image_path.relative_to(ORIGINAL)))
                    else:
                        missing.append(str(image_path.relative_to(ORIGINAL)))
                mode = 'individual_frames'
            sprite['image_variants'].append(dict(faction_prefix=prefix, mode=mode, sources=sources,
                                                 missing_sources=(missing if mode=='individual_frames' else [])))
        sprites.append(sprite)
    write_json(OUT / 'data/sprites.json', sprites)

    maps = []
    for prefix, size in [('m', 8), ('s', 12)]:
        for index in range(size):
            path = RES / f'{prefix}{index}.aem'
            r = Reader(path.read_bytes())
            width, height = r.unpack('ii')
            assert 0 < width < 512 and 0 < height < 512
            columns = [list(r.take(height)) for _ in range(width)]
            assert all(0 <= tile < len(tiles) for col in columns for tile in col)
            skipped_count, = r.unpack('i')
            skipped = [r.take(4).hex() for _ in range(skipped_count)]
            unit_count, = r.unpack('i')
            map_units = []
            for i in range(unit_count):
                packed, px, py = r.unpack('bhh')
                assert 0 <= packed < 48
                ux, uy = int(px/24), int(py/24)  # Java signed division truncates towards zero
                map_units.append(dict(index=i, packed_id=packed, type=packed%12, fraction=1+packed//12,
                                      pixel_x=px, pixel_y=py, x=ux, y=uy,
                                      pixel_aligned=px%24==0 and py%24==0,
                                      in_bounds=0<=ux<width and 0<=uy<height))
            r.done()
            buildings = [dict(x=x, y=y, tile=columns[x][y], terrain=tiles[columns[x][y]]['terrain'],
                              fraction=((columns[x][y]-37)//2 if columns[x][y]>=37 else -1))
                         for x in range(width) for y in range(height)
                         if 37 <= columns[x][y] < 47 or columns[x][y] == 27]
            item = dict(id=path.stem, kind='campaign' if prefix=='m' else 'skirmish', index=index,
                        name=texts[(113 if prefix=='m' else 101)+index], width=width, height=height,
                        grid_orientation='rows[y][x]; binary input is columns[x][y]',
                        rows=[[columns[x][y] for x in range(width)] for y in range(height)],
                        skipped_record_count=skipped_count, skipped_records_hex=skipped,
                        units=map_units, buildings=buildings, trailing_bytes=0,
                        tile_histogram=dict(sorted(collections.Counter(t for col in columns for t in col).items())),
                        source=f'res/{path.name}', sha256=sha(path.read_bytes()))
            rebuilt = struct.pack('>ii', width, height)
            rebuilt += bytes(item['rows'][y][x] for x in range(width) for y in range(height))
            rebuilt += struct.pack('>i', item['skipped_record_count'])
            rebuilt += b''.join(bytes.fromhex(value) for value in item['skipped_records_hex'])
            rebuilt += struct.pack('>i', len(item['units']))
            rebuilt += b''.join(struct.pack('>bhh', u['packed_id'], u['pixel_x'], u['pixel_y']) for u in item['units'])
            assert rebuilt == path.read_bytes(), path
            write_json(OUT / f'data/maps/{path.stem}.json', item)
            maps.append(item)
    write_json(OUT / 'data/maps.json', maps)

    # A source appendix retains every case, including implicit state changes in
    # moveMapAndCursor. It is an index of evidence, not an executable port.
    source_path = ROOT / 'reference/inputs/AEIIRM_src/src/aeii/MainDisplayable.java'
    source = source_path.read_text()
    start = source.index('public final void updateScript()')
    end = source.index('public final void startNormalPlay()', start)
    section = source[start:end]
    branches = list(re.finditer(r'(?:if|else if)\(currentMission == (\d)\)', section))
    states, appendix = [], ['# 战役状态机逐项证据\n\n来自 AEIIRM_src 的 updateScript。保留条件、坐标与调用；尚未逐状态核对 JAR 字节码。\n\nmoveMapAndCursor 自带 scriptState++，不能把没有显式 ++ 的 case 误判为不前进。\n']
    for i, branch in enumerate(branches):
        mission = int(branch.group(1))
        stop = branches[i+1].start() if i+1 < len(branches) else len(section)
        chunk = section[branch.end():stop]
        switch_start = chunk.index('switch(scriptState)')
        opening = chunk.index('{', switch_start)
        depth = 1
        closing = None
        # Ignore Java strings/comments when finding the matching switch brace.
        tokens = re.finditer(r'"(?:\\.|[^"\\])*"|//[^\n]*|/\*[\s\S]*?\*/|[{}]', chunk[opening+1:])
        for token in tokens:
            if token.group() == '{': depth += 1
            elif token.group() == '}': depth -= 1
            if depth == 0:
                closing = opening + 1 + token.start()
                break
        assert closing is not None
        chunk_offset = branch.end() + opening + 1
        chunk = chunk[opening+1:closing]
        cases = list(re.finditer(r'^[\t ]*case (\d+):', chunk, flags=re.M))
        appendix.append(f'## m{mission} — {texts[121+mission]}\n')
        for j, case in enumerate(cases):
            case_stop = cases[j+1].start() if j+1 < len(cases) else len(chunk)
            raw = chunk[case.start():case_stop].strip('\n\r')
            offset = start + chunk_offset + case.start()
            line = source[:offset].count('\n') + 1
            code = textwrap.dedent(raw)
            ids = [int(x) for x in re.findall(r'getLocaleString\((\d+)\)', raw)]
            states.append(dict(mission=mission, state=int(case.group(1)), source_line=line, code=code, text_ids=ids))
            appendix.append(f"### Case {case.group(1)}（源码约 {line} 行）\n\n```java\n{code}\n```\n")
            for text_id in ids: appendix.append(f'> 文本 {text_id}: {texts[text_id]}\n')
    write_json(OUT / 'data/campaign-states.json', states)
    (OUT / 'CAMPAIGN_STATES.md').write_text('\n'.join(appendix))

    # Derived research previews: terrain + encoded units only, before cinematic
    # relocations/spawns. Original PNGs are embedded unchanged in the HTML.
    preview_dir = OUT / 'previews'
    preview_dir.mkdir(exist_ok=True)
    tile_images = [Image.open(RES / f'tiles0_{i:02}.png').convert('RGBA') for i in range(48)]
    for m in maps:
        im = Image.new('RGBA', (m['width']*24, m['height']*24))
        for y, row in enumerate(m['rows']):
            for x, tile in enumerate(row): im.alpha_composite(tile_images[tile], (x*24, y*24))
        for u in m['units']:
            if u['in_bounds']:
                prefix = ['blue/', 'red/', 'green/', 'black/'][u['fraction']-1]
                atlas = Image.open(RES / f'{prefix}unit_icons.png').convert('RGBA')
                frame = u['type']  # icon animation bank 0; bank 1 starts at 12
                cols = atlas.width // 24
                x, y = (frame%cols)*24, (frame//cols)*24
                im.alpha_composite(atlas.crop((x, y, x+24, y+24)), (u['x']*24, u['y']*24))
        im.save(preview_dir / f"{m['id']}.png")
    sheet = Image.new('RGB', (8*96, 6*84), '#172127')
    draw = ImageDraw.Draw(sheet)
    for i, im in enumerate(tile_images):
        x, y = i%8*96, i//8*84
        sheet.paste(im.resize((48,48), Image.Resampling.NEAREST), (x+24,y+4))
        draw.text((x+12,y+58), f"{i:02} T{tiles[i]['terrain']}", fill='white')
    sheet.save(preview_dir / 'tiles.png')

    def data_url(path):
        return 'data:image/png;base64,' + base64.b64encode(path.read_bytes()).decode('ascii')

    viewer_data = dict(maps=maps, units=units, tiles=tiles, terrain=terrain, sprites=sprites,
                       images=[dict(**a, url=data_url(ORIGINAL/a['path'])) for a in assets if a['extension']=='.png'],
                       tile_urls=[data_url(RES/f'tiles0_{i:02}.png') for i in range(48)],
                       icon_urls=[data_url(RES/f'{p}unit_icons.png') for p in ('blue/','red/','green/','black/')])
    startup = OUT / 'startup'
    if (startup / 'algorithm-verification.json').is_file():
        viewer_data['startup'] = dict(animation_url=data_url(startup / 'title-animation.png'),
                                     wave_urls=[data_url(startup / f'wave-{p:02}.png') for p in range(41)])
    template = ROOT / 'tools/game_reference_viewer.html.template'
    viewer_html = template.read_text()
    assert viewer_html.count('__GAME_DATA__') == 1
    viewer_html = viewer_html.replace('__GAME_DATA__', json.dumps(viewer_data, ensure_ascii=False).replace('</', '<\\/'))
    assert '__GAME_DATA__' not in viewer_html
    (OUT / 'viewer.html').write_text(viewer_html)
    counts = dict(collections.Counter(a['extension'] for a in assets))
    assert counts['.png']==234 and counts['.sprite']==58 and counts['.unit']==12 and counts['.mid']==9 and counts['.aem']==20
    assert len(strings)==317 and len(entries)==355 and len(states)>0
    for rel, digest in source_hashes.items(): assert sha((ROOT/rel).read_bytes())==digest
    validation = dict(baseline_jar_sha256=BASELINE_SHA, verified_jar_entries=len(entries),
                      asset_counts=counts, unit_count=len(units), terrain_count=len(terrain), tile_count=len(tiles),
                      map_count=len(maps), string_count=len(strings), sprite_count=len(sprites),
                      campaign_case_count=len(states), map_cells=sum(m['width']*m['height'] for m in maps),
                      encoded_map_units=sum(len(m['units']) for m in maps),
                      nonzero_skipped_records=[m['id'] for m in maps if m['skipped_record_count']],
                      unaligned_map_units=[dict(map=m['id'], **u) for m in maps for u in m['units'] if not u['pixel_aligned']],
                      offboard_map_units=[dict(map=m['id'], **u) for m in maps for u in m['units'] if not u['in_bounds']],
                      missing_sprite_images=[dict(sprite=s['name'], **v) for s in sprites for v in s['image_variants'] if v['missing_sources']],
                      checked=['all JAR extraction bytes', 'all map/lang/MIDI bytes consumed', 'map/lang exact byte roundtrip', 'PNG chunk CRC and pixel decoding',
                               'unit CharPos counts', 'terrain/tile IDs', 'sprite frame/sequence/image references', 'provided source files unchanged'],
                      runtime_verified=False, source_hashes=source_hashes)
    write_json(OUT / 'VALIDATION.json', validation)
    rows=['# 地图与音乐清单\n\n地图预览是原始地图记录，未执行开场剧情；单位阵营和类型来自二进制记录。\n',
          '| ID | 名称 | 尺寸 | 原始单位 | 城镇/城堡/废墟 | 预览 |','| --- | --- | --- | --- | --- | --- |']
    for m in maps: rows.append(f"| {m['id']} | {m['name']} | {m['width']}×{m['height']} | {len(m['units'])} | {len(m['buildings'])} | [PNG](previews/{m['id']}.png) / [JSON](data/maps/{m['id']}.json) |")
    rows += ['\n## MIDI\n\n时长由 MIDI tempo 事件和结束 tick 计算；未录制 J2ME 音源，也未确认实际循环点。\n',
             '| 文件 | 时长（秒） | 轨道 | PPQN |','| --- | --- | --- | --- |']
    for a in assets:
        if a['extension']=='.mid': rows.append(f"| {a['path']} | {a['duration_seconds']:.3f} | {a['track_count']} | {a['ticks_per_quarter']} |")
    (OUT/'CATALOG.md').write_text('\n'.join(rows)+'\n')
    print(json.dumps({k:validation[k] for k in ('verified_jar_entries', 'asset_counts', 'unit_count',
          'terrain_count', 'tile_count', 'map_count', 'string_count', 'sprite_count', 'campaign_case_count',
          'map_cells', 'encoded_map_units', 'runtime_verified')}, ensure_ascii=False, indent=2))
    print('Preserved unusual records:', len(validation['unaligned_map_units']), 'unaligned units,',
          len(validation['offboard_map_units']), 'offboard units;',
          'missing image definition:', sorted(set(v['sprite'] for v in validation['missing_sprite_images'])))


if __name__ == '__main__':
    main()
