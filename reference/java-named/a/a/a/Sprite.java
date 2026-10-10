package a.a.a;

import aeii.GameCanvas;
import aeii.SpriteImage;
import java.io.InputStream;
import javax.microedition.io.Connection;
import javax.microedition.io.Connector;
import javax.microedition.lcdui.Graphics;

// $FF: renamed from: a.a.a.g
public class Sprite {
   // $FF: renamed from: a aeii.d[]
   public SpriteImage[] field_0;
   // $FF: renamed from: a byte[]
   private byte[] field_1;
   // $FF: renamed from: l int
   public int field_2;
   // $FF: renamed from: m int
   public int field_3;
   // $FF: renamed from: n int
   public int field_4;
   // $FF: renamed from: a boolean
   public boolean field_5;
   // $FF: renamed from: o int
   public int field_6;
   // $FF: renamed from: p int
   public int field_7;
   // $FF: renamed from: b byte[][]
   public byte[][] field_8;
   // $FF: renamed from: q int
   public int field_9;
   // $FF: renamed from: k byte
   public byte field_10;
   // $FF: renamed from: r int
   public int field_11;
   // $FF: renamed from: s int
   public int field_12;
   // $FF: renamed from: t int
   public int field_13;
   // $FF: renamed from: u int
   public int field_14;
   // $FF: renamed from: v int
   public int field_15;
   // $FF: renamed from: w int
   public int field_16;
   // $FF: renamed from: b boolean
   public boolean field_17;
   // $FF: renamed from: c boolean
   public boolean field_18;
   // $FF: renamed from: d boolean
   public boolean field_19;
   // $FF: renamed from: x int
   public int field_20;
   // $FF: renamed from: l byte
   public byte field_21;
   // $FF: renamed from: e boolean
   public boolean field_22;
   // $FF: renamed from: y int
   public int field_23;
   // $FF: renamed from: b java.lang.String
   public String field_24;
   // $FF: renamed from: z int
   public int field_25;
   // $FF: renamed from: a a.a.a.g
   public Sprite field_26;
   // $FF: renamed from: b a.a.a.g
   public Sprite field_27;
   // $FF: renamed from: a int[][]
   public int[][] field_28;
   // $FF: renamed from: a short[][]
   public short[][] field_29;
   // $FF: renamed from: A int
   public int field_30;
   // $FF: renamed from: b byte[]
   public byte[] field_31;
   // $FF: renamed from: a boolean[]
   public boolean[] field_32;

   public Sprite() {
      super();
   }

   // $FF: renamed from: a (java.lang.String) javax.microedition.io.Connection
   public static Connection method_4(String var0) {
      return (Connection)(var0.startsWith("file://") ? method_6(Connector.open(var0)) : Connector.open(var0));
   }

   // $FF: renamed from: a (java.lang.String, int) javax.microedition.io.Connection
   public static Connection method_5(String var0, int var1) {
      return (Connection)(var0.startsWith("file://") ? method_6(Connector.open(var0, 1)) : Connector.open(var0, 1));
   }

   // $FF: renamed from: a (javax.microedition.io.Connection) a.a.a.c
   private static GameFileConnection method_6(Connection var0) {
      switch (FileSystemSupport.method_61()) {
         case 1:
            return new Jsr75FileConnectionAdapter(var0);
         case 2:
            return new SiemensFileConnectionAdapter(var0);
         case 3:
            return new MotorolaFileConnectionAdapter(var0);
         case 4:
            return new MotorolaFilePackageConnectionAdapter(var0);
         default:
            return null;
      }
   }

   public Sprite(String var1) {
      super();
      this.field_2 = 0;
      this.field_3 = 0;
      this.field_4 = 0;
      this.field_5 = true;
      this.field_10 = 0;
      this.field_11 = -1;
      this.field_17 = true;
      this.field_20 = -1;
      this.field_21 = -1;
      this.field_30 = 16769024;
      this.method_7(var1, -1);
   }

   public Sprite(SpriteImage[] var1) {
      super();
      this.field_2 = 0;
      this.field_3 = 0;
      this.field_4 = 0;
      this.field_5 = true;
      this.field_10 = 0;
      this.field_11 = -1;
      this.field_17 = true;
      this.field_20 = -1;
      this.field_21 = -1;
      this.field_30 = 16769024;
      this.field_0 = var1;
      this.field_1 = new byte[this.field_0.length];

      for(byte var2 = 0; var2 < this.field_0.length; this.field_1[var2] = var2++) {
      }

      this.field_6 = this.field_0[0].field_634;
      this.field_7 = this.field_0[0].field_635;
   }

