package aeii;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordStore;

public final class c extends Canvas implements Runnable, CommandListener {
   public static boolean a;
   public static boolean b;
   public static final Font a = Font.getFont(0, 1, 8);
   public static final Font b;
   public static final int a;
   public static final int b;
   public static final int c;
   private static short[] a;
   private static short[] b;
   private static byte[][] a;
   private static Display a;
   private boolean c = false;
   private boolean d = true;
   private int f;
   private String a = "";
   public f a;
   public static int d;
   public static int e;
   private int g = 0;
   private int h;
   private int i = 0;
   private long a;
   public static a.a.a.g[] a;
   public static Random a;
   public static boolean[] a;
   public static String[] a;
   private static int[] a;
   private boolean e = false;
   private static int j;
   private static int k;
   private static boolean f;
   public static final String[] b;
   private static byte[] a;
   private static Player[] a;
   private static Player a;
   private static boolean[] b;
   private static int l;
   private static int m;
   private static byte[][] b;
   private static String[] c;

   public c(MIDlet var1) {
      super();

      try {
         a = "true".equalsIgnoreCase(Main.a.getAppProperty("Developer-Mode"));
      } catch (Exception var5) {
         a = false;
      }

      try {
         b = "true".equalsIgnoreCase(Main.a.getAppProperty("Disable-Intro"));
      } catch (Exception var4) {
         b = false;
      }

      try {
         this.c(Integer.parseInt(Main.a.getAppProperty("Override-FPS")));
      } catch (Exception var3) {
         this.c(15);
      }

      try {
         this.setFullScreenMode(true);
         aeii.f.a = this;
         aeii.f.a("/lang.dat");
         d = this.getWidth();
         e = this.getHeight();
         (a = Display.getDisplay(var1)).setCurrent(this);
         (new Thread(this)).start();
      } catch (Exception var2) {
         var2.printStackTrace();
         this.a(var2.toString());
      }
   }

   public static final int a(int var0) {
      return a(0, var0);
   }

   public static final int a(int var0, int var1) {
      return var0 + Math.abs(a.nextInt()) % (var1 - var0);
   }

   public static final int a() {
      return a.nextInt();
   }

   public static final int a(int[] var0) {
      int var1 = var0[var0.length - 1];
      var1 = a(0, var1);

      for(int var2 = 0; var2 < var0.length; ++var2) {
         if (var1 < var0[var2]) {
            return var2;
         }
      }

      return -1;
   }

   public static final void a(int[] var0, boolean var1) {
      if (var1) {
         for(int var3 = 1; var3 < var0.length; ++var3) {
            var0[var3] += var0[var3 - 1];
         }

      } else {
         for(int var2 = var0.length - 1; var2 > 0; --var2) {
            var0[var2] -= var0[var2 - 1];
         }

      }
   }

   public static String a(InputStream var0) {
      StringBuffer var1 = new StringBuffer();

      char var2;
      while((var2 = (char)var0.read()) != '\uffff') {
         if (var2 == '\n') {
            return var1.toString();
         }

         if (var2 != '\r') {
            var1.append(var2);
         }
      }

      return var1.length() > 0 ? var1.toString() : null;
   }

   public static String[] a(String var0, char var1) {
      Vector var4 = new Vector();

      while(var0.length() > 0) {
         int var2;
         if ((var2 = var0.indexOf(32)) >= 0) {
            var4.addElement(var0.substring(0, var2));
            var0 = var0.substring(var2 + 1);
         } else {
            if (var2 != 0) {
               var4.addElement(var0);
               break;
            }

            var0 = var0.substring(1);
         }
      }

      var0 = new String[var4.size()];
      var4.copyInto(var0);
      return var0;
   }

   public static final byte[] a(String var0, int var1) {
      try {
         RecordStore var3;
         byte[] var4 = (var3 = RecordStore.openRecordStore(var0, false)).getRecord(var1 + 1);
         var3.closeRecordStore();
         return var4;
      } catch (Exception var2) {
         return null;
      }
   }

   public static final void a(String var0, int var1, byte[] var2) {
      try {
         int var3;
         RecordStore var5;
         if ((var3 = (var5 = RecordStore.openRecordStore(var0, true)).getNumRecords()) > var1) {
            var5.setRecord(var1 + 1, var2, 0, var2.length);
         } else {
            while(var3 < var1) {
               var5.addRecord((byte[])null, 0, 0);
               ++var3;
            }

            var5.addRecord(var2, 0, var2.length);
         }

         var5.closeRecordStore();
      } catch (Exception var4) {
      }
   }

