/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.io.Connection
 *  javax.microedition.io.Connector
 *  javax.microedition.lcdui.Graphics
 */
package a.a.a;

import a.a.a.a;
import a.a.a.b;
import a.a.a.c;
import a.a.a.d;
import a.a.a.e;
import a.a.a.f;
import java.io.InputStream;
import javax.microedition.io.Connection;
import javax.microedition.io.Connector;
import javax.microedition.lcdui.Graphics;

public class g {
    public aeii.d[] var_aeii_d_arr_a;
    private byte[] var_byte_arr_a;
    public int var_int_l;
    public int m;
    public int n;
    public boolean var_boolean_a;
    public int o;
    public int p;
    public byte[][] var_byte_arr_arr_b;
    public int q;
    public byte k;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public boolean var_boolean_b;
    public boolean c;
    public boolean d;
    public int x;
    public byte var_byte_l;
    public boolean e;
    public int y;
    public String var_java_lang_String_b;
    public int z;
    public g var_a_a_a_g_a;
    public g var_a_a_a_g_b;
    public int[][] var_int_arr_arr_a;
    public short[][] var_short_arr_arr_a;
    public int A;
    public byte[] var_byte_arr_b;
    public boolean[] var_boolean_arr_a;

    public g() {
    }

    public static Connection a(String string) {
        if (string.startsWith("file://")) {
            return g.a(Connector.open((String)string));
        }
        return Connector.open((String)string);
    }

    public static Connection javax_microedition_io_Connection_a(String string, int n) {
        if (string.startsWith("file://")) {
            return g.a(Connector.open((String)string, (int)1));
        }
        return Connector.open((String)string, (int)1);
    }

    private static c a(Connection connection) {
        switch (a.a.a.a.int_a()) {
            case 1: {
                return new e(connection);
            }
            case 2: {
                return new d(connection);
            }
            case 3: {
                return new b(connection);
            }
            case 4: {
                return new f(connection);
            }
        }
        return null;
    }

    public g(String string) {
        this.var_int_l = 0;
        this.m = 0;
        this.n = 0;
        this.var_boolean_a = true;
        this.k = 0;
        this.r = -1;
        this.var_boolean_b = true;
        this.x = -1;
        this.var_byte_l = (byte)-1;
        this.A = 0xFFE000;
        this.void_a(string, -1);
    }

    public g(aeii.d[] dArray) {
        this.var_int_l = 0;
        this.m = 0;
        this.n = 0;
        this.var_boolean_a = true;
        this.k = 0;
        this.r = -1;
        this.var_boolean_b = true;
        this.x = -1;
        this.var_byte_l = (byte)-1;
        this.A = 0xFFE000;
        this.var_aeii_d_arr_a = dArray;
        this.var_byte_arr_a = new byte[this.var_aeii_d_arr_a.length];
        for (int n = 0; n < this.var_aeii_d_arr_a.length; n = (int)((byte)(n + 1))) {
            int n2 = n;
            int n3 = n;
            this.var_byte_arr_a[n2] = n3;
        }
        this.o = this.var_aeii_d_arr_a[0].var_int_a;
        this.p = this.var_aeii_d_arr_a[0].b;
    }

    public g(String string, byte by) {
        this.var_int_l = 0;
        this.m = 0;
        this.n = 0;
        this.var_boolean_a = true;
        this.k = 0;
        this.r = -1;
        this.var_boolean_b = true;
        this.x = -1;
        this.var_byte_l = (byte)-1;
        this.A = 0xFFE000;
        this.void_a(string, by);
    }