   public Sprite(String var1, byte var2) {
      super();
      this.field_2 = 0;
      this.field_3 = 0;
      this.field_4 = 0;
      this.field_5 = true;
      this.field_10 = 0;
      this.field_11 = -1;
      this.field_17 = true;
      this.field_20 = -1;
      this.field_21 = -1;
      this.field_30 = 16769024;
      this.method_7(var1, var2);
   }

   // $FF: renamed from: a (java.lang.String, int) void
   private void method_7(String var1, int var2) {
      try {
         byte var3 = 0;
         int[][] var4 = null;
         int[][] var5 = null;
         int var6 = 0;
         InputStream var7;
         String var20;
         if ((var7 = GameCanvas.method_276(var1 + ".sprite")) != null) {
            while((var20 = GameCanvas.method_238(var7)) != null) {
               int var27;
               if ((var27 = var20.indexOf(59)) >= 0) {
                  var20 = var20.substring(0, var27);
               }

               if ((var20 = var20.trim()).length() != 0) {
                  String[] var18;
                  if ((var18 = GameCanvas.method_239(var20, ' '))[0].equalsIgnoreCase("FrameCount")) {
                     var4 = new int[var3 = (byte)Integer.parseInt(var18[1])][2];
                     var5 = new int[var3][2];
                  } else if (var18[0].equalsIgnoreCase("FrameWidth")) {
                     this.field_6 = Integer.parseInt(var18[1]);
                  } else if (var18[0].equalsIgnoreCase("FrameHeight")) {
                     this.field_7 = Integer.parseInt(var18[1]);
                  } else if (var18[0].equalsIgnoreCase("FrameDef")) {
                     var27 = Integer.parseInt(var18[1]);
                     var4[var27][0] = Integer.parseInt(var18[2]);
                     var4[var27][1] = Integer.parseInt(var18[3]);
                  } else if (var18[0].equalsIgnoreCase("Align")) {
                     var6 = Integer.parseInt(var18[1]);
                  } else if (var18[0].equalsIgnoreCase("FrameSeqCount")) {
                     if ((var27 = Integer.parseInt(var18[1])) > 0) {
                        this.field_8 = new byte[var27][];
                     }
                  } else if (var18[0].equalsIgnoreCase("FrameDelay")) {
                     this.field_13 = Integer.parseInt(var18[1]) * 50;
                  } else if (var18[0].equalsIgnoreCase("FrameSeq")) {
                     var27 = Integer.parseInt(var18[1]);
                     this.field_8[var27] = new byte[Integer.parseInt(var18[2])];

                     for(int var35 = 0; var35 < this.field_8[var27].length; ++var35) {
                        this.field_8[var27][var35] = (byte)Integer.parseInt(var18[var35 + 3]);
                     }
                  } else if (var18[0].equalsIgnoreCase("Translate")) {
                     var27 = Integer.parseInt(var18[1]);
                     var5[var27][0] = Integer.parseInt(var18[2]);
                     var5[var27][1] = Integer.parseInt(var18[3]);
                  }
               }
            }
         } else {
            var4 = new int[var3 = (byte)(var7 = GameCanvas.method_276(var1 + ".sprbin")).read()][2];
            var5 = new int[var3][2];
            this.field_6 = (byte)var7.read();
            this.field_7 = (byte)var7.read();

            for(int var12 = 0; var12 < var3; ++var12) {
               var4[var12][0] = var7.read();
               var4[var12][1] = var7.read();
            }

            var6 = var7.read();
            int var8;
            if ((var8 = var7.read()) > 0) {
               this.field_8 = new byte[var8][];
               this.field_13 = var7.read() * 50;

               for(int var11 = 0; var11 < var8; ++var11) {
                  int var9 = var7.read();
                  this.field_8[var11] = new byte[var9];

                  for(int var10 = 0; var10 < var9; ++var10) {
                     this.field_8[var11][var10] = (byte)var7.read();
                  }
               }
            }

            for(int var34 = 0; var34 < var3; ++var34) {
               byte var13 = (byte)var7.read();
               byte var14 = (byte)var7.read();
               if (var13 == -1 || var14 == -1) {
                  break;
               }

               var5[var34][0] = var13;
               var5[var34][1] = var14;
            }
         }

         var7.close();
         this.field_0 = new SpriteImage[var3];
         SpriteImage[] var19 = new SpriteImage[var3];

         try {
            SpriteImage var22;
            int var33 = (var22 = new SpriteImage(var1, var2)).field_634 / this.field_6;
            int var37 = var22.field_635 / this.field_7;
            int var38 = 0;

            for(int var39 = 0; var39 < var37; ++var39) {
               for(int var40 = 0; var40 < var33; ++var40) {
                  var19[var38] = new SpriteImage(var22, var40, var39, this.field_6, this.field_7);
                  ++var38;
               }
            }
         } catch (Exception var16) {
            try {
               for(int var32 = 0; var32 < var3; ++var32) {
                  StringBuffer var36;
                  (var36 = new StringBuffer(var1)).append('_');
                  if (var32 < 10) {
                     var36.append('0');
                  }

                  var36.append(var32);
                  if (var2 < 0) {
                     var19[var32] = new SpriteImage(var36.toString());
                  } else {
                     var19[var32] = new SpriteImage(var36.toString(), var2);
                  }
               }
            } catch (Exception var15) {
            }
         }

         for(int var23 = 0; var23 < var3; ++var23) {
            this.field_0[var23] = new SpriteImage(var19[var4[var23][0]], var4[var23][1]);
         }

         if (var6 > 0) {
            for(int var25 = 0; var25 < var3; ++var25) {
               this.field_0[var25].method_279(var6, this.field_6, this.field_7);
               this.field_0[var25].method_280(var5[var25][0], var5[var25][1]);
            }
         } else {
            for(int var24 = 0; var24 < var3; ++var24) {
               this.field_0[var24].method_280(var5[var24][0], var5[var24][1]);
            }
         }

         if (this.field_8 == null) {
            this.field_1 = new byte[this.field_0.length];

            for(byte var26 = 0; var26 < this.field_0.length; this.field_1[var26] = var26++) {
            }

            return;
         }

         this.field_1 = this.field_8[0];
      } catch (Exception var17) {
         var17.printStackTrace();
      }

   }

