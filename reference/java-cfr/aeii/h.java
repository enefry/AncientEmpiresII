/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
package aeii;

import aeii.a;
import aeii.c;
import aeii.d;
import aeii.f;
import aeii.g;
import javax.microedition.lcdui.Graphics;

public final class h {
    public static String[] var_java_lang_String_arr_a;
    public static String[] var_java_lang_String_arr_b;
    private static String[] var_java_lang_String_arr_c;
    private g var_aeii_g_a;
    public a var_aeii_a_a;
    private byte var_byte_c;
    public boolean var_boolean_a = false;
    public boolean var_boolean_b = false;
    private boolean var_boolean_c;
    private byte var_byte_d;
    public byte var_byte_a;
    private byte var_byte_e;
    public byte var_byte_b;
    private byte var_byte_f;
    private byte var_byte_g;
    private boolean var_boolean_d;
    private int var_int_a = 0;
    private static byte[] var_byte_arr_a;
    private a.a.a.g var_a_a_a_g_a;
    private a.a.a.g var_a_a_a_g_b;
    private a.a.a.g var_a_a_a_g_c;
    private a.a.a.g var_a_a_a_g_d;
    private int var_int_b;
    private int var_int_c;
    private long var_long_a;
    private long var_long_b;
    private a.a.a.g[] var_a_a_a_g_arr_a;
    private d[] var_aeii_d_arr_a;
    private d var_aeii_d_a;
    public h var_aeii_h_a;
    private int var_int_d;
    private int var_int_e;
    private int var_int_f;
    private int var_int_g;
    private boolean var_boolean_e = false;
    private int h;
    private int i;
    private int[][] var_int_arr_arr_a;
    private int[] var_int_arr_a;
    private int j;
    private int k = 20;
    private int l;
    private int m;
    private byte[][] var_byte_arr_arr_a;
    private a.a.a.g var_a_a_a_g_e;
    private a.a.a.g var_a_a_a_g_f;
    private a.a.a.g[] var_a_a_a_g_arr_b;
    private int n;
    private int o;

    public h(g g2, a a2, h h2) {
        int n;
        this.var_aeii_g_a = g2;
        this.var_aeii_a_a = a2;
        this.var_byte_c = a2.var_byte_d;
        this.var_aeii_h_a = h2;
        this.var_byte_d = (byte)a2.var_int_h;
        this.h = this.var_byte_d;
        this.var_byte_f = this.var_byte_e = (byte)a2.int_b();
        int n2 = 0;
        if (h2 == null) {
            this.var_byte_g = 0;
            this.var_int_b = 0;
            this.var_boolean_d = true;
        } else {
            n2 = g2.var_int_c;
            this.var_byte_g = 1;
            this.var_int_a = 0;
        }
        this.var_int_d = g2.byte_a((int)a2.var_short_b, (int)a2.var_short_c);
        String string = var_java_lang_String_arr_a[this.var_int_d];
        String string2 = var_java_lang_String_arr_b[this.var_int_d];
        if (h2 != null && string2.equals(var_java_lang_String_arr_b[h2.var_int_d])) {
            this.var_aeii_d_arr_a = new d[h2.var_aeii_d_arr_a.length];
            System.arraycopy(h2.var_aeii_d_arr_a, 0, this.var_aeii_d_arr_a, 0, this.var_aeii_d_arr_a.length);
        } else {
            this.var_aeii_d_arr_a = new a.a.a.g((String)string2).var_aeii_d_arr_a;
        }
        if (this.var_byte_g == 1) {
            for (int i = 0; i < this.var_aeii_d_arr_a.length; ++i) {
                this.var_aeii_d_arr_a[i] = new d(this.var_aeii_d_arr_a[i], 1);
            }
        }
        try {
            this.var_aeii_d_a = h2 != null && string.equals(var_java_lang_String_arr_a[h2.var_int_d]) ? h2.var_aeii_d_a : new d(string + "_bg");
            if (this.var_byte_g == 1) {
                this.var_aeii_d_a = new d(this.var_aeii_d_a, 1);
            }
        }
        catch (Exception exception) {}
        if (this.var_aeii_d_a != null) {
            this.var_int_e = this.var_aeii_d_a.b;
        }
        this.var_int_f = g2.var_int_c / this.var_aeii_d_arr_a[0].var_int_a;
        if (g2.var_int_c % this.var_aeii_d_arr_a[0].var_int_a != 0) {
            ++this.var_int_f;
        }
        this.var_int_g = (g2.var_int_o - this.var_int_e) / this.var_aeii_d_arr_a[0].b;
        if ((g2.var_int_o - this.var_int_e) % this.var_aeii_d_arr_a[0].b != 0) {
            ++this.var_int_g;
        }
        this.var_byte_arr_arr_a = new byte[this.var_int_f][this.var_int_g];
        for (n = 0; n < this.var_int_f; ++n) {
            for (int i = 0; i < this.var_int_g; ++i) {
                this.var_byte_arr_arr_a[n][i] = (byte)Math.abs(aeii.c.var_java_util_Random_a.nextInt() % this.var_aeii_d_arr_a.length);
            }
        }
        this.var_a_a_a_g_a = new a.a.a.g(var_java_lang_String_arr_c[this.var_byte_c], (byte)(g2.var_byte_arr_d[a2.var_byte_e] - 1 << 1));
        if (h2 != null && h2.var_byte_c == this.var_byte_c) {
            if (h2.var_a_a_a_g_d != null) {
                this.var_a_a_a_g_d = new a.a.a.g(h2.var_a_a_a_g_d);
            }
            if (h2.var_a_a_a_g_b != null) {
                this.var_a_a_a_g_b = new a.a.a.g(h2.var_a_a_a_g_b);
            }
            if (h2.var_a_a_a_g_c != null) {
                this.var_a_a_a_g_c = new a.a.a.g(h2.var_a_a_a_g_c);
            }
            if (h2.var_a_a_a_g_e != null) {
                this.var_a_a_a_g_e = new a.a.a.g(h2.var_a_a_a_g_e);
                this.var_a_a_a_g_f = new a.a.a.g(h2.var_a_a_a_g_f);
            }
        } else if (this.var_byte_c != 0 && this.var_byte_c != 10 && this.var_byte_c != 5) {
            if (this.var_byte_c == 1) {
                this.var_a_a_a_g_c = new a.a.a.g("archer_arrow");
            } else if (this.var_byte_c == 9) {
                this.var_a_a_a_g_b = new a.a.a.g("kingwave");
                this.var_a_a_a_g_d = new a.a.a.g("kingslash");
                this.var_a_a_a_g_e = new a.a.a.g("king_heads");
                this.var_a_a_a_g_f = new a.a.a.g("king_heads_back");
            } else if (this.var_byte_c == 2) {
                this.var_a_a_a_g_b = new a.a.a.g("watermagic");
                this.var_a_a_a_g_c = new a.a.a.g("fish");
            } else if (this.var_byte_c != 7 && this.var_byte_c != 6) {
                if (this.var_byte_c == 3) {
                    this.var_a_a_a_g_c = this.var_a_a_a_g_b = new a.a.a.g("spell");
                }
            } else {
                this.var_a_a_a_g_c = new a.a.a.g("crater");
            }
        } else {
            this.var_a_a_a_g_d = new a.a.a.g("slash");
        }
        if (this.var_a_a_a_g_b != null) {
            this.var_a_a_a_g_b.a(0, this.var_boolean_d);
        }
        if (this.var_a_a_a_g_c != null) {
            this.var_a_a_a_g_c.a(0, this.var_boolean_d);
        }
        if (this.var_a_a_a_g_d != null) {
            this.var_a_a_a_g_d.a(0, this.var_boolean_d);
        }
        if (this.var_a_a_a_g_e != null) {
            this.var_a_a_a_g_e.a(a2.var_byte_j, this.var_boolean_d);
            this.var_a_a_a_g_e.c(a2.var_byte_j);
            this.var_a_a_a_g_f.a(0, this.var_boolean_d);
            this.var_a_a_a_g_f.c(a2.var_byte_j);
        }
        this.var_int_arr_arr_a = new int[a2.var_byte_arr_arr_a.length][2];
        for (n = 0; n < this.var_int_arr_arr_a.length; ++n) {
            this.var_int_arr_arr_a[n][0] = a2.var_byte_arr_arr_a[n][0] * g2.var_java_lang_String_arr_j / 128;
            if (this.var_byte_g == 1) {
                this.var_int_arr_arr_a[n][0] = g2.var_int_c - this.var_int_arr_arr_a[n][0] - this.var_a_a_a_g_a.o + n2;
            }
            this.var_int_arr_arr_a[n][1] = a2.var_byte_arr_arr_a[n][1] * g2.var_int_o / 128 - this.var_a_a_a_g_a.p;
        }
        this.var_a_a_a_g_arr_a = new a.a.a.g[this.var_byte_e];
        if (this.var_byte_c == 4 || this.var_byte_c == 6) {
            this.var_int_arr_a = new int[this.var_byte_e];
        }
        for (n = 0; n < this.var_byte_e; ++n) {
            this.var_a_a_a_g_arr_a[n] = new a.a.a.g(this.var_a_a_a_g_a);
            g2.a(this.var_a_a_a_g_arr_a[n]);
            this.var_a_a_a_g_arr_a[n].b(this.var_int_arr_arr_a[n][0], this.var_int_arr_arr_a[n][1]);
            this.var_a_a_a_g_arr_a[n].a(0, this.var_boolean_d);
            this.var_a_a_a_g_arr_a[n].c = false;
            this.var_a_a_a_g_arr_a[n].v = 0;
            if (this.var_byte_c == 6) {
                this.var_a_a_a_g_arr_a[n].e = true;
                this.var_int_arr_a[n] = aeii.c.int_a(360);
                this.var_a_a_a_g_arr_a[n].q = -6 + 4 * aeii.f.short_a(this.var_int_arr_a[n]) >> 10;
                continue;
            }
            if (this.var_byte_c == 4) {
                this.var_a_a_a_g_arr_a[n].e = true;
                this.var_a_a_a_g_arr_a[n].q = -5 - aeii.c.int_a(10);
                this.var_int_arr_a[n] = aeii.c.int_a(360);
                continue;
            }
            if (this.var_byte_c != 9) continue;
            this.var_a_a_a_g_arr_a[n].var_a_a_a_g_a = this.var_a_a_a_g_e;
            this.var_a_a_a_g_arr_a[n].var_a_a_a_g_b = this.var_a_a_a_g_f;
        }
    }

