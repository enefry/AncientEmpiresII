package aeii;

import javax.microedition.lcdui.Graphics;

public final class h {
   public static String[] a = new String[]{"road", "grass", "woods", "hill", "mountain", "water", "bridge", "town", "town", "town"};
   public static String[] b = new String[]{"road", "grass", "grass", "grass", "mountain", "water", "bridge", "town", "town", "town"};
   private static String[] c = new String[]{"soldier", "archer", "lizard", "wizard", "wisp", "spider", "golem", "catapult", "wyvern", "king", "skeleton"};
   private g a;
   public a a;
   private byte c;
   public boolean a = false;
   public boolean b = false;
   private boolean c;
   private byte d;
   public byte a;
   private byte e;
   public byte b;
   private byte f;
   private byte g;
   private boolean d;
   private int a = 0;
   private static byte[] a;
   private a.a.a.g a;
   private a.a.a.g b;
   private a.a.a.g c;
   private a.a.a.g d;
   private int b;
   private int c;
   private long a;
   private long b;
   private a.a.a.g[] a;
   private d[] a;
   private d a;
   public h a;
   private int d;
   private int e;
   private int f;
   private int g;
   private boolean e = false;
   private int h;
   private int i;
   private int[][] a;
   private int[] a;
   private int j;
   private int k = 20;
   private int l;
   private int m;
   private byte[][] a;
   private a.a.a.g e;
   private a.a.a.g f;
   private a.a.a.g[] b;
   private int n;
   private int o;

   public h(g var1, a var2, h var3) {
      super();
      this.a = var1;
      this.a = var2;
      this.c = var2.d;
      this.a = var3;
      this.d = (byte)var2.h;
      this.h = this.d;
      this.e = (byte)var2.b();
      this.f = this.e;
      int var4 = 0;
      if (var3 == null) {
         this.g = 0;
         this.b = 0;
         this.d = true;
      } else {
         var4 = var1.c;
         this.g = 1;
         this.a = 0;
      }

      this.d = var1.a(var2.b, var2.c);
      String var5 = a[this.d];
      String var6 = b[this.d];
      if (var3 != null && var6.equals(b[var3.d])) {
         this.a = new d[var3.a.length];
         System.arraycopy(var3.a, 0, this.a, 0, this.a.length);
      } else {
         this.a = (new a.a.a.g(var6)).a;
      }

      if (this.g == 1) {
         for(int var9 = 0; var9 < this.a.length; ++var9) {
            this.a[var9] = new d(this.a[var9], 1);
         }
      }

      try {
         if (var3 != null && var5.equals(a[var3.d])) {
            this.a = var3.a;
         } else {
            this.a = new d(var5 + "_bg");
         }

         if (this.g == 1) {
            this.a = new d(this.a, 1);
         }
      } catch (Exception var7) {
      }

      if (this.a != null) {
         this.e = this.a.b;
      }

      this.f = var1.c / this.a[0].a;
      if (var1.c % this.a[0].a != 0) {
         ++this.f;
      }

      this.g = (var1.o - this.e) / this.a[0].b;
      if ((var1.o - this.e) % this.a[0].b != 0) {
         ++this.g;
      }

      this.a = new byte[this.f][this.g];

      for(int var10 = 0; var10 < this.f; ++var10) {
         for(int var8 = 0; var8 < this.g; ++var8) {
            this.a[var10][var8] = (byte)Math.abs(aeii.c.a.nextInt() % this.a.length);
         }
      }

      this.a = new a.a.a.g(c[this.c], (byte)(var1.d[var2.e] - 1 << 1));
      if (var3 != null && var3.c == this.c) {
         if (var3.d != null) {
            this.d = new a.a.a.g(var3.d);
         }

         if (var3.b != null) {
            this.b = new a.a.a.g(var3.b);
         }

         if (var3.c != null) {
            this.c = new a.a.a.g(var3.c);
         }

         if (var3.e != null) {
            this.e = new a.a.a.g(var3.e);
            this.f = new a.a.a.g(var3.f);
         }
      } else if (this.c != 0 && this.c != 10 && this.c != 5) {
         if (this.c == 1) {
            this.c = new a.a.a.g("archer_arrow");
         } else if (this.c == 9) {
            this.b = new a.a.a.g("kingwave");
            this.d = new a.a.a.g("kingslash");
            this.e = new a.a.a.g("king_heads");
            this.f = new a.a.a.g("king_heads_back");
         } else if (this.c == 2) {
            this.b = new a.a.a.g("watermagic");
            this.c = new a.a.a.g("fish");
         } else if (this.c != 7 && this.c != 6) {
            if (this.c == 3) {
               this.b = new a.a.a.g("spell");
               this.c = this.b;
            }
         } else {
            this.c = new a.a.a.g("crater");
         }
      } else {
         this.d = new a.a.a.g("slash");
      }

      if (this.b != null) {
         this.b.a(0, this.d);
      }

      if (this.c != null) {
         this.c.a(0, this.d);
      }

      if (this.d != null) {
         this.d.a(0, this.d);
      }

      if (this.e != null) {
         this.e.a(var2.j, this.d);
         this.e.c(var2.j);
         this.f.a(0, this.d);
         this.f.c(var2.j);
      }

      this.a = new int[var2.a.length][2];

      for(int var11 = 0; var11 < this.a.length; ++var11) {
         this.a[var11][0] = var2.a[var11][0] * var1.j / 128;
         if (this.g == 1) {
            this.a[var11][0] = var1.c - this.a[var11][0] - this.a.o + var4;
         }

         this.a[var11][1] = var2.a[var11][1] * var1.o / 128 - this.a.p;
      }

      this.a = new a.a.a.g[this.e];
      if (this.c == 4 || this.c == 6) {
         this.a = new int[this.e];
      }

      for(int var12 = 0; var12 < this.e; ++var12) {
         this.a[var12] = new a.a.a.g(this.a);
         var1.a(this.a[var12]);
         this.a[var12].b(this.a[var12][0], this.a[var12][1]);
         this.a[var12].a(0, this.d);
         this.a[var12].c = false;
         this.a[var12].v = 0;
         if (this.c == 6) {
            this.a[var12].e = true;
            this.a[var12] = aeii.c.a((int)360);
            this.a[var12].q = -6 + 4 * aeii.f.a(this.a[var12]) >> 10;
         } else if (this.c == 4) {
            this.a[var12].e = true;
            this.a[var12].q = -5 - aeii.c.a((int)10);
            this.a[var12] = aeii.c.a((int)360);
         } else if (this.c == 9) {
            this.a[var12].a = this.e;
            this.a[var12].b = this.f;
         }
      }

   }

