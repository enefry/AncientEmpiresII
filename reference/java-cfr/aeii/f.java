/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Font
 *  javax.microedition.lcdui.Graphics
 */
package aeii;

import aeii.Main;
import aeii.c;
import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class f {
    public static c var_aeii_c_a;
    public int j = var_aeii_c_a.getWidth();
    public int k = var_aeii_c_a.getHeight();
    public int l = this.j >> 1;
    public int m = this.k >> 1;
    private static String[] var_java_lang_String_arr_a;
    private static short[] var_short_arr_a;
    private static int var_int_a;
    private static int b;
    private static int c;

    public void void_a() {
    }

    public void b() {
    }

    public void a(Graphics graphics) {
    }

    public static final String[] a(String string, int n, Font font) {
        int n2;
        Vector<String> vector = new Vector<String>();
        int n3 = 0;
        int n4 = string.length();
        String string2 = null;
        do {
            n2 = n3;
            int n5 = string.indexOf(10, n3);
            block1: do {
                int n6 = n2;
                String string3 = string2;
                n2 = f.a(string, n2);
                if (n5 >= 0 && n5 < n2) {
                    n2 = n5;
                }
                if (font.stringWidth(string2 = string.substring(n3, n2).trim()) <= n) continue;
                if (n6 == n3) {
                    for (n3 = string2.length() - 1; n3 > 0; --n3) {
                        String string4 = string2.substring(0, n3);
                        if (font.stringWidth(string4) > n) continue;
                        n2 = n6 + n3;
                        string2 = string4;
                        break block1;
                    }
                    break;
                }
                n2 = n6;
                string2 = string3;
                break;
            } while (n2 != n5 && n2 < n4);
            vector.addElement(string2);
            n3 = ++n2;
        } while (n2 < n4);
        Object[] objectArray = new String[vector.size()];
        vector.copyInto(objectArray);
        return objectArray;
    }

    private static final int a(String string, int n) {
        int n2;
        if (f.boolean_a(string.charAt(n))) {
            return n + 1;
        }
        while ((n2 = string.indexOf(32, n)) == 0) {
            ++n;
        }
        int n3 = n2 == -1 ? string.length() : n2 + 1;
        for (n2 = n + 1; n2 < n3; ++n2) {
            if (!f.boolean_a(string.charAt(n2))) continue;
            return n2;
        }
        return n3;
    }

    private static final boolean boolean_a(int n) {
        return n >= 11904 && n < 44032 || n >= 63744 && n < 64256 || n >= 65280 && n < 65504;
    }

    public static final int int_a(String object) {
        object = new DataInputStream(((Object)((Object)Main.var_aeii_Main_a)).getClass().getResourceAsStream((String)object));
        var_java_lang_String_arr_a = new String[((DataInputStream)object).readInt()];
        int n = var_java_lang_String_arr_a.length;
        for (int i = 0; i < n; ++i) {
            String string;
            f.var_java_lang_String_arr_a[i] = string = ((DataInputStream)object).readUTF();
        }
        ((FilterInputStream)object).close();
        return var_java_lang_String_arr_a.length;
    }

    public static final String java_lang_String_a(int n) {
        return f.a(n, false);
    }

    public static final String a(int n, boolean bl) {
        if (n < var_java_lang_String_arr_a.length) {
            String string = var_java_lang_String_arr_a[n];
            if (bl && (string = f.a(f.a(f.a(f.a(string, "%K5", f.a(20, var_aeii_c_a.java_lang_String_a(16)), true), "%K0", var_aeii_c_a.java_lang_String_a(32), true), "%K7", var_aeii_c_a.java_lang_String_a(256), true), "%K9", var_aeii_c_a.java_lang_String_a(512), true)).indexOf("%KM") != -1) {
                StringBuffer stringBuffer = new StringBuffer();
                String[] stringArray = new String[]{var_aeii_c_a.java_lang_String_a(1), var_aeii_c_a.java_lang_String_a(2), var_aeii_c_a.java_lang_String_a(4), var_aeii_c_a.java_lang_String_a(8)};
                stringBuffer.append(f.a(17, stringArray));
                if (stringBuffer.length() > 0) {
                    stringBuffer.append('/');
                }
                stringBuffer.append(f.java_lang_String_a(18));
                if (stringBuffer.length() > 0) {
                    stringBuffer.append('/');
                }
                stringBuffer.append(f.java_lang_String_a(19));
                string = f.a(string, "%KM", stringBuffer.toString(), true);
            }
            return string;
        }
        return "?: " + n;
    }

    private static String a(int n, String[] stringArray) {
        String string = new String(f.java_lang_String_a(17));
        for (int i = 0; i < stringArray.length; ++i) {
            string = f.a(string, "%U", stringArray[i], false);
        }
        return string;
    }

    public static final String a(int n, String string) {
        return f.a(f.a(n, false), "%U", string, false);
    }

    private static String a(String string, String string2, String string3, boolean bl) {
        int n;
        while ((n = string.indexOf(string2)) != -1) {
            string = string.substring(0, n) + string3 + string.substring(n + string2.length());
            if (bl) continue;
        }
        return string;
    }

    public static final void c() {
        b = var_int_a >> 1;
        c = b >> 1;
        var_short_arr_a = new short[var_int_a];
        int n = var_int_a * 10000 / 2 / 31415;
        int n2 = n * 1024;
        int n3 = 0;
        for (int i = 0; i < var_int_a; ++i) {
            int n4 = n3 / n;
            f.var_short_arr_a[i] = (short)n4;
            n3 += (n2 -= n4) / n;
        }
        f.var_short_arr_a[180] = 0;
        f.var_short_arr_a[270] = -1024;
    }

    public static final short short_a(int n) {
        return var_short_arr_a[n %= 360];
    }

    public static final short short_b(int n) {
        n = (n + c) % 360;
        return var_short_arr_a[n];
    }

    static {
        var_short_arr_a = null;
        var_int_a = 360;
        b = 0;
        c = 0;
    }
}