    private int a(a.a.a.g g2, int n) {
        if (this.var_byte_g == 1) {
            return this.var_a_a_a_g_a.o - n - g2.o;
        }
        return n;
    }

    public final void a() {
        this.var_boolean_a = true;
        this.var_int_a = 1;
        this.var_long_b = this.var_aeii_g_a.var_long_a;
    }

    /*
     * Unable to fully structure code
     */
    public final void b() {
        block124: {
            block122: {
                block123: {
                    if (this.var_boolean_c && this.h > this.var_byte_a) {
                        this.h -= 2;
                        if (this.h < this.var_byte_a) {
                            this.h = this.var_byte_a;
                        }
                        this.var_aeii_g_a.var_boolean_a = true;
                    }
                    if (this.var_byte_c == 8 || this.var_byte_c == 9 || this.var_byte_c == 7 || this.var_byte_c == 1 || this.var_byte_c == 3 || this.var_byte_c == 2 || this.var_byte_c == 6) break block122;
                    if (this.var_byte_c != 4) break block123;
                    var1_1 = this;
                    switch (var1_1.var_int_a) {
                        case 1: {
                            if (var1_1.l == 0) {
                                if (var1_1.var_aeii_g_a.var_long_a - var1_1.var_long_b < 200L) break;
                                var1_1.l = 1;
                                var1_1.var_long_b = var1_1.var_aeii_g_a.var_long_a;
                                break;
                            }
                            if (var1_1.l == 1) {
                                var1_1.k += 5;
                                if (var1_1.k >= 90) {
                                    ++var1_1.l;
                                    var1_1.var_long_b = var1_1.var_aeii_g_a.var_long_a;
                                }
                                if ((var1_1.k - 20) % 15 == 0) {
                                    for (var2_5 = 0; var2_5 < var1_1.var_a_a_a_g_arr_a.length; ++var2_5) {
                                        var3_7 = a.a.a.g.a(var1_1.var_aeii_g_a.var_a_a_a_g_o, aeii.c.int_a(-1, 2), 0, 0, 1, 100, (byte)0);
                                        var3_7.b(var1_1.var_a_a_a_g_arr_a[var2_5].m + aeii.c.int_a(var1_1.var_a_a_a_g_a.o - var3_7.o), var1_1.var_a_a_a_g_arr_a[var2_5].n + var1_1.var_a_a_a_g_a.p - var3_7.p + 1);
                                        var1_1.var_aeii_g_a.a((a.a.a.g)var3_7);
                                    }
                                }
                            } else if (var1_1.l == 2 && var1_1.var_aeii_g_a.var_long_a - var1_1.var_long_b >= 400L) {
                                var1_1.k = 20;
                                var1_1.var_aeii_h_a.c();
                                var1_1.var_int_a = 4;
                                var1_1.var_aeii_h_a.var_boolean_e = false;
                                var1_1.var_long_b = var1_1.var_aeii_g_a.var_long_a;
                            }
                            if (++var1_1.j < 2) break;
                            for (var2_5 = 0; var2_5 < var1_1.var_aeii_h_a.var_a_a_a_g_arr_a.length; ++var2_5) {
                                var3_7 = a.a.a.g.a(var1_1.var_aeii_g_a.var_a_a_a_g_g, 0, 0, 0, 1, 50, (byte)0);
                                var3_7.b(var1_1.var_aeii_h_a.var_a_a_a_g_arr_a[var2_5].m + aeii.c.int_a(var1_1.var_aeii_h_a.var_a_a_a_g_arr_a[var2_5].o - var3_7.o), var1_1.var_aeii_h_a.var_a_a_a_g_arr_a[var2_5].n + aeii.c.int_a(var1_1.var_aeii_h_a.var_a_a_a_g_arr_a[var2_5].p - var3_7.p));
                                var3_7.d = true;
                                var1_1.var_aeii_g_a.a((a.a.a.g)var3_7);
                            }
                            var1_1.j = 0;
                            var3_7 = var1_1.var_aeii_h_a;
                            for (var4_13 = 0; var4_13 < var3_7.var_byte_f; ++var4_13) {
                                var3_7.var_a_a_a_g_arr_a[var4_13].x = 6;
                            }
                            break;
                        }
                        case 4: {
                            if (var1_1.var_aeii_g_a.var_long_a - var1_1.var_long_b < 800L) break;
                            var1_1.var_boolean_b = true;
                            var1_1.var_int_a = 0;
                        }
                    }
                    var2_5 = 0;
                    if (var1_1.var_aeii_g_a.var_long_a - var1_1.var_long_a >= 300L) {
                        var2_5 = 1;
                        var1_1.var_long_a = var1_1.var_aeii_g_a.var_long_a;
                    }
                    for (var3_8 = 0; var3_8 < var1_1.var_a_a_a_g_arr_a.length; ++var3_8) {
                        if (var2_5 != 0) {
                            var4_14 = a.a.a.g.a(null, 0, 0, 0, 1, 500, (byte)4);
                            var4_14.b(var1_1.var_a_a_a_g_arr_a[var3_8].m + (var1_1.var_a_a_a_g_arr_a[var3_8].o >> 1), var1_1.var_a_a_a_g_arr_a[var3_8].n + (var1_1.var_a_a_a_g_arr_a[var3_8].p >> 1) + var1_1.var_int_c);
                            var1_1.var_aeii_g_a.a(var4_14);
                        }
                        var4_13 = var1_1.var_a_a_a_g_arr_a[var3_8].o / 3;
                        var1_1.var_a_a_a_g_arr_a[var3_8].b(var1_1.var_int_arr_arr_a[var3_8][0] + (var4_13 * aeii.f.short_b(var1_1.var_int_arr_a[var3_8]) >> 10), var1_1.var_int_arr_arr_a[var3_8][1] + (var4_13 * aeii.f.short_a(var1_1.var_int_arr_a[var3_8]) / 3 >> 10));
                        var1_1.var_int_arr_a[var3_8] = (var1_1.var_int_arr_a[var3_8] + var1_1.k) % 360;
                    }
                    break block124;
                }
                switch (this.var_int_a) {
                    case 1: {
                        if (this.var_aeii_g_a.var_long_a - this.var_long_b >= 200L) {
                            this.var_int_a = 3;
                        }
                    }
                    default: {
                        break;
                    }
                    case 3: {
                        var1_2 = true;
                        if (this.l < this.var_byte_f) {
                            if (this.var_byte_c == 0 || this.var_byte_c == 5) {
                                this.var_a_a_a_g_arr_a[this.l].w = -6;
                            }
                            if (this.var_byte_c == 5) {
                                this.var_a_a_a_g_arr_a[this.l].u = 2 * aeii.h.var_byte_arr_a[this.var_byte_g];
                                for (var2_5 = 0; var2_5 < 3; ++var2_5) {
                                    var3_9 = a.a.a.g.a(this.var_aeii_g_a.var_a_a_a_g_o, aeii.c.int_a(-1, 2), 0, -1, 1, 100, (byte)0);
                                    var3_9.b(this.var_a_a_a_g_arr_a[this.l].m + aeii.c.int_a(this.var_a_a_a_g_a.o - var3_9.o), this.var_a_a_a_g_arr_a[this.l].n + this.var_a_a_a_g_a.p - var3_9.p + 1);
                                    this.var_aeii_g_a.a(var3_9);
                                }
                            } else {
                                this.var_a_a_a_g_arr_a[this.l].u = aeii.h.var_byte_arr_a[this.var_byte_g];
                            }
                            this.var_a_a_a_g_arr_a[this.l].a(1, this.var_boolean_d);
                            ++this.l;
                            var1_2 = false;
                        }
                        for (var2_5 = 0; var2_5 < this.l; ++var2_5) {
                            if (this.var_byte_c == 10) {
                                if (this.var_a_a_a_g_arr_a[var2_5].y == -1) continue;
                                ++this.var_a_a_a_g_arr_a[var2_5].y;
                                if (this.var_a_a_a_g_arr_a[var2_5].y >= 16) {
                                    this.var_a_a_a_g_arr_a[var2_5].a(2, this.var_boolean_d);
                                    this.var_a_a_a_g_arr_a[var2_5].u = 0;
                                    this.var_a_a_a_g_arr_a[var2_5].y = -1;
                                    var3_9 = a.a.a.g.a(null, 0, 0, 0, 1, 800, (byte)2);
                                    var3_9.b(this.var_a_a_a_g_arr_a[var2_5].m + this.a(var3_9, this.var_a_a_a_g_a.o), this.var_a_a_a_g_arr_a[var2_5].n + this.var_a_a_a_g_a.p);
                                    this.var_aeii_g_a.a(var3_9);
                                    var3_9 = a.a.a.g.a(this.var_a_a_a_g_d, 0, 0, 0, 1, 150, (byte)0);
                                    var3_9.b(this.var_a_a_a_g_arr_a[var2_5].m + this.a(var3_9, 24), this.var_a_a_a_g_arr_a[var2_5].n + 3);
                                    this.var_aeii_g_a.a(var3_9);
                                    continue;
                                }
                                var1_2 = false;
                                continue;
                            }
                            if (this.var_a_a_a_g_arr_a[var2_5].q < 0) {
                                ++this.var_a_a_a_g_arr_a[var2_5].w;
                                var1_2 = false;
                                continue;
                            }
                            if (this.var_a_a_a_g_arr_a[var2_5].w < 6) continue;
                            this.var_a_a_a_g_arr_a[var2_5].q = 0;
                            this.var_a_a_a_g_arr_a[var2_5].w = 0;
                            this.var_a_a_a_g_arr_a[var2_5].u = 0;
                            if (this.var_byte_c != 0 && this.var_byte_c != 5) continue;
                            this.var_a_a_a_g_arr_a[var2_5].a(2, this.var_boolean_d);
                            if (this.var_byte_c == 0) {
                                var3_9 = a.a.a.g.a(this.var_a_a_a_g_d, 0, 0, 0, 1, 150, (byte)0);
                                var3_9.b(this.var_a_a_a_g_arr_a[var2_5].m + this.a(var3_9, 14), this.var_a_a_a_g_arr_a[var2_5].n + this.var_a_a_a_g_arr_a[var2_5].p);
                                var3_9.q = 4 - this.var_a_a_a_g_arr_a[var2_5].p;
                                this.var_aeii_g_a.a(var3_9);
                                continue;
                            }
                            if (this.var_byte_c != 5) continue;
                            var3_9 = a.a.a.g.a(this.var_aeii_g_a.var_a_a_a_g_g, 0, 0, 0, 1, 50, (byte)0);
                            var3_9.b(this.var_a_a_a_g_arr_a[var2_5].m + this.a(var3_9, this.var_a_a_a_g_a.o * 3 / 4), this.var_a_a_a_g_arr_a[var2_5].n + this.var_a_a_a_g_arr_a[var2_5].p);
                            var3_9.q = -var3_9.p;
                            this.var_aeii_g_a.a(var3_9);
                        }
                        if (var1_2) {
                            this.l = 0;
                            this.var_int_a = 6;
                            this.var_aeii_h_a.c();
                            this.var_aeii_g_a.void_a(200);
                            aeii.c.d(200);
                            aeii.c.b(14, 1);
                            this.var_long_b = this.var_aeii_g_a.var_long_a;
                            break;
                        }
                        break block124;
                    }
                    case 4: {
                        var1_2 = true;
                        if (this.l < this.var_byte_f) {
                            if (this.var_byte_c != 0 && this.var_byte_c != 2 && this.var_byte_c != 5) {
                                if (this.var_byte_c == 10) {
                                    this.var_a_a_a_g_arr_a[this.l].y = 0;
                                }
                            } else {
                                this.var_a_a_a_g_arr_a[this.l].w = -6;
                            }
                            this.var_a_a_a_g_arr_a[this.l].u = this.var_byte_c == 5 ? -2 * aeii.h.var_byte_arr_a[this.var_byte_g] : -aeii.h.var_byte_arr_a[this.var_byte_g];
                            this.var_a_a_a_g_arr_a[this.l].a(3, this.var_boolean_d);
                            ++this.l;
                            var1_2 = false;
                        }
                        for (var2_5 = 0; var2_5 < this.l; ++var2_5) {
                            if (this.var_byte_c == 10) {
                                if (this.var_a_a_a_g_arr_a[var2_5].y == -1) continue;
                                ++this.var_a_a_a_g_arr_a[var2_5].y;
                                if (this.var_a_a_a_g_arr_a[var2_5].y >= 16) {
                                    this.var_a_a_a_g_arr_a[var2_5].a(0, this.var_boolean_d);
                                    this.var_a_a_a_g_arr_a[var2_5].u = 0;
                                    this.var_a_a_a_g_arr_a[var2_5].y = -1;
                                    continue;
                                }
                                var1_2 = false;
                                continue;
                            }
                            if (this.var_a_a_a_g_arr_a[var2_5].q < 0) {
                                ++this.var_a_a_a_g_arr_a[var2_5].w;
                                var1_2 = false;
                                continue;
                            }
                            if (this.var_a_a_a_g_arr_a[var2_5].w < 6) continue;
                            this.var_a_a_a_g_arr_a[var2_5].w = 0;
                            this.var_a_a_a_g_arr_a[var2_5].u = 0;
                            this.var_a_a_a_g_arr_a[var2_5].q = 0;
                            this.var_a_a_a_g_arr_a[var2_5].a(0, this.var_boolean_d);
                        }
                        if (var1_2) {
                            this.var_boolean_b = true;
                            this.var_int_a = 0;
                            this.var_long_b = this.var_aeii_g_a.var_long_a;
                            break;
                        }
                        break block124;
                    }
                    case 6: {
                        if (this.var_byte_c == 10 && this.var_aeii_g_a.var_long_a - this.var_long_b >= 400L || (this.var_byte_c == 0 || this.var_byte_c == 2 || this.var_byte_c == 5) && this.var_aeii_g_a.var_long_a - this.var_long_b >= 50L) {
                            this.var_int_a = 4;
                            break;
                        }
                        break block124;
                    }
                }
                break block124;
            }
            var1_3 = this;
            switch (var1_3.var_int_a) {
                case 1: {
                    if (var1_3.var_byte_c != 6) ** GOTO lbl270
                    if (var1_3.l != 0) ** GOTO lbl200
                    if (var1_3.var_aeii_g_a.var_long_a - var1_3.var_long_b >= 200L) {
                        if (var1_3.o < var1_3.var_byte_f) {
                            var1_3.var_a_a_a_g_arr_a[var1_3.o].y = -1;
                            var1_3.var_a_a_a_g_arr_a[var1_3.o].e = false;
                            var1_3.var_a_a_a_g_arr_a[var1_3.o].w = 0;
                        }
                        if (++var1_3.o >= var1_3.var_byte_f) {
                            var1_3.o = 0;
                            var1_3.l = 1;
                            var1_3.var_long_b = var1_3.var_aeii_g_a.var_long_a;
                            break;
                        }
                    }
                    ** GOTO lbl365
lbl200:
                    // 1 sources

                    if (var1_3.l != 1) ** GOTO lbl210
                    if (var1_3.var_aeii_g_a.var_long_a - var1_3.var_long_b >= 200L) {
                        if (var1_3.o < var1_3.var_byte_f) {
                            var1_3.var_a_a_a_g_arr_a[var1_3.o].w = -1;
                        }
                        if (++var1_3.o >= var1_3.var_byte_f) {
                            var1_3.o = 0;
                            var1_3.l = 2;
                            var1_3.var_long_b = var1_3.var_aeii_g_a.var_long_a;
                            break;
                        }
                    }
                    ** GOTO lbl365
lbl210:
                    // 1 sources

                    if (var1_3.l != 2) ** GOTO lbl365
                    var2_5 = 1;
                    if (var1_3.var_aeii_g_a.var_long_a - var1_3.var_long_b < 200L) ** GOTO lbl219
                    if (var1_3.o < var1_3.var_byte_f) {
                        var1_3.var_a_a_a_g_arr_a[var1_3.o].y = 0;
                        var1_3.var_a_a_a_g_arr_a[var1_3.o].w = 0;
                        var1_3.var_a_a_a_g_arr_a[var1_3.o].a(2, var1_3.var_boolean_d);
                        var1_3.var_a_a_a_g_arr_a[var1_3.o].r = 1;
                    }
                    if (++var1_3.o >= var1_3.var_byte_f) ** GOTO lbl220
lbl219:
                    // 2 sources

                    var2_5 = 0;
lbl220:
                    // 9 sources

                    for (var3_10 = 0; var3_10 < var1_3.var_byte_f; ++var3_10) {
                        if (var1_3.var_a_a_a_g_arr_a[var3_10].y == 0) {
                            if (var1_3.var_a_a_a_g_arr_a[var3_10].var_int_l == 1) {
                                var1_3.var_a_a_a_g_arr_a[var3_10].q = 0;
                                var1_3.var_a_a_a_g_arr_a[var3_10].y = 1;
                                aeii.c.d(200);
                                var1_3.var_aeii_g_a.void_a(1200);
                                aeii.c.b(14, 1);
                                for (var4_16 = 0; var4_16 < 2; ++var4_16) {
                                    var2_6 = a.a.a.g.a(var1_3.var_aeii_g_a.var_a_a_a_g_e, 0, 0, -1, 1, aeii.c.int_a(4) * 50, (byte)0);
                                    var2_6.b(var1_3.var_a_a_a_g_arr_a[var3_10].m + aeii.c.int_a(var1_3.var_a_a_a_g_a.o - var2_6.o), var1_3.var_a_a_a_g_arr_a[var3_10].n + var1_3.var_a_a_a_g_a.p - var2_6.p + 2);
                                    var2_6.d = true;
                                    var1_3.var_aeii_g_a.a(var2_6);
                                }
                                var4_15 = a.a.a.g.a(var1_3.var_aeii_g_a.var_a_a_a_g_e, -1, 0, -1, 1, aeii.c.int_a(4) * 50, (byte)0);
                                var4_15.b(var1_3.var_a_a_a_g_arr_a[var3_10].m, var1_3.var_a_a_a_g_arr_a[var3_10].n + var1_3.var_a_a_a_g_a.p - var4_15.p + 2);
                                var4_15.d = true;
                                var1_3.var_aeii_g_a.a(var4_15);
                                var4_15 = a.a.a.g.a(var1_3.var_aeii_g_a.var_a_a_a_g_e, 1, 0, -1, 1, aeii.c.int_a(4) * 50, (byte)0);
                                var4_15.b(var1_3.var_a_a_a_g_arr_a[var3_10].m + var1_3.var_a_a_a_g_a.o - var4_15.o, var1_3.var_a_a_a_g_arr_a[var3_10].n + var1_3.var_a_a_a_g_a.p - var4_15.p + 2);
                                var4_15.d = true;
                                var1_3.var_aeii_g_a.a(var4_15);
                            }
                            var2_5 = 0;
                            continue;
                        }
                        if (var1_3.var_a_a_a_g_arr_a[var3_10].y == -1) continue;
                        if (var1_3.var_a_a_a_g_arr_a[var3_10].r > 0) {
                            var2_5 = 0;
                            continue;
                        }
                        if (var1_3.var_a_a_a_g_arr_a[var3_10].r == -1) continue;
                        if (var1_3.var_a_a_a_g_arr_a[var3_10].y == 1) {
                            var1_3.var_a_a_a_g_arr_a[var3_10].a(3, var1_3.var_boolean_d);
                            var1_3.var_a_a_a_g_arr_a[var3_10].r = 1;
                            var1_3.var_a_a_a_g_arr_a[var3_10].y = 2;
                            var2_5 = 0;
                            continue;
                        }
                        if (var1_3.var_a_a_a_g_arr_a[var3_10].y != 2) continue;
                        var1_3.var_a_a_a_g_arr_a[var3_10].q = -6;
                        var1_3.var_a_a_a_g_arr_a[var3_10].e = true;
                        var1_3.var_a_a_a_g_arr_a[var3_10].w = aeii.c.int_a(-2, 3);
                        var1_3.var_a_a_a_g_arr_a[var3_10].a(0, var1_3.var_boolean_d);
                        var1_3.var_a_a_a_g_arr_a[var3_10].r = -1;
                        var1_3.var_a_a_a_g_arr_a[var3_10].y = 3;
                        var2_5 = 0;
                    }
                    if (var2_5 != 0) {
                        var1_3.l = 0;
                        var1_3.var_long_b = var1_3.var_aeii_g_a.var_long_a;
                        var1_3.var_int_a = 4;
                        break;
                    }
                    ** GOTO lbl365
lbl270:
                    // 1 sources

                    if (var1_3.l != 0) ** GOTO lbl282
                    if (var1_3.var_aeii_g_a.var_long_a - var1_3.var_long_b >= 200L) {
                        if (var1_3.var_byte_c == 9) {
                            var1_3.var_a_a_a_g_arr_b = new a.a.a.g[var1_3.var_byte_f << 1];
                        } else if (var1_3.var_byte_c != 7 && var1_3.var_byte_c != 1) {
                            var1_3.var_a_a_a_g_arr_b = new a.a.a.g[var1_3.var_byte_f];
                        }
                        var1_3.l = 1;
                        var1_3.o = 0;
                        var1_3.var_long_b = var1_3.var_aeii_g_a.var_long_a;
                        break;
                    }
                    ** GOTO lbl365
lbl282:
                    // 1 sources

                    if (var1_3.l == 1) {
                        var2_5 = 1;
                        if (var1_3.o < var1_3.var_byte_f) {
                            var1_3.var_a_a_a_g_arr_a[var1_3.o].a(2, var1_3.var_boolean_d);
                            var1_3.var_a_a_a_g_arr_a[var1_3.o].r = 1;
                            if (var1_3.var_byte_c != 3 && var1_3.var_byte_c != 2) {
                                if (var1_3.var_byte_c == 1) {
                                    var5_22 = a.a.a.g.a(var1_3.var_a_a_a_g_c, 0, 0, 0, 1, 0, (byte)0);
                                    var5_22.a(1, var1_3.var_boolean_d);
                                    var5_22.b(var1_3.var_a_a_a_g_arr_a[var1_3.o].m + var1_3.a(var5_22, var1_3.var_a_a_a_g_arr_a[var1_3.o].o), var1_3.var_a_a_a_g_arr_a[var1_3.o].n);
                                    var1_3.var_aeii_g_a.a(var5_22);
                                } else if (var1_3.var_byte_c == 7) {
                                    var1_3.var_a_a_a_g_arr_a[var1_3.o].x = 5;
                                    for (var3_10 = 0; var3_10 < 3; ++var3_10) {
                                        var4_17 = a.a.a.g.a(var1_3.var_aeii_g_a.var_a_a_a_g_o, aeii.c.int_a(-1, 2), 0, 0, 1, aeii.c.int_a(4) * 50, (byte)0);
                                        var4_17.b(var1_3.var_a_a_a_g_arr_a[var1_3.o].m + var1_3.var_a_a_a_g_a.o / 2, var1_3.var_a_a_a_g_arr_a[var1_3.o].n);
                                        var4_17.d = true;
                                        var1_3.var_aeii_g_a.a(var4_17);
                                    }
                                } else if (var1_3.var_byte_c == 9) {
                                    var5_22 = a.a.a.g.a(var1_3.var_a_a_a_g_d, 0, 0, 0, 1, 200, (byte)0);
                                    var5_22.b(var1_3.var_a_a_a_g_arr_a[0].m, var1_3.var_a_a_a_g_arr_a[0].n + var1_3.var_a_a_a_g_a.p);
                                    var5_22.q = -var1_3.var_a_a_a_g_a.p;
                                    var1_3.var_aeii_g_a.a(var5_22);
                                    var1_3.var_a_a_a_g_arr_b[0] = a.a.a.g.a(var1_3.var_a_a_a_g_b, aeii.h.var_byte_arr_a[var1_3.var_byte_g] * 3, -2, 0, -1, 100, (byte)0);
                                    var4_18 = var1_3.var_a_a_a_g_arr_a[var1_3.o].m + var1_3.a(var1_3.var_a_a_a_g_arr_b[var1_3.o], var1_3.var_a_a_a_g_arr_a[var1_3.o].o / 2);
                                    var3_10 = var1_3.var_a_a_a_g_arr_a[var1_3.o].n + var1_3.var_a_a_a_g_a.p - var1_3.var_a_a_a_g_arr_b[var1_3.o].p + 2;
                                    var1_3.var_a_a_a_g_arr_b[0].b(var4_18, var3_10);
                                    var1_3.var_a_a_a_g_arr_b[1] = a.a.a.g.a(var1_3.var_a_a_a_g_b, aeii.h.var_byte_arr_a[var1_3.var_byte_g] * 3, 1, 0, -1, 100, (byte)0);
                                    var1_3.var_a_a_a_g_arr_b[1].b(var4_18, var3_10);
                                    var1_3.var_aeii_g_a.a(var1_3.var_a_a_a_g_arr_b[1]);
                                    var1_3.var_a_a_a_g_arr_b[1].var_byte_l = var1_3.var_byte_g;
                                    var1_3.var_aeii_g_a.a(var1_3.var_a_a_a_g_arr_b[0]);
                                    var1_3.var_a_a_a_g_arr_b[0].var_byte_l = var1_3.var_byte_g;
                                } else if (var1_3.var_byte_c == 8) {
                                    var1_3.var_a_a_a_g_arr_b[var1_3.o] = a.a.a.g.a(null, aeii.h.var_byte_arr_a[var1_3.var_byte_g], 0, 0, -1, 2000, (byte)6);
                                    var1_3.var_a_a_a_g_arr_b[var1_3.o].b(var1_3.var_a_a_a_g_arr_a[var1_3.o].m + var1_3.a(var1_3.var_a_a_a_g_arr_b[var1_3.o], var1_3.var_a_a_a_g_arr_a[var1_3.o].o + 2), var1_3.var_a_a_a_g_arr_a[var1_3.o].n + 30);
                                    var1_3.var_a_a_a_g_arr_b[var1_3.o].d = true;
                                    aeii.c.d(200);
                                    var1_3.var_aeii_g_a.void_a(1200);
                                    aeii.c.b(14, 1);
                                    var1_3.var_aeii_g_a.a(var1_3.var_a_a_a_g_arr_b[var1_3.o]);
                                }
                            } else {
                                var1_3.var_a_a_a_g_arr_b[var1_3.o] = a.a.a.g.a(var1_3.var_a_a_a_g_b, 0, 0, 0, 1, 50, (byte)0);
                                var1_3.var_a_a_a_g_arr_b[var1_3.o].b(var1_3.var_a_a_a_g_arr_a[var1_3.o].m + var1_3.a(var1_3.var_a_a_a_g_arr_b[var1_3.o], var1_3.var_a_a_a_g_arr_a[var1_3.o].o), var1_3.var_a_a_a_g_arr_a[var1_3.o].n);
                                var1_3.var_aeii_g_a.a(var1_3.var_a_a_a_g_arr_b[var1_3.o]);
                            }
                        }
                        if (++var1_3.o < var1_3.var_byte_f) {
                            var2_5 = 0;
                        }
                        for (var3_10 = 0; var3_10 < var1_3.var_byte_f; ++var3_10) {
                            if (var1_3.var_a_a_a_g_arr_a[var3_10].r > 0) {
                                var2_5 = 0;
                            } else if (var1_3.var_byte_c != 7 && var1_3.var_a_a_a_g_arr_a[var3_10].r != -1) {
                                if (var1_3.var_a_a_a_g_arr_a[var3_10].y == 0) {
                                    var1_3.var_a_a_a_g_arr_a[var3_10].a(3, var1_3.var_boolean_d);
                                    var1_3.var_a_a_a_g_arr_a[var3_10].r = 1;
                                    var1_3.var_a_a_a_g_arr_a[var3_10].y = 1;
                                    if (var1_3.var_byte_c == 8) {
                                        var1_3.var_a_a_a_g_arr_b[var3_10].var_boolean_b = false;
                                    }
                                    var2_5 = 0;
                                } else if (var1_3.var_a_a_a_g_arr_a[var3_10].y == 1) {
                                    var1_3.var_a_a_a_g_arr_a[var3_10].a(0, var1_3.var_boolean_d);
                                    var1_3.var_a_a_a_g_arr_a[var3_10].r = -1;
                                    var1_3.var_a_a_a_g_arr_a[var3_10].y = 2;
                                    var2_5 = 0;
                                }
                            }
                            if (var1_3.var_byte_c != 8 || var1_3.var_a_a_a_g_arr_b[var3_10] == null || !var1_3.var_a_a_a_g_arr_b[var3_10].var_boolean_b) continue;
                            var4_19 = a.a.a.g.a(var1_3.var_aeii_g_a.var_a_a_a_g_o, aeii.h.var_byte_arr_a[var1_3.var_byte_g] * aeii.c.int_a(1, 4), aeii.c.int_a(-2, 3), 0, 1, 50 * aeii.c.int_a(4), (byte)0);
                            var4_19.b(var1_3.var_a_a_a_g_arr_a[var3_10].m + var1_3.a(var4_19, var1_3.var_a_a_a_g_a.o), var1_3.var_a_a_a_g_arr_b[var3_10].n + aeii.c.int_a(30 - var1_3.var_a_a_a_g_arr_b[var3_10].p) - 15);
                            var4_19.d = true;
                            var1_3.var_aeii_g_a.a(var4_19);
                        }
                        if (var2_5 != 0) {
                            var1_3.var_int_a = var1_3.var_aeii_a_a.var_byte_d == 9 ? 6 : 4;
                            var1_3.m = 400;
                            if (var1_3.var_aeii_a_a.var_byte_d == 8) {
                                var1_3.m = 0;
                            }
                            var1_3.var_long_b = var1_3.var_aeii_g_a.var_long_a;
                            var1_3.l = 0;
                            break;
                        }
                    }
                }
lbl365:
                // 10 sources

                default: {
                    break;
                }
                case 4: {
                    if (var1_3.l == 0) {
                        if (var1_3.var_aeii_g_a.var_long_a - var1_3.var_long_b >= (long)var1_3.m) {
                            var1_3.var_aeii_h_a.c();
                            if (var1_3.var_byte_c != 1) {
                                var1_3.var_aeii_g_a.void_a(200);
                            }
                            aeii.c.d(200);
                            aeii.c.b(14, 1);
                            if (var1_3.var_a_a_a_g_c != null) {
                                var1_3.n = aeii.a.var_byte_arr_arr_arr_a[var1_3.var_byte_c].length;
                            }
                            var1_3.l = 1;
                        }
                    } else if (var1_3.l == 1) {
                        if (--var1_3.n < 0) {
                            var1_3.var_int_a = 7;
                            var1_3.var_long_b = var1_3.var_aeii_g_a.var_long_a;
                            break;
                        }
                        var5_22 = var1_3.var_byte_c != 3 && var1_3.var_byte_c != 2 && var1_3.var_byte_c != 1 ? a.a.a.g.a(var1_3.var_a_a_a_g_c, 0, 0, 0, -1, 0, (byte)0) : a.a.a.g.a(var1_3.var_a_a_a_g_c, 0, 0, 0, 1, 0, (byte)0);
                        if (var1_3.var_byte_c == 2 || var1_3.var_byte_c == 1) {
                            var5_22.c = false;
                        }
                        var4_20 = aeii.c.int_a(var1_3.var_aeii_g_a.var_int_a / 2 - var5_22.o);
                        var3_11 = 0;
                        if (var1_3.var_aeii_h_a.var_aeii_d_a != null) {
                            var3_11 = 0 + (var1_3.var_aeii_h_a.var_aeii_d_a.b - var5_22.p);
                        }
                        var2_5 = (var1_3.var_aeii_g_a.var_int_o - var3_11) * ((var1_3.n << 1) + 1) / (aeii.a.var_byte_arr_arr_arr_a[var1_3.var_byte_c].length << 1) - var5_22.p / 2 + var3_11;
                        if (var1_3.var_byte_g == 0) {
                            var4_20 += var1_3.var_aeii_g_a.var_int_c;
                        }
                        if (var1_3.var_byte_c == 7 || var1_3.var_byte_c == 6) {
                            var5_22.q = var2_5;
                            var2_5 = 0;
                        }
                        var5_22.b(var4_20, var2_5);
                        var1_3.var_aeii_g_a.a(var5_22);
                        for (var3_11 = 0; var3_11 < 3; ++var3_11) {
                            if (var1_3.var_byte_c != 7 && var1_3.var_byte_c != 6) {
                                var6_23 = a.a.a.g.a(var1_3.var_aeii_g_a.var_a_a_a_g_o, aeii.c.int_a(-1, 2), 0, -1, 1, 100, (byte)0);
                                var6_23.b(var4_20 + aeii.c.int_a(var5_22.o - var6_23.o), var2_5 + var5_22.p - var6_23.p + 1);
                            } else {
                                var6_23 = a.a.a.g.a(var1_3.var_aeii_g_a.var_a_a_a_g_e, aeii.c.int_a(-1, 2), 0, aeii.c.int_a(-2, 0), 1, aeii.c.int_a(4) * 50, (byte)0);
                                var6_23.b(var4_20 + aeii.c.int_a(var5_22.o - var6_23.o), var5_22.q + var5_22.p - var6_23.p + 1);
                                var6_23.q = -var5_22.p / 2;
                            }
                            var1_3.var_aeii_g_a.a(var6_23);
                        }
                    }
                    break;
                }
                case 6: {
                    var2_5 = 1;
                    for (var3_12 = 0; var3_12 < var1_3.var_a_a_a_g_arr_b.length; ++var3_12) {
                        if (var1_3.var_byte_c == 9 && aeii.c.int_a(2) == 0) {
                            var4_21 = a.a.a.g.a(var1_3.var_aeii_g_a.var_a_a_a_g_o, aeii.c.int_a(-2, 1), 0, -1, 1, 100, (byte)0);
                            var4_21.b(var1_3.var_a_a_a_g_arr_b[var3_12].m + var1_3.a(var1_3.var_a_a_a_g_arr_b[var3_12], 0), var1_3.var_a_a_a_g_arr_b[var3_12].n + var1_3.var_a_a_a_g_arr_b[var3_12].p - var4_21.p);
                            var4_21.var_byte_l = var1_3.var_a_a_a_g_arr_b[var3_12].y == 1 ? var1_3.var_aeii_h_a.var_byte_g : var1_3.var_byte_g;
                            var1_3.var_aeii_g_a.a(var4_21);
                        }
                        if (var1_3.var_byte_c != 9) continue;
                        if (var1_3.var_byte_g != 0) ** GOTO lbl433
                        if (var1_3.var_a_a_a_g_arr_b[var3_12].m < var1_3.var_aeii_g_a.var_int_a) ** GOTO lbl431
                        if (var1_3.var_a_a_a_g_arr_b[var3_12].y == 0) {
                            var1_3.var_a_a_a_g_arr_b[var3_12].b(var1_3.var_aeii_g_a.var_int_c - var1_3.var_a_a_a_g_arr_b[var3_12].o, var1_3.var_a_a_a_g_arr_b[var3_12].n);
                            var1_3.var_a_a_a_g_arr_b[var3_12].var_byte_l = var1_3.var_aeii_h_a.var_byte_g;
                            var1_3.var_a_a_a_g_arr_b[var3_12].y = 1;
                        } else {
                            if (var1_3.var_a_a_a_g_arr_b[var3_12].y != 1) continue;
                            var1_3.var_aeii_g_a.b(var1_3.var_a_a_a_g_arr_b[var3_12]);
                            var1_3.var_a_a_a_g_arr_b[var3_12].y = 2;
                            continue;
lbl431:
                            // 1 sources

                            var2_5 = 0;
                            continue;
lbl433:
                            // 1 sources

                            if (var1_3.var_byte_g != 1) continue;
                            if (var1_3.var_a_a_a_g_arr_b[var3_12].m + var1_3.var_a_a_a_g_arr_b[var3_12].o < 0) {
                                if (var1_3.var_a_a_a_g_arr_b[var3_12].y == 0) {
                                    var1_3.var_a_a_a_g_arr_b[var3_12].b(var1_3.var_aeii_g_a.var_int_c, var1_3.var_a_a_a_g_arr_b[var3_12].n);
                                    var1_3.var_a_a_a_g_arr_b[var3_12].var_byte_l = var1_3.var_aeii_h_a.var_byte_g;
                                    var1_3.var_a_a_a_g_arr_b[var3_12].y = 1;
                                } else {
                                    if (var1_3.var_a_a_a_g_arr_b[var3_12].y != 1) continue;
                                    var1_3.var_aeii_g_a.b(var1_3.var_a_a_a_g_arr_b[var3_12]);
                                    var1_3.var_a_a_a_g_arr_b[var3_12].y = 2;
                                    continue;
                                }
                            }
                        }
                        var2_5 = 0;
                    }
                    if (var2_5 != 0) {
                        var1_3.l = 0;
                        var1_3.var_int_a = 4;
                        var1_3.var_long_b = var1_3.var_aeii_g_a.var_long_a;
                    }
                    break;
                }
                case 7: {
                    if (var1_3.var_aeii_g_a.var_long_a - var1_3.var_long_b < 1000L) break;
                    var1_3.var_int_a = 0;
                    var1_3.var_boolean_b = true;
                }
            }
        }
        if (this.var_byte_c == 6) {
            for (var1_4 = 0; var1_4 < this.var_a_a_a_g_arr_a.length; ++var1_4) {
                if (!this.var_a_a_a_g_arr_a[var1_4].e) continue;
                this.var_a_a_a_g_arr_a[var1_4].q = -6 + 4 * aeii.f.short_a(this.var_int_arr_a[var1_4]) >> 10;
                this.var_int_arr_a[var1_4] = (this.var_int_arr_a[var1_4] + 10) % 360;
            }
        }
    }