   private int a(a.a.a.g var1, int var2) {
      return this.g == 1 ? this.a.o - var2 - var1.o : var2;
   }

   public final void a() {
      this.a = true;
      this.a = 1;
      this.b = this.a.a;
   }

   public final void b() {
      if (this.c && this.h > this.a) {
         this.h -= 2;
         if (this.h < this.a) {
            this.h = this.a;
         }

         this.a.a = true;
      }

      if (this.c != 8 && this.c != 9 && this.c != 7 && this.c != 1 && this.c != 3 && this.c != 2 && this.c != 6) {
         if (this.c == 4) {
            h var7 = this;
            switch (this.a) {
               case 1:
                  if (this.l == 0) {
                     if (this.a.a - this.b >= 200L) {
                        this.l = 1;
                        this.b = this.a.a;
                     }
                  } else {
                     if (this.l == 1) {
                        this.k += 5;
                        if (this.k >= 90) {
                           ++this.l;
                           this.b = this.a.a;
                        }

                        if ((this.k - 20) % 15 == 0) {
                           for(int var15 = 0; var15 < var7.a.length; ++var15) {
                              a.a.a.g var27;
                              (var27 = a.a.a.g.a(var7.a.o, aeii.c.a(-1, 2), 0, 0, 1, 100, (byte)0)).b(var7.a[var15].m + aeii.c.a(var7.a.o - var27.o), var7.a[var15].n + var7.a.p - var27.p + 1);
                              var7.a.a(var27);
                           }
                        }
                     } else if (this.l == 2 && this.a.a - this.b >= 400L) {
                        this.k = 20;
                        this.a.c();
                        this.a = 4;
                        this.a.e = false;
                        this.b = this.a.a;
                     }

                     if (++var7.j < 2) {
                        break;
                     }

                     for(int var16 = 0; var16 < var7.a.a.length; ++var16) {
                        a.a.a.g var28;
                        (var28 = a.a.a.g.a(var7.a.g, 0, 0, 0, 1, 50, (byte)0)).b(var7.a.a[var16].m + aeii.c.a(var7.a.a[var16].o - var28.o), var7.a.a[var16].n + aeii.c.a(var7.a.a[var16].p - var28.p));
                        var28.d = true;
                        var7.a.a(var28);
                     }

                     var7.j = 0;
                     h var29 = var7.a;

                     for(int var43 = 0; var43 < var29.f; ++var43) {
                        var29.a[var43].x = 6;
                     }
                  }
                  break;
               case 4:
                  if (this.a.a - this.b >= 800L) {
                     this.b = true;
                     this.a = 0;
                  }
            }

            boolean var17 = false;
            if (var7.a.a - var7.a >= 300L) {
               var17 = true;
               var7.a = var7.a.a;
            }

            for(int var30 = 0; var30 < var7.a.length; ++var30) {
               if (var17) {
                  a.a.a.g var44;
                  (var44 = a.a.a.g.a((a.a.a.g)null, 0, 0, 0, 1, 500, (byte)4)).b(var7.a[var30].m + (var7.a[var30].o >> 1), var7.a[var30].n + (var7.a[var30].p >> 1) + var7.c);
                  var7.a.a(var44);
               }

               int var45 = var7.a[var30].o / 3;
               var7.a[var30].b(var7.a[var30][0] + (var45 * aeii.f.b(var7.a[var30]) >> 10), var7.a[var30][1] + (var45 * aeii.f.a(var7.a[var30]) / 3 >> 10));
               var7.a[var30] = (var7.a[var30] + var7.k) % 360;
            }
         } else {
            switch (this.a) {
               case 1:
                  if (this.a.a - this.b >= 200L) {
                     this.a = 3;
                  }
               case 2:
               case 5:
               default:
                  break;
               case 3:
                  boolean var9 = true;
                  if (this.l < this.f) {
                     if (this.c == 0 || this.c == 5) {
                        this.a[this.l].w = -6;
                     }

                     if (this.c == 5) {
                        this.a[this.l].u = 2 * a[this.g];

                        for(int var19 = 0; var19 < 3; ++var19) {
                           a.a.a.g var31;
                           (var31 = a.a.a.g.a(this.a.o, aeii.c.a(-1, 2), 0, -1, 1, 100, (byte)0)).b(this.a[this.l].m + aeii.c.a(this.a.o - var31.o), this.a[this.l].n + this.a.p - var31.p + 1);
                           this.a.a(var31);
                        }
                     } else {
                        this.a[this.l].u = a[this.g];
                     }

                     this.a[this.l].a(1, this.d);
                     ++this.l;
                     var9 = false;
                  }

                  for(int var20 = 0; var20 < this.l; ++var20) {
                     if (this.c == 10) {
                        if (this.a[var20].y != -1) {
                           ++this.a[var20].y;
                           if (this.a[var20].y >= 16) {
                              this.a[var20].a(2, this.d);
                              this.a[var20].u = 0;
                              this.a[var20].y = -1;
                              a.a.a.g var32;
                              (var32 = a.a.a.g.a((a.a.a.g)null, 0, 0, 0, 1, 800, (byte)2)).b(this.a[var20].m + this.a(var32, this.a.o), this.a[var20].n + this.a.p);
                              this.a.a(var32);
                              (var32 = a.a.a.g.a(this.d, 0, 0, 0, 1, 150, (byte)0)).b(this.a[var20].m + this.a(var32, 24), this.a[var20].n + 3);
                              this.a.a(var32);
                           } else {
                              var9 = false;
                           }
                        }
                     } else if (this.a[var20].q < 0) {
                        ++this.a[var20].w;
                        var9 = false;
                     } else if (this.a[var20].w >= 6) {
                        this.a[var20].q = 0;
                        this.a[var20].w = 0;
                        this.a[var20].u = 0;
                        if (this.c == 0 || this.c == 5) {
                           this.a[var20].a(2, this.d);
                           if (this.c == 0) {
                              a.a.a.g var34;
                              (var34 = a.a.a.g.a(this.d, 0, 0, 0, 1, 150, (byte)0)).b(this.a[var20].m + this.a(var34, 14), this.a[var20].n + this.a[var20].p);
                              var34.q = 4 - this.a[var20].p;
                              this.a.a(var34);
                           } else if (this.c == 5) {
                              a.a.a.g var35;
                              (var35 = a.a.a.g.a(this.a.g, 0, 0, 0, 1, 50, (byte)0)).b(this.a[var20].m + this.a(var35, this.a.o * 3 / 4), this.a[var20].n + this.a[var20].p);
                              var35.q = -var35.p;
                              this.a.a(var35);
                           }
                        }
                     }
                  }

                  if (var9) {
                     this.l = 0;
                     this.a = 6;
                     this.a.c();
                     this.a.a((int)200);
                     aeii.c.d(200);
                     aeii.c.b(14, 1);
                     this.b = this.a.a;
                  }
                  break;
               case 4:
                  boolean var8 = true;
                  if (this.l < this.f) {
                     if (this.c != 0 && this.c != 2 && this.c != 5) {
                        if (this.c == 10) {
                           this.a[this.l].y = 0;
                        }
                     } else {
                        this.a[this.l].w = -6;
                     }

                     if (this.c == 5) {
                        this.a[this.l].u = -2 * a[this.g];
                     } else {
                        this.a[this.l].u = -a[this.g];
                     }

                     this.a[this.l].a(3, this.d);
                     ++this.l;
                     var8 = false;
                  }

                  for(int var18 = 0; var18 < this.l; ++var18) {
                     if (this.c == 10) {
                        if (this.a[var18].y != -1) {
                           ++this.a[var18].y;
                           if (this.a[var18].y >= 16) {
                              this.a[var18].a(0, this.d);
                              this.a[var18].u = 0;
                              this.a[var18].y = -1;
                           } else {
                              var8 = false;
                           }
                        }
                     } else if (this.a[var18].q < 0) {
                        ++this.a[var18].w;
                        var8 = false;
                     } else if (this.a[var18].w >= 6) {
                        this.a[var18].w = 0;
                        this.a[var18].u = 0;
                        this.a[var18].q = 0;
                        this.a[var18].a(0, this.d);
                     }
                  }

                  if (var8) {
                     this.b = true;
                     this.a = 0;
                     this.b = this.a.a;
                  }
                  break;
               case 6:
                  if (this.c == 10 && this.a.a - this.b >= 400L || (this.c == 0 || this.c == 2 || this.c == 5) && this.a.a - this.b >= 50L) {
                     this.a = 4;
                  }
            }
         }
      } else {
         h var1 = this;
         switch (this.a) {
            case 1:
               if (this.c == 6) {
                  if (this.l == 0) {
                     if (this.a.a - this.b >= 200L) {
                        if (this.o < this.f) {
                           this.a[this.o].y = -1;
                           this.a[this.o].e = false;
                           this.a[this.o].w = 0;
                        }

                        if (++this.o >= this.f) {
                           this.o = 0;
                           this.l = 1;
                           this.b = this.a.a;
                        }
                     }
                  } else if (this.l == 1) {
                     if (this.a.a - this.b >= 200L) {
                        if (this.o < this.f) {
                           this.a[this.o].w = -1;
                        }

                        if (++this.o >= this.f) {
                           this.o = 0;
                           this.l = 2;
                           this.b = this.a.a;
                        }
                     }
                  } else if (this.l == 2) {
                     boolean var12;
                     label435: {
                        var12 = true;
                        if (this.a.a - this.b >= 200L) {
                           if (this.o < this.f) {
                              this.a[this.o].y = 0;
                              this.a[this.o].w = 0;
                              this.a[this.o].a(2, this.d);
                              this.a[this.o].r = 1;
                           }

                           if (++this.o >= this.f) {
                              break label435;
                           }
                        }

                        var12 = false;
                     }

                     for(int var23 = 0; var23 < var1.f; ++var23) {
                        if (var1.a[var23].y != 0) {
                           if (var1.a[var23].y != -1) {
                              if (var1.a[var23].r > 0) {
                                 var12 = false;
                              } else if (var1.a[var23].r != -1) {
                                 if (var1.a[var23].y == 1) {
                                    var1.a[var23].a(3, var1.d);
                                    var1.a[var23].r = 1;
                                    var1.a[var23].y = 2;
                                    var12 = false;
                                 } else if (var1.a[var23].y == 2) {
                                    var1.a[var23].q = -6;
                                    var1.a[var23].e = true;
                                    var1.a[var23].w = aeii.c.a(-2, 3);
                                    var1.a[var23].a(0, var1.d);
                                    var1.a[var23].r = -1;
                                    var1.a[var23].y = 3;
                                    var12 = false;
                                 }
                              }
                           }
                        } else {
                           if (var1.a[var23].l == 1) {
                              var1.a[var23].q = 0;
                              var1.a[var23].y = 1;
                              aeii.c.d(200);
                              var1.a.a((int)1200);
                              aeii.c.b(14, 1);

                              for(int var37 = 0; var37 < 2; ++var37) {
                                 a.a.a.g var13;
                                 (var13 = a.a.a.g.a(var1.a.e, 0, 0, -1, 1, aeii.c.a((int)4) * 50, (byte)0)).b(var1.a[var23].m + aeii.c.a(var1.a.o - var13.o), var1.a[var23].n + var1.a.p - var13.p + 2);
                                 var13.d = true;
                                 var1.a.a(var13);
                              }

                              a.a.a.g var38;
                              (var38 = a.a.a.g.a(var1.a.e, -1, 0, -1, 1, aeii.c.a((int)4) * 50, (byte)0)).b(var1.a[var23].m, var1.a[var23].n + var1.a.p - var38.p + 2);
                              var38.d = true;
                              var1.a.a(var38);
                              (var38 = a.a.a.g.a(var1.a.e, 1, 0, -1, 1, aeii.c.a((int)4) * 50, (byte)0)).b(var1.a[var23].m + var1.a.o - var38.o, var1.a[var23].n + var1.a.p - var38.p + 2);
                              var38.d = true;
                              var1.a.a(var38);
                           }

                           var12 = false;
                        }
                     }

                     if (var12) {
                        var1.l = 0;
                        var1.b = var1.a.a;
                        var1.a = 4;
                     }
                  }
               } else if (this.l == 0) {
                  if (this.a.a - this.b >= 200L) {
                     if (this.c == 9) {
                        this.b = new a.a.a.g[this.f << 1];
                     } else if (this.c != 7 && this.c != 1) {
                        this.b = new a.a.a.g[this.f];
                     }

                     this.l = 1;
                     this.o = 0;
                     this.b = this.a.a;
                  }
               } else if (this.l == 1) {
                  boolean var14 = true;
                  if (this.o < this.f) {
                     this.a[this.o].a(2, this.d);
                     this.a[this.o].r = 1;
                     if (this.c != 3 && this.c != 2) {
                        if (this.c == 1) {
                           a.a.a.g var47;
                           (var47 = a.a.a.g.a(this.c, 0, 0, 0, 1, 0, (byte)0)).a(1, this.d);
                           var47.b(this.a[this.o].m + this.a(var47, this.a[this.o].o), this.a[this.o].n);
                           this.a.a(var47);
                        } else if (this.c == 7) {
                           this.a[this.o].x = 5;

                           for(int var25 = 0; var25 < 3; ++var25) {
                              a.a.a.g var41;
                              (var41 = a.a.a.g.a(var1.a.o, aeii.c.a(-1, 2), 0, 0, 1, aeii.c.a((int)4) * 50, (byte)0)).b(var1.a[var1.o].m + var1.a.o / 2, var1.a[var1.o].n);
                              var41.d = true;
                              var1.a.a(var41);
                           }
                        } else if (this.c == 9) {
                           a.a.a.g var46;
                           (var46 = a.a.a.g.a(this.d, 0, 0, 0, 1, 200, (byte)0)).b(this.a[0].m, this.a[0].n + this.a.p);
                           var46.q = -this.a.p;
                           this.a.a(var46);
                           this.b[0] = a.a.a.g.a(this.b, a[this.g] * 3, -2, 0, -1, 100, (byte)0);
                           int var40 = this.a[this.o].m + this.a(this.b[this.o], this.a[this.o].o / 2);
                           int var24 = this.a[this.o].n + this.a.p - this.b[this.o].p + 2;
                           this.b[0].b(var40, var24);
                           this.b[1] = a.a.a.g.a(this.b, a[this.g] * 3, 1, 0, -1, 100, (byte)0);
                           this.b[1].b(var40, var24);
                           this.a.a(this.b[1]);
                           this.b[1].l = this.g;
                           this.a.a(this.b[0]);
                           this.b[0].l = this.g;
                        } else if (this.c == 8) {
                           this.b[this.o] = a.a.a.g.a((a.a.a.g)null, a[this.g], 0, 0, -1, 2000, (byte)6);
                           this.b[this.o].b(this.a[this.o].m + this.a(this.b[this.o], this.a[this.o].o + 2), this.a[this.o].n + 30);
                           this.b[this.o].d = true;
                           aeii.c.d(200);
                           this.a.a((int)1200);
                           aeii.c.b(14, 1);
                           this.a.a(this.b[this.o]);
                        }
                     } else {
                        this.b[this.o] = a.a.a.g.a(this.b, 0, 0, 0, 1, 50, (byte)0);
                        this.b[this.o].b(this.a[this.o].m + this.a(this.b[this.o], this.a[this.o].o), this.a[this.o].n);
                        this.a.a(this.b[this.o]);
                     }
                  }

                  if (++var1.o < var1.f) {
                     var14 = false;
                  }

                  for(int var26 = 0; var26 < var1.f; ++var26) {
                     if (var1.a[var26].r > 0) {
                        var14 = false;
                     } else if (var1.c != 7 && var1.a[var26].r != -1) {
                        if (var1.a[var26].y == 0) {
                           var1.a[var26].a(3, var1.d);
                           var1.a[var26].r = 1;
                           var1.a[var26].y = 1;
                           if (var1.c == 8) {
                              var1.b[var26].b = false;
                           }

                           var14 = false;
                        } else if (var1.a[var26].y == 1) {
                           var1.a[var26].a(0, var1.d);
                           var1.a[var26].r = -1;
                           var1.a[var26].y = 2;
                           var14 = false;
                        }
                     }

                     if (var1.c == 8 && var1.b[var26] != null && var1.b[var26].b) {
                        a.a.a.g var42;
                        (var42 = a.a.a.g.a(var1.a.o, a[var1.g] * aeii.c.a(1, 4), aeii.c.a(-2, 3), 0, 1, 50 * aeii.c.a((int)4), (byte)0)).b(var1.a[var26].m + var1.a(var42, var1.a.o), var1.b[var26].n + aeii.c.a(30 - var1.b[var26].p) - 15);
                        var42.d = true;
                        var1.a.a(var42);
                     }
                  }

                  if (var14) {
                     if (var1.a.d == 9) {
                        var1.a = 6;
                     } else {
                        var1.a = 4;
                     }

                     var1.m = 400;
                     if (var1.a.d == 8) {
                        var1.m = 0;
                     }

                     var1.b = var1.a.a;
                     var1.l = 0;
                  }
               }
            case 2:
            case 3:
            case 5:
            default:
               break;
            case 4:
               if (this.l == 0) {
                  if (this.a.a - this.b >= (long)this.m) {
                     this.a.c();
                     if (this.c != 1) {
                        this.a.a((int)200);
                     }

                     aeii.c.d(200);
                     aeii.c.b(14, 1);
                     if (this.c != null) {
                        this.n = aeii.a.a[this.c].length;
                     }

                     this.l = 1;
                  }
               } else if (this.l == 1) {
                  if (--this.n < 0) {
                     this.a = 7;
                     this.b = this.a.a;
                  } else {
                     a.a.a.g var5;
                     if (this.c != 3 && this.c != 2 && this.c != 1) {
                        var5 = a.a.a.g.a(this.c, 0, 0, 0, -1, 0, (byte)0);
                     } else {
                        var5 = a.a.a.g.a(this.c, 0, 0, 0, 1, 0, (byte)0);
                     }

                     if (this.c == 2 || this.c == 1) {
                        var5.c = false;
                     }

                     int var36 = aeii.c.a(this.a.a / 2 - var5.o);
                     int var21 = 0;
                     if (this.a.a != null) {
                        var21 = 0 + (this.a.a.b - var5.p);
                     }

                     int var11 = (this.a.o - var21) * ((this.n << 1) + 1) / (aeii.a.a[this.c].length << 1) - var5.p / 2 + var21;
                     if (this.g == 0) {
                        var36 += this.a.c;
                     }

                     if (this.c == 7 || this.c == 6) {
                        var5.q = var11;
                        var11 = 0;
                     }

                     var5.b(var36, var11);
                     this.a.a(var5);

                     for(int var22 = 0; var22 < 3; ++var22) {
                        a.a.a.g var6;
                        if (var1.c != 7 && var1.c != 6) {
                           (var6 = a.a.a.g.a(var1.a.o, aeii.c.a(-1, 2), 0, -1, 1, 100, (byte)0)).b(var36 + aeii.c.a(var5.o - var6.o), var11 + var5.p - var6.p + 1);
                        } else {
                           (var6 = a.a.a.g.a(var1.a.e, aeii.c.a(-1, 2), 0, aeii.c.a(-2, 0), 1, aeii.c.a((int)4) * 50, (byte)0)).b(var36 + aeii.c.a(var5.o - var6.o), var5.q + var5.p - var6.p + 1);
                           var6.q = -var5.p / 2;
                        }

                        var1.a.a(var6);
                     }
                  }
               }
               break;
            case 6:
               boolean var2 = true;

               for(int var3 = 0; var3 < var1.b.length; ++var3) {
                  if (var1.c == 9 && aeii.c.a((int)2) == 0) {
                     a.a.a.g var4;
                     (var4 = a.a.a.g.a(var1.a.o, aeii.c.a(-2, 1), 0, -1, 1, 100, (byte)0)).b(var1.b[var3].m + var1.a(var1.b[var3], 0), var1.b[var3].n + var1.b[var3].p - var4.p);
                     if (var1.b[var3].y == 1) {
                        var4.l = var1.a.g;
                     } else {
                        var4.l = var1.g;
                     }

                     var1.a.a(var4);
                  }

                  if (var1.c == 9) {
                     if (var1.g == 0) {
                        if (var1.b[var3].m < var1.a.a) {
                           var2 = false;
                           continue;
                        }

                        if (var1.b[var3].y != 0) {
                           if (var1.b[var3].y == 1) {
                              var1.a.b(var1.b[var3]);
                              var1.b[var3].y = 2;
                           }
                           continue;
                        }

                        var1.b[var3].b(var1.a.c - var1.b[var3].o, var1.b[var3].n);
                        var1.b[var3].l = var1.a.g;
                        var1.b[var3].y = 1;
                     } else {
                        if (var1.g != 1) {
                           continue;
                        }

                        if (var1.b[var3].m + var1.b[var3].o < 0) {
                           if (var1.b[var3].y != 0) {
                              if (var1.b[var3].y == 1) {
                                 var1.a.b(var1.b[var3]);
                                 var1.b[var3].y = 2;
                              }
                              continue;
                           }

                           var1.b[var3].b(var1.a.c, var1.b[var3].n);
                           var1.b[var3].l = var1.a.g;
                           var1.b[var3].y = 1;
                        }
                     }

                     var2 = false;
                  }
               }

               if (var2) {
                  var1.l = 0;
                  var1.a = 4;
                  var1.b = var1.a.a;
               }
               break;
            case 7:
               if (this.a.a - this.b >= 1000L) {
                  this.a = 0;
                  this.b = true;
               }
         }
      }

      if (this.c == 6) {
         for(int var10 = 0; var10 < this.a.length; ++var10) {
            if (this.a[var10].e) {
               this.a[var10].q = -6 + 4 * aeii.f.a(this.a[var10]) >> 10;
               this.a[var10] = (this.a[var10] + 10) % 360;
            }
         }
      }

   }

