/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Canvas
 *  javax.microedition.lcdui.Command
 *  javax.microedition.lcdui.CommandListener
 *  javax.microedition.lcdui.Display
 *  javax.microedition.lcdui.Displayable
 *  javax.microedition.lcdui.Font
 *  javax.microedition.lcdui.Form
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.media.Manager
 *  javax.microedition.media.Player
 *  javax.microedition.midlet.MIDlet
 *  javax.microedition.rms.RecordStore
 */
package aeii;

import aeii.Main;
import aeii.f;
import aeii.g;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordStore;

public final class c
extends Canvas
implements Runnable,
CommandListener {
    public static boolean var_boolean_a;
    public static boolean var_boolean_b;
    public static final Font var_javax_microedition_lcdui_Font_a;
    public static final Font var_javax_microedition_lcdui_Font_b;
    public static final int var_int_a;
    public static final int var_int_b;
    public static final int var_int_c;
    private static short[] var_short_arr_a;
    private static short[] var_short_arr_b;
    private static byte[][] var_byte_arr_arr_a;
    private static Display var_javax_microedition_lcdui_Display_a;
    private boolean var_boolean_c = false;
    private boolean var_boolean_d = true;
    private int var_int_f;
    private String var_java_lang_String_a = "";
    public f var_aeii_f_a;
    public static int var_int_d;
    public static int var_int_e;
    private int g = 0;
    private int h;
    private int i = 0;
    private long var_long_a;
    public static a.a.a.g[] var_a_a_a_g_arr_a;
    public static Random var_java_util_Random_a;
    public static boolean[] var_boolean_arr_a;
    public static String[] var_java_lang_String_arr_a;
    private static int[] var_int_arr_a;
    private boolean var_boolean_e = false;
    private static int j;
    private static int k;
    private static boolean var_boolean_f;
    public static final String[] var_java_lang_String_arr_b;
    private static byte[] var_byte_arr_a;
    private static Player[] var_javax_microedition_media_Player_arr_a;
    private static Player var_javax_microedition_media_Player_a;
    private static boolean[] var_boolean_arr_b;
    private static int l;
    private static int m;
    private static byte[][] var_byte_arr_arr_b;
    private static String[] var_java_lang_String_arr_c;

    public c(MIDlet object) {
        try {
            var_boolean_a = "true".equalsIgnoreCase(Main.var_aeii_Main_a.getAppProperty("Developer-Mode"));
        }
        catch (Exception exception) {
            var_boolean_a = false;
        }
        try {
            var_boolean_b = "true".equalsIgnoreCase(Main.var_aeii_Main_a.getAppProperty("Disable-Intro"));
        }
        catch (Exception exception) {
            var_boolean_b = false;
        }
        try {
            this.void_c(Integer.parseInt(Main.var_aeii_Main_a.getAppProperty("Override-FPS")));
        }
        catch (Exception exception) {
            this.void_c(15);
        }
        try {
            this.setFullScreenMode(true);
            aeii.f.var_aeii_c_a = this;
            aeii.f.int_a("/lang.dat");
            var_int_d = this.getWidth();
            var_int_e = this.getHeight();
            var_javax_microedition_lcdui_Display_a = Display.getDisplay((MIDlet)object);
            var_javax_microedition_lcdui_Display_a.setCurrent((Displayable)this);
            new Thread(this).start();
            return;
        }
        catch (Exception exception) {
            object = exception;
            exception.printStackTrace();
            this.void_a(((Throwable)object).toString());
            return;
        }
    }

    public static final int int_a(int n) {
        return aeii.c.int_a(0, n);
    }

    public static final int int_a(int n, int n2) {
        return n + Math.abs(var_java_util_Random_a.nextInt()) % (n2 - n);
    }

    public static final int int_a() {
        return var_java_util_Random_a.nextInt();
    }

    public static final int a(int[] nArray) {
        int n = nArray[nArray.length - 1];
        n = aeii.c.int_a(0, n);
        for (int i = 0; i < nArray.length; ++i) {
            if (n >= nArray[i]) continue;
            return i;
        }
        return -1;
    }

    public static final void a(int[] nArray, boolean n) {
        if (n != 0) {
            for (n = 1; n < nArray.length; ++n) {
                int n2 = n;
                nArray[n2] = nArray[n2] + nArray[n - 1];
            }
            return;
        }
        for (n = nArray.length - 1; n > 0; --n) {
            int n3 = n;
            nArray[n3] = nArray[n3] - nArray[n - 1];
        }
    }

    public static String a(InputStream inputStream) {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            char c2;
            if ((c2 = (char)inputStream.read()) == '\uffff') {
                if (stringBuffer.length() > 0) {
                    return stringBuffer.toString();
                }
                return null;
            }
            if (c2 == '\n') {
                return stringBuffer.toString();
            }
            if (c2 == '\r') continue;
            stringBuffer.append(c2);
        }
    }

    public static String[] a(String object, char c2) {
        Vector<Object> vector = new Vector<Object>();
        while (((String)object).length() > 0) {
            int n = ((String)object).indexOf(32);
            if (n >= 0) {
                vector.addElement(((String)object).substring(0, n));
                object = ((String)object).substring(n + 1);
                continue;
            }
            if (n == 0) {
                object = ((String)object).substring(1);
                continue;
            }
            vector.addElement(object);
            break;
        }
        object = new String[vector.size()];
        vector.copyInto((Object[])object);
        return object;
    }

    public static final byte[] byte_arr_a(String string, int n) {
        try {
            string = RecordStore.openRecordStore((String)string, (boolean)false);
            byte[] byArray = string.getRecord(n + 1);
            string.closeRecordStore();
            return byArray;
        }
        catch (Exception exception) {
            return null;
        }
    }

    public static final void a(String string, int n, byte[] byArray) {
        try {
            int n2;
            string = RecordStore.openRecordStore((String)string, (boolean)true);
            if (n2 <= n) {
                for (n2 = string.getNumRecords(); n2 < n; ++n2) {
                    string.addRecord(null, 0, 0);
                }
                string.addRecord(byArray, 0, byArray.length);
            } else {
                string.setRecord(n + 1, byArray, 0, byArray.length);
            }
            string.closeRecordStore();
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public static final int a(String string, byte[] byArray) {
        try {
            string = RecordStore.openRecordStore((String)string, (boolean)true);
            int n = string.addRecord(byArray, 0, byArray.length);
            string.closeRecordStore();
            return n - 1;
        }
        catch (Exception exception) {
            return -1;
        }
    }

    public static final void void_a(String string, int n) {
        try {
            string = RecordStore.openRecordStore((String)string, (boolean)true);
            string.deleteRecord(n + 1);
            string.closeRecordStore();
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public static final int int_a(String string) {
        int n = 0;
        try {
            string = RecordStore.openRecordStore((String)string, (boolean)true);
            n = string.getSizeAvailable();
            string.closeRecordStore();
        }
        catch (Exception exception) {}
        return n;
    }

    public static final int a(byte by, String string) {
        return aeii.c.var_a_a_a_g_arr_a[by].o * string.length();
    }

    public static final int a(byte by) {
        return aeii.c.var_a_a_a_g_arr_a[by].p;
    }

    public static final void a(Graphics graphics, int n) {
        graphics.setColor(n);
    }

    public static final void a(Graphics graphics, int n, int n2, int n3, int n4, int n5) {
        for (n2 = n4 * n5 - 1; n2 >= 0; --n2) {
            aeii.c.var_int_arr_a[n2] = n;
        }
        graphics.drawRGB(var_int_arr_a, 0, n4, 0, 0, n4, n5, true);
    }

    public final void showNotify() {
        this.setFullScreenMode(true);
        this.var_boolean_e = false;
        var_boolean_f = false;
        this.b();
        if (this.var_aeii_f_a != null) {
            this.var_aeii_f_a.void_a();
        }
    }

    public final void hideNotify() {
        this.b();
        if (this.var_aeii_f_a != null) {
            var_boolean_f = true;
            if (var_javax_microedition_media_Player_a != null && var_javax_microedition_media_Player_a.getState() == 400 && var_byte_arr_a[l] == 1) {
                j = l;
                k = m;
            }
            aeii.c.f();
            this.var_boolean_e = false;
        }
    }

    public static final void a(Graphics graphics, String string, int n, int n2, int n3, int n4) {
        if ((n4 & 8) != 0) {
            n -= aeii.c.a((byte)n3, string);
        } else if ((n4 & 1) != 0) {
            n -= aeii.c.a((byte)n3, string) / 2;
        }
        if ((n4 & 0x20) != 0) {
            n2 -= aeii.c.a((byte)n3);
        } else if ((n4 & 2) != 0) {
            n2 -= aeii.c.a((byte)n3) / 2;
        }
        aeii.c.a(graphics, string, n, n2, n3);
    }

    public static final void a(Graphics graphics, String string, int n, int n2, int n3) {
        int n4 = string.length();
        for (int i = 0; i < n4; ++i) {
            char c2 = string.charAt(i);
            if (c2 < var_short_arr_a[n3] || c2 > var_short_arr_b[n3]) continue;
            byte by = var_byte_arr_arr_a[n3][c2 - var_short_arr_a[n3]];
            if (by != -1) {
                var_a_a_a_g_arr_a[n3].c(by);
                var_a_a_a_g_arr_a[n3].a(graphics, n, n2);
                n += aeii.c.var_a_a_a_g_arr_a[n3].o;
                continue;
            }
            Object object = new byte[]{(byte)c2};
            object = new String((byte[])object);
            graphics.drawString((String)object, n, n2, 20);
            n += graphics.getFont().stringWidth((String)object);
        }
    }

    public static final void b(Graphics graphics, String string, int n, int n2, int n3) {
        graphics.drawString(string, n, n2 - 2, n3);
    }

    public static void a(Displayable displayable) {
        var_javax_microedition_lcdui_Display_a.setCurrent(displayable);
    }

    public final void a(f f2) {
        this.b();
        f2.void_a();
        this.var_aeii_f_a = f2;
    }

    public final void void_a() {
        this.repaint();
        this.serviceRepaints();
    }

    public final void paint(Graphics graphics) {
        if (this.var_boolean_d) {
            graphics.setColor(0xFFFFFF);
            graphics.fillRect(0, 0, var_int_d, var_int_e);
            graphics.setFont(var_javax_microedition_lcdui_Font_b);
            graphics.setColor(0);
            graphics.drawString(aeii.f.java_lang_String_a(58), var_int_d / 2, var_int_e / 2 - 1, 33);
        } else {
            this.var_aeii_f_a.a(graphics);
        }
        if (var_boolean_a) {
            graphics.setFont(var_javax_microedition_lcdui_Font_b);
            aeii.g.a(graphics, this.var_java_lang_String_a, 2, 2, 20, -1, 0);
        }
    }

    public final int getGameAction(int n) {
        switch (n) {
            case -11: {
                return 0;
            }
            case -7: {
                return 2048;
            }
            case -6: {
                return 1024;
            }
            case 35: {
                return 8192;
            }
            case 42: {
                return 16384;
            }
            case 48: {
                return 32;
            }
            case 49: {
                return 64;
            }
            case 50: {
                return 1;
            }
            case 51: {
                return 128;
            }
            case 52: {
                return 4;
            }
            case 53: {
                return 16;
            }
            case 54: {
                return 8;
            }
            case 55: {
                return 256;
            }
            case 56: {
                return 2;
            }
            case 57: {
                return 512;
            }
        }
        try {
            switch (super.getGameAction(n)) {
                case 1: {
                    return 1;
                }
                case 2: {
                    return 4;
                }
                default: {
                    break;
                }
                case 5: {
                    return 8;
                }
                case 6: {
                    return 2;
                }
                case 8: {
                    return 16;
                }
            }
        }
        catch (Exception exception) {}
        return 4096;
    }

    public final String java_lang_String_a(int n) {
        int n2 = 0;
        switch (n) {
            case 1: {
                n2 = 50;
                break;
            }
            case 2: {
                n2 = 56;
                break;
            }
            case 4: {
                n2 = 52;
                break;
            }
            case 8: {
                n2 = 54;
                break;
            }
            case 16: {
                n2 = 53;
                break;
            }
            case 32: {
                n2 = 48;
                break;
            }
            case 64: {
                n2 = 49;
                break;
            }
            case 128: {
                n2 = 51;
                break;
            }
            case 256: {
                n2 = 55;
                break;
            }
            case 512: {
                n2 = 57;
            }
        }
        return super.getKeyName(n2);
    }

    public final void keyPressed(int n) {
        n = this.getGameAction(n);
        this.void_a(n);
    }

    public final boolean boolean_a() {
        return this.g != 0;
    }

    public final void b() {
        this.i = 0;
        this.g = 0;
        this.h = 0;
    }

    public final boolean boolean_a(int n) {
        boolean bl = (this.h & n) != 0;
        this.h &= ~n;
        return bl;
    }

    public final boolean boolean_b(int n) {
        return (this.g & n) != 0;
    }

    public final void keyReleased(int n) {
        this.void_b(this.getGameAction(n));
    }

    public final boolean boolean_c(int n) {
        return this.i == n && System.currentTimeMillis() - this.var_long_a >= 400L;
    }

    public final void void_a(int n) {
        this.i = n;
        this.var_long_a = System.currentTimeMillis();
        this.g |= n;
        this.h |= n;
    }

    public final void void_b(int n) {
        if (n == this.i) {
            this.i = 0;
        }
        this.g &= ~n;
    }

    private void void_a(String string) {
        this.var_boolean_c = false;
        Form form = new Form("Fatal error!");
        form.append(string);
        string = new Command("Exit", 7, 1);
        form.addCommand((Command)string);
        form.setCommandListener((CommandListener)this);
        var_javax_microedition_lcdui_Display_a.setCurrent((Displayable)form);
    }

    public final void c() {
        this.var_boolean_c = false;
    }

    public static final void d() {
        aeii.c.var_a_a_a_g_arr_a[0] = new a.a.a.g("chars");
        aeii.c.var_a_a_a_g_arr_a[1] = new a.a.a.g("lchars");
    }

    public final void void_c(int n) {
        if (n <= 0) {
            this.var_int_f = 0;
            return;
        }
        this.var_int_f = 1000 / n;
    }

    public final void run() {
        try {
            this.void_a();
            var_java_lang_String_arr_a = new String[]{aeii.f.java_lang_String_a(26), aeii.f.java_lang_String_a(28), aeii.f.java_lang_String_a(25), aeii.f.java_lang_String_a(24)};
            g g2 = new g();
            this.void_a();
            this.var_aeii_f_a = g2;
            this.var_boolean_d = false;
            this.var_boolean_c = true;
            g2.d();
            var_int_arr_a = new int[var_int_d * var_int_e];
            long l = System.currentTimeMillis();
            int n = 0;
            while (this.var_boolean_c) {
                long l2 = System.currentTimeMillis();
                if (this.isShown() && !var_boolean_f) {
                    if (j >= 0) {
                        aeii.c.b(j, k);
                        if (var_javax_microedition_media_Player_a != null && var_javax_microedition_media_Player_a.getState() == 400) {
                            j = -1;
                        }
                    }
                    this.var_aeii_f_a.b();
                    this.void_a();
                }
                if (var_boolean_a) {
                    int n2 = (int)(System.currentTimeMillis() - l);
                    ++n;
                    if (n2 >= 1000) {
                        this.var_java_lang_String_a = Integer.toString(n * 1000 / n2);
                        l = System.currentTimeMillis();
                        n = 0;
                    }
                }
                int n3 = (int)(System.currentTimeMillis() - l2);
                if ((n3 = this.var_int_f - n3) <= 0) continue;
                try {
                    Thread.sleep(n3);
                }
                catch (Exception exception) {}
            }
            if (Main.var_aeii_Main_a != null) {
                Main.var_aeii_Main_a.notifyDestroyed();
                Main.var_aeii_Main_a.destroyApp(true);
            }
            return;
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            this.void_a(exception2.toString());
            return;
        }
    }

    public static final void d(int n) {
        try {
            if (var_boolean_arr_a[1]) {
                var_javax_microedition_lcdui_Display_a.vibrate(n << 2);
            }
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public static final void e() {
        var_javax_microedition_media_Player_arr_a = new Player[var_java_lang_String_arr_b.length];
        var_boolean_arr_b = new boolean[var_java_lang_String_arr_b.length];
    }

    public static final void e(int n) {
        try {
            aeii.c.var_boolean_arr_b[n] = false;
            InputStream inputStream = aeii.c.java_io_InputStream_a(var_java_lang_String_arr_b[n] + ".mid");
            aeii.c.var_javax_microedition_media_Player_arr_a[n] = Manager.createPlayer((InputStream)inputStream, (String)"audio/midi");
            var_javax_microedition_media_Player_arr_a[n].realize();
            var_javax_microedition_media_Player_arr_a[n].prefetch();
            aeii.c.var_boolean_arr_b[n] = true;
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public static final void void_a(int n, int n2) {
        aeii.c.b(n, n2);
    }

    public static final void f() {
        try {
            if (var_javax_microedition_media_Player_a != null) {
                var_javax_microedition_media_Player_a.stop();
                var_javax_microedition_media_Player_a = null;
                l = -1;
            }
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public static final void b(int n, int n2) {
        try {
            block9: {
                block8: {
                    if (!var_boolean_arr_b[n]) {
                        return;
                    }
                    if (var_javax_microedition_media_Player_a != null) {
                        var_javax_microedition_media_Player_a.stop();
                    }
                    if (var_byte_arr_a[n] != 1 || !var_boolean_arr_a[0]) break block8;
                    if (n2 == 0) {
                        n2 = -1;
                    }
                    if (var_boolean_f) break block9;
                    var_javax_microedition_media_Player_a = var_javax_microedition_media_Player_arr_a[n];
                    var_javax_microedition_media_Player_a.setLoopCount(n2);
                    var_javax_microedition_media_Player_a.start();
                    l = n;
                    m = n2;
                }
                return;
            }
            j = n;
            k = n2;
        }
        catch (Exception exception) {
            return;
        }
    }

    public static final void f(int n) {
        try {
            if (!var_boolean_arr_b[10]) {
                return;
            }
            if (var_javax_microedition_media_Player_a == var_javax_microedition_media_Player_arr_a[10]) {
                var_javax_microedition_media_Player_a.stop();
                var_javax_microedition_media_Player_a = null;
                l = -1;
            }
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public static final void g() {
        if (var_java_lang_String_arr_c == null) {
            try {
                int n;
                DataInputStream dataInputStream = new DataInputStream(((Object)((Object)Main.var_aeii_Main_a)).getClass().getResourceAsStream("/res.pak"));
                short s = dataInputStream.readShort();
                int n2 = dataInputStream.readShort();
                var_java_lang_String_arr_c = new String[n2];
                int[] nArray = new int[n2];
                int[] nArray2 = new int[n2];
                for (n = 0; n < n2; ++n) {
                    aeii.c.var_java_lang_String_arr_c[n] = dataInputStream.readUTF();
                    nArray[n] = dataInputStream.readInt() + s;
                    nArray2[n] = dataInputStream.readShort();
                }
                var_byte_arr_arr_b = new byte[var_java_lang_String_arr_c.length][];
                for (n = 0; n < var_java_lang_String_arr_c.length; ++n) {
                    aeii.c.var_byte_arr_arr_b[n] = new byte[nArray2[n]];
                    dataInputStream.readFully(var_byte_arr_arr_b[n]);
                }
                dataInputStream.close();
                return;
            }
            catch (Exception exception) {}
        }
    }

    public static final byte[] byte_arr_a(String object) {
        if (var_java_lang_String_arr_c != null) {
            for (int i = 0; i < var_java_lang_String_arr_c.length; ++i) {
                if (!((String)object).equals(var_java_lang_String_arr_c[i])) continue;
                return var_byte_arr_arr_b[i];
            }
        }
        try {
            Object object2 = ((Object)((Object)Main.var_aeii_Main_a)).getClass().getResourceAsStream("/res/" + (String)object);
            if (object2 != null) {
                int n;
                object = new ByteArrayOutputStream();
                while ((n = ((InputStream)object2).read()) >= 0) {
                    ((ByteArrayOutputStream)object).write(n);
                }
                ((InputStream)object2).close();
                object2 = ((ByteArrayOutputStream)object).toByteArray();
                ((ByteArrayOutputStream)object).close();
                return object2;
            }
        }
        catch (IOException iOException) {}
        return null;
    }

    public static final InputStream java_io_InputStream_a(String string) {
        if (var_java_lang_String_arr_c != null) {
            for (int i = 0; i < var_java_lang_String_arr_c.length; ++i) {
                if (!string.equals(var_java_lang_String_arr_c[i])) continue;
                return new ByteArrayInputStream(var_byte_arr_arr_b[i]);
            }
        }
        return ((Object)((Object)Main.var_aeii_Main_a)).getClass().getResourceAsStream("/res/" + string);
    }

    public final void commandAction(Command command, Displayable displayable) {
        Main.var_aeii_Main_a.notifyDestroyed();
    }

    static {
        var_javax_microedition_lcdui_Font_a = Font.getFont((int)0, (int)1, (int)8);
        var_javax_microedition_lcdui_Font_b = Font.getFont((int)0, (int)1, (int)8);
        var_int_a = var_javax_microedition_lcdui_Font_b.getBaselinePosition();
        var_int_b = var_int_a + 6;
        var_javax_microedition_lcdui_Font_a.getBaselinePosition();
        var_int_c = var_javax_microedition_lcdui_Font_a.getHeight();
        var_short_arr_a = new short[]{45, 43};
        var_short_arr_b = new short[]{57, 57};
        var_byte_arr_arr_a = new byte[][]{{10, 11, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, {12, -1, 11, -1, 10, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9}};
        var_a_a_a_g_arr_a = new a.a.a.g[2];
        var_java_util_Random_a = new Random();
        var_boolean_arr_a = new boolean[]{true, true, true, true};
        j = -1;
        var_boolean_f = false;
        var_java_lang_String_arr_b = new String[]{"main_theme", "bg_story", "bg_good", "bg_bad", "battle_good", "battle_bad", "victory", "gameover", "game_complete"};
        var_byte_arr_a = new byte[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    }
}