    private void void_a(String string, int n) {
        try {
            int n2;
            int n3;
            int n4;
            int n5;
            int n6;
            int n7;
            int n8 = 0;
            int[][] nArray = null;
            int[][] nArray2 = null;
            int n9 = 0;
            InputStream inputStream = aeii.c.java_io_InputStream_a(string + ".sprite");
            if (inputStream != null) {
                String string2;
                while ((string2 = aeii.c.a(inputStream)) != null) {
                    n7 = string2.indexOf(59);
                    if (n7 >= 0) {
                        string2 = string2.substring(0, n7);
                    }
                    if ((string2 = string2.trim()).length() == 0) continue;
                    String[] stringArray = aeii.c.a(string2, ' ');
                    if (stringArray[0].equalsIgnoreCase("FrameCount")) {
                        byte by = (byte)Integer.parseInt(stringArray[1]);
                        n8 = by;
                        nArray = new int[by][2];
                        nArray2 = new int[n8][2];
                        continue;
                    }
                    if (stringArray[0].equalsIgnoreCase("FrameWidth")) {
                        this.o = Integer.parseInt(stringArray[1]);
                        continue;
                    }
                    if (stringArray[0].equalsIgnoreCase("FrameHeight")) {
                        this.p = Integer.parseInt(stringArray[1]);
                        continue;
                    }
                    if (stringArray[0].equalsIgnoreCase("FrameDef")) {
                        n7 = Integer.parseInt(stringArray[1]);
                        nArray[n7][0] = Integer.parseInt(stringArray[2]);
                        nArray[n7][1] = Integer.parseInt(stringArray[3]);
                        continue;
                    }
                    if (stringArray[0].equalsIgnoreCase("Align")) {
                        n9 = Integer.parseInt(stringArray[1]);
                        continue;
                    }
                    if (stringArray[0].equalsIgnoreCase("FrameSeqCount")) {
                        n7 = Integer.parseInt(stringArray[1]);
                        if (n7 <= 0) continue;
                        this.var_byte_arr_arr_b = new byte[n7][];
                        continue;
                    }
                    if (stringArray[0].equalsIgnoreCase("FrameDelay")) {
                        this.t = Integer.parseInt(stringArray[1]) * 50;
                        continue;
                    }
                    if (stringArray[0].equalsIgnoreCase("FrameSeq")) {
                        n7 = Integer.parseInt(stringArray[1]);
                        this.var_byte_arr_arr_b[n7] = new byte[Integer.parseInt(stringArray[2])];
                        for (n6 = 0; n6 < this.var_byte_arr_arr_b[n7].length; ++n6) {
                            this.var_byte_arr_arr_b[n7][n6] = (byte)Integer.parseInt(stringArray[n6 + 3]);
                        }
                        continue;
                    }
                    if (!stringArray[0].equalsIgnoreCase("Translate")) continue;
                    n7 = Integer.parseInt(stringArray[1]);
                    nArray2[n7][0] = Integer.parseInt(stringArray[2]);
                    nArray2[n7][1] = Integer.parseInt(stringArray[3]);
                }
            } else {
                inputStream = aeii.c.java_io_InputStream_a(string + ".sprbin");
                byte by = (byte)inputStream.read();
                n8 = by;
                nArray = new int[by][2];
                nArray2 = new int[n8][2];
                this.o = (byte)inputStream.read();
                this.p = (byte)inputStream.read();
                for (n5 = 0; n5 < n8; ++n5) {
                    nArray[n5][0] = inputStream.read();
                    nArray[n5][1] = inputStream.read();
                }
                n9 = inputStream.read();
                int n10 = inputStream.read();
                if (n10 > 0) {
                    this.var_byte_arr_arr_b = new byte[n10][];
                    this.t = inputStream.read() * 50;
                    for (n6 = 0; n6 < n10; ++n6) {
                        int n11 = inputStream.read();
                        this.var_byte_arr_arr_b[n6] = new byte[n11];
                        for (n7 = 0; n7 < n11; ++n7) {
                            this.var_byte_arr_arr_b[n6][n7] = (byte)inputStream.read();
                        }
                    }
                }
                for (n6 = 0; n6 < n8; ++n6) {
                    n4 = (byte)inputStream.read();
                    n3 = (byte)inputStream.read();
                    if (n4 != -1 && n3 != -1) {
                        nArray2[n6][0] = n4;
                        nArray2[n6][1] = n3;
                        continue;
                    }
                    break;
                }
            }
            inputStream.close();
            this.var_aeii_d_arr_a = new aeii.d[n8];
            aeii.d[] dArray = new aeii.d[n8];
            try {
                aeii.d d2 = new aeii.d(string, n);
                n7 = d2.var_int_a / this.o;
                n6 = d2.b / this.p;
                n5 = 0;
                for (n4 = 0; n4 < n6; ++n4) {
                    for (n3 = 0; n3 < n7; ++n3) {
                        dArray[n5] = new aeii.d(d2, n3, n4, this.o, this.p);
                        ++n5;
                    }
                }
            }
            catch (Exception exception) {
                try {
                    for (n7 = 0; n7 < n8; ++n7) {
                        StringBuffer stringBuffer = new StringBuffer(string);
                        stringBuffer.append('_');
                        if (n7 < 10) {
                            stringBuffer.append('0');
                        }
                        stringBuffer.append(n7);
                        dArray[n7] = n < 0 ? new aeii.d(stringBuffer.toString()) : new aeii.d(stringBuffer.toString(), n);
                    }
                }
                catch (Exception exception2) {}
            }
            for (n2 = 0; n2 < n8; ++n2) {
                this.var_aeii_d_arr_a[n2] = new aeii.d(dArray[nArray[n2][0]], nArray[n2][1]);
            }
            if (n9 > 0) {
                for (n2 = 0; n2 < n8; ++n2) {
                    this.var_aeii_d_arr_a[n2].a(n9, this.o, this.p);
                    this.var_aeii_d_arr_a[n2].a(nArray2[n2][0], nArray2[n2][1]);
                }
            } else {
                for (n2 = 0; n2 < n8; ++n2) {
                    this.var_aeii_d_arr_a[n2].a(nArray2[n2][0], nArray2[n2][1]);
                }
            }
            if (this.var_byte_arr_arr_b == null) {
                this.var_byte_arr_a = new byte[this.var_aeii_d_arr_a.length];
                for (n2 = 0; n2 < this.var_aeii_d_arr_a.length; n2 = (int)((byte)(n2 + 1))) {
                    int n12 = n2;
                    int n13 = n2;
                    this.var_byte_arr_a[n12] = n13;
                }
                return;
            }
            this.var_byte_arr_a = this.var_byte_arr_arr_b[0];
        }
        catch (Exception exception) {
            Exception exception3 = exception;
            exception.printStackTrace();
        }
    }