   private void c() {
      this.c = true;
      this.i = this.e - this.b;
      this.f = this.b;

      for(int var3 = 0; var3 < this.i; ++var3) {
         this.a.b(this.a[var3]);
         a.a.a.g var1;
         (var1 = a.a.a.g.a(this.a.g, 0, 0, 0, 1, 0, (byte)0)).b(this.a[var3].m + (this.a[var3].o - var1.o) / 2, this.a.k);
         var1.q = this.a[var3].n + (this.a[var3].p - var1.p) / 2 - this.a.k;
         this.a.a(var1);

         for(int var2 = 0; var2 < 3; ++var2) {
            a.a.a.g var4;
            (var4 = a.a.a.g.a(this.a.o, var2 + -1, 0, aeii.c.a(-4, -1), 1, aeii.c.a((int)4) * 50, (byte)0)).b(this.a[var3].m + (this.a[var3].o - var4.o) / 2, this.a[var3].n + this.a[var3].p - var4.p + 3);
            this.a.a(var4);
         }

         a.a.a.g var8;
         (var8 = a.a.a.g.a(this.a.e, 0, 0, -1, 1, 200, (byte)0)).b(this.a[var3].m + (this.a[var3].o - var8.o) / 2, this.a[var3].n + this.a[var3].p - var8.p + 3);
         this.a.a(var8);
      }

      a.a.a.g[] var7 = new a.a.a.g[this.f];
      System.arraycopy(this.a, this.i, var7, 0, this.f);
      this.a = var7;
      a.a.a.g var5 = a.a.a.g.a((String)("" + (this.a - this.d)), 0, -4, (byte)1);
      int var6;
      int var9;
      if (this.b == 1) {
         var9 = this.a[0].m + this.a[0].o / 2;
         var6 = this.a[0].n + this.a[0].p + 1;
      } else {
         var9 = this.a.c / 2;
         if (this.g == 1) {
            var9 += this.a.c;
         }

         var6 = (this.a.o + this.e) / 2;
      }

      var5.b(var9, var6);
      var5.d = true;
      this.a.a(var5);
   }