   public Sprite(Sprite var1) {
      super();
      this.field_2 = 0;
      this.field_3 = 0;
      this.field_4 = 0;
      this.field_5 = true;
      this.field_10 = 0;
      this.field_11 = -1;
      this.field_17 = true;
      this.field_20 = -1;
      this.field_21 = -1;
      this.field_30 = 16769024;
      this.field_0 = var1.field_0;
      this.field_1 = var1.field_1;
      this.field_2 = var1.field_2;
      this.field_3 = var1.field_3;
      this.field_4 = var1.field_4;
      this.field_9 = var1.field_9;
      this.field_5 = var1.field_5;
      this.field_6 = var1.field_6;
      this.field_7 = var1.field_7;
      this.field_13 = var1.field_13;
      this.field_8 = var1.field_8;
   }

   public Sprite(int var1, int var2) {
      super();
      this.field_2 = 0;
      this.field_3 = 0;
      this.field_4 = 0;
      this.field_5 = true;
      this.field_10 = 0;
      this.field_11 = -1;
      this.field_17 = true;
      this.field_20 = -1;
      this.field_21 = -1;
      this.field_30 = 16769024;
      this.field_6 = var1;
      this.field_7 = var2;
   }

   // $FF: renamed from: c () int
   public int method_8() {
      return this.field_1.length;
   }

   // $FF: renamed from: d () int
   public int method_9() {
      return this.field_0.length;
   }

   // $FF: renamed from: c (int) void
   public void method_10(int var1) {
      if (var1 < this.field_1.length) {
         this.field_2 = (byte)var1;
      }

   }

   // $FF: renamed from: b (int, int) void
   public void method_11(int var1, int var2) {
      this.field_3 = (short)var1;
      this.field_4 = (short)var2;
   }

   // $FF: renamed from: e () void
   public void method_12() {
      ++this.field_2;
      if (this.field_2 >= this.field_1.length) {
         this.field_2 = 0;
      }

   }

   // $FF: renamed from: a (byte[]) void
   public void method_13(byte[] var1) {
      this.field_1 = var1;
      this.field_2 = 0;
      this.field_12 = 0;
   }