    private void c() {
        int n;
        int n2;
        a.a.a.g g2;
        this.var_boolean_c = true;
        this.i = this.var_byte_e - this.var_byte_b;
        this.var_byte_f = this.var_byte_b;
        for (int i = 0; i < this.i; ++i) {
            a.a.a.g g3;
            this.var_aeii_g_a.b(this.var_a_a_a_g_arr_a[i]);
            g2 = a.a.a.g.a(this.var_aeii_g_a.var_a_a_a_g_g, 0, 0, 0, 1, 0, (byte)0);
            g2.b(this.var_a_a_a_g_arr_a[i].m + (this.var_a_a_a_g_arr_a[i].o - g2.o) / 2, (int)this.var_aeii_g_a.var_byte_arr_k);
            g2.q = this.var_a_a_a_g_arr_a[i].n + (this.var_a_a_a_g_arr_a[i].p - g2.p) / 2 - this.var_aeii_g_a.var_byte_arr_k;
            this.var_aeii_g_a.a(g2);
            for (n2 = 0; n2 < 3; ++n2) {
                g3 = a.a.a.g.a(this.var_aeii_g_a.var_a_a_a_g_o, n2 + -1, 0, aeii.c.int_a(-4, -1), 1, aeii.c.int_a(4) * 50, (byte)0);
                g3.b(this.var_a_a_a_g_arr_a[i].m + (this.var_a_a_a_g_arr_a[i].o - g3.o) / 2, this.var_a_a_a_g_arr_a[i].n + this.var_a_a_a_g_arr_a[i].p - g3.p + 3);
                this.var_aeii_g_a.a(g3);
            }
            g3 = a.a.a.g.a(this.var_aeii_g_a.var_a_a_a_g_e, 0, 0, -1, 1, 200, (byte)0);
            g3.b(this.var_a_a_a_g_arr_a[i].m + (this.var_a_a_a_g_arr_a[i].o - g3.o) / 2, this.var_a_a_a_g_arr_a[i].n + this.var_a_a_a_g_arr_a[i].p - g3.p + 3);
            this.var_aeii_g_a.a(g3);
        }
        a.a.a.g[] gArray = new a.a.a.g[this.var_byte_f];
        System.arraycopy(this.var_a_a_a_g_arr_a, this.i, gArray, 0, this.var_byte_f);
        this.var_a_a_a_g_arr_a = gArray;
        g2 = a.a.a.g.a("" + (this.var_byte_a - this.var_byte_d), 0, -4, (byte)1);
        if (this.var_byte_b == 1) {
            n = this.var_a_a_a_g_arr_a[0].m + this.var_a_a_a_g_arr_a[0].o / 2;
            n2 = this.var_a_a_a_g_arr_a[0].n + this.var_a_a_a_g_arr_a[0].p + 1;
        } else {
            n = this.var_aeii_g_a.var_int_c / 2;
            if (this.var_byte_g == 1) {
                n += this.var_aeii_g_a.var_int_c;
            }
            n2 = (this.var_aeii_g_a.var_int_o + this.var_int_e) / 2;
        }
        g2.b(n, n2);
        g2.d = true;
        this.var_aeii_g_a.a(g2);
    }

