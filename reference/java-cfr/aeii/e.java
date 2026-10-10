/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Font
 *  javax.microedition.lcdui.Graphics
 */
package aeii;

import aeii.a;
import aeii.c;
import aeii.d;
import aeii.f;
import aeii.g;
import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public final class e
extends f {
    private boolean[] var_boolean_arr_a = new boolean[]{false, false};
    public int var_int_a = 0xCECECE;
    private static int n = aeii.c.var_int_e <= 143 ? 1 : 2;
    private static int o = (n << 1) + 1;
    private byte var_byte_b = (byte)2;
    private short var_short_a = (short)3;
    public static g var_aeii_g_a;
    public String[] var_java_lang_String_arr_a;
    private d[] var_aeii_d_arr_a;
    private int p;
    private int q;
    public int var_int_b;
    public int var_int_c;
    private int r;
    private Font var_javax_microedition_lcdui_Font_a = aeii.c.var_javax_microedition_lcdui_Font_b;
    public int var_int_d;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    public byte var_byte_a;
    private int x;
    private boolean var_boolean_c = false;
    private boolean var_boolean_d = false;
    public a[] var_aeii_a_arr_a;
    private int y;
    private int z;
    private byte var_byte_c = (byte)-1;
    private int A;
    private int B;
    private int C;
    private int D;
    private String[] var_java_lang_String_arr_b;
    public int var_int_e = -1;
    private boolean var_boolean_e = true;
    private int E;
    public a var_aeii_a_a;
    private boolean var_boolean_f = true;
    public f var_aeii_f_a;
    private int F;
    private a.a.a.g[] var_a_a_a_g_arr_a;
    private int G;
    private boolean var_boolean_g = false;
    private Vector var_java_util_Vector_a;
    public int var_int_f = -1;
    public boolean var_boolean_a;
    public boolean var_boolean_b;
    private int H;
    private int I;
    private int J;
    private int K;
    private byte[][] var_byte_arr_arr_a;
    private Vector var_java_util_Vector_b;
    private int L;
    private int M;
    public int var_int_g = 2370117;
    public int h = 2370117;
    public d var_aeii_d_a;
    private e var_aeii_e_a;
    public int[] var_int_arr_a;
    private int N;
    private int O;
    private int[] var_int_arr_b;
    public int i;
    private int P = -1;
    private short[] var_short_arr_a;
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
    private byte var_byte_d;
    private a.a.a.g var_a_a_a_g_a;

    public final void void_a() {
        this.X = 0;
        if (this.var_a_a_a_g_arr_a != null) {
            this.e();
        }
        this.G = 4;
        this.var_boolean_c = true;
        this.var_boolean_d = true;
        if (var_aeii_g_a != null) {
            var_aeii_g_a.void_a();
        }
        if (this.var_aeii_e_a != null) {
            this.var_aeii_e_a.var_boolean_c = true;
        }
        if (this.var_byte_a == 15) {
            for (int i = 0; i < this.var_java_util_Vector_a.size(); ++i) {
                e e2 = (e)this.var_java_util_Vector_a.elementAt(i);
                e2.void_a();
                e2.var_boolean_d = false;
            }
        }
    }

    public final void a(byte by, boolean bl) {
        this.var_boolean_arr_a[by] = bl;
    }

    public final void a(f f2) {
        this.var_aeii_f_a = f2;
        this.var_boolean_arr_a[1] = f2 != null;
    }

    public e(byte by, int n) {
        this.var_byte_a = by;
        this.x = n;
        if (by == 15) {
            this.i = aeii.e.var_aeii_g_a.var_int_b - aeii.e.var_aeii_g_a.var_a_a_a_g_c.p;
            this.var_boolean_e = true;
        } else if (by != 0 && by != 11) {
            if (by == 3) {
                this.var_a_a_a_g_a = aeii.e.var_aeii_g_a.var_a_a_a_g_j;
                this.d();
                this.var_boolean_e = false;
                this.var_boolean_a = true;
                this.y = aeii.c.var_int_b - aeii.c.var_int_a;
                this.var_int_b = aeii.e.var_aeii_g_a.var_int_a;
                this.var_int_c = aeii.e.var_aeii_g_a.var_a_a_a_g_j.p + o;
                if ((n & 2) == 0) {
                    this.var_int_c += 5;
                }
                this.var_aeii_a_arr_a = aeii.a.aeii_a_arr_a(aeii.e.var_aeii_g_a.var_byte_b);
                this.s = this.var_aeii_a_arr_a.length;
                by = (byte)(this.var_int_b - (aeii.e.var_aeii_g_a.var_a_a_a_g_a.o << 1));
                if ((n & 4) == 0) {
                    by = (byte)(by - 8);
                }
                if ((n & 8) == 0) {
                    by = (byte)(by - 8);
                }
                this.B = by / (aeii.e.var_aeii_g_a.var_a_a_a_g_j.o + 3);
                if (this.B > this.s) {
                    this.B = this.s;
                }
                this.r = by / this.B;
                this.F = (by - this.r * this.B) / 2;
                this.var_byte_b = (byte)2;
            } else if (by != 2 && by != 5) {
                if (by != 7 && by == 8) {
                    this.var_short_a = (short)8;
                    this.var_boolean_arr_a[0] = true;
                }
            } else {
                this.var_boolean_e = false;
                this.var_int_c = 5 + aeii.e.n + 24 + o + (aeii.e.var_aeii_g_a.var_a_a_a_g_k.p << 1) + aeii.e.n + aeii.e.n + 1;
                if (by == 5) {
                    this.var_int_c += o + aeii.c.var_int_a;
                    this.var_aeii_a_a = var_aeii_g_a.aeii_a_a(aeii.e.var_aeii_g_a.var_int_h, aeii.e.var_aeii_g_a.var_int_i, (byte)0);
                    this.var_int_d = this.var_aeii_a_a.var_byte_d;
                    this.var_int_b = aeii.e.var_aeii_g_a.var_int_a;
                } else {
                    this.var_int_b = aeii.e.var_aeii_g_a.var_int_a;
                }
            }
        } else {
            this.var_boolean_arr_a[0] = true;
            this.var_boolean_arr_a[1] = true;
        }
        this.var_boolean_c = true;
    }

    public final e aeii_e_a(String string) {
        this.var_aeii_e_a = new e(10, 0);
        this.var_aeii_e_a.a(null, string, aeii.e.var_aeii_g_a.var_int_a, -1);
        return this.var_aeii_e_a;
    }

    public final void a(e e2, int n, int n2, int n3) {
        int n4;
        if (this.var_java_util_Vector_a == null) {
            this.var_java_util_Vector_a = new Vector();
        }
        if (this.var_int_arr_b == null) {
            this.var_int_arr_b = new int[5];
            for (n4 = 0; n4 < 5; ++n4) {
                this.var_int_arr_b[n4] = this.i;
                if (n4 <= 0) continue;
                int n5 = n4;
                this.var_int_arr_b[n5] = this.var_int_arr_b[n5] - aeii.e.var_aeii_g_a.var_a_a_a_g_c.p;
            }
        }
        e2.a(n, n2, n3);
        n4 = e2.q;
        for (n = 0; n < 5; ++n) {
            if (n4 < this.var_int_arr_b[n]) {
                if (n4 + e2.var_int_c <= this.var_int_arr_b[n]) break;
                this.var_int_arr_b[n] = n4;
                if (n + 1 <= this.O) break;
                this.O = n + 1;
                break;
            }
            n4 -= this.var_int_arr_b[n];
        }
        n3 = 0;
        n2 = 0;
        e e3 = e2;
        e3.var_boolean_arr_a[n2] = n3;
        n3 = 0;
        n2 = 1;
        e3 = e2;
        e3.var_boolean_arr_a[n2] = n3;
        this.var_java_util_Vector_a.addElement(e2);
    }

    public final void a(int n, int n2, byte[][] byArray, Vector vector) {
        this.x = 15;
        this.var_byte_arr_arr_a = byArray;
        this.var_java_util_Vector_b = vector;
        this.var_short_a = (short)8;
        this.var_boolean_arr_a[0] = true;
        this.var_boolean_e = true;
        this.L = byArray.length;
        this.M = byArray[0].length;
        this.var_int_b = this.L * aeii.e.var_aeii_g_a.var_aeii_d_arr_b[0].var_int_a + 8;
        this.var_int_c = this.M * aeii.e.var_aeii_g_a.var_aeii_d_arr_b[0].b + 8;
        if (this.var_int_b > n) {
            int n3 = aeii.e.var_aeii_g_a.var_aeii_d_arr_b[0].var_int_a;
            this.J = (n - 8) / n3;
            this.var_int_b = n3 * this.J + 8;
        } else {
            this.J = this.L;
        }
        if (this.var_int_c > n2) {
            int n4 = aeii.e.var_aeii_g_a.var_aeii_d_arr_b[0].b;
            this.K = (n2 - 8) / n4;
            this.var_int_c = n4 * this.K + 8;
        } else {
            this.K = this.M;
        }
        this.var_byte_a = (byte)8;
    }

    public final void a(int n, int n2, int n3) {
        this.p = n;
        this.q = n2;
        if ((n3 & 1) != 0) {
            this.p -= this.var_int_b >> 1;
        } else if ((n3 & 8) != 0) {
            this.p -= this.var_int_b;
        }
        if ((n3 & 2) != 0) {
            this.q -= this.var_int_c >> 1;
        } else if ((n3 & 0x20) != 0) {
            this.q -= this.var_int_c;
        }
        this.v = this.p;
        this.w = this.q;
    }

    public final void a(String string, int n, int n2, byte by) {
        this.var_byte_c = by;
        if (by == -1) {
            this.x = 14;
        } else {
            this.C = aeii.e.var_aeii_g_a.var_a_a_a_g_i.o - 8;
        }
        by = (byte)(n - this.C - 16);
        this.var_java_lang_String_arr_a = aeii.f.a(string, by, aeii.c.var_javax_microedition_lcdui_Font_b);
        this.a(null, this.var_java_lang_String_arr_a, n, n2);
        this.var_boolean_g = false;
        this.var_byte_a = (byte)7;
    }

    private void a(String string, String[] stringArray, int n, int n2) {
        this.var_boolean_e = false;
        this.var_int_b = n;
        this.var_int_c = n2;
        this.s = stringArray.length;
        this.var_int_d = 0;
        this.A = 0;
        this.D = 0;
        this.var_boolean_g = false;
        n = n - this.C - 16;
        if (string != null) {
            this.var_java_lang_String_arr_b = aeii.f.a(string, n, aeii.c.var_javax_microedition_lcdui_Font_b);
        }
        this.var_java_lang_String_arr_a = stringArray;
        this.r = aeii.c.var_int_b;
        this.y = aeii.c.var_int_b - aeii.c.var_int_a;
        this.z = this.y / 2;
        int n3 = n2 <= 0 ? this.k : n2;
        if ((this.x & 1) == 0) {
            n3 -= 5;
        }
        if ((this.x & 2) == 0) {
            n3 -= 5;
        }
        if (string != null) {
            n3 -= this.var_java_lang_String_arr_b.length * this.r;
        }
        this.B = (n3 - 2) / this.r;
        if (this.B > this.var_java_lang_String_arr_a.length) {
            this.B = this.var_java_lang_String_arr_a.length;
        } else if (this.B < this.var_java_lang_String_arr_a.length) {
            this.var_boolean_g = true;
        }
        if (n2 < 0) {
            if (this.var_java_lang_String_arr_b != null) {
                this.var_int_c = this.var_java_lang_String_arr_b.length * this.r;
            }
            this.var_int_c += this.B * this.r;
            if ((this.x & 1) == 0) {
                this.var_int_c += 5;
            }
            if ((this.x & 2) == 0) {
                this.var_int_c += 5;
            }
        } else {
            this.F = (n3 - this.B * this.r) / 2;
        }
        this.var_byte_a = (byte)10;
        this.var_byte_b = (byte)2;
    }

    public final void a(String string, String string2, int n, int n2) {
        int n3 = n - this.C;
        if ((this.x & 4) == 0) {
            n3 -= 8;
        }
        if ((this.x & 8) == 0) {
            n3 -= 8;
        }
        this.var_java_lang_String_arr_a = aeii.f.a(string2, n3, aeii.c.var_javax_microedition_lcdui_Font_b);
        this.a(string, this.var_java_lang_String_arr_a, n, n2);
        if (this.var_boolean_g) {
            this.var_java_lang_String_arr_a = aeii.f.a(string2, n3 -= aeii.e.var_aeii_g_a.var_a_a_a_g_b.o, aeii.c.var_javax_microedition_lcdui_Font_b);
            this.a(string, this.var_java_lang_String_arr_a, n, n2);
        }
    }

    private final void d() {
        this.var_a_a_a_g_arr_a = new a.a.a.g[3];
        for (int i = 0; i < this.var_a_a_a_g_arr_a.length; ++i) {
            this.var_a_a_a_g_arr_a[i] = new a.a.a.g(aeii.e.var_aeii_g_a.var_a_a_a_g_h);
        }
        this.e();
    }

    private void e() {
        for (int i = 0; i < this.var_a_a_a_g_arr_a.length; ++i) {
            this.var_a_a_a_g_arr_a[i].var_boolean_a = true;
            this.var_a_a_a_g_arr_a[i].b(aeii.c.int_a(this.var_a_a_a_g_a.o), aeii.c.int_a(this.var_a_a_a_g_a.p));
            this.var_a_a_a_g_arr_a[i].c(aeii.c.int_a(this.var_a_a_a_g_arr_a[i].int_d()));
        }
    }

    public final void a(String[] stringArray, d[] dArray, int n, int n2, int n3) {
        this.x = 15;
        this.var_java_lang_String_arr_a = stringArray;
        this.var_aeii_d_arr_a = dArray;
        this.s = this.var_java_lang_String_arr_a.length;
        this.var_int_b = 0;
        for (int i = 0; i < this.s; ++i) {
            int n4 = aeii.c.var_javax_microedition_lcdui_Font_b.stringWidth(this.var_java_lang_String_arr_a[i]);
            if (n4 <= this.var_int_b) continue;
            this.var_int_b = n4;
        }
        this.y = aeii.c.var_int_b - aeii.c.var_int_a;
        this.z = this.y / 2;
        this.Y = aeii.e.var_aeii_g_a.var_a_a_a_g_k.o;
        this.r = this.Y + this.y;
        this.var_int_b += this.s * this.r;
        this.var_int_b += 32;
        if (this.var_int_b > this.j) {
            this.var_int_b = this.j;
        }
        this.var_int_c = this.Y;
        this.a(n, n2, n3);
        this.var_byte_a = (byte)13;
        this.var_byte_b = (byte)2;
    }

    public final void a(String[] stringArray, int n, int n2) {
        this.var_java_lang_String_arr_a = stringArray;
        this.s = this.var_java_lang_String_arr_a.length;
        this.var_boolean_a = true;
        this.var_boolean_e = false;
        this.var_int_c = n2;
        int n3 = 0;
        for (n2 = 0; n2 < this.var_java_lang_String_arr_a.length; ++n2) {
            int n4 = aeii.c.var_javax_microedition_lcdui_Font_b.stringWidth(this.var_java_lang_String_arr_a[n2]);
            if (n4 <= n3) continue;
            n3 = n4;
        }
        this.var_int_b = n3 + 16 + (aeii.e.var_aeii_g_a.var_a_a_a_g_a.o << 1);
        if (this.var_int_b < n) {
            this.var_int_b = n;
        }
        if (this.var_int_c < 0) {
            this.var_int_c = aeii.c.var_int_b;
            if (aeii.e.var_aeii_g_a.var_a_a_a_g_a.p > this.var_int_c) {
                this.var_int_c = aeii.e.var_aeii_g_a.var_a_a_a_g_a.p;
            }
            if ((this.x & 1) == 0) {
                this.var_int_c += 5;
            }
            if ((this.x & 2) == 0) {
                this.var_int_c += 5;
            }
        }
        this.var_byte_a = (byte)14;
        this.var_byte_b = (byte)2;
    }

    public final void a(String[] stringArray, int n, int n2, int n3, int n4, int n5, int n6) {
        this.var_java_lang_String_arr_a = stringArray;
        this.s = this.var_java_lang_String_arr_a.length;
        this.y = aeii.c.var_int_b - aeii.c.var_int_a;
        this.r = aeii.c.var_int_b;
        int n7 = 0;
        for (int i = 0; i < this.var_java_lang_String_arr_a.length; ++i) {
            int n8 = aeii.c.var_javax_microedition_lcdui_Font_b.stringWidth(this.var_java_lang_String_arr_a[i]);
            if (n8 <= n7) continue;
            n7 = n8;
        }
        this.var_int_b = n7 + 4 + 16;
        if (this.var_int_b > this.j) {
            this.var_int_b = this.j;
        } else if (this.var_int_b < n3) {
            if (n6 == 4) {
                this.P = (n3 - this.var_int_b) / 2;
            }
            this.var_int_b = n3;
        }
        this.var_int_c = this.r * this.var_java_lang_String_arr_a.length + this.y + 16;
        if (this.var_int_c > n4) {
            this.var_int_c = n4;
        }
        this.a(null, this.var_java_lang_String_arr_a, this.var_int_b, this.var_int_c);
        if (this.var_int_b < this.j && this.var_boolean_g) {
            this.var_int_b += aeii.e.var_aeii_g_a.var_a_a_a_g_b.o;
        }
        this.var_byte_a = (byte)11;
        this.a(n, n2, n5);
    }

    public final void a(String[] stringArray, d[] dArray, int n, int n2, int n3, int n4, byte by) {
        int n5;
        this.var_byte_d = 1;
        this.var_java_lang_String_arr_a = stringArray;
        this.var_aeii_d_arr_a = dArray;
        this.s = this.var_java_lang_String_arr_a.length;
        this.var_a_a_a_g_a = aeii.e.var_aeii_g_a.var_a_a_a_g_j;
        this.y = aeii.c.var_int_b - aeii.c.var_int_a;
        this.x = 15;
        this.Y = this.var_a_a_a_g_a.o;
        this.Z = this.Y >> 1;
        this.d();
        this.var_short_arr_a = new short[this.s];
        this.V = 360 / this.s;
        this.T = this.U = this.V / 2;
        for (n5 = 0; n5 < this.s; ++n5) {
            this.var_short_arr_a[n5] = (short)(this.V * n5);
        }
        if (this.s == 1) {
            this.R = 0;
        } else if (n3 <= 0) {
            this.R = (this.var_a_a_a_g_a.o << 10) / (2 * aeii.f.short_a(45));
            this.S = this.R + this.var_a_a_a_g_a.o / 2;
            n3 = (this.S << 1) + aeii.c.var_int_b + 2;
        } else {
            n5 = (this.var_a_a_a_g_a.o << 10) / aeii.f.short_a(this.V / 2) + this.var_a_a_a_g_a.p / 2;
            this.S = (n3 - aeii.c.var_int_b) / 2 - 2;
            if (this.S > n5) {
                this.S = n5;
            }
            this.R = this.S - this.var_a_a_a_g_a.p / 2;
        }
        this.Q = 0;
        this.var_int_b = this.S << 1;
        this.var_int_c = n3;
        this.var_byte_b = 0;
        this.a(n, n2, 3);
    }

    private int int_a(int n) {
        int n2 = this.var_int_f;
        int n3 = this.var_int_f;
        int n4 = this.var_java_util_Vector_a.size();
        do {
            if ((n2 += n) < 0) {
                n2 = n4 - 1;
                continue;
            }
            if (n2 < this.var_java_util_Vector_a.size()) continue;
            if (n3 < 0) {
                return -1;
            }
            n2 = 0;
        } while (!((e)this.var_java_util_Vector_a.elementAt((int)n2)).var_boolean_a);
        return n2;
    }

    public final void b() {
        this.a(true);
    }

    private void a(boolean n) {
        if (this.var_byte_b != 3) {
            if (this.var_byte_a == 10 && this.var_int_e > 0 && this.var_int_e <= 250) {
                ++this.G;
                this.var_boolean_c = true;
            } else if (this.G > 0) {
                --this.G;
                this.var_boolean_c = true;
            }
            if (n != 0 && this.var_byte_b == 2) {
                int n2;
                n = 0;
                if (this.var_boolean_arr_a[0] && (aeii.f.var_aeii_c_a.boolean_a(aeii.g.var_int_d) || aeii.f.var_aeii_c_a.boolean_a(16))) {
                    n = 1;
                    aeii.f.var_aeii_c_a.void_b(aeii.g.var_int_d);
                    aeii.f.var_aeii_c_a.void_b(16);
                }
                if (this.var_byte_a == 0 || this.var_byte_a == 3) {
                    for (n2 = 0; n2 < this.var_a_a_a_g_arr_a.length; ++n2) {
                        if (this.var_a_a_a_g_arr_a[n2].var_int_l == this.var_a_a_a_g_arr_a[n2].int_d() - 1) {
                            if (this.X == 0) {
                                this.var_a_a_a_g_arr_a[n2].b(aeii.c.int_a(this.var_a_a_a_g_a.o - this.var_a_a_a_g_arr_a[n2].o), aeii.c.int_a(this.var_a_a_a_g_a.p - this.var_a_a_a_g_arr_a[n2].p));
                            } else {
                                this.var_a_a_a_g_arr_a[n2].var_boolean_a = false;
                            }
                        }
                        this.var_a_a_a_g_arr_a[n2].void_e();
                    }
                    this.var_boolean_c = true;
                }
                if (this.var_byte_a == 15) {
                    if (!this.var_boolean_b && this.var_int_f >= 0) {
                        int n3;
                        if (aeii.f.var_aeii_c_a.boolean_a(1)) {
                            ((e)this.var_java_util_Vector_a.elementAt((int)this.var_int_f)).var_boolean_c = true;
                            this.var_int_f = this.int_a(-1);
                            e e2 = (e)this.var_java_util_Vector_a.elementAt(this.var_int_f);
                            ((e)this.var_java_util_Vector_a.elementAt(this.var_int_f)).var_boolean_c = true;
                            n3 = e2.q;
                            for (n2 = 0; n2 < 5; ++n2) {
                                if (n3 < this.var_int_arr_b[n2]) {
                                    if (this.N == n2) break;
                                    this.void_a();
                                    this.N = n2;
                                    break;
                                }
                                n3 -= this.var_int_arr_b[n2];
                            }
                        } else if (aeii.f.var_aeii_c_a.boolean_a(2)) {
                            ((e)this.var_java_util_Vector_a.elementAt((int)this.var_int_f)).var_boolean_c = true;
                            this.var_int_f = this.int_a(1);
                            e e3 = (e)this.var_java_util_Vector_a.elementAt(this.var_int_f);
                            ((e)this.var_java_util_Vector_a.elementAt(this.var_int_f)).var_boolean_c = true;
                            n3 = e3.q;
                            for (n2 = 0; n2 < 5; ++n2) {
                                if (n3 < this.var_int_arr_b[n2]) {
                                    if (this.N == n2) break;
                                    this.void_a();
                                    this.N = n2;
                                    break;
                                }
                                n3 -= this.var_int_arr_b[n2];
                            }
                        }
                    }
                    if (n != 0) {
                        var_aeii_g_a.a(this, this.var_int_f, "", (byte)0);
                        return;
                    }
                    for (n2 = 0; n2 < this.var_java_util_Vector_a.size(); ++n2) {
                        boolean bl;
                        e e4;
                        e e5 = (e)this.var_java_util_Vector_a.elementAt(n2);
                        if (this.var_boolean_b) {
                            e4 = e5;
                            bl = true;
                        } else {
                            e4 = e5;
                            bl = n2 == this.var_int_f;
                        }
                        e4.a(bl);
                    }
                    this.var_boolean_c = true;
                } else if (this.var_byte_a == 0) {
                    if (this.var_byte_b == 2) {
                        this.var_boolean_c = true;
                        if (aeii.f.var_aeii_c_a.boolean_a(4)) {
                            this.X -= this.V;
                            this.W += this.V;
                            --this.var_int_d;
                            if (this.var_int_d < 0) {
                                this.var_int_d = this.s - 1;
                            }
                        } else if (aeii.f.var_aeii_c_a.boolean_a(8)) {
                            this.X += this.V;
                            this.W -= this.V;
                            if (this.W < 0) {
                                this.W += 360;
                            }
                            ++this.var_int_d;
                            if (this.var_int_d >= this.s) {
                                this.var_int_d = 0;
                            }
                        }
                        if (this.X != 0) {
                            n2 = -this.X / 2;
                            this.X = n2 == 0 ? 0 : (this.X += n2);
                            if (this.X == 0) {
                                this.e();
                            }
                        } else if (aeii.f.var_aeii_c_a.boolean_c(4)) {
                            this.X -= this.V;
                            this.W += this.V;
                            --this.var_int_d;
                            if (this.var_int_d < 0) {
                                this.var_int_d = this.s - 1;
                            }
                        } else if (aeii.f.var_aeii_c_a.boolean_c(8)) {
                            this.X += this.V;
                            this.W -= this.V;
                            if (this.W < 0) {
                                this.W += 360;
                            }
                            ++this.var_int_d;
                            if (this.var_int_d >= this.s) {
                                this.var_int_d = 0;
                            }
                        }
                        if (n != 0) {
                            var_aeii_g_a.a(this, this.var_int_d, this.var_java_lang_String_arr_a[this.var_int_d], (byte)0);
                            return;
                        }
                    }
                } else if (this.var_byte_a != 13 && this.var_byte_a != 14) {
                    if (this.var_byte_a == 3) {
                        if (this.D != 0) {
                            this.D = Math.abs(this.D) < 2 ? 0 : (this.D -= this.D / 2);
                            this.var_boolean_c = true;
                        }
                        if (n != 0) {
                            var_aeii_g_a.a(this, this.var_int_d, this.var_java_lang_String_arr_a[this.var_int_d], (byte)0);
                            return;
                        }
                        if (aeii.f.var_aeii_c_a.boolean_a(4)) {
                            if (this.var_int_d < this.A) {
                                this.var_int_d += this.s;
                            }
                            --this.var_int_d;
                            if (this.var_int_d < this.A) {
                                if (this.var_int_d < 0) {
                                    this.var_int_d += this.s;
                                }
                                this.A = this.var_int_d;
                                this.D = -this.r;
                            }
                            this.var_int_d %= this.s;
                            var_aeii_g_a.a(this, this.var_int_d, null, (byte)2);
                            this.e();
                            this.var_boolean_c = true;
                        } else if (aeii.f.var_aeii_c_a.boolean_a(8)) {
                            if (this.var_int_d < this.A) {
                                this.var_int_d += this.s;
                            }
                            ++this.var_int_d;
                            if (this.var_int_d >= this.A + this.B) {
                                this.D = this.r;
                                this.A = (this.A + 1) % this.s;
                            }
                            this.var_int_d %= this.s;
                            var_aeii_g_a.a(this, this.var_int_d, null, (byte)3);
                            this.e();
                            this.var_boolean_c = true;
                        }
                    } else if (this.var_byte_a != 10 && this.var_byte_a != 7 && this.var_byte_a != 11) {
                        if (this.var_byte_a == 8) {
                            if (aeii.f.var_aeii_c_a.boolean_a(1)) {
                                if (this.I > 0) {
                                    --this.I;
                                    this.var_boolean_c = true;
                                }
                            } else if (aeii.f.var_aeii_c_a.boolean_a(2) && this.I + this.K < this.M) {
                                ++this.I;
                                this.var_boolean_c = true;
                            }
                            if (aeii.f.var_aeii_c_a.boolean_a(4)) {
                                if (this.H > 0) {
                                    --this.H;
                                    this.var_boolean_c = true;
                                }
                            } else if (aeii.f.var_aeii_c_a.boolean_a(8) && this.H + this.J < this.L) {
                                ++this.H;
                                this.var_boolean_c = true;
                            }
                        }
                    } else {
                        if (this.var_int_e != -1) {
                            if (this.var_int_e > 0) {
                                this.var_int_e -= 50;
                            } else {
                                aeii.f.var_aeii_c_a.a(this.var_aeii_f_a);
                            }
                        }
                        if (this.D > 0) {
                            this.D -= this.r / 3 + 1;
                            if (this.D < 0) {
                                this.D = 0;
                            }
                            this.var_boolean_c = true;
                        } else if (this.D < 0) {
                            this.D += this.r / 3 + 1;
                            if (this.D > 0) {
                                this.D = 0;
                            }
                            this.var_boolean_c = true;
                        }
                        if (this.D == 0) {
                            if ((this.var_byte_a == 11 || this.var_byte_a == 10) && n != 0) {
                                var_aeii_g_a.a(this, this.var_int_d, this.var_java_lang_String_arr_a[this.var_int_d], (byte)0);
                                return;
                            }
                            if (this.var_byte_a != 7 && aeii.f.var_aeii_c_a.boolean_a(1)) {
                                if (this.var_byte_a == 11) {
                                    --this.var_int_d;
                                    if (this.var_int_d < 0) {
                                        this.var_int_d = this.s - 1;
                                        this.A = this.s - this.B;
                                        if (this.var_byte_a == 3) {
                                            this.A = this.var_int_d;
                                        }
                                    } else if (this.var_int_d < this.A) {
                                        this.D = -this.r;
                                        --this.A;
                                    }
                                    var_aeii_g_a.a(this, this.var_int_d, null, (byte)2);
                                    this.var_boolean_c = true;
                                } else if (this.A > 0) {
                                    this.D = -this.r;
                                    --this.A;
                                    this.var_boolean_c = true;
                                }
                                aeii.f.var_aeii_c_a.b();
                            }
                            if (aeii.f.var_aeii_c_a.boolean_a(2) || this.var_byte_a == 7 && aeii.f.var_aeii_c_a.boolean_a(2048)) {
                                if (this.var_byte_a == 11) {
                                    ++this.var_int_d;
                                    if (this.var_int_d >= this.s) {
                                        this.var_int_d = 0;
                                        this.A = 0;
                                    } else if (this.var_int_d >= this.A + this.B) {
                                        this.D = this.r;
                                        ++this.A;
                                    }
                                    var_aeii_g_a.a(this, this.var_int_d, null, (byte)3);
                                    this.var_boolean_c = true;
                                } else if (this.A + this.B < this.var_java_lang_String_arr_a.length) {
                                    this.D = this.r;
                                    ++this.A;
                                    this.var_boolean_c = true;
                                } else if (this.var_byte_a == 7) {
                                    var_aeii_g_a.a(this, 0, null, (byte)0);
                                    return;
                                }
                                aeii.f.var_aeii_c_a.b();
                            }
                        }
                    }
                } else if (aeii.f.var_aeii_c_a.boolean_a(4)) {
                    --this.var_int_d;
                    if (this.var_int_d < 0) {
                        this.var_int_d = this.s - 1;
                    }
                    if (this.var_byte_a == 14) {
                        var_aeii_g_a.a(this, this.var_int_d, null, (byte)2);
                    }
                    this.var_boolean_c = true;
                } else if (aeii.f.var_aeii_c_a.boolean_a(8)) {
                    ++this.var_int_d;
                    if (this.var_int_d >= this.s) {
                        this.var_int_d = 0;
                    }
                    if (this.var_byte_a == 14) {
                        var_aeii_g_a.a(this, this.var_int_d, null, (byte)2);
                    }
                    this.var_boolean_c = true;
                } else if (n != 0) {
                    var_aeii_g_a.a(this, this.var_int_d, this.var_java_lang_String_arr_a[this.var_int_d], (byte)0);
                    return;
                }
                if (this.var_byte_b == 2 && this.var_boolean_arr_a[1] && aeii.f.var_aeii_c_a.boolean_a(aeii.g.var_int_e)) {
                    aeii.f.var_aeii_c_a.void_b(aeii.g.var_int_e);
                    aeii.f.var_aeii_c_a.b();
                    if (this.var_aeii_f_a != null) {
                        aeii.f.var_aeii_c_a.a(this.var_aeii_f_a);
                    }
                    if (this.var_java_lang_String_arr_a != null) {
                        var_aeii_g_a.a(this, this.var_int_d, this.var_java_lang_String_arr_a[this.var_int_d], (byte)1);
                        return;
                    }
                    var_aeii_g_a.a(this, -1, null, (byte)1);
                    return;
                }
            }
            if (this.var_boolean_e && ++this.u >= this.var_short_a) {
                this.t = this.t == 0 ? 2 : 0;
                this.u = 0;
                this.var_boolean_c = true;
            }
            switch (this.var_byte_b) {
                case 0: {
                    if (this.var_byte_a == 0) {
                        if (this.Q < this.R) {
                            n = this.var_byte_d == 2 ? this.R / 2 : this.R / 5;
                            if (n <= 0) {
                                n = 1;
                            }
                            this.Q += n;
                            if (this.Q > this.R) {
                                this.Q = this.R;
                            }
                        } else {
                            this.T = Math.abs(360 - this.var_short_arr_a[0]) / 2;
                            if (this.T <= 0) {
                                this.T = 1;
                            } else if (this.T > this.U) {
                                this.T = this.U;
                            }
                        }
                        if (this.var_byte_d == 1) {
                            for (n = 0; n < this.var_short_arr_a.length; ++n) {
                                this.var_short_arr_a[n] = (short)((this.var_short_arr_a[n] + this.T) % 360);
                            }
                        }
                        if (aeii.f.var_aeii_c_a.boolean_a() || this.Q >= this.R && this.var_short_arr_a[0] == 0) {
                            this.Q = this.R;
                            for (n = 0; n < this.var_short_arr_a.length; ++n) {
                                this.var_short_arr_a[n] = (short)(this.V * n);
                            }
                            this.var_byte_b = (byte)2;
                            if (this.var_boolean_f) {
                                aeii.f.var_aeii_c_a.b();
                            }
                        }
                    } else if (this.var_byte_a == 13) {
                        this.v += (this.p - this.v) / 2;
                        ++this.E;
                        if (this.E == 2) {
                            this.var_byte_b = (byte)2;
                            this.v = this.p;
                        }
                    } else {
                        n = (this.p - this.v) / 4;
                        if (n <= 0) {
                            n = 1;
                        }
                        this.v += n;
                        if (this.v == this.p) {
                            this.var_byte_b = (byte)2;
                        }
                    }
                    this.var_boolean_c = true;
                    return;
                }
                case 1: {
                    this.var_byte_b = (byte)3;
                }
            }
        }
    }

    public static final void a(Graphics graphics, int n, int n2, int n3, int n4) {
        if (n4 <= 2) {
            graphics.fillRect(n, n2, n3, n4);
            return;
        }
        graphics.fillRect(n, n2 + 1, n3, n4 - 2);
        graphics.fillRect(n + 1, n2, n3 - 2, n4);
    }

    public final void a(Graphics graphics) {
        this.a(graphics, 0, 0, false);
    }

    private void a(Graphics graphics, int n, int n2, boolean n3) {
        if (this.var_byte_b != 3 && this.var_boolean_c) {
            int n4;
            int n5;
            int n6;
            int n7;
            this.var_boolean_c = false;
            if (aeii.f.var_aeii_c_a.var_aeii_f_a == this && this.var_boolean_d || this.var_byte_a == 0) {
                var_aeii_g_a.a(graphics);
            }
            this.var_boolean_d = false;
            graphics.setClip(0, 0, this.j, this.k);
            if (this.var_aeii_e_a != null) {
                this.var_aeii_e_a.a(graphics);
            }
            n = this.v;
            n2 = this.w + n2;
            if (this.var_byte_a != 0 && this.var_byte_a != 13) {
                aeii.e.a(graphics, n, n2, this.var_int_b, this.var_int_c, this.x, this.h, this.var_int_g, this.G, 5);
                graphics.setClip(0, 0, this.j, this.k);
            }
            int n8 = this.var_int_b;
            int n9 = this.var_int_c;
            if ((this.x & 1) == 0) {
                n9 -= 5;
                n2 += 5;
            }
            if ((this.x & 2) == 0) {
                n9 -= 5;
            }
            if ((this.x & 4) == 0) {
                n += 8;
                n8 -= 8;
            }
            if ((this.x & 8) == 0) {
                n8 -= 8;
            }
            graphics.translate(n, n2);
            graphics.setFont(this.var_javax_microedition_lcdui_Font_a);
            if (this.var_aeii_d_a != null && (this.var_java_lang_String_arr_a == null || this.var_javax_microedition_lcdui_Font_a.stringWidth(this.var_java_lang_String_arr_a[0]) < n8 - (this.var_aeii_d_a.var_int_a << 1))) {
                this.var_aeii_d_a.a(graphics, 0, n9 / 2, 6);
            }
            if (n3 != 0) {
                graphics.setColor(5594742);
                aeii.e.a(graphics, 0, 0, n8, n9);
            }
            if (this.var_byte_a == 0) {
                graphics.setColor(0xFFFFFF);
                n7 = this.W + this.X;
                for (n6 = this.var_short_arr_a.length - 1; n6 >= 0; --n6) {
                    n5 = (this.var_short_arr_a[n6] + n7) % 360;
                    if (n5 < 0) {
                        n5 += 360;
                    }
                    n3 = this.S + (aeii.f.short_a(n5) * this.Q >> 10);
                    n5 = aeii.c.var_int_b + this.S + 2 - (aeii.f.short_b(n5) * this.Q >> 10);
                    if (this.var_byte_b == 2 && n6 == this.var_int_d) {
                        this.var_a_a_a_g_a.a(graphics, 1, n3, n5, 3);
                        if (this.X == 0) {
                            for (n4 = 0; n4 < this.var_a_a_a_g_arr_a.length; ++n4) {
                                this.var_a_a_a_g_arr_a[n4].a(graphics, n3 - this.Z, n5 - this.Z, 20);
                            }
                        }
                    } else {
                        this.var_a_a_a_g_a.a(graphics, 0, n3, n5, 3);
                    }
                    if (this.var_java_lang_String_arr_a[n6] == null || this.var_aeii_d_arr_a == null || this.var_aeii_d_arr_a[n6] == null) continue;
                    this.var_aeii_d_arr_a[n6].a(graphics, n3, n5, 3);
                }
                if (this.var_byte_b == 2) {
                    for (n6 = 0; n6 < this.var_a_a_a_g_arr_a.length; ++n6) {
                        this.var_a_a_a_g_arr_a[n6].a(graphics, (this.var_int_b - this.Y) / 2, aeii.c.var_int_b, 3);
                    }
                }
            }
            switch (this.var_byte_a) {
                case 0: {
                    if (this.var_byte_b == 2) {
                        graphics.setColor(1645370);
                        if (this.var_byte_d == 2) {
                            int n10;
                            n9 = this.var_int_b;
                            if (this.var_java_lang_String_arr_a[this.var_int_d] != null && n9 < (n10 = aeii.c.var_javax_microedition_lcdui_Font_b.stringWidth(this.var_java_lang_String_arr_a[this.var_int_d]) + 2)) {
                                n9 = n10;
                            }
                            aeii.e.a(graphics, (this.var_int_b - n9) / 2, 1, n9, aeii.c.var_int_b);
                        } else {
                            aeii.e.a(graphics, 2 - this.p, 1, this.j - 4, aeii.c.var_int_b);
                        }
                        if (this.var_java_lang_String_arr_a[this.var_int_d] != null) {
                            graphics.setColor(0xFFFFFF);
                            aeii.c.b(graphics, this.var_java_lang_String_arr_a[this.var_int_d], this.S, (this.y >> 1) + 1, 17);
                        }
                    }
                }
                default: {
                    break;
                }
                case 2: 
                case 5: {
                    int n11;
                    int n12;
                    int n13;
                    String string;
                    graphics.setClip(0, 0, n8, n9);
                    n9 = aeii.e.n;
                    this.var_aeii_a_a.a(graphics, -this.var_aeii_a_a.m + n9, -this.var_aeii_a_a.n + n9);
                    n7 = n9 + this.var_aeii_a_a.p / 2;
                    graphics.setFont(aeii.c.var_javax_microedition_lcdui_Font_b);
                    graphics.setColor(this.var_int_a);
                    aeii.c.b(graphics, this.var_aeii_a_a.var_java_lang_String_a, n9 + this.var_aeii_a_a.o + n9, n7 - aeii.c.var_int_a / 2, 20);
                    if (this.var_byte_a == 2) {
                        string = "" + this.var_aeii_a_a.var_int_j;
                        aeii.e.var_aeii_g_a.var_a_a_a_g_m.a(graphics, 1, n8 - n9 - aeii.c.a((byte)1, string), n7, 10);
                    } else {
                        string = "" + this.var_aeii_a_a.var_int_h;
                    }
                    aeii.c.a(graphics, string, n8 - n9, n7, 1, 10);
                    this.y = aeii.c.var_int_b - aeii.c.var_int_a;
                    n5 = n9 + this.var_aeii_a_a.p + aeii.e.n;
                    graphics.setColor(this.var_int_a);
                    graphics.drawLine(n9, n5, n8 - n9 - n9, n5);
                    n5 += 1 + aeii.e.n;
                    if (this.var_byte_a == 5) {
                        n4 = aeii.c.var_int_a;
                        n7 = n5 + n4 / 2;
                        aeii.c.b(graphics, aeii.f.java_lang_String_a(97), n9, n5, 20);
                        n6 = aeii.c.var_javax_microedition_lcdui_Font_b.stringWidth(aeii.f.java_lang_String_a(97));
                        n3 = n9 + n6 + n9;
                        String string2 = "" + this.var_aeii_a_a.var_short_a;
                        n13 = aeii.c.a((byte)0, string2);
                        n12 = n8 - n3 - n9 - aeii.e.var_aeii_g_a.var_a_a_a_g_l.o - n13 - n9;
                        graphics.setColor(this.var_int_a);
                        aeii.e.a(graphics, n3, n5, n12, n4);
                        graphics.setColor(2370117);
                        n11 = n12 * this.var_aeii_a_a.var_int_a / this.var_aeii_a_a.int_a();
                        if (n11 <= 0) {
                            n11 = 1;
                        }
                        graphics.fillRect(n3 + 1, n5 + 1, n11, n4 - 2);
                        n3 = n8 - n9 - n13;
                        aeii.e.var_aeii_g_a.var_a_a_a_g_l.a(graphics, 2, n3, n7, 10);
                        aeii.c.a(graphics, string2, n3, n7, 0, 6);
                        graphics.setColor(this.var_int_a);
                        graphics.drawLine(n9, n5 += n4 + aeii.e.n, n8 - n9 - n9, n5);
                        n5 += 1 + aeii.e.n;
                    }
                    n4 = (n8 - n9 * 3) / 2;
                    n6 = aeii.e.var_aeii_g_a.var_a_a_a_g_l.p;
                    n12 = aeii.e.var_aeii_g_a.var_a_a_a_g_k.p;
                    n11 = aeii.e.var_aeii_g_a.var_a_a_a_g_k.p / 2;
                    for (n7 = 0; n7 < 2; ++n7) {
                        int n14 = n5 + n11 - n6 / 2;
                        n3 = n9;
                        for (int i = 0; i < 2; ++i) {
                            if (n7 != 0 && i != 0) continue;
                            n13 = n3 + n11;
                            aeii.e.a(graphics, n13, n14, n4 - n11, n6);
                            aeii.e.var_aeii_g_a.var_a_a_a_g_k.a(graphics, n3, n5);
                            int n15 = (n7 << 1) + i;
                            if (n15 == 0 || n15 == 1) {
                                aeii.e.var_aeii_g_a.var_a_a_a_g_l.a(graphics, n15, n13, n5 + n11, 3);
                            }
                            int n16 = 0;
                            if (n15 == 0) {
                                if (this.var_byte_a == 5) {
                                    n16 = this.var_aeii_a_a.a((a)null);
                                }
                                string = this.var_aeii_a_a.var_int_d + n16 + "-" + (this.var_aeii_a_a.var_int_e + n16);
                            } else if (n15 == 1) {
                                if (this.var_byte_a == 5) {
                                    n16 = this.var_aeii_a_a.b((a)null);
                                }
                                string = "" + (this.var_aeii_a_a.var_int_f + n16);
                            } else if (n15 == 2) {
                                aeii.e.var_aeii_g_a.var_aeii_d_arr_a[5].a(graphics, n13, n5 + n11, 3);
                                string = "" + this.var_aeii_a_a.var_int_g;
                            }
                            aeii.c.a(graphics, string, n3 + n12 + 1, n5 + n11, 0, 6);
                            if (n16 > 0) {
                                aeii.e.var_aeii_g_a.var_a_a_a_g_p.a(graphics, 1, n13 + n4 - n11 - 1, n5 + n11, 10);
                            } else if (n16 < 0) {
                                aeii.e.var_aeii_g_a.var_a_a_a_g_p.a(graphics, 2, n13 + n4 - n11 - 1, n5 + n11, 10);
                            }
                            n3 += n4 + aeii.e.n;
                        }
                        n5 += n12;
                    }
                    graphics.setColor(this.var_int_a);
                    graphics.drawLine(n9, n5 += aeii.e.n, n8 - n9 - n9, n5);
                    break;
                }
                case 3: {
                    graphics.setClip(0, 0, n8, n9);
                    n5 = aeii.e.n;
                    graphics.setColor(11515819);
                    graphics.drawLine(aeii.e.n, n5, n8 - (aeii.e.n << 1), n5);
                    n5 = n5 + (1 + aeii.e.n) + aeii.e.var_aeii_g_a.var_a_a_a_g_j.p / 2;
                    n3 = aeii.e.var_aeii_g_a.var_a_a_a_g_a.o + this.D + this.F;
                    n9 = this.A;
                    int n17 = this.A + this.B;
                    if (this.D > 0) {
                        --n9;
                        n3 -= this.r;
                    } else if (this.D < 0) {
                        ++n17;
                    }
                    for (int i = n9; i < n17; ++i) {
                        n4 = i % this.s;
                        if (n4 < 0) {
                            n4 += this.s;
                        }
                        n6 = n3 + this.r / 2;
                        if (n4 == this.var_int_d) {
                            aeii.e.var_aeii_g_a.var_a_a_a_g_j.a(graphics, 1, n6, n5, 3);
                        } else {
                            aeii.e.var_aeii_g_a.var_a_a_a_g_j.a(graphics, 0, n6, n5, 3);
                        }
                        a a2 = this.var_aeii_a_arr_a[n4];
                        int n18 = n6 - a2.m - a2.o / 2;
                        int n19 = n5 - a2.n - a2.p / 2;
                        a2.a(graphics, n18, n19, a2.var_int_j > aeii.e.var_aeii_g_a.var_int_arr_b[aeii.e.var_aeii_g_a.var_byte_b]);
                        if (n4 == this.var_int_d) {
                            n7 = n6 - this.var_a_a_a_g_a.o / 2;
                            int n20 = n5 - this.var_a_a_a_g_a.o / 2;
                            for (int j = 0; j < this.var_a_a_a_g_arr_a.length; ++j) {
                                this.var_a_a_a_g_arr_a[j].a(graphics, n7, n20, 20);
                            }
                        }
                        n3 += this.r;
                    }
                    aeii.e.var_aeii_g_a.var_a_a_a_g_a.a(graphics, 0, 0, n5, 6);
                    aeii.e.var_aeii_g_a.var_a_a_a_g_a.a(graphics, 1, n8, n5, 10);
                    break;
                }
                case 7: 
                case 10: 
                case 11: {
                    int n21;
                    int n22;
                    int n23;
                    graphics.setFont(aeii.c.var_javax_microedition_lcdui_Font_b);
                    if (this.var_byte_c != -1) {
                        aeii.e.var_aeii_g_a.var_a_a_a_g_i.a(graphics, this.var_byte_c, -8, n9, 36);
                    }
                    graphics.setClip(0, 0, n8 -= this.C, this.var_int_c - 10);
                    n3 = 0;
                    n5 = 0;
                    if (this.var_java_lang_String_arr_b != null) {
                        graphics.setColor(aeii.g.a(0xFFFFFF, this.h, this.G, 5));
                        for (n23 = 0; n23 < this.var_java_lang_String_arr_b.length; ++n23) {
                            aeii.c.b(graphics, this.var_java_lang_String_arr_b[n23], this.C + n8 / 2, n5 + this.z, 17);
                            n5 += this.r;
                        }
                        graphics.setColor(10463131);
                        graphics.drawLine(0, n5, n8 - 1, n5);
                        n3 = n5;
                    }
                    n23 = n5 + this.F;
                    graphics.setColor(this.var_int_a);
                    int n24 = this.A;
                    n7 = this.A + this.B;
                    if (this.D > 0) {
                        --n24;
                        n5 -= this.r;
                    } else if (this.D < 0) {
                        ++n7;
                    }
                    n5 += this.D + this.F;
                    graphics.setClip(this.C, n3, n8, n9 - n3);
                    n3 = n8;
                    if (this.var_boolean_g) {
                        n3 = n8 - aeii.e.var_aeii_g_a.var_a_a_a_g_b.o;
                    }
                    n6 = this.C + n3 / 2;
                    for (n22 = n24; n22 < n7; ++n22) {
                        n4 = 0;
                        if (n5 < n23) {
                            n4 = n23 - n5;
                        } else if (n5 + this.r > n9 - this.F) {
                            n4 = n5 + this.r - n9 + this.F;
                        }
                        if (this.var_byte_a == 11 && n22 == this.var_int_d) {
                            graphics.setColor(5594742);
                            aeii.e.a(graphics, 0, n5, n3, this.r);
                            n21 = aeii.g.a(this.h, 0xFFFFFF, this.r - n4, this.r);
                        } else {
                            n21 = aeii.g.a(this.h, this.var_int_a, this.r - n4, this.r);
                        }
                        n21 = aeii.g.a(n21, this.h, this.G, 5);
                        graphics.setColor(n21);
                        if (this.P >= 0) {
                            aeii.c.b(graphics, this.var_java_lang_String_arr_a[n22], this.P, n5 + this.z, 20);
                        } else {
                            aeii.c.b(graphics, this.var_java_lang_String_arr_a[n22], n6, n5 + this.z, 17);
                        }
                        n5 += this.r;
                    }
                    if (this.var_boolean_g) {
                        n22 = aeii.e.var_aeii_g_a.var_a_a_a_g_b.p;
                        n4 = aeii.e.var_aeii_g_a.var_a_a_a_g_b.o;
                        n21 = aeii.e.var_aeii_g_a.var_a_a_a_g_b.o / 2;
                        int n25 = n9 - (n22 << 1) - 2;
                        n3 = n8 - (n4 + n21) / 2;
                        if (n25 > 2) {
                            graphics.setColor(this.var_int_a);
                            aeii.e.a(graphics, n3, n22 + 1, n21, n25);
                            n5 = (n25 - 2) * this.B / this.s;
                            if (n5 <= 0) {
                                n5 = 1;
                            }
                            graphics.setColor(2370117);
                            aeii.e.a(graphics, n3 + 1, n22 + (n25 - 2) * this.A / this.s + 2, n21 - 2, n5);
                            aeii.e.var_aeii_g_a.var_a_a_a_g_b.a(graphics, 0, n8 - n4, 0, 20);
                            aeii.e.var_aeii_g_a.var_a_a_a_g_b.a(graphics, 1, n8 - n4, n9, 36);
                        } else {
                            if (this.A > 0) {
                                aeii.e.var_aeii_g_a.var_a_a_a_g_b.a(graphics, 0, n8 - n4, 0, 20);
                            }
                            if (this.A + this.B < this.s) {
                                aeii.e.var_aeii_g_a.var_a_a_a_g_b.a(graphics, 1, n8 - n4, n9, 36);
                            }
                        }
                    }
                    if (this.var_byte_a != 7) break;
                    graphics.setClip(0, 0, this.j, this.k);
                    n22 = n9;
                    if ((this.x & 2) == 0) {
                        n22 = n9 + 5;
                    }
                    aeii.e.var_aeii_g_a.var_a_a_a_g_b.a(graphics, 1, n8 + this.C, n22, 40);
                    break;
                }
                case 8: {
                    aeii.g.a(graphics, 0, 0, this.var_int_b, this.var_int_c);
                    int n26 = aeii.e.var_aeii_g_a.var_aeii_d_arr_b[0].var_int_a;
                    int n27 = aeii.e.var_aeii_g_a.var_aeii_d_arr_b[0].b;
                    int n28 = this.K + this.I;
                    int n29 = this.J + this.H;
                    n5 = 4;
                    for (n6 = this.I; n6 < n28; ++n6) {
                        n3 = 4;
                        for (n7 = this.H; n7 < n29; ++n7) {
                            n4 = aeii.e.var_aeii_g_a.var_byte_arr_c[this.var_byte_arr_arr_a[n7][n6]];
                            aeii.e.var_aeii_g_a.var_aeii_d_arr_b[n4].a(graphics, n3, n5);
                            n3 += n26;
                        }
                        n5 += n27;
                    }
                    if (this.var_java_util_Vector_b != null && this.t == 0) {
                        n6 = -this.H * n26 + 4;
                        n7 = -this.I * n27 + 4;
                        n3 = aeii.e.var_aeii_g_a.var_java_util_Vector_a.size();
                        for (n4 = 0; n4 < n3; ++n4) {
                            a a3 = (a)aeii.e.var_aeii_g_a.var_java_util_Vector_a.elementAt(n4);
                            if (a3.var_short_b < this.H || a3.var_short_b >= n29 || a3.var_short_c < this.I || a3.var_short_c >= n28) continue;
                            aeii.e.var_aeii_g_a.var_a_a_a_g_n.a(graphics, aeii.e.var_aeii_g_a.var_byte_arr_d[a3.var_byte_e] - 1, a3.var_short_b * n26 + n6, a3.var_short_c * n27 + n7, 0);
                        }
                    }
                    if (this.t != 0) break;
                    if (this.I > 0) {
                        aeii.e.var_aeii_g_a.var_a_a_a_g_b.a(graphics, 0, n8 / 2, 0, 17);
                    }
                    if (this.I + this.K < this.M) {
                        aeii.e.var_aeii_g_a.var_a_a_a_g_b.a(graphics, 1, n8 / 2, n9, 33);
                    }
                    if (this.H > 0) {
                        aeii.e.var_aeii_g_a.var_a_a_a_g_a.a(graphics, 0, 0, n9 / 2, 6);
                    }
                    if (this.H + this.J >= this.L) break;
                    aeii.e.var_aeii_g_a.var_a_a_a_g_a.a(graphics, 1, n8, n9 / 2, 10);
                    break;
                }
                case 13: {
                    n6 = aeii.c.var_int_b;
                    n7 = (this.var_int_c - n6) / 2;
                    graphics.setColor(aeii.g.a(1645370, 0xFFFFFF, this.G, 5));
                    aeii.e.a(graphics, 0, n7, this.var_int_b, n6);
                    graphics.setFont(aeii.c.var_javax_microedition_lcdui_Font_b);
                    graphics.setColor(0xFFFFFF);
                    aeii.c.b(graphics, this.var_java_lang_String_arr_a[this.var_int_d], 16, n7 + this.z, 20);
                    n4 = this.var_int_b - this.r;
                    for (n3 = this.s - 1; n3 >= 0; --n3) {
                        if (n3 == this.var_int_d) {
                            aeii.e.var_aeii_g_a.var_a_a_a_g_k.a(graphics, 1, n4, 0, 20);
                        } else {
                            aeii.e.var_aeii_g_a.var_a_a_a_g_k.a(graphics, 0, n4, 0, 20);
                        }
                        this.var_aeii_d_arr_a[n3].a(graphics, n4 + aeii.e.var_aeii_g_a.var_a_a_a_g_k.o / 2, this.var_int_c / 2, 3);
                        n4 -= this.r;
                    }
                    break;
                }
                case 14: {
                    graphics.setFont(aeii.c.var_javax_microedition_lcdui_Font_b);
                    graphics.setColor(aeii.g.a(0xFFFFFF, 1645370, this.G, 5));
                    n7 = n9 / 2;
                    aeii.c.b(graphics, this.var_java_lang_String_arr_a[this.var_int_d], n8 / 2, (n9 - aeii.c.var_int_a) / 2, 17);
                    aeii.e.var_aeii_g_a.var_a_a_a_g_a.a(graphics, 0, 0, n7, 6);
                    aeii.e.var_aeii_g_a.var_a_a_a_g_a.a(graphics, 1, n8, n7, 10);
                    break;
                }
                case 15: {
                    n6 = 0;
                    n5 = 0;
                    n4 = this.var_int_arr_b[0];
                    for (n9 = 1; n9 <= this.N; ++n9) {
                        n6 = n4;
                        n4 += this.var_int_arr_b[n9];
                    }
                    if (this.N > 0) {
                        n5 = -n6 + aeii.e.var_aeii_g_a.var_a_a_a_g_c.p;
                    }
                    for (n9 = 0; n9 < this.var_java_util_Vector_a.size(); ++n9) {
                        e e2 = (e)this.var_java_util_Vector_a.elementAt(n9);
                        if (e2.q < n6 || e2.q >= n4) continue;
                        e2.a(graphics, 0, n5, n9 == this.var_int_f);
                    }
                    graphics.setClip(0, 0, aeii.e.var_aeii_g_a.var_int_a, aeii.e.var_aeii_g_a.var_int_b);
                    if (this.N > 0) {
                        graphics.setColor(2370117);
                        graphics.fillRect(0, 0, this.j, aeii.e.var_aeii_g_a.var_a_a_a_g_c.p);
                        aeii.e.var_aeii_g_a.var_a_a_a_g_b.a(graphics, 0, aeii.e.var_aeii_g_a.var_int_a / 2, -this.t, 17);
                    }
                    if (this.N >= this.O) break;
                    graphics.setColor(2370117);
                    n9 = this.var_int_arr_b[this.N];
                    if (this.N > 0) {
                        n9 += aeii.e.var_aeii_g_a.var_a_a_a_g_c.p;
                    }
                    graphics.fillRect(0, n9, this.j, aeii.e.var_aeii_g_a.var_int_b - n9);
                    aeii.e.var_aeii_g_a.var_a_a_a_g_b.a(graphics, 1, aeii.e.var_aeii_g_a.var_int_a / 2, aeii.e.var_aeii_g_a.var_int_b + this.t, 33);
                }
            }
            graphics.translate(-n, -n2);
            graphics.setClip(0, 0, this.j, this.k);
            if (aeii.f.var_aeii_c_a.var_aeii_f_a == this && this.var_byte_b == 2) {
                if (this.var_boolean_arr_a[0]) {
                    var_aeii_g_a.a(graphics, aeii.g.var_int_d, 0, aeii.e.var_aeii_g_a.var_int_b);
                }
                if (this.var_boolean_arr_a[1]) {
                    var_aeii_g_a.a(graphics, aeii.g.var_int_e, 1, aeii.e.var_aeii_g_a.var_int_b);
                }
            }
        }
    }

    public static final void a(Graphics graphics, int n, int n2, int n3, int n4, int n5) {
        aeii.e.a(graphics, 0, n2, n3, n4, n5, 2370117, 2370117, 0, 0);
    }

    public static final void a(Graphics graphics, int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9) {
        int n10;
        int n11;
        int n12;
        int n13;
        a.a.a.g g2 = aeii.e.var_aeii_g_a.var_a_a_a_g_d;
        graphics.setClip(n, n2, n3, n4);
        graphics.setColor(n6);
        graphics.fillRect(n, n2, n3, n4);
        if (n7 != n6) {
            n13 = n4 / 4;
            n12 = n2 + 5;
            for (n11 = 0; n11 < n13; ++n11) {
                n10 = aeii.g.a(n7, n6, n11, n13);
                graphics.setColor(aeii.g.a(n10, n6, n8, n9));
                graphics.fillRect(n, n12, n3, 1);
                ++n12;
            }
        }
        if (n5 != 15) {
            n13 = (n5 & 4) == 0 ? 1 : 0;
            n12 = (n5 & 8) == 0 ? 1 : 0;
            n11 = (n5 & 1) == 0 ? 1 : 0;
            n10 = (n5 & 2) == 0 ? 1 : 0;
            n5 = n3 / g2.o - 2;
            if (n3 % g2.o != 0) {
                ++n5;
            }
            if (n13 == 0) {
                ++n5;
            }
            if (n12 == 0) {
                ++n5;
            }
            n6 = n4 / g2.p - 2;
            if (n4 % g2.p != 0) {
                ++n6;
            }
            if (n11 == 0) {
                ++n6;
            }
            if (n10 == 0) {
                ++n6;
            }
            n7 = n;
            if (n13 != 0) {
                n7 = n + g2.o;
            }
            n4 = n2 + n4 - g2.p;
            for (n8 = 0; n8 < n5; ++n8) {
                if (n11 != 0) {
                    g2.a(graphics, 1, n7, n2, 0);
                }
                if (n10 != 0) {
                    g2.a(graphics, 6, n7, n4, 0);
                }
                n7 += g2.o;
            }
            n8 = n2;
            if (n11 != 0) {
                n8 = n2 + g2.p;
            }
            n3 = n + n3 - g2.o;
            for (n5 = 0; n5 < n6; ++n5) {
                if (n13 != 0) {
                    g2.a(graphics, 3, n, n8, 0);
                }
                if (n12 != 0) {
                    g2.a(graphics, 4, n3, n8, 0);
                }
                n8 += g2.p;
            }
            if (n13 != 0 && n11 != 0) {
                g2.a(graphics, 0, n, n2, 0);
            }
            if (n12 != 0 && n11 != 0) {
                g2.a(graphics, 2, n3, n2, 0);
            }
            if (n13 != 0 && n10 != 0) {
                g2.a(graphics, 5, n, n4, 0);
            }
            if (n12 != 0 && n10 != 0) {
                g2.a(graphics, 7, n3, n4, 0);
            }
        }
    }
}

