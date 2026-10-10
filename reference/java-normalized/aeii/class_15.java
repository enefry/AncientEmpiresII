package aeii;

import a.a.a.class_1;
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

// $FF: renamed from: aeii.c
public final class class_15 extends Canvas implements Runnable, CommandListener {
   // $FF: renamed from: a boolean
   public static boolean field_588;
   // $FF: renamed from: b boolean
   public static boolean field_589;
   // $FF: renamed from: a javax.microedition.lcdui.Font
   public static final Font field_590 = Font.getFont(0, 1, 8);
   // $FF: renamed from: b javax.microedition.lcdui.Font
   public static final Font field_591;
   // $FF: renamed from: a int
   public static final int field_592;
   // $FF: renamed from: b int
   public static final int field_593;
   // $FF: renamed from: c int
   public static final int field_594;
   // $FF: renamed from: a short[]
   private static short[] field_595;
   // $FF: renamed from: b short[]
   private static short[] field_596;
   // $FF: renamed from: a byte[][]
   private static byte[][] field_597;
   // $FF: renamed from: a javax.microedition.lcdui.Display
   private static Display field_598;
   // $FF: renamed from: c boolean
   private boolean field_599 = false;
   // $FF: renamed from: d boolean
   private boolean field_600 = true;
   // $FF: renamed from: f int
   private int field_601;
   // $FF: renamed from: a java.lang.String
   private String field_602 = "";
   // $FF: renamed from: a aeii.f
   public class_11 field_603;
   // $FF: renamed from: d int
   public static int field_604;
   // $FF: renamed from: e int
   public static int field_605;
   // $FF: renamed from: g int
   private int field_606 = 0;
   // $FF: renamed from: h int
   private int field_607;
   // $FF: renamed from: i int
   private int field_608 = 0;
   // $FF: renamed from: a long
   private long field_609;
   // $FF: renamed from: a a.a.a.g[]
   public static class_1[] field_610;
   // $FF: renamed from: a java.util.Random
   public static Random field_611;
   // $FF: renamed from: a boolean[]
   public static boolean[] field_612;
   // $FF: renamed from: a java.lang.String[]
   public static String[] field_613;
   // $FF: renamed from: a int[]
   private static int[] field_614;
   // $FF: renamed from: e boolean
   private boolean field_615 = false;
   // $FF: renamed from: j int
   private static int field_616;
   // $FF: renamed from: k int
   private static int field_617;
   // $FF: renamed from: f boolean
   private static boolean field_618;
   // $FF: renamed from: b java.lang.String[]
   public static final String[] field_619;
   // $FF: renamed from: a byte[]
   private static byte[] field_620;
   // $FF: renamed from: a javax.microedition.media.Player[]
   private static Player[] field_621;
   // $FF: renamed from: a javax.microedition.media.Player
   private static Player field_622;
   // $FF: renamed from: b boolean[]
   private static boolean[] field_623;
   // $FF: renamed from: l int
   private static int field_624;
   // $FF: renamed from: m int
   private static int field_625;
   // $FF: renamed from: b byte[][]
   private static byte[][] field_626;
   // $FF: renamed from: c java.lang.String[]
   private static String[] field_627;

   public class_15(MIDlet var1) {
      super();

      try {
         field_588 = "true".equalsIgnoreCase(Main.field_628.getAppProperty("Developer-Mode"));
      } catch (Exception var5) {
         field_588 = false;
      }

      try {
         field_589 = "true".equalsIgnoreCase(Main.field_628.getAppProperty("Disable-Intro"));
      } catch (Exception var4) {
         field_589 = false;
      }

      try {
         this.method_266(Integer.parseInt(Main.field_628.getAppProperty("Override-FPS")));
      } catch (Exception var3) {
         this.method_266(15);
      }

      try {
         this.setFullScreenMode(true);
         class_11.field_92 = this;
         class_11.method_78("/lang.dat");
         field_604 = this.getWidth();
         field_605 = this.getHeight();
         (field_598 = Display.getDisplay(var1)).setCurrent(this);
         (new Thread(this)).start();
      } catch (Exception var2) {
         var2.printStackTrace();
         this.method_263(var2.toString());
      }
   }