   public static final int a(String var0, byte[] var1) {
      try {
         RecordStore var3;
         int var4 = (var3 = RecordStore.openRecordStore(var0, true)).addRecord(var1, 0, var1.length);
         var3.closeRecordStore();
         return var4 - 1;
      } catch (Exception var2) {
         return -1;
      }
   }

   public static final void a(String var0, int var1) {
      try {
         RecordStore var3;
         (var3 = RecordStore.openRecordStore(var0, true)).deleteRecord(var1 + 1);
         var3.closeRecordStore();
      } catch (Exception var2) {
      }
   }

   public static final int a(String var0) {
      int var1 = 0;

      try {
         RecordStore var3;
         var1 = (var3 = RecordStore.openRecordStore(var0, true)).getSizeAvailable();
         var3.closeRecordStore();
      } catch (Exception var2) {
      }

      return var1;
   }

   public static final int a(byte var0, String var1) {
      return a[var0].o * var1.length();
   }

   public static final int a(byte var0) {
      return a[var0].p;
   }

   public static final void a(Graphics var0, int var1) {
      var0.setColor(var1);
   }

   public static final void a(Graphics var0, int var1, int var2, int var3, int var4, int var5) {
      for(int var6 = var4 * var5 - 1; var6 >= 0; --var6) {
         a[var6] = var1;
      }

      var0.drawRGB(a, 0, var4, 0, 0, var4, var5, true);
   }

   public final void showNotify() {
      this.setFullScreenMode(true);
      this.e = false;
      f = false;
      this.b();
      if (this.a != null) {
         this.a.a();
      }

   }

   public final void hideNotify() {
      this.b();
      if (this.a != null) {
         f = true;
         if (a != null && a.getState() == 400 && a[l] == 1) {
            j = l;
            k = m;
         }

         f();
         this.e = false;
      }

   }

   public static final void a(Graphics var0, String var1, int var2, int var3, int var4, int var5) {
      if ((var5 & 8) != 0) {
         var2 -= a((byte)var4, var1);
      } else if ((var5 & 1) != 0) {
         var2 -= a((byte)var4, var1) / 2;
      }

      if ((var5 & 32) != 0) {
         var3 -= a((byte)var4);
      } else if ((var5 & 2) != 0) {
         var3 -= a((byte)var4) / 2;
      }

      a(var0, var1, var2, var3, var4);
   }

   public static final void a(Graphics var0, String var1, int var2, int var3, int var4) {
      int var5 = 0;

      for(int var6 = var1.length(); var5 < var6; ++var5) {
         char var7;
         if ((var7 = var1.charAt(var5)) >= a[var4] && var7 <= b[var4]) {
            byte var8;
            if ((var8 = a[var4][var7 - a[var4]]) != -1) {
               a[var4].c(var8);
               a[var4].a(var0, var2, var3);
               var2 += a[var4].o;
            } else {
               byte[] var9 = new byte[]{(byte)var7};
               String var10 = new String(var9);
               var0.drawString(var10, var2, var3, 20);
               var2 += var0.getFont().stringWidth(var10);
            }
         }
      }

   }

   public static final void b(Graphics var0, String var1, int var2, int var3, int var4) {
      var0.drawString(var1, var2, var3 - 2, var4);
   }

   public static void a(Displayable var0) {
      a.setCurrent(var0);
   }

   public final void a(f var1) {
      this.b();
      var1.a();
      this.a = var1;
   }

   public final void a() {
      this.repaint();
      this.serviceRepaints();
   }

   public final void paint(Graphics var1) {
      if (this.d) {
         var1.setColor(16777215);
         var1.fillRect(0, 0, d, e);
         var1.setFont(b);
         var1.setColor(0);
         var1.drawString(aeii.f.a(58), d / 2, e / 2 - 1, 33);
      } else {
         this.a.a(var1);
      }

      if (a) {
         var1.setFont(b);
         aeii.g.a(var1, this.a, 2, 2, 20, -1, 0);
      }

   }

