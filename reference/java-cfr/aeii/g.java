/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.io.Connector
 *  javax.microedition.io.HttpConnection
 *  javax.microedition.lcdui.ChoiceGroup
 *  javax.microedition.lcdui.Command
 *  javax.microedition.lcdui.CommandListener
 *  javax.microedition.lcdui.Displayable
 *  javax.microedition.lcdui.Form
 *  javax.microedition.lcdui.Gauge
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 *  javax.microedition.lcdui.ImageItem
 *  javax.microedition.lcdui.Item
 *  javax.microedition.lcdui.ItemStateListener
 *  javax.microedition.lcdui.TextBox
 *  javax.microedition.lcdui.TextField
 */
package aeii;

import aeii.Main;
import aeii.a;
import aeii.b;
import aeii.c;
import aeii.d;
import aeii.e;
import aeii.f;
import aeii.h;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;

public final class g
extends f
implements Runnable,
CommandListener,
ItemStateListener {
    private static String[] var_java_lang_String_arr_b;
    private static String[] var_java_lang_String_arr_c;
    private static String[] var_java_lang_String_arr_d;
    private String var_java_lang_String_a = "?";
    public static byte var_byte_a;
    public int var_int_a;
    public int var_int_b;
    public int var_int_c;
    private int var_int_p;
    private int var_int_q = 0;
    private static String[] var_java_lang_String_arr_e;
    private static int[] var_int_arr_c;
    private boolean[] var_boolean_arr_a;
    private static int[] var_int_arr_d;
    private static int[] var_int_arr_e;
    private int var_int_r;
    private int var_int_s;
    private byte var_byte_c;
    public static int var_int_d;
    public static int var_int_e;
    private String[] var_java_lang_String_arr_f;
    private String[] var_java_lang_String_arr_g;
    public d[] var_aeii_d_arr_a;
    private String[] var_java_lang_String_arr_h;
    private d[] var_aeii_d_arr_c;
    private String[] var_java_lang_String_arr_i;
    private String[] var_java_lang_String_arr_j;
    private byte[] var_byte_arr_f;
    private byte[] var_byte_arr_g;
    private byte[] var_byte_arr_h;
    private static byte[][] var_byte_arr_arr_c;
    private static byte[] var_byte_arr_i;
    private long var_long_b;
    private long var_long_c;
    public static final String[] var_java_lang_String_arr_a;
    private static int[] var_int_arr_f;
    private static int[] var_int_arr_g;
    private static int[] var_int_arr_h;
    public a.a.a.g[][] var_a_a_a_g_arr_arr_a;
    private static byte[] var_byte_arr_j;
    public static byte[] var_byte_arr_a;
    public static byte[] var_byte_arr_b;
    public d[] var_aeii_d_arr_b;
    private byte[] var_byte_arr_k;
    public byte[] var_byte_arr_c;
    private int var_int_t;
    private int var_int_u;
    private int var_int_v;
    private int var_int_w;
    public int var_int_f;
    public int var_int_g;
    private d var_aeii_d_a;
    private d[] var_aeii_d_arr_d;
    private a.a.a.g var_a_a_a_g_q;
    private a.a.a.g var_a_a_a_g_r;
    public a.a.a.g var_a_a_a_g_a;
    public a.a.a.g var_a_a_a_g_b;
    public a.a.a.g var_a_a_a_g_c;
    public a.a.a.g var_a_a_a_g_d;
    public a.a.a.g var_a_a_a_g_e;
    public a.a.a.g var_a_a_a_g_f;
    public a.a.a.g var_a_a_a_g_g;
    private a.a.a.g var_a_a_a_g_s;
    public a.a.a.g var_a_a_a_g_h;
    public a.a.a.g var_a_a_a_g_i;
    public int var_int_h;
    public int var_int_i;
    public byte[][] var_byte_arr_arr_a;
    private byte var_byte_d;
    private byte var_byte_e;
    public long var_long_a;
    private int var_int_x;
    private int var_int_y;
    private int var_int_z;
    private a[] var_aeii_a_arr_a;
    private a var_aeii_a_b;
    private int var_int_A;
    private int var_int_B;
    public byte[][] var_byte_arr_arr_b;
    private boolean var_boolean_b;
    private boolean var_boolean_c;
    private boolean var_boolean_d;
    public Vector var_java_util_Vector_a;
    private Vector var_java_util_Vector_b;
    private int var_int_C;
    private int var_int_D;
    private long var_long_d;
    private byte var_byte_f;
    private byte[] var_byte_arr_l;
    public byte[] var_byte_arr_d;
    public byte[] var_byte_arr_e;
    public byte var_byte_b;
    private short var_short_a;
    private a[] var_aeii_a_arr_b;
    public a[][] var_aeii_a_arr_arr_a;
    public int[] var_int_arr_a;
    public int[] var_int_arr_b;
    private byte[][] var_byte_arr_arr_d;
    private byte[] var_byte_arr_m;
    private e var_aeii_e_a;
    private Vector var_java_util_Vector_c;
    private Vector var_java_util_Vector_d;
    private a var_aeii_a_c;
    private a var_aeii_a_d;
    private long var_long_e;
    private a var_aeii_a_e;
    private byte var_byte_g;
    private long var_long_f;
    private int var_int_E;
    private boolean var_boolean_e;
    private boolean var_boolean_f;
    private byte var_byte_h;
    private d var_aeii_d_b;
    private d var_aeii_d_c;
    private d var_aeii_d_d;
    private d var_aeii_d_e;
    private int var_int_F;
    private boolean var_boolean_g;
    private int var_int_G;
    private int var_int_H;
    private int var_int_I;
    private long var_long_g;
    private a var_aeii_a_f;
    private a var_aeii_a_g;
    private boolean var_boolean_h;
    private long var_long_h;
    private boolean var_boolean_i;
    private long var_long_i;
    private int var_int_J;
    private int var_int_K;
    private d[] var_aeii_d_arr_e;
    private boolean var_boolean_j;
    private boolean var_boolean_k;
    private static int[] var_int_arr_i;
    private static int[] var_int_arr_j;
    private int var_int_L;
    private e var_aeii_e_b;
    private e var_aeii_e_c;
    private e var_aeii_e_d;
    private e var_aeii_e_e;
    public int var_int_n;
    private e var_aeii_e_f;
    private e var_aeii_e_g;
    private e var_aeii_e_h;
    private boolean var_boolean_l;
    private int var_int_M;
    private byte[][] var_byte_arr_arr_e;
    private byte[][] var_byte_arr_arr_f;
    public a.a.a.g var_a_a_a_g_j;
    public a.a.a.g var_a_a_a_g_k;
    private a var_aeii_a_h;
    public a.a.a.g var_a_a_a_g_l;
    public a.a.a.g var_a_a_a_g_m;
    private int var_int_N;
    private int var_int_O;
    private int P;
    private boolean var_boolean_m;
    private int Q;
    private boolean var_boolean_n;
    public a.a.a.g var_a_a_a_g_n;
    private a.a.a.g[] var_a_a_a_g_arr_b;
    public a.a.a.g var_a_a_a_g_o;
    private e var_aeii_e_i;
    private e var_aeii_e_j;
    private e var_aeii_e_k;
    private e var_aeii_e_l;
    private e[] var_aeii_e_arr_a;
    private e[] var_aeii_e_arr_b;
    private e var_aeii_e_m;
    private e[] var_aeii_e_arr_c;
    private a.a.a.g var_a_a_a_g_t;
    private int R;
    private boolean var_boolean_o;
    private boolean var_boolean_p;
    private boolean var_boolean_q;
    private Vector var_java_util_Vector_e;
    private a.a.a.g var_a_a_a_g_u;
    private int S;
    public a.a.a.g[] var_a_a_a_g_arr_a;
    private e var_aeii_e_n;
    private e var_aeii_e_o;
    private e var_aeii_e_p;
    private e var_aeii_e_q;
    public a.a.a.g var_a_a_a_g_p;
    private a.a.a.g var_a_a_a_g_v;
    private a var_aeii_a_i;
    private int T;
    private boolean var_boolean_r;
    private boolean var_boolean_s;
    private String var_java_lang_String_b;
    private String[] var_java_lang_String_arr_k;
    private byte[] var_byte_arr_n;
    private int[] var_int_arr_k;
    private e var_aeii_e_r;
    private e var_aeii_e_s;
    private e var_aeii_e_t;
    private e var_aeii_e_u;
    private e var_aeii_e_v;
    private e var_aeii_e_w;
    private e var_aeii_e_x;
    private byte var_byte_i;
    private byte[] var_byte_arr_o;
    private int U;
    private String[] var_java_lang_String_arr_l;
    private int[] var_int_arr_l;
    private String[] var_java_lang_String_arr_m;
    private String[] var_java_lang_String_arr_n;
    private e var_aeii_e_y;
    private e var_aeii_e_z;
    private e var_aeii_e_A;
    private e var_aeii_e_B;
    private e var_aeii_e_C;
    private e var_aeii_e_D;
    private e var_aeii_e_E;
    private e var_aeii_e_F;
    private int V;
    private String var_java_lang_String_c;
    private int W;
    private boolean var_boolean_t;
    private boolean var_boolean_u;
    private boolean var_boolean_v;
    private d var_aeii_d_f;
    private Image[][] var_javax_microedition_lcdui_Image_arr_arr_a;
    private int X;
    private a var_aeii_a_j;
    private int Y;
    private int Z;
    private boolean var_boolean_w;
    private int aa;
    private int ab;
    private int ac;
    private int ad;
    private int ae;
    private d var_aeii_d_g;
    private String[] var_java_lang_String_arr_o;
    private boolean var_boolean_x;
    private byte var_byte_j;
    private int af;
    private int ag;
    private int ah;
    private int ai;
    private int aj;
    private int ak;
    private int al;
    private int am;
    private int an;
    private static byte[] var_byte_arr_p;
    private byte var_byte_k;
    private int ao;
    private int ap;
    private a var_aeii_a_k;
    private a var_aeii_a_l;
    private a var_aeii_a_m;
    private int aq;
    private long var_long_j;
    private a[] var_aeii_a_arr_c;
    private a[] var_aeii_a_arr_d;
    private byte[] var_byte_arr_q;
    private int[][] var_int_arr_arr_a;
    private int[] var_int_arr_m;
    private int ar;
    private byte[][] var_byte_arr_arr_g;
    private int as;
    private int at;
    private int au;
    private int av;
    private int aw;
    private Vector var_java_util_Vector_f;
    private boolean var_boolean_y;
    private int ax;
    private e var_aeii_e_G;
    private a var_aeii_a_n;
    private a var_aeii_a_o;
    private a var_aeii_a_p;
    private a var_aeii_a_q;
    private a var_aeii_a_r;
    public a var_aeii_a_a;
    private int ay;
    private String[][][] var_java_lang_String_arr_arr_arr_a;
    private long var_long_k;
    private int az;
    private boolean var_boolean_z;
    private boolean var_boolean_A;
    private int aA;
    private int aB;
    private int aC;
    private h var_aeii_h_a;
    private h var_aeii_h_b;
    private long var_long_l;
    private boolean var_boolean_B;
    private Vector var_java_util_Vector_g;
    private boolean var_boolean_C;
    private long var_long_m;
    private long var_long_n;
    private boolean var_boolean_D;
    private boolean var_boolean_E;
    private boolean var_boolean_F;
    public boolean var_boolean_a;
    public int var_int_o;
    private int aD;
    private int aE;
    private String[] var_java_lang_String_arr_p;
    private String[] var_java_lang_String_arr_q;
    private String[] var_java_lang_String_arr_r;
    private String[] var_java_lang_String_arr_s;
    private String[] var_java_lang_String_arr_t;
    private int[] var_int_arr_n;
    private String var_java_lang_String_d;
    private String var_java_lang_String_e;
    private String var_java_lang_String_f;
    private ByteArrayOutputStream var_java_io_ByteArrayOutputStream_a;
    private DataOutputStream var_java_io_DataOutputStream_a;
    private int aF;
    private boolean var_boolean_G;
    private f var_aeii_f_a;
    private int aG;
    private e var_aeii_e_H;
    private e var_aeii_e_I;
    private e var_aeii_e_J;
    private e var_aeii_e_K;
    private b var_aeii_b_a;
    private String[] var_java_lang_String_arr_u;
    private byte var_byte_l;
    private byte var_byte_m;
    private a var_aeii_a_s;
    private byte var_byte_n;
    private e var_aeii_e_L;
    private e var_aeii_e_M;
    private e var_aeii_e_N;
    private e var_aeii_e_O;
    private int[] var_int_arr_o;
    private int[] var_int_arr_p;
    private boolean[] var_boolean_arr_b;
    private Form var_javax_microedition_lcdui_Form_a;
    private Form var_javax_microedition_lcdui_Form_b;
    private Form var_javax_microedition_lcdui_Form_c;
    private Form var_javax_microedition_lcdui_Form_d;
    private Form var_javax_microedition_lcdui_Form_e;
    private Form var_javax_microedition_lcdui_Form_f;
    private TextField var_javax_microedition_lcdui_TextField_a;
    private TextField var_javax_microedition_lcdui_TextField_b;
    private TextField var_javax_microedition_lcdui_TextField_c;
    private ChoiceGroup var_javax_microedition_lcdui_ChoiceGroup_a;
    private TextField var_javax_microedition_lcdui_TextField_d;
    private Gauge var_javax_microedition_lcdui_Gauge_a;
    private ImageItem var_javax_microedition_lcdui_ImageItem_a;
    private TextField var_javax_microedition_lcdui_TextField_e;
    private TextField var_javax_microedition_lcdui_TextField_f;
    private TextField var_javax_microedition_lcdui_TextField_g;
    private TextField var_javax_microedition_lcdui_TextField_h;
    private TextBox var_javax_microedition_lcdui_TextBox_a;
    private Gauge var_javax_microedition_lcdui_Gauge_b;
    private Command var_javax_microedition_lcdui_Command_a;
    private Command var_javax_microedition_lcdui_Command_b;
    private Command var_javax_microedition_lcdui_Command_c;
    private Command var_javax_microedition_lcdui_Command_d;
    private Command var_javax_microedition_lcdui_Command_e;
    private Command var_javax_microedition_lcdui_Command_f;

    public g() {
        var_java_lang_String_arr_b = new String[]{".."};
        var_java_lang_String_arr_c = new String[]{aeii.f.java_lang_String_a(300), ".."};
        var_java_lang_String_arr_d = new String[]{aeii.f.java_lang_String_a(305), aeii.f.java_lang_String_a(306), aeii.f.java_lang_String_a(307), aeii.f.java_lang_String_a(303), aeii.f.java_lang_String_a(70), aeii.f.java_lang_String_a(293), aeii.f.java_lang_String_a(60)};
        this.var_byte_n = 0;
        this.var_javax_microedition_lcdui_Command_a = new Command(aeii.f.java_lang_String_a(12), 4, 1);
        this.var_javax_microedition_lcdui_Command_b = new Command(aeii.f.java_lang_String_a(13), 3, 5);
        this.var_javax_microedition_lcdui_Command_c = new Command(aeii.f.java_lang_String_a(301), 4, 3);
        this.var_javax_microedition_lcdui_Command_d = new Command(aeii.f.java_lang_String_a(302), 4, 4);
        this.var_javax_microedition_lcdui_Command_e = new Command(aeii.f.java_lang_String_a(304), 4, 2);
        this.var_javax_microedition_lcdui_Command_f = new Command(aeii.f.java_lang_String_a(316), 4, 2);
        this.var_java_lang_String_arr_f = aeii.c.var_boolean_a ? new String[]{aeii.f.java_lang_String_a(66), aeii.f.java_lang_String_a(70), aeii.f.java_lang_String_a(71), aeii.f.java_lang_String_a(4), aeii.f.java_lang_String_a(314), aeii.f.java_lang_String_a(60)} : new String[]{aeii.f.java_lang_String_a(66), aeii.f.java_lang_String_a(70), aeii.f.java_lang_String_a(71), aeii.f.java_lang_String_a(4), aeii.f.java_lang_String_a(60)};
        this.var_java_lang_String_arr_g = new String[]{aeii.f.java_lang_String_a(63), aeii.f.java_lang_String_a(67), aeii.f.java_lang_String_a(68), aeii.f.java_lang_String_a(62), aeii.f.java_lang_String_a(69), aeii.f.java_lang_String_a(61), aeii.f.java_lang_String_a(64)};
        this.var_aeii_d_arr_a = new d[this.var_java_lang_String_arr_g.length];
        this.var_java_lang_String_arr_h = new String[]{aeii.f.java_lang_String_a(1), aeii.f.java_lang_String_a(2), aeii.f.java_lang_String_a(5), aeii.f.java_lang_String_a(3), aeii.f.java_lang_String_a(6), aeii.f.java_lang_String_a(8), aeii.f.java_lang_String_a(7), aeii.f.java_lang_String_a(9), aeii.f.java_lang_String_a(10), aeii.f.java_lang_String_a(11), aeii.f.java_lang_String_a(4)};
        this.var_aeii_d_arr_c = new d[this.var_java_lang_String_arr_h.length];
        this.var_java_lang_String_arr_i = new String[]{aeii.f.java_lang_String_a(35), aeii.f.java_lang_String_a(36), aeii.f.java_lang_String_a(37)};
        this.var_java_lang_String_arr_j = new String[]{aeii.f.java_lang_String_a(29), aeii.f.java_lang_String_a(30)};
        this.var_byte_arr_f = new byte[]{0, 6, 5, 7, 8, 9};
        this.var_byte_arr_g = new byte[]{0, 6, 5, 7, 8, 9};
        this.var_byte_arr_h = new byte[]{1, 2, 3, 4};
        this.var_long_b = 0L;
        this.var_boolean_b = false;
        this.var_boolean_c = false;
        this.var_boolean_d = true;
        this.var_java_util_Vector_a = new Vector();
        this.var_byte_f = (byte)2;
        this.var_byte_arr_l = new byte[5];
        this.var_byte_arr_d = new byte[4];
        this.var_byte_arr_e = new byte[4];
        this.var_byte_b = 0;
        this.var_int_arr_b = new int[4];
        this.var_byte_arr_arr_d = new byte[4][2];
        this.var_byte_arr_m = new byte[4];
        this.var_java_util_Vector_c = new Vector();
        this.var_java_util_Vector_d = new Vector();
        this.var_boolean_e = false;
        this.var_boolean_f = false;
        this.var_boolean_g = false;
        this.var_boolean_h = true;
        this.var_boolean_i = false;
        this.var_boolean_j = false;
        this.var_int_L = -1;
        this.var_int_n = 8;
        this.var_boolean_l = false;
        this.var_boolean_m = true;
        this.var_a_a_a_g_arr_b = new a.a.a.g[0];
        this.var_java_util_Vector_e = new Vector(2);
        this.T = 0;
        this.var_boolean_r = true;
        this.var_java_lang_String_arr_m = new String[]{aeii.f.java_lang_String_a(46), aeii.f.java_lang_String_a(47), aeii.f.java_lang_String_a(291)};
        this.var_java_lang_String_arr_n = new String[]{aeii.f.java_lang_String_a(289), aeii.f.java_lang_String_a(298), aeii.f.java_lang_String_a(48), aeii.f.java_lang_String_a(49)};
        this.var_boolean_t = true;
        this.var_boolean_u = true;
        this.var_boolean_v = true;
        new StringBuffer();
        this.var_aeii_a_j = null;
        this.Y = 12;
        this.Z = 1;
        this.ai = 0;
        this.ak = 24;
        this.al = 8;
        this.am = this.al >> 1;
        this.var_byte_k = 0;
        this.var_boolean_y = false;
        this.var_aeii_a_r = null;
        this.var_aeii_a_a = null;
        this.var_boolean_z = false;
        this.var_boolean_A = false;
        this.aA = -1;
        this.aB = -1;
        this.aC = 0;
        this.var_java_util_Vector_g = new Vector();
        this.var_boolean_C = false;
        this.var_java_lang_String_d = "Macrospace";
        this.var_java_lang_String_e = "msaeii";
        this.var_java_lang_String_f = "http://msaeii.scores.macrospace.com/connectx/in";
        this.var_byte_h = (byte)4;
    }

    public final void d() {
        int n;
        this.void_b(0);
        aeii.c.g();
        this.void_b(18);
        aeii.c.e();
        for (int i = 0; i < aeii.c.var_java_lang_String_arr_b.length; ++i) {
            aeii.c.e(i);
            this.void_b(i + 19);
        }
        this.void_b(28);
        aeii.e.var_aeii_g_a = this;
        this.void_b(29);
        aeii.f.c();
        this.void_b(30);
        aeii.c.d();
        this.void_b(32);
        this.var_aeii_d_arr_a = new a.a.a.g((String)"action_icons").var_aeii_d_arr_a;
        this.void_b(34);
        this.var_aeii_d_arr_c = new a.a.a.g((String)"menu_icons").var_aeii_d_arr_a;
        this.void_b(36);
        this.var_a_a_a_g_l = new a.a.a.g("hud_icons");
        this.void_b(38);
        this.var_a_a_a_g_m = new a.a.a.g("hud_icons_2");
        this.void_b(40);
        this.var_a_a_a_g_b = new a.a.a.g("arrow");
        this.void_b(42);
        this.var_a_a_a_g_a = new a.a.a.g("side_arrow");
        this.void_b(44);
        this.var_a_a_a_g_c = new a.a.a.g("buttons");
        this.void_b(46);
        this.var_a_a_a_g_d = new a.a.a.g("menu");
        this.void_b(48);
        this.var_a_a_a_g_j = new a.a.a.g("big_circle");
        this.void_b(50);
        this.var_a_a_a_g_k = new a.a.a.g("small_circle");
        this.void_b(52);
        this.var_a_a_a_g_h = new a.a.a.g("small_spark");
        this.void_b(54);
        this.var_a_a_a_g_t = new a.a.a.g("alpha");
        this.void_b(56);
        try {
            this.var_aeii_d_f = new d("gameover");
        }
        catch (Exception exception) {}
        this.void_b(58);
        this.var_aeii_d_b = new d("ms_logo");
        this.void_b(62);
        InputStream inputStream = aeii.c.java_io_InputStream_a("tiles0.prop");
        if (inputStream != null) {
            String string;
            while ((string = aeii.c.a(inputStream)) != null) {
                int n2 = string.indexOf(59);
                if (n2 >= 0) {
                    string = string.substring(0, n2);
                }
                if ((string = string.trim()).length() == 0) continue;
                String[] stringArray = aeii.c.a(string, ' ');
                if (stringArray[0].equalsIgnoreCase("TypeCount")) {
                    n2 = Integer.parseInt(stringArray[1]);
                    var_byte_arr_b = new byte[n2];
                    var_byte_arr_a = new byte[n2];
                    aeii.h.var_java_lang_String_arr_a = new String[n2];
                    aeii.h.var_java_lang_String_arr_b = new String[n2];
                    continue;
                }
                if (stringArray[0].equalsIgnoreCase("TypeDef")) {
                    n2 = Integer.parseInt(stringArray[1]);
                    aeii.g.var_byte_arr_b[n2] = (byte)Integer.parseInt(stringArray[2]);
                    aeii.g.var_byte_arr_a[n2] = (byte)Integer.parseInt(stringArray[3]);
                    aeii.h.var_java_lang_String_arr_a[n2] = stringArray[4];
                    aeii.h.var_java_lang_String_arr_b[n2] = stringArray[5];
                    continue;
                }
                if (stringArray[0].equalsIgnoreCase("TileCount")) {
                    this.var_byte_arr_k = new byte[Integer.parseInt(stringArray[1])];
                    this.var_byte_arr_c = new byte[this.var_byte_arr_k.length];
                    continue;
                }
                if (!stringArray[0].equalsIgnoreCase("TileDef")) continue;
                n2 = Integer.parseInt(stringArray[1]);
                this.var_byte_arr_k[n2] = (byte)Integer.parseInt(stringArray[2]);
                this.var_byte_arr_c[n2] = (byte)Integer.parseInt(stringArray[3]);
            }
            inputStream.close();
        } else {
            inputStream = new DataInputStream(aeii.c.java_io_InputStream_a("tiles0.bin"));
            int n3 = ((DataInputStream)inputStream).readShort();
            ((DataInputStream)inputStream).readShort();
            this.var_byte_arr_k = new byte[n3];
            for (int i = 0; i < n3; ++i) {
                this.var_byte_arr_k[i] = ((DataInputStream)inputStream).readByte();
            }
            ((FilterInputStream)inputStream).close();
        }
        this.void_b(64);
        a.a.a.g g2 = new a.a.a.g("stiles0");
        this.var_aeii_d_arr_b = g2.var_aeii_d_arr_a;
        this.void_b(70);
        this.var_a_a_a_g_n = new a.a.a.g("mini_icons");
        this.void_b(72);
        this.var_aeii_d_arr_d = new a.a.a.g((String)"tiles0").var_aeii_d_arr_a;
        this.var_int_a = ((f)this).j;
        this.var_int_b = ((f)this).k;
        this.var_int_c = this.var_int_a >> 1;
        this.var_int_p = this.var_int_b >> 1;
        this.var_int_F = 0;
        for (n = 0; n < 12; ++n) {
            aeii.g.var_java_lang_String_arr_e[n] = aeii.f.java_lang_String_a(n + 101);
        }
        aeii.g.f();
        this.void_b(74);
        try {
            this.var_int_q = aeii.c.byte_arr_a("settings", 1)[0];
        }
        catch (Exception exception) {}
        this.void_b(76);
        this.var_java_lang_String_arr_l = new String[0];
        this.var_int_arr_l = new int[0];
        try {
            byte[] byArray = aeii.c.byte_arr_a("settings", 2);
            inputStream = new DataInputStream(new ByteArrayInputStream(byArray));
            this.U = ((DataInputStream)inputStream).readInt();
            this.var_int_arr_l = new int[this.U];
            this.var_java_lang_String_arr_l = new String[this.U];
            for (int i = 0; i < this.U; ++i) {
                this.var_int_arr_l[i] = ((DataInputStream)inputStream).readInt();
                this.var_java_lang_String_arr_l[i] = ((DataInputStream)inputStream).readUTF();
            }
            ((FilterInputStream)inputStream).close();
        }
        catch (Exception exception) {}
        this.void_b(80);
        this.W = aeii.c.int_a("download");
        this.void_b(84);
        this.var_java_lang_String_arr_k = new String[10];
        this.var_byte_arr_n = new byte[10];
        this.var_int_arr_k = new int[10];
        for (n = 0; n < 10; ++n) {
            this.var_byte_arr_n[n] = -1;
            this.var_int_arr_k[n] = -1;
            byte[] byArray = null;
            try {
                byArray = aeii.c.byte_arr_a("save", n);
            }
            catch (Exception exception) {}
            if (byArray != null && byArray.length != 0) {
                inputStream = new DataInputStream(new ByteArrayInputStream(byArray));
                byte by = ((DataInputStream)inputStream).readByte();
                byte by2 = ((DataInputStream)inputStream).readByte();
                ((DataInputStream)inputStream).readByte();
                ((DataInputStream)inputStream).readByte();
                byte by3 = ((DataInputStream)inputStream).readByte();
                short s = ((DataInputStream)inputStream).readShort();
                ((FilterInputStream)inputStream).close();
                this.var_byte_arr_n[n] = by3;
                this.var_java_lang_String_arr_k[n] = this.java_lang_String_a((int)by, (int)by2, (int)s);
                this.var_int_arr_k[n] = by2;
                continue;
            }
            this.var_java_lang_String_arr_k[n] = "\n" + aeii.f.java_lang_String_a(79) + "\n ";
        }
        this.void_b(90);
        n = 0;
        String string = Main.var_aeii_Main_a.getAppProperty("ProvisionX-Highscore-gameCode");
        if (string != null) {
            this.var_java_lang_String_e = string.trim();
        }
        if ((string = Main.var_aeii_Main_a.getAppProperty("ProvisionX-Highscore-portalCode")) != null) {
            this.var_java_lang_String_d = string.trim();
        }
        if ((string = Main.var_aeii_Main_a.getAppProperty("ProvisionX-Highscore-Url")) != null) {
            this.var_java_lang_String_f = string.trim();
        }
        if ((string = Main.var_aeii_Main_a.getAppProperty("ms-highscoreUpload")) != null) {
            boolean bl = this.var_boolean_t = Integer.parseInt(string.trim()) == 1;
        }
        if ((string = Main.var_aeii_Main_a.getAppProperty("ms-skPos")) != null) {
            n = Integer.parseInt(string.trim());
        }
        if ((string = Main.var_aeii_Main_a.getAppProperty("MIDlet-Version")) != null) {
            this.var_java_lang_String_a = string.trim();
        }
        this.void_b(96);
        if (n == 1) {
            var_int_d = 2048;
            var_int_e = 1024;
        }
        this.void_b(100);
        aeii.c.b(0, 0);
        this.var_byte_h = 0;
    }

    private void void_b(int n) {
        this.X = n;
        aeii.f.var_aeii_c_a.void_a();
    }

    private String java_lang_String_a(int n, int n2, int n3) {
        String string = n == 0 ? aeii.f.java_lang_String_a(n2 + 121) : this.java_lang_String_b(n2);
        return aeii.f.java_lang_String_a(n + 32) + "\n" + string + "\n" + "Current turn: " + (n3 + 1);
    }

    private boolean boolean_a() {
        return this.var_boolean_m && aeii.f.var_aeii_c_a.var_aeii_f_a == this;
    }

    private void e() {
        this.var_aeii_d_b = null;
        this.var_aeii_d_c = null;
        this.var_aeii_d_d = null;
        this.var_aeii_d_e = null;
        System.gc();
        this.var_int_b = ((f)this).k - var_byte_a;
        this.var_int_p = this.var_int_b >> 1;
        aeii.c.f();
        if (this.var_byte_h != 1) {
            int n;
            a.a.a.g g2;
            byte by;
            this.var_byte_h = 1;
            aeii.a.a(this);
            aeii.c.g();
            this.var_a_a_a_g_arr_arr_a = new a.a.a.g[8][12];
            for (by = 0; by < 8; by = (byte)(by + 1)) {
                g2 = new a.a.a.g("unit_icons", by);
                int n2 = g2.int_d() / 12;
                for (n = 0; n < 12; n = (int)((byte)(n + 1))) {
                    d[] dArray;
                    if ((by & 1) == 1) {
                        dArray = new d[]{g2.var_aeii_d_arr_a[n]};
                        this.var_a_a_a_g_arr_arr_a[by][n] = new a.a.a.g(dArray);
                        continue;
                    }
                    dArray = new d[n2];
                    for (int i = 0; i < n2; ++i) {
                        dArray[i] = g2.var_aeii_d_arr_a[i * 12 + n];
                    }
                    this.var_a_a_a_g_arr_arr_a[by][n] = new a.a.a.g(dArray);
                }
            }
            this.var_javax_microedition_lcdui_Image_arr_arr_a = new Image[2][this.var_aeii_d_arr_d.length];
            for (by = 0; by < 2; by = (byte)(by + 1)) {
                for (n = 0; n < this.var_aeii_d_arr_d.length; n = (int)((byte)(n + 1))) {
                    this.var_javax_microedition_lcdui_Image_arr_arr_a[by][n] = Image.createImage((int)24, (int)24);
                    g2 = this.var_javax_microedition_lcdui_Image_arr_arr_a[by][n].getGraphics();
                    this.var_aeii_d_arr_d[n].a((Graphics)g2, 0, 0);
                    this.var_a_a_a_g_t.a((Graphics)g2, by, 0, 0, 0);
                }
            }
            this.var_a_a_a_g_i = new a.a.a.g("portraits");
            this.var_a_a_a_g_q = new a.a.a.g("cursor");
            this.var_a_a_a_g_g = new a.a.a.g("redspark");
            this.var_a_a_a_g_e = new a.a.a.g("smoke");
            this.var_a_a_a_g_f = new a.a.a.g("spark");
            this.var_a_a_a_g_s = new a.a.a.g("status");
            this.var_a_a_a_g_p = new a.a.a.g("arrow_icons");
            this.var_aeii_d_a = new d("tombstone");
            this.var_a_a_a_g_u = new a.a.a.g("levelup");
            this.var_a_a_a_g_arr_a = new a.a.a.g[2];
            this.var_a_a_a_g_arr_a[0] = new a.a.a.g("king_head_icons");
            this.var_a_a_a_g_arr_a[1] = new a.a.a.g("king_head_icons", 0);
            this.var_a_a_a_g_q.a(var_byte_arr_arr_c[0]);
            this.var_a_a_a_g_r = new a.a.a.g(this.var_a_a_a_g_q);
            this.var_a_a_a_g_r.a(var_byte_arr_arr_c[3]);
            this.var_aeii_d_arr_e = new d[2];
            this.var_int_K = var_byte_arr_j[0];
            this.var_aeii_d_arr_e[0] = this.var_aeii_d_arr_d[var_byte_arr_j[0]];
            this.var_aeii_d_arr_e[1] = this.var_aeii_d_arr_d[var_byte_arr_j[1]];
            this.var_a_a_a_g_o = new a.a.a.g("b_smoke");
        }
    }

    public final void void_a() {
        this.var_boolean_u = true;
        this.var_boolean_v = true;
        this.var_boolean_a = true;
        this.var_boolean_F = true;
    }

    private void a(byte[] object) {
        int n;
        object = new DataInputStream(new ByteArrayInputStream((byte[])object));
        this.var_byte_c = ((DataInputStream)object).readByte();
        this.var_int_x = ((DataInputStream)object).readByte();
        this.var_byte_f = ((DataInputStream)object).readByte();
        this.void_c(this.var_int_x);
        this.var_byte_b = ((DataInputStream)object).readByte();
        ((DataInputStream)object).readByte();
        this.var_short_a = ((DataInputStream)object).readShort();
        this.var_int_n = ((DataInputStream)object).readByte();
        this.var_byte_f = ((DataInputStream)object).readByte();
        for (n = 0; n < this.var_byte_f; ++n) {
            this.var_byte_arr_e[n] = ((DataInputStream)object).readByte();
            this.var_byte_arr_m[n] = ((DataInputStream)object).readByte();
            this.var_int_arr_b[n] = ((DataInputStream)object).readInt();
            this.var_byte_arr_arr_d[n][0] = ((DataInputStream)object).readByte();
            this.var_byte_arr_arr_d[n][1] = ((DataInputStream)object).readByte();
        }
        this.var_int_s = ((DataInputStream)object).readShort();
        for (n = 0; n < this.var_byte_arr_arr_e.length; ++n) {
            this.var_byte_arr_arr_a[this.var_byte_arr_arr_e[n][0]][this.var_byte_arr_arr_e[n][1]] = ((DataInputStream)object).readByte();
        }
        this.var_aeii_a_arr_arr_a = new a[this.var_byte_f][4];
        this.var_int_arr_a = new int[this.var_byte_f];
        this.i();
        n = ((DataInputStream)object).readByte();
        for (int i = 0; i < n; ++i) {
            byte by = ((DataInputStream)object).readByte();
            byte by2 = ((DataInputStream)object).readByte();
            short s = ((DataInputStream)object).readByte();
            byte by3 = ((DataInputStream)object).readByte();
            byte by4 = ((DataInputStream)object).readByte();
            byte by5 = ((DataInputStream)object).readByte();
            short s2 = ((DataInputStream)object).readShort();
            short s3 = ((DataInputStream)object).readShort();
            short s4 = ((DataInputStream)object).readShort();
            byte by6 = ((DataInputStream)object).readByte();
            byte by7 = ((DataInputStream)object).readByte();
            a a2 = aeii.a.a(by, by2, (int)s3, s4);
            aeii.a.a(by, by2, (int)s3, s4).var_byte_f = s;
            a2.var_int_a = s2;
            a2.void_a(by5);
            a2.var_byte_g = by3;
            a2.void_b();
            a2.var_int_h = by4;
            a2.var_byte_h = by6;
            a2.var_byte_i = by7;
            if (by != 9) continue;
            by = ((DataInputStream)object).readByte();
            s = ((DataInputStream)object).readShort();
            a2.b((int)by);
            a2.var_int_j = s;
            if (((DataInputStream)object).readByte() != 1) continue;
            this.var_aeii_a_arr_b[a2.var_byte_e] = a2;
        }
        this.ay = ((DataInputStream)object).readShort();
        this.var_long_k = ((DataInputStream)object).readInt();
        this.az = ((DataInputStream)object).readInt();
        this.var_boolean_z = ((DataInputStream)object).readByte() != 0;
        ((FilterInputStream)object).close();
        if (this.var_int_x == 6 && this.ay > 32) {
            this.var_aeii_e_e = this.a(aeii.f.java_lang_String_a(121 + this.var_int_x), aeii.f.java_lang_String_a(138), this.var_int_b, -1);
            this.var_aeii_e_e.a((byte)0, true);
            this.var_aeii_e_e.a((f)null);
        }
        this.d(this.var_byte_arr_arr_d[this.var_byte_b][0], this.var_byte_arr_arr_d[this.var_byte_b][1]);
        this.void_b(this.var_byte_arr_arr_d[this.var_byte_b][0], this.var_byte_arr_arr_d[this.var_byte_b][1]);
        aeii.c.void_a(var_int_arr_g[this.var_byte_arr_d[this.var_byte_b]], 0);
    }

    private void a(int n, f f2) {
        try {
            int n2;
            g g2 = this;
            g2.var_byte_arr_arr_d[g2.var_byte_b][0] = (byte)g2.var_int_h;
            g2.var_byte_arr_arr_d[g2.var_byte_b][1] = (byte)g2.var_int_i;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeByte(g2.var_byte_c);
            dataOutputStream.writeByte(g2.var_int_x);
            dataOutputStream.writeByte(g2.var_byte_f);
            dataOutputStream.writeByte(g2.var_byte_b);
            dataOutputStream.writeByte(g2.var_byte_arr_d[g2.var_byte_b]);
            dataOutputStream.writeShort(g2.var_short_a);
            dataOutputStream.writeByte(g2.var_int_n);
            dataOutputStream.writeByte(g2.var_byte_f);
            for (n2 = 0; n2 < g2.var_byte_f; ++n2) {
                dataOutputStream.writeByte(g2.var_byte_arr_e[n2]);
                dataOutputStream.writeByte(g2.var_byte_arr_m[n2]);
                dataOutputStream.writeInt(g2.var_int_arr_b[n2]);
                dataOutputStream.writeByte(g2.var_byte_arr_arr_d[n2][0]);
                dataOutputStream.writeByte(g2.var_byte_arr_arr_d[n2][1]);
            }
            dataOutputStream.writeShort(g2.var_int_s);
            for (n2 = 0; n2 < g2.var_byte_arr_arr_e.length; ++n2) {
                dataOutputStream.writeByte(g2.var_byte_arr_arr_a[g2.var_byte_arr_arr_e[n2][0]][g2.var_byte_arr_arr_e[n2][1]]);
            }
            dataOutputStream.writeByte(g2.var_java_util_Vector_a.size());
            int n3 = g2.var_java_util_Vector_a.size();
            for (n2 = 0; n2 < n3; ++n2) {
                a a2 = (a)g2.var_java_util_Vector_a.elementAt(n2);
                dataOutputStream.writeByte(a2.var_byte_d);
                dataOutputStream.writeByte(a2.var_byte_e);
                dataOutputStream.writeByte(a2.var_byte_f);
                dataOutputStream.writeByte(a2.var_byte_g);
                dataOutputStream.writeByte(a2.var_int_h);
                dataOutputStream.writeByte(a2.var_short_a);
                dataOutputStream.writeShort(a2.var_int_a);
                dataOutputStream.writeShort(a2.var_short_b);
                dataOutputStream.writeShort(a2.var_short_c);
                dataOutputStream.writeByte(a2.var_byte_h);
                dataOutputStream.writeByte(a2.var_byte_i);
                if (a2.var_byte_d != 9) continue;
                dataOutputStream.writeByte(a2.var_byte_j);
                dataOutputStream.writeShort(a2.var_int_j);
                dataOutputStream.writeByte(g2.var_aeii_a_arr_b[a2.var_byte_e] == a2 ? 1 : 0);
            }
            dataOutputStream.writeShort((short)g2.ay);
            dataOutputStream.writeInt((short)g2.var_long_k);
            dataOutputStream.writeInt(g2.az);
            dataOutputStream.writeByte(g2.var_boolean_z ? 0 : 1);
            byte[] byArray = byteArrayOutputStream.toByteArray();
            dataOutputStream.close();
            aeii.c.a("save", n, byArray);
            this.var_java_lang_String_arr_k[n] = this.java_lang_String_a((int)this.var_byte_c, this.var_int_x, (int)this.var_short_a);
            this.var_byte_arr_n[n] = this.var_byte_arr_d[this.var_byte_b];
            this.var_int_arr_k[n] = this.var_int_x;
            this.var_aeii_e_u.a(null, this.var_java_lang_String_arr_k[n], this.var_int_a, -1);
            this.var_aeii_e_u.var_int_g = var_int_arr_f[this.var_byte_arr_n[n]];
            this.var_aeii_e_u.void_a();
            e e2 = this.a(null, aeii.f.java_lang_String_a(77), this.var_int_b, 1000);
            this.a(null, aeii.f.java_lang_String_a(77), this.var_int_b, 1000).var_aeii_f_a = f2;
            aeii.f.var_aeii_c_a.a(e2);
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    private static void f() {
        try {
            byte[] byArray = aeii.c.byte_arr_a("settings", 0);
            for (int i = 0; i < 4; ++i) {
                aeii.c.var_boolean_arr_a[i] = (byArray[0] & 1 << i) != 0;
            }
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    private static void g() {
        try {
            byte[] byArray = new byte[1];
            for (int i = 0; i < 4; ++i) {
                if (!aeii.c.var_boolean_arr_a[i]) continue;
                byArray[0] = (byte)(byArray[0] | 1 << i);
            }
            aeii.c.a("settings", 0, byArray);
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    private void void_a(a a2) {
        this.T = 0;
        this.d(a2.var_short_b, a2.var_short_c);
        this.var_aeii_a_i = a2;
    }

    private void a(a a2, a a3) {
        if (a3.var_byte_f == 4) {
            this.var_aeii_a_d = a3;
            this.a(this.var_a_a_a_g_g, this.var_aeii_a_d.m, this.var_aeii_a_d.n, 0, 0, 1, 50);
            this.var_int_E = 6;
            this.var_aeii_a_b.void_d();
            this.var_byte_d = 0;
            this.k();
            this.var_a_a_a_g_q.a(var_byte_arr_arr_c[0]);
            if (this.var_byte_arr_m[this.var_byte_b] == 0) {
                this.var_long_j = this.var_long_a;
                this.var_byte_k = (byte)6;
                return;
            }
        } else {
            if (aeii.c.var_boolean_arr_a[3] && a3.var_byte_arr_arr_a.length > 0) {
                this.var_boolean_g = true;
                this.var_int_G = 0;
                aeii.c.f();
            } else {
                this.var_byte_d = (byte)13;
                this.var_int_I = 0;
                this.var_a_a_a_g_q.a(var_byte_arr_arr_c[0]);
            }
            this.var_aeii_a_f = a2;
            this.var_aeii_a_g = a3;
        }
    }

    private void h() {
        this.k();
        if (this.var_aeii_a_f.var_int_h <= 0) {
            this.var_aeii_a_c = this.var_aeii_a_f;
        } else if (this.var_aeii_a_f.boolean_a()) {
            this.var_java_util_Vector_e.addElement(this.var_aeii_a_f);
        }
        if (this.var_aeii_a_g.var_int_h <= 0) {
            this.var_aeii_a_c = this.var_aeii_a_g;
        } else {
            if (this.var_aeii_a_f.a((short)128)) {
                this.a(this.var_a_a_a_g_f, this.var_aeii_a_g.m, this.var_aeii_a_g.n, 0, 0, 1, 50);
                a.a.a.g g2 = a.a.a.g.a(this.var_a_a_a_g_s, 0, 0, -4, -1, 800, (byte)5);
                g2.b(this.var_aeii_a_g.m + (this.var_aeii_a_g.o - g2.o) / 2, this.var_aeii_a_g.n - g2.p);
                g2.a(var_byte_arr_i);
                this.var_java_util_Vector_c.addElement(g2);
                this.var_aeii_a_g.b((byte)1);
            }
            if (this.var_aeii_a_g.boolean_a()) {
                this.var_java_util_Vector_e.addElement(this.var_aeii_a_g);
            }
        }
        if (this.var_aeii_a_c != null) {
            this.d(this.var_aeii_a_c.var_short_b, this.var_aeii_a_c.var_short_c);
            this.a(this.var_a_a_a_g_f, this.var_aeii_a_c.m, this.var_aeii_a_c.n, 0, 0, 1, 50);
            aeii.c.b(12, 1);
        }
        this.var_long_e = this.var_long_a;
        if (this.var_byte_arr_m[this.var_byte_b] == 0) {
            this.var_long_j = this.var_long_a;
            this.var_byte_k = (byte)6;
        }
        this.var_a_a_a_g_q.a(var_byte_arr_arr_c[0]);
        this.var_byte_d = 0;
        this.var_aeii_a_f.void_d();
        this.var_aeii_a_g = null;
        this.var_aeii_a_f = null;
    }

    public final a.a.a.g a(a.a.a.g g2, int n, int n2, int n3, int n4, int n5, int n6) {
        g2 = a.a.a.g.a(g2, n3, n4, 0, n5, n6, (byte)0);
        g2.b(n, n2);
        this.var_java_util_Vector_d.addElement(g2);
        return g2;
    }

    private void void_b(a a2) {
        this.var_boolean_f = true;
        this.var_boolean_e = !this.var_boolean_l;
        this.aa = 12;
        this.var_byte_d = 1;
        this.var_boolean_i = true;
        this.a(this.var_byte_arr_arr_b, 0);
        a2.b(this.var_byte_arr_arr_b);
        this.var_boolean_b = true;
        this.var_boolean_c = false;
        this.var_a_a_a_g_q.a(var_byte_arr_arr_c[2]);
    }

    private void a(byte[] byArray, int n, int n2, f f2) {
        e e2 = new e(0, 0);
        this.var_int_N = n;
        this.var_int_O = n2;
        n = byArray.length;
        Vector<String> vector = new Vector<String>(n);
        Vector<d> vector2 = new Vector<d>(n);
        for (int i = 0; i < n; ++i) {
            byte by = byArray[i];
            if (!this.var_boolean_t && by == 6) continue;
            vector.addElement(this.var_java_lang_String_arr_h[by]);
            vector2.addElement(this.var_aeii_d_arr_c[by]);
        }
        Object[] objectArray = new String[vector.size()];
        Object[] objectArray2 = new d[vector2.size()];
        vector.copyInto(objectArray);
        vector2.copyInto(objectArray2);
        e2.a((String[])objectArray, (d[])objectArray2, ((f)this).l, this.var_int_N, this.var_int_O, 3, (byte)1);
        e2.a(f2);
        aeii.f.var_aeii_c_a.a(e2);
    }

    private void b(byte[] byArray) {
        this.var_aeii_e_a = new e(0, 0);
        int n = byArray.length;
        String[] stringArray = new String[n];
        d[] dArray = new d[n];
        for (int i = 0; i < byArray.length; ++i) {
            stringArray[i] = this.var_java_lang_String_arr_g[byArray[i]];
            dArray[i] = this.var_aeii_d_arr_a[byArray[i]];
        }
        if (this.var_int_i * 24 <= this.var_int_b / 2 - 24) {
            this.var_aeii_e_a.a(stringArray, dArray, 0, this.var_int_b - this.var_a_a_a_g_c.p, 36);
        } else {
            this.var_aeii_e_a.a(stringArray, dArray, this.var_int_a, 0, 8);
        }
        this.var_aeii_e_a.a(this);
        aeii.f.var_aeii_c_a.a(this.var_aeii_e_a);
    }

    private e a(String string, d d2) {
        String[] stringArray = new String[10];
        for (int i = 0; i < 10; ++i) {
            stringArray[i] = "SLOT " + (i + 1) + "/" + 10;
        }
        this.var_aeii_e_t = new e(14, 0);
        this.var_aeii_e_t.a(stringArray, this.var_int_a, -1);
        this.var_aeii_e_u = new e(10, 0);
        this.var_aeii_e_u.a(null, this.var_java_lang_String_arr_k[0], this.var_int_a, -1);
        if (this.var_byte_arr_n[0] != -1) {
            this.var_aeii_e_u.var_int_g = var_int_arr_f[this.var_byte_arr_n[0]];
        }
        e e2 = new e(15, 15);
        int n = (this.var_int_b - this.var_aeii_e_t.var_int_c - this.var_aeii_e_u.var_int_c) / 2;
        e e3 = new e(10, 0);
        e3.a(null, string, this.var_int_a, -1);
        e3.var_aeii_d_a = d2;
        e2.a(e3, 0, 0, 0);
        e2.a(this.var_aeii_e_t, 0, n += e3.var_int_c / 2, 0);
        e2.a(this.var_aeii_e_u, 0, n += this.var_aeii_e_t.var_int_c, 20);
        e2.var_boolean_b = true;
        e2.a((byte)0, true);
        return e2;
    }

    private void void_a(f f2) {
        int n = this.var_java_lang_String_arr_s.length;
        Object object = new String[n];
        int[] nArray = new int[n];
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            boolean bl = false;
            for (int j = 0; j < this.U; ++j) {
                if (!this.var_java_lang_String_arr_s[i].equals(this.var_java_lang_String_arr_l[j])) {
                    continue;
                }
                bl = true;
                break;
            }
            if (bl) continue;
            object[n2] = this.var_java_lang_String_arr_s[i];
            nArray[n2] = i;
            ++n2;
        }
        this.var_aeii_e_D = new e(15, 15);
        e e2 = new e(10, 0);
        e2.a(null, aeii.f.java_lang_String_a(48), this.var_int_a, -1);
        if (n2 == 0) {
            e e3 = new e(10, 0);
            e3.a(null, aeii.f.java_lang_String_a(52), this.var_int_a, this.var_int_p);
            this.var_aeii_e_D.a(e3, 0, (this.var_int_b + e2.var_int_c) / 2, 6);
        } else {
            String[] stringArray = new String[n2];
            int[] nArray2 = new int[n2];
            System.arraycopy(object, 0, stringArray, 0, n2);
            System.arraycopy(nArray, 0, nArray2, 0, n2);
            this.var_aeii_e_F = new e(10, 0);
            object = aeii.g.java_lang_String_c(this.W);
            this.var_aeii_e_F.a(null, aeii.f.a(54, aeii.g.java_lang_String_c(this.var_int_arr_n[nArray2[0]])) + "\n" + aeii.f.a(53, (String)object), this.var_int_a, -1);
            this.var_aeii_e_E = new e(11, 0);
            this.var_aeii_e_E.a(stringArray, this.var_int_c, this.var_int_p, this.var_int_a, this.var_int_b - e2.var_int_c - this.var_aeii_e_F.var_int_c, 3, 4);
            this.var_aeii_e_E.var_int_arr_a = nArray2;
            int n3 = (this.var_int_b - this.var_aeii_e_F.var_int_c - this.var_aeii_e_E.var_int_c + e2.var_int_c) / 2;
            this.var_aeii_e_D.a(this.var_aeii_e_E, 0, n3, 20);
            this.var_aeii_e_D.a(this.var_aeii_e_F, 0, n3 += this.var_aeii_e_E.var_int_c, 20);
            this.var_aeii_e_D.var_boolean_b = true;
            this.var_aeii_e_D.a((byte)0, true);
        }
        this.var_aeii_e_D.a(e2, 0, 0, 20);
        this.var_aeii_e_D.a(f2);
    }

    private e aeii_e_a(f f2) {
        if (this.var_java_lang_String_arr_l.length == 0) {
            e e2 = new e(10, 0);
            e e3 = e2.aeii_e_a(aeii.f.java_lang_String_a(49));
            e2.aeii_e_a(aeii.f.java_lang_String_a(49)).var_aeii_d_a = this.var_aeii_d_arr_c[6];
            e2.a(null, aeii.f.java_lang_String_a(52), ((f)this).j, -1);
            e2.a(0, (this.var_int_b + e3.var_int_c) / 2, 6);
            e2.a(f2);
            this.var_aeii_e_B = null;
            return e2;
        }
        this.var_aeii_e_B = new e(11, 0);
        e e4 = this.var_aeii_e_B.aeii_e_a(aeii.f.java_lang_String_a(49));
        this.var_aeii_e_B.aeii_e_a(aeii.f.java_lang_String_a(49)).var_aeii_d_a = this.var_aeii_d_arr_c[6];
        this.var_aeii_e_B.a(this.var_java_lang_String_arr_l, ((f)this).j / 2, (this.var_int_b + e4.var_int_c) / 2, this.var_int_a, this.var_int_b - e4.var_int_c, 3, 4);
        this.var_aeii_e_B.a(f2);
        return this.var_aeii_e_B;
    }

    private int int_a(int n) {
        if (n >= var_java_lang_String_arr_e.length) {
            return this.var_int_arr_l[n - var_java_lang_String_arr_e.length] + var_java_lang_String_arr_e.length;
        }
        return n;
    }

    private static DataInputStream java_io_DataInputStream_a(int n) {
        if (n >= var_java_lang_String_arr_e.length) {
            return new DataInputStream(new ByteArrayInputStream(aeii.c.byte_arr_a("download", n -= var_java_lang_String_arr_e.length)));
        }
        return new DataInputStream(aeii.c.java_io_InputStream_a("s" + n + ".aem"));
    }

    private static byte[] byte_arr_a(int n) {
        if (n >= var_java_lang_String_arr_e.length) {
            return aeii.c.byte_arr_a("download", n -= var_java_lang_String_arr_e.length);
        }
        try {
            int n2;
            Object object = aeii.c.java_io_InputStream_a("s" + n);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while ((n2 = ((InputStream)object).read()) >= 0) {
                byteArrayOutputStream.write(n2);
            }
            ((InputStream)object).close();
            object = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return object;
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            return null;
        }
    }

    private String java_lang_String_b(int n) {
        if (n >= var_java_lang_String_arr_e.length) {
            n -= var_java_lang_String_arr_e.length;
            for (int i = 0; i < this.var_int_arr_l.length; ++i) {
                if (this.var_int_arr_l[i] != n) continue;
                return this.var_java_lang_String_arr_l[i];
            }
            return null;
        }
        return var_java_lang_String_arr_e[n];
    }

    private static String java_lang_String_c(int n) {
        n = n * 100 / 1024;
        int n2 = n / 100;
        return n2 + "." + (n %= 100);
    }

    private static String a(String string, String string2) {
        if (string != null) {
            int n;
            if (string.endsWith("/")) {
                string = string.substring(0, string.length() - 1);
            }
            if ((n = string.lastIndexOf(47)) >= 0) {
                return string.substring(n + 1);
            }
            return string;
        }
        return string2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final void a(e e2, int n, String string, byte by) {
        try {
            String[] stringArray;
            int n2;
            Object object;
            int n3;
            int n4;
            Object object2;
            this.var_boolean_G = true;
            if (e2 == this.var_aeii_e_a && by == 1) {
                if (this.var_byte_d != 3) return;
                this.var_aeii_a_b.void_a(this.var_int_A, this.var_int_B);
                this.var_aeii_a_b.b(this.var_byte_arr_arr_b);
                this.void_b(this.var_aeii_a_b);
                this.var_boolean_i = true;
                return;
            }
            if (e2 == this.var_aeii_e_v) {
                if (by == 0) {
                    aeii.f.var_aeii_c_a.c();
                    return;
                } else {
                    this.var_aeii_e_v = null;
                }
                return;
            }
            if (e2 == this.var_aeii_e_w) {
                if (by == 0) {
                    this.a(this.var_byte_arr_h, this.var_int_N, this.var_int_O, e2.var_aeii_f_a);
                }
                this.var_aeii_e_w = null;
                return;
            }
            if (e2 == this.var_aeii_e_H) {
                if (by == 0) {
                    Object object3 = this.var_java_lang_String_arr_u[this.var_aeii_e_I.var_int_d];
                    if (((String)object3).endsWith(".aem")) {
                        int n5;
                        a.a.a.c c2 = this.var_aeii_b_a.a_a_a_c_a((String)object3);
                        InputStream inputStream = c2.openInputStream();
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        while ((n5 = inputStream.read()) >= 0) {
                            byteArrayOutputStream.write(n5);
                        }
                        inputStream.close();
                        c2.close();
                        object3 = ((String)object3).substring(0, ((String)object3).length() - ".aem".length());
                        this.a((String)object3, byteArrayOutputStream.toByteArray());
                        byteArrayOutputStream.close();
                        object3 = this.a(null, aeii.f.a(45, (String)object3), this.var_int_b, 2000);
                        ((e)object3).a(this.var_aeii_e_H);
                        aeii.f.var_aeii_c_a.a((f)object3);
                        return;
                    }
                    this.var_aeii_b_a.void_a((String)object3);
                    this.var_java_lang_String_arr_u = aeii.b.a(aeii.b.a(this.var_aeii_b_a.a(".aem", true)), (String[])(this.var_aeii_b_a.a() != null ? var_java_lang_String_arr_b : null));
                    this.var_aeii_e_H = new e(15, 15);
                    e e3 = this.var_aeii_e_H.aeii_e_a(aeii.g.a(this.var_aeii_b_a.a(), aeii.f.java_lang_String_a(290)));
                    this.var_aeii_e_H.aeii_e_a(aeii.g.a(this.var_aeii_b_a.a(), aeii.f.java_lang_String_a(290))).var_aeii_d_a = this.var_aeii_d_arr_c[6];
                    this.var_aeii_e_I = new e(0, 0);
                    this.var_aeii_e_I.a(this.var_java_lang_String_arr_u, 0, 0, this.var_int_a, this.var_int_b - e3.var_int_c - (this.var_a_a_a_g_c.p << 1), 3, 4);
                    if (this.var_aeii_b_a.a() != null && this.var_java_lang_String_arr_u.length > 1) {
                        this.var_aeii_e_I.var_int_d = 1;
                    }
                    this.var_aeii_e_H.a(this.var_aeii_e_I, this.var_int_c, (this.var_int_b + e3.var_int_c) / 2, 3);
                    this.var_aeii_e_H.a((byte)0, true);
                    this.var_aeii_e_H.var_boolean_b = true;
                    this.var_aeii_e_H.a(this.var_aeii_e_z);
                    aeii.f.var_aeii_c_a.a(this.var_aeii_e_H);
                    return;
                }
                this.var_aeii_e_H = null;
                this.var_aeii_e_I = null;
                this.var_java_lang_String_arr_u = null;
                return;
            }
            if (e2 == this.var_aeii_e_J) {
                if (by == 0) {
                    String string2 = this.var_aeii_e_K.var_int_d == 0 ? this.var_java_lang_String_b + ".aem" : this.var_java_lang_String_arr_u[this.var_aeii_e_K.var_int_d];
                    if (string2.endsWith(".aem")) {
                        a.a.a.c c3 = this.var_aeii_b_a.a_a_a_c_a(string2);
                        if (c3.boolean_a()) {
                            c3.a(0L);
                        } else {
                            c3.void_a();
                        }
                        OutputStream outputStream = c3.openOutputStream();
                        outputStream.write(aeii.g.byte_arr_a(this.var_int_y));
                        outputStream.close();
                        c3.close();
                        this.var_java_lang_String_arr_u = aeii.b.a(aeii.b.a(this.var_aeii_b_a.a(".aem", true)), (String[])(this.var_aeii_b_a.a() != null ? var_java_lang_String_arr_c : null));
                        this.var_aeii_e_J = new e(15, 15);
                        e e4 = this.var_aeii_e_J.aeii_e_a(aeii.g.a(this.var_aeii_b_a.a(), aeii.f.java_lang_String_a(299)));
                        this.var_aeii_e_J.aeii_e_a(aeii.g.a(this.var_aeii_b_a.a(), aeii.f.java_lang_String_a(299))).var_aeii_d_a = this.var_aeii_d_arr_c[6];
                        this.var_aeii_e_K = new e(0, 0);
                        this.var_aeii_e_K.a(this.var_java_lang_String_arr_u, 0, 0, this.var_int_a, this.var_int_b - e4.var_int_c - (this.var_a_a_a_g_c.p << 1), 3, 4);
                        if (this.var_aeii_b_a.a() != null && this.var_java_lang_String_arr_u.length > 2) {
                            this.var_aeii_e_K.var_int_d = 2;
                        }
                        this.var_aeii_e_J.a(this.var_aeii_e_K, this.var_int_c, (this.var_int_b + e4.var_int_c) / 2, 3);
                        this.var_aeii_e_J.a((byte)0, true);
                        this.var_aeii_e_J.var_boolean_b = true;
                        this.var_aeii_e_J.a(this.var_aeii_e_N);
                        e e5 = this.a(null, aeii.f.a(45, this.var_java_lang_String_b), this.var_int_b, 2000);
                        e5.a(this.var_aeii_e_J);
                        aeii.f.var_aeii_c_a.a(e5);
                        return;
                    }
                    this.var_aeii_b_a.void_a(string2);
                    this.var_java_lang_String_arr_u = aeii.b.a(aeii.b.a(this.var_aeii_b_a.a(".aem", true)), (String[])(this.var_aeii_b_a.a() != null ? var_java_lang_String_arr_c : null));
                    this.var_aeii_e_J = new e(15, 15);
                    e e6 = this.var_aeii_e_J.aeii_e_a(aeii.g.a(this.var_aeii_b_a.a(), aeii.f.java_lang_String_a(290)));
                    this.var_aeii_e_J.aeii_e_a(aeii.g.a(this.var_aeii_b_a.a(), aeii.f.java_lang_String_a(290))).var_aeii_d_a = this.var_aeii_d_arr_c[6];
                    this.var_aeii_e_K = new e(0, 0);
                    this.var_aeii_e_K.a(this.var_java_lang_String_arr_u, 0, 0, this.var_int_a, this.var_int_b - e6.var_int_c - (this.var_a_a_a_g_c.p << 1), 3, 4);
                    if (this.var_aeii_b_a.a() != null && this.var_java_lang_String_arr_u.length > 2) {
                        this.var_aeii_e_K.var_int_d = 2;
                    }
                    this.var_aeii_e_J.a(this.var_aeii_e_K, this.var_int_c, (this.var_int_b + e6.var_int_c) / 2, 3);
                    this.var_aeii_e_J.a((byte)0, true);
                    this.var_aeii_e_J.var_boolean_b = true;
                    this.var_aeii_e_J.a(this.var_aeii_e_N);
                    aeii.f.var_aeii_c_a.a(this.var_aeii_e_J);
                    return;
                }
                this.var_aeii_e_J = null;
                this.var_aeii_e_K = null;
                this.var_java_lang_String_arr_u = null;
                return;
            }
            if (e2 == this.var_aeii_e_x) {
                if (by == 0) {
                    int n6 = this.var_aeii_e_t.var_int_d;
                    this.a(n6, this.var_aeii_e_s);
                }
                this.var_aeii_e_x = null;
                return;
            }
            if (e2 == this.var_aeii_e_p) {
                if (by != 2 && by != 3) return;
                this.var_aeii_e_q.a(null, aeii.f.java_lang_String_a(184 + this.var_aeii_e_p.var_aeii_a_arr_a[n].var_byte_d), this.var_int_a, this.var_int_b - this.var_aeii_e_o.var_int_c - this.var_aeii_e_p.var_int_c);
                this.var_aeii_e_o.var_aeii_a_a = this.var_aeii_e_p.var_aeii_a_arr_a[n];
                this.var_aeii_e_q.void_a();
                this.var_aeii_e_o.void_a();
                return;
            }
            if (e2 == this.var_aeii_e_n) {
                if (by == 0) {
                    a a2 = this.var_aeii_e_p.var_aeii_a_arr_a[this.var_aeii_e_p.var_int_d];
                    if (!this.boolean_a(a2, this.var_int_h, this.var_int_i)) {
                        return;
                    }
                    this.var_aeii_a_b = this.aeii_a_a(a2, this.var_int_h, this.var_int_i);
                    this.var_boolean_l = true;
                    this.void_b(this.var_aeii_a_b);
                    aeii.f.var_aeii_c_a.a(this);
                }
                this.var_aeii_e_n = null;
                this.var_aeii_e_p = null;
                this.var_aeii_e_q = null;
                this.var_aeii_e_o = null;
                return;
            }
            if (e2 == this.var_aeii_e_c) {
                if (by != 2 && by != 3) return;
                String string3 = aeii.f.a(var_int_arr_j[n], true);
                this.var_aeii_e_d.a(null, string3, this.var_int_a, this.var_aeii_e_d.var_int_c);
                this.var_aeii_e_d.void_a();
                return;
            }
            if (e2 == this.var_aeii_e_b) {
                this.var_aeii_e_b = null;
                this.var_aeii_e_c = null;
                this.var_aeii_e_d = null;
                return;
            }
            if (e2 == this.var_aeii_e_e) {
                if (by != 0) return;
                aeii.f.var_aeii_c_a.a(this);
                return;
            }
            if (e2 == this.var_aeii_e_i) {
                if (by != 0) {
                    this.var_aeii_e_i = null;
                    return;
                } else {
                    int n7;
                    int n8;
                    this.var_aeii_e_j = new e(15, 15);
                    e e7 = new e(10, 0);
                    new e(10, 0).var_aeii_d_a = this.var_aeii_d_arr_c[4];
                    e7.a(null, aeii.f.java_lang_String_a(34), this.var_int_a, -1);
                    this.var_aeii_e_j.a(e7, 0, 0, 20);
                    String[] stringArray2 = new String[this.var_byte_i];
                    for (n8 = 0; n8 < this.var_byte_i; ++n8) {
                        stringArray2[n8] = aeii.f.a(38, "" + (n8 + 1));
                    }
                    n8 = e7.var_int_c;
                    e[] eArray = new e[this.var_byte_i];
                    this.var_aeii_e_arr_a = new e[this.var_byte_i];
                    this.var_aeii_e_arr_b = new e[this.var_byte_i];
                    for (n = 0; n < this.var_byte_i; ++n) {
                        this.var_aeii_e_arr_a[n] = new e(14, 6);
                        this.var_aeii_e_arr_a[n].a(this.var_java_lang_String_arr_i, ((f)this).l, -1);
                        this.var_aeii_e_arr_b[n] = new e(14, 5);
                        this.var_aeii_e_arr_b[n].a(stringArray2, ((f)this).l, -1);
                        this.var_aeii_e_arr_b[n].var_int_d = n;
                        int n9 = Math.max(this.var_aeii_e_arr_a[n].var_int_b, this.var_aeii_e_arr_b[n].var_int_b);
                        n7 = ((f)this).j - n9;
                        this.var_aeii_e_arr_a[n].var_int_b = n9;
                        this.var_aeii_e_arr_b[n].var_int_b = n9;
                        this.var_aeii_e_j.a(this.var_aeii_e_arr_a[n], n7, n8, 20);
                        this.var_aeii_e_j.a(this.var_aeii_e_arr_b[n], n7, n8 += this.var_aeii_e_arr_a[n].var_int_c, 20);
                        eArray[n] = new e(10, 8);
                        eArray[n].a(null, aeii.f.java_lang_String_a(this.var_byte_arr_o[n] - 1 + 89), n7, this.var_aeii_e_arr_a[n].var_int_c + this.var_aeii_e_arr_b[n].var_int_c);
                        eArray[n].var_int_g = var_int_arr_f[this.var_byte_arr_o[n]];
                        this.var_aeii_e_j.a(eArray[n], 0, n8 += this.var_aeii_e_arr_b[n].var_int_c, 36);
                    }
                    this.var_aeii_e_j.var_int_f = 1;
                    e e8 = new e(10, 8);
                    e8.a(null, aeii.f.java_lang_String_a(40), this.var_int_c, -1);
                    this.var_aeii_e_j.a(e8, 0, n8, 20);
                    this.var_aeii_e_k = new e(14, 4);
                    String[] stringArray3 = new String[var_int_arr_d.length];
                    for (n7 = 0; n7 < stringArray3.length; ++n7) {
                        stringArray3[n7] = "" + var_int_arr_d[n7];
                    }
                    this.var_aeii_e_k.a(stringArray3, this.var_int_c, e8.var_int_c);
                    this.var_aeii_e_j.a(this.var_aeii_e_k, ((f)this).l, n8, 20);
                    e7 = new e(10, 8);
                    e7.a(null, aeii.f.java_lang_String_a(41), this.var_int_c, -1);
                    this.var_aeii_e_j.a(e7, 0, n8 += e8.var_int_c, 20);
                    this.var_aeii_e_l = new e(14, 4);
                    String[] stringArray4 = new String[var_int_arr_e.length];
                    for (int i = 0; i < stringArray4.length; ++i) {
                        stringArray4[i] = "" + var_int_arr_e[i];
                    }
                    this.var_aeii_e_l.a(stringArray4, this.var_int_c, e7.var_int_c);
                    this.var_aeii_e_j.a(this.var_aeii_e_l, ((f)this).l, n8, 20);
                    this.var_aeii_e_j.a(e2);
                    this.var_aeii_e_j.a((byte)0, true);
                    aeii.f.var_aeii_c_a.a(this.var_aeii_e_j);
                }
                return;
            }
            if (e2 == this.var_aeii_e_j) {
                if (by != 0) return;
                int n10 = 0;
                int n11 = 0;
                boolean[] blArray = new boolean[this.var_byte_i];
                for (int i = 0; i < this.var_byte_i; ++i) {
                    if (this.var_aeii_e_arr_a[i].var_int_d == 2) {
                        this.var_byte_arr_m[i] = 2;
                        continue;
                    }
                    ++n10;
                    if (this.var_aeii_e_arr_a[i].var_int_d == 0) {
                        this.var_byte_arr_m[i] = 1;
                    } else if (this.var_aeii_e_arr_a[i].var_int_d == 1) {
                        this.var_byte_arr_m[i] = 0;
                    }
                    this.var_byte_arr_e[i] = (byte)this.var_aeii_e_arr_b[i].var_int_d;
                    if (blArray[this.var_byte_arr_e[i]]) continue;
                    ++n11;
                    blArray[this.var_byte_arr_e[i]] = true;
                }
                if (n10 < 2 || n11 < 2) {
                    e e9 = this.a(null, aeii.f.java_lang_String_a(39), this.var_int_b, 2000);
                    e9.a(this.var_aeii_e_j);
                    aeii.f.var_aeii_c_a.a(e9);
                    return;
                }
                this.var_aeii_e_j = null;
                this.var_aeii_e_i = null;
                aeii.f.var_aeii_c_a.a(this);
                this.var_int_r = var_int_arr_d[this.var_aeii_e_k.var_int_d];
                this.var_int_s = var_int_arr_e[this.var_aeii_e_l.var_int_d];
                this.var_aeii_e_arr_a = null;
                this.var_aeii_e_k = null;
                this.var_aeii_e_l = null;
                this.E();
                this.var_byte_c = 1;
                this.var_int_n = 8;
                this.var_boolean_j = true;
                aeii.f.var_aeii_c_a.void_a();
                this.e();
                this.void_c(this.var_int_y);
                this.var_int_x = this.var_int_y;
                this.var_boolean_j = false;
                this.var_byte_d = 0;
                return;
            }
            if (e2 == this.var_aeii_e_g) {
                int n12 = this.var_aeii_e_f.var_int_d;
                if (!(by != 0 || n12 < var_java_lang_String_arr_e.length && this.var_boolean_arr_a[n12])) {
                    int n13;
                    this.var_int_y = this.int_a(n12);
                    object2 = aeii.g.java_io_DataInputStream_a(this.var_int_y);
                    n4 = ((DataInputStream)object2).readInt();
                    n3 = ((DataInputStream)object2).readInt();
                    object = new byte[n4][n3];
                    this.var_byte_arr_o = new byte[4];
                    byte[] byArray = new byte[5];
                    for (n13 = 0; n13 < 5; ++n13) {
                        byArray[n13] = -1;
                    }
                    this.var_byte_i = 0;
                    for (n13 = 0; n13 < n4; ++n13) {
                        for (int i = 0; i < n3; ++i) {
                            object[n13][i] = ((DataInputStream)object2).readByte();
                            if (this.var_byte_arr_k[object[n13][i]] != 9 || (n2 = aeii.g.a(n13, i, (byte[][])object)) == 0 || byArray[n2] != -1) continue;
                            this.var_byte_arr_o[this.var_byte_i] = (byte)n2;
                            this.var_byte_i = (byte)(this.var_byte_i + 1);
                        }
                    }
                    ((FilterInputStream)object2).close();
                    this.var_java_lang_String_b = this.var_aeii_e_f.var_java_lang_String_arr_a[n12];
                    this.var_aeii_e_i = new e(15, 15);
                    e e10 = new e(10, 0);
                    new e(10, 0).var_aeii_d_a = this.var_aeii_d_arr_c[4];
                    e10.a(null, this.var_java_lang_String_b, this.var_int_a, -1);
                    stringArray = new e(8, 0);
                    stringArray.a(((f)this).j, this.var_int_b - e10.var_int_c - this.var_a_a_a_g_c.p, (byte[][])object, null);
                    this.var_aeii_e_i.a((e)stringArray, this.var_int_c, this.var_int_p + (e10.var_int_c - this.var_a_a_a_g_c.p) / 2, 3);
                    this.var_aeii_e_i.a(e10, 0, 0, 0);
                    this.var_aeii_e_i.a(e2);
                    this.var_aeii_e_i.a((byte)0, true);
                    this.var_aeii_e_i.var_boolean_b = true;
                    aeii.f.var_aeii_c_a.a(this.var_aeii_e_i);
                }
            } else if (e2 == this.var_aeii_e_L) {
                int n14 = this.var_aeii_e_M.var_int_d - 1;
                if (by != 0) {
                    this.var_aeii_e_L = null;
                    this.var_aeii_e_M = null;
                    return;
                }
                if (n14 < 0) {
                    this.B();
                    aeii.c.a((Displayable)this.var_javax_microedition_lcdui_Form_a);
                    return;
                }
                if (n14 >= var_java_lang_String_arr_e.length || !this.var_boolean_arr_a[n14]) {
                    this.var_int_y = this.int_a(n14);
                    this.var_java_lang_String_b = this.var_aeii_e_M.var_java_lang_String_arr_a[n14];
                    this.var_aeii_e_L = null;
                    this.var_aeii_e_M = null;
                    aeii.f.var_aeii_c_a.a(this);
                    this.var_byte_c = 1;
                    this.var_int_n = 8;
                    this.var_byte_n = 1;
                    this.var_boolean_j = true;
                    aeii.f.var_aeii_c_a.void_a();
                    this.e();
                    this.void_c(this.var_int_y);
                    this.var_int_x = this.var_int_y;
                    this.var_boolean_j = false;
                    this.var_byte_d = 0;
                }
            } else if (e2 == this.var_aeii_e_N) {
                int n15 = this.var_aeii_e_O.var_int_d;
                if (by != 0) {
                    this.var_aeii_e_N = null;
                    this.var_aeii_e_O = null;
                    return;
                }
                if (n15 >= var_java_lang_String_arr_e.length || !this.var_boolean_arr_a[n15]) {
                    this.var_int_y = this.int_a(n15);
                    this.var_java_lang_String_b = this.var_aeii_e_O.var_java_lang_String_arr_a[n15];
                    if (this.var_aeii_b_a == null) {
                        this.var_aeii_b_a = new b();
                    }
                    this.var_java_lang_String_arr_u = aeii.b.a(aeii.b.a(this.var_aeii_b_a.a(".aem", true)), (String[])(this.var_aeii_b_a.a() != null ? var_java_lang_String_arr_c : null));
                    this.var_aeii_e_J = new e(15, 15);
                    object2 = this.var_aeii_e_J.aeii_e_a(aeii.g.a(this.var_aeii_b_a.a(), aeii.f.java_lang_String_a(299)));
                    this.var_aeii_e_J.aeii_e_a(aeii.g.a(this.var_aeii_b_a.a(), aeii.f.java_lang_String_a(299))).var_aeii_d_a = this.var_aeii_d_arr_c[6];
                    this.var_aeii_e_K = new e(0, 0);
                    this.var_aeii_e_K.a(this.var_java_lang_String_arr_u, 0, 0, this.var_int_a, this.var_int_b - ((e)object2).var_int_c - (this.var_a_a_a_g_c.p << 1), 3, 4);
                    if (this.var_aeii_b_a.a() != null && this.var_java_lang_String_arr_u.length > 2) {
                        this.var_aeii_e_K.var_int_d = 2;
                    }
                    this.var_aeii_e_J.a(this.var_aeii_e_K, this.var_int_c, (this.var_int_b + ((e)object2).var_int_c) / 2, 3);
                    this.var_aeii_e_J.a((byte)0, true);
                    this.var_aeii_e_J.var_boolean_b = true;
                    this.var_aeii_e_J.a(this.var_aeii_e_N);
                    aeii.f.var_aeii_c_a.a(this.var_aeii_e_J);
                }
            } else if (e2 == this.var_aeii_e_m) {
                if (by != 1) return;
                n = aeii.c.var_boolean_arr_a[0];
                boolean bl = false;
                for (int i = 0; i < 4; ++i) {
                    boolean bl2 = this.var_aeii_e_arr_c[i].var_int_d == 0;
                    if (bl2 == aeii.c.var_boolean_arr_a[i]) continue;
                    aeii.c.var_boolean_arr_a[i] = bl2;
                    bl = true;
                }
                if (bl) {
                    aeii.g.g();
                    if (n == aeii.c.var_boolean_arr_a[0]) return;
                    if (!aeii.c.var_boolean_arr_a[0]) {
                        aeii.c.f();
                        return;
                    }
                    if (this.var_byte_h == 1) {
                        if (this.var_byte_d == 11 || this.var_byte_d == 14) return;
                        aeii.c.b(var_int_arr_g[this.var_byte_arr_d[this.var_byte_b]], 0);
                        return;
                    }
                    if (this.var_byte_h != 0) return;
                    aeii.c.b(0, 0);
                    return;
                }
                this.var_aeii_e_m = null;
                this.var_aeii_e_arr_c = null;
                return;
            }
            if (e2 == this.var_aeii_e_s) {
                if (by == 0) {
                    int n16 = this.var_aeii_e_t.var_int_d;
                    if (this.var_byte_arr_n[n16] == -1) {
                        this.a(n16, e2);
                    } else {
                        this.var_aeii_e_x = this.a(null, aeii.f.java_lang_String_a(88), this.var_int_b, -1);
                        this.var_aeii_e_x.a((byte)0, true);
                        this.var_aeii_e_x.a(e2);
                        aeii.f.var_aeii_c_a.a(this.var_aeii_e_x);
                    }
                } else {
                    this.var_aeii_e_s = null;
                    this.var_aeii_e_t = null;
                    this.var_aeii_e_u = null;
                }
            } else if (e2 == this.var_aeii_e_r) {
                if (by == 0) {
                    object2 = null;
                    try {
                        object2 = aeii.c.byte_arr_a("save", this.var_aeii_e_t.var_int_d);
                    }
                    catch (Exception exception) {}
                    if (object2 != null) {
                        this.var_aeii_e_r = null;
                        this.E();
                        this.var_aeii_e_t = null;
                        this.var_aeii_e_u = null;
                        aeii.f.var_aeii_c_a.a(this);
                        this.var_boolean_j = true;
                        aeii.f.var_aeii_c_a.void_a();
                        this.e();
                        this.a((byte[])object2);
                        if (this.var_byte_c == 0) {
                            this.var_boolean_m = true;
                        }
                        this.var_boolean_j = false;
                        this.var_byte_d = 0;
                    }
                } else {
                    this.var_aeii_e_r = null;
                    this.var_aeii_e_t = null;
                    this.var_aeii_e_u = null;
                }
            } else if (e2 == this.var_aeii_e_t) {
                if (by != 2 && by != 3) return;
                this.var_aeii_e_u.a(null, this.var_java_lang_String_arr_k[n], this.var_int_a, -1);
                this.var_aeii_e_u.var_int_g = this.var_byte_arr_n[n] == -1 ? 2370117 : var_int_arr_f[this.var_byte_arr_n[n]];
                this.var_aeii_e_u.void_a();
                return;
            }
            if (e2 == this.var_aeii_e_y) {
                if (by == 0) {
                    if (string.equals(this.var_java_lang_String_arr_m[0])) {
                        if (this.var_java_lang_String_arr_p == null) {
                            this.a(0, "news", aeii.f.java_lang_String_a(0), (f)e2);
                            return;
                        }
                        this.var_aeii_e_A = this.b(e2);
                        return;
                    }
                    if (string.equals(this.var_java_lang_String_arr_m[1])) {
                        this.var_aeii_e_z = new e(11, 0);
                        e e11 = this.var_aeii_e_z.aeii_e_a(string);
                        this.var_aeii_e_z.aeii_e_a(string).var_aeii_d_a = this.var_aeii_d_arr_c[6];
                        this.var_aeii_e_z.a(this.var_java_lang_String_arr_n, ((f)this).j / 2, (this.var_int_b + e11.var_int_c) / 2, this.var_int_a, this.var_int_b - e11.var_int_c, 3, 0);
                        this.var_aeii_e_z.a(e2);
                        aeii.f.var_aeii_c_a.a(this.var_aeii_e_z);
                    }
                    if (string.equals(this.var_java_lang_String_arr_m[2])) {
                        this.var_aeii_e_L = new e(15, 15);
                        e e12 = new e(10, 0);
                        e12.a(null, string, this.var_int_a, -1);
                        e12.var_aeii_d_a = this.var_aeii_d_arr_c[6];
                        this.var_boolean_arr_a = new boolean[12];
                        for (n2 = this.var_int_q; n2 <= 7; ++n2) {
                            if (var_int_arr_c[n2] < 0) continue;
                            this.var_boolean_arr_a[aeii.g.var_int_arr_c[n2]] = true;
                        }
                        object = new String[12];
                        for (n3 = 0; n3 < 12; ++n3) {
                            object[n3] = this.var_boolean_arr_a[n3] ? (Object)aeii.f.java_lang_String_a(42) : (byte[])var_java_lang_String_arr_e[n3];
                        }
                        String[] stringArray5 = new String[12 + this.U + 1];
                        String[] stringArray6 = stringArray5;
                        stringArray5[0] = aeii.f.java_lang_String_a(292);
                        System.arraycopy(object, 0, stringArray6, 1, 12);
                        System.arraycopy(this.var_java_lang_String_arr_l, 0, stringArray6, 13, this.U);
                        this.var_aeii_e_M = new e(0, 0);
                        this.var_aeii_e_M.a(stringArray6, 0, 0, this.var_int_a, this.var_int_b - e12.var_int_c - (this.var_a_a_a_g_c.p << 1), 3, 4);
                        this.var_aeii_e_L.a(this.var_aeii_e_M, this.var_int_c, (this.var_int_b + e12.var_int_c) / 2, 3);
                        this.var_aeii_e_L.a(e12, 0, 0, 0);
                        this.var_aeii_e_L.var_boolean_b = true;
                        this.var_aeii_e_L.a((byte)0, true);
                        this.var_aeii_e_L.a(e2);
                        aeii.f.var_aeii_c_a.a(this.var_aeii_e_L);
                    }
                } else if (by == 1) {
                    this.var_aeii_e_y = null;
                }
            } else if (e2 == this.var_aeii_e_z) {
                if (by == 0) {
                    if (string.equals(this.var_java_lang_String_arr_n[0])) {
                        if (this.var_aeii_b_a == null) {
                            this.var_aeii_b_a = new b();
                        }
                        this.var_java_lang_String_arr_u = aeii.b.a(aeii.b.a(this.var_aeii_b_a.a(".aem", true)), (String[])(this.var_aeii_b_a.a() != null ? var_java_lang_String_arr_b : null));
                        this.var_aeii_e_H = new e(15, 15);
                        object = this.var_aeii_e_H.aeii_e_a(aeii.g.a(this.var_aeii_b_a.a(), aeii.f.java_lang_String_a(290)));
                        this.var_aeii_e_H.aeii_e_a(aeii.g.a(this.var_aeii_b_a.a(), aeii.f.java_lang_String_a(290))).var_aeii_d_a = this.var_aeii_d_arr_c[6];
                        this.var_aeii_e_I = new e(0, 0);
                        this.var_aeii_e_I.a(this.var_java_lang_String_arr_u, 0, 0, this.var_int_a, this.var_int_b - ((e)object).var_int_c - (this.var_a_a_a_g_c.p << 1), 3, 4);
                        if (this.var_aeii_b_a.a() != null && this.var_java_lang_String_arr_u.length > 1) {
                            this.var_aeii_e_I.var_int_d = 1;
                        }
                        this.var_aeii_e_H.a(this.var_aeii_e_I, this.var_int_c, (this.var_int_b + ((e)object).var_int_c) / 2, 3);
                        this.var_aeii_e_H.a((byte)0, true);
                        this.var_aeii_e_H.var_boolean_b = true;
                        this.var_aeii_e_H.a(this.var_aeii_e_z);
                        aeii.f.var_aeii_c_a.a(this.var_aeii_e_H);
                    } else if (string.equals(this.var_java_lang_String_arr_n[1])) {
                        this.var_aeii_e_N = new e(15, 15);
                        e e13 = new e(10, 0);
                        e13.a(null, aeii.f.java_lang_String_a(299), this.var_int_a, -1);
                        e13.var_aeii_d_a = this.var_aeii_d_arr_c[6];
                        this.var_boolean_arr_a = new boolean[12];
                        for (n2 = this.var_int_q; n2 <= 7; ++n2) {
                            if (var_int_arr_c[n2] < 0) continue;
                            this.var_boolean_arr_a[aeii.g.var_int_arr_c[n2]] = true;
                        }
                        object = new String[12];
                        for (n3 = 0; n3 < 12; ++n3) {
                            object[n3] = this.var_boolean_arr_a[n3] ? (Object)aeii.f.java_lang_String_a(42) : (byte[])var_java_lang_String_arr_e[n3];
                        }
                        String[] stringArray7 = new String[12 + this.U];
                        System.arraycopy(object, 0, stringArray7, 0, 12);
                        System.arraycopy(this.var_java_lang_String_arr_l, 0, stringArray7, 12, this.U);
                        this.var_aeii_e_O = new e(0, 0);
                        this.var_aeii_e_O.a(stringArray7, 0, 0, this.var_int_a, this.var_int_b - e13.var_int_c - (this.var_a_a_a_g_c.p << 1), 3, 4);
                        this.var_aeii_e_N.a(this.var_aeii_e_O, this.var_int_c, (this.var_int_b + e13.var_int_c) / 2, 3);
                        this.var_aeii_e_N.a(e13, 0, 0, 0);
                        this.var_aeii_e_N.var_boolean_b = true;
                        this.var_aeii_e_N.a((byte)0, true);
                        this.var_aeii_e_N.a(e2);
                        aeii.f.var_aeii_c_a.a(this.var_aeii_e_N);
                    } else if (string.equals(this.var_java_lang_String_arr_n[2])) {
                        if (this.var_java_lang_String_arr_s == null) {
                            this.a(2, "levels", aeii.f.java_lang_String_a(0), (f)e2);
                        } else {
                            this.void_a(e2);
                            aeii.f.var_aeii_c_a.a(this.var_aeii_e_D);
                        }
                    } else if (string.equals(this.var_java_lang_String_arr_n[3])) {
                        aeii.f.var_aeii_c_a.a(this.aeii_e_a(e2));
                    }
                } else if (by == 1) {
                    this.var_aeii_e_z = null;
                }
            } else if (e2 == this.var_aeii_e_A) {
                if (by == 0) {
                    if (this.var_java_lang_String_arr_r[n] == null) {
                        this.aD = n;
                        this.a(1, this.var_java_lang_String_arr_q[n], aeii.f.java_lang_String_a(0), (f)e2);
                    } else {
                        e e14 = this.a(this.var_java_lang_String_arr_p[n], this.var_java_lang_String_arr_r[n], this.var_int_b, this.var_int_b / 2, -1);
                        e14.a(e2);
                        aeii.f.var_aeii_c_a.a(e14);
                    }
                } else if (by == 1) {
                    this.var_aeii_e_A = null;
                }
            } else if (e2 == this.var_aeii_e_D) {
                if (by == 0) {
                    this.aE = this.var_aeii_e_E.var_int_arr_a[this.var_aeii_e_E.var_int_d];
                    if (this.W >= this.var_int_arr_n[this.aE]) {
                        this.a(3, this.var_java_lang_String_arr_t[this.aE], aeii.f.java_lang_String_a(0), (f)e2);
                    } else {
                        e e15 = this.a(null, aeii.f.java_lang_String_a(55), this.var_int_b, -1);
                        e15.a(e2);
                        aeii.f.var_aeii_c_a.a(e15);
                    }
                } else if (by == 1) {
                    this.var_aeii_e_F = null;
                    this.var_aeii_e_E = null;
                    this.var_aeii_e_D = null;
                }
            } else if (e2 == this.var_aeii_e_E) {
                if (by == 2 || by == 3) {
                    String string4 = aeii.g.java_lang_String_c(this.W);
                    this.var_aeii_e_F.a(null, aeii.f.a(54, aeii.g.java_lang_String_c(this.var_int_arr_n[this.var_aeii_e_E.var_int_arr_a[n]])) + "\n" + aeii.f.a(53, string4), this.var_int_a, -1);
                    this.var_aeii_e_F.void_a();
                }
            } else if (e2 == this.var_aeii_e_B) {
                if (by == 0) {
                    if (this.var_int_arr_l[n] + var_java_lang_String_arr_e.length == this.var_int_x) {
                        e e16 = this.a(null, aeii.f.java_lang_String_a(56), this.var_int_b, -1);
                        e16.a(e2);
                        aeii.f.var_aeii_c_a.a(e16);
                    } else {
                        this.V = n;
                        this.var_java_lang_String_c = string;
                        this.var_aeii_e_C = this.a(null, aeii.f.a(50, string), this.var_int_b, this.var_int_p, -1);
                        this.var_aeii_e_C.a(e2);
                        this.var_aeii_e_C.a((byte)0, true);
                        aeii.f.var_aeii_c_a.a(this.var_aeii_e_C);
                    }
                } else if (by == 1) {
                    this.var_aeii_e_B = null;
                }
            } else if (e2 == this.var_aeii_e_C) {
                if (by == 0) {
                    this.f(this.V);
                    e e17 = this.aeii_e_a(this.var_aeii_e_B.var_aeii_f_a);
                    object2 = this.a(null, aeii.f.a(51, this.var_java_lang_String_c), this.var_int_b, -1);
                    ((e)object2).a(e17);
                    aeii.f.var_aeii_c_a.a((f)object2);
                }
                this.var_aeii_e_C = null;
                this.var_java_lang_String_c = null;
            }
            if (e2.var_byte_a == 7) {
                aeii.f.var_aeii_c_a.a(this);
                return;
            }
            if (string == null || by != 0) return;
            if (string.equals(this.var_java_lang_String_arr_h[0])) {
                if (this.var_byte_h != 0 && this.var_byte_d == 0) {
                    this.var_aeii_e_w = this.a(null, aeii.f.java_lang_String_a(87), this.var_int_b, -1);
                    this.var_aeii_e_w.a((byte)0, true);
                    this.var_aeii_e_w.a(e2);
                    aeii.f.var_aeii_c_a.a(this.var_aeii_e_w);
                    return;
                } else {
                    this.a(this.var_byte_arr_h, this.var_int_N, this.var_int_O, (f)e2);
                }
                return;
            }
            if (!string.equals(this.var_java_lang_String_arr_h[1]) && e2 != this.var_aeii_e_h) {
                if (string.equals(aeii.f.java_lang_String_a(3))) {
                    this.var_aeii_e_h = new e(11, 0);
                    int n17 = this.var_int_q;
                    if (this.var_int_q > 7) {
                        n17 = 7;
                    }
                    String[] stringArray8 = new String[++n17];
                    for (n4 = 0; n4 < n17; ++n4) {
                        stringArray8[n4] = n4 + 1 + ". " + aeii.f.java_lang_String_a(n4 + 121);
                    }
                    object = this.var_aeii_e_h.aeii_e_a(string);
                    this.var_aeii_e_h.aeii_e_a(string).var_aeii_d_a = this.var_aeii_d_arr_c[3];
                    this.var_aeii_e_h.a(stringArray8, ((f)this).j / 2, (this.var_int_b + ((e)object).var_int_c) / 2, this.var_int_a, this.var_int_b - ((e)object).var_int_c, 3, 4);
                    this.var_aeii_e_h.a(e2);
                    aeii.f.var_aeii_c_a.a(this.var_aeii_e_h);
                    return;
                } else if (string.equals(aeii.f.java_lang_String_a(4))) {
                    this.var_aeii_e_s = this.a(string, this.var_aeii_d_arr_c[10]);
                    this.var_aeii_e_s.a(e2);
                    aeii.f.var_aeii_c_a.a(this.var_aeii_e_s);
                    return;
                } else if (string.equals(aeii.f.java_lang_String_a(5))) {
                    this.var_aeii_e_r = this.a(string, this.var_aeii_d_arr_c[2]);
                    this.var_aeii_e_r.a(e2);
                    aeii.f.var_aeii_c_a.a(this.var_aeii_e_r);
                    return;
                } else if (!string.equals(aeii.f.java_lang_String_a(6))) {
                    if (string.equals(aeii.f.java_lang_String_a(7))) {
                        this.var_aeii_e_y = new e(11, 0);
                        e e18 = this.var_aeii_e_y.aeii_e_a(string);
                        this.var_aeii_e_y.aeii_e_a(string).var_aeii_d_a = this.var_aeii_d_arr_c[6];
                        this.var_aeii_e_y.a(this.var_java_lang_String_arr_m, ((f)this).j / 2, (this.var_int_b + e18.var_int_c) / 2, this.var_int_a, this.var_int_b - e18.var_int_c, 3, 0);
                        this.var_aeii_e_y.a(e2);
                        aeii.f.var_aeii_c_a.a(this.var_aeii_e_y);
                        return;
                    } else if (string.equals(aeii.f.java_lang_String_a(8))) {
                        this.var_aeii_e_m = new e(15, 15);
                        e e19 = new e(10, 0);
                        new e(10, 0).var_aeii_d_a = this.var_aeii_d_arr_c[5];
                        e19.a(null, string, this.var_int_a, -1);
                        this.var_aeii_e_m.a(e19, 0, 0, 20);
                        n2 = e19.var_int_c;
                        e[] eArray = new e[4];
                        this.var_aeii_e_arr_c = new e[4];
                        for (int i = 0; i < 4; ++i) {
                            n = 8;
                            if (i != 0) {
                                n = 9;
                            }
                            if (i != 3) {
                                n |= 2;
                            }
                            eArray[i] = new e(10, n);
                            eArray[i].a(null, aeii.c.var_java_lang_String_arr_a[i], this.var_int_c, -1);
                            this.var_aeii_e_m.a(eArray[i], 0, n2, 20);
                            n = 4;
                            if (i != 0) {
                                n = 5;
                            }
                            if (i != 3) {
                                n |= 2;
                            }
                            this.var_aeii_e_arr_c[i] = new e(14, n);
                            this.var_aeii_e_arr_c[i].a(this.var_java_lang_String_arr_j, this.var_int_c, eArray[i].var_int_c);
                            this.var_aeii_e_arr_c[i].var_int_d = aeii.c.var_boolean_arr_a[i] ? 0 : 1;
                            this.var_aeii_e_m.a(this.var_aeii_e_arr_c[i], ((f)this).l, n2, 20);
                            n2 += eArray[i].var_int_c;
                        }
                        this.var_aeii_e_m.var_int_f = 2;
                        this.var_aeii_e_m.a(e2);
                        aeii.f.var_aeii_c_a.a(this.var_aeii_e_m);
                        return;
                    } else if (string.equals(aeii.f.java_lang_String_a(9))) {
                        this.var_aeii_e_b = new e(15, 15);
                        e e20 = new e(10, 0);
                        e20.a(null, string, this.var_int_a, -1);
                        e20.var_aeii_d_a = this.var_aeii_d_arr_c[7];
                        String[] stringArray9 = new String[20];
                        for (n4 = 0; n4 < var_int_arr_i.length; ++n4) {
                            stringArray9[n4] = aeii.f.java_lang_String_a(var_int_arr_i[n4]) + " " + n4 + "/" + 19;
                        }
                        this.var_aeii_e_c = new e(14, 2);
                        this.var_aeii_e_c.a(stringArray9, this.var_int_a, -1);
                        this.var_aeii_e_d = new e(10, 1);
                        this.var_aeii_e_d.a(null, aeii.f.java_lang_String_a(var_int_arr_j[0]), this.var_int_a, this.var_int_b - e20.var_int_c - this.var_aeii_e_c.var_int_c - (this.var_a_a_a_g_c.p << 1));
                        n4 = e20.var_int_c + this.var_a_a_a_g_c.p;
                        this.var_aeii_e_b.a(this.var_aeii_e_c, this.var_int_c, n4, 17);
                        this.var_aeii_e_b.a(this.var_aeii_e_d, this.var_int_c, n4 += this.var_aeii_e_c.var_int_c, 17);
                        this.var_aeii_e_b.a(e20, 0, 0, 0);
                        this.var_aeii_e_b.a(e2);
                        this.var_aeii_e_b.var_boolean_b = true;
                        aeii.f.var_aeii_c_a.a(this.var_aeii_e_b);
                        return;
                    } else if (string.equals(aeii.f.java_lang_String_a(10))) {
                        e e21 = new e(15, 15);
                        object2 = new e(10, 0);
                        ((e)object2).a(null, string, this.var_int_a, -1);
                        ((e)object2).var_aeii_d_a = this.var_aeii_d_arr_c[8];
                        object = new e(10, 0);
                        String string5 = aeii.f.a(16, this.var_java_lang_String_a);
                        ((e)object).a(null, string5, this.var_int_a, this.var_int_b - ((e)object2).var_int_c - (this.var_a_a_a_g_c.p << 1));
                        e21.a((e)object, 0, (this.var_int_b + ((e)object2).var_int_c) / 2, 6);
                        e21.a((e)object2, 0, 0, 0);
                        e21.a(e2);
                        e21.var_boolean_b = true;
                        aeii.f.var_aeii_c_a.a(e21);
                        return;
                    } else if (string.equals(aeii.f.java_lang_String_a(11))) {
                        this.var_aeii_e_v = this.a(null, aeii.f.java_lang_String_a(86), this.var_int_b, -1);
                        this.var_aeii_e_v.a((byte)0, true);
                        this.var_aeii_e_v.a(e2);
                        aeii.f.var_aeii_c_a.a(this.var_aeii_e_v);
                        return;
                    } else if (string.equals(aeii.f.java_lang_String_a(60))) {
                        this.a(this.var_byte_arr_g, this.var_int_p, this.var_int_b, (f)e2);
                        return;
                    } else if (string.equals(aeii.f.java_lang_String_a(61))) {
                        this.var_boolean_l = false;
                        this.void_b(this.var_aeii_a_b);
                        aeii.f.var_aeii_c_a.a(this);
                        return;
                    } else if (string.equals(aeii.f.java_lang_String_a(62))) {
                        this.a(this.var_byte_arr_arr_b, 0);
                        this.var_byte_e = this.var_byte_d;
                        this.var_byte_d = (byte)6;
                        this.var_boolean_i = true;
                        this.var_aeii_a_arr_a = this.var_aeii_a_b.a((int)this.var_aeii_a_b.var_short_b, (int)this.var_aeii_a_b.var_short_c, (byte)0);
                        this.var_int_z = 0;
                        this.var_boolean_b = true;
                        this.var_boolean_c = true;
                        this.var_aeii_a_b.a(this.var_byte_arr_arr_b, (int)this.var_aeii_a_b.var_short_b, (int)this.var_aeii_a_b.var_short_c);
                        this.var_a_a_a_g_q.a(var_byte_arr_arr_c[1]);
                        this.d(this.var_aeii_a_arr_a[this.var_int_z].var_short_b, this.var_aeii_a_arr_a[this.var_int_z].var_short_c);
                        this.var_boolean_e = true;
                        this.var_boolean_f = true;
                        aeii.f.var_aeii_c_a.a(this);
                        return;
                    } else if (string.equals(aeii.f.java_lang_String_a(63))) {
                        this.var_aeii_e_n = new e(15, 15);
                        this.var_aeii_e_n.i = this.var_int_b;
                        this.var_aeii_e_o = new e(2, 2);
                        this.var_aeii_e_p = new e(3, 1);
                        this.var_aeii_e_q = new e(10, 3);
                        this.var_aeii_e_q.var_boolean_a = true;
                        this.var_aeii_e_o.var_aeii_a_a = this.var_aeii_e_p.var_aeii_a_arr_a[0];
                        this.var_aeii_e_q.a(null, aeii.f.java_lang_String_a(184 + this.var_aeii_e_p.var_aeii_a_arr_a[0].var_byte_d), this.var_int_a, this.var_int_b - this.var_aeii_e_o.var_int_c - this.var_aeii_e_p.var_int_c);
                        this.var_aeii_e_n.a(this.var_aeii_e_o, 0, 0, 0);
                        this.var_aeii_e_n.a(this.var_aeii_e_q, 0, this.var_aeii_e_o.var_int_c, 0);
                        this.var_aeii_e_n.a(this.var_aeii_e_p, 0, this.var_int_b, 32);
                        this.var_aeii_e_n.var_boolean_b = true;
                        this.var_aeii_e_n.a(this);
                        this.var_aeii_e_n.a((byte)0, true);
                        aeii.f.var_aeii_c_a.a(this.var_aeii_e_n);
                        return;
                    } else if (string.equals(aeii.f.java_lang_String_a(64))) {
                        this.var_aeii_a_b.void_d();
                        this.k();
                        this.var_aeii_a_h = this.aeii_a_a(this.var_int_h, this.var_int_i, (byte)0);
                        this.var_byte_d = 0;
                        aeii.f.var_aeii_c_a.a(this);
                        return;
                    } else if (string.equals(aeii.f.java_lang_String_a(66))) {
                        this.m();
                        aeii.f.var_aeii_c_a.a(this);
                        return;
                    } else if (string.equals(aeii.f.java_lang_String_a(68))) {
                        this.void_a((byte)37, (int)this.var_aeii_a_b.var_short_b, (int)this.var_aeii_a_b.var_short_c);
                        aeii.f.var_aeii_c_a.a(this.a(null, aeii.f.java_lang_String_a(74), this.var_int_b, 1000));
                        aeii.c.b(9, 1);
                        this.var_aeii_a_b.void_d();
                        this.var_byte_d = 0;
                        return;
                    } else if (string.equals(aeii.f.java_lang_String_a(67))) {
                        if (this.boolean_b(this.var_aeii_a_b)) {
                            this.void_b(this.var_aeii_a_b.var_short_b, this.var_aeii_a_b.var_short_c, this.var_byte_arr_d[this.var_aeii_a_b.var_byte_e]);
                            aeii.f.var_aeii_c_a.a(this.a(null, aeii.f.java_lang_String_a(73), this.var_int_b, 1000));
                            this.var_byte_d = (byte)9;
                            aeii.c.b(9, 1);
                            this.var_long_d = this.var_long_a;
                        }
                        this.var_aeii_a_b.void_d();
                        return;
                    } else if (string.equals(aeii.f.java_lang_String_a(69))) {
                        this.var_byte_d = (byte)7;
                        this.var_aeii_a_arr_a = this.var_aeii_a_b.a((int)this.var_aeii_a_b.var_short_b, (int)this.var_aeii_a_b.var_short_c, (byte)1);
                        this.var_boolean_b = true;
                        this.var_boolean_c = true;
                        this.var_aeii_a_b.a(this.var_byte_arr_arr_b, (int)this.var_aeii_a_b.var_short_b, (int)this.var_aeii_a_b.var_short_c);
                        this.var_boolean_e = true;
                        aeii.f.var_aeii_c_a.a(this);
                        return;
                    } else if (string.equals(aeii.f.java_lang_String_a(70))) {
                        e e22 = new e(15, 15);
                        object2 = new e(10, 0);
                        ((e)object2).a(null, this.var_java_lang_String_b, this.var_int_a, -1);
                        object = new e(8, 0);
                        ((e)object).a(this.var_int_a, this.var_int_b - ((e)object2).var_int_c - this.var_a_a_a_g_c.p, this.var_byte_arr_arr_a, this.var_java_util_Vector_a);
                        e22.a((e)object, this.var_int_c, this.var_int_p + (((e)object2).var_int_c - this.var_a_a_a_g_c.p) / 2, 3);
                        e22.a((e)object2, 0, 0, 0);
                        e22.a(e2);
                        e22.var_boolean_b = true;
                        aeii.f.var_aeii_c_a.a(e22);
                        return;
                    } else if (string.equals(aeii.f.java_lang_String_a(71))) {
                        this.var_aeii_e_e.a((byte)0, false);
                        this.var_aeii_e_e.a(e2);
                        aeii.f.var_aeii_c_a.a(this.var_aeii_e_e);
                        return;
                    } else if (string.equals(aeii.f.java_lang_String_a(293))) {
                        this.a(this.var_java_lang_String_b, this.byte_arr_a());
                        e e23 = this.a(null, aeii.f.a(45, this.var_java_lang_String_b), this.var_int_b, 2000);
                        e23.a(this);
                        aeii.f.var_aeii_c_a.a(e23);
                        return;
                    } else if (string.equals(aeii.f.java_lang_String_a(305))) {
                        this.x();
                        aeii.c.a((Displayable)this.var_javax_microedition_lcdui_Form_c);
                        return;
                    } else if (string.equals(aeii.f.java_lang_String_a(306))) {
                        this.A();
                        this.y();
                        aeii.c.a((Displayable)this.var_javax_microedition_lcdui_Form_d);
                        return;
                    } else if (string.equals(aeii.f.java_lang_String_a(307))) {
                        this.z();
                        aeii.c.a((Displayable)this.var_javax_microedition_lcdui_Form_f);
                        return;
                    } else if (string.equals(aeii.f.java_lang_String_a(303))) {
                        this.A();
                        aeii.c.a((Displayable)this.var_javax_microedition_lcdui_Form_b);
                        return;
                    } else {
                        if (!string.equals(aeii.f.java_lang_String_a(314))) return;
                        this.F();
                        aeii.c.a((Displayable)this.var_javax_microedition_lcdui_TextBox_a);
                    }
                    return;
                } else {
                    this.var_aeii_e_g = new e(15, 15);
                    e e24 = new e(10, 0);
                    e24.a(null, string, this.var_int_a, -1);
                    e24.var_aeii_d_a = this.var_aeii_d_arr_c[4];
                    this.var_boolean_arr_a = new boolean[12];
                    for (n2 = this.var_int_q; n2 <= 7; ++n2) {
                        if (var_int_arr_c[n2] < 0) continue;
                        this.var_boolean_arr_a[aeii.g.var_int_arr_c[n2]] = true;
                    }
                    String[] stringArray10 = new String[12];
                    for (int i = 0; i < 12; ++i) {
                        stringArray10[i] = this.var_boolean_arr_a[i] ? aeii.f.java_lang_String_a(42) : var_java_lang_String_arr_e[i];
                    }
                    stringArray = new String[12 + this.U];
                    System.arraycopy(stringArray10, 0, stringArray, 0, 12);
                    System.arraycopy(this.var_java_lang_String_arr_l, 0, stringArray, 12, this.U);
                    this.var_aeii_e_f = new e(0, 0);
                    this.var_aeii_e_f.a(stringArray, 0, 0, this.var_int_a, this.var_int_b - e24.var_int_c - (this.var_a_a_a_g_c.p << 1), 3, 4);
                    this.var_aeii_e_g.a(this.var_aeii_e_f, this.var_int_c, (this.var_int_b + e24.var_int_c) / 2, 3);
                    this.var_aeii_e_g.a(e24, 0, 0, 0);
                    this.var_aeii_e_g.var_boolean_b = true;
                    this.var_aeii_e_g.a((byte)0, true);
                    this.var_aeii_e_g.a(e2);
                    aeii.f.var_aeii_c_a.a(this.var_aeii_e_g);
                }
                return;
            }
            aeii.f.var_aeii_c_a.a(this);
            if (e2 == this.var_aeii_e_h) {
                this.var_int_x = n;
                this.var_aeii_e_h = null;
            } else {
                this.var_int_x = 0;
            }
            this.E();
            this.var_byte_c = 0;
            this.var_byte_arr_m[1] = 0;
            this.var_boolean_j = true;
            aeii.f.var_aeii_c_a.void_a();
            System.gc();
            this.e();
            this.void_c(this.var_int_x);
            this.var_boolean_j = false;
            this.q();
            this.var_byte_d = 0;
            return;
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
        }
    }

    private a aeii_a_a(a a2, int n, int n2) {
        byte by = this.var_byte_b;
        this.var_int_arr_b[by] = this.var_int_arr_b[by] - a2.var_int_j;
        a2.var_int_h = 100;
        a2.void_a(n, n2);
        if (!this.var_java_util_Vector_a.contains(a2)) {
            this.var_java_util_Vector_a.addElement(a2);
        }
        this.var_boolean_u = true;
        return a2;
    }

    private a aeii_a_a(byte by, int n, int n2) {
        byte by2 = this.var_byte_b;
        this.var_int_arr_b[by2] = this.var_int_arr_b[by2] - aeii.a.var_short_arr_a[by];
        this.var_boolean_u = true;
        return aeii.a.a(by, this.var_byte_b, n, n2);
    }

    private void i() {
        this.var_java_util_Vector_a = new Vector();
        this.var_aeii_a_b = null;
        this.var_aeii_a_arr_a = null;
        this.var_aeii_a_j = null;
    }

    private void void_c(int n) {
        try {
            int n2;
            int n3;
            int n4;
            DataInputStream dataInputStream;
            aeii.c.f();
            this.var_java_util_Vector_c = new Vector();
            this.var_boolean_o = false;
            this.var_boolean_d = true;
            this.var_boolean_g = false;
            this.var_boolean_s = false;
            this.var_java_util_Vector_b = null;
            this.var_aeii_a_c = null;
            this.var_aeii_a_i = null;
            this.var_aeii_a_d = null;
            this.var_aeii_a_e = null;
            this.var_java_util_Vector_e.removeAllElements();
            this.var_short_a = 0;
            this.var_byte_b = 0;
            this.var_byte_b = 0;
            this.ay = 0;
            this.i();
            this.var_aeii_a_arr_b = null;
            this.var_byte_arr_arr_a = null;
            this.var_byte_arr_arr_b = null;
            this.var_byte_f = 0;
            for (int i = 0; i < 5; ++i) {
                this.var_byte_arr_l[i] = -1;
            }
            this.var_boolean_i = true;
            aeii.c.g();
            if (this.var_byte_c == 0) {
                this.var_java_lang_String_b = aeii.f.java_lang_String_a(n + 113);
                dataInputStream = new DataInputStream(aeii.c.java_io_InputStream_a("m" + n + ".aem"));
            } else {
                this.var_java_lang_String_b = this.java_lang_String_b(n);
                dataInputStream = aeii.g.java_io_DataInputStream_a(n);
            }
            this.var_int_f = dataInputStream.readInt();
            this.var_int_g = dataInputStream.readInt();
            this.var_byte_arr_arr_a = new byte[this.var_int_f][this.var_int_g];
            this.var_byte_arr_arr_b = new byte[this.var_int_f][this.var_int_g];
            this.var_byte_arr_arr_g = new byte[this.var_int_f][this.var_int_g];
            this.as = 0;
            int n5 = 0;
            Vector<byte[]> vector = new Vector<byte[]>();
            Vector<byte[]> vector2 = new Vector<byte[]>();
            for (n4 = 0; n4 < this.var_int_f; n4 = (int)((short)(n4 + 1))) {
                for (n3 = 0; n3 < this.var_int_g; n3 = (int)((short)(n3 + 1))) {
                    this.var_byte_arr_arr_a[n4][n3] = dataInputStream.readByte();
                    this.var_byte_arr_arr_b[n4][n3] = 0;
                    if (!aeii.g.b(this.var_byte_arr_arr_a[n4][n3])) continue;
                    n2 = this.int_a(n4, n3);
                    byte[] byArray = new byte[3];
                    byte[] byArray2 = byArray;
                    byArray[0] = (byte)n4;
                    byArray2[1] = (byte)n3;
                    byArray2[2] = (byte)n2;
                    vector.addElement(byArray2);
                    ++n5;
                    if (this.byte_a(n4, n3) != 9) continue;
                    if (this.var_byte_c == 1 && n2 != 0 && this.var_byte_arr_l[n2] == -1) {
                        this.var_byte_arr_d[this.var_byte_f] = (byte)n2;
                        this.var_byte_f = (byte)(this.var_byte_f + 1);
                    }
                    byte[] byArray3 = new byte[2];
                    byte[] byArray4 = byArray3;
                    byArray3[0] = (byte)n4;
                    byArray4[1] = (byte)n3;
                    vector2.addElement(byArray4);
                    ++this.as;
                }
            }
            this.var_int_arr_m = new int[n5];
            this.var_byte_arr_arr_e = new byte[n5][];
            vector.copyInto((Object[])this.var_byte_arr_arr_e);
            this.var_byte_arr_arr_f = new byte[this.as][];
            vector2.copyInto((Object[])this.var_byte_arr_arr_f);
            this.var_int_t = this.var_int_f * 24;
            this.var_int_u = this.var_int_g * 24;
            if (this.var_byte_c == 1) {
                for (n4 = 0; n4 < this.var_byte_f; n4 = (int)((short)(n4 + 1))) {
                    this.var_int_arr_b[n4] = this.var_int_r;
                }
            } else {
                this.var_byte_f = (byte)2;
                this.var_int_arr_b[0] = 0;
                this.var_int_arr_b[1] = 0;
                this.var_byte_arr_l[1] = 0;
                this.var_byte_arr_l[2] = 1;
                this.var_byte_arr_d[0] = 1;
                this.var_byte_arr_d[1] = 2;
                this.var_byte_arr_e[0] = 0;
                this.var_byte_arr_e[1] = 1;
                this.var_byte_arr_m[0] = 1;
                this.var_byte_arr_m[1] = 0;
                this.var_int_s = 100;
            }
            for (n4 = 0; n4 < this.var_byte_arr_arr_e.length; n4 = (int)((short)(n4 + 1))) {
                n3 = this.var_byte_arr_arr_e[n4][2];
                int n6 = this.int_d(n3);
                if (n3 <= 0 || n6 >= 0 && this.var_byte_arr_m[n6] != 2) continue;
                this.void_b(this.var_byte_arr_arr_e[n4][0], this.var_byte_arr_arr_e[n4][1], 0);
            }
            n2 = dataInputStream.readInt();
            dataInputStream.skip(n2 << 2);
            n3 = dataInputStream.readInt();
            this.var_aeii_a_arr_b = new a[this.var_byte_f];
            this.var_aeii_a_arr_arr_a = new a[this.var_byte_f][4];
            this.var_int_arr_a = new int[this.var_byte_f];
            for (n4 = 0; n4 < n3; n4 = (int)((short)(n4 + 1))) {
                byte by = dataInputStream.readByte();
                n2 = dataInputStream.readShort() / 24;
                n5 = dataInputStream.readShort() / 24;
                byte by2 = (byte)(by % 12);
                byte by3 = (byte)this.int_d(1 + by / 12);
                if (this.var_byte_arr_m[by3] == 2) continue;
                a a2 = aeii.a.a(by2, by3, n2, n5);
                if (by2 != 9) continue;
                this.var_aeii_a_arr_b[by3] = a2;
            }
            dataInputStream.close();
            this.var_aeii_e_e = this.var_byte_c == 0 ? this.a(aeii.f.java_lang_String_a(121 + this.var_int_x), aeii.f.java_lang_String_a(129 + this.var_int_x), this.var_int_b, -1) : (this.var_aeii_e_e = this.a(aeii.f.java_lang_String_a(71), aeii.f.java_lang_String_a(137), this.var_int_b, -1));
            this.var_boolean_A = false;
            this.var_aeii_a_r = null;
            this.var_aeii_a_n = null;
            this.var_aeii_a_o = null;
            this.var_aeii_a_p = null;
            this.var_aeii_a_q = null;
            this.var_aeii_a_a = null;
            for (n4 = 0; n4 < this.var_aeii_a_arr_b.length; n4 = (int)((short)(n4 + 1))) {
                if (this.var_aeii_a_arr_b[n4] == null) {
                    this.var_byte_arr_arr_d[n4][0] = 0;
                    this.var_byte_arr_arr_d[n4][1] = 0;
                    continue;
                }
                this.var_byte_arr_arr_d[n4][0] = (byte)this.var_aeii_a_arr_b[n4].var_short_b;
                this.var_byte_arr_arr_d[n4][1] = (byte)this.var_aeii_a_arr_b[n4].var_short_c;
            }
            if (this.var_byte_c == 1) {
                this.ay = 100;
                this.var_boolean_m = true;
                for (n4 = 0; n4 < this.var_byte_f; n4 = (int)((short)(n4 + 1))) {
                    if (this.var_byte_arr_m[n4] != 2) {
                        this.var_byte_b = (byte)n4;
                        break;
                    }
                    this.var_short_a = (short)(this.var_short_a + 1);
                }
            }
            if (this.var_aeii_a_arr_b.length > 0 && this.var_aeii_a_arr_b[this.var_byte_b] != null) {
                this.void_b(this.var_aeii_a_arr_b[this.var_byte_b].var_short_b, this.var_aeii_a_arr_b[this.var_byte_b].var_short_c);
                this.d(this.var_aeii_a_arr_b[this.var_byte_b].var_short_b, this.var_aeii_a_arr_b[this.var_byte_b].var_short_c);
            }
            this.var_aeii_a_arr_d = new a[this.var_byte_arr_arr_e.length];
            this.var_byte_arr_q = new byte[this.var_byte_arr_arr_e.length];
            this.var_a_a_a_g_arr_b = new a.a.a.g[this.var_byte_arr_arr_e.length];
            for (n4 = 0; n4 < this.var_byte_arr_arr_e.length; n4 = (int)((short)(n4 + 1))) {
                if (this.byte_a((int)this.var_byte_arr_arr_e[n4][0], (int)this.var_byte_arr_arr_e[n4][1]) != 8) continue;
                this.var_a_a_a_g_arr_b[n4] = a.a.a.g.a(this.var_a_a_a_g_o, 0, -1, 0, 1, 250, (byte)0);
                this.var_a_a_a_g_arr_b[n4].var_boolean_b = false;
            }
            if (this.var_byte_arr_m[this.var_byte_b] == 0) {
                this.o();
            }
            if (this.var_byte_n != 0 || this.var_byte_f == 0) {
                aeii.c.void_a(var_int_arr_g[1], 0);
            }
            if (this.var_byte_n == 0) {
                this.void_e(n);
                return;
            }
            this.var_int_v = 0;
            this.var_int_w = 0;
            this.var_int_h = 0;
            this.var_int_i = 0;
            this.var_a_a_a_g_q.b(this.var_int_h * 24, this.var_int_i * 24);
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
        }
    }

    private void a(int[] nArray) {
        aeii.c.a(nArray, true);
        for (int i = 0; i < this.var_int_f; ++i) {
            for (int j = 0; j < this.var_int_g; ++j) {
                if (this.var_byte_arr_arr_b[i][j] <= 0) continue;
                this.var_byte_arr_arr_a[i][j] = (byte)aeii.c.a(nArray);
            }
        }
        aeii.c.a(nArray, false);
        this.j();
    }

    private void a(int n, int n2, int n3, int n4, int[] nArray) {
        n2 = this.var_int_f + n + n2;
        n4 = this.var_int_g + n3 + n4;
        byte[][] byArray = new byte[n2][n4];
        aeii.c.a(nArray, true);
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n4; ++j) {
                int n5 = i - n;
                int n6 = j - n3;
                byArray[i][j] = n5 >= 0 && n6 >= 0 && n5 < this.var_int_f && n6 < this.var_int_g ? this.var_byte_arr_arr_a[n5][n6] : (byte)aeii.c.a(nArray);
            }
        }
        aeii.c.a(nArray, false);
        for (int i = 0; i < this.var_java_util_Vector_a.size(); ++i) {
            a a2 = (a)this.var_java_util_Vector_a.elementAt(i);
            a2.void_a(a2.var_short_b + n, a2.var_short_c + n3);
        }
        this.var_byte_arr_arr_a = byArray;
        this.var_int_f = n2;
        this.var_int_g = n4;
        this.var_byte_arr_arr_b = new byte[this.var_int_f][this.var_int_g];
        this.var_byte_arr_arr_g = new byte[this.var_int_f][this.var_int_g];
        this.j();
        this.var_int_t = this.var_int_f * 24;
        this.var_int_u = this.var_int_g * 24;
        this.var_int_v = 0;
        this.var_int_w = 0;
        this.var_int_h = 0;
        this.var_int_i = 0;
        this.var_a_a_a_g_q.b(this.var_int_h * 24, this.var_int_i * 24);
    }

    private void j() {
        try {
            int n;
            int n2;
            this.var_byte_f = 0;
            for (n2 = 0; n2 < 5; ++n2) {
                this.var_byte_arr_l[n2] = -1;
            }
            this.as = 0;
            n2 = 0;
            Vector<byte[]> vector = new Vector<byte[]>();
            Vector<byte[]> vector2 = new Vector<byte[]>();
            for (n = 0; n < this.var_int_f; n = (int)((short)(n + 1))) {
                for (int n3 = 0; n3 < this.var_int_g; n3 = (int)((short)(n3 + 1))) {
                    if (!aeii.g.b(this.var_byte_arr_arr_a[n][n3])) continue;
                    int n4 = this.int_a(n, n3);
                    byte[] byArray = new byte[3];
                    byte[] byArray2 = byArray;
                    byArray[0] = (byte)n;
                    byArray2[1] = (byte)n3;
                    byArray2[2] = (byte)n4;
                    vector.addElement(byArray2);
                    ++n2;
                    if (this.byte_a(n, n3) != 9) continue;
                    if (this.var_byte_c == 1 && n4 != 0 && this.var_byte_arr_l[n4] == -1) {
                        this.var_byte_arr_d[this.var_byte_f] = (byte)n4;
                        this.var_byte_f = (byte)(this.var_byte_f + 1);
                    }
                    byte[] byArray3 = new byte[2];
                    byte[] byArray4 = byArray3;
                    byArray3[0] = (byte)n;
                    byArray4[1] = (byte)n3;
                    vector2.addElement(byArray4);
                    ++this.as;
                }
            }
            this.var_int_arr_m = new int[n2];
            this.var_byte_arr_arr_e = new byte[n2][];
            vector.copyInto((Object[])this.var_byte_arr_arr_e);
            this.var_byte_arr_arr_f = new byte[this.as][];
            vector2.copyInto((Object[])this.var_byte_arr_arr_f);
            this.var_aeii_a_arr_d = new a[this.var_byte_arr_arr_e.length];
            this.var_byte_arr_q = new byte[this.var_byte_arr_arr_e.length];
            this.var_a_a_a_g_arr_b = new a.a.a.g[this.var_byte_arr_arr_e.length];
            for (n = 0; n < this.var_byte_arr_arr_e.length; n = (int)((short)(n + 1))) {
                if (this.byte_a((int)this.var_byte_arr_arr_e[n][0], (int)this.var_byte_arr_arr_e[n][1]) != 8) continue;
                this.var_a_a_a_g_arr_b[n] = a.a.a.g.a(this.var_a_a_a_g_o, 0, -1, 0, 1, 250, (byte)0);
                this.var_a_a_a_g_arr_b[n].var_boolean_b = false;
            }
            return;
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            return;
        }
    }

    private byte[] byte_arr_a() {
        try {
            int n;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt(this.var_int_f);
            dataOutputStream.writeInt(this.var_int_g);
            for (n = 0; n < this.var_int_f; ++n) {
                for (int i = 0; i < this.var_int_g; ++i) {
                    dataOutputStream.writeByte(this.var_byte_arr_arr_a[n][i]);
                }
            }
            dataOutputStream.writeInt(0);
            dataOutputStream.writeInt(this.var_java_util_Vector_a.size());
            for (n = 0; n < this.var_java_util_Vector_a.size(); ++n) {
                a a2 = (a)this.var_java_util_Vector_a.elementAt(n);
                dataOutputStream.writeByte((this.var_byte_arr_d[a2.var_byte_e] - 1) * 12 + a2.var_byte_d);
                dataOutputStream.writeShort(a2.var_short_b * 24);
                dataOutputStream.writeShort(a2.var_short_c * 24);
            }
            dataOutputStream.flush();
            byte[] byArray = byteArrayOutputStream.toByteArray();
            dataOutputStream.close();
            return byArray;
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            return null;
        }
    }

    private void k() {
        this.var_int_z = 0;
        this.var_aeii_a_b = null;
        this.var_aeii_a_arr_a = new a[0];
        this.a(this.var_byte_arr_arr_b, 0);
        this.var_boolean_b = false;
        this.var_boolean_c = false;
    }

    private void a(byte[][] byArray, int n) {
        for (int i = 0; i < this.var_int_f; ++i) {
            for (int j = 0; j < this.var_int_g; ++j) {
                byArray[i][j] = (byte)n;
            }
        }
    }

    private void c(a a2) {
        aeii.c.f(10);
        this.var_aeii_a_j = null;
        this.a(this.var_byte_arr_arr_b, 0);
        this.var_boolean_b = false;
        if (this.var_byte_arr_m[this.var_byte_b] == 1) {
            this.Z = 1;
            this.var_boolean_d = true;
            this.var_a_a_a_g_q.a(var_byte_arr_arr_c[0]);
            this.var_byte_d = (byte)3;
            this.b(this.byte_arr_a(a2, (byte)0));
            aeii.c.b(11, 1);
            return;
        }
        if (this.var_byte_arr_m[this.var_byte_b] == 0) {
            this.var_byte_k = (byte)4;
            this.var_byte_d = 0;
        }
    }

    private byte[] byte_arr_a(a object, byte by) {
        int n = 0;
        byte[] byArray = new byte[this.var_java_lang_String_arr_g.length];
        if (by == 1 && this.byte_a((int)this.var_aeii_a_b.var_short_b, (int)this.var_aeii_a_b.var_short_c) == 9 && this.boolean_b(this.var_aeii_a_b.var_short_b, this.var_aeii_a_b.var_short_c, ((a)object).var_byte_e)) {
            ++n;
            byArray[0] = 0;
        }
        if (this.boolean_a((a)object)) {
            byArray[n++] = 2;
        } else if (this.boolean_b((a)object)) {
            byArray[n++] = 1;
        }
        if ((by == 1 || ((a)object).var_byte_d != 7) && ((a)object).var_byte_arr_arr_a.length > 0 && ((a)object).a((int)((a)object).var_short_b, (int)((a)object).var_short_c, (byte)0).length > 0) {
            byArray[n++] = 3;
        }
        if (((a)object).a((short)32) && ((a)object).a((int)((a)object).var_short_b, (int)((a)object).var_short_c, (byte)1).length > 0) {
            byArray[n++] = 4;
        }
        byArray[n++] = by == 1 ? 5 : 6;
        object = new byte[n];
        System.arraycopy(byArray, 0, object, 0, n);
        return object;
    }

    private void l() {
        if (aeii.c.var_boolean_b && this.var_int_F < 4) {
            this.var_aeii_d_d = new d("logo", (int)this.var_java_lang_String_arr_j, -1);
            this.var_int_F = 3;
            this.var_int_G = 40;
        }
        switch (this.var_int_F) {
            case 0: {
                if (this.var_long_a >= 1200L) {
                    this.var_int_F = 1;
                }
                this.var_int_G = 40;
                return;
            }
            case 1: {
                if (this.var_int_G <= 0) {
                    this.var_aeii_d_b = null;
                    this.var_int_G = 0;
                    ++this.var_int_F;
                    this.void_a(0, 0, 3);
                    return;
                }
                --this.var_int_G;
                return;
            }
            case 2: {
                this.void_a(1, 2, 3);
                this.var_aeii_d_d = new d("logo", (int)this.var_java_lang_String_arr_j, -1);
                ++this.var_int_F;
                return;
            }
            case 3: {
                if (++this.var_int_G <= 40) break;
                try {
                    this.var_aeii_d_c = new d("splash", this.var_int_a, -1);
                    this.var_aeii_d_e = new d("glow");
                }
                catch (Exception exception) {}
                this.var_int_G = this.var_aeii_d_c != null ? 0 : 11;
                ++this.var_int_F;
                aeii.f.var_aeii_c_a.b();
                return;
            }
            case 4: {
                if (this.var_int_G < 16) {
                    ++this.var_int_G;
                    ++this.var_int_H;
                    if (this.var_aeii_d_e == null) break;
                    this.P = -this.var_aeii_d_e.var_int_a;
                    return;
                }
                if (this.P >= ((f)this).j << 2) {
                    if (this.var_aeii_d_e != null) {
                        this.P = -this.var_aeii_d_e.var_int_a;
                    }
                } else {
                    this.P += this.var_aeii_d_d.var_int_a / 6;
                }
                if (this.var_long_a % 100L == 0L) {
                    boolean bl = this.var_boolean_k = !this.var_boolean_k;
                }
                if (this.var_aeii_d_c != null && !aeii.f.var_aeii_c_a.boolean_a() || !this.boolean_a()) break;
                if (this.var_aeii_d_e != null) {
                    this.P = -this.var_aeii_d_e.var_int_a;
                }
                this.var_boolean_k = false;
                int n = this.var_aeii_d_d.b + 1;
                this.a(this.var_byte_arr_f, (((f)this).k + n) / 2, ((f)this).k - n, (f)(this.var_aeii_d_c == null ? null : this));
                aeii.f.var_aeii_c_a.b();
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final void b() {
        int n;
        block289: {
            int n2;
            this.var_long_a += 50L;
            if (this.var_byte_h == 2) {
                int n3;
                g g2 = this;
                if (g2.var_boolean_C && g2.var_long_a - g2.var_long_n >= g2.var_long_m) {
                    g2.var_boolean_C = false;
                }
                for (n3 = 0; n3 < g2.var_java_util_Vector_g.size(); ++n3) {
                    ((a.a.a.g)g2.var_java_util_Vector_g.elementAt(n3)).void_c();
                }
                for (n3 = 0; n3 < g2.var_java_util_Vector_g.size(); ++n3) {
                    a.a.a.g g3 = (a.a.a.g)g2.var_java_util_Vector_g.elementAt(n3);
                    if (g3.var_boolean_b) continue;
                    g2.b(g3);
                }
                g2.var_aeii_h_a.b();
                g2.var_aeii_h_b.b();
                if (g2.var_boolean_E) {
                    ++g2.var_int_G;
                    if (g2.var_int_G >= 16) {
                        g2.var_boolean_E = false;
                        g2.var_aeii_h_a.a();
                    }
                    g2.var_boolean_F = true;
                    g2.var_boolean_a = true;
                    return;
                }
                if (g2.var_boolean_B) {
                    if (g2.var_long_a - g2.var_long_l < 300L) return;
                    g2.var_java_util_Vector_g.removeAllElements();
                    g2.var_aeii_h_b = null;
                    g2.var_aeii_h_a = null;
                    g2.var_java_util_Vector_c = new Vector();
                    g2.h();
                    g2.var_byte_h = 1;
                    aeii.c.f();
                    aeii.c.b(var_int_arr_g[g2.var_byte_arr_d[g2.var_byte_b]], 0);
                    aeii.f.var_aeii_c_a.b();
                    g2.var_boolean_u = true;
                    g2.var_boolean_v = true;
                    return;
                }
                if (!g2.var_aeii_h_a.var_boolean_b) return;
                if (g2.var_boolean_D && g2.var_aeii_h_b.var_byte_a > 0) {
                    if (!g2.var_aeii_h_b.var_boolean_a) {
                        g2.var_aeii_h_b.a();
                    }
                    if (!g2.var_aeii_h_b.var_boolean_b) return;
                }
                g2.var_boolean_B = true;
                g2.var_long_l = g2.var_long_a;
                return;
            }
            if (this.var_byte_h == 3) {
                g g4 = this;
                if (g4.var_boolean_x) {
                    --g4.var_int_G;
                    if (g4.var_int_G >= 0) return;
                    g4.var_int_G = 0;
                    g4.var_byte_h = g4.var_byte_j;
                    g4.var_aeii_d_g = null;
                    g4.var_java_lang_String_arr_o = null;
                    return;
                }
                if (!(g4.af == 2 && g4.var_int_G < 40 || g4.af != 2 && g4.var_int_G < 16)) {
                    --g4.ac;
                    if (g4.ac < g4.ah) {
                        g4.ac = g4.ah + g4.ab - (g4.ah - g4.ac);
                        ++g4.ae;
                    }
                } else {
                    ++g4.var_int_G;
                }
                if (g4.ae < g4.var_java_lang_String_arr_o.length && !aeii.f.var_aeii_c_a.boolean_b(var_int_d)) return;
                g4.var_boolean_x = true;
                if (g4.var_aeii_d_g != null || g4.ae < g4.var_java_lang_String_arr_o.length) {
                    g4.var_int_G = g4.ag == 2 ? 40 : 16;
                    return;
                }
                g4.var_int_G = 0;
                return;
            }
            if (this.var_byte_h == 0) {
                this.l();
                return;
            }
            this.s();
            if (this.var_int_L != -1) {
                if (aeii.c.var_boolean_arr_a[2]) {
                    e e2 = this.a(aeii.f.a(196 + this.var_int_L, true), (byte)-1, (byte)2);
                    this.a(aeii.f.a(196 + this.var_int_L, true), (byte)-1, (byte)2).h = 7831691;
                    e2.var_int_g = 7831691;
                    e2.var_int_a = 0xF7F7E7;
                }
                this.var_int_L = -1;
            }
            if (aeii.f.var_aeii_c_a.var_aeii_f_a != this) return;
            if (this.var_byte_d == 0) {
                for (int i = 0; i < this.var_a_a_a_g_arr_b.length; ++i) {
                    n2 = this.int_a((int)this.var_byte_arr_arr_e[i][0], (int)this.var_byte_arr_arr_e[i][1]);
                    if (this.var_a_a_a_g_arr_b[i] == null || n2 == -1 || n2 == 0 || this.var_a_a_a_g_arr_b[i].var_boolean_b || aeii.c.var_java_util_Random_a.nextInt() % 8 != 0) continue;
                    this.var_a_a_a_g_arr_b[i].var_boolean_b = true;
                    this.var_a_a_a_g_arr_b[i].c(0);
                    this.var_a_a_a_g_arr_b[i].r = 1;
                    this.var_a_a_a_g_arr_b[i].b((this.var_byte_arr_arr_e[i][0] + 1) * 24 - this.var_a_a_a_g_o.o, this.var_byte_arr_arr_e[i][1] * 24 - 2);
                    this.var_java_util_Vector_d.addElement(this.var_a_a_a_g_arr_b[i]);
                }
            }
            if (this.var_long_a - this.var_long_h >= 300L) {
                this.var_boolean_h = !this.var_boolean_h;
                this.var_long_h = this.var_long_a;
            }
            if (this.var_boolean_C && this.var_long_a - this.var_long_n >= this.var_long_m) {
                this.var_boolean_C = false;
            }
            if (this.var_boolean_g) {
                ++this.var_int_G;
                if (this.var_int_G <= 16) return;
                if (this.var_byte_d == 10) {
                    this.aC = 1;
                } else if (this.var_byte_d == 11) {
                    if (this.var_byte_c == 0) {
                        this.var_boolean_q = true;
                        this.var_boolean_p = true;
                        this.R = 0;
                    }
                    this.var_int_M = 0;
                    this.var_long_d = this.var_long_a;
                } else {
                    this.b(this.var_aeii_a_f, this.var_aeii_a_g);
                    this.var_aeii_a_k = null;
                    this.k();
                }
                this.var_boolean_g = false;
                return;
            }
            if (this.var_boolean_q) {
                if (this.var_boolean_o) {
                    if (this.R < 16) {
                        ++this.R;
                    }
                } else if (this.var_boolean_p) {
                    ++this.R;
                    if (this.R > 16) {
                        this.var_boolean_p = false;
                    }
                }
            }
            if (this.var_java_util_Vector_b != null) {
                this.var_int_C = (this.var_int_C + 1) % 12;
            }
            if (this.var_boolean_b) {
                if (this.aj == 0) {
                    ++this.ai;
                    if (this.ai >= 15) {
                        this.aj = 1;
                    }
                } else {
                    --this.ai;
                    if (this.ai <= 0) {
                        this.aj = 0;
                    }
                }
                if (this.aa > 0) {
                    this.aa -= 4;
                    if (this.aa < 0) {
                        this.aa = 0;
                    }
                }
            }
            if (this.var_boolean_d && this.var_long_a - this.var_long_b >= 200L) {
                this.var_a_a_a_g_q.void_e();
                this.var_long_b = this.var_long_a;
            }
            n2 = this.var_int_h * 24;
            int n4 = this.var_int_i * 24;
            int n5 = this.var_a_a_a_g_q.m;
            int n6 = this.var_a_a_a_g_q.n;
            if (n2 > n5) {
                n5 += 8;
            } else if (n2 < n5) {
                n5 -= 8;
            }
            if (n4 > n6) {
                n6 += 8;
            } else if (n4 < n6) {
                n6 -= 8;
            }
            this.var_a_a_a_g_q.b(n5, n6);
            if (!this.var_boolean_n && this.Q > 0) {
                this.Q = this.Q < 2 ? 0 : (this.Q /= 2);
                this.var_boolean_u = true;
                this.var_boolean_v = true;
            } else if (this.var_byte_d == 8) {
                if (this.var_int_D == 0) {
                    if (this.Q >= var_byte_a) {
                        this.var_int_D = 1;
                        this.n();
                        Object object = this.var_byte_arr_m[this.var_byte_b] == 1 ? "" + this.an : "?";
                        object = this.a(aeii.f.java_lang_String_a(75), aeii.f.a(76, (String)object), this.var_int_b, 1500);
                        aeii.f.var_aeii_c_a.a((f)object);
                        ((e)object).var_int_g = var_int_arr_f[this.var_byte_arr_d[this.var_byte_b]];
                        aeii.c.void_a(var_int_arr_g[this.var_byte_arr_d[this.var_byte_b]], 0);
                        return;
                    }
                    if (this.Q == 0) {
                        this.var_boolean_n = true;
                        this.Q = 1;
                    } else {
                        this.Q <<= 1;
                    }
                    this.var_boolean_u = true;
                    this.var_boolean_v = true;
                } else {
                    for (n4 = this.var_java_util_Vector_a.size() - 1; n4 >= 0; --n4) {
                        a a2 = (a)this.var_java_util_Vector_a.elementAt(n4);
                        if (a2.var_byte_f == 3 || this.var_byte_b != a2.var_byte_e || this.byte_a((int)a2.var_short_b, (int)a2.var_short_c) != 7 && !this.boolean_a((int)a2.var_short_b, (int)a2.var_short_c, (int)this.var_byte_arr_e[a2.var_byte_e]) || a2.var_int_h >= 100) continue;
                        int n7 = 100 - a2.var_int_h;
                        if (n7 > 20) {
                            n7 = 20;
                        }
                        a2.var_int_h += n7;
                        a.a.a.g g5 = a.a.a.g.a("+" + n7, 0, -4, (byte)1);
                        g5.b(a2.m + a2.o / 2, a2.n + a2.p);
                        this.var_java_util_Vector_c.addElement(g5);
                    }
                    this.var_int_D = 0;
                    this.var_boolean_n = false;
                    this.var_byte_d = 0;
                }
            } else if (this.var_byte_d == 9) {
                this.var_byte_d = 0;
            } else if (this.var_byte_d == 11) {
                if (!this.var_boolean_g && this.var_int_M == 0 && (this.var_byte_c == 1 || this.var_long_a - this.var_long_d >= 3000L || aeii.f.var_aeii_c_a.boolean_a())) {
                    this.var_int_b = ((f)this).k;
                    this.var_int_p = ((f)this).m;
                    this.a(this.var_byte_arr_f, ((f)this).m, ((f)this).k, null);
                    this.var_int_M = 1;
                    this.var_boolean_p = false;
                }
            } else if (this.var_byte_d != 10 && this.var_byte_d != 14) {
                if (this.var_byte_d == 13) {
                    if (this.var_int_I == 0) {
                        aeii.c.d(200);
                        n2 = this.var_aeii_a_f.c(this.var_aeii_a_g);
                        this.var_aeii_a_g.a(400);
                        aeii.c.b(14, 1);
                        this.a(this.var_a_a_a_g_g, this.var_aeii_a_g.m, this.var_aeii_a_g.n, 0, 0, 2, 50);
                        a.a.a.g g6 = a.a.a.g.a("-" + n2, 0, -4, (byte)1);
                        n4 = this.var_aeii_a_g.m + this.var_aeii_a_g.o / 2;
                        if (n4 + g6.o / 2 > this.var_int_t) {
                            n4 = this.var_int_t - g6.o / 2;
                        } else if (n4 - g6.o / 2 < 0) {
                            n4 = g6.o / 2;
                        }
                        g6.b(n4, this.var_aeii_a_g.n + this.var_aeii_a_g.p);
                        this.var_java_util_Vector_c.addElement(g6);
                        this.var_long_g = this.var_long_a;
                        ++this.var_int_I;
                    } else if (this.var_int_I == 1) {
                        if (this.var_long_a - this.var_long_g >= 800L) {
                            this.d(this.var_aeii_a_f.var_short_b, this.var_aeii_a_f.var_short_c);
                            if (this.var_aeii_a_g.boolean_a((int)this.var_aeii_a_f.var_short_b, this.var_aeii_a_f.var_short_c)) {
                                aeii.c.d(200);
                                n2 = this.var_aeii_a_g.c(this.var_aeii_a_f);
                                this.var_aeii_a_f.a(400);
                                aeii.c.b(14, 1);
                                this.a(this.var_a_a_a_g_g, this.var_aeii_a_f.m, this.var_aeii_a_f.n, 0, 0, 2, 50);
                                a.a.a.g g7 = a.a.a.g.a("-" + n2, 0, -4, (byte)1);
                                n4 = this.var_aeii_a_f.m + this.var_aeii_a_f.o / 2;
                                if (n4 + g7.o / 2 > this.var_int_t) {
                                    n4 = this.var_int_t - g7.o / 2;
                                } else if (n4 - g7.o / 2 < 0) {
                                    n4 = g7.o / 2;
                                }
                                g7.b(n4, this.var_aeii_a_f.n + this.var_aeii_a_f.p);
                                this.var_java_util_Vector_c.addElement(g7);
                                this.var_long_g = this.var_long_a;
                                ++this.var_int_I;
                            } else {
                                this.h();
                            }
                        }
                    } else if (this.var_long_a - this.var_long_g >= 800L) {
                        this.h();
                    }
                } else if (this.var_aeii_a_i != null) {
                    if (this.T == 0) {
                        if (this.var_boolean_w) {
                            this.var_a_a_a_g_v = this.a(this.var_a_a_a_g_f, this.var_aeii_a_i.m, -this.var_int_w, 0, 12, -1, 0);
                            e e3 = this.a(null, aeii.f.java_lang_String_a(280), this.var_int_b, 2000);
                            e3.a(this.var_int_c, 2, 17);
                            aeii.f.var_aeii_c_a.a(e3);
                            this.T = 1;
                        }
                    } else if (this.T == 1) {
                        for (n4 = 0; n4 < 3; ++n4) {
                            this.a(this.var_a_a_a_g_o, this.var_a_a_a_g_v.m + aeii.c.int_a(this.var_a_a_a_g_v.o - this.var_a_a_a_g_o.o), this.var_a_a_a_g_v.n, 0, aeii.c.int_a(-3, 0), 1, 50 * aeii.c.int_a(4));
                        }
                        if (this.var_a_a_a_g_v.n >= this.var_aeii_a_i.n) {
                            this.var_a_a_a_g_v.var_boolean_b = false;
                            aeii.c.d(200);
                            this.void_a(500);
                            if (this.var_boolean_r) {
                                n2 = 25 + aeii.c.int_a(25);
                                if (n2 > this.var_aeii_a_i.var_int_h) {
                                    n2 = this.var_aeii_a_i.var_int_h;
                                }
                                this.var_aeii_a_i.var_int_h -= n2;
                                a.a.a.g g8 = a.a.a.g.a("-" + n2, 0, -4, (byte)1);
                                g8.b(this.var_aeii_a_i.m + this.var_aeii_a_i.o / 2, this.var_aeii_a_i.n + this.var_aeii_a_i.p);
                                this.var_java_util_Vector_c.addElement(g8);
                            }
                            this.d(this.var_aeii_a_i);
                            this.T = 2;
                        }
                    } else if (++this.T >= 20) {
                        if (this.var_aeii_a_i.var_int_h <= 0) {
                            this.var_aeii_a_c = this.var_aeii_a_i;
                            this.a(this.var_a_a_a_g_f, this.var_aeii_a_c.m, this.var_aeii_a_c.n, 0, 0, 1, 50);
                            aeii.c.b(12, 1);
                            this.var_long_e = this.var_long_a;
                        }
                        this.var_aeii_a_i = null;
                    }
                } else if (this.var_aeii_a_d != null) {
                    if (--this.var_int_E <= 0) {
                        aeii.c.d(100);
                        this.d(this.var_aeii_a_d);
                        this.void_a((byte)27, (int)this.var_aeii_a_d.var_short_b, (int)this.var_aeii_a_d.var_short_c);
                        this.var_aeii_a_d = null;
                    }
                } else if (this.var_aeii_a_c != null) {
                    if (this.var_long_a - this.var_long_e >= 300L && this.boolean_b(this.var_aeii_a_c.var_short_b, this.var_aeii_a_c.var_short_c)) {
                        if (this.var_byte_c == 0 && this.var_int_x == 7 && this.var_aeii_a_c == this.var_aeii_a_arr_b[1]) {
                            this.var_boolean_s = true;
                        } else {
                            this.a(this.var_a_a_a_g_e, this.var_aeii_a_c.m, this.var_aeii_a_c.n, 0, -3, 1, 100);
                            this.var_aeii_a_c.var_byte_f = (byte)3;
                            this.var_aeii_a_c.var_byte_h = (byte)3;
                            if (this.var_aeii_a_c.var_byte_d != 10 && this.var_aeii_a_c.var_byte_d != 11) {
                                if (this.var_aeii_a_c.var_byte_d == 9) {
                                    this.var_aeii_a_c.void_a(-10, -10);
                                    this.var_aeii_a_c.var_byte_g = 0;
                                    this.var_aeii_a_c.void_b();
                                }
                            } else {
                                this.var_aeii_a_c.void_a();
                            }
                            if (this.var_aeii_a_c.var_byte_d == 9 && this.var_aeii_a_c.var_int_j < 1000) {
                                this.var_aeii_a_c.var_int_j += 200;
                            }
                        }
                        this.var_aeii_a_c = null;
                    }
                } else if (this.var_java_util_Vector_e.size() > 0) {
                    a a3 = (a)this.var_java_util_Vector_e.elementAt(0);
                    if (this.S == 0) {
                        this.d(a3.var_short_b, a3.var_short_c);
                        this.S = 1;
                    } else if (this.boolean_b(a3.var_short_b, a3.var_short_c)) {
                        this.a(this.var_a_a_a_g_h, a3.m + aeii.c.int_a(a3.o), a3.n + aeii.c.int_a(a3.p), 0, 0, 1, 50);
                        if (this.S == 1) {
                            aeii.c.b(13, 1);
                        }
                        if (this.S <= 5) {
                            n6 = 200;
                            if (this.S == 5) {
                                n6 = 1000;
                            }
                            n4 = a3.m + (a3.o - this.var_a_a_a_g_u.o) / 2;
                            int n8 = a3.n - (this.S << 2);
                            if (n4 < 0) {
                                n4 = 0;
                            } else if (n4 + this.var_a_a_a_g_u.o > this.var_int_t) {
                                n4 = this.var_int_t - this.var_a_a_a_g_u.o;
                            }
                            if (n8 < 0) {
                                n8 = 0;
                            }
                            this.a(this.var_a_a_a_g_u, n4, n8, 0, 0, 1, n6);
                        }
                        ++this.S;
                        if (this.S >= 20) {
                            this.var_java_util_Vector_e.removeElement(a3);
                            this.S = 0;
                            if (a3.var_byte_d != 9 && a3.var_short_a <= 6 && a3.var_short_a % 2 == 0) {
                                aeii.f.var_aeii_c_a.a(this.a(null, aeii.f.java_lang_String_a(80) + "\n" + a3.var_java_lang_String_a, this.var_int_a, 2000));
                            }
                        }
                    }
                } else if (this.var_aeii_a_e != null) {
                    if (this.var_long_a - this.var_long_f >= 400L) {
                        this.var_aeii_a_e.void_a();
                        aeii.a.a((byte)10, this.var_byte_g, (int)this.var_aeii_a_e.var_short_b, this.var_aeii_a_e.var_short_c).void_d();
                        this.var_aeii_a_e = null;
                    }
                } else if (!this.var_boolean_A) {
                    if (this.var_byte_d == 2) {
                        if (this.var_aeii_a_b.var_byte_f != 1 && this.var_boolean_w) {
                            this.c(this.var_aeii_a_b);
                        }
                    } else if (this.var_byte_n == 0 && this.var_byte_arr_m[this.var_byte_b] == 0) {
                        this.p();
                    } else if (this.boolean_a()) {
                        if (this.var_boolean_f && aeii.f.var_aeii_c_a.boolean_b(var_int_d)) {
                            aeii.f.var_aeii_c_a.void_a(16);
                            aeii.f.var_aeii_c_a.void_b(var_int_d);
                        }
                        if (this.var_byte_d != 6 && this.var_byte_d != 7) {
                            if (this.var_long_a - this.var_long_c >= 150L && this.var_a_a_a_g_q.m % 24 == 0 && this.var_a_a_a_g_q.n % 24 == 0) {
                                if (!aeii.f.var_aeii_c_a.boolean_a(4) && !aeii.f.var_aeii_c_a.boolean_c(4)) {
                                    if (aeii.f.var_aeii_c_a.boolean_a(8) || aeii.f.var_aeii_c_a.boolean_c(8)) {
                                        if (this.var_int_h < this.var_int_f - 1) {
                                            ++this.var_int_h;
                                        }
                                        this.var_boolean_i = true;
                                        this.var_long_c = this.var_long_a;
                                    }
                                } else {
                                    if (this.var_int_h > 0) {
                                        --this.var_int_h;
                                    }
                                    this.var_boolean_i = true;
                                    this.var_long_c = this.var_long_a;
                                }
                                if (!aeii.f.var_aeii_c_a.boolean_a(1) && !aeii.f.var_aeii_c_a.boolean_c(1)) {
                                    if (aeii.f.var_aeii_c_a.boolean_a(2) || aeii.f.var_aeii_c_a.boolean_c(2)) {
                                        if (this.var_int_i < this.var_int_g - 1) {
                                            ++this.var_int_i;
                                        }
                                        this.var_boolean_i = true;
                                        this.var_long_c = this.var_long_a;
                                    }
                                } else {
                                    if (this.var_int_i > 0) {
                                        --this.var_int_i;
                                    }
                                    this.var_boolean_i = true;
                                    this.var_long_c = this.var_long_a;
                                }
                                if (this.var_boolean_i) {
                                    if (this.var_byte_d == 1) {
                                        if (this.var_byte_arr_arr_b[this.var_int_h][this.var_int_i] > 0) {
                                            this.var_java_util_Vector_b = this.var_aeii_a_b.a((int)this.var_aeii_a_b.var_short_b, (int)this.var_aeii_a_b.var_short_c, this.var_int_h, this.var_int_i);
                                        }
                                    } else {
                                        this.var_aeii_a_h = this.aeii_a_a(this.var_int_h, this.var_int_i, (byte)0);
                                    }
                                    this.var_boolean_v = true;
                                }
                                this.var_boolean_i = false;
                            }
                            if ((this.var_byte_d == 1 || this.var_byte_d == 0) && this.var_byte_n == 0 && aeii.f.var_aeii_c_a.boolean_a(256)) {
                                a a4 = this.aeii_a_a(this.var_int_h, this.var_int_i, (byte)0);
                                if (a4 != null) {
                                    e e4 = new e(15, 15);
                                    new e(15, 15).i = this.var_int_b;
                                    e e5 = new e(5, 2);
                                    e e6 = new e(10, 1);
                                    new e(10, 1).var_boolean_a = true;
                                    String string = aeii.f.java_lang_String_a(184 + a4.var_byte_d);
                                    if (a4.var_byte_g != 0) {
                                        StringBuffer stringBuffer = new StringBuffer(aeii.f.java_lang_String_a(98));
                                        if ((a4.var_byte_g & 2) != 0) {
                                            stringBuffer.append('\n');
                                            stringBuffer.append(aeii.f.java_lang_String_a(100));
                                        }
                                        if ((a4.var_byte_g & 1) != 0) {
                                            stringBuffer.append('\n');
                                            stringBuffer.append(aeii.f.java_lang_String_a(99));
                                        }
                                        stringBuffer.append("\n-----------\n");
                                        string = stringBuffer.toString() + string;
                                    }
                                    e6.a(null, string, this.var_int_a, this.var_int_b - e5.var_int_c);
                                    e4.a(e5, 0, 0, 0);
                                    e4.a(e6, 0, e5.var_int_c, 0);
                                    e4.var_boolean_b = true;
                                    e4.a(this);
                                    aeii.f.var_aeii_c_a.a(e4);
                                }
                                aeii.f.var_aeii_c_a.void_b(256);
                            }
                            if (this.var_byte_d == 1) {
                                if (aeii.f.var_aeii_c_a.boolean_a(16) && this.var_aeii_a_b != null) {
                                    a a5 = this.aeii_a_a(this.var_int_h, this.var_int_i, (byte)0);
                                    if (this.var_byte_arr_arr_b[this.var_int_h][this.var_int_i] > 0 && (a5 == null || a5 == this.var_aeii_a_b)) {
                                        this.var_int_A = this.var_aeii_a_b.var_short_b;
                                        this.var_int_B = this.var_aeii_a_b.var_short_c;
                                        this.var_aeii_a_b.a(this.var_int_h, this.var_int_i, true);
                                        this.var_aeii_a_j = this.var_aeii_a_b;
                                        this.var_boolean_d = false;
                                        this.var_boolean_b = false;
                                        this.var_java_util_Vector_b = null;
                                        this.var_aeii_e_a = null;
                                        this.var_boolean_e = false;
                                        this.var_boolean_f = false;
                                        this.var_byte_d = (byte)2;
                                        aeii.c.b(10, 1);
                                    }
                                    aeii.f.var_aeii_c_a.void_b(16);
                                }
                            } else if (this.var_byte_d == 0) {
                                if (this.var_byte_n == 1) {
                                    if (aeii.f.var_aeii_c_a.boolean_a(128)) {
                                        this.var_byte_l = (byte)(this.var_byte_l + 1);
                                        if (this.var_byte_l >= this.var_aeii_d_arr_d.length) {
                                            this.var_byte_l = 0;
                                        }
                                        this.var_boolean_u = true;
                                        this.var_boolean_v = true;
                                    } else if (aeii.f.var_aeii_c_a.boolean_a(64)) {
                                        this.var_byte_l = (byte)(this.var_byte_l - 1);
                                        if (this.var_byte_l < 0) {
                                            this.var_byte_l = (byte)(this.var_aeii_d_arr_d.length - 1);
                                        }
                                        this.var_boolean_u = true;
                                        this.var_boolean_v = true;
                                    } else if (aeii.f.var_aeii_c_a.boolean_a(16)) {
                                        if (this.var_boolean_b) {
                                            this.var_byte_arr_arr_b[this.var_int_h][this.var_int_i] = this.var_byte_arr_arr_b[this.var_int_h][this.var_int_i] == 0 ? (byte)1 : 0;
                                            this.var_boolean_b = false;
                                            for (n6 = 0; n6 < this.var_int_f; ++n6) {
                                                for (int i = 0; i < this.var_int_g; ++i) {
                                                    if (this.var_byte_arr_arr_b[n6][i] <= 0) continue;
                                                    this.var_boolean_b = true;
                                                    break;
                                                }
                                                if (!this.var_boolean_b) {
                                                    continue;
                                                }
                                                break;
                                            }
                                        } else {
                                            this.var_byte_arr_arr_a[this.var_int_h][this.var_int_i] = this.var_byte_l;
                                            this.j();
                                        }
                                    } else if (aeii.f.var_aeii_c_a.boolean_a(512)) {
                                        if (this.var_byte_l == this.var_byte_arr_arr_a[this.var_int_h][this.var_int_i]) {
                                            this.var_byte_l = (byte)(this.var_byte_l + 1);
                                            if (this.var_byte_l >= this.var_aeii_d_arr_d.length) {
                                                this.var_byte_l = 0;
                                            }
                                            this.var_byte_arr_arr_a[this.var_int_h][this.var_int_i] = this.var_byte_l;
                                            this.j();
                                        } else {
                                            this.var_byte_l = this.var_byte_arr_arr_a[this.var_int_h][this.var_int_i];
                                        }
                                        this.var_boolean_u = true;
                                        this.var_boolean_v = true;
                                    } else if (aeii.f.var_aeii_c_a.boolean_a(256)) {
                                        if (this.var_byte_l == this.var_byte_arr_arr_a[this.var_int_h][this.var_int_i]) {
                                            this.var_byte_l = (byte)(this.var_byte_l - 1);
                                            if (this.var_byte_l < 0) {
                                                this.var_byte_l = (byte)(this.var_aeii_d_arr_d.length - 1);
                                            }
                                            this.var_byte_arr_arr_a[this.var_int_h][this.var_int_i] = this.var_byte_l;
                                            this.j();
                                        } else {
                                            this.var_byte_l = this.var_byte_arr_arr_a[this.var_int_h][this.var_int_i];
                                        }
                                        this.var_boolean_u = true;
                                        this.var_boolean_v = true;
                                    } else if (aeii.f.var_aeii_c_a.boolean_a(32)) {
                                        if (this.var_boolean_b) {
                                            this.a(this.var_int_arr_o);
                                        } else {
                                            this.var_byte_l = this.var_byte_arr_arr_a[this.var_int_h][this.var_int_i];
                                            this.A();
                                            for (n6 = 0; n6 < this.var_int_arr_o.length; ++n6) {
                                                this.var_int_arr_o[n6] = 0;
                                                this.var_boolean_arr_b[n6] = false;
                                            }
                                            this.var_int_arr_o[this.var_byte_l] = 100;
                                            this.var_boolean_arr_b[this.var_byte_l] = true;
                                            this.var_javax_microedition_lcdui_ChoiceGroup_a.setSelectedFlags(this.var_boolean_arr_b);
                                            this.var_boolean_u = true;
                                            this.var_boolean_v = true;
                                        }
                                    } else if (aeii.f.var_aeii_c_a.boolean_a(16384)) {
                                        if (this.var_boolean_b) {
                                            if (this.var_byte_arr_arr_b[this.var_int_h][this.var_int_i] == 0) {
                                                this.var_byte_arr_arr_b[this.var_int_h][this.var_int_i] = 1;
                                            } else {
                                                int n9;
                                                n6 = this.var_int_f;
                                                int n10 = this.var_int_g;
                                                int n11 = -1;
                                                int n12 = -1;
                                                for (n9 = 0; n9 < this.var_int_f; ++n9) {
                                                    for (int i = 0; i < this.var_int_g; ++i) {
                                                        if (this.var_byte_arr_arr_b[n9][i] <= 0) continue;
                                                        if (n9 < n6) {
                                                            n6 = n9;
                                                        }
                                                        if (n9 > n11) {
                                                            n11 = n9;
                                                        }
                                                        if (i < n10) {
                                                            n10 = i;
                                                        }
                                                        if (i <= n12) continue;
                                                        n12 = i;
                                                    }
                                                }
                                                boolean bl = true;
                                                for (n9 = n6; n9 <= n11; ++n9) {
                                                    for (int i = n10; i <= n12; ++i) {
                                                        if (this.var_byte_arr_arr_b[n9][i] == 0) {
                                                            bl = false;
                                                        }
                                                        this.var_byte_arr_arr_b[n9][i] = 1;
                                                    }
                                                }
                                                if (bl) {
                                                    if (n6 == 0 && n10 == 0 && n11 == this.var_int_f - 1 && n12 == this.var_int_g - 1) {
                                                        this.a(this.var_byte_arr_arr_b, 0);
                                                        this.var_boolean_b = false;
                                                    } else {
                                                        this.a(this.var_byte_arr_arr_b, 1);
                                                    }
                                                }
                                            }
                                        } else {
                                            this.var_byte_arr_arr_b[this.var_int_h][this.var_int_i] = 1;
                                            this.var_boolean_b = true;
                                        }
                                    } else if (aeii.f.var_aeii_c_a.boolean_a(8192)) {
                                        if (this.var_byte_f > 0) {
                                            this.a(this.var_byte_arr_arr_b, 0);
                                            this.var_boolean_b = false;
                                            this.var_byte_n = (byte)2;
                                            this.var_byte_b = 0;
                                            this.var_aeii_a_s = null;
                                            this.var_boolean_u = true;
                                            this.var_boolean_v = true;
                                        }
                                    } else if (aeii.f.var_aeii_c_a.boolean_a(var_int_d)) {
                                        this.var_aeii_e_a = new e(11, 0);
                                        this.var_aeii_e_a.a(var_java_lang_String_arr_d, 2, 2, -1, this.var_int_b, 20, 0);
                                        this.var_aeii_e_a.a(this);
                                        aeii.f.var_aeii_c_a.a(this.var_aeii_e_a);
                                    }
                                } else if (this.var_byte_n == 2) {
                                    if (aeii.f.var_aeii_c_a.boolean_a(128)) {
                                        this.var_byte_m = (byte)(this.var_byte_m + 1);
                                        if (this.var_byte_m >= 12) {
                                            this.var_byte_m = 0;
                                        }
                                        this.var_aeii_a_s = null;
                                        this.var_boolean_u = true;
                                        this.var_boolean_v = true;
                                    } else if (aeii.f.var_aeii_c_a.boolean_a(64)) {
                                        this.var_byte_m = (byte)(this.var_byte_m - 1);
                                        if (this.var_byte_m < 0) {
                                            this.var_byte_m = (byte)11;
                                        }
                                        this.var_aeii_a_s = null;
                                        this.var_boolean_u = true;
                                        this.var_boolean_v = true;
                                    } else if (aeii.f.var_aeii_c_a.boolean_a(16)) {
                                        a a6 = this.aeii_a_a(this.var_int_h, this.var_int_i, (byte)0);
                                        if (a6 == null) {
                                            aeii.a.b(this.var_byte_m, this.var_byte_b, this.var_int_h, this.var_int_i, true);
                                        } else {
                                            a6.void_a();
                                        }
                                    } else if (aeii.f.var_aeii_c_a.boolean_a(512)) {
                                        a a7 = this.aeii_a_a(this.var_int_h, this.var_int_i, (byte)0);
                                        if (a7 != null) {
                                            if (this.var_byte_m != a7.var_byte_d) {
                                                this.var_byte_m = a7.var_byte_d;
                                            } else {
                                                this.var_byte_m = (byte)(this.var_byte_m + 1);
                                                if (this.var_byte_m >= 12) {
                                                    this.var_byte_m = 0;
                                                }
                                                a7.void_a();
                                                aeii.a.b(this.var_byte_m, this.var_byte_b, this.var_int_h, this.var_int_i, true);
                                            }
                                        } else {
                                            aeii.a.b(this.var_byte_m, this.var_byte_b, this.var_int_h, this.var_int_i, true);
                                        }
                                        this.var_aeii_a_s = null;
                                        this.var_boolean_u = true;
                                        this.var_boolean_v = true;
                                    } else if (aeii.f.var_aeii_c_a.boolean_a(256)) {
                                        a a8 = this.aeii_a_a(this.var_int_h, this.var_int_i, (byte)0);
                                        if (a8 != null) {
                                            if (this.var_byte_m != a8.var_byte_d) {
                                                this.var_byte_m = a8.var_byte_d;
                                            } else {
                                                this.var_byte_m = (byte)(this.var_byte_m - 1);
                                                if (this.var_byte_m < 0) {
                                                    this.var_byte_m = (byte)11;
                                                }
                                                a8.void_a();
                                                aeii.a.b(this.var_byte_m, this.var_byte_b, this.var_int_h, this.var_int_i, true);
                                            }
                                        } else {
                                            aeii.a.b(this.var_byte_m, this.var_byte_b, this.var_int_h, this.var_int_i, true);
                                        }
                                        this.var_aeii_a_s = null;
                                        this.var_boolean_u = true;
                                        this.var_boolean_v = true;
                                    } else if (aeii.f.var_aeii_c_a.boolean_a(8192)) {
                                        this.var_byte_b = (byte)(this.var_byte_b + 1);
                                        if (this.var_byte_b >= this.var_byte_f) {
                                            this.var_byte_b = 0;
                                            this.var_byte_n = 1;
                                        }
                                        this.var_aeii_a_s = null;
                                        this.var_boolean_u = true;
                                        this.var_boolean_v = true;
                                    } else if (aeii.f.var_aeii_c_a.boolean_a(var_int_d)) {
                                        this.var_aeii_e_a = new e(11, 0);
                                        this.var_aeii_e_a.a(var_java_lang_String_arr_d, 2, 2, -1, this.var_int_b, 20, 0);
                                        this.var_aeii_e_a.a(this);
                                        aeii.f.var_aeii_c_a.a(this.var_aeii_e_a);
                                    }
                                } else {
                                    if (aeii.f.var_aeii_c_a.boolean_a(512)) {
                                        int n13 = 0;
                                        a a9 = this.var_aeii_a_arr_b[this.var_byte_b];
                                        if (this.var_aeii_a_h != null && this.var_aeii_a_h.var_byte_d == 9) {
                                            a9 = this.var_aeii_a_arr_arr_a[this.var_byte_b][(this.var_aeii_a_h.var_int_i + 1) % this.var_int_arr_a[this.var_byte_b]];
                                        }
                                        while (true) {
                                            if (++n13 >= this.var_int_arr_a[this.var_byte_b] || a9.var_byte_f != 3) {
                                                if (a9 != null && a9.var_byte_f != 3) {
                                                    this.d(a9.var_short_b, a9.var_short_c);
                                                    this.void_a(a9.m + 12, a9.n + 12);
                                                }
                                                break block289;
                                            }
                                            a9 = this.var_aeii_a_arr_arr_a[this.var_byte_b][(a9.var_int_i + 1) % this.var_int_arr_a[this.var_byte_b]];
                                        }
                                    }
                                    if (aeii.f.var_aeii_c_a.boolean_a(32)) {
                                        if (this.var_boolean_c) {
                                            this.a(this.var_byte_arr_arr_b, 0);
                                            this.var_boolean_b = false;
                                            this.var_boolean_c = false;
                                        } else {
                                            this.var_aeii_a_b = this.aeii_a_a(this.var_int_h, this.var_int_i, (byte)0);
                                            if (this.var_aeii_a_b != null) {
                                                this.a(this.var_byte_arr_arr_b, 0);
                                                this.var_aeii_a_b.a(this.var_byte_arr_arr_b);
                                                this.var_boolean_c = true;
                                                this.var_boolean_b = true;
                                                this.aa = 12;
                                            }
                                        }
                                        aeii.f.var_aeii_c_a.void_b(32);
                                    } else if (aeii.f.var_aeii_c_a.boolean_a(16) || aeii.f.var_aeii_c_a.boolean_a(var_int_d)) {
                                        this.var_aeii_a_b = this.aeii_a_a(this.var_int_h, this.var_int_i, (byte)0);
                                        if (this.var_aeii_a_b != null && this.var_aeii_a_b.var_byte_f == 0 && this.var_aeii_a_b.var_byte_e == this.var_byte_b) {
                                            byte[] byArray = this.byte_arr_a(this.var_aeii_a_b, (byte)1);
                                            if (byArray.length > 1) {
                                                this.b(byArray);
                                                aeii.c.b(11, 1);
                                            } else {
                                                this.var_boolean_l = false;
                                                this.void_b(this.var_aeii_a_b);
                                            }
                                        } else if (this.byte_a(this.var_int_h, this.var_int_i) == 9 && this.boolean_b(this.var_int_h, this.var_int_i, this.var_byte_b)) {
                                            byte[] byArray = new byte[]{0};
                                            this.b(byArray);
                                            aeii.c.b(11, 1);
                                        } else {
                                            this.var_aeii_a_b = null;
                                            this.var_aeii_e_a = new e(11, 0);
                                            this.var_aeii_e_a.a(this.var_java_lang_String_arr_f, 2, 2, -1, this.var_int_b, 20, 0);
                                            this.var_aeii_e_a.a(this);
                                            aeii.f.var_aeii_c_a.a(this.var_aeii_e_a);
                                            aeii.c.b(11, 1);
                                        }
                                        aeii.f.var_aeii_c_a.b();
                                    }
                                }
                            }
                        } else {
                            if (!aeii.f.var_aeii_c_a.boolean_a(4) && !aeii.f.var_aeii_c_a.boolean_a(1)) {
                                if (aeii.f.var_aeii_c_a.boolean_a(8) || aeii.f.var_aeii_c_a.boolean_a(2)) {
                                    ++this.var_int_z;
                                    if (this.var_int_z >= this.var_aeii_a_arr_a.length) {
                                        this.var_int_z = 0;
                                    }
                                    aeii.f.var_aeii_c_a.void_b(8);
                                    aeii.f.var_aeii_c_a.void_b(2);
                                    this.var_boolean_i = true;
                                }
                            } else {
                                --this.var_int_z;
                                if (this.var_int_z < 0) {
                                    this.var_int_z = this.var_aeii_a_arr_a.length - 1;
                                }
                                aeii.f.var_aeii_c_a.void_b(4);
                                aeii.f.var_aeii_c_a.void_b(1);
                                this.var_boolean_i = true;
                            }
                            this.d(this.var_aeii_a_arr_a[this.var_int_z].var_short_b, this.var_aeii_a_arr_a[this.var_int_z].var_short_c);
                            if (this.var_boolean_i) {
                                this.var_aeii_a_h = this.aeii_a_a(this.var_int_h, this.var_int_i, (byte)0);
                                this.var_boolean_v = true;
                            }
                            if (aeii.f.var_aeii_c_a.boolean_a(16)) {
                                if (this.var_byte_d == 6) {
                                    this.a(this.var_aeii_a_b, this.var_aeii_a_arr_a[this.var_int_z]);
                                } else if (this.var_byte_d == 7) {
                                    this.void_a(this.var_aeii_a_arr_a[this.var_int_z], this.var_byte_b);
                                    this.var_aeii_a_b.void_d();
                                    this.var_byte_d = 0;
                                }
                                this.var_aeii_e_a = null;
                                this.var_boolean_b = false;
                                this.var_boolean_c = false;
                                this.var_boolean_e = false;
                                this.var_boolean_f = false;
                            }
                            this.var_boolean_i = false;
                        }
                    }
                }
            } else if (this.aC != 1 && (this.var_byte_d != 14 || this.aC == 2)) {
                if (this.aC == 0) {
                    this.var_boolean_g = true;
                    this.var_int_G = 0;
                } else if (this.aC == 2) {
                    if (this.var_byte_d == 14) {
                        this.var_int_b = ((f)this).k;
                        this.var_int_p = ((f)this).m;
                        this.a(this.var_byte_arr_f, ((f)this).m, ((f)this).k, null);
                        return;
                    }
                    if (this.var_int_x <= 7) {
                        this.void_c(this.var_int_x);
                        this.q();
                        this.var_byte_d = 0;
                    }
                }
            } else {
                ++this.var_int_x;
                if (this.var_int_x > this.var_int_q) {
                    if (var_int_arr_c[this.var_int_q] >= 0) {
                        Object object = var_java_lang_String_arr_e[var_int_arr_c[this.var_int_q]];
                        object = this.a(null, aeii.f.a(82, (String)object), ((f)this).k, 3000);
                        aeii.f.var_aeii_c_a.a((f)object);
                    }
                    this.var_int_q = this.var_int_x;
                    try {
                        byte[] byArray = new byte[]{(byte)this.var_int_q};
                        aeii.c.a("settings", 1, byArray);
                    }
                    catch (Exception exception) {}
                }
                this.aC = 2;
            }
        }
        int n14 = this.var_java_util_Vector_a.size();
        for (int i = 0; i < n14; ++i) {
            ((a)this.var_java_util_Vector_a.elementAt(i)).void_c();
        }
        if (this.var_long_a - this.var_long_i >= 300L) {
            this.var_int_J = (this.var_int_J + 1) % this.var_aeii_d_arr_e.length;
            this.var_aeii_d_arr_d[this.var_int_K] = this.var_aeii_d_arr_e[this.var_int_J];
            this.var_long_i = this.var_long_a;
        }
        g g9 = this;
        if (g9.var_aeii_a_j == null) {
            g9.c(g9.var_a_a_a_g_q.m + 12, g9.var_a_a_a_g_q.n + 12);
        } else {
            g9.c(g9.var_aeii_a_j.m + 12, g9.var_aeii_a_j.n + 12);
        }
        if (this.var_boolean_e && aeii.f.var_aeii_c_a.boolean_b(var_int_e)) {
            if (this.var_byte_d == 1) {
                this.var_byte_d = 0;
                this.a(this.var_byte_arr_arr_b, 0);
                this.var_java_util_Vector_b = null;
                this.var_a_a_a_g_q.a(var_byte_arr_arr_c[0]);
                this.d(this.var_aeii_a_b.var_short_b, this.var_aeii_a_b.var_short_c);
                this.var_aeii_a_b = null;
            } else if (this.var_byte_d == 6 || this.var_byte_d == 7) {
                this.var_byte_d = this.var_byte_e;
                this.a(this.var_byte_arr_arr_b, 0);
                this.var_a_a_a_g_q.a(var_byte_arr_arr_c[0]);
                this.d(this.var_aeii_a_b.var_short_b, this.var_aeii_a_b.var_short_c);
                aeii.f.var_aeii_c_a.a(this.var_aeii_e_a);
            }
            this.var_boolean_b = false;
            this.var_boolean_c = false;
            aeii.f.var_aeii_c_a.void_b(var_int_e);
            this.var_boolean_e = false;
            this.var_boolean_f = false;
        }
        for (n = this.var_java_util_Vector_c.size() - 1; n >= 0; --n) {
            a.a.a.g g10 = (a.a.a.g)this.var_java_util_Vector_c.elementAt(n);
            g10.void_c();
            if (g10.var_boolean_b) continue;
            this.var_java_util_Vector_c.removeElement(g10);
        }
        int n15 = this.var_java_util_Vector_d.size();
        for (n = 0; n < n15; ++n) {
            this.var_java_util_Vector_c.addElement(this.var_java_util_Vector_d.elementAt(n));
        }
        this.var_java_util_Vector_d.removeAllElements();
    }

    private void d(a a2) {
        int n = a2.n + 24;
        this.a(this.var_a_a_a_g_e, a2.m, n - this.var_a_a_a_g_e.p, 0, -2, 1, 100);
        for (int i = 0; i < 5; ++i) {
            this.a(this.var_a_a_a_g_o, a2.m, n - this.var_a_a_a_g_o.p, i + -2, aeii.c.int_a(-4, -1), 1, 50 + 50 * aeii.c.int_a(4));
        }
        this.a(this.var_a_a_a_g_f, a2.m, a2.n, 0, 0, 1, 100);
    }

    private void void_a(a a2, byte by) {
        this.var_aeii_a_e = a2;
        this.var_byte_g = by;
        this.a(this.var_a_a_a_g_f, a2.m - 8, a2.n - 8, 1, 1, 3, 50);
        this.a(this.var_a_a_a_g_f, a2.m + 8, a2.n - 8, -1, 1, 3, 50);
        this.a(this.var_a_a_a_g_f, a2.m - 8, a2.n + 8, 1, -1, 3, 50);
        this.a(this.var_a_a_a_g_f, a2.m + 8, a2.n + 8, -1, -1, 3, 50);
        this.var_long_f = this.var_long_a;
    }

    private boolean boolean_a(int n, int n2) {
        return this.var_int_v == this.int_b(n) && this.var_int_w == this.int_c(n2);
    }

    private boolean boolean_b(int n, int n2) {
        return this.boolean_a(n * 24 + 12, n2 * 24 + 12);
    }

    private int int_b(int n) {
        if (this.var_int_t > this.var_int_a) {
            if ((n = this.var_int_c - n) > 0) {
                n = 0;
            } else if (n < this.var_int_a - this.var_int_t) {
                n = this.var_int_a - this.var_int_t;
            }
        } else {
            n = (this.var_int_a - this.var_int_t) / 2;
        }
        return n;
    }

    private int int_c(int n) {
        if (this.var_int_u > this.var_int_b) {
            if ((n = this.var_int_p - n) > 0) {
                n = 0;
            } else if (n < this.var_int_b - this.var_int_u) {
                n = this.var_int_b - this.var_int_u;
            }
        } else {
            n = (this.var_int_b - this.var_int_u) / 2;
        }
        return n;
    }

    private void void_a(int n, int n2) {
        this.var_int_v = this.int_b(n);
        this.var_int_w = this.int_c(n2);
    }

    private void void_b(int n, int n2) {
        this.void_a(n * 24 + 12, n2 * 24 + 12);
    }

    private void c(int n, int n2) {
        int n3;
        this.var_boolean_w = true;
        n = this.int_b(n);
        n2 = this.int_c(n2);
        n2 -= this.var_int_w;
        if ((n -= this.var_int_v) != 0) {
            n3 = n / 2;
            if (n < 0) {
                if (n3 > -this.Z) {
                    n3 = -this.Z;
                } else if (n3 < -this.Y) {
                    n3 = -this.Y;
                }
            } else if (n3 < this.Z) {
                n3 = this.Z;
            } else if (n3 > this.Y) {
                n3 = this.Y;
            }
            this.var_int_v += n3;
            this.var_boolean_w = false;
        }
        if (n2 != 0) {
            n3 = n2 / 2;
            if (n2 < 0) {
                if (n3 > -this.Z) {
                    n3 = -this.Z;
                } else if (n3 < -this.Y) {
                    n3 = -this.Y;
                }
            } else if (n3 < this.Z) {
                n3 = this.Z;
            } else if (n3 > this.Y) {
                n3 = this.Y;
            }
            this.var_int_w += n3;
            this.var_boolean_w = false;
        }
    }

    private void d(int n, int n2) {
        this.var_int_h = n;
        this.var_int_i = n2;
        this.var_a_a_a_g_q.b(n * 24, n2 * 24);
        this.var_aeii_a_h = this.aeii_a_a(this.var_int_h, this.var_int_i, (byte)0);
        this.var_boolean_v = true;
    }

    private void b(Graphics graphics) {
        int n = -this.var_int_v / 24;
        int n2 = -this.var_int_w / 24;
        if (n < 0) {
            n = 0;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        int n3 = (this.var_int_a - this.var_int_v - 1) / 24;
        int n4 = (this.var_int_b - this.var_int_w - 1) / 24;
        if (n3 >= this.var_int_f) {
            n3 = this.var_int_f - 1;
        }
        if (n4 >= this.var_int_g) {
            n4 = this.var_int_g - 1;
        }
        int n5 = this.var_int_v < 0 ? this.var_int_v % 24 : this.var_int_v;
        int n6 = this.var_int_w < 0 ? this.var_int_w % 24 : this.var_int_w;
        int n7 = 0;
        if (this.var_boolean_c) {
            n7 = 1;
        }
        while (n2 <= n4) {
            int n8 = n5;
            for (int i = n; i <= n3; ++i) {
                int n9 = this.var_byte_arr_arr_a[i][n2];
                if (!this.var_boolean_b || this.var_byte_arr_arr_b[i][n2] == 0 || this.aa > 0) {
                    this.var_aeii_d_arr_d[n9].a(graphics, n8, n6);
                }
                if (this.var_boolean_b && this.var_byte_arr_arr_b[i][n2] > 0) {
                    if (this.aa != 0) {
                        graphics.clipRect(n8 + this.aa, n6 + this.aa, 24 - (this.aa << 1), 24 - (this.aa << 1));
                    }
                    graphics.drawImage(this.var_javax_microedition_lcdui_Image_arr_arr_a[n7][n9], n8, n6, 0);
                    if (this.aa != 0) {
                        graphics.setClip(0, 0, this.var_int_a, this.var_int_b);
                    }
                }
                if ((n9 = n2 + 1) < this.var_int_g && this.var_byte_arr_k[this.var_byte_arr_arr_a[i][n9]] == 9) {
                    this.var_aeii_d_arr_d[28].a(graphics, n8, n6);
                }
                n8 += 24;
            }
            n6 += 24;
            ++n2;
        }
    }

    private void c(Graphics graphics) {
        graphics.setFont(aeii.c.var_javax_microedition_lcdui_Font_b);
        graphics.setColor(0);
        graphics.fillRect(0, 0, ((f)this).j, ((f)this).k);
        graphics.setColor(0xFFFFFF);
        aeii.c.b(graphics, aeii.f.java_lang_String_a(58), ((f)this).j / 2, (((f)this).k - aeii.c.var_javax_microedition_lcdui_Font_b.getHeight()) / 2, 17);
    }

    public static final int a(int n, int n2, int n3, int n4) {
        if (n3 == 0) {
            return n;
        }
        if (n3 == n4) {
            return n2;
        }
        int n5 = n & 0xFF0000;
        int n6 = n & 0xFF00;
        n &= 0xFF;
        n5 = (((n2 & 0xFF0000) - n5) * n3 / n4 & 0xFF0000) + n5;
        n6 = (((n2 & 0xFF00) - n6) * n3 / n4 & 0xFF00) + n6;
        n = ((n2 & 0xFF) - n) * n3 / n4 + n;
        return n5 | n6 | n;
    }

    private void void_a(int n, int n2, int n3) {
        this.af = n2;
        this.ag = n3;
        this.var_byte_j = this.var_byte_h;
        try {
            this.var_aeii_d_g = new d("intro" + n, this.var_int_a, -1);
        }
        catch (Exception exception) {}
        this.ab = aeii.c.var_int_c;
        if (this.var_aeii_d_g != null) {
            this.ah = this.var_aeii_d_g.b;
            this.var_int_G = 0;
            this.ad = (((f)this).k - this.var_aeii_d_g.b) / this.ab;
        } else {
            this.ah = 0;
            this.var_int_G = 16;
            this.ad = ((f)this).k / this.ab;
        }
        this.var_java_lang_String_arr_o = aeii.f.a(aeii.f.java_lang_String_a(n + 215), ((f)this).j, aeii.c.var_javax_microedition_lcdui_Font_a);
        this.ac = ((f)this).k - this.ab;
        this.ae = 0;
        this.var_boolean_x = false;
        aeii.f.var_aeii_c_a.b();
        this.var_byte_h = (byte)3;
    }

    private void void_a(String string) {
        this.var_byte_j = this.var_byte_h;
        this.var_java_lang_String_arr_o = aeii.f.a(string, ((f)this).j, aeii.c.var_javax_microedition_lcdui_Font_a);
        this.var_int_G = 16;
        this.var_boolean_x = false;
        this.af = 3;
        this.ag = 3;
        this.ah = 0;
        this.ab = aeii.c.var_int_c;
        this.ad = ((f)this).k / this.ab;
        this.ac = ((f)this).k - this.ab;
        this.ae = 0;
        aeii.f.var_aeii_c_a.b();
        this.var_byte_h = (byte)3;
    }

    private void d(Graphics graphics) {
        int n;
        graphics.setFont(aeii.c.var_javax_microedition_lcdui_Font_a);
        graphics.setColor(0);
        graphics.fillRect(0, 0, ((f)this).j, ((f)this).k);
        if (this.var_aeii_d_g != null) {
            if (!this.var_boolean_x && (this.af == 2 && this.var_int_G >= 40 || this.af != 2 && this.var_int_G >= 16)) {
                this.var_aeii_d_g.a(graphics, 0, 0);
            } else if (!(this.ag == 2 && this.var_boolean_x || this.af == 2 && !this.var_boolean_x)) {
                if (!(this.ag == 3 && this.var_boolean_x || this.af == 3 && !this.var_boolean_x)) {
                    this.var_aeii_d_g.a(graphics, 0, 0);
                    if (this.var_boolean_x) {
                        if (this.ag == 1 && this.var_int_G <= 16) {
                            aeii.g.a(graphics, 0, 16 - this.var_int_G, 16, 0, 0, 0, ((f)this).j, ((f)this).k);
                        }
                    } else if (this.af == 0) {
                        if (this.var_int_G <= 16) {
                            aeii.g.a(graphics, 0xFFFFFF, this.var_int_G, 16, 1, 0, 0, ((f)this).j, ((f)this).k);
                        }
                    } else if (this.af == 1 && this.var_int_G <= 16) {
                        aeii.g.a(graphics, 0, this.var_int_G, 16, 1, 0, 0, ((f)this).j, this.var_aeii_d_g.b);
                    }
                } else {
                    this.var_aeii_d_g.a(graphics, 0, 0);
                    n = 255 * (16 - this.var_int_G) / 16;
                    aeii.c.a(graphics, n << 24, 0, 0, this.var_aeii_d_g.var_int_a, this.var_aeii_d_g.b);
                }
            } else {
                aeii.g.a(graphics, this.var_int_G, 40, 0, this.var_aeii_d_g, 0, 0, 2);
            }
        }
        graphics.setClip(0, 0, ((f)this).j, ((f)this).k);
        n = this.var_int_G;
        if (this.var_boolean_x && this.ag == 2 && (n -= 24) < 0) {
            n = 0;
        }
        int n2 = this.ac;
        for (int i = this.ae; i < this.ae + this.ad && i < this.var_java_lang_String_arr_o.length && n2 < ((f)this).k - this.ab; n2 += this.ab, ++i) {
            int n3 = this.ab;
            if (n2 < this.ah + this.ab) {
                n3 = n2 - this.ah;
            } else if (n2 + this.ab > ((f)this).k - this.ab) {
                n3 = ((f)this).k - this.ab - n2;
            }
            if (n3 < this.ab) {
                int n4 = this.ab;
                int n5 = n3;
                n3 = 14672074;
                n3 = n5 * 0xDF0000 / n4 & 0xFF0000;
                int n6 = n5 * 57344 / n4 & 0xFF00;
                n5 = n5 * 202 / n4;
                n3 = n3 | n6 | n5;
            } else {
                n3 = 14672074;
            }
            if (this.var_boolean_x) {
                n3 = aeii.g.a(0, n3, n, 16);
            }
            graphics.setColor(n3);
            aeii.c.b(graphics, this.var_java_lang_String_arr_o[i], ((f)this).l, n2 + 3, 17);
        }
        if (!this.var_boolean_x) {
            this.a(graphics, var_int_d, 2, ((f)this).k);
        }
    }

    private void e(Graphics graphics) {
        if (this.var_int_F == 0) {
            graphics.setColor(0xFFFFFF);
            graphics.fillRect(0, 0, ((f)this).j, ((f)this).k);
            aeii.g.a(graphics, this.var_int_G, 40, 0, this.var_aeii_d_b, (((f)this).j - this.var_aeii_d_b.var_int_a) / 2, (((f)this).k - this.var_aeii_d_b.b) / 2, 4);
            return;
        }
        if (this.var_int_F == 1) {
            graphics.setColor(0xFFFFFF);
            graphics.fillRect(0, 0, ((f)this).j, ((f)this).k);
            aeii.g.a(graphics, this.var_int_G, 40, 0, this.var_aeii_d_b, (((f)this).j - this.var_aeii_d_b.var_int_a) / 2, (((f)this).k - this.var_aeii_d_b.b) / 2, 4);
            return;
        }
        if (this.var_int_F == 3) {
            graphics.setColor(0);
            graphics.fillRect(0, 0, ((f)this).j, ((f)this).k);
            aeii.g.a(graphics, this.var_int_G, 40, 0, this.var_aeii_d_d, 0, 0, 1);
            graphics.setClip(0, 0, ((f)this).j, ((f)this).k);
            return;
        }
        if (this.var_int_F == 4) {
            if (this.var_int_G >= 16) {
                if (this.var_aeii_d_c != null) {
                    this.var_aeii_d_d.a(graphics, 0, 0);
                    if (this.P != -1) {
                        int n = (this.var_aeii_d_d.b + this.var_aeii_d_e.b - 1) / this.var_aeii_d_e.b;
                        for (int i = 0; i < n; ++i) {
                            this.var_aeii_d_e.a(graphics, 4 + this.P - i * 38, 6 + this.var_aeii_d_e.b * i);
                        }
                    }
                    this.var_aeii_d_c.a(graphics, 0, 0);
                } else {
                    graphics.setColor(0);
                    graphics.fillRect(0, 0, ((f)this).j, ((f)this).k);
                    this.var_aeii_d_d.a(graphics, 0, 0);
                }
                if (this.var_boolean_k && this.var_aeii_d_c != null) {
                    graphics.setColor(0xFFFFFF);
                    graphics.setFont(aeii.c.var_javax_microedition_lcdui_Font_b);
                    aeii.g.a(graphics, aeii.f.java_lang_String_a(59), ((f)this).l, ((f)this).k - this.var_a_a_a_g_c.p - 1, 33, 0xFFFFFF, 0);
                    return;
                }
            } else {
                if (this.var_aeii_d_c != null) {
                    this.var_aeii_d_c.a(graphics, 0, 0);
                } else {
                    graphics.setColor(0);
                    graphics.fillRect(0, 0, ((f)this).j, ((f)this).k);
                }
                aeii.c.a(graphics, 255 * (16 - this.var_int_G) / 16 << 24, 0, 0, ((f)this).j, ((f)this).k);
                this.var_aeii_d_d.a(graphics, 0, 0);
            }
        }
    }

    public static final void a(Graphics graphics, String string, int n, int n2, int n3, int n4, int n5) {
        graphics.setColor(0);
        graphics.drawString(string, n - 1, n2 - 1, n3);
        graphics.drawString(string, n - 1, n2, n3);
        graphics.drawString(string, n - 1, n2 + 1, n3);
        graphics.drawString(string, n, n2 - 1, n3);
        graphics.drawString(string, n, n2 + 1, n3);
        graphics.drawString(string, n + 1, n2 + 1, n3);
        graphics.drawString(string, n + 1, n2, n3);
        graphics.drawString(string, n + 1, n2 - 1, n3);
        graphics.setColor(n4);
        graphics.drawString(string, n, n2, n3);
    }

    public final void a(Graphics object) {
        Object object2;
        int n;
        if (this.var_byte_h == 4) {
            object.setColor(0xFFFFFF);
            object.fillRect(0, 0, ((f)this).j, ((f)this).k);
            object.setFont(aeii.c.var_javax_microedition_lcdui_Font_b);
            object.setColor(0);
            object.drawString(aeii.f.java_lang_String_a(58), ((f)this).j / 2, ((f)this).k / 2 - 1, 33);
            int n2 = ((f)this).k / 18;
            if (n2 < 12) {
                n2 = 12;
            }
            int n3 = ((f)this).k / 2 + 1;
            object.setColor(0xCECECE);
            aeii.e.a((Graphics)object, 1, n3, ((f)this).j - 2, n2);
            object.setColor(2370117);
            aeii.e.a((Graphics)object, 2, n3 + 2, this.X * (((f)this).j - 6) / 100, n2 - 4);
            return;
        }
        if (this.var_byte_h == 2) {
            a.a.a.g g2;
            Graphics graphics = object;
            object = this;
            int n4 = 0;
            int n5 = 0;
            if (((g)object).var_boolean_C) {
                n4 = aeii.c.int_a() % 10;
                n5 = aeii.c.int_a() % 3;
            }
            graphics.translate(0, ((g)object).ak);
            graphics.setClip(0, 0, ((g)object).var_int_a, ((g)object).var_int_o);
            ((g)object).var_aeii_h_a.a(graphics, n4, n5);
            ((g)object).var_aeii_h_b.a(graphics, n4 + ((g)object).var_int_c, n5);
            graphics.setColor(0);
            graphics.fillRect(((g)object).var_int_c - 1 + n4, 0, 2, ((g)object).var_int_o);
            ((g)object).var_aeii_h_a.b(graphics);
            ((g)object).var_aeii_h_b.b(graphics);
            Vector<a.a.a.g> vector = new Vector<a.a.a.g>(((g)object).var_java_util_Vector_g.size());
            for (n5 = 0; n5 < ((g)object).var_java_util_Vector_g.size(); ++n5) {
                g2 = (a.a.a.g)((g)object).var_java_util_Vector_g.elementAt(n5);
                int n6 = 0;
                if (g2.d) {
                    vector.addElement(g2);
                } else {
                    for (n6 = 0; n6 < vector.size(); ++n6) {
                        a.a.a.g g3 = (a.a.a.g)vector.elementAt(n6);
                        if (!g3.d && g2.n + g2.p >= g3.n + g3.p) continue;
                        vector.insertElementAt(g2, n6);
                        break;
                    }
                }
                if (n6 != vector.size()) continue;
                vector.addElement(g2);
            }
            ((g)object).var_java_util_Vector_g = vector;
            for (n5 = 0; n5 < ((g)object).var_java_util_Vector_g.size(); ++n5) {
                g2 = (a.a.a.g)((g)object).var_java_util_Vector_g.elementAt(n5);
                if (g2.var_byte_l == 0) {
                    graphics.setClip(0, 0, ((g)object).var_int_c, ((g)object).var_int_o);
                } else if (g2.var_byte_l == 1) {
                    graphics.setClip(((g)object).var_int_c, 0, ((g)object).var_int_c, ((g)object).var_int_o);
                } else {
                    graphics.setClip(0, 0, ((g)object).var_int_a, ((g)object).var_int_o);
                }
                g2.a(graphics, 0, g2.q);
            }
            graphics.translate(0, -((g)object).ak);
            graphics.setClip(0, 0, ((f)object).j, ((f)object).k);
            if (((g)object).var_boolean_a) {
                ((g)object).var_boolean_a = false;
                n5 = ((f)object).k - var_byte_a;
                graphics.setColor(14672074);
                graphics.fillRect(0, n5, ((f)object).j, (int)var_byte_a);
                aeii.e.a(graphics, 0, n5, ((f)object).j, var_byte_a, 0);
                graphics.setClip(0, 0, ((f)object).j, ((f)object).k);
                ((g)object).var_aeii_h_a.a(graphics);
                graphics.translate(((g)object).var_int_c, 0);
                ((g)object).var_aeii_h_b.a(graphics);
                graphics.translate(-((g)object).var_int_c, 0);
            }
            if (((g)object).var_boolean_F) {
                ((g)object).var_boolean_F = false;
                super.a(graphics, ((g)object).var_aeii_h_a.var_aeii_a_a, ((g)object).var_aeii_h_b.var_aeii_a_a, 0);
            }
            if (((g)object).var_boolean_E) {
                aeii.g.a(graphics, 0, ((g)object).var_int_G, 16, 1, 0, 0, ((f)object).j, ((f)object).k);
            }
            return;
        }
        if (this.var_byte_h == 3) {
            this.d((Graphics)object);
            return;
        }
        if (this.var_boolean_j) {
            this.c((Graphics)object);
            return;
        }
        if (this.var_boolean_g) {
            if (this.var_int_G >= 16) {
                if (this.var_byte_d != 11 && this.var_byte_d != 10) {
                    this.c((Graphics)object);
                    return;
                }
                object.setColor(0);
                object.fillRect(0, 0, ((f)this).j, ((f)this).k);
                return;
            }
            aeii.g.a((Graphics)object, 0, this.var_int_G, 16, 0, 0, 0, ((f)this).j, ((f)this).k);
            return;
        }
        if (this.var_byte_h == 0) {
            this.e((Graphics)object);
        } else if (this.var_byte_d == 14) {
            object.setClip(0, 0, ((f)this).j, ((f)this).k);
            object.setColor(0);
            object.fillRect(0, 0, ((f)this).j, ((f)this).k);
        } else if (this.var_byte_d == 10 && this.aC > 0) {
            this.c((Graphics)object);
        } else if (this.var_byte_d == 11 && !this.var_boolean_g) {
            String string = aeii.f.java_lang_String_a(57);
            object.setClip(0, 0, ((f)this).j, ((f)this).k);
            object.setFont(aeii.c.var_javax_microedition_lcdui_Font_b);
            object.setColor(0);
            object.fillRect(0, 0, ((f)this).j, ((f)this).k);
            if (this.var_byte_c == 0) {
                object.setColor(0xFFFFFF);
                if (this.var_aeii_d_f != null) {
                    this.var_aeii_d_f.a((Graphics)object, ((f)this).l, ((f)this).m, 3);
                    aeii.c.b((Graphics)object, string, ((f)this).l, ((f)this).k - 2, 33);
                } else {
                    n = ((f)this).m - aeii.c.var_javax_microedition_lcdui_Font_b.getHeight() / 2;
                    aeii.c.b((Graphics)object, string, ((f)this).l, n, 17);
                }
            }
        } else {
            int n7;
            Object object3;
            int n8;
            int n9;
            object.setClip(0, 0, this.var_int_a, this.var_int_b);
            if (this.var_int_t < this.var_int_a || this.var_int_u < this.var_int_b) {
                object.setColor(0);
                object.fillRect(0, 0, this.var_int_a, this.var_int_b);
            }
            if (this.var_boolean_C) {
                object2 = aeii.c.int_a() % 10;
                n = aeii.c.int_a() % 4;
                object.translate(object2, n);
                this.b((Graphics)object);
                object.translate(-object2, -n);
            } else {
                this.b((Graphics)object);
            }
            n = this.var_java_util_Vector_a.size();
            for (object2 = 0; object2 < n; ++object2) {
                a a2 = (a)this.var_java_util_Vector_a.elementAt((int)object2);
                if (a2.var_byte_f == 3) {
                    this.var_aeii_d_a.a((Graphics)object, this.var_int_v + a2.m, this.var_int_w + a2.n);
                    continue;
                }
                if (a2 == this.var_aeii_a_b) continue;
                a2.a((Graphics)object, this.var_int_v, this.var_int_w);
            }
            n = this.var_java_util_Vector_a.size();
            for (object2 = 0; object2 < n; ++object2) {
                ((a)this.var_java_util_Vector_a.elementAt((int)object2)).b((Graphics)object, this.var_int_v, this.var_int_w);
            }
            if (this.var_java_util_Vector_b != null) {
                object.setColor(14745682);
                n = 12 + this.al / 4;
                n9 = 24 - n;
                n8 = this.var_java_util_Vector_b.size();
                for (int i = 0; i < n8; ++i) {
                    short[] sArray;
                    short[] sArray2 = (short[])this.var_java_util_Vector_b.elementAt(i);
                    object3 = sArray2;
                    n7 = sArray2[0] * 24 + this.var_int_v;
                    object2 = object3[1] * 24 + this.var_int_w;
                    int n10 = n7 + 12;
                    int n11 = object2 + 12;
                    if (i != 0) {
                        sArray = (short[])this.var_java_util_Vector_b.elementAt(i - 1);
                        if (sArray[0] == object3[0] + true) {
                            object.fillRect(n7 + n9, n11 - this.am, n, this.al);
                        } else if (sArray[0] == object3[0] - true) {
                            object.fillRect(n7, n11 - this.am, n, this.al);
                        } else if (sArray[1] == object3[1] + true) {
                            object.fillRect(n10 - this.am, object2 + n9, this.al, n);
                        } else if (sArray[1] == object3[1] - true) {
                            object.fillRect(n10 - this.am, object2, this.al, n);
                        }
                    }
                    if (i == n8 - 1) {
                        object.setClip(0, 0, this.var_int_a, this.var_int_b);
                        this.var_a_a_a_g_r.a((Graphics)object, n10, n11, 3);
                        continue;
                    }
                    sArray = (short[])this.var_java_util_Vector_b.elementAt(i + 1);
                    if (sArray[0] == object3[0] + true) {
                        object.fillRect(n7 + n9, n11 - this.am, n, this.al);
                        continue;
                    }
                    if (sArray[0] == object3[0] - true) {
                        object.fillRect(n7, n11 - this.am, n, this.al);
                        continue;
                    }
                    if (sArray[1] == object3[1] + true) {
                        object.fillRect(n10 - this.am, object2 + n9, this.al, n);
                        continue;
                    }
                    if (sArray[1] != object3[1] - true) continue;
                    object.fillRect(n10 - this.am, object2, this.al, n);
                }
            }
            if (this.var_aeii_a_b != null) {
                this.var_aeii_a_b.a((Graphics)object, this.var_int_v, this.var_int_w);
                this.var_aeii_a_b.b((Graphics)object, this.var_int_v, this.var_int_w);
            }
            if (this.var_boolean_d) {
                this.var_a_a_a_g_q.a((Graphics)object, this.var_int_v + 12, this.var_int_w + 12, 3);
            }
            n = this.var_java_util_Vector_c.size();
            for (object2 = 0; object2 < n; ++object2) {
                object3 = (a.a.a.g)this.var_java_util_Vector_c.elementAt((int)object2);
                ((a.a.a.g)object3).a((Graphics)object, this.var_int_v, this.var_int_w + ((a.a.a.g)object3).q);
            }
            object.setClip(0, 0, ((f)this).j, ((f)this).k);
            object2 = ((f)this).k - var_byte_a;
            if (this.Q > 0) {
                aeii.e.a((Graphics)object, 0, object2, this.var_int_a, var_byte_a, 14);
                object.setClip(0, 0, ((f)this).j, ((f)this).k);
            }
            n = var_byte_a - 24 >> 1;
            n9 = 24 + (n << 1);
            int n12 = ((f)this).j - n9;
            object2 += this.Q;
            if (this.var_boolean_u) {
                this.var_boolean_u = false;
                n8 = ((f)this).k - var_byte_a / 2 + this.Q;
                int n13 = 10;
                if (this.var_int_a <= 120) {
                    n13 = 4;
                }
                if (this.var_byte_n == 1) {
                    aeii.e.a((Graphics)object, 0, object2, n12 + 1, var_byte_a, 0, 2370117, var_int_arr_f[0], this.Q, var_byte_a);
                    aeii.c.a((Graphics)object, this.var_byte_l + 1 + "/" + this.var_aeii_d_arr_d.length, n13 + 1, n8, 1, 6);
                } else if (this.var_byte_n == 2) {
                    aeii.e.a((Graphics)object, 0, object2, n12 + 1, var_byte_a, 0, 2370117, var_int_arr_f[this.var_byte_arr_d[this.var_byte_b]], this.Q, var_byte_a);
                    aeii.c.a((Graphics)object, this.var_byte_m + 1 + "/" + 12, n13 + 1, n8, 1, 6);
                } else {
                    aeii.e.a((Graphics)object, 0, object2, n12 + 1, var_byte_a, 0, 2370117, var_int_arr_f[this.var_byte_arr_d[this.var_byte_b]], this.Q, var_byte_a);
                    if (this.var_byte_c == 1) {
                        n7 = n12 / 2;
                        this.var_a_a_a_g_m.a((Graphics)object, 0, n7, n8, 6);
                        aeii.c.a((Graphics)object, this.int_a(-1, -1, this.var_byte_b) - this.int_a(10, -1, this.var_byte_b) + "/" + this.var_int_s, n7 + this.var_a_a_a_g_m.o + 1, n8, 1, 6);
                    }
                    this.var_a_a_a_g_m.a((Graphics)object, 1, n13, n8, 6);
                    n7 = n13 + this.var_a_a_a_g_m.o + 1;
                    if (!aeii.c.var_boolean_a && this.var_byte_arr_m[this.var_byte_b] != 1) {
                        aeii.c.a((Graphics)object, "- - -", n7, n8, 1, 6);
                    } else {
                        aeii.c.a((Graphics)object, "" + this.var_int_arr_b[this.var_byte_b], n7, n8, 1, 6);
                    }
                }
                object.setClip(0, 0, ((f)this).j, ((f)this).k);
            }
            if (this.var_boolean_v) {
                this.var_boolean_v = false;
                if (n > 0) {
                    aeii.g.a((Graphics)object, n12, object2, n9, var_byte_a);
                }
                n8 = n12 + n;
                n7 = object2 + n;
                if (this.var_byte_n == 0 || this.var_byte_n == 2) {
                    this.var_byte_l = this.var_byte_arr_arr_a[this.var_int_h][this.var_int_i];
                }
                this.var_aeii_d_arr_d[this.var_byte_l].a((Graphics)object, n8, n7);
                if (this.var_byte_n == 2) {
                    if (this.var_aeii_a_s == null) {
                        this.var_aeii_a_s = aeii.a.b(this.var_byte_m, this.var_byte_b, 0, 0, false);
                    }
                    this.var_aeii_a_s.a((Graphics)object, n8, n7);
                }
                if (this.var_byte_n == 0) {
                    String string = "." + var_byte_arr_a[this.var_byte_arr_k[this.var_byte_l]];
                    aeii.c.a((Graphics)object, string, n8 + 24, n7 + 24, 0, 40);
                }
                if (n == 0) {
                    object.setColor(0);
                    object.drawRect(n8, n7, 24, 24);
                }
            }
            if (this.var_byte_d == 6 && this.var_aeii_a_arr_a[this.var_int_z].var_byte_f != 4) {
                n8 = 0;
                if (this.var_int_i * 24 <= this.var_int_b / 2 - 24) {
                    n8 = this.var_int_b - this.var_a_a_a_g_c.p - this.ak + 2;
                }
                this.a((Graphics)object, this.var_aeii_a_b, this.var_aeii_a_arr_a[this.var_int_z], n8);
            }
        }
        if (this.boolean_a()) {
            if (this.var_boolean_e) {
                this.a((Graphics)object, var_int_e, 1, this.var_int_b);
            }
            if (this.var_boolean_f) {
                this.a((Graphics)object, var_int_d, 0, this.var_int_b);
            }
            if (this.var_byte_h == 1 && (this.var_byte_arr_m[this.var_byte_b] == 0 || this.var_byte_d == 0) && this.var_byte_d != 11) {
                this.a((Graphics)object, var_int_d, 3, this.var_int_b);
            }
        }
        if (this.var_boolean_o || this.var_boolean_p) {
            object2 = this.R;
            if (this.var_boolean_p) {
                object2 = 16 - this.R;
            }
            n = object2 * 255 / 16;
            aeii.c.a((Graphics)object, n << 24, 0, 0, ((f)this).j, ((f)this).k);
        }
    }

    public static void a(Graphics graphics, int n, int n2, int n3, int n4) {
        graphics.setColor(4344163);
        graphics.fillRect(n, n2, n3, n4);
        graphics.setColor(11384493);
        graphics.fillRect(n + 1, n2 + 1, n3 - 2, n4 - 2);
        graphics.setColor(4344163);
        graphics.fillRect(n + 3, n2 + 3, n3 - 6, n4 - 6);
    }

    private void a(Graphics graphics, a a2, a a3, int n) {
        int n2 = this.ak - 2;
        graphics.setColor(11384493);
        graphics.fillRect(0, n, this.var_int_a, n2);
        graphics.setColor(0);
        graphics.fillRect(0, n2 + n, this.var_int_a, 2);
        int n3 = 0;
        int n4 = (n2 /= 2) + n;
        for (int i = 0; i < 3; ++i) {
            this.var_a_a_a_g_l.a(graphics, i, n3 + 1, n4, 6);
            int n5 = n + 1;
            int n6 = i == 0 ? (((f)this).j <= 132 ? 56 : 61 * this.var_int_a / 176) : (i == 1 ? (((f)this).j <= 132 ? 28 : 47 * this.var_int_a / 176) : this.var_int_a - (n3 += this.var_a_a_a_g_l.o + 2));
            for (int j = 0; j < 2; ++j) {
                Object object;
                a a4;
                if (j == 0) {
                    a4 = a2;
                    object = a3;
                } else {
                    a4 = a3;
                    object = a2;
                }
                int n7 = n2 - 2;
                graphics.setColor(2172994);
                graphics.fillRect(n3, n5, n6, n7);
                int n8 = n3 + 1;
                if (i == 0 || ((f)this).j > 132) {
                    graphics.setColor(var_int_arr_f[this.var_byte_arr_d[a4.var_byte_e]]);
                    graphics.fillRect(n8, n5 + 1, 3, n7 - 2);
                    n8 += 4;
                }
                int n9 = 0;
                if (i == 0) {
                    if (j != 0 && !a3.boolean_a((int)a2.var_short_b, a2.var_short_c)) {
                        object = "0-0";
                    } else {
                        n9 = a4.a((a)object);
                        object = a4.var_int_d + n9 + "-" + (a4.var_int_e + n9);
                    }
                } else if (i == 1) {
                    n9 = a4.b((a)object);
                    object = "" + (a4.var_int_f + n9);
                } else {
                    object = "" + a4.var_short_a;
                }
                aeii.c.a(graphics, (String)object, n8, n5 + 1, 0);
                if (n9 > 0) {
                    this.var_a_a_a_g_p.a(graphics, 1, n8 + 1 + aeii.c.a((byte)0, (String)object), n5 + n7 / 2, 6);
                } else if (n9 < 0) {
                    this.var_a_a_a_g_p.a(graphics, 2, n8 + 1 + aeii.c.a((byte)0, (String)object), n5 + n7 / 2, 6);
                }
                int n10 = n3 + n6 - 2;
                if (i == 0 && (a4.var_byte_g & 2) != 0) {
                    this.var_a_a_a_g_s.a(graphics, 1, n10, n5 + n7 / 2, 10);
                    n10 -= this.var_a_a_a_g_s.o;
                }
                if ((i == 0 || i == 1) && (a4.var_byte_g & 1) != 0) {
                    this.var_a_a_a_g_s.a(graphics, 0, n10, n5 + n7 / 2, 10);
                }
                n5 += n2;
            }
            n3 += n6;
        }
    }

    public final void a(Graphics graphics, int n, int n2, int n3) {
        int n4 = 0;
        int n5 = 0;
        if (n == 1024) {
            n5 = 36;
        } else if (n == 2048) {
            n4 = ((f)this).j;
            n5 = 40;
        }
        this.var_a_a_a_g_c.a(graphics, n2, n4, n3, n5);
    }

    public final a aeii_a_a(int n, int n2, byte by) {
        int n3 = this.var_java_util_Vector_a.size();
        for (int i = 0; i < n3; ++i) {
            int n4;
            int n5;
            a a2 = (a)this.var_java_util_Vector_a.elementAt(i);
            if (a2.var_byte_f == 1) {
                n5 = a2.var_int_b;
                n4 = a2.var_int_c;
            } else {
                n5 = a2.var_short_b;
                n4 = a2.var_short_c;
            }
            if (n != n5 || n2 != n4 || !(by == 0 ? a2.var_byte_f != 3 : by == 1 && a2.var_byte_f == 3)) continue;
            return a2;
        }
        return null;
    }

    public final byte byte_a(int n, int n2) {
        return this.var_byte_arr_k[this.var_byte_arr_arr_a[n][n2]];
    }

    private void m() {
        aeii.c.f();
        this.var_int_D = 0;
        this.Q = 0;
        this.var_byte_d = (byte)8;
        this.var_long_d = this.var_long_a;
    }

    private void n() {
        do {
            int n;
            if (this.var_byte_n != 0) {
                return;
            }
            this.var_byte_arr_arr_d[this.var_byte_b][0] = (byte)this.var_int_h;
            this.var_byte_arr_arr_d[this.var_byte_b][1] = (byte)this.var_int_i;
            this.var_short_a = (short)(this.var_short_a + 1);
            this.var_byte_b = (byte)((this.var_byte_b + 1) % this.var_byte_f);
            if (this.var_byte_arr_m[this.var_byte_b] == 2) {
                this.n();
                return;
            }
            for (n = this.var_java_util_Vector_a.size() - 1; n >= 0; --n) {
                a a2 = (a)this.var_java_util_Vector_a.elementAt(n);
                if (a2.var_byte_f == 3) {
                    if (a2.var_byte_d == 9 || (a2.var_byte_h = (byte)(a2.var_byte_h - 1)) > 0) continue;
                    a2.void_a();
                    continue;
                }
                a2.var_byte_f = 0;
                if ((a2.var_byte_g & 1) != 0 && a2.var_byte_i == this.var_byte_b) {
                    a2.c((byte)1);
                }
                if (a2.var_byte_e == this.var_byte_b) {
                    a2.c((byte)2);
                }
                a2.k = 0;
            }
            this.an = 0;
            for (n = 0; n < this.var_byte_arr_arr_a.length; ++n) {
                for (int i = 0; i < this.var_byte_arr_arr_a[n].length; ++i) {
                    if (!this.boolean_b(n, i, this.var_byte_b)) continue;
                    if (this.byte_a(n, i) == 8) {
                        this.an += 30;
                        continue;
                    }
                    if (this.byte_a(n, i) != 9) continue;
                    this.an += 50;
                }
            }
            byte by = this.var_byte_b;
            this.var_int_arr_b[by] = this.var_int_arr_b[by] + this.an;
            for (n = 0; n < this.var_byte_arr_arr_e.length; ++n) {
                this.var_int_arr_m[n] = 0;
            }
            if (this.var_byte_arr_m[this.var_byte_b] == 1) {
                this.d(this.var_byte_arr_arr_d[this.var_byte_b][0], this.var_byte_arr_arr_d[this.var_byte_b][1]);
            }
            this.var_boolean_i = true;
            this.var_boolean_u = true;
            if (this.var_byte_arr_m[this.var_byte_b] == 0) {
                this.o();
                continue;
            }
            aeii.a.var_byte_c = aeii.a.var_byte_b;
        } while (this.int_a(-1, 0, this.var_byte_b) <= 0 && this.int_e(this.var_byte_b) == 0);
    }

    private boolean boolean_a(a a2) {
        return a2.a((short)8) && this.byte_a((int)a2.var_short_b, (int)a2.var_short_c) == 8 && !aeii.g.a(this.var_byte_arr_arr_a[a2.var_short_b][a2.var_short_c]);
    }

    private boolean boolean_b(a a2) {
        if (a2.a((short)8) && this.byte_a((int)a2.var_short_b, (int)a2.var_short_c) == 8 && aeii.g.a(this.var_byte_arr_arr_a[a2.var_short_b][a2.var_short_c]) && !this.boolean_a((int)a2.var_short_b, (int)a2.var_short_c, (int)this.var_byte_arr_e[a2.var_byte_e])) {
            return true;
        }
        return a2.a((short)16) && this.byte_a((int)a2.var_short_b, (int)a2.var_short_c) == 9 && !this.boolean_a((int)a2.var_short_b, (int)a2.var_short_c, (int)this.var_byte_arr_e[a2.var_byte_e]);
    }

    private void void_a(byte by, int n, int n2) {
        this.var_byte_arr_arr_a[n][n2] = by;
    }

    private void void_b(int n, int n2, int n3) {
        if (aeii.g.a(this.var_byte_arr_arr_a[n][n2])) {
            this.void_a((byte)(37 + (n3 << 1) + (this.var_byte_arr_arr_a[n][n2] - 37) % 2), n, n2);
        }
    }

    private static boolean b(byte by) {
        return by >= 37 && by < 47 || by == 27;
    }

    public static boolean a(byte by) {
        return by >= 37 && by < 47;
    }

    private int int_a(int n, int n2) {
        return aeii.g.a(n, n2, this.var_byte_arr_arr_a);
    }

    private static int a(int n, int n2, byte[][] byArray) {
        if ((n = byArray[n][n2] - 37 >> 1) >= 0 && n <= 4) {
            return n;
        }
        return -1;
    }

    private int int_d(int n) {
        if (n != -1 && n != 0) {
            return this.var_byte_arr_l[n];
        }
        return -1;
    }

    public final boolean boolean_a(int n, int n2, int n3) {
        if ((n = this.int_d(this.int_a(n, n2))) >= 0) {
            return n3 == this.var_byte_arr_e[n];
        }
        return false;
    }

    private boolean boolean_b(int n, int n2, int n3) {
        return this.int_a(n, n2) == this.var_byte_arr_d[n3];
    }

    private int int_e(int n) {
        int n2 = 0;
        for (int i = 0; i < this.as; ++i) {
            if (!this.boolean_b(this.var_byte_arr_arr_f[i][0], this.var_byte_arr_arr_f[i][1], n)) continue;
            ++n2;
        }
        return n2;
    }

    private int int_a(int n, int n2, byte by) {
        int n3 = 0;
        int n4 = this.var_java_util_Vector_a.size();
        for (int i = 0; i < n4; ++i) {
            a a2 = (a)this.var_java_util_Vector_a.elementAt(i);
            if (n != -1 && a2.var_byte_d != n || (n2 != -1 || a2.var_byte_f == 3) && n2 != a2.var_byte_f || by != -1 && a2.var_byte_e != by) continue;
            ++n3;
        }
        return n3;
    }

    private a[] aeii_a_arr_a(int n, int n2, byte by) {
        Vector<a> vector = new Vector<a>();
        int n3 = this.var_java_util_Vector_a.size();
        for (int i = 0; i < n3; ++i) {
            a a2 = (a)this.var_java_util_Vector_a.elementAt(i);
            if (n != -1 && a2.var_byte_d != n || (n2 != -1 || a2.var_byte_f == 3) && n2 != a2.var_byte_f || by != -1 && a2.var_byte_e != by) continue;
            vector.addElement(a2);
        }
        Object[] objectArray = new a[vector.size()];
        vector.copyInto(objectArray);
        return objectArray;
    }

    private static void a(Graphics graphics, int n, int n2, int n3, d d2, int n4, int n5, int n6) {
        n2 = d2.var_int_a;
        n3 = d2.b;
        int n7 = n2 / 2;
        int n8 = n2 * n / 40;
        n2 = n2 * (40 - n) / 160;
        n = n * 360 / 40;
        n6 = n6 * 360 / n3;
        for (int i = 0; i < n3; ++i) {
            int n9 = n2 * aeii.f.short_a(n) >> 10;
            graphics.setClip(n4 + n7 - n8 / 2 + n9, n5 + i, n8, 1);
            d2.a(graphics, n4 + n9, n5);
            n += n6;
        }
    }

    private static void a(Graphics graphics, int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8) {
        n3 = n7 / 8 + 1;
        for (n5 = 0; n5 < 8; ++n5) {
            n6 = n2 - n5;
            if (n6 < 0) {
                n6 = 0;
            }
            n6 = n6 >= 9 ? n3 : n3 * n6 / 9;
            if (n4 == 1) {
                n7 = n5 * n3 + n6;
                n6 = n3 - n6;
            } else {
                n7 = n5 * n3;
            }
            int n9 = n6 * 255 / n3;
            aeii.c.a(graphics, n9 << 24 | n);
            for (n9 = 0; n9 <= 0; ++n9) {
                graphics.fillRect(n7 + 0, 0, n6, n8);
            }
        }
    }

    private boolean boolean_a(byte by, int n, int n2) {
        if (this.var_int_s > this.int_a(-1, -1, this.var_byte_b) - this.int_a(10, -1, this.var_byte_b) && by <= this.var_int_n && aeii.a.var_short_arr_a[by] <= this.var_int_arr_b[this.var_byte_b] && aeii.a.var_short_arr_a[by] > 0) {
            this.a(this.var_byte_arr_arr_b, 0);
            return aeii.a.a(this.var_byte_arr_arr_b, n, n2, aeii.a.var_byte_arr_a[by], -1, by, this.var_byte_b, true);
        }
        return false;
    }

    private boolean boolean_a(a a2, int n, int n2) {
        if (this.var_int_s > this.int_a(-1, -1, this.var_byte_b) - this.int_a(10, -1, this.var_byte_b) && (a2.var_byte_d <= this.var_int_n || a2.var_byte_d == 9) && a2.var_int_j <= this.var_int_arr_b[this.var_byte_b]) {
            this.a(this.var_byte_arr_arr_b, 0);
            return aeii.a.a(this.var_byte_arr_arr_b, n, n2, aeii.a.var_byte_arr_a[a2.var_byte_d], -1, a2.var_byte_d, this.var_byte_b, true);
        }
        return false;
    }

    private void o() {
        a[] aArray = this.aeii_a_arr_a(-1, 0, this.var_byte_b);
        this.var_java_util_Vector_f = new Vector(aArray.length);
        for (int i = 0; i < aArray.length; ++i) {
            int n;
            for (n = 0; n < i; ++n) {
                a a2 = (a)this.var_java_util_Vector_f.elementAt(n);
                byte by = var_byte_arr_p[aArray[i].var_byte_d];
                byte by2 = var_byte_arr_p[a2.var_byte_d];
                if (by >= by2 && (by != by2 || aArray[i].var_int_h >= a2.var_int_h)) {
                    continue;
                }
                this.var_java_util_Vector_f.insertElementAt(aArray[i], n);
                break;
            }
            if (n != i) continue;
            this.var_java_util_Vector_f.addElement(aArray[i]);
        }
        this.var_aeii_a_arr_d = new a[this.var_byte_arr_arr_e.length];
        this.var_byte_arr_q = new byte[this.var_byte_arr_arr_e.length];
        aeii.a.var_byte_c = aeii.a.var_byte_a;
        this.aq = 0;
        this.var_byte_k = 0;
    }

    private void p() {
        if (aeii.f.var_aeii_c_a.boolean_a(var_int_d)) {
            this.a(this.var_byte_arr_g, this.var_int_p, this.var_int_b, (f)this);
            aeii.f.var_aeii_c_a.b();
            return;
        }
        if (this.var_byte_k == 4) {
            if (this.var_aeii_a_k == null && this.var_aeii_a_l == null) {
                if (this.boolean_b(this.var_aeii_a_b)) {
                    int n = this.int_b(this.var_aeii_a_b.var_short_b, this.var_aeii_a_b.var_short_c);
                    if (this.aw != -1 && this.aw != n) {
                        this.var_aeii_a_arr_d[this.aw] = this.var_aeii_a_arr_d[n];
                        this.var_aeii_a_arr_d[n] = this.var_aeii_a_b;
                    }
                    this.void_b(this.var_aeii_a_b.var_short_b, this.var_aeii_a_b.var_short_c, this.var_byte_arr_d[this.var_aeii_a_b.var_byte_e]);
                    aeii.f.var_aeii_c_a.a(this.a(null, aeii.f.java_lang_String_a(73), this.var_int_b, 1000));
                    aeii.c.b(9, 1);
                    this.var_byte_d = (byte)9;
                    this.var_long_d = this.var_long_a;
                } else if (this.boolean_a(this.var_aeii_a_b)) {
                    int n = this.int_b(this.var_aeii_a_b.var_short_b, this.var_aeii_a_b.var_short_c);
                    if (this.aw != -1 && this.aw != n) {
                        this.var_aeii_a_arr_d[this.aw] = this.var_aeii_a_arr_d[n];
                        this.var_aeii_a_arr_d[n] = this.var_aeii_a_b;
                    }
                    this.void_a((byte)37, (int)this.var_aeii_a_b.var_short_b, (int)this.var_aeii_a_b.var_short_c);
                    aeii.f.var_aeii_c_a.a(this.a(null, aeii.f.java_lang_String_a(74), this.var_int_b, 1000));
                    aeii.c.b(9, 1);
                    this.var_byte_d = 0;
                    this.var_long_d = this.var_long_a;
                } else {
                    this.var_byte_d = 0;
                }
                this.var_aeii_a_b.void_d();
                this.var_aeii_a_b = null;
                this.var_byte_k = 0;
            } else {
                this.var_byte_k = (byte)5;
                this.var_aeii_a_b.a(this.var_byte_arr_arr_b, (int)this.var_aeii_a_b.var_short_b, (int)this.var_aeii_a_b.var_short_c);
                this.var_boolean_c = true;
                this.var_boolean_b = true;
                this.var_long_j = this.var_long_a;
                if (this.var_aeii_a_k != null) {
                    this.var_a_a_a_g_q.a(var_byte_arr_arr_c[1]);
                    this.d(this.var_aeii_a_k.var_short_b, this.var_aeii_a_k.var_short_c);
                } else if (this.var_aeii_a_l != null) {
                    this.d(this.var_aeii_a_l.var_short_b, this.var_aeii_a_l.var_short_c);
                }
            }
            this.var_boolean_d = true;
            return;
        }
        if (this.var_byte_k == 5) {
            if (this.var_long_a - this.var_long_j >= 500L) {
                if (this.var_aeii_a_k != null) {
                    this.a(this.var_aeii_a_b, this.var_aeii_a_k);
                } else if (this.var_aeii_a_l != null) {
                    this.void_a(this.var_aeii_a_l, this.var_byte_b);
                    this.var_aeii_a_l = null;
                    this.var_byte_k = (byte)7;
                    this.var_aeii_a_b.void_d();
                }
                this.var_boolean_b = false;
                this.var_boolean_c = false;
                return;
            }
        } else if (this.var_byte_k == 7) {
            if (this.var_aeii_a_e == null) {
                this.var_byte_k = 0;
                this.var_byte_d = 0;
                return;
            }
        } else if (this.var_byte_k == 6) {
            if (this.var_long_a - this.var_long_j >= 1000L) {
                this.var_aeii_a_k = null;
                this.var_byte_k = 0;
                this.var_byte_d = 0;
                return;
            }
        } else {
            if (this.var_byte_k == 2) {
                return;
            }
            if (this.var_byte_k == 3) {
                if (this.aq == 0) {
                    if (this.boolean_a(this.var_aeii_a_b.m + 12, this.var_aeii_a_b.n + 12)) {
                        if (this.var_byte_c == 0 && this.var_int_x == 7 && this.var_aeii_a_b == this.var_aeii_a_arr_b[1]) {
                            this.var_aeii_a_j = null;
                            a[] aArray = this.aeii_a_arr_a(-1, -1, (byte)0);
                            if (aArray.length > 0) {
                                this.void_a(aArray[aeii.c.int_a(aArray.length)]);
                            }
                            this.var_byte_k = (byte)4;
                            return;
                        }
                        this.aq = 1;
                        this.var_long_j = this.var_long_a;
                        return;
                    }
                } else if (this.aq == 1) {
                    if (this.var_long_a - this.var_long_j >= 100L) {
                        this.var_boolean_b = true;
                        this.var_boolean_c = false;
                        this.aq = 2;
                        this.var_byte_d = 1;
                        this.var_long_j = this.var_long_a;
                        return;
                    }
                } else if (this.aq == 2) {
                    if (this.var_long_a - this.var_long_j >= 200L) {
                        this.var_int_h = this.ao;
                        this.var_int_i = this.ap;
                        this.var_a_a_a_g_q.b(this.ao * 24, this.ap * 24);
                        this.var_java_util_Vector_b = this.var_aeii_a_b.a((int)this.var_aeii_a_b.var_short_b, (int)this.var_aeii_a_b.var_short_c, this.var_int_h, this.var_int_i);
                        this.aq = 3;
                        this.var_long_j = this.var_long_a;
                        return;
                    }
                } else if (this.aq == 3 && this.var_long_a - this.var_long_j >= 200L) {
                    this.var_java_util_Vector_b = null;
                    this.var_aeii_a_b.a(this.ao, this.ap, true);
                    this.var_byte_k = (byte)2;
                    this.aq = 0;
                    this.var_byte_d = (byte)2;
                }
                return;
            }
            if (this.var_java_util_Vector_f.isEmpty()) {
                int n;
                byte by;
                int n2;
                int n3;
                a a2 = null;
                int n4 = 0;
                int n5 = 6666;
                int n6 = 0;
                byte by2 = 0;
                for (n3 = 0; n3 < this.var_byte_arr_arr_e.length; ++n3) {
                    int n7;
                    int n8;
                    n2 = this.var_byte_arr_arr_e[n3][0];
                    by = this.var_byte_arr_arr_e[n3][1];
                    if (this.byte_a(n2, (int)by) != 9 || !this.boolean_b(n2, by, this.var_byte_b)) continue;
                    if (n4 == 0) {
                        n6 = n2;
                        by2 = by;
                    }
                    ++n4;
                    n = 0;
                    for (n8 = this.var_java_util_Vector_a.size() - 1; n8 >= 0; --n8) {
                        a a3 = (a)this.var_java_util_Vector_a.elementAt(n8);
                        if (this.var_byte_arr_e[a3.var_byte_e] == this.var_byte_arr_e[this.var_byte_b] || !a3.a((short)16)) continue;
                        n7 = Math.abs(a3.var_short_b - n2) + Math.abs(a3.var_short_c - by);
                        if (n7 < n5) {
                            n5 = n7;
                            n6 = n2;
                            by2 = by;
                        }
                        ++n;
                    }
                    if (n != 0) continue;
                    for (n8 = 0; n8 < this.var_byte_arr_arr_e.length; ++n8) {
                        byte by3 = this.var_byte_arr_arr_e[n8][0];
                        n7 = this.var_byte_arr_arr_e[n8][1];
                        if (this.byte_a((int)by3, n7) != 9 || this.boolean_b(by3, n7, this.var_byte_b) || (n = Math.abs(by3 - n2) + Math.abs(n7 - by)) >= n5) continue;
                        n5 = n;
                        n6 = n2;
                        by2 = by;
                    }
                }
                if (n4 > 0) {
                    for (n3 = 0; n3 < this.var_int_arr_a[this.var_byte_b]; ++n3) {
                        if (this.var_aeii_a_arr_arr_a[this.var_byte_b][n3] == null || this.var_aeii_a_arr_arr_a[this.var_byte_b][n3].var_byte_f != 3 || !this.boolean_a(this.var_aeii_a_arr_arr_a[this.var_byte_b][n3], n6, (int)by2)) continue;
                        a2 = this.aeii_a_a(this.var_aeii_a_arr_arr_a[this.var_byte_b][n3], n6, (int)by2);
                    }
                    if (a2 == null) {
                        if (this.int_a(0, -1, this.var_byte_b) < 2 && this.boolean_a((byte)0, n6, (int)by2)) {
                            a2 = this.aeii_a_a((byte)0, n6, (int)by2);
                        } else if (this.int_a(1, -1, this.var_byte_b) < 2 && this.boolean_a((byte)1, n6, (int)by2)) {
                            a2 = this.aeii_a_a((byte)1, n6, (int)by2);
                        } else {
                            n3 = 0;
                            n2 = 0;
                            for (by = 0; by < this.var_byte_f; by = (byte)(by + 1)) {
                                if (this.var_byte_arr_e[by] == this.var_byte_arr_e[this.var_byte_b]) {
                                    n3 += this.int_a(-1, -1, by);
                                    continue;
                                }
                                n2 += this.int_a(-1, -1, by);
                            }
                            if (this.var_int_arr_b[this.var_byte_b] >= 1000 || this.int_a(-1, -1, this.var_byte_b) < 8 || n3 < n2) {
                                byte by4;
                                n = 0;
                                byte[] byArray = new byte[12];
                                for (by4 = 1; by4 < 12; by4 = (byte)(by4 + 1)) {
                                    if (this.int_a((int)by4, -1, this.var_byte_b) > 0 && aeii.a.var_short_arr_a[by4] < 600 || !this.boolean_a(by4, n6, (int)by2)) continue;
                                    byArray[n] = by4;
                                    ++n;
                                }
                                if (n > 0) {
                                    by4 = byArray[Math.abs(aeii.c.int_a()) % n];
                                    a2 = this.aeii_a_a(by4, n6, (int)by2);
                                }
                            }
                        }
                    }
                }
                if (a2 != null) {
                    this.e(a2);
                    return;
                }
                this.var_aeii_a_arr_c = null;
                this.var_java_util_Vector_f = null;
                this.m();
                return;
            }
            if (this.var_byte_c == 0 && this.var_int_x == 7 && this.var_aeii_a_arr_b[1].var_byte_f != 2) {
                this.var_aeii_a_b = this.var_aeii_a_arr_b[1];
                this.d(this.var_aeii_a_b.var_short_b, this.var_aeii_a_b.var_short_c);
                this.var_aeii_a_j = this.var_aeii_a_b;
                this.var_byte_k = (byte)3;
                this.var_java_util_Vector_f.removeElement(this.var_aeii_a_b);
                return;
            }
            a a4 = this.var_aeii_a_m;
            if (this.var_aeii_a_m == null) {
                a4 = (a)this.var_java_util_Vector_f.elementAt(0);
            }
            this.e(a4);
            if (this.var_aeii_a_m == null) {
                this.var_java_util_Vector_f.removeElement(a4);
            }
        }
    }

    private void e(a a2) {
        int n;
        int n2;
        int n3;
        int n4;
        int n5;
        int n6;
        int n7;
        this.var_aeii_a_b = a2;
        this.var_boolean_d = true;
        this.a(this.var_byte_arr_arr_b, 0);
        this.var_aeii_a_b.b(this.var_byte_arr_arr_b);
        this.var_boolean_b = false;
        this.var_aeii_a_arr_c = this.aeii_a_arr_a(0, -1, this.var_byte_b);
        int n8 = 0;
        int n9 = this.var_aeii_a_arr_b.length + this.var_aeii_a_arr_c.length + this.var_byte_arr_arr_e.length;
        this.var_int_arr_arr_a = new int[n9][5];
        this.ar = 0;
        n9 = 10000;
        this.at = -1;
        this.au = -1;
        this.av = -1;
        this.aw = -1;
        for (n7 = 0; n7 < this.var_aeii_a_arr_c.length + this.var_aeii_a_arr_b.length; ++n7) {
            a a3 = null;
            if (n7 >= this.var_aeii_a_arr_c.length) {
                a3 = this.var_aeii_a_arr_b[n7 - this.var_aeii_a_arr_c.length];
                if (a3 != null) {
                    if (a3.var_byte_f == 3) {
                        a3 = null;
                    } else if (this.var_byte_arr_e[a3.var_byte_e] != this.var_byte_arr_e[this.var_byte_b] && this.var_aeii_a_arr_b[this.var_byte_b] == null) {
                        int[] nArray = this.var_int_arr_arr_a[n8];
                        nArray[2] = nArray[2] + (a3.a((int)a3.var_short_b, (int)a3.var_short_c, null) << 1);
                    } else if (this.var_short_a >= 15 && this.var_byte_arr_e[a3.var_byte_e] != this.var_byte_arr_e[this.var_byte_b] && this.int_a(-1, -1, a3.var_byte_e) < 4 && this.int_a(-1, -1, this.var_byte_b) >= 8) {
                        int[] nArray = this.var_int_arr_arr_a[n8];
                        nArray[2] = nArray[2] + (a3.a((int)a3.var_short_b, (int)a3.var_short_c, null) << 1);
                    } else if (a3.var_byte_e != this.var_byte_b) {
                        a3 = null;
                    }
                }
            } else if (this.var_aeii_a_arr_b[this.var_byte_b] != null) {
                a3 = this.var_aeii_a_arr_c[n7];
            }
            if (a3 != null) {
                this.var_int_arr_arr_a[n8][0] = a3.var_short_b;
                this.var_int_arr_arr_a[n8][1] = a3.var_short_c;
                if (a3.var_byte_e == this.var_byte_b) {
                    a[] aArray = a3.a(a3.var_short_b, (int)a3.var_short_c, 1, 5, (byte)0);
                    for (n6 = 0; n6 < aArray.length; ++n6) {
                        if (aArray[n6].var_byte_f == 4) continue;
                        int[] nArray = this.var_int_arr_arr_a[n8];
                        nArray[2] = nArray[2] + aArray[n6].a((int)aArray[n6].var_short_b, (int)aArray[n6].var_short_c, a3);
                    }
                }
                if (this.var_int_arr_arr_a[n8][2] > 0) {
                    int[] nArray = this.var_int_arr_arr_a[n8];
                    nArray[4] = nArray[4] + a3.a((int)a3.var_short_b, (int)a3.var_short_c, null);
                    int[] nArray2 = this.var_int_arr_arr_a[n8];
                    nArray2[4] = nArray2[4] + a3.k;
                    if (this.var_int_arr_arr_a[n8][2] > this.ar) {
                        this.ar = this.var_int_arr_arr_a[n8][2];
                    }
                    this.var_int_arr_arr_a[n8][3] = Math.abs(a3.var_short_b - a2.var_short_b) + Math.abs(a3.var_short_c - a2.var_short_c);
                    if (this.var_int_arr_arr_a[n8][3] <= 0) {
                        this.var_int_arr_arr_a[n8][3] = 1;
                    }
                    if (this.var_int_arr_arr_a[n8][3] < n9) {
                        n9 = this.var_int_arr_arr_a[n8][3];
                    }
                } else {
                    this.var_int_arr_arr_a[n8][2] = -6666;
                }
            } else {
                this.var_int_arr_arr_a[n8][2] = -6666;
            }
            ++n8;
        }
        n7 = 666;
        int n10 = 666;
        int n11 = -1;
        n6 = -1;
        for (n5 = 0; n5 < this.var_byte_arr_arr_e.length; ++n5) {
            n4 = this.var_byte_arr_arr_e[n5][0];
            n3 = this.var_byte_arr_arr_e[n5][1];
            n2 = this.byte_a(n4, n3);
            n = this.boolean_b(n4, n3, a2.var_byte_e);
            this.var_int_arr_arr_a[n8][2] = -6666;
            if (n != 0 || this.var_aeii_a_arr_d[n5] != null) {
                a[] aArray = a2.a(n4, n3, 1, 5, (byte)0);
                this.var_int_arr_arr_a[n8][0] = n4;
                this.var_int_arr_arr_a[n8][1] = n3;
                this.var_int_arr_arr_a[n8][2] = 0;
                for (int i = 0; i < aArray.length; ++i) {
                    if (aArray[i].var_byte_f == 4 || !(this.var_aeii_a_arr_d[n5] != null && n == 0 || n2 == 8 && aArray[i].a((short)8)) && (n2 != 9 || !aArray[i].a((short)16))) continue;
                    int[] nArray = this.var_int_arr_arr_a[n8];
                    nArray[2] = nArray[2] + aArray[i].a((int)aArray[i].var_short_b, (int)aArray[i].var_short_c, null);
                }
                if (this.var_int_arr_arr_a[n8][2] == 0) {
                    if (this.var_aeii_a_arr_d[n5] != null && n == 0) {
                        this.var_int_arr_arr_a[n8][2] = 100;
                        int[] nArray = this.var_int_arr_arr_a[n8];
                        nArray[4] = nArray[4] + 2000;
                    } else {
                        this.var_int_arr_arr_a[n8][2] = -6666;
                    }
                }
                if (this.var_int_arr_arr_a[n8][2] != -6666) {
                    int[] nArray = this.var_int_arr_arr_a[n8];
                    nArray[4] = nArray[4] + this.var_int_arr_m[n5];
                    if (this.var_int_arr_arr_a[n8][2] > this.ar) {
                        this.ar = this.var_int_arr_arr_a[n8][2];
                    }
                    this.var_int_arr_arr_a[n8][3] = Math.abs(n4 - a2.var_short_b) + Math.abs(n3 - a2.var_short_c);
                    if (this.var_int_arr_arr_a[n8][3] <= 0) {
                        this.var_int_arr_arr_a[n8][3] = 1;
                    }
                    if (this.var_int_arr_arr_a[n8][3] < n9) {
                        n9 = this.var_int_arr_arr_a[n8][3];
                    }
                }
            }
            if (this.boolean_a(n4, n3, (int)this.var_byte_arr_e[a2.var_byte_e])) {
                int n12;
                a a4 = this.aeii_a_a(n4, n3, (byte)0);
                if ((a4 == null || a4.var_byte_e == a2.var_byte_e) && (n12 = Math.abs(n4 - a2.var_short_b) + Math.abs(n3 - a2.var_short_c)) < n10) {
                    n6 = n5;
                    n10 = n12;
                }
            } else if ((this.var_aeii_a_arr_d[n5] == null || this.var_aeii_a_arr_d[n5] == a2) && (n2 == 8 && a2.a((short)8) || n2 == 9 && a2.a((short)16))) {
                int n13 = Math.abs(n4 - a2.var_short_b) + Math.abs(n3 - a2.var_short_c);
                if (n13 < n7) {
                    n11 = n5;
                    n7 = n13;
                }
                a a5 = this.aeii_a_a(n4, n3, (byte)0);
                if (n13 < n10 && this.var_byte_arr_arr_b[n4][n3] > 0 && (a5 == null || a5.var_byte_e == a2.var_byte_e)) {
                    n6 = n5;
                    n10 = n13;
                }
            }
            ++n8;
        }
        if (a2.var_int_h < 50 && n6 != -1) {
            if (n6 == n11) {
                this.aw = n11;
            }
            this.var_aeii_a_arr_d[n6] = a2;
            this.at = this.var_byte_arr_arr_e[n6][0];
            this.au = this.var_byte_arr_arr_e[n6][1];
            this.a(this.var_byte_arr_arr_g, 0);
            aeii.a.a(this.var_byte_arr_arr_g, this.at, this.au, 10, -1, a2.var_byte_d, this.var_byte_b, false);
        } else if (this.var_aeii_a_arr_b[this.var_byte_b] != null && n11 != -1 && (a2.a((short)8) || a2.a((short)16))) {
            this.aw = n11;
            this.var_aeii_a_arr_d[n11] = a2;
            this.at = this.var_byte_arr_arr_e[n11][0];
            this.au = this.var_byte_arr_arr_e[n11][1];
            this.a(this.var_byte_arr_arr_g, 0);
            aeii.a.a(this.var_byte_arr_arr_g, this.at, this.au, 10, -1, a2.var_byte_d, this.var_byte_b, false);
        } else {
            n5 = -1;
            n4 = -6666;
            for (n3 = 0; n3 < n8; ++n3) {
                if (this.var_int_arr_arr_a[n3][2] <= -6666) continue;
                if (this.var_int_arr_arr_a[n3][2] > 0) {
                    this.var_int_arr_arr_a[n3][2] = this.var_int_arr_arr_a[n3][2] * n9 / this.var_int_arr_arr_a[n3][3];
                }
                int[] nArray = this.var_int_arr_arr_a[n3];
                nArray[2] = nArray[2] - this.var_int_arr_arr_a[n3][4];
                if (this.var_int_arr_arr_a[n3][2] <= n4) continue;
                n4 = this.var_int_arr_arr_a[n3][2];
                n5 = n3;
            }
            if (n5 != -1) {
                n3 = a2.a((int)a2.var_short_b, (int)a2.var_short_c, null);
                if (n5 < this.var_aeii_a_arr_c.length) {
                    this.var_aeii_a_arr_c[n5].k += n3;
                } else if (n5 < this.var_aeii_a_arr_b.length + this.var_aeii_a_arr_c.length) {
                    this.var_aeii_a_arr_b[n5 - this.var_aeii_a_arr_c.length].k += n3;
                } else {
                    int n14 = this.av = n5 - this.var_aeii_a_arr_b.length - this.var_aeii_a_arr_c.length;
                    this.var_int_arr_m[n14] = this.var_int_arr_m[n14] + n3;
                }
                this.at = this.var_int_arr_arr_a[n5][0];
                this.au = this.var_int_arr_arr_a[n5][1];
                this.a(this.var_byte_arr_arr_g, 0);
                aeii.a.a(this.var_byte_arr_arr_g, this.at, this.au, 10, -1, a2.var_byte_d, this.var_byte_b, false);
            }
        }
        n5 = -10000;
        n3 = this.var_byte_arr_arr_b.length;
        for (n4 = 0; n4 < n3; ++n4) {
            n = this.var_byte_arr_arr_b[n4].length;
            for (n2 = 0; n2 < n; ++n2) {
                int n15;
                a a6;
                if (this.var_byte_arr_arr_b[n4][n2] <= 0 || (a6 = this.aeii_a_a(n4, n2, (byte)0)) != null && a6 != a2 && (this.var_aeii_a_m != null || a6.var_byte_e != a2.var_byte_e || a6.var_byte_f != 0)) continue;
                if (!a2.a((short)512) || a6 == a2) {
                    a[] aArray = a2.a(n4, n2, (byte)0);
                    for (int i = 0; i < aArray.length; ++i) {
                        int n16 = this.a(a2, n4, n2, aArray[i], null);
                        if (n16 <= n5) continue;
                        this.var_aeii_a_l = null;
                        this.var_aeii_a_k = aArray[i];
                        n5 = n16;
                        this.ao = n4;
                        this.ap = n2;
                    }
                }
                if (a2.a((short)32)) {
                    this.var_aeii_a_arr_a = a2.a(n4, n2, (byte)1);
                    for (int i = 0; i < this.var_aeii_a_arr_a.length; ++i) {
                        int n17 = this.a(a2, n4, n2, null, this.var_aeii_a_arr_a[i]);
                        if (n17 <= n5) continue;
                        this.var_aeii_a_k = null;
                        this.var_aeii_a_l = this.var_aeii_a_arr_a[i];
                        n5 = n17;
                        this.ao = n4;
                        this.ap = n2;
                    }
                }
                if ((n15 = this.a(a2, n4, n2, null, null)) <= n5) continue;
                this.var_aeii_a_k = null;
                this.var_aeii_a_l = null;
                n5 = n15;
                this.ao = n4;
                this.ap = n2;
            }
        }
        if (this.aw != -1) {
            this.var_byte_arr_q[this.aw] = (byte)(10 - this.var_byte_arr_arr_g[this.ao][this.ap]);
        }
        this.var_aeii_a_m = null;
        a a7 = this.aeii_a_a(this.ao, this.ap, (byte)0);
        if (a7 != null && a7 != a2) {
            this.var_aeii_a_m = a7;
            this.var_byte_k = 0;
            return;
        }
        this.d(a2.var_short_b, a2.var_short_c);
        this.var_aeii_a_j = a2;
        this.var_byte_k = (byte)3;
    }

    private int a(a a2, int n, int n2, a a3, a a4) {
        int n3;
        int n4;
        int n5 = 0;
        if (this.aw != -1 && this.var_aeii_a_arr_b[a2.var_byte_e] != null) {
            if (this.at != -1) {
                if (this.var_byte_arr_arr_g[n][n2] > 0) {
                    n5 = 100 + 100 * this.var_byte_arr_arr_g[n][n2] / 10;
                } else {
                    n4 = Math.abs(this.at - a2.var_short_b) + Math.abs(this.au - a2.var_short_c);
                    n3 = Math.abs(this.at - n) + Math.abs(this.au - n2);
                    n5 = 0 + 100 * (n4 - n3) / (a2.var_int_g - 1);
                    if (var_byte_arr_b[this.byte_a(n, n2)] <= 1) {
                        n5 += 20;
                    }
                }
            }
            if (a3 == null && !this.boolean_a(n, n2, (int)this.var_byte_arr_e[a2.var_byte_e])) {
                if (a2.a((short)16) && this.byte_a(n, n2) == 9) {
                    n5 += 300;
                } else if (a2.a((short)8) && (this.byte_a(n, n2) == 8 || this.var_byte_arr_arr_a[n][n2] == 27)) {
                    n5 += 200;
                }
            }
        }
        switch (a2.var_byte_d) {
            case 2: {
                if (this.byte_a(n, n2) != 5) break;
                n5 += 25;
                break;
            }
            case 3: {
                if (a4 == null) break;
                n5 += 100;
                break;
            }
            case 4: {
                a[] aArray = a2.a(n, n2, 1, 2, (byte)2);
                if (a4 == null) break;
                n5 += 25 * aArray.length;
            }
        }
        if (a3 != null) {
            if (a3.var_byte_f == 4) {
                n4 = this.int_a((int)a3.var_short_b, (int)a3.var_short_c);
                n3 = this.int_b(a3.var_short_b, a3.var_short_c);
                if (n4 != 0 && n3 != -1 && this.var_aeii_a_arr_d[n3] == null) {
                    n5 += a2.a(n, n2, a3) / 2;
                }
            } else {
                n5 = !a3.boolean_a(n, n2) ? (n5 += a2.a(n, n2, a3) << 1) : (n5 += a2.a(n, n2, a3) * 3 / 2 - a3.a(n, n2, a2));
                if (a3.var_byte_d == 9) {
                    n5 += 25;
                } else if (a3.var_byte_d == 11) {
                    n5 += 100;
                }
            }
        }
        n5 += var_byte_arr_a[this.byte_a(n, n2)];
        if (a2.var_int_h < 100 && this.boolean_a(n, n2, (int)this.var_byte_arr_e[a2.var_byte_e])) {
            n5 += 100 - a2.var_int_h;
        }
        n4 = this.int_b(n, n2);
        if (this.var_byte_arr_arr_g[n][n2] > 0) {
            n3 = this.var_byte_arr_arr_g[n][n2];
            int n6 = 10 - a2.var_int_g / 2;
            if (n3 > n6) {
                n3 = n6;
            }
            n5 += 50 + n3 * 100 / n6;
        } else if (this.at != -1) {
            n3 = Math.abs(this.at - a2.var_short_b) + Math.abs(this.au - a2.var_short_c);
            int n7 = Math.abs(this.at - n) + Math.abs(this.au - n2);
            n5 += 50 * (n3 - n7) / (a2.var_int_g - 1);
        }
        if (n4 != -1 && (a3 = this.var_aeii_a_arr_d[n4]) != null && a3 != a2 && a3.var_byte_f == 0 && this.var_byte_arr_q[n4] < a3.var_int_g) {
            n5 -= 200;
        }
        return n5 + 20 * (Math.abs(n - a2.var_short_b) + Math.abs(n2 - a2.var_short_c)) / (a2.var_int_g - 1);
    }

    private int int_b(int n, int n2) {
        for (int i = 0; i < this.var_byte_arr_arr_e.length; ++i) {
            if (this.var_byte_arr_arr_e[i][0] != n || this.var_byte_arr_arr_e[i][1] != n2) continue;
            return i;
        }
        return -1;
    }

    private void void_d(int n) {
        this.ax = n;
        this.var_boolean_z = true;
    }

    private e a(String string, byte by, byte by2) {
        e e2 = new e(7, 12);
        int n = aeii.c.var_int_b * 3;
        e2.a(string, ((f)this).j, n, by);
        e2.a(0, ((f)this).k - n, 0);
        aeii.f.var_aeii_c_a.a(e2);
        return e2;
    }

    private e a(String string, String string2, int n, int n2) {
        return this.a(string, string2, n, -1, n2);
    }

    private e a(String string, String string2, int n, int n2, int n3) {
        e e2 = new e(10, 12);
        e2.a(string, string2, ((f)this).j, n2);
        e2.a(((f)this).l, n / 2, 3);
        e2.var_aeii_f_a = this;
        e2.var_int_e = n3;
        return e2;
    }

    private void q() {
        this.var_aeii_a_r = null;
        if (this.var_byte_c == 0) {
            this.Q = var_byte_a;
            this.var_boolean_n = true;
            this.var_boolean_m = false;
            this.var_aeii_e_G = this.a(null, this.var_java_lang_String_b, ((f)this).k, 2000);
            this.var_aeii_e_G.a(((f)this).l, ((f)this).m, 3);
            this.var_boolean_q = true;
            this.var_boolean_p = true;
            this.R = 0;
        }
        if (this.var_int_x == 0) {
            this.var_int_n = 0;
            this.var_int_arr_b[0] = 0;
            this.var_int_arr_b[1] = 0;
            aeii.a.var_byte_c = (byte)4;
            this.Y = 2;
            this.void_b(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
            this.d(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
            this.void_a(2, 3, 3);
            aeii.c.void_a(1, 1);
            this.var_boolean_q = false;
            this.var_boolean_d = false;
            this.ay = 0;
            return;
        }
        if (this.var_int_x == 1) {
            this.var_int_n = 1;
            this.var_int_arr_b[0] = 300;
            this.var_int_arr_b[1] = 50;
            aeii.a.var_byte_c = (byte)4;
            this.Y = 2;
            this.var_aeii_a_arr_b[0].b(2);
            this.void_b(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
            this.d(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
            this.aeii_a_a(7, 12, (byte)0).a(7, 10, false);
            this.aeii_a_a(8, 11, (byte)0).a(8, 9, false);
            this.aeii_a_a(9, 12, (byte)0).a(9, 10, false);
            this.e(7, 3);
            aeii.f.var_aeii_c_a.a(this.var_aeii_e_G);
            return;
        }
        if (this.var_int_x == 2) {
            this.var_int_n = 0;
            this.var_int_arr_b[0] = 0;
            this.var_int_arr_b[1] = 0;
            aeii.a.var_byte_c = (byte)4;
            this.var_int_arr_b[0] = 0;
            this.void_b(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
            this.d(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
            this.var_aeii_a_n = this.aeii_a_a(8, 17, (byte)0);
            this.var_aeii_a_o = this.aeii_a_a(8, 18, (byte)0);
            this.var_aeii_a_p = this.aeii_a_a(8, 19, (byte)0);
            this.var_aeii_a_n.a(8, 15, false);
            this.var_aeii_a_o.a(8, 15, false);
            this.var_aeii_a_p.a(8, 15, false);
            this.var_aeii_a_arr_b[0].a(8, 14, false);
            this.var_boolean_d = false;
            aeii.f.var_aeii_c_a.a(this.var_aeii_e_G);
            this.ay = 0;
            return;
        }
        if (this.var_int_x == 3) {
            this.var_int_n = 7;
            aeii.a.var_byte_c = (byte)4;
            this.var_int_arr_b[0] = 400;
            this.var_int_arr_b[1] = 400;
            this.var_aeii_a_n = aeii.a.a((byte)0, (byte)0, -1, 5);
            this.var_aeii_a_o = aeii.a.a((byte)2, (byte)0, -2, 5);
            this.var_aeii_a_p = aeii.a.a((byte)3, (byte)0, -3, 5);
            this.var_aeii_a_n.a(3, 4, false);
            this.var_aeii_a_o.a(4, 4, false);
            this.var_aeii_a_p.a(2, 4, false);
            this.var_aeii_a_arr_b[0].a(3, 3, false);
            this.void_b(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
            this.d(3, 3);
            this.var_aeii_a_j = this.var_aeii_a_arr_b[0];
            this.void_a(3, 3, 3);
            aeii.c.void_a(1, 1);
            this.var_boolean_q = false;
            this.var_boolean_d = false;
            this.ay = 0;
            return;
        }
        if (this.var_int_x == 4) {
            this.var_int_n = 0;
            this.var_int_arr_b[0] = 0;
            this.var_int_arr_b[1] = 0;
            this.void_a(this.var_aeii_a_arr_b[0].m + 12, this.var_aeii_a_arr_b[0].n + 12);
            this.d(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
            aeii.a.var_byte_c = (byte)4;
            a a2 = this.aeii_a_a(11, 2, (byte)0);
            a2.void_a(11, -3);
            a2.a(11, 2, false);
            a2 = this.aeii_a_a(10, 1, (byte)0);
            a2.void_a(10, -5);
            a2.a(10, 1, false);
            a2 = this.aeii_a_a(11, 1, (byte)0);
            a2.void_a(11, -5);
            a2.a(11, 1, false);
            a2 = this.aeii_a_a(12, 1, (byte)0);
            a2.void_a(12, -5);
            a2.a(12, 1, false);
            a2 = this.aeii_a_a(11, 0, (byte)0);
            a2.void_a(11, -7);
            a2.a(11, 0, false);
            a2 = this.aeii_a_a(12, 0, (byte)0);
            a2.void_a(12, -7);
            a2.a(12, 0, false);
            aeii.f.var_aeii_c_a.a(this.var_aeii_e_G);
            this.var_boolean_d = false;
            this.ay = 0;
            return;
        }
        if (this.var_int_x == 5) {
            this.var_int_n = 7;
            this.var_int_arr_b[0] = 600;
            this.var_int_arr_b[1] = 600;
            this.var_aeii_a_arr_b[0].b(2);
            this.void_b(5, 0);
            this.d(5, 0);
            this.Y = 4;
            this.e(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
            aeii.f.var_aeii_c_a.a(this.var_aeii_e_G);
            this.var_boolean_d = false;
            this.ay = 0;
            return;
        }
        if (this.var_int_x == 6) {
            this.var_int_n = 8;
            aeii.a.var_byte_c = (byte)4;
            this.var_int_arr_b[0] = 400;
            this.var_int_arr_b[1] = 600;
            a a3 = aeii.a.a((byte)0, (byte)0, 13, -1);
            a a4 = aeii.a.a((byte)1, (byte)0, 13, -1);
            a a5 = aeii.a.a((byte)3, (byte)0, 13, -1);
            a a6 = aeii.a.a((byte)11, (byte)0, 13, -1);
            this.var_aeii_a_arr_b[0].var_aeii_a_a = a3;
            a3.var_aeii_a_a = a4;
            a4.var_aeii_a_a = a5;
            a5.var_aeii_a_a = a6;
            this.var_aeii_a_arr_b[0].b(this.var_byte_arr_arr_b);
            this.var_aeii_a_arr_b[0].a(14, 3, true);
            this.void_b(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
            this.d(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
            this.var_aeii_a_j = this.var_aeii_a_arr_b[0];
            aeii.f.var_aeii_c_a.a(this.var_aeii_e_G);
            this.var_boolean_d = false;
            this.ay = 0;
            return;
        }
        if (this.var_int_x == 7) {
            a a7;
            this.var_int_n = 8;
            aeii.a.var_byte_c = (byte)4;
            this.var_int_arr_b[0] = 800;
            this.var_int_arr_b[1] = 200;
            a a8 = this.aeii_a_a(7, 4, (byte)0);
            a8.b(3);
            this.var_aeii_a_arr_b[1] = a8;
            this.var_aeii_a_arr_b[0] = a7 = this.aeii_a_a(8, 15, (byte)0);
            a7.b(0);
            this.aeii_a_a(6, 15, (byte)0).b(2);
            this.void_a(this.var_aeii_a_arr_b[0].m + 12, this.var_aeii_a_arr_b[0].n + 12);
            this.d(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
            this.void_a(4, 3, 3);
            aeii.c.void_a(1, 1);
            this.var_boolean_q = false;
            this.var_boolean_d = false;
            this.ay = 0;
        }
    }

    private void void_e(int n) {
        String[] stringArray;
        InputStream inputStream = aeii.c.java_io_InputStream_a("m" + n + ".script");
        if (inputStream == null) {
            this.var_java_lang_String_arr_arr_arr_a = null;
            return;
        }
        Vector vector = new Vector();
        Vector vector2 = null;
        while ((stringArray = aeii.c.a(inputStream)) != null) {
            int n2 = stringArray.indexOf(59);
            if (n2 >= 0) {
                stringArray = stringArray.substring(0, n2);
            }
            if ((stringArray = stringArray.trim()).length() == 0) continue;
            if ((stringArray = aeii.c.a((String)stringArray, ' '))[0].charAt(0) == '@') {
                stringArray[0] = stringArray[0].substring(1);
                if (!stringArray[0].equalsIgnoreCase("case")) continue;
                int n3 = Integer.parseInt(stringArray[1]);
                while (vector.size() <= n3) {
                    vector.addElement(new Vector());
                }
                vector2 = (Vector)vector.elementAt(n3);
                continue;
            }
            vector2.addElement(stringArray);
        }
        this.var_java_lang_String_arr_arr_arr_a = new String[vector.size()][][];
        for (int i = 0; i < vector.size(); ++i) {
            vector2 = (Vector)vector.elementAt(i);
            this.var_java_lang_String_arr_arr_arr_a[i] = new String[vector2.size()][];
            vector2.copyInto((Object[])this.var_java_lang_String_arr_arr_arr_a[i]);
        }
    }

    private void r() {
        String[][] stringArray = this.var_java_lang_String_arr_arr_arr_a[this.ay];
        int n = 0;
        int n2 = -1;
        a a2 = null;
        while (n >= 0 && n < stringArray.length) {
            String[] stringArray2;
            if ((stringArray2 = stringArray[n++])[0].equalsIgnoreCase("Jump")) {
                n += Integer.parseInt(stringArray2[1]);
                continue;
            }
            if (stringArray2[0].equalsIgnoreCase("Alt")) {
                n2 = Integer.parseInt(stringArray2[1]);
                if (n2 == 0) {
                    n2 = -1;
                    continue;
                }
                n2 += n;
                continue;
            }
            if (stringArray2[0].equalsIgnoreCase("Test")) {
                int n3 = 0;
                int n4 = 0;
                String string = null;
                if (stringArray2[1].equalsIgnoreCase("CurrentPlayer")) {
                    n3 = this.var_byte_b;
                } else if (stringArray2[1].equalsIgnoreCase("CurrentTurn")) {
                    n3 = this.var_short_a;
                } else if (stringArray2[1].equalsIgnoreCase("GameState")) {
                    n3 = this.var_byte_d;
                } else if (stringArray2[1].equalsIgnoreCase("AlphaWindow")) {
                    n3 = this.aa;
                } else if (stringArray2[1].equalsIgnoreCase("StatusBarOffset")) {
                    n3 = this.Q;
                } else if (stringArray2[1].equalsIgnoreCase("UnitFinishedMove")) {
                    n3 = this.var_aeii_a_a != null ? 1 : 0;
                } else if (stringArray2[1].equalsIgnoreCase("CountUnits")) {
                    n3 = this.int_a(Integer.parseInt(stringArray2[2]), Integer.parseInt(stringArray2[3]), (byte)Integer.parseInt(stringArray2[4]));
                    n4 = Integer.parseInt(stringArray2[6]);
                    string = stringArray2[5];
                }
                if (string == null) {
                    n4 = Integer.parseInt(stringArray2[3]);
                    string = stringArray2[2];
                }
                if (!((stringArray2 = string).equals("=") ? n3 == n4 : (stringArray2.equals("!=") ? n3 != n4 : (stringArray2.equals(">") ? n3 > n4 : (stringArray2.equals(">=") ? n3 >= n4 : (stringArray2.equals("<") ? n3 < n4 : (stringArray2.equals("<=") ? n3 <= n4 : false))))))) {
                    n = n2;
                }
                n2 = -1;
                continue;
            }
            if (stringArray2[0].equalsIgnoreCase("Wait")) {
                this.void_d(Integer.parseInt(stringArray2[1]));
                continue;
            }
            if (stringArray2[0].equalsIgnoreCase("NextState")) {
                ++this.ay;
                continue;
            }
            if (stringArray2[0].equalsIgnoreCase("ShowMapName")) {
                aeii.f.var_aeii_c_a.a(this.var_aeii_e_G);
                continue;
            }
            if (stringArray2[0].equalsIgnoreCase("StartPlay")) {
                this.t();
                continue;
            }
            if (stringArray2[0].equalsIgnoreCase("CompleteMission")) {
                this.u();
                continue;
            }
            if (stringArray2[0].equalsIgnoreCase("SetFadeEnabled")) {
                this.var_boolean_q = "true".equalsIgnoreCase(stringArray2[1]);
                continue;
            }
            if (stringArray2[0].equalsIgnoreCase("SetCursorVisible")) {
                this.var_boolean_d = "true".equalsIgnoreCase(stringArray2[1]);
                continue;
            }
            if (stringArray2[0].equalsIgnoreCase("SetGameActive")) {
                this.var_boolean_m = "true".equalsIgnoreCase(stringArray2[1]);
                continue;
            }
            if (stringArray2[0].equalsIgnoreCase("SetFadeValue")) {
                this.R = Integer.parseInt(stringArray2[1]);
                continue;
            }
            if (stringArray2[0].equalsIgnoreCase("SetMapStepMax")) {
                this.Y = Integer.parseInt(stringArray2[1]);
                continue;
            }
            if (stringArray2[0].equalsIgnoreCase("SetUnitSpeed")) {
                if (stringArray2[1].equalsIgnoreCase("slow")) {
                    aeii.a.var_byte_c = aeii.a.var_byte_b;
                    continue;
                }
                if (stringArray2[1].equalsIgnoreCase("fast")) {
                    aeii.a.var_byte_c = aeii.a.var_byte_a;
                    continue;
                }
                aeii.a.var_byte_c = (byte)Integer.parseInt(stringArray2[1]);
                continue;
            }
            if (stringArray2[0].equalsIgnoreCase("ShowDialog")) {
                this.a(aeii.f.java_lang_String_a(Integer.parseInt(stringArray2[1])), (byte)Integer.parseInt(stringArray2[2]), (byte)Integer.parseInt(stringArray2[3]));
                continue;
            }
            if (stringArray2[0].equalsIgnoreCase("ShowHelp")) {
                this.var_int_L = Integer.parseInt(stringArray2[1]);
                continue;
            }
            if (stringArray2[0].equalsIgnoreCase("Vibrate")) {
                aeii.c.d(Integer.parseInt(stringArray2[1]));
                continue;
            }
            if (stringArray2[0].equalsIgnoreCase("MoveMapAndCursor")) {
                if (stringArray2[1].equalsIgnoreCase("king")) {
                    a a3 = this.var_aeii_a_arr_b[Integer.parseInt(stringArray2[2])];
                    this.e(a3.var_short_b, a3.var_short_c);
                    continue;
                }
                this.e(Integer.parseInt(stringArray2[1]), Integer.parseInt(stringArray2[2]));
                continue;
            }
            if (stringArray2[0].equalsIgnoreCase("GetUnitPlotRoute")) {
                this.aeii_a_a(Integer.parseInt(stringArray2[1]), Integer.parseInt(stringArray2[2]), (byte)Integer.parseInt(stringArray2[3])).a(Integer.parseInt(stringArray2[4]), Integer.parseInt(stringArray2[5]), "true".equalsIgnoreCase(stringArray2[6]));
                continue;
            }
            if (stringArray2[0].equalsIgnoreCase("CreateUnitPlotRoute")) {
                aeii.a.a((byte)Integer.parseInt(stringArray2[1]), (byte)Integer.parseInt(stringArray2[2]), Integer.parseInt(stringArray2[3]), Integer.parseInt(stringArray2[4])).a(Integer.parseInt(stringArray2[5]), Integer.parseInt(stringArray2[6]), "true".equalsIgnoreCase(stringArray2[7]), "true".equalsIgnoreCase(stringArray2[8]));
                continue;
            }
            if (stringArray2[0].equalsIgnoreCase("GetUnit")) {
                a2 = this.aeii_a_a(Integer.parseInt(stringArray2[1]), Integer.parseInt(stringArray2[2]), (byte)Integer.parseInt(stringArray2[3]));
                continue;
            }
            if (stringArray2[0].equalsIgnoreCase("RemoveUnit")) {
                a2.void_a();
                continue;
            }
            if (stringArray2[0].equalsIgnoreCase("ScheduleUnitAnimationStop")) {
                a2.a(Integer.parseInt(stringArray2[1]));
                continue;
            }
            if (!stringArray2[0].equalsIgnoreCase("CreateSpriteAtUnit")) continue;
            a.a.a.g g2 = null;
            if (stringArray2[1].equalsIgnoreCase("Smoke")) {
                g2 = this.var_a_a_a_g_e;
            } else if (stringArray2[1].equalsIgnoreCase("Spark")) {
                g2 = this.var_a_a_a_g_f;
            } else if (stringArray2[1].equalsIgnoreCase("RedSpark")) {
                g2 = this.var_a_a_a_g_g;
            }
            this.a(g2, a2.m, a2.n, Integer.parseInt(stringArray2[2]), Integer.parseInt(stringArray2[3]), Integer.parseInt(stringArray2[4]), Integer.parseInt(stringArray2[5]));
        }
    }

    private void s() {
        block288: {
            block292: {
                block299: {
                    int n;
                    int n2;
                    block298: {
                        block297: {
                            block296: {
                                block295: {
                                    block294: {
                                        block293: {
                                            block291: {
                                                block290: {
                                                    block289: {
                                                        if (this.var_byte_n != 0) {
                                                            return;
                                                        }
                                                        if (this.ax > 0 && --this.ax > 0) break block288;
                                                        if (!this.var_boolean_A) break block289;
                                                        if (this.ay == 0) {
                                                            this.var_boolean_g = true;
                                                            this.var_int_G = 0;
                                                            this.var_byte_d = (byte)11;
                                                            this.ay = 1;
                                                            return;
                                                        }
                                                        break block288;
                                                    }
                                                    if (this.var_byte_c != 1) break block290;
                                                    if (this.ay == 100) {
                                                        aeii.c.void_a(var_int_arr_g[this.var_byte_arr_d[this.var_byte_b]], 0);
                                                        this.var_aeii_e_e.a((byte)0, true);
                                                        this.var_aeii_e_e.a((f)null);
                                                        aeii.f.var_aeii_c_a.a(this.var_aeii_e_e);
                                                        ++this.ay;
                                                        return;
                                                    }
                                                    if (this.ay == 101) {
                                                        byte by = -1;
                                                        int n3 = -1;
                                                        int n4 = 1;
                                                        for (int i = 0; i < this.var_byte_f; ++i) {
                                                            if (this.var_byte_arr_m[i] == 2 || (this.var_aeii_a_arr_b[i] == null || this.var_aeii_a_arr_b[i].var_byte_f == 3) && this.int_e(i) == 0) continue;
                                                            n3 = i;
                                                            if (by != -1 && by != this.var_byte_arr_e[i]) {
                                                                n4 = 0;
                                                                break;
                                                            }
                                                            by = this.var_byte_arr_e[i];
                                                        }
                                                        if (n4 != 0) {
                                                            this.var_boolean_m = false;
                                                            this.var_boolean_d = false;
                                                            String string = aeii.f.a(38, "" + (this.var_byte_arr_e[n3] + 1));
                                                            String string2 = aeii.f.a(81, string) + "\n(";
                                                            for (n4 = 0; n4 < this.var_byte_f; ++n4) {
                                                                if (this.var_byte_arr_m[n4] == 2 || this.var_byte_arr_e[n4] != this.var_byte_arr_e[n3]) continue;
                                                                string2 = string2 + " " + aeii.f.java_lang_String_a(88 + this.var_byte_arr_d[n4]) + " ";
                                                            }
                                                            string2 = string2 + ")";
                                                            e e2 = this.a(null, string2, this.var_int_b, this.var_int_p, -1);
                                                            e2.a(this);
                                                            aeii.f.var_aeii_c_a.a(e2);
                                                            if (this.var_byte_arr_m[n3] == 1) {
                                                                aeii.c.void_a(6, 1);
                                                            } else {
                                                                aeii.c.void_a(7, 1);
                                                            }
                                                            this.void_d(15);
                                                            ++this.ay;
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                    if (this.ay == 102) {
                                                        this.var_boolean_g = true;
                                                        this.var_int_G = 0;
                                                        this.var_byte_d = (byte)11;
                                                        ++this.ay;
                                                        return;
                                                    }
                                                    break block288;
                                                }
                                                if (this.var_byte_h != 1 || this.var_byte_c != 0 || this.ay == -1) break block288;
                                                if (this.aA != -1) {
                                                    if (!this.boolean_b(this.aA, this.aB)) {
                                                        return;
                                                    }
                                                    this.aA = -1;
                                                    this.aB = -1;
                                                }
                                                if (this.var_byte_d != 11) {
                                                    n2 = 1;
                                                    for (n = 0; n < this.var_int_arr_a[0]; ++n) {
                                                        if (this.var_aeii_a_arr_arr_a[0][n].var_byte_f == 3) continue;
                                                        n2 = 0;
                                                        break;
                                                    }
                                                    if (n2 != 0 && this.int_e(0) == 0) {
                                                        this.v();
                                                        return;
                                                    }
                                                }
                                                if (this.var_java_lang_String_arr_arr_arr_a == null) break block291;
                                                this.r();
                                                break block292;
                                            }
                                            if (this.var_int_x != 0) break block293;
                                            switch (this.ay) {
                                                case 0: {
                                                    ++this.ay;
                                                    break;
                                                }
                                                case 1: {
                                                    aeii.f.var_aeii_c_a.a(this.var_aeii_e_G);
                                                    this.void_d(10);
                                                    ++this.ay;
                                                    break;
                                                }
                                                case 2: {
                                                    this.var_boolean_q = true;
                                                    this.aeii_a_a(0, 8, (byte)0).a(3, 8, false);
                                                    this.aeii_a_a(1, 9, (byte)0).a(4, 9, false);
                                                    this.aeii_a_a(0, 10, (byte)0).a(3, 10, false);
                                                    this.e(5, 9);
                                                    break;
                                                }
                                                case 3: {
                                                    this.e(9, 3);
                                                    this.var_boolean_d = true;
                                                    break;
                                                }
                                                case 4: {
                                                    this.void_d(10);
                                                    this.Y = 12;
                                                    aeii.a.var_byte_c = aeii.a.var_byte_b;
                                                    ++this.ay;
                                                    break;
                                                }
                                                case 5: {
                                                    this.a(aeii.f.java_lang_String_a(221), (byte)2, (byte)4);
                                                    this.void_d(10);
                                                    ++this.ay;
                                                    break;
                                                }
                                                case 6: {
                                                    a a2 = this.aeii_a_a(9, 3, (byte)0);
                                                    aeii.c.d(100);
                                                    a2.a(400);
                                                    this.a(this.var_a_a_a_g_g, a2.m, a2.n, 0, 0, 2, 50);
                                                    this.void_d(10);
                                                    ++this.ay;
                                                    break;
                                                }
                                                case 7: {
                                                    this.a(aeii.f.java_lang_String_a(222), (byte)2, (byte)4);
                                                    ++this.ay;
                                                    break;
                                                }
                                                case 8: {
                                                    a a3 = this.aeii_a_a(9, 3, (byte)0);
                                                    this.a(this.var_a_a_a_g_f, a3.m, a3.n, 0, 0, 1, 50);
                                                    this.a(this.var_a_a_a_g_e, a3.m, a3.n, 0, -3, 1, 100);
                                                    a3.void_a();
                                                    this.void_d(20);
                                                    ++this.ay;
                                                    break;
                                                }
                                                case 9: {
                                                    this.var_boolean_d = false;
                                                    this.e(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
                                                    break;
                                                }
                                                case 10: {
                                                    this.void_d(10);
                                                    ++this.ay;
                                                    break;
                                                }
                                                case 11: {
                                                    this.a(aeii.f.java_lang_String_a(223), (byte)0, (byte)4);
                                                    this.void_d(5);
                                                    ++this.ay;
                                                    break;
                                                }
                                                case 12: {
                                                    this.a(aeii.f.java_lang_String_a(224), (byte)5, (byte)4);
                                                    ++this.ay;
                                                    break;
                                                }
                                                case 13: {
                                                    this.t();
                                                    ++this.ay;
                                                    break;
                                                }
                                                case 14: {
                                                    if (this.Q == 0) {
                                                        this.var_int_L = 0;
                                                        ++this.ay;
                                                        break;
                                                    }
                                                    break block292;
                                                }
                                                case 15: {
                                                    if (this.var_byte_b == 0 && this.var_byte_d == 1 && this.aa == 0) {
                                                        this.var_int_L = 1;
                                                        ++this.ay;
                                                        break;
                                                    }
                                                    break block292;
                                                }
                                                case 16: {
                                                    if (this.var_byte_b == 0 && this.var_aeii_a_a != null) {
                                                        this.var_int_L = 2;
                                                        ++this.ay;
                                                        break;
                                                    }
                                                    break block292;
                                                }
                                                case 17: {
                                                    if (this.var_byte_b == 0 && this.var_aeii_a_a != null) {
                                                        this.var_int_L = 3;
                                                        ++this.ay;
                                                        break;
                                                    }
                                                    break block292;
                                                }
                                                case 18: {
                                                    if (this.int_a(-1, 2, (byte)0) >= 3) {
                                                        this.var_int_L = 4;
                                                        ++this.ay;
                                                        break;
                                                    }
                                                    if (this.var_short_a > 0) {
                                                        ++this.ay;
                                                        break;
                                                    }
                                                    break block292;
                                                }
                                                case 19: {
                                                    if (this.var_short_a >= 2) {
                                                        this.var_int_L = 5;
                                                        ++this.ay;
                                                        break;
                                                    }
                                                    break block292;
                                                }
                                                case 20: {
                                                    this.var_int_L = 6;
                                                    ++this.ay;
                                                    break;
                                                }
                                                case 21: {
                                                    if (this.var_byte_d == 1 && this.var_byte_b == 0) {
                                                        this.var_int_L = 7;
                                                        ++this.ay;
                                                        break;
                                                    }
                                                    break block292;
                                                }
                                                case 22: {
                                                    if (this.int_a(-1, -1, (byte)1) == 0) {
                                                        this.var_boolean_m = false;
                                                        this.void_d(20);
                                                        ++this.ay;
                                                        break;
                                                    }
                                                    break block292;
                                                }
                                                case 23: {
                                                    this.e(1, 1);
                                                    break;
                                                }
                                                case 24: {
                                                    aeii.a.a((byte)1, (byte)1, 1, 1).a(1, 2, false, true);
                                                    this.void_d(10);
                                                    ++this.ay;
                                                    break;
                                                }
                                                case 25: {
                                                    this.e(10, 10);
                                                    break;
                                                }
                                                case 26: {
                                                    aeii.a.a((byte)0, (byte)1, 10, 10).a(10, 9, false, true);
                                                    this.void_d(10);
                                                    ++this.ay;
                                                    break;
                                                }
                                                case 27: {
                                                    this.e(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
                                                    break;
                                                }
                                                case 28: {
                                                    this.a(aeii.f.java_lang_String_a(225), (byte)5, (byte)4);
                                                    this.void_d(5);
                                                    ++this.ay;
                                                    break;
                                                }
                                                case 29: {
                                                    this.a(aeii.f.java_lang_String_a(226), (byte)0, (byte)4);
                                                    this.var_boolean_m = true;
                                                    ++this.ay;
                                                    break;
                                                }
                                                case 30: {
                                                    if (this.int_a(-1, -1, (byte)1) == 0 && this.var_byte_d == 0) {
                                                        this.var_boolean_m = false;
                                                        this.var_boolean_d = false;
                                                        this.void_d(30);
                                                        ++this.ay;
                                                        break;
                                                    }
                                                    break block292;
                                                }
                                                case 31: {
                                                    this.var_boolean_o = true;
                                                    this.R = 0;
                                                    this.void_d(20);
                                                    ++this.ay;
                                                    break;
                                                }
                                                case 32: {
                                                    this.a(aeii.f.java_lang_String_a(227), (byte)2, (byte)4);
                                                    this.void_d(10);
                                                    ++this.ay;
                                                    break;
                                                }
                                                case 33: {
                                                    this.a(aeii.f.java_lang_String_a(228), (byte)0, (byte)4);
                                                    this.void_d(5);
                                                    ++this.ay;
                                                    break;
                                                }
                                                case 34: {
                                                    this.a(aeii.f.java_lang_String_a(229), (byte)2, (byte)4);
                                                    this.void_d(5);
                                                    ++this.ay;
                                                    break;
                                                }
                                                case 35: {
                                                    this.a(aeii.f.java_lang_String_a(230), (byte)0, (byte)4);
                                                    this.void_d(15);
                                                    ++this.ay;
                                                    break;
                                                }
                                                case 36: {
                                                    this.u();
                                                }
                                            }
                                            break block292;
                                        }
                                        if (this.var_int_x != 1) break block294;
                                        switch (this.ay) {
                                            case 1: {
                                                this.void_d(10);
                                                ++this.ay;
                                                break;
                                            }
                                            case 2: {
                                                this.Y = 4;
                                                this.e(12, 3);
                                                break;
                                            }
                                            case 3: {
                                                this.a(aeii.f.java_lang_String_a(231), (byte)1, (byte)4);
                                                this.void_d(10);
                                                ++this.ay;
                                                break;
                                            }
                                            case 4: {
                                                this.a(aeii.f.java_lang_String_a(232), (byte)3, (byte)4);
                                                this.e(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
                                                break;
                                            }
                                            case 5: {
                                                this.a(aeii.f.java_lang_String_a(233), (byte)5, (byte)4);
                                                this.e(7, 3);
                                                break;
                                            }
                                            case 6: {
                                                aeii.a.var_byte_c = (byte)2;
                                                this.var_aeii_a_n = aeii.a.a((byte)0, (byte)1, 7, 3);
                                                this.var_aeii_a_o = aeii.a.a((byte)11, (byte)1, 7, 3);
                                                this.var_aeii_a_p = aeii.a.a((byte)0, (byte)1, 7, 3);
                                                this.var_aeii_a_n.var_aeii_a_a = this.var_aeii_a_o;
                                                this.var_aeii_a_o.var_aeii_a_a = this.var_aeii_a_p;
                                                this.var_aeii_a_n.a(6, -2, false);
                                                this.void_d(30);
                                                ++this.ay;
                                                break;
                                            }
                                            case 7: {
                                                if (this.var_aeii_a_p.var_short_b == 6 && this.var_aeii_a_p.var_short_c == 1) {
                                                    aeii.a.var_byte_c = (byte)4;
                                                    this.var_aeii_a_q = aeii.a.a((byte)0, (byte)0, 7, 3);
                                                    this.var_aeii_a_q.a(6, 2, false);
                                                    this.var_aeii_a_o.var_aeii_a_a = null;
                                                    this.a(aeii.f.java_lang_String_a(234), (byte)2, (byte)4);
                                                    ++this.ay;
                                                    break;
                                                }
                                                break block292;
                                            }
                                            case 8: {
                                                if (this.var_aeii_a_q.var_byte_f != 1) {
                                                    aeii.c.d(100);
                                                    this.var_aeii_a_q.a(400);
                                                    this.a(this.var_a_a_a_g_g, this.var_aeii_a_q.m, this.var_aeii_a_q.n, 0, 0, 2, 50);
                                                    this.void_d(10);
                                                    ++this.ay;
                                                    break;
                                                }
                                                break block292;
                                            }
                                            case 9: {
                                                this.a(aeii.f.java_lang_String_a(235), (byte)2, (byte)4);
                                                this.void_d(5);
                                                ++this.ay;
                                                break;
                                            }
                                            case 10: {
                                                this.a(this.var_a_a_a_g_e, this.var_aeii_a_q.m, this.var_aeii_a_q.n, 0, -3, 1, 100);
                                                this.a(this.var_a_a_a_g_f, this.var_aeii_a_q.m, this.var_aeii_a_q.n, 0, 0, 1, 50);
                                                this.var_aeii_a_q.void_a();
                                                this.var_aeii_a_q = null;
                                                this.void_d(15);
                                                ++this.ay;
                                                break;
                                            }
                                            case 11: {
                                                aeii.a.var_byte_c = aeii.a.var_byte_b;
                                                this.var_aeii_a_n.void_a();
                                                this.var_aeii_a_o.void_a();
                                                this.var_aeii_a_n = null;
                                                this.var_aeii_a_o = null;
                                                this.var_aeii_a_p = null;
                                                this.Y = 12;
                                                this.e(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
                                                break;
                                            }
                                            case 12: {
                                                this.a(aeii.f.java_lang_String_a(236), (byte)5, (byte)4);
                                                ++this.ay;
                                                break;
                                            }
                                            case 13: {
                                                this.a(aeii.f.java_lang_String_a(237), (byte)1, (byte)4);
                                                ++this.ay;
                                                break;
                                            }
                                            case 14: {
                                                this.e(3, 5);
                                                break;
                                            }
                                            case 15: {
                                                this.a(aeii.f.java_lang_String_a(238), (byte)5, (byte)4);
                                                ++this.ay;
                                                break;
                                            }
                                            case 16: {
                                                this.e(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
                                                break;
                                            }
                                            case 17: {
                                                this.var_int_L = 8;
                                                ++this.ay;
                                                break;
                                            }
                                            case 18: {
                                                this.var_int_L = 9;
                                                ++this.ay;
                                                break;
                                            }
                                            case 19: {
                                                this.t();
                                                ++this.ay;
                                                break;
                                            }
                                            case 20: {
                                                if (this.var_byte_d == 9 && this.var_byte_b == 0) {
                                                    this.var_int_L = 10;
                                                    ++this.ay;
                                                    break;
                                                }
                                                break block292;
                                            }
                                            case 21: {
                                                this.var_int_L = 11;
                                                ++this.ay;
                                                break;
                                            }
                                            case 22: {
                                                if (this.var_aeii_a_arr_b[0].var_byte_f == 3 || this.var_aeii_a_arr_b[1].var_byte_f == 3) {
                                                    this.var_int_L = 12;
                                                    ++this.ay;
                                                    break;
                                                }
                                                break block292;
                                            }
                                            case 23: {
                                                if (this.int_a(-1, -1, (byte)1) == 0 && this.int_e(1) == 0) {
                                                    this.var_boolean_m = false;
                                                    this.void_d(20);
                                                    ++this.ay;
                                                    break;
                                                }
                                                break block292;
                                            }
                                            case 24: {
                                                this.e(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
                                                break;
                                            }
                                            case 25: {
                                                this.a(aeii.f.java_lang_String_a(239), (byte)1, (byte)4);
                                                ++this.ay;
                                                break;
                                            }
                                            case 26: {
                                                this.a(aeii.f.java_lang_String_a(240), (byte)5, (byte)4);
                                                this.void_d(10);
                                                ++this.ay;
                                                break;
                                            }
                                            case 27: {
                                                this.u();
                                            }
                                        }
                                        break block292;
                                    }
                                    if (this.var_int_x != 2) break block295;
                                    block68 : switch (this.ay) {
                                        case 0: {
                                            if (this.var_aeii_a_n.var_byte_f != 1) {
                                                this.var_aeii_a_n.a(7, 14, false);
                                                ++this.ay;
                                                break;
                                            }
                                            break block292;
                                        }
                                        case 1: {
                                            if (this.var_aeii_a_o.var_byte_f != 1) {
                                                this.var_aeii_a_o.a(7, 15, false);
                                                this.void_d(20);
                                                ++this.ay;
                                                break;
                                            }
                                            break block292;
                                        }
                                        case 2: {
                                            aeii.a.var_byte_c = aeii.a.var_byte_b;
                                            this.var_aeii_a_n = null;
                                            this.var_aeii_a_o = null;
                                            this.var_aeii_a_p = null;
                                            this.a(aeii.f.java_lang_String_a(241), (byte)5, (byte)4);
                                            this.void_d(5);
                                            ++this.ay;
                                            break;
                                        }
                                        case 3: {
                                            this.a(aeii.f.java_lang_String_a(242), (byte)0, (byte)4);
                                            this.void_d(5);
                                            ++this.ay;
                                            break;
                                        }
                                        case 4: {
                                            this.a(aeii.f.java_lang_String_a(243), (byte)5, (byte)4);
                                            this.void_d(5);
                                            ++this.ay;
                                            break;
                                        }
                                        case 5: {
                                            this.var_int_L = 14;
                                            ++this.ay;
                                            break;
                                        }
                                        case 6: {
                                            this.t();
                                            ++this.ay;
                                            break;
                                        }
                                        case 7: 
                                        case 8: {
                                            if (this.ay == 7 && this.var_byte_b == 0 && this.Q == 0 && this.int_a(-1, 3, (byte)-1) > 0) {
                                                this.var_int_L = 15;
                                                ++this.ay;
                                            }
                                            a[] aArray = this.aeii_a_arr_a(-1, 2, (byte)0);
                                            for (n = 0; n < aArray.length; ++n) {
                                                if (aArray[n].var_short_b > 4 && aArray[n].var_short_c > 10) continue;
                                                this.void_d(10);
                                                this.var_boolean_m = false;
                                                this.var_boolean_d = false;
                                                this.ay = 9;
                                                break block68;
                                            }
                                            break block292;
                                        }
                                        case 9: {
                                            this.e(0, 8);
                                            aeii.a.a((byte)5, (byte)1, -1, 8).a(0, 8, false);
                                            aeii.a.a((byte)5, (byte)1, -2, 7).a(1, 7, false);
                                            this.void_d(20);
                                            break;
                                        }
                                        case 10: {
                                            this.e(8, 6);
                                            aeii.a.a((byte)5, (byte)1, 12, 6).a(8, 6, false);
                                            this.void_d(20);
                                            break;
                                        }
                                        case 11: {
                                            this.e(2, 1);
                                            aeii.a.a((byte)5, (byte)1, 1, -2).a(1, 2, false);
                                            aeii.a.a((byte)5, (byte)1, 3, -2).a(3, 2, false);
                                            aeii.a.a((byte)4, (byte)1, 2, -1).a(2, 1, false);
                                            this.void_d(20);
                                            break;
                                        }
                                        case 12: {
                                            this.a(aeii.f.java_lang_String_a(244), (byte)5, (byte)4);
                                            this.void_d(10);
                                            ++this.ay;
                                            break;
                                        }
                                        case 13: {
                                            this.e(4, 8);
                                            this.void_d(15);
                                            break;
                                        }
                                        case 14: {
                                            a a4 = aeii.a.a((byte)2, (byte)1, 3, 8);
                                            a a5 = aeii.a.a((byte)2, (byte)1, 4, 7);
                                            a a6 = aeii.a.a((byte)2, (byte)1, 5, 8);
                                            this.a(this.var_a_a_a_g_f, a4.m, a4.n, 0, 0, 1, 50);
                                            this.a(this.var_a_a_a_g_f, a5.m, a5.n, 0, 0, 1, 50);
                                            this.a(this.var_a_a_a_g_f, a6.m, a6.n, 0, 0, 1, 50);
                                            this.void_d(10);
                                            ++this.ay;
                                            break;
                                        }
                                        case 15: {
                                            this.a(aeii.f.java_lang_String_a(245), (byte)5, (byte)4);
                                            this.void_d(10);
                                            ++this.ay;
                                            break;
                                        }
                                        case 16: {
                                            this.a(aeii.f.java_lang_String_a(246), (byte)-1, (byte)4);
                                            this.void_d(5);
                                            ++this.ay;
                                            break;
                                        }
                                        case 17: {
                                            this.a(aeii.f.java_lang_String_a(247), (byte)0, (byte)4);
                                            this.void_d(10);
                                            ++this.ay;
                                            break;
                                        }
                                        case 18: {
                                            aeii.f.var_aeii_c_a.a(this.a(null, aeii.f.java_lang_String_a(248), this.var_int_b, 1500));
                                            ++this.ay;
                                            break;
                                        }
                                        case 19: {
                                            this.aeii_a_a(3, 8, (byte)0).void_a();
                                            this.aeii_a_a(4, 7, (byte)0).void_a();
                                            this.aeii_a_a(5, 8, (byte)0).void_a();
                                            a a7 = aeii.a.a((byte)2, (byte)0, 3, 8);
                                            a a8 = aeii.a.a((byte)2, (byte)0, 4, 7);
                                            a a9 = aeii.a.a((byte)2, (byte)0, 5, 8);
                                            this.a(this.var_a_a_a_g_f, a7.m, a7.n, 0, 0, 1, 50);
                                            this.a(this.var_a_a_a_g_f, a8.m, a8.n, 0, 0, 1, 50);
                                            this.a(this.var_a_a_a_g_f, a9.m, a9.n, 0, 0, 1, 50);
                                            this.void_d(10);
                                            ++this.ay;
                                            break;
                                        }
                                        case 20: {
                                            this.var_boolean_m = true;
                                            this.var_boolean_d = true;
                                            this.var_int_L = 13;
                                            ++this.ay;
                                            break;
                                        }
                                        case 21: {
                                            if (this.int_a(-1, -1, (byte)1) == 0) {
                                                this.var_boolean_m = false;
                                                this.void_d(10);
                                                ++this.ay;
                                                break;
                                            }
                                            break block292;
                                        }
                                        case 22: {
                                            this.u();
                                        }
                                    }
                                    break block292;
                                }
                                if (this.var_int_x != 3) break block296;
                                switch (this.ay) {
                                    case 0: {
                                        aeii.f.var_aeii_c_a.a(this.var_aeii_e_G);
                                        this.var_boolean_q = true;
                                        ++this.ay;
                                        break;
                                    }
                                    case 1: {
                                        if (this.var_aeii_a_arr_b[0].var_byte_f != 1) {
                                            this.var_aeii_a_j = null;
                                            this.void_d(20);
                                            ++this.ay;
                                            break;
                                        }
                                        break block292;
                                    }
                                    case 2: {
                                        this.var_boolean_o = true;
                                        this.var_boolean_q = true;
                                        this.R = 0;
                                        this.void_d(20);
                                        ++this.ay;
                                        break;
                                    }
                                    case 3: {
                                        this.a(aeii.f.java_lang_String_a(249), (byte)2, (byte)4);
                                        this.void_d(10);
                                        ++this.ay;
                                        break;
                                    }
                                    case 4: {
                                        this.a(aeii.f.java_lang_String_a(250), (byte)0, (byte)4);
                                        this.void_d(10);
                                        ++this.ay;
                                        break;
                                    }
                                    case 5: {
                                        this.a(aeii.f.java_lang_String_a(251), (byte)5, (byte)4);
                                        this.d(13, 3);
                                        this.void_a(312, 72);
                                        this.var_aeii_a_arr_b[0].void_a(7, 1);
                                        this.var_aeii_a_n.void_a(5, 4);
                                        this.var_aeii_a_o.void_a(7, 5);
                                        this.var_aeii_a_p.void_a(3, 3);
                                        this.var_aeii_a_n = null;
                                        this.var_aeii_a_o = null;
                                        this.var_aeii_a_p = null;
                                        this.Y = 2;
                                        this.void_d(5);
                                        ++this.ay;
                                        break;
                                    }
                                    case 6: {
                                        this.var_boolean_o = false;
                                        this.var_boolean_p = true;
                                        this.R = 0;
                                        this.void_d(5);
                                        ++this.ay;
                                        break;
                                    }
                                    case 7: {
                                        this.e(13, 10);
                                        break;
                                    }
                                    case 8: {
                                        aeii.a.var_byte_c = (byte)2;
                                        this.Y = 4;
                                        this.var_aeii_a_n = this.aeii_a_a(10, 10, (byte)0);
                                        this.var_aeii_a_n.a(6, 10, false);
                                        this.d(6, 10);
                                        this.e(6, 10);
                                        break;
                                    }
                                    case 9: {
                                        if (this.var_aeii_a_n.var_byte_f != 1) {
                                            this.var_aeii_a_n = null;
                                            this.var_boolean_d = true;
                                            this.void_d(10);
                                            ++this.ay;
                                            break;
                                        }
                                        break block292;
                                    }
                                    case 10: {
                                        this.d(4, 9);
                                        this.e(4, 9);
                                        this.a(this.var_a_a_a_g_g, 96, 216, 0, 0, 1, 50);
                                        this.void_d(15);
                                        break;
                                    }
                                    case 11: {
                                        this.var_aeii_a_d = aeii.a.a((byte)0, (byte)0, 4, 9, false);
                                        this.var_aeii_a_d.var_byte_d = (byte)-1;
                                        this.var_aeii_a_d.var_byte_f = (byte)4;
                                        this.var_int_E = 6;
                                        this.void_d(20);
                                        ++this.ay;
                                        break;
                                    }
                                    case 12: {
                                        this.a(aeii.f.java_lang_String_a(252), (byte)5, (byte)4);
                                        this.e(7, 1);
                                        break;
                                    }
                                    case 13: {
                                        this.a(aeii.f.java_lang_String_a(253), (byte)0, (byte)4);
                                        this.void_d(5);
                                        ++this.ay;
                                        break;
                                    }
                                    case 14: {
                                        this.var_int_L = 17;
                                        ++this.ay;
                                        break;
                                    }
                                    case 15: {
                                        this.t();
                                        ++this.ay;
                                        break;
                                    }
                                    case 16: {
                                        if (this.int_a(-1, -1, (byte)1) == 0 && this.int_e(1) == 0) {
                                            this.void_d(15);
                                            this.var_boolean_m = false;
                                            ++this.ay;
                                            break;
                                        }
                                        break block292;
                                    }
                                    case 17: {
                                        this.a(aeii.f.java_lang_String_a(254), (byte)5, (byte)4);
                                        this.void_d(5);
                                        ++this.ay;
                                        break;
                                    }
                                    case 18: {
                                        this.a(aeii.f.java_lang_String_a(255), (byte)0, (byte)4);
                                        this.void_d(10);
                                        ++this.ay;
                                        break;
                                    }
                                    case 19: {
                                        this.u();
                                    }
                                }
                                break block292;
                            }
                            if (this.var_int_x != 4) break block297;
                            if (this.var_aeii_a_r == null) {
                                this.var_aeii_a_r = this.aeii_a_arr_a(11, -1, (byte)0)[0];
                            }
                            if (this.ay == 25 && this.var_aeii_a_r.var_short_b >= 15 && this.var_aeii_a_r.var_short_c >= 11 && this.var_aeii_a_r.var_byte_f == 2) {
                                this.void_d(10);
                                this.var_boolean_m = false;
                                this.var_boolean_d = false;
                                this.ay = 26;
                                return;
                            }
                            if (this.var_aeii_a_r.var_byte_f == 3) {
                                this.var_aeii_a_r = null;
                                this.v();
                                return;
                            }
                            block114 : switch (this.ay) {
                                case 0: {
                                    this.void_d(50);
                                    ++this.ay;
                                    break;
                                }
                                case 1: {
                                    this.a(aeii.f.java_lang_String_a(256), (byte)5, (byte)4);
                                    this.void_d(5);
                                    ++this.ay;
                                    break;
                                }
                                case 2: {
                                    this.a(aeii.f.java_lang_String_a(257), (byte)0, (byte)4);
                                    this.void_d(5);
                                    ++this.ay;
                                    break;
                                }
                                case 3: {
                                    this.t();
                                    ++this.ay;
                                    break;
                                }
                                case 4: {
                                    a[] aArray = this.aeii_a_arr_a(-1, -1, (byte)0);
                                    for (n = 0; n < aArray.length; ++n) {
                                        if (aArray[n].var_byte_f != 2 || aArray[n].var_short_b > 8) continue;
                                        this.var_boolean_m = false;
                                        this.void_d(5);
                                        ++this.ay;
                                        break block114;
                                    }
                                    break block292;
                                }
                                case 5: {
                                    this.var_boolean_d = false;
                                    this.e(4, 4);
                                    break;
                                }
                                case 6: {
                                    aeii.a.a((byte)10, (byte)1, 4, 4).a(4, 1, false, true);
                                    this.void_d(10);
                                    ++this.ay;
                                    break;
                                }
                                case 7: {
                                    aeii.a.a((byte)1, (byte)1, 4, 4).a(5, 2, false, true);
                                    this.void_d(10);
                                    ++this.ay;
                                    break;
                                }
                                case 8: {
                                    aeii.a.a((byte)10, (byte)1, 4, 4).a(4, 3, false, true);
                                    this.void_d(10);
                                    ++this.ay;
                                    break;
                                }
                                case 9: {
                                    this.a(aeii.f.java_lang_String_a(258), (byte)5, (byte)4);
                                    this.e(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
                                    break;
                                }
                                case 10: {
                                    this.var_boolean_m = true;
                                    this.var_boolean_d = true;
                                    ++this.ay;
                                    break;
                                }
                                case 11: {
                                    a[] aArray = this.aeii_a_arr_a(-1, -1, (byte)0);
                                    for (n = 0; n < aArray.length; ++n) {
                                        if (aArray[n].var_byte_f != 2 || aArray[n].var_short_c < 7) continue;
                                        this.var_boolean_m = false;
                                        this.var_boolean_d = false;
                                        this.e(6, 10);
                                        break block114;
                                    }
                                    break block292;
                                }
                                case 12: {
                                    aeii.a.a((byte)1, (byte)1, 6, 10).a(5, 10, false, true);
                                    this.void_d(10);
                                    ++this.ay;
                                    break;
                                }
                                case 13: {
                                    aeii.a.a((byte)5, (byte)1, 6, 10).a(7, 8, false, true);
                                    this.void_d(15);
                                    ++this.ay;
                                    break;
                                }
                                case 14: {
                                    aeii.a.a((byte)5, (byte)1, 6, 10).a(7, 9, false, true);
                                    this.var_boolean_m = true;
                                    this.var_boolean_d = true;
                                    ++this.ay;
                                    break;
                                }
                                case 15: {
                                    a[] aArray = this.aeii_a_arr_a(-1, -1, (byte)0);
                                    for (n = 0; n < aArray.length; ++n) {
                                        if (aArray[n].var_byte_f != 2 || aArray[n].var_short_b < 8 || aArray[n].var_short_c < 6) continue;
                                        this.var_boolean_m = false;
                                        this.var_boolean_d = false;
                                        this.e(12, 5);
                                        break block114;
                                    }
                                    break block292;
                                }
                                case 16: {
                                    aeii.a.a((byte)5, (byte)1, 12, 5).a(12, 7, false, true);
                                    this.void_d(15);
                                    ++this.ay;
                                    break;
                                }
                                case 17: {
                                    aeii.a.a((byte)6, (byte)1, 12, 5).a(12, 6, false, true);
                                    this.void_d(10);
                                    ++this.ay;
                                    break;
                                }
                                case 18: {
                                    aeii.a.a((byte)5, (byte)1, 12, 5).a(12, 5, false, true);
                                    this.var_boolean_m = true;
                                    this.var_boolean_d = true;
                                    ++this.ay;
                                    break;
                                }
                                case 19: {
                                    a[] aArray = this.aeii_a_arr_a(-1, -1, (byte)0);
                                    for (n = 0; n < aArray.length; ++n) {
                                        if (aArray[n].var_byte_f != 2 || aArray[n].var_short_b < 15 || aArray[n].var_short_c < 8) continue;
                                        this.var_boolean_m = false;
                                        this.var_boolean_d = false;
                                        this.e(18, 8);
                                        break block114;
                                    }
                                    break block292;
                                }
                                case 20: {
                                    aeii.a.a((byte)5, (byte)1, 18, 8).a(16, 10, false, true);
                                    this.void_d(10);
                                    ++this.ay;
                                    break;
                                }
                                case 21: {
                                    aeii.a.a((byte)6, (byte)1, 18, 8).a(17, 10, false, true);
                                    this.void_d(10);
                                    ++this.ay;
                                    break;
                                }
                                case 22: {
                                    aeii.a.a((byte)5, (byte)1, 18, 8).a(18, 10, false, true);
                                    this.void_d(10);
                                    ++this.ay;
                                    break;
                                }
                                case 23: {
                                    aeii.a.a((byte)1, (byte)1, 18, 8).a(18, 9, false, true);
                                    this.void_d(10);
                                    ++this.ay;
                                    break;
                                }
                                case 24: {
                                    this.a(aeii.f.java_lang_String_a(259), (byte)0, (byte)4);
                                    this.var_boolean_m = true;
                                    this.var_boolean_d = true;
                                    ++this.ay;
                                    break;
                                }
                                case 25: {
                                    if (this.int_a(-1, -1, (byte)1) == 0) {
                                        this.var_boolean_m = false;
                                        this.var_boolean_d = false;
                                        this.void_d(10);
                                        ++this.ay;
                                        break;
                                    }
                                    break block292;
                                }
                                case 26: {
                                    this.u();
                                    ++this.ay;
                                }
                            }
                            break block292;
                        }
                        if (this.var_int_x != 5) break block298;
                        switch (this.ay) {
                            case 0: {
                                this.void_d(10);
                                ++this.ay;
                                break;
                            }
                            case 1: {
                                this.a(aeii.f.java_lang_String_a(260), (byte)1, (byte)4);
                                ++this.ay;
                                break;
                            }
                            case 2: {
                                this.t();
                                ++this.ay;
                                break;
                            }
                            case 3: {
                                if (this.int_a(-1, -1, (byte)1) == 0 && this.int_e(1) == 0) {
                                    this.var_boolean_m = false;
                                    this.var_boolean_d = false;
                                    this.void_d(15);
                                    ++this.ay;
                                    break;
                                }
                                break block292;
                            }
                            case 4: {
                                this.e(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
                                break;
                            }
                            case 5: {
                                this.a(aeii.f.java_lang_String_a(261), (byte)0, (byte)4);
                                this.void_d(10);
                                ++this.ay;
                                break;
                            }
                            case 6: {
                                this.u();
                            }
                        }
                        break block292;
                    }
                    if (this.var_int_x != 6) break block299;
                    if (this.ay <= 10) {
                        if (this.var_aeii_a_r == null) {
                            this.var_aeii_a_r = this.aeii_a_arr_a(11, -1, (byte)0)[0];
                        }
                        if (this.var_aeii_a_r.var_byte_f == 3) {
                            this.var_aeii_a_r = null;
                            this.v();
                            return;
                        }
                    }
                    switch (this.ay) {
                        case 0: {
                            if (this.var_aeii_a_arr_b[0].var_byte_f != 1) {
                                this.var_aeii_a_j = null;
                                this.void_d(10);
                                ++this.ay;
                                break;
                            }
                            break block292;
                        }
                        case 1: {
                            this.a(aeii.f.java_lang_String_a(262), (byte)5, (byte)4);
                            this.void_d(5);
                            ++this.ay;
                            break;
                        }
                        case 2: {
                            this.a(aeii.f.java_lang_String_a(263), (byte)0, (byte)4);
                            this.void_d(5);
                            ++this.ay;
                            break;
                        }
                        case 3: {
                            a[] aArray = this.aeii_a_arr_a(-1, -1, (byte)0);
                            for (n = 0; n < aArray.length; ++n) {
                                aArray[n].var_aeii_a_a = null;
                            }
                            this.t();
                            ++this.ay;
                            break;
                        }
                        case 4: {
                            if (this.var_short_a >= 2) {
                                this.void_d(15);
                                this.var_boolean_m = false;
                                this.var_boolean_d = false;
                                this.e(11, 7);
                                break;
                            }
                            break block292;
                        }
                        case 5: {
                            a a10 = aeii.a.a((byte)5, (byte)1, 11, 8);
                            a10.b(this.var_byte_arr_arr_b);
                            a10.a(14, 7, true);
                            this.void_d(10);
                            ++this.ay;
                            break;
                        }
                        case 6: {
                            a a11 = aeii.a.a((byte)0, (byte)1, 11, 8);
                            a11.b(this.var_byte_arr_arr_b);
                            a11.a(13, 7, true);
                            this.void_d(10);
                            ++this.ay;
                            break;
                        }
                        case 7: {
                            a a12 = aeii.a.a((byte)3, (byte)1, 11, 8);
                            a12.b(this.var_byte_arr_arr_b);
                            a12.a(12, 7, true);
                            this.void_d(10);
                            ++this.ay;
                            break;
                        }
                        case 8: {
                            aeii.a.a((byte)1, (byte)1, 11, 8).a(13, 8, false);
                            this.void_d(20);
                            ++this.ay;
                            break;
                        }
                        case 9: {
                            this.a(aeii.f.java_lang_String_a(264), (byte)5, (byte)4);
                            this.var_boolean_m = true;
                            this.var_boolean_d = true;
                            this.e(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
                            break;
                        }
                        case 10: {
                            boolean bl = false;
                            a[] aArray = this.aeii_a_arr_a(-1, 2, (byte)0);
                            for (n2 = 0; n2 < aArray.length; ++n2) {
                                if (aArray[n2].var_short_b > 9 && aArray[n2].var_short_c < 10) continue;
                                bl = true;
                                break;
                            }
                            if (bl || this.int_a(-1, -1, (byte)1) == 0) {
                                this.var_boolean_d = false;
                                this.var_boolean_m = false;
                                this.void_d(10);
                                ++this.ay;
                                break;
                            }
                            break block292;
                        }
                        case 11: {
                            this.var_aeii_a_n = this.aeii_a_arr_a(11, -1, (byte)0)[0];
                            this.var_aeii_a_o = aeii.a.a((byte)8, (byte)1, this.var_int_f, this.var_aeii_a_n.var_short_c);
                            this.e(this.var_int_f - 1, this.var_aeii_a_n.var_short_c);
                            this.var_boolean_d = false;
                            break;
                        }
                        case 12: {
                            this.var_aeii_a_o.a((int)this.var_aeii_a_n.var_short_b, (int)this.var_aeii_a_n.var_short_c, false);
                            this.void_d(5);
                            ++this.ay;
                            break;
                        }
                        case 13: {
                            this.a(aeii.f.java_lang_String_a(265), (byte)5, (byte)4);
                            this.var_aeii_a_j = this.var_aeii_a_o;
                            ++this.ay;
                            break;
                        }
                        case 14: {
                            if (this.var_aeii_a_o.var_byte_f != 1) {
                                this.a(aeii.f.java_lang_String_a(266), (byte)0, (byte)4);
                                this.var_aeii_a_o.a(-1, (int)this.var_aeii_a_o.var_short_c, false);
                                this.void_d(3);
                                ++this.ay;
                                break;
                            }
                            break block292;
                        }
                        case 15: {
                            this.var_aeii_a_n.a(-1, (int)this.var_aeii_a_o.var_short_c, false);
                            ++this.ay;
                            break;
                        }
                        case 16: {
                            if (this.var_aeii_a_o.var_byte_f != 1) {
                                this.void_d(10);
                                this.var_aeii_a_n.void_a();
                                this.var_aeii_a_o.void_a();
                                this.d(0, this.var_aeii_a_o.var_short_c);
                                this.var_aeii_a_n = null;
                                this.var_aeii_a_o = null;
                                this.var_aeii_a_j = null;
                                ++this.ay;
                                break;
                            }
                            break block292;
                        }
                        case 17: {
                            this.e(1, 9);
                            break;
                        }
                        case 18: {
                            this.var_aeii_a_arr_b[1] = aeii.a.a((byte)9, (byte)1, -2, 8);
                            this.var_aeii_a_arr_b[1].a(0, 8, false);
                            aeii.a.a((byte)0, (byte)1, -1, 8).a(3, 8, false);
                            aeii.a.a((byte)0, (byte)1, -1, 10).a(1, 10, false);
                            aeii.a.a((byte)8, (byte)1, -3, 7).a(4, 8, false);
                            aeii.a.a((byte)8, (byte)1, -3, 11).a(2, 10, false);
                            aeii.a.a((byte)4, (byte)1, -2, 9).a(2, 9, false);
                            aeii.a.a((byte)6, (byte)1, -4, 9).a(4, 9, false);
                            aeii.a.a((byte)6, (byte)1, -6, 9).a(5, 10, false);
                            this.void_d(50);
                            ++this.ay;
                            break;
                        }
                        case 19: {
                            this.a(aeii.f.java_lang_String_a(267), (byte)3, (byte)4);
                            ++this.ay;
                            break;
                        }
                        case 20: {
                            this.e(13, 14);
                            break;
                        }
                        case 21: {
                            aeii.a.a((byte)0, (byte)1, 13, 14).a(12, 14, false);
                            this.void_d(5);
                            ++this.ay;
                            break;
                        }
                        case 22: {
                            aeii.a.a((byte)6, (byte)1, 13, 14).a(14, 14, false);
                            this.void_d(5);
                            ++this.ay;
                            break;
                        }
                        case 23: {
                            aeii.a.a((byte)2, (byte)1, 13, 14).a(13, 12, false);
                            this.void_d(5);
                            ++this.ay;
                            break;
                        }
                        case 24: {
                            aeii.a.a((byte)3, (byte)1, 13, 14).a(13, 15, false);
                            this.void_d(15);
                            ++this.ay;
                            break;
                        }
                        case 25: {
                            this.a(aeii.f.java_lang_String_a(268), (byte)5, (byte)4);
                            this.void_d(10);
                            ++this.ay;
                            break;
                        }
                        case 26: {
                            this.a(aeii.f.java_lang_String_a(269), (byte)0, (byte)4);
                            this.void_d(10);
                            ++this.ay;
                            break;
                        }
                        case 27: {
                            this.e(13, 17);
                            break;
                        }
                        case 28: {
                            a a13 = aeii.a.a((byte)9, (byte)0, 13, 18);
                            a13.b(2);
                            a13.a(13, 16, false);
                            aeii.a.a((byte)6, (byte)0, 12, 18).a(12, 16, false);
                            aeii.a.a((byte)8, (byte)0, 14, 19).a(14, 16, false);
                            aeii.a.a((byte)4, (byte)0, 13, 19).a(13, 17, false);
                            aeii.a.a((byte)1, (byte)0, 12, 19).a(12, 17, false);
                            this.void_d(20);
                            ++this.ay;
                            break;
                        }
                        case 29: {
                            this.a(aeii.f.java_lang_String_a(270), (byte)1, (byte)4);
                            this.void_d(10);
                            ++this.ay;
                            break;
                        }
                        case 30: {
                            this.e(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
                            break;
                        }
                        case 31: {
                            this.var_int_L = 18;
                            ++this.ay;
                            break;
                        }
                        case 32: {
                            this.var_boolean_m = true;
                            this.var_boolean_d = true;
                            this.var_aeii_e_e = this.a(aeii.f.java_lang_String_a(121 + this.var_int_x), aeii.f.java_lang_String_a(138), this.var_int_b, -1);
                            this.var_aeii_e_e.a((byte)0, true);
                            this.var_aeii_e_e.a((f)null);
                            aeii.f.var_aeii_c_a.a(this.var_aeii_e_e);
                            ++this.ay;
                            break;
                        }
                        case 33: {
                            if (this.int_a(-1, -1, (byte)1) == 0 && this.int_e(1) == 0) {
                                this.var_boolean_m = false;
                                this.var_boolean_d = false;
                                this.void_d(10);
                                ++this.ay;
                                break;
                            }
                            break block292;
                        }
                        case 34: {
                            this.u();
                        }
                    }
                    break block292;
                }
                if (this.var_int_x == 7) {
                    switch (this.ay) {
                        case 0: {
                            aeii.f.var_aeii_c_a.a(this.var_aeii_e_G);
                            this.var_boolean_q = true;
                            ++this.ay;
                            break;
                        }
                        case 1: {
                            if (this.var_boolean_p) break;
                            this.a(aeii.f.java_lang_String_a(271), (byte)0, (byte)4);
                            ++this.ay;
                            break;
                        }
                        case 2: {
                            this.e(this.var_aeii_a_arr_b[1].var_short_b, this.var_aeii_a_arr_b[1].var_short_c);
                            break;
                        }
                        case 3: {
                            this.a(aeii.f.java_lang_String_a(272), (byte)4, (byte)4);
                            this.void_d(10);
                            ++this.ay;
                            break;
                        }
                        case 4: {
                            this.a(aeii.f.java_lang_String_a(273), (byte)1, (byte)4);
                            this.void_d(10);
                            ++this.ay;
                            break;
                        }
                        case 5: {
                            this.a(aeii.f.java_lang_String_a(274), (byte)4, (byte)4);
                            ++this.ay;
                            break;
                        }
                        case 6: {
                            this.var_aeii_a_n = this.aeii_a_a(5, 2, (byte)0);
                            this.var_aeii_a_n.a(7, 2, false);
                            ++this.ay;
                            break;
                        }
                        case 7: {
                            if (this.var_aeii_a_n.var_byte_f == 1) break;
                            this.var_aeii_a_n.void_a();
                            this.var_aeii_a_n = null;
                            this.var_aeii_a_o = this.aeii_a_a(7, 3, (byte)0);
                            this.var_aeii_a_o.a(7, 2, false);
                            ++this.ay;
                            break;
                        }
                        case 8: {
                            if (this.var_aeii_a_o.var_byte_f == 1) break;
                            this.var_aeii_a_o.void_a();
                            this.var_aeii_a_o = null;
                            this.var_aeii_a_p = this.aeii_a_a(9, 2, (byte)0);
                            this.var_aeii_a_p.a(7, 2, false);
                            ++this.ay;
                            break;
                        }
                        case 9: {
                            if (this.var_aeii_a_p.var_byte_f == 1) break;
                            this.var_aeii_a_p.void_a();
                            this.var_aeii_a_p = null;
                            this.var_aeii_a_arr_b[1].a(7, 2, false);
                            this.void_d(20);
                            ++this.ay;
                            break;
                        }
                        case 10: {
                            this.e(9, 15);
                            this.var_boolean_d = true;
                            break;
                        }
                        case 11: {
                            e e3 = this.a(null, aeii.f.java_lang_String_a(279), this.var_int_b, 2000);
                            e3.a(this.var_int_c, 2, 17);
                            aeii.f.var_aeii_c_a.a(e3);
                            ++this.ay;
                            break;
                        }
                        case 12: {
                            this.var_boolean_r = false;
                            this.void_a(this.aeii_a_a(9, 15, (byte)0));
                            ++this.ay;
                            break;
                        }
                        case 13: {
                            if (this.T < 2) break;
                            this.a(aeii.f.java_lang_String_a(275), (byte)0, (byte)4);
                            this.aeii_a_a(9, 15, (byte)0).void_a();
                            this.var_boolean_r = true;
                            this.void_d(20);
                            ++this.ay;
                            break;
                        }
                        case 14: {
                            this.var_boolean_d = false;
                            this.a(aeii.f.java_lang_String_a(276), (byte)4, (byte)4);
                            ++this.ay;
                            break;
                        }
                        case 15: {
                            this.a(aeii.f.java_lang_String_a(277), (byte)1, (byte)4);
                            this.void_d(10);
                            ++this.ay;
                            break;
                        }
                        case 16: {
                            this.var_boolean_d = true;
                            this.Y = 4;
                            this.e(3, 9);
                            break;
                        }
                        case 17: {
                            this.e(13, 4);
                            break;
                        }
                        case 18: {
                            this.Y = 12;
                            this.a(aeii.f.java_lang_String_a(278), (byte)5, (byte)4);
                            this.e(this.var_aeii_a_arr_b[0].var_short_b, this.var_aeii_a_arr_b[0].var_short_c);
                            break;
                        }
                        case 19: {
                            this.t();
                            ++this.ay;
                            break;
                        }
                        case 20: {
                            if (!this.var_boolean_s) break;
                            this.var_boolean_d = false;
                            this.var_boolean_m = false;
                            this.void_d(20);
                            ++this.ay;
                            break;
                        }
                        case 21: {
                            this.var_boolean_o = true;
                            this.R = 0;
                            this.var_boolean_q = true;
                            ++this.ay;
                            break;
                        }
                        case 22: {
                            if (this.R < 16) break;
                            this.void_b(7, 2);
                            this.d(7, 2);
                            this.i();
                            this.var_aeii_a_arr_b[1] = aeii.a.a((byte)9, (byte)1, 7, 2);
                            this.var_aeii_a_arr_b[1].b(3);
                            aeii.a.a((byte)9, (byte)0, 6, 3);
                            aeii.a.a((byte)9, (byte)0, 8, 3).b(2);
                            aeii.a.a((byte)0, (byte)0, 6, 1);
                            aeii.a.a((byte)0, (byte)0, 8, 1);
                            this.void_d(10);
                            ++this.ay;
                            aeii.c.void_a(8, 0);
                            break;
                        }
                        case 23: {
                            this.var_boolean_o = false;
                            this.var_boolean_p = true;
                            this.R = 0;
                            ++this.ay;
                            break;
                        }
                        case 24: {
                            if (this.R < 16) break;
                            this.a(aeii.f.java_lang_String_a(281), (byte)4, (byte)4);
                            this.void_d(15);
                            ++this.ay;
                            break;
                        }
                        case 25: {
                            this.a(aeii.f.java_lang_String_a(282), (byte)0, (byte)4);
                            this.void_d(8);
                            ++this.ay;
                            break;
                        }
                        case 26: {
                            this.a(aeii.f.java_lang_String_a(283), (byte)4, (byte)4);
                            this.void_d(15);
                            ++this.ay;
                            break;
                        }
                        case 27: {
                            this.a(this.var_a_a_a_g_f, 168, 48, 0, 0, 1, 50);
                            this.var_java_util_Vector_a.removeElement(this.var_aeii_a_arr_b[1]);
                            this.void_d(15);
                            ++this.ay;
                            break;
                        }
                        case 28: {
                            aeii.c.d(400);
                            this.void_a(5000);
                            this.void_d(10);
                            ++this.ay;
                            break;
                        }
                        case 29: {
                            this.a(aeii.f.java_lang_String_a(284), (byte)5, (byte)4);
                            this.void_d(5);
                            ++this.ay;
                            break;
                        }
                        case 30: {
                            this.a(aeii.f.java_lang_String_a(285), (byte)2, (byte)4);
                            this.void_d(5);
                            ++this.ay;
                            break;
                        }
                        case 31: {
                            this.a(aeii.f.java_lang_String_a(286), (byte)1, (byte)4);
                            this.void_d(5);
                            ++this.ay;
                            break;
                        }
                        case 32: {
                            this.a(aeii.f.java_lang_String_a(287), (byte)0, (byte)4);
                            this.var_boolean_o = true;
                            this.R = 0;
                            ++this.ay;
                            break;
                        }
                        case 33: {
                            if (this.R < 16) break;
                            this.void_d(10);
                            this.var_boolean_C = false;
                            ++this.ay;
                            break;
                        }
                        case 34: {
                            this.void_a(5, 2, 2);
                            ++this.ay;
                            break;
                        }
                        case 35: {
                            aeii.c.void_a(0, 0);
                            this.void_a(aeii.f.java_lang_String_a(288));
                            ++this.ay;
                            break;
                        }
                        case 36: {
                            this.aC = 0;
                            this.var_byte_d = (byte)14;
                            ++this.ay;
                        }
                    }
                }
            }
            this.var_aeii_a_a = null;
        }
    }

    private void t() {
        this.var_aeii_e_G = null;
        this.Y = 12;
        aeii.a.var_byte_c = aeii.a.var_byte_b;
        this.var_aeii_e_e.a((byte)0, true);
        this.var_aeii_e_e.a((byte)1, false);
        aeii.f.var_aeii_c_a.a(this.var_aeii_e_e);
        this.var_boolean_d = true;
        this.var_boolean_m = true;
        this.var_boolean_n = false;
        aeii.c.void_a(2, 0);
    }

    private void e(int n, int n2) {
        this.aA = n;
        this.aB = n2;
        this.d(n, n2);
        ++this.ay;
    }

    private void u() {
        aeii.c.f();
        aeii.c.b(6, 1);
        aeii.f.var_aeii_c_a.a(this.a(null, aeii.f.java_lang_String_a(72), this.var_int_b, 3000));
        this.var_long_d = this.var_long_a;
        this.ay = -1;
        this.aC = 0;
        this.var_byte_d = (byte)10;
    }

    private void v() {
        this.var_boolean_A = true;
        this.ay = 0;
        this.void_d(20);
        aeii.c.f();
        aeii.c.b(7, 1);
    }

    private void b(a object, a a2) {
        try {
            System.gc();
            this.var_int_o = this.var_int_b - this.ak;
            this.var_boolean_E = true;
            this.var_int_G = 0;
            this.var_boolean_B = false;
            this.var_aeii_a_f = object;
            this.var_aeii_a_g = a2;
            aeii.c.g();
            this.var_aeii_h_a = new h(this, (a)object, null);
            this.var_aeii_h_a.var_aeii_h_a = this.var_aeii_h_b = new h(this, a2, this.var_aeii_h_a);
            ((a)object).c(a2);
            if (a2.boolean_a((int)((a)object).var_short_b, ((a)object).var_short_c)) {
                a2.c((a)object);
                this.var_boolean_D = true;
            } else {
                this.var_boolean_D = false;
            }
            this.var_aeii_h_a.var_byte_a = (byte)((a)object).var_int_h;
            this.var_aeii_h_a.var_byte_b = (byte)((a)object).int_b();
            this.var_aeii_h_b.var_byte_a = (byte)a2.var_int_h;
            this.var_aeii_h_b.var_byte_b = (byte)a2.int_b();
            aeii.c.b(var_int_arr_h[this.var_byte_arr_d[this.var_byte_b]], 0);
            this.var_byte_h = (byte)2;
            return;
        }
        catch (IOException iOException) {
            object = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    public final void a(a.a.a.g g2) {
        this.var_java_util_Vector_g.addElement(g2);
    }

    public final void b(a.a.a.g g2) {
        this.var_java_util_Vector_g.removeElement(g2);
    }

    public final void void_a(int n) {
        this.var_boolean_C = true;
        this.var_long_m = n;
        this.var_long_n = this.var_long_a;
    }

    private e b(f f2) {
        e e2;
        if (this.var_java_lang_String_arr_p.length > 0) {
            e2 = new e(11, 0);
            e e3 = e2.aeii_e_a(aeii.f.java_lang_String_a(46));
            e2.aeii_e_a(aeii.f.java_lang_String_a(46)).var_aeii_d_a = this.var_aeii_d_arr_c[6];
            e2.a(this.var_java_lang_String_arr_p, this.var_int_a / 2, (this.var_int_b + e3.var_int_c) / 2, this.var_int_a, this.var_int_b - e3.var_int_c, 3, 4);
        } else {
            e2 = new e(10, 0);
            new e(10, 0).aeii_e_a((String)aeii.f.java_lang_String_a((int)46)).var_aeii_d_a = this.var_aeii_d_arr_c[6];
            e2.a(null, aeii.f.java_lang_String_a(52), this.var_int_a, -1);
        }
        e2.a(f2);
        aeii.f.var_aeii_c_a.a(e2);
        return e2;
    }

    private void c(byte[] object) {
        try {
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream((byte[])object));
            object = dataInputStream;
            dataInputStream.readLong();
            switch (((DataInputStream)object).readInt()) {
                case 10001: {
                    ((DataInputStream)object).readUTF();
                    ((DataInputStream)object).readInt();
                    ((DataInputStream)object).readUTF();
                    ((DataInputStream)object).readInt();
                    ((DataInputStream)object).readUTF();
                    ((DataInputStream)object).readInt();
                    ((DataInputStream)object).readUTF();
                    int n = ((DataInputStream)object).readInt();
                    ((DataInputStream)object).readUTF();
                    ((DataInputStream)object).readUTF();
                    if (this.aG == 0) {
                        n = ((DataInputStream)object).readInt() / 2;
                        this.var_java_lang_String_arr_p = new String[n];
                        this.var_java_lang_String_arr_q = new String[n];
                        this.var_java_lang_String_arr_r = new String[n];
                        for (int i = 0; i < n; ++i) {
                            this.var_java_lang_String_arr_p[i] = ((DataInputStream)object).readUTF();
                            this.var_java_lang_String_arr_q[i] = ((DataInputStream)object).readUTF();
                        }
                        this.var_aeii_e_A = this.b(this.var_aeii_f_a);
                    } else if (this.aG == 1) {
                        ((DataInputStream)object).readInt();
                        this.var_java_lang_String_arr_r[this.aD] = ((DataInputStream)object).readUTF();
                        e e2 = this.a(this.var_java_lang_String_arr_p[this.aD], this.var_java_lang_String_arr_r[this.aD], this.var_int_b, this.var_int_b / 2, -1);
                        e2.a(this.var_aeii_f_a);
                        aeii.f.var_aeii_c_a.a(e2);
                    } else if (this.aG == 2) {
                        n = ((DataInputStream)object).readInt() / 3;
                        this.var_java_lang_String_arr_s = new String[n];
                        this.var_java_lang_String_arr_t = new String[n];
                        this.var_int_arr_n = new int[n];
                        for (int i = 0; i < n; ++i) {
                            this.var_java_lang_String_arr_s[i] = ((DataInputStream)object).readUTF();
                            this.var_java_lang_String_arr_t[i] = ((DataInputStream)object).readUTF();
                            this.var_int_arr_n[i] = Integer.parseInt(((DataInputStream)object).readUTF());
                        }
                        this.void_a(this.var_aeii_f_a);
                        aeii.f.var_aeii_c_a.a(this.var_aeii_e_D);
                    } else if (this.aG == 3) {
                        String string = this.var_java_lang_String_arr_s[this.aE];
                        byte[] byArray = new byte[n];
                        ((DataInputStream)object).readFully(byArray);
                        this.a(string, byArray);
                        this.void_a(this.var_aeii_e_D.var_aeii_f_a);
                        object = this.a(null, aeii.f.a(45, string), this.var_int_b, 2000);
                        ((e)object).a(this.var_aeii_e_D);
                        aeii.f.var_aeii_c_a.a((f)object);
                    }
                    this.var_aeii_f_a = null;
                    return;
                }
            }
            ((FilterInputStream)object).close();
        }
        catch (Exception exception) {
            object = exception;
            exception.printStackTrace();
        }
        object = this.a(null, aeii.f.java_lang_String_a(44), this.var_int_b, -1);
        ((e)object).a(this.var_aeii_f_a);
        this.var_aeii_f_a = null;
        aeii.f.var_aeii_c_a.a((f)object);
    }

    private void a(int n, String string, String string2, f f2) {
        try {
            this.aG = n;
            this.var_aeii_f_a = f2;
            this.var_boolean_G = false;
            this.var_java_io_ByteArrayOutputStream_a = new ByteArrayOutputStream();
            this.var_java_io_DataOutputStream_a = new DataOutputStream(this.var_java_io_ByteArrayOutputStream_a);
            this.var_java_io_DataOutputStream_a.writeInt(10001);
            this.var_java_io_DataOutputStream_a.writeUTF(this.var_java_lang_String_d);
            this.var_java_io_DataOutputStream_a.writeUTF(this.var_java_lang_String_e);
            this.aF = this.var_java_io_ByteArrayOutputStream_a.size();
            this.var_java_io_DataOutputStream_a.writeUTF("resourceName");
            this.var_java_io_DataOutputStream_a.writeUTF(string);
            this.var_java_io_DataOutputStream_a.writeUTF("languageCode");
            this.var_java_io_DataOutputStream_a.writeUTF(string2);
            this.var_java_io_DataOutputStream_a.writeUTF("maxChunkSize");
            this.var_java_io_DataOutputStream_a.writeUTF("1024");
            this.var_java_io_DataOutputStream_a.writeUTF("chunk");
            this.var_java_io_DataOutputStream_a.writeUTF("0");
            this.var_java_io_DataOutputStream_a.writeUTF("requestId");
            this.var_java_io_DataOutputStream_a.writeUTF("0");
            e e2 = this.a(null, aeii.f.java_lang_String_a(43), this.var_int_b, -1);
            e2.a(f2);
            aeii.f.var_aeii_c_a.a(e2);
            new Thread(this).start();
            return;
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    /*
     * Unable to fully structure code
     */
    public final void run() {
        var1_1 = null;
        var2_3 = null;
        try {
            var3_4 = this.var_java_io_ByteArrayOutputStream_a.toByteArray();
            this.var_java_io_ByteArrayOutputStream_a.close();
            this.var_java_io_DataOutputStream_a.close();
            var2_3 = (HttpConnection)Connector.open((String)this.var_java_lang_String_f);
            if ("J4d28g1Kl490".length() > 0) {
                var4_8 = "J4d28g1Kl490".getBytes();
                for (var5_10 = this.aF; var5_10 < var3_4.length; ++var5_10) {
                    v0 = var5_10;
                    var3_4[v0] = (byte)(var3_4[v0] ^ var4_8[var5_10 % ((byte[])var4_8).length]);
                }
            }
            var2_3.setRequestMethod("POST");
            var2_3.setRequestProperty("Content-Length", Integer.toString(var3_4.length));
            var2_3.setRequestProperty("Connection", "close");
            v1 = var2_3.openOutputStream();
            var4_8 = v1;
            v1.write(var3_4);
            var4_8.flush();
            var4_8.close();
            var5_11 = var2_3.openDataInputStream();
            var3_5 = var2_3.getResponseCode();
            if (var3_5 != 200) {
                throw new Exception("" + var3_5);
            }
            var3_6 = new ByteArrayOutputStream();
            while ((var4_9 = var5_11.read()) != -1) {
                var3_6.write(var4_9);
            }
            var1_1 = var3_6.toByteArray();
            var3_6.close();
            var5_11.close();
            ** if (var2_3 == null) goto lbl-1000
        }
        catch (Exception v3) {
            try {
                var3_7 = v3;
                v3.printStackTrace();
                ** if (var2_3 == null) goto lbl-1000
            }
            catch (Throwable var1_2) {
                if (var2_3 != null) {
                    try {
                        var2_3.close();
                    }
                    catch (Exception v5) {
                        var2_3 = v5;
                        v5.printStackTrace();
                    }
                }
                throw var1_2;
            }
lbl-1000:
            // 1 sources

            {
                try {
                    var2_3.close();
                }
                catch (Exception v4) {
                    var3_7 = v4;
                    v4.printStackTrace();
                }
            }
lbl-1000:
            // 2 sources

            {
            }
        }
lbl-1000:
        // 1 sources

        {
            try {
                var2_3.close();
            }
            catch (Exception v2) {
                var3_6 = v2;
                v2.printStackTrace();
            }
        }
lbl-1000:
        // 2 sources

        {
        }
        if (!this.var_boolean_G) {
            this.c(var1_1);
        }
    }

    private void f(int n) {
        int n2 = this.var_int_arr_l[n];
        --this.U;
        String[] stringArray = new String[this.U];
        int[] nArray = new int[this.U];
        System.arraycopy(this.var_java_lang_String_arr_l, 0, stringArray, 0, n);
        System.arraycopy(this.var_java_lang_String_arr_l, n + 1, stringArray, n, this.U - n);
        System.arraycopy(this.var_int_arr_l, 0, nArray, 0, n);
        System.arraycopy(this.var_int_arr_l, n + 1, nArray, n, this.U - n);
        this.var_java_lang_String_arr_l = stringArray;
        this.var_int_arr_l = nArray;
        aeii.c.void_a("download", n2);
        this.W = aeii.c.int_a("download");
        this.w();
        for (n = 0; n < 3; ++n) {
            if (this.var_int_arr_k[n] != n2 + var_java_lang_String_arr_e.length) continue;
            this.var_byte_arr_n[n] = -1;
            this.var_int_arr_k[n] = -1;
            this.var_java_lang_String_arr_k[n] = "\n" + aeii.f.java_lang_String_a(79) + "\n ";
            aeii.c.a("save", n, new byte[0]);
        }
    }

    private void a(String string, byte[] byArray) {
        String[] stringArray = new String[this.U + 1];
        int[] nArray = new int[this.U + 1];
        System.arraycopy(this.var_java_lang_String_arr_l, 0, stringArray, 0, this.U);
        System.arraycopy(this.var_int_arr_l, 0, nArray, 0, this.U);
        this.var_java_lang_String_arr_l = stringArray;
        this.var_int_arr_l = nArray;
        this.var_int_arr_l[this.U] = aeii.c.a("download", byArray);
        this.var_java_lang_String_arr_l[this.U] = string;
        ++this.U;
        this.W = aeii.c.int_a("download");
        this.w();
    }

    private void w() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt(this.U);
            for (int i = 0; i < this.U; ++i) {
                dataOutputStream.writeInt(this.var_int_arr_l[i]);
                dataOutputStream.writeUTF(this.var_java_lang_String_arr_l[i]);
            }
            aeii.c.a("settings", 2, byteArrayOutputStream.toByteArray());
            dataOutputStream.close();
            return;
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    public final void commandAction(Command objectArray, Displayable object) {
        if (object == this.var_javax_microedition_lcdui_Form_a) {
            if (objectArray == this.var_javax_microedition_lcdui_Command_a) {
                int n;
                if (this.var_javax_microedition_lcdui_TextField_a.size() == 0 || this.var_javax_microedition_lcdui_TextField_b.size() == 0 || this.var_javax_microedition_lcdui_TextField_c.size() == 0) {
                    return;
                }
                int n2 = Integer.parseInt(this.var_javax_microedition_lcdui_TextField_b.getString());
                int n3 = Integer.parseInt(this.var_javax_microedition_lcdui_TextField_c.getString());
                if (n2 <= 0 || n3 <= 0) {
                    return;
                }
                this.var_javax_microedition_lcdui_ChoiceGroup_a.getSelectedFlags(this.var_boolean_arr_b);
                int n4 = 0;
                for (n = 0; n < this.var_boolean_arr_b.length; ++n) {
                    if (!this.var_boolean_arr_b[n]) continue;
                    ++n4;
                }
                if (n4 == 0) {
                    return;
                }
                try {
                    aeii.c.a((Displayable)aeii.f.var_aeii_c_a);
                    aeii.f.var_aeii_c_a.a(this);
                    this.var_byte_c = 1;
                    this.var_byte_n = 1;
                    this.var_boolean_j = true;
                    aeii.f.var_aeii_c_a.void_a();
                    this.e();
                    int[] nArray = this.var_int_arr_o;
                    n = n3;
                    n4 = n2;
                    Object object2 = this.var_javax_microedition_lcdui_TextField_a.getString();
                    g g2 = this;
                    try {
                        int n5;
                        int n6;
                        aeii.c.f();
                        g2.var_java_util_Vector_c = new Vector();
                        g2.var_boolean_o = false;
                        g2.var_boolean_d = true;
                        g2.var_boolean_g = false;
                        g2.var_boolean_s = false;
                        g2.var_java_util_Vector_b = null;
                        g2.var_aeii_a_c = null;
                        g2.var_aeii_a_i = null;
                        g2.var_aeii_a_d = null;
                        g2.var_aeii_a_e = null;
                        g2.var_java_util_Vector_e.removeAllElements();
                        g2.var_short_a = 0;
                        g2.var_byte_b = 0;
                        g2.var_byte_b = 0;
                        g2.ay = 0;
                        g2.i();
                        g2.var_aeii_a_arr_b = null;
                        g2.var_byte_arr_arr_a = null;
                        g2.var_byte_arr_arr_b = null;
                        g2.var_byte_f = 0;
                        for (n6 = 0; n6 < 5; ++n6) {
                            g2.var_byte_arr_l[n6] = -1;
                        }
                        g2.var_boolean_i = true;
                        aeii.c.g();
                        g2.var_java_lang_String_b = object2;
                        g2.var_int_f = n4;
                        g2.var_int_g = n;
                        g2.var_byte_arr_arr_a = new byte[g2.var_int_f][g2.var_int_g];
                        g2.var_byte_arr_arr_b = new byte[g2.var_int_f][g2.var_int_g];
                        g2.var_byte_arr_arr_g = new byte[g2.var_int_f][g2.var_int_g];
                        g2.as = 0;
                        n6 = 0;
                        object2 = new Vector();
                        Vector<byte[]> vector = new Vector<byte[]>();
                        aeii.c.a(nArray, true);
                        for (n5 = 0; n5 < g2.var_int_f; n5 = (int)((short)(n5 + 1))) {
                            for (int n7 = 0; n7 < g2.var_int_g; n7 = (int)((short)(n7 + 1))) {
                                g2.var_byte_arr_arr_a[n5][n7] = (byte)aeii.c.a(nArray);
                                g2.var_byte_arr_arr_b[n5][n7] = 0;
                                if (!aeii.g.b(g2.var_byte_arr_arr_a[n5][n7])) continue;
                                int n8 = g2.int_a(n5, n7);
                                byte[] byArray = new byte[3];
                                byte[] byArray2 = byArray;
                                byArray[0] = (byte)n5;
                                byArray2[1] = (byte)n7;
                                byArray2[2] = (byte)n8;
                                ((Vector)object2).addElement(byArray2);
                                ++n6;
                                if (g2.byte_a(n5, n7) != 9) continue;
                                if (g2.var_byte_c == 1 && n8 != 0 && g2.var_byte_arr_l[n8] == -1) {
                                    g2.var_byte_arr_d[g2.var_byte_f] = (byte)n8;
                                    g2.var_byte_f = (byte)(g2.var_byte_f + 1);
                                }
                                byte[] byArray3 = new byte[2];
                                byte[] byArray4 = byArray3;
                                byArray3[0] = (byte)n5;
                                byArray4[1] = (byte)n7;
                                vector.addElement(byArray4);
                                ++g2.as;
                            }
                        }
                        aeii.c.a(nArray, false);
                        g2.var_int_arr_m = new int[n6];
                        g2.var_byte_arr_arr_e = new byte[n6][];
                        ((Vector)object2).copyInto((Object[])g2.var_byte_arr_arr_e);
                        g2.var_byte_arr_arr_f = new byte[g2.as][];
                        vector.copyInto((Object[])g2.var_byte_arr_arr_f);
                        g2.var_int_t = g2.var_int_f * 24;
                        g2.var_int_u = g2.var_int_g * 24;
                        g2.var_boolean_A = false;
                        g2.var_aeii_a_r = null;
                        g2.var_aeii_a_n = null;
                        g2.var_aeii_a_o = null;
                        g2.var_aeii_a_p = null;
                        g2.var_aeii_a_q = null;
                        g2.var_aeii_a_a = null;
                        if (g2.var_byte_c == 1) {
                            g2.ay = 100;
                            g2.var_boolean_m = true;
                        }
                        g2.var_aeii_a_arr_d = new a[g2.var_byte_arr_arr_e.length];
                        g2.var_byte_arr_q = new byte[g2.var_byte_arr_arr_e.length];
                        g2.var_a_a_a_g_arr_b = new a.a.a.g[g2.var_byte_arr_arr_e.length];
                        for (n5 = 0; n5 < g2.var_byte_arr_arr_e.length; n5 = (int)((short)(n5 + 1))) {
                            if (g2.byte_a((int)g2.var_byte_arr_arr_e[n5][0], (int)g2.var_byte_arr_arr_e[n5][1]) != 8) continue;
                            g2.var_a_a_a_g_arr_b[n5] = a.a.a.g.a(g2.var_a_a_a_g_o, 0, -1, 0, 1, 250, (byte)0);
                            g2.var_a_a_a_g_arr_b[n5].var_boolean_b = false;
                        }
                        aeii.c.void_a(var_int_arr_g[1], 0);
                        g2.var_int_v = 0;
                        g2.var_int_w = 0;
                        g2.var_int_h = 0;
                        g2.var_int_i = 0;
                        g2.var_a_a_a_g_q.b(g2.var_int_h * 24, g2.var_int_i * 24);
                    }
                    catch (Exception exception) {
                        Exception exception2 = exception;
                        exception.printStackTrace();
                    }
                    this.var_boolean_j = false;
                    this.var_byte_d = 0;
                    this.C();
                }
                catch (Exception exception) {
                    Exception exception3 = exception;
                    exception.printStackTrace();
                    return;
                }
            }
            if (objectArray == this.var_javax_microedition_lcdui_Command_e) {
                System.arraycopy(this.var_int_arr_o, 0, this.var_int_arr_p, 0, this.var_int_arr_o.length);
                this.itemStateChanged((Item)this.var_javax_microedition_lcdui_Gauge_a);
                aeii.c.a((Displayable)this.var_javax_microedition_lcdui_Form_e);
                return;
            }
            if (objectArray == this.var_javax_microedition_lcdui_Command_c) {
                for (int i = 0; i < this.var_boolean_arr_b.length; ++i) {
                    this.var_boolean_arr_b[i] = true;
                }
                this.var_javax_microedition_lcdui_ChoiceGroup_a.setSelectedFlags(this.var_boolean_arr_b);
                this.itemStateChanged((Item)this.var_javax_microedition_lcdui_ChoiceGroup_a);
                return;
            }
            if (objectArray == this.var_javax_microedition_lcdui_Command_d) {
                for (int i = 0; i < this.var_boolean_arr_b.length; ++i) {
                    this.var_boolean_arr_b[i] = false;
                }
                this.var_javax_microedition_lcdui_ChoiceGroup_a.setSelectedFlags(this.var_boolean_arr_b);
                this.itemStateChanged((Item)this.var_javax_microedition_lcdui_ChoiceGroup_a);
                return;
            }
            aeii.c.a((Displayable)aeii.f.var_aeii_c_a);
            aeii.f.var_aeii_c_a.a(this);
            this.C();
            return;
        }
        if (object == this.var_javax_microedition_lcdui_Form_b) {
            if (objectArray == this.var_javax_microedition_lcdui_Command_a) {
                this.var_javax_microedition_lcdui_ChoiceGroup_a.getSelectedFlags(this.var_boolean_arr_b);
                int n = 0;
                for (int i = 0; i < this.var_boolean_arr_b.length; ++i) {
                    if (!this.var_boolean_arr_b[i]) continue;
                    ++n;
                }
                if (n == 0) {
                    return;
                }
                this.itemStateChanged((Item)this.var_javax_microedition_lcdui_ChoiceGroup_a);
                aeii.c.a((Displayable)aeii.f.var_aeii_c_a);
                aeii.f.var_aeii_c_a.a(this);
                return;
            }
            if (objectArray == this.var_javax_microedition_lcdui_Command_e) {
                System.arraycopy(this.var_int_arr_o, 0, this.var_int_arr_p, 0, this.var_int_arr_o.length);
                this.itemStateChanged((Item)this.var_javax_microedition_lcdui_Gauge_a);
                aeii.c.a((Displayable)this.var_javax_microedition_lcdui_Form_e);
                return;
            }
            if (objectArray == this.var_javax_microedition_lcdui_Command_c) {
                for (int i = 0; i < this.var_boolean_arr_b.length; ++i) {
                    this.var_boolean_arr_b[i] = true;
                }
                this.var_javax_microedition_lcdui_ChoiceGroup_a.setSelectedFlags(this.var_boolean_arr_b);
                this.itemStateChanged((Item)this.var_javax_microedition_lcdui_ChoiceGroup_a);
                return;
            }
            if (objectArray == this.var_javax_microedition_lcdui_Command_d) {
                for (int i = 0; i < this.var_boolean_arr_b.length; ++i) {
                    this.var_boolean_arr_b[i] = false;
                }
                this.var_javax_microedition_lcdui_ChoiceGroup_a.setSelectedFlags(this.var_boolean_arr_b);
                this.itemStateChanged((Item)this.var_javax_microedition_lcdui_ChoiceGroup_a);
                return;
            }
            aeii.c.a((Displayable)aeii.f.var_aeii_c_a);
            aeii.f.var_aeii_c_a.a(this);
            return;
        }
        if (object == this.var_javax_microedition_lcdui_Form_c) {
            if (objectArray == this.var_javax_microedition_lcdui_Command_a) {
                if (this.var_javax_microedition_lcdui_TextField_a.size() == 0) {
                    return;
                }
                aeii.c.a((Displayable)aeii.f.var_aeii_c_a);
                aeii.f.var_aeii_c_a.a(this);
                this.var_java_lang_String_b = this.var_javax_microedition_lcdui_TextField_a.getString();
                return;
            }
            if (objectArray == this.var_javax_microedition_lcdui_Command_f) {
                this.var_javax_microedition_lcdui_TextField_a.setString(Long.toString(System.currentTimeMillis(), 36).toUpperCase());
                return;
            }
            aeii.c.a((Displayable)aeii.f.var_aeii_c_a);
            aeii.f.var_aeii_c_a.a(this);
            return;
        }
        if (object == this.var_javax_microedition_lcdui_Form_d) {
            if (objectArray == this.var_javax_microedition_lcdui_Command_a) {
                if (this.var_javax_microedition_lcdui_TextField_e.size() == 0 || this.var_javax_microedition_lcdui_TextField_f.size() == 0 || this.var_javax_microedition_lcdui_TextField_g.size() == 0 || this.var_javax_microedition_lcdui_TextField_h.size() == 0) {
                    return;
                }
                this.a(Integer.parseInt(this.var_javax_microedition_lcdui_TextField_e.getString()), Integer.parseInt(this.var_javax_microedition_lcdui_TextField_f.getString()), Integer.parseInt(this.var_javax_microedition_lcdui_TextField_g.getString()), Integer.parseInt(this.var_javax_microedition_lcdui_TextField_h.getString()), this.var_int_arr_o);
            }
            aeii.c.a((Displayable)aeii.f.var_aeii_c_a);
            aeii.f.var_aeii_c_a.a(this);
            return;
        }
        if (object == this.var_javax_microedition_lcdui_Form_e) {
            if (objectArray == this.var_javax_microedition_lcdui_Command_a) {
                this.var_javax_microedition_lcdui_ChoiceGroup_a.getSelectedFlags(this.var_boolean_arr_b);
                for (int i = 0; i < this.var_int_arr_o.length; ++i) {
                    this.var_boolean_arr_b[i] = this.var_int_arr_o[i] > 0;
                }
                this.var_javax_microedition_lcdui_ChoiceGroup_a.setSelectedFlags(this.var_boolean_arr_b);
                this.itemStateChanged((Item)this.var_javax_microedition_lcdui_ChoiceGroup_a);
            } else {
                System.arraycopy(this.var_int_arr_p, 0, this.var_int_arr_o, 0, this.var_int_arr_o.length);
            }
            if (this.var_javax_microedition_lcdui_Form_b != null) {
                aeii.c.a((Displayable)this.var_javax_microedition_lcdui_Form_b);
                return;
            }
            if (this.var_javax_microedition_lcdui_Form_a != null) {
                aeii.c.a((Displayable)this.var_javax_microedition_lcdui_Form_a);
                return;
            }
        } else {
            if (object == this.var_javax_microedition_lcdui_Form_f) {
                if (objectArray == this.var_javax_microedition_lcdui_Command_a) {
                    objectArray = new int[]{100 - this.var_javax_microedition_lcdui_Gauge_b.getValue(), 100};
                    for (int i = 0; i < this.var_int_f; ++i) {
                        for (int j = 0; j < this.var_int_g; ++j) {
                            if (this.var_byte_arr_arr_b[i][j] <= 0) continue;
                            this.var_byte_arr_arr_b[i][j] = (byte)aeii.c.a((int[])objectArray);
                        }
                    }
                }
                aeii.c.a((Displayable)aeii.f.var_aeii_c_a);
                aeii.f.var_aeii_c_a.a(this);
                return;
            }
            if (object == this.var_javax_microedition_lcdui_TextBox_a) {
                if (objectArray == this.var_javax_microedition_lcdui_Command_a) {
                    try {
                        objectArray = aeii.c.a(this.var_javax_microedition_lcdui_TextBox_a.getString().toLowerCase(), ' ');
                        if (objectArray[0].equals("win")) {
                            if (this.var_int_x == 7) {
                                this.var_boolean_s = true;
                            } else {
                                this.u();
                            }
                        } else if (objectArray[0].equals("fps")) {
                            aeii.f.var_aeii_c_a.void_c(Integer.parseInt(objectArray[1]));
                        } else if (objectArray[0].equals("money")) {
                            if (objectArray[1].equals("add")) {
                                if (objectArray[2].equals("player")) {
                                    byte by = this.var_byte_b;
                                    this.var_int_arr_b[by] = this.var_int_arr_b[by] + Integer.parseInt(objectArray[3]);
                                } else if (objectArray[2].equals("enemy")) {
                                    for (int i = 0; i < 4; ++i) {
                                        if (this.var_byte_arr_m[i] != 0) continue;
                                        int n = i;
                                        this.var_int_arr_b[n] = this.var_int_arr_b[n] + Integer.parseInt(objectArray[3]);
                                    }
                                } else if (objectArray[2].equals("all")) {
                                    int n = 0;
                                    while (n < 4) {
                                        int n9 = n++;
                                        this.var_int_arr_b[n9] = this.var_int_arr_b[n9] + Integer.parseInt(objectArray[3]);
                                    }
                                }
                            } else if (objectArray[1].equals("remove")) {
                                if (objectArray[2].equals("player")) {
                                    byte by = this.var_byte_b;
                                    this.var_int_arr_b[by] = this.var_int_arr_b[by] - Integer.parseInt(objectArray[3]);
                                } else if (objectArray[2].equals("enemy")) {
                                    for (int i = 0; i < 4; ++i) {
                                        if (this.var_byte_arr_m[i] != 0) continue;
                                        int n = i;
                                        this.var_int_arr_b[n] = this.var_int_arr_b[n] - Integer.parseInt(objectArray[3]);
                                    }
                                } else if (objectArray[2].equals("all")) {
                                    int n = 0;
                                    while (n < 4) {
                                        int n10 = n++;
                                        this.var_int_arr_b[n10] = this.var_int_arr_b[n10] - Integer.parseInt(objectArray[3]);
                                    }
                                }
                            }
                        } else if (objectArray[0].equals("occupy")) {
                            if (objectArray[1].equals("house")) {
                                if (objectArray[2].equals("civilian")) {
                                    for (int i = 0; i < this.var_int_f; ++i) {
                                        for (int j = 0; j < this.var_int_g; ++j) {
                                            if (this.byte_a(i, j) != 8 || this.int_a(i, j) != 0) continue;
                                            this.void_b(i, j, this.var_byte_arr_d[this.var_byte_b]);
                                        }
                                    }
                                } else if (objectArray[2].equals("all")) {
                                    for (int i = 0; i < this.var_int_f; ++i) {
                                        for (int j = 0; j < this.var_int_g; ++j) {
                                            if (this.byte_a(i, j) != 8) continue;
                                            this.void_b(i, j, this.var_byte_arr_d[this.var_byte_b]);
                                        }
                                    }
                                }
                            } else if (objectArray[1].equals("castle")) {
                                if (objectArray[2].equals("civilian")) {
                                    for (int i = 0; i < this.var_int_f; ++i) {
                                        for (int j = 0; j < this.var_int_g; ++j) {
                                            if (this.byte_a(i, j) != 9 || this.int_a(i, j) != 0) continue;
                                            this.void_b(i, j, this.var_byte_arr_d[this.var_byte_b]);
                                        }
                                    }
                                } else if (objectArray[2].equals("all")) {
                                    for (int i = 0; i < this.var_int_f; ++i) {
                                        for (int j = 0; j < this.var_int_g; ++j) {
                                            if (this.byte_a(i, j) != 9) continue;
                                            this.void_b(i, j, this.var_byte_arr_d[this.var_byte_b]);
                                        }
                                    }
                                }
                            }
                        } else if (objectArray[0].equals("unit")) {
                            if (objectArray[1].equals("reset")) {
                                if (objectArray[2].equals("current")) {
                                    object = this.aeii_a_a(this.var_int_h, this.var_int_i, (byte)0);
                                    if (object != null) {
                                        ((a)object).var_byte_f = 0;
                                    }
                                } else if (objectArray[2].equals("player")) {
                                    for (int i = 0; i < this.var_java_util_Vector_a.size(); ++i) {
                                        a a2 = (a)this.var_java_util_Vector_a.elementAt(i);
                                        if (a2.var_byte_e != this.var_byte_b) continue;
                                        a2.var_byte_f = 0;
                                    }
                                } else if (objectArray[2].equals("enemy")) {
                                    for (int i = 0; i < this.var_java_util_Vector_a.size(); ++i) {
                                        a a3 = (a)this.var_java_util_Vector_a.elementAt(i);
                                        if (a3.var_byte_e == this.var_byte_b) continue;
                                        a3.var_byte_f = 0;
                                    }
                                } else if (objectArray[2].equals("all")) {
                                    for (int i = 0; i < this.var_java_util_Vector_a.size(); ++i) {
                                        a a4 = (a)this.var_java_util_Vector_a.elementAt(i);
                                        ((a)this.var_java_util_Vector_a.elementAt(i)).var_byte_f = 0;
                                    }
                                }
                            } else if (objectArray[1].equals("promote")) {
                                if (objectArray[2].equals("current")) {
                                    object = this.aeii_a_a(this.var_int_h, this.var_int_i, (byte)0);
                                    if (object != null) {
                                        ((a)object).void_a((byte)Integer.parseInt(objectArray[3]));
                                    }
                                } else if (objectArray[2].equals("player")) {
                                    byte by = (byte)Integer.parseInt(objectArray[3]);
                                    for (int i = 0; i < this.var_java_util_Vector_a.size(); ++i) {
                                        a a5 = (a)this.var_java_util_Vector_a.elementAt(i);
                                        if (a5.var_byte_e != this.var_byte_b) continue;
                                        a5.void_a(by);
                                    }
                                } else if (objectArray[2].equals("enemy")) {
                                    byte by = (byte)Integer.parseInt(objectArray[3]);
                                    for (int i = 0; i < this.var_java_util_Vector_a.size(); ++i) {
                                        a a6 = (a)this.var_java_util_Vector_a.elementAt(i);
                                        if (a6.var_byte_e == this.var_byte_b) continue;
                                        a6.void_a(by);
                                    }
                                } else if (objectArray[2].equals("all")) {
                                    byte by = (byte)Integer.parseInt(objectArray[3]);
                                    for (int i = 0; i < this.var_java_util_Vector_a.size(); ++i) {
                                        a a7 = (a)this.var_java_util_Vector_a.elementAt(i);
                                        a7.void_a(by);
                                    }
                                }
                            }
                        }
                    }
                    catch (Exception exception) {
                        return;
                    }
                }
                aeii.c.a((Displayable)aeii.f.var_aeii_c_a);
                aeii.f.var_aeii_c_a.a(this);
            }
        }
    }

    public final void itemStateChanged(Item item) {
        if (item == this.var_javax_microedition_lcdui_ChoiceGroup_a) {
            int n;
            this.var_javax_microedition_lcdui_ChoiceGroup_a.getSelectedFlags(this.var_boolean_arr_b);
            int n2 = 0;
            for (n = 0; n < this.var_int_arr_o.length; ++n) {
                if (this.var_int_arr_o[n] <= n2) continue;
                n2 = this.var_int_arr_o[n];
            }
            if (n2 == 0) {
                n2 = 100;
            }
            for (n = 0; n < this.var_boolean_arr_b.length; ++n) {
                if (this.var_boolean_arr_b[n]) {
                    if (this.var_int_arr_o[n] > 0) continue;
                    this.var_int_arr_o[n] = n2;
                    continue;
                }
                this.var_int_arr_o[n] = 0;
            }
            return;
        }
        if (item == this.var_javax_microedition_lcdui_Gauge_a) {
            this.var_javax_microedition_lcdui_Gauge_a.setLabel(aeii.f.a(308, Integer.toString(this.var_javax_microedition_lcdui_Gauge_a.getValue() + 1)));
            this.var_javax_microedition_lcdui_ImageItem_a.setImage(this.var_javax_microedition_lcdui_ChoiceGroup_a.getImage(this.var_javax_microedition_lcdui_Gauge_a.getValue()));
            this.var_javax_microedition_lcdui_TextField_d.setString(Integer.toString(this.var_int_arr_o[this.var_javax_microedition_lcdui_Gauge_a.getValue()]));
            return;
        }
        if (item == this.var_javax_microedition_lcdui_TextField_d) {
            if (this.var_javax_microedition_lcdui_TextField_d.size() > 0) {
                this.var_int_arr_o[this.var_javax_microedition_lcdui_Gauge_a.getValue()] = Integer.parseInt(this.var_javax_microedition_lcdui_TextField_d.getString());
                return;
            }
        } else if (item == this.var_javax_microedition_lcdui_Gauge_b) {
            this.var_javax_microedition_lcdui_Gauge_b.setLabel(aeii.f.a(315, Integer.toString(this.var_javax_microedition_lcdui_Gauge_b.getValue()) + "%"));
        }
    }

    private void x() {
        if (this.var_javax_microedition_lcdui_Form_c != null) {
            return;
        }
        this.var_javax_microedition_lcdui_Form_c = new Form(aeii.f.java_lang_String_a(304));
        this.var_javax_microedition_lcdui_TextField_a = new TextField(aeii.f.java_lang_String_a(294), this.var_java_lang_String_b, 100, 0);
        this.var_javax_microedition_lcdui_Form_c.append((Item)this.var_javax_microedition_lcdui_TextField_a);
        this.var_javax_microedition_lcdui_Form_c.addCommand(this.var_javax_microedition_lcdui_Command_a);
        this.var_javax_microedition_lcdui_Form_c.addCommand(this.var_javax_microedition_lcdui_Command_f);
        this.var_javax_microedition_lcdui_Form_c.addCommand(this.var_javax_microedition_lcdui_Command_b);
        this.var_javax_microedition_lcdui_Form_c.setCommandListener((CommandListener)this);
    }

    private void y() {
        if (this.var_javax_microedition_lcdui_Form_d != null) {
            return;
        }
        this.var_javax_microedition_lcdui_Form_d = new Form(aeii.f.java_lang_String_a(306));
        this.var_javax_microedition_lcdui_TextField_e = new TextField(aeii.f.java_lang_String_a(310), "0", 20, 2);
        this.var_javax_microedition_lcdui_TextField_f = new TextField(aeii.f.java_lang_String_a(311), "0", 20, 2);
        this.var_javax_microedition_lcdui_TextField_g = new TextField(aeii.f.java_lang_String_a(312), "0", 20, 2);
        this.var_javax_microedition_lcdui_TextField_h = new TextField(aeii.f.java_lang_String_a(313), "0", 20, 2);
        this.var_javax_microedition_lcdui_Form_d.append((Item)this.var_javax_microedition_lcdui_TextField_e);
        this.var_javax_microedition_lcdui_Form_d.append((Item)this.var_javax_microedition_lcdui_TextField_f);
        this.var_javax_microedition_lcdui_Form_d.append((Item)this.var_javax_microedition_lcdui_TextField_g);
        this.var_javax_microedition_lcdui_Form_d.append((Item)this.var_javax_microedition_lcdui_TextField_h);
        this.var_javax_microedition_lcdui_Form_d.addCommand(this.var_javax_microedition_lcdui_Command_a);
        this.var_javax_microedition_lcdui_Form_d.addCommand(this.var_javax_microedition_lcdui_Command_b);
        this.var_javax_microedition_lcdui_Form_d.setCommandListener((CommandListener)this);
    }

    private void z() {
        if (this.var_javax_microedition_lcdui_Form_f != null) {
            return;
        }
        this.var_javax_microedition_lcdui_Form_f = new Form(aeii.f.java_lang_String_a(307));
        this.var_javax_microedition_lcdui_Gauge_b = new Gauge("", true, 100, 100);
        this.var_javax_microedition_lcdui_Gauge_b.setLayout(2049);
        this.itemStateChanged((Item)this.var_javax_microedition_lcdui_Gauge_b);
        this.var_javax_microedition_lcdui_Form_f.append((Item)this.var_javax_microedition_lcdui_Gauge_b);
        this.var_javax_microedition_lcdui_Form_f.addCommand(this.var_javax_microedition_lcdui_Command_a);
        this.var_javax_microedition_lcdui_Form_f.addCommand(this.var_javax_microedition_lcdui_Command_b);
        this.var_javax_microedition_lcdui_Form_f.setCommandListener((CommandListener)this);
        this.var_javax_microedition_lcdui_Form_f.setItemStateListener((ItemStateListener)this);
    }

    private void A() {
        int n;
        if (this.var_javax_microedition_lcdui_Form_b != null) {
            return;
        }
        this.var_javax_microedition_lcdui_Form_b = new Form(aeii.f.java_lang_String_a(303));
        this.var_javax_microedition_lcdui_ChoiceGroup_a = new ChoiceGroup(aeii.f.java_lang_String_a(297), 2);
        if (this.var_int_arr_o == null) {
            this.var_int_arr_o = new int[this.var_aeii_d_arr_d.length];
            for (n = 0; n < this.var_int_arr_o.length; ++n) {
                this.var_int_arr_o[n] = 100;
            }
        }
        this.var_int_arr_p = new int[this.var_aeii_d_arr_d.length];
        this.var_boolean_arr_b = new boolean[this.var_aeii_d_arr_d.length];
        for (n = 0; n < this.var_aeii_d_arr_d.length; ++n) {
            this.var_javax_microedition_lcdui_ChoiceGroup_a.append(" " + aeii.f.a(308, Integer.toString(n + 1)) + " ", this.var_aeii_d_arr_d[n].a());
            this.var_javax_microedition_lcdui_ChoiceGroup_a.setSelectedIndex(n, this.var_int_arr_o[n] > 0);
        }
        this.itemStateChanged((Item)this.var_javax_microedition_lcdui_ChoiceGroup_a);
        this.var_javax_microedition_lcdui_Form_b.append((Item)this.var_javax_microedition_lcdui_ChoiceGroup_a);
        this.var_javax_microedition_lcdui_Form_b.addCommand(this.var_javax_microedition_lcdui_Command_a);
        this.var_javax_microedition_lcdui_Form_b.addCommand(this.var_javax_microedition_lcdui_Command_e);
        this.var_javax_microedition_lcdui_Form_b.addCommand(this.var_javax_microedition_lcdui_Command_c);
        this.var_javax_microedition_lcdui_Form_b.addCommand(this.var_javax_microedition_lcdui_Command_d);
        this.var_javax_microedition_lcdui_Form_b.addCommand(this.var_javax_microedition_lcdui_Command_b);
        this.var_javax_microedition_lcdui_Form_b.setCommandListener((CommandListener)this);
        this.var_javax_microedition_lcdui_Form_b.setItemStateListener((ItemStateListener)this);
        this.D();
    }

    private void B() {
        this.var_javax_microedition_lcdui_Form_a = new Form(aeii.f.java_lang_String_a(292));
        this.var_javax_microedition_lcdui_TextField_a = new TextField(aeii.f.java_lang_String_a(294), Long.toString(System.currentTimeMillis(), 36).toUpperCase(), 100, 0);
        int n = (((f)this).j + 23) / 24;
        int n2 = (((f)this).k - var_byte_a + 23) / 24;
        this.var_javax_microedition_lcdui_TextField_b = new TextField(aeii.f.java_lang_String_a(295), Integer.toString(n), 3, 2);
        this.var_javax_microedition_lcdui_TextField_c = new TextField(aeii.f.java_lang_String_a(296), Integer.toString(n2), 3, 2);
        this.var_javax_microedition_lcdui_ChoiceGroup_a = new ChoiceGroup(aeii.f.java_lang_String_a(297), 2);
        if (this.var_int_arr_o == null) {
            this.var_int_arr_o = new int[this.var_aeii_d_arr_d.length];
            for (n = 0; n < this.var_int_arr_o.length; ++n) {
                this.var_int_arr_o[n] = 100;
            }
        }
        this.var_int_arr_p = new int[this.var_aeii_d_arr_d.length];
        this.var_boolean_arr_b = new boolean[this.var_aeii_d_arr_d.length];
        for (n = 0; n < this.var_aeii_d_arr_d.length; ++n) {
            this.var_javax_microedition_lcdui_ChoiceGroup_a.append(" " + aeii.f.a(308, Integer.toString(n + 1)) + " ", this.var_aeii_d_arr_d[n].a());
            this.var_javax_microedition_lcdui_ChoiceGroup_a.setSelectedIndex(n, this.var_int_arr_o[n] > 0);
        }
        this.itemStateChanged((Item)this.var_javax_microedition_lcdui_ChoiceGroup_a);
        this.var_javax_microedition_lcdui_Form_a.append((Item)this.var_javax_microedition_lcdui_TextField_a);
        this.var_javax_microedition_lcdui_Form_a.append((Item)this.var_javax_microedition_lcdui_TextField_b);
        this.var_javax_microedition_lcdui_Form_a.append((Item)this.var_javax_microedition_lcdui_TextField_c);
        this.var_javax_microedition_lcdui_Form_a.append((Item)this.var_javax_microedition_lcdui_ChoiceGroup_a);
        this.var_javax_microedition_lcdui_Form_a.addCommand(this.var_javax_microedition_lcdui_Command_a);
        this.var_javax_microedition_lcdui_Form_a.addCommand(this.var_javax_microedition_lcdui_Command_e);
        this.var_javax_microedition_lcdui_Form_a.addCommand(this.var_javax_microedition_lcdui_Command_c);
        this.var_javax_microedition_lcdui_Form_a.addCommand(this.var_javax_microedition_lcdui_Command_d);
        this.var_javax_microedition_lcdui_Form_a.addCommand(this.var_javax_microedition_lcdui_Command_b);
        this.var_javax_microedition_lcdui_Form_a.setCommandListener((CommandListener)this);
        this.var_javax_microedition_lcdui_Form_a.setItemStateListener((ItemStateListener)this);
        this.D();
    }

    private void C() {
        this.var_javax_microedition_lcdui_Form_a = null;
        this.var_javax_microedition_lcdui_TextField_a = null;
        this.var_javax_microedition_lcdui_TextField_b = null;
        this.var_javax_microedition_lcdui_TextField_c = null;
        this.var_javax_microedition_lcdui_ChoiceGroup_a = null;
    }

    private void D() {
        if (this.var_javax_microedition_lcdui_Form_e != null) {
            return;
        }
        this.var_javax_microedition_lcdui_Form_e = new Form(aeii.f.java_lang_String_a(304));
        this.var_javax_microedition_lcdui_ImageItem_a = new ImageItem(null, null, 1, null);
        this.var_javax_microedition_lcdui_ImageItem_a.setLayout(2049);
        this.var_javax_microedition_lcdui_Gauge_a = new Gauge("", true, this.var_aeii_d_arr_d.length - 1, 0);
        this.var_javax_microedition_lcdui_Gauge_a.setLayout(2049);
        this.var_javax_microedition_lcdui_TextField_d = new TextField(aeii.f.java_lang_String_a(309), "", 20, 2);
        this.var_javax_microedition_lcdui_TextField_d.setLayout(2049);
        this.var_javax_microedition_lcdui_Form_e.append((Item)this.var_javax_microedition_lcdui_ImageItem_a);
        this.var_javax_microedition_lcdui_Form_e.append((Item)this.var_javax_microedition_lcdui_Gauge_a);
        this.var_javax_microedition_lcdui_Form_e.append((Item)this.var_javax_microedition_lcdui_TextField_d);
        this.var_javax_microedition_lcdui_Form_e.addCommand(this.var_javax_microedition_lcdui_Command_a);
        this.var_javax_microedition_lcdui_Form_e.addCommand(this.var_javax_microedition_lcdui_Command_b);
        this.var_javax_microedition_lcdui_Form_e.setCommandListener((CommandListener)this);
        this.var_javax_microedition_lcdui_Form_e.setItemStateListener((ItemStateListener)this);
    }

    private void E() {
        g g2 = this;
        this.var_javax_microedition_lcdui_Form_b = null;
        g2.var_javax_microedition_lcdui_ChoiceGroup_a = null;
        v0.var_int_arr_o = null;
        g2.var_int_arr_p = null;
        g2.var_javax_microedition_lcdui_Form_e = null;
        g2.var_javax_microedition_lcdui_TextField_d = null;
        g2.var_javax_microedition_lcdui_Gauge_a = null;
        g2.var_javax_microedition_lcdui_ImageItem_a = null;
        g2 = this;
        this.var_javax_microedition_lcdui_Form_c = null;
        g2.var_javax_microedition_lcdui_TextField_a = null;
        g2 = this;
        this.var_javax_microedition_lcdui_Form_d = null;
        g2.var_javax_microedition_lcdui_TextField_e = null;
        g2.var_javax_microedition_lcdui_TextField_f = null;
        g2.var_javax_microedition_lcdui_TextField_g = null;
        g2.var_javax_microedition_lcdui_TextField_h = null;
        g2 = this;
        this.var_javax_microedition_lcdui_Form_f = null;
        g2.var_javax_microedition_lcdui_Gauge_b = null;
        this.var_byte_n = 0;
    }

    private void F() {
        if (this.var_javax_microedition_lcdui_TextBox_a != null) {
            return;
        }
        this.var_javax_microedition_lcdui_TextBox_a = new TextBox(aeii.f.java_lang_String_a(314), "", 100, 0);
        this.var_javax_microedition_lcdui_TextBox_a.addCommand(this.var_javax_microedition_lcdui_Command_a);
        this.var_javax_microedition_lcdui_TextBox_a.addCommand(this.var_javax_microedition_lcdui_Command_b);
        this.var_javax_microedition_lcdui_TextBox_a.setCommandListener((CommandListener)this);
    }

    static {
        var_byte_a = (byte)32;
        var_java_lang_String_arr_e = new String[12];
        var_int_arr_c = new int[]{-1, -1, -1, -1, -1, -1, -1, -1};
        var_int_arr_d = new int[]{500, 1000, 2000, 5000, 10000, 25000, 50000, 75000, 100000, 150000, 200000};
        var_int_arr_e = new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200};
        var_int_d = 1024;
        var_int_e = 2048;
        var_byte_arr_arr_c = new byte[][]{{0, 1}, {2, 3, 4}, {0, 1}, {5}};
        var_byte_arr_i = new byte[]{0};
        int[][][] nArrayArray = new int[][][]{new int[0][], new int[][]{{150, 217, 244}, {65, 149, 233}, {0, 100, 198}, {12, 53, 112}}, new int[][]{{244, 158, 156}, {219, 36, 113}, {161, 0, 112}, {95, 5, 120}}, new int[][]{{171, 237, 90}, {99, 190, 37}, {0, 153, 55}, {0, 85, 82}}, new int[][]{{0, 118, 150}, {0, 65, 114}, {0, 43, 75}, {0, 22, 48}}};
        var_java_lang_String_arr_a = new String[]{"blue", "red", "green", "black"};
        var_int_arr_f = new int[]{0xA0A0A0, 26054, 15204434, 39473, 16754};
        var_int_arr_g = new int[]{-1, 2, 3, 2, 3};
        var_int_arr_h = new int[]{-1, 4, 5, 4, 5};
        var_byte_arr_j = new byte[]{1, 2};
        var_byte_arr_a = new byte[]{0, 5, 10, 10, 15, 0, 5, 15, 15, 15};
        var_byte_arr_b = new byte[]{1, 1, 2, 2, 3, 3, 1, 1, 1, 1};
        var_int_arr_i = new int[]{85, 83, 83, 83, 83, 83, 83, 83, 83, 175, 84, 84, 84, 175, 147, 159, 151, 155, 167, 171};
        var_int_arr_j = new int[]{15, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214};
        var_byte_arr_p = new byte[]{0, 2, 3, 3, 1, 3, 3, 3, 3, 3, 3, 3};
    }
}

