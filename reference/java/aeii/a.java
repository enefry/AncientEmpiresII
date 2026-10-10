package aeii;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class a extends a.a.g {
   public static byte a = 12;
   public static byte b = 6;
   public static byte c = 6;
   private static g a;
   public String a;
   public short a;
   public int a;
   public byte[][] a;
   private Vector a;
   private short d;
   private long a;
   public byte d;
   public byte e;
   public short b;
   public short c;
   public int b;
   public int c;
   public int d;
   public int e;
   public int f;
   public int g;
   public int h;
   public byte f;
   public byte g;
   private short e;
   private short f;
   private short g;
   private boolean f;
   private boolean g = true;
   private int B;
   private long b;
   public byte h;
   public byte i;
   private int C;
   public a a;
   public byte j = 0;
   public int i;
   public int j;
   public int k;
   private byte m;
   private static String[] a = new String[]{"soldier", "archer", "lizard", "wizard", "wisp", "spider", "golem", "catapult", "wyvern", "king", "skeleton", "crystall"};
   public static byte[] a = new byte[12];
   private static byte[][] c = new byte[12][2];
   private static byte[] c = new byte[12];
   private static byte[] d = new byte[12];
   private static byte[] e = new byte[12];
   public static byte[][][] a = new byte[12][][];
   public static short[] a = new short[12];
   private static short[] b = new short[12];

   private a(byte var1, byte var2, int var3, int var4, boolean var5) {
      super(a.a[var2 - 1 << 1][var1]);
      this.m = var2;
      this.d = var1;
      this.f = 0;
      this.b = (short)var3;
      this.c = (short)var4;
      this.b(var3 * 24, var4 * 24);
      this.a((byte)0);
      if (var5) {
         a.a.addElement(this);
      }

   }

   public final void a(byte var1) {
      this.a = (short)var1;
      var1 <<= 1;
      this.d = c[this.d][0] + var1;
      this.e = c[this.d][1] + var1;
      this.f = c[this.d] + var1;
      this.g = a[this.d];
      if (this.d != 9) {
         if ((var1 = this.a / 2) > 3) {
            var1 = 3;
         }

         this.a = aeii.f.a(139 + (this.d << 2) + var1);
      }

   }

   public final void a(int var1) {
      this.f = true;
      this.b = a.a;
      this.B = var1;
   }

   public static final a a(byte var0, byte var1, int var2, int var3) {
      return a(var0, var1, var2, var3, true);
   }

   public static final a a(byte var0, byte var1, int var2, int var3, boolean var4) {
      if (var0 == 9 && a.a[var1] >= a.a[var1].length) {
         return null;
      } else {
         a var5;
         (var5 = new a(var0, a.d[var1], var2, var3, var4)).d = var0;
         var5.e = var1;
         var5.h = 100;
         var5.a = a[var0];
         var5.j = a[var0];
         if (var0 == 9) {
            var5.b(a.d[var1] - 1);
            var5.i = a.a[var1];
            a.a[var1][var5.i] = var5;
            int var10002 = a.a[var1]++;
         }

         return var5;
      }
   }

   public static final a b(byte var0, byte var1, int var2, int var3, boolean var4) {
      a var5;
      (var5 = new a(var0, a.d[var1], var2, var3, var4)).d = var0;
      var5.e = var1;
      var5.h = 100;
      var5.a = a[var0];
      var5.j = a[var0];
      if (var0 == 9) {
         var5.b(a.d[var1] - 1);
      }

      return var5;
   }

   public final void a() {
      a.a.removeElement(this);
   }

   public final void b(int var1) {
      this.j = (byte)var1;
      this.a = aeii.f.a(var1 + 93);
   }

   public final int a(a var1) {
      return this.a((a)var1, this.b, this.c);
   }

   private int a(a var1, int var2, int var3) {
      int var4 = this.f;
      if (var1 != null) {
         if (this.a((short)64) && var1.a((short)1)) {
            var4 += 15;
         }

         if (this.d == 4 && var1.d == 10) {
            var4 += 15;
         }
      }

      if (this.a((short)2) && a.a(var2, var3) == 5) {
         var4 += 10;
      }

      if (a.a[var2][var3] == 34) {
         var4 += 25;
      }

      return var4;
   }

   public final int b(a var1) {
      return this.a((int)this.b, (int)this.c);
   }

   private int a(int var1, int var2) {
      byte var3 = a.a(var1, var2);
      int var4 = this.g + aeii.g.a[var3];
      if (this.a((short)2) && var3 == 5) {
         var4 += 15;
      }

      if (a.a[var1][var2] == 34) {
         var4 += 15;
      }

      return var4;
   }

   public final int c(a var1) {
      int var2 = aeii.c.a(this.d, this.e) + this.a(var1);
      int var3 = var1.f + var1.b(this);
      if ((var2 = (var2 - var3) * this.h / 100) < 0) {
         var2 = 0;
      } else if (var2 > var1.h) {
         var2 = var1.h;
      }

      var1.h -= var2;
      this.a += var1.e() * var2;
      return var2;
   }

   private int e() {
      return this.d + this.e + this.f;
   }

   public final int a() {
      return (this.e() * 100 << 1) / 3;
   }

   public final boolean a() {
      int var1 = this.a();
      if (this.a >= var1) {
         this.a -= var1;
         this.a((byte)(this.a + 1));
         return true;
      } else {
         return false;
      }
   }

   public final boolean a(int var1, int var2) {
      return this.f != 4 && this.h > 0 && Math.abs(this.b - var1) + Math.abs(this.c - var2) == 1 && e[this.d] == 1;
   }

   public final void b(byte var1) {
      this.g |= var1;
      this.b();
      if (var1 == 1) {
         this.i = a.b;
      }

   }

   public final void c(byte var1) {
      this.g = (byte)(this.g & ~var1);
      this.b();
   }

   public final void b() {
      this.e = 0;
      this.f = 0;
      this.g = 0;
      if ((this.g & 1) != 0) {
         this.f = (short)(this.f - 10);
         this.g = (short)(this.g - 10);
      }

      if ((this.g & 2) != 0) {
         this.f = (short)(this.f + 10);
      }

   }

   public final void a(int var1, int var2) {
      this.b = (short)var1;
      this.c = (short)var2;
      super.m = (short)(var1 * 24);
      super.n = (short)(var2 * 24);
   }

   public final int b() {
      int var1 = 100 / this.a.length;
      int var2 = this.h / var1;
      if (this.h != 100 && this.h % var1 > 0) {
         ++var2;
      }

      return var2;
   }

   public final int a(int var1, int var2, a var3) {
      return (this.d + this.e + this.f + this.a(var3, var1, var2) + this.a(var1, var2)) * this.h / 100;
   }

   public final void a(byte[][] var1, int var2, int var3) {
      byte var4 = e[this.d];
      byte var5 = d[this.d];
      int var6;
      if ((var6 = var2 - var5) < 0) {
         var6 = 0;
      }

      int var7;
      if ((var7 = var3 - var5) < 0) {
         var7 = 0;
      }

      int var8;
      if ((var8 = var2 + var5) >= a.f) {
         var8 = a.f - 1;
      }

      int var9;
      if ((var9 = var3 + var5) >= a.g) {
         var9 = a.g - 1;
      }

      for(int var12 = var6; var12 <= var8; ++var12) {
         for(int var10 = var7; var10 <= var9; ++var10) {
            int var11;
            if ((var11 = Math.abs(var12 - var2) + Math.abs(var10 - var3)) >= var4 && var11 <= var5 && var1[var12][var10] <= 0) {
               var1[var12][var10] = 127;
            }
         }
      }

   }

   public final void a(byte[][] var1) {
      if (this.a((short)512)) {
         this.a((byte[][])var1, this.b, this.c);
      } else {
         this.b(var1);

         for(int var2 = 0; var2 < a.f; ++var2) {
            for(int var3 = 0; var3 < a.g; ++var3) {
               if (var1[var2][var3] > 0 && var1[var2][var3] != 127) {
                  this.a(var1, var2, var3);
               }
            }
         }

      }
   }

   public final a[] a(int var1, int var2, byte var3) {
      return this.a(var1, var2, e[this.d], d[this.d], var3);
   }

   public final a[] a(int var1, int var2, int var3, int var4, byte var5) {
      Vector var6 = new Vector();
      int var7;
      if ((var7 = var1 - var4) < 0) {
         var7 = 0;
      }

      int var8;
      if ((var8 = var2 - var4) < 0) {
         var8 = 0;
      }

      int var9;
      if ((var9 = var1 + var4) >= a.f) {
         var9 = a.f - 1;
      }

      int var10;
      if ((var10 = var2 + var4) >= a.g) {
         var10 = a.g - 1;
      }

      for(int var13 = var7; var13 <= var9; ++var13) {
         for(int var11 = var8; var11 <= var10; ++var11) {
            int var12;
            if ((var12 = Math.abs(var13 - var1) + Math.abs(var11 - var2)) >= var3 && var12 <= var4) {
               if (var5 == 0) {
                  a var15;
                  if ((var15 = a.a((int)var13, var11, (byte)0)) != null) {
                     if (a.e[var15.e] != a.e[this.e]) {
                        var6.addElement(var15);
                     }
                  } else if (this.d == 7 && a.a(var13, var11) == 8 && aeii.g.a(a.a[var13][var11]) && !a.a((int)var13, var11, (int)a.e[this.e])) {
                     (var15 = a((byte)0, (byte)0, var13, var11, false)).d = -1;
                     var15.f = 4;
                     var6.addElement(var15);
                  }
               } else if (var5 == 1) {
                  a var17;
                  if ((var17 = a.a((int)var13, var11, (byte)1)) != null) {
                     var6.addElement(var17);
                  }
               } else {
                  a var18;
                  if (var5 == 2 && (var18 = a.a((int)var13, var11, (byte)0)) != null && a.e[var18.e] == a.e[this.e]) {
                     var6.addElement(var18);
                  }
               }
            }
         }
      }

      a[] var14 = new a[var6.size()];
      var6.copyInto(var14);
      return var14;
   }

   public final void a(int var1, int var2, boolean var3) {
      this.a(var1, var2, var3, false);
   }

   public final void a(int var1, int var2, boolean var3, boolean var4) {
      if (var3) {
         this.a = this.a((int)this.b, (int)this.c, (int)var1, (int)var2);
      } else {
         if (var4 && a.a((int)var1, var2, (byte)0) != null) {
            var4 = false;

            for(int var5 = var1 - 1; var5 <= var1 + 1; ++var5) {
               for(int var9 = var2 - 1; var9 <= var2 + 1; ++var9) {
                  if (((var5 != var1 || var9 != var2) && var5 == var1 || var9 == var2) && a.a((int)var5, var9, (byte)0) == null) {
                     var1 = var5;
                     var2 = var9;
                     var4 = true;
                     break;
                  }
               }

               if (var4) {
                  break;
               }
            }
         }

         this.a = new Vector();
         short[] var13 = new short[]{this.b, this.c};
         this.a.addElement(var13);
         short var16 = this.b;
         if ((var3 = Math.abs(var1 - this.b)) > 0) {
            int var6 = (var1 - this.b) / var3;

            for(int var14 = 0; var14 < var3; ++var14) {
               var16 = (short)(var16 + var6);
               short[] var7 = new short[]{var16, this.c};
               this.a.addElement(var7);
            }
         }

         short var17 = this.c;
         if ((var3 = Math.abs(var2 - this.c)) > 0) {
            int var15 = (var2 - this.c) / var3;

            for(int var18 = 0; var18 < var3; ++var18) {
               var17 = (short)(var17 + var15);
               short[] var8 = new short[]{var16, var17};
               this.a.addElement(var8);
            }
         }
      }

      this.b = var1;
      this.c = var2;
      this.d = 1;
      this.f = 1;
   }

   public final Vector a(int var1, int var2, int var3, int var4) {
      Vector var5 = null;
      short[] var6 = new short[]{(short)var3, (short)var4};
      if (var1 == var3 && var2 == var4) {
         (var5 = new Vector()).addElement(var6);
      } else {
         byte var7 = 0;
         byte var8 = 0;
         byte var9 = 0;
         byte var10 = 0;
         if (var4 > 0) {
            var7 = a.b[var3][var4 - 1];
         }

         if (var4 < a.g - 1) {
            var8 = a.b[var3][var4 + 1];
         }

         if (var3 > 0) {
            var9 = a.b[var3 - 1][var4];
         }

         if (var3 < a.f - 1) {
            var10 = a.b[var3 + 1][var4];
         }

         int var11;
         if ((var11 = Math.max(Math.max(var7, var8), Math.max(var9, var10))) == var7) {
            var5 = this.a(var1, var2, var3, var4 - 1);
         } else if (var11 == var8) {
            var5 = this.a(var1, var2, var3, var4 + 1);
         } else if (var11 == var9) {
            var5 = this.a(var1, var2, var3 - 1, var4);
         } else if (var11 == var10) {
            var5 = this.a(var1, var2, var3 + 1, var4);
         }

         var5.addElement(var6);
      }

      return var5;
   }

   public final void b(byte[][] var1) {
      a(var1, this.b, this.c, this.g, -1, this.d, this.e, false);
   }

   public static final boolean a(byte[][] var0, int var1, int var2, int var3, int var4, byte var5, byte var6, boolean var7) {
      if (var3 > var0[var1][var2]) {
         var0[var1][var2] = (byte)var3;
         if (var7 && a.a((int)var1, var2, (byte)0) == null) {
            return true;
         } else {
            int var8;
            if (var4 != 1 && (var8 = var3 - a(var1, var2 - 1, var5, var6)) >= 0 && a(var0, var1, var2 - 1, var8, 2, var5, var6, var7) && var7) {
               return true;
            } else if (var4 != 2 && (var8 = var3 - a(var1, var2 + 1, var5, var6)) >= 0 && a(var0, var1, var2 + 1, var8, 1, var5, var6, var7) && var7) {
               return true;
            } else if (var4 != 4 && (var8 = var3 - a(var1 - 1, var2, var5, var6)) >= 0 && a(var0, var1 - 1, var2, var8, 8, var5, var6, var7) && var7) {
               return true;
            } else {
               return var4 != 8 && (var8 = var3 - a(var1 + 1, var2, var5, var6)) >= 0 && a(var0, var1 + 1, var2, var8, 4, var5, var6, var7) && var7;
            }
         }
      } else {
         return false;
      }
   }

   private static int a(int var0, int var1, byte var2, byte var3) {
      if (var0 >= 0 && var1 >= 0 && var0 < a.f && var1 < a.g) {
         a var4;
         if ((var4 = a.a((int)var0, var1, (byte)0)) != null && a.e[var4.e] != a.e[var3]) {
            return 1000;
         } else {
            var0 = a.a(var0, var1);
            if (var2 == 11) {
               if (var0 == 4) {
                  return 1000;
               }
            } else {
               if (a((byte)var2, (short)1)) {
                  return 1;
               }

               if (a((byte)var2, (short)2) && var0 == 5) {
                  return 1;
               }
            }

            return aeii.g.b[var0];
         }
      } else {
         return 10000;
      }
   }

   public final void c() {
      if (this.f) {
         if (a.a - this.b >= (long)this.B) {
            this.f = false;
         } else {
            this.g = !this.g;
         }
      }

      if (this.f == 1) {
         if (this.d >= this.a.size()) {
            this.f = 0;
            this.b = (short)(super.m / 24);
            this.c = (short)(super.n / 24);
            this.a = null;
            this.d = 0;
         } else {
            if (this.a != null && super.m % 24 == 0 && super.n % 24 == 0) {
               this.a.a(this.b, this.c, false);
            }

            short[] var1;
            int var2 = (var1 = (short[])this.a.elementAt(this.d))[0] * 24;
            int var3 = var1[1] * 24;
            a.a.g var4 = null;
            if (this.a == null && ++this.C >= 24 / c / 2) {
               var4 = a.a(a.o, super.m, super.n, 0, 0, 1, aeii.c.a(1, 4) * 50);
               this.C = 0;
            }

            if (var2 < super.m) {
               super.m -= c;
               if (var4 != null) {
                  var4.b(super.m + super.o, super.n + super.p - var4.p);
               }
            } else if (var2 > super.m) {
               super.m += c;
               if (var4 != null) {
                  var4.b(super.m - var4.o, super.n + super.p - var4.p);
               }
            } else if (var3 < super.n) {
               super.n -= c;
               if (var4 != null) {
                  var4.b(super.m + (super.o - var4.o) / 2, super.n + super.p);
               }
            } else if (var3 > super.n) {
               super.n += c;
               if (var4 != null) {
                  var4.b(super.m + (super.o - var4.o) / 2, super.n - var4.p);
               }
            }

            if (super.m == var2 && super.n == var3) {
               this.b = var1[0];
               this.c = var1[1];
               ++this.d;
            }
         }

         super.b(super.m, super.n);
         this.e();
      } else {
         if (this.f == 0 && a.a - this.a >= 200L) {
            this.e();
            this.a = a.a;
         }

      }
   }

   private static boolean a(byte var0, short var1) {
      return (b[var0] & var1) != 0;
   }

   public final boolean a(short var1) {
      return a(this.d, var1);
   }

   public final void d() {
      this.f = 2;
      a var1;
      if ((var1 = a.a((int)this.b, this.c, (byte)1)) != null) {
         ((a)var1).a();
      }

      if (this.a((short)256)) {
         var1 = this.a(this.b, this.c, 1, 2, (byte)2);

         for(int var2 = 0; var2 < var1.length; ++var2) {
            var1[var2].b((byte)2);
            a.a(a.f, var1[var2].m, var1[var2].n, 0, 0, 1, 50);
         }
      }

      a.a = this;
   }

   public static final a[] a(byte var0) {
      a[] var1 = new a[a.a[var0]];
      int var2 = 0;

      for(int var3 = 0; var3 < var1.length; ++var3) {
         if (a.a[a.b][var3] != null && a.a[a.b][var3].f == 3) {
            var1[var2++] = a.a[a.b][var3];
         }
      }

      a[] var5 = new a[a.n + 1 + var2];

      for(byte var4 = 0; var4 < var5.length; ++var4) {
         if (var4 < var2) {
            var5[var4] = var1[var4];
         } else {
            var5[var4] = a((byte)(var4 - var2), var0, 0, 0, false);
         }
      }

      return var5;
   }

   public final void a(Graphics var1, int var2, int var3) {
      this.a(var1, var2, var3, false);
   }

   public final void a(Graphics var1, int var2, int var3, boolean var4) {
      if (this.f != 4) {
         if (this.f) {
            byte var6;
            if (this.g) {
               var6 = -2;
            } else {
               var6 = 2;
            }

            aeii.c.a();
            byte var5 = 0;
            super.a(var1, var2 + var6, var3 + var5);
         } else if (!var4 && this.f != 2) {
            super.a(var1, var2, var3);
         } else {
            a.a[(this.m - 1 << 1) + 1][this.d].a(var1, super.m + var2, super.n + var3);
         }

         if (this.d == 9) {
            int var8 = super.m + var2;
            int var7 = super.n + var3;
            if (var4 || this.f == 2) {
               a.a[1].a(var1, (this.j << 1) + super.l, var8, var7, 0);
               return;
            }

            a.a[0].a(var1, (this.j << 1) + super.l, var8, var7, 0);
         }
      }

   }

   public final void b(Graphics var1, int var2, int var3) {
      var2 = super.m + var2;
      var3 = super.n + var3;
      if (this.f != 3 && this.h < 100) {
         aeii.c.a(var1, "" + this.h, var2, var3 + super.p - 7, 0);
      }

   }

   public static final void a(g var0) {
      a = var0;

      try {
         for(int var7 = 0; var7 < a.length; ++var7) {
            InputStream var8 = aeii.c.a(a[var7] + ".unit");

            String var11;
            while((var11 = aeii.c.a(var8)) != null) {
               int var14;
               if ((var14 = var11.indexOf(59)) >= 0) {
                  var11 = var11.substring(0, var14);
               }

               if ((var11 = var11.trim()).length() != 0) {
                  String[] var9;
                  if ((var9 = aeii.c.a(var11, ' '))[0].equalsIgnoreCase("MoveRange")) {
                     a[var7] = (byte)Integer.parseInt(var9[1]);
                  } else if (var9[0].equalsIgnoreCase("Attack")) {
                     c[var7][0] = (byte)Integer.parseInt(var9[1]);
                     c[var7][1] = (byte)Integer.parseInt(var9[2]);
                  } else if (var9[0].equalsIgnoreCase("Defence")) {
                     c[var7] = (byte)Integer.parseInt(var9[1]);
                  } else if (var9[0].equalsIgnoreCase("AttackRange")) {
                     d[var7] = (byte)Integer.parseInt(var9[1]);
                     e[var7] = (byte)Integer.parseInt(var9[2]);
                  } else if (var9[0].equalsIgnoreCase("Cost")) {
                     a[var7] = (short)Integer.parseInt(var9[1]);
                  } else if (var9[0].equalsIgnoreCase("CharCount")) {
                     a[var7] = new byte[Integer.parseInt(var9[1])][2];
                  } else if (var9[0].equalsIgnoreCase("CharPos")) {
                     var14 = Integer.parseInt(var9[1]);
                     a[var7][var14][0] = (byte)Integer.parseInt(var9[2]);
                     a[var7][var14][1] = (byte)Integer.parseInt(var9[3]);
                  } else if (var9[0].equalsIgnoreCase("HasProperty")) {
                     b[var7] = (short)(b[var7] | 1 << Integer.parseInt(var9[1]));
                  }
               }
            }
         }

      } catch (Exception var6) {
         try {
            DataInputStream var1 = new DataInputStream(aeii.c.a("units.bin"));

            for(int var2 = 0; var2 < 12; ++var2) {
               a[var2] = var1.readByte();
               c[var2][0] = var1.readByte();
               c[var2][1] = var1.readByte();
               c[var2] = var1.readByte();
               d[var2] = var1.readByte();
               e[var2] = var1.readByte();
               a[var2] = var1.readShort();
               byte var3 = var1.readByte();
               a[var2] = new byte[var3][2];

               for(int var4 = 0; var4 < var3; ++var4) {
                  a[var2][var4][0] = var1.readByte();
                  a[var2][var4][1] = var1.readByte();
               }

               byte var13 = var1.readByte();

               for(int var10 = 0; var10 < var13; ++var10) {
                  b[var2] = (short)(b[var2] | 1 << var1.readByte());
               }
            }

            var1.close();
         } catch (Exception var5) {
            var6.printStackTrace();
            var5.printStackTrace();
         }
      }
   }
}