   public final int getGameAction(int var1) {
      switch (var1) {
         case -11:
            return 0;
         case -7:
            return 2048;
         case -6:
            return 1024;
         case 35:
            return 8192;
         case 42:
            return 16384;
         case 48:
            return 32;
         case 49:
            return 64;
         case 50:
            return 1;
         case 51:
            return 128;
         case 52:
            return 4;
         case 53:
            return 16;
         case 54:
            return 8;
         case 55:
            return 256;
         case 56:
            return 2;
         case 57:
            return 512;
         default:
            try {
               switch (super.getGameAction(var1)) {
                  case 1:
                     return 1;
                  case 2:
                     return 4;
                  case 3:
                  case 4:
                  case 7:
                  default:
                     break;
                  case 5:
                     return 8;
                  case 6:
                     return 2;
                  case 8:
                     return 16;
               }
            } catch (Exception var2) {
            }

            return 4096;
      }
   }

   public final String a(int var1) {
      byte var2 = 0;
      switch (var1) {
         case 1:
            var2 = 50;
            break;
         case 2:
            var2 = 56;
            break;
         case 4:
            var2 = 52;
            break;
         case 8:
            var2 = 54;
            break;
         case 16:
            var2 = 53;
            break;
         case 32:
            var2 = 48;
            break;
         case 64:
            var2 = 49;
            break;
         case 128:
            var2 = 51;
            break;
         case 256:
            var2 = 55;
            break;
         case 512:
            var2 = 57;
      }

      return super.getKeyName(var2);
   }

   public final void keyPressed(int var1) {
      var1 = this.getGameAction(var1);
      this.a(var1);
   }

   public final boolean a() {
      return this.g != 0;
   }

   public final void b() {
      this.i = 0;
      this.g = 0;
      this.h = 0;
   }

   public final boolean a(int var1) {
      boolean var2 = (this.h & var1) != 0;
      this.h &= ~var1;
      return var2;
   }

   public final boolean b(int var1) {
      return (this.g & var1) != 0;
   }

   public final void keyReleased(int var1) {
      this.b(this.getGameAction(var1));
   }

   public final boolean c(int var1) {
      return this.i == var1 && System.currentTimeMillis() - this.a >= 400L;
   }

   public final void a(int var1) {
      this.i = var1;
      this.a = System.currentTimeMillis();
      this.g |= var1;
      this.h |= var1;
   }

   public final void b(int var1) {
      if (var1 == this.i) {
         this.i = 0;
      }

      this.g &= ~var1;
   }

   private void a(String var1) {
      this.c = false;
      Form var2;
      (var2 = new Form("Fatal error!")).append(var1);
      Command var3 = new Command("Exit", 7, 1);
      var2.addCommand(var3);
      var2.setCommandListener(this);
      a.setCurrent(var2);
   }

   public final void c() {
      this.c = false;
   }

   public static final void d() {
      a[0] = new a.a.a.g("chars");
      a[1] = new a.a.a.g("lchars");
   }

   public final void c(int var1) {
      if (var1 <= 0) {
         this.f = 0;
      } else {
         this.f = 1000 / var1;
      }
   }

   public final void run() {
      try {
         this.a();
         a = new String[]{aeii.f.a(26), aeii.f.a(28), aeii.f.a(25), aeii.f.a(24)};
         g var1 = new g();
         this.a();
         this.a = var1;
         this.d = false;
         this.c = true;
         var1.d();
         a = new int[d * e];
         long var2 = System.currentTimeMillis();
         int var4 = 0;

         while(this.c) {
            long var6 = System.currentTimeMillis();
            if (this.isShown() && !f) {
               if (j >= 0) {
                  b(j, k);
                  if (a != null && a.getState() == 400) {
                     j = -1;
                  }
               }

               this.a.b();
               this.a();
            }

            if (a) {
               int var10 = (int)(System.currentTimeMillis() - var2);
               ++var4;
               if (var10 >= 1000) {
                  this.a = Integer.toString(var4 * 1000 / var10);
                  var2 = System.currentTimeMillis();
                  var4 = 0;
               }
            }

            int var11 = (int)(System.currentTimeMillis() - var6);
            if ((var11 = this.f - var11) > 0) {
               try {
                  Thread.sleep((long)var11);
               } catch (Exception var8) {
               }
            }
         }

         if (Main.a != null) {
            Main.a.notifyDestroyed();
            Main.a.destroyApp(true);
         }

      } catch (Exception var9) {
         var9.printStackTrace();
         this.a(var9.toString());
      }
   }

