package aeii;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class d {
   public Image a;
   private boolean a;
   private int d;
   private int e;
   public int a;
   public int b;
   private int f;
   private int g;
   public int c;

   public d() {
      super();
   }

   public static Image a(Image var0, int var1, int var2, boolean var3) {
      if (var0 == null) {
         return null;
      } else {
         int var4;
         try {
            var3 = var0.getWidth();
            var4 = var0.getHeight();
            if (var3 == var1 && var4 == var2) {
               return var0;
            }
         } catch (Throwable var16) {
            boolean var10001 = false;
            return var0;
         }

         if (var1 < 0) {
            if (var2 < 0) {
               return var0;
            }

            try {
               var1 = var3 * var2 / var4;
            } catch (Throwable var15) {
               boolean var23 = false;
               return var0;
            }
         } else {
            try {
               if (var2 < 0) {
                  var2 = var4 * var1 / var3;
               }
            } catch (Throwable var14) {
               boolean var24 = false;
               return var0;
            }
         }

         int[] var5;
         int[] var6;
         int var7;
         int var8;
         int var9;
         int var10;
         int var11;
         try {
            var7 = 1;

            do {
               var8 = var3 / var7;
               var9 = var4 / var7;
               var10 = var1 / var7;
               var11 = var2 / var7;

               try {
                  var5 = new int[var8 * var9];
                  var6 = new int[var10 * var11];
               } catch (OutOfMemoryError var12) {
                  var5 = null;
                  var6 = null;
                  ++var7;
               }
            } while(var5 == null || var6 == null);

            if (var7 == 1) {
               var0.getRGB(var5, 0, var3, 0, 0, var3, var4);
               a(var5, var6, var3, var4, var1, var2, true);
               return Image.createRGBImage(var6, var1, var2, true);
            }
         } catch (Throwable var17) {
            boolean var25 = false;
            return var0;
         }

         try {
            Image var18;
            Graphics var19 = (var18 = Image.createImage(var1, var2)).getGraphics();

            for(int var21 = 0; var21 < var7; ++var21) {
               for(int var22 = 0; var22 < var7; ++var22) {
                  var0.getRGB(var5, 0, var8, var21 * var8, var22 * var9, var8, var9);
                  a(var5, var6, var8, var9, var10, var11, false);
                  var19.drawRGB(var6, 0, var10, var21 * var10, var22 * var11, var10, var11, false);
               }
            }

            return var18;
         } catch (Throwable var13) {
            boolean var26 = false;
            return var0;
         }
      }
   }

   private static void a(int[] var0, int[] var1, int var2, int var3, int var4, int var5, boolean var6) {
      int var19 = (var2 << 16) / var4;
      int var20 = (var3 << 16) / var5;
      var3 = var19 >>> 16;
      int var7 = var20 >>> 16;
      if (var3 == 0) {
         var3 = 1;
      }

      if (var7 == 0) {
         var7 = 1;
      }

      int var8 = var3 * var7;

      try {
         if (var6) {
            for(int var23 = 0; var23 < var4; ++var23) {
               for(int var9 = 0; var9 < var5; ++var9) {
                  int var10 = var23 * var19 >>> 16;
                  int var11 = var9 * var20 >>> 16;
                  int var17 = 0;
                  int var16 = 0;
                  int var15 = 0;
                  int var14 = 0;

                  for(int var12 = 0; var12 < var3; ++var12) {
                     for(int var13 = 0; var13 < var7; ++var13) {
                        int var18 = var10 + var12 + (var11 + var13) * var2;
                        var14 += var0[var18] >>> 24;
                        var15 += var0[var18] >> 16 & 255;
                        var16 += var0[var18] >> 8 & 255;
                        var17 += var0[var18] & 255;
                     }
                  }

                  var14 /= var8;
                  var15 /= var8;
                  var16 /= var8;
                  var17 /= var8;
                  var1[var23 + var9 * var4] = var14 << 24 | var15 << 16 | var16 << 8 | var17;
               }
            }
         } else {
            for(int var24 = 0; var24 < var4; ++var24) {
               for(int var25 = 0; var25 < var5; ++var25) {
                  int var26 = var24 * var19 >>> 16;
                  int var27 = var25 * var20 >>> 16;
                  int var38 = 0;
                  int var35 = 0;
                  int var32 = 0;

                  for(int var28 = 0; var28 < var3; ++var28) {
                     for(int var29 = 0; var29 < var7; ++var29) {
                        int var40 = var26 + var28 + (var27 + var29) * var2;
                        var32 += var0[var40] >> 16 & 255;
                        var35 += var0[var40] >> 8 & 255;
                        var38 += var0[var40] & 255;
                     }
                  }

                  var32 /= var8;
                  var35 /= var8;
                  var38 /= var8;
                  var1[var24 + var25 * var4] = var32 << 16 | var35 << 8 | var38;
               }
            }
         }

      } catch (ArrayIndexOutOfBoundsException var21) {
      }
   }

   public d(d var1, int var2, int var3, int var4, int var5) {
      super();
      this.a = false;
      this.c = 0;
      this.a = var1.a;
      this.a = var4;
      this.b = var5;
      this.d = var2 * var4 + var1.d;
      this.e = var3 * var5 + var1.e;
      this.a = true;
   }

   public d(d var1, int var2) {
      super();
      this.a = false;
      this.c = 0;
      this.a = var1.a;
      this.a = var1.a;
      this.b = var1.b;
      this.d = var1.d;
      this.e = var1.e;
      this.f = var1.f;
      this.g = var1.g;
      this.a = var1.a;
      if ((var2 & 1) != 0) {
         this.c = 2;
      } else if ((var2 & 2) != 0) {
         this.c = 1;
      } else if ((var2 & 4) != 0) {
         this.c = 6;
      } else if ((var2 & 8) != 0) {
         this.c = 3;
      } else {
         if ((var2 & 16) != 0) {
            this.c = 5;
         }

      }
   }

   public d(String var1, int var2, int var3) {
      super();
      this.a = false;
      this.c = 0;
      byte[] var4 = aeii.c.a(var1 + ".png");
      this.a = a(Image.createImage(var4, 0, var4.length), var2, -1, true);
      this.a = (short)this.a.getWidth();
      this.b = (short)this.a.getHeight();
      this.a = false;
   }

   public d(String var1) {
      this(var1, -1, -1);
   }

   public void a(int var1, int var2, int var3) {
      if (!this.a) {
         int var4 = var1 & 13;
         var1 &= 50;
         if (this.c == 2) {
            if ((var4 & 4) != 0) {
               var4 = 8;
            } else if ((var4 & 8) != 0) {
               var4 = 4;
            }
         } else if (this.c == 1) {
            if ((var1 & 16) != 0) {
               var4 = 32;
            } else if ((var4 & 32) != 0) {
               var4 = 16;
            }
         }

         if (((var1 = var4 | var1) & 8) != 0) {
            this.f = var2 - this.a;
         } else if ((var1 & 1) != 0) {
            this.f = var2 - this.a >> 1;
         }

         if ((var1 & 32) != 0) {
            this.g = var3 - this.b;
            return;
         }

         if ((var1 & 2) != 0) {
            this.g = var3 - this.b >> 1;
         }
      }

   }

   public void a(int var1, int var2) {
      this.f += var1;
      this.g += var2;
   }

   public void a(Graphics var1, int var2, int var3) {
      this.a(var1, var2, var3, 20);
   }

   public void a(Graphics var1, int var2, int var3, int var4) {
      if (!this.a && this.c == 0) {
         var1.drawImage(this.a, var2 + this.f, var3 + this.g, var4);
      } else {
         var1.drawRegion(this.a, this.d, this.e, this.a, this.b, this.c, var2 + this.f, var3 + this.g, var4);
      }
   }

   public Image a() {
      return Image.createImage(this.a, this.d, this.e, this.a, this.b, this.c);
   }

   public d(String var1, int var2) {
      super();
      this.a = false;
      this.c = 0;
      byte[] var3 = null;
      if (var2 >= 0) {
         var3 = aeii.c.a(aeii.g.a[var2 / 2] + "/" + var1 + ".png");
      }

      if (var3 == null) {
         var3 = aeii.c.a(var1 + ".png");
      }

      if (var2 >= 0 && (var2 & 1) == 1) {
         byte[] var4 = new byte[var3.length];
         System.arraycopy(var3, 0, var4, 0, var3.length);
         a(var4);
         var3 = var4;
      }

      this.a = Image.createImage(var3, 0, var3.length);
      this.a = (short)this.a.getWidth();
      this.b = (short)this.a.getHeight();
   }

   public static void a(byte[] var0) {
      try {
         int var1 = 33;
         int var2 = 0;

         for(int var3 = var0.length - 3; var2 < var3; ++var2) {
            if (var0[var2] == 80 && var0[var2 + 1] == 76 && var0[var2 + 2] == 84) {
               var1 = var2 - 4;
               break;
            }
         }

         int var12 = (var0[var1] & 255) << 24 | (var0[var1 + 1] & 255) << 16 | (var0[var1 + 2] & 255) << 8 | var0[var1 + 3] & 255;
         var2 = var1 + 4;
         int var4 = -1;

         for(int var5 = 0; var5 < 4; ++var5) {
            var4 = a(var0[var2 + var5], var4);
         }

         var2 += 4;

         for(int var16 = var2; var16 < var2 + var12; var16 += 3) {
            int var6 = var0[var16] & 255;
            int var7 = var0[var16 + 1] & 255;
            int var8 = var0[var16 + 2] & 255;
            if (var6 != 244 || var7 != 244 || var8 != 230) {
               int var19;
               var6 = var19 = (var6 + var7 + var8) / 3;
               var0[var16] = (byte)var6;
               var0[var16 + 1] = (byte)var19;
               var0[var16 + 2] = (byte)var19;
            }

            var4 = a(var0[var16], var4);
            var4 = a(var0[var16 + 1], var4);
            var4 = a(var0[var16 + 2], var4);
         }

         var4 = ~var4;
         int var17 = var1 + 8 + var12;
         var0[var17] = (byte)(var4 >> 24);
         var0[var17 + 1] = (byte)(var4 >> 16);
         var0[var17 + 2] = (byte)(var4 >> 8);
         var0[var17 + 3] = (byte)var4;
      } catch (Exception var9) {
         var9.printStackTrace();
      }
   }

   public static int a(byte var0, int var1) {
      var0 &= 255;
      var1 ^= var0;

      for(int var3 = 0; var3 < 8; ++var3) {
         if ((var1 & 1) != 0) {
            var1 = var1 >>> 1 ^ -306674912;
         } else {
            var1 >>>= 1;
         }
      }

      return var1;
   }
}
