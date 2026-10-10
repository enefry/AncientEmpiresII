package aeii;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

// $FF: renamed from: aeii.d
public class SpriteImage {
   // $FF: renamed from: a javax.microedition.lcdui.Image
   public Image field_630;
   // $FF: renamed from: a boolean
   private boolean field_631;
   // $FF: renamed from: d int
   private int field_632;
   // $FF: renamed from: e int
   private int field_633;
   // $FF: renamed from: a int
   public int field_634;
   // $FF: renamed from: b int
   public int field_635;
   // $FF: renamed from: f int
   private int field_636;
   // $FF: renamed from: g int
   private int field_637;
   // $FF: renamed from: c int
   public int field_638;

   public SpriteImage() {
      super();
   }

   // $FF: renamed from: a (javax.microedition.lcdui.Image, int, int, boolean) javax.microedition.lcdui.Image
   public static Image method_277(Image var0, int var1, int var2, boolean var3) {
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
               method_278(var5, var6, var3, var4, var1, var2, true);
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
                  method_278(var5, var6, var8, var9, var10, var11, false);
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

   // $FF: renamed from: a (int[], int[], int, int, int, int, boolean) void
   private static void method_278(int[] var0, int[] var1, int var2, int var3, int var4, int var5, boolean var6) {
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

   public SpriteImage(SpriteImage var1, int var2, int var3, int var4, int var5) {
      super();
      this.field_631 = false;
      this.field_638 = 0;
      this.field_630 = var1.field_630;
      this.field_634 = var4;
      this.field_635 = var5;
      this.field_632 = var2 * var4 + var1.field_632;
      this.field_633 = var3 * var5 + var1.field_633;
      this.field_631 = true;
   }

   public SpriteImage(SpriteImage var1, int var2) {
      super();
      this.field_631 = false;
      this.field_638 = 0;
      this.field_630 = var1.field_630;
      this.field_634 = var1.field_634;
      this.field_635 = var1.field_635;
      this.field_632 = var1.field_632;
      this.field_633 = var1.field_633;
      this.field_636 = var1.field_636;
      this.field_637 = var1.field_637;
      this.field_631 = var1.field_631;
      if ((var2 & 1) != 0) {
         this.field_638 = 2;
      } else if ((var2 & 2) != 0) {
         this.field_638 = 1;
      } else if ((var2 & 4) != 0) {
         this.field_638 = 6;
      } else if ((var2 & 8) != 0) {
         this.field_638 = 3;
      } else {
         if ((var2 & 16) != 0) {
            this.field_638 = 5;
         }

      }
   }

   public SpriteImage(String var1, int var2, int var3) {
      super();
      this.field_631 = false;
      this.field_638 = 0;
      byte[] var4 = GameCanvas.method_275(var1 + ".png");
      this.field_630 = method_277(Image.createImage(var4, 0, var4.length), var2, -1, true);
      this.field_634 = (short)this.field_630.getWidth();
      this.field_635 = (short)this.field_630.getHeight();
      this.field_631 = false;
   }

   public SpriteImage(String var1) {
      this(var1, -1, -1);
   }

   // $FF: renamed from: a (int, int, int) void
   public void method_279(int var1, int var2, int var3) {
      if (!this.field_631) {
         int var4 = var1 & 13;
         var1 &= 50;
         if (this.field_638 == 2) {
            if ((var4 & 4) != 0) {
               var4 = 8;
            } else if ((var4 & 8) != 0) {
               var4 = 4;
            }
         } else if (this.field_638 == 1) {
            if ((var1 & 16) != 0) {
               var4 = 32;
            } else if ((var4 & 32) != 0) {
               var4 = 16;
            }
         }

         if (((var1 = var4 | var1) & 8) != 0) {
            this.field_636 = var2 - this.field_634;
         } else if ((var1 & 1) != 0) {
            this.field_636 = var2 - this.field_634 >> 1;
         }

         if ((var1 & 32) != 0) {
            this.field_637 = var3 - this.field_635;
            return;
         }

         if ((var1 & 2) != 0) {
            this.field_637 = var3 - this.field_635 >> 1;
         }
      }

   }

   // $FF: renamed from: a (int, int) void
   public void method_280(int var1, int var2) {
      this.field_636 += var1;
      this.field_637 += var2;
   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics, int, int) void
   public void method_281(Graphics var1, int var2, int var3) {
      this.method_282(var1, var2, var3, 20);
   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics, int, int, int) void
   public void method_282(Graphics var1, int var2, int var3, int var4) {
      if (!this.field_631 && this.field_638 == 0) {
         var1.drawImage(this.field_630, var2 + this.field_636, var3 + this.field_637, var4);
      } else {
         var1.drawRegion(this.field_630, this.field_632, this.field_633, this.field_634, this.field_635, this.field_638, var2 + this.field_636, var3 + this.field_637, var4);
      }
   }

   // $FF: renamed from: a () javax.microedition.lcdui.Image
   public Image method_283() {
      return Image.createImage(this.field_630, this.field_632, this.field_633, this.field_634, this.field_635, this.field_638);
   }

   public SpriteImage(String var1, int var2) {
      super();
      this.field_631 = false;
      this.field_638 = 0;
      byte[] var3 = null;
      if (var2 >= 0) {
         var3 = GameCanvas.method_275(GameController.field_215[var2 / 2] + "/" + var1 + ".png");
      }

      if (var3 == null) {
         var3 = GameCanvas.method_275(var1 + ".png");
      }

      if (var2 >= 0 && (var2 & 1) == 1) {
         byte[] var4 = new byte[var3.length];
         System.arraycopy(var3, 0, var4, 0, var3.length);
         method_284(var4);
         var3 = var4;
      }

      this.field_630 = Image.createImage(var3, 0, var3.length);
      this.field_634 = (short)this.field_630.getWidth();
      this.field_635 = (short)this.field_630.getHeight();
   }

   // $FF: renamed from: a (byte[]) void
   public static void method_284(byte[] var0) {
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
            var4 = method_285(var0[var2 + var5], var4);
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

            var4 = method_285(var0[var16], var4);
            var4 = method_285(var0[var16 + 1], var4);
            var4 = method_285(var0[var16 + 2], var4);
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

   // $FF: renamed from: a (byte, int) int
   public static int method_285(byte var0, int var1) {
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