   public static final void d(int var0) {
      try {
         if (a[1]) {
            a.vibrate(var0 << 2);
         }

      } catch (Exception var1) {
      }
   }

   public static final void e() {
      a = new Player[b.length];
      b = new boolean[b.length];
   }

   public static final void e(int var0) {
      try {
         b[var0] = false;
         InputStream var1 = a(b[var0] + ".mid");
         a[var0] = Manager.createPlayer(var1, "audio/midi");
         a[var0].realize();
         a[var0].prefetch();
         b[var0] = true;
      } catch (Exception var2) {
      }
   }

   public static final void a(int var0, int var1) {
      b(var0, var1);
   }

   public static final void f() {
      try {
         if (a != null) {
            a.stop();
            a = null;
            l = -1;
         }

      } catch (Exception var0) {
      }
   }

   public static final void b(int var0, int var1) {
      try {
         if (b[var0]) {
            if (a != null) {
               a.stop();
            }

            if (a[var0] == 1 && a[0]) {
               if (var1 == 0) {
                  var1 = -1;
               }

               if (f) {
                  j = var0;
                  k = var1;
                  return;
               }

               (a = a[var0]).setLoopCount(var1);
               a.start();
               l = var0;
               m = var1;
            }

         }
      } catch (Exception var2) {
      }
   }

   public static final void f(int var0) {
      try {
         if (b[10]) {
            if (a == a[10]) {
               a.stop();
               a = null;
               l = -1;
            }

         }
      } catch (Exception var1) {
      }
   }

   public static final void g() {
      if (c == null) {
         try {
            DataInputStream var0;
            short var1 = (var0 = new DataInputStream(Main.a.getClass().getResourceAsStream("/res.pak"))).readShort();
            short var2;
            c = new String[var2 = var0.readShort()];
            int[] var3 = new int[var2];
            int[] var4 = new int[var2];

            for(int var5 = 0; var5 < var2; ++var5) {
               c[var5] = var0.readUTF();
               var3[var5] = var0.readInt() + var1;
               var4[var5] = var0.readShort();
            }

            b = new byte[c.length][];

            for(int var7 = 0; var7 < c.length; ++var7) {
               b[var7] = new byte[var4[var7]];
               var0.readFully(b[var7]);
            }

            var0.close();
            return;
         } catch (Exception var6) {
         }
      }

   }

   public static final byte[] a(String var0) {
      if (c != null) {
         for(int var1 = 0; var1 < c.length; ++var1) {
            if (var0.equals(c[var1])) {
               return b[var1];
            }
         }
      }

      try {
         InputStream var5;
         if ((var5 = Main.a.getClass().getResourceAsStream("/res/" + var0)) != null) {
            ByteArrayOutputStream var4 = new ByteArrayOutputStream();

            int var2;
            while((var2 = var5.read()) >= 0) {
               var4.write(var2);
            }

            var5.close();
            byte[] var6 = var4.toByteArray();
            var4.close();
            return var6;
         }
      } catch (IOException var3) {
      }

      return null;
   }

   public static final InputStream a(String var0) {
      if (c != null) {
         for(int var1 = 0; var1 < c.length; ++var1) {
            if (var0.equals(c[var1])) {
               return new ByteArrayInputStream(b[var1]);
            }
         }
      }

      return Main.a.getClass().getResourceAsStream("/res/" + var0);
   }

   public final void commandAction(Command var1, Displayable var2) {
      Main.a.notifyDestroyed();
   }

   static {
      b = (a = (b = Font.getFont(0, 1, 8)).getBaselinePosition()) + 6;
      a.getBaselinePosition();
      c = a.getHeight();
      a = new short[]{45, 43};
      b = new short[]{57, 57};
      a = new byte[][]{{10, 11, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, {12, -1, 11, -1, 10, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9}};
      a = new a.a.a.g[2];
      a = new Random();
      a = new boolean[]{true, true, true, true};
      j = -1;
      f = false;
      b = new String[]{"main_theme", "bg_story", "bg_good", "bg_bad", "battle_good", "battle_bad", "victory", "gameover", "game_complete"};
      a = new byte[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
   }
}
