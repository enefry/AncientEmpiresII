package a.a.a;

import java.io.InputStream;
import javax.microedition.io.Connection;
import javax.microedition.io.Connector;
import javax.microedition.lcdui.Graphics;

public class g {
   public aeii.d[] a;
   private byte[] a;
   public int l;
   public int m;
   public int n;
   public boolean a;
   public int o;
   public int p;
   public byte[][] b;
   public int q;
   public byte k;
   public int r;
   public int s;
   public int t;
   public int u;
   public int v;
   public int w;
   public boolean b;
   public boolean c;
   public boolean d;
   public int x;
   public byte l;
   public boolean e;
   public int y;
   public String b;
   public int z;
   public g a;
   public g b;
   public int[][] a;
   public short[][] a;
   public int A;
   public byte[] b;
   public boolean[] a;

   public g() {
      super();
   }

   public static Connection a(String var0) {
      return (Connection)(var0.startsWith("file://") ? a(Connector.open(var0)) : Connector.open(var0));
   }

   public static Connection a(String var0, int var1) {
      return (Connection)(var0.startsWith("file://") ? a(Connector.open(var0, 1)) : Connector.open(var0, 1));
   }

   private static c a(Connection var0) {
      switch (a.a.a.a.a()) {
         case 1:
            return new e(var0);
         case 2:
            return new d(var0);
         case 3:
            return new b(var0);
         case 4:
            return new f(var0);
         default:
            return null;
      }
   }

   public g(String var1) {
      super();
      this.l = 0;
      this.m = 0;
      this.n = 0;
      this.a = true;
      this.k = 0;
      this.r = -1;
      this.b = true;
      this.x = -1;
      this.l = -1;
      this.A = 16769024;
      this.a(var1, -1);
   }

   public g(aeii.d[] var1) {
      super();
      this.l = 0;
      this.m = 0;
      this.n = 0;
      this.a = true;
      this.k = 0;
      this.r = -1;
      this.b = true;
      this.x = -1;
      this.l = -1;
      this.A = 16769024;
      this.a = var1;
      this.a = new byte[this.a.length];

      for(byte var2 = 0; var2 < this.a.length; this.a[var2] = var2++) {
      }

      this.o = this.a[0].a;
      this.p = this.a[0].b;
   }

   public g(String var1, byte var2) {
      super();
      this.l = 0;
      this.m = 0;
      this.n = 0;
      this.a = true;
      this.k = 0;
      this.r = -1;
      this.b = true;
      this.x = -1;
      this.l = -1;
      this.A = 16769024;
      this.a(var1, var2);
   }