    public g(g g2) {
        this.var_int_l = 0;
        this.m = 0;
        this.n = 0;
        this.var_boolean_a = true;
        this.k = 0;
        this.r = -1;
        this.var_boolean_b = true;
        this.x = -1;
        this.var_byte_l = (byte)-1;
        this.A = 0xFFE000;
        this.var_aeii_d_arr_a = g2.var_aeii_d_arr_a;
        this.var_byte_arr_a = g2.var_byte_arr_a;
        this.var_int_l = g2.var_int_l;
        this.m = g2.m;
        this.n = g2.n;
        this.q = g2.q;
        this.var_boolean_a = g2.var_boolean_a;
        this.o = g2.o;
        this.p = g2.p;
        this.t = g2.t;
        this.var_byte_arr_arr_b = g2.var_byte_arr_arr_b;
    }

    public g(int n, int n2) {
        this.var_int_l = 0;
        this.m = 0;
        this.n = 0;
        this.var_boolean_a = true;
        this.k = 0;
        this.r = -1;
        this.var_boolean_b = true;
        this.x = -1;
        this.var_byte_l = (byte)-1;
        this.A = 0xFFE000;
        this.o = n;
        this.p = n2;
    }

    public int int_c() {
        return this.var_byte_arr_a.length;
    }

    public int int_d() {
        return this.var_aeii_d_arr_a.length;
    }

    public void c(int n) {
        if (n < this.var_byte_arr_a.length) {
            this.var_int_l = (byte)n;
        }
    }

    public void b(int n, int n2) {
        this.m = (short)n;
        this.n = (short)n2;
    }

    public void void_e() {
        ++this.var_int_l;
        if (this.var_int_l >= this.var_byte_arr_a.length) {
            this.var_int_l = 0;
        }
    }

    public void a(byte[] byArray) {
        this.var_byte_arr_a = byArray;
        this.var_int_l = 0;
        this.s = 0;
    }

    public void a(int n, boolean bl) {
        if (this.var_byte_arr_arr_b != null && n <= this.var_byte_arr_arr_b.length) {
            byte[] byArray = this.var_byte_arr_arr_b[n];
            if (bl) {
                byte[] byArray2 = new byte[byArray.length];
                for (int i = 0; i < byArray2.length; ++i) {
                    byArray2[i] = (byte)(byArray[i] + this.int_d() / 2);
                }
                byArray = byArray2;
            }
            this.a(byArray);
        }
    }

