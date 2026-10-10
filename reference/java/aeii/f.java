package aeii;

import java.io.DataInputStream;
import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class f {
   public static c a;
   public int j;
   public int k;
   public int l;
   public int m;
   private static String[] a;
   private static short[] a = null;
   private static int a = 360;
   private static int b = 0;
   private static int c = 0;

   public f() {
      super();
      this.j = a.getWidth();
      this.k = a.getHeight();
      this.l = this.j >> 1;
      this.m = this.k >> 1;
   }

   public void a() {
   }

   public void b() {
   }

   public void a(Graphics var1) {
   }

   public static final String[] a(String var0, int var1, Font var2) {
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
            var7 = a(var0, var7);
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

   private static final int a(String var0, int var1) {
      if (a(var0.charAt(var1))) {
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
            if (a(var0.charAt(var4))) {
               return var4;
            }
         }

         return var3;
      }
   }

   private static final boolean a(int var0) {
      return var0 >= 11904 && var0 < 44032 || var0 >= 63744 && var0 < 64256 || var0 >= 65280 && var0 < 65504;
   }

   public static final int a(String var0) {
      DataInputStream var4;
      a = new String[(var4 = new DataInputStream(Main.a.getClass().getResourceAsStream(var0))).readInt()];
      int var1 = 0;

      for(int var2 = a.length; var1 < var2; ++var1) {
         String var3 = var4.readUTF();
         a[var1] = var3;
      }

      var4.close();
      return a.length;
   }

   public static final String a(int var0) {
      return a(var0, false);
   }

   public static final String a(int var0, boolean var1) {
      if (var0 < a.length) {
         String var3 = a[var0];
         if (var1 && (var3 = a(a(a(a(var3, "%K5", a(20, (String)a.a((int)16)), true), "%K0", a.a((int)32), true), "%K7", a.a((int)256), true), "%K9", a.a((int)512), true)).indexOf("%KM") != -1) {
            StringBuffer var4 = new StringBuffer();
            String[] var2 = new String[]{a.a((int)1), a.a((int)2), a.a((int)4), a.a((int)8)};
            var4.append(a(17, (String[])var2));
            if (var4.length() > 0) {
               var4.append('/');
            }

            var4.append(a(18));
            if (var4.length() > 0) {
               var4.append('/');
            }

            var4.append(a(19));
            var3 = a(var3, "%KM", var4.toString(), true);
         }

         return var3;
      } else {
         return "?: " + var0;
      }
   }

   private static String a(int var0, String[] var1) {
      String var3 = new String(a(17));

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var3 = a(var3, "%U", var1[var2], false);
      }

      return var3;
   }

   public static final String a(int var0, String var1) {
      return a(a(var0, false), "%U", var1, false);
   }

   private static String a(String var0, String var1, String var2, boolean var3) {
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

   public static final void c() {
      c = (b = a >> 1) >> 1;
      a = new short[a];
      int var0 = a * 10000 / 2 / 31415;
      int var1 = var0 * 1024;
      int var2 = 0;

      for(int var3 = 0; var3 < a; ++var3) {
         int var4 = var2 / var0;
         a[var3] = (short)var4;
         var1 -= var4;
         var2 += var1 / var0;
      }

      a[180] = 0;
      a[270] = -1024;
   }

   public static final short a(int var0) {
      var0 %= 360;
      return a[var0];
   }

   public static final short b(int var0) {
      var0 = (var0 + c) % 360;
      return a[var0];
   }
}