   private void a(String var1, int var2) {
      try {
         byte var3 = 0;
         int[][] var4 = null;
         int[][] var5 = null;
         int var6 = 0;
         InputStream var7;
         String var20;
         if ((var7 = aeii.c.a(var1 + ".sprite")) != null) {
            while((var20 = aeii.c.a(var7)) != null) {
               int var27;
               if ((var27 = var20.indexOf(59)) >= 0) {
                  var20 = var20.substring(0, var27);
               }

               if ((var20 = var20.trim()).length() != 0) {
                  String[] var18;
                  if ((var18 = aeii.c.a(var20, ' '))[0].equalsIgnoreCase("FrameCount")) {
                     var4 = new int[var3 = (byte)Integer.parseInt(var18[1])][2];
                     var5 = new int[var3][2];
                  } else if (var18[0].equalsIgnoreCase("FrameWidth")) {
                     this.o = Integer.parseInt(var18[1]);
                  } else if (var18[0].equalsIgnoreCase("FrameHeight")) {
                     this.p = Integer.parseInt(var18[1]);
                  } else if (var18[0].equalsIgnoreCase("FrameDef")) {
                     var27 = Integer.parseInt(var18[1]);
                     var4[var27][0] = Integer.parseInt(var18[2]);
                     var4[var27][1] = Integer.parseInt(var18[3]);
                  } else if (var18[0].equalsIgnoreCase("Align")) {
                     var6 = Integer.parseInt(var18[1]);
                  } else if (var18[0].equalsIgnoreCase("FrameSeqCount")) {
                     if ((var27 = Integer.parseInt(var18[1])) > 0) {
                        this.b = new byte[var27][];
                     }
                  } else if (var18[0].equalsIgnoreCase("FrameDelay")) {
                     this.t = Integer.parseInt(var18[1]) * 50;
                  } else if (var18[0].equalsIgnoreCase("FrameSeq")) {
                     var27 = Integer.parseInt(var18[1]);
                     this.b[var27] = new byte[Integer.parseInt(var18[2])];

                     for(int var35 = 0; var35 < this.b[var27].length; ++var35) {
                        this.b[var27][var35] = (byte)Integer.parseInt(var18[var35 + 3]);
                     }
                  } else if (var18[0].equalsIgnoreCase("Translate")) {
                     var27 = Integer.parseInt(var18[1]);
                     var5[var27][0] = Integer.parseInt(var18[2]);
                     var5[var27][1] = Integer.parseInt(var18[3]);
                  }
               }
            }
         } else {
            var4 = new int[var3 = (byte)(var7 = aeii.c.a(var1 + ".sprbin")).read()][2];
            var5 = new int[var3][2];
            this.o = (byte)var7.read();
            this.p = (byte)var7.read();

            for(int var12 = 0; var12 < var3; ++var12) {
               var4[var12][0] = var7.read();
               var4[var12][1] = var7.read();
            }

            var6 = var7.read();
            int var8;
            if ((var8 = var7.read()) > 0) {
               this.b = new byte[var8][];
               this.t = var7.read() * 50;

               for(int var11 = 0; var11 < var8; ++var11) {
                  int var9 = var7.read();
                  this.b[var11] = new byte[var9];

                  for(int var10 = 0; var10 < var9; ++var10) {
                     this.b[var11][var10] = (byte)var7.read();
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
         this.a = new aeii.d[var3];
         aeii.d[] var19 = new aeii.d[var3];

         try {
            aeii.d var22;
            int var33 = (var22 = new aeii.d(var1, var2)).a / this.o;
            int var37 = var22.b / this.p;
            int var38 = 0;

            for(int var39 = 0; var39 < var37; ++var39) {
               for(int var40 = 0; var40 < var33; ++var40) {
                  var19[var38] = new aeii.d(var22, var40, var39, this.o, this.p);
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
                     var19[var32] = new aeii.d(var36.toString());
                  } else {
                     var19[var32] = new aeii.d(var36.toString(), var2);
                  }
               }
            } catch (Exception var15) {
            }
         }

         for(int var23 = 0; var23 < var3; ++var23) {
            this.a[var23] = new aeii.d(var19[var4[var23][0]], var4[var23][1]);
         }

         if (var6 > 0) {
            for(int var25 = 0; var25 < var3; ++var25) {
               this.a[var25].a(var6, this.o, this.p);
               this.a[var25].a(var5[var25][0], var5[var25][1]);
            }
         } else {
            for(int var24 = 0; var24 < var3; ++var24) {
               this.a[var24].a(var5[var24][0], var5[var24][1]);
            }
         }

         if (this.b == null) {
            this.a = new byte[this.a.length];

            for(byte var26 = 0; var26 < this.a.length; this.a[var26] = var26++) {
            }

            return;
         }

         this.a = this.b[0];
      } catch (Exception var17) {
         var17.printStackTrace();
      }

   }

   public g(g var1) {
      super();
      this.l = 0;
      this.m = 0;
      this.n = 0;
      this.a = true;
      this.k = 0;
      this.r = -1;
      this.b = true;
      this.x = -1;
      this.l = -1;
      this.A = 16769024;
      this.a = var1.a;
      this.a = var1.a;
      this.l = var1.l;
      this.m = var1.m;
      this.n = var1.n;
      this.q = var1.q;
      this.a = var1.a;
      this.o = var1.o;
      this.p = var1.p;
      this.t = var1.t;
      this.b = var1.b;
   }

   public g(int var1, int var2) {
      super();
      this.l = 0;
      this.m = 0;
      this.n = 0;
      this.a = true;
      this.k = 0;
      this.r = -1;
      this.b = true;
      this.x = -1;
      this.l = -1;
      this.A = 16769024;
      this.o = var1;
      this.p = var2;
   }

   public int c() {
      return this.a.length;
   }

   public int d() {
      return this.a.length;
   }

   public void c(int var1) {
      if (var1 < this.a.length) {
         this.l = (byte)var1;
      }

   }

   public void b(int var1, int var2) {
      this.m = (short)var1;
      this.n = (short)var2;
   }

   public void e() {
      ++this.l;
      if (this.l >= this.a.length) {
         this.l = 0;
      }

   }

   public void a(byte[] var1) {
      this.a = var1;
      this.l = 0;
      this.s = 0;
   }

   public void a(int var1, boolean var2) {
      if (this.b != null && var1 <= this.b.length) {
         byte[] var4 = this.b[var1];
         if (var2) {
            byte[] var5 = new byte[var4.length];

            for(int var3 = 0; var3 < var5.length; ++var3) {
               var5[var3] = (byte)(var4[var3] + this.d() / 2);
            }

            var4 = var5;
         }

         this.a(var4);
      }

   }

   public void a(Graphics var1, int var2, int var3, int var4, int var5) {
      if (this.k != 2 && this.k != 4 && this.k != 3) {
         if (this.a) {
            var3 = this.m + var3;
            var4 = this.n + var4;
            this.a[var2].a(var1, var3, var4, var5);
            return;
         }
      } else {
         this.a(var1, var3, var4);
      }

   }

   public void a(Graphics var1, int var2, int var3, int var4) {
      this.a(var1, this.a[this.l], var2, var3, var4);
   }

   public void a(Graphics var1, int var2, int var3) {
      if (this.k != 2 && this.k != 4) {
         if (this.k == 6) {
            if (this.l == 0) {
               var1.setColor(15718144);
            } else {
               var1.setColor(16777215);
            }

            if (this.u > 0) {
               int var11 = this.m + 15;
               var1.fillArc(this.m, this.n - 15, 30, 30, 0, 360);
               var1.fillRect(var11, this.n - 15, aeii.c.d - var11, 30);
               return;
            }

            var1.fillArc(this.m - 30, this.n - 15, 30, 30, 0, 360);
            var1.fillRect(0, this.n - 15, this.m - 15, 30);
            return;
         }

         if (this.k == 3) {
            var1.setColor(0);
            if (this.u > 0) {
               var1.drawLine(this.m, this.n, this.m + 4, this.n - 2);
               return;
            }

            var1.drawLine(this.m - 4, this.n - 2, this.m, this.n);
            return;
         }

         if (this.a) {
            int var9 = this.m + var2;
            int var10 = this.n + var3;
            if (this.b != null) {
               aeii.c.a(var1, this.b, var9, var10, this.z, 33);
               return;
            }

            if (this.x > 0) {
               var9 += aeii.c.a(-4, 5);
               var10 += aeii.c.a(-1, 2);
            }

            byte var12 = this.a[this.l];
            this.a[var12].a(var1, var9, var10);
            if (this.a != null) {
               g var8;
               if ((var2 = var12 % (this.d() / 2)) == 2) {
                  var8 = this.b;
               } else {
                  var8 = this.a;
                  this.a.c(var2);
               }

               var8.a(var1, var9, var10);
            }

            return;
         }
      } else {
         var1.setColor(this.A);

         for(int var6 = 0; var6 < 5; ++var6) {
            if (this.a[var6]) {
               int var4 = (this.a[var6][0] >> 10) + var2 + this.m;
               int var5 = (this.a[var6][1] >> 10) + var3 + this.n;
               var1.fillRect(var4, var5, this.b[var6], this.b[var6]);
            }
         }
      }

   }

   public static g a(String var0, int var1, int var2, byte var3) {
      var1 = aeii.c.a((byte)1, var0);
      var2 = aeii.c.a((byte)1);
      g var5;
      (var5 = new g(var1, var2)).z = 1;
      var5.b = var0;
      var5.u = 0;
      var5.w = -4;
      var5.k = 5;
      return var5;
   }

   public static g a(g var0, int var1, int var2, int var3, int var4, int var5, byte var6) {
      if (var0 != null) {
         var0 = new g(var0);
      } else {
         var0 = new g(0, 0);
         if (var6 == 2 || var6 == 4) {
            if (var6 == 4) {
               var0.A = 15658751;
            }

            var0.a = new int[5][2];
            var0.a = new short[5][2];
            var0.b = new byte[5];
            var0.a = new boolean[5];

            for(int var7 = 0; var7 < 5; ++var7) {
               var0.a[var7] = true;
               if (var6 == 4) {
                  var0.a[var7][0] = (short)(aeii.c.a.nextInt() % 4 << 10);
                  var0.a[var7][1] = (short)(aeii.c.a.nextInt() % 4 << 10);
               } else {
                  var0.a[var7][0] = (short)(Math.abs(aeii.c.a.nextInt()) % 8192 + -4096);
                  var0.a[var7][1] = (short)(Math.abs(aeii.c.a.nextInt()) % 4096 + -2048);
               }

               var0.b[var7] = (byte)(Math.abs(aeii.c.a.nextInt()) % 2 + 1);
            }
         }
      }

      var0.k = var6;
      var0.r = var4;
      var0.t = var5;
      var0.u = var1;
      var0.v = var2;
      var0.w = var3;
      var0.c = true;
      return var0;
   }

   public void c() {
      if (this.b) {
         this.s += 50;
         if (this.x >= 0) {
            --this.x;
         }

         switch (this.k) {
            case 2:
            case 4:
               this.f();
               return;
            case 3:
               this.b(this.m + this.u, this.n + this.v);
               return;
            case 5:
               if (this.r == -1) {
                  this.b(this.m + this.u, this.n);
                  this.q += this.w;
                  if (this.q < 0) {
                     ++this.w;
                     return;
                  }

                  this.q = 0;
                  this.w = -this.w / 2;
                  if (this.w == 0) {
                     this.r = 1;
                     this.s = 0;
                     return;
                  }
               } else if (this.s >= 400) {
                  this.b = false;
                  return;
               }
               break;
            case 6:
               this.l = (this.l + 1) % 2;
               if (this.s >= this.t) {
                  this.b = false;
                  return;
               }
               break;
            default:
               this.b(this.m + this.u, this.n + this.v);
               this.q += this.w;
               if (this.r != 0 && this.s >= this.t) {
                  this.e();
                  if (this.k == 0 && this.l == 0 && this.r > 0) {
                     --this.r;
                     if (this.r <= 0) {
                        this.c(this.c() - 1);
                        if (this.c) {
                           this.b = false;
                        }
                     }
                  }

                  this.s = 0;
               }
         }
      }

   }

   public void f() {
      if (this.k != 4) {
         this.A += -263168;
      }

      for(int var1 = 0; var1 < 5; ++var1) {
         if (this.a[var1]) {
            if (this.k == 4) {
               int[] var10000 = this.a[var1];
               var10000[0] += this.a[var1][0];
               var10000 = this.a[var1];
               var10000[1] += this.a[var1][1];
               if (this.a[var1][0] < 0) {
                  this.a[var1][0] = (short)(this.a[var1][0] + 256);
               } else if (this.a[var1][0] > 0) {
                  this.a[var1][0] = (short)(this.a[var1][0] - 256);
               }

               if (this.a[var1][1] < 0) {
                  this.a[var1][1] = (short)(this.a[var1][1] + 256);
               } else if (this.a[var1][1] > 0) {
                  this.a[var1][1] = (short)(this.a[var1][1] - 256);
               }
            } else {
               int[] var3 = this.a[var1];
               var3[0] += this.a[var1][0];
               var3 = this.a[var1];
               var3[1] += this.a[var1][1];
               this.a[var1][1] = (short)(this.a[var1][1] + 256);
            }
         }
      }

      if (this.s >= this.t) {
         this.b = false;
      }

   }
}