    public void a(Graphics graphics, int n, int n2, int n3, int n4) {
        if (this.k != 2 && this.k != 4 && this.k != 3) {
            if (this.var_boolean_a) {
                n2 = this.m + n2;
                n3 = this.n + n3;
                this.var_aeii_d_arr_a[n].a(graphics, n2, n3, n4);
                return;
            }
        } else {
            this.a(graphics, n2, n3);
        }
    }

    public void a(Graphics graphics, int n, int n2, int n3) {
        this.a(graphics, this.var_byte_arr_a[this.var_int_l], n, n2, n3);
    }

    public void a(Graphics graphics, int n, int n2) {
        if (this.k != 2 && this.k != 4) {
            if (this.k == 6) {
                if (this.var_int_l == 0) {
                    graphics.setColor(15718144);
                } else {
                    graphics.setColor(0xFFFFFF);
                }
                if (this.u > 0) {
                    int n3 = this.m + 15;
                    graphics.fillArc(this.m, this.n - 15, 30, 30, 0, 360);
                    graphics.fillRect(n3, this.n - 15, aeii.c.var_int_d - n3, 30);
                    return;
                }
                graphics.fillArc(this.m - 30, this.n - 15, 30, 30, 0, 360);
                graphics.fillRect(0, this.n - 15, this.m - 15, 30);
                return;
            }
            if (this.k == 3) {
                graphics.setColor(0);
                if (this.u > 0) {
                    graphics.drawLine(this.m, this.n, this.m + 4, this.n - 2);
                    return;
                }
                graphics.drawLine(this.m - 4, this.n - 2, this.m, this.n);
                return;
            }
            if (this.var_boolean_a) {
                int n4 = this.m + n;
                int n5 = this.n + n2;
                if (this.var_java_lang_String_b != null) {
                    aeii.c.a(graphics, this.var_java_lang_String_b, n4, n5, this.z, 33);
                    return;
                }
                if (this.x > 0) {
                    n4 += aeii.c.int_a(-4, 5);
                    n5 += aeii.c.int_a(-1, 2);
                }
                byte by = this.var_byte_arr_a[this.var_int_l];
                this.var_aeii_d_arr_a[by].a(graphics, n4, n5);
                if (this.var_a_a_a_g_a != null) {
                    g g2;
                    n = by % (this.int_d() / 2);
                    if (n == 2) {
                        g2 = this.var_a_a_a_g_b;
                    } else {
                        g2 = this.var_a_a_a_g_a;
                        this.var_a_a_a_g_a.c(n);
                    }
                    g2.a(graphics, n4, n5);
                }
                return;
            }
        } else {
            graphics.setColor(this.A);
            for (int i = 0; i < 5; ++i) {
                if (!this.var_boolean_arr_a[i]) continue;
                int n6 = (this.var_int_arr_arr_a[i][0] >> 10) + n + this.m;
                int n7 = (this.var_int_arr_arr_a[i][1] >> 10) + n2 + this.n;
                graphics.fillRect(n6, n7, (int)this.var_byte_arr_b[i], (int)this.var_byte_arr_b[i]);
            }
        }
    }

    public static g a(String string, int n, int n2, byte by) {
        n = aeii.c.a((byte)1, string);
        n2 = aeii.c.a((byte)1);
        g g2 = new g(n, n2);
        new g(n, n2).z = 1;
        g2.var_java_lang_String_b = string;
        g2.u = 0;
        g2.w = -4;
        g2.k = (byte)5;
        return g2;
    }

