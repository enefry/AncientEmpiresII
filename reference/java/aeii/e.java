package aeii;

import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public final class e extends f {
   private boolean[] a = new boolean[]{false, false};
   public int a = 13553358;
   private static int n;
   private static int o;
   private byte b = 2;
   private short a = 3;
   public static g a;
   public String[] a;
   private d[] a;
   private int p;
   private int q;
   public int b;
   public int c;
   private int r;
   private Font a;
   public int d;
   private int s;
   private int t;
   private int u;
   private int v;
   private int w;
   public byte a;
   private int x;
   private boolean c;
   private boolean d;
   public a[] a;
   private int y;
   private int z;
   private byte c;
   private int A;
   private int B;
   private int C;
   private int D;
   private String[] b;
   public int e;
   private boolean e;
   private int E;
   public a a;
   private boolean f;
   public f a;
   private int F;
   private a.a.a.g[] a;
   private int G;
   private boolean g;
   private Vector a;
   public int f;
   public boolean a;
   public boolean b;
   private int H;
   private int I;
   private int J;
   private int K;
   private byte[][] a;
   private Vector b;
   private int L;
   private int M;
   public int g;
   public int h;
   public d a;
   private e a;
   public int[] a;
   private int N;
   private int O;
   private int[] b;
   public int i;
   private int P;
   private short[] a;
   private int Q;
   private int R;
   private int S;
   private int T;
   private int U;
   private int V;
   private int W;
   private int X;
   private int Y;
   private int Z;
   private byte d;
   private a.a.a.g a;

   public final void a() {
      this.X = 0;
      if (this.a != null) {
         this.e();
      }

      this.G = 4;
      this.c = true;
      this.d = true;
      if (a != null) {
         a.a();
      }

      if (this.a != null) {
         this.a.c = true;
      }

      if (this.a == 15) {
         for(int var1 = 0; var1 < this.a.size(); ++var1) {
            e var2;
            (var2 = (e)this.a.elementAt(var1)).a();
            var2.d = false;
         }
      }

   }

   public final void a(byte var1, boolean var2) {
      this.a[var1] = var2;
   }

   public final void a(f var1) {
      this.a = var1;
      this.a[1] = var1 != null;
   }

   public e(byte var1, int var2) {
      super();
      this.a = aeii.c.b;
      this.c = false;
      this.d = false;
      this.c = -1;
      this.e = -1;
      this.e = true;
      this.f = true;
      this.g = false;
      this.f = -1;
      this.g = 2370117;
      this.h = 2370117;
      this.P = -1;
      this.a = (byte)var1;
      this.x = var2;
      if (var1 == 15) {
         this.i = a.b - a.c.p;
         this.e = true;
      } else if (var1 != 0 && var1 != 11) {
         if (var1 == 3) {
            this.a = a.j;
            this.d();
            this.e = false;
            this.a = true;
            this.y = aeii.c.b - aeii.c.a;
            this.b = a.a;
            this.c = a.j.p + o;
            if ((var2 & 2) == 0) {
               this.c += 5;
            }

            this.a = aeii.a.a(a.b);
            this.s = this.a.length;
            var1 = this.b - (a.a.o << 1);
            if ((var2 & 4) == 0) {
               var1 -= 8;
            }

            if ((var2 & 8) == 0) {
               var1 -= 8;
            }

            this.B = var1 / (a.j.o + 3);
            if (this.B > this.s) {
               this.B = this.s;
            }

            this.r = var1 / this.B;
            this.F = (var1 - this.r * this.B) / 2;
            this.b = 2;
         } else if (var1 != 2 && var1 != 5) {
            if (var1 != 7 && var1 == 8) {
               this.a = 8;
               this.a[0] = true;
            }
         } else {
            this.e = false;
            this.c = 5 + n + 24 + o + (a.k.p << 1) + n + n + 1;
            if (var1 == 5) {
               this.c += o + aeii.c.a;
               this.a = a.a((int)a.h, a.i, (byte)0);
               this.d = this.a.d;
               this.b = a.a;
            } else {
               this.b = a.a;
            }
         }
      } else {
         this.a[0] = true;
         this.a[1] = true;
      }

      this.c = true;
   }

   public final e a(String var1) {
      this.a = new e((byte)10, 0);
      this.a.a((String)null, (String)var1, a.a, -1);
      return this.a;
   }

   public final void a(e var1, int var2, int var3, int var4) {
      if (this.a == null) {
         this.a = new Vector();
      }

      if (this.b == null) {
         this.b = new int[5];

         for(int var5 = 0; var5 < 5; ++var5) {
            this.b[var5] = this.i;
            if (var5 > 0) {
               int[] var10000 = this.b;
               var10000[var5] -= a.c.p;
            }
         }
      }

      var1.a(var2, var3, var4);
      int var11 = var1.q;

      for(int var6 = 0; var6 < 5; ++var6) {
         if (var11 < this.b[var6]) {
            if (var11 + var1.c > this.b[var6]) {
               this.b[var6] = var11;
               if (var6 + 1 > this.O) {
                  this.O = var6 + 1;
               }
            }
            break;
         }

         var11 -= this.b[var6];
      }

      var4 = 0;
      var3 = 0;
      var1.a[var3] = (boolean)var4;
      var4 = 0;
      var3 = 1;
      var1.a[var3] = (boolean)var4;
      this.a.addElement(var1);
   }

   public final void a(int var1, int var2, byte[][] var3, Vector var4) {
      this.x = 15;
      this.a = var3;
      this.b = var4;
      this.a = 8;
      this.a[0] = true;
      this.e = true;
      this.L = var3.length;
      this.M = var3[0].length;
      this.b = this.L * a.b[0].a + 8;
      this.c = this.M * a.b[0].b + 8;
      if (this.b > var1) {
         int var5 = a.b[0].a;
         this.J = (var1 - 8) / var5;
         this.b = var5 * this.J + 8;
      } else {
         this.J = this.L;
      }

      if (this.c > var2) {
         int var6 = a.b[0].b;
         this.K = (var2 - 8) / var6;
         this.c = var6 * this.K + 8;
      } else {
         this.K = this.M;
      }

      this.a = 8;
   }

   public final void a(int var1, int var2, int var3) {
      this.p = var1;
      this.q = var2;
      if ((var3 & 1) != 0) {
         this.p -= this.b >> 1;
      } else if ((var3 & 8) != 0) {
         this.p -= this.b;
      }

      if ((var3 & 2) != 0) {
         this.q -= this.c >> 1;
      } else if ((var3 & 32) != 0) {
         this.q -= this.c;
      }

      this.v = this.p;
      this.w = this.q;
   }

   public final void a(String var1, int var2, int var3, byte var4) {
      this.c = (byte)var4;
      if (var4 == -1) {
         this.x = 14;
      } else {
         this.C = a.i.o - 8;
      }

      var4 = var2 - this.C - 16;
      this.a = aeii.f.a(var1, var4, aeii.c.b);
      this.a((String)null, (String[])this.a, var2, var3);
      this.g = false;
      this.a = 7;
   }

   private void a(String var1, String[] var2, int var3, int var4) {
      this.e = false;
      this.b = var3;
      this.c = var4;
      this.s = var2.length;
      this.d = 0;
      this.A = 0;
      this.D = 0;
      this.g = false;
      var3 = var3 - this.C - 16;
      if (var1 != null) {
         this.b = aeii.f.a(var1, var3, aeii.c.b);
      }

      this.a = var2;
      this.r = aeii.c.b;
      this.y = aeii.c.b - aeii.c.a;
      this.z = this.y / 2;
      int var5;
      if (var4 <= 0) {
         var5 = super.k;
      } else {
         var5 = var4;
      }

      if ((this.x & 1) == 0) {
         var5 -= 5;
      }

      if ((this.x & 2) == 0) {
         var5 -= 5;
      }

      if (var1 != null) {
         var5 -= this.b.length * this.r;
      }

      this.B = (var5 - 2) / this.r;
      if (this.B > this.a.length) {
         this.B = this.a.length;
      } else if (this.B < this.a.length) {
         this.g = true;
      }

      if (var4 < 0) {
         if (this.b != null) {
            this.c = this.b.length * this.r;
         }

         this.c += this.B * this.r;
         if ((this.x & 1) == 0) {
            this.c += 5;
         }

         if ((this.x & 2) == 0) {
            this.c += 5;
         }
      } else {
         this.F = (var5 - this.B * this.r) / 2;
      }

      this.a = 10;
      this.b = 2;
   }

   public final void a(String var1, String var2, int var3, int var4) {
      int var5 = var3 - this.C;
      if ((this.x & 4) == 0) {
         var5 -= 8;
      }

      if ((this.x & 8) == 0) {
         var5 -= 8;
      }

      this.a = aeii.f.a(var2, var5, aeii.c.b);
      this.a(var1, this.a, var3, var4);
      if (this.g) {
         var5 -= a.b.o;
         this.a = aeii.f.a(var2, var5, aeii.c.b);
         this.a(var1, this.a, var3, var4);
      }

   }

   private final void d() {
      this.a = new a.a.a.g[3];

      for(int var1 = 0; var1 < this.a.length; ++var1) {
         this.a[var1] = new a.a.a.g(a.h);
      }

      this.e();
   }

   private void e() {
      for(int var1 = 0; var1 < this.a.length; ++var1) {
         this.a[var1].a = true;
         this.a[var1].b(aeii.c.a(this.a.o), aeii.c.a(this.a.p));
         this.a[var1].c(aeii.c.a(this.a[var1].d()));
      }

   }

   public final void a(String[] var1, d[] var2, int var3, int var4, int var5) {
      this.x = 15;
      this.a = var1;
      this.a = var2;
      this.s = this.a.length;
      this.b = 0;

      for(int var6 = 0; var6 < this.s; ++var6) {
         int var7;
         if ((var7 = aeii.c.b.stringWidth(this.a[var6])) > this.b) {
            this.b = var7;
         }
      }

      this.y = aeii.c.b - aeii.c.a;
      this.z = this.y / 2;
      this.Y = a.k.o;
      this.r = this.Y + this.y;
      this.b += this.s * this.r;
      this.b += 32;
      if (this.b > super.j) {
         this.b = super.j;
      }

      this.c = this.Y;
      this.a(var3, var4, var5);
      this.a = 13;
      this.b = 2;
   }

   public final void a(String[] var1, int var2, int var3) {
      this.a = var1;
      this.s = this.a.length;
      this.a = true;
      this.e = false;
      this.c = var3;
      int var5 = 0;

      for(int var6 = 0; var6 < this.a.length; ++var6) {
         int var4;
         if ((var4 = aeii.c.b.stringWidth(this.a[var6])) > var5) {
            var5 = var4;
         }
      }

      this.b = var5 + 16 + (a.a.o << 1);
      if (this.b < var2) {
         this.b = var2;
      }

      if (this.c < 0) {
         this.c = aeii.c.b;
         if (a.a.p > this.c) {
            this.c = a.a.p;
         }

         if ((this.x & 1) == 0) {
            this.c += 5;
         }

         if ((this.x & 2) == 0) {
            this.c += 5;
         }
      }

      this.a = 14;
      this.b = 2;
   }

   public final void a(String[] var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      this.a = var1;
      this.s = this.a.length;
      this.y = aeii.c.b - aeii.c.a;
      this.r = aeii.c.b;
      int var10 = 0;

      for(int var8 = 0; var8 < this.a.length; ++var8) {
         int var9;
         if ((var9 = aeii.c.b.stringWidth(this.a[var8])) > var10) {
            var10 = var9;
         }
      }

      this.b = var10 + 4 + 16;
      if (this.b > super.j) {
         this.b = super.j;
      } else if (this.b < var4) {
         if (var7 == 4) {
            this.P = (var4 - this.b) / 2;
         }

         this.b = var4;
      }

      this.c = this.r * this.a.length + this.y + 16;
      if (this.c > var5) {
         this.c = var5;
      }

      this.a((String)null, (String[])this.a, this.b, this.c);
      if (this.b < super.j && this.g) {
         this.b += a.b.o;
      }

      this.a = 11;
      this.a(var2, var3, var6);
   }

   public final void a(String[] var1, d[] var2, int var3, int var4, int var5, int var6, byte var7) {
      this.d = 1;
      this.a = var1;
      this.a = var2;
      this.s = this.a.length;
      this.a = a.j;
      this.y = aeii.c.b - aeii.c.a;
      this.x = 15;
      this.Y = this.a.o;
      this.Z = this.Y >> 1;
      this.d();
      this.a = new short[this.s];
      this.V = 360 / this.s;
      this.U = this.V / 2;
      this.T = this.U;

      for(int var8 = 0; var8 < this.s; ++var8) {
         this.a[var8] = (short)(this.V * var8);
      }

      if (this.s == 1) {
         this.R = 0;
      } else if (var5 <= 0) {
         this.R = (this.a.o << 10) / (2 * aeii.f.a(45));
         this.S = this.R + this.a.o / 2;
         var5 = (this.S << 1) + aeii.c.b + 2;
      } else {
         int var9 = (this.a.o << 10) / aeii.f.a(this.V / 2) + this.a.p / 2;
         this.S = (var5 - aeii.c.b) / 2 - 2;
         if (this.S > var9) {
            this.S = var9;
         }

         this.R = this.S - this.a.p / 2;
      }

      this.Q = 0;
      this.b = this.S << 1;
      this.c = var5;
      this.b = 0;
      this.a(var3, var4, 3);
   }

   private int a(int var1) {
      int var2 = this.f;
      int var3 = this.f;
      int var4 = this.a.size();

      do {
         if ((var2 += var1) < 0) {
            var2 = var4 - 1;
         } else if (var2 >= this.a.size()) {
            if (var3 < 0) {
               return -1;
            }

            var2 = 0;
         }
      } while(!((e)this.a.elementAt(var2)).a);

      return var2;
   }

   public final void b() {
      this.a(true);
   }

   private void a(boolean var1) {
      if (this.b != 3) {
         if (this.a == 10 && this.e > 0 && this.e <= 250) {
            ++this.G;
            this.c = true;
         } else if (this.G > 0) {
            --this.G;
            this.c = true;
         }

         if (var1 && this.b == 2) {
            var1 = (boolean)0;
            if (this.a[0] && (aeii.f.a.a(aeii.g.d) || aeii.f.a.a((int)16))) {
               var1 = (boolean)1;
               aeii.f.a.b(aeii.g.d);
               aeii.f.a.b(16);
            }

            if (this.a == 0 || this.a == 3) {
               for(int var2 = 0; var2 < this.a.length; ++var2) {
                  if (this.a[var2].l == this.a[var2].d() - 1) {
                     if (this.X == 0) {
                        this.a[var2].b(aeii.c.a(this.a.o - this.a[var2].o), aeii.c.a(this.a.p - this.a[var2].p));
                     } else {
                        this.a[var2].a = false;
                     }
                  }

                  this.a[var2].e();
               }

               this.c = true;
            }

            if (this.a == 15) {
               if (!this.b && this.f >= 0) {
                  if (aeii.f.a.a((int)1)) {
                     ((e)this.a.elementAt(this.f)).c = true;
                     this.f = this.a(-1);
                     e var11;
                     (var11 = (e)this.a.elementAt(this.f)).c = true;
                     int var15 = var11.q;

                     for(int var12 = 0; var12 < 5; ++var12) {
                        if (var15 < this.b[var12]) {
                           if (this.N != var12) {
                              this.a();
                              this.N = var12;
                           }
                           break;
                        }

                        var15 -= this.b[var12];
                     }
                  } else if (aeii.f.a.a((int)2)) {
                     ((e)this.a.elementAt(this.f)).c = true;
                     this.f = this.a(1);
                     e var9;
                     (var9 = (e)this.a.elementAt(this.f)).c = true;
                     int var3 = var9.q;

                     for(int var10 = 0; var10 < 5; ++var10) {
                        if (var3 < this.b[var10]) {
                           if (this.N != var10) {
                              this.a();
                              this.N = var10;
                           }
                           break;
                        }

                        var3 -= this.b[var10];
                     }
                  }
               }

               if (var1) {
                  a.a(this, this.f, "", (byte)0);
                  return;
               }

               for(int var13 = 0; var13 < this.a.size(); ++var13) {
                  e var16 = (e)this.a.elementAt(var13);
                  e var10000;
                  boolean var10001;
                  if (this.b) {
                     var10000 = var16;
                     var10001 = true;
                  } else {
                     var10000 = var16;
                     var10001 = var13 == this.f;
                  }

                  var10000.a(var10001);
               }

               this.c = true;
            } else if (this.a == 0) {
               if (this.b == 2) {
                  this.c = true;
                  if (aeii.f.a.a((int)4)) {
                     this.X -= this.V;
                     this.W += this.V;
                     --this.d;
                     if (this.d < 0) {
                        this.d = this.s - 1;
                     }
                  } else if (aeii.f.a.a((int)8)) {
                     this.X += this.V;
                     this.W -= this.V;
                     if (this.W < 0) {
                        this.W += 360;
                     }

                     ++this.d;
                     if (this.d >= this.s) {
                        this.d = 0;
                     }
                  }

                  if (this.X != 0) {
                     int var14;
                     if ((var14 = -this.X / 2) == 0) {
                        this.X = 0;
                     } else {
                        this.X += var14;
                     }

                     if (this.X == 0) {
                        this.e();
                     }
                  } else if (aeii.f.a.c(4)) {
                     this.X -= this.V;
                     this.W += this.V;
                     --this.d;
                     if (this.d < 0) {
                        this.d = this.s - 1;
                     }
                  } else if (aeii.f.a.c(8)) {
                     this.X += this.V;
                     this.W -= this.V;
                     if (this.W < 0) {
                        this.W += 360;
                     }

                     ++this.d;
                     if (this.d >= this.s) {
                        this.d = 0;
                     }
                  }

                  if (var1) {
                     a.a(this, this.d, this.a[this.d], (byte)0);
                     return;
                  }
               }
            } else if (this.a != 13 && this.a != 14) {
               if (this.a == 3) {
                  if (this.D != 0) {
                     if (Math.abs(this.D) < 2) {
                        this.D = 0;
                     } else {
                        this.D -= this.D / 2;
                     }

                     this.c = true;
                  }

                  if (var1) {
                     a.a(this, this.d, this.a[this.d], (byte)0);
                     return;
                  }

                  if (aeii.f.a.a((int)4)) {
                     if (this.d < this.A) {
                        this.d += this.s;
                     }

                     --this.d;
                     if (this.d < this.A) {
                        if (this.d < 0) {
                           this.d += this.s;
                        }

                        this.A = this.d;
                        this.D = -this.r;
                     }

                     this.d %= this.s;
                     a.a(this, this.d, (String)null, (byte)2);
                     this.e();
                     this.c = true;
                  } else if (aeii.f.a.a((int)8)) {
                     if (this.d < this.A) {
                        this.d += this.s;
                     }

                     ++this.d;
                     if (this.d >= this.A + this.B) {
                        this.D = this.r;
                        this.A = (this.A + 1) % this.s;
                     }

                     this.d %= this.s;
                     a.a(this, this.d, (String)null, (byte)3);
                     this.e();
                     this.c = true;
                  }
               } else if (this.a != 10 && this.a != 7 && this.a != 11) {
                  if (this.a == 8) {
                     if (aeii.f.a.a((int)1)) {
                        if (this.I > 0) {
                           --this.I;
                           this.c = true;
                        }
                     } else if (aeii.f.a.a((int)2) && this.I + this.K < this.M) {
                        ++this.I;
                        this.c = true;
                     }

                     if (aeii.f.a.a((int)4)) {
                        if (this.H > 0) {
                           --this.H;
                           this.c = true;
                        }
                     } else if (aeii.f.a.a((int)8) && this.H + this.J < this.L) {
                        ++this.H;
                        this.c = true;
                     }
                  }
               } else {
                  if (this.e != -1) {
                     if (this.e > 0) {
                        this.e -= 50;
                     } else {
                        aeii.f.a.a(this.a);
                     }
                  }

                  if (this.D > 0) {
                     this.D -= this.r / 3 + 1;
                     if (this.D < 0) {
                        this.D = 0;
                     }

                     this.c = true;
                  } else if (this.D < 0) {
                     this.D += this.r / 3 + 1;
                     if (this.D > 0) {
                        this.D = 0;
                     }

                     this.c = true;
                  }

                  if (this.D == 0) {
                     if ((this.a == 11 || this.a == 10) && var1) {
                        a.a(this, this.d, this.a[this.d], (byte)0);
                        return;
                     }

                     if (this.a != 7 && aeii.f.a.a((int)1)) {
                        if (this.a == 11) {
                           --this.d;
                           if (this.d < 0) {
                              this.d = this.s - 1;
                              this.A = this.s - this.B;
                              if (this.a == 3) {
                                 this.A = this.d;
                              }
                           } else if (this.d < this.A) {
                              this.D = -this.r;
                              --this.A;
                           }

                           a.a(this, this.d, (String)null, (byte)2);
                           this.c = true;
                        } else if (this.A > 0) {
                           this.D = -this.r;
                           --this.A;
                           this.c = true;
                        }

                        aeii.f.a.b();
                     }

                     if (aeii.f.a.a((int)2) || this.a == 7 && aeii.f.a.a((int)2048)) {
                        if (this.a == 11) {
                           ++this.d;
                           if (this.d >= this.s) {
                              this.d = 0;
                              this.A = 0;
                           } else if (this.d >= this.A + this.B) {
                              this.D = this.r;
                              ++this.A;
                           }

                           a.a(this, this.d, (String)null, (byte)3);
                           this.c = true;
                        } else if (this.A + this.B < this.a.length) {
                           this.D = this.r;
                           ++this.A;
                           this.c = true;
                        } else if (this.a == 7) {
                           a.a(this, 0, (String)null, (byte)0);
                           return;
                        }

                        aeii.f.a.b();
                     }
                  }
               }
            } else if (aeii.f.a.a((int)4)) {
               --this.d;
               if (this.d < 0) {
                  this.d = this.s - 1;
               }

               if (this.a == 14) {
                  a.a(this, this.d, (String)null, (byte)2);
               }

               this.c = true;
            } else if (aeii.f.a.a((int)8)) {
               ++this.d;
               if (this.d >= this.s) {
                  this.d = 0;
               }

               if (this.a == 14) {
                  a.a(this, this.d, (String)null, (byte)2);
               }

               this.c = true;
            } else if (var1) {
               a.a(this, this.d, this.a[this.d], (byte)0);
               return;
            }

            if (this.b == 2 && this.a[1] && aeii.f.a.a(aeii.g.e)) {
               aeii.f.a.b(aeii.g.e);
               aeii.f.a.b();
               if (this.a != null) {
                  aeii.f.a.a(this.a);
               }

               if (this.a != null) {
                  a.a(this, this.d, this.a[this.d], (byte)1);
                  return;
               }

               a.a(this, -1, (String)null, (byte)1);
               return;
            }
         }

         if (this.e && ++this.u >= this.a) {
            if (this.t == 0) {
               this.t = 2;
            } else {
               this.t = 0;
            }

            this.u = 0;
            this.c = true;
         }

         switch (this.b) {
            case 0:
               if (this.a == 0) {
                  if (this.Q < this.R) {
                     if (this.d == 2) {
                        var1 = this.R / 2;
                     } else {
                        var1 = this.R / 5;
                     }

                     if (var1 <= 0) {
                        var1 = 1;
                     }

                     this.Q += var1;
                     if (this.Q > this.R) {
                        this.Q = this.R;
                     }
                  } else {
                     this.T = Math.abs(360 - this.a[0]) / 2;
                     if (this.T <= 0) {
                        this.T = 1;
                     } else if (this.T > this.U) {
                        this.T = this.U;
                     }
                  }

                  if (this.d == 1) {
                     for(int var6 = 0; var6 < this.a.length; ++var6) {
                        this.a[var6] = (short)((this.a[var6] + this.T) % 360);
                     }
                  }

                  if (aeii.f.a.a() || this.Q >= this.R && this.a[0] == 0) {
                     this.Q = this.R;

                     for(int var7 = 0; var7 < this.a.length; ++var7) {
                        this.a[var7] = (short)(this.V * var7);
                     }

                     this.b = 2;
                     if (this.f) {
                        aeii.f.a.b();
                     }
                  }
               } else if (this.a == 13) {
                  this.v += (this.p - this.v) / 2;
                  ++this.E;
                  if (this.E == 2) {
                     this.b = 2;
                     this.v = this.p;
                  }
               } else {
                  if ((var1 = (this.p - this.v) / 4) <= 0) {
                     var1 = 1;
                  }

                  this.v += var1;
                  if (this.v == this.p) {
                     this.b = 2;
                  }
               }

               this.c = true;
               return;
            case 1:
               this.b = 3;
         }
      }

   }

   public static final void a(Graphics var0, int var1, int var2, int var3, int var4) {
      if (var4 <= 2) {
         var0.fillRect(var1, var2, var3, var4);
      } else {
         var0.fillRect(var1, var2 + 1, var3, var4 - 2);
         var0.fillRect(var1 + 1, var2, var3 - 2, var4);
      }
   }

   public final void a(Graphics var1) {
      this.a(var1, 0, 0, false);
   }

   private void a(Graphics var1, int var2, int var3, boolean var4) {
      if (this.b != 3 && this.c) {
         this.c = false;
         if (aeii.f.a.a == this && this.d || this.a == 0) {
            a.a(var1);
         }

         this.d = false;
         var1.setClip(0, 0, super.j, super.k);
         if (this.a != null) {
            this.a.a(var1);
         }

         var2 = this.v;
         var3 = this.w + var3;
         if (this.a != 0 && this.a != 13) {
            a(var1, var2, var3, this.b, this.c, this.x, this.h, this.g, this.G, 5);
            var1.setClip(0, 0, super.j, super.k);
         }

         int var5 = this.b;
         int var6 = this.c;
         if ((this.x & 1) == 0) {
            var6 -= 5;
            var3 += 5;
         }

         if ((this.x & 2) == 0) {
            var6 -= 5;
         }

         if ((this.x & 4) == 0) {
            var2 += 8;
            var5 -= 8;
         }

         if ((this.x & 8) == 0) {
            var5 -= 8;
         }

         var1.translate(var2, var3);
         var1.setFont(this.a);
         if (this.a != null && (this.a == null || this.a.stringWidth(this.a[0]) < var5 - (this.a.a << 1))) {
            this.a.a(var1, 0, var6 / 2, 6);
         }

         if (var4) {
            var1.setColor(5594742);
            a(var1, 0, 0, var5, var6);
         }

         if (this.a == 0) {
            var1.setColor(16777215);
            int var8 = this.W + this.X;

            for(int var9 = this.a.length - 1; var9 >= 0; --var9) {
               int var7;
               if ((var7 = (this.a[var9] + var8) % 360) < 0) {
                  var7 += 360;
               }

               var4 = this.S + (aeii.f.a(var7) * this.Q >> 10);
               var7 = aeii.c.b + this.S + 2 - (aeii.f.b(var7) * this.Q >> 10);
               if (this.b == 2 && var9 == this.d) {
                  this.a.a(var1, 1, var4, var7, 3);
                  if (this.X == 0) {
                     for(int var10 = 0; var10 < this.a.length; ++var10) {
                        this.a[var10].a((Graphics)var1, var4 - this.Z, var7 - this.Z, (int)20);
                     }
                  }
               } else {
                  this.a.a(var1, 0, var4, var7, 3);
               }

               if (this.a[var9] != null && this.a != null && this.a[var9] != null) {
                  this.a[var9].a(var1, var4, var7, 3);
               }
            }

            if (this.b == 2) {
               for(int var60 = 0; var60 < this.a.length; ++var60) {
                  this.a[var60].a((Graphics)var1, (this.b - this.Y) / 2, aeii.c.b, (int)3);
               }
            }
         }

         switch (this.a) {
            case 0:
               if (this.b == 2) {
                  var1.setColor(1645370);
                  if (this.d == 2) {
                     var6 = this.b;
                     if (this.a[this.d] != null) {
                        int var96 = aeii.c.b.stringWidth(this.a[this.d]) + 2;
                        if (var6 < var96) {
                           var6 = var96;
                        }
                     }

                     a(var1, (this.b - var6) / 2, 1, var6, aeii.c.b);
                  } else {
                     a(var1, 2 - this.p, 1, super.j - 4, aeii.c.b);
                  }

                  if (this.a[this.d] != null) {
                     var1.setColor(16777215);
                     aeii.c.b(var1, this.a[this.d], this.S, (this.y >> 1) + 1, 17);
                  }
               }
            case 1:
            case 4:
            case 6:
            case 9:
            case 12:
            default:
               break;
            case 2:
            case 5:
               var1.setClip(0, 0, var5, var6);
               var6 = n;
               this.a.a(var1, -this.a.m + var6, -this.a.n + var6);
               int var57 = var6 + this.a.p / 2;
               var1.setFont(aeii.c.b);
               var1.setColor(this.a);
               aeii.c.b(var1, this.a.a, var6 + this.a.o + var6, var57 - aeii.c.a / 2, 20);
               String var104;
               if (this.a == 2) {
                  var104 = "" + this.a.j;
                  a.m.a(var1, 1, var5 - var6 - aeii.c.a((byte)1, var104), var57, 10);
               } else {
                  var104 = "" + this.a.h;
               }

               aeii.c.a(var1, var104, var5 - var6, var57, 1, 10);
               this.y = aeii.c.b - aeii.c.a;
               int var47 = var6 + this.a.p + n;
               var1.setColor(this.a);
               var1.drawLine(var6, var47, var5 - var6 - var6, var47);
               var47 += 1 + n;
               if (this.a == 5) {
                  int var76 = aeii.c.a;
                  var57 = var47 + var76 / 2;
                  aeii.c.b(var1, aeii.f.a(97), var6, var47, 20);
                  int var67 = aeii.c.b.stringWidth(aeii.f.a(97));
                  var4 = var6 + var67 + var6;
                  String var100 = "" + this.a.a;
                  int var90 = aeii.c.a((byte)0, var100);
                  int var18 = var5 - var4 - var6 - a.l.o - var90 - var6;
                  var1.setColor(this.a);
                  a(var1, var4, var47, var18, var76);
                  var1.setColor(2370117);
                  int var81;
                  if ((var81 = var18 * this.a.a / this.a.a()) <= 0) {
                     var81 = 1;
                  }

                  var1.fillRect(var4 + 1, var47 + 1, var81, var76 - 2);
                  var4 = var5 - var6 - var90;
                  a.l.a(var1, 2, var4, var57, 10);
                  aeii.c.a(var1, var100, var4, var57, 0, 6);
                  var47 += var76 + n;
                  var1.setColor(this.a);
                  var1.drawLine(var6, var47, var5 - var6 - var6, var47);
                  var47 += 1 + n;
               }

               int var77 = (var5 - var6 * 3) / 2;
               int var68 = a.l.p;
               int var105 = a.k.p;
               int var82 = a.k.p / 2;

               for(int var59 = 0; var59 < 2; ++var59) {
                  int var86 = var47 + var82 - var68 / 2;
                  var4 = var6;

                  for(int var93 = 0; var93 < 2; ++var93) {
                     if (var59 == 0 || var93 == 0) {
                        int var91 = var4 + var82;
                        a(var1, var91, var86, var77 - var82, var68);
                        a.k.a(var1, var4, var47);
                        int var101;
                        if ((var101 = (var59 << 1) + var93) == 0 || var101 == 1) {
                           a.l.a(var1, var101, var91, var47 + var82, 3);
                        }

                        int var95 = 0;
                        if (var101 == 0) {
                           if (this.a == 5) {
                              var95 = this.a.a((a)null);
                           }

                           var104 = this.a.d + var95 + "-" + (this.a.e + var95);
                        } else if (var101 == 1) {
                           if (this.a == 5) {
                              var95 = this.a.b((a)null);
                           }

                           var104 = "" + (this.a.f + var95);
                        } else if (var101 == 2) {
                           a.a[5].a(var1, var91, var47 + var82, 3);
                           var104 = "" + this.a.g;
                        }

                        aeii.c.a(var1, var104, var4 + var105 + 1, var47 + var82, 0, 6);
                        if (var95 > 0) {
                           a.p.a(var1, 1, var91 + var77 - var82 - 1, var47 + var82, 10);
                        } else if (var95 < 0) {
                           a.p.a(var1, 2, var91 + var77 - var82 - 1, var47 + var82, 10);
                        }

                        var4 += var77 + n;
                     }
                  }

                  var47 += var105;
               }

               var47 += n;
               var1.setColor(this.a);
               var1.drawLine(var6, var47, var5 - var6 - var6, var47);
               break;
            case 3:
               var1.setClip(0, 0, var5, var6);
               int var45 = n;
               var1.setColor(11515819);
               var1.drawLine(n, var45, var5 - (n << 1), var45);
               var45 = var45 + 1 + n + a.j.p / 2;
               var4 = a.a.o + this.D + this.F;
               var6 = this.A;
               int var94 = this.A + this.B;
               if (this.D > 0) {
                  --var6;
                  var4 -= this.r;
               } else if (this.D < 0) {
                  ++var94;
               }

               for(int var99 = var6; var99 < var94; ++var99) {
                  int var75;
                  if ((var75 = var99 % this.s) < 0) {
                     var75 += this.s;
                  }

                  int var66 = var4 + this.r / 2;
                  if (var75 == this.d) {
                     a.j.a(var1, 1, var66, var45, 3);
                  } else {
                     a.j.a(var1, 0, var66, var45, 3);
                  }

                  a var88 = this.a[var75];
                  int var80 = var66 - var88.m - var88.o / 2;
                  int var85 = var45 - var88.n - var88.p / 2;
                  var88.a(var1, var80, var85, var88.j > a.b[a.b]);
                  if (var75 == this.d) {
                     int var56 = var66 - this.a.o / 2;
                     int var92 = var45 - this.a.o / 2;

                     for(int var89 = 0; var89 < this.a.length; ++var89) {
                        this.a[var89].a((Graphics)var1, var56, var92, (int)20);
                     }
                  }

                  var4 += this.r;
               }

               a.a.a(var1, 0, 0, var45, 6);
               a.a.a(var1, 1, var5, var45, 10);
               break;
            case 7:
            case 10:
            case 11:
               var1.setFont(aeii.c.b);
               if (this.c != -1) {
                  a.i.a(var1, this.c, -8, var6, 36);
               }

               var5 -= this.C;
               var1.setClip(0, 0, var5, this.c - 10);
               var4 = 0;
               int var42 = 0;
               if (this.b != null) {
                  var1.setColor(aeii.g.a(16777215, this.h, this.G, 5));

                  for(int var97 = 0; var97 < this.b.length; ++var97) {
                     aeii.c.b(var1, this.b[var97], this.C + var5 / 2, var42 + this.z, 17);
                     var42 += this.r;
                  }

                  var1.setColor(10463131);
                  var1.drawLine(0, var42, var5 - 1, var42);
                  var4 = var42;
               }

               int var98 = var42 + this.F;
               var1.setColor(this.a);
               int var87 = this.A;
               int var55 = this.A + this.B;
               if (this.D > 0) {
                  --var87;
                  var42 -= this.r;
               } else if (this.D < 0) {
                  ++var55;
               }

               var42 += this.D + this.F;
               var1.setClip(this.C, var4, var5, var6 - var4);
               var4 = var5;
               if (this.g) {
                  var4 = var5 - a.b.o;
               }

               int var65 = this.C + var4 / 2;

               for(int var12 = var87; var12 < var55; ++var12) {
                  int var73 = 0;
                  if (var42 < var98) {
                     var73 = var98 - var42;
                  } else if (var42 + this.r > var6 - this.F) {
                     var73 = var42 + this.r - var6 + this.F;
                  }

                  int var11;
                  if (this.a == 11 && var12 == this.d) {
                     var1.setColor(5594742);
                     a(var1, 0, var42, var4, this.r);
                     var11 = aeii.g.a(this.h, 16777215, this.r - var73, this.r);
                  } else {
                     var11 = aeii.g.a(this.h, this.a, this.r - var73, this.r);
                  }

                  var11 = aeii.g.a(var11, this.h, this.G, 5);
                  var1.setColor(var11);
                  if (this.P >= 0) {
                     aeii.c.b(var1, this.a[var12], this.P, var42 + this.z, 20);
                  } else {
                     aeii.c.b(var1, this.a[var12], var65, var42 + this.z, 17);
                  }

                  var42 += this.r;
               }

               if (this.g) {
                  int var83 = a.b.p;
                  int var74 = a.b.o;
                  int var79 = a.b.o / 2;
                  int var103 = var6 - (var83 << 1) - 2;
                  var4 = var5 - (var74 + var79) / 2;
                  if (var103 > 2) {
                     var1.setColor(this.a);
                     a(var1, var4, var83 + 1, var79, var103);
                     if ((var42 = (var103 - 2) * this.B / this.s) <= 0) {
                        var42 = 1;
                     }

                     var1.setColor(2370117);
                     a(var1, var4 + 1, var83 + (var103 - 2) * this.A / this.s + 2, var79 - 2, var42);
                     a.b.a(var1, 0, var5 - var74, 0, 20);
                     a.b.a(var1, 1, var5 - var74, var6, 36);
                  } else {
                     if (this.A > 0) {
                        a.b.a(var1, 0, var5 - var74, 0, 20);
                     }

                     if (this.A + this.B < this.s) {
                        a.b.a(var1, 1, var5 - var74, var6, 36);
                     }
                  }
               }

               if (this.a == 7) {
                  var1.setClip(0, 0, super.j, super.k);
                  int var84 = var6;
                  if ((this.x & 2) == 0) {
                     var84 = var6 + 5;
                  }

                  a.b.a(var1, 1, var5 + this.C, var84, 40);
               }
               break;
            case 8:
               aeii.g.a(var1, 0, 0, this.b, this.c);
               int var14 = a.b[0].a;
               int var13 = a.b[0].b;
               int var16 = this.K + this.I;
               int var15 = this.J + this.H;
               int var41 = 4;

               for(int var63 = this.I; var63 < var16; ++var63) {
                  var4 = 4;

                  for(int var53 = this.H; var53 < var15; ++var53) {
                     byte var71 = a.c[this.a[var53][var63]];
                     a.b[var71].a(var1, var4, var41);
                     var4 += var14;
                  }

                  var41 += var13;
               }

               if (this.b != null && this.t == 0) {
                  int var64 = -this.H * var14 + 4;
                  int var54 = -this.I * var13 + 4;
                  int var72 = 0;

                  for(int var24 = a.a.size(); var72 < var24; ++var72) {
                     a var102;
                     if ((var102 = (a)a.a.elementAt(var72)).b >= this.H && var102.b < var15 && var102.c >= this.I && var102.c < var16) {
                        a.n.a(var1, a.d[var102.e] - 1, var102.b * var14 + var64, var102.c * var13 + var54, 0);
                     }
                  }
               }

               if (this.t == 0) {
                  if (this.I > 0) {
                     a.b.a(var1, 0, var5 / 2, 0, 17);
                  }

                  if (this.I + this.K < this.M) {
                     a.b.a(var1, 1, var5 / 2, var6, 33);
                  }

                  if (this.H > 0) {
                     a.a.a(var1, 0, 0, var6 / 2, 6);
                  }

                  if (this.H + this.J < this.L) {
                     a.a.a(var1, 1, var5, var6 / 2, 10);
                  }
               }
               break;
            case 13:
               int var62 = aeii.c.b;
               int var52 = (this.c - var62) / 2;
               var1.setColor(aeii.g.a(1645370, 16777215, this.G, 5));
               a(var1, 0, var52, this.b, var62);
               var1.setFont(aeii.c.b);
               var1.setColor(16777215);
               aeii.c.b(var1, this.a[this.d], 16, var52 + this.z, 20);
               int var70 = this.b - this.r;

               for(int var22 = this.s - 1; var22 >= 0; --var22) {
                  if (var22 == this.d) {
                     a.k.a(var1, 1, var70, 0, 20);
                  } else {
                     a.k.a(var1, 0, var70, 0, 20);
                  }

                  this.a[var22].a(var1, var70 + a.k.o / 2, this.c / 2, 3);
                  var70 -= this.r;
               }
               break;
            case 14:
               var1.setFont(aeii.c.b);
               var1.setColor(aeii.g.a(16777215, 1645370, this.G, 5));
               int var51 = var6 / 2;
               aeii.c.b(var1, this.a[this.d], var5 / 2, (var6 - aeii.c.a) / 2, 17);
               a.a.a(var1, 0, 0, var51, 6);
               a.a.a(var1, 1, var5, var51, 10);
               break;
            case 15:
               int var61 = 0;
               int var40 = 0;
               int var69 = this.b[0];

               for(int var33 = 1; var33 <= this.N; ++var33) {
                  var61 = var69;
                  var69 += this.b[var33];
               }

               if (this.N > 0) {
                  var40 = -var61 + a.c.p;
               }

               for(int var34 = 0; var34 < this.a.size(); ++var34) {
                  e var17;
                  if ((var17 = (e)this.a.elementAt(var34)).q >= var61 && var17.q < var69) {
                     var17.a(var1, 0, var40, var34 == this.f);
                  }
               }

               var1.setClip(0, 0, a.a, a.b);
               if (this.N > 0) {
                  var1.setColor(2370117);
                  var1.fillRect(0, 0, super.j, a.c.p);
                  a.b.a(var1, 0, a.a / 2, -this.t, 17);
               }

               if (this.N < this.O) {
                  var1.setColor(2370117);
                  var6 = this.b[this.N];
                  if (this.N > 0) {
                     var6 += a.c.p;
                  }

                  var1.fillRect(0, var6, super.j, a.b - var6);
                  a.b.a(var1, 1, a.a / 2, a.b + this.t, 33);
               }
         }

         var1.translate(-var2, -var3);
         var1.setClip(0, 0, super.j, super.k);
         if (aeii.f.a.a == this && this.b == 2) {
            if (this.a[0]) {
               a.a(var1, aeii.g.d, 0, a.b);
            }

            if (this.a[1]) {
               a.a(var1, aeii.g.e, 1, a.b);
            }
         }
      }

   }

   public static final void a(Graphics var0, int var1, int var2, int var3, int var4, int var5) {
      a(var0, 0, var2, var3, var4, var5, 2370117, 2370117, 0, 0);
   }

   public static final void a(Graphics var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9) {
      a.a.a.g var10 = a.d;
      var0.setClip(var1, var2, var3, var4);
      var0.setColor(var6);
      var0.fillRect(var1, var2, var3, var4);
      if (var7 != var6) {
         int var11 = var4 / 4;
         int var12 = var2 + 5;

         for(int var13 = 0; var13 < var11; ++var13) {
            int var14 = aeii.g.a(var7, var6, var13, var11);
            var0.setColor(aeii.g.a(var14, var6, var8, var9));
            var0.fillRect(var1, var12, var3, 1);
            ++var12;
         }
      }

      if (var5 != 15) {
         boolean var23 = (var5 & 4) == 0;
         boolean var24 = (var5 & 8) == 0;
         boolean var25 = (var5 & 1) == 0;
         boolean var26 = (var5 & 2) == 0;
         var5 = var3 / var10.o - 2;
         if (var3 % var10.o != 0) {
            ++var5;
         }

         if (!var23) {
            ++var5;
         }

         if (!var24) {
            ++var5;
         }

         var6 = var4 / var10.p - 2;
         if (var4 % var10.p != 0) {
            ++var6;
         }

         if (!var25) {
            ++var6;
         }

         if (!var26) {
            ++var6;
         }

         var7 = var1;
         if (var23) {
            var7 = var1 + var10.o;
         }

         var4 = var2 + var4 - var10.p;

         for(int var21 = 0; var21 < var5; ++var21) {
            if (var25) {
               var10.a(var0, 1, var7, var2, 0);
            }

            if (var26) {
               var10.a(var0, 6, var7, var4, 0);
            }

            var7 += var10.o;
         }

         var8 = var2;
         if (var25) {
            var8 = var2 + var10.p;
         }

         var3 = var1 + var3 - var10.o;

         for(int var18 = 0; var18 < var6; ++var18) {
            if (var23) {
               var10.a(var0, 3, var1, var8, 0);
            }

            if (var24) {
               var10.a(var0, 4, var3, var8, 0);
            }

            var8 += var10.p;
         }

         if (var23 && var25) {
            var10.a(var0, 0, var1, var2, 0);
         }

         if (var24 && var25) {
            var10.a(var0, 2, var3, var2, 0);
         }

         if (var23 && var26) {
            var10.a(var0, 5, var1, var4, 0);
         }

         if (var24 && var26) {
            var10.a(var0, 7, var3, var4, 0);
         }
      }

   }

   static {
      o = ((n = aeii.c.e <= 143 ? 1 : 2) << 1) + 1;
   }
}