   // $FF: renamed from: a (int, boolean) void
   public void method_14(int var1, boolean var2) {
      if (this.field_8 != null && var1 <= this.field_8.length) {
         byte[] var4 = this.field_8[var1];
         if (var2) {
            byte[] var5 = new byte[var4.length];

            for(int var3 = 0; var3 < var5.length; ++var3) {
               var5[var3] = (byte)(var4[var3] + this.method_9() / 2);
            }

            var4 = var5;
         }

         this.method_13(var4);
      }

   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics, int, int, int, int) void
   public void method_15(Graphics var1, int var2, int var3, int var4, int var5) {
      if (this.field_10 != 2 && this.field_10 != 4 && this.field_10 != 3) {
         if (this.field_5) {
            var3 = this.field_3 + var3;
            var4 = this.field_4 + var4;
            this.field_0[var2].method_282(var1, var3, var4, var5);
            return;
         }
      } else {
         this.method_17(var1, var3, var4);
      }

   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics, int, int, int) void
   public void method_16(Graphics var1, int var2, int var3, int var4) {
      this.method_15(var1, this.field_1[this.field_2], var2, var3, var4);
   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics, int, int) void
   public void method_17(Graphics var1, int var2, int var3) {
      if (this.field_10 != 2 && this.field_10 != 4) {
         if (this.field_10 == 6) {
            if (this.field_2 == 0) {
               var1.setColor(15718144);
            } else {
               var1.setColor(16777215);
            }

            if (this.field_14 > 0) {
               int var11 = this.field_3 + 15;
               var1.fillArc(this.field_3, this.field_4 - 15, 30, 30, 0, 360);
               var1.fillRect(var11, this.field_4 - 15, GameCanvas.field_604 - var11, 30);
               return;
            }

            var1.fillArc(this.field_3 - 30, this.field_4 - 15, 30, 30, 0, 360);
            var1.fillRect(0, this.field_4 - 15, this.field_3 - 15, 30);
            return;
         }

         if (this.field_10 == 3) {
            var1.setColor(0);
            if (this.field_14 > 0) {
               var1.drawLine(this.field_3, this.field_4, this.field_3 + 4, this.field_4 - 2);
               return;
            }

            var1.drawLine(this.field_3 - 4, this.field_4 - 2, this.field_3, this.field_4);
            return;
         }

         if (this.field_5) {
            int var9 = this.field_3 + var2;
            int var10 = this.field_4 + var3;
            if (this.field_24 != null) {
               GameCanvas.method_249(var1, this.field_24, var9, var10, this.field_25, 33);
               return;
            }

            if (this.field_20 > 0) {
               var9 += GameCanvas.method_234(-4, 5);
               var10 += GameCanvas.method_234(-1, 2);
            }

            byte var12 = this.field_1[this.field_2];
            this.field_0[var12].method_281(var1, var9, var10);
            if (this.field_26 != null) {
               Sprite var8;
               if ((var2 = var12 % (this.method_9() / 2)) == 2) {
                  var8 = this.field_27;
               } else {
                  var8 = this.field_26;
                  this.field_26.method_10(var2);
               }

               var8.method_17(var1, var9, var10);
            }

            return;
         }
      } else {
         var1.setColor(this.field_30);

         for(int var6 = 0; var6 < 5; ++var6) {
            if (this.field_32[var6]) {
               int var4 = (this.field_28[var6][0] >> 10) + var2 + this.field_3;
               int var5 = (this.field_28[var6][1] >> 10) + var3 + this.field_4;
               var1.fillRect(var4, var5, this.field_31[var6], this.field_31[var6]);
            }
         }
      }

   }

   // $FF: renamed from: a (java.lang.String, int, int, byte) a.a.a.g
   public static Sprite method_18(String var0, int var1, int var2, byte var3) {
      var1 = GameCanvas.method_245((byte)1, var0);
      var2 = GameCanvas.method_246((byte)1);
      Sprite var5;
      (var5 = new Sprite(var1, var2)).field_25 = 1;
      var5.field_24 = var0;
      var5.field_14 = 0;
      var5.field_16 = -4;
      var5.field_10 = 5;
      return var5;
   }