    public static g a(g g2, int n, int n2, int n3, int n4, int n5, byte by) {
        if (g2 != null) {
            g2 = new g(g2);
        } else {
            g2 = new g(0, 0);
            if (by == 2 || by == 4) {
                if (by == 4) {
                    g2.A = 0xEEEEFF;
                }
                g2.var_int_arr_arr_a = new int[5][2];
                g2.var_short_arr_arr_a = new short[5][2];
                g2.var_byte_arr_b = new byte[5];
                g2.var_boolean_arr_a = new boolean[5];
                for (int i = 0; i < 5; ++i) {
                    g2.var_boolean_arr_a[i] = true;
                    if (by == 4) {
                        g2.var_short_arr_arr_a[i][0] = (short)(aeii.c.var_java_util_Random_a.nextInt() % 4 << 10);
                        g2.var_short_arr_arr_a[i][1] = (short)(aeii.c.var_java_util_Random_a.nextInt() % 4 << 10);
                    } else {
                        g2.var_short_arr_arr_a[i][0] = (short)(Math.abs(aeii.c.var_java_util_Random_a.nextInt()) % 8192 + -4096);
                        g2.var_short_arr_arr_a[i][1] = (short)(Math.abs(aeii.c.var_java_util_Random_a.nextInt()) % 4096 + -2048);
                    }
                    g2.var_byte_arr_b[i] = (byte)(Math.abs(aeii.c.var_java_util_Random_a.nextInt()) % 2 + 1);
                }
            }
        }
        g2.k = by;
        g2.r = n4;
        g2.t = n5;
        g2.u = n;
        g2.v = n2;
        g2.w = n3;
        g2.c = true;
        return g2;
    }

    public void void_c() {
        if (this.var_boolean_b) {
            this.s += 50;
            if (this.x >= 0) {
                --this.x;
            }
            switch (this.k) {
                case 2: 
                case 4: {
                    this.f();
                    return;
                }
                case 3: {
                    this.b(this.m + this.u, this.n + this.v);
                    return;
                }
                case 5: {
                    if (this.r == -1) {
                        this.b(this.m + this.u, this.n);
                        this.q += this.w;
                        if (this.q < 0) {
                            ++this.w;
                            return;
                        }
                        this.q = 0;
                        this.w = -this.w / 2;
                        if (this.w != 0) break;
                        this.r = 1;
                        this.s = 0;
                        return;
                    }
                    if (this.s < 400) break;
                    this.var_boolean_b = false;
                    return;
                }
                case 6: {
                    this.var_int_l = (this.var_int_l + 1) % 2;
                    if (this.s < this.t) break;
                    this.var_boolean_b = false;
                    return;
                }
                default: {
                    this.b(this.m + this.u, this.n + this.v);
                    this.q += this.w;
                    if (this.r == 0 || this.s < this.t) break;
                    this.void_e();
                    if (this.k == 0 && this.var_int_l == 0 && this.r > 0) {
                        --this.r;
                        if (this.r <= 0) {
                            this.c(this.int_c() - 1);
                            if (this.c) {
                                this.var_boolean_b = false;
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
        for (int i = 0; i < 5; ++i) {
            if (!this.var_boolean_arr_a[i]) continue;
            if (this.k == 4) {
                int[] nArray = this.var_int_arr_arr_a[i];
                nArray[0] = nArray[0] + this.var_short_arr_arr_a[i][0];
                int[] nArray2 = this.var_int_arr_arr_a[i];
                nArray2[1] = nArray2[1] + this.var_short_arr_arr_a[i][1];
                if (this.var_short_arr_arr_a[i][0] < 0) {
                    this.var_short_arr_arr_a[i][0] = (short)(this.var_short_arr_arr_a[i][0] + 256);
                } else if (this.var_short_arr_arr_a[i][0] > 0) {
                    this.var_short_arr_arr_a[i][0] = (short)(this.var_short_arr_arr_a[i][0] - 256);
                }
                if (this.var_short_arr_arr_a[i][1] < 0) {
                    this.var_short_arr_arr_a[i][1] = (short)(this.var_short_arr_arr_a[i][1] + 256);
                    continue;
                }
                if (this.var_short_arr_arr_a[i][1] <= 0) continue;
                this.var_short_arr_arr_a[i][1] = (short)(this.var_short_arr_arr_a[i][1] - 256);
                continue;
            }
            int[] nArray = this.var_int_arr_arr_a[i];
            nArray[0] = nArray[0] + this.var_short_arr_arr_a[i][0];
            int[] nArray3 = this.var_int_arr_arr_a[i];
            nArray3[1] = nArray3[1] + this.var_short_arr_arr_a[i][1];
            this.var_short_arr_arr_a[i][1] = (short)(this.var_short_arr_arr_a[i][1] + 256);
        }
        if (this.s >= this.t) {
            this.var_boolean_b = false;
        }
    }
}

