/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
package aeii;

import aeii.c;
import aeii.f;
import aeii.g;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class a
extends a.a.a.g {
    public static byte var_byte_a;
    public static byte var_byte_b;
    public static byte var_byte_c;
    private static g var_aeii_g_a;
    public String var_java_lang_String_a;
    public short var_short_a;
    public int var_int_a;
    public byte[][] var_byte_arr_arr_a;
    private Vector var_java_util_Vector_a;
    private short var_short_d;
    private long var_long_a;
    public byte var_byte_d;
    public byte var_byte_e;
    public short var_short_b;
    public short var_short_c;
    public int var_int_b;
    public int var_int_c;
    public int var_int_d;
    public int var_int_e;
    public int var_int_f;
    public int var_int_g;
    public int var_int_h;
    public byte var_byte_f;
    public byte var_byte_g;
    private short var_short_e;
    private short var_short_f;
    private short var_short_g;
    private boolean var_boolean_f;
    private boolean var_boolean_g = true;
    private int B;
    private long var_long_b;
    public byte var_byte_h;
    public byte var_byte_i;
    private int C;
    public a var_aeii_a_a;
    public byte var_byte_j = 0;
    public int var_int_i;
    public int var_int_j;
    public int k;
    private byte m;
    private static String[] var_java_lang_String_arr_a;
    public static byte[] var_byte_arr_a;
    private static byte[][] var_byte_arr_arr_c;
    private static byte[] var_byte_arr_c;
    private static byte[] var_byte_arr_d;
    private static byte[] var_byte_arr_e;
    public static byte[][][] var_byte_arr_arr_arr_a;
    public static short[] var_short_arr_a;
    private static short[] var_short_arr_b;

    private a(byte by, byte by2, int n, int n2, boolean bl) {
        super(aeii.a.var_aeii_g_a.var_a_a_a_g_arr_arr_a[by2 - 1 << 1][by]);
        this.m = by2;
        this.var_byte_d = by;
        this.var_byte_f = 0;
        this.var_short_b = (short)n;
        this.var_short_c = (short)n2;
        this.b(n * 24, n2 * 24);
        this.void_a((byte)0);
        if (bl) {
            aeii.a.var_aeii_g_a.var_java_util_Vector_a.addElement(this);
        }
    }

    public final void void_a(byte by) {
        this.var_short_a = by;
        by = (byte)(by << 1);
        this.var_int_d = var_byte_arr_arr_c[this.var_byte_d][0] + by;
        this.var_int_e = var_byte_arr_arr_c[this.var_byte_d][1] + by;
        this.var_int_f = var_byte_arr_c[this.var_byte_d] + by;
        this.var_int_g = var_byte_arr_a[this.var_byte_d];
        if (this.var_byte_d != 9) {
            int n = this.var_short_a / 2;
            by = (byte)n;
            if (n > 3) {
                by = (byte)3;
            }
            this.var_java_lang_String_a = aeii.f.java_lang_String_a(139 + (this.var_byte_d << 2) + by);
        }
    }

    public final void a(int n) {
        this.var_boolean_f = true;
        this.var_long_b = aeii.a.var_aeii_g_a.var_long_a;
        this.B = n;
    }

    public static final a a(byte by, byte by2, int n, int n2) {
        return aeii.a.a(by, by2, n, n2, true);
    }

    public static final a a(byte by, byte by2, int n, int n2, boolean bl) {
        if (by == 9 && aeii.a.var_aeii_g_a.var_int_arr_a[by2] >= aeii.a.var_aeii_g_a.var_aeii_a_arr_arr_a[by2].length) {
            return null;
        }
        a a2 = new a(by, aeii.a.var_aeii_g_a.var_byte_arr_d[by2], n, n2, bl);
        new a(by, aeii.a.var_aeii_g_a.var_byte_arr_d[by2], n, n2, bl).var_byte_d = by;
        a2.var_byte_e = by2;
        a2.var_int_h = 100;
        a2.var_byte_arr_arr_a = var_byte_arr_arr_arr_a[by];
        a2.var_int_j = var_short_arr_a[by];
        if (by == 9) {
            a2.b(aeii.a.var_aeii_g_a.var_byte_arr_d[by2] - 1);
            a2.var_int_i = aeii.a.var_aeii_g_a.var_int_arr_a[by2];
            aeii.a.var_aeii_g_a.var_aeii_a_arr_arr_a[by2][a2.var_int_i] = a2;
            byte by3 = by2;
            aeii.a.var_aeii_g_a.var_int_arr_a[by3] = aeii.a.var_aeii_g_a.var_int_arr_a[by3] + 1;
        }
        return a2;
    }

    public static final a b(byte by, byte by2, int n, int n2, boolean bl) {
        a a2 = new a(by, aeii.a.var_aeii_g_a.var_byte_arr_d[by2], n, n2, bl);
        new a(by, aeii.a.var_aeii_g_a.var_byte_arr_d[by2], n, n2, bl).var_byte_d = by;
        a2.var_byte_e = by2;
        a2.var_int_h = 100;
        a2.var_byte_arr_arr_a = var_byte_arr_arr_arr_a[by];
        a2.var_int_j = var_short_arr_a[by];
        if (by == 9) {
            a2.b(aeii.a.var_aeii_g_a.var_byte_arr_d[by2] - 1);
        }
        return a2;
    }

    public final void void_a() {
        aeii.a.var_aeii_g_a.var_java_util_Vector_a.removeElement(this);
    }

    public final void b(int n) {
        this.var_byte_j = (byte)n;
        this.var_java_lang_String_a = aeii.f.java_lang_String_a(n + 93);
    }

    public final int a(a a2) {
        return this.a(a2, (int)this.var_short_b, (int)this.var_short_c);
    }

    private int a(a a2, int n, int n2) {
        int n3 = this.var_short_f;
        if (a2 != null) {
            if (this.a((short)64) && a2.a((short)1)) {
                n3 += 15;
            }
            if (this.var_byte_d == 4 && a2.var_byte_d == 10) {
                n3 += 15;
            }
        }
        if (this.a((short)2) && var_aeii_g_a.byte_a(n, n2) == 5) {
            n3 += 10;
        }
        if (aeii.a.var_aeii_g_a.var_byte_arr_arr_a[n][n2] == 34) {
            n3 += 25;
        }
        return n3;
    }

    public final int b(a a2) {
        return this.int_a((int)this.var_short_b, this.var_short_c);
    }

    private int int_a(int n, int n2) {
        byte by = var_aeii_g_a.byte_a(n, n2);
        int n3 = this.var_short_g + aeii.g.var_byte_arr_a[by];
        if (this.a((short)2) && by == 5) {
            n3 += 15;
        }
        if (aeii.a.var_aeii_g_a.var_byte_arr_arr_a[n][n2] == 34) {
            n3 += 15;
        }
        return n3;
    }

    public final int c(a a2) {
        int n = aeii.c.int_a(this.var_int_d, this.var_int_e) + this.a(a2);
        int n2 = a2.var_int_f + a2.b(this);
        if ((n = (n - n2) * this.var_int_h / 100) < 0) {
            n = 0;
        } else if (n > a2.var_int_h) {
            n = a2.var_int_h;
        }
        a2.var_int_h -= n;
        this.var_int_a += a2.int_e() * n;
        return n;
    }

    private int int_e() {
        return this.var_int_d + this.var_int_e + this.var_int_f;
    }

    public final int int_a() {
        return (this.int_e() * 100 << 1) / 3;
    }

    public final boolean boolean_a() {
        int n = this.int_a();
        if (this.var_int_a >= n) {
            this.var_int_a -= n;
            this.void_a((byte)(this.var_short_a + 1));
            return true;
        }
        return false;
    }

    public final boolean boolean_a(int n, int n2) {
        return this.var_byte_f != 4 && this.var_int_h > 0 && Math.abs(this.var_short_b - n) + Math.abs(this.var_short_c - n2) == 1 && var_byte_arr_e[this.var_byte_d] == 1;
    }

    public final void b(byte by) {
        this.var_byte_g = (byte)(this.var_byte_g | by);
        this.void_b();
        if (by == 1) {
            this.var_byte_i = aeii.a.var_aeii_g_a.var_byte_b;
        }
    }

    public final void c(byte by) {
        this.var_byte_g = (byte)(this.var_byte_g & ~by);
        this.void_b();
    }

    public final void void_b() {
        this.var_short_e = 0;
        this.var_short_f = 0;
        this.var_short_g = 0;
        if ((this.var_byte_g & 1) != 0) {
            this.var_short_f = (short)(this.var_short_f - 10);
            this.var_short_g = (short)(this.var_short_g - 10);
        }
        if ((this.var_byte_g & 2) != 0) {
            this.var_short_f = (short)(this.var_short_f + 10);
        }
    }

    public final void void_a(int n, int n2) {
        this.var_short_b = (short)n;
        this.var_short_c = (short)n2;
        ((a.a.a.g)this).m = (short)(n * 24);
        this.n = (short)(n2 * 24);
    }

    public final int int_b() {
        int n = 100 / this.var_byte_arr_arr_a.length;
        int n2 = this.var_int_h / n;
        if (this.var_int_h != 100 && this.var_int_h % n > 0) {
            ++n2;
        }
        return n2;
    }

    public final int a(int n, int n2, a a2) {
        return (this.var_int_d + this.var_int_e + this.var_int_f + this.a(a2, n, n2) + this.int_a(n, n2)) * this.var_int_h / 100;
    }

    public final void a(byte[][] byArray, int n, int n2) {
        int n3;
        int n4;
        int n5;
        byte by = var_byte_arr_e[this.var_byte_d];
        byte by2 = var_byte_arr_d[this.var_byte_d];
        int n6 = n - by2;
        if (n6 < 0) {
            n6 = 0;
        }
        if ((n5 = n2 - by2) < 0) {
            n5 = 0;
        }
        if ((n4 = n + by2) >= aeii.a.var_aeii_g_a.var_int_f) {
            n4 = aeii.a.var_aeii_g_a.var_int_f - 1;
        }
        if ((n3 = n2 + by2) >= aeii.a.var_aeii_g_a.var_int_g) {
            n3 = aeii.a.var_aeii_g_a.var_int_g - 1;
        }
        while (n6 <= n4) {
            for (int i = n5; i <= n3; ++i) {
                int n7 = Math.abs(n6 - n) + Math.abs(i - n2);
                if (n7 < by || n7 > by2 || byArray[n6][i] > 0) continue;
                byArray[n6][i] = 127;
            }
            ++n6;
        }
    }

    public final void a(byte[][] byArray) {
        if (this.a((short)512)) {
            this.a(byArray, (int)this.var_short_b, (int)this.var_short_c);
            return;
        }
        this.b(byArray);
        for (int i = 0; i < aeii.a.var_aeii_g_a.var_int_f; ++i) {
            for (int j = 0; j < aeii.a.var_aeii_g_a.var_int_g; ++j) {
                if (byArray[i][j] <= 0 || byArray[i][j] == 127) continue;
                this.a(byArray, i, j);
            }
        }
    }

    public final a[] a(int n, int n2, byte by) {
        return this.a(n, n2, (int)var_byte_arr_e[this.var_byte_d], (int)var_byte_arr_d[this.var_byte_d], by);
    }

    public final a[] a(int n, int n2, int n3, int n4, byte by) {
        int n5;
        int n6;
        int n7;
        Vector<a> vector = new Vector<a>();
        int n8 = n - n4;
        if (n8 < 0) {
            n8 = 0;
        }
        if ((n7 = n2 - n4) < 0) {
            n7 = 0;
        }
        if ((n6 = n + n4) >= aeii.a.var_aeii_g_a.var_int_f) {
            n6 = aeii.a.var_aeii_g_a.var_int_f - 1;
        }
        if ((n5 = n2 + n4) >= aeii.a.var_aeii_g_a.var_int_g) {
            n5 = aeii.a.var_aeii_g_a.var_int_g - 1;
        }
        while (n8 <= n6) {
            for (int i = n7; i <= n5; ++i) {
                a a2;
                int n9 = Math.abs(n8 - n) + Math.abs(i - n2);
                if (n9 < n3 || n9 > n4) continue;
                if (by == 0) {
                    a a3 = var_aeii_g_a.aeii_a_a(n8, i, (byte)0);
                    if (a3 != null) {
                        if (aeii.a.var_aeii_g_a.var_byte_arr_e[a3.var_byte_e] == aeii.a.var_aeii_g_a.var_byte_arr_e[this.var_byte_e]) continue;
                        vector.addElement(a3);
                        continue;
                    }
                    if (this.var_byte_d != 7 || var_aeii_g_a.byte_a(n8, i) != 8 || !aeii.g.a(aeii.a.var_aeii_g_a.var_byte_arr_arr_a[n8][i]) || var_aeii_g_a.boolean_a(n8, i, (int)aeii.a.var_aeii_g_a.var_byte_arr_e[this.var_byte_e])) continue;
                    a3 = aeii.a.a((byte)0, (byte)0, n8, i, false);
                    aeii.a.a((byte)0, (byte)0, n8, i, false).var_byte_d = (byte)-1;
                    a3.var_byte_f = (byte)4;
                    vector.addElement(a3);
                    continue;
                }
                if (by == 1) {
                    a a4 = var_aeii_g_a.aeii_a_a(n8, i, (byte)1);
                    if (a4 == null) continue;
                    vector.addElement(a4);
                    continue;
                }
                if (by != 2 || (a2 = var_aeii_g_a.aeii_a_a(n8, i, (byte)0)) == null || aeii.a.var_aeii_g_a.var_byte_arr_e[a2.var_byte_e] != aeii.a.var_aeii_g_a.var_byte_arr_e[this.var_byte_e]) continue;
                vector.addElement(a2);
            }
            ++n8;
        }
        Object[] objectArray = new a[vector.size()];
        vector.copyInto(objectArray);
        return objectArray;
    }

    public final void a(int n, int n2, boolean bl) {
        this.a(n, n2, bl, false);
    }

    public final void a(int n, int n2, boolean n3, boolean bl) {
        if (n3 != 0) {
            this.var_java_util_Vector_a = this.a((int)this.var_short_b, (int)this.var_short_c, n, n2);
        } else {
            short s;
            short s2;
            if (bl && var_aeii_g_a.aeii_a_a(n, n2, (byte)0) != null) {
                bl = false;
                for (s2 = n - 1; s2 <= n + 1; ++s2) {
                    for (n3 = n2 - 1; n3 <= n2 + 1; ++n3) {
                        if ((s2 == n && n3 == n2 || s2 != n) && n3 != n2 || var_aeii_g_a.aeii_a_a((int)s2, n3, (byte)0) != null) continue;
                        n = s2;
                        n2 = n3;
                        bl = true;
                        break;
                    }
                    if (bl) break;
                }
            }
            this.var_java_util_Vector_a = new Vector();
            short[] sArray = new short[]{this.var_short_b, this.var_short_c};
            this.var_java_util_Vector_a.addElement(sArray);
            s2 = this.var_short_b;
            n3 = Math.abs(n - this.var_short_b);
            if (n3 > 0) {
                s = (n - this.var_short_b) / n3;
                for (int i = 0; i < n3; ++i) {
                    s2 = (short)(s2 + s);
                    short[] sArray2 = new short[]{s2, this.var_short_c};
                    this.var_java_util_Vector_a.addElement(sArray2);
                }
            }
            s = this.var_short_c;
            n3 = Math.abs(n2 - this.var_short_c);
            if (n3 > 0) {
                int n4 = (n2 - this.var_short_c) / n3;
                for (int i = 0; i < n3; ++i) {
                    s = (short)(s + n4);
                    short[] sArray3 = new short[]{s2, s};
                    this.var_java_util_Vector_a.addElement(sArray3);
                }
            }
        }
        this.var_int_b = n;
        this.var_int_c = n2;
        this.var_short_d = 1;
        this.var_byte_f = 1;
    }

    public final Vector a(int n, int n2, int n3, int n4) {
        Vector vector = null;
        short[] sArray = new short[]{(short)n3, (short)n4};
        if (n == n3 && n2 == n4) {
            vector = new Vector();
            vector.addElement(sArray);
        } else {
            int n5;
            byte by = 0;
            byte by2 = 0;
            byte by3 = 0;
            int n6 = 0;
            if (n4 > 0) {
                by = aeii.a.var_aeii_g_a.var_byte_arr_arr_b[n3][n4 - 1];
            }
            if (n4 < aeii.a.var_aeii_g_a.var_int_g - 1) {
                by2 = aeii.a.var_aeii_g_a.var_byte_arr_arr_b[n3][n4 + 1];
            }
            if (n3 > 0) {
                by3 = aeii.a.var_aeii_g_a.var_byte_arr_arr_b[n3 - 1][n4];
            }
            if (n3 < aeii.a.var_aeii_g_a.var_int_f - 1) {
                n6 = aeii.a.var_aeii_g_a.var_byte_arr_arr_b[n3 + 1][n4];
            }
            if ((n5 = Math.max(Math.max(by, by2), Math.max(by3, n6))) == by) {
                vector = this.a(n, n2, n3, n4 - 1);
            } else if (n5 == by2) {
                vector = this.a(n, n2, n3, n4 + 1);
            } else if (n5 == by3) {
                vector = this.a(n, n2, n3 - 1, n4);
            } else if (n5 == n6) {
                vector = this.a(n, n2, n3 + 1, n4);
            }
            vector.addElement(sArray);
        }
        return vector;
    }

    public final void b(byte[][] byArray) {
        aeii.a.a(byArray, this.var_short_b, this.var_short_c, this.var_int_g, -1, this.var_byte_d, this.var_byte_e, false);
    }

    public static final boolean a(byte[][] byArray, int n, int n2, int n3, int n4, byte by, byte by2, boolean bl) {
        if (n3 > byArray[n][n2]) {
            int n5;
            byArray[n][n2] = (byte)n3;
            if (bl && var_aeii_g_a.aeii_a_a(n, n2, (byte)0) == null) {
                return true;
            }
            if (n4 != 1 && (n5 = n3 - aeii.a.a(n, n2 - 1, by, by2)) >= 0 && aeii.a.a(byArray, n, n2 - 1, n5, 2, by, by2, bl) && bl) {
                return true;
            }
            if (n4 != 2 && (n5 = n3 - aeii.a.a(n, n2 + 1, by, by2)) >= 0 && aeii.a.a(byArray, n, n2 + 1, n5, 1, by, by2, bl) && bl) {
                return true;
            }
            if (n4 != 4 && (n5 = n3 - aeii.a.a(n - 1, n2, by, by2)) >= 0 && aeii.a.a(byArray, n - 1, n2, n5, 8, by, by2, bl) && bl) {
                return true;
            }
            return n4 != 8 && (n5 = n3 - aeii.a.a(n + 1, n2, by, by2)) >= 0 && aeii.a.a(byArray, n + 1, n2, n5, 4, by, by2, bl) && bl;
        }
        return false;
    }

    private static int a(int n, int n2, byte by, byte by2) {
        if (n >= 0 && n2 >= 0 && n < aeii.a.var_aeii_g_a.var_int_f && n2 < aeii.a.var_aeii_g_a.var_int_g) {
            a a2 = var_aeii_g_a.aeii_a_a(n, n2, (byte)0);
            if (a2 != null && aeii.a.var_aeii_g_a.var_byte_arr_e[a2.var_byte_e] != aeii.a.var_aeii_g_a.var_byte_arr_e[by2]) {
                return 1000;
            }
            n = var_aeii_g_a.byte_a(n, n2);
            if (by == 11) {
                if (n == 4) {
                    return 1000;
                }
            } else {
                if (aeii.a.a(by, (short)1)) {
                    return 1;
                }
                if (aeii.a.a(by, (short)2) && n == 5) {
                    return 1;
                }
            }
            return aeii.g.var_byte_arr_b[n];
        }
        return 10000;
    }

    public final void void_c() {
        if (this.var_boolean_f) {
            if (aeii.a.var_aeii_g_a.var_long_a - this.var_long_b >= (long)this.B) {
                this.var_boolean_f = false;
            } else {
                boolean bl = this.var_boolean_g = !this.var_boolean_g;
            }
        }
        if (this.var_byte_f == 1) {
            if (this.var_short_d >= this.var_java_util_Vector_a.size()) {
                this.var_byte_f = 0;
                this.var_short_b = (short)(((a.a.a.g)this).m / 24);
                this.var_short_c = (short)(this.n / 24);
                this.var_java_util_Vector_a = null;
                this.var_short_d = 0;
            } else {
                if (this.var_aeii_a_a != null && ((a.a.a.g)this).m % 24 == 0 && this.n % 24 == 0) {
                    this.var_aeii_a_a.a((int)this.var_short_b, (int)this.var_short_c, false);
                }
                short[] sArray = (short[])this.var_java_util_Vector_a.elementAt(this.var_short_d);
                int n = sArray[0] * 24;
                int n2 = sArray[1] * 24;
                a.a.a.g g2 = null;
                if (this.var_aeii_a_a == null && ++this.C >= 24 / var_byte_c / 2) {
                    g2 = var_aeii_g_a.a(aeii.a.var_aeii_g_a.var_a_a_a_g_o, ((a.a.a.g)this).m, this.n, 0, 0, 1, aeii.c.int_a(1, 4) * 50);
                    this.C = 0;
                }
                if (n < ((a.a.a.g)this).m) {
                    ((a.a.a.g)this).m -= var_byte_c;
                    if (g2 != null) {
                        g2.b(((a.a.a.g)this).m + this.o, this.n + this.p - g2.p);
                    }
                } else if (n > ((a.a.a.g)this).m) {
                    ((a.a.a.g)this).m += var_byte_c;
                    if (g2 != null) {
                        g2.b(((a.a.a.g)this).m - g2.o, this.n + this.p - g2.p);
                    }
                } else if (n2 < this.n) {
                    this.n -= var_byte_c;
                    if (g2 != null) {
                        g2.b(((a.a.a.g)this).m + (this.o - g2.o) / 2, this.n + this.p);
                    }
                } else if (n2 > this.n) {
                    this.n += var_byte_c;
                    if (g2 != null) {
                        g2.b(((a.a.a.g)this).m + (this.o - g2.o) / 2, this.n - g2.p);
                    }
                }
                if (((a.a.a.g)this).m == n && this.n == n2) {
                    this.var_short_b = sArray[0];
                    this.var_short_c = sArray[1];
                    this.var_short_d = (short)(this.var_short_d + 1);
                }
            }
            super.b(((a.a.a.g)this).m, this.n);
            this.void_e();
            return;
        }
        if (this.var_byte_f == 0 && aeii.a.var_aeii_g_a.var_long_a - this.var_long_a >= 200L) {
            this.void_e();
            this.var_long_a = aeii.a.var_aeii_g_a.var_long_a;
        }
    }

    private static boolean a(byte by, short s) {
        return (var_short_arr_b[by] & s) != 0;
    }

    public final boolean a(short s) {
        return aeii.a.a(this.var_byte_d, s);
    }

    public final void void_d() {
        this.var_byte_f = (byte)2;
        a[] aArray = var_aeii_g_a.aeii_a_a((int)this.var_short_b, (int)this.var_short_c, (byte)1);
        if (aArray != null) {
            aArray.void_a();
        }
        if (this.a((short)256)) {
            aArray = this.a(this.var_short_b, (int)this.var_short_c, 1, 2, (byte)2);
            for (int i = 0; i < aArray.length; ++i) {
                aArray[i].b((byte)2);
                var_aeii_g_a.a(aeii.a.var_aeii_g_a.var_a_a_a_g_f, aArray[i].m, aArray[i].n, 0, 0, 1, 50);
            }
        }
        aeii.a.var_aeii_g_a.var_aeii_a_a = this;
    }

    public static final a[] aeii_a_arr_a(byte by) {
        a[] aArray = new a[aeii.a.var_aeii_g_a.var_int_arr_a[by]];
        int n = 0;
        for (int i = 0; i < aArray.length; ++i) {
            if (aeii.a.var_aeii_g_a.var_aeii_a_arr_arr_a[aeii.a.var_aeii_g_a.var_byte_b][i] == null || aeii.a.var_aeii_g_a.var_aeii_a_arr_arr_a[aeii.a.var_aeii_g_a.var_byte_b][i].var_byte_f != 3) continue;
            aArray[n++] = aeii.a.var_aeii_g_a.var_aeii_a_arr_arr_a[aeii.a.var_aeii_g_a.var_byte_b][i];
        }
        a[] aArray2 = new a[aeii.a.var_aeii_g_a.var_int_n + 1 + n];
        for (int n2 = 0; n2 < aArray2.length; n2 = (int)((byte)(n2 + 1))) {
            aArray2[n2] = n2 < n ? aArray[n2] : aeii.a.a((byte)(n2 - n), by, 0, 0, false);
        }
        return aArray2;
    }

    public final void a(Graphics graphics, int n, int n2) {
        this.a(graphics, n, n2, false);
    }

    public final void a(Graphics graphics, int n, int n2, boolean bl) {
        if (this.var_byte_f != 4) {
            int n3;
            int n4;
            if (this.var_boolean_f) {
                n4 = this.var_boolean_g ? -2 : 2;
                aeii.c.int_a();
                n3 = 0;
                super.a(graphics, n + n4, n2 + n3);
            } else if (!bl && this.var_byte_f != 2) {
                super.a(graphics, n, n2);
            } else {
                aeii.a.var_aeii_g_a.var_a_a_a_g_arr_arr_a[(this.m - 1 << 1) + 1][this.var_byte_d].a(graphics, ((a.a.a.g)this).m + n, this.n + n2);
            }
            if (this.var_byte_d == 9) {
                n4 = ((a.a.a.g)this).m + n;
                n3 = this.n + n2;
                if (bl || this.var_byte_f == 2) {
                    aeii.a.var_aeii_g_a.var_a_a_a_g_arr_a[1].a(graphics, (this.var_byte_j << 1) + this.var_int_l, n4, n3, 0);
                    return;
                }
                aeii.a.var_aeii_g_a.var_a_a_a_g_arr_a[0].a(graphics, (this.var_byte_j << 1) + this.var_int_l, n4, n3, 0);
            }
        }
    }

    public final void b(Graphics graphics, int n, int n2) {
        n = ((a.a.a.g)this).m + n;
        n2 = this.n + n2;
        if (this.var_byte_f != 3 && this.var_int_h < 100) {
            aeii.c.a(graphics, "" + this.var_int_h, n, n2 + this.p - 7, 0);
        }
    }

    public static final void a(g g2) {
        var_aeii_g_a = g2;
        try {
            for (int i = 0; i < var_java_lang_String_arr_a.length; ++i) {
                String string;
                InputStream inputStream = aeii.c.java_io_InputStream_a(var_java_lang_String_arr_a[i] + ".unit");
                while ((string = aeii.c.a(inputStream)) != null) {
                    int n = string.indexOf(59);
                    if (n >= 0) {
                        string = string.substring(0, n);
                    }
                    if ((string = string.trim()).length() == 0) continue;
                    String[] stringArray = aeii.c.a(string, ' ');
                    if (stringArray[0].equalsIgnoreCase("MoveRange")) {
                        aeii.a.var_byte_arr_a[i] = (byte)Integer.parseInt(stringArray[1]);
                        continue;
                    }
                    if (stringArray[0].equalsIgnoreCase("Attack")) {
                        aeii.a.var_byte_arr_arr_c[i][0] = (byte)Integer.parseInt(stringArray[1]);
                        aeii.a.var_byte_arr_arr_c[i][1] = (byte)Integer.parseInt(stringArray[2]);
                        continue;
                    }
                    if (stringArray[0].equalsIgnoreCase("Defence")) {
                        aeii.a.var_byte_arr_c[i] = (byte)Integer.parseInt(stringArray[1]);
                        continue;
                    }
                    if (stringArray[0].equalsIgnoreCase("AttackRange")) {
                        aeii.a.var_byte_arr_d[i] = (byte)Integer.parseInt(stringArray[1]);
                        aeii.a.var_byte_arr_e[i] = (byte)Integer.parseInt(stringArray[2]);
                        continue;
                    }
                    if (stringArray[0].equalsIgnoreCase("Cost")) {
                        aeii.a.var_short_arr_a[i] = (short)Integer.parseInt(stringArray[1]);
                        continue;
                    }
                    if (stringArray[0].equalsIgnoreCase("CharCount")) {
                        aeii.a.var_byte_arr_arr_arr_a[i] = new byte[Integer.parseInt(stringArray[1])][2];
                        continue;
                    }
                    if (stringArray[0].equalsIgnoreCase("CharPos")) {
                        n = Integer.parseInt(stringArray[1]);
                        aeii.a.var_byte_arr_arr_arr_a[i][n][0] = (byte)Integer.parseInt(stringArray[2]);
                        aeii.a.var_byte_arr_arr_arr_a[i][n][1] = (byte)Integer.parseInt(stringArray[3]);
                        continue;
                    }
                    if (!stringArray[0].equalsIgnoreCase("HasProperty")) continue;
                    aeii.a.var_short_arr_b[i] = (short)(var_short_arr_b[i] | 1 << Integer.parseInt(stringArray[1]));
                }
            }
            return;
        }
        catch (Exception exception) {
            try {
                DataInputStream dataInputStream = new DataInputStream(aeii.c.java_io_InputStream_a("units.bin"));
                for (int i = 0; i < 12; ++i) {
                    int n;
                    aeii.a.var_byte_arr_a[i] = dataInputStream.readByte();
                    aeii.a.var_byte_arr_arr_c[i][0] = dataInputStream.readByte();
                    aeii.a.var_byte_arr_arr_c[i][1] = dataInputStream.readByte();
                    aeii.a.var_byte_arr_c[i] = dataInputStream.readByte();
                    aeii.a.var_byte_arr_d[i] = dataInputStream.readByte();
                    aeii.a.var_byte_arr_e[i] = dataInputStream.readByte();
                    aeii.a.var_short_arr_a[i] = dataInputStream.readShort();
                    int n2 = dataInputStream.readByte();
                    aeii.a.var_byte_arr_arr_arr_a[i] = new byte[n2][2];
                    for (n = 0; n < n2; ++n) {
                        aeii.a.var_byte_arr_arr_arr_a[i][n][0] = dataInputStream.readByte();
                        aeii.a.var_byte_arr_arr_arr_a[i][n][1] = dataInputStream.readByte();
                    }
                    n = dataInputStream.readByte();
                    for (n2 = 0; n2 < n; ++n2) {
                        aeii.a.var_short_arr_b[i] = (short)(var_short_arr_b[i] | 1 << dataInputStream.readByte());
                    }
                }
                dataInputStream.close();
                return;
            }
            catch (Exception exception2) {
                exception.printStackTrace();
                exception2.printStackTrace();
                return;
            }
        }
    }

    static {
        var_byte_a = (byte)12;
        var_byte_b = (byte)6;
        var_byte_c = (byte)6;
        var_java_lang_String_arr_a = new String[]{"soldier", "archer", "lizard", "wizard", "wisp", "spider", "golem", "catapult", "wyvern", "king", "skeleton", "crystall"};
        var_byte_arr_a = new byte[12];
        var_byte_arr_arr_c = new byte[12][2];
        var_byte_arr_c = new byte[12];
        var_byte_arr_d = new byte[12];
        var_byte_arr_e = new byte[12];
        var_byte_arr_arr_arr_a = new byte[12][][];
        var_short_arr_a = new short[12];
        var_short_arr_b = new short[12];
    }
}

