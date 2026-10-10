package aeii;

import java.io.DataInputStream;
import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

// $FF: renamed from: aeii.f
public class GameScreen {
   // $FF: renamed from: a aeii.c
   public static GameCanvas field_92;
   // $FF: renamed from: j int
   public int field_93;
   // $FF: renamed from: k int
   public int field_94;
   // $FF: renamed from: l int
   public int field_95;
   // $FF: renamed from: m int
   public int field_96;
   // $FF: renamed from: a java.lang.String[]
   private static String[] field_97;
   // $FF: renamed from: a short[]
   private static short[] field_98 = null;
   // $FF: renamed from: a int
   private static int field_99 = 360;
   // $FF: renamed from: b int
   private static int field_100 = 0;
   // $FF: renamed from: c int
   private static int field_101 = 0;

   public GameScreen() {
      super();
      this.field_93 = field_92.getWidth();
      this.field_94 = field_92.getHeight();
      this.field_95 = this.field_93 >> 1;
      this.field_96 = this.field_94 >> 1;
   }

   // $FF: renamed from: a () void
   public void method_72() {
   }

   // $FF: renamed from: b () void
   public void method_73() {
   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics) void
   public void method_74(Graphics var1) {
   }

   // $FF: renamed from: a (java.lang.String, int, javax.microedition.lcdui.Font) java.lang.String[]
   public static final String[] method_75(String var0, int var1, Font var2) {
      Vector var3 = new Vector();
      int var4 = 0;
      int var5 = var0.length();
      String var6 = null;

      int var7;
      do {
         var7 = var4;
         int var8 = var0.indexOf(10, var4);

         label41:
         do {
            int var9 = var7;
            String var10 = var6;
            var7 = method_76(var0, var7);
            if (var8 >= 0 && var8 < var7) {
               var7 = var8;
            }

            var6 = var0.substring(var4, var7).trim();
            if (var2.stringWidth(var6) > var1) {
               if (var9 == var4) {
                  var4 = var6.length() - 1;

                  while(true) {
                     if (var4 <= 0) {
                        break label41;
                     }

                     String var13 = var6.substring(0, var4);
                     if (var2.stringWidth(var13) <= var1) {
                        var7 = var9 + var4;
                        var6 = var13;
                        break label41;
                     }

                     --var4;
                  }
               }

               var7 = var9;
               var6 = var10;
               break;
            }

            if (var7 == var8) {
               ++var7;
               break;
            }
         } while(var7 < var5);

         var3.addElement(var6);
         var4 = var7;
      } while(var7 < var5);

      String[] var12 = new String[var3.size()];
      var3.copyInto(var12);
      return var12;
   }

   // $FF: renamed from: a (java.lang.String, int) int
   private static final int method_76(String var0, int var1) {
      if (method_77(var0.charAt(var1))) {
         return var1 + 1;
      } else {
         int var2;
         while((var2 = var0.indexOf(32, var1)) == 0) {
            ++var1;
         }

         int var3;
         if (var2 == -1) {
            var3 = var0.length();
         } else {
            var3 = var2 + 1;
         }

         for(int var4 = var1 + 1; var4 < var3; ++var4) {
            if (method_77(var0.charAt(var4))) {
               return var4;
            }
         }

         return var3;
      }
   }

   // $FF: renamed from: a (int) boolean
   private static final boolean method_77(int var0) {
      return var0 >= 11904 && var0 < 44032 || var0 >= 63744 && var0 < 64256 || var0 >= 65280 && var0 < 65504;
   }

   // $FF: renamed from: a (java.lang.String) int
   public static final int method_78(String var0) {
      DataInputStream var4;
      field_97 = new String[(var4 = new DataInputStream(GameMIDlet.field_628.getClass().getResourceAsStream(var0))).readInt()];
      int var1 = 0;

      for(int var2 = field_97.length; var1 < var2; ++var1) {
         String var3 = var4.readUTF();
         field_97[var1] = var3;
      }

      var4.close();
      return field_97.length;
   }

   // $FF: renamed from: a (int) java.lang.String
   public static final String method_79(int var0) {
      return method_80(var0, false);
   }

   // $FF: renamed from: a (int, boolean) java.lang.String
   public static final String method_80(int var0, boolean var1) {
      if (var0 < field_97.length) {
         String var3 = field_97[var0];
         if (var1 && (var3 = method_83(method_83(method_83(method_83(var3, "%K5", method_82(20, field_92.method_255(16)), true), "%K0", field_92.method_255(32), true), "%K7", field_92.method_255(256), true), "%K9", field_92.method_255(512), true)).indexOf("%KM") != -1) {
            StringBuffer var4 = new StringBuffer();
            String[] var2 = new String[]{field_92.method_255(1), field_92.method_255(2), field_92.method_255(4), field_92.method_255(8)};
            var4.append(method_81(17, var2));
            if (var4.length() > 0) {
               var4.append('/');
            }

            var4.append(method_79(18));
            if (var4.length() > 0) {
               var4.append('/');
            }

            var4.append(method_79(19));
            var3 = method_83(var3, "%KM", var4.toString(), true);
         }

         return var3;
      } else {
         return "?: " + var0;
      }
   }

   // $FF: renamed from: a (int, java.lang.String[]) java.lang.String
   private static String method_81(int var0, String[] var1) {
      String var3 = new String(method_79(17));

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var3 = method_83(var3, "%U", var1[var2], false);
      }

      return var3;
   }

   // $FF: renamed from: a (int, java.lang.String) java.lang.String
   public static final String method_82(int var0, String var1) {
      return method_83(method_80(var0, false), "%U", var1, false);
   }

   // $FF: renamed from: a (java.lang.String, java.lang.String, java.lang.String, boolean) java.lang.String
   private static String method_83(String var0, String var1, String var2, boolean var3) {
      var0 = var0;

      int var4;
      while((var4 = var0.indexOf(var1)) != -1) {
         var0 = var0.substring(0, var4) + var2 + var0.substring(var4 + var1.length());
         if (!var3) {
            break;
         }
      }

      return var0;
   }

   // $FF: renamed from: c () void
   public static final void method_84() {
      field_101 = (field_100 = field_99 >> 1) >> 1;
      field_98 = new short[field_99];
      int var0 = field_99 * 10000 / 2 / 31415;
      int var1 = var0 * 1024;
      int var2 = 0;

      for(int var3 = 0; var3 < field_99; ++var3) {
         int var4 = var2 / var0;
         field_98[var3] = (short)var4;
         var1 -= var4;
         var2 += var1 / var0;
      }

      field_98[180] = 0;
      field_98[270] = -1024;
   }

   // $FF: renamed from: a (int) short
   public static final short method_85(int var0) {
      var0 %= 360;
      return field_98[var0];
   }

   // $FF: renamed from: b (int) short
   public static final short method_86(int var0) {
      var0 = (var0 + field_101) % 360;
      return field_98[var0];
   }
}