   public final void a(Graphics var1, int var2, int var3) {
      var1.translate(var2, var3);
      int var4 = 0;
      int var5 = 0;

      for(int var6 = this.f; var5 < var6; ++var5) {
         int var8 = this.e + var3;
         int var7 = 0;

         for(int var9 = this.g; var7 < var9; ++var7) {
            this.a[this.a[var5][var7]].a(var1, var4, var8);
            var8 += 24;
         }

         var4 += 24;
      }

      if (this.a != null) {
         var5 = this.a.a;
         var4 = 0;
         int var12 = 0;

         for(int var13 = (this.a.c + var5 - 1) / var5; var12 < var13; ++var12) {
            this.a.a(var1, var4, 0);
            var4 += var5;
         }
      }

      var1.translate(-var2, -var3);
   }

   public final void a(Graphics var1) {
      int var2 = this.a.k - aeii.g.a / 2;
      aeii.c.a(var1, this.h + "/" + 100, this.a.c / 2, var2, 1, 3);
   }

   public final void b(Graphics var1) {
      var1.setColor(4210752);

      for(int var2 = 0; var2 < this.f; ++var2) {
         a.a.a.g var3 = this.a[var2];
         if (this.c == 0 || this.c == 4 || this.c == 5 || this.c == 6) {
            var1.fillArc(var3.m, var3.n + (var3.p << 2) / 5, var3.o, var3.p / 4, 0, 360);
         }
      }

   }

   static {
      byte[] var10000 = new byte[]{18, -18};
      a = new byte[]{3, -3};
   }
}