   // $FF: renamed from: a (a.a.a.g, int, int, int, int, int, byte) a.a.a.g
   public static Sprite method_19(Sprite var0, int var1, int var2, int var3, int var4, int var5, byte var6) {
      if (var0 != null) {
         var0 = new Sprite(var0);
      } else {
         var0 = new Sprite(0, 0);
         if (var6 == 2 || var6 == 4) {
            if (var6 == 4) {
               var0.field_30 = 15658751;
            }

            var0.field_28 = new int[5][2];
            var0.field_29 = new short[5][2];
            var0.field_31 = new byte[5];
            var0.field_32 = new boolean[5];

            for(int var7 = 0; var7 < 5; ++var7) {
               var0.field_32[var7] = true;
               if (var6 == 4) {
                  var0.field_29[var7][0] = (short)(GameCanvas.field_611.nextInt() % 4 << 10);
                  var0.field_29[var7][1] = (short)(GameCanvas.field_611.nextInt() % 4 << 10);
               } else {
                  var0.field_29[var7][0] = (short)(Math.abs(GameCanvas.field_611.nextInt()) % 8192 + -4096);
                  var0.field_29[var7][1] = (short)(Math.abs(GameCanvas.field_611.nextInt()) % 4096 + -2048);
               }

               var0.field_31[var7] = (byte)(Math.abs(GameCanvas.field_611.nextInt()) % 2 + 1);
            }
         }
      }

      var0.field_10 = var6;
      var0.field_11 = var4;
      var0.field_13 = var5;
      var0.field_14 = var1;
      var0.field_15 = var2;
      var0.field_16 = var3;
      var0.field_18 = true;
      return var0;
   }

   // $FF: renamed from: c () void
   public void method_20() {
      if (this.field_17) {
         this.field_12 += 50;
         if (this.field_20 >= 0) {
            --this.field_20;
         }

         switch (this.field_10) {
            case 2:
            case 4:
               this.method_21();
               return;
            case 3:
               this.method_11(this.field_3 + this.field_14, this.field_4 + this.field_15);
               return;
            case 5:
               if (this.field_11 == -1) {
                  this.method_11(this.field_3 + this.field_14, this.field_4);
                  this.field_9 += this.field_16;
                  if (this.field_9 < 0) {
                     ++this.field_16;
                     return;
                  }

                  this.field_9 = 0;
                  this.field_16 = -this.field_16 / 2;
                  if (this.field_16 == 0) {
                     this.field_11 = 1;
                     this.field_12 = 0;
                     return;
                  }
               } else if (this.field_12 >= 400) {
                  this.field_17 = false;
                  return;
               }
               break;
            case 6:
               this.field_2 = (this.field_2 + 1) % 2;
               if (this.field_12 >= this.field_13) {
                  this.field_17 = false;
                  return;
               }
               break;
            default:
               this.method_11(this.field_3 + this.field_14, this.field_4 + this.field_15);
               this.field_9 += this.field_16;
               if (this.field_11 != 0 && this.field_12 >= this.field_13) {
                  this.method_12();
                  if (this.field_10 == 0 && this.field_2 == 0 && this.field_11 > 0) {
                     --this.field_11;
                     if (this.field_11 <= 0) {
                        this.method_10(this.method_8() - 1);
                        if (this.field_18) {
                           this.field_17 = false;
                        }
                     }
                  }

                  this.field_12 = 0;
               }
         }
      }

   }

   // $FF: renamed from: f () void
   public void method_21() {
      if (this.field_10 != 4) {
         this.field_30 += -263168;
      }

      for(int var1 = 0; var1 < 5; ++var1) {
         if (this.field_32[var1]) {
            if (this.field_10 == 4) {
               int[] var10000 = this.field_28[var1];
               var10000[0] += this.field_29[var1][0];
               var10000 = this.field_28[var1];
               var10000[1] += this.field_29[var1][1];
               if (this.field_29[var1][0] < 0) {
                  this.field_29[var1][0] = (short)(this.field_29[var1][0] + 256);
               } else if (this.field_29[var1][0] > 0) {
                  this.field_29[var1][0] = (short)(this.field_29[var1][0] - 256);
               }

               if (this.field_29[var1][1] < 0) {
                  this.field_29[var1][1] = (short)(this.field_29[var1][1] + 256);
               } else if (this.field_29[var1][1] > 0) {
                  this.field_29[var1][1] = (short)(this.field_29[var1][1] - 256);
               }
            } else {
               int[] var3 = this.field_28[var1];
               var3[0] += this.field_29[var1][0];
               var3 = this.field_28[var1];
               var3[1] += this.field_29[var1][1];
               this.field_29[var1][1] = (short)(this.field_29[var1][1] + 256);
            }
         }
      }

      if (this.field_12 >= this.field_13) {
         this.field_17 = false;
      }

   }
}