   // $FF: renamed from: a (int) int
   public static final int method_233(int var0) {
      return method_234(0, var0);
   }

   // $FF: renamed from: a (int, int) int
   public static final int method_234(int var0, int var1) {
      return var0 + Math.abs(field_611.nextInt()) % (var1 - var0);
   }

   // $FF: renamed from: a () int
   public static final int method_235() {
      return field_611.nextInt();
   }

   // $FF: renamed from: a (int[]) int
   public static final int method_236(int[] var0) {
      int var1 = var0[var0.length - 1];
      var1 = method_234(0, var1);

      for(int var2 = 0; var2 < var0.length; ++var2) {
         if (var1 < var0[var2]) {
            return var2;
         }
      }

      return -1;
   }

   // $FF: renamed from: a (int[], boolean) void
   public static final void method_237(int[] var0, boolean var1) {
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

   // $FF: renamed from: a (java.io.InputStream) java.lang.String
   public static String method_238(InputStream var0) {
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

   // $FF: renamed from: a (java.lang.String, char) java.lang.String[]
   public static String[] method_239(String var0, char var1) {
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

   // $FF: renamed from: a (java.lang.String, int) byte[]
   public static final byte[] method_240(String var0, int var1) {
      try {
         RecordStore var3;
         byte[] var4 = (var3 = RecordStore.openRecordStore(var0, false)).getRecord(var1 + 1);
         var3.closeRecordStore();
         return var4;
      } catch (Exception var2) {
         return null;
      }
   }

   // $FF: renamed from: a (java.lang.String, int, byte[]) void
   public static final void method_241(String var0, int var1, byte[] var2) {
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

   // $FF: renamed from: a (java.lang.String, byte[]) int
   public static final int method_242(String var0, byte[] var1) {
      try {
         RecordStore var3;
         int var4 = (var3 = RecordStore.openRecordStore(var0, true)).addRecord(var1, 0, var1.length);
         var3.closeRecordStore();
         return var4 - 1;
      } catch (Exception var2) {
         return -1;
      }
   }

   // $FF: renamed from: a (java.lang.String, int) void
   public static final void method_243(String var0, int var1) {
      try {
         RecordStore var3;
         (var3 = RecordStore.openRecordStore(var0, true)).deleteRecord(var1 + 1);
         var3.closeRecordStore();
      } catch (Exception var2) {
      }
   }

   // $FF: renamed from: a (java.lang.String) int
   public static final int method_244(String var0) {
      int var1 = 0;

      try {
         RecordStore var3;
         var1 = (var3 = RecordStore.openRecordStore(var0, true)).getSizeAvailable();
         var3.closeRecordStore();
      } catch (Exception var2) {
      }

      return var1;
   }

   // $FF: renamed from: a (byte, java.lang.String) int
   public static final int method_245(byte var0, String var1) {
      return field_610[var0].field_6 * var1.length();
   }

   // $FF: renamed from: a (byte) int
   public static final int method_246(byte var0) {
      return field_610[var0].field_7;
   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics, int) void
   public static final void method_247(Graphics var0, int var1) {
      var0.setColor(var1);
   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics, int, int, int, int, int) void
   public static final void method_248(Graphics var0, int var1, int var2, int var3, int var4, int var5) {
      for(int var6 = var4 * var5 - 1; var6 >= 0; --var6) {
         field_614[var6] = var1;
      }

      var0.drawRGB(field_614, 0, var4, 0, 0, var4, var5, true);
   }

   public final void showNotify() {
      this.setFullScreenMode(true);
      this.field_615 = false;
      field_618 = false;
      this.method_257();
      if (this.field_603 != null) {
         this.field_603.method_72();
      }

   }

   public final void hideNotify() {
      this.method_257();
      if (this.field_603 != null) {
         field_618 = true;
         if (field_622 != null && field_622.getState() == 400 && field_620[field_624] == 1) {
            field_616 = field_624;
            field_617 = field_625;
         }

         method_271();
         this.field_615 = false;
      }

   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics, java.lang.String, int, int, int, int) void
   public static final void method_249(Graphics var0, String var1, int var2, int var3, int var4, int var5) {
      if ((var5 & 8) != 0) {
         var2 -= method_245((byte)var4, var1);
      } else if ((var5 & 1) != 0) {
         var2 -= method_245((byte)var4, var1) / 2;
      }

      if ((var5 & 32) != 0) {
         var3 -= method_246((byte)var4);
      } else if ((var5 & 2) != 0) {
         var3 -= method_246((byte)var4) / 2;
      }

      method_250(var0, var1, var2, var3, var4);
   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics, java.lang.String, int, int, int) void
   public static final void method_250(Graphics var0, String var1, int var2, int var3, int var4) {
      int var5 = 0;

      for(int var6 = var1.length(); var5 < var6; ++var5) {
         char var7;
         if ((var7 = var1.charAt(var5)) >= field_595[var4] && var7 <= field_596[var4]) {
            byte var8;
            if ((var8 = field_597[var4][var7 - field_595[var4]]) != -1) {
               field_610[var4].method_10(var8);
               field_610[var4].method_17(var0, var2, var3);
               var2 += field_610[var4].field_6;
            } else {
               byte[] var9 = new byte[]{(byte)var7};
               String var10 = new String(var9);
               var0.drawString(var10, var2, var3, 20);
               var2 += var0.getFont().stringWidth(var10);
            }
         }
      }

   }

   // $FF: renamed from: b (javax.microedition.lcdui.Graphics, java.lang.String, int, int, int) void
   public static final void method_251(Graphics var0, String var1, int var2, int var3, int var4) {
      var0.drawString(var1, var2, var3 - 2, var4);
   }

   // $FF: renamed from: a (javax.microedition.lcdui.Displayable) void
   public static void method_252(Displayable var0) {
      field_598.setCurrent(var0);
   }

   // $FF: renamed from: a (aeii.f) void
   public final void method_253(class_11 var1) {
      this.method_257();
      var1.method_72();
      this.field_603 = var1;
   }

   // $FF: renamed from: a () void
   public final void method_254() {
      this.repaint();
      this.serviceRepaints();
   }

   public final void paint(Graphics var1) {
      if (this.field_600) {
         var1.setColor(16777215);
         var1.fillRect(0, 0, field_604, field_605);
         var1.setFont(field_591);
         var1.setColor(0);
         var1.drawString(class_11.method_79(58), field_604 / 2, field_605 / 2 - 1, 33);
      } else {
         this.field_603.method_74(var1);
      }

      if (field_588) {
         var1.setFont(field_591);
         class_13.method_164(var1, this.field_602, 2, 2, 20, -1, 0);
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

   // $FF: renamed from: a (int) java.lang.String
   public final String method_255(int var1) {
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
      this.method_261(var1);
   }

   // $FF: renamed from: a () boolean
   public final boolean method_256() {
      return this.field_606 != 0;
   }

   // $FF: renamed from: b () void
   public final void method_257() {
      this.field_608 = 0;
      this.field_606 = 0;
      this.field_607 = 0;
   }

   // $FF: renamed from: a (int) boolean
   public final boolean method_258(int var1) {
      boolean var2 = (this.field_607 & var1) != 0;
      this.field_607 &= ~var1;
      return var2;
   }

   // $FF: renamed from: b (int) boolean
   public final boolean method_259(int var1) {
      return (this.field_606 & var1) != 0;
   }

   public final void keyReleased(int var1) {
      this.method_262(this.getGameAction(var1));
   }

   // $FF: renamed from: c (int) boolean
   public final boolean method_260(int var1) {
      return this.field_608 == var1 && System.currentTimeMillis() - this.field_609 >= 400L;
   }

   // $FF: renamed from: a (int) void
   public final void method_261(int var1) {
      this.field_608 = var1;
      this.field_609 = System.currentTimeMillis();
      this.field_606 |= var1;
      this.field_607 |= var1;
   }

   // $FF: renamed from: b (int) void
   public final void method_262(int var1) {
      if (var1 == this.field_608) {
         this.field_608 = 0;
      }

      this.field_606 &= ~var1;
   }

   // $FF: renamed from: a (java.lang.String) void
   private void method_263(String var1) {
      this.field_599 = false;
      Form var2;
      (var2 = new Form("Fatal error!")).append(var1);
      Command var3 = new Command("Exit", 7, 1);
      var2.addCommand(var3);
      var2.setCommandListener(this);
      field_598.setCurrent(var2);
   }

   // $FF: renamed from: c () void
   public final void method_264() {
      this.field_599 = false;
   }

   // $FF: renamed from: d () void
   public static final void method_265() {
      field_610[0] = new class_1("chars");
      field_610[1] = new class_1("lchars");
   }

   // $FF: renamed from: c (int) void
   public final void method_266(int var1) {
      if (var1 <= 0) {
         this.field_601 = 0;
      } else {
         this.field_601 = 1000 / var1;
      }
   }

   public final void run() {
      try {
         this.method_254();
         field_613 = new String[]{class_11.method_79(26), class_11.method_79(28), class_11.method_79(25), class_11.method_79(24)};
         class_13 var1 = new class_13();
         this.method_254();
         this.field_603 = var1;
         this.field_600 = false;
         this.field_599 = true;
         var1.method_108();
         field_614 = new int[field_604 * field_605];
         long var2 = System.currentTimeMillis();
         int var4 = 0;

         while(this.field_599) {
            long var6 = System.currentTimeMillis();
            if (this.isShown() && !field_618) {
               if (field_616 >= 0) {
                  method_272(field_616, field_617);
                  if (field_622 != null && field_622.getState() == 400) {
                     field_616 = -1;
                  }
               }

               this.field_603.method_73();
               this.method_254();
            }

            if (field_588) {
               int var10 = (int)(System.currentTimeMillis() - var2);
               ++var4;
               if (var10 >= 1000) {
                  this.field_602 = Integer.toString(var4 * 1000 / var10);
                  var2 = System.currentTimeMillis();
                  var4 = 0;
               }
            }

            int var11 = (int)(System.currentTimeMillis() - var6);
            if ((var11 = this.field_601 - var11) > 0) {
               try {
                  Thread.sleep((long)var11);
               } catch (Exception var8) {
               }
            }
         }

         if (Main.field_628 != null) {
            Main.field_628.notifyDestroyed();
            Main.field_628.destroyApp(true);
         }

      } catch (Exception var9) {
         var9.printStackTrace();
         this.method_263(var9.toString());
      }
   }

   // $FF: renamed from: d (int) void
   public static final void method_267(int var0) {
      try {
         if (field_612[1]) {
            field_598.vibrate(var0 << 2);
         }

      } catch (Exception var1) {
      }
   }

   // $FF: renamed from: e () void
   public static final void method_268() {
      field_621 = new Player[field_619.length];
      field_623 = new boolean[field_619.length];
   }

   // $FF: renamed from: e (int) void
   public static final void method_269(int var0) {
      try {
         field_623[var0] = false;
         InputStream var1 = method_276(field_619[var0] + ".mid");
         field_621[var0] = Manager.createPlayer(var1, "audio/midi");
         field_621[var0].realize();
         field_621[var0].prefetch();
         field_623[var0] = true;
      } catch (Exception var2) {
      }
   }

   // $FF: renamed from: a (int, int) void
   public static final void method_270(int var0, int var1) {
      method_272(var0, var1);
   }

   // $FF: renamed from: f () void
   public static final void method_271() {
      try {
         if (field_622 != null) {
            field_622.stop();
            field_622 = null;
            field_624 = -1;
         }

      } catch (Exception var0) {
      }
   }

   // $FF: renamed from: b (int, int) void
   public static final void method_272(int var0, int var1) {
      try {
         if (field_623[var0]) {
            if (field_622 != null) {
               field_622.stop();
            }

            if (field_620[var0] == 1 && field_612[0]) {
               if (var1 == 0) {
                  var1 = -1;
               }

               if (field_618) {
                  field_616 = var0;
                  field_617 = var1;
                  return;
               }

               (field_622 = field_621[var0]).setLoopCount(var1);
               field_622.start();
               field_624 = var0;
               field_625 = var1;
            }

         }
      } catch (Exception var2) {
      }
   }

   // $FF: renamed from: f (int) void
   public static final void method_273(int var0) {
      try {
         if (field_623[10]) {
            if (field_622 == field_621[10]) {
               field_622.stop();
               field_622 = null;
               field_624 = -1;
            }

         }
      } catch (Exception var1) {
      }
   }

   // $FF: renamed from: g () void
   public static final void method_274() {
      if (field_627 == null) {
         try {
            DataInputStream var0;
            short var1 = (var0 = new DataInputStream(Main.field_628.getClass().getResourceAsStream("/res.pak"))).readShort();
            short var2;
            field_627 = new String[var2 = var0.readShort()];
            int[] var3 = new int[var2];
            int[] var4 = new int[var2];

            for(int var5 = 0; var5 < var2; ++var5) {
               field_627[var5] = var0.readUTF();
               var3[var5] = var0.readInt() + var1;
               var4[var5] = var0.readShort();
            }

            field_626 = new byte[field_627.length][];

            for(int var7 = 0; var7 < field_627.length; ++var7) {
               field_626[var7] = new byte[var4[var7]];
               var0.readFully(field_626[var7]);
            }

            var0.close();
            return;
         } catch (Exception var6) {
         }
      }

   }

   // $FF: renamed from: a (java.lang.String) byte[]
   public static final byte[] method_275(String var0) {
      if (field_627 != null) {
         for(int var1 = 0; var1 < field_627.length; ++var1) {
            if (var0.equals(field_627[var1])) {
               return field_626[var1];
            }
         }
      }

      try {
         InputStream var5;
         if ((var5 = Main.field_628.getClass().getResourceAsStream("/res/" + var0)) != null) {
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

   // $FF: renamed from: a (java.lang.String) java.io.InputStream
   public static final InputStream method_276(String var0) {
      if (field_627 != null) {
         for(int var1 = 0; var1 < field_627.length; ++var1) {
            if (var0.equals(field_627[var1])) {
               return new ByteArrayInputStream(field_626[var1]);
            }
         }
      }

      return Main.field_628.getClass().getResourceAsStream("/res/" + var0);
   }

   public final void commandAction(Command var1, Displayable var2) {
      Main.field_628.notifyDestroyed();
   }

   static {
      field_593 = (field_592 = (field_591 = Font.getFont(0, 1, 8)).getBaselinePosition()) + 6;
      field_590.getBaselinePosition();
      field_594 = field_590.getHeight();
      field_595 = new short[]{45, 43};
      field_596 = new short[]{57, 57};
      field_597 = new byte[][]{{10, 11, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, {12, -1, 11, -1, 10, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9}};
      field_610 = new class_1[2];
      field_611 = new Random();
      field_612 = new boolean[]{true, true, true, true};
      field_616 = -1;
      field_618 = false;
      field_619 = new String[]{"main_theme", "bg_story", "bg_good", "bg_bad", "battle_good", "battle_bad", "victory", "gameover", "game_complete"};
      field_620 = new byte[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
   }
}