    public final void a(Graphics graphics, int n, int n2) {
        int n3;
        int n4;
        graphics.translate(n, n2);
        int n5 = 0;
        int n6 = this.var_int_f;
        for (n4 = 0; n4 < n6; ++n4) {
            int n7 = this.var_int_e + n2;
            int n8 = this.var_int_g;
            for (n3 = 0; n3 < n8; ++n3) {
                this.var_aeii_d_arr_a[this.var_byte_arr_arr_a[n4][n3]].a(graphics, n5, n7);
                n7 += 24;
            }
            n5 += 24;
        }
        if (this.var_aeii_d_a != null) {
            n4 = this.var_aeii_d_a.var_int_a;
            n5 = 0;
            n3 = (this.var_aeii_g_a.var_int_c + n4 - 1) / n4;
            for (n6 = 0; n6 < n3; ++n6) {
                this.var_aeii_d_a.a(graphics, n5, 0);
                n5 += n4;
            }
        }
        graphics.translate(-n, -n2);
    }

    public final void a(Graphics graphics) {
        reference var2_2 = this.var_aeii_g_a.var_byte_arr_k - aeii.g.var_byte_a / 2;
        aeii.c.a(graphics, this.h + "/" + 100, this.var_aeii_g_a.var_int_c / 2, (int)var2_2, 1, 3);
    }

    public final void b(Graphics graphics) {
        graphics.setColor(0x404040);
        for (int i = 0; i < this.var_byte_f; ++i) {
            a.a.a.g g2 = this.var_a_a_a_g_arr_a[i];
            if (this.var_byte_c != 0 && this.var_byte_c != 4 && this.var_byte_c != 5 && this.var_byte_c != 6) continue;
            graphics.fillArc(g2.m, g2.n + (g2.p << 2) / 5, g2.o, g2.p / 4, 0, 360);
        }
    }

    static {
        var_java_lang_String_arr_a = new String[]{"road", "grass", "woods", "hill", "mountain", "water", "bridge", "town", "town", "town"};
        var_java_lang_String_arr_b = new String[]{"road", "grass", "grass", "grass", "mountain", "water", "bridge", "town", "town", "town"};
        var_java_lang_String_arr_c = new String[]{"soldier", "archer", "lizard", "wizard", "wisp", "spider", "golem", "catapult", "wyvern", "king", "skeleton"};
        byte[] byArray = new byte[]{18, -18};
        var_byte_arr_a = new byte[]{3, -3};
    }
}

