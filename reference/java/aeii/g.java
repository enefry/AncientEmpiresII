package aeii;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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

public final class g extends f implements Runnable, CommandListener, ItemStateListener {
   private static String[] b;
   private static String[] c;
   private static String[] d;
   private String a = "?";
   public static byte a = 32;
   public int a;
   public int b;
   public int c;
   private int p;
   private int q = 0;
   private static String[] e = new String[12];
   private static int[] c = new int[]{-1, -1, -1, -1, -1, -1, -1, -1};
   private boolean[] a;
   private static int[] d = new int[]{500, 1000, 2000, 5000, 10000, 25000, 50000, 75000, 100000, 150000, 200000};
   private static int[] e = new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200};
   private int r;
   private int s;
   private byte c;
   public static int d = 1024;
   public static int e = 2048;
   private String[] f;
   private String[] g;
   public d[] a;
   private String[] h;
   private d[] c;
   private String[] i;
   private String[] j;
   private byte[] f;
   private byte[] g;
   private byte[] h;
   private static byte[][] c = new byte[][]{{0, 1}, {2, 3, 4}, {0, 1}, {5}};
   private static byte[] i = new byte[]{0};
   private long b;
   private long c;
   public static final String[] a;
   private static int[] f;
   private static int[] g;
   private static int[] h;
   public a.a.a.g[][] a;
   private static byte[] j;
   public static byte[] a;
   public static byte[] b;
   public d[] b;
   private byte[] k;
   public byte[] c;
   private int t;
   private int u;
   private int v;
   private int w;
   public int f;
   public int g;
   private d a;
   private d[] d;
   private a.a.a.g q;
   private a.a.a.g r;
   public a.a.a.g a;
   public a.a.a.g b;
   public a.a.a.g c;
   public a.a.a.g d;
   public a.a.a.g e;
   public a.a.a.g f;
   public a.a.a.g g;
   private a.a.a.g s;
   public a.a.a.g h;
   public a.a.a.g i;
   public int h;
   public int i;
   public byte[][] a;
   private byte d;
   private byte e;
   public long a;
   private int x;
   private int y;
   private int z;
   private a[] a;
   private a b;
   private int A;
   private int B;
   public byte[][] b;
   private boolean b;
   private boolean c;
   private boolean d;
   public Vector a;
   private Vector b;
   private int C;
   private int D;
   private long d;
   private byte f;
   private byte[] l;
   public byte[] d;
   public byte[] e;
   public byte b;
   private short a;
   private a[] b;
   public a[][] a;
   public int[] a;
   public int[] b;
   private byte[][] d;
   private byte[] m;
   private e a;
   private Vector c;
   private Vector d;
   private a c;
   private a d;
   private long e;
   private a e;
   private byte g;
   private long f;
   private int E;
   private boolean e;
   private boolean f;
   private byte h;
   private d b;
   private d c;
   private d d;
   private d e;
   private int F;
   private boolean g;
   private int G;
   private int H;
   private int I;
   private long g;
   private a f;
   private a g;
   private boolean h;
   private long h;
   private boolean i;
   private long i;
   private int J;
   private int K;
   private d[] e;
   private boolean j;
   private boolean k;
   private static int[] i;
   private static int[] j;
   private int L;
   private e b;
   private e c;
   private e d;
   private e e;
   public int n;
   private e f;
   private e g;
   private e h;
   private boolean l;
   private int M;
   private byte[][] e;
   private byte[][] f;
   public a.a.a.g j;
   public a.a.a.g k;
   private a h;
   public a.a.a.g l;
   public a.a.a.g m;
   private int N;
   private int O;
   private int P;
   private boolean m;
   private int Q;
   private boolean n;
   public a.a.a.g n;
   private a.a.a.g[] b;
   public a.a.a.g o;
   private e i;
   private e j;
   private e k;
   private e l;
   private e[] a;
   private e[] b;
   private e m;
   private e[] c;
   private a.a.a.g t;
   private int R;
   private boolean o;
   private boolean p;
   private boolean q;
   private Vector e;
   private a.a.a.g u;
   private int S;
   public a.a.a.g[] a;
   private e n;
   private e o;
   private e p;
   private e q;
   public a.a.a.g p;
   private a.a.a.g v;
   private a i;
   private int T;
   private boolean r;
   private boolean s;
   private String b;
   private String[] k;
   private byte[] n;
   private int[] k;
   private e r;
   private e s;
   private e t;
   private e u;
   private e v;
   private e w;
   private e x;
   private byte i;
   private byte[] o;
   private int U;
   private String[] l;
   private int[] l;
   private String[] m;
   private String[] n;
   private e y;
   private e z;
   private e A;
   private e B;
   private e C;
   private e D;
   private e E;
   private e F;
   private int V;
   private String c;
   private int W;
   private boolean t;
   private boolean u;
   private boolean v;
   private d f;
   private Image[][] a;
   private int X;
   private a j;
   private int Y;
   private int Z;
   private boolean w;
   private int aa;
   private int ab;
   private int ac;
   private int ad;
   private int ae;
   private d g;
   private String[] o;
   private boolean x;
   private byte j;
   private int af;
   private int ag;
   private int ah;
   private int ai;
   private int aj;
   private int ak;
   private int al;
   private int am;
   private int an;
   private static byte[] p;
   private byte k;
   private int ao;
   private int ap;
   private a k;
   private a l;
   private a m;
   private int aq;
   private long j;
   private a[] c;
   private a[] d;
   private byte[] q;
   private int[][] a;
   private int[] m;
   private int ar;
   private byte[][] g;
   private int as;
   private int at;
   private int au;
   private int av;
   private int aw;
   private Vector f;
   private boolean y;
   private int ax;
   private e G;
   private a n;
   private a o;
   private a p;
   private a q;
   private a r;
   public a a;
   private int ay;
   private String[][][] a;
   private long k;
   private int az;
   private boolean z;
   private boolean A;
   private int aA;
   private int aB;
   private int aC;
   private h a;
   private h b;
   private long l;
   private boolean B;
   private Vector g;
   private boolean C;
   private long m;
   private long n;
   private boolean D;
   private boolean E;
   private boolean F;
   public boolean a;
   public int o;
   private int aD;
   private int aE;
   private String[] p;
   private String[] q;
   private String[] r;
   private String[] s;
   private String[] t;
   private int[] n;
   private String d;
   private String e;
   private String f;
   private ByteArrayOutputStream a;
   private DataOutputStream a;
   private int aF;
   private boolean G;
   private f a;
   private int aG;
   private e H;
   private e I;
   private e J;
   private e K;
   private b a;
   private String[] u;
   private byte l;
   private byte m;
   private a s;
   private byte n;
   private e L;
   private e M;
   private e N;
   private e O;
   private int[] o;
   private int[] p;
   private boolean[] b;
   private Form a;
   private Form b;
   private Form c;
   private Form d;
   private Form e;
   private Form f;
   private TextField a;
   private TextField b;
   private TextField c;
   private ChoiceGroup a;
   private TextField d;
   private Gauge a;
   private ImageItem a;
   private TextField e;
   private TextField f;
   private TextField g;
   private TextField h;
   private TextBox a;
   private Gauge b;
   private Command a;
   private Command b;
   private Command c;
   private Command d;
   private Command e;
   private Command f;

   public g() {
      super();
      b = new String[]{".."};
      c = new String[]{aeii.f.a(300), ".."};
      d = new String[]{aeii.f.a(305), aeii.f.a(306), aeii.f.a(307), aeii.f.a(303), aeii.f.a(70), aeii.f.a(293), aeii.f.a(60)};
      this.n = 0;
      this.a = new Command(aeii.f.a(12), 4, 1);
      this.b = new Command(aeii.f.a(13), 3, 5);
      this.c = new Command(aeii.f.a(301), 4, 3);
      this.d = new Command(aeii.f.a(302), 4, 4);
      this.e = new Command(aeii.f.a(304), 4, 2);
      this.f = new Command(aeii.f.a(316), 4, 2);
      if (aeii.c.a) {
         this.f = new String[]{aeii.f.a(66), aeii.f.a(70), aeii.f.a(71), aeii.f.a(4), aeii.f.a(314), aeii.f.a(60)};
      } else {
         this.f = new String[]{aeii.f.a(66), aeii.f.a(70), aeii.f.a(71), aeii.f.a(4), aeii.f.a(60)};
      }

      this.g = new String[]{aeii.f.a(63), aeii.f.a(67), aeii.f.a(68), aeii.f.a(62), aeii.f.a(69), aeii.f.a(61), aeii.f.a(64)};
      this.a = new d[this.g.length];
      this.h = new String[]{aeii.f.a(1), aeii.f.a(2), aeii.f.a(5), aeii.f.a(3), aeii.f.a(6), aeii.f.a(8), aeii.f.a(7), aeii.f.a(9), aeii.f.a(10), aeii.f.a(11), aeii.f.a(4)};
      this.c = new d[this.h.length];
      this.i = new String[]{aeii.f.a(35), aeii.f.a(36), aeii.f.a(37)};
      this.j = new String[]{aeii.f.a(29), aeii.f.a(30)};
      this.f = new byte[]{0, 6, 5, 7, 8, 9};
      this.g = new byte[]{0, 6, 5, 7, 8, 9};
      this.h = new byte[]{1, 2, 3, 4};
      this.b = 0L;
      this.b = false;
      this.c = false;
      this.d = true;
      this.a = new Vector();
      this.f = 2;
      this.l = new byte[5];
      this.d = new byte[4];
      this.e = new byte[4];
      this.b = 0;
      this.b = new int[4];
      this.d = new byte[4][2];
      this.m = new byte[4];
      this.c = new Vector();
      this.d = new Vector();
      this.e = false;
      this.f = false;
      this.g = false;
      this.h = true;
      this.i = false;
      this.j = false;
      this.L = -1;
      this.n = 8;
      this.l = false;
      this.m = true;
      this.b = new a.a.a.g[0];
      this.e = new Vector(2);
      this.T = 0;
      this.r = true;
      this.m = new String[]{aeii.f.a(46), aeii.f.a(47), aeii.f.a(291)};
      this.n = new String[]{aeii.f.a(289), aeii.f.a(298), aeii.f.a(48), aeii.f.a(49)};
      this.t = true;
      this.u = true;
      this.v = true;
      new StringBuffer();
      this.j = null;
      this.Y = 12;
      this.Z = 1;
      this.ai = 0;
      this.ak = 24;
      this.al = 8;
      this.am = this.al >> 1;
      this.k = 0;
      this.y = false;
      this.r = null;
      this.a = null;
      this.z = false;
      this.A = false;
      this.aA = -1;
      this.aB = -1;
      this.aC = 0;
      this.g = new Vector();
      this.C = false;
      this.d = "Macrospace";
      this.e = "msaeii";
      this.f = "http://msaeii.scores.macrospace.com/connectx/in";
      this.h = 4;
   }

   public final void d() {
      this.b((int)0);
      aeii.c.g();
      this.b((int)18);
      aeii.c.e();

      for(int var1 = 0; var1 < aeii.c.b.length; ++var1) {
         aeii.c.e(var1);
         this.b(var1 + 19);
      }

      this.b((int)28);
      aeii.e.a = this;
      this.b((int)29);
      aeii.f.c();
      this.b((int)30);
      aeii.c.d();
      this.b((int)32);
      this.a = (new a.a.a.g("action_icons")).a;
      this.b((int)34);
      this.c = (new a.a.a.g("menu_icons")).a;
      this.b((int)36);
      this.l = new a.a.a.g("hud_icons");
      this.b((int)38);
      this.m = new a.a.a.g("hud_icons_2");
      this.b((int)40);
      this.b = new a.a.a.g("arrow");
      this.b((int)42);
      this.a = new a.a.a.g("side_arrow");
      this.b((int)44);
      this.c = new a.a.a.g("buttons");
      this.b((int)46);
      this.d = new a.a.a.g("menu");
      this.b((int)48);
      this.j = new a.a.a.g("big_circle");
      this.b((int)50);
      this.k = new a.a.a.g("small_circle");
      this.b((int)52);
      this.h = new a.a.a.g("small_spark");
      this.b((int)54);
      this.t = new a.a.a.g("alpha");
      this.b((int)56);

      try {
         this.f = new d("gameover");
      } catch (Exception var9) {
      }

      this.b((int)58);
      this.b = new d("ms_logo");
      this.b((int)62);
      InputStream var11;
      if ((var11 = aeii.c.a("tiles0.prop")) == null) {
         DataInputStream var12;
         short var15 = (var12 = new DataInputStream(aeii.c.a("tiles0.bin"))).readShort();
         var12.readShort();
         this.k = new byte[var15];

         for(int var20 = 0; var20 < var15; ++var20) {
            this.k[var20] = var12.readByte();
         }

         var12.close();
      } else {
         String var3;
         while((var3 = aeii.c.a(var11)) != null) {
            int var4;
            if ((var4 = var3.indexOf(59)) >= 0) {
               var3 = var3.substring(0, var4);
            }

            if ((var3 = var3.trim()).length() != 0) {
               String[] var2;
               if ((var2 = aeii.c.a(var3, ' '))[0].equalsIgnoreCase("TypeCount")) {
                  b = new byte[var4 = Integer.parseInt(var2[1])];
                  a = new byte[var4];
                  a = new String[var4];
                  aeii.h.b = new String[var4];
               } else if (var2[0].equalsIgnoreCase("TypeDef")) {
                  var4 = Integer.parseInt(var2[1]);
                  b[var4] = (byte)Integer.parseInt(var2[2]);
                  a[var4] = (byte)Integer.parseInt(var2[3]);
                  aeii.h.a[var4] = var2[4];
                  aeii.h.b[var4] = var2[5];
               } else if (var2[0].equalsIgnoreCase("TileCount")) {
                  this.k = new byte[Integer.parseInt(var2[1])];
                  this.c = new byte[this.k.length];
               } else if (var2[0].equalsIgnoreCase("TileDef")) {
                  var4 = Integer.parseInt(var2[1]);
                  this.k[var4] = (byte)Integer.parseInt(var2[2]);
                  this.c[var4] = (byte)Integer.parseInt(var2[3]);
               }
            }
         }

         var11.close();
      }

      this.b((int)64);
      a.a.a.g var16 = new a.a.a.g("stiles0");
      this.b = var16.a;
      this.b((int)70);
      this.n = new a.a.a.g("mini_icons");
      this.b((int)72);
      this.d = (new a.a.a.g("tiles0")).a;
      this.a = super.j;
      this.b = super.k;
      this.c = this.a >> 1;
      this.p = this.b >> 1;
      this.F = 0;

      for(int var21 = 0; var21 < 12; ++var21) {
         e[var21] = aeii.f.a(var21 + 101);
      }

      f();
      this.b((int)74);

      try {
         this.q = aeii.c.a((String)"settings", (int)1)[0];
      } catch (Exception var8) {
      }

      this.b((int)76);
      this.l = new String[0];
      this.l = new int[0];

      try {
         byte[] var27 = aeii.c.a((String)"settings", (int)2);
         DataInputStream var13 = new DataInputStream(new ByteArrayInputStream(var27));
         this.U = var13.readInt();
         this.l = new int[this.U];
         this.l = new String[this.U];

         for(int var17 = 0; var17 < this.U; ++var17) {
            this.l[var17] = var13.readInt();
            this.l[var17] = var13.readUTF();
         }

         var13.close();
      } catch (Exception var10) {
      }

      this.b((int)80);
      this.W = aeii.c.a("download");
      this.b((int)84);
      this.k = new String[10];
      this.n = new byte[10];
      this.k = new int[10];

      for(int var22 = 0; var22 < 10; ++var22) {
         this.n[var22] = -1;
         this.k[var22] = -1;
         byte[] var28 = null;

         try {
            var28 = aeii.c.a("save", var22);
         } catch (Exception var7) {
         }

         if (var28 != null && var28.length != 0) {
            DataInputStream var14;
            byte var18 = (var14 = new DataInputStream(new ByteArrayInputStream(var28))).readByte();
            byte var29 = var14.readByte();
            var14.readByte();
            var14.readByte();
            byte var5 = var14.readByte();
            short var6 = var14.readShort();
            var14.close();
            this.n[var22] = var5;
            this.k[var22] = this.a((int)var18, var29, (int)var6);
            this.k[var22] = var29;
         } else {
            this.k[var22] = "\n" + aeii.f.a(79) + "\n ";
         }
      }

      this.b((int)90);
      int var23 = 0;
      String var30;
      if ((var30 = Main.a.getAppProperty("ProvisionX-Highscore-gameCode")) != null) {
         this.e = var30.trim();
      }

      if ((var30 = Main.a.getAppProperty("ProvisionX-Highscore-portalCode")) != null) {
         this.d = var30.trim();
      }

      if ((var30 = Main.a.getAppProperty("ProvisionX-Highscore-Url")) != null) {
         this.f = var30.trim();
      }

      if ((var30 = Main.a.getAppProperty("ms-highscoreUpload")) != null) {
         this.t = Integer.parseInt(var30.trim()) == 1;
      }

      if ((var30 = Main.a.getAppProperty("ms-skPos")) != null) {
         var23 = Integer.parseInt(var30.trim());
      }

      if ((var30 = Main.a.getAppProperty("MIDlet-Version")) != null) {
         this.a = var30.trim();
      }

      this.b((int)96);
      if (var23 == 1) {
         d = 2048;
         e = 1024;
      }

      this.b((int)100);
      aeii.c.b(0, 0);
      this.h = 0;
   }

   private void b(int var1) {
      this.X = var1;
      aeii.f.a.a();
   }

   private String a(int var1, int var2, int var3) {
      String var4;
      if (var1 == 0) {
         var4 = aeii.f.a(var2 + 121);
      } else {
         var4 = this.b(var2);
      }

      return aeii.f.a(var1 + 32) + "\n" + var4 + "\n" + "Current turn: " + (var3 + 1);
   }

   private boolean a() {
      return this.m && aeii.f.a.a == this;
   }

   private void e() {
      this.b = null;
      this.c = null;
      this.d = null;
      this.e = null;
      System.gc();
      this.b = super.k - a;
      this.p = this.b >> 1;
      aeii.c.f();
      if (this.h != 1) {
         this.h = 1;
         aeii.a.a(this);
         aeii.c.g();
         this.a = new a.a.a.g[8][12];

         for(byte var1 = 0; var1 < 8; ++var1) {
            a.a.a.g var4;
            int var5 = (var4 = new a.a.a.g("unit_icons", var1)).d() / 12;

            for(byte var2 = 0; var2 < 12; ++var2) {
               if ((var1 & 1) == 1) {
                  d[] var9 = new d[]{var4.a[var2]};
                  this.a[var1][var2] = new a.a.a.g(var9);
               } else {
                  d[] var3 = new d[var5];

                  for(int var6 = 0; var6 < var5; ++var6) {
                     var3[var6] = var4.a[var6 * 12 + var2];
                  }

                  this.a[var1][var2] = new a.a.a.g(var3);
               }
            }
         }

         this.a = new Image[2][this.d.length];

         for(byte var7 = 0; var7 < 2; ++var7) {
            for(byte var8 = 0; var8 < this.d.length; ++var8) {
               this.a[var7][var8] = Image.createImage(24, 24);
               Graphics var10 = this.a[var7][var8].getGraphics();
               this.d[var8].a(var10, 0, 0);
               this.t.a(var10, var7, 0, 0, 0);
            }
         }

         this.i = new a.a.a.g("portraits");
         this.q = new a.a.a.g("cursor");
         this.g = new a.a.a.g("redspark");
         this.e = new a.a.a.g("smoke");
         this.f = new a.a.a.g("spark");
         this.s = new a.a.a.g("status");
         this.p = new a.a.a.g("arrow_icons");
         this.a = new d("tombstone");
         this.u = new a.a.a.g("levelup");
         this.a = new a.a.a.g[2];
         this.a[0] = new a.a.a.g("king_head_icons");
         this.a[1] = new a.a.a.g("king_head_icons", (byte)0);
         this.q.a(c[0]);
         this.r = new a.a.a.g(this.q);
         this.r.a(c[3]);
         this.e = new d[2];
         this.K = j[0];
         this.e[0] = this.d[j[0]];
         this.e[1] = this.d[j[1]];
         this.o = new a.a.a.g("b_smoke");
      }

   }

   public final void a() {
      this.u = true;
      this.v = true;
      this.a = true;
      this.F = true;
   }

   private void a(byte[] var1) {
      DataInputStream var15 = new DataInputStream(new ByteArrayInputStream(var1));
      this.c = var15.readByte();
      this.x = var15.readByte();
      this.f = var15.readByte();
      this.c(this.x);
      this.b = var15.readByte();
      var15.readByte();
      this.a = var15.readShort();
      this.n = var15.readByte();
      this.f = var15.readByte();

      for(int var2 = 0; var2 < this.f; ++var2) {
         this.e[var2] = var15.readByte();
         this.m[var2] = var15.readByte();
         this.b[var2] = var15.readInt();
         this.d[var2][0] = var15.readByte();
         this.d[var2][1] = var15.readByte();
      }

      this.s = var15.readShort();

      for(int var16 = 0; var16 < this.e.length; ++var16) {
         this.a[this.e[var16][0]][this.e[var16][1]] = var15.readByte();
      }

      this.a = new a[this.f][4];
      this.a = new int[this.f];
      this.i();
      byte var17 = var15.readByte();
      int var3 = 0;

      for(byte var18 = var17; var3 < var18; ++var3) {
         byte var4 = var15.readByte();
         byte var5 = var15.readByte();
         short var6 = var15.readByte();
         byte var7 = var15.readByte();
         byte var8 = var15.readByte();
         byte var9 = var15.readByte();
         short var10 = var15.readShort();
         short var11 = var15.readShort();
         short var12 = var15.readShort();
         byte var13 = var15.readByte();
         byte var14 = var15.readByte();
         a var20;
         (var20 = aeii.a.a((byte)var4, (byte)var5, (int)var11, (int)var12)).f = (byte)var6;
         var20.a = var10;
         var20.a(var9);
         var20.g = var7;
         var20.b();
         var20.h = (short)var8;
         var20.h = var13;
         var20.i = var14;
         if (var4 == 9) {
            var4 = var15.readByte();
            var6 = var15.readShort();
            var20.b((int)var4);
            var20.j = var6;
            if (var15.readByte() == 1) {
               this.b[var20.e] = var20;
            }
         }
      }

      this.ay = var15.readShort();
      this.k = (long)var15.readInt();
      this.az = var15.readInt();
      this.z = var15.readByte() != 0;
      var15.close();
      if (this.x == 6 && this.ay > 32) {
         this.e = this.a(aeii.f.a(121 + this.x), aeii.f.a(138), this.b, -1);
         this.e.a((byte)0, true);
         this.e.a((f)null);
      }

      this.d(this.d[this.b][0], this.d[this.b][1]);
      this.b(this.d[this.b][0], this.d[this.b][1]);
      aeii.c.a(g[this.d[this.b]], 0);
   }

   private void a(int var1, f var2) {
      try {
         g var3 = this;
         this.d[this.b][0] = (byte)this.h;
         this.d[this.b][1] = (byte)this.i;
         ByteArrayOutputStream var4 = new ByteArrayOutputStream();
         DataOutputStream var5;
         (var5 = new DataOutputStream(var4)).writeByte(this.c);
         var5.writeByte(this.x);
         var5.writeByte(this.f);
         var5.writeByte(this.b);
         var5.writeByte(this.d[this.b]);
         var5.writeShort(this.a);
         var5.writeByte(this.n);
         var5.writeByte(this.f);

         for(int var6 = 0; var6 < var3.f; ++var6) {
            var5.writeByte(var3.e[var6]);
            var5.writeByte(var3.m[var6]);
            var5.writeInt(var3.b[var6]);
            var5.writeByte(var3.d[var6][0]);
            var5.writeByte(var3.d[var6][1]);
         }

         var5.writeShort(var3.s);

         for(int var11 = 0; var11 < var3.e.length; ++var11) {
            var5.writeByte(var3.a[var3.e[var11][0]][var3.e[var11][1]]);
         }

         var5.writeByte(var3.a.size());
         int var12 = 0;

         for(int var7 = var3.a.size(); var12 < var7; ++var12) {
            a var8 = (a)var3.a.elementAt(var12);
            var5.writeByte(var8.d);
            var5.writeByte(var8.e);
            var5.writeByte(var8.f);
            var5.writeByte(var8.g);
            var5.writeByte(var8.h);
            var5.writeByte(var8.a);
            var5.writeShort(var8.a);
            var5.writeShort(var8.b);
            var5.writeShort(var8.c);
            var5.writeByte(var8.h);
            var5.writeByte(var8.i);
            if (var8.d == 9) {
               var5.writeByte(var8.j);
               var5.writeShort(var8.j);
               var5.writeByte(var3.b[var8.e] == var8 ? 1 : 0);
            }
         }

         var5.writeShort((short)var3.ay);
         var5.writeInt((short)((int)var3.k));
         var5.writeInt(var3.az);
         var5.writeByte(var3.z ? 0 : 1);
         byte[] var13 = var4.toByteArray();
         var5.close();
         aeii.c.a("save", var1, var13);
         this.k[var1] = this.a((int)this.c, this.x, (int)this.a);
         this.n[var1] = this.d[this.b];
         this.k[var1] = this.x;
         this.u.a((String)null, (String)this.k[var1], this.a, -1);
         this.u.g = f[this.n[var1]];
         this.u.a();
         e var10;
         (var10 = this.a((String)null, aeii.f.a(77), this.b, 1000)).a = var2;
         aeii.f.a.a((f)var10);
      } catch (Exception var9) {
      }
   }

   private static void f() {
      try {
         byte[] var0 = aeii.c.a((String)"settings", (int)0);

         for(int var1 = 0; var1 < 4; ++var1) {
            aeii.c.a[var1] = (var0[0] & 1 << var1) != 0;
         }

      } catch (Exception var2) {
      }
   }

   private static void g() {
      try {
         byte[] var0 = new byte[1];

         for(int var1 = 0; var1 < 4; ++var1) {
            if (aeii.c.a[var1]) {
               var0[0] = (byte)(var0[0] | 1 << var1);
            }
         }

         aeii.c.a("settings", 0, var0);
      } catch (Exception var2) {
      }
   }

   private void a(a var1) {
      this.T = 0;
      this.d(var1.b, var1.c);
      this.i = var1;
   }

   private void a(a var1, a var2) {
      if (var2.f == 4) {
         this.d = var2;
         this.a(this.g, this.d.m, this.d.n, 0, 0, 1, 50);
         this.E = 6;
         this.b.d();
         this.d = 0;
         this.k();
         this.q.a(c[0]);
         if (this.m[this.b] == 0) {
            this.j = this.a;
            this.k = 6;
            return;
         }
      } else {
         if (aeii.c.a[3] && var2.a.length > 0) {
            this.g = true;
            this.G = 0;
            aeii.c.f();
         } else {
            this.d = 13;
            this.I = 0;
            this.q.a(c[0]);
         }

         this.f = var1;
         this.g = var2;
      }

   }

   private void h() {
      this.k();
      if (this.f.h <= 0) {
         this.c = this.f;
      } else if (this.f.a()) {
         this.e.addElement(this.f);
      }

      if (this.g.h <= 0) {
         this.c = this.g;
      } else {
         if (this.f.a((short)128)) {
            this.a(this.f, this.g.m, this.g.n, 0, 0, 1, 50);
            a.a.a.g var1;
            (var1 = a.a.a.g.a(this.s, 0, 0, -4, -1, 800, (byte)5)).b(this.g.m + (this.g.o - var1.o) / 2, this.g.n - var1.p);
            var1.a(i);
            this.c.addElement(var1);
            this.g.b((byte)1);
         }

         if (this.g.a()) {
            this.e.addElement(this.g);
         }
      }

      if (this.c != null) {
         this.d(this.c.b, this.c.c);
         this.a(this.f, this.c.m, this.c.n, 0, 0, 1, 50);
         aeii.c.b(12, 1);
      }

      this.e = this.a;
      if (this.m[this.b] == 0) {
         this.j = this.a;
         this.k = 6;
      }

      this.q.a(c[0]);
      this.d = 0;
      this.f.d();
      this.g = null;
      this.f = null;
   }

   public final a.a.a.g a(a.a.a.g var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      a.a.a.g var8;
      (var8 = a.a.a.g.a(var1, var4, var5, 0, var6, var7, (byte)0)).b(var2, var3);
      this.d.addElement(var8);
      return var8;
   }

   private void b(a var1) {
      this.f = true;
      this.e = !this.l;
      this.aa = 12;
      this.d = 1;
      this.i = true;
      this.a((byte[][])this.b, (int)0);
      var1.b(this.b);
      this.b = true;
      this.c = false;
      this.q.a(c[2]);
   }

   private void a(byte[] var1, int var2, int var3, f var4) {
      e var5 = new e((byte)0, 0);
      this.N = var2;
      this.O = var3;
      var2 = var1.length;
      Vector var10 = new Vector(var2);
      Vector var6 = new Vector(var2);

      for(int var7 = 0; var7 < var2; ++var7) {
         byte var8 = var1[var7];
         if (this.t || var8 != 6) {
            var10.addElement(this.h[var8]);
            var6.addElement(this.c[var8]);
         }
      }

      String[] var11 = new String[var10.size()];
      d[] var12 = new d[var6.size()];
      var10.copyInto(var11);
      var6.copyInto(var12);
      var5.a(var11, var12, super.l, this.N, this.O, 3, (byte)1);
      var5.a(var4);
      aeii.f.a.a((f)var5);
   }

   private void b(byte[] var1) {
      this.a = new e((byte)0, 0);
      int var2;
      String[] var3 = new String[var2 = var1.length];
      d[] var5 = new d[var2];

      for(int var4 = 0; var4 < var1.length; ++var4) {
         var3[var4] = this.g[var1[var4]];
         var5[var4] = this.a[var1[var4]];
      }

      if (this.i * 24 <= this.b / 2 - 24) {
         this.a.a(var3, var5, 0, this.b - this.c.p, 36);
      } else {
         this.a.a(var3, var5, this.a, 0, 8);
      }

      this.a.a((f)this);
      aeii.f.a.a((f)this.a);
   }

   private e a(String var1, d var2) {
      String[] var3 = new String[10];

      for(int var4 = 0; var4 < 10; ++var4) {
         var3[var4] = "SLOT " + (var4 + 1) + "/" + 10;
      }

      this.t = new e((byte)14, 0);
      this.t.a(var3, this.a, -1);
      this.u = new e((byte)10, 0);
      this.u.a((String)null, (String)this.k[0], this.a, -1);
      if (this.n[0] != -1) {
         this.u.g = f[this.n[0]];
      }

      e var9 = new e((byte)15, 15);
      int var6 = (this.b - this.t.c - this.u.c) / 2;
      e var5;
      (var5 = new e((byte)10, 0)).a((String)null, (String)var1, this.a, -1);
      var5.a = var2;
      var9.a((e)var5, 0, 0, (int)0);
      var6 += var5.c / 2;
      var9.a((e)this.t, 0, var6, (int)0);
      var6 += this.t.c;
      var9.a((e)this.u, 0, var6, (int)20);
      var9.b = true;
      var9.a((byte)0, true);
      return var9;
   }

   private void a(f var1) {
      int var2;
      String[] var3 = new String[var2 = this.s.length];
      int[] var4 = new int[var2];
      int var5 = 0;

      for(int var6 = 0; var6 < var2; ++var6) {
         boolean var7 = false;

         for(int var8 = 0; var8 < this.U; ++var8) {
            if (this.s[var6].equals(this.l[var8])) {
               var7 = true;
               break;
            }
         }

         if (!var7) {
            var3[var5] = this.s[var6];
            var4[var5] = var6;
            ++var5;
         }
      }

      this.D = new e((byte)15, 15);
      e var13;
      (var13 = new e((byte)10, 0)).a((String)null, (String)aeii.f.a(48), this.a, -1);
      if (var5 == 0) {
         e var14;
         (var14 = new e((byte)10, 0)).a((String)null, (String)aeii.f.a(52), this.a, this.p);
         this.D.a((e)var14, 0, (this.b + var13.c) / 2, (int)6);
      } else {
         String[] var15 = new String[var5];
         int[] var9 = new int[var5];
         System.arraycopy(var3, 0, var15, 0, var5);
         System.arraycopy(var4, 0, var9, 0, var5);
         this.F = new e((byte)10, 0);
         var3 = c(this.W);
         this.F.a((String)null, (String)(aeii.f.a(54, (String)c(this.n[var9[0]])) + "\n" + aeii.f.a(53, (String)var3)), this.a, -1);
         this.E = new e((byte)11, 0);
         this.E.a(var15, this.c, this.p, this.a, this.b - var13.c - this.F.c, 3, 4);
         this.E.a = var9;
         int var10 = (this.b - this.F.c - this.E.c + var13.c) / 2;
         this.D.a((e)this.E, 0, var10, (int)20);
         var10 += this.E.c;
         this.D.a((e)this.F, 0, var10, (int)20);
         this.D.b = true;
         this.D.a((byte)0, true);
      }

      this.D.a((e)var13, 0, 0, (int)20);
      this.D.a(var1);
   }

   private e a(f var1) {
      if (this.l.length == 0) {
         e var3;
         e var4;
         (var3 = (var4 = new e((byte)10, 0)).a(aeii.f.a(49))).a = this.c[6];
         var4.a((String)null, (String)aeii.f.a(52), super.j, -1);
         var4.a(0, (this.b + var3.c) / 2, 6);
         var4.a(var1);
         this.B = null;
         return var4;
      } else {
         this.B = new e((byte)11, 0);
         e var2;
         (var2 = this.B.a(aeii.f.a(49))).a = this.c[6];
         this.B.a(this.l, super.j / 2, (this.b + var2.c) / 2, this.a, this.b - var2.c, 3, 4);
         this.B.a(var1);
         return this.B;
      }
   }

   private int a(int var1) {
      return var1 >= e.length ? this.l[var1 - e.length] + e.length : var1;
   }

   private static DataInputStream a(int var0) {
      if (var0 >= e.length) {
         var0 -= e.length;
         return new DataInputStream(new ByteArrayInputStream(aeii.c.a("download", var0)));
      } else {
         return new DataInputStream(aeii.c.a("s" + var0 + ".aem"));
      }
   }

   private static byte[] a(int var0) {
      if (var0 >= e.length) {
         var0 -= e.length;
         return aeii.c.a("download", var0);
      } else {
         try {
            InputStream var4 = aeii.c.a("s" + var0);
            ByteArrayOutputStream var1 = new ByteArrayOutputStream();

            int var2;
            while((var2 = var4.read()) >= 0) {
               var1.write(var2);
            }

            var4.close();
            byte[] var5 = var1.toByteArray();
            var1.close();
            return var5;
         } catch (Exception var3) {
            var3.printStackTrace();
            return null;
         }
      }
   }

   private String b(int var1) {
      if (var1 >= e.length) {
         var1 -= e.length;

         for(int var2 = 0; var2 < this.l.length; ++var2) {
            if (this.l[var2] == var1) {
               return this.l[var2];
            }
         }

         return null;
      } else {
         return e[var1];
      }
   }

   private static String c(int var0) {
      int var2;
      int var1 = (var2 = var0 * 100 / 1024) / 100;
      var0 = var2 % 100;
      return var1 + "." + var0;
   }

   private static String a(String var0, String var1) {
      if (var0 != null) {
         if (var0.endsWith("/")) {
            var0 = var0.substring(0, var0.length() - 1);
         }

         int var2;
         return (var2 = var0.lastIndexOf(47)) >= 0 ? var0.substring(var2 + 1) : var0;
      } else {
         return var1;
      }
   }

   public final void a(e var1, int var2, String var3, byte var4) {
      try {
         this.G = true;
         if (var1 == this.a && var4 == 1) {
            if (this.d == 3) {
               this.b.a(this.A, this.B);
               this.b.b(this.b);
               this.b(this.b);
               this.i = true;
               return;
            }
         } else {
            if (var1 == this.v) {
               if (var4 == 0) {
                  aeii.f.a.c();
               } else {
                  this.v = null;
               }

               return;
            }

            if (var1 == this.w) {
               if (var4 == 0) {
                  this.a(this.h, this.N, this.O, var1.a);
               }

               this.w = null;
               return;
            }

            if (var1 == this.H) {
               if (var4 != 0) {
                  this.H = null;
                  this.I = null;
                  this.u = null;
                  return;
               }

               String var49;
               if (!(var49 = this.u[this.I.d]).endsWith(".aem")) {
                  this.a.a(var49);
                  this.u = aeii.b.a(aeii.b.a(this.a.a(".aem", true)), this.a.a() != null ? b : null);
                  this.H = new e((byte)15, 15);
                  e var64;
                  (var64 = this.H.a(a(this.a.a(), aeii.f.a(290)))).a = this.c[6];
                  this.I = new e((byte)0, 0);
                  this.I.a(this.u, 0, 0, this.a, this.b - var64.c - (this.c.p << 1), 3, 4);
                  if (this.a.a() != null && this.u.length > 1) {
                     this.I.d = 1;
                  }

                  this.H.a((e)this.I, this.c, (this.b + var64.c) / 2, (int)3);
                  this.H.a((byte)0, true);
                  this.H.b = true;
                  this.H.a((f)this.z);
                  aeii.f.a.a((f)this.H);
                  return;
               }

               a.a.a.c var63;
               InputStream var75 = (var63 = this.a.a(var49)).openInputStream();
               ByteArrayOutputStream var80 = new ByteArrayOutputStream();

               int var90;
               while((var90 = var75.read()) >= 0) {
                  var80.write(var90);
               }

               var75.close();
               var63.close();
               var49 = var49.substring(0, var49.length() - ".aem".length());
               this.a(var49, var80.toByteArray());
               var80.close();
               e var51;
               (var51 = this.a((String)null, aeii.f.a(45, (String)var49), this.b, 2000)).a((f)this.H);
               aeii.f.a.a((f)var51);
            } else if (var1 == this.J) {
               if (var4 != 0) {
                  this.J = null;
                  this.K = null;
                  this.u = null;
                  return;
               }

               String var48;
               if (this.K.d == 0) {
                  var48 = this.b + ".aem";
               } else {
                  var48 = this.u[this.K.d];
               }

               if (!var48.endsWith(".aem")) {
                  this.a.a(var48);
                  this.u = aeii.b.a(aeii.b.a(this.a.a(".aem", true)), this.a.a() != null ? c : null);
                  this.J = new e((byte)15, 15);
                  e var62;
                  (var62 = this.J.a(a(this.a.a(), aeii.f.a(290)))).a = this.c[6];
                  this.K = new e((byte)0, 0);
                  this.K.a(this.u, 0, 0, this.a, this.b - var62.c - (this.c.p << 1), 3, 4);
                  if (this.a.a() != null && this.u.length > 2) {
                     this.K.d = 2;
                  }

                  this.J.a((e)this.K, this.c, (this.b + var62.c) / 2, (int)3);
                  this.J.a((byte)0, true);
                  this.J.b = true;
                  this.J.a((f)this.N);
                  aeii.f.a.a((f)this.J);
                  return;
               }

               a.a.a.c var61;
               if ((var61 = this.a.a(var48)).a()) {
                  var61.a(0L);
               } else {
                  var61.a();
               }

               OutputStream var74;
               (var74 = var61.openOutputStream()).write(a(this.y));
               var74.close();
               var61.close();
               this.u = aeii.b.a(aeii.b.a(this.a.a(".aem", true)), this.a.a() != null ? c : null);
               this.J = new e((byte)15, 15);
               e var79;
               (var79 = this.J.a(a(this.a.a(), aeii.f.a(299)))).a = this.c[6];
               this.K = new e((byte)0, 0);
               this.K.a(this.u, 0, 0, this.a, this.b - var79.c - (this.c.p << 1), 3, 4);
               if (this.a.a() != null && this.u.length > 2) {
                  this.K.d = 2;
               }

               this.J.a((e)this.K, this.c, (this.b + var79.c) / 2, (int)3);
               this.J.a((byte)0, true);
               this.J.b = true;
               this.J.a((f)this.N);
               e var89;
               (var89 = this.a((String)null, aeii.f.a(45, (String)this.b), this.b, 2000)).a((f)this.J);
               aeii.f.a.a((f)var89);
            } else {
               if (var1 == this.x) {
                  if (var4 == 0) {
                     int var47 = this.t.d;
                     this.a(var47, this.s);
                  }

                  this.x = null;
                  return;
               }

               if (var1 == this.p) {
                  if (var4 == 2 || var4 == 3) {
                     this.q.a((String)null, (String)aeii.f.a(184 + this.p.a[var2].d), this.a, this.b - this.o.c - this.p.c);
                     this.o.a = this.p.a[var2];
                     this.q.a();
                     this.o.a();
                     return;
                  }
               } else {
                  if (var1 == this.n) {
                     if (var4 == 0) {
                        a var60 = this.p.a[this.p.d];
                        if (!this.a(var60, this.h, this.i)) {
                           return;
                        }

                        this.b = this.a(var60, this.h, this.i);
                        this.l = true;
                        this.b(this.b);
                        aeii.f.a.a((f)this);
                     }

                     this.n = null;
                     this.p = null;
                     this.q = null;
                     this.o = null;
                     return;
                  }

                  if (var1 == this.c) {
                     if (var4 == 2 || var4 == 3) {
                        String var59 = aeii.f.a(j[var2], true);
                        this.d.a((String)null, (String)var59, this.a, this.d.c);
                        this.d.a();
                        return;
                     }
                  } else {
                     if (var1 == this.b) {
                        this.b = null;
                        this.c = null;
                        this.d = null;
                        return;
                     }

                     if (var1 == this.e) {
                        if (var4 == 0) {
                           aeii.f.a.a((f)this);
                           return;
                        }
                     } else {
                        if (var1 == this.i) {
                           if (var4 != 0) {
                              this.i = null;
                           } else {
                              this.j = new e((byte)15, 15);
                              e var45;
                              (var45 = new e((byte)10, 0)).a = this.c[4];
                              var45.a((String)null, (String)aeii.f.a(34), this.a, -1);
                              this.j.a((e)var45, 0, 0, (int)20);
                              String[] var24 = new String[this.i];

                              for(int var70 = 0; var70 < this.i; ++var70) {
                                 var24[var70] = aeii.f.a(38, (String)("" + (var70 + 1)));
                              }

                              int var71 = var45.c;
                              e[] var58 = new e[this.i];
                              this.a = new e[this.i];
                              this.b = new e[this.i];

                              for(int var20 = 0; var20 < this.i; ++var20) {
                                 this.a[var20] = new e((byte)14, 6);
                                 this.a[var20].a(this.i, super.l, -1);
                                 this.b[var20] = new e((byte)14, 5);
                                 this.b[var20].a(var24, super.l, -1);
                                 this.b[var20].d = var20;
                                 int var86 = Math.max(this.a[var20].b, this.b[var20].b);
                                 int var77 = super.j - var86;
                                 this.a[var20].b = var86;
                                 this.b[var20].b = var86;
                                 this.j.a((e)this.a[var20], var77, var71, (int)20);
                                 int var72 = var71 + this.a[var20].c;
                                 this.j.a((e)this.b[var20], var77, var72, (int)20);
                                 var71 = var72 + this.b[var20].c;
                                 var58[var20] = new e((byte)10, 8);
                                 var58[var20].a((String)null, (String)aeii.f.a(this.o[var20] - 1 + 89), var77, this.a[var20].c + this.b[var20].c);
                                 var58[var20].g = f[this.o[var20]];
                                 this.j.a((e)var58[var20], 0, var71, (int)36);
                              }

                              this.j.f = 1;
                              e var87;
                              (var87 = new e((byte)10, 8)).a((String)null, (String)aeii.f.a(40), this.c, -1);
                              this.j.a((e)var87, 0, var71, (int)20);
                              this.k = new e((byte)14, 4);
                              String[] var21 = new String[d.length];

                              for(int var78 = 0; var78 < var21.length; ++var78) {
                                 var21[var78] = "" + d[var78];
                              }

                              this.k.a(var21, this.c, var87.c);
                              this.j.a((e)this.k, super.l, var71, (int)20);
                              var71 += var87.c;
                              (var45 = new e((byte)10, 8)).a((String)null, (String)aeii.f.a(41), this.c, -1);
                              this.j.a((e)var45, 0, var71, (int)20);
                              this.l = new e((byte)14, 4);
                              String[] var98 = new String[e.length];

                              for(int var88 = 0; var88 < var98.length; ++var88) {
                                 var98[var88] = "" + e[var88];
                              }

                              this.l.a(var98, this.c, var45.c);
                              this.j.a((e)this.l, super.l, var71, (int)20);
                              this.j.a((f)var1);
                              this.j.a((byte)0, true);
                              aeii.f.a.a((f)this.j);
                           }

                           return;
                        }

                        if (var1 == this.j) {
                           if (var4 == 0) {
                              int var44 = 0;
                              int var85 = 0;
                              boolean[] var19 = new boolean[this.i];

                              for(int var57 = 0; var57 < this.i; ++var57) {
                                 if (this.a[var57].d == 2) {
                                    this.m[var57] = 2;
                                 } else {
                                    ++var44;
                                    if (this.a[var57].d == 0) {
                                       this.m[var57] = 1;
                                    } else if (this.a[var57].d == 1) {
                                       this.m[var57] = 0;
                                    }

                                    this.e[var57] = (byte)this.b[var57].d;
                                    if (!var19[this.e[var57]]) {
                                       ++var85;
                                       var19[this.e[var57]] = true;
                                    }
                                 }
                              }

                              if (var44 >= 2 && var85 >= 2) {
                                 this.j = null;
                                 this.i = null;
                                 aeii.f.a.a((f)this);
                                 this.r = d[this.k.d];
                                 this.s = e[this.l.d];
                                 this.a = null;
                                 this.k = null;
                                 this.l = null;
                                 this.E();
                                 this.c = 1;
                                 this.n = 8;
                                 this.j = true;
                                 aeii.f.a.a();
                                 this.e();
                                 this.c(this.y);
                                 this.x = this.y;
                                 this.j = false;
                                 this.d = 0;
                                 return;
                              }

                              e var97;
                              (var97 = this.a((String)null, aeii.f.a(39), this.b, 2000)).a((f)this.j);
                              aeii.f.a.a((f)var97);
                              return;
                           }
                        } else {
                           if (var1 == this.g) {
                              int var5 = this.f.d;
                              if (var4 == 0 && (var5 >= e.length || !this.a[var5])) {
                                 this.y = this.a(var5);
                                 DataInputStream var10;
                                 int var7 = (var10 = a(this.y)).readInt();
                                 int var6 = var10.readInt();
                                 byte[][] var11 = new byte[var7][var6];
                                 this.o = new byte[4];
                                 byte[] var12 = new byte[5];

                                 for(int var8 = 0; var8 < 5; ++var8) {
                                    var12[var8] = -1;
                                 }

                                 this.i = 0;

                                 for(int var76 = 0; var76 < var7; ++var76) {
                                    for(int var13 = 0; var13 < var6; ++var13) {
                                       var11[var76][var13] = var10.readByte();
                                       int var9;
                                       if (this.k[var11[var76][var13]] == 9 && (var9 = a(var76, var13, var11)) != 0 && var12[var9] == -1) {
                                          this.o[this.i] = (byte)var9;
                                          byte var10004 = this.i;
                                          this.i = (byte)(var10004 + 1);
                                          var12[var9] = var10004;
                                       }
                                    }
                                 }

                                 var10.close();
                                 this.b = this.f.a[var5];
                                 this.i = new e((byte)15, 15);
                                 e var25;
                                 (var25 = new e((byte)10, 0)).a = this.c[4];
                                 var25.a((String)null, (String)this.b, this.a, -1);
                                 e var112;
                                 (var112 = new e((byte)8, 0)).a(super.j, this.b - var25.c - this.c.p, var11, (Vector)null);
                                 this.i.a((e)var112, this.c, this.p + (var25.c - this.c.p) / 2, (int)3);
                                 this.i.a((e)var25, 0, 0, (int)0);
                                 this.i.a((f)var1);
                                 this.i.a((byte)0, true);
                                 this.i.b = true;
                                 aeii.f.a.a((f)this.i);
                              }
                           } else if (var1 == this.L) {
                              int var26 = this.M.d - 1;
                              if (var4 != 0) {
                                 this.L = null;
                                 this.M = null;
                                 return;
                              }

                              if (var26 < 0) {
                                 this.B();
                                 aeii.c.a((Displayable)this.a);
                                 return;
                              }

                              if (var26 >= e.length || !this.a[var26]) {
                                 this.y = this.a(var26);
                                 this.b = this.M.a[var26];
                                 this.L = null;
                                 this.M = null;
                                 aeii.f.a.a((f)this);
                                 this.c = 1;
                                 this.n = 8;
                                 this.n = 1;
                                 this.j = true;
                                 aeii.f.a.a();
                                 this.e();
                                 this.c(this.y);
                                 this.x = this.y;
                                 this.j = false;
                                 this.d = 0;
                              }
                           } else if (var1 == this.N) {
                              int var27 = this.O.d;
                              if (var4 != 0) {
                                 this.N = null;
                                 this.O = null;
                                 return;
                              }

                              if (var27 >= e.length || !this.a[var27]) {
                                 this.y = this.a(var27);
                                 this.b = this.O.a[var27];
                                 if (this.a == null) {
                                    this.a = new b();
                                 }

                                 this.u = aeii.b.a(aeii.b.a(this.a.a(".aem", true)), this.a.a() != null ? c : null);
                                 this.J = new e((byte)15, 15);
                                 e var91;
                                 (var91 = this.J.a(a(this.a.a(), aeii.f.a(299)))).a = this.c[6];
                                 this.K = new e((byte)0, 0);
                                 this.K.a(this.u, 0, 0, this.a, this.b - var91.c - (this.c.p << 1), 3, 4);
                                 if (this.a.a() != null && this.u.length > 2) {
                                    this.K.d = 2;
                                 }

                                 this.J.a((e)this.K, this.c, (this.b + var91.c) / 2, (int)3);
                                 this.J.a((byte)0, true);
                                 this.J.b = true;
                                 this.J.a((f)this.N);
                                 aeii.f.a.a((f)this.J);
                              }
                           } else if (var1 == this.m) {
                              if (var4 == 1) {
                                 var2 = aeii.c.a[0];
                                 boolean var96 = false;

                                 for(int var69 = 0; var69 < 4; ++var69) {
                                    boolean var105;
                                    if ((var105 = this.c[var69].d == 0) != aeii.c.a[var69]) {
                                       aeii.c.a[var69] = var105;
                                       var96 = true;
                                    }
                                 }

                                 if (var96) {
                                    g();
                                    if (var2 != aeii.c.a[0]) {
                                       if (!aeii.c.a[0]) {
                                          aeii.c.f();
                                          return;
                                       }

                                       if (this.h == 1) {
                                          if (this.d != 11 && this.d != 14) {
                                             aeii.c.b(g[this.d[this.b]], 0);
                                             return;
                                          }
                                       } else if (this.h == 0) {
                                          aeii.c.b(0, 0);
                                          return;
                                       }
                                    }
                                 } else {
                                    this.m = null;
                                    this.c = null;
                                 }
                              }

                              return;
                           }

                           if (var1 == this.s) {
                              if (var4 == 0) {
                                 int var28 = this.t.d;
                                 if (this.n[var28] == -1) {
                                    this.a(var28, var1);
                                 } else {
                                    this.x = this.a((String)null, aeii.f.a(88), this.b, -1);
                                    this.x.a((byte)0, true);
                                    this.x.a((f)var1);
                                    aeii.f.a.a((f)this.x);
                                 }
                              } else {
                                 this.s = null;
                                 this.t = null;
                                 this.u = null;
                              }
                           } else if (var1 == this.r) {
                              if (var4 == 0) {
                                 byte[] var92 = null;

                                 try {
                                    var92 = aeii.c.a("save", this.t.d);
                                 } catch (Exception var14) {
                                 }

                                 if (var92 != null) {
                                    this.r = null;
                                    this.E();
                                    this.t = null;
                                    this.u = null;
                                    aeii.f.a.a((f)this);
                                    this.j = true;
                                    aeii.f.a.a();
                                    this.e();
                                    this.a(var92);
                                    if (this.c == 0) {
                                       this.m = true;
                                    }

                                    this.j = false;
                                    this.d = 0;
                                 }
                              } else {
                                 this.r = null;
                                 this.t = null;
                                 this.u = null;
                              }
                           } else if (var1 == this.t) {
                              if (var4 == 2 || var4 == 3) {
                                 this.u.a((String)null, (String)this.k[var2], this.a, -1);
                                 if (this.n[var2] == -1) {
                                    this.u.g = 2370117;
                                 } else {
                                    this.u.g = f[this.n[var2]];
                                 }

                                 this.u.a();
                              }

                              return;
                           }

                           if (var1 == this.y) {
                              if (var4 == 0) {
                                 if (var3.equals(this.m[0])) {
                                    if (this.p == null) {
                                       this.a(0, "news", aeii.f.a(0), var1);
                                       return;
                                    }

                                    this.A = this.b((f)var1);
                                    return;
                                 }

                                 if (var3.equals(this.m[1])) {
                                    this.z = new e((byte)11, 0);
                                    e var29;
                                    (var29 = this.z.a(var3)).a = this.c[6];
                                    this.z.a(this.n, super.j / 2, (this.b + var29.c) / 2, this.a, this.b - var29.c, 3, 0);
                                    this.z.a((f)var1);
                                    aeii.f.a.a((f)this.z);
                                 }

                                 if (var3.equals(this.m[2])) {
                                    this.L = new e((byte)15, 15);
                                    e var30;
                                    (var30 = new e((byte)10, 0)).a((String)null, (String)var3, this.a, -1);
                                    var30.a = this.c[6];
                                    this.a = new boolean[12];

                                    for(int var81 = this.q; var81 <= 7; ++var81) {
                                       if (c[var81] >= 0) {
                                          this.a[c[var81]] = true;
                                       }
                                    }

                                    String[] var99 = new String[12];

                                    for(int var52 = 0; var52 < 12; ++var52) {
                                       if (this.a[var52]) {
                                          var99[var52] = aeii.f.a(42);
                                       } else {
                                          var99[var52] = e[var52];
                                       }
                                    }

                                    String[] var106;
                                    (var106 = new String[12 + this.U + 1])[0] = aeii.f.a(292);
                                    System.arraycopy(var99, 0, var106, 1, 12);
                                    System.arraycopy(this.l, 0, var106, 13, this.U);
                                    this.M = new e((byte)0, 0);
                                    this.M.a(var106, 0, 0, this.a, this.b - var30.c - (this.c.p << 1), 3, 4);
                                    this.L.a((e)this.M, this.c, (this.b + var30.c) / 2, (int)3);
                                    this.L.a((e)var30, 0, 0, (int)0);
                                    this.L.b = true;
                                    this.L.a((byte)0, true);
                                    this.L.a((f)var1);
                                    aeii.f.a.a((f)this.L);
                                 }
                              } else if (var4 == 1) {
                                 this.y = null;
                              }
                           } else if (var1 == this.z) {
                              if (var4 == 0) {
                                 if (var3.equals(this.n[0])) {
                                    if (this.a == null) {
                                       this.a = new b();
                                    }

                                    this.u = aeii.b.a(aeii.b.a(this.a.a(".aem", true)), this.a.a() != null ? b : null);
                                    this.H = new e((byte)15, 15);
                                    e var100;
                                    (var100 = this.H.a(a(this.a.a(), aeii.f.a(290)))).a = this.c[6];
                                    this.I = new e((byte)0, 0);
                                    this.I.a(this.u, 0, 0, this.a, this.b - var100.c - (this.c.p << 1), 3, 4);
                                    if (this.a.a() != null && this.u.length > 1) {
                                       this.I.d = 1;
                                    }

                                    this.H.a((e)this.I, this.c, (this.b + var100.c) / 2, (int)3);
                                    this.H.a((byte)0, true);
                                    this.H.b = true;
                                    this.H.a((f)this.z);
                                    aeii.f.a.a((f)this.H);
                                 } else if (var3.equals(this.n[1])) {
                                    this.N = new e((byte)15, 15);
                                    e var31;
                                    (var31 = new e((byte)10, 0)).a((String)null, (String)aeii.f.a(299), this.a, -1);
                                    var31.a = this.c[6];
                                    this.a = new boolean[12];

                                    for(int var82 = this.q; var82 <= 7; ++var82) {
                                       if (c[var82] >= 0) {
                                          this.a[c[var82]] = true;
                                       }
                                    }

                                    String[] var101 = new String[12];

                                    for(int var53 = 0; var53 < 12; ++var53) {
                                       if (this.a[var53]) {
                                          var101[var53] = aeii.f.a(42);
                                       } else {
                                          var101[var53] = e[var53];
                                       }
                                    }

                                    String[] var107 = new String[12 + this.U];
                                    System.arraycopy(var101, 0, var107, 0, 12);
                                    System.arraycopy(this.l, 0, var107, 12, this.U);
                                    this.O = new e((byte)0, 0);
                                    this.O.a(var107, 0, 0, this.a, this.b - var31.c - (this.c.p << 1), 3, 4);
                                    this.N.a((e)this.O, this.c, (this.b + var31.c) / 2, (int)3);
                                    this.N.a((e)var31, 0, 0, (int)0);
                                    this.N.b = true;
                                    this.N.a((byte)0, true);
                                    this.N.a((f)var1);
                                    aeii.f.a.a((f)this.N);
                                 } else if (var3.equals(this.n[2])) {
                                    if (this.s == null) {
                                       this.a(2, "levels", aeii.f.a(0), var1);
                                    } else {
                                       this.a((f)var1);
                                       aeii.f.a.a((f)this.D);
                                    }
                                 } else if (var3.equals(this.n[3])) {
                                    aeii.f.a.a((f)this.a((f)var1));
                                 }
                              } else if (var4 == 1) {
                                 this.z = null;
                              }
                           } else if (var1 == this.A) {
                              if (var4 == 0) {
                                 if (this.r[var2] == null) {
                                    this.aD = var2;
                                    this.a(1, this.q[var2], aeii.f.a(0), var1);
                                 } else {
                                    e var32;
                                    (var32 = this.a(this.p[var2], this.r[var2], this.b, this.b / 2, -1)).a((f)var1);
                                    aeii.f.a.a((f)var32);
                                 }
                              } else if (var4 == 1) {
                                 this.A = null;
                              }
                           } else if (var1 == this.D) {
                              if (var4 == 0) {
                                 this.aE = this.E.a[this.E.d];
                                 if (this.W >= this.n[this.aE]) {
                                    this.a(3, this.t[this.aE], aeii.f.a(0), var1);
                                 } else {
                                    e var33;
                                    (var33 = this.a((String)null, aeii.f.a(55), this.b, -1)).a((f)var1);
                                    aeii.f.a.a((f)var33);
                                 }
                              } else if (var4 == 1) {
                                 this.F = null;
                                 this.E = null;
                                 this.D = null;
                              }
                           } else if (var1 == this.E) {
                              if (var4 == 2 || var4 == 3) {
                                 String var54 = c(this.W);
                                 this.F.a((String)null, (String)(aeii.f.a(54, (String)c(this.n[this.E.a[var2]])) + "\n" + aeii.f.a(53, (String)var54)), this.a, -1);
                                 this.F.a();
                              }
                           } else if (var1 == this.B) {
                              if (var4 == 0) {
                                 if (this.l[var2] + e.length == this.x) {
                                    e var34;
                                    (var34 = this.a((String)null, aeii.f.a(56), this.b, -1)).a((f)var1);
                                    aeii.f.a.a((f)var34);
                                 } else {
                                    this.V = var2;
                                    this.c = var3;
                                    this.C = this.a((String)null, aeii.f.a(50, (String)var3), this.b, this.p, -1);
                                    this.C.a((f)var1);
                                    this.C.a((byte)0, true);
                                    aeii.f.a.a((f)this.C);
                                 }
                              } else if (var4 == 1) {
                                 this.B = null;
                              }
                           } else if (var1 == this.C) {
                              if (var4 == 0) {
                                 this.f(this.V);
                                 e var35 = this.a(this.B.a);
                                 e var93;
                                 (var93 = this.a((String)null, aeii.f.a(51, (String)this.c), this.b, -1)).a((f)var35);
                                 aeii.f.a.a((f)var93);
                              }

                              this.C = null;
                              this.c = null;
                           }

                           if (var1.a == 7) {
                              aeii.f.a.a((f)this);
                              return;
                           }

                           if (var3 != null && var4 == 0) {
                              if (var3.equals(this.h[0])) {
                                 if (this.h != 0 && this.d == 0) {
                                    this.w = this.a((String)null, aeii.f.a(87), this.b, -1);
                                    this.w.a((byte)0, true);
                                    this.w.a((f)var1);
                                    aeii.f.a.a((f)this.w);
                                 } else {
                                    this.a(this.h, this.N, this.O, var1);
                                 }

                                 return;
                              }

                              if (!var3.equals(this.h[1]) && var1 != this.h) {
                                 if (var3.equals(aeii.f.a(3))) {
                                    this.h = new e((byte)11, 0);
                                    int var42 = this.q;
                                    if (this.q > 7) {
                                       var42 = 7;
                                    }

                                    ++var42;
                                    String[] var23 = new String[var42];

                                    for(int var68 = 0; var68 < var42; ++var68) {
                                       var23[var68] = var68 + 1 + ". " + aeii.f.a(var68 + 121);
                                    }

                                    e var104;
                                    (var104 = this.h.a(var3)).a = this.c[3];
                                    this.h.a(var23, super.j / 2, (this.b + var104.c) / 2, this.a, this.b - var104.c, 3, 4);
                                    this.h.a((f)var1);
                                    aeii.f.a.a((f)this.h);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(4))) {
                                    this.s = this.a(var3, this.c[10]);
                                    this.s.a((f)var1);
                                    aeii.f.a.a((f)this.s);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(5))) {
                                    this.r = this.a(var3, this.c[2]);
                                    this.r.a((f)var1);
                                    aeii.f.a.a((f)this.r);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(6))) {
                                    this.g = new e((byte)15, 15);
                                    e var41;
                                    (var41 = new e((byte)10, 0)).a((String)null, (String)var3, this.a, -1);
                                    var41.a = this.c[4];
                                    this.a = new boolean[12];

                                    for(int var84 = this.q; var84 <= 7; ++var84) {
                                       if (c[var84] >= 0) {
                                          this.a[c[var84]] = true;
                                       }
                                    }

                                    String[] var111 = new String[12];

                                    for(int var56 = 0; var56 < 12; ++var56) {
                                       if (this.a[var56]) {
                                          var111[var56] = aeii.f.a(42);
                                       } else {
                                          var111[var56] = e[var56];
                                       }
                                    }

                                    String[] var113 = new String[12 + this.U];
                                    System.arraycopy(var111, 0, var113, 0, 12);
                                    System.arraycopy(this.l, 0, var113, 12, this.U);
                                    this.f = new e((byte)0, 0);
                                    this.f.a(var113, 0, 0, this.a, this.b - var41.c - (this.c.p << 1), 3, 4);
                                    this.g.a((e)this.f, this.c, (this.b + var41.c) / 2, (int)3);
                                    this.g.a((e)var41, 0, 0, (int)0);
                                    this.g.b = true;
                                    this.g.a((byte)0, true);
                                    this.g.a((f)var1);
                                    aeii.f.a.a((f)this.g);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(7))) {
                                    this.y = new e((byte)11, 0);
                                    e var40;
                                    (var40 = this.y.a(var3)).a = this.c[6];
                                    this.y.a(this.m, super.j / 2, (this.b + var40.c) / 2, this.a, this.b - var40.c, 3, 0);
                                    this.y.a((f)var1);
                                    aeii.f.a.a((f)this.y);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(8))) {
                                    this.m = new e((byte)15, 15);
                                    e var39;
                                    (var39 = new e((byte)10, 0)).a = this.c[5];
                                    var39.a((String)null, (String)var3, this.a, -1);
                                    this.m.a((e)var39, 0, 0, (int)20);
                                    int var83 = var39.c;
                                    e[] var110 = new e[4];
                                    this.c = new e[4];

                                    for(int var55 = 0; var55 < 4; ++var55) {
                                       var2 = 8;
                                       if (var55 != 0) {
                                          var2 = 9;
                                       }

                                       if (var55 != 3) {
                                          var2 |= 2;
                                       }

                                       var110[var55] = new e((byte)10, var2);
                                       var110[var55].a((String)null, (String)aeii.c.a[var55], this.c, -1);
                                       this.m.a((e)var110[var55], 0, var83, (int)20);
                                       var2 = 4;
                                       if (var55 != 0) {
                                          var2 = 5;
                                       }

                                       if (var55 != 3) {
                                          var2 |= 2;
                                       }

                                       this.c[var55] = new e((byte)14, var2);
                                       this.c[var55].a(this.j, this.c, var110[var55].c);
                                       this.c[var55].d = aeii.c.a[var55] ? 0 : 1;
                                       this.m.a((e)this.c[var55], super.l, var83, (int)20);
                                       var83 += var110[var55].c;
                                    }

                                    this.m.f = 2;
                                    this.m.a((f)var1);
                                    aeii.f.a.a((f)this.m);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(9))) {
                                    this.b = new e((byte)15, 15);
                                    e var38;
                                    (var38 = new e((byte)10, 0)).a((String)null, (String)var3, this.a, -1);
                                    var38.a = this.c[7];
                                    String[] var22 = new String[20];

                                    for(int var65 = 0; var65 < i.length; ++var65) {
                                       var22[var65] = aeii.f.a(i[var65]) + " " + var65 + "/" + 19;
                                    }

                                    this.c = new e((byte)14, 2);
                                    this.c.a(var22, this.a, -1);
                                    this.d = new e((byte)10, 1);
                                    this.d.a((String)null, (String)aeii.f.a(j[0]), this.a, this.b - var38.c - this.c.c - (this.c.p << 1));
                                    int var66 = var38.c + this.c.p;
                                    this.b.a((e)this.c, this.c, var66, (int)17);
                                    var66 += this.c.c;
                                    this.b.a((e)this.d, this.c, var66, (int)17);
                                    this.b.a((e)var38, 0, 0, (int)0);
                                    this.b.a((f)var1);
                                    this.b.b = true;
                                    aeii.f.a.a((f)this.b);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(10))) {
                                    e var37 = new e((byte)15, 15);
                                    e var95;
                                    (var95 = new e((byte)10, 0)).a((String)null, (String)var3, this.a, -1);
                                    var95.a = this.c[8];
                                    e var103 = new e((byte)10, 0);
                                    String var109 = aeii.f.a(16, (String)this.a);
                                    var103.a((String)null, (String)var109, this.a, this.b - var95.c - (this.c.p << 1));
                                    var37.a((e)var103, 0, (this.b + var95.c) / 2, (int)6);
                                    var37.a((e)var95, 0, 0, (int)0);
                                    var37.a((f)var1);
                                    var37.b = true;
                                    aeii.f.a.a((f)var37);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(11))) {
                                    this.v = this.a((String)null, aeii.f.a(86), this.b, -1);
                                    this.v.a((byte)0, true);
                                    this.v.a((f)var1);
                                    aeii.f.a.a((f)this.v);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(60))) {
                                    this.a(this.g, this.p, this.b, var1);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(61))) {
                                    this.l = false;
                                    this.b(this.b);
                                    aeii.f.a.a((f)this);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(62))) {
                                    this.a((byte[][])this.b, (int)0);
                                    this.e = this.d;
                                    this.d = 6;
                                    this.i = true;
                                    this.a = this.b.a(this.b.b, this.b.c, (byte)0);
                                    this.z = 0;
                                    this.b = true;
                                    this.c = true;
                                    this.b.a((byte[][])this.b, this.b.b, this.b.c);
                                    this.q.a(c[1]);
                                    this.d(this.a[this.z].b, this.a[this.z].c);
                                    this.e = true;
                                    this.f = true;
                                    aeii.f.a.a((f)this);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(63))) {
                                    this.n = new e((byte)15, 15);
                                    this.n.i = this.b;
                                    this.o = new e((byte)2, 2);
                                    this.p = new e((byte)3, 1);
                                    this.q = new e((byte)10, 3);
                                    this.q.a = true;
                                    this.o.a = this.p.a[0];
                                    this.q.a((String)null, (String)aeii.f.a(184 + this.p.a[0].d), this.a, this.b - this.o.c - this.p.c);
                                    this.n.a((e)this.o, 0, 0, (int)0);
                                    this.n.a((e)this.q, 0, this.o.c, (int)0);
                                    this.n.a((e)this.p, 0, this.b, (int)32);
                                    this.n.b = true;
                                    this.n.a((f)this);
                                    this.n.a((byte)0, true);
                                    aeii.f.a.a((f)this.n);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(64))) {
                                    this.b.d();
                                    this.k();
                                    this.h = this.a((int)this.h, this.i, (byte)0);
                                    this.d = 0;
                                    aeii.f.a.a((f)this);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(66))) {
                                    this.m();
                                    aeii.f.a.a((f)this);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(68))) {
                                    this.a((byte)37, this.b.b, (int)this.b.c);
                                    aeii.f.a.a((f)this.a((String)null, aeii.f.a(74), this.b, 1000));
                                    aeii.c.b(9, 1);
                                    this.b.d();
                                    this.d = 0;
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(67))) {
                                    if (this.b(this.b)) {
                                       this.b(this.b.b, this.b.c, this.d[this.b.e]);
                                       aeii.f.a.a((f)this.a((String)null, aeii.f.a(73), this.b, 1000));
                                       this.d = 9;
                                       aeii.c.b(9, 1);
                                       this.d = this.a;
                                    }

                                    this.b.d();
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(69))) {
                                    this.d = 7;
                                    this.a = this.b.a(this.b.b, this.b.c, (byte)1);
                                    this.b = true;
                                    this.c = true;
                                    this.b.a((byte[][])this.b, this.b.b, this.b.c);
                                    this.e = true;
                                    aeii.f.a.a((f)this);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(70))) {
                                    e var36 = new e((byte)15, 15);
                                    e var94;
                                    (var94 = new e((byte)10, 0)).a((String)null, (String)this.b, this.a, -1);
                                    e var102;
                                    (var102 = new e((byte)8, 0)).a(this.a, this.b - var94.c - this.c.p, this.a, this.a);
                                    var36.a((e)var102, this.c, this.p + (var94.c - this.c.p) / 2, (int)3);
                                    var36.a((e)var94, 0, 0, (int)0);
                                    var36.a((f)var1);
                                    var36.b = true;
                                    aeii.f.a.a((f)var36);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(71))) {
                                    this.e.a((byte)0, false);
                                    this.e.a((f)var1);
                                    aeii.f.a.a((f)this.e);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(293))) {
                                    this.a(this.b, this.a());
                                    e var108;
                                    (var108 = this.a((String)null, aeii.f.a(45, (String)this.b), this.b, 2000)).a((f)this);
                                    aeii.f.a.a((f)var108);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(305))) {
                                    this.x();
                                    aeii.c.a((Displayable)this.c);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(306))) {
                                    this.A();
                                    this.y();
                                    aeii.c.a((Displayable)this.d);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(307))) {
                                    this.z();
                                    aeii.c.a((Displayable)this.f);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(303))) {
                                    this.A();
                                    aeii.c.a((Displayable)this.b);
                                    return;
                                 }

                                 if (var3.equals(aeii.f.a(314))) {
                                    this.F();
                                    aeii.c.a((Displayable)this.a);
                                    return;
                                 }
                              } else {
                                 aeii.f.a.a((f)this);
                                 if (var1 == this.h) {
                                    this.x = var2;
                                    this.h = null;
                                 } else {
                                    this.x = 0;
                                 }

                                 this.E();
                                 this.c = 0;
                                 this.m[1] = 0;
                                 this.j = true;
                                 aeii.f.a.a();
                                 System.gc();
                                 this.e();
                                 this.c(this.x);
                                 this.j = false;
                                 this.q();
                                 this.d = 0;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

      } catch (IOException var15) {
         var15.printStackTrace();
      }
   }

   private a a(a var1, int var2, int var3) {
      int[] var10000 = this.b;
      byte var10001 = this.b;
      var10000[var10001] -= var1.j;
      var1.h = 100;
      var1.a(var2, var3);
      if (!this.a.contains(var1)) {
         this.a.addElement(var1);
      }

      this.u = true;
      return var1;
   }

   private a a(byte var1, int var2, int var3) {
      int[] var10000 = this.b;
      byte var10001 = this.b;
      var10000[var10001] -= aeii.a.a[var1];
      this.u = true;
      return aeii.a.a(var1, this.b, var2, var3);
   }

   private void i() {
      this.a = new Vector();
      this.b = null;
      this.a = null;
      this.j = null;
   }

   private void c(int var1) {
      try {
         aeii.c.f();
         this.c = new Vector();
         this.o = false;
         this.d = true;
         this.g = false;
         this.s = false;
         this.b = null;
         this.c = null;
         this.i = null;
         this.d = null;
         this.e = null;
         this.e.removeAllElements();
         this.a = 0;
         this.b = 0;
         this.b = 0;
         this.ay = 0;
         this.i();
         this.b = null;
         this.a = null;
         this.b = null;
         this.f = 0;

         for(int var2 = 0; var2 < 5; ++var2) {
            this.l[var2] = -1;
         }

         this.i = true;
         aeii.c.g();
         DataInputStream var11;
         if (this.c == 0) {
            this.b = aeii.f.a(var1 + 113);
            var11 = new DataInputStream(aeii.c.a("m" + var1 + ".aem"));
         } else {
            this.b = this.b(var1);
            var11 = a(var1);
         }

         this.f = var11.readInt();
         this.g = var11.readInt();
         this.a = new byte[this.f][this.g];
         this.b = new byte[this.f][this.g];
         this.g = new byte[this.f][this.g];
         this.as = 0;
         int var3 = 0;
         Vector var4 = new Vector();
         Vector var5 = new Vector();

         for(short var7 = 0; var7 < this.f; ++var7) {
            for(short var8 = 0; var8 < this.g; ++var8) {
               this.a[var7][var8] = var11.readByte();
               this.b[var7][var8] = 0;
               if (b(this.a[var7][var8])) {
                  int var6 = this.a(var7, var8);
                  byte[] var9;
                  (var9 = new byte[3])[0] = (byte)var7;
                  var9[1] = (byte)var8;
                  var9[2] = (byte)var6;
                  var4.addElement(var9);
                  ++var3;
                  if (this.a(var7, var8) == 9) {
                     if (this.c == 1 && var6 != 0 && this.l[var6] == -1) {
                        this.d[this.f] = (byte)var6;
                        byte[] var10000 = this.l;
                        byte var10004 = this.f;
                        this.f = (byte)(var10004 + 1);
                        var10000[var6] = var10004;
                     }

                     byte[] var16;
                     (var16 = new byte[2])[0] = (byte)var7;
                     var16[1] = (byte)var8;
                     var5.addElement(var16);
                     ++this.as;
                  }
               }
            }
         }

         this.m = new int[var3];
         this.e = new byte[var3][];
         var4.copyInto(this.e);
         this.f = new byte[this.as][];
         var5.copyInto(this.f);
         this.t = this.f * 24;
         this.u = this.g * 24;
         if (this.c == 1) {
            for(short var19 = 0; var19 < this.f; ++var19) {
               this.b[var19] = this.r;
            }
         } else {
            this.f = 2;
            this.b[0] = 0;
            this.b[1] = 0;
            this.l[1] = 0;
            this.l[2] = 1;
            this.d[0] = 1;
            this.d[1] = 2;
            this.e[0] = 0;
            this.e[1] = 1;
            this.m[0] = 1;
            this.m[1] = 0;
            this.s = 100;
         }

         for(short var20 = 0; var20 < this.e.length; ++var20) {
            byte var25 = this.e[var20][2];
            int var27 = this.d(var25);
            if (var25 > 0 && (var27 < 0 || this.m[var27] == 2)) {
               this.b(this.e[var20][0], this.e[var20][1], 0);
            }
         }

         int var17 = var11.readInt();
         var11.skip((long)(var17 << 2));
         int var26 = var11.readInt();
         this.b = new a[this.f];
         this.a = new a[this.f][4];
         this.a = new int[this.f];

         for(short var21 = 0; var21 < var26; ++var21) {
            byte var28 = var11.readByte();
            var17 = var11.readShort() / 24;
            var3 = var11.readShort() / 24;
            byte var14 = (byte)(var28 % 12);
            byte var15 = (byte)this.d(1 + var28 / 12);
            if (this.m[var15] != 2) {
               a var13 = aeii.a.a(var14, var15, var17, var3);
               if (var14 == 9) {
                  this.b[var15] = var13;
               }
            }
         }

         var11.close();
         if (this.c == 0) {
            this.e = this.a(aeii.f.a(121 + this.x), aeii.f.a(129 + this.x), this.b, -1);
         } else {
            this.e = this.e = this.a(aeii.f.a(71), aeii.f.a(137), this.b, -1);
         }

         this.A = false;
         this.r = null;
         this.n = null;
         this.o = null;
         this.p = null;
         this.q = null;
         this.a = null;

         for(short var22 = 0; var22 < this.b.length; ++var22) {
            if (this.b[var22] == null) {
               this.d[var22][0] = 0;
               this.d[var22][1] = 0;
            } else {
               this.d[var22][0] = (byte)this.b[var22].b;
               this.d[var22][1] = (byte)this.b[var22].c;
            }
         }

         if (this.c == 1) {
            this.ay = 100;
            this.m = true;

            for(short var23 = 0; var23 < this.f; ++var23) {
               if (this.m[var23] != 2) {
                  this.b = (byte)var23;
                  break;
               }

               ++this.a;
            }
         }

         if (this.b.length > 0 && this.b[this.b] != null) {
            this.b(this.b[this.b].b, this.b[this.b].c);
            this.d(this.b[this.b].b, this.b[this.b].c);
         }

         this.d = new a[this.e.length];
         this.q = new byte[this.e.length];
         this.b = new a.a.a.g[this.e.length];

         for(short var24 = 0; var24 < this.e.length; ++var24) {
            if (this.a(this.e[var24][0], this.e[var24][1]) == 8) {
               this.b[var24] = a.a.a.g.a(this.o, 0, -1, 0, 1, 250, (byte)0);
               this.b[var24].b = false;
            }
         }

         if (this.m[this.b] == 0) {
            this.o();
         }

         if (this.n != 0 || this.f == 0) {
            aeii.c.a(g[1], 0);
         }

         if (this.n == 0) {
            this.e(var1);
            return;
         }

         this.v = 0;
         this.w = 0;
         this.h = 0;
         this.i = 0;
         this.q.b(this.h * 24, this.i * 24);
      } catch (Exception var10) {
         var10.printStackTrace();
      }

   }

   private void a(int[] var1) {
      aeii.c.a(var1, true);

      for(int var2 = 0; var2 < this.f; ++var2) {
         for(int var3 = 0; var3 < this.g; ++var3) {
            if (this.b[var2][var3] > 0) {
               this.a[var2][var3] = (byte)aeii.c.a(var1);
            }
         }
      }

      aeii.c.a(var1, false);
      this.j();
   }

   private void a(int var1, int var2, int var3, int var4, int[] var5) {
      var2 = this.f + var1 + var2;
      var4 = this.g + var3 + var4;
      byte[][] var6 = new byte[var2][var4];
      aeii.c.a(var5, true);

      for(int var9 = 0; var9 < var2; ++var9) {
         for(int var10 = 0; var10 < var4; ++var10) {
            int var7 = var9 - var1;
            int var8 = var10 - var3;
            if (var7 >= 0 && var8 >= 0 && var7 < this.f && var8 < this.g) {
               var6[var9][var10] = this.a[var7][var8];
            } else {
               var6[var9][var10] = (byte)aeii.c.a(var5);
            }
         }
      }

      aeii.c.a(var5, false);

      for(int var13 = 0; var13 < this.a.size(); ++var13) {
         a var14;
         (var14 = (a)this.a.elementAt(var13)).a(var14.b + var1, var14.c + var3);
      }

      this.a = var6;
      this.f = var2;
      this.g = var4;
      this.b = new byte[this.f][this.g];
      this.g = new byte[this.f][this.g];
      this.j();
      this.t = this.f * 24;
      this.u = this.g * 24;
      this.v = 0;
      this.w = 0;
      this.h = 0;
      this.i = 0;
      this.q.b(this.h * 24, this.i * 24);
   }

   private void j() {
      try {
         this.f = 0;

         for(int var1 = 0; var1 < 5; ++var1) {
            this.l[var1] = -1;
         }

         this.as = 0;
         int var9 = 0;
         Vector var2 = new Vector();
         Vector var3 = new Vector();

         for(short var5 = 0; var5 < this.f; ++var5) {
            for(short var6 = 0; var6 < this.g; ++var6) {
               if (b(this.a[var5][var6])) {
                  int var4 = this.a(var5, var6);
                  byte[] var7;
                  (var7 = new byte[3])[0] = (byte)var5;
                  var7[1] = (byte)var6;
                  var7[2] = (byte)var4;
                  var2.addElement(var7);
                  ++var9;
                  if (this.a(var5, var6) == 9) {
                     if (this.c == 1 && var4 != 0 && this.l[var4] == -1) {
                        this.d[this.f] = (byte)var4;
                        byte[] var10000 = this.l;
                        byte var10004 = this.f;
                        this.f = (byte)(var10004 + 1);
                        var10000[var4] = var10004;
                     }

                     byte[] var10;
                     (var10 = new byte[2])[0] = (byte)var5;
                     var10[1] = (byte)var6;
                     var3.addElement(var10);
                     ++this.as;
                  }
               }
            }
         }

         this.m = new int[var9];
         this.e = new byte[var9][];
         var2.copyInto(this.e);
         this.f = new byte[this.as][];
         var3.copyInto(this.f);
         this.d = new a[this.e.length];
         this.q = new byte[this.e.length];
         this.b = new a.a.a.g[this.e.length];

         for(short var11 = 0; var11 < this.e.length; ++var11) {
            if (this.a(this.e[var11][0], this.e[var11][1]) == 8) {
               this.b[var11] = a.a.a.g.a(this.o, 0, -1, 0, 1, 250, (byte)0);
               this.b[var11].b = false;
            }
         }

      } catch (Exception var8) {
         var8.printStackTrace();
      }
   }

   private byte[] a() {
      try {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         DataOutputStream var2;
         (var2 = new DataOutputStream(var1)).writeInt(this.f);
         var2.writeInt(this.g);

         for(int var3 = 0; var3 < this.f; ++var3) {
            for(int var4 = 0; var4 < this.g; ++var4) {
               var2.writeByte(this.a[var3][var4]);
            }
         }

         var2.writeInt(0);
         var2.writeInt(this.a.size());

         for(int var6 = 0; var6 < this.a.size(); ++var6) {
            a var8 = (a)this.a.elementAt(var6);
            var2.writeByte((this.d[var8.e] - 1) * 12 + var8.d);
            var2.writeShort(var8.b * 24);
            var2.writeShort(var8.c * 24);
         }

         var2.flush();
         byte[] var7 = var1.toByteArray();
         var2.close();
         return var7;
      } catch (Exception var5) {
         var5.printStackTrace();
         return null;
      }
   }

   private void k() {
      this.z = 0;
      this.b = null;
      this.a = new a[0];
      this.a((byte[][])this.b, (int)0);
      this.b = false;
      this.c = false;
   }

   private void a(byte[][] var1, int var2) {
      for(int var3 = 0; var3 < this.f; ++var3) {
         for(int var4 = 0; var4 < this.g; ++var4) {
            var1[var3][var4] = (byte)var2;
         }
      }

   }

   private void c(a var1) {
      aeii.c.f(10);
      this.j = null;
      this.a((byte[][])this.b, (int)0);
      this.b = false;
      if (this.m[this.b] == 1) {
         this.Z = 1;
         this.d = true;
         this.q.a(c[0]);
         this.d = 3;
         this.b(this.a((a)var1, (byte)0));
         aeii.c.b(11, 1);
      } else {
         if (this.m[this.b] == 0) {
            this.k = 4;
            this.d = 0;
         }

      }
   }

   private byte[] a(a var1, byte var2) {
      int var3 = 0;
      byte[] var4 = new byte[this.g.length];
      if (var2 == 1 && this.a(this.b.b, this.b.c) == 9 && this.b(this.b.b, this.b.c, var1.e)) {
         ++var3;
         var4[0] = 0;
      }

      if (this.a(var1)) {
         var4[var3++] = 2;
      } else if (this.b(var1)) {
         var4[var3++] = 1;
      }

      if ((var2 == 1 || var1.d != 7) && var1.a.length > 0 && var1.a(var1.b, var1.c, (byte)0).length > 0) {
         var4[var3++] = 3;
      }

      if (var1.a((short)32) && var1.a(var1.b, var1.c, (byte)1).length > 0) {
         var4[var3++] = 4;
      }

      if (var2 == 1) {
         var4[var3++] = 5;
      } else {
         var4[var3++] = 6;
      }

      byte[] var5 = new byte[var3];
      System.arraycopy(var4, 0, var5, 0, var3);
      return var5;
   }

   private void l() {
      if (aeii.c.b && this.F < 4) {
         this.d = new d("logo", this.j, -1);
         this.F = 3;
         this.G = 40;
      }

      switch (this.F) {
         case 0:
            if (this.a >= 1200L) {
               this.F = 1;
            }

            this.G = 40;
            return;
         case 1:
            if (this.G <= 0) {
               this.b = null;
               this.G = 0;
               ++this.F;
               this.a((int)0, 0, (int)3);
               return;
            }

            --this.G;
            return;
         case 2:
            this.a((int)1, 2, (int)3);
            this.d = new d("logo", this.j, -1);
            ++this.F;
            return;
         case 3:
            if (++this.G > 40) {
               try {
                  this.c = new d("splash", this.a, -1);
                  this.e = new d("glow");
               } catch (Exception var2) {
               }

               if (this.c != null) {
                  this.G = 0;
               } else {
                  this.G = 11;
               }

               ++this.F;
               aeii.f.a.b();
               return;
            }
            break;
         case 4:
            if (this.G < 16) {
               ++this.G;
               ++this.H;
               if (this.e != null) {
                  this.P = -this.e.a;
                  return;
               }
            } else {
               if (this.P >= super.j << 2) {
                  if (this.e != null) {
                     this.P = -this.e.a;
                  }
               } else {
                  this.P += this.d.a / 6;
               }

               if (this.a % 100L == 0L) {
                  this.k = !this.k;
               }

               if ((this.c == null || aeii.f.a.a()) && this.a()) {
                  if (this.e != null) {
                     this.P = -this.e.a;
                  }

                  this.k = false;
                  int var1 = this.d.b + 1;
                  this.a(this.f, (super.k + var1) / 2, super.k - var1, this.c == null ? null : this);
                  aeii.f.a.b();
               }
            }
      }

   }

   public final void b() {
      this.a += 50L;
      if (this.h == 2) {
         g var1 = this;
         if (this.C && this.a - this.n >= this.m) {
            this.C = false;
         }

         for(int var2 = 0; var2 < var1.g.size(); ++var2) {
            ((a.a.a.g)var1.g.elementAt(var2)).c();
         }

         for(int var22 = 0; var22 < var1.g.size(); ++var22) {
            a.a.a.g var3;
            if (!(var3 = (a.a.a.g)var1.g.elementAt(var22)).b) {
               var1.b(var3);
            }
         }

         var1.a.b();
         var1.b.b();
         if (var1.E) {
            ++var1.G;
            if (var1.G >= 16) {
               var1.E = false;
               var1.a.a();
            }

            var1.F = true;
            var1.a = true;
         } else {
            if (!var1.B) {
               if (var1.a.b) {
                  if (var1.D && var1.b.a > 0) {
                     if (!var1.b.a) {
                        var1.b.a();
                     }

                     if (!var1.b.b) {
                        return;
                     }
                  }

                  var1.B = true;
                  var1.l = var1.a;
               }

               return;
            }

            if (var1.a - var1.l < 300L) {
               return;
            }

            var1.g.removeAllElements();
            var1.b = null;
            var1.a = null;
            var1.c = new Vector();
            var1.h();
            var1.h = 1;
            aeii.c.f();
            aeii.c.b(g[var1.d[var1.b]], 0);
            aeii.f.a.b();
            var1.u = true;
            var1.v = true;
         }
      } else if (this.h == 3) {
         if (this.x) {
            --this.G;
            if (this.G >= 0) {
               return;
            }

            this.G = 0;
            this.h = this.j;
            this.g = null;
            this.o = null;
         } else {
            if ((this.af != 2 || this.G >= 40) && (this.af == 2 || this.G >= 16)) {
               --this.ac;
               if (this.ac < this.ah) {
                  this.ac = this.ah + this.ab - (this.ah - this.ac);
                  ++this.ae;
               }
            } else {
               ++this.G;
            }

            if (this.ae < this.o.length && !aeii.f.a.b(d)) {
               return;
            }

            this.x = true;
            if (this.g == null && this.ae >= this.o.length) {
               this.G = 0;
               return;
            }

            if (this.ag == 2) {
               this.G = 40;
            } else {
               this.G = 16;
            }
         }
      } else {
         if (this.h == 0) {
            this.l();
            return;
         }

         this.s();
         if (this.L != -1) {
            if (aeii.c.a[2]) {
               e var9;
               (var9 = this.a((String)aeii.f.a(196 + this.L, true), (byte)-1, (byte)2)).h = 7831691;
               var9.g = 7831691;
               var9.a = 16250855;
            }

            this.L = -1;
         }

         if (aeii.f.a.a == this) {
            if (this.d == 0) {
               for(int var10 = 0; var10 < this.b.length; ++var10) {
                  int var23 = this.a(this.e[var10][0], this.e[var10][1]);
                  if (this.b[var10] != null && var23 != -1 && var23 != 0 && !this.b[var10].b && aeii.c.a.nextInt() % 8 == 0) {
                     this.b[var10].b = true;
                     this.b[var10].c(0);
                     this.b[var10].r = 1;
                     this.b[var10].b((this.e[var10][0] + 1) * 24 - this.o.o, this.e[var10][1] * 24 - 2);
                     this.d.addElement(this.b[var10]);
                  }
               }
            }

            if (this.a - this.h >= 300L) {
               this.h = !this.h;
               this.h = this.a;
            }

            if (this.C && this.a - this.n >= this.m) {
               this.C = false;
            }

            if (this.g) {
               ++this.G;
               if (this.G > 16) {
                  if (this.d == 10) {
                     this.aC = 1;
                  } else if (this.d == 11) {
                     if (this.c == 0) {
                        this.q = true;
                        this.p = true;
                        this.R = 0;
                     }

                     this.M = 0;
                     this.d = this.a;
                  } else {
                     this.b(this.f, this.g);
                     this.k = null;
                     this.k();
                  }

                  this.g = false;
                  return;
               }
            } else {
               if (this.q) {
                  if (this.o) {
                     if (this.R < 16) {
                        ++this.R;
                     }
                  } else if (this.p) {
                     ++this.R;
                     if (this.R > 16) {
                        this.p = false;
                     }
                  }
               }

               if (this.b != null) {
                  this.C = (this.C + 1) % 12;
               }

               if (this.b) {
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

               if (this.d && this.a - this.b >= 200L) {
                  this.q.e();
                  this.b = this.a;
               }

               int var24 = this.h * 24;
               int var11 = this.i * 24;
               int var45 = this.q.m;
               int var4 = this.q.n;
               if (var24 > var45) {
                  var45 += 8;
               } else if (var24 < var45) {
                  var45 -= 8;
               }

               if (var11 > var4) {
                  var4 += 8;
               } else if (var11 < var4) {
                  var4 -= 8;
               }

               this.q.b(var45, var4);
               if (!this.n && this.Q > 0) {
                  if (this.Q < 2) {
                     this.Q = 0;
                  } else {
                     this.Q /= 2;
                  }

                  this.u = true;
                  this.v = true;
               } else if (this.d == 8) {
                  if (this.D == 0) {
                     if (this.Q >= a) {
                        this.D = 1;
                        this.n();
                        String var40;
                        if (this.m[this.b] == 1) {
                           var40 = "" + this.an;
                        } else {
                           var40 = "?";
                        }

                        e var41 = this.a(aeii.f.a(75), aeii.f.a(76, (String)var40), this.b, 1500);
                        aeii.f.a.a((f)var41);
                        var41.g = f[this.d[this.b]];
                        aeii.c.a(g[this.d[this.b]], 0);
                        return;
                     }

                     if (this.Q == 0) {
                        this.n = true;
                        this.Q = 1;
                     } else {
                        this.Q <<= 1;
                     }

                     this.u = true;
                     this.v = true;
                  } else {
                     for(int var18 = this.a.size() - 1; var18 >= 0; --var18) {
                        a var50;
                        if ((var50 = (a)this.a.elementAt(var18)).f != 3 && this.b == var50.e && (this.a(var50.b, var50.c) == 7 || this.a((int)var50.b, var50.c, (int)this.e[var50.e])) && var50.h < 100) {
                           if ((var24 = 100 - var50.h) > 20) {
                              var24 = 20;
                           }

                           var50.h += var24;
                           a.a.a.g var39;
                           (var39 = a.a.a.g.a((String)("+" + var24), 0, -4, (byte)1)).b(var50.m + var50.o / 2, var50.n + var50.p);
                           this.c.addElement(var39);
                        }
                     }

                     this.D = 0;
                     this.n = false;
                     this.d = 0;
                  }
               } else if (this.d == 9) {
                  this.d = 0;
               } else if (this.d == 11) {
                  if (!this.g && this.M == 0 && (this.c == 1 || this.a - this.d >= 3000L || aeii.f.a.a())) {
                     this.b = super.k;
                     this.p = super.m;
                     this.a(this.f, super.m, super.k, (f)null);
                     this.M = 1;
                     this.p = false;
                  }
               } else if (this.d != 10 && this.d != 14) {
                  if (this.d == 13) {
                     if (this.I == 0) {
                        aeii.c.d(200);
                        var24 = this.f.c(this.g);
                        this.g.a((int)400);
                        aeii.c.b(14, 1);
                        this.a(this.g, this.g.m, this.g.n, 0, 0, 2, 50);
                        a.a.a.g var35 = a.a.a.g.a((String)("-" + var24), 0, -4, (byte)1);
                        if ((var11 = this.g.m + this.g.o / 2) + var35.o / 2 > this.t) {
                           var11 = this.t - var35.o / 2;
                        } else if (var11 - var35.o / 2 < 0) {
                           var11 = var35.o / 2;
                        }

                        var35.b(var11, this.g.n + this.g.p);
                        this.c.addElement(var35);
                        this.g = this.a;
                        ++this.I;
                     } else if (this.I == 1) {
                        if (this.a - this.g >= 800L) {
                           this.d(this.f.b, this.f.c);
                           if (this.g.a((int)this.f.b, (int)this.f.c)) {
                              aeii.c.d(200);
                              var24 = this.g.c(this.f);
                              this.f.a((int)400);
                              aeii.c.b(14, 1);
                              this.a(this.g, this.f.m, this.f.n, 0, 0, 2, 50);
                              a.a.a.g var37 = a.a.a.g.a((String)("-" + var24), 0, -4, (byte)1);
                              if ((var11 = this.f.m + this.f.o / 2) + var37.o / 2 > this.t) {
                                 var11 = this.t - var37.o / 2;
                              } else if (var11 - var37.o / 2 < 0) {
                                 var11 = var37.o / 2;
                              }

                              var37.b(var11, this.f.n + this.f.p);
                              this.c.addElement(var37);
                              this.g = this.a;
                              ++this.I;
                           } else {
                              this.h();
                           }
                        }
                     } else if (this.a - this.g >= 800L) {
                        this.h();
                     }
                  } else if (this.i != null) {
                     if (this.T == 0) {
                        if (this.w) {
                           this.v = this.a(this.f, this.i.m, -this.w, 0, 12, -1, 0);
                           e var61;
                           (var61 = this.a((String)null, aeii.f.a(280), this.b, 2000)).a(this.c, 2, 17);
                           aeii.f.a.a((f)var61);
                           this.T = 1;
                        }
                     } else if (this.T != 1) {
                        if (++this.T >= 20) {
                           if (this.i.h <= 0) {
                              this.c = this.i;
                              this.a(this.f, this.c.m, this.c.n, 0, 0, 1, 50);
                              aeii.c.b(12, 1);
                              this.e = this.a;
                           }

                           this.i = null;
                        }
                     } else {
                        for(int var15 = 0; var15 < 3; ++var15) {
                           this.a(this.o, this.v.m + aeii.c.a(this.v.o - this.o.o), this.v.n, 0, aeii.c.a(-3, 0), 1, 50 * aeii.c.a((int)4));
                        }

                        if (this.v.n >= this.i.n) {
                           this.v.b = false;
                           aeii.c.d(200);
                           this.a((int)500);
                           if (this.r) {
                              if ((var24 = 25 + aeii.c.a((int)25)) > this.i.h) {
                                 var24 = this.i.h;
                              }

                              a var68 = this.i;
                              var68.h -= var24;
                              a.a.a.g var33;
                              (var33 = a.a.a.g.a((String)("-" + var24), 0, -4, (byte)1)).b(this.i.m + this.i.o / 2, this.i.n + this.i.p);
                              this.c.addElement(var33);
                           }

                           this.d(this.i);
                           this.T = 2;
                        }
                     }
                  } else if (this.d != null) {
                     if (--this.E <= 0) {
                        aeii.c.d(100);
                        this.d(this.d);
                        this.a((byte)27, this.d.b, (int)this.d.c);
                        this.d = null;
                     }
                  } else if (this.c != null) {
                     if (this.a - this.e >= 300L && this.b(this.c.b, this.c.c)) {
                        if (this.c == 0 && this.x == 7 && this.c == this.b[1]) {
                           this.s = true;
                        } else {
                           this.a(this.e, this.c.m, this.c.n, 0, -3, 1, 100);
                           this.c.f = 3;
                           this.c.h = 3;
                           if (this.c.d != 10 && this.c.d != 11) {
                              if (this.c.d == 9) {
                                 this.c.a((int)-10, (int)-10);
                                 this.c.g = 0;
                                 this.c.b();
                              }
                           } else {
                              this.c.a();
                           }

                           if (this.c.d == 9 && this.c.j < 1000) {
                              a var10000 = this.c;
                              var10000.j += 200;
                           }
                        }

                        this.c = null;
                     }
                  } else if (this.e.size() > 0) {
                     a var49 = (a)this.e.elementAt(0);
                     if (this.S == 0) {
                        this.d(var49.b, var49.c);
                        this.S = 1;
                     } else if (this.b(var49.b, var49.c)) {
                        this.a(this.h, var49.m + aeii.c.a(var49.o), var49.n + aeii.c.a(var49.p), 0, 0, 1, 50);
                        if (this.S == 1) {
                           aeii.c.b(13, 1);
                        }

                        if (this.S <= 5) {
                           var4 = 200;
                           if (this.S == 5) {
                              var4 = 1000;
                           }

                           var11 = var49.m + (var49.o - this.u.o) / 2;
                           int var64 = var49.n - (this.S << 2);
                           if (var11 < 0) {
                              var11 = 0;
                           } else if (var11 + this.u.o > this.t) {
                              var11 = this.t - this.u.o;
                           }

                           if (var64 < 0) {
                              var64 = 0;
                           }

                           this.a(this.u, var11, var64, 0, 0, 1, var4);
                        }

                        ++this.S;
                        if (this.S >= 20) {
                           this.e.removeElement(var49);
                           this.S = 0;
                           if (var49.d != 9 && var49.a <= 6 && var49.a % 2 == 0) {
                              aeii.f.a.a((f)this.a((String)null, aeii.f.a(80) + "\n" + var49.a, this.a, 2000));
                           }
                        }
                     }
                  } else if (this.e != null) {
                     if (this.a - this.f >= 400L) {
                        this.e.a();
                        aeii.a.a((byte)10, (byte)this.g, (int)this.e.b, (int)this.e.c).d();
                        this.e = null;
                     }
                  } else if (!this.A) {
                     if (this.d == 2) {
                        if (this.b.f != 1 && this.w) {
                           this.c(this.b);
                        }
                     } else if (this.n == 0 && this.m[this.b] == 0) {
                        this.p();
                     } else if (this.a()) {
                        if (this.f && aeii.f.a.b(d)) {
                           aeii.f.a.a((int)16);
                           aeii.f.a.b(d);
                        }

                        if (this.d != 6 && this.d != 7) {
                           if (this.a - this.c >= 150L && this.q.m % 24 == 0 && this.q.n % 24 == 0) {
                              if (!aeii.f.a.a((int)4) && !aeii.f.a.c(4)) {
                                 if (aeii.f.a.a((int)8) || aeii.f.a.c(8)) {
                                    if (this.h < this.f - 1) {
                                       ++this.h;
                                    }

                                    this.i = true;
                                    this.c = this.a;
                                 }
                              } else {
                                 if (this.h > 0) {
                                    --this.h;
                                 }

                                 this.i = true;
                                 this.c = this.a;
                              }

                              if (!aeii.f.a.a((int)1) && !aeii.f.a.c(1)) {
                                 if (aeii.f.a.a((int)2) || aeii.f.a.c(2)) {
                                    if (this.i < this.g - 1) {
                                       ++this.i;
                                    }

                                    this.i = true;
                                    this.c = this.a;
                                 }
                              } else {
                                 if (this.i > 0) {
                                    --this.i;
                                 }

                                 this.i = true;
                                 this.c = this.a;
                              }

                              if (this.i) {
                                 if (this.d == 1) {
                                    if (this.b[this.h][this.i] > 0) {
                                       this.b = this.b.a((int)this.b.b, (int)this.b.c, (int)this.h, (int)this.i);
                                    }
                                 } else {
                                    this.h = this.a((int)this.h, this.i, (byte)0);
                                 }

                                 this.v = true;
                              }

                              this.i = false;
                           }

                           if ((this.d == 1 || this.d == 0) && this.n == 0 && aeii.f.a.a((int)256)) {
                              a var46;
                              if ((var46 = this.a((int)this.h, this.i, (byte)0)) != null) {
                                 e var28;
                                 (var28 = new e((byte)15, 15)).i = this.b;
                                 e var5 = new e((byte)5, 2);
                                 e var12;
                                 (var12 = new e((byte)10, 1)).a = true;
                                 String var6 = aeii.f.a(184 + var46.d);
                                 if (var46.g != 0) {
                                    StringBuffer var7 = new StringBuffer(aeii.f.a(98));
                                    if ((var46.g & 2) != 0) {
                                       var7.append('\n');
                                       var7.append(aeii.f.a(100));
                                    }

                                    if ((var46.g & 1) != 0) {
                                       var7.append('\n');
                                       var7.append(aeii.f.a(99));
                                    }

                                    var7.append("\n-----------\n");
                                    var6 = var7.toString() + var6;
                                 }

                                 var12.a((String)null, (String)var6, this.a, this.b - var5.c);
                                 var28.a((e)var5, 0, 0, (int)0);
                                 var28.a((e)var12, 0, var5.c, (int)0);
                                 var28.b = true;
                                 var28.a((f)this);
                                 aeii.f.a.a((f)var28);
                              }

                              aeii.f.a.b(256);
                           }

                           if (this.d == 1) {
                              if (aeii.f.a.a((int)16) && this.b != null) {
                                 a var48 = this.a((int)this.h, this.i, (byte)0);
                                 if (this.b[this.h][this.i] > 0 && (var48 == null || var48 == this.b)) {
                                    this.A = this.b.b;
                                    this.B = this.b.c;
                                    this.b.a(this.h, this.i, true);
                                    this.j = this.b;
                                    this.d = false;
                                    this.b = false;
                                    this.b = null;
                                    this.a = null;
                                    this.e = false;
                                    this.f = false;
                                    this.d = 2;
                                    aeii.c.b(10, 1);
                                 }

                                 aeii.f.a.b(16);
                              }
                           } else if (this.d == 0) {
                              if (this.n == 1) {
                                 if (aeii.f.a.a((int)128)) {
                                    if (++this.l >= this.d.length) {
                                       this.l = 0;
                                    }

                                    this.u = true;
                                    this.v = true;
                                 } else if (aeii.f.a.a((int)64)) {
                                    if (--this.l < 0) {
                                       this.l = (byte)(this.d.length - 1);
                                    }

                                    this.u = true;
                                    this.v = true;
                                 } else if (aeii.f.a.a((int)16)) {
                                    if (!this.b) {
                                       this.a[this.h][this.i] = this.l;
                                       this.j();
                                    } else {
                                       if (this.b[this.h][this.i] == 0) {
                                          this.b[this.h][this.i] = 1;
                                       } else {
                                          this.b[this.h][this.i] = 0;
                                       }

                                       this.b = false;

                                       for(int var59 = 0; var59 < this.f; ++var59) {
                                          for(int var63 = 0; var63 < this.g; ++var63) {
                                             if (this.b[var59][var63] > 0) {
                                                this.b = true;
                                                break;
                                             }
                                          }

                                          if (this.b) {
                                             break;
                                          }
                                       }
                                    }
                                 } else if (aeii.f.a.a((int)512)) {
                                    if (this.l == this.a[this.h][this.i]) {
                                       if (++this.l >= this.d.length) {
                                          this.l = 0;
                                       }

                                       this.a[this.h][this.i] = this.l;
                                       this.j();
                                    } else {
                                       this.l = this.a[this.h][this.i];
                                    }

                                    this.u = true;
                                    this.v = true;
                                 } else if (aeii.f.a.a((int)256)) {
                                    if (this.l == this.a[this.h][this.i]) {
                                       if (--this.l < 0) {
                                          this.l = (byte)(this.d.length - 1);
                                       }

                                       this.a[this.h][this.i] = this.l;
                                       this.j();
                                    } else {
                                       this.l = this.a[this.h][this.i];
                                    }

                                    this.u = true;
                                    this.v = true;
                                 } else if (aeii.f.a.a((int)32)) {
                                    if (this.b) {
                                       this.a(this.o);
                                    } else {
                                       this.l = this.a[this.h][this.i];
                                       this.A();

                                       for(int var58 = 0; var58 < this.o.length; ++var58) {
                                          this.o[var58] = 0;
                                          this.b[var58] = false;
                                       }

                                       this.o[this.l] = 100;
                                       this.b[this.l] = true;
                                       this.a.setSelectedFlags(this.b);
                                       this.u = true;
                                       this.v = true;
                                    }
                                 } else if (!aeii.f.a.a((int)16384)) {
                                    if (aeii.f.a.a((int)8192)) {
                                       if (this.f > 0) {
                                          this.a((byte[][])this.b, (int)0);
                                          this.b = false;
                                          this.n = 2;
                                          this.b = 0;
                                          this.s = null;
                                          this.u = true;
                                          this.v = true;
                                       }
                                    } else if (aeii.f.a.a(d)) {
                                       this.a = new e((byte)11, 0);
                                       this.a.a(d, 2, 2, -1, this.b, 20, 0);
                                       this.a.a((f)this);
                                       aeii.f.a.a((f)this.a);
                                    }
                                 } else if (!this.b) {
                                    this.b[this.h][this.i] = 1;
                                    this.b = true;
                                 } else if (this.b[this.h][this.i] == 0) {
                                    this.b[this.h][this.i] = 1;
                                 } else {
                                    var4 = this.f;
                                    int var62 = this.g;
                                    var11 = -1;
                                    int var65 = -1;

                                    for(int var66 = 0; var66 < this.f; ++var66) {
                                       for(int var30 = 0; var30 < this.g; ++var30) {
                                          if (this.b[var66][var30] > 0) {
                                             if (var66 < var4) {
                                                var4 = var66;
                                             }

                                             if (var66 > var11) {
                                                var11 = var66;
                                             }

                                             if (var30 < var62) {
                                                var62 = var30;
                                             }

                                             if (var30 > var65) {
                                                var65 = var30;
                                             }
                                          }
                                       }
                                    }

                                    var45 = 1;

                                    for(int var67 = var4; var67 <= var11; ++var67) {
                                       for(int var31 = var62; var31 <= var65; ++var31) {
                                          if (this.b[var67][var31] == 0) {
                                             var45 = 0;
                                          }

                                          this.b[var67][var31] = 1;
                                       }
                                    }

                                    if (var45) {
                                       if (var4 == 0 && var62 == 0 && var11 == this.f - 1 && var65 == this.g - 1) {
                                          this.a((byte[][])this.b, (int)0);
                                          this.b = false;
                                       } else {
                                          this.a((byte[][])this.b, (int)1);
                                       }
                                    }
                                 }
                              } else if (this.n == 2) {
                                 if (aeii.f.a.a((int)128)) {
                                    if (++this.m >= 12) {
                                       this.m = 0;
                                    }

                                    this.s = null;
                                    this.u = true;
                                    this.v = true;
                                 } else if (aeii.f.a.a((int)64)) {
                                    if (--this.m < 0) {
                                       this.m = 11;
                                    }

                                    this.s = null;
                                    this.u = true;
                                    this.v = true;
                                 } else if (aeii.f.a.a((int)16)) {
                                    a var54;
                                    if ((var54 = this.a((int)this.h, this.i, (byte)0)) == null) {
                                       aeii.a.b(this.m, this.b, this.h, this.i, true);
                                    } else {
                                       var54.a();
                                    }
                                 } else if (aeii.f.a.a((int)512)) {
                                    a var55;
                                    if ((var55 = this.a((int)this.h, this.i, (byte)0)) != null) {
                                       if (this.m != var55.d) {
                                          this.m = var55.d;
                                       } else {
                                          if (++this.m >= 12) {
                                             this.m = 0;
                                          }

                                          var55.a();
                                          aeii.a.b(this.m, this.b, this.h, this.i, true);
                                       }
                                    } else {
                                       aeii.a.b(this.m, this.b, this.h, this.i, true);
                                    }

                                    this.s = null;
                                    this.u = true;
                                    this.v = true;
                                 } else if (aeii.f.a.a((int)256)) {
                                    a var56;
                                    if ((var56 = this.a((int)this.h, this.i, (byte)0)) != null) {
                                       if (this.m != var56.d) {
                                          this.m = var56.d;
                                       } else {
                                          if (--this.m < 0) {
                                             this.m = 11;
                                          }

                                          var56.a();
                                          aeii.a.b(this.m, this.b, this.h, this.i, true);
                                       }
                                    } else {
                                       aeii.a.b(this.m, this.b, this.h, this.i, true);
                                    }

                                    this.s = null;
                                    this.u = true;
                                    this.v = true;
                                 } else if (aeii.f.a.a((int)8192)) {
                                    if (++this.b >= this.f) {
                                       this.b = 0;
                                       this.n = 1;
                                    }

                                    this.s = null;
                                    this.u = true;
                                    this.v = true;
                                 } else if (aeii.f.a.a(d)) {
                                    this.a = new e((byte)11, 0);
                                    this.a.a(d, 2, 2, -1, this.b, 20, 0);
                                    this.a.a((f)this);
                                    aeii.f.a.a((f)this.a);
                                 }
                              } else if (aeii.f.a.a((int)512)) {
                                 var24 = 0;
                                 a var53 = this.b[this.b];
                                 if (this.h != null && this.h.d == 9) {
                                    var53 = this.a[this.b][(this.h.i + 1) % this.a[this.b]];
                                 }

                                 while(true) {
                                    ++var24;
                                    if (var24 >= this.a[this.b] || var53.f != 3) {
                                       if (var53 != null && var53.f != 3) {
                                          this.d(var53.b, var53.c);
                                          this.a(var53.m + 12, var53.n + 12);
                                       }
                                       break;
                                    }

                                    var53 = this.a[this.b][(var53.i + 1) % this.a[this.b]];
                                 }
                              } else if (aeii.f.a.a((int)32)) {
                                 if (this.c) {
                                    this.a((byte[][])this.b, (int)0);
                                    this.b = false;
                                    this.c = false;
                                 } else {
                                    this.b = this.a((int)this.h, this.i, (byte)0);
                                    if (this.b != null) {
                                       this.a((byte[][])this.b, (int)0);
                                       this.b.a(this.b);
                                       this.c = true;
                                       this.b = true;
                                       this.aa = 12;
                                    }
                                 }

                                 aeii.f.a.b(32);
                              } else if (aeii.f.a.a((int)16) || aeii.f.a.a(d)) {
                                 this.b = this.a((int)this.h, this.i, (byte)0);
                                 if (this.b != null && this.b.f == 0 && this.b.e == this.b) {
                                    byte[] var52;
                                    if ((var52 = this.a((a)this.b, (byte)1)).length > 1) {
                                       this.b(var52);
                                       aeii.c.b(11, 1);
                                    } else {
                                       this.l = false;
                                       this.b(this.b);
                                    }
                                 } else if (this.a(this.h, this.i) == 9 && this.b(this.h, this.i, this.b)) {
                                    byte[] var51 = new byte[]{0};
                                    this.b(var51);
                                    aeii.c.b(11, 1);
                                 } else {
                                    this.b = null;
                                    this.a = new e((byte)11, 0);
                                    this.a.a(this.f, 2, 2, -1, this.b, 20, 0);
                                    this.a.a((f)this);
                                    aeii.f.a.a((f)this.a);
                                    aeii.c.b(11, 1);
                                 }

                                 aeii.f.a.b();
                              }
                           }
                        } else {
                           if (!aeii.f.a.a((int)4) && !aeii.f.a.a((int)1)) {
                              if (aeii.f.a.a((int)8) || aeii.f.a.a((int)2)) {
                                 ++this.z;
                                 if (this.z >= this.a.length) {
                                    this.z = 0;
                                 }

                                 aeii.f.a.b(8);
                                 aeii.f.a.b(2);
                                 this.i = true;
                              }
                           } else {
                              --this.z;
                              if (this.z < 0) {
                                 this.z = this.a.length - 1;
                              }

                              aeii.f.a.b(4);
                              aeii.f.a.b(1);
                              this.i = true;
                           }

                           this.d(this.a[this.z].b, this.a[this.z].c);
                           if (this.i) {
                              this.h = this.a((int)this.h, this.i, (byte)0);
                              this.v = true;
                           }

                           if (aeii.f.a.a((int)16)) {
                              if (this.d == 6) {
                                 this.a(this.b, this.a[this.z]);
                              } else if (this.d == 7) {
                                 this.a(this.a[this.z], this.b);
                                 this.b.d();
                                 this.d = 0;
                              }

                              this.a = null;
                              this.b = false;
                              this.c = false;
                              this.e = false;
                              this.f = false;
                           }

                           this.i = false;
                        }
                     }
                  }
               } else if (this.aC != 1 && (this.d != 14 || this.aC == 2)) {
                  if (this.aC == 0) {
                     this.g = true;
                     this.G = 0;
                  } else if (this.aC == 2) {
                     if (this.d == 14) {
                        this.b = super.k;
                        this.p = super.m;
                        this.a(this.f, super.m, super.k, (f)null);
                        return;
                     }

                     if (this.x <= 7) {
                        this.c(this.x);
                        this.q();
                        this.d = 0;
                     }
                  }
               } else {
                  ++this.x;
                  if (this.x > this.q) {
                     if (c[this.q] >= 0) {
                        String var25 = e[c[this.q]];
                        e var26 = this.a((String)null, aeii.f.a(82, (String)var25), super.k, 3000);
                        aeii.f.a.a((f)var26);
                     }

                     this.q = this.x;

                     try {
                        byte[] var27 = new byte[]{(byte)this.q};
                        aeii.c.a("settings", 1, var27);
                     } catch (Exception var8) {
                     }
                  }

                  this.aC = 2;
               }

               var11 = 0;

               for(int var42 = this.a.size(); var11 < var42; ++var11) {
                  ((a)this.a.elementAt(var11)).c();
               }

               if (this.a - this.i >= 300L) {
                  this.J = (this.J + 1) % this.e.length;
                  this.d[this.K] = this.e[this.J];
                  this.i = this.a;
               }

               if (this.j == null) {
                  this.c(this.q.m + 12, this.q.n + 12);
               } else {
                  this.c(this.j.m + 12, this.j.n + 12);
               }

               if (this.e && aeii.f.a.b(e)) {
                  if (this.d == 1) {
                     this.d = 0;
                     this.a((byte[][])this.b, (int)0);
                     this.b = null;
                     this.q.a(c[0]);
                     this.d(this.b.b, this.b.c);
                     this.b = null;
                  } else if (this.d == 6 || this.d == 7) {
                     this.d = this.e;
                     this.a((byte[][])this.b, (int)0);
                     this.q.a(c[0]);
                     this.d(this.b.b, this.b.c);
                     aeii.f.a.a((f)this.a);
                  }

                  this.b = false;
                  this.c = false;
                  aeii.f.a.b(e);
                  this.e = false;
                  this.f = false;
               }

               for(int var20 = this.c.size() - 1; var20 >= 0; --var20) {
                  a.a.a.g var43;
                  (var43 = (a.a.a.g)this.c.elementAt(var20)).c();
                  if (!var43.b) {
                     this.c.removeElement(var43);
                  }
               }

               var11 = 0;

               for(int var44 = this.d.size(); var11 < var44; ++var11) {
                  this.c.addElement(this.d.elementAt(var11));
               }

               this.d.removeAllElements();
            }
         }
      }

   }

   private void d(a var1) {
      int var2 = var1.n + 24;
      this.a(this.e, var1.m, var2 - this.e.p, 0, -2, 1, 100);

      for(int var3 = 0; var3 < 5; ++var3) {
         this.a(this.o, var1.m, var2 - this.o.p, var3 + -2, aeii.c.a(-4, -1), 1, 50 + 50 * aeii.c.a((int)4));
      }

      this.a(this.f, var1.m, var1.n, 0, 0, 1, 100);
   }

   private void a(a var1, byte var2) {
      this.e = var1;
      this.g = var2;
      this.a(this.f, var1.m - 8, var1.n - 8, 1, 1, 3, 50);
      this.a(this.f, var1.m + 8, var1.n - 8, -1, 1, 3, 50);
      this.a(this.f, var1.m - 8, var1.n + 8, 1, -1, 3, 50);
      this.a(this.f, var1.m + 8, var1.n + 8, -1, -1, 3, 50);
      this.f = this.a;
   }

   private boolean a(int var1, int var2) {
      return this.v == this.b(var1) && this.w == this.c(var2);
   }

   private boolean b(int var1, int var2) {
      return this.a(var1 * 24 + 12, var2 * 24 + 12);
   }

   private int b(int var1) {
      if (this.t > this.a) {
         if ((var1 = this.c - var1) > 0) {
            var1 = 0;
         } else if (var1 < this.a - this.t) {
            var1 = this.a - this.t;
         }
      } else {
         var1 = (this.a - this.t) / 2;
      }

      return var1;
   }

   private int c(int var1) {
      if (this.u > this.b) {
         if ((var1 = this.p - var1) > 0) {
            var1 = 0;
         } else if (var1 < this.b - this.u) {
            var1 = this.b - this.u;
         }
      } else {
         var1 = (this.b - this.u) / 2;
      }

      return var1;
   }

   private void a(int var1, int var2) {
      this.v = this.b(var1);
      this.w = this.c(var2);
   }

   private void b(int var1, int var2) {
      this.a(var1 * 24 + 12, var2 * 24 + 12);
   }

   private void c(int var1, int var2) {
      this.w = true;
      var1 = this.b(var1);
      var2 = this.c(var2);
      var1 -= this.v;
      var2 -= this.w;
      if (var1 != 0) {
         int var3 = var1 / 2;
         if (var1 < 0) {
            if (var3 > -this.Z) {
               var3 = -this.Z;
            } else if (var3 < -this.Y) {
               var3 = -this.Y;
            }
         } else if (var3 < this.Z) {
            var3 = this.Z;
         } else if (var3 > this.Y) {
            var3 = this.Y;
         }

         this.v += var3;
         this.w = false;
      }

      if (var2 != 0) {
         int var8 = var2 / 2;
         if (var2 < 0) {
            if (var8 > -this.Z) {
               var8 = -this.Z;
            } else if (var8 < -this.Y) {
               var8 = -this.Y;
            }
         } else if (var8 < this.Z) {
            var8 = this.Z;
         } else if (var8 > this.Y) {
            var8 = this.Y;
         }

         this.w += var8;
         this.w = false;
      }

   }

   private void d(int var1, int var2) {
      this.h = var1;
      this.i = var2;
      this.q.b(var1 * 24, var2 * 24);
      this.h = this.a((int)this.h, this.i, (byte)0);
      this.v = true;
   }

   private void b(Graphics var1) {
      int var2 = -this.v / 24;
      int var3 = -this.w / 24;
      if (var2 < 0) {
         var2 = 0;
      }

      if (var3 < 0) {
         var3 = 0;
      }

      int var4 = (this.a - this.v - 1) / 24;
      int var5 = (this.b - this.w - 1) / 24;
      if (var4 >= this.f) {
         var4 = this.f - 1;
      }

      if (var5 >= this.g) {
         var5 = this.g - 1;
      }

      int var6;
      if (this.v < 0) {
         var6 = this.v % 24;
      } else {
         var6 = this.v;
      }

      int var7;
      if (this.w < 0) {
         var7 = this.w % 24;
      } else {
         var7 = this.w;
      }

      byte var8 = 0;
      if (this.c) {
         var8 = 1;
      }

      for(int var12 = var3; var12 <= var5; ++var12) {
         int var9 = var6;

         for(int var10 = var2; var10 <= var4; ++var10) {
            int var11 = this.a[var10][var12];
            if (!this.b || this.b[var10][var12] == 0 || this.aa > 0) {
               this.d[var11].a(var1, var9, var7);
            }

            if (this.b && this.b[var10][var12] > 0) {
               if (this.aa != 0) {
                  var1.clipRect(var9 + this.aa, var7 + this.aa, 24 - (this.aa << 1), 24 - (this.aa << 1));
               }

               var1.drawImage(this.a[var8][var11], var9, var7, 0);
               if (this.aa != 0) {
                  var1.setClip(0, 0, this.a, this.b);
               }
            }

            if ((var11 = var12 + 1) < this.g && this.k[this.a[var10][var11]] == 9) {
               this.d[28].a(var1, var9, var7);
            }

            var9 += 24;
         }

         var7 += 24;
      }

   }

   private void c(Graphics var1) {
      var1.setFont(aeii.c.b);
      var1.setColor(0);
      var1.fillRect(0, 0, super.j, super.k);
      var1.setColor(16777215);
      aeii.c.b(var1, aeii.f.a(58), super.j / 2, (super.k - aeii.c.b.getHeight()) / 2, 17);
   }

   public static final int a(int var0, int var1, int var2, int var3) {
      if (var2 == 0) {
         return var0;
      } else if (var2 == var3) {
         return var1;
      } else {
         int var4 = var0 & 16711680;
         int var5 = var0 & '\uff00';
         var0 &= 255;
         var4 = (((var1 & 16711680) - var4) * var2 / var3 & 16711680) + var4;
         var5 = (((var1 & '\uff00') - var5) * var2 / var3 & '\uff00') + var5;
         var0 = ((var1 & 255) - var0) * var2 / var3 + var0;
         return var4 | var5 | var0;
      }
   }

   private void a(int var1, int var2, int var3) {
      this.af = var2;
      this.ag = var3;
      this.j = this.h;

      try {
         this.g = new d("intro" + var1, this.a, -1);
      } catch (Exception var4) {
      }

      this.ab = aeii.c.c;
      if (this.g != null) {
         this.ah = this.g.b;
         this.G = 0;
         this.ad = (super.k - this.g.b) / this.ab;
      } else {
         this.ah = 0;
         this.G = 16;
         this.ad = super.k / this.ab;
      }

      this.o = aeii.f.a(aeii.f.a(var1 + 215), super.j, aeii.c.a);
      this.ac = super.k - this.ab;
      this.ae = 0;
      this.x = false;
      aeii.f.a.b();
      this.h = 3;
   }

   private void a(String var1) {
      this.j = this.h;
      this.o = aeii.f.a(var1, super.j, aeii.c.a);
      this.G = 16;
      this.x = false;
      this.af = 3;
      this.ag = 3;
      this.ah = 0;
      this.ab = aeii.c.c;
      this.ad = super.k / this.ab;
      this.ac = super.k - this.ab;
      this.ae = 0;
      aeii.f.a.b();
      this.h = 3;
   }

   private void d(Graphics var1) {
      var1.setFont(aeii.c.a);
      var1.setColor(0);
      var1.fillRect(0, 0, super.j, super.k);
      if (this.g != null) {
         if (this.x || (this.af != 2 || this.G < 40) && (this.af == 2 || this.G < 16)) {
            if ((this.ag != 2 || !this.x) && (this.af != 2 || this.x)) {
               if ((this.ag != 3 || !this.x) && (this.af != 3 || this.x)) {
                  this.g.a(var1, 0, 0);
                  if (this.x) {
                     if (this.ag == 1 && this.G <= 16) {
                        a(var1, 0, 16 - this.G, 16, 0, 0, 0, super.j, super.k);
                     }
                  } else if (this.af == 0) {
                     if (this.G <= 16) {
                        a(var1, 16777215, this.G, 16, 1, 0, 0, super.j, super.k);
                     }
                  } else if (this.af == 1 && this.G <= 16) {
                     a(var1, 0, this.G, 16, 1, 0, 0, super.j, this.g.b);
                  }
               } else {
                  this.g.a(var1, 0, 0);
                  int var2 = 255 * (16 - this.G) / 16;
                  aeii.c.a(var1, var2 << 24, 0, 0, this.g.a, this.g.b);
               }
            } else {
               a(var1, this.G, 40, 0, this.g, 0, 0, 2);
            }
         } else {
            this.g.a(var1, 0, 0);
         }
      }

      var1.setClip(0, 0, super.j, super.k);
      int var9 = this.G;
      if (this.x && this.ag == 2) {
         var9 -= 24;
         if (var9 < 0) {
            var9 = 0;
         }
      }

      int var3 = this.ac;

      for(int var4 = this.ae; var4 < this.ae + this.ad && var4 < this.o.length && var3 < super.k - this.ab; ++var4) {
         int var5 = this.ab;
         if (var3 < this.ah + this.ab) {
            var5 = var3 - this.ah;
         } else if (var3 + this.ab > super.k - this.ab) {
            var5 = super.k - this.ab - var3;
         }

         if (var5 < this.ab) {
            int var7 = this.ab;
            int var10 = 14672074;
            int var11 = var5 * 14614528 / var7 & 16711680;
            int var8 = var5 * '\ue000' / var7 & '\uff00';
            int var13 = var5 * 202 / var7;
            var5 = var11 | var8 | var13;
         } else {
            var5 = 14672074;
         }

         if (this.x) {
            var5 = a(0, var5, var9, 16);
         }

         var1.setColor(var5);
         aeii.c.b(var1, this.o[var4], super.l, var3 + 3, 17);
         var3 += this.ab;
      }

      if (!this.x) {
         this.a(var1, d, 2, super.k);
      }

   }

   private void e(Graphics var1) {
      if (this.F == 0) {
         var1.setColor(16777215);
         var1.fillRect(0, 0, super.j, super.k);
         a(var1, this.G, 40, 0, this.b, (super.j - this.b.a) / 2, (super.k - this.b.b) / 2, 4);
      } else if (this.F == 1) {
         var1.setColor(16777215);
         var1.fillRect(0, 0, super.j, super.k);
         a(var1, this.G, 40, 0, this.b, (super.j - this.b.a) / 2, (super.k - this.b.b) / 2, 4);
      } else if (this.F == 3) {
         var1.setColor(0);
         var1.fillRect(0, 0, super.j, super.k);
         a(var1, this.G, 40, 0, this.d, 0, 0, 1);
         var1.setClip(0, 0, super.j, super.k);
      } else {
         if (this.F == 4) {
            if (this.G >= 16) {
               if (this.c == null) {
                  var1.setColor(0);
                  var1.fillRect(0, 0, super.j, super.k);
                  this.d.a(var1, 0, 0);
               } else {
                  this.d.a(var1, 0, 0);
                  if (this.P != -1) {
                     int var2 = (this.d.b + this.e.b - 1) / this.e.b;

                     for(int var3 = 0; var3 < var2; ++var3) {
                        this.e.a(var1, 4 + this.P - var3 * 38, 6 + this.e.b * var3);
                     }
                  }

                  this.c.a(var1, 0, 0);
               }

               if (this.k && this.c != null) {
                  var1.setColor(16777215);
                  var1.setFont(aeii.c.b);
                  a(var1, aeii.f.a(59), super.l, super.k - this.c.p - 1, 33, 16777215, 0);
                  return;
               }
            } else {
               if (this.c != null) {
                  this.c.a(var1, 0, 0);
               } else {
                  var1.setColor(0);
                  var1.fillRect(0, 0, super.j, super.k);
               }

               aeii.c.a(var1, 255 * (16 - this.G) / 16 << 24, 0, 0, super.j, super.k);
               this.d.a(var1, 0, 0);
            }
         }

      }
   }

   public static final void a(Graphics var0, String var1, int var2, int var3, int var4, int var5, int var6) {
      var0.setColor(0);
      var0.drawString(var1, var2 - 1, var3 - 1, var4);
      var0.drawString(var1, var2 - 1, var3, var4);
      var0.drawString(var1, var2 - 1, var3 + 1, var4);
      var0.drawString(var1, var2, var3 - 1, var4);
      var0.drawString(var1, var2, var3 + 1, var4);
      var0.drawString(var1, var2 + 1, var3 + 1, var4);
      var0.drawString(var1, var2 + 1, var3, var4);
      var0.drawString(var1, var2 + 1, var3 - 1, var4);
      var0.setColor(var5);
      var0.drawString(var1, var2, var3, var4);
   }

   public final void a(Graphics var1) {
      if (this.h == 4) {
         var1.setColor(16777215);
         var1.fillRect(0, 0, super.j, super.k);
         var1.setFont(aeii.c.b);
         var1.setColor(0);
         var1.drawString(aeii.f.a(58), super.j / 2, super.k / 2 - 1, 33);
         int var21;
         if ((var21 = super.k / 18) < 12) {
            var21 = 12;
         }

         int var31 = super.k / 2 + 1;
         var1.setColor(13553358);
         aeii.e.a(var1, 1, var31, super.j - 2, var21);
         var1.setColor(2370117);
         aeii.e.a(var1, 2, var31 + 2, this.X * (super.j - 6) / 100, var21 - 4);
      } else if (this.h == 2) {
         Graphics var20 = var1;
         g var12 = this;
         int var29 = 0;
         int var35 = 0;
         if (this.C) {
            var29 = aeii.c.a() % 10;
            var35 = aeii.c.a() % 3;
         }

         var1.translate(0, this.ak);
         var1.setClip(0, 0, this.a, this.o);
         this.a.a(var1, var29, var35);
         this.b.a(var1, var29 + this.c, var35);
         var1.setColor(0);
         var1.fillRect(this.c - 1 + var29, 0, 2, this.o);
         this.a.b(var1);
         this.b.b(var1);
         Vector var30 = new Vector(this.g.size());

         for(int var36 = 0; var36 < var12.g.size(); ++var36) {
            a.a.a.g var42 = (a.a.a.g)var12.g.elementAt(var36);
            int var47 = 0;
            if (var42.d) {
               var30.addElement(var42);
            } else {
               for(var47 = 0; var47 < var30.size(); ++var47) {
                  a.a.a.g var49;
                  if ((var49 = (a.a.a.g)var30.elementAt(var47)).d || var42.n + var42.p < var49.n + var49.p) {
                     var30.insertElementAt(var42, var47);
                     break;
                  }
               }
            }

            if (var47 == var30.size()) {
               var30.addElement(var42);
            }
         }

         var12.g = var30;

         for(int var37 = 0; var37 < var12.g.size(); ++var37) {
            a.a.a.g var43;
            if ((var43 = (a.a.a.g)var12.g.elementAt(var37)).l == 0) {
               var20.setClip(0, 0, var12.c, var12.o);
            } else if (var43.l == 1) {
               var20.setClip(var12.c, 0, var12.c, var12.o);
            } else {
               var20.setClip(0, 0, var12.a, var12.o);
            }

            var43.a(var20, 0, var43.q);
         }

         var20.translate(0, -var12.ak);
         var20.setClip(0, 0, var12.j, var12.k);
         if (var12.a) {
            var12.a = false;
            var35 = var12.k - a;
            var20.setColor(14672074);
            var20.fillRect(0, var35, var12.j, a);
            aeii.e.a(var20, 0, var35, var12.j, a, 0);
            var20.setClip(0, 0, var12.j, var12.k);
            var12.a.a(var20);
            var20.translate(var12.c, 0);
            var12.b.a(var20);
            var20.translate(-var12.c, 0);
         }

         if (var12.F) {
            var12.F = false;
            var12.a(var20, var12.a.a, var12.b.a, 0);
         }

         if (var12.E) {
            a(var20, 0, var12.G, 16, 1, 0, 0, var12.j, var12.k);
         }

      } else if (this.h == 3) {
         this.d(var1);
      } else if (this.j) {
         this.c(var1);
      } else if (this.g) {
         if (this.G >= 16) {
            if (this.d != 11 && this.d != 10) {
               this.c(var1);
            } else {
               var1.setColor(0);
               var1.fillRect(0, 0, super.j, super.k);
            }
         } else {
            a(var1, 0, this.G, 16, 0, 0, 0, super.j, super.k);
         }
      } else {
         if (this.h == 0) {
            this.e(var1);
         } else if (this.d == 14) {
            var1.setClip(0, 0, super.j, super.k);
            var1.setColor(0);
            var1.fillRect(0, 0, super.j, super.k);
         } else if (this.d == 10 && this.aC > 0) {
            this.c(var1);
         } else if (this.d == 11 && !this.g) {
            String var34 = aeii.f.a(57);
            var1.setClip(0, 0, super.j, super.k);
            var1.setFont(aeii.c.b);
            var1.setColor(0);
            var1.fillRect(0, 0, super.j, super.k);
            if (this.c == 0) {
               var1.setColor(16777215);
               if (this.f != null) {
                  this.f.a(var1, super.l, super.m, 3);
                  aeii.c.b(var1, var34, super.l, super.k - 2, 33);
               } else {
                  int var27 = super.m - aeii.c.b.getHeight() / 2;
                  aeii.c.b(var1, var34, super.l, var27, 17);
               }
            }
         } else {
            var1.setClip(0, 0, this.a, this.b);
            if (this.t < this.a || this.u < this.b) {
               var1.setColor(0);
               var1.fillRect(0, 0, this.a, this.b);
            }

            if (this.C) {
               int var2 = aeii.c.a() % 10;
               int var3 = aeii.c.a() % 4;
               var1.translate(var2, var3);
               this.b(var1);
               var1.translate(-var2, -var3);
            } else {
               this.b(var1);
            }

            int var13 = 0;

            for(int var22 = this.a.size(); var13 < var22; ++var13) {
               a var4;
               if ((var4 = (a)this.a.elementAt(var13)).f == 3) {
                  this.a.a(var1, this.v + var4.m, this.w + var4.n);
               } else if (var4 != this.b) {
                  var4.a(var1, this.v, this.w);
               }
            }

            var13 = 0;

            for(int var23 = this.a.size(); var13 < var23; ++var13) {
               ((a)this.a.elementAt(var13)).b(var1, this.v, this.w);
            }

            if (this.b != null) {
               var1.setColor(14745682);
               int var24 = 12 + this.al / 4;
               int var7 = 24 - var24;
               int var32 = 0;

               for(int var5 = this.b.size(); var32 < var5; ++var32) {
                  short[] var8;
                  int var6 = (var8 = (short[])this.b.elementAt(var32))[0] * 24 + this.v;
                  var13 = var8[1] * 24 + this.w;
                  int var9 = var6 + 12;
                  int var10 = var13 + 12;
                  if (var32 != 0) {
                     short[] var11;
                     if ((var11 = (short[])this.b.elementAt(var32 - 1))[0] == var8[0] + 1) {
                        var1.fillRect(var6 + var7, var10 - this.am, var24, this.al);
                     } else if (var11[0] == var8[0] - 1) {
                        var1.fillRect(var6, var10 - this.am, var24, this.al);
                     } else if (var11[1] == var8[1] + 1) {
                        var1.fillRect(var9 - this.am, var13 + var7, this.al, var24);
                     } else if (var11[1] == var8[1] - 1) {
                        var1.fillRect(var9 - this.am, var13, this.al, var24);
                     }
                  }

                  if (var32 == var5 - 1) {
                     var1.setClip(0, 0, this.a, this.b);
                     this.r.a((Graphics)var1, var9, var10, (int)3);
                  } else {
                     short[] var53;
                     if ((var53 = (short[])this.b.elementAt(var32 + 1))[0] == var8[0] + 1) {
                        var1.fillRect(var6 + var7, var10 - this.am, var24, this.al);
                     } else if (var53[0] == var8[0] - 1) {
                        var1.fillRect(var6, var10 - this.am, var24, this.al);
                     } else if (var53[1] == var8[1] + 1) {
                        var1.fillRect(var9 - this.am, var13 + var7, this.al, var24);
                     } else if (var53[1] == var8[1] - 1) {
                        var1.fillRect(var9 - this.am, var13, this.al, var24);
                     }
                  }
               }
            }

            if (this.b != null) {
               this.b.a(var1, this.v, this.w);
               this.b.b(var1, this.v, this.w);
            }

            if (this.d) {
               this.q.a((Graphics)var1, this.v + 12, this.w + 12, (int)3);
            }

            var13 = 0;

            for(int var25 = this.c.size(); var13 < var25; ++var13) {
               a.a.a.g var50;
               (var50 = (a.a.a.g)this.c.elementAt(var13)).a(var1, this.v, this.w + var50.q);
            }

            var1.setClip(0, 0, super.j, super.k);
            var13 = super.k - a;
            if (this.Q > 0) {
               aeii.e.a(var1, 0, var13, this.a, a, 14);
               var1.setClip(0, 0, super.j, super.k);
            }

            int var26 = a - 24 >> 1;
            int var48 = 24 + (var26 << 1);
            int var33 = super.j - var48;
            var13 += this.Q;
            if (this.u) {
               this.u = false;
               int var39 = super.k - a / 2 + this.Q;
               byte var51 = 10;
               if (this.a <= 120) {
                  var51 = 4;
               }

               if (this.n == 1) {
                  aeii.e.a(var1, 0, var13, var33 + 1, a, 0, 2370117, f[0], this.Q, a);
                  aeii.c.a(var1, this.l + 1 + "/" + this.d.length, var51 + 1, var39, 1, 6);
               } else if (this.n == 2) {
                  aeii.e.a(var1, 0, var13, var33 + 1, a, 0, 2370117, f[this.d[this.b]], this.Q, a);
                  aeii.c.a(var1, this.m + 1 + "/" + 12, var51 + 1, var39, 1, 6);
               } else {
                  aeii.e.a(var1, 0, var13, var33 + 1, a, 0, 2370117, f[this.d[this.b]], this.Q, a);
                  if (this.c == 1) {
                     int var44 = var33 / 2;
                     this.m.a(var1, 0, var44, var39, 6);
                     aeii.c.a(var1, this.a((int)-1, -1, (byte)this.b) - this.a((int)10, -1, (byte)this.b) + "/" + this.s, var44 + this.m.o + 1, var39, 1, 6);
                  }

                  this.m.a(var1, 1, var51, var39, 6);
                  int var45 = var51 + this.m.o + 1;
                  if (!aeii.c.a && this.m[this.b] != 1) {
                     aeii.c.a(var1, "- - -", var45, var39, 1, 6);
                  } else {
                     aeii.c.a(var1, "" + this.b[this.b], var45, var39, 1, 6);
                  }
               }

               var1.setClip(0, 0, super.j, super.k);
            }

            if (this.v) {
               this.v = false;
               if (var26 > 0) {
                  a(var1, var33, var13, var48, a);
               }

               int var40 = var33 + var26;
               int var46 = var13 + var26;
               if (this.n == 0 || this.n == 2) {
                  this.l = this.a[this.h][this.i];
               }

               this.d[this.l].a(var1, var40, var46);
               if (this.n == 2) {
                  if (this.s == null) {
                     this.s = aeii.a.b(this.m, this.b, 0, 0, false);
                  }

                  this.s.a(var1, var40, var46);
               }

               if (this.n == 0) {
                  String var52 = "." + a[this.k[this.l]];
                  aeii.c.a(var1, var52, var40 + 24, var46 + 24, 0, 40);
               }

               if (var26 == 0) {
                  var1.setColor(0);
                  var1.drawRect(var40, var46, 24, 24);
               }
            }

            if (this.d == 6 && this.a[this.z].f != 4) {
               int var41 = 0;
               if (this.i * 24 <= this.b / 2 - 24) {
                  var41 = this.b - this.c.p - this.ak + 2;
               }

               this.a(var1, this.b, this.a[this.z], var41);
            }
         }

         if (this.a()) {
            if (this.e) {
               this.a(var1, e, 1, this.b);
            }

            if (this.f) {
               this.a(var1, d, 0, this.b);
            }

            if (this.h == 1 && (this.m[this.b] == 0 || this.d == 0) && this.d != 11) {
               this.a(var1, d, 3, this.b);
            }
         }

         if (this.o || this.p) {
            int var19 = this.R;
            if (this.p) {
               var19 = 16 - this.R;
            }

            int var28 = var19 * 255 / 16;
            aeii.c.a(var1, var28 << 24, 0, 0, super.j, super.k);
         }

      }
   }

   public static void a(Graphics var0, int var1, int var2, int var3, int var4) {
      var0.setColor(4344163);
      var0.fillRect(var1, var2, var3, var4);
      var0.setColor(11384493);
      var0.fillRect(var1 + 1, var2 + 1, var3 - 2, var4 - 2);
      var0.setColor(4344163);
      var0.fillRect(var1 + 3, var2 + 3, var3 - 6, var4 - 6);
   }

   private void a(Graphics var1, a var2, a var3, int var4) {
      int var5 = this.ak - 2;
      var1.setColor(11384493);
      var1.fillRect(0, var4, this.a, var5);
      var1.setColor(0);
      var1.fillRect(0, var5 + var4, this.a, 2);
      int var6 = 0;
      int var17;
      int var7 = (var17 = var5 / 2) + var4;

      for(int var8 = 0; var8 < 3; ++var8) {
         this.l.a(var1, var8, var6 + 1, var7, 6);
         var6 += this.l.o + 2;
         int var9 = var4 + 1;
         int var10;
         if (var8 == 0) {
            if (super.j <= 132) {
               var10 = 56;
            } else {
               var10 = 61 * this.a / 176;
            }
         } else if (var8 == 1) {
            if (super.j <= 132) {
               var10 = 28;
            } else {
               var10 = 47 * this.a / 176;
            }
         } else {
            var10 = this.a - var6;
         }

         for(int var11 = 0; var11 < 2; ++var11) {
            a var12;
            a var13;
            if (var11 == 0) {
               var12 = var2;
               var13 = var3;
            } else {
               var12 = var3;
               var13 = var2;
            }

            int var14 = var17 - 2;
            var1.setColor(2172994);
            var1.fillRect(var6, var9, var10, var14);
            int var15 = var6 + 1;
            if (var8 == 0 || super.j > 132) {
               var1.setColor(f[this.d[var12.e]]);
               var1.fillRect(var15, var9 + 1, 3, var14 - 2);
               var15 += 4;
            }

            int var16 = 0;
            String var19;
            if (var8 == 0) {
               if (var11 != 0 && !var3.a((int)var2.b, (int)var2.c)) {
                  var19 = "0-0";
               } else {
                  var16 = var12.a(var13);
                  var19 = var12.d + var16 + "-" + (var12.e + var16);
               }
            } else if (var8 == 1) {
               var16 = var12.b(var13);
               var19 = "" + (var12.f + var16);
            } else {
               var19 = "" + var12.a;
            }

            aeii.c.a(var1, var19, var15, var9 + 1, 0);
            if (var16 > 0) {
               this.p.a(var1, 1, var15 + 1 + aeii.c.a((byte)0, var19), var9 + var14 / 2, 6);
            } else if (var16 < 0) {
               this.p.a(var1, 2, var15 + 1 + aeii.c.a((byte)0, var19), var9 + var14 / 2, 6);
            }

            int var20 = var6 + var10 - 2;
            if (var8 == 0 && (var12.g & 2) != 0) {
               this.s.a(var1, 1, var20, var9 + var14 / 2, 10);
               var20 -= this.s.o;
            }

            if ((var8 == 0 || var8 == 1) && (var12.g & 1) != 0) {
               this.s.a(var1, 0, var20, var9 + var14 / 2, 10);
            }

            var9 += var17;
         }

         var6 += var10;
      }

   }

   public final void a(Graphics var1, int var2, int var3, int var4) {
      int var5 = 0;
      byte var6 = 0;
      if (var2 == 1024) {
         var6 = 36;
      } else if (var2 == 2048) {
         var5 = super.j;
         var6 = 40;
      }

      this.c.a(var1, var3, var5, var4, var6);
   }

   public final a a(int var1, int var2, byte var3) {
      int var4 = 0;

      for(int var5 = this.a.size(); var4 < var5; ++var4) {
         int var6;
         int var7;
         a var8;
         if ((var8 = (a)this.a.elementAt(var4)).f == 1) {
            var6 = var8.b;
            var7 = var8.c;
         } else {
            var6 = var8.b;
            var7 = var8.c;
         }

         if (var1 == var6 && var2 == var7) {
            if (var3 == 0) {
               if (var8.f != 3) {
                  return var8;
               }
            } else if (var3 == 1 && var8.f == 3) {
               return var8;
            }
         }
      }

      return null;
   }

   public final byte a(int var1, int var2) {
      return this.k[this.a[var1][var2]];
   }

   private void m() {
      aeii.c.f();
      this.D = 0;
      this.Q = 0;
      this.d = 8;
      this.d = this.a;
   }

   private void n() {
      while(this.n == 0) {
         this.d[this.b][0] = (byte)this.h;
         this.d[this.b][1] = (byte)this.i;
         ++this.a;
         this.b = (byte)((this.b + 1) % this.f);
         if (this.m[this.b] == 2) {
            this.n();
            return;
         }

         for(int var1 = this.a.size() - 1; var1 >= 0; --var1) {
            a var2;
            if ((var2 = (a)this.a.elementAt(var1)).f == 3) {
               if (var2.d != 9 && --var2.h <= 0) {
                  var2.a();
               }
            } else {
               var2.f = 0;
               if ((var2.g & 1) != 0 && var2.i == this.b) {
                  var2.c((byte)1);
               }

               if (var2.e == this.b) {
                  var2.c((byte)2);
               }

               var2.k = 0;
            }
         }

         this.an = 0;

         for(int var3 = 0; var3 < this.a.length; ++var3) {
            for(int var5 = 0; var5 < this.a[var3].length; ++var5) {
               if (this.b(var3, var5, this.b)) {
                  if (this.a(var3, var5) == 8) {
                     this.an += 30;
                  } else if (this.a(var3, var5) == 9) {
                     this.an += 50;
                  }
               }
            }
         }

         int[] var10000 = this.b;
         byte var10001 = this.b;
         var10000[var10001] += this.an;

         for(int var4 = 0; var4 < this.e.length; ++var4) {
            this.m[var4] = 0;
         }

         if (this.m[this.b] == 1) {
            this.d(this.d[this.b][0], this.d[this.b][1]);
         }

         this.i = true;
         this.u = true;
         if (this.m[this.b] == 0) {
            this.o();
         } else {
            aeii.a.c = aeii.a.b;
         }

         if (this.a((int)-1, 0, (byte)this.b) > 0 || this.e(this.b) != 0) {
            return;
         }

         this = this;
      }

   }

   private boolean a(a var1) {
      return var1.a((short)8) && this.a(var1.b, var1.c) == 8 && !a(this.a[var1.b][var1.c]);
   }

   private boolean b(a var1) {
      if (var1.a((short)8) && this.a(var1.b, var1.c) == 8 && a(this.a[var1.b][var1.c]) && !this.a((int)var1.b, var1.c, (int)this.e[var1.e])) {
         return true;
      } else {
         return var1.a((short)16) && this.a(var1.b, var1.c) == 9 && !this.a((int)var1.b, var1.c, (int)this.e[var1.e]);
      }
   }

   private void a(byte var1, int var2, int var3) {
      this.a[var2][var3] = var1;
   }

   private void b(int var1, int var2, int var3) {
      if (a(this.a[var1][var2])) {
         this.a((byte)(37 + (var3 << 1) + (this.a[var1][var2] - 37) % 2), var1, var2);
      }

   }

   private static boolean b(byte var0) {
      return var0 >= 37 && var0 < 47 || var0 == 27;
   }

   public static boolean a(byte var0) {
      return var0 >= 37 && var0 < 47;
   }

   private int a(int var1, int var2) {
      return a(var1, var2, this.a);
   }

   private static int a(int var0, int var1, byte[][] var2) {
      int var3;
      return (var3 = var2[var0][var1] - 37 >> 1) >= 0 && var3 <= 4 ? var3 : -1;
   }

   private int d(int var1) {
      return var1 != -1 && var1 != 0 ? this.l[var1] : -1;
   }

   public final boolean a(int var1, int var2, int var3) {
      if ((var1 = this.d(this.a(var1, var2))) >= 0) {
         return var3 == this.e[var1];
      } else {
         return false;
      }
   }

   private boolean b(int var1, int var2, int var3) {
      return this.a(var1, var2) == this.d[var3];
   }

   private int e(int var1) {
      int var2 = 0;

      for(int var3 = 0; var3 < this.as; ++var3) {
         if (this.b(this.f[var3][0], this.f[var3][1], var1)) {
            ++var2;
         }
      }

      return var2;
   }

   private int a(int var1, int var2, byte var3) {
      int var4 = 0;
      int var5 = 0;

      for(int var6 = this.a.size(); var5 < var6; ++var5) {
         a var7 = (a)this.a.elementAt(var5);
         if ((var1 == -1 || var7.d == var1) && (var2 == -1 && var7.f != 3 || var2 == var7.f) && (var3 == -1 || var7.e == var3)) {
            ++var4;
         }
      }

      return var4;
   }

   private a[] a(int var1, int var2, byte var3) {
      Vector var4 = new Vector();
      int var5 = 0;

      for(int var6 = this.a.size(); var5 < var6; ++var5) {
         a var7 = (a)this.a.elementAt(var5);
         if ((var1 == -1 || var7.d == var1) && (var2 == -1 && var7.f != 3 || var2 == var7.f) && (var3 == -1 || var7.e == var3)) {
            var4.addElement(var7);
         }
      }

      a[] var8 = new a[var4.size()];
      var4.copyInto(var8);
      return var8;
   }

   private static void a(Graphics var0, int var1, int var2, int var3, d var4, int var5, int var6, int var7) {
      var2 = var4.a;
      var3 = var4.b;
      int var8 = var2 / 2;
      int var16 = var3;
      int var9 = var2 * var1 / 40;
      var2 = var2 * (40 - var1) / 160;
      var1 = var1 * 360 / 40;
      var7 = var7 * 360 / var3;

      for(int var10 = 0; var10 < var16; ++var10) {
         int var11 = var2 * aeii.f.a(var1) >> 10;
         var0.setClip(var5 + var8 - var9 / 2 + var11, var6 + var10, var9, 1);
         var4.a(var0, var5 + var11, var6);
         var1 += var7;
      }

   }

   private static void a(Graphics var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      var3 = var7 / 8 + 1;

      for(int var11 = 0; var11 < 8; ++var11) {
         if ((var6 = var2 - var11) < 0) {
            var6 = 0;
         }

         if (var6 >= 9) {
            var6 = var3;
         } else {
            var6 = var3 * var6 / 9;
         }

         if (var4 == 1) {
            var7 = var11 * var3 + var6;
            var6 = var3 - var6;
         } else {
            var7 = var11 * var3;
         }

         int var9 = var6 * 255 / var3;
         aeii.c.a(var0, var9 << 24 | var1);

         for(int var15 = 0; var15 <= 0; ++var15) {
            var0.fillRect(var7 + 0, 0, var6, var8);
         }
      }

   }

   private boolean a(byte var1, int var2, int var3) {
      if (this.s > this.a((int)-1, -1, (byte)this.b) - this.a((int)10, -1, (byte)this.b) && var1 <= this.n && aeii.a.a[var1] <= this.b[this.b] && aeii.a.a[var1] > 0) {
         this.a((byte[][])this.b, (int)0);
         return aeii.a.a(this.b, var2, var3, aeii.a.a[var1], -1, var1, this.b, true);
      } else {
         return false;
      }
   }

   private boolean a(a var1, int var2, int var3) {
      if (this.s > this.a((int)-1, -1, (byte)this.b) - this.a((int)10, -1, (byte)this.b) && (var1.d <= this.n || var1.d == 9) && var1.j <= this.b[this.b]) {
         this.a((byte[][])this.b, (int)0);
         return aeii.a.a(this.b, var2, var3, aeii.a.a[var1.d], -1, var1.d, this.b, true);
      } else {
         return false;
      }
   }

   private void o() {
      a[] var1 = this.a((int)-1, 0, (byte)this.b);
      this.f = new Vector(var1.length);

      for(int var2 = 0; var2 < var1.length; ++var2) {
         int var3;
         for(var3 = 0; var3 < var2; ++var3) {
            a var4 = (a)this.f.elementAt(var3);
            byte var5 = p[var4.d];
            byte var6;
            if ((var6 = p[var1[var2].d]) < var5 || var6 == var5 && var1[var2].h < var4.h) {
               this.f.insertElementAt(var1[var2], var3);
               break;
            }
         }

         if (var3 == var2) {
            this.f.addElement(var1[var2]);
         }
      }

      this.d = new a[this.e.length];
      this.q = new byte[this.e.length];
      aeii.a.c = aeii.a.a;
      this.aq = 0;
      this.k = 0;
   }

   private void p() {
      if (aeii.f.a.a(d)) {
         this.a(this.g, this.p, this.b, this);
         aeii.f.a.b();
      } else if (this.k == 4) {
         if (this.k == null && this.l == null) {
            if (this.b(this.b)) {
               int var15 = this.b(this.b.b, this.b.c);
               if (this.aw != -1 && this.aw != var15) {
                  this.d[this.aw] = this.d[var15];
                  this.d[var15] = this.b;
               }

               this.b(this.b.b, this.b.c, this.d[this.b.e]);
               aeii.f.a.a((f)this.a((String)null, aeii.f.a(73), this.b, 1000));
               aeii.c.b(9, 1);
               this.d = 9;
               this.d = this.a;
            } else if (this.a(this.b)) {
               int var16 = this.b(this.b.b, this.b.c);
               if (this.aw != -1 && this.aw != var16) {
                  this.d[this.aw] = this.d[var16];
                  this.d[var16] = this.b;
               }

               this.a((byte)37, this.b.b, (int)this.b.c);
               aeii.f.a.a((f)this.a((String)null, aeii.f.a(74), this.b, 1000));
               aeii.c.b(9, 1);
               this.d = 0;
               this.d = this.a;
            } else {
               this.d = 0;
            }

            this.b.d();
            this.b = null;
            this.k = 0;
         } else {
            this.k = 5;
            this.b.a((byte[][])this.b, this.b.b, this.b.c);
            this.c = true;
            this.b = true;
            this.j = this.a;
            if (this.k != null) {
               this.q.a(c[1]);
               this.d(this.k.b, this.k.c);
            } else if (this.l != null) {
               this.d(this.l.b, this.l.c);
            }
         }

         this.d = true;
      } else {
         if (this.k == 5) {
            if (this.a - this.j >= 500L) {
               if (this.k != null) {
                  this.a(this.b, this.k);
               } else if (this.l != null) {
                  this.a(this.l, this.b);
                  this.l = null;
                  this.k = 7;
                  this.b.d();
               }

               this.b = false;
               this.c = false;
               return;
            }
         } else if (this.k == 7) {
            if (this.e == null) {
               this.k = 0;
               this.d = 0;
               return;
            }
         } else if (this.k == 6) {
            if (this.a - this.j >= 1000L) {
               this.k = null;
               this.k = 0;
               this.d = 0;
               return;
            }
         } else {
            if (this.k == 2) {
               return;
            }

            if (this.k == 3) {
               if (this.aq == 0) {
                  if (this.a(this.b.m + 12, this.b.n + 12)) {
                     if (this.c == 0 && this.x == 7 && this.b == this.b[1]) {
                        this.j = null;
                        a[] var14;
                        if ((var14 = this.a((int)-1, -1, (byte)0)).length > 0) {
                           this.a(var14[aeii.c.a(var14.length)]);
                        }

                        this.k = 4;
                        return;
                     }

                     this.aq = 1;
                     this.j = this.a;
                     return;
                  }
               } else if (this.aq == 1) {
                  if (this.a - this.j >= 100L) {
                     this.b = true;
                     this.c = false;
                     this.aq = 2;
                     this.d = 1;
                     this.j = this.a;
                     return;
                  }
               } else if (this.aq == 2) {
                  if (this.a - this.j >= 200L) {
                     this.h = this.ao;
                     this.i = this.ap;
                     this.q.b(this.ao * 24, this.ap * 24);
                     this.b = this.b.a((int)this.b.b, (int)this.b.c, (int)this.h, (int)this.i);
                     this.aq = 3;
                     this.j = this.a;
                     return;
                  }
               } else if (this.aq == 3 && this.a - this.j >= 200L) {
                  this.b = null;
                  this.b.a(this.ao, this.ap, true);
                  this.k = 2;
                  this.aq = 0;
                  this.d = 2;
               }

               return;
            }

            if (this.f.isEmpty()) {
               a var13 = null;
               int var2 = 0;
               int var3 = 6666;
               byte var4 = 0;
               byte var5 = 0;

               for(int var6 = 0; var6 < this.e.length; ++var6) {
                  byte var8 = this.e[var6][0];
                  byte var7 = this.e[var6][1];
                  if (this.a(var8, var7) == 9 && this.b(var8, var7, this.b)) {
                     if (var2 == 0) {
                        var4 = var8;
                        var5 = var7;
                     }

                     ++var2;
                     int var9 = 0;

                     for(int var10 = this.a.size() - 1; var10 >= 0; --var10) {
                        a var11 = (a)this.a.elementAt(var10);
                        if (this.e[var11.e] != this.e[this.b] && var11.a((short)16)) {
                           int var12;
                           if ((var12 = Math.abs(var11.b - var8) + Math.abs(var11.c - var7)) < var3) {
                              var3 = var12;
                              var4 = var8;
                              var5 = var7;
                           }

                           ++var9;
                        }
                     }

                     if (var9 == 0) {
                        for(int var23 = 0; var23 < this.e.length; ++var23) {
                           byte var25 = this.e[var23][0];
                           byte var28 = this.e[var23][1];
                           if (this.a(var25, var28) == 9 && !this.b(var25, var28, this.b) && (var9 = Math.abs(var25 - var8) + Math.abs(var28 - var7)) < var3) {
                              var3 = var9;
                              var4 = var8;
                              var5 = var7;
                           }
                        }
                     }
                  }
               }

               if (var2 > 0) {
                  for(int var17 = 0; var17 < this.a[this.b]; ++var17) {
                     if (this.a[this.b][var17] != null && this.a[this.b][var17].f == 3 && this.a((a)this.a[this.b][var17], (int)var4, (int)var5)) {
                        var13 = this.a((a)this.a[this.b][var17], (int)var4, (int)var5);
                     }
                  }

                  if (var13 == null) {
                     if (this.a((int)0, -1, (byte)this.b) < 2 && this.a((byte)0, var4, (int)var5)) {
                        var13 = this.a((byte)0, var4, (int)var5);
                     } else if (this.a((int)1, -1, (byte)this.b) < 2 && this.a((byte)1, var4, (int)var5)) {
                        var13 = this.a((byte)1, var4, (int)var5);
                     } else {
                        int var18 = 0;
                        int var20 = 0;

                        for(byte var19 = 0; var19 < this.f; ++var19) {
                           if (this.e[var19] == this.e[this.b]) {
                              var18 += this.a((int)-1, -1, (byte)var19);
                           } else {
                              var20 += this.a((int)-1, -1, (byte)var19);
                           }
                        }

                        if (this.b[this.b] >= 1000 || this.a((int)-1, -1, (byte)this.b) < 8 || var18 < var20) {
                           int var22 = 0;
                           byte[] var24 = new byte[12];

                           for(byte var26 = 1; var26 < 12; ++var26) {
                              if ((this.a((int)var26, -1, (byte)this.b) <= 0 || aeii.a.a[var26] >= 600) && this.a((byte)var26, var4, (int)var5)) {
                                 var24[var22] = var26;
                                 ++var22;
                              }
                           }

                           if (var22 > 0) {
                              byte var27 = var24[Math.abs(aeii.c.a()) % var22];
                              var13 = this.a((byte)var27, var4, (int)var5);
                           }
                        }
                     }
                  }
               }

               if (var13 != null) {
                  this.e(var13);
                  return;
               }

               this.c = null;
               this.f = null;
               this.m();
               return;
            }

            if (this.c == 0 && this.x == 7 && this.b[1].f != 2) {
               this.b = this.b[1];
               this.d(this.b.b, this.b.c);
               this.j = this.b;
               this.k = 3;
               this.f.removeElement(this.b);
               return;
            }

            a var1 = this.m;
            if (this.m == null) {
               var1 = (a)this.f.elementAt(0);
            }

            this.e(var1);
            if (this.m == null) {
               this.f.removeElement(var1);
            }
         }

      }
   }

   private void e(a var1) {
      this.b = var1;
      this.d = true;
      this.a((byte[][])this.b, (int)0);
      this.b.b(this.b);
      this.b = false;
      this.c = this.a((int)0, -1, (byte)this.b);
      int var2 = 0;
      int var3 = this.b.length + this.c.length + this.e.length;
      this.a = new int[var3][5];
      this.ar = 0;
      var3 = 10000;
      this.at = -1;
      this.au = -1;
      this.av = -1;
      this.aw = -1;

      for(int var4 = 0; var4 < this.c.length + this.b.length; ++var4) {
         a var6 = null;
         if (var4 >= this.c.length) {
            if ((var6 = this.b[var4 - this.c.length]) != null) {
               if (var6.f == 3) {
                  var6 = null;
               } else if (this.e[var6.e] != this.e[this.b] && this.b[this.b] == null) {
                  int[] var41 = this.a[var2];
                  var41[2] += var6.a(var6.b, var6.c, (a)null) << 1;
               } else if (this.a >= 15 && this.e[var6.e] != this.e[this.b] && this.a((int)-1, -1, (byte)var6.e) < 4 && this.a((int)-1, -1, (byte)this.b) >= 8) {
                  int[] var10000 = this.a[var2];
                  var10000[2] += var6.a(var6.b, var6.c, (a)null) << 1;
               } else if (var6.e != this.b) {
                  var6 = null;
               }
            }
         } else if (this.b[this.b] != null) {
            var6 = this.c[var4];
         }

         if (var6 == null) {
            this.a[var2][2] = -6666;
         } else {
            this.a[var2][0] = var6.b;
            this.a[var2][1] = var6.c;
            if (var6.e == this.b) {
               a[] var7 = var6.a(var6.b, var6.c, 1, 5, (byte)0);

               for(int var5 = 0; var5 < var7.length; ++var5) {
                  if (var7[var5].f != 4) {
                     int[] var42 = this.a[var2];
                     var42[2] += var7[var5].a(var7[var5].b, var7[var5].c, var6);
                  }
               }
            }

            if (this.a[var2][2] > 0) {
               int[] var43 = this.a[var2];
               var43[4] += var6.a(var6.b, var6.c, (a)null);
               var43 = this.a[var2];
               var43[4] += var6.k;
               if (this.a[var2][2] > this.ar) {
                  this.ar = this.a[var2][2];
               }

               this.a[var2][3] = Math.abs(var6.b - var1.b) + Math.abs(var6.c - var1.c);
               if (this.a[var2][3] <= 0) {
                  this.a[var2][3] = 1;
               }

               if (this.a[var2][3] < var3) {
                  var3 = this.a[var2][3];
               }
            } else {
               this.a[var2][2] = -6666;
            }
         }

         ++var2;
      }

      int var17 = 666;
      int var19 = 666;
      int var20 = -1;
      int var18 = -1;

      for(int var8 = 0; var8 < this.e.length; ++var8) {
         byte var10 = this.e[var8][0];
         byte var11 = this.e[var8][1];
         byte var12 = this.a(var10, var11);
         boolean var13 = this.b(var10, var11, var1.e);
         this.a[var2][2] = -6666;
         if (var13 || this.d[var8] != null) {
            a[] var14 = var1.a(var10, var11, 1, 5, (byte)0);
            this.a[var2][0] = var10;
            this.a[var2][1] = var11;
            this.a[var2][2] = 0;

            for(int var9 = 0; var9 < var14.length; ++var9) {
               if (var14[var9].f != 4 && (this.d[var8] != null && !var13 || var12 == 8 && var14[var9].a((short)8) || var12 == 9 && var14[var9].a((short)16))) {
                  int[] var45 = this.a[var2];
                  var45[2] += var14[var9].a(var14[var9].b, var14[var9].c, (a)null);
               }
            }

            if (this.a[var2][2] == 0) {
               if (this.d[var8] != null && !var13) {
                  this.a[var2][2] = 100;
                  int[] var46 = this.a[var2];
                  var46[4] += 2000;
               } else {
                  this.a[var2][2] = -6666;
               }
            }

            if (this.a[var2][2] != -6666) {
               int[] var47 = this.a[var2];
               var47[4] += this.m[var8];
               if (this.a[var2][2] > this.ar) {
                  this.ar = this.a[var2][2];
               }

               this.a[var2][3] = Math.abs(var10 - var1.b) + Math.abs(var11 - var1.c);
               if (this.a[var2][3] <= 0) {
                  this.a[var2][3] = 1;
               }

               if (this.a[var2][3] < var3) {
                  var3 = this.a[var2][3];
               }
            }
         }

         if (this.a((int)var10, var11, (int)this.e[var1.e])) {
            a var23;
            int var24;
            if (((var23 = this.a((int)var10, var11, (byte)0)) == null || var23.e == var1.e) && (var24 = Math.abs(var10 - var1.b) + Math.abs(var11 - var1.c)) < var19) {
               var18 = var8;
               var19 = var24;
            }
         } else if ((this.d[var8] == null || this.d[var8] == var1) && (var12 == 8 && var1.a((short)8) || var12 == 9 && var1.a((short)16))) {
            int var37;
            if ((var37 = Math.abs(var10 - var1.b) + Math.abs(var11 - var1.c)) < var17) {
               var20 = var8;
               var17 = var37;
            }

            a var15 = this.a((int)var10, var11, (byte)0);
            if (var37 < var19 && this.b[var10][var11] > 0 && (var15 == null || var15.e == var1.e)) {
               var18 = var8;
               var19 = var37;
            }
         }

         ++var2;
      }

      if (var1.h < 50 && var18 != -1) {
         if (var18 == var20) {
            this.aw = var20;
         }

         this.d[var18] = var1;
         this.at = this.e[var18][0];
         this.au = this.e[var18][1];
         this.a((byte[][])this.g, (int)0);
         aeii.a.a(this.g, this.at, this.au, 10, -1, var1.d, this.b, false);
      } else if (this.b[this.b] == null || var20 == -1 || !var1.a((short)8) && !var1.a((short)16)) {
         int var21 = -1;
         int var29 = -6666;

         for(int var31 = 0; var31 < var2; ++var31) {
            if (this.a[var31][2] > -6666) {
               if (this.a[var31][2] > 0) {
                  this.a[var31][2] = this.a[var31][2] * var3 / this.a[var31][3];
               }

               int[] var48 = this.a[var31];
               var48[2] -= this.a[var31][4];
               if (this.a[var31][2] > var29) {
                  var29 = this.a[var31][2];
                  var21 = var31;
               }
            }
         }

         if (var21 != -1) {
            int var32 = var1.a(var1.b, var1.c, (a)null);
            if (var21 < this.c.length) {
               a var49 = this.c[var21];
               var49.k += var32;
            } else if (var21 < this.b.length + this.c.length) {
               a var50 = this.b[var21 - this.c.length];
               var50.k += var32;
            } else {
               this.av = var21 - this.b.length - this.c.length;
               int[] var51 = this.m;
               int var10001 = this.av;
               var51[var10001] += var32;
            }

            this.at = this.a[var21][0];
            this.au = this.a[var21][1];
            this.a((byte[][])this.g, (int)0);
            aeii.a.a(this.g, this.at, this.au, 10, -1, var1.d, this.b, false);
         }
      } else {
         this.aw = var20;
         this.d[var20] = var1;
         this.at = this.e[var20][0];
         this.au = this.e[var20][1];
         this.a((byte[][])this.g, (int)0);
         aeii.a.a(this.g, this.at, this.au, 10, -1, var1.d, this.b, false);
      }

      int var22 = -10000;
      int var30 = 0;

      for(int var33 = this.b.length; var30 < var33; ++var30) {
         int var34 = 0;

         for(int var36 = this.b[var30].length; var34 < var36; ++var34) {
            a var25;
            if (this.b[var30][var34] > 0 && ((var25 = this.a((int)var30, var34, (byte)0)) == null || var25 == var1 || this.m == null && var25.e == var1.e && var25.f == 0)) {
               if (!var1.a((short)512) || var25 == var1) {
                  a[] var38 = var1.a(var30, var34, (byte)0);

                  for(int var40 = 0; var40 < var38.length; ++var40) {
                     int var26;
                     if ((var26 = this.a(var1, var30, var34, var38[var40], (a)null)) > var22) {
                        this.l = null;
                        this.k = var38[var40];
                        var22 = var26;
                        this.ao = var30;
                        this.ap = var34;
                     }
                  }
               }

               if (var1.a((short)32)) {
                  this.a = var1.a(var30, var34, (byte)1);

                  for(int var39 = 0; var39 < this.a.length; ++var39) {
                     int var27;
                     if ((var27 = this.a(var1, var30, var34, (a)null, this.a[var39])) > var22) {
                        this.k = null;
                        this.l = this.a[var39];
                        var22 = var27;
                        this.ao = var30;
                        this.ap = var34;
                     }
                  }
               }

               int var28;
               if ((var28 = this.a(var1, var30, var34, (a)null, (a)null)) > var22) {
                  this.k = null;
                  this.l = null;
                  var22 = var28;
                  this.ao = var30;
                  this.ap = var34;
               }
            }
         }
      }

      if (this.aw != -1) {
         this.q[this.aw] = (byte)(10 - this.g[this.ao][this.ap]);
      }

      this.m = null;
      a var35;
      if ((var35 = this.a((int)this.ao, this.ap, (byte)0)) != null && var35 != var1) {
         this.m = var35;
         this.k = 0;
      } else {
         this.d(var1.b, var1.c);
         this.j = var1;
         this.k = 3;
      }
   }

   private int a(a var1, int var2, int var3, a var4, a var5) {
      int var6 = 0;
      if (this.aw != -1 && this.b[var1.e] != null) {
         if (this.at != -1) {
            if (this.g[var2][var3] > 0) {
               var6 = 100 + 100 * this.g[var2][var3] / 10;
            } else {
               int var7 = Math.abs(this.at - var1.b) + Math.abs(this.au - var1.c);
               int var8 = Math.abs(this.at - var2) + Math.abs(this.au - var3);
               var6 = 0 + 100 * (var7 - var8) / (var1.g - 1);
               if (b[this.a(var2, var3)] <= 1) {
                  var6 += 20;
               }
            }
         }

         if (var4 == null && !this.a((int)var2, var3, (int)this.e[var1.e])) {
            if (var1.a((short)16) && this.a(var2, var3) == 9) {
               var6 += 300;
            } else if (var1.a((short)8) && (this.a(var2, var3) == 8 || this.a[var2][var3] == 27)) {
               var6 += 200;
            }
         }
      }

      switch (var1.d) {
         case 2:
            if (this.a(var2, var3) == 5) {
               var6 += 25;
            }
            break;
         case 3:
            if (var5 != null) {
               var6 += 100;
            }
            break;
         case 4:
            a[] var9 = var1.a(var2, var3, 1, 2, (byte)2);
            if (var5 != null) {
               var6 += 25 * var9.length;
            }
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
      }

      if (var4 != null) {
         if (var4.f == 4) {
            int var12 = this.a(var4.b, var4.c);
            int var14 = this.b(var4.b, var4.c);
            if (var12 != 0 && var14 != -1 && this.d[var14] == null) {
               var6 += var1.a(var2, var3, var4) / 2;
            }
         } else {
            if (!var4.a(var2, var3)) {
               var6 += var1.a(var2, var3, var4) << 1;
            } else {
               var6 += var1.a(var2, var3, var4) * 3 / 2 - var4.a(var2, var3, var1);
            }

            if (var4.d == 9) {
               var6 += 25;
            } else if (var4.d == 11) {
               var6 += 100;
            }
         }
      }

      var6 += a[this.a(var2, var3)];
      if (var1.h < 100 && this.a((int)var2, var3, (int)this.e[var1.e])) {
         var6 += 100 - var1.h;
      }

      int var13 = this.b(var2, var3);
      if (this.g[var2][var3] > 0) {
         int var15 = this.g[var2][var3];
         int var17 = 10 - var1.g / 2;
         if (var15 > var17) {
            var15 = var17;
         }

         var6 += 50 + var15 * 100 / var17;
      } else if (this.at != -1) {
         int var16 = Math.abs(this.at - var1.b) + Math.abs(this.au - var1.c);
         int var18 = Math.abs(this.at - var2) + Math.abs(this.au - var3);
         var6 += 50 * (var16 - var18) / (var1.g - 1);
      }

      if (var13 != -1 && (var4 = this.d[var13]) != null && var4 != var1 && var4.f == 0 && this.q[var13] < var4.g) {
         var6 -= 200;
      }

      return var6 + 20 * (Math.abs(var2 - var1.b) + Math.abs(var3 - var1.c)) / (var1.g - 1);
   }

   private int b(int var1, int var2) {
      for(int var3 = 0; var3 < this.e.length; ++var3) {
         if (this.e[var3][0] == var1 && this.e[var3][1] == var2) {
            return var3;
         }
      }

      return -1;
   }

   private void d(int var1) {
      this.ax = var1;
      this.z = true;
   }

   private e a(String var1, byte var2, byte var3) {
      e var5 = new e((byte)7, 12);
      int var4 = aeii.c.b * 3;
      var5.a(var1, super.j, var4, var2);
      var5.a(0, super.k - var4, 0);
      aeii.f.a.a((f)var5);
      return var5;
   }

   private e a(String var1, String var2, int var3, int var4) {
      return this.a(var1, var2, var3, -1, var4);
   }

   private e a(String var1, String var2, int var3, int var4, int var5) {
      e var6;
      (var6 = new e((byte)10, 12)).a(var1, var2, super.j, var4);
      var6.a(super.l, var3 / 2, 3);
      var6.a = this;
      var6.e = var5;
      return var6;
   }

   private void q() {
      this.r = null;
      if (this.c == 0) {
         this.Q = a;
         this.n = true;
         this.m = false;
         this.G = this.a((String)null, this.b, super.k, 2000);
         this.G.a(super.l, super.m, 3);
         this.q = true;
         this.p = true;
         this.R = 0;
      }

      if (this.x == 0) {
         this.n = 0;
         this.b[0] = 0;
         this.b[1] = 0;
         aeii.a.c = 4;
         this.Y = 2;
         this.b(this.b[0].b, this.b[0].c);
         this.d(this.b[0].b, this.b[0].c);
         this.a((int)2, 3, (int)3);
         aeii.c.a(1, 1);
         this.q = false;
         this.d = false;
         this.ay = 0;
      } else if (this.x == 1) {
         this.n = 1;
         this.b[0] = 300;
         this.b[1] = 50;
         aeii.a.c = 4;
         this.Y = 2;
         this.b[0].b((int)2);
         this.b(this.b[0].b, this.b[0].c);
         this.d(this.b[0].b, this.b[0].c);
         this.a((int)7, 12, (byte)0).a(7, 10, false);
         this.a((int)8, 11, (byte)0).a(8, 9, false);
         this.a((int)9, 12, (byte)0).a(9, 10, false);
         this.e(7, 3);
         aeii.f.a.a((f)this.G);
      } else if (this.x == 2) {
         this.n = 0;
         this.b[0] = 0;
         this.b[1] = 0;
         aeii.a.c = 4;
         this.b[0] = 0;
         this.b(this.b[0].b, this.b[0].c);
         this.d(this.b[0].b, this.b[0].c);
         this.n = this.a((int)8, 17, (byte)0);
         this.o = this.a((int)8, 18, (byte)0);
         this.p = this.a((int)8, 19, (byte)0);
         this.n.a(8, 15, false);
         this.o.a(8, 15, false);
         this.p.a(8, 15, false);
         this.b[0].a(8, 14, false);
         this.d = false;
         aeii.f.a.a((f)this.G);
         this.ay = 0;
      } else if (this.x == 3) {
         this.n = 7;
         aeii.a.c = 4;
         this.b[0] = 400;
         this.b[1] = 400;
         this.n = aeii.a.a((byte)0, (byte)0, (int)-1, (int)5);
         this.o = aeii.a.a((byte)2, (byte)0, (int)-2, (int)5);
         this.p = aeii.a.a((byte)3, (byte)0, (int)-3, (int)5);
         this.n.a(3, 4, false);
         this.o.a(4, 4, false);
         this.p.a(2, 4, false);
         this.b[0].a(3, 3, false);
         this.b(this.b[0].b, this.b[0].c);
         this.d(3, 3);
         this.j = this.b[0];
         this.a((int)3, 3, (int)3);
         aeii.c.a(1, 1);
         this.q = false;
         this.d = false;
         this.ay = 0;
      } else if (this.x == 4) {
         this.n = 0;
         this.b[0] = 0;
         this.b[1] = 0;
         this.a(this.b[0].m + 12, this.b[0].n + 12);
         this.d(this.b[0].b, this.b[0].c);
         aeii.a.c = 4;
         a var6;
         (var6 = this.a((int)11, 2, (byte)0)).a((int)11, (int)-3);
         var6.a(11, 2, false);
         (var6 = this.a((int)10, 1, (byte)0)).a((int)10, (int)-5);
         var6.a(10, 1, false);
         (var6 = this.a((int)11, 1, (byte)0)).a((int)11, (int)-5);
         var6.a(11, 1, false);
         (var6 = this.a((int)12, 1, (byte)0)).a((int)12, (int)-5);
         var6.a(12, 1, false);
         (var6 = this.a((int)11, 0, (byte)0)).a((int)11, (int)-7);
         var6.a(11, 0, false);
         (var6 = this.a((int)12, 0, (byte)0)).a((int)12, (int)-7);
         var6.a(12, 0, false);
         aeii.f.a.a((f)this.G);
         this.d = false;
         this.ay = 0;
      } else if (this.x == 5) {
         this.n = 7;
         this.b[0] = 600;
         this.b[1] = 600;
         this.b[0].b((int)2);
         this.b(5, 0);
         this.d(5, 0);
         this.Y = 4;
         this.e(this.b[0].b, this.b[0].c);
         aeii.f.a.a((f)this.G);
         this.d = false;
         this.ay = 0;
      } else if (this.x == 6) {
         this.n = 8;
         aeii.a.c = 4;
         this.b[0] = 400;
         this.b[1] = 600;
         a var5 = aeii.a.a((byte)0, (byte)0, (int)13, (int)-1);
         a var12 = aeii.a.a((byte)1, (byte)0, (int)13, (int)-1);
         a var3 = aeii.a.a((byte)3, (byte)0, (int)13, (int)-1);
         a var4 = aeii.a.a((byte)11, (byte)0, (int)13, (int)-1);
         this.b[0].a = var5;
         var5.a = var12;
         var12.a = var3;
         var3.a = var4;
         this.b[0].b(this.b);
         this.b[0].a(14, 3, true);
         this.b(this.b[0].b, this.b[0].c);
         this.d(this.b[0].b, this.b[0].c);
         this.j = this.b[0];
         aeii.f.a.a((f)this.G);
         this.d = false;
         this.ay = 0;
      } else {
         if (this.x == 7) {
            this.n = 8;
            aeii.a.c = 4;
            this.b[0] = 800;
            this.b[1] = 200;
            a var1;
            (var1 = this.a((int)7, 4, (byte)0)).b((int)3);
            this.b[1] = var1;
            a var2 = this.a((int)8, 15, (byte)0);
            this.b[0] = var2;
            var2.b((int)0);
            this.a((int)6, 15, (byte)0).b((int)2);
            this.a(this.b[0].m + 12, this.b[0].n + 12);
            this.d(this.b[0].b, this.b[0].c);
            this.a((int)4, 3, (int)3);
            aeii.c.a(1, 1);
            this.q = false;
            this.d = false;
            this.ay = 0;
         }

      }
   }

   private void e(int var1) {
      InputStream var6;
      if ((var6 = aeii.c.a("m" + var1 + ".script")) == null) {
         this.a = null;
      } else {
         Vector var2 = new Vector();
         Vector var3 = null;

         String[] var4;
         while((var4 = aeii.c.a(var6)) != null) {
            int var5;
            if ((var5 = ((String)var4).indexOf(59)) >= 0) {
               var4 = ((String)var4).substring(0, var5);
            }

            if ((var4 = ((String)var4).trim()).length() != 0) {
               if ((var4 = aeii.c.a(var4, ' '))[0].charAt(0) != '@') {
                  var3.addElement(var4);
               } else {
                  var4[0] = var4[0].substring(1);
                  if (var4[0].equalsIgnoreCase("case")) {
                     int var10 = Integer.parseInt(var4[1]);

                     while(var2.size() <= var10) {
                        var2.addElement(new Vector());
                     }

                     var3 = (Vector)var2.elementAt(var10);
                  }
               }
            }
         }

         this.a = new String[var2.size()][][];

         for(int var11 = 0; var11 < var2.size(); ++var11) {
            var3 = (Vector)var2.elementAt(var11);
            this.a[var11] = new String[var3.size()][];
            var3.copyInto(this.a[var11]);
         }

      }
   }

   private void r() {
      String[][] var1 = this.a[this.ay];
      int var3 = 0;
      int var4 = -1;
      a var5 = null;

      while(var3 >= 0 && var3 < var1.length) {
         String[] var2;
         if ((var2 = var1[var3++])[0].equalsIgnoreCase("Jump")) {
            var3 += Integer.parseInt(var2[1]);
         } else if (var2[0].equalsIgnoreCase("Alt")) {
            if ((var4 = Integer.parseInt(var2[1])) == 0) {
               var4 = -1;
            } else {
               var4 += var3;
            }
         } else if (var2[0].equalsIgnoreCase("Test")) {
            int var6 = 0;
            int var7 = 0;
            String var8 = null;
            if (var2[1].equalsIgnoreCase("CurrentPlayer")) {
               var6 = this.b;
            } else if (var2[1].equalsIgnoreCase("CurrentTurn")) {
               var6 = this.a;
            } else if (var2[1].equalsIgnoreCase("GameState")) {
               var6 = this.d;
            } else if (var2[1].equalsIgnoreCase("AlphaWindow")) {
               var6 = this.aa;
            } else if (var2[1].equalsIgnoreCase("StatusBarOffset")) {
               var6 = this.Q;
            } else if (var2[1].equalsIgnoreCase("UnitFinishedMove")) {
               var6 = this.a != null ? 1 : 0;
            } else if (var2[1].equalsIgnoreCase("CountUnits")) {
               var6 = this.a(Integer.parseInt(var2[2]), Integer.parseInt(var2[3]), (byte)Integer.parseInt(var2[4]));
               var7 = Integer.parseInt(var2[6]);
               var8 = var2[5];
            }

            if (var8 == null) {
               var7 = Integer.parseInt(var2[3]);
               var8 = var2[2];
            }

            if (!(var8.equals("=") ? var6 == var7 : (var8.equals("!=") ? var6 != var7 : (var8.equals(">") ? var6 > var7 : (var8.equals(">=") ? var6 >= var7 : (var8.equals("<") ? var6 < var7 : (var8.equals("<=") ? var6 <= var7 : false))))))) {
               var3 = var4;
            }

            var4 = -1;
         } else if (var2[0].equalsIgnoreCase("Wait")) {
            this.d(Integer.parseInt(var2[1]));
         } else if (var2[0].equalsIgnoreCase("NextState")) {
            ++this.ay;
         } else if (var2[0].equalsIgnoreCase("ShowMapName")) {
            aeii.f.a.a((f)this.G);
         } else if (var2[0].equalsIgnoreCase("StartPlay")) {
            this.t();
         } else if (var2[0].equalsIgnoreCase("CompleteMission")) {
            this.u();
         } else if (var2[0].equalsIgnoreCase("SetFadeEnabled")) {
            this.q = "true".equalsIgnoreCase(var2[1]);
         } else if (var2[0].equalsIgnoreCase("SetCursorVisible")) {
            this.d = "true".equalsIgnoreCase(var2[1]);
         } else if (var2[0].equalsIgnoreCase("SetGameActive")) {
            this.m = "true".equalsIgnoreCase(var2[1]);
         } else if (var2[0].equalsIgnoreCase("SetFadeValue")) {
            this.R = Integer.parseInt(var2[1]);
         } else if (var2[0].equalsIgnoreCase("SetMapStepMax")) {
            this.Y = Integer.parseInt(var2[1]);
         } else if (var2[0].equalsIgnoreCase("SetUnitSpeed")) {
            if (var2[1].equalsIgnoreCase("slow")) {
               aeii.a.c = aeii.a.b;
            } else if (var2[1].equalsIgnoreCase("fast")) {
               aeii.a.c = aeii.a.a;
            } else {
               aeii.a.c = (byte)Integer.parseInt(var2[1]);
            }
         } else if (var2[0].equalsIgnoreCase("ShowDialog")) {
            this.a(aeii.f.a(Integer.parseInt(var2[1])), (byte)Integer.parseInt(var2[2]), (byte)Integer.parseInt(var2[3]));
         } else if (var2[0].equalsIgnoreCase("ShowHelp")) {
            this.L = Integer.parseInt(var2[1]);
         } else if (var2[0].equalsIgnoreCase("Vibrate")) {
            aeii.c.d(Integer.parseInt(var2[1]));
         } else if (var2[0].equalsIgnoreCase("MoveMapAndCursor")) {
            if (var2[1].equalsIgnoreCase("king")) {
               a var10 = this.b[Integer.parseInt(var2[2])];
               this.e(var10.b, var10.c);
            } else {
               this.e(Integer.parseInt(var2[1]), Integer.parseInt(var2[2]));
            }
         } else if (var2[0].equalsIgnoreCase("GetUnitPlotRoute")) {
            this.a(Integer.parseInt(var2[1]), Integer.parseInt(var2[2]), (byte)Integer.parseInt(var2[3])).a(Integer.parseInt(var2[4]), Integer.parseInt(var2[5]), "true".equalsIgnoreCase(var2[6]));
         } else if (var2[0].equalsIgnoreCase("CreateUnitPlotRoute")) {
            aeii.a.a((byte)Integer.parseInt(var2[1]), (byte)Integer.parseInt(var2[2]), Integer.parseInt(var2[3]), Integer.parseInt(var2[4])).a(Integer.parseInt(var2[5]), Integer.parseInt(var2[6]), "true".equalsIgnoreCase(var2[7]), "true".equalsIgnoreCase(var2[8]));
         } else if (var2[0].equalsIgnoreCase("GetUnit")) {
            var5 = this.a(Integer.parseInt(var2[1]), Integer.parseInt(var2[2]), (byte)Integer.parseInt(var2[3]));
         } else if (var2[0].equalsIgnoreCase("RemoveUnit")) {
            var5.a();
         } else if (var2[0].equalsIgnoreCase("ScheduleUnitAnimationStop")) {
            var5.a(Integer.parseInt(var2[1]));
         } else if (var2[0].equalsIgnoreCase("CreateSpriteAtUnit")) {
            a.a.a.g var11 = null;
            if (var2[1].equalsIgnoreCase("Smoke")) {
               var11 = this.e;
            } else if (var2[1].equalsIgnoreCase("Spark")) {
               var11 = this.f;
            } else if (var2[1].equalsIgnoreCase("RedSpark")) {
               var11 = this.g;
            }

            this.a(var11, var5.m, var5.n, Integer.parseInt(var2[2]), Integer.parseInt(var2[3]), Integer.parseInt(var2[4]), Integer.parseInt(var2[5]));
         }
      }

   }

   private void s() {
      if (this.n == 0) {
         if (this.ax <= 0 || --this.ax <= 0) {
            if (this.A) {
               if (this.ay == 0) {
                  this.g = true;
                  this.G = 0;
                  this.d = 11;
                  this.ay = 1;
                  return;
               }
            } else if (this.c == 1) {
               if (this.ay == 100) {
                  aeii.c.a(g[this.d[this.b]], 0);
                  this.e.a((byte)0, true);
                  this.e.a((f)null);
                  aeii.f.a.a((f)this.e);
                  ++this.ay;
                  return;
               }

               if (this.ay == 101) {
                  byte var2 = -1;
                  int var1 = -1;
                  boolean var3 = true;

                  for(int var4 = 0; var4 < this.f; ++var4) {
                     if (this.m[var4] != 2 && (this.b[var4] != null && this.b[var4].f != 3 || this.e(var4) != 0)) {
                        var1 = var4;
                        if (var2 != -1 && var2 != this.e[var4]) {
                           var3 = false;
                           break;
                        }

                        var2 = this.e[var4];
                     }
                  }

                  if (var3) {
                     this.m = false;
                     this.d = false;
                     String var36 = aeii.f.a(38, (String)("" + (this.e[var1] + 1)));
                     String var13 = aeii.f.a(81, (String)var36) + "\n(";

                     for(int var28 = 0; var28 < this.f; ++var28) {
                        if (this.m[var28] != 2 && this.e[var28] == this.e[var1]) {
                           var13 = var13 + " " + aeii.f.a(88 + this.d[var28]) + " ";
                        }
                     }

                     var13 = var13 + ")";
                     e var29;
                     (var29 = this.a((String)null, var13, this.b, this.p, -1)).a((f)this);
                     aeii.f.a.a((f)var29);
                     if (this.m[var1] == 1) {
                        aeii.c.a(6, 1);
                     } else {
                        aeii.c.a(7, 1);
                     }

                     this.d(15);
                     ++this.ay;
                     return;
                  }

                  return;
               }

               if (this.ay == 102) {
                  this.g = true;
                  this.G = 0;
                  this.d = 11;
                  ++this.ay;
                  return;
               }
            } else if (this.h == 1 && this.c == 0 && this.ay != -1) {
               if (this.aA != -1) {
                  if (!this.b(this.aA, this.aB)) {
                     return;
                  }

                  this.aA = -1;
                  this.aB = -1;
               }

               if (this.d != 11) {
                  boolean var15 = true;

                  for(int var5 = 0; var5 < this.a[0]; ++var5) {
                     if (this.a[0][var5].f != 3) {
                        var15 = false;
                        break;
                     }
                  }

                  if (var15 && this.e(0) == 0) {
                     this.v();
                     return;
                  }
               }

               if (this.a != null) {
                  this.r();
               } else if (this.x == 0) {
                  switch (this.ay) {
                     case 0:
                        ++this.ay;
                        break;
                     case 1:
                        aeii.f.a.a((f)this.G);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 2:
                        this.q = true;
                        this.a((int)0, 8, (byte)0).a(3, 8, false);
                        this.a((int)1, 9, (byte)0).a(4, 9, false);
                        this.a((int)0, 10, (byte)0).a(3, 10, false);
                        this.e(5, 9);
                        break;
                     case 3:
                        this.e(9, 3);
                        this.d = true;
                        break;
                     case 4:
                        this.d(10);
                        this.Y = 12;
                        aeii.a.c = aeii.a.b;
                        ++this.ay;
                        break;
                     case 5:
                        this.a((String)aeii.f.a(221), (byte)2, (byte)4);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 6:
                        a var35 = this.a((int)9, 3, (byte)0);
                        aeii.c.d(100);
                        var35.a((int)400);
                        this.a(this.g, var35.m, var35.n, 0, 0, 2, 50);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 7:
                        this.a((String)aeii.f.a(222), (byte)2, (byte)4);
                        ++this.ay;
                        break;
                     case 8:
                        a var27 = this.a((int)9, 3, (byte)0);
                        this.a(this.f, var27.m, var27.n, 0, 0, 1, 50);
                        this.a(this.e, var27.m, var27.n, 0, -3, 1, 100);
                        var27.a();
                        this.d(20);
                        ++this.ay;
                        break;
                     case 9:
                        this.d = false;
                        this.e(this.b[0].b, this.b[0].c);
                        break;
                     case 10:
                        this.d(10);
                        ++this.ay;
                        break;
                     case 11:
                        this.a((String)aeii.f.a(223), (byte)0, (byte)4);
                        this.d(5);
                        ++this.ay;
                        break;
                     case 12:
                        this.a((String)aeii.f.a(224), (byte)5, (byte)4);
                        ++this.ay;
                        break;
                     case 13:
                        this.t();
                        ++this.ay;
                        break;
                     case 14:
                        if (this.Q == 0) {
                           this.L = 0;
                           ++this.ay;
                        }
                        break;
                     case 15:
                        if (this.b == 0 && this.d == 1 && this.aa == 0) {
                           this.L = 1;
                           ++this.ay;
                        }
                        break;
                     case 16:
                        if (this.b == 0 && this.a != null) {
                           this.L = 2;
                           ++this.ay;
                        }
                        break;
                     case 17:
                        if (this.b == 0 && this.a != null) {
                           this.L = 3;
                           ++this.ay;
                        }
                        break;
                     case 18:
                        if (this.a((int)-1, 2, (byte)0) >= 3) {
                           this.L = 4;
                           ++this.ay;
                        } else if (this.a > 0) {
                           ++this.ay;
                        }
                        break;
                     case 19:
                        if (this.a >= 2) {
                           this.L = 5;
                           ++this.ay;
                        }
                        break;
                     case 20:
                        this.L = 6;
                        ++this.ay;
                        break;
                     case 21:
                        if (this.d == 1 && this.b == 0) {
                           this.L = 7;
                           ++this.ay;
                        }
                        break;
                     case 22:
                        if (this.a((int)-1, -1, (byte)1) == 0) {
                           this.m = false;
                           this.d(20);
                           ++this.ay;
                        }
                        break;
                     case 23:
                        this.e(1, 1);
                        break;
                     case 24:
                        aeii.a.a((byte)1, (byte)1, (int)1, (int)1).a(1, 2, false, true);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 25:
                        this.e(10, 10);
                        break;
                     case 26:
                        aeii.a.a((byte)0, (byte)1, (int)10, (int)10).a(10, 9, false, true);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 27:
                        this.e(this.b[0].b, this.b[0].c);
                        break;
                     case 28:
                        this.a((String)aeii.f.a(225), (byte)5, (byte)4);
                        this.d(5);
                        ++this.ay;
                        break;
                     case 29:
                        this.a((String)aeii.f.a(226), (byte)0, (byte)4);
                        this.m = true;
                        ++this.ay;
                        break;
                     case 30:
                        if (this.a((int)-1, -1, (byte)1) == 0 && this.d == 0) {
                           this.m = false;
                           this.d = false;
                           this.d(30);
                           ++this.ay;
                        }
                        break;
                     case 31:
                        this.o = true;
                        this.R = 0;
                        this.d(20);
                        ++this.ay;
                        break;
                     case 32:
                        this.a((String)aeii.f.a(227), (byte)2, (byte)4);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 33:
                        this.a((String)aeii.f.a(228), (byte)0, (byte)4);
                        this.d(5);
                        ++this.ay;
                        break;
                     case 34:
                        this.a((String)aeii.f.a(229), (byte)2, (byte)4);
                        this.d(5);
                        ++this.ay;
                        break;
                     case 35:
                        this.a((String)aeii.f.a(230), (byte)0, (byte)4);
                        this.d(15);
                        ++this.ay;
                        break;
                     case 36:
                        this.u();
                  }
               } else if (this.x == 1) {
                  switch (this.ay) {
                     case 1:
                        this.d(10);
                        ++this.ay;
                        break;
                     case 2:
                        this.Y = 4;
                        this.e(12, 3);
                        break;
                     case 3:
                        this.a((String)aeii.f.a(231), (byte)1, (byte)4);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 4:
                        this.a((String)aeii.f.a(232), (byte)3, (byte)4);
                        this.e(this.b[0].b, this.b[0].c);
                        break;
                     case 5:
                        this.a((String)aeii.f.a(233), (byte)5, (byte)4);
                        this.e(7, 3);
                        break;
                     case 6:
                        aeii.a.c = 2;
                        this.n = aeii.a.a((byte)0, (byte)1, (int)7, (int)3);
                        this.o = aeii.a.a((byte)11, (byte)1, (int)7, (int)3);
                        this.p = aeii.a.a((byte)0, (byte)1, (int)7, (int)3);
                        this.n.a = this.o;
                        this.o.a = this.p;
                        this.n.a(6, -2, false);
                        this.d(30);
                        ++this.ay;
                        break;
                     case 7:
                        if (this.p.b == 6 && this.p.c == 1) {
                           aeii.a.c = 4;
                           this.q = aeii.a.a((byte)0, (byte)0, (int)7, (int)3);
                           this.q.a(6, 2, false);
                           this.o.a = null;
                           this.a((String)aeii.f.a(234), (byte)2, (byte)4);
                           ++this.ay;
                        }
                        break;
                     case 8:
                        if (this.q.f != 1) {
                           aeii.c.d(100);
                           this.q.a((int)400);
                           this.a(this.g, this.q.m, this.q.n, 0, 0, 2, 50);
                           this.d(10);
                           ++this.ay;
                        }
                        break;
                     case 9:
                        this.a((String)aeii.f.a(235), (byte)2, (byte)4);
                        this.d(5);
                        ++this.ay;
                        break;
                     case 10:
                        this.a(this.e, this.q.m, this.q.n, 0, -3, 1, 100);
                        this.a(this.f, this.q.m, this.q.n, 0, 0, 1, 50);
                        this.q.a();
                        this.q = null;
                        this.d(15);
                        ++this.ay;
                        break;
                     case 11:
                        aeii.a.c = aeii.a.b;
                        this.n.a();
                        this.o.a();
                        this.n = null;
                        this.o = null;
                        this.p = null;
                        this.Y = 12;
                        this.e(this.b[0].b, this.b[0].c);
                        break;
                     case 12:
                        this.a((String)aeii.f.a(236), (byte)5, (byte)4);
                        ++this.ay;
                        break;
                     case 13:
                        this.a((String)aeii.f.a(237), (byte)1, (byte)4);
                        ++this.ay;
                        break;
                     case 14:
                        this.e(3, 5);
                        break;
                     case 15:
                        this.a((String)aeii.f.a(238), (byte)5, (byte)4);
                        ++this.ay;
                        break;
                     case 16:
                        this.e(this.b[0].b, this.b[0].c);
                        break;
                     case 17:
                        this.L = 8;
                        ++this.ay;
                        break;
                     case 18:
                        this.L = 9;
                        ++this.ay;
                        break;
                     case 19:
                        this.t();
                        ++this.ay;
                        break;
                     case 20:
                        if (this.d == 9 && this.b == 0) {
                           this.L = 10;
                           ++this.ay;
                        }
                        break;
                     case 21:
                        this.L = 11;
                        ++this.ay;
                        break;
                     case 22:
                        if (this.b[0].f == 3 || this.b[1].f == 3) {
                           this.L = 12;
                           ++this.ay;
                        }
                        break;
                     case 23:
                        if (this.a((int)-1, -1, (byte)1) == 0 && this.e(1) == 0) {
                           this.m = false;
                           this.d(20);
                           ++this.ay;
                        }
                        break;
                     case 24:
                        this.e(this.b[0].b, this.b[0].c);
                        break;
                     case 25:
                        this.a((String)aeii.f.a(239), (byte)1, (byte)4);
                        ++this.ay;
                        break;
                     case 26:
                        this.a((String)aeii.f.a(240), (byte)5, (byte)4);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 27:
                        this.u();
                  }
               } else if (this.x == 2) {
                  label527:
                  switch (this.ay) {
                     case 0:
                        if (this.n.f != 1) {
                           this.n.a(7, 14, false);
                           ++this.ay;
                        }
                        break;
                     case 1:
                        if (this.o.f != 1) {
                           this.o.a(7, 15, false);
                           this.d(20);
                           ++this.ay;
                        }
                        break;
                     case 2:
                        aeii.a.c = aeii.a.b;
                        this.n = null;
                        this.o = null;
                        this.p = null;
                        this.a((String)aeii.f.a(241), (byte)5, (byte)4);
                        this.d(5);
                        ++this.ay;
                        break;
                     case 3:
                        this.a((String)aeii.f.a(242), (byte)0, (byte)4);
                        this.d(5);
                        ++this.ay;
                        break;
                     case 4:
                        this.a((String)aeii.f.a(243), (byte)5, (byte)4);
                        this.d(5);
                        ++this.ay;
                        break;
                     case 5:
                        this.L = 14;
                        ++this.ay;
                        break;
                     case 6:
                        this.t();
                        ++this.ay;
                        break;
                     case 7:
                     case 8:
                        if (this.ay == 7 && this.b == 0 && this.Q == 0 && this.a((int)-1, 3, (byte)-1) > 0) {
                           this.L = 15;
                           ++this.ay;
                        }

                        a[] var26 = this.a((int)-1, 2, (byte)0);

                        for(int var12 = 0; var12 < var26.length; ++var12) {
                           if (var26[var12].b <= 4 || var26[var12].c <= 10) {
                              this.d(10);
                              this.m = false;
                              this.d = false;
                              this.ay = 9;
                              break label527;
                           }
                        }
                        break;
                     case 9:
                        this.e(0, 8);
                        aeii.a.a((byte)5, (byte)1, (int)-1, (int)8).a(0, 8, false);
                        aeii.a.a((byte)5, (byte)1, (int)-2, (int)7).a(1, 7, false);
                        this.d(20);
                        break;
                     case 10:
                        this.e(8, 6);
                        aeii.a.a((byte)5, (byte)1, (int)12, (int)6).a(8, 6, false);
                        this.d(20);
                        break;
                     case 11:
                        this.e(2, 1);
                        aeii.a.a((byte)5, (byte)1, (int)1, (int)-2).a(1, 2, false);
                        aeii.a.a((byte)5, (byte)1, (int)3, (int)-2).a(3, 2, false);
                        aeii.a.a((byte)4, (byte)1, (int)2, (int)-1).a(2, 1, false);
                        this.d(20);
                        break;
                     case 12:
                        this.a((String)aeii.f.a(244), (byte)5, (byte)4);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 13:
                        this.e(4, 8);
                        this.d(15);
                        break;
                     case 14:
                        a var25 = aeii.a.a((byte)2, (byte)1, (int)3, (int)8);
                        a var34 = aeii.a.a((byte)2, (byte)1, (int)4, (int)7);
                        a var39 = aeii.a.a((byte)2, (byte)1, (int)5, (int)8);
                        this.a(this.f, var25.m, var25.n, 0, 0, 1, 50);
                        this.a(this.f, var34.m, var34.n, 0, 0, 1, 50);
                        this.a(this.f, var39.m, var39.n, 0, 0, 1, 50);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 15:
                        this.a((String)aeii.f.a(245), (byte)5, (byte)4);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 16:
                        this.a((String)aeii.f.a(246), (byte)-1, (byte)4);
                        this.d(5);
                        ++this.ay;
                        break;
                     case 17:
                        this.a((String)aeii.f.a(247), (byte)0, (byte)4);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 18:
                        aeii.f.a.a((f)this.a((String)null, aeii.f.a(248), this.b, 1500));
                        ++this.ay;
                        break;
                     case 19:
                        this.a((int)3, 8, (byte)0).a();
                        this.a((int)4, 7, (byte)0).a();
                        this.a((int)5, 8, (byte)0).a();
                        a var24 = aeii.a.a((byte)2, (byte)0, (int)3, (int)8);
                        a var33 = aeii.a.a((byte)2, (byte)0, (int)4, (int)7);
                        a var38 = aeii.a.a((byte)2, (byte)0, (int)5, (int)8);
                        this.a(this.f, var24.m, var24.n, 0, 0, 1, 50);
                        this.a(this.f, var33.m, var33.n, 0, 0, 1, 50);
                        this.a(this.f, var38.m, var38.n, 0, 0, 1, 50);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 20:
                        this.m = true;
                        this.d = true;
                        this.L = 13;
                        ++this.ay;
                        break;
                     case 21:
                        if (this.a((int)-1, -1, (byte)1) == 0) {
                           this.m = false;
                           this.d(10);
                           ++this.ay;
                        }
                        break;
                     case 22:
                        this.u();
                  }
               } else if (this.x == 3) {
                  switch (this.ay) {
                     case 0:
                        aeii.f.a.a((f)this.G);
                        this.q = true;
                        ++this.ay;
                        break;
                     case 1:
                        if (this.b[0].f != 1) {
                           this.j = null;
                           this.d(20);
                           ++this.ay;
                        }
                        break;
                     case 2:
                        this.o = true;
                        this.q = true;
                        this.R = 0;
                        this.d(20);
                        ++this.ay;
                        break;
                     case 3:
                        this.a((String)aeii.f.a(249), (byte)2, (byte)4);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 4:
                        this.a((String)aeii.f.a(250), (byte)0, (byte)4);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 5:
                        this.a((String)aeii.f.a(251), (byte)5, (byte)4);
                        this.d(13, 3);
                        this.a(312, 72);
                        this.b[0].a((int)7, (int)1);
                        this.n.a((int)5, (int)4);
                        this.o.a((int)7, (int)5);
                        this.p.a((int)3, (int)3);
                        this.n = null;
                        this.o = null;
                        this.p = null;
                        this.Y = 2;
                        this.d(5);
                        ++this.ay;
                        break;
                     case 6:
                        this.o = false;
                        this.p = true;
                        this.R = 0;
                        this.d(5);
                        ++this.ay;
                        break;
                     case 7:
                        this.e(13, 10);
                        break;
                     case 8:
                        aeii.a.c = 2;
                        this.Y = 4;
                        this.n = this.a((int)10, 10, (byte)0);
                        this.n.a(6, 10, false);
                        this.d(6, 10);
                        this.e(6, 10);
                        break;
                     case 9:
                        if (this.n.f != 1) {
                           this.n = null;
                           this.d = true;
                           this.d(10);
                           ++this.ay;
                        }
                        break;
                     case 10:
                        this.d(4, 9);
                        this.e(4, 9);
                        this.a(this.g, 96, 216, 0, 0, 1, 50);
                        this.d(15);
                        break;
                     case 11:
                        this.d = aeii.a.a((byte)0, (byte)0, 4, 9, false);
                        this.d.d = -1;
                        this.d.f = 4;
                        this.E = 6;
                        this.d(20);
                        ++this.ay;
                        break;
                     case 12:
                        this.a((String)aeii.f.a(252), (byte)5, (byte)4);
                        this.e(7, 1);
                        break;
                     case 13:
                        this.a((String)aeii.f.a(253), (byte)0, (byte)4);
                        this.d(5);
                        ++this.ay;
                        break;
                     case 14:
                        this.L = 17;
                        ++this.ay;
                        break;
                     case 15:
                        this.t();
                        ++this.ay;
                        break;
                     case 16:
                        if (this.a((int)-1, -1, (byte)1) == 0 && this.e(1) == 0) {
                           this.d(15);
                           this.m = false;
                           ++this.ay;
                        }
                        break;
                     case 17:
                        this.a((String)aeii.f.a(254), (byte)5, (byte)4);
                        this.d(5);
                        ++this.ay;
                        break;
                     case 18:
                        this.a((String)aeii.f.a(255), (byte)0, (byte)4);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 19:
                        this.u();
                  }
               } else if (this.x == 4) {
                  if (this.r == null) {
                     this.r = this.a((int)11, -1, (byte)0)[0];
                  }

                  if (this.ay == 25 && this.r.b >= 15 && this.r.c >= 11 && this.r.f == 2) {
                     this.d(10);
                     this.m = false;
                     this.d = false;
                     this.ay = 26;
                     return;
                  }

                  if (this.r.f == 3) {
                     this.r = null;
                     this.v();
                     return;
                  }

                  label560:
                  switch (this.ay) {
                     case 0:
                        this.d(50);
                        ++this.ay;
                        break;
                     case 1:
                        this.a((String)aeii.f.a(256), (byte)5, (byte)4);
                        this.d(5);
                        ++this.ay;
                        break;
                     case 2:
                        this.a((String)aeii.f.a(257), (byte)0, (byte)4);
                        this.d(5);
                        ++this.ay;
                        break;
                     case 3:
                        this.t();
                        ++this.ay;
                        break;
                     case 4:
                        a[] var23 = this.a((int)-1, -1, (byte)0);

                        for(int var11 = 0; var11 < var23.length; ++var11) {
                           if (var23[var11].f == 2 && var23[var11].b <= 8) {
                              this.m = false;
                              this.d(5);
                              ++this.ay;
                              break label560;
                           }
                        }
                        break;
                     case 5:
                        this.d = false;
                        this.e(4, 4);
                        break;
                     case 6:
                        aeii.a.a((byte)10, (byte)1, (int)4, (int)4).a(4, 1, false, true);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 7:
                        aeii.a.a((byte)1, (byte)1, (int)4, (int)4).a(5, 2, false, true);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 8:
                        aeii.a.a((byte)10, (byte)1, (int)4, (int)4).a(4, 3, false, true);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 9:
                        this.a((String)aeii.f.a(258), (byte)5, (byte)4);
                        this.e(this.b[0].b, this.b[0].c);
                        break;
                     case 10:
                        this.m = true;
                        this.d = true;
                        ++this.ay;
                        break;
                     case 11:
                        a[] var22 = this.a((int)-1, -1, (byte)0);

                        for(int var10 = 0; var10 < var22.length; ++var10) {
                           if (var22[var10].f == 2 && var22[var10].c >= 7) {
                              this.m = false;
                              this.d = false;
                              this.e(6, 10);
                              break label560;
                           }
                        }
                        break;
                     case 12:
                        aeii.a.a((byte)1, (byte)1, (int)6, (int)10).a(5, 10, false, true);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 13:
                        aeii.a.a((byte)5, (byte)1, (int)6, (int)10).a(7, 8, false, true);
                        this.d(15);
                        ++this.ay;
                        break;
                     case 14:
                        aeii.a.a((byte)5, (byte)1, (int)6, (int)10).a(7, 9, false, true);
                        this.m = true;
                        this.d = true;
                        ++this.ay;
                        break;
                     case 15:
                        a[] var21 = this.a((int)-1, -1, (byte)0);

                        for(int var9 = 0; var9 < var21.length; ++var9) {
                           if (var21[var9].f == 2 && var21[var9].b >= 8 && var21[var9].c >= 6) {
                              this.m = false;
                              this.d = false;
                              this.e(12, 5);
                              break label560;
                           }
                        }
                        break;
                     case 16:
                        aeii.a.a((byte)5, (byte)1, (int)12, (int)5).a(12, 7, false, true);
                        this.d(15);
                        ++this.ay;
                        break;
                     case 17:
                        aeii.a.a((byte)6, (byte)1, (int)12, (int)5).a(12, 6, false, true);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 18:
                        aeii.a.a((byte)5, (byte)1, (int)12, (int)5).a(12, 5, false, true);
                        this.m = true;
                        this.d = true;
                        ++this.ay;
                        break;
                     case 19:
                        a[] var20 = this.a((int)-1, -1, (byte)0);

                        for(int var8 = 0; var8 < var20.length; ++var8) {
                           if (var20[var8].f == 2 && var20[var8].b >= 15 && var20[var8].c >= 8) {
                              this.m = false;
                              this.d = false;
                              this.e(18, 8);
                              break label560;
                           }
                        }
                        break;
                     case 20:
                        aeii.a.a((byte)5, (byte)1, (int)18, (int)8).a(16, 10, false, true);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 21:
                        aeii.a.a((byte)6, (byte)1, (int)18, (int)8).a(17, 10, false, true);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 22:
                        aeii.a.a((byte)5, (byte)1, (int)18, (int)8).a(18, 10, false, true);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 23:
                        aeii.a.a((byte)1, (byte)1, (int)18, (int)8).a(18, 9, false, true);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 24:
                        this.a((String)aeii.f.a(259), (byte)0, (byte)4);
                        this.m = true;
                        this.d = true;
                        ++this.ay;
                        break;
                     case 25:
                        if (this.a((int)-1, -1, (byte)1) == 0) {
                           this.m = false;
                           this.d = false;
                           this.d(10);
                           ++this.ay;
                        }
                        break;
                     case 26:
                        this.u();
                        ++this.ay;
                  }
               } else if (this.x == 5) {
                  switch (this.ay) {
                     case 0:
                        this.d(10);
                        ++this.ay;
                        break;
                     case 1:
                        this.a((String)aeii.f.a(260), (byte)1, (byte)4);
                        ++this.ay;
                        break;
                     case 2:
                        this.t();
                        ++this.ay;
                        break;
                     case 3:
                        if (this.a((int)-1, -1, (byte)1) == 0 && this.e(1) == 0) {
                           this.m = false;
                           this.d = false;
                           this.d(15);
                           ++this.ay;
                        }
                        break;
                     case 4:
                        this.e(this.b[0].b, this.b[0].c);
                        break;
                     case 5:
                        this.a((String)aeii.f.a(261), (byte)0, (byte)4);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 6:
                        this.u();
                  }
               } else if (this.x != 6) {
                  if (this.x == 7) {
                     switch (this.ay) {
                        case 0:
                           aeii.f.a.a((f)this.G);
                           this.q = true;
                           ++this.ay;
                           break;
                        case 1:
                           if (!this.p) {
                              this.a((String)aeii.f.a(271), (byte)0, (byte)4);
                              ++this.ay;
                           }
                           break;
                        case 2:
                           this.e(this.b[1].b, this.b[1].c);
                           break;
                        case 3:
                           this.a((String)aeii.f.a(272), (byte)4, (byte)4);
                           this.d(10);
                           ++this.ay;
                           break;
                        case 4:
                           this.a((String)aeii.f.a(273), (byte)1, (byte)4);
                           this.d(10);
                           ++this.ay;
                           break;
                        case 5:
                           this.a((String)aeii.f.a(274), (byte)4, (byte)4);
                           ++this.ay;
                           break;
                        case 6:
                           this.n = this.a((int)5, 2, (byte)0);
                           this.n.a(7, 2, false);
                           ++this.ay;
                           break;
                        case 7:
                           if (this.n.f != 1) {
                              this.n.a();
                              this.n = null;
                              this.o = this.a((int)7, 3, (byte)0);
                              this.o.a(7, 2, false);
                              ++this.ay;
                           }
                           break;
                        case 8:
                           if (this.o.f != 1) {
                              this.o.a();
                              this.o = null;
                              this.p = this.a((int)9, 2, (byte)0);
                              this.p.a(7, 2, false);
                              ++this.ay;
                           }
                           break;
                        case 9:
                           if (this.p.f != 1) {
                              this.p.a();
                              this.p = null;
                              this.b[1].a(7, 2, false);
                              this.d(20);
                              ++this.ay;
                           }
                           break;
                        case 10:
                           this.e(9, 15);
                           this.d = true;
                           break;
                        case 11:
                           e var32;
                           (var32 = this.a((String)null, aeii.f.a(279), this.b, 2000)).a(this.c, 2, 17);
                           aeii.f.a.a((f)var32);
                           ++this.ay;
                           break;
                        case 12:
                           this.r = false;
                           this.a(this.a((int)9, 15, (byte)0));
                           ++this.ay;
                           break;
                        case 13:
                           if (this.T >= 2) {
                              this.a((String)aeii.f.a(275), (byte)0, (byte)4);
                              this.a((int)9, 15, (byte)0).a();
                              this.r = true;
                              this.d(20);
                              ++this.ay;
                           }
                           break;
                        case 14:
                           this.d = false;
                           this.a((String)aeii.f.a(276), (byte)4, (byte)4);
                           ++this.ay;
                           break;
                        case 15:
                           this.a((String)aeii.f.a(277), (byte)1, (byte)4);
                           this.d(10);
                           ++this.ay;
                           break;
                        case 16:
                           this.d = true;
                           this.Y = 4;
                           this.e(3, 9);
                           break;
                        case 17:
                           this.e(13, 4);
                           break;
                        case 18:
                           this.Y = 12;
                           this.a((String)aeii.f.a(278), (byte)5, (byte)4);
                           this.e(this.b[0].b, this.b[0].c);
                           break;
                        case 19:
                           this.t();
                           ++this.ay;
                           break;
                        case 20:
                           if (this.s) {
                              this.d = false;
                              this.m = false;
                              this.d(20);
                              ++this.ay;
                           }
                           break;
                        case 21:
                           this.o = true;
                           this.R = 0;
                           this.q = true;
                           ++this.ay;
                           break;
                        case 22:
                           if (this.R >= 16) {
                              this.b(7, 2);
                              this.d(7, 2);
                              this.i();
                              this.b[1] = aeii.a.a((byte)9, (byte)1, (int)7, (int)2);
                              this.b[1].b((int)3);
                              aeii.a.a((byte)9, (byte)0, (int)6, (int)3);
                              aeii.a.a((byte)9, (byte)0, (int)8, (int)3).b((int)2);
                              aeii.a.a((byte)0, (byte)0, (int)6, (int)1);
                              aeii.a.a((byte)0, (byte)0, (int)8, (int)1);
                              this.d(10);
                              ++this.ay;
                              aeii.c.a(8, 0);
                           }
                           break;
                        case 23:
                           this.o = false;
                           this.p = true;
                           this.R = 0;
                           ++this.ay;
                           break;
                        case 24:
                           if (this.R >= 16) {
                              this.a((String)aeii.f.a(281), (byte)4, (byte)4);
                              this.d(15);
                              ++this.ay;
                           }
                           break;
                        case 25:
                           this.a((String)aeii.f.a(282), (byte)0, (byte)4);
                           this.d(8);
                           ++this.ay;
                           break;
                        case 26:
                           this.a((String)aeii.f.a(283), (byte)4, (byte)4);
                           this.d(15);
                           ++this.ay;
                           break;
                        case 27:
                           this.a(this.f, 168, 48, 0, 0, 1, 50);
                           this.a.removeElement(this.b[1]);
                           this.d(15);
                           ++this.ay;
                           break;
                        case 28:
                           aeii.c.d(400);
                           this.a((int)5000);
                           this.d(10);
                           ++this.ay;
                           break;
                        case 29:
                           this.a((String)aeii.f.a(284), (byte)5, (byte)4);
                           this.d(5);
                           ++this.ay;
                           break;
                        case 30:
                           this.a((String)aeii.f.a(285), (byte)2, (byte)4);
                           this.d(5);
                           ++this.ay;
                           break;
                        case 31:
                           this.a((String)aeii.f.a(286), (byte)1, (byte)4);
                           this.d(5);
                           ++this.ay;
                           break;
                        case 32:
                           this.a((String)aeii.f.a(287), (byte)0, (byte)4);
                           this.o = true;
                           this.R = 0;
                           ++this.ay;
                           break;
                        case 33:
                           if (this.R >= 16) {
                              this.d(10);
                              this.C = false;
                              ++this.ay;
                           }
                           break;
                        case 34:
                           this.a((int)5, 2, (int)2);
                           ++this.ay;
                           break;
                        case 35:
                           aeii.c.a(0, 0);
                           this.a(aeii.f.a(288));
                           ++this.ay;
                           break;
                        case 36:
                           this.aC = 0;
                           this.d = 14;
                           ++this.ay;
                     }
                  }
               } else {
                  if (this.ay <= 10) {
                     if (this.r == null) {
                        this.r = this.a((int)11, -1, (byte)0)[0];
                     }

                     if (this.r.f == 3) {
                        this.r = null;
                        this.v();
                        return;
                     }
                  }

                  switch (this.ay) {
                     case 0:
                        if (this.b[0].f != 1) {
                           this.j = null;
                           this.d(10);
                           ++this.ay;
                        }
                        break;
                     case 1:
                        this.a((String)aeii.f.a(262), (byte)5, (byte)4);
                        this.d(5);
                        ++this.ay;
                        break;
                     case 2:
                        this.a((String)aeii.f.a(263), (byte)0, (byte)4);
                        this.d(5);
                        ++this.ay;
                        break;
                     case 3:
                        a[] var19 = this.a((int)-1, -1, (byte)0);

                        for(int var7 = 0; var7 < var19.length; ++var7) {
                           var19[var7].a = null;
                        }

                        this.t();
                        ++this.ay;
                        break;
                     case 4:
                        if (this.a >= 2) {
                           this.d(15);
                           this.m = false;
                           this.d = false;
                           this.e(11, 7);
                        }
                        break;
                     case 5:
                        a var18;
                        (var18 = aeii.a.a((byte)5, (byte)1, (int)11, (int)8)).b(this.b);
                        var18.a(14, 7, true);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 6:
                        a var31;
                        (var31 = aeii.a.a((byte)0, (byte)1, (int)11, (int)8)).b(this.b);
                        var31.a(13, 7, true);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 7:
                        a var37;
                        (var37 = aeii.a.a((byte)3, (byte)1, (int)11, (int)8)).b(this.b);
                        var37.a(12, 7, true);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 8:
                        aeii.a.a((byte)1, (byte)1, (int)11, (int)8).a(13, 8, false);
                        this.d(20);
                        ++this.ay;
                        break;
                     case 9:
                        this.a((String)aeii.f.a(264), (byte)5, (byte)4);
                        this.m = true;
                        this.d = true;
                        this.e(this.b[0].b, this.b[0].c);
                        break;
                     case 10:
                        boolean var30 = false;
                        a[] var6 = this.a((int)-1, 2, (byte)0);

                        for(int var17 = 0; var17 < var6.length; ++var17) {
                           if (var6[var17].b <= 9 || var6[var17].c >= 10) {
                              var30 = true;
                              break;
                           }
                        }

                        if (var30 || this.a((int)-1, -1, (byte)1) == 0) {
                           this.d = false;
                           this.m = false;
                           this.d(10);
                           ++this.ay;
                        }
                        break;
                     case 11:
                        this.n = this.a((int)11, -1, (byte)0)[0];
                        this.o = aeii.a.a((byte)8, (byte)1, (int)this.f, (int)this.n.c);
                        this.e(this.f - 1, this.n.c);
                        this.d = false;
                        break;
                     case 12:
                        this.o.a(this.n.b, this.n.c, false);
                        this.d(5);
                        ++this.ay;
                        break;
                     case 13:
                        this.a((String)aeii.f.a(265), (byte)5, (byte)4);
                        this.j = this.o;
                        ++this.ay;
                        break;
                     case 14:
                        if (this.o.f != 1) {
                           this.a((String)aeii.f.a(266), (byte)0, (byte)4);
                           this.o.a(-1, this.o.c, false);
                           this.d(3);
                           ++this.ay;
                        }
                        break;
                     case 15:
                        this.n.a(-1, this.o.c, false);
                        ++this.ay;
                        break;
                     case 16:
                        if (this.o.f != 1) {
                           this.d(10);
                           this.n.a();
                           this.o.a();
                           this.d(0, this.o.c);
                           this.n = null;
                           this.o = null;
                           this.j = null;
                           ++this.ay;
                        }
                        break;
                     case 17:
                        this.e(1, 9);
                        break;
                     case 18:
                        this.b[1] = aeii.a.a((byte)9, (byte)1, (int)-2, (int)8);
                        this.b[1].a(0, 8, false);
                        aeii.a.a((byte)0, (byte)1, (int)-1, (int)8).a(3, 8, false);
                        aeii.a.a((byte)0, (byte)1, (int)-1, (int)10).a(1, 10, false);
                        aeii.a.a((byte)8, (byte)1, (int)-3, (int)7).a(4, 8, false);
                        aeii.a.a((byte)8, (byte)1, (int)-3, (int)11).a(2, 10, false);
                        aeii.a.a((byte)4, (byte)1, (int)-2, (int)9).a(2, 9, false);
                        aeii.a.a((byte)6, (byte)1, (int)-4, (int)9).a(4, 9, false);
                        aeii.a.a((byte)6, (byte)1, (int)-6, (int)9).a(5, 10, false);
                        this.d(50);
                        ++this.ay;
                        break;
                     case 19:
                        this.a((String)aeii.f.a(267), (byte)3, (byte)4);
                        ++this.ay;
                        break;
                     case 20:
                        this.e(13, 14);
                        break;
                     case 21:
                        aeii.a.a((byte)0, (byte)1, (int)13, (int)14).a(12, 14, false);
                        this.d(5);
                        ++this.ay;
                        break;
                     case 22:
                        aeii.a.a((byte)6, (byte)1, (int)13, (int)14).a(14, 14, false);
                        this.d(5);
                        ++this.ay;
                        break;
                     case 23:
                        aeii.a.a((byte)2, (byte)1, (int)13, (int)14).a(13, 12, false);
                        this.d(5);
                        ++this.ay;
                        break;
                     case 24:
                        aeii.a.a((byte)3, (byte)1, (int)13, (int)14).a(13, 15, false);
                        this.d(15);
                        ++this.ay;
                        break;
                     case 25:
                        this.a((String)aeii.f.a(268), (byte)5, (byte)4);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 26:
                        this.a((String)aeii.f.a(269), (byte)0, (byte)4);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 27:
                        this.e(13, 17);
                        break;
                     case 28:
                        a var16;
                        (var16 = aeii.a.a((byte)9, (byte)0, (int)13, (int)18)).b((int)2);
                        var16.a(13, 16, false);
                        aeii.a.a((byte)6, (byte)0, (int)12, (int)18).a(12, 16, false);
                        aeii.a.a((byte)8, (byte)0, (int)14, (int)19).a(14, 16, false);
                        aeii.a.a((byte)4, (byte)0, (int)13, (int)19).a(13, 17, false);
                        aeii.a.a((byte)1, (byte)0, (int)12, (int)19).a(12, 17, false);
                        this.d(20);
                        ++this.ay;
                        break;
                     case 29:
                        this.a((String)aeii.f.a(270), (byte)1, (byte)4);
                        this.d(10);
                        ++this.ay;
                        break;
                     case 30:
                        this.e(this.b[0].b, this.b[0].c);
                        break;
                     case 31:
                        this.L = 18;
                        ++this.ay;
                        break;
                     case 32:
                        this.m = true;
                        this.d = true;
                        this.e = this.a(aeii.f.a(121 + this.x), aeii.f.a(138), this.b, -1);
                        this.e.a((byte)0, true);
                        this.e.a((f)null);
                        aeii.f.a.a((f)this.e);
                        ++this.ay;
                        break;
                     case 33:
                        if (this.a((int)-1, -1, (byte)1) == 0 && this.e(1) == 0) {
                           this.m = false;
                           this.d = false;
                           this.d(10);
                           ++this.ay;
                        }
                        break;
                     case 34:
                        this.u();
                  }
               }

               this.a = null;
            }
         }

      }
   }

   private void t() {
      this.G = null;
      this.Y = 12;
      aeii.a.c = aeii.a.b;
      this.e.a((byte)0, true);
      this.e.a((byte)1, false);
      aeii.f.a.a((f)this.e);
      this.d = true;
      this.m = true;
      this.n = false;
      aeii.c.a(2, 0);
   }

   private void e(int var1, int var2) {
      this.aA = var1;
      this.aB = var2;
      this.d(var1, var2);
      ++this.ay;
   }

   private void u() {
      aeii.c.f();
      aeii.c.b(6, 1);
      aeii.f.a.a((f)this.a((String)null, aeii.f.a(72), this.b, 3000));
      this.d = this.a;
      this.ay = -1;
      this.aC = 0;
      this.d = 10;
   }

   private void v() {
      this.A = true;
      this.ay = 0;
      this.d(20);
      aeii.c.f();
      aeii.c.b(7, 1);
   }

   private void b(a var1, a var2) {
      try {
         System.gc();
         this.o = this.b - this.ak;
         this.E = true;
         this.G = 0;
         this.B = false;
         this.f = var1;
         this.g = var2;
         aeii.c.g();
         this.a = new h(this, var1, (h)null);
         this.b = new h(this, var2, this.a);
         this.a.a = this.b;
         var1.c(var2);
         if (var2.a((int)var1.b, (int)var1.c)) {
            var2.c(var1);
            this.D = true;
         } else {
            this.D = false;
         }

         this.a.a = (byte)var1.h;
         this.a.b = (byte)var1.b();
         this.b.a = (byte)var2.h;
         this.b.b = (byte)var2.b();
         aeii.c.b(h[this.d[this.b]], 0);
         this.h = 2;
      } catch (IOException var3) {
         var3.printStackTrace();
      }
   }

   public final void a(a.a.a.g var1) {
      this.g.addElement(var1);
   }

   public final void b(a.a.a.g var1) {
      this.g.removeElement(var1);
   }

   public final void a(int var1) {
      this.C = true;
      this.m = (long)var1;
      this.n = this.a;
   }

   private e b(f var1) {
      e var2;
      if (this.p.length > 0) {
         e var3;
         (var3 = (var2 = new e((byte)11, 0)).a(aeii.f.a(46))).a = this.c[6];
         var2.a(this.p, this.a / 2, (this.b + var3.c) / 2, this.a, this.b - var3.c, 3, 4);
      } else {
         (var2 = new e((byte)10, 0)).a(aeii.f.a(46)).a = this.c[6];
         var2.a((String)null, (String)aeii.f.a(52), this.a, -1);
      }

      var2.a(var1);
      aeii.f.a.a((f)var2);
      return var2;
   }

   private void c(byte[] var1) {
      try {
         DataInputStream var6;
         (var6 = new DataInputStream(new ByteArrayInputStream(var1))).readLong();
         switch (var6.readInt()) {
            case 10001:
               var6.readUTF();
               var6.readInt();
               var6.readUTF();
               var6.readInt();
               var6.readUTF();
               var6.readInt();
               var6.readUTF();
               int var2 = var6.readInt();
               var6.readUTF();
               var6.readUTF();
               if (this.aG == 0) {
                  var2 = var6.readInt() / 2;
                  this.p = new String[var2];
                  this.q = new String[var2];
                  this.r = new String[var2];

                  for(int var13 = 0; var13 < var2; ++var13) {
                     this.p[var13] = var6.readUTF();
                     this.q[var13] = var6.readUTF();
                  }

                  this.A = this.b(this.a);
               } else if (this.aG == 1) {
                  var6.readInt();
                  this.r[this.aD] = var6.readUTF();
                  e var12;
                  (var12 = this.a(this.p[this.aD], this.r[this.aD], this.b, this.b / 2, -1)).a(this.a);
                  aeii.f.a.a((f)var12);
               } else if (this.aG != 2) {
                  if (this.aG == 3) {
                     String var11 = this.s[this.aE];
                     byte[] var4 = new byte[var2];
                     var6.readFully(var4);
                     this.a(var11, var4);
                     this.a(this.D.a);
                     e var7;
                     (var7 = this.a((String)null, aeii.f.a(45, (String)var11), this.b, 2000)).a((f)this.D);
                     aeii.f.a.a((f)var7);
                  }
               } else {
                  var2 = var6.readInt() / 3;
                  this.s = new String[var2];
                  this.t = new String[var2];
                  this.n = new int[var2];

                  for(int var3 = 0; var3 < var2; ++var3) {
                     this.s[var3] = var6.readUTF();
                     this.t[var3] = var6.readUTF();
                     this.n[var3] = Integer.parseInt(var6.readUTF());
                  }

                  this.a(this.a);
                  aeii.f.a.a((f)this.D);
               }

               this.a = null;
               return;
            default:
               var6.close();
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      e var8;
      (var8 = this.a((String)null, aeii.f.a(44), this.b, -1)).a(this.a);
      this.a = null;
      aeii.f.a.a((f)var8);
   }

   private void a(int var1, String var2, String var3, f var4) {
      try {
         this.aG = var1;
         this.a = var4;
         this.G = false;
         this.a = new ByteArrayOutputStream();
         this.a = new DataOutputStream(this.a);
         this.a.writeInt(10001);
         this.a.writeUTF(this.d);
         this.a.writeUTF(this.e);
         this.aF = this.a.size();
         this.a.writeUTF("resourceName");
         this.a.writeUTF(var2);
         this.a.writeUTF("languageCode");
         this.a.writeUTF(var3);
         this.a.writeUTF("maxChunkSize");
         this.a.writeUTF("1024");
         this.a.writeUTF("chunk");
         this.a.writeUTF("0");
         this.a.writeUTF("requestId");
         this.a.writeUTF("0");
         e var6;
         (var6 = this.a((String)null, aeii.f.a(43), this.b, -1)).a(var4);
         aeii.f.a.a((f)var6);
         (new Thread(this)).start();
      } catch (IOException var5) {
         var5.printStackTrace();
      }
   }

   public final void run() {
      byte[] var1 = null;
      HttpConnection var2 = null;

      try {
         byte[] var3 = this.a.toByteArray();
         this.a.close();
         this.a.close();
         var2 = (HttpConnection)Connector.open(this.f);
         if ("J4d28g1Kl490".length() > 0) {
            byte[] var4 = "J4d28g1Kl490".getBytes();

            for(int var5 = this.aF; var5 < var3.length; ++var5) {
               var3[var5] ^= var4[var5 % var4.length];
            }
         }

         var2.setRequestMethod("POST");
         var2.setRequestProperty("Content-Length", Integer.toString(var3.length));
         var2.setRequestProperty("Connection", "close");
         OutputStream var16;
         (var16 = var2.openOutputStream()).write(var3);
         var16.flush();
         var16.close();
         DataInputStream var18 = var2.openDataInputStream();
         int var14;
         if ((var14 = var2.getResponseCode()) != 200) {
            throw new Exception("" + var14);
         }

         ByteArrayOutputStream var15 = new ByteArrayOutputStream();

         while((var17 = var18.read()) != -1) {
            var15.write(var17);
         }

         var1 = var15.toByteArray();
         var15.close();
         var18.close();
      } catch (Exception var12) {
         var12.printStackTrace();
      } finally {
         if (var2 != null) {
            try {
               var2.close();
            } catch (Exception var11) {
               var11.printStackTrace();
            }
         }

      }

      if (!this.G) {
         this.c(var1);
      }

   }

   private void f(int var1) {
      int var2 = this.l[var1];
      --this.U;
      String[] var3 = new String[this.U];
      int[] var4 = new int[this.U];
      System.arraycopy(this.l, 0, var3, 0, var1);
      System.arraycopy(this.l, var1 + 1, var3, var1, this.U - var1);
      System.arraycopy(this.l, 0, var4, 0, var1);
      System.arraycopy(this.l, var1 + 1, var4, var1, this.U - var1);
      this.l = var3;
      this.l = var4;
      aeii.c.a("download", var2);
      this.W = aeii.c.a("download");
      this.w();

      for(int var5 = 0; var5 < 3; ++var5) {
         if (this.k[var5] == var2 + e.length) {
            this.n[var5] = -1;
            this.k[var5] = -1;
            this.k[var5] = "\n" + aeii.f.a(79) + "\n ";
            aeii.c.a("save", var5, new byte[0]);
         }
      }

   }

   private void a(String var1, byte[] var2) {
      String[] var3 = new String[this.U + 1];
      int[] var4 = new int[this.U + 1];
      System.arraycopy(this.l, 0, var3, 0, this.U);
      System.arraycopy(this.l, 0, var4, 0, this.U);
      this.l = var3;
      this.l = var4;
      this.l[this.U] = aeii.c.a("download", var2);
      this.l[this.U] = var1;
      ++this.U;
      this.W = aeii.c.a("download");
      this.w();
   }

   private void w() {
      try {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         DataOutputStream var2;
         (var2 = new DataOutputStream(var1)).writeInt(this.U);

         for(int var3 = 0; var3 < this.U; ++var3) {
            var2.writeInt(this.l[var3]);
            var2.writeUTF(this.l[var3]);
         }

         aeii.c.a("settings", 2, var1.toByteArray());
         var2.close();
      } catch (IOException var4) {
         var4.printStackTrace();
      }
   }

   public final void commandAction(Command var1, Displayable var2) {
      if (var2 == this.a) {
         if (var1 != this.a) {
            if (var1 == this.e) {
               System.arraycopy(this.o, 0, this.p, 0, this.o.length);
               this.itemStateChanged(this.a);
               aeii.c.a((Displayable)this.e);
               return;
            }

            if (var1 == this.c) {
               for(int var16 = 0; var16 < this.b.length; ++var16) {
                  this.b[var16] = true;
               }

               this.a.setSelectedFlags(this.b);
               this.itemStateChanged(this.a);
               return;
            }

            if (var1 != this.d) {
               aeii.c.a((Displayable)aeii.f.a);
               aeii.f.a.a((f)this);
               this.C();
               return;
            }

            for(int var15 = 0; var15 < this.b.length; ++var15) {
               this.b[var15] = false;
            }

            this.a.setSelectedFlags(this.b);
            this.itemStateChanged(this.a);
            return;
         }

         if (this.a.size() == 0 || this.b.size() == 0 || this.c.size() == 0) {
            return;
         }

         int var13 = Integer.parseInt(this.b.getString());
         int var23 = Integer.parseInt(this.c.getString());
         if (var13 <= 0 || var23 <= 0) {
            return;
         }

         this.a.getSelectedFlags(this.b);
         int var3 = 0;

         for(int var4 = 0; var4 < this.b.length; ++var4) {
            if (this.b[var4]) {
               ++var3;
            }
         }

         if (var3 == 0) {
            return;
         }

         try {
            aeii.c.a((Displayable)aeii.f.a);
            aeii.f.a.a((f)this);
            this.c = 1;
            this.n = 1;
            this.j = true;
            aeii.f.a.a();
            this.e();
            String var10001 = this.a.getString();
            int[] var5 = this.o;
            int var56 = var23;
            var3 = var13;
            String var24 = var10001;
            g var14 = this;

            try {
               aeii.c.f();
               var14.c = new Vector();
               var14.o = false;
               var14.d = true;
               var14.g = false;
               var14.s = false;
               var14.b = null;
               var14.c = null;
               var14.i = null;
               var14.d = null;
               var14.e = null;
               var14.e.removeAllElements();
               var14.a = 0;
               var14.b = 0;
               var14.b = 0;
               var14.ay = 0;
               var14.i();
               var14.b = null;
               var14.a = null;
               var14.b = null;
               var14.f = 0;

               for(int var6 = 0; var6 < 5; ++var6) {
                  var14.l[var6] = -1;
               }

               var14.i = true;
               aeii.c.g();
               var14.b = var24;
               var14.f = var3;
               var14.g = var56;
               var14.a = new byte[var14.f][var14.g];
               var14.b = new byte[var14.f][var14.g];
               var14.g = new byte[var14.f][var14.g];
               var14.as = 0;
               int var61 = 0;
               Vector var25 = new Vector();
               Vector var45 = new Vector();
               aeii.c.a(var5, true);

               for(short var7 = 0; var7 < var14.f; ++var7) {
                  for(short var8 = 0; var8 < var14.g; ++var8) {
                     var14.a[var7][var8] = (byte)aeii.c.a(var5);
                     var14.b[var7][var8] = 0;
                     if (b(var14.a[var7][var8])) {
                        var56 = var14.a(var7, var8);
                        byte[] var9;
                        (var9 = new byte[3])[0] = (byte)var7;
                        var9[1] = (byte)var8;
                        var9[2] = (byte)var56;
                        var25.addElement(var9);
                        ++var61;
                        if (var14.a(var7, var8) == 9) {
                           if (var14.c == 1 && var56 != 0 && var14.l[var56] == -1) {
                              var14.d[var14.f] = (byte)var56;
                              byte[] var10000 = var14.l;
                              byte var10004 = var14.f;
                              var14.f = (byte)(var10004 + 1);
                              var10000[var56] = var10004;
                           }

                           byte[] var58;
                           (var58 = new byte[2])[0] = (byte)var7;
                           var58[1] = (byte)var8;
                           var45.addElement(var58);
                           ++var14.as;
                        }
                     }
                  }
               }

               aeii.c.a(var5, false);
               var14.m = new int[var61];
               var14.e = new byte[var61][];
               var25.copyInto(var14.e);
               var14.f = new byte[var14.as][];
               var45.copyInto(var14.f);
               var14.t = var14.f * 24;
               var14.u = var14.g * 24;
               var14.A = false;
               var14.r = null;
               var14.n = null;
               var14.o = null;
               var14.p = null;
               var14.q = null;
               var14.a = null;
               if (var14.c == 1) {
                  var14.ay = 100;
                  var14.m = true;
               }

               var14.d = new a[var14.e.length];
               var14.q = new byte[var14.e.length];
               var14.b = new a.a.a.g[var14.e.length];

               for(short var62 = 0; var62 < var14.e.length; ++var62) {
                  if (var14.a(var14.e[var62][0], var14.e[var62][1]) == 8) {
                     var14.b[var62] = a.a.a.g.a(var14.o, 0, -1, 0, 1, 250, (byte)0);
                     var14.b[var62].b = false;
                  }
               }

               aeii.c.a(g[1], 0);
               var14.v = 0;
               var14.w = 0;
               var14.h = 0;
               var14.i = 0;
               var14.q.b(var14.h * 24, var14.i * 24);
            } catch (Exception var11) {
               var11.printStackTrace();
            }

            this.j = false;
            this.d = 0;
            this.C();
         } catch (Exception var12) {
            var12.printStackTrace();
            return;
         }
      } else {
         if (var2 == this.b) {
            if (var1 == this.a) {
               this.a.getSelectedFlags(this.b);
               int var22 = 0;

               for(int var43 = 0; var43 < this.b.length; ++var43) {
                  if (this.b[var43]) {
                     ++var22;
                  }
               }

               if (var22 == 0) {
                  return;
               }

               this.itemStateChanged(this.a);
               aeii.c.a((Displayable)aeii.f.a);
               aeii.f.a.a((f)this);
               return;
            }

            if (var1 == this.e) {
               System.arraycopy(this.o, 0, this.p, 0, this.o.length);
               this.itemStateChanged(this.a);
               aeii.c.a((Displayable)this.e);
               return;
            }

            if (var1 == this.c) {
               for(int var21 = 0; var21 < this.b.length; ++var21) {
                  this.b[var21] = true;
               }

               this.a.setSelectedFlags(this.b);
               this.itemStateChanged(this.a);
               return;
            }

            if (var1 != this.d) {
               aeii.c.a((Displayable)aeii.f.a);
               aeii.f.a.a((f)this);
               return;
            }

            for(int var20 = 0; var20 < this.b.length; ++var20) {
               this.b[var20] = false;
            }

            this.a.setSelectedFlags(this.b);
            this.itemStateChanged(this.a);
            return;
         }

         if (var2 == this.c) {
            if (var1 == this.a) {
               if (this.a.size() == 0) {
                  return;
               }

               aeii.c.a((Displayable)aeii.f.a);
               aeii.f.a.a((f)this);
               this.b = this.a.getString();
               return;
            }

            if (var1 == this.f) {
               this.a.setString(Long.toString(System.currentTimeMillis(), 36).toUpperCase());
               return;
            }

            aeii.c.a((Displayable)aeii.f.a);
            aeii.f.a.a((f)this);
            return;
         }

         if (var2 == this.d) {
            if (var1 == this.a) {
               if (this.e.size() == 0 || this.f.size() == 0 || this.g.size() == 0 || this.h.size() == 0) {
                  return;
               }

               this.a(Integer.parseInt(this.e.getString()), Integer.parseInt(this.f.getString()), Integer.parseInt(this.g.getString()), Integer.parseInt(this.h.getString()), this.o);
            }

            aeii.c.a((Displayable)aeii.f.a);
            aeii.f.a.a((f)this);
            return;
         }

         if (var2 == this.e) {
            if (var1 != this.a) {
               System.arraycopy(this.p, 0, this.o, 0, this.o.length);
            } else {
               this.a.getSelectedFlags(this.b);

               for(int var17 = 0; var17 < this.o.length; ++var17) {
                  this.b[var17] = this.o[var17] > 0;
               }

               this.a.setSelectedFlags(this.b);
               this.itemStateChanged(this.a);
            }

            if (this.b != null) {
               aeii.c.a((Displayable)this.b);
               return;
            }

            if (this.a != null) {
               aeii.c.a((Displayable)this.a);
               return;
            }
         } else {
            if (var2 == this.f) {
               if (var1 == this.a) {
                  int[] var19 = new int[]{100 - this.b.getValue(), 100};

                  for(int var42 = 0; var42 < this.f; ++var42) {
                     for(int var55 = 0; var55 < this.g; ++var55) {
                        if (this.b[var42][var55] > 0) {
                           this.b[var42][var55] = (byte)aeii.c.a(var19);
                        }
                     }
                  }
               }

               aeii.c.a((Displayable)aeii.f.a);
               aeii.f.a.a((f)this);
               return;
            }

            if (var2 == this.a) {
               if (var1 == this.a) {
                  try {
                     String[] var18;
                     if ((var18 = aeii.c.a(this.a.getString().toLowerCase(), ' '))[0].equals("win")) {
                        if (this.x == 7) {
                           this.s = true;
                        } else {
                           this.u();
                        }
                     } else if (var18[0].equals("fps")) {
                        aeii.f.a.c(Integer.parseInt(var18[1]));
                     } else if (var18[0].equals("money")) {
                        if (var18[1].equals("add")) {
                           if (var18[2].equals("player")) {
                              int[] var68 = this.b;
                              byte var70 = this.b;
                              var68[var70] += Integer.parseInt(var18[3]);
                           } else if (var18[2].equals("enemy")) {
                              for(int var41 = 0; var41 < 4; ++var41) {
                                 if (this.m[var41] == 0) {
                                    int[] var67 = this.b;
                                    var67[var41] += Integer.parseInt(var18[3]);
                                 }
                              }
                           } else if (var18[2].equals("all")) {
                              for(int var40 = 0; var40 < 4; ++var40) {
                                 int[] var66 = this.b;
                                 var66[var40] += Integer.parseInt(var18[3]);
                              }
                           }
                        } else if (var18[1].equals("remove")) {
                           if (var18[2].equals("player")) {
                              int[] var65 = this.b;
                              byte var69 = this.b;
                              var65[var69] -= Integer.parseInt(var18[3]);
                           } else if (var18[2].equals("enemy")) {
                              for(int var39 = 0; var39 < 4; ++var39) {
                                 if (this.m[var39] == 0) {
                                    int[] var64 = this.b;
                                    var64[var39] -= Integer.parseInt(var18[3]);
                                 }
                              }
                           } else if (var18[2].equals("all")) {
                              for(int var38 = 0; var38 < 4; ++var38) {
                                 int[] var63 = this.b;
                                 var63[var38] -= Integer.parseInt(var18[3]);
                              }
                           }
                        }
                     } else if (!var18[0].equals("occupy")) {
                        if (var18[0].equals("unit")) {
                           if (var18[1].equals("reset")) {
                              if (var18[2].equals("current")) {
                                 a var37;
                                 if ((var37 = this.a((int)this.h, this.i, (byte)0)) != null) {
                                    var37.f = 0;
                                 }
                              } else if (var18[2].equals("player")) {
                                 for(int var36 = 0; var36 < this.a.size(); ++var36) {
                                    a var54;
                                    if ((var54 = (a)this.a.elementAt(var36)).e == this.b) {
                                       var54.f = 0;
                                    }
                                 }
                              } else if (var18[2].equals("enemy")) {
                                 for(int var35 = 0; var35 < this.a.size(); ++var35) {
                                    a var53;
                                    if ((var53 = (a)this.a.elementAt(var35)).e != this.b) {
                                       var53.f = 0;
                                    }
                                 }
                              } else if (var18[2].equals("all")) {
                                 for(int var34 = 0; var34 < this.a.size(); ++var34) {
                                    ((a)this.a.elementAt(var34)).f = 0;
                                 }
                              }
                           } else if (var18[1].equals("promote")) {
                              if (var18[2].equals("current")) {
                                 a var33;
                                 if ((var33 = this.a((int)this.h, this.i, (byte)0)) != null) {
                                    var33.a((byte)Integer.parseInt(var18[3]));
                                 }
                              } else if (var18[2].equals("player")) {
                                 byte var32 = (byte)Integer.parseInt(var18[3]);

                                 for(int var52 = 0; var52 < this.a.size(); ++var52) {
                                    a var60;
                                    if ((var60 = (a)this.a.elementAt(var52)).e == this.b) {
                                       var60.a(var32);
                                    }
                                 }
                              } else if (var18[2].equals("enemy")) {
                                 byte var31 = (byte)Integer.parseInt(var18[3]);

                                 for(int var51 = 0; var51 < this.a.size(); ++var51) {
                                    a var59;
                                    if ((var59 = (a)this.a.elementAt(var51)).e != this.b) {
                                       var59.a(var31);
                                    }
                                 }
                              } else if (var18[2].equals("all")) {
                                 byte var30 = (byte)Integer.parseInt(var18[3]);

                                 for(int var50 = 0; var50 < this.a.size(); ++var50) {
                                    ((a)this.a.elementAt(var50)).a(var30);
                                 }
                              }
                           }
                        }
                     } else if (var18[1].equals("house")) {
                        if (var18[2].equals("civilian")) {
                           for(int var29 = 0; var29 < this.f; ++var29) {
                              for(int var49 = 0; var49 < this.g; ++var49) {
                                 if (this.a(var29, var49) == 8 && this.a(var29, var49) == 0) {
                                    this.b(var29, var49, this.d[this.b]);
                                 }
                              }
                           }
                        } else if (var18[2].equals("all")) {
                           for(int var28 = 0; var28 < this.f; ++var28) {
                              for(int var48 = 0; var48 < this.g; ++var48) {
                                 if (this.a(var28, var48) == 8) {
                                    this.b(var28, var48, this.d[this.b]);
                                 }
                              }
                           }
                        }
                     } else if (var18[1].equals("castle")) {
                        if (var18[2].equals("civilian")) {
                           for(int var27 = 0; var27 < this.f; ++var27) {
                              for(int var47 = 0; var47 < this.g; ++var47) {
                                 if (this.a(var27, var47) == 9 && this.a(var27, var47) == 0) {
                                    this.b(var27, var47, this.d[this.b]);
                                 }
                              }
                           }
                        } else if (var18[2].equals("all")) {
                           for(int var26 = 0; var26 < this.f; ++var26) {
                              for(int var46 = 0; var46 < this.g; ++var46) {
                                 if (this.a(var26, var46) == 9) {
                                    this.b(var26, var46, this.d[this.b]);
                                 }
                              }
                           }
                        }
                     }
                  } catch (Exception var10) {
                     return;
                  }
               }

               aeii.c.a((Displayable)aeii.f.a);
               aeii.f.a.a((f)this);
            }
         }
      }

   }

   public final void itemStateChanged(Item var1) {
      if (var1 == this.a) {
         this.a.getSelectedFlags(this.b);
         int var3 = 0;

         for(int var2 = 0; var2 < this.o.length; ++var2) {
            if (this.o[var2] > var3) {
               var3 = this.o[var2];
            }
         }

         if (var3 == 0) {
            var3 = 100;
         }

         for(int var4 = 0; var4 < this.b.length; ++var4) {
            if (this.b[var4]) {
               if (this.o[var4] <= 0) {
                  this.o[var4] = var3;
               }
            } else {
               this.o[var4] = 0;
            }
         }

      } else if (var1 == this.a) {
         this.a.setLabel(aeii.f.a(308, (String)Integer.toString(this.a.getValue() + 1)));
         this.a.setImage(this.a.getImage(this.a.getValue()));
         this.d.setString(Integer.toString(this.o[this.a.getValue()]));
      } else {
         if (var1 == this.d) {
            if (this.d.size() > 0) {
               this.o[this.a.getValue()] = Integer.parseInt(this.d.getString());
               return;
            }
         } else if (var1 == this.b) {
            this.b.setLabel(aeii.f.a(315, (String)(Integer.toString(this.b.getValue()) + "%")));
         }

      }
   }

   private void x() {
      if (this.c == null) {
         this.c = new Form(aeii.f.a(304));
         this.a = new TextField(aeii.f.a(294), this.b, 100, 0);
         this.c.append(this.a);
         this.c.addCommand(this.a);
         this.c.addCommand(this.f);
         this.c.addCommand(this.b);
         this.c.setCommandListener(this);
      }
   }

   private void y() {
      if (this.d == null) {
         this.d = new Form(aeii.f.a(306));
         this.e = new TextField(aeii.f.a(310), "0", 20, 2);
         this.f = new TextField(aeii.f.a(311), "0", 20, 2);
         this.g = new TextField(aeii.f.a(312), "0", 20, 2);
         this.h = new TextField(aeii.f.a(313), "0", 20, 2);
         this.d.append(this.e);
         this.d.append(this.f);
         this.d.append(this.g);
         this.d.append(this.h);
         this.d.addCommand(this.a);
         this.d.addCommand(this.b);
         this.d.setCommandListener(this);
      }
   }

   private void z() {
      if (this.f == null) {
         this.f = new Form(aeii.f.a(307));
         this.b = new Gauge("", true, 100, 100);
         this.b.setLayout(2049);
         this.itemStateChanged(this.b);
         this.f.append(this.b);
         this.f.addCommand(this.a);
         this.f.addCommand(this.b);
         this.f.setCommandListener(this);
         this.f.setItemStateListener(this);
      }
   }

   private void A() {
      if (this.b == null) {
         this.b = new Form(aeii.f.a(303));
         this.a = new ChoiceGroup(aeii.f.a(297), 2);
         if (this.o == null) {
            this.o = new int[this.d.length];

            for(int var1 = 0; var1 < this.o.length; ++var1) {
               this.o[var1] = 100;
            }
         }

         this.p = new int[this.d.length];
         this.b = new boolean[this.d.length];

         for(int var2 = 0; var2 < this.d.length; ++var2) {
            this.a.append(" " + aeii.f.a(308, (String)Integer.toString(var2 + 1)) + " ", this.d[var2].a());
            this.a.setSelectedIndex(var2, this.o[var2] > 0);
         }

         this.itemStateChanged(this.a);
         this.b.append(this.a);
         this.b.addCommand(this.a);
         this.b.addCommand(this.e);
         this.b.addCommand(this.c);
         this.b.addCommand(this.d);
         this.b.addCommand(this.b);
         this.b.setCommandListener(this);
         this.b.setItemStateListener(this);
         this.D();
      }
   }

   private void B() {
      this.a = new Form(aeii.f.a(292));
      this.a = new TextField(aeii.f.a(294), Long.toString(System.currentTimeMillis(), 36).toUpperCase(), 100, 0);
      int var1 = (super.j + 23) / 24;
      int var2 = (super.k - a + 23) / 24;
      this.b = new TextField(aeii.f.a(295), Integer.toString(var1), 3, 2);
      this.c = new TextField(aeii.f.a(296), Integer.toString(var2), 3, 2);
      this.a = new ChoiceGroup(aeii.f.a(297), 2);
      if (this.o == null) {
         this.o = new int[this.d.length];

         for(int var3 = 0; var3 < this.o.length; ++var3) {
            this.o[var3] = 100;
         }
      }

      this.p = new int[this.d.length];
      this.b = new boolean[this.d.length];

      for(int var4 = 0; var4 < this.d.length; ++var4) {
         this.a.append(" " + aeii.f.a(308, (String)Integer.toString(var4 + 1)) + " ", this.d[var4].a());
         this.a.setSelectedIndex(var4, this.o[var4] > 0);
      }

      this.itemStateChanged(this.a);
      this.a.append(this.a);
      this.a.append(this.b);
      this.a.append(this.c);
      this.a.append(this.a);
      this.a.addCommand(this.a);
      this.a.addCommand(this.e);
      this.a.addCommand(this.c);
      this.a.addCommand(this.d);
      this.a.addCommand(this.b);
      this.a.setCommandListener(this);
      this.a.setItemStateListener(this);
      this.D();
   }

   private void C() {
      this.a = null;
      this.a = null;
      this.b = null;
      this.c = null;
      this.a = null;
   }

   private void D() {
      if (this.e == null) {
         this.e = new Form(aeii.f.a(304));
         this.a = new ImageItem((String)null, (Image)null, 1, (String)null);
         this.a.setLayout(2049);
         this.a = new Gauge("", true, this.d.length - 1, 0);
         this.a.setLayout(2049);
         this.d = new TextField(aeii.f.a(309), "", 20, 2);
         this.d.setLayout(2049);
         this.e.append(this.a);
         this.e.append(this.a);
         this.e.append(this.d);
         this.e.addCommand(this.a);
         this.e.addCommand(this.b);
         this.e.setCommandListener(this);
         this.e.setItemStateListener(this);
      }
   }

   private void E() {
      this.b = null;
      this.a = null;
      this.o = null;
      this.p = null;
      this.e = null;
      this.d = null;
      this.a = null;
      this.a = null;
      this.c = null;
      this.a = null;
      this.d = null;
      this.e = null;
      this.f = null;
      this.g = null;
      this.h = null;
      this.f = null;
      this.b = null;
      this.n = 0;
   }

   private void F() {
      if (this.a == null) {
         this.a = new TextBox(aeii.f.a(314), "", 100, 0);
         this.a.addCommand(this.a);
         this.a.addCommand(this.b);
         this.a.setCommandListener(this);
      }
   }

   static {
      int[][][] var10000 = new int[][][]{new int[0][], {{150, 217, 244}, {65, 149, 233}, {0, 100, 198}, {12, 53, 112}}, {{244, 158, 156}, {219, 36, 113}, {161, 0, 112}, {95, 5, 120}}, {{171, 237, 90}, {99, 190, 37}, {0, 153, 55}, {0, 85, 82}}, {{0, 118, 150}, {0, 65, 114}, {0, 43, 75}, {0, 22, 48}}};
      a = new String[]{"blue", "red", "green", "black"};
      f = new int[]{10526880, 26054, 15204434, 39473, 16754};
      g = new int[]{-1, 2, 3, 2, 3};
      h = new int[]{-1, 4, 5, 4, 5};
      j = new byte[]{1, 2};
      a = new byte[]{0, 5, 10, 10, 15, 0, 5, 15, 15, 15};
      b = new byte[]{1, 1, 2, 2, 3, 3, 1, 1, 1, 1};
      i = new int[]{85, 83, 83, 83, 83, 83, 83, 83, 83, 175, 84, 84, 84, 175, 147, 159, 151, 155, 167, 171};
      j = new int[]{15, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214};
      p = new byte[]{0, 2, 3, 3, 1, 3, 3, 3, 3, 3, 3, 3};
   }
}
