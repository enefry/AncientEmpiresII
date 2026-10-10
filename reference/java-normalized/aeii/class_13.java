package aeii;

import a.a.a.class_0;
import a.a.a.class_1;
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

// $FF: renamed from: aeii.g
public final class class_13 extends class_11 implements Runnable, CommandListener, ItemStateListener {
   // $FF: renamed from: b java.lang.String[]
   private static String[] field_181;
   // $FF: renamed from: c java.lang.String[]
   private static String[] field_182;
   // $FF: renamed from: d java.lang.String[]
   private static String[] field_183;
   // $FF: renamed from: a java.lang.String
   private String field_184 = "?";
   // $FF: renamed from: a byte
   public static byte field_185 = 32;
   // $FF: renamed from: a int
   public int field_186;
   // $FF: renamed from: b int
   public int field_187;
   // $FF: renamed from: c int
   public int field_188;
   // $FF: renamed from: p int
   private int field_189;
   // $FF: renamed from: q int
   private int field_190 = 0;
   // $FF: renamed from: e java.lang.String[]
   private static String[] field_191 = new String[12];
   // $FF: renamed from: c int[]
   private static int[] field_192 = new int[]{-1, -1, -1, -1, -1, -1, -1, -1};
   // $FF: renamed from: a boolean[]
   private boolean[] field_193;
   // $FF: renamed from: d int[]
   private static int[] field_194 = new int[]{500, 1000, 2000, 5000, 10000, 25000, 50000, 75000, 100000, 150000, 200000};
   // $FF: renamed from: e int[]
   private static int[] field_195 = new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200};
   // $FF: renamed from: r int
   private int field_196;
   // $FF: renamed from: s int
   private int field_197;
   // $FF: renamed from: c byte
   private byte field_198;
   // $FF: renamed from: d int
   public static int field_199 = 1024;
   // $FF: renamed from: e int
   public static int field_200 = 2048;
   // $FF: renamed from: f java.lang.String[]
   private String[] field_201;
   // $FF: renamed from: g java.lang.String[]
   private String[] field_202;
   // $FF: renamed from: a aeii.d[]
   public class_16[] field_203;
   // $FF: renamed from: h java.lang.String[]
   private String[] field_204;
   // $FF: renamed from: c aeii.d[]
   private class_16[] field_205;
   // $FF: renamed from: i java.lang.String[]
   private String[] field_206;
   // $FF: renamed from: j java.lang.String[]
   private String[] field_207;
   // $FF: renamed from: f byte[]
   private byte[] field_208;
   // $FF: renamed from: g byte[]
   private byte[] field_209;
   // $FF: renamed from: h byte[]
   private byte[] field_210;
   // $FF: renamed from: c byte[][]
   private static byte[][] field_211 = new byte[][]{{0, 1}, {2, 3, 4}, {0, 1}, {5}};
   // $FF: renamed from: i byte[]
   private static byte[] field_212 = new byte[]{0};
   // $FF: renamed from: b long
   private long field_213;
   // $FF: renamed from: c long
   private long field_214;
   // $FF: renamed from: a java.lang.String[]
   public static final String[] field_215;
   // $FF: renamed from: f int[]
   private static int[] field_216;
   // $FF: renamed from: g int[]
   private static int[] field_217;
   // $FF: renamed from: h int[]
   private static int[] field_218;
   // $FF: renamed from: a a.a.a.g[][]
   public class_1[][] field_219;
   // $FF: renamed from: j byte[]
   private static byte[] field_220;
   // $FF: renamed from: a byte[]
   public static byte[] field_221;
   // $FF: renamed from: b byte[]
   public static byte[] field_222;
   // $FF: renamed from: b aeii.d[]
   public class_16[] field_223;
   // $FF: renamed from: k byte[]
   private byte[] field_224;
   // $FF: renamed from: c byte[]
   public byte[] field_225;
   // $FF: renamed from: t int
   private int field_226;
   // $FF: renamed from: u int
   private int field_227;
   // $FF: renamed from: v int
   private int field_228;
   // $FF: renamed from: w int
   private int field_229;
   // $FF: renamed from: f int
   public int field_230;
   // $FF: renamed from: g int
   public int field_231;
   // $FF: renamed from: a aeii.d
   private class_16 field_232;
   // $FF: renamed from: d aeii.d[]
   private class_16[] field_233;
   // $FF: renamed from: q a.a.a.g
   private class_1 field_234;
   // $FF: renamed from: r a.a.a.g
   private class_1 field_235;
   // $FF: renamed from: a a.a.a.g
   public class_1 field_236;
   // $FF: renamed from: b a.a.a.g
   public class_1 field_237;
   // $FF: renamed from: c a.a.a.g
   public class_1 field_238;
   // $FF: renamed from: d a.a.a.g
   public class_1 field_239;
   // $FF: renamed from: e a.a.a.g
   public class_1 field_240;
   // $FF: renamed from: f a.a.a.g
   public class_1 field_241;
   // $FF: renamed from: g a.a.a.g
   public class_1 field_242;
   // $FF: renamed from: s a.a.a.g
   private class_1 field_243;
   // $FF: renamed from: h a.a.a.g
   public class_1 field_244;
   // $FF: renamed from: i a.a.a.g
   public class_1 field_245;
   // $FF: renamed from: h int
   public int field_246;
   // $FF: renamed from: i int
   public int field_247;
   // $FF: renamed from: a byte[][]
   public byte[][] field_248;
   // $FF: renamed from: d byte
   private byte field_249;
   // $FF: renamed from: e byte
   private byte field_250;
   // $FF: renamed from: a long
   public long field_251;
   // $FF: renamed from: x int
   private int field_252;
   // $FF: renamed from: y int
   private int field_253;
   // $FF: renamed from: z int
   private int field_254;
   // $FF: renamed from: a aeii.a[]
   private class_2[] field_255;
   // $FF: renamed from: b aeii.a
   private class_2 field_256;
   // $FF: renamed from: A int
   private int field_257;
   // $FF: renamed from: B int
   private int field_258;
   // $FF: renamed from: b byte[][]
   public byte[][] field_259;
   // $FF: renamed from: b boolean
   private boolean field_260;
   // $FF: renamed from: c boolean
   private boolean field_261;
   // $FF: renamed from: d boolean
   private boolean field_262;
   // $FF: renamed from: a java.util.Vector
   public Vector field_263;
   // $FF: renamed from: b java.util.Vector
   private Vector field_264;
   // $FF: renamed from: C int
   private int field_265;
   // $FF: renamed from: D int
   private int field_266;
   // $FF: renamed from: d long
   private long field_267;
   // $FF: renamed from: f byte
   private byte field_268;
   // $FF: renamed from: l byte[]
   private byte[] field_269;
   // $FF: renamed from: d byte[]
   public byte[] field_270;
   // $FF: renamed from: e byte[]
   public byte[] field_271;
   // $FF: renamed from: b byte
   public byte field_272;
   // $FF: renamed from: a short
   private short field_273;
   // $FF: renamed from: b aeii.a[]
   private class_2[] field_274;
   // $FF: renamed from: a aeii.a[][]
   public class_2[][] field_275;
   // $FF: renamed from: a int[]
   public int[] field_276;
   // $FF: renamed from: b int[]
   public int[] field_277;
   // $FF: renamed from: d byte[][]
   private byte[][] field_278;
   // $FF: renamed from: m byte[]
   private byte[] field_279;
   // $FF: renamed from: a aeii.e
   private class_12 field_280;
   // $FF: renamed from: c java.util.Vector
   private Vector field_281;
   // $FF: renamed from: d java.util.Vector
   private Vector field_282;
   // $FF: renamed from: c aeii.a
   private class_2 field_283;
   // $FF: renamed from: d aeii.a
   private class_2 field_284;
   // $FF: renamed from: e long
   private long field_285;
   // $FF: renamed from: e aeii.a
   private class_2 field_286;
   // $FF: renamed from: g byte
   private byte field_287;
   // $FF: renamed from: f long
   private long field_288;
   // $FF: renamed from: E int
   private int field_289;
   // $FF: renamed from: e boolean
   private boolean field_290;
   // $FF: renamed from: f boolean
   private boolean field_291;
   // $FF: renamed from: h byte
   private byte field_292;
   // $FF: renamed from: b aeii.d
   private class_16 field_293;
   // $FF: renamed from: c aeii.d
   private class_16 field_294;
   // $FF: renamed from: d aeii.d
   private class_16 field_295;
   // $FF: renamed from: e aeii.d
   private class_16 field_296;
   // $FF: renamed from: F int
   private int field_297;
   // $FF: renamed from: g boolean
   private boolean field_298;
   // $FF: renamed from: G int
   private int field_299;
   // $FF: renamed from: H int
   private int field_300;
   // $FF: renamed from: I int
   private int field_301;
   // $FF: renamed from: g long
   private long field_302;
   // $FF: renamed from: f aeii.a
   private class_2 field_303;
   // $FF: renamed from: g aeii.a
   private class_2 field_304;
   // $FF: renamed from: h boolean
   private boolean field_305;
   // $FF: renamed from: h long
   private long field_306;
   // $FF: renamed from: i boolean
   private boolean field_307;
   // $FF: renamed from: i long
   private long field_308;
   // $FF: renamed from: J int
   private int field_309;
   // $FF: renamed from: K int
   private int field_310;
   // $FF: renamed from: e aeii.d[]
   private class_16[] field_311;
   // $FF: renamed from: j boolean
   private boolean field_312;
   // $FF: renamed from: k boolean
   private boolean field_313;
   // $FF: renamed from: i int[]
   private static int[] field_314;
   // $FF: renamed from: j int[]
   private static int[] field_315;
   // $FF: renamed from: L int
   private int field_316;
   // $FF: renamed from: b aeii.e
   private class_12 field_317;
   // $FF: renamed from: c aeii.e
   private class_12 field_318;
   // $FF: renamed from: d aeii.e
   private class_12 field_319;
   // $FF: renamed from: e aeii.e
   private class_12 field_320;
   // $FF: renamed from: n int
   public int field_321;
   // $FF: renamed from: f aeii.e
   private class_12 field_322;
   // $FF: renamed from: g aeii.e
   private class_12 field_323;
   // $FF: renamed from: h aeii.e
   private class_12 field_324;
   // $FF: renamed from: l boolean
   private boolean field_325;
   // $FF: renamed from: M int
   private int field_326;
   // $FF: renamed from: e byte[][]
   private byte[][] field_327;
   // $FF: renamed from: f byte[][]
   private byte[][] field_328;
   // $FF: renamed from: j a.a.a.g
   public class_1 field_329;
   // $FF: renamed from: k a.a.a.g
   public class_1 field_330;
   // $FF: renamed from: h aeii.a
   private class_2 field_331;
   // $FF: renamed from: l a.a.a.g
   public class_1 field_332;
   // $FF: renamed from: m a.a.a.g
   public class_1 field_333;
   // $FF: renamed from: N int
   private int field_334;
   // $FF: renamed from: O int
   private int field_335;
   // $FF: renamed from: P int
   private int field_336;
   // $FF: renamed from: m boolean
   private boolean field_337;
   // $FF: renamed from: Q int
   private int field_338;
   // $FF: renamed from: n boolean
   private boolean field_339;
   // $FF: renamed from: n a.a.a.g
   public class_1 field_340;
   // $FF: renamed from: b a.a.a.g[]
   private class_1[] field_341;
   // $FF: renamed from: o a.a.a.g
   public class_1 field_342;
   // $FF: renamed from: i aeii.e
   private class_12 field_343;
   // $FF: renamed from: j aeii.e
   private class_12 field_344;
   // $FF: renamed from: k aeii.e
   private class_12 field_345;
   // $FF: renamed from: l aeii.e
   private class_12 field_346;
   // $FF: renamed from: a aeii.e[]
   private class_12[] field_347;
   // $FF: renamed from: b aeii.e[]
   private class_12[] field_348;
   // $FF: renamed from: m aeii.e
   private class_12 field_349;
   // $FF: renamed from: c aeii.e[]
   private class_12[] field_350;
   // $FF: renamed from: t a.a.a.g
   private class_1 field_351;
   // $FF: renamed from: R int
   private int field_352;
   // $FF: renamed from: o boolean
   private boolean field_353;
   // $FF: renamed from: p boolean
   private boolean field_354;
   // $FF: renamed from: q boolean
   private boolean field_355;
   // $FF: renamed from: e java.util.Vector
   private Vector field_356;
   // $FF: renamed from: u a.a.a.g
   private class_1 field_357;
   // $FF: renamed from: S int
   private int field_358;
   // $FF: renamed from: a a.a.a.g[]
   public class_1[] field_359;
   // $FF: renamed from: n aeii.e
   private class_12 field_360;
   // $FF: renamed from: o aeii.e
   private class_12 field_361;
   // $FF: renamed from: p aeii.e
   private class_12 field_362;
   // $FF: renamed from: q aeii.e
   private class_12 field_363;
   // $FF: renamed from: p a.a.a.g
   public class_1 field_364;
   // $FF: renamed from: v a.a.a.g
   private class_1 field_365;
   // $FF: renamed from: i aeii.a
   private class_2 field_366;
   // $FF: renamed from: T int
   private int field_367;
   // $FF: renamed from: r boolean
   private boolean field_368;
   // $FF: renamed from: s boolean
   private boolean field_369;
   // $FF: renamed from: b java.lang.String
   private String field_370;
   // $FF: renamed from: k java.lang.String[]
   private String[] field_371;
   // $FF: renamed from: n byte[]
   private byte[] field_372;
   // $FF: renamed from: k int[]
   private int[] field_373;
   // $FF: renamed from: r aeii.e
   private class_12 field_374;
   // $FF: renamed from: s aeii.e
   private class_12 field_375;
   // $FF: renamed from: t aeii.e
   private class_12 field_376;
   // $FF: renamed from: u aeii.e
   private class_12 field_377;
   // $FF: renamed from: v aeii.e
   private class_12 field_378;
   // $FF: renamed from: w aeii.e
   private class_12 field_379;
   // $FF: renamed from: x aeii.e
   private class_12 field_380;
   // $FF: renamed from: i byte
   private byte field_381;
   // $FF: renamed from: o byte[]
   private byte[] field_382;
   // $FF: renamed from: U int
   private int field_383;
   // $FF: renamed from: l java.lang.String[]
   private String[] field_384;
   // $FF: renamed from: l int[]
   private int[] field_385;
   // $FF: renamed from: m java.lang.String[]
   private String[] field_386;
   // $FF: renamed from: n java.lang.String[]
   private String[] field_387;
   // $FF: renamed from: y aeii.e
   private class_12 field_388;
   // $FF: renamed from: z aeii.e
   private class_12 field_389;
   // $FF: renamed from: A aeii.e
   private class_12 field_390;
   // $FF: renamed from: B aeii.e
   private class_12 field_391;
   // $FF: renamed from: C aeii.e
   private class_12 field_392;
   // $FF: renamed from: D aeii.e
   private class_12 field_393;
   // $FF: renamed from: E aeii.e
   private class_12 field_394;
   // $FF: renamed from: F aeii.e
   private class_12 field_395;
   // $FF: renamed from: V int
   private int field_396;
   // $FF: renamed from: c java.lang.String
   private String field_397;
   // $FF: renamed from: W int
   private int field_398;
   // $FF: renamed from: t boolean
   private boolean field_399;
   // $FF: renamed from: u boolean
   private boolean field_400;
   // $FF: renamed from: v boolean
   private boolean field_401;
   // $FF: renamed from: f aeii.d
   private class_16 field_402;
   // $FF: renamed from: a javax.microedition.lcdui.Image[][]
   private Image[][] field_403;
   // $FF: renamed from: X int
   private int field_404;
   // $FF: renamed from: j aeii.a
   private class_2 field_405;
   // $FF: renamed from: Y int
   private int field_406;
   // $FF: renamed from: Z int
   private int field_407;
   // $FF: renamed from: w boolean
   private boolean field_408;
   // $FF: renamed from: aa int
   private int field_409;
   // $FF: renamed from: ab int
   private int field_410;
   // $FF: renamed from: ac int
   private int field_411;
   // $FF: renamed from: ad int
   private int field_412;
   // $FF: renamed from: ae int
   private int field_413;
   // $FF: renamed from: g aeii.d
   private class_16 field_414;
   // $FF: renamed from: o java.lang.String[]
   private String[] field_415;
   // $FF: renamed from: x boolean
   private boolean field_416;
   // $FF: renamed from: j byte
   private byte field_417;
   // $FF: renamed from: af int
   private int field_418;
   // $FF: renamed from: ag int
   private int field_419;
   // $FF: renamed from: ah int
   private int field_420;
   // $FF: renamed from: ai int
   private int field_421;
   // $FF: renamed from: aj int
   private int field_422;
   // $FF: renamed from: ak int
   private int field_423;
   // $FF: renamed from: al int
   private int field_424;
   // $FF: renamed from: am int
   private int field_425;
   // $FF: renamed from: an int
   private int field_426;
   // $FF: renamed from: p byte[]
   private static byte[] field_427;
   // $FF: renamed from: k byte
   private byte field_428;
   // $FF: renamed from: ao int
   private int field_429;
   // $FF: renamed from: ap int
   private int field_430;
   // $FF: renamed from: k aeii.a
   private class_2 field_431;
   // $FF: renamed from: l aeii.a
   private class_2 field_432;
   // $FF: renamed from: m aeii.a
   private class_2 field_433;
   // $FF: renamed from: aq int
   private int field_434;
   // $FF: renamed from: j long
   private long field_435;
   // $FF: renamed from: c aeii.a[]
   private class_2[] field_436;
   // $FF: renamed from: d aeii.a[]
   private class_2[] field_437;
   // $FF: renamed from: q byte[]
   private byte[] field_438;
   // $FF: renamed from: a int[][]
   private int[][] field_439;
   // $FF: renamed from: m int[]
   private int[] field_440;
   // $FF: renamed from: ar int
   private int field_441;
   // $FF: renamed from: g byte[][]
   private byte[][] field_442;
   // $FF: renamed from: as int
   private int field_443;
   // $FF: renamed from: at int
   private int field_444;
   // $FF: renamed from: au int
   private int field_445;
   // $FF: renamed from: av int
   private int field_446;
   // $FF: renamed from: aw int
   private int field_447;
   // $FF: renamed from: f java.util.Vector
   private Vector field_448;
   // $FF: renamed from: y boolean
   private boolean field_449;
   // $FF: renamed from: ax int
   private int field_450;
   // $FF: renamed from: G aeii.e
   private class_12 field_451;
   // $FF: renamed from: n aeii.a
   private class_2 field_452;
   // $FF: renamed from: o aeii.a
   private class_2 field_453;
   // $FF: renamed from: p aeii.a
   private class_2 field_454;
   // $FF: renamed from: q aeii.a
   private class_2 field_455;
   // $FF: renamed from: r aeii.a
   private class_2 field_456;
   // $FF: renamed from: a aeii.a
   public class_2 field_457;
   // $FF: renamed from: ay int
   private int field_458;
   // $FF: renamed from: a java.lang.String[][][]
   private String[][][] field_459;
   // $FF: renamed from: k long
   private long field_460;
   // $FF: renamed from: az int
   private int field_461;
   // $FF: renamed from: z boolean
   private boolean field_462;
   // $FF: renamed from: A boolean
   private boolean field_463;
   // $FF: renamed from: aA int
   private int field_464;
   // $FF: renamed from: aB int
   private int field_465;
   // $FF: renamed from: aC int
   private int field_466;
   // $FF: renamed from: a aeii.h
   private class_14 field_467;
   // $FF: renamed from: b aeii.h
   private class_14 field_468;
   // $FF: renamed from: l long
   private long field_469;
   // $FF: renamed from: B boolean
   private boolean field_470;
   // $FF: renamed from: g java.util.Vector
   private Vector field_471;
   // $FF: renamed from: C boolean
   private boolean field_472;
   // $FF: renamed from: m long
   private long field_473;
   // $FF: renamed from: n long
   private long field_474;
   // $FF: renamed from: D boolean
   private boolean field_475;
   // $FF: renamed from: E boolean
   private boolean field_476;
   // $FF: renamed from: F boolean
   private boolean field_477;
   // $FF: renamed from: a boolean
   public boolean field_478;
   // $FF: renamed from: o int
   public int field_479;
   // $FF: renamed from: aD int
   private int field_480;
   // $FF: renamed from: aE int
   private int field_481;
   // $FF: renamed from: p java.lang.String[]
   private String[] field_482;
   // $FF: renamed from: q java.lang.String[]
   private String[] field_483;
   // $FF: renamed from: r java.lang.String[]
   private String[] field_484;
   // $FF: renamed from: s java.lang.String[]
   private String[] field_485;
   // $FF: renamed from: t java.lang.String[]
   private String[] field_486;
   // $FF: renamed from: n int[]
   private int[] field_487;
   // $FF: renamed from: d java.lang.String
   private String field_488;
   // $FF: renamed from: e java.lang.String
   private String field_489;
   // $FF: renamed from: f java.lang.String
   private String field_490;
   // $FF: renamed from: a java.io.ByteArrayOutputStream
   private ByteArrayOutputStream field_491;
   // $FF: renamed from: a java.io.DataOutputStream
   private DataOutputStream field_492;
   // $FF: renamed from: aF int
   private int field_493;
   // $FF: renamed from: G boolean
   private boolean field_494;
   // $FF: renamed from: a aeii.f
   private class_11 field_495;
   // $FF: renamed from: aG int
   private int field_496;
   // $FF: renamed from: H aeii.e
   private class_12 field_497;
   // $FF: renamed from: I aeii.e
   private class_12 field_498;
   // $FF: renamed from: J aeii.e
   private class_12 field_499;
   // $FF: renamed from: K aeii.e
   private class_12 field_500;
   // $FF: renamed from: a aeii.b
   private class_10 field_501;
   // $FF: renamed from: u java.lang.String[]
   private String[] field_502;
   // $FF: renamed from: l byte
   private byte field_503;
   // $FF: renamed from: m byte
   private byte field_504;
   // $FF: renamed from: s aeii.a
   private class_2 field_505;
   // $FF: renamed from: n byte
   private byte field_506;
   // $FF: renamed from: L aeii.e
   private class_12 field_507;
   // $FF: renamed from: M aeii.e
   private class_12 field_508;
   // $FF: renamed from: N aeii.e
   private class_12 field_509;
   // $FF: renamed from: O aeii.e
   private class_12 field_510;
   // $FF: renamed from: o int[]
   private int[] field_511;
   // $FF: renamed from: p int[]
   private int[] field_512;
   // $FF: renamed from: b boolean[]
   private boolean[] field_513;
   // $FF: renamed from: a javax.microedition.lcdui.Form
   private Form field_514;
   // $FF: renamed from: b javax.microedition.lcdui.Form
   private Form field_515;
   // $FF: renamed from: c javax.microedition.lcdui.Form
   private Form field_516;
   // $FF: renamed from: d javax.microedition.lcdui.Form
   private Form field_517;
   // $FF: renamed from: e javax.microedition.lcdui.Form
   private Form field_518;
   // $FF: renamed from: f javax.microedition.lcdui.Form
   private Form field_519;
   // $FF: renamed from: a javax.microedition.lcdui.TextField
   private TextField field_520;
   // $FF: renamed from: b javax.microedition.lcdui.TextField
   private TextField field_521;
   // $FF: renamed from: c javax.microedition.lcdui.TextField
   private TextField field_522;
   // $FF: renamed from: a javax.microedition.lcdui.ChoiceGroup
   private ChoiceGroup field_523;
   // $FF: renamed from: d javax.microedition.lcdui.TextField
   private TextField field_524;
   // $FF: renamed from: a javax.microedition.lcdui.Gauge
   private Gauge field_525;
   // $FF: renamed from: a javax.microedition.lcdui.ImageItem
   private ImageItem field_526;
   // $FF: renamed from: e javax.microedition.lcdui.TextField
   private TextField field_527;
   // $FF: renamed from: f javax.microedition.lcdui.TextField
   private TextField field_528;
   // $FF: renamed from: g javax.microedition.lcdui.TextField
   private TextField field_529;
   // $FF: renamed from: h javax.microedition.lcdui.TextField
   private TextField field_530;
   // $FF: renamed from: a javax.microedition.lcdui.TextBox
   private TextBox field_531;
   // $FF: renamed from: b javax.microedition.lcdui.Gauge
   private Gauge field_532;
   // $FF: renamed from: a javax.microedition.lcdui.Command
   private Command field_533;
   // $FF: renamed from: b javax.microedition.lcdui.Command
   private Command field_534;
   // $FF: renamed from: c javax.microedition.lcdui.Command
   private Command field_535;
   // $FF: renamed from: d javax.microedition.lcdui.Command
   private Command field_536;
   // $FF: renamed from: e javax.microedition.lcdui.Command
   private Command field_537;
   // $FF: renamed from: f javax.microedition.lcdui.Command
   private Command field_538;

   public class_13() {
      super();
      field_181 = new String[]{".."};
      field_182 = new String[]{class_11.method_79(300), ".."};
      field_183 = new String[]{class_11.method_79(305), class_11.method_79(306), class_11.method_79(307), class_11.method_79(303), class_11.method_79(70), class_11.method_79(293), class_11.method_79(60)};
      this.field_506 = 0;
      this.field_533 = new Command(class_11.method_79(12), 4, 1);
      this.field_534 = new Command(class_11.method_79(13), 3, 5);
      this.field_535 = new Command(class_11.method_79(301), 4, 3);
      this.field_536 = new Command(class_11.method_79(302), 4, 4);
      this.field_537 = new Command(class_11.method_79(304), 4, 2);
      this.field_538 = new Command(class_11.method_79(316), 4, 2);
      if (class_15.field_588) {
         this.field_201 = new String[]{class_11.method_79(66), class_11.method_79(70), class_11.method_79(71), class_11.method_79(4), class_11.method_79(314), class_11.method_79(60)};
      } else {
         this.field_201 = new String[]{class_11.method_79(66), class_11.method_79(70), class_11.method_79(71), class_11.method_79(4), class_11.method_79(60)};
      }

      this.field_202 = new String[]{class_11.method_79(63), class_11.method_79(67), class_11.method_79(68), class_11.method_79(62), class_11.method_79(69), class_11.method_79(61), class_11.method_79(64)};
      this.field_203 = new class_16[this.field_202.length];
      this.field_204 = new String[]{class_11.method_79(1), class_11.method_79(2), class_11.method_79(5), class_11.method_79(3), class_11.method_79(6), class_11.method_79(8), class_11.method_79(7), class_11.method_79(9), class_11.method_79(10), class_11.method_79(11), class_11.method_79(4)};
      this.field_205 = new class_16[this.field_204.length];
      this.field_206 = new String[]{class_11.method_79(35), class_11.method_79(36), class_11.method_79(37)};
      this.field_207 = new String[]{class_11.method_79(29), class_11.method_79(30)};
      this.field_208 = new byte[]{0, 6, 5, 7, 8, 9};
      this.field_209 = new byte[]{0, 6, 5, 7, 8, 9};
      this.field_210 = new byte[]{1, 2, 3, 4};
      this.field_213 = 0L;
      this.field_260 = false;
      this.field_261 = false;
      this.field_262 = true;
      this.field_263 = new Vector();
      this.field_268 = 2;
      this.field_269 = new byte[5];
      this.field_270 = new byte[4];
      this.field_271 = new byte[4];
      this.field_272 = 0;
      this.field_277 = new int[4];
      this.field_278 = new byte[4][2];
      this.field_279 = new byte[4];
      this.field_281 = new Vector();
      this.field_282 = new Vector();
      this.field_290 = false;
      this.field_291 = false;
      this.field_298 = false;
      this.field_305 = true;
      this.field_307 = false;
      this.field_312 = false;
      this.field_316 = -1;
      this.field_321 = 8;
      this.field_325 = false;
      this.field_337 = true;
      this.field_341 = new class_1[0];
      this.field_356 = new Vector(2);
      this.field_367 = 0;
      this.field_368 = true;
      this.field_386 = new String[]{class_11.method_79(46), class_11.method_79(47), class_11.method_79(291)};
      this.field_387 = new String[]{class_11.method_79(289), class_11.method_79(298), class_11.method_79(48), class_11.method_79(49)};
      this.field_399 = true;
      this.field_400 = true;
      this.field_401 = true;
      new StringBuffer();
      this.field_405 = null;
      this.field_406 = 12;
      this.field_407 = 1;
      this.field_421 = 0;
      this.field_423 = 24;
      this.field_424 = 8;
      this.field_425 = this.field_424 >> 1;
      this.field_428 = 0;
      this.field_449 = false;
      this.field_456 = null;
      this.field_457 = null;
      this.field_462 = false;
      this.field_463 = false;
      this.field_464 = -1;
      this.field_465 = -1;
      this.field_466 = 0;
      this.field_471 = new Vector();
      this.field_472 = false;
      this.field_488 = "Macrospace";
      this.field_489 = "msaeii";
      this.field_490 = "http://msaeii.scores.macrospace.com/connectx/in";
      this.field_292 = 4;
   }

   // $FF: renamed from: d () void
   public final void method_108() {
      this.method_109(0);
      class_15.method_274();
      this.method_109(18);
      class_15.method_268();

      for(int var1 = 0; var1 < class_15.field_619.length; ++var1) {
         class_15.method_269(var1);
         this.method_109(var1 + 19);
      }

      this.method_109(28);
      class_12.field_108 = this;
      this.method_109(29);
      class_11.method_84();
      this.method_109(30);
      class_15.method_265();
      this.method_109(32);
      this.field_203 = (new class_1("action_icons")).field_0;
      this.method_109(34);
      this.field_205 = (new class_1("menu_icons")).field_0;
      this.method_109(36);
      this.field_332 = new class_1("hud_icons");
      this.method_109(38);
      this.field_333 = new class_1("hud_icons_2");
      this.method_109(40);
      this.field_237 = new class_1("arrow");
      this.method_109(42);
      this.field_236 = new class_1("side_arrow");
      this.method_109(44);
      this.field_238 = new class_1("buttons");
      this.method_109(46);
      this.field_239 = new class_1("menu");
      this.method_109(48);
      this.field_329 = new class_1("big_circle");
      this.method_109(50);
      this.field_330 = new class_1("small_circle");
      this.method_109(52);
      this.field_244 = new class_1("small_spark");
      this.method_109(54);
      this.field_351 = new class_1("alpha");
      this.method_109(56);

      try {
         this.field_402 = new class_16("gameover");
      } catch (Exception var9) {
      }

      this.method_109(58);
      this.field_293 = new class_16("ms_logo");
      this.method_109(62);
      InputStream var11;
      if ((var11 = class_15.method_276("tiles0.prop")) == null) {
         DataInputStream var12;
         short var15 = (var12 = new DataInputStream(class_15.method_276("tiles0.bin"))).readShort();
         var12.readShort();
         this.field_224 = new byte[var15];

         for(int var20 = 0; var20 < var15; ++var20) {
            this.field_224[var20] = var12.readByte();
         }

         var12.close();
      } else {
         String var3;
         while((var3 = class_15.method_238(var11)) != null) {
            int var4;
            if ((var4 = var3.indexOf(59)) >= 0) {
               var3 = var3.substring(0, var4);
            }

            if ((var3 = var3.trim()).length() != 0) {
               String[] var2;
               if ((var2 = class_15.method_239(var3, ' '))[0].equalsIgnoreCase("TypeCount")) {
                  field_222 = new byte[var4 = Integer.parseInt(var2[1])];
                  field_221 = new byte[var4];
                  class_14.field_539 = new String[var4];
                  class_14.field_540 = new String[var4];
               } else if (var2[0].equalsIgnoreCase("TypeDef")) {
                  var4 = Integer.parseInt(var2[1]);
                  field_222[var4] = (byte)Integer.parseInt(var2[2]);
                  field_221[var4] = (byte)Integer.parseInt(var2[3]);
                  class_14.field_539[var4] = var2[4];
                  class_14.field_540[var4] = var2[5];
               } else if (var2[0].equalsIgnoreCase("TileCount")) {
                  this.field_224 = new byte[Integer.parseInt(var2[1])];
                  this.field_225 = new byte[this.field_224.length];
               } else if (var2[0].equalsIgnoreCase("TileDef")) {
                  var4 = Integer.parseInt(var2[1]);
                  this.field_224[var4] = (byte)Integer.parseInt(var2[2]);
                  this.field_225[var4] = (byte)Integer.parseInt(var2[3]);
               }
            }
         }

         var11.close();
      }

      this.method_109(64);
      class_1 var16 = new class_1("stiles0");
      this.field_223 = var16.field_0;
      this.method_109(70);
      this.field_340 = new class_1("mini_icons");
      this.method_109(72);
      this.field_233 = (new class_1("tiles0")).field_0;
      this.field_186 = super.field_93;
      this.field_187 = super.field_94;
      this.field_188 = this.field_186 >> 1;
      this.field_189 = this.field_187 >> 1;
      this.field_297 = 0;

      for(int var21 = 0; var21 < 12; ++var21) {
         field_191[var21] = class_11.method_79(var21 + 101);
      }

      method_115();
      this.method_109(74);

      try {
         this.field_190 = class_15.method_240("settings", 1)[0];
      } catch (Exception var8) {
      }

      this.method_109(76);
      this.field_384 = new String[0];
      this.field_385 = new int[0];

      try {
         byte[] var27 = class_15.method_240("settings", 2);
         DataInputStream var13 = new DataInputStream(new ByteArrayInputStream(var27));
         this.field_383 = var13.readInt();
         this.field_385 = new int[this.field_383];
         this.field_384 = new String[this.field_383];

         for(int var17 = 0; var17 < this.field_383; ++var17) {
            this.field_385[var17] = var13.readInt();
            this.field_384[var17] = var13.readUTF();
         }

         var13.close();
      } catch (Exception var10) {
      }

      this.method_109(80);
      this.field_398 = class_15.method_244("download");
      this.method_109(84);
      this.field_371 = new String[10];
      this.field_372 = new byte[10];
      this.field_373 = new int[10];

      for(int var22 = 0; var22 < 10; ++var22) {
         this.field_372[var22] = -1;
         this.field_373[var22] = -1;
         byte[] var28 = null;

         try {
            var28 = class_15.method_240("save", var22);
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
            this.field_372[var22] = var5;
            this.field_371[var22] = this.method_110(var18, var29, var6);
            this.field_373[var22] = var29;
         } else {
            this.field_371[var22] = "\n" + class_11.method_79(79) + "\n ";
         }
      }

      this.method_109(90);
      int var23 = 0;
      String var30;
      if ((var30 = Main.field_628.getAppProperty("ProvisionX-Highscore-gameCode")) != null) {
         this.field_489 = var30.trim();
      }

      if ((var30 = Main.field_628.getAppProperty("ProvisionX-Highscore-portalCode")) != null) {
         this.field_488 = var30.trim();
      }

      if ((var30 = Main.field_628.getAppProperty("ProvisionX-Highscore-Url")) != null) {
         this.field_490 = var30.trim();
      }

      if ((var30 = Main.field_628.getAppProperty("ms-highscoreUpload")) != null) {
         this.field_399 = Integer.parseInt(var30.trim()) == 1;
      }

      if ((var30 = Main.field_628.getAppProperty("ms-skPos")) != null) {
         var23 = Integer.parseInt(var30.trim());
      }

      if ((var30 = Main.field_628.getAppProperty("MIDlet-Version")) != null) {
         this.field_184 = var30.trim();
      }

      this.method_109(96);
      if (var23 == 1) {
         field_199 = 2048;
         field_200 = 1024;
      }

      this.method_109(100);
      class_15.method_272(0, 0);
      this.field_292 = 0;
   }

   // $FF: renamed from: b (int) void
   private void method_109(int var1) {
      this.field_404 = var1;
      class_11.field_92.method_254();
   }

   // $FF: renamed from: a (int, int, int) java.lang.String
   private String method_110(int var1, int var2, int var3) {
      String var4;
      if (var1 == 0) {
         var4 = class_11.method_79(var2 + 121);
      } else {
         var4 = this.method_130(var2);
      }

      return class_11.method_79(var1 + 32) + "\n" + var4 + "\n" + "Current turn: " + (var3 + 1);
   }

   // $FF: renamed from: a () boolean
   private boolean method_111() {
      return this.field_337 && class_11.field_92.field_603 == this;
   }

   // $FF: renamed from: e () void
   private void method_112() {
      this.field_293 = null;
      this.field_294 = null;
      this.field_295 = null;
      this.field_296 = null;
      System.gc();
      this.field_187 = super.field_94 - field_185;
      this.field_189 = this.field_187 >> 1;
      class_15.method_271();
      if (this.field_292 != 1) {
         this.field_292 = 1;
         class_2.method_60(this);
         class_15.method_274();
         this.field_219 = new class_1[8][12];

         for(byte var1 = 0; var1 < 8; ++var1) {
            class_1 var4;
            int var5 = (var4 = new class_1("unit_icons", var1)).method_9() / 12;

            for(byte var2 = 0; var2 < 12; ++var2) {
               if ((var1 & 1) == 1) {
                  class_16[] var9 = new class_16[]{var4.field_0[var2]};
                  this.field_219[var1][var2] = new class_1(var9);
               } else {
                  class_16[] var3 = new class_16[var5];

                  for(int var6 = 0; var6 < var5; ++var6) {
                     var3[var6] = var4.field_0[var6 * 12 + var2];
                  }

                  this.field_219[var1][var2] = new class_1(var3);
               }
            }
         }

         this.field_403 = new Image[2][this.field_233.length];

         for(byte var7 = 0; var7 < 2; ++var7) {
            for(byte var8 = 0; var8 < this.field_233.length; ++var8) {
               this.field_403[var7][var8] = Image.createImage(24, 24);
               Graphics var10 = this.field_403[var7][var8].getGraphics();
               this.field_233[var8].method_281(var10, 0, 0);
               this.field_351.method_15(var10, var7, 0, 0, 0);
            }
         }

         this.field_245 = new class_1("portraits");
         this.field_234 = new class_1("cursor");
         this.field_242 = new class_1("redspark");
         this.field_240 = new class_1("smoke");
         this.field_241 = new class_1("spark");
         this.field_243 = new class_1("status");
         this.field_364 = new class_1("arrow_icons");
         this.field_232 = new class_16("tombstone");
         this.field_357 = new class_1("levelup");
         this.field_359 = new class_1[2];
         this.field_359[0] = new class_1("king_head_icons");
         this.field_359[1] = new class_1("king_head_icons", (byte)0);
         this.field_234.method_13(field_211[0]);
         this.field_235 = new class_1(this.field_234);
         this.field_235.method_13(field_211[3]);
         this.field_311 = new class_16[2];
         this.field_310 = field_220[0];
         this.field_311[0] = this.field_233[field_220[0]];
         this.field_311[1] = this.field_233[field_220[1]];
         this.field_342 = new class_1("b_smoke");
      }

   }

   // $FF: renamed from: a () void
   public final void method_72() {
      this.field_400 = true;
      this.field_401 = true;
      this.field_478 = true;
      this.field_477 = true;
   }

   // $FF: renamed from: a (byte[]) void
   private void method_113(byte[] var1) {
      DataInputStream var15 = new DataInputStream(new ByteArrayInputStream(var1));
      this.field_198 = var15.readByte();
      this.field_252 = var15.readByte();
      this.field_268 = var15.readByte();
      this.method_137(this.field_252);
      this.field_272 = var15.readByte();
      var15.readByte();
      this.field_273 = var15.readShort();
      this.field_321 = var15.readByte();
      this.field_268 = var15.readByte();

      for(int var2 = 0; var2 < this.field_268; ++var2) {
         this.field_271[var2] = var15.readByte();
         this.field_279[var2] = var15.readByte();
         this.field_277[var2] = var15.readInt();
         this.field_278[var2][0] = var15.readByte();
         this.field_278[var2][1] = var15.readByte();
      }

      this.field_197 = var15.readShort();

      for(int var16 = 0; var16 < this.field_327.length; ++var16) {
         this.field_248[this.field_327[var16][0]][this.field_327[var16][1]] = var15.readByte();
      }

      this.field_275 = new class_2[this.field_268][4];
      this.field_276 = new int[this.field_268];
      this.method_136();
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
         class_2 var20;
         (var20 = class_2.method_24(var4, var5, var11, var12)).field_55 = (byte)var6;
         var20.field_39 = var10;
         var20.method_22(var9);
         var20.field_56 = var7;
         var20.method_40();
         var20.field_54 = (short)var8;
         var20.field_64 = var13;
         var20.field_65 = var14;
         if (var4 == 9) {
            var4 = var15.readByte();
            var6 = var15.readShort();
            var20.method_28(var4);
            var20.field_70 = var6;
            if (var15.readByte() == 1) {
               this.field_274[var20.field_45] = var20;
            }
         }
      }

      this.field_458 = var15.readShort();
      this.field_460 = (long)var15.readInt();
      this.field_461 = var15.readInt();
      this.field_462 = var15.readByte() != 0;
      var15.close();
      if (this.field_252 == 6 && this.field_458 > 32) {
         this.field_320 = this.method_197(class_11.method_79(121 + this.field_252), class_11.method_79(138), this.field_187, -1);
         this.field_320.method_87((byte)0, true);
         this.field_320.method_88((class_11)null);
      }

      this.method_156(this.field_278[this.field_272][0], this.field_278[this.field_272][1]);
      this.method_154(this.field_278[this.field_272][0], this.field_278[this.field_272][1]);
      class_15.method_270(field_217[this.field_270[this.field_272]], 0);
   }

   // $FF: renamed from: a (int, aeii.f) void
   private void method_114(int var1, class_11 var2) {
      try {
         class_13 var3 = this;
         this.field_278[this.field_272][0] = (byte)this.field_246;
         this.field_278[this.field_272][1] = (byte)this.field_247;
         ByteArrayOutputStream var4 = new ByteArrayOutputStream();
         DataOutputStream var5;
         (var5 = new DataOutputStream(var4)).writeByte(this.field_198);
         var5.writeByte(this.field_252);
         var5.writeByte(this.field_268);
         var5.writeByte(this.field_272);
         var5.writeByte(this.field_270[this.field_272]);
         var5.writeShort(this.field_273);
         var5.writeByte(this.field_321);
         var5.writeByte(this.field_268);

         for(int var6 = 0; var6 < var3.field_268; ++var6) {
            var5.writeByte(var3.field_271[var6]);
            var5.writeByte(var3.field_279[var6]);
            var5.writeInt(var3.field_277[var6]);
            var5.writeByte(var3.field_278[var6][0]);
            var5.writeByte(var3.field_278[var6][1]);
         }

         var5.writeShort(var3.field_197);

         for(int var11 = 0; var11 < var3.field_327.length; ++var11) {
            var5.writeByte(var3.field_248[var3.field_327[var11][0]][var3.field_327[var11][1]]);
         }

         var5.writeByte(var3.field_263.size());
         int var12 = 0;

         for(int var7 = var3.field_263.size(); var12 < var7; ++var12) {
            class_2 var8 = (class_2)var3.field_263.elementAt(var12);
            var5.writeByte(var8.field_44);
            var5.writeByte(var8.field_45);
            var5.writeByte(var8.field_55);
            var5.writeByte(var8.field_56);
            var5.writeByte(var8.field_54);
            var5.writeByte(var8.field_38);
            var5.writeShort(var8.field_39);
            var5.writeShort(var8.field_46);
            var5.writeShort(var8.field_47);
            var5.writeByte(var8.field_64);
            var5.writeByte(var8.field_65);
            if (var8.field_44 == 9) {
               var5.writeByte(var8.field_68);
               var5.writeShort(var8.field_70);
               var5.writeByte(var3.field_274[var8.field_45] == var8 ? 1 : 0);
            }
         }

         var5.writeShort((short)var3.field_458);
         var5.writeInt((short)((int)var3.field_460));
         var5.writeInt(var3.field_461);
         var5.writeByte(var3.field_462 ? 0 : 1);
         byte[] var13 = var4.toByteArray();
         var5.close();
         class_15.method_241("save", var1, var13);
         this.field_371[var1] = this.method_110(this.field_198, this.field_252, this.field_273);
         this.field_372[var1] = this.field_270[this.field_272];
         this.field_373[var1] = this.field_252;
         this.field_377.method_95((String)null, this.field_371[var1], this.field_186, -1);
         this.field_377.field_158 = field_216[this.field_372[var1]];
         this.field_377.method_72();
         class_12 var10;
         (var10 = this.method_197((String)null, class_11.method_79(77), this.field_187, 1000)).field_141 = var2;
         class_11.field_92.method_253(var10);
      } catch (Exception var9) {
      }
   }

   // $FF: renamed from: f () void
   private static void method_115() {
      try {
         byte[] var0 = class_15.method_240("settings", 0);

         for(int var1 = 0; var1 < 4; ++var1) {
            class_15.field_612[var1] = (var0[0] & 1 << var1) != 0;
         }

      } catch (Exception var2) {
      }
   }

   // $FF: renamed from: g () void
   private static void method_116() {
      try {
         byte[] var0 = new byte[1];

         for(int var1 = 0; var1 < 4; ++var1) {
            if (class_15.field_612[var1]) {
               var0[0] = (byte)(var0[0] | 1 << var1);
            }
         }

         class_15.method_241("settings", 0, var0);
      } catch (Exception var2) {
      }
   }

   // $FF: renamed from: a (aeii.a) void
   private void method_117(class_2 var1) {
      this.field_367 = 0;
      this.method_156(var1.field_46, var1.field_47);
      this.field_366 = var1;
   }

   // $FF: renamed from: a (aeii.a, aeii.a) void
   private void method_118(class_2 var1, class_2 var2) {
      if (var2.field_55 == 4) {
         this.field_284 = var2;
         this.method_120(this.field_242, this.field_284.m, this.field_284.n, 0, 0, 1, 50);
         this.field_289 = 6;
         this.field_256.method_56();
         this.field_249 = 0;
         this.method_142();
         this.field_234.method_13(field_211[0]);
         if (this.field_279[this.field_272] == 0) {
            this.field_435 = this.field_251;
            this.field_428 = 6;
            return;
         }
      } else {
         if (class_15.field_612[3] && var2.field_40.length > 0) {
            this.field_298 = true;
            this.field_299 = 0;
            class_15.method_271();
         } else {
            this.field_249 = 13;
            this.field_301 = 0;
            this.field_234.method_13(field_211[0]);
         }

         this.field_303 = var1;
         this.field_304 = var2;
      }

   }

   // $FF: renamed from: h () void
   private void method_119() {
      this.method_142();
      if (this.field_303.field_54 <= 0) {
         this.field_283 = this.field_303;
      } else if (this.field_303.method_36()) {
         this.field_356.addElement(this.field_303);
      }

      if (this.field_304.field_54 <= 0) {
         this.field_283 = this.field_304;
      } else {
         if (this.field_303.method_55((short)128)) {
            this.method_120(this.field_241, this.field_304.m, this.field_304.n, 0, 0, 1, 50);
            class_1 var1;
            (var1 = class_1.method_19(this.field_243, 0, 0, -4, -1, 800, (byte)5)).method_11(this.field_304.m + (this.field_304.o - var1.field_6) / 2, this.field_304.n - var1.field_7);
            var1.method_13(field_212);
            this.field_281.addElement(var1);
            this.field_304.method_38((byte)1);
         }

         if (this.field_304.method_36()) {
            this.field_356.addElement(this.field_304);
         }
      }

      if (this.field_283 != null) {
         this.method_156(this.field_283.field_46, this.field_283.field_47);
         this.method_120(this.field_241, this.field_283.m, this.field_283.n, 0, 0, 1, 50);
         class_15.method_272(12, 1);
      }

      this.field_285 = this.field_251;
      if (this.field_279[this.field_272] == 0) {
         this.field_435 = this.field_251;
         this.field_428 = 6;
      }

      this.field_234.method_13(field_211[0]);
      this.field_249 = 0;
      this.field_303.method_56();
      this.field_304 = null;
      this.field_303 = null;
   }

   // $FF: renamed from: a (a.a.a.g, int, int, int, int, int, int) a.a.a.g
   public final class_1 method_120(class_1 var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      class_1 var8;
      (var8 = class_1.method_19(var1, var4, var5, 0, var6, var7, (byte)0)).method_11(var2, var3);
      this.field_282.addElement(var8);
      return var8;
   }

   // $FF: renamed from: b (aeii.a) void
   private void method_121(class_2 var1) {
      this.field_291 = true;
      this.field_290 = !this.field_325;
      this.field_409 = 12;
      this.field_249 = 1;
      this.field_307 = true;
      this.method_143(this.field_259, 0);
      var1.method_51(this.field_259);
      this.field_260 = true;
      this.field_261 = false;
      this.field_234.method_13(field_211[2]);
   }

   // $FF: renamed from: a (byte[], int, int, aeii.f) void
   private void method_122(byte[] var1, int var2, int var3, class_11 var4) {
      class_12 var5 = new class_12((byte)0, 0);
      this.field_334 = var2;
      this.field_335 = var3;
      var2 = var1.length;
      Vector var10 = new Vector(var2);
      Vector var6 = new Vector(var2);

      for(int var7 = 0; var7 < var2; ++var7) {
         byte var8 = var1[var7];
         if (this.field_399 || var8 != 6) {
            var10.addElement(this.field_204[var8]);
            var6.addElement(this.field_205[var8]);
         }
      }

      String[] var11 = new String[var10.size()];
      class_16[] var12 = new class_16[var6.size()];
      var10.copyInto(var11);
      var6.copyInto(var12);
      var5.method_101(var11, var12, super.field_95, this.field_334, this.field_335, 3, (byte)1);
      var5.method_88(var4);
      class_11.field_92.method_253(var5);
   }

   // $FF: renamed from: b (byte[]) void
   private void method_123(byte[] var1) {
      this.field_280 = new class_12((byte)0, 0);
      int var2;
      String[] var3 = new String[var2 = var1.length];
      class_16[] var5 = new class_16[var2];

      for(int var4 = 0; var4 < var1.length; ++var4) {
         var3[var4] = this.field_202[var1[var4]];
         var5[var4] = this.field_203[var1[var4]];
      }

      if (this.field_247 * 24 <= this.field_187 / 2 - 24) {
         this.field_280.method_98(var3, var5, 0, this.field_187 - this.field_238.field_7, 36);
      } else {
         this.field_280.method_98(var3, var5, this.field_186, 0, 8);
      }

      this.field_280.method_88(this);
      class_11.field_92.method_253(this.field_280);
   }

   // $FF: renamed from: a (java.lang.String, aeii.d) aeii.e
   private class_12 method_124(String var1, class_16 var2) {
      String[] var3 = new String[10];

      for(int var4 = 0; var4 < 10; ++var4) {
         var3[var4] = "SLOT " + (var4 + 1) + "/" + 10;
      }

      this.field_376 = new class_12((byte)14, 0);
      this.field_376.method_99(var3, this.field_186, -1);
      this.field_377 = new class_12((byte)10, 0);
      this.field_377.method_95((String)null, this.field_371[0], this.field_186, -1);
      if (this.field_372[0] != -1) {
         this.field_377.field_158 = field_216[this.field_372[0]];
      }

      class_12 var9 = new class_12((byte)15, 15);
      int var6 = (this.field_187 - this.field_376.field_114 - this.field_377.field_114) / 2;
      class_12 var5;
      (var5 = new class_12((byte)10, 0)).method_95((String)null, var1, this.field_186, -1);
      var5.field_160 = var2;
      var9.method_90(var5, 0, 0, 0);
      var6 += var5.field_114 / 2;
      var9.method_90(this.field_376, 0, var6, 0);
      var6 += this.field_376.field_114;
      var9.method_90(this.field_377, 0, var6, 20);
      var9.field_149 = true;
      var9.method_87((byte)0, true);
      return var9;
   }

   // $FF: renamed from: a (aeii.f) void
   private void method_125(class_11 var1) {
      int var2;
      String[] var3 = new String[var2 = this.field_485.length];
      int[] var4 = new int[var2];
      int var5 = 0;

      for(int var6 = 0; var6 < var2; ++var6) {
         boolean var7 = false;

         for(int var8 = 0; var8 < this.field_383; ++var8) {
            if (this.field_485[var6].equals(this.field_384[var8])) {
               var7 = true;
               break;
            }
         }

         if (!var7) {
            var3[var5] = this.field_485[var6];
            var4[var5] = var6;
            ++var5;
         }
      }

      this.field_393 = new class_12((byte)15, 15);
      class_12 var13;
      (var13 = new class_12((byte)10, 0)).method_95((String)null, class_11.method_79(48), this.field_186, -1);
      if (var5 == 0) {
         class_12 var14;
         (var14 = new class_12((byte)10, 0)).method_95((String)null, class_11.method_79(52), this.field_186, this.field_189);
         this.field_393.method_90(var14, 0, (this.field_187 + var13.field_114) / 2, 6);
      } else {
         String[] var15 = new String[var5];
         int[] var9 = new int[var5];
         System.arraycopy(var3, 0, var15, 0, var5);
         System.arraycopy(var4, 0, var9, 0, var5);
         this.field_395 = new class_12((byte)10, 0);
         var3 = method_131(this.field_398);
         this.field_395.method_95((String)null, class_11.method_82(54, method_131(this.field_487[var9[0]])) + "\n" + class_11.method_82(53, var3), this.field_186, -1);
         this.field_394 = new class_12((byte)11, 0);
         this.field_394.method_100(var15, this.field_188, this.field_189, this.field_186, this.field_187 - var13.field_114 - this.field_395.field_114, 3, 4);
         this.field_394.field_162 = var9;
         int var10 = (this.field_187 - this.field_395.field_114 - this.field_394.field_114 + var13.field_114) / 2;
         this.field_393.method_90(this.field_394, 0, var10, 20);
         var10 += this.field_394.field_114;
         this.field_393.method_90(this.field_395, 0, var10, 20);
         this.field_393.field_149 = true;
         this.field_393.method_87((byte)0, true);
      }

      this.field_393.method_90(var13, 0, 0, 20);
      this.field_393.method_88(var1);
   }

   // $FF: renamed from: a (aeii.f) aeii.e
   private class_12 method_126(class_11 var1) {
      if (this.field_384.length == 0) {
         class_12 var3;
         class_12 var4;
         (var3 = (var4 = new class_12((byte)10, 0)).method_89(class_11.method_79(49))).field_160 = this.field_205[6];
         var4.method_95((String)null, class_11.method_79(52), super.field_93, -1);
         var4.method_92(0, (this.field_187 + var3.field_114) / 2, 6);
         var4.method_88(var1);
         this.field_391 = null;
         return var4;
      } else {
         this.field_391 = new class_12((byte)11, 0);
         class_12 var2;
         (var2 = this.field_391.method_89(class_11.method_79(49))).field_160 = this.field_205[6];
         this.field_391.method_100(this.field_384, super.field_93 / 2, (this.field_187 + var2.field_114) / 2, this.field_186, this.field_187 - var2.field_114, 3, 4);
         this.field_391.method_88(var1);
         return this.field_391;
      }
   }

   // $FF: renamed from: a (int) int
   private int method_127(int var1) {
      return var1 >= field_191.length ? this.field_385[var1 - field_191.length] + field_191.length : var1;
   }

   // $FF: renamed from: a (int) java.io.DataInputStream
   private static DataInputStream method_128(int var0) {
      if (var0 >= field_191.length) {
         var0 -= field_191.length;
         return new DataInputStream(new ByteArrayInputStream(class_15.method_240("download", var0)));
      } else {
         return new DataInputStream(class_15.method_276("s" + var0 + ".aem"));
      }
   }

   // $FF: renamed from: a (int) byte[]
   private static byte[] method_129(int var0) {
      if (var0 >= field_191.length) {
         var0 -= field_191.length;
         return class_15.method_240("download", var0);
      } else {
         try {
            InputStream var4 = class_15.method_276("s" + var0);
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

   // $FF: renamed from: b (int) java.lang.String
   private String method_130(int var1) {
      if (var1 >= field_191.length) {
         var1 -= field_191.length;

         for(int var2 = 0; var2 < this.field_385.length; ++var2) {
            if (this.field_385[var2] == var1) {
               return this.field_384[var2];
            }
         }

         return null;
      } else {
         return field_191[var1];
      }
   }

   // $FF: renamed from: c (int) java.lang.String
   private static String method_131(int var0) {
      int var2;
      int var1 = (var2 = var0 * 100 / 1024) / 100;
      var0 = var2 % 100;
      return var1 + "." + var0;
   }

   // $FF: renamed from: a (java.lang.String, java.lang.String) java.lang.String
   private static String method_132(String var0, String var1) {
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

   // $FF: renamed from: a (aeii.e, int, java.lang.String, byte) void
   public final void method_133(class_12 var1, int var2, String var3, byte var4) {
      try {
         this.field_494 = true;
         if (var1 == this.field_280 && var4 == 1) {
            if (this.field_249 == 3) {
               this.field_256.method_41(this.field_257, this.field_258);
               this.field_256.method_51(this.field_259);
               this.method_121(this.field_256);
               this.field_307 = true;
               return;
            }
         } else {
            if (var1 == this.field_378) {
               if (var4 == 0) {
                  class_11.field_92.method_264();
               } else {
                  this.field_378 = null;
               }

               return;
            }

            if (var1 == this.field_379) {
               if (var4 == 0) {
                  this.method_122(this.field_210, this.field_334, this.field_335, var1.field_141);
               }

               this.field_379 = null;
               return;
            }

            if (var1 == this.field_497) {
               if (var4 != 0) {
                  this.field_497 = null;
                  this.field_498 = null;
                  this.field_502 = null;
                  return;
               }

               String var49;
               if (!(var49 = this.field_502[this.field_498.field_117]).endsWith(".aem")) {
                  this.field_501.method_66(var49);
                  this.field_502 = class_10.method_71(class_10.method_69(this.field_501.method_65(".aem", true)), this.field_501.method_68() != null ? field_181 : null);
                  this.field_497 = new class_12((byte)15, 15);
                  class_12 var64;
                  (var64 = this.field_497.method_89(method_132(this.field_501.method_68(), class_11.method_79(290)))).field_160 = this.field_205[6];
                  this.field_498 = new class_12((byte)0, 0);
                  this.field_498.method_100(this.field_502, 0, 0, this.field_186, this.field_187 - var64.field_114 - (this.field_238.field_7 << 1), 3, 4);
                  if (this.field_501.method_68() != null && this.field_502.length > 1) {
                     this.field_498.field_117 = 1;
                  }

                  this.field_497.method_90(this.field_498, this.field_188, (this.field_187 + var64.field_114) / 2, 3);
                  this.field_497.method_87((byte)0, true);
                  this.field_497.field_149 = true;
                  this.field_497.method_88(this.field_389);
                  class_11.field_92.method_253(this.field_497);
                  return;
               }

               class_0 var63;
               InputStream var75 = (var63 = this.field_501.method_67(var49)).openInputStream();
               ByteArrayOutputStream var80 = new ByteArrayOutputStream();

               int var90;
               while((var90 = var75.read()) >= 0) {
                  var80.write(var90);
               }

               var75.close();
               var63.close();
               var49 = var49.substring(0, var49.length() - ".aem".length());
               this.method_215(var49, var80.toByteArray());
               var80.close();
               class_12 var51;
               (var51 = this.method_197((String)null, class_11.method_82(45, var49), this.field_187, 2000)).method_88(this.field_497);
               class_11.field_92.method_253(var51);
            } else if (var1 == this.field_499) {
               if (var4 != 0) {
                  this.field_499 = null;
                  this.field_500 = null;
                  this.field_502 = null;
                  return;
               }

               String var48;
               if (this.field_500.field_117 == 0) {
                  var48 = this.field_370 + ".aem";
               } else {
                  var48 = this.field_502[this.field_500.field_117];
               }

               if (!var48.endsWith(".aem")) {
                  this.field_501.method_66(var48);
                  this.field_502 = class_10.method_71(class_10.method_69(this.field_501.method_65(".aem", true)), this.field_501.method_68() != null ? field_182 : null);
                  this.field_499 = new class_12((byte)15, 15);
                  class_12 var62;
                  (var62 = this.field_499.method_89(method_132(this.field_501.method_68(), class_11.method_79(290)))).field_160 = this.field_205[6];
                  this.field_500 = new class_12((byte)0, 0);
                  this.field_500.method_100(this.field_502, 0, 0, this.field_186, this.field_187 - var62.field_114 - (this.field_238.field_7 << 1), 3, 4);
                  if (this.field_501.method_68() != null && this.field_502.length > 2) {
                     this.field_500.field_117 = 2;
                  }

                  this.field_499.method_90(this.field_500, this.field_188, (this.field_187 + var62.field_114) / 2, 3);
                  this.field_499.method_87((byte)0, true);
                  this.field_499.field_149 = true;
                  this.field_499.method_88(this.field_509);
                  class_11.field_92.method_253(this.field_499);
                  return;
               }

               class_0 var61;
               if ((var61 = this.field_501.method_67(var48)).method_2()) {
                  var61.method_3(0L);
               } else {
                  var61.method_1();
               }

               OutputStream var74;
               (var74 = var61.openOutputStream()).write(method_129(this.field_253));
               var74.close();
               var61.close();
               this.field_502 = class_10.method_71(class_10.method_69(this.field_501.method_65(".aem", true)), this.field_501.method_68() != null ? field_182 : null);
               this.field_499 = new class_12((byte)15, 15);
               class_12 var79;
               (var79 = this.field_499.method_89(method_132(this.field_501.method_68(), class_11.method_79(299)))).field_160 = this.field_205[6];
               this.field_500 = new class_12((byte)0, 0);
               this.field_500.method_100(this.field_502, 0, 0, this.field_186, this.field_187 - var79.field_114 - (this.field_238.field_7 << 1), 3, 4);
               if (this.field_501.method_68() != null && this.field_502.length > 2) {
                  this.field_500.field_117 = 2;
               }

               this.field_499.method_90(this.field_500, this.field_188, (this.field_187 + var79.field_114) / 2, 3);
               this.field_499.method_87((byte)0, true);
               this.field_499.field_149 = true;
               this.field_499.method_88(this.field_509);
               class_12 var89;
               (var89 = this.method_197((String)null, class_11.method_82(45, this.field_370), this.field_187, 2000)).method_88(this.field_499);
               class_11.field_92.method_253(var89);
            } else {
               if (var1 == this.field_380) {
                  if (var4 == 0) {
                     int var47 = this.field_376.field_117;
                     this.method_114(var47, this.field_375);
                  }

                  this.field_380 = null;
                  return;
               }

               if (var1 == this.field_362) {
                  if (var4 == 2 || var4 == 3) {
                     this.field_363.method_95((String)null, class_11.method_79(184 + this.field_362.field_127[var2].field_44), this.field_186, this.field_187 - this.field_361.field_114 - this.field_362.field_114);
                     this.field_361.field_139 = this.field_362.field_127[var2];
                     this.field_363.method_72();
                     this.field_361.method_72();
                     return;
                  }
               } else {
                  if (var1 == this.field_360) {
                     if (var4 == 0) {
                        class_2 var60 = this.field_362.field_127[this.field_362.field_117];
                        if (!this.method_189(var60, this.field_246, this.field_247)) {
                           return;
                        }

                        this.field_256 = this.method_134(var60, this.field_246, this.field_247);
                        this.field_325 = true;
                        this.method_121(this.field_256);
                        class_11.field_92.method_253(this);
                     }

                     this.field_360 = null;
                     this.field_362 = null;
                     this.field_363 = null;
                     this.field_361 = null;
                     return;
                  }

                  if (var1 == this.field_318) {
                     if (var4 == 2 || var4 == 3) {
                        String var59 = class_11.method_80(field_315[var2], true);
                        this.field_319.method_95((String)null, var59, this.field_186, this.field_319.field_114);
                        this.field_319.method_72();
                        return;
                     }
                  } else {
                     if (var1 == this.field_317) {
                        this.field_317 = null;
                        this.field_318 = null;
                        this.field_319 = null;
                        return;
                     }

                     if (var1 == this.field_320) {
                        if (var4 == 0) {
                           class_11.field_92.method_253(this);
                           return;
                        }
                     } else {
                        if (var1 == this.field_343) {
                           if (var4 != 0) {
                              this.field_343 = null;
                           } else {
                              this.field_344 = new class_12((byte)15, 15);
                              class_12 var45;
                              (var45 = new class_12((byte)10, 0)).field_160 = this.field_205[4];
                              var45.method_95((String)null, class_11.method_79(34), this.field_186, -1);
                              this.field_344.method_90(var45, 0, 0, 20);
                              String[] var24 = new String[this.field_381];

                              for(int var70 = 0; var70 < this.field_381; ++var70) {
                                 var24[var70] = class_11.method_82(38, "" + (var70 + 1));
                              }

                              int var71 = var45.field_114;
                              class_12[] var58 = new class_12[this.field_381];
                              this.field_347 = new class_12[this.field_381];
                              this.field_348 = new class_12[this.field_381];

                              for(int var20 = 0; var20 < this.field_381; ++var20) {
                                 this.field_347[var20] = new class_12((byte)14, 6);
                                 this.field_347[var20].method_99(this.field_206, super.field_95, -1);
                                 this.field_348[var20] = new class_12((byte)14, 5);
                                 this.field_348[var20].method_99(var24, super.field_95, -1);
                                 this.field_348[var20].field_117 = var20;
                                 int var86 = Math.max(this.field_347[var20].field_113, this.field_348[var20].field_113);
                                 int var77 = super.field_93 - var86;
                                 this.field_347[var20].field_113 = var86;
                                 this.field_348[var20].field_113 = var86;
                                 this.field_344.method_90(this.field_347[var20], var77, var71, 20);
                                 int var72 = var71 + this.field_347[var20].field_114;
                                 this.field_344.method_90(this.field_348[var20], var77, var72, 20);
                                 var71 = var72 + this.field_348[var20].field_114;
                                 var58[var20] = new class_12((byte)10, 8);
                                 var58[var20].method_95((String)null, class_11.method_79(this.field_382[var20] - 1 + 89), var77, this.field_347[var20].field_114 + this.field_348[var20].field_114);
                                 var58[var20].field_158 = field_216[this.field_382[var20]];
                                 this.field_344.method_90(var58[var20], 0, var71, 36);
                              }

                              this.field_344.field_147 = 1;
                              class_12 var87;
                              (var87 = new class_12((byte)10, 8)).method_95((String)null, class_11.method_79(40), this.field_188, -1);
                              this.field_344.method_90(var87, 0, var71, 20);
                              this.field_345 = new class_12((byte)14, 4);
                              String[] var21 = new String[field_194.length];

                              for(int var78 = 0; var78 < var21.length; ++var78) {
                                 var21[var78] = "" + field_194[var78];
                              }

                              this.field_345.method_99(var21, this.field_188, var87.field_114);
                              this.field_344.method_90(this.field_345, super.field_95, var71, 20);
                              var71 += var87.field_114;
                              (var45 = new class_12((byte)10, 8)).method_95((String)null, class_11.method_79(41), this.field_188, -1);
                              this.field_344.method_90(var45, 0, var71, 20);
                              this.field_346 = new class_12((byte)14, 4);
                              String[] var98 = new String[field_195.length];

                              for(int var88 = 0; var88 < var98.length; ++var88) {
                                 var98[var88] = "" + field_195[var88];
                              }

                              this.field_346.method_99(var98, this.field_188, var45.field_114);
                              this.field_344.method_90(this.field_346, super.field_95, var71, 20);
                              this.field_344.method_88(var1);
                              this.field_344.method_87((byte)0, true);
                              class_11.field_92.method_253(this.field_344);
                           }

                           return;
                        }

                        if (var1 == this.field_344) {
                           if (var4 == 0) {
                              int var44 = 0;
                              int var85 = 0;
                              boolean[] var19 = new boolean[this.field_381];

                              for(int var57 = 0; var57 < this.field_381; ++var57) {
                                 if (this.field_347[var57].field_117 == 2) {
                                    this.field_279[var57] = 2;
                                 } else {
                                    ++var44;
                                    if (this.field_347[var57].field_117 == 0) {
                                       this.field_279[var57] = 1;
                                    } else if (this.field_347[var57].field_117 == 1) {
                                       this.field_279[var57] = 0;
                                    }

                                    this.field_271[var57] = (byte)this.field_348[var57].field_117;
                                    if (!var19[this.field_271[var57]]) {
                                       ++var85;
                                       var19[this.field_271[var57]] = true;
                                    }
                                 }
                              }

                              if (var44 >= 2 && var85 >= 2) {
                                 this.field_344 = null;
                                 this.field_343 = null;
                                 class_11.field_92.method_253(this);
                                 this.field_196 = field_194[this.field_345.field_117];
                                 this.field_197 = field_195[this.field_346.field_117];
                                 this.field_347 = null;
                                 this.field_345 = null;
                                 this.field_346 = null;
                                 this.method_224();
                                 this.field_198 = 1;
                                 this.field_321 = 8;
                                 this.field_312 = true;
                                 class_11.field_92.method_254();
                                 this.method_112();
                                 this.method_137(this.field_253);
                                 this.field_252 = this.field_253;
                                 this.field_312 = false;
                                 this.field_249 = 0;
                                 return;
                              }

                              class_12 var97;
                              (var97 = this.method_197((String)null, class_11.method_79(39), this.field_187, 2000)).method_88(this.field_344);
                              class_11.field_92.method_253(var97);
                              return;
                           }
                        } else {
                           if (var1 == this.field_323) {
                              int var5 = this.field_322.field_117;
                              if (var4 == 0 && (var5 >= field_191.length || !this.field_193[var5])) {
                                 this.field_253 = this.method_127(var5);
                                 DataInputStream var10;
                                 int var7 = (var10 = method_128(this.field_253)).readInt();
                                 int var6 = var10.readInt();
                                 byte[][] var11 = new byte[var7][var6];
                                 this.field_382 = new byte[4];
                                 byte[] var12 = new byte[5];

                                 for(int var8 = 0; var8 < 5; ++var8) {
                                    var12[var8] = -1;
                                 }

                                 this.field_381 = 0;

                                 for(int var76 = 0; var76 < var7; ++var76) {
                                    for(int var13 = 0; var13 < var6; ++var13) {
                                       var11[var76][var13] = var10.readByte();
                                       int var9;
                                       if (this.field_224[var11[var76][var13]] == 9 && (var9 = method_179(var76, var13, var11)) != 0 && var12[var9] == -1) {
                                          this.field_382[this.field_381] = (byte)var9;
                                          byte var10004 = this.field_381;
                                          this.field_381 = (byte)(var10004 + 1);
                                          var12[var9] = var10004;
                                       }
                                    }
                                 }

                                 var10.close();
                                 this.field_370 = this.field_322.field_109[var5];
                                 this.field_343 = new class_12((byte)15, 15);
                                 class_12 var25;
                                 (var25 = new class_12((byte)10, 0)).field_160 = this.field_205[4];
                                 var25.method_95((String)null, this.field_370, this.field_186, -1);
                                 class_12 var112;
                                 (var112 = new class_12((byte)8, 0)).method_91(super.field_93, this.field_187 - var25.field_114 - this.field_238.field_7, var11, (Vector)null);
                                 this.field_343.method_90(var112, this.field_188, this.field_189 + (var25.field_114 - this.field_238.field_7) / 2, 3);
                                 this.field_343.method_90(var25, 0, 0, 0);
                                 this.field_343.method_88(var1);
                                 this.field_343.method_87((byte)0, true);
                                 this.field_343.field_149 = true;
                                 class_11.field_92.method_253(this.field_343);
                              }
                           } else if (var1 == this.field_507) {
                              int var26 = this.field_508.field_117 - 1;
                              if (var4 != 0) {
                                 this.field_507 = null;
                                 this.field_508 = null;
                                 return;
                              }

                              if (var26 < 0) {
                                 this.method_221();
                                 class_15.method_252(this.field_514);
                                 return;
                              }

                              if (var26 >= field_191.length || !this.field_193[var26]) {
                                 this.field_253 = this.method_127(var26);
                                 this.field_370 = this.field_508.field_109[var26];
                                 this.field_507 = null;
                                 this.field_508 = null;
                                 class_11.field_92.method_253(this);
                                 this.field_198 = 1;
                                 this.field_321 = 8;
                                 this.field_506 = 1;
                                 this.field_312 = true;
                                 class_11.field_92.method_254();
                                 this.method_112();
                                 this.method_137(this.field_253);
                                 this.field_252 = this.field_253;
                                 this.field_312 = false;
                                 this.field_249 = 0;
                              }
                           } else if (var1 == this.field_509) {
                              int var27 = this.field_510.field_117;
                              if (var4 != 0) {
                                 this.field_509 = null;
                                 this.field_510 = null;
                                 return;
                              }

                              if (var27 >= field_191.length || !this.field_193[var27]) {
                                 this.field_253 = this.method_127(var27);
                                 this.field_370 = this.field_510.field_109[var27];
                                 if (this.field_501 == null) {
                                    this.field_501 = new class_10();
                                 }

                                 this.field_502 = class_10.method_71(class_10.method_69(this.field_501.method_65(".aem", true)), this.field_501.method_68() != null ? field_182 : null);
                                 this.field_499 = new class_12((byte)15, 15);
                                 class_12 var91;
                                 (var91 = this.field_499.method_89(method_132(this.field_501.method_68(), class_11.method_79(299)))).field_160 = this.field_205[6];
                                 this.field_500 = new class_12((byte)0, 0);
                                 this.field_500.method_100(this.field_502, 0, 0, this.field_186, this.field_187 - var91.field_114 - (this.field_238.field_7 << 1), 3, 4);
                                 if (this.field_501.method_68() != null && this.field_502.length > 2) {
                                    this.field_500.field_117 = 2;
                                 }

                                 this.field_499.method_90(this.field_500, this.field_188, (this.field_187 + var91.field_114) / 2, 3);
                                 this.field_499.method_87((byte)0, true);
                                 this.field_499.field_149 = true;
                                 this.field_499.method_88(this.field_509);
                                 class_11.field_92.method_253(this.field_499);
                              }
                           } else if (var1 == this.field_349) {
                              if (var4 == 1) {
                                 var2 = class_15.field_612[0];
                                 boolean var96 = false;

                                 for(int var69 = 0; var69 < 4; ++var69) {
                                    boolean var105;
                                    if ((var105 = this.field_350[var69].field_117 == 0) != class_15.field_612[var69]) {
                                       class_15.field_612[var69] = var105;
                                       var96 = true;
                                    }
                                 }

                                 if (var96) {
                                    method_116();
                                    if (var2 != class_15.field_612[0]) {
                                       if (!class_15.field_612[0]) {
                                          class_15.method_271();
                                          return;
                                       }

                                       if (this.field_292 == 1) {
                                          if (this.field_249 != 11 && this.field_249 != 14) {
                                             class_15.method_272(field_217[this.field_270[this.field_272]], 0);
                                             return;
                                          }
                                       } else if (this.field_292 == 0) {
                                          class_15.method_272(0, 0);
                                          return;
                                       }
                                    }
                                 } else {
                                    this.field_349 = null;
                                    this.field_350 = null;
                                 }
                              }

                              return;
                           }

                           if (var1 == this.field_375) {
                              if (var4 == 0) {
                                 int var28 = this.field_376.field_117;
                                 if (this.field_372[var28] == -1) {
                                    this.method_114(var28, var1);
                                 } else {
                                    this.field_380 = this.method_197((String)null, class_11.method_79(88), this.field_187, -1);
                                    this.field_380.method_87((byte)0, true);
                                    this.field_380.method_88(var1);
                                    class_11.field_92.method_253(this.field_380);
                                 }
                              } else {
                                 this.field_375 = null;
                                 this.field_376 = null;
                                 this.field_377 = null;
                              }
                           } else if (var1 == this.field_374) {
                              if (var4 == 0) {
                                 byte[] var92 = null;

                                 try {
                                    var92 = class_15.method_240("save", this.field_376.field_117);
                                 } catch (Exception var14) {
                                 }

                                 if (var92 != null) {
                                    this.field_374 = null;
                                    this.method_224();
                                    this.field_376 = null;
                                    this.field_377 = null;
                                    class_11.field_92.method_253(this);
                                    this.field_312 = true;
                                    class_11.field_92.method_254();
                                    this.method_112();
                                    this.method_113(var92);
                                    if (this.field_198 == 0) {
                                       this.field_337 = true;
                                    }

                                    this.field_312 = false;
                                    this.field_249 = 0;
                                 }
                              } else {
                                 this.field_374 = null;
                                 this.field_376 = null;
                                 this.field_377 = null;
                              }
                           } else if (var1 == this.field_376) {
                              if (var4 == 2 || var4 == 3) {
                                 this.field_377.method_95((String)null, this.field_371[var2], this.field_186, -1);
                                 if (this.field_372[var2] == -1) {
                                    this.field_377.field_158 = 2370117;
                                 } else {
                                    this.field_377.field_158 = field_216[this.field_372[var2]];
                                 }

                                 this.field_377.method_72();
                              }

                              return;
                           }

                           if (var1 == this.field_388) {
                              if (var4 == 0) {
                                 if (var3.equals(this.field_386[0])) {
                                    if (this.field_482 == null) {
                                       this.method_213(0, "news", class_11.method_79(0), var1);
                                       return;
                                    }

                                    this.field_390 = this.method_211(var1);
                                    return;
                                 }

                                 if (var3.equals(this.field_386[1])) {
                                    this.field_389 = new class_12((byte)11, 0);
                                    class_12 var29;
                                    (var29 = this.field_389.method_89(var3)).field_160 = this.field_205[6];
                                    this.field_389.method_100(this.field_387, super.field_93 / 2, (this.field_187 + var29.field_114) / 2, this.field_186, this.field_187 - var29.field_114, 3, 0);
                                    this.field_389.method_88(var1);
                                    class_11.field_92.method_253(this.field_389);
                                 }

                                 if (var3.equals(this.field_386[2])) {
                                    this.field_507 = new class_12((byte)15, 15);
                                    class_12 var30;
                                    (var30 = new class_12((byte)10, 0)).method_95((String)null, var3, this.field_186, -1);
                                    var30.field_160 = this.field_205[6];
                                    this.field_193 = new boolean[12];

                                    for(int var81 = this.field_190; var81 <= 7; ++var81) {
                                       if (field_192[var81] >= 0) {
                                          this.field_193[field_192[var81]] = true;
                                       }
                                    }

                                    String[] var99 = new String[12];

                                    for(int var52 = 0; var52 < 12; ++var52) {
                                       if (this.field_193[var52]) {
                                          var99[var52] = class_11.method_79(42);
                                       } else {
                                          var99[var52] = field_191[var52];
                                       }
                                    }

                                    String[] var106;
                                    (var106 = new String[12 + this.field_383 + 1])[0] = class_11.method_79(292);
                                    System.arraycopy(var99, 0, var106, 1, 12);
                                    System.arraycopy(this.field_384, 0, var106, 13, this.field_383);
                                    this.field_508 = new class_12((byte)0, 0);
                                    this.field_508.method_100(var106, 0, 0, this.field_186, this.field_187 - var30.field_114 - (this.field_238.field_7 << 1), 3, 4);
                                    this.field_507.method_90(this.field_508, this.field_188, (this.field_187 + var30.field_114) / 2, 3);
                                    this.field_507.method_90(var30, 0, 0, 0);
                                    this.field_507.field_149 = true;
                                    this.field_507.method_87((byte)0, true);
                                    this.field_507.method_88(var1);
                                    class_11.field_92.method_253(this.field_507);
                                 }
                              } else if (var4 == 1) {
                                 this.field_388 = null;
                              }
                           } else if (var1 == this.field_389) {
                              if (var4 == 0) {
                                 if (var3.equals(this.field_387[0])) {
                                    if (this.field_501 == null) {
                                       this.field_501 = new class_10();
                                    }

                                    this.field_502 = class_10.method_71(class_10.method_69(this.field_501.method_65(".aem", true)), this.field_501.method_68() != null ? field_181 : null);
                                    this.field_497 = new class_12((byte)15, 15);
                                    class_12 var100;
                                    (var100 = this.field_497.method_89(method_132(this.field_501.method_68(), class_11.method_79(290)))).field_160 = this.field_205[6];
                                    this.field_498 = new class_12((byte)0, 0);
                                    this.field_498.method_100(this.field_502, 0, 0, this.field_186, this.field_187 - var100.field_114 - (this.field_238.field_7 << 1), 3, 4);
                                    if (this.field_501.method_68() != null && this.field_502.length > 1) {
                                       this.field_498.field_117 = 1;
                                    }

                                    this.field_497.method_90(this.field_498, this.field_188, (this.field_187 + var100.field_114) / 2, 3);
                                    this.field_497.method_87((byte)0, true);
                                    this.field_497.field_149 = true;
                                    this.field_497.method_88(this.field_389);
                                    class_11.field_92.method_253(this.field_497);
                                 } else if (var3.equals(this.field_387[1])) {
                                    this.field_509 = new class_12((byte)15, 15);
                                    class_12 var31;
                                    (var31 = new class_12((byte)10, 0)).method_95((String)null, class_11.method_79(299), this.field_186, -1);
                                    var31.field_160 = this.field_205[6];
                                    this.field_193 = new boolean[12];

                                    for(int var82 = this.field_190; var82 <= 7; ++var82) {
                                       if (field_192[var82] >= 0) {
                                          this.field_193[field_192[var82]] = true;
                                       }
                                    }

                                    String[] var101 = new String[12];

                                    for(int var53 = 0; var53 < 12; ++var53) {
                                       if (this.field_193[var53]) {
                                          var101[var53] = class_11.method_79(42);
                                       } else {
                                          var101[var53] = field_191[var53];
                                       }
                                    }

                                    String[] var107 = new String[12 + this.field_383];
                                    System.arraycopy(var101, 0, var107, 0, 12);
                                    System.arraycopy(this.field_384, 0, var107, 12, this.field_383);
                                    this.field_510 = new class_12((byte)0, 0);
                                    this.field_510.method_100(var107, 0, 0, this.field_186, this.field_187 - var31.field_114 - (this.field_238.field_7 << 1), 3, 4);
                                    this.field_509.method_90(this.field_510, this.field_188, (this.field_187 + var31.field_114) / 2, 3);
                                    this.field_509.method_90(var31, 0, 0, 0);
                                    this.field_509.field_149 = true;
                                    this.field_509.method_87((byte)0, true);
                                    this.field_509.method_88(var1);
                                    class_11.field_92.method_253(this.field_509);
                                 } else if (var3.equals(this.field_387[2])) {
                                    if (this.field_485 == null) {
                                       this.method_213(2, "levels", class_11.method_79(0), var1);
                                    } else {
                                       this.method_125(var1);
                                       class_11.field_92.method_253(this.field_393);
                                    }
                                 } else if (var3.equals(this.field_387[3])) {
                                    class_11.field_92.method_253(this.method_126(var1));
                                 }
                              } else if (var4 == 1) {
                                 this.field_389 = null;
                              }
                           } else if (var1 == this.field_390) {
                              if (var4 == 0) {
                                 if (this.field_484[var2] == null) {
                                    this.field_480 = var2;
                                    this.method_213(1, this.field_483[var2], class_11.method_79(0), var1);
                                 } else {
                                    class_12 var32;
                                    (var32 = this.method_198(this.field_482[var2], this.field_484[var2], this.field_187, this.field_187 / 2, -1)).method_88(var1);
                                    class_11.field_92.method_253(var32);
                                 }
                              } else if (var4 == 1) {
                                 this.field_390 = null;
                              }
                           } else if (var1 == this.field_393) {
                              if (var4 == 0) {
                                 this.field_481 = this.field_394.field_162[this.field_394.field_117];
                                 if (this.field_398 >= this.field_487[this.field_481]) {
                                    this.method_213(3, this.field_486[this.field_481], class_11.method_79(0), var1);
                                 } else {
                                    class_12 var33;
                                    (var33 = this.method_197((String)null, class_11.method_79(55), this.field_187, -1)).method_88(var1);
                                    class_11.field_92.method_253(var33);
                                 }
                              } else if (var4 == 1) {
                                 this.field_395 = null;
                                 this.field_394 = null;
                                 this.field_393 = null;
                              }
                           } else if (var1 == this.field_394) {
                              if (var4 == 2 || var4 == 3) {
                                 String var54 = method_131(this.field_398);
                                 this.field_395.method_95((String)null, class_11.method_82(54, method_131(this.field_487[this.field_394.field_162[var2]])) + "\n" + class_11.method_82(53, var54), this.field_186, -1);
                                 this.field_395.method_72();
                              }
                           } else if (var1 == this.field_391) {
                              if (var4 == 0) {
                                 if (this.field_385[var2] + field_191.length == this.field_252) {
                                    class_12 var34;
                                    (var34 = this.method_197((String)null, class_11.method_79(56), this.field_187, -1)).method_88(var1);
                                    class_11.field_92.method_253(var34);
                                 } else {
                                    this.field_396 = var2;
                                    this.field_397 = var3;
                                    this.field_392 = this.method_198((String)null, class_11.method_82(50, var3), this.field_187, this.field_189, -1);
                                    this.field_392.method_88(var1);
                                    this.field_392.method_87((byte)0, true);
                                    class_11.field_92.method_253(this.field_392);
                                 }
                              } else if (var4 == 1) {
                                 this.field_391 = null;
                              }
                           } else if (var1 == this.field_392) {
                              if (var4 == 0) {
                                 this.method_214(this.field_396);
                                 class_12 var35 = this.method_126(this.field_391.field_141);
                                 class_12 var93;
                                 (var93 = this.method_197((String)null, class_11.method_82(51, this.field_397), this.field_187, -1)).method_88(var35);
                                 class_11.field_92.method_253(var93);
                              }

                              this.field_392 = null;
                              this.field_397 = null;
                           }

                           if (var1.field_123 == 7) {
                              class_11.field_92.method_253(this);
                              return;
                           }

                           if (var3 != null && var4 == 0) {
                              if (var3.equals(this.field_204[0])) {
                                 if (this.field_292 != 0 && this.field_249 == 0) {
                                    this.field_379 = this.method_197((String)null, class_11.method_79(87), this.field_187, -1);
                                    this.field_379.method_87((byte)0, true);
                                    this.field_379.method_88(var1);
                                    class_11.field_92.method_253(this.field_379);
                                 } else {
                                    this.method_122(this.field_210, this.field_334, this.field_335, var1);
                                 }

                                 return;
                              }

                              if (!var3.equals(this.field_204[1]) && var1 != this.field_324) {
                                 if (var3.equals(class_11.method_79(3))) {
                                    this.field_324 = new class_12((byte)11, 0);
                                    int var42 = this.field_190;
                                    if (this.field_190 > 7) {
                                       var42 = 7;
                                    }

                                    ++var42;
                                    String[] var23 = new String[var42];

                                    for(int var68 = 0; var68 < var42; ++var68) {
                                       var23[var68] = var68 + 1 + ". " + class_11.method_79(var68 + 121);
                                    }

                                    class_12 var104;
                                    (var104 = this.field_324.method_89(var3)).field_160 = this.field_205[3];
                                    this.field_324.method_100(var23, super.field_93 / 2, (this.field_187 + var104.field_114) / 2, this.field_186, this.field_187 - var104.field_114, 3, 4);
                                    this.field_324.method_88(var1);
                                    class_11.field_92.method_253(this.field_324);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(4))) {
                                    this.field_375 = this.method_124(var3, this.field_205[10]);
                                    this.field_375.method_88(var1);
                                    class_11.field_92.method_253(this.field_375);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(5))) {
                                    this.field_374 = this.method_124(var3, this.field_205[2]);
                                    this.field_374.method_88(var1);
                                    class_11.field_92.method_253(this.field_374);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(6))) {
                                    this.field_323 = new class_12((byte)15, 15);
                                    class_12 var41;
                                    (var41 = new class_12((byte)10, 0)).method_95((String)null, var3, this.field_186, -1);
                                    var41.field_160 = this.field_205[4];
                                    this.field_193 = new boolean[12];

                                    for(int var84 = this.field_190; var84 <= 7; ++var84) {
                                       if (field_192[var84] >= 0) {
                                          this.field_193[field_192[var84]] = true;
                                       }
                                    }

                                    String[] var111 = new String[12];

                                    for(int var56 = 0; var56 < 12; ++var56) {
                                       if (this.field_193[var56]) {
                                          var111[var56] = class_11.method_79(42);
                                       } else {
                                          var111[var56] = field_191[var56];
                                       }
                                    }

                                    String[] var113 = new String[12 + this.field_383];
                                    System.arraycopy(var111, 0, var113, 0, 12);
                                    System.arraycopy(this.field_384, 0, var113, 12, this.field_383);
                                    this.field_322 = new class_12((byte)0, 0);
                                    this.field_322.method_100(var113, 0, 0, this.field_186, this.field_187 - var41.field_114 - (this.field_238.field_7 << 1), 3, 4);
                                    this.field_323.method_90(this.field_322, this.field_188, (this.field_187 + var41.field_114) / 2, 3);
                                    this.field_323.method_90(var41, 0, 0, 0);
                                    this.field_323.field_149 = true;
                                    this.field_323.method_87((byte)0, true);
                                    this.field_323.method_88(var1);
                                    class_11.field_92.method_253(this.field_323);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(7))) {
                                    this.field_388 = new class_12((byte)11, 0);
                                    class_12 var40;
                                    (var40 = this.field_388.method_89(var3)).field_160 = this.field_205[6];
                                    this.field_388.method_100(this.field_386, super.field_93 / 2, (this.field_187 + var40.field_114) / 2, this.field_186, this.field_187 - var40.field_114, 3, 0);
                                    this.field_388.method_88(var1);
                                    class_11.field_92.method_253(this.field_388);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(8))) {
                                    this.field_349 = new class_12((byte)15, 15);
                                    class_12 var39;
                                    (var39 = new class_12((byte)10, 0)).field_160 = this.field_205[5];
                                    var39.method_95((String)null, var3, this.field_186, -1);
                                    this.field_349.method_90(var39, 0, 0, 20);
                                    int var83 = var39.field_114;
                                    class_12[] var110 = new class_12[4];
                                    this.field_350 = new class_12[4];

                                    for(int var55 = 0; var55 < 4; ++var55) {
                                       var2 = 8;
                                       if (var55 != 0) {
                                          var2 = 9;
                                       }

                                       if (var55 != 3) {
                                          var2 |= 2;
                                       }

                                       var110[var55] = new class_12((byte)10, var2);
                                       var110[var55].method_95((String)null, class_15.field_613[var55], this.field_188, -1);
                                       this.field_349.method_90(var110[var55], 0, var83, 20);
                                       var2 = 4;
                                       if (var55 != 0) {
                                          var2 = 5;
                                       }

                                       if (var55 != 3) {
                                          var2 |= 2;
                                       }

                                       this.field_350[var55] = new class_12((byte)14, var2);
                                       this.field_350[var55].method_99(this.field_207, this.field_188, var110[var55].field_114);
                                       this.field_350[var55].field_117 = class_15.field_612[var55] ? 0 : 1;
                                       this.field_349.method_90(this.field_350[var55], super.field_95, var83, 20);
                                       var83 += var110[var55].field_114;
                                    }

                                    this.field_349.field_147 = 2;
                                    this.field_349.method_88(var1);
                                    class_11.field_92.method_253(this.field_349);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(9))) {
                                    this.field_317 = new class_12((byte)15, 15);
                                    class_12 var38;
                                    (var38 = new class_12((byte)10, 0)).method_95((String)null, var3, this.field_186, -1);
                                    var38.field_160 = this.field_205[7];
                                    String[] var22 = new String[20];

                                    for(int var65 = 0; var65 < field_314.length; ++var65) {
                                       var22[var65] = class_11.method_79(field_314[var65]) + " " + var65 + "/" + 19;
                                    }

                                    this.field_318 = new class_12((byte)14, 2);
                                    this.field_318.method_99(var22, this.field_186, -1);
                                    this.field_319 = new class_12((byte)10, 1);
                                    this.field_319.method_95((String)null, class_11.method_79(field_315[0]), this.field_186, this.field_187 - var38.field_114 - this.field_318.field_114 - (this.field_238.field_7 << 1));
                                    int var66 = var38.field_114 + this.field_238.field_7;
                                    this.field_317.method_90(this.field_318, this.field_188, var66, 17);
                                    var66 += this.field_318.field_114;
                                    this.field_317.method_90(this.field_319, this.field_188, var66, 17);
                                    this.field_317.method_90(var38, 0, 0, 0);
                                    this.field_317.method_88(var1);
                                    this.field_317.field_149 = true;
                                    class_11.field_92.method_253(this.field_317);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(10))) {
                                    class_12 var37 = new class_12((byte)15, 15);
                                    class_12 var95;
                                    (var95 = new class_12((byte)10, 0)).method_95((String)null, var3, this.field_186, -1);
                                    var95.field_160 = this.field_205[8];
                                    class_12 var103 = new class_12((byte)10, 0);
                                    String var109 = class_11.method_82(16, this.field_184);
                                    var103.method_95((String)null, var109, this.field_186, this.field_187 - var95.field_114 - (this.field_238.field_7 << 1));
                                    var37.method_90(var103, 0, (this.field_187 + var95.field_114) / 2, 6);
                                    var37.method_90(var95, 0, 0, 0);
                                    var37.method_88(var1);
                                    var37.field_149 = true;
                                    class_11.field_92.method_253(var37);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(11))) {
                                    this.field_378 = this.method_197((String)null, class_11.method_79(86), this.field_187, -1);
                                    this.field_378.method_87((byte)0, true);
                                    this.field_378.method_88(var1);
                                    class_11.field_92.method_253(this.field_378);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(60))) {
                                    this.method_122(this.field_209, this.field_189, this.field_187, var1);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(61))) {
                                    this.field_325 = false;
                                    this.method_121(this.field_256);
                                    class_11.field_92.method_253(this);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(62))) {
                                    this.method_143(this.field_259, 0);
                                    this.field_250 = this.field_249;
                                    this.field_249 = 6;
                                    this.field_307 = true;
                                    this.field_255 = this.field_256.method_46(this.field_256.field_46, this.field_256.field_47, (byte)0);
                                    this.field_254 = 0;
                                    this.field_260 = true;
                                    this.field_261 = true;
                                    this.field_256.method_44(this.field_259, this.field_256.field_46, this.field_256.field_47);
                                    this.field_234.method_13(field_211[1]);
                                    this.method_156(this.field_255[this.field_254].field_46, this.field_255[this.field_254].field_47);
                                    this.field_290 = true;
                                    this.field_291 = true;
                                    class_11.field_92.method_253(this);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(63))) {
                                    this.field_360 = new class_12((byte)15, 15);
                                    this.field_360.field_166 = this.field_187;
                                    this.field_361 = new class_12((byte)2, 2);
                                    this.field_362 = new class_12((byte)3, 1);
                                    this.field_363 = new class_12((byte)10, 3);
                                    this.field_363.field_148 = true;
                                    this.field_361.field_139 = this.field_362.field_127[0];
                                    this.field_363.method_95((String)null, class_11.method_79(184 + this.field_362.field_127[0].field_44), this.field_186, this.field_187 - this.field_361.field_114 - this.field_362.field_114);
                                    this.field_360.method_90(this.field_361, 0, 0, 0);
                                    this.field_360.method_90(this.field_363, 0, this.field_361.field_114, 0);
                                    this.field_360.method_90(this.field_362, 0, this.field_187, 32);
                                    this.field_360.field_149 = true;
                                    this.field_360.method_88(this);
                                    this.field_360.method_87((byte)0, true);
                                    class_11.field_92.method_253(this.field_360);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(64))) {
                                    this.field_256.method_56();
                                    this.method_142();
                                    this.field_331 = this.method_168(this.field_246, this.field_247, (byte)0);
                                    this.field_249 = 0;
                                    class_11.field_92.method_253(this);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(66))) {
                                    this.method_170();
                                    class_11.field_92.method_253(this);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(68))) {
                                    this.method_174((byte)37, this.field_256.field_46, this.field_256.field_47);
                                    class_11.field_92.method_253(this.method_197((String)null, class_11.method_79(74), this.field_187, 1000));
                                    class_15.method_272(9, 1);
                                    this.field_256.method_56();
                                    this.field_249 = 0;
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(67))) {
                                    if (this.method_173(this.field_256)) {
                                       this.method_175(this.field_256.field_46, this.field_256.field_47, this.field_270[this.field_256.field_45]);
                                       class_11.field_92.method_253(this.method_197((String)null, class_11.method_79(73), this.field_187, 1000));
                                       this.field_249 = 9;
                                       class_15.method_272(9, 1);
                                       this.field_267 = this.field_251;
                                    }

                                    this.field_256.method_56();
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(69))) {
                                    this.field_249 = 7;
                                    this.field_255 = this.field_256.method_46(this.field_256.field_46, this.field_256.field_47, (byte)1);
                                    this.field_260 = true;
                                    this.field_261 = true;
                                    this.field_256.method_44(this.field_259, this.field_256.field_46, this.field_256.field_47);
                                    this.field_290 = true;
                                    class_11.field_92.method_253(this);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(70))) {
                                    class_12 var36 = new class_12((byte)15, 15);
                                    class_12 var94;
                                    (var94 = new class_12((byte)10, 0)).method_95((String)null, this.field_370, this.field_186, -1);
                                    class_12 var102;
                                    (var102 = new class_12((byte)8, 0)).method_91(this.field_186, this.field_187 - var94.field_114 - this.field_238.field_7, this.field_248, this.field_263);
                                    var36.method_90(var102, this.field_188, this.field_189 + (var94.field_114 - this.field_238.field_7) / 2, 3);
                                    var36.method_90(var94, 0, 0, 0);
                                    var36.method_88(var1);
                                    var36.field_149 = true;
                                    class_11.field_92.method_253(var36);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(71))) {
                                    this.field_320.method_87((byte)0, false);
                                    this.field_320.method_88(var1);
                                    class_11.field_92.method_253(this.field_320);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(293))) {
                                    this.method_215(this.field_370, this.method_141());
                                    class_12 var108;
                                    (var108 = this.method_197((String)null, class_11.method_82(45, this.field_370), this.field_187, 2000)).method_88(this);
                                    class_11.field_92.method_253(var108);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(305))) {
                                    this.method_217();
                                    class_15.method_252(this.field_516);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(306))) {
                                    this.method_220();
                                    this.method_218();
                                    class_15.method_252(this.field_517);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(307))) {
                                    this.method_219();
                                    class_15.method_252(this.field_519);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(303))) {
                                    this.method_220();
                                    class_15.method_252(this.field_515);
                                    return;
                                 }

                                 if (var3.equals(class_11.method_79(314))) {
                                    this.method_225();
                                    class_15.method_252(this.field_531);
                                    return;
                                 }
                              } else {
                                 class_11.field_92.method_253(this);
                                 if (var1 == this.field_324) {
                                    this.field_252 = var2;
                                    this.field_324 = null;
                                 } else {
                                    this.field_252 = 0;
                                 }

                                 this.method_224();
                                 this.field_198 = 0;
                                 this.field_279[1] = 0;
                                 this.field_312 = true;
                                 class_11.field_92.method_254();
                                 System.gc();
                                 this.method_112();
                                 this.method_137(this.field_252);
                                 this.field_312 = false;
                                 this.method_199();
                                 this.field_249 = 0;
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

   // $FF: renamed from: a (aeii.a, int, int) aeii.a
   private class_2 method_134(class_2 var1, int var2, int var3) {
      int[] var10000 = this.field_277;
      byte var10001 = this.field_272;
      var10000[var10001] -= var1.field_70;
      var1.field_54 = 100;
      var1.method_41(var2, var3);
      if (!this.field_263.contains(var1)) {
         this.field_263.addElement(var1);
      }

      this.field_400 = true;
      return var1;
   }

   // $FF: renamed from: a (byte, int, int) aeii.a
   private class_2 method_135(byte var1, int var2, int var3) {
      int[] var10000 = this.field_277;
      byte var10001 = this.field_272;
      var10000[var10001] -= class_2.field_80[var1];
      this.field_400 = true;
      return class_2.method_24(var1, this.field_272, var2, var3);
   }

   // $FF: renamed from: i () void
   private void method_136() {
      this.field_263 = new Vector();
      this.field_256 = null;
      this.field_255 = null;
      this.field_405 = null;
   }

   // $FF: renamed from: c (int) void
   private void method_137(int var1) {
      try {
         class_15.method_271();
         this.field_281 = new Vector();
         this.field_353 = false;
         this.field_262 = true;
         this.field_298 = false;
         this.field_369 = false;
         this.field_264 = null;
         this.field_283 = null;
         this.field_366 = null;
         this.field_284 = null;
         this.field_286 = null;
         this.field_356.removeAllElements();
         this.field_273 = 0;
         this.field_272 = 0;
         this.field_272 = 0;
         this.field_458 = 0;
         this.method_136();
         this.field_274 = null;
         this.field_248 = null;
         this.field_259 = null;
         this.field_268 = 0;

         for(int var2 = 0; var2 < 5; ++var2) {
            this.field_269[var2] = -1;
         }

         this.field_307 = true;
         class_15.method_274();
         DataInputStream var11;
         if (this.field_198 == 0) {
            this.field_370 = class_11.method_79(var1 + 113);
            var11 = new DataInputStream(class_15.method_276("m" + var1 + ".aem"));
         } else {
            this.field_370 = this.method_130(var1);
            var11 = method_128(var1);
         }

         this.field_230 = var11.readInt();
         this.field_231 = var11.readInt();
         this.field_248 = new byte[this.field_230][this.field_231];
         this.field_259 = new byte[this.field_230][this.field_231];
         this.field_442 = new byte[this.field_230][this.field_231];
         this.field_443 = 0;
         int var3 = 0;
         Vector var4 = new Vector();
         Vector var5 = new Vector();

         for(short var7 = 0; var7 < this.field_230; ++var7) {
            for(short var8 = 0; var8 < this.field_231; ++var8) {
               this.field_248[var7][var8] = var11.readByte();
               this.field_259[var7][var8] = 0;
               if (method_176(this.field_248[var7][var8])) {
                  int var6 = this.method_178(var7, var8);
                  byte[] var9;
                  (var9 = new byte[3])[0] = (byte)var7;
                  var9[1] = (byte)var8;
                  var9[2] = (byte)var6;
                  var4.addElement(var9);
                  ++var3;
                  if (this.method_169(var7, var8) == 9) {
                     if (this.field_198 == 1 && var6 != 0 && this.field_269[var6] == -1) {
                        this.field_270[this.field_268] = (byte)var6;
                        byte[] var10000 = this.field_269;
                        byte var10004 = this.field_268;
                        this.field_268 = (byte)(var10004 + 1);
                        var10000[var6] = var10004;
                     }

                     byte[] var16;
                     (var16 = new byte[2])[0] = (byte)var7;
                     var16[1] = (byte)var8;
                     var5.addElement(var16);
                     ++this.field_443;
                  }
               }
            }
         }

         this.field_440 = new int[var3];
         this.field_327 = new byte[var3][];
         var4.copyInto(this.field_327);
         this.field_328 = new byte[this.field_443][];
         var5.copyInto(this.field_328);
         this.field_226 = this.field_230 * 24;
         this.field_227 = this.field_231 * 24;
         if (this.field_198 == 1) {
            for(short var19 = 0; var19 < this.field_268; ++var19) {
               this.field_277[var19] = this.field_196;
            }
         } else {
            this.field_268 = 2;
            this.field_277[0] = 0;
            this.field_277[1] = 0;
            this.field_269[1] = 0;
            this.field_269[2] = 1;
            this.field_270[0] = 1;
            this.field_270[1] = 2;
            this.field_271[0] = 0;
            this.field_271[1] = 1;
            this.field_279[0] = 1;
            this.field_279[1] = 0;
            this.field_197 = 100;
         }

         for(short var20 = 0; var20 < this.field_327.length; ++var20) {
            byte var25 = this.field_327[var20][2];
            int var27 = this.method_180(var25);
            if (var25 > 0 && (var27 < 0 || this.field_279[var27] == 2)) {
               this.method_175(this.field_327[var20][0], this.field_327[var20][1], 0);
            }
         }

         int var17 = var11.readInt();
         var11.skip((long)(var17 << 2));
         int var26 = var11.readInt();
         this.field_274 = new class_2[this.field_268];
         this.field_275 = new class_2[this.field_268][4];
         this.field_276 = new int[this.field_268];

         for(short var21 = 0; var21 < var26; ++var21) {
            byte var28 = var11.readByte();
            var17 = var11.readShort() / 24;
            var3 = var11.readShort() / 24;
            byte var14 = (byte)(var28 % 12);
            byte var15 = (byte)this.method_180(1 + var28 / 12);
            if (this.field_279[var15] != 2) {
               class_2 var13 = class_2.method_24(var14, var15, var17, var3);
               if (var14 == 9) {
                  this.field_274[var15] = var13;
               }
            }
         }

         var11.close();
         if (this.field_198 == 0) {
            this.field_320 = this.method_197(class_11.method_79(121 + this.field_252), class_11.method_79(129 + this.field_252), this.field_187, -1);
         } else {
            this.field_320 = this.field_320 = this.method_197(class_11.method_79(71), class_11.method_79(137), this.field_187, -1);
         }

         this.field_463 = false;
         this.field_456 = null;
         this.field_452 = null;
         this.field_453 = null;
         this.field_454 = null;
         this.field_455 = null;
         this.field_457 = null;

         for(short var22 = 0; var22 < this.field_274.length; ++var22) {
            if (this.field_274[var22] == null) {
               this.field_278[var22][0] = 0;
               this.field_278[var22][1] = 0;
            } else {
               this.field_278[var22][0] = (byte)this.field_274[var22].field_46;
               this.field_278[var22][1] = (byte)this.field_274[var22].field_47;
            }
         }

         if (this.field_198 == 1) {
            this.field_458 = 100;
            this.field_337 = true;

            for(short var23 = 0; var23 < this.field_268; ++var23) {
               if (this.field_279[var23] != 2) {
                  this.field_272 = (byte)var23;
                  break;
               }

               ++this.field_273;
            }
         }

         if (this.field_274.length > 0 && this.field_274[this.field_272] != null) {
            this.method_154(this.field_274[this.field_272].field_46, this.field_274[this.field_272].field_47);
            this.method_156(this.field_274[this.field_272].field_46, this.field_274[this.field_272].field_47);
         }

         this.field_437 = new class_2[this.field_327.length];
         this.field_438 = new byte[this.field_327.length];
         this.field_341 = new class_1[this.field_327.length];

         for(short var24 = 0; var24 < this.field_327.length; ++var24) {
            if (this.method_169(this.field_327[var24][0], this.field_327[var24][1]) == 8) {
               this.field_341[var24] = class_1.method_19(this.field_342, 0, -1, 0, 1, 250, (byte)0);
               this.field_341[var24].field_17 = false;
            }
         }

         if (this.field_279[this.field_272] == 0) {
            this.method_190();
         }

         if (this.field_506 != 0 || this.field_268 == 0) {
            class_15.method_270(field_217[1], 0);
         }

         if (this.field_506 == 0) {
            this.method_200(var1);
            return;
         }

         this.field_228 = 0;
         this.field_229 = 0;
         this.field_246 = 0;
         this.field_247 = 0;
         this.field_234.method_11(this.field_246 * 24, this.field_247 * 24);
      } catch (Exception var10) {
         var10.printStackTrace();
      }

   }

   // $FF: renamed from: a (int[]) void
   private void method_138(int[] var1) {
      class_15.method_237(var1, true);

      for(int var2 = 0; var2 < this.field_230; ++var2) {
         for(int var3 = 0; var3 < this.field_231; ++var3) {
            if (this.field_259[var2][var3] > 0) {
               this.field_248[var2][var3] = (byte)class_15.method_236(var1);
            }
         }
      }

      class_15.method_237(var1, false);
      this.method_140();
   }

   // $FF: renamed from: a (int, int, int, int, int[]) void
   private void method_139(int var1, int var2, int var3, int var4, int[] var5) {
      var2 = this.field_230 + var1 + var2;
      var4 = this.field_231 + var3 + var4;
      byte[][] var6 = new byte[var2][var4];
      class_15.method_237(var5, true);

      for(int var9 = 0; var9 < var2; ++var9) {
         for(int var10 = 0; var10 < var4; ++var10) {
            int var7 = var9 - var1;
            int var8 = var10 - var3;
            if (var7 >= 0 && var8 >= 0 && var7 < this.field_230 && var8 < this.field_231) {
               var6[var9][var10] = this.field_248[var7][var8];
            } else {
               var6[var9][var10] = (byte)class_15.method_236(var5);
            }
         }
      }

      class_15.method_237(var5, false);

      for(int var13 = 0; var13 < this.field_263.size(); ++var13) {
         class_2 var14;
         (var14 = (class_2)this.field_263.elementAt(var13)).method_41(var14.field_46 + var1, var14.field_47 + var3);
      }

      this.field_248 = var6;
      this.field_230 = var2;
      this.field_231 = var4;
      this.field_259 = new byte[this.field_230][this.field_231];
      this.field_442 = new byte[this.field_230][this.field_231];
      this.method_140();
      this.field_226 = this.field_230 * 24;
      this.field_227 = this.field_231 * 24;
      this.field_228 = 0;
      this.field_229 = 0;
      this.field_246 = 0;
      this.field_247 = 0;
      this.field_234.method_11(this.field_246 * 24, this.field_247 * 24);
   }

   // $FF: renamed from: j () void
   private void method_140() {
      try {
         this.field_268 = 0;

         for(int var1 = 0; var1 < 5; ++var1) {
            this.field_269[var1] = -1;
         }

         this.field_443 = 0;
         int var9 = 0;
         Vector var2 = new Vector();
         Vector var3 = new Vector();

         for(short var5 = 0; var5 < this.field_230; ++var5) {
            for(short var6 = 0; var6 < this.field_231; ++var6) {
               if (method_176(this.field_248[var5][var6])) {
                  int var4 = this.method_178(var5, var6);
                  byte[] var7;
                  (var7 = new byte[3])[0] = (byte)var5;
                  var7[1] = (byte)var6;
                  var7[2] = (byte)var4;
                  var2.addElement(var7);
                  ++var9;
                  if (this.method_169(var5, var6) == 9) {
                     if (this.field_198 == 1 && var4 != 0 && this.field_269[var4] == -1) {
                        this.field_270[this.field_268] = (byte)var4;
                        byte[] var10000 = this.field_269;
                        byte var10004 = this.field_268;
                        this.field_268 = (byte)(var10004 + 1);
                        var10000[var4] = var10004;
                     }

                     byte[] var10;
                     (var10 = new byte[2])[0] = (byte)var5;
                     var10[1] = (byte)var6;
                     var3.addElement(var10);
                     ++this.field_443;
                  }
               }
            }
         }

         this.field_440 = new int[var9];
         this.field_327 = new byte[var9][];
         var2.copyInto(this.field_327);
         this.field_328 = new byte[this.field_443][];
         var3.copyInto(this.field_328);
         this.field_437 = new class_2[this.field_327.length];
         this.field_438 = new byte[this.field_327.length];
         this.field_341 = new class_1[this.field_327.length];

         for(short var11 = 0; var11 < this.field_327.length; ++var11) {
            if (this.method_169(this.field_327[var11][0], this.field_327[var11][1]) == 8) {
               this.field_341[var11] = class_1.method_19(this.field_342, 0, -1, 0, 1, 250, (byte)0);
               this.field_341[var11].field_17 = false;
            }
         }

      } catch (Exception var8) {
         var8.printStackTrace();
      }
   }

   // $FF: renamed from: a () byte[]
   private byte[] method_141() {
      try {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         DataOutputStream var2;
         (var2 = new DataOutputStream(var1)).writeInt(this.field_230);
         var2.writeInt(this.field_231);

         for(int var3 = 0; var3 < this.field_230; ++var3) {
            for(int var4 = 0; var4 < this.field_231; ++var4) {
               var2.writeByte(this.field_248[var3][var4]);
            }
         }

         var2.writeInt(0);
         var2.writeInt(this.field_263.size());

         for(int var6 = 0; var6 < this.field_263.size(); ++var6) {
            class_2 var8 = (class_2)this.field_263.elementAt(var6);
            var2.writeByte((this.field_270[var8.field_45] - 1) * 12 + var8.field_44);
            var2.writeShort(var8.field_46 * 24);
            var2.writeShort(var8.field_47 * 24);
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

   // $FF: renamed from: k () void
   private void method_142() {
      this.field_254 = 0;
      this.field_256 = null;
      this.field_255 = new class_2[0];
      this.method_143(this.field_259, 0);
      this.field_260 = false;
      this.field_261 = false;
   }

   // $FF: renamed from: a (byte[][], int) void
   private void method_143(byte[][] var1, int var2) {
      for(int var3 = 0; var3 < this.field_230; ++var3) {
         for(int var4 = 0; var4 < this.field_231; ++var4) {
            var1[var3][var4] = (byte)var2;
         }
      }

   }

   // $FF: renamed from: c (aeii.a) void
   private void method_144(class_2 var1) {
      class_15.method_273(10);
      this.field_405 = null;
      this.method_143(this.field_259, 0);
      this.field_260 = false;
      if (this.field_279[this.field_272] == 1) {
         this.field_407 = 1;
         this.field_262 = true;
         this.field_234.method_13(field_211[0]);
         this.field_249 = 3;
         this.method_123(this.method_145(var1, (byte)0));
         class_15.method_272(11, 1);
      } else {
         if (this.field_279[this.field_272] == 0) {
            this.field_428 = 4;
            this.field_249 = 0;
         }

      }
   }

   // $FF: renamed from: a (aeii.a, byte) byte[]
   private byte[] method_145(class_2 var1, byte var2) {
      int var3 = 0;
      byte[] var4 = new byte[this.field_202.length];
      if (var2 == 1 && this.method_169(this.field_256.field_46, this.field_256.field_47) == 9 && this.method_182(this.field_256.field_46, this.field_256.field_47, var1.field_45)) {
         ++var3;
         var4[0] = 0;
      }

      if (this.method_172(var1)) {
         var4[var3++] = 2;
      } else if (this.method_173(var1)) {
         var4[var3++] = 1;
      }

      if ((var2 == 1 || var1.field_44 != 7) && var1.field_40.length > 0 && var1.method_46(var1.field_46, var1.field_47, (byte)0).length > 0) {
         var4[var3++] = 3;
      }

      if (var1.method_55((short)32) && var1.method_46(var1.field_46, var1.field_47, (byte)1).length > 0) {
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

   // $FF: renamed from: l () void
   private void method_146() {
      if (class_15.field_589 && this.field_297 < 4) {
         this.field_295 = new class_16("logo", this.j, -1);
         this.field_297 = 3;
         this.field_299 = 40;
      }

      switch (this.field_297) {
         case 0:
            if (this.field_251 >= 1200L) {
               this.field_297 = 1;
            }

            this.field_299 = 40;
            return;
         case 1:
            if (this.field_299 <= 0) {
               this.field_293 = null;
               this.field_299 = 0;
               ++this.field_297;
               this.method_160(0, 0, 3);
               return;
            }

            --this.field_299;
            return;
         case 2:
            this.method_160(1, 2, 3);
            this.field_295 = new class_16("logo", this.j, -1);
            ++this.field_297;
            return;
         case 3:
            if (++this.field_299 > 40) {
               try {
                  this.field_294 = new class_16("splash", this.field_186, -1);
                  this.field_296 = new class_16("glow");
               } catch (Exception var2) {
               }

               if (this.field_294 != null) {
                  this.field_299 = 0;
               } else {
                  this.field_299 = 11;
               }

               ++this.field_297;
               class_11.field_92.method_257();
               return;
            }
            break;
         case 4:
            if (this.field_299 < 16) {
               ++this.field_299;
               ++this.field_300;
               if (this.field_296 != null) {
                  this.field_336 = -this.field_296.field_634;
                  return;
               }
            } else {
               if (this.field_336 >= super.field_93 << 2) {
                  if (this.field_296 != null) {
                     this.field_336 = -this.field_296.field_634;
                  }
               } else {
                  this.field_336 += this.field_295.field_634 / 6;
               }

               if (this.field_251 % 100L == 0L) {
                  this.field_313 = !this.field_313;
               }

               if ((this.field_294 == null || class_11.field_92.method_256()) && this.method_111()) {
                  if (this.field_296 != null) {
                     this.field_336 = -this.field_296.field_634;
                  }

                  this.field_313 = false;
                  int var1 = this.field_295.field_635 + 1;
                  this.method_122(this.field_208, (super.field_94 + var1) / 2, super.field_94 - var1, this.field_294 == null ? null : this);
                  class_11.field_92.method_257();
               }
            }
      }

   }

   // $FF: renamed from: b () void
   public final void method_73() {
      this.field_251 += 50L;
      if (this.field_292 == 2) {
         class_13 var1 = this;
         if (this.field_472 && this.field_251 - this.field_474 >= this.field_473) {
            this.field_472 = false;
         }

         for(int var2 = 0; var2 < var1.field_471.size(); ++var2) {
            ((class_1)var1.field_471.elementAt(var2)).method_20();
         }

         for(int var22 = 0; var22 < var1.field_471.size(); ++var22) {
            class_1 var3;
            if (!(var3 = (class_1)var1.field_471.elementAt(var22)).field_17) {
               var1.method_209(var3);
            }
         }

         var1.field_467.method_228();
         var1.field_468.method_228();
         if (var1.field_476) {
            ++var1.field_299;
            if (var1.field_299 >= 16) {
               var1.field_476 = false;
               var1.field_467.method_227();
            }

            var1.field_477 = true;
            var1.field_478 = true;
         } else {
            if (!var1.field_470) {
               if (var1.field_467.field_546) {
                  if (var1.field_475 && var1.field_468.field_549 > 0) {
                     if (!var1.field_468.field_545) {
                        var1.field_468.method_227();
                     }

                     if (!var1.field_468.field_546) {
                        return;
                     }
                  }

                  var1.field_470 = true;
                  var1.field_469 = var1.field_251;
               }

               return;
            }

            if (var1.field_251 - var1.field_469 < 300L) {
               return;
            }

            var1.field_471.removeAllElements();
            var1.field_468 = null;
            var1.field_467 = null;
            var1.field_281 = new Vector();
            var1.method_119();
            var1.field_292 = 1;
            class_15.method_271();
            class_15.method_272(field_217[var1.field_270[var1.field_272]], 0);
            class_11.field_92.method_257();
            var1.field_400 = true;
            var1.field_401 = true;
         }
      } else if (this.field_292 == 3) {
         if (this.field_416) {
            --this.field_299;
            if (this.field_299 >= 0) {
               return;
            }

            this.field_299 = 0;
            this.field_292 = this.field_417;
            this.field_414 = null;
            this.field_415 = null;
         } else {
            if ((this.field_418 != 2 || this.field_299 >= 40) && (this.field_418 == 2 || this.field_299 >= 16)) {
               --this.field_411;
               if (this.field_411 < this.field_420) {
                  this.field_411 = this.field_420 + this.field_410 - (this.field_420 - this.field_411);
                  ++this.field_413;
               }
            } else {
               ++this.field_299;
            }

            if (this.field_413 < this.field_415.length && !class_11.field_92.method_259(field_199)) {
               return;
            }

            this.field_416 = true;
            if (this.field_414 == null && this.field_413 >= this.field_415.length) {
               this.field_299 = 0;
               return;
            }

            if (this.field_419 == 2) {
               this.field_299 = 40;
            } else {
               this.field_299 = 16;
            }
         }
      } else {
         if (this.field_292 == 0) {
            this.method_146();
            return;
         }

         this.method_202();
         if (this.field_316 != -1) {
            if (class_15.field_612[2]) {
               class_12 var9;
               (var9 = this.method_196(class_11.method_80(196 + this.field_316, true), (byte)-1, (byte)2)).field_159 = 7831691;
               var9.field_158 = 7831691;
               var9.field_103 = 16250855;
            }

            this.field_316 = -1;
         }

         if (class_11.field_92.field_603 == this) {
            if (this.field_249 == 0) {
               for(int var10 = 0; var10 < this.field_341.length; ++var10) {
                  int var23 = this.method_178(this.field_327[var10][0], this.field_327[var10][1]);
                  if (this.field_341[var10] != null && var23 != -1 && var23 != 0 && !this.field_341[var10].field_17 && class_15.field_611.nextInt() % 8 == 0) {
                     this.field_341[var10].field_17 = true;
                     this.field_341[var10].method_10(0);
                     this.field_341[var10].field_11 = 1;
                     this.field_341[var10].method_11((this.field_327[var10][0] + 1) * 24 - this.field_342.field_6, this.field_327[var10][1] * 24 - 2);
                     this.field_282.addElement(this.field_341[var10]);
                  }
               }
            }

            if (this.field_251 - this.field_306 >= 300L) {
               this.field_305 = !this.field_305;
               this.field_306 = this.field_251;
            }

            if (this.field_472 && this.field_251 - this.field_474 >= this.field_473) {
               this.field_472 = false;
            }

            if (this.field_298) {
               ++this.field_299;
               if (this.field_299 > 16) {
                  if (this.field_249 == 10) {
                     this.field_466 = 1;
                  } else if (this.field_249 == 11) {
                     if (this.field_198 == 0) {
                        this.field_355 = true;
                        this.field_354 = true;
                        this.field_352 = 0;
                     }

                     this.field_326 = 0;
                     this.field_267 = this.field_251;
                  } else {
                     this.method_207(this.field_303, this.field_304);
                     this.field_431 = null;
                     this.method_142();
                  }

                  this.field_298 = false;
                  return;
               }
            } else {
               if (this.field_355) {
                  if (this.field_353) {
                     if (this.field_352 < 16) {
                        ++this.field_352;
                     }
                  } else if (this.field_354) {
                     ++this.field_352;
                     if (this.field_352 > 16) {
                        this.field_354 = false;
                     }
                  }
               }

               if (this.field_264 != null) {
                  this.field_265 = (this.field_265 + 1) % 12;
               }

               if (this.field_260) {
                  if (this.field_422 == 0) {
                     ++this.field_421;
                     if (this.field_421 >= 15) {
                        this.field_422 = 1;
                     }
                  } else {
                     --this.field_421;
                     if (this.field_421 <= 0) {
                        this.field_422 = 0;
                     }
                  }

                  if (this.field_409 > 0) {
                     this.field_409 -= 4;
                     if (this.field_409 < 0) {
                        this.field_409 = 0;
                     }
                  }
               }

               if (this.field_262 && this.field_251 - this.field_213 >= 200L) {
                  this.field_234.method_12();
                  this.field_213 = this.field_251;
               }

               int var24 = this.field_246 * 24;
               int var11 = this.field_247 * 24;
               int var45 = this.field_234.field_3;
               int var4 = this.field_234.field_4;
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

               this.field_234.method_11(var45, var4);
               if (!this.field_339 && this.field_338 > 0) {
                  if (this.field_338 < 2) {
                     this.field_338 = 0;
                  } else {
                     this.field_338 /= 2;
                  }

                  this.field_400 = true;
                  this.field_401 = true;
               } else if (this.field_249 == 8) {
                  if (this.field_266 == 0) {
                     if (this.field_338 >= field_185) {
                        this.field_266 = 1;
                        this.method_171();
                        String var40;
                        if (this.field_279[this.field_272] == 1) {
                           var40 = "" + this.field_426;
                        } else {
                           var40 = "?";
                        }

                        class_12 var41 = this.method_197(class_11.method_79(75), class_11.method_82(76, var40), this.field_187, 1500);
                        class_11.field_92.method_253(var41);
                        var41.field_158 = field_216[this.field_270[this.field_272]];
                        class_15.method_270(field_217[this.field_270[this.field_272]], 0);
                        return;
                     }

                     if (this.field_338 == 0) {
                        this.field_339 = true;
                        this.field_338 = 1;
                     } else {
                        this.field_338 <<= 1;
                     }

                     this.field_400 = true;
                     this.field_401 = true;
                  } else {
                     for(int var18 = this.field_263.size() - 1; var18 >= 0; --var18) {
                        class_2 var50;
                        if ((var50 = (class_2)this.field_263.elementAt(var18)).field_55 != 3 && this.field_272 == var50.field_45 && (this.method_169(var50.field_46, var50.field_47) == 7 || this.method_181(var50.field_46, var50.field_47, this.field_271[var50.field_45])) && var50.field_54 < 100) {
                           if ((var24 = 100 - var50.field_54) > 20) {
                              var24 = 20;
                           }

                           var50.field_54 += var24;
                           class_1 var39;
                           (var39 = class_1.method_18("+" + var24, 0, -4, (byte)1)).method_11(var50.m + var50.o / 2, var50.n + var50.p);
                           this.field_281.addElement(var39);
                        }
                     }

                     this.field_266 = 0;
                     this.field_339 = false;
                     this.field_249 = 0;
                  }
               } else if (this.field_249 == 9) {
                  this.field_249 = 0;
               } else if (this.field_249 == 11) {
                  if (!this.field_298 && this.field_326 == 0 && (this.field_198 == 1 || this.field_251 - this.field_267 >= 3000L || class_11.field_92.method_256())) {
                     this.field_187 = super.field_94;
                     this.field_189 = super.field_96;
                     this.method_122(this.field_208, super.field_96, super.field_94, (class_11)null);
                     this.field_326 = 1;
                     this.field_354 = false;
                  }
               } else if (this.field_249 != 10 && this.field_249 != 14) {
                  if (this.field_249 == 13) {
                     if (this.field_301 == 0) {
                        class_15.method_267(200);
                        var24 = this.field_303.method_33(this.field_304);
                        this.field_304.method_23(400);
                        class_15.method_272(14, 1);
                        this.method_120(this.field_242, this.field_304.m, this.field_304.n, 0, 0, 2, 50);
                        class_1 var35 = class_1.method_18("-" + var24, 0, -4, (byte)1);
                        if ((var11 = this.field_304.m + this.field_304.o / 2) + var35.field_6 / 2 > this.field_226) {
                           var11 = this.field_226 - var35.field_6 / 2;
                        } else if (var11 - var35.field_6 / 2 < 0) {
                           var11 = var35.field_6 / 2;
                        }

                        var35.method_11(var11, this.field_304.n + this.field_304.p);
                        this.field_281.addElement(var35);
                        this.field_302 = this.field_251;
                        ++this.field_301;
                     } else if (this.field_301 == 1) {
                        if (this.field_251 - this.field_302 >= 800L) {
                           this.method_156(this.field_303.field_46, this.field_303.field_47);
                           if (this.field_304.method_37(this.field_303.field_46, this.field_303.field_47)) {
                              class_15.method_267(200);
                              var24 = this.field_304.method_33(this.field_303);
                              this.field_303.method_23(400);
                              class_15.method_272(14, 1);
                              this.method_120(this.field_242, this.field_303.m, this.field_303.n, 0, 0, 2, 50);
                              class_1 var37 = class_1.method_18("-" + var24, 0, -4, (byte)1);
                              if ((var11 = this.field_303.m + this.field_303.o / 2) + var37.field_6 / 2 > this.field_226) {
                                 var11 = this.field_226 - var37.field_6 / 2;
                              } else if (var11 - var37.field_6 / 2 < 0) {
                                 var11 = var37.field_6 / 2;
                              }

                              var37.method_11(var11, this.field_303.n + this.field_303.p);
                              this.field_281.addElement(var37);
                              this.field_302 = this.field_251;
                              ++this.field_301;
                           } else {
                              this.method_119();
                           }
                        }
                     } else if (this.field_251 - this.field_302 >= 800L) {
                        this.method_119();
                     }
                  } else if (this.field_366 != null) {
                     if (this.field_367 == 0) {
                        if (this.field_408) {
                           this.field_365 = this.method_120(this.field_241, this.field_366.m, -this.field_229, 0, 12, -1, 0);
                           class_12 var61;
                           (var61 = this.method_197((String)null, class_11.method_79(280), this.field_187, 2000)).method_92(this.field_188, 2, 17);
                           class_11.field_92.method_253(var61);
                           this.field_367 = 1;
                        }
                     } else if (this.field_367 != 1) {
                        if (++this.field_367 >= 20) {
                           if (this.field_366.field_54 <= 0) {
                              this.field_283 = this.field_366;
                              this.method_120(this.field_241, this.field_283.m, this.field_283.n, 0, 0, 1, 50);
                              class_15.method_272(12, 1);
                              this.field_285 = this.field_251;
                           }

                           this.field_366 = null;
                        }
                     } else {
                        for(int var15 = 0; var15 < 3; ++var15) {
                           this.method_120(this.field_342, this.field_365.field_3 + class_15.method_233(this.field_365.field_6 - this.field_342.field_6), this.field_365.field_4, 0, class_15.method_234(-3, 0), 1, 50 * class_15.method_233(4));
                        }

                        if (this.field_365.field_4 >= this.field_366.n) {
                           this.field_365.field_17 = false;
                           class_15.method_267(200);
                           this.method_210(500);
                           if (this.field_368) {
                              if ((var24 = 25 + class_15.method_233(25)) > this.field_366.field_54) {
                                 var24 = this.field_366.field_54;
                              }

                              class_2 var68 = this.field_366;
                              var68.field_54 -= var24;
                              class_1 var33;
                              (var33 = class_1.method_18("-" + var24, 0, -4, (byte)1)).method_11(this.field_366.m + this.field_366.o / 2, this.field_366.n + this.field_366.p);
                              this.field_281.addElement(var33);
                           }

                           this.method_147(this.field_366);
                           this.field_367 = 2;
                        }
                     }
                  } else if (this.field_284 != null) {
                     if (--this.field_289 <= 0) {
                        class_15.method_267(100);
                        this.method_147(this.field_284);
                        this.method_174((byte)27, this.field_284.field_46, this.field_284.field_47);
                        this.field_284 = null;
                     }
                  } else if (this.field_283 != null) {
                     if (this.field_251 - this.field_285 >= 300L && this.method_150(this.field_283.field_46, this.field_283.field_47)) {
                        if (this.field_198 == 0 && this.field_252 == 7 && this.field_283 == this.field_274[1]) {
                           this.field_369 = true;
                        } else {
                           this.method_120(this.field_240, this.field_283.m, this.field_283.n, 0, -3, 1, 100);
                           this.field_283.field_55 = 3;
                           this.field_283.field_64 = 3;
                           if (this.field_283.field_44 != 10 && this.field_283.field_44 != 11) {
                              if (this.field_283.field_44 == 9) {
                                 this.field_283.method_41(-10, -10);
                                 this.field_283.field_56 = 0;
                                 this.field_283.method_40();
                              }
                           } else {
                              this.field_283.method_27();
                           }

                           if (this.field_283.field_44 == 9 && this.field_283.field_70 < 1000) {
                              class_2 var10000 = this.field_283;
                              var10000.field_70 += 200;
                           }
                        }

                        this.field_283 = null;
                     }
                  } else if (this.field_356.size() > 0) {
                     class_2 var49 = (class_2)this.field_356.elementAt(0);
                     if (this.field_358 == 0) {
                        this.method_156(var49.field_46, var49.field_47);
                        this.field_358 = 1;
                     } else if (this.method_150(var49.field_46, var49.field_47)) {
                        this.method_120(this.field_244, var49.m + class_15.method_233(var49.o), var49.n + class_15.method_233(var49.p), 0, 0, 1, 50);
                        if (this.field_358 == 1) {
                           class_15.method_272(13, 1);
                        }

                        if (this.field_358 <= 5) {
                           var4 = 200;
                           if (this.field_358 == 5) {
                              var4 = 1000;
                           }

                           var11 = var49.m + (var49.o - this.field_357.field_6) / 2;
                           int var64 = var49.n - (this.field_358 << 2);
                           if (var11 < 0) {
                              var11 = 0;
                           } else if (var11 + this.field_357.field_6 > this.field_226) {
                              var11 = this.field_226 - this.field_357.field_6;
                           }

                           if (var64 < 0) {
                              var64 = 0;
                           }

                           this.method_120(this.field_357, var11, var64, 0, 0, 1, var4);
                        }

                        ++this.field_358;
                        if (this.field_358 >= 20) {
                           this.field_356.removeElement(var49);
                           this.field_358 = 0;
                           if (var49.field_44 != 9 && var49.field_38 <= 6 && var49.field_38 % 2 == 0) {
                              class_11.field_92.method_253(this.method_197((String)null, class_11.method_79(80) + "\n" + var49.field_37, this.field_186, 2000));
                           }
                        }
                     }
                  } else if (this.field_286 != null) {
                     if (this.field_251 - this.field_288 >= 400L) {
                        this.field_286.method_27();
                        class_2.method_24((byte)10, this.field_287, this.field_286.field_46, this.field_286.field_47).method_56();
                        this.field_286 = null;
                     }
                  } else if (!this.field_463) {
                     if (this.field_249 == 2) {
                        if (this.field_256.field_55 != 1 && this.field_408) {
                           this.method_144(this.field_256);
                        }
                     } else if (this.field_506 == 0 && this.field_279[this.field_272] == 0) {
                        this.method_191();
                     } else if (this.method_111()) {
                        if (this.field_291 && class_11.field_92.method_259(field_199)) {
                           class_11.field_92.method_261(16);
                           class_11.field_92.method_262(field_199);
                        }

                        if (this.field_249 != 6 && this.field_249 != 7) {
                           if (this.field_251 - this.field_214 >= 150L && this.field_234.field_3 % 24 == 0 && this.field_234.field_4 % 24 == 0) {
                              if (!class_11.field_92.method_258(4) && !class_11.field_92.method_260(4)) {
                                 if (class_11.field_92.method_258(8) || class_11.field_92.method_260(8)) {
                                    if (this.field_246 < this.field_230 - 1) {
                                       ++this.field_246;
                                    }

                                    this.field_307 = true;
                                    this.field_214 = this.field_251;
                                 }
                              } else {
                                 if (this.field_246 > 0) {
                                    --this.field_246;
                                 }

                                 this.field_307 = true;
                                 this.field_214 = this.field_251;
                              }

                              if (!class_11.field_92.method_258(1) && !class_11.field_92.method_260(1)) {
                                 if (class_11.field_92.method_258(2) || class_11.field_92.method_260(2)) {
                                    if (this.field_247 < this.field_231 - 1) {
                                       ++this.field_247;
                                    }

                                    this.field_307 = true;
                                    this.field_214 = this.field_251;
                                 }
                              } else {
                                 if (this.field_247 > 0) {
                                    --this.field_247;
                                 }

                                 this.field_307 = true;
                                 this.field_214 = this.field_251;
                              }

                              if (this.field_307) {
                                 if (this.field_249 == 1) {
                                    if (this.field_259[this.field_246][this.field_247] > 0) {
                                       this.field_264 = this.field_256.method_50(this.field_256.field_46, this.field_256.field_47, this.field_246, this.field_247);
                                    }
                                 } else {
                                    this.field_331 = this.method_168(this.field_246, this.field_247, (byte)0);
                                 }

                                 this.field_401 = true;
                              }

                              this.field_307 = false;
                           }

                           if ((this.field_249 == 1 || this.field_249 == 0) && this.field_506 == 0 && class_11.field_92.method_258(256)) {
                              class_2 var46;
                              if ((var46 = this.method_168(this.field_246, this.field_247, (byte)0)) != null) {
                                 class_12 var28;
                                 (var28 = new class_12((byte)15, 15)).field_166 = this.field_187;
                                 class_12 var5 = new class_12((byte)5, 2);
                                 class_12 var12;
                                 (var12 = new class_12((byte)10, 1)).field_148 = true;
                                 String var6 = class_11.method_79(184 + var46.field_44);
                                 if (var46.field_56 != 0) {
                                    StringBuffer var7 = new StringBuffer(class_11.method_79(98));
                                    if ((var46.field_56 & 2) != 0) {
                                       var7.append('\n');
                                       var7.append(class_11.method_79(100));
                                    }

                                    if ((var46.field_56 & 1) != 0) {
                                       var7.append('\n');
                                       var7.append(class_11.method_79(99));
                                    }

                                    var7.append("\n-----------\n");
                                    var6 = var7.toString() + var6;
                                 }

                                 var12.method_95((String)null, var6, this.field_186, this.field_187 - var5.field_114);
                                 var28.method_90(var5, 0, 0, 0);
                                 var28.method_90(var12, 0, var5.field_114, 0);
                                 var28.field_149 = true;
                                 var28.method_88(this);
                                 class_11.field_92.method_253(var28);
                              }

                              class_11.field_92.method_262(256);
                           }

                           if (this.field_249 == 1) {
                              if (class_11.field_92.method_258(16) && this.field_256 != null) {
                                 class_2 var48 = this.method_168(this.field_246, this.field_247, (byte)0);
                                 if (this.field_259[this.field_246][this.field_247] > 0 && (var48 == null || var48 == this.field_256)) {
                                    this.field_257 = this.field_256.field_46;
                                    this.field_258 = this.field_256.field_47;
                                    this.field_256.method_48(this.field_246, this.field_247, true);
                                    this.field_405 = this.field_256;
                                    this.field_262 = false;
                                    this.field_260 = false;
                                    this.field_264 = null;
                                    this.field_280 = null;
                                    this.field_290 = false;
                                    this.field_291 = false;
                                    this.field_249 = 2;
                                    class_15.method_272(10, 1);
                                 }

                                 class_11.field_92.method_262(16);
                              }
                           } else if (this.field_249 == 0) {
                              if (this.field_506 == 1) {
                                 if (class_11.field_92.method_258(128)) {
                                    if (++this.field_503 >= this.field_233.length) {
                                       this.field_503 = 0;
                                    }

                                    this.field_400 = true;
                                    this.field_401 = true;
                                 } else if (class_11.field_92.method_258(64)) {
                                    if (--this.field_503 < 0) {
                                       this.field_503 = (byte)(this.field_233.length - 1);
                                    }

                                    this.field_400 = true;
                                    this.field_401 = true;
                                 } else if (class_11.field_92.method_258(16)) {
                                    if (!this.field_260) {
                                       this.field_248[this.field_246][this.field_247] = this.field_503;
                                       this.method_140();
                                    } else {
                                       if (this.field_259[this.field_246][this.field_247] == 0) {
                                          this.field_259[this.field_246][this.field_247] = 1;
                                       } else {
                                          this.field_259[this.field_246][this.field_247] = 0;
                                       }

                                       this.field_260 = false;

                                       for(int var59 = 0; var59 < this.field_230; ++var59) {
                                          for(int var63 = 0; var63 < this.field_231; ++var63) {
                                             if (this.field_259[var59][var63] > 0) {
                                                this.field_260 = true;
                                                break;
                                             }
                                          }

                                          if (this.field_260) {
                                             break;
                                          }
                                       }
                                    }
                                 } else if (class_11.field_92.method_258(512)) {
                                    if (this.field_503 == this.field_248[this.field_246][this.field_247]) {
                                       if (++this.field_503 >= this.field_233.length) {
                                          this.field_503 = 0;
                                       }

                                       this.field_248[this.field_246][this.field_247] = this.field_503;
                                       this.method_140();
                                    } else {
                                       this.field_503 = this.field_248[this.field_246][this.field_247];
                                    }

                                    this.field_400 = true;
                                    this.field_401 = true;
                                 } else if (class_11.field_92.method_258(256)) {
                                    if (this.field_503 == this.field_248[this.field_246][this.field_247]) {
                                       if (--this.field_503 < 0) {
                                          this.field_503 = (byte)(this.field_233.length - 1);
                                       }

                                       this.field_248[this.field_246][this.field_247] = this.field_503;
                                       this.method_140();
                                    } else {
                                       this.field_503 = this.field_248[this.field_246][this.field_247];
                                    }

                                    this.field_400 = true;
                                    this.field_401 = true;
                                 } else if (class_11.field_92.method_258(32)) {
                                    if (this.field_260) {
                                       this.method_138(this.field_511);
                                    } else {
                                       this.field_503 = this.field_248[this.field_246][this.field_247];
                                       this.method_220();

                                       for(int var58 = 0; var58 < this.field_511.length; ++var58) {
                                          this.field_511[var58] = 0;
                                          this.field_513[var58] = false;
                                       }

                                       this.field_511[this.field_503] = 100;
                                       this.field_513[this.field_503] = true;
                                       this.field_523.setSelectedFlags(this.field_513);
                                       this.field_400 = true;
                                       this.field_401 = true;
                                    }
                                 } else if (!class_11.field_92.method_258(16384)) {
                                    if (class_11.field_92.method_258(8192)) {
                                       if (this.field_268 > 0) {
                                          this.method_143(this.field_259, 0);
                                          this.field_260 = false;
                                          this.field_506 = 2;
                                          this.field_272 = 0;
                                          this.field_505 = null;
                                          this.field_400 = true;
                                          this.field_401 = true;
                                       }
                                    } else if (class_11.field_92.method_258(field_199)) {
                                       this.field_280 = new class_12((byte)11, 0);
                                       this.field_280.method_100(field_183, 2, 2, -1, this.field_187, 20, 0);
                                       this.field_280.method_88(this);
                                       class_11.field_92.method_253(this.field_280);
                                    }
                                 } else if (!this.field_260) {
                                    this.field_259[this.field_246][this.field_247] = 1;
                                    this.field_260 = true;
                                 } else if (this.field_259[this.field_246][this.field_247] == 0) {
                                    this.field_259[this.field_246][this.field_247] = 1;
                                 } else {
                                    var4 = this.field_230;
                                    int var62 = this.field_231;
                                    var11 = -1;
                                    int var65 = -1;

                                    for(int var66 = 0; var66 < this.field_230; ++var66) {
                                       for(int var30 = 0; var30 < this.field_231; ++var30) {
                                          if (this.field_259[var66][var30] > 0) {
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
                                          if (this.field_259[var67][var31] == 0) {
                                             var45 = 0;
                                          }

                                          this.field_259[var67][var31] = 1;
                                       }
                                    }

                                    if (var45) {
                                       if (var4 == 0 && var62 == 0 && var11 == this.field_230 - 1 && var65 == this.field_231 - 1) {
                                          this.method_143(this.field_259, 0);
                                          this.field_260 = false;
                                       } else {
                                          this.method_143(this.field_259, 1);
                                       }
                                    }
                                 }
                              } else if (this.field_506 == 2) {
                                 if (class_11.field_92.method_258(128)) {
                                    if (++this.field_504 >= 12) {
                                       this.field_504 = 0;
                                    }

                                    this.field_505 = null;
                                    this.field_400 = true;
                                    this.field_401 = true;
                                 } else if (class_11.field_92.method_258(64)) {
                                    if (--this.field_504 < 0) {
                                       this.field_504 = 11;
                                    }

                                    this.field_505 = null;
                                    this.field_400 = true;
                                    this.field_401 = true;
                                 } else if (class_11.field_92.method_258(16)) {
                                    class_2 var54;
                                    if ((var54 = this.method_168(this.field_246, this.field_247, (byte)0)) == null) {
                                       class_2.method_26(this.field_504, this.field_272, this.field_246, this.field_247, true);
                                    } else {
                                       var54.method_27();
                                    }
                                 } else if (class_11.field_92.method_258(512)) {
                                    class_2 var55;
                                    if ((var55 = this.method_168(this.field_246, this.field_247, (byte)0)) != null) {
                                       if (this.field_504 != var55.field_44) {
                                          this.field_504 = var55.field_44;
                                       } else {
                                          if (++this.field_504 >= 12) {
                                             this.field_504 = 0;
                                          }

                                          var55.method_27();
                                          class_2.method_26(this.field_504, this.field_272, this.field_246, this.field_247, true);
                                       }
                                    } else {
                                       class_2.method_26(this.field_504, this.field_272, this.field_246, this.field_247, true);
                                    }

                                    this.field_505 = null;
                                    this.field_400 = true;
                                    this.field_401 = true;
                                 } else if (class_11.field_92.method_258(256)) {
                                    class_2 var56;
                                    if ((var56 = this.method_168(this.field_246, this.field_247, (byte)0)) != null) {
                                       if (this.field_504 != var56.field_44) {
                                          this.field_504 = var56.field_44;
                                       } else {
                                          if (--this.field_504 < 0) {
                                             this.field_504 = 11;
                                          }

                                          var56.method_27();
                                          class_2.method_26(this.field_504, this.field_272, this.field_246, this.field_247, true);
                                       }
                                    } else {
                                       class_2.method_26(this.field_504, this.field_272, this.field_246, this.field_247, true);
                                    }

                                    this.field_505 = null;
                                    this.field_400 = true;
                                    this.field_401 = true;
                                 } else if (class_11.field_92.method_258(8192)) {
                                    if (++this.field_272 >= this.field_268) {
                                       this.field_272 = 0;
                                       this.field_506 = 1;
                                    }

                                    this.field_505 = null;
                                    this.field_400 = true;
                                    this.field_401 = true;
                                 } else if (class_11.field_92.method_258(field_199)) {
                                    this.field_280 = new class_12((byte)11, 0);
                                    this.field_280.method_100(field_183, 2, 2, -1, this.field_187, 20, 0);
                                    this.field_280.method_88(this);
                                    class_11.field_92.method_253(this.field_280);
                                 }
                              } else if (class_11.field_92.method_258(512)) {
                                 var24 = 0;
                                 class_2 var53 = this.field_274[this.field_272];
                                 if (this.field_331 != null && this.field_331.field_44 == 9) {
                                    var53 = this.field_275[this.field_272][(this.field_331.field_69 + 1) % this.field_276[this.field_272]];
                                 }

                                 while(true) {
                                    ++var24;
                                    if (var24 >= this.field_276[this.field_272] || var53.field_55 != 3) {
                                       if (var53 != null && var53.field_55 != 3) {
                                          this.method_156(var53.field_46, var53.field_47);
                                          this.method_153(var53.m + 12, var53.n + 12);
                                       }
                                       break;
                                    }

                                    var53 = this.field_275[this.field_272][(var53.field_69 + 1) % this.field_276[this.field_272]];
                                 }
                              } else if (class_11.field_92.method_258(32)) {
                                 if (this.field_261) {
                                    this.method_143(this.field_259, 0);
                                    this.field_260 = false;
                                    this.field_261 = false;
                                 } else {
                                    this.field_256 = this.method_168(this.field_246, this.field_247, (byte)0);
                                    if (this.field_256 != null) {
                                       this.method_143(this.field_259, 0);
                                       this.field_256.method_45(this.field_259);
                                       this.field_261 = true;
                                       this.field_260 = true;
                                       this.field_409 = 12;
                                    }
                                 }

                                 class_11.field_92.method_262(32);
                              } else if (class_11.field_92.method_258(16) || class_11.field_92.method_258(field_199)) {
                                 this.field_256 = this.method_168(this.field_246, this.field_247, (byte)0);
                                 if (this.field_256 != null && this.field_256.field_55 == 0 && this.field_256.field_45 == this.field_272) {
                                    byte[] var52;
                                    if ((var52 = this.method_145(this.field_256, (byte)1)).length > 1) {
                                       this.method_123(var52);
                                       class_15.method_272(11, 1);
                                    } else {
                                       this.field_325 = false;
                                       this.method_121(this.field_256);
                                    }
                                 } else if (this.method_169(this.field_246, this.field_247) == 9 && this.method_182(this.field_246, this.field_247, this.field_272)) {
                                    byte[] var51 = new byte[]{0};
                                    this.method_123(var51);
                                    class_15.method_272(11, 1);
                                 } else {
                                    this.field_256 = null;
                                    this.field_280 = new class_12((byte)11, 0);
                                    this.field_280.method_100(this.field_201, 2, 2, -1, this.field_187, 20, 0);
                                    this.field_280.method_88(this);
                                    class_11.field_92.method_253(this.field_280);
                                    class_15.method_272(11, 1);
                                 }

                                 class_11.field_92.method_257();
                              }
                           }
                        } else {
                           if (!class_11.field_92.method_258(4) && !class_11.field_92.method_258(1)) {
                              if (class_11.field_92.method_258(8) || class_11.field_92.method_258(2)) {
                                 ++this.field_254;
                                 if (this.field_254 >= this.field_255.length) {
                                    this.field_254 = 0;
                                 }

                                 class_11.field_92.method_262(8);
                                 class_11.field_92.method_262(2);
                                 this.field_307 = true;
                              }
                           } else {
                              --this.field_254;
                              if (this.field_254 < 0) {
                                 this.field_254 = this.field_255.length - 1;
                              }

                              class_11.field_92.method_262(4);
                              class_11.field_92.method_262(1);
                              this.field_307 = true;
                           }

                           this.method_156(this.field_255[this.field_254].field_46, this.field_255[this.field_254].field_47);
                           if (this.field_307) {
                              this.field_331 = this.method_168(this.field_246, this.field_247, (byte)0);
                              this.field_401 = true;
                           }

                           if (class_11.field_92.method_258(16)) {
                              if (this.field_249 == 6) {
                                 this.method_118(this.field_256, this.field_255[this.field_254]);
                              } else if (this.field_249 == 7) {
                                 this.method_148(this.field_255[this.field_254], this.field_272);
                                 this.field_256.method_56();
                                 this.field_249 = 0;
                              }

                              this.field_280 = null;
                              this.field_260 = false;
                              this.field_261 = false;
                              this.field_290 = false;
                              this.field_291 = false;
                           }

                           this.field_307 = false;
                        }
                     }
                  }
               } else if (this.field_466 != 1 && (this.field_249 != 14 || this.field_466 == 2)) {
                  if (this.field_466 == 0) {
                     this.field_298 = true;
                     this.field_299 = 0;
                  } else if (this.field_466 == 2) {
                     if (this.field_249 == 14) {
                        this.field_187 = super.field_94;
                        this.field_189 = super.field_96;
                        this.method_122(this.field_208, super.field_96, super.field_94, (class_11)null);
                        return;
                     }

                     if (this.field_252 <= 7) {
                        this.method_137(this.field_252);
                        this.method_199();
                        this.field_249 = 0;
                     }
                  }
               } else {
                  ++this.field_252;
                  if (this.field_252 > this.field_190) {
                     if (field_192[this.field_190] >= 0) {
                        String var25 = field_191[field_192[this.field_190]];
                        class_12 var26 = this.method_197((String)null, class_11.method_82(82, var25), super.field_94, 3000);
                        class_11.field_92.method_253(var26);
                     }

                     this.field_190 = this.field_252;

                     try {
                        byte[] var27 = new byte[]{(byte)this.field_190};
                        class_15.method_241("settings", 1, var27);
                     } catch (Exception var8) {
                     }
                  }

                  this.field_466 = 2;
               }

               var11 = 0;

               for(int var42 = this.field_263.size(); var11 < var42; ++var11) {
                  ((class_2)this.field_263.elementAt(var11)).method_20();
               }

               if (this.field_251 - this.field_308 >= 300L) {
                  this.field_309 = (this.field_309 + 1) % this.field_311.length;
                  this.field_233[this.field_310] = this.field_311[this.field_309];
                  this.field_308 = this.field_251;
               }

               if (this.field_405 == null) {
                  this.method_155(this.field_234.field_3 + 12, this.field_234.field_4 + 12);
               } else {
                  this.method_155(this.field_405.m + 12, this.field_405.n + 12);
               }

               if (this.field_290 && class_11.field_92.method_259(field_200)) {
                  if (this.field_249 == 1) {
                     this.field_249 = 0;
                     this.method_143(this.field_259, 0);
                     this.field_264 = null;
                     this.field_234.method_13(field_211[0]);
                     this.method_156(this.field_256.field_46, this.field_256.field_47);
                     this.field_256 = null;
                  } else if (this.field_249 == 6 || this.field_249 == 7) {
                     this.field_249 = this.field_250;
                     this.method_143(this.field_259, 0);
                     this.field_234.method_13(field_211[0]);
                     this.method_156(this.field_256.field_46, this.field_256.field_47);
                     class_11.field_92.method_253(this.field_280);
                  }

                  this.field_260 = false;
                  this.field_261 = false;
                  class_11.field_92.method_262(field_200);
                  this.field_290 = false;
                  this.field_291 = false;
               }

               for(int var20 = this.field_281.size() - 1; var20 >= 0; --var20) {
                  class_1 var43;
                  (var43 = (class_1)this.field_281.elementAt(var20)).method_20();
                  if (!var43.field_17) {
                     this.field_281.removeElement(var43);
                  }
               }

               var11 = 0;

               for(int var44 = this.field_282.size(); var11 < var44; ++var11) {
                  this.field_281.addElement(this.field_282.elementAt(var11));
               }

               this.field_282.removeAllElements();
            }
         }
      }

   }

   // $FF: renamed from: d (aeii.a) void
   private void method_147(class_2 var1) {
      int var2 = var1.n + 24;
      this.method_120(this.field_240, var1.m, var2 - this.field_240.field_7, 0, -2, 1, 100);

      for(int var3 = 0; var3 < 5; ++var3) {
         this.method_120(this.field_342, var1.m, var2 - this.field_342.field_7, var3 + -2, class_15.method_234(-4, -1), 1, 50 + 50 * class_15.method_233(4));
      }

      this.method_120(this.field_241, var1.m, var1.n, 0, 0, 1, 100);
   }

   // $FF: renamed from: a (aeii.a, byte) void
   private void method_148(class_2 var1, byte var2) {
      this.field_286 = var1;
      this.field_287 = var2;
      this.method_120(this.field_241, var1.m - 8, var1.n - 8, 1, 1, 3, 50);
      this.method_120(this.field_241, var1.m + 8, var1.n - 8, -1, 1, 3, 50);
      this.method_120(this.field_241, var1.m - 8, var1.n + 8, 1, -1, 3, 50);
      this.method_120(this.field_241, var1.m + 8, var1.n + 8, -1, -1, 3, 50);
      this.field_288 = this.field_251;
   }

   // $FF: renamed from: a (int, int) boolean
   private boolean method_149(int var1, int var2) {
      return this.field_228 == this.method_151(var1) && this.field_229 == this.method_152(var2);
   }

   // $FF: renamed from: b (int, int) boolean
   private boolean method_150(int var1, int var2) {
      return this.method_149(var1 * 24 + 12, var2 * 24 + 12);
   }

   // $FF: renamed from: b (int) int
   private int method_151(int var1) {
      if (this.field_226 > this.field_186) {
         if ((var1 = this.field_188 - var1) > 0) {
            var1 = 0;
         } else if (var1 < this.field_186 - this.field_226) {
            var1 = this.field_186 - this.field_226;
         }
      } else {
         var1 = (this.field_186 - this.field_226) / 2;
      }

      return var1;
   }

   // $FF: renamed from: c (int) int
   private int method_152(int var1) {
      if (this.field_227 > this.field_187) {
         if ((var1 = this.field_189 - var1) > 0) {
            var1 = 0;
         } else if (var1 < this.field_187 - this.field_227) {
            var1 = this.field_187 - this.field_227;
         }
      } else {
         var1 = (this.field_187 - this.field_227) / 2;
      }

      return var1;
   }

   // $FF: renamed from: a (int, int) void
   private void method_153(int var1, int var2) {
      this.field_228 = this.method_151(var1);
      this.field_229 = this.method_152(var2);
   }

   // $FF: renamed from: b (int, int) void
   private void method_154(int var1, int var2) {
      this.method_153(var1 * 24 + 12, var2 * 24 + 12);
   }

   // $FF: renamed from: c (int, int) void
   private void method_155(int var1, int var2) {
      this.field_408 = true;
      var1 = this.method_151(var1);
      var2 = this.method_152(var2);
      var1 -= this.field_228;
      var2 -= this.field_229;
      if (var1 != 0) {
         int var3 = var1 / 2;
         if (var1 < 0) {
            if (var3 > -this.field_407) {
               var3 = -this.field_407;
            } else if (var3 < -this.field_406) {
               var3 = -this.field_406;
            }
         } else if (var3 < this.field_407) {
            var3 = this.field_407;
         } else if (var3 > this.field_406) {
            var3 = this.field_406;
         }

         this.field_228 += var3;
         this.field_408 = false;
      }

      if (var2 != 0) {
         int var8 = var2 / 2;
         if (var2 < 0) {
            if (var8 > -this.field_407) {
               var8 = -this.field_407;
            } else if (var8 < -this.field_406) {
               var8 = -this.field_406;
            }
         } else if (var8 < this.field_407) {
            var8 = this.field_407;
         } else if (var8 > this.field_406) {
            var8 = this.field_406;
         }

         this.field_229 += var8;
         this.field_408 = false;
      }

   }

   // $FF: renamed from: d (int, int) void
   private void method_156(int var1, int var2) {
      this.field_246 = var1;
      this.field_247 = var2;
      this.field_234.method_11(var1 * 24, var2 * 24);
      this.field_331 = this.method_168(this.field_246, this.field_247, (byte)0);
      this.field_401 = true;
   }

   // $FF: renamed from: b (javax.microedition.lcdui.Graphics) void
   private void method_157(Graphics var1) {
      int var2 = -this.field_228 / 24;
      int var3 = -this.field_229 / 24;
      if (var2 < 0) {
         var2 = 0;
      }

      if (var3 < 0) {
         var3 = 0;
      }

      int var4 = (this.field_186 - this.field_228 - 1) / 24;
      int var5 = (this.field_187 - this.field_229 - 1) / 24;
      if (var4 >= this.field_230) {
         var4 = this.field_230 - 1;
      }

      if (var5 >= this.field_231) {
         var5 = this.field_231 - 1;
      }

      int var6;
      if (this.field_228 < 0) {
         var6 = this.field_228 % 24;
      } else {
         var6 = this.field_228;
      }

      int var7;
      if (this.field_229 < 0) {
         var7 = this.field_229 % 24;
      } else {
         var7 = this.field_229;
      }

      byte var8 = 0;
      if (this.field_261) {
         var8 = 1;
      }

      for(int var12 = var3; var12 <= var5; ++var12) {
         int var9 = var6;

         for(int var10 = var2; var10 <= var4; ++var10) {
            int var11 = this.field_248[var10][var12];
            if (!this.field_260 || this.field_259[var10][var12] == 0 || this.field_409 > 0) {
               this.field_233[var11].method_281(var1, var9, var7);
            }

            if (this.field_260 && this.field_259[var10][var12] > 0) {
               if (this.field_409 != 0) {
                  var1.clipRect(var9 + this.field_409, var7 + this.field_409, 24 - (this.field_409 << 1), 24 - (this.field_409 << 1));
               }

               var1.drawImage(this.field_403[var8][var11], var9, var7, 0);
               if (this.field_409 != 0) {
                  var1.setClip(0, 0, this.field_186, this.field_187);
               }
            }

            if ((var11 = var12 + 1) < this.field_231 && this.field_224[this.field_248[var10][var11]] == 9) {
               this.field_233[28].method_281(var1, var9, var7);
            }

            var9 += 24;
         }

         var7 += 24;
      }

   }

   // $FF: renamed from: c (javax.microedition.lcdui.Graphics) void
   private void method_158(Graphics var1) {
      var1.setFont(class_15.field_591);
      var1.setColor(0);
      var1.fillRect(0, 0, super.field_93, super.field_94);
      var1.setColor(16777215);
      class_15.method_251(var1, class_11.method_79(58), super.field_93 / 2, (super.field_94 - class_15.field_591.getHeight()) / 2, 17);
   }

   // $FF: renamed from: a (int, int, int, int) int
   public static final int method_159(int var0, int var1, int var2, int var3) {
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

   // $FF: renamed from: a (int, int, int) void
   private void method_160(int var1, int var2, int var3) {
      this.field_418 = var2;
      this.field_419 = var3;
      this.field_417 = this.field_292;

      try {
         this.field_414 = new class_16("intro" + var1, this.field_186, -1);
      } catch (Exception var4) {
      }

      this.field_410 = class_15.field_594;
      if (this.field_414 != null) {
         this.field_420 = this.field_414.field_635;
         this.field_299 = 0;
         this.field_412 = (super.field_94 - this.field_414.field_635) / this.field_410;
      } else {
         this.field_420 = 0;
         this.field_299 = 16;
         this.field_412 = super.field_94 / this.field_410;
      }

      this.field_415 = class_11.method_75(class_11.method_79(var1 + 215), super.field_93, class_15.field_590);
      this.field_411 = super.field_94 - this.field_410;
      this.field_413 = 0;
      this.field_416 = false;
      class_11.field_92.method_257();
      this.field_292 = 3;
   }

   // $FF: renamed from: a (java.lang.String) void
   private void method_161(String var1) {
      this.field_417 = this.field_292;
      this.field_415 = class_11.method_75(var1, super.field_93, class_15.field_590);
      this.field_299 = 16;
      this.field_416 = false;
      this.field_418 = 3;
      this.field_419 = 3;
      this.field_420 = 0;
      this.field_410 = class_15.field_594;
      this.field_412 = super.field_94 / this.field_410;
      this.field_411 = super.field_94 - this.field_410;
      this.field_413 = 0;
      class_11.field_92.method_257();
      this.field_292 = 3;
   }

   // $FF: renamed from: d (javax.microedition.lcdui.Graphics) void
   private void method_162(Graphics var1) {
      var1.setFont(class_15.field_590);
      var1.setColor(0);
      var1.fillRect(0, 0, super.field_93, super.field_94);
      if (this.field_414 != null) {
         if (this.field_416 || (this.field_418 != 2 || this.field_299 < 40) && (this.field_418 == 2 || this.field_299 < 16)) {
            if ((this.field_419 != 2 || !this.field_416) && (this.field_418 != 2 || this.field_416)) {
               if ((this.field_419 != 3 || !this.field_416) && (this.field_418 != 3 || this.field_416)) {
                  this.field_414.method_281(var1, 0, 0);
                  if (this.field_416) {
                     if (this.field_419 == 1 && this.field_299 <= 16) {
                        method_187(var1, 0, 16 - this.field_299, 16, 0, 0, 0, super.field_93, super.field_94);
                     }
                  } else if (this.field_418 == 0) {
                     if (this.field_299 <= 16) {
                        method_187(var1, 16777215, this.field_299, 16, 1, 0, 0, super.field_93, super.field_94);
                     }
                  } else if (this.field_418 == 1 && this.field_299 <= 16) {
                     method_187(var1, 0, this.field_299, 16, 1, 0, 0, super.field_93, this.field_414.field_635);
                  }
               } else {
                  this.field_414.method_281(var1, 0, 0);
                  int var2 = 255 * (16 - this.field_299) / 16;
                  class_15.method_248(var1, var2 << 24, 0, 0, this.field_414.field_634, this.field_414.field_635);
               }
            } else {
               method_186(var1, this.field_299, 40, 0, this.field_414, 0, 0, 2);
            }
         } else {
            this.field_414.method_281(var1, 0, 0);
         }
      }

      var1.setClip(0, 0, super.field_93, super.field_94);
      int var9 = this.field_299;
      if (this.field_416 && this.field_419 == 2) {
         var9 -= 24;
         if (var9 < 0) {
            var9 = 0;
         }
      }

      int var3 = this.field_411;

      for(int var4 = this.field_413; var4 < this.field_413 + this.field_412 && var4 < this.field_415.length && var3 < super.field_94 - this.field_410; ++var4) {
         int var5 = this.field_410;
         if (var3 < this.field_420 + this.field_410) {
            var5 = var3 - this.field_420;
         } else if (var3 + this.field_410 > super.field_94 - this.field_410) {
            var5 = super.field_94 - this.field_410 - var3;
         }

         if (var5 < this.field_410) {
            int var7 = this.field_410;
            int var10 = 14672074;
            int var11 = var5 * 14614528 / var7 & 16711680;
            int var8 = var5 * '\ue000' / var7 & '\uff00';
            int var13 = var5 * 202 / var7;
            var5 = var11 | var8 | var13;
         } else {
            var5 = 14672074;
         }

         if (this.field_416) {
            var5 = method_159(0, var5, var9, 16);
         }

         var1.setColor(var5);
         class_15.method_251(var1, this.field_415[var4], super.field_95, var3 + 3, 17);
         var3 += this.field_410;
      }

      if (!this.field_416) {
         this.method_167(var1, field_199, 2, super.field_94);
      }

   }

   // $FF: renamed from: e (javax.microedition.lcdui.Graphics) void
   private void method_163(Graphics var1) {
      if (this.field_297 == 0) {
         var1.setColor(16777215);
         var1.fillRect(0, 0, super.field_93, super.field_94);
         method_186(var1, this.field_299, 40, 0, this.field_293, (super.field_93 - this.field_293.field_634) / 2, (super.field_94 - this.field_293.field_635) / 2, 4);
      } else if (this.field_297 == 1) {
         var1.setColor(16777215);
         var1.fillRect(0, 0, super.field_93, super.field_94);
         method_186(var1, this.field_299, 40, 0, this.field_293, (super.field_93 - this.field_293.field_634) / 2, (super.field_94 - this.field_293.field_635) / 2, 4);
      } else if (this.field_297 == 3) {
         var1.setColor(0);
         var1.fillRect(0, 0, super.field_93, super.field_94);
         method_186(var1, this.field_299, 40, 0, this.field_295, 0, 0, 1);
         var1.setClip(0, 0, super.field_93, super.field_94);
      } else {
         if (this.field_297 == 4) {
            if (this.field_299 >= 16) {
               if (this.field_294 == null) {
                  var1.setColor(0);
                  var1.fillRect(0, 0, super.field_93, super.field_94);
                  this.field_295.method_281(var1, 0, 0);
               } else {
                  this.field_295.method_281(var1, 0, 0);
                  if (this.field_336 != -1) {
                     int var2 = (this.field_295.field_635 + this.field_296.field_635 - 1) / this.field_296.field_635;

                     for(int var3 = 0; var3 < var2; ++var3) {
                        this.field_296.method_281(var1, 4 + this.field_336 - var3 * 38, 6 + this.field_296.field_635 * var3);
                     }
                  }

                  this.field_294.method_281(var1, 0, 0);
               }

               if (this.field_313 && this.field_294 != null) {
                  var1.setColor(16777215);
                  var1.setFont(class_15.field_591);
                  method_164(var1, class_11.method_79(59), super.field_95, super.field_94 - this.field_238.field_7 - 1, 33, 16777215, 0);
                  return;
               }
            } else {
               if (this.field_294 != null) {
                  this.field_294.method_281(var1, 0, 0);
               } else {
                  var1.setColor(0);
                  var1.fillRect(0, 0, super.field_93, super.field_94);
               }

               class_15.method_248(var1, 255 * (16 - this.field_299) / 16 << 24, 0, 0, super.field_93, super.field_94);
               this.field_295.method_281(var1, 0, 0);
            }
         }

      }
   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics, java.lang.String, int, int, int, int, int) void
   public static final void method_164(Graphics var0, String var1, int var2, int var3, int var4, int var5, int var6) {
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

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics) void
   public final void method_74(Graphics var1) {
      if (this.field_292 == 4) {
         var1.setColor(16777215);
         var1.fillRect(0, 0, super.field_93, super.field_94);
         var1.setFont(class_15.field_591);
         var1.setColor(0);
         var1.drawString(class_11.method_79(58), super.field_93 / 2, super.field_94 / 2 - 1, 33);
         int var21;
         if ((var21 = super.field_94 / 18) < 12) {
            var21 = 12;
         }

         int var31 = super.field_94 / 2 + 1;
         var1.setColor(13553358);
         class_12.method_104(var1, 1, var31, super.field_93 - 2, var21);
         var1.setColor(2370117);
         class_12.method_104(var1, 2, var31 + 2, this.field_404 * (super.field_93 - 6) / 100, var21 - 4);
      } else if (this.field_292 == 2) {
         Graphics var20 = var1;
         class_13 var12 = this;
         int var29 = 0;
         int var35 = 0;
         if (this.field_472) {
            var29 = class_15.method_235() % 10;
            var35 = class_15.method_235() % 3;
         }

         var1.translate(0, this.field_423);
         var1.setClip(0, 0, this.field_186, this.field_479);
         this.field_467.method_230(var1, var29, var35);
         this.field_468.method_230(var1, var29 + this.field_188, var35);
         var1.setColor(0);
         var1.fillRect(this.field_188 - 1 + var29, 0, 2, this.field_479);
         this.field_467.method_232(var1);
         this.field_468.method_232(var1);
         Vector var30 = new Vector(this.field_471.size());

         for(int var36 = 0; var36 < var12.field_471.size(); ++var36) {
            class_1 var42 = (class_1)var12.field_471.elementAt(var36);
            int var47 = 0;
            if (var42.field_19) {
               var30.addElement(var42);
            } else {
               for(var47 = 0; var47 < var30.size(); ++var47) {
                  class_1 var49;
                  if ((var49 = (class_1)var30.elementAt(var47)).field_19 || var42.field_4 + var42.field_7 < var49.field_4 + var49.field_7) {
                     var30.insertElementAt(var42, var47);
                     break;
                  }
               }
            }

            if (var47 == var30.size()) {
               var30.addElement(var42);
            }
         }

         var12.field_471 = var30;

         for(int var37 = 0; var37 < var12.field_471.size(); ++var37) {
            class_1 var43;
            if ((var43 = (class_1)var12.field_471.elementAt(var37)).field_21 == 0) {
               var20.setClip(0, 0, var12.field_188, var12.field_479);
            } else if (var43.field_21 == 1) {
               var20.setClip(var12.field_188, 0, var12.field_188, var12.field_479);
            } else {
               var20.setClip(0, 0, var12.field_186, var12.field_479);
            }

            var43.method_17(var20, 0, var43.field_9);
         }

         var20.translate(0, -var12.field_423);
         var20.setClip(0, 0, var12.field_93, var12.field_94);
         if (var12.field_478) {
            var12.field_478 = false;
            var35 = var12.field_94 - field_185;
            var20.setColor(14672074);
            var20.fillRect(0, var35, var12.field_93, field_185);
            class_12.method_106(var20, 0, var35, var12.field_93, field_185, 0);
            var20.setClip(0, 0, var12.field_93, var12.field_94);
            var12.field_467.method_231(var20);
            var20.translate(var12.field_188, 0);
            var12.field_468.method_231(var20);
            var20.translate(-var12.field_188, 0);
         }

         if (var12.field_477) {
            var12.field_477 = false;
            var12.method_166(var20, var12.field_467.field_543, var12.field_468.field_543, 0);
         }

         if (var12.field_476) {
            method_187(var20, 0, var12.field_299, 16, 1, 0, 0, var12.field_93, var12.field_94);
         }

      } else if (this.field_292 == 3) {
         this.method_162(var1);
      } else if (this.field_312) {
         this.method_158(var1);
      } else if (this.field_298) {
         if (this.field_299 >= 16) {
            if (this.field_249 != 11 && this.field_249 != 10) {
               this.method_158(var1);
            } else {
               var1.setColor(0);
               var1.fillRect(0, 0, super.field_93, super.field_94);
            }
         } else {
            method_187(var1, 0, this.field_299, 16, 0, 0, 0, super.field_93, super.field_94);
         }
      } else {
         if (this.field_292 == 0) {
            this.method_163(var1);
         } else if (this.field_249 == 14) {
            var1.setClip(0, 0, super.field_93, super.field_94);
            var1.setColor(0);
            var1.fillRect(0, 0, super.field_93, super.field_94);
         } else if (this.field_249 == 10 && this.field_466 > 0) {
            this.method_158(var1);
         } else if (this.field_249 == 11 && !this.field_298) {
            String var34 = class_11.method_79(57);
            var1.setClip(0, 0, super.field_93, super.field_94);
            var1.setFont(class_15.field_591);
            var1.setColor(0);
            var1.fillRect(0, 0, super.field_93, super.field_94);
            if (this.field_198 == 0) {
               var1.setColor(16777215);
               if (this.field_402 != null) {
                  this.field_402.method_282(var1, super.field_95, super.field_96, 3);
                  class_15.method_251(var1, var34, super.field_95, super.field_94 - 2, 33);
               } else {
                  int var27 = super.field_96 - class_15.field_591.getHeight() / 2;
                  class_15.method_251(var1, var34, super.field_95, var27, 17);
               }
            }
         } else {
            var1.setClip(0, 0, this.field_186, this.field_187);
            if (this.field_226 < this.field_186 || this.field_227 < this.field_187) {
               var1.setColor(0);
               var1.fillRect(0, 0, this.field_186, this.field_187);
            }

            if (this.field_472) {
               int var2 = class_15.method_235() % 10;
               int var3 = class_15.method_235() % 4;
               var1.translate(var2, var3);
               this.method_157(var1);
               var1.translate(-var2, -var3);
            } else {
               this.method_157(var1);
            }

            int var13 = 0;

            for(int var22 = this.field_263.size(); var13 < var22; ++var13) {
               class_2 var4;
               if ((var4 = (class_2)this.field_263.elementAt(var13)).field_55 == 3) {
                  this.field_232.method_281(var1, this.field_228 + var4.m, this.field_229 + var4.n);
               } else if (var4 != this.field_256) {
                  var4.method_17(var1, this.field_228, this.field_229);
               }
            }

            var13 = 0;

            for(int var23 = this.field_263.size(); var13 < var23; ++var13) {
               ((class_2)this.field_263.elementAt(var13)).method_59(var1, this.field_228, this.field_229);
            }

            if (this.field_264 != null) {
               var1.setColor(14745682);
               int var24 = 12 + this.field_424 / 4;
               int var7 = 24 - var24;
               int var32 = 0;

               for(int var5 = this.field_264.size(); var32 < var5; ++var32) {
                  short[] var8;
                  int var6 = (var8 = (short[])this.field_264.elementAt(var32))[0] * 24 + this.field_228;
                  var13 = var8[1] * 24 + this.field_229;
                  int var9 = var6 + 12;
                  int var10 = var13 + 12;
                  if (var32 != 0) {
                     short[] var11;
                     if ((var11 = (short[])this.field_264.elementAt(var32 - 1))[0] == var8[0] + 1) {
                        var1.fillRect(var6 + var7, var10 - this.field_425, var24, this.field_424);
                     } else if (var11[0] == var8[0] - 1) {
                        var1.fillRect(var6, var10 - this.field_425, var24, this.field_424);
                     } else if (var11[1] == var8[1] + 1) {
                        var1.fillRect(var9 - this.field_425, var13 + var7, this.field_424, var24);
                     } else if (var11[1] == var8[1] - 1) {
                        var1.fillRect(var9 - this.field_425, var13, this.field_424, var24);
                     }
                  }

                  if (var32 == var5 - 1) {
                     var1.setClip(0, 0, this.field_186, this.field_187);
                     this.field_235.method_16(var1, var9, var10, 3);
                  } else {
                     short[] var53;
                     if ((var53 = (short[])this.field_264.elementAt(var32 + 1))[0] == var8[0] + 1) {
                        var1.fillRect(var6 + var7, var10 - this.field_425, var24, this.field_424);
                     } else if (var53[0] == var8[0] - 1) {
                        var1.fillRect(var6, var10 - this.field_425, var24, this.field_424);
                     } else if (var53[1] == var8[1] + 1) {
                        var1.fillRect(var9 - this.field_425, var13 + var7, this.field_424, var24);
                     } else if (var53[1] == var8[1] - 1) {
                        var1.fillRect(var9 - this.field_425, var13, this.field_424, var24);
                     }
                  }
               }
            }

            if (this.field_256 != null) {
               this.field_256.method_17(var1, this.field_228, this.field_229);
               this.field_256.method_59(var1, this.field_228, this.field_229);
            }

            if (this.field_262) {
               this.field_234.method_16(var1, this.field_228 + 12, this.field_229 + 12, 3);
            }

            var13 = 0;

            for(int var25 = this.field_281.size(); var13 < var25; ++var13) {
               class_1 var50;
               (var50 = (class_1)this.field_281.elementAt(var13)).method_17(var1, this.field_228, this.field_229 + var50.field_9);
            }

            var1.setClip(0, 0, super.field_93, super.field_94);
            var13 = super.field_94 - field_185;
            if (this.field_338 > 0) {
               class_12.method_106(var1, 0, var13, this.field_186, field_185, 14);
               var1.setClip(0, 0, super.field_93, super.field_94);
            }

            int var26 = field_185 - 24 >> 1;
            int var48 = 24 + (var26 << 1);
            int var33 = super.field_93 - var48;
            var13 += this.field_338;
            if (this.field_400) {
               this.field_400 = false;
               int var39 = super.field_94 - field_185 / 2 + this.field_338;
               byte var51 = 10;
               if (this.field_186 <= 120) {
                  var51 = 4;
               }

               if (this.field_506 == 1) {
                  class_12.method_107(var1, 0, var13, var33 + 1, field_185, 0, 2370117, field_216[0], this.field_338, field_185);
                  class_15.method_249(var1, this.field_503 + 1 + "/" + this.field_233.length, var51 + 1, var39, 1, 6);
               } else if (this.field_506 == 2) {
                  class_12.method_107(var1, 0, var13, var33 + 1, field_185, 0, 2370117, field_216[this.field_270[this.field_272]], this.field_338, field_185);
                  class_15.method_249(var1, this.field_504 + 1 + "/" + 12, var51 + 1, var39, 1, 6);
               } else {
                  class_12.method_107(var1, 0, var13, var33 + 1, field_185, 0, 2370117, field_216[this.field_270[this.field_272]], this.field_338, field_185);
                  if (this.field_198 == 1) {
                     int var44 = var33 / 2;
                     this.field_333.method_15(var1, 0, var44, var39, 6);
                     class_15.method_249(var1, this.method_184(-1, -1, this.field_272) - this.method_184(10, -1, this.field_272) + "/" + this.field_197, var44 + this.field_333.field_6 + 1, var39, 1, 6);
                  }

                  this.field_333.method_15(var1, 1, var51, var39, 6);
                  int var45 = var51 + this.field_333.field_6 + 1;
                  if (!class_15.field_588 && this.field_279[this.field_272] != 1) {
                     class_15.method_249(var1, "- - -", var45, var39, 1, 6);
                  } else {
                     class_15.method_249(var1, "" + this.field_277[this.field_272], var45, var39, 1, 6);
                  }
               }

               var1.setClip(0, 0, super.field_93, super.field_94);
            }

            if (this.field_401) {
               this.field_401 = false;
               if (var26 > 0) {
                  method_165(var1, var33, var13, var48, field_185);
               }

               int var40 = var33 + var26;
               int var46 = var13 + var26;
               if (this.field_506 == 0 || this.field_506 == 2) {
                  this.field_503 = this.field_248[this.field_246][this.field_247];
               }

               this.field_233[this.field_503].method_281(var1, var40, var46);
               if (this.field_506 == 2) {
                  if (this.field_505 == null) {
                     this.field_505 = class_2.method_26(this.field_504, this.field_272, 0, 0, false);
                  }

                  this.field_505.method_17(var1, var40, var46);
               }

               if (this.field_506 == 0) {
                  String var52 = "." + field_221[this.field_224[this.field_503]];
                  class_15.method_249(var1, var52, var40 + 24, var46 + 24, 0, 40);
               }

               if (var26 == 0) {
                  var1.setColor(0);
                  var1.drawRect(var40, var46, 24, 24);
               }
            }

            if (this.field_249 == 6 && this.field_255[this.field_254].field_55 != 4) {
               int var41 = 0;
               if (this.field_247 * 24 <= this.field_187 / 2 - 24) {
                  var41 = this.field_187 - this.field_238.field_7 - this.field_423 + 2;
               }

               this.method_166(var1, this.field_256, this.field_255[this.field_254], var41);
            }
         }

         if (this.method_111()) {
            if (this.field_290) {
               this.method_167(var1, field_200, 1, this.field_187);
            }

            if (this.field_291) {
               this.method_167(var1, field_199, 0, this.field_187);
            }

            if (this.field_292 == 1 && (this.field_279[this.field_272] == 0 || this.field_249 == 0) && this.field_249 != 11) {
               this.method_167(var1, field_199, 3, this.field_187);
            }
         }

         if (this.field_353 || this.field_354) {
            int var19 = this.field_352;
            if (this.field_354) {
               var19 = 16 - this.field_352;
            }

            int var28 = var19 * 255 / 16;
            class_15.method_248(var1, var28 << 24, 0, 0, super.field_93, super.field_94);
         }

      }
   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics, int, int, int, int) void
   public static void method_165(Graphics var0, int var1, int var2, int var3, int var4) {
      var0.setColor(4344163);
      var0.fillRect(var1, var2, var3, var4);
      var0.setColor(11384493);
      var0.fillRect(var1 + 1, var2 + 1, var3 - 2, var4 - 2);
      var0.setColor(4344163);
      var0.fillRect(var1 + 3, var2 + 3, var3 - 6, var4 - 6);
   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics, aeii.a, aeii.a, int) void
   private void method_166(Graphics var1, class_2 var2, class_2 var3, int var4) {
      int var5 = this.field_423 - 2;
      var1.setColor(11384493);
      var1.fillRect(0, var4, this.field_186, var5);
      var1.setColor(0);
      var1.fillRect(0, var5 + var4, this.field_186, 2);
      int var6 = 0;
      int var17;
      int var7 = (var17 = var5 / 2) + var4;

      for(int var8 = 0; var8 < 3; ++var8) {
         this.field_332.method_15(var1, var8, var6 + 1, var7, 6);
         var6 += this.field_332.field_6 + 2;
         int var9 = var4 + 1;
         int var10;
         if (var8 == 0) {
            if (super.field_93 <= 132) {
               var10 = 56;
            } else {
               var10 = 61 * this.field_186 / 176;
            }
         } else if (var8 == 1) {
            if (super.field_93 <= 132) {
               var10 = 28;
            } else {
               var10 = 47 * this.field_186 / 176;
            }
         } else {
            var10 = this.field_186 - var6;
         }

         for(int var11 = 0; var11 < 2; ++var11) {
            class_2 var12;
            class_2 var13;
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
            if (var8 == 0 || super.field_93 > 132) {
               var1.setColor(field_216[this.field_270[var12.field_45]]);
               var1.fillRect(var15, var9 + 1, 3, var14 - 2);
               var15 += 4;
            }

            int var16 = 0;
            String var19;
            if (var8 == 0) {
               if (var11 != 0 && !var3.method_37(var2.field_46, var2.field_47)) {
                  var19 = "0-0";
               } else {
                  var16 = var12.method_29(var13);
                  var19 = var12.field_50 + var16 + "-" + (var12.field_51 + var16);
               }
            } else if (var8 == 1) {
               var16 = var12.method_31(var13);
               var19 = "" + (var12.field_52 + var16);
            } else {
               var19 = "" + var12.field_38;
            }

            class_15.method_250(var1, var19, var15, var9 + 1, 0);
            if (var16 > 0) {
               this.field_364.method_15(var1, 1, var15 + 1 + class_15.method_245((byte)0, var19), var9 + var14 / 2, 6);
            } else if (var16 < 0) {
               this.field_364.method_15(var1, 2, var15 + 1 + class_15.method_245((byte)0, var19), var9 + var14 / 2, 6);
            }

            int var20 = var6 + var10 - 2;
            if (var8 == 0 && (var12.field_56 & 2) != 0) {
               this.field_243.method_15(var1, 1, var20, var9 + var14 / 2, 10);
               var20 -= this.field_243.field_6;
            }

            if ((var8 == 0 || var8 == 1) && (var12.field_56 & 1) != 0) {
               this.field_243.method_15(var1, 0, var20, var9 + var14 / 2, 10);
            }

            var9 += var17;
         }

         var6 += var10;
      }

   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics, int, int, int) void
   public final void method_167(Graphics var1, int var2, int var3, int var4) {
      int var5 = 0;
      byte var6 = 0;
      if (var2 == 1024) {
         var6 = 36;
      } else if (var2 == 2048) {
         var5 = super.field_93;
         var6 = 40;
      }

      this.field_238.method_15(var1, var3, var5, var4, var6);
   }

   // $FF: renamed from: a (int, int, byte) aeii.a
   public final class_2 method_168(int var1, int var2, byte var3) {
      int var4 = 0;

      for(int var5 = this.field_263.size(); var4 < var5; ++var4) {
         int var6;
         int var7;
         class_2 var8;
         if ((var8 = (class_2)this.field_263.elementAt(var4)).field_55 == 1) {
            var6 = var8.field_48;
            var7 = var8.field_49;
         } else {
            var6 = var8.field_46;
            var7 = var8.field_47;
         }

         if (var1 == var6 && var2 == var7) {
            if (var3 == 0) {
               if (var8.field_55 != 3) {
                  return var8;
               }
            } else if (var3 == 1 && var8.field_55 == 3) {
               return var8;
            }
         }
      }

      return null;
   }

   // $FF: renamed from: a (int, int) byte
   public final byte method_169(int var1, int var2) {
      return this.field_224[this.field_248[var1][var2]];
   }

   // $FF: renamed from: m () void
   private void method_170() {
      class_15.method_271();
      this.field_266 = 0;
      this.field_338 = 0;
      this.field_249 = 8;
      this.field_267 = this.field_251;
   }

   // $FF: renamed from: n () void
   private void method_171() {
      while(this.field_506 == 0) {
         this.field_278[this.field_272][0] = (byte)this.field_246;
         this.field_278[this.field_272][1] = (byte)this.field_247;
         ++this.field_273;
         this.field_272 = (byte)((this.field_272 + 1) % this.field_268);
         if (this.field_279[this.field_272] == 2) {
            this.method_171();
            return;
         }

         for(int var1 = this.field_263.size() - 1; var1 >= 0; --var1) {
            class_2 var2;
            if ((var2 = (class_2)this.field_263.elementAt(var1)).field_55 == 3) {
               if (var2.field_44 != 9 && --var2.field_64 <= 0) {
                  var2.method_27();
               }
            } else {
               var2.field_55 = 0;
               if ((var2.field_56 & 1) != 0 && var2.field_65 == this.field_272) {
                  var2.method_39((byte)1);
               }

               if (var2.field_45 == this.field_272) {
                  var2.method_39((byte)2);
               }

               var2.field_71 = 0;
            }
         }

         this.field_426 = 0;

         for(int var3 = 0; var3 < this.field_248.length; ++var3) {
            for(int var5 = 0; var5 < this.field_248[var3].length; ++var5) {
               if (this.method_182(var3, var5, this.field_272)) {
                  if (this.method_169(var3, var5) == 8) {
                     this.field_426 += 30;
                  } else if (this.method_169(var3, var5) == 9) {
                     this.field_426 += 50;
                  }
               }
            }
         }

         int[] var10000 = this.field_277;
         byte var10001 = this.field_272;
         var10000[var10001] += this.field_426;

         for(int var4 = 0; var4 < this.field_327.length; ++var4) {
            this.field_440[var4] = 0;
         }

         if (this.field_279[this.field_272] == 1) {
            this.method_156(this.field_278[this.field_272][0], this.field_278[this.field_272][1]);
         }

         this.field_307 = true;
         this.field_400 = true;
         if (this.field_279[this.field_272] == 0) {
            this.method_190();
         } else {
            class_2.field_35 = class_2.field_34;
         }

         if (this.method_184(-1, 0, this.field_272) > 0 || this.method_183(this.field_272) != 0) {
            return;
         }

         this = this;
      }

   }

   // $FF: renamed from: a (aeii.a) boolean
   private boolean method_172(class_2 var1) {
      return var1.method_55((short)8) && this.method_169(var1.field_46, var1.field_47) == 8 && !method_177(this.field_248[var1.field_46][var1.field_47]);
   }

   // $FF: renamed from: b (aeii.a) boolean
   private boolean method_173(class_2 var1) {
      if (var1.method_55((short)8) && this.method_169(var1.field_46, var1.field_47) == 8 && method_177(this.field_248[var1.field_46][var1.field_47]) && !this.method_181(var1.field_46, var1.field_47, this.field_271[var1.field_45])) {
         return true;
      } else {
         return var1.method_55((short)16) && this.method_169(var1.field_46, var1.field_47) == 9 && !this.method_181(var1.field_46, var1.field_47, this.field_271[var1.field_45]);
      }
   }

   // $FF: renamed from: a (byte, int, int) void
   private void method_174(byte var1, int var2, int var3) {
      this.field_248[var2][var3] = var1;
   }

   // $FF: renamed from: b (int, int, int) void
   private void method_175(int var1, int var2, int var3) {
      if (method_177(this.field_248[var1][var2])) {
         this.method_174((byte)(37 + (var3 << 1) + (this.field_248[var1][var2] - 37) % 2), var1, var2);
      }

   }

   // $FF: renamed from: b (byte) boolean
   private static boolean method_176(byte var0) {
      return var0 >= 37 && var0 < 47 || var0 == 27;
   }

   // $FF: renamed from: a (byte) boolean
   public static boolean method_177(byte var0) {
      return var0 >= 37 && var0 < 47;
   }

   // $FF: renamed from: a (int, int) int
   private int method_178(int var1, int var2) {
      return method_179(var1, var2, this.field_248);
   }

   // $FF: renamed from: a (int, int, byte[][]) int
   private static int method_179(int var0, int var1, byte[][] var2) {
      int var3;
      return (var3 = var2[var0][var1] - 37 >> 1) >= 0 && var3 <= 4 ? var3 : -1;
   }

   // $FF: renamed from: d (int) int
   private int method_180(int var1) {
      return var1 != -1 && var1 != 0 ? this.field_269[var1] : -1;
   }

   // $FF: renamed from: a (int, int, int) boolean
   public final boolean method_181(int var1, int var2, int var3) {
      if ((var1 = this.method_180(this.method_178(var1, var2))) >= 0) {
         return var3 == this.field_271[var1];
      } else {
         return false;
      }
   }

   // $FF: renamed from: b (int, int, int) boolean
   private boolean method_182(int var1, int var2, int var3) {
      return this.method_178(var1, var2) == this.field_270[var3];
   }

   // $FF: renamed from: e (int) int
   private int method_183(int var1) {
      int var2 = 0;

      for(int var3 = 0; var3 < this.field_443; ++var3) {
         if (this.method_182(this.field_328[var3][0], this.field_328[var3][1], var1)) {
            ++var2;
         }
      }

      return var2;
   }

   // $FF: renamed from: a (int, int, byte) int
   private int method_184(int var1, int var2, byte var3) {
      int var4 = 0;
      int var5 = 0;

      for(int var6 = this.field_263.size(); var5 < var6; ++var5) {
         class_2 var7 = (class_2)this.field_263.elementAt(var5);
         if ((var1 == -1 || var7.field_44 == var1) && (var2 == -1 && var7.field_55 != 3 || var2 == var7.field_55) && (var3 == -1 || var7.field_45 == var3)) {
            ++var4;
         }
      }

      return var4;
   }

   // $FF: renamed from: a (int, int, byte) aeii.a[]
   private class_2[] method_185(int var1, int var2, byte var3) {
      Vector var4 = new Vector();
      int var5 = 0;

      for(int var6 = this.field_263.size(); var5 < var6; ++var5) {
         class_2 var7 = (class_2)this.field_263.elementAt(var5);
         if ((var1 == -1 || var7.field_44 == var1) && (var2 == -1 && var7.field_55 != 3 || var2 == var7.field_55) && (var3 == -1 || var7.field_45 == var3)) {
            var4.addElement(var7);
         }
      }

      class_2[] var8 = new class_2[var4.size()];
      var4.copyInto(var8);
      return var8;
   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics, int, int, int, aeii.d, int, int, int) void
   private static void method_186(Graphics var0, int var1, int var2, int var3, class_16 var4, int var5, int var6, int var7) {
      var2 = var4.field_634;
      var3 = var4.field_635;
      int var8 = var2 / 2;
      int var16 = var3;
      int var9 = var2 * var1 / 40;
      var2 = var2 * (40 - var1) / 160;
      var1 = var1 * 360 / 40;
      var7 = var7 * 360 / var3;

      for(int var10 = 0; var10 < var16; ++var10) {
         int var11 = var2 * class_11.method_85(var1) >> 10;
         var0.setClip(var5 + var8 - var9 / 2 + var11, var6 + var10, var9, 1);
         var4.method_281(var0, var5 + var11, var6);
         var1 += var7;
      }

   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics, int, int, int, int, int, int, int, int) void
   private static void method_187(Graphics var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
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
         class_15.method_247(var0, var9 << 24 | var1);

         for(int var15 = 0; var15 <= 0; ++var15) {
            var0.fillRect(var7 + 0, 0, var6, var8);
         }
      }

   }

   // $FF: renamed from: a (byte, int, int) boolean
   private boolean method_188(byte var1, int var2, int var3) {
      if (this.field_197 > this.method_184(-1, -1, this.field_272) - this.method_184(10, -1, this.field_272) && var1 <= this.field_321 && class_2.field_80[var1] <= this.field_277[this.field_272] && class_2.field_80[var1] > 0) {
         this.method_143(this.field_259, 0);
         return class_2.method_52(this.field_259, var2, var3, class_2.field_74[var1], -1, var1, this.field_272, true);
      } else {
         return false;
      }
   }

   // $FF: renamed from: a (aeii.a, int, int) boolean
   private boolean method_189(class_2 var1, int var2, int var3) {
      if (this.field_197 > this.method_184(-1, -1, this.field_272) - this.method_184(10, -1, this.field_272) && (var1.field_44 <= this.field_321 || var1.field_44 == 9) && var1.field_70 <= this.field_277[this.field_272]) {
         this.method_143(this.field_259, 0);
         return class_2.method_52(this.field_259, var2, var3, class_2.field_74[var1.field_44], -1, var1.field_44, this.field_272, true);
      } else {
         return false;
      }
   }

   // $FF: renamed from: o () void
   private void method_190() {
      class_2[] var1 = this.method_185(-1, 0, this.field_272);
      this.field_448 = new Vector(var1.length);

      for(int var2 = 0; var2 < var1.length; ++var2) {
         int var3;
         for(var3 = 0; var3 < var2; ++var3) {
            class_2 var4 = (class_2)this.field_448.elementAt(var3);
            byte var5 = field_427[var4.field_44];
            byte var6;
            if ((var6 = field_427[var1[var2].field_44]) < var5 || var6 == var5 && var1[var2].field_54 < var4.field_54) {
               this.field_448.insertElementAt(var1[var2], var3);
               break;
            }
         }

         if (var3 == var2) {
            this.field_448.addElement(var1[var2]);
         }
      }

      this.field_437 = new class_2[this.field_327.length];
      this.field_438 = new byte[this.field_327.length];
      class_2.field_35 = class_2.field_33;
      this.field_434 = 0;
      this.field_428 = 0;
   }

   // $FF: renamed from: p () void
   private void method_191() {
      if (class_11.field_92.method_258(field_199)) {
         this.method_122(this.field_209, this.field_189, this.field_187, this);
         class_11.field_92.method_257();
      } else if (this.field_428 == 4) {
         if (this.field_431 == null && this.field_432 == null) {
            if (this.method_173(this.field_256)) {
               int var15 = this.method_194(this.field_256.field_46, this.field_256.field_47);
               if (this.field_447 != -1 && this.field_447 != var15) {
                  this.field_437[this.field_447] = this.field_437[var15];
                  this.field_437[var15] = this.field_256;
               }

               this.method_175(this.field_256.field_46, this.field_256.field_47, this.field_270[this.field_256.field_45]);
               class_11.field_92.method_253(this.method_197((String)null, class_11.method_79(73), this.field_187, 1000));
               class_15.method_272(9, 1);
               this.field_249 = 9;
               this.field_267 = this.field_251;
            } else if (this.method_172(this.field_256)) {
               int var16 = this.method_194(this.field_256.field_46, this.field_256.field_47);
               if (this.field_447 != -1 && this.field_447 != var16) {
                  this.field_437[this.field_447] = this.field_437[var16];
                  this.field_437[var16] = this.field_256;
               }

               this.method_174((byte)37, this.field_256.field_46, this.field_256.field_47);
               class_11.field_92.method_253(this.method_197((String)null, class_11.method_79(74), this.field_187, 1000));
               class_15.method_272(9, 1);
               this.field_249 = 0;
               this.field_267 = this.field_251;
            } else {
               this.field_249 = 0;
            }

            this.field_256.method_56();
            this.field_256 = null;
            this.field_428 = 0;
         } else {
            this.field_428 = 5;
            this.field_256.method_44(this.field_259, this.field_256.field_46, this.field_256.field_47);
            this.field_261 = true;
            this.field_260 = true;
            this.field_435 = this.field_251;
            if (this.field_431 != null) {
               this.field_234.method_13(field_211[1]);
               this.method_156(this.field_431.field_46, this.field_431.field_47);
            } else if (this.field_432 != null) {
               this.method_156(this.field_432.field_46, this.field_432.field_47);
            }
         }

         this.field_262 = true;
      } else {
         if (this.field_428 == 5) {
            if (this.field_251 - this.field_435 >= 500L) {
               if (this.field_431 != null) {
                  this.method_118(this.field_256, this.field_431);
               } else if (this.field_432 != null) {
                  this.method_148(this.field_432, this.field_272);
                  this.field_432 = null;
                  this.field_428 = 7;
                  this.field_256.method_56();
               }

               this.field_260 = false;
               this.field_261 = false;
               return;
            }
         } else if (this.field_428 == 7) {
            if (this.field_286 == null) {
               this.field_428 = 0;
               this.field_249 = 0;
               return;
            }
         } else if (this.field_428 == 6) {
            if (this.field_251 - this.field_435 >= 1000L) {
               this.field_431 = null;
               this.field_428 = 0;
               this.field_249 = 0;
               return;
            }
         } else {
            if (this.field_428 == 2) {
               return;
            }

            if (this.field_428 == 3) {
               if (this.field_434 == 0) {
                  if (this.method_149(this.field_256.m + 12, this.field_256.n + 12)) {
                     if (this.field_198 == 0 && this.field_252 == 7 && this.field_256 == this.field_274[1]) {
                        this.field_405 = null;
                        class_2[] var14;
                        if ((var14 = this.method_185(-1, -1, (byte)0)).length > 0) {
                           this.method_117(var14[class_15.method_233(var14.length)]);
                        }

                        this.field_428 = 4;
                        return;
                     }

                     this.field_434 = 1;
                     this.field_435 = this.field_251;
                     return;
                  }
               } else if (this.field_434 == 1) {
                  if (this.field_251 - this.field_435 >= 100L) {
                     this.field_260 = true;
                     this.field_261 = false;
                     this.field_434 = 2;
                     this.field_249 = 1;
                     this.field_435 = this.field_251;
                     return;
                  }
               } else if (this.field_434 == 2) {
                  if (this.field_251 - this.field_435 >= 200L) {
                     this.field_246 = this.field_429;
                     this.field_247 = this.field_430;
                     this.field_234.method_11(this.field_429 * 24, this.field_430 * 24);
                     this.field_264 = this.field_256.method_50(this.field_256.field_46, this.field_256.field_47, this.field_246, this.field_247);
                     this.field_434 = 3;
                     this.field_435 = this.field_251;
                     return;
                  }
               } else if (this.field_434 == 3 && this.field_251 - this.field_435 >= 200L) {
                  this.field_264 = null;
                  this.field_256.method_48(this.field_429, this.field_430, true);
                  this.field_428 = 2;
                  this.field_434 = 0;
                  this.field_249 = 2;
               }

               return;
            }

            if (this.field_448.isEmpty()) {
               class_2 var13 = null;
               int var2 = 0;
               int var3 = 6666;
               byte var4 = 0;
               byte var5 = 0;

               for(int var6 = 0; var6 < this.field_327.length; ++var6) {
                  byte var8 = this.field_327[var6][0];
                  byte var7 = this.field_327[var6][1];
                  if (this.method_169(var8, var7) == 9 && this.method_182(var8, var7, this.field_272)) {
                     if (var2 == 0) {
                        var4 = var8;
                        var5 = var7;
                     }

                     ++var2;
                     int var9 = 0;

                     for(int var10 = this.field_263.size() - 1; var10 >= 0; --var10) {
                        class_2 var11 = (class_2)this.field_263.elementAt(var10);
                        if (this.field_271[var11.field_45] != this.field_271[this.field_272] && var11.method_55((short)16)) {
                           int var12;
                           if ((var12 = Math.abs(var11.field_46 - var8) + Math.abs(var11.field_47 - var7)) < var3) {
                              var3 = var12;
                              var4 = var8;
                              var5 = var7;
                           }

                           ++var9;
                        }
                     }

                     if (var9 == 0) {
                        for(int var23 = 0; var23 < this.field_327.length; ++var23) {
                           byte var25 = this.field_327[var23][0];
                           byte var28 = this.field_327[var23][1];
                           if (this.method_169(var25, var28) == 9 && !this.method_182(var25, var28, this.field_272) && (var9 = Math.abs(var25 - var8) + Math.abs(var28 - var7)) < var3) {
                              var3 = var9;
                              var4 = var8;
                              var5 = var7;
                           }
                        }
                     }
                  }
               }

               if (var2 > 0) {
                  for(int var17 = 0; var17 < this.field_276[this.field_272]; ++var17) {
                     if (this.field_275[this.field_272][var17] != null && this.field_275[this.field_272][var17].field_55 == 3 && this.method_189(this.field_275[this.field_272][var17], var4, var5)) {
                        var13 = this.method_134(this.field_275[this.field_272][var17], var4, var5);
                     }
                  }

                  if (var13 == null) {
                     if (this.method_184(0, -1, this.field_272) < 2 && this.method_188((byte)0, var4, var5)) {
                        var13 = this.method_135((byte)0, var4, var5);
                     } else if (this.method_184(1, -1, this.field_272) < 2 && this.method_188((byte)1, var4, var5)) {
                        var13 = this.method_135((byte)1, var4, var5);
                     } else {
                        int var18 = 0;
                        int var20 = 0;

                        for(byte var19 = 0; var19 < this.field_268; ++var19) {
                           if (this.field_271[var19] == this.field_271[this.field_272]) {
                              var18 += this.method_184(-1, -1, var19);
                           } else {
                              var20 += this.method_184(-1, -1, var19);
                           }
                        }

                        if (this.field_277[this.field_272] >= 1000 || this.method_184(-1, -1, this.field_272) < 8 || var18 < var20) {
                           int var22 = 0;
                           byte[] var24 = new byte[12];

                           for(byte var26 = 1; var26 < 12; ++var26) {
                              if ((this.method_184(var26, -1, this.field_272) <= 0 || class_2.field_80[var26] >= 600) && this.method_188(var26, var4, var5)) {
                                 var24[var22] = var26;
                                 ++var22;
                              }
                           }

                           if (var22 > 0) {
                              byte var27 = var24[Math.abs(class_15.method_235()) % var22];
                              var13 = this.method_135(var27, var4, var5);
                           }
                        }
                     }
                  }
               }

               if (var13 != null) {
                  this.method_192(var13);
                  return;
               }

               this.field_436 = null;
               this.field_448 = null;
               this.method_170();
               return;
            }

            if (this.field_198 == 0 && this.field_252 == 7 && this.field_274[1].field_55 != 2) {
               this.field_256 = this.field_274[1];
               this.method_156(this.field_256.field_46, this.field_256.field_47);
               this.field_405 = this.field_256;
               this.field_428 = 3;
               this.field_448.removeElement(this.field_256);
               return;
            }

            class_2 var1 = this.field_433;
            if (this.field_433 == null) {
               var1 = (class_2)this.field_448.elementAt(0);
            }

            this.method_192(var1);
            if (this.field_433 == null) {
               this.field_448.removeElement(var1);
            }
         }

      }
   }

   // $FF: renamed from: e (aeii.a) void
   private void method_192(class_2 var1) {
      this.field_256 = var1;
      this.field_262 = true;
      this.method_143(this.field_259, 0);
      this.field_256.method_51(this.field_259);
      this.field_260 = false;
      this.field_436 = this.method_185(0, -1, this.field_272);
      int var2 = 0;
      int var3 = this.field_274.length + this.field_436.length + this.field_327.length;
      this.field_439 = new int[var3][5];
      this.field_441 = 0;
      var3 = 10000;
      this.field_444 = -1;
      this.field_445 = -1;
      this.field_446 = -1;
      this.field_447 = -1;

      for(int var4 = 0; var4 < this.field_436.length + this.field_274.length; ++var4) {
         class_2 var6 = null;
         if (var4 >= this.field_436.length) {
            if ((var6 = this.field_274[var4 - this.field_436.length]) != null) {
               if (var6.field_55 == 3) {
                  var6 = null;
               } else if (this.field_271[var6.field_45] != this.field_271[this.field_272] && this.field_274[this.field_272] == null) {
                  int[] var41 = this.field_439[var2];
                  var41[2] += var6.method_43(var6.field_46, var6.field_47, (class_2)null) << 1;
               } else if (this.field_273 >= 15 && this.field_271[var6.field_45] != this.field_271[this.field_272] && this.method_184(-1, -1, var6.field_45) < 4 && this.method_184(-1, -1, this.field_272) >= 8) {
                  int[] var10000 = this.field_439[var2];
                  var10000[2] += var6.method_43(var6.field_46, var6.field_47, (class_2)null) << 1;
               } else if (var6.field_45 != this.field_272) {
                  var6 = null;
               }
            }
         } else if (this.field_274[this.field_272] != null) {
            var6 = this.field_436[var4];
         }

         if (var6 == null) {
            this.field_439[var2][2] = -6666;
         } else {
            this.field_439[var2][0] = var6.field_46;
            this.field_439[var2][1] = var6.field_47;
            if (var6.field_45 == this.field_272) {
               class_2[] var7 = var6.method_47(var6.field_46, var6.field_47, 1, 5, (byte)0);

               for(int var5 = 0; var5 < var7.length; ++var5) {
                  if (var7[var5].field_55 != 4) {
                     int[] var42 = this.field_439[var2];
                     var42[2] += var7[var5].method_43(var7[var5].field_46, var7[var5].field_47, var6);
                  }
               }
            }

            if (this.field_439[var2][2] > 0) {
               int[] var43 = this.field_439[var2];
               var43[4] += var6.method_43(var6.field_46, var6.field_47, (class_2)null);
               var43 = this.field_439[var2];
               var43[4] += var6.field_71;
               if (this.field_439[var2][2] > this.field_441) {
                  this.field_441 = this.field_439[var2][2];
               }

               this.field_439[var2][3] = Math.abs(var6.field_46 - var1.field_46) + Math.abs(var6.field_47 - var1.field_47);
               if (this.field_439[var2][3] <= 0) {
                  this.field_439[var2][3] = 1;
               }

               if (this.field_439[var2][3] < var3) {
                  var3 = this.field_439[var2][3];
               }
            } else {
               this.field_439[var2][2] = -6666;
            }
         }

         ++var2;
      }

      int var17 = 666;
      int var19 = 666;
      int var20 = -1;
      int var18 = -1;

      for(int var8 = 0; var8 < this.field_327.length; ++var8) {
         byte var10 = this.field_327[var8][0];
         byte var11 = this.field_327[var8][1];
         byte var12 = this.method_169(var10, var11);
         boolean var13 = this.method_182(var10, var11, var1.field_45);
         this.field_439[var2][2] = -6666;
         if (var13 || this.field_437[var8] != null) {
            class_2[] var14 = var1.method_47(var10, var11, 1, 5, (byte)0);
            this.field_439[var2][0] = var10;
            this.field_439[var2][1] = var11;
            this.field_439[var2][2] = 0;

            for(int var9 = 0; var9 < var14.length; ++var9) {
               if (var14[var9].field_55 != 4 && (this.field_437[var8] != null && !var13 || var12 == 8 && var14[var9].method_55((short)8) || var12 == 9 && var14[var9].method_55((short)16))) {
                  int[] var45 = this.field_439[var2];
                  var45[2] += var14[var9].method_43(var14[var9].field_46, var14[var9].field_47, (class_2)null);
               }
            }

            if (this.field_439[var2][2] == 0) {
               if (this.field_437[var8] != null && !var13) {
                  this.field_439[var2][2] = 100;
                  int[] var46 = this.field_439[var2];
                  var46[4] += 2000;
               } else {
                  this.field_439[var2][2] = -6666;
               }
            }

            if (this.field_439[var2][2] != -6666) {
               int[] var47 = this.field_439[var2];
               var47[4] += this.field_440[var8];
               if (this.field_439[var2][2] > this.field_441) {
                  this.field_441 = this.field_439[var2][2];
               }

               this.field_439[var2][3] = Math.abs(var10 - var1.field_46) + Math.abs(var11 - var1.field_47);
               if (this.field_439[var2][3] <= 0) {
                  this.field_439[var2][3] = 1;
               }

               if (this.field_439[var2][3] < var3) {
                  var3 = this.field_439[var2][3];
               }
            }
         }

         if (this.method_181(var10, var11, this.field_271[var1.field_45])) {
            class_2 var23;
            int var24;
            if (((var23 = this.method_168(var10, var11, (byte)0)) == null || var23.field_45 == var1.field_45) && (var24 = Math.abs(var10 - var1.field_46) + Math.abs(var11 - var1.field_47)) < var19) {
               var18 = var8;
               var19 = var24;
            }
         } else if ((this.field_437[var8] == null || this.field_437[var8] == var1) && (var12 == 8 && var1.method_55((short)8) || var12 == 9 && var1.method_55((short)16))) {
            int var37;
            if ((var37 = Math.abs(var10 - var1.field_46) + Math.abs(var11 - var1.field_47)) < var17) {
               var20 = var8;
               var17 = var37;
            }

            class_2 var15 = this.method_168(var10, var11, (byte)0);
            if (var37 < var19 && this.field_259[var10][var11] > 0 && (var15 == null || var15.field_45 == var1.field_45)) {
               var18 = var8;
               var19 = var37;
            }
         }

         ++var2;
      }

      if (var1.field_54 < 50 && var18 != -1) {
         if (var18 == var20) {
            this.field_447 = var20;
         }

         this.field_437[var18] = var1;
         this.field_444 = this.field_327[var18][0];
         this.field_445 = this.field_327[var18][1];
         this.method_143(this.field_442, 0);
         class_2.method_52(this.field_442, this.field_444, this.field_445, 10, -1, var1.field_44, this.field_272, false);
      } else if (this.field_274[this.field_272] == null || var20 == -1 || !var1.method_55((short)8) && !var1.method_55((short)16)) {
         int var21 = -1;
         int var29 = -6666;

         for(int var31 = 0; var31 < var2; ++var31) {
            if (this.field_439[var31][2] > -6666) {
               if (this.field_439[var31][2] > 0) {
                  this.field_439[var31][2] = this.field_439[var31][2] * var3 / this.field_439[var31][3];
               }

               int[] var48 = this.field_439[var31];
               var48[2] -= this.field_439[var31][4];
               if (this.field_439[var31][2] > var29) {
                  var29 = this.field_439[var31][2];
                  var21 = var31;
               }
            }
         }

         if (var21 != -1) {
            int var32 = var1.method_43(var1.field_46, var1.field_47, (class_2)null);
            if (var21 < this.field_436.length) {
               class_2 var49 = this.field_436[var21];
               var49.field_71 += var32;
            } else if (var21 < this.field_274.length + this.field_436.length) {
               class_2 var50 = this.field_274[var21 - this.field_436.length];
               var50.field_71 += var32;
            } else {
               this.field_446 = var21 - this.field_274.length - this.field_436.length;
               int[] var51 = this.field_440;
               int var10001 = this.field_446;
               var51[var10001] += var32;
            }

            this.field_444 = this.field_439[var21][0];
            this.field_445 = this.field_439[var21][1];
            this.method_143(this.field_442, 0);
            class_2.method_52(this.field_442, this.field_444, this.field_445, 10, -1, var1.field_44, this.field_272, false);
         }
      } else {
         this.field_447 = var20;
         this.field_437[var20] = var1;
         this.field_444 = this.field_327[var20][0];
         this.field_445 = this.field_327[var20][1];
         this.method_143(this.field_442, 0);
         class_2.method_52(this.field_442, this.field_444, this.field_445, 10, -1, var1.field_44, this.field_272, false);
      }

      int var22 = -10000;
      int var30 = 0;

      for(int var33 = this.field_259.length; var30 < var33; ++var30) {
         int var34 = 0;

         for(int var36 = this.field_259[var30].length; var34 < var36; ++var34) {
            class_2 var25;
            if (this.field_259[var30][var34] > 0 && ((var25 = this.method_168(var30, var34, (byte)0)) == null || var25 == var1 || this.field_433 == null && var25.field_45 == var1.field_45 && var25.field_55 == 0)) {
               if (!var1.method_55((short)512) || var25 == var1) {
                  class_2[] var38 = var1.method_46(var30, var34, (byte)0);

                  for(int var40 = 0; var40 < var38.length; ++var40) {
                     int var26;
                     if ((var26 = this.method_193(var1, var30, var34, var38[var40], (class_2)null)) > var22) {
                        this.field_432 = null;
                        this.field_431 = var38[var40];
                        var22 = var26;
                        this.field_429 = var30;
                        this.field_430 = var34;
                     }
                  }
               }

               if (var1.method_55((short)32)) {
                  this.field_255 = var1.method_46(var30, var34, (byte)1);

                  for(int var39 = 0; var39 < this.field_255.length; ++var39) {
                     int var27;
                     if ((var27 = this.method_193(var1, var30, var34, (class_2)null, this.field_255[var39])) > var22) {
                        this.field_431 = null;
                        this.field_432 = this.field_255[var39];
                        var22 = var27;
                        this.field_429 = var30;
                        this.field_430 = var34;
                     }
                  }
               }

               int var28;
               if ((var28 = this.method_193(var1, var30, var34, (class_2)null, (class_2)null)) > var22) {
                  this.field_431 = null;
                  this.field_432 = null;
                  var22 = var28;
                  this.field_429 = var30;
                  this.field_430 = var34;
               }
            }
         }
      }

      if (this.field_447 != -1) {
         this.field_438[this.field_447] = (byte)(10 - this.field_442[this.field_429][this.field_430]);
      }

      this.field_433 = null;
      class_2 var35;
      if ((var35 = this.method_168(this.field_429, this.field_430, (byte)0)) != null && var35 != var1) {
         this.field_433 = var35;
         this.field_428 = 0;
      } else {
         this.method_156(var1.field_46, var1.field_47);
         this.field_405 = var1;
         this.field_428 = 3;
      }
   }

   // $FF: renamed from: a (aeii.a, int, int, aeii.a, aeii.a) int
   private int method_193(class_2 var1, int var2, int var3, class_2 var4, class_2 var5) {
      int var6 = 0;
      if (this.field_447 != -1 && this.field_274[var1.field_45] != null) {
         if (this.field_444 != -1) {
            if (this.field_442[var2][var3] > 0) {
               var6 = 100 + 100 * this.field_442[var2][var3] / 10;
            } else {
               int var7 = Math.abs(this.field_444 - var1.field_46) + Math.abs(this.field_445 - var1.field_47);
               int var8 = Math.abs(this.field_444 - var2) + Math.abs(this.field_445 - var3);
               var6 = 0 + 100 * (var7 - var8) / (var1.field_53 - 1);
               if (field_222[this.method_169(var2, var3)] <= 1) {
                  var6 += 20;
               }
            }
         }

         if (var4 == null && !this.method_181(var2, var3, this.field_271[var1.field_45])) {
            if (var1.method_55((short)16) && this.method_169(var2, var3) == 9) {
               var6 += 300;
            } else if (var1.method_55((short)8) && (this.method_169(var2, var3) == 8 || this.field_248[var2][var3] == 27)) {
               var6 += 200;
            }
         }
      }

      switch (var1.field_44) {
         case 2:
            if (this.method_169(var2, var3) == 5) {
               var6 += 25;
            }
            break;
         case 3:
            if (var5 != null) {
               var6 += 100;
            }
            break;
         case 4:
            class_2[] var9 = var1.method_47(var2, var3, 1, 2, (byte)2);
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
         if (var4.field_55 == 4) {
            int var12 = this.method_178(var4.field_46, var4.field_47);
            int var14 = this.method_194(var4.field_46, var4.field_47);
            if (var12 != 0 && var14 != -1 && this.field_437[var14] == null) {
               var6 += var1.method_43(var2, var3, var4) / 2;
            }
         } else {
            if (!var4.method_37(var2, var3)) {
               var6 += var1.method_43(var2, var3, var4) << 1;
            } else {
               var6 += var1.method_43(var2, var3, var4) * 3 / 2 - var4.method_43(var2, var3, var1);
            }

            if (var4.field_44 == 9) {
               var6 += 25;
            } else if (var4.field_44 == 11) {
               var6 += 100;
            }
         }
      }

      var6 += field_221[this.method_169(var2, var3)];
      if (var1.field_54 < 100 && this.method_181(var2, var3, this.field_271[var1.field_45])) {
         var6 += 100 - var1.field_54;
      }

      int var13 = this.method_194(var2, var3);
      if (this.field_442[var2][var3] > 0) {
         int var15 = this.field_442[var2][var3];
         int var17 = 10 - var1.field_53 / 2;
         if (var15 > var17) {
            var15 = var17;
         }

         var6 += 50 + var15 * 100 / var17;
      } else if (this.field_444 != -1) {
         int var16 = Math.abs(this.field_444 - var1.field_46) + Math.abs(this.field_445 - var1.field_47);
         int var18 = Math.abs(this.field_444 - var2) + Math.abs(this.field_445 - var3);
         var6 += 50 * (var16 - var18) / (var1.field_53 - 1);
      }

      if (var13 != -1 && (var4 = this.field_437[var13]) != null && var4 != var1 && var4.field_55 == 0 && this.field_438[var13] < var4.field_53) {
         var6 -= 200;
      }

      return var6 + 20 * (Math.abs(var2 - var1.field_46) + Math.abs(var3 - var1.field_47)) / (var1.field_53 - 1);
   }

   // $FF: renamed from: b (int, int) int
   private int method_194(int var1, int var2) {
      for(int var3 = 0; var3 < this.field_327.length; ++var3) {
         if (this.field_327[var3][0] == var1 && this.field_327[var3][1] == var2) {
            return var3;
         }
      }

      return -1;
   }

   // $FF: renamed from: d (int) void
   private void method_195(int var1) {
      this.field_450 = var1;
      this.field_462 = true;
   }

   // $FF: renamed from: a (java.lang.String, byte, byte) aeii.e
   private class_12 method_196(String var1, byte var2, byte var3) {
      class_12 var5 = new class_12((byte)7, 12);
      int var4 = class_15.field_593 * 3;
      var5.method_93(var1, super.field_93, var4, var2);
      var5.method_92(0, super.field_94 - var4, 0);
      class_11.field_92.method_253(var5);
      return var5;
   }

   // $FF: renamed from: a (java.lang.String, java.lang.String, int, int) aeii.e
   private class_12 method_197(String var1, String var2, int var3, int var4) {
      return this.method_198(var1, var2, var3, -1, var4);
   }

   // $FF: renamed from: a (java.lang.String, java.lang.String, int, int, int) aeii.e
   private class_12 method_198(String var1, String var2, int var3, int var4, int var5) {
      class_12 var6;
      (var6 = new class_12((byte)10, 12)).method_95(var1, var2, super.field_93, var4);
      var6.method_92(super.field_95, var3 / 2, 3);
      var6.field_141 = this;
      var6.field_136 = var5;
      return var6;
   }

   // $FF: renamed from: q () void
   private void method_199() {
      this.field_456 = null;
      if (this.field_198 == 0) {
         this.field_338 = field_185;
         this.field_339 = true;
         this.field_337 = false;
         this.field_451 = this.method_197((String)null, this.field_370, super.field_94, 2000);
         this.field_451.method_92(super.field_95, super.field_96, 3);
         this.field_355 = true;
         this.field_354 = true;
         this.field_352 = 0;
      }

      if (this.field_252 == 0) {
         this.field_321 = 0;
         this.field_277[0] = 0;
         this.field_277[1] = 0;
         class_2.field_35 = 4;
         this.field_406 = 2;
         this.method_154(this.field_274[0].field_46, this.field_274[0].field_47);
         this.method_156(this.field_274[0].field_46, this.field_274[0].field_47);
         this.method_160(2, 3, 3);
         class_15.method_270(1, 1);
         this.field_355 = false;
         this.field_262 = false;
         this.field_458 = 0;
      } else if (this.field_252 == 1) {
         this.field_321 = 1;
         this.field_277[0] = 300;
         this.field_277[1] = 50;
         class_2.field_35 = 4;
         this.field_406 = 2;
         this.field_274[0].method_28(2);
         this.method_154(this.field_274[0].field_46, this.field_274[0].field_47);
         this.method_156(this.field_274[0].field_46, this.field_274[0].field_47);
         this.method_168(7, 12, (byte)0).method_48(7, 10, false);
         this.method_168(8, 11, (byte)0).method_48(8, 9, false);
         this.method_168(9, 12, (byte)0).method_48(9, 10, false);
         this.method_204(7, 3);
         class_11.field_92.method_253(this.field_451);
      } else if (this.field_252 == 2) {
         this.field_321 = 0;
         this.field_277[0] = 0;
         this.field_277[1] = 0;
         class_2.field_35 = 4;
         this.field_277[0] = 0;
         this.method_154(this.field_274[0].field_46, this.field_274[0].field_47);
         this.method_156(this.field_274[0].field_46, this.field_274[0].field_47);
         this.field_452 = this.method_168(8, 17, (byte)0);
         this.field_453 = this.method_168(8, 18, (byte)0);
         this.field_454 = this.method_168(8, 19, (byte)0);
         this.field_452.method_48(8, 15, false);
         this.field_453.method_48(8, 15, false);
         this.field_454.method_48(8, 15, false);
         this.field_274[0].method_48(8, 14, false);
         this.field_262 = false;
         class_11.field_92.method_253(this.field_451);
         this.field_458 = 0;
      } else if (this.field_252 == 3) {
         this.field_321 = 7;
         class_2.field_35 = 4;
         this.field_277[0] = 400;
         this.field_277[1] = 400;
         this.field_452 = class_2.method_24((byte)0, (byte)0, -1, 5);
         this.field_453 = class_2.method_24((byte)2, (byte)0, -2, 5);
         this.field_454 = class_2.method_24((byte)3, (byte)0, -3, 5);
         this.field_452.method_48(3, 4, false);
         this.field_453.method_48(4, 4, false);
         this.field_454.method_48(2, 4, false);
         this.field_274[0].method_48(3, 3, false);
         this.method_154(this.field_274[0].field_46, this.field_274[0].field_47);
         this.method_156(3, 3);
         this.field_405 = this.field_274[0];
         this.method_160(3, 3, 3);
         class_15.method_270(1, 1);
         this.field_355 = false;
         this.field_262 = false;
         this.field_458 = 0;
      } else if (this.field_252 == 4) {
         this.field_321 = 0;
         this.field_277[0] = 0;
         this.field_277[1] = 0;
         this.method_153(this.field_274[0].m + 12, this.field_274[0].n + 12);
         this.method_156(this.field_274[0].field_46, this.field_274[0].field_47);
         class_2.field_35 = 4;
         class_2 var6;
         (var6 = this.method_168(11, 2, (byte)0)).method_41(11, -3);
         var6.method_48(11, 2, false);
         (var6 = this.method_168(10, 1, (byte)0)).method_41(10, -5);
         var6.method_48(10, 1, false);
         (var6 = this.method_168(11, 1, (byte)0)).method_41(11, -5);
         var6.method_48(11, 1, false);
         (var6 = this.method_168(12, 1, (byte)0)).method_41(12, -5);
         var6.method_48(12, 1, false);
         (var6 = this.method_168(11, 0, (byte)0)).method_41(11, -7);
         var6.method_48(11, 0, false);
         (var6 = this.method_168(12, 0, (byte)0)).method_41(12, -7);
         var6.method_48(12, 0, false);
         class_11.field_92.method_253(this.field_451);
         this.field_262 = false;
         this.field_458 = 0;
      } else if (this.field_252 == 5) {
         this.field_321 = 7;
         this.field_277[0] = 600;
         this.field_277[1] = 600;
         this.field_274[0].method_28(2);
         this.method_154(5, 0);
         this.method_156(5, 0);
         this.field_406 = 4;
         this.method_204(this.field_274[0].field_46, this.field_274[0].field_47);
         class_11.field_92.method_253(this.field_451);
         this.field_262 = false;
         this.field_458 = 0;
      } else if (this.field_252 == 6) {
         this.field_321 = 8;
         class_2.field_35 = 4;
         this.field_277[0] = 400;
         this.field_277[1] = 600;
         class_2 var5 = class_2.method_24((byte)0, (byte)0, 13, -1);
         class_2 var12 = class_2.method_24((byte)1, (byte)0, 13, -1);
         class_2 var3 = class_2.method_24((byte)3, (byte)0, 13, -1);
         class_2 var4 = class_2.method_24((byte)11, (byte)0, 13, -1);
         this.field_274[0].field_67 = var5;
         var5.field_67 = var12;
         var12.field_67 = var3;
         var3.field_67 = var4;
         this.field_274[0].method_51(this.field_259);
         this.field_274[0].method_48(14, 3, true);
         this.method_154(this.field_274[0].field_46, this.field_274[0].field_47);
         this.method_156(this.field_274[0].field_46, this.field_274[0].field_47);
         this.field_405 = this.field_274[0];
         class_11.field_92.method_253(this.field_451);
         this.field_262 = false;
         this.field_458 = 0;
      } else {
         if (this.field_252 == 7) {
            this.field_321 = 8;
            class_2.field_35 = 4;
            this.field_277[0] = 800;
            this.field_277[1] = 200;
            class_2 var1;
            (var1 = this.method_168(7, 4, (byte)0)).method_28(3);
            this.field_274[1] = var1;
            class_2 var2 = this.method_168(8, 15, (byte)0);
            this.field_274[0] = var2;
            var2.method_28(0);
            this.method_168(6, 15, (byte)0).method_28(2);
            this.method_153(this.field_274[0].m + 12, this.field_274[0].n + 12);
            this.method_156(this.field_274[0].field_46, this.field_274[0].field_47);
            this.method_160(4, 3, 3);
            class_15.method_270(1, 1);
            this.field_355 = false;
            this.field_262 = false;
            this.field_458 = 0;
         }

      }
   }

   // $FF: renamed from: e (int) void
   private void method_200(int var1) {
      InputStream var6;
      if ((var6 = class_15.method_276("m" + var1 + ".script")) == null) {
         this.field_459 = null;
      } else {
         Vector var2 = new Vector();
         Vector var3 = null;

         String[] var4;
         while((var4 = class_15.method_238(var6)) != null) {
            int var5;
            if ((var5 = ((String)var4).indexOf(59)) >= 0) {
               var4 = ((String)var4).substring(0, var5);
            }

            if ((var4 = ((String)var4).trim()).length() != 0) {
               if ((var4 = class_15.method_239(var4, ' '))[0].charAt(0) != '@') {
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

         this.field_459 = new String[var2.size()][][];

         for(int var11 = 0; var11 < var2.size(); ++var11) {
            var3 = (Vector)var2.elementAt(var11);
            this.field_459[var11] = new String[var3.size()][];
            var3.copyInto(this.field_459[var11]);
         }

      }
   }

   // $FF: renamed from: r () void
   private void method_201() {
      String[][] var1 = this.field_459[this.field_458];
      int var3 = 0;
      int var4 = -1;
      class_2 var5 = null;

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
               var6 = this.field_272;
            } else if (var2[1].equalsIgnoreCase("CurrentTurn")) {
               var6 = this.field_273;
            } else if (var2[1].equalsIgnoreCase("GameState")) {
               var6 = this.field_249;
            } else if (var2[1].equalsIgnoreCase("AlphaWindow")) {
               var6 = this.field_409;
            } else if (var2[1].equalsIgnoreCase("StatusBarOffset")) {
               var6 = this.field_338;
            } else if (var2[1].equalsIgnoreCase("UnitFinishedMove")) {
               var6 = this.field_457 != null ? 1 : 0;
            } else if (var2[1].equalsIgnoreCase("CountUnits")) {
               var6 = this.method_184(Integer.parseInt(var2[2]), Integer.parseInt(var2[3]), (byte)Integer.parseInt(var2[4]));
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
            this.method_195(Integer.parseInt(var2[1]));
         } else if (var2[0].equalsIgnoreCase("NextState")) {
            ++this.field_458;
         } else if (var2[0].equalsIgnoreCase("ShowMapName")) {
            class_11.field_92.method_253(this.field_451);
         } else if (var2[0].equalsIgnoreCase("StartPlay")) {
            this.method_203();
         } else if (var2[0].equalsIgnoreCase("CompleteMission")) {
            this.method_205();
         } else if (var2[0].equalsIgnoreCase("SetFadeEnabled")) {
            this.field_355 = "true".equalsIgnoreCase(var2[1]);
         } else if (var2[0].equalsIgnoreCase("SetCursorVisible")) {
            this.field_262 = "true".equalsIgnoreCase(var2[1]);
         } else if (var2[0].equalsIgnoreCase("SetGameActive")) {
            this.field_337 = "true".equalsIgnoreCase(var2[1]);
         } else if (var2[0].equalsIgnoreCase("SetFadeValue")) {
            this.field_352 = Integer.parseInt(var2[1]);
         } else if (var2[0].equalsIgnoreCase("SetMapStepMax")) {
            this.field_406 = Integer.parseInt(var2[1]);
         } else if (var2[0].equalsIgnoreCase("SetUnitSpeed")) {
            if (var2[1].equalsIgnoreCase("slow")) {
               class_2.field_35 = class_2.field_34;
            } else if (var2[1].equalsIgnoreCase("fast")) {
               class_2.field_35 = class_2.field_33;
            } else {
               class_2.field_35 = (byte)Integer.parseInt(var2[1]);
            }
         } else if (var2[0].equalsIgnoreCase("ShowDialog")) {
            this.method_196(class_11.method_79(Integer.parseInt(var2[1])), (byte)Integer.parseInt(var2[2]), (byte)Integer.parseInt(var2[3]));
         } else if (var2[0].equalsIgnoreCase("ShowHelp")) {
            this.field_316 = Integer.parseInt(var2[1]);
         } else if (var2[0].equalsIgnoreCase("Vibrate")) {
            class_15.method_267(Integer.parseInt(var2[1]));
         } else if (var2[0].equalsIgnoreCase("MoveMapAndCursor")) {
            if (var2[1].equalsIgnoreCase("king")) {
               class_2 var10 = this.field_274[Integer.parseInt(var2[2])];
               this.method_204(var10.field_46, var10.field_47);
            } else {
               this.method_204(Integer.parseInt(var2[1]), Integer.parseInt(var2[2]));
            }
         } else if (var2[0].equalsIgnoreCase("GetUnitPlotRoute")) {
            this.method_168(Integer.parseInt(var2[1]), Integer.parseInt(var2[2]), (byte)Integer.parseInt(var2[3])).method_48(Integer.parseInt(var2[4]), Integer.parseInt(var2[5]), "true".equalsIgnoreCase(var2[6]));
         } else if (var2[0].equalsIgnoreCase("CreateUnitPlotRoute")) {
            class_2.method_24((byte)Integer.parseInt(var2[1]), (byte)Integer.parseInt(var2[2]), Integer.parseInt(var2[3]), Integer.parseInt(var2[4])).method_49(Integer.parseInt(var2[5]), Integer.parseInt(var2[6]), "true".equalsIgnoreCase(var2[7]), "true".equalsIgnoreCase(var2[8]));
         } else if (var2[0].equalsIgnoreCase("GetUnit")) {
            var5 = this.method_168(Integer.parseInt(var2[1]), Integer.parseInt(var2[2]), (byte)Integer.parseInt(var2[3]));
         } else if (var2[0].equalsIgnoreCase("RemoveUnit")) {
            var5.method_27();
         } else if (var2[0].equalsIgnoreCase("ScheduleUnitAnimationStop")) {
            var5.method_23(Integer.parseInt(var2[1]));
         } else if (var2[0].equalsIgnoreCase("CreateSpriteAtUnit")) {
            class_1 var11 = null;
            if (var2[1].equalsIgnoreCase("Smoke")) {
               var11 = this.field_240;
            } else if (var2[1].equalsIgnoreCase("Spark")) {
               var11 = this.field_241;
            } else if (var2[1].equalsIgnoreCase("RedSpark")) {
               var11 = this.field_242;
            }

            this.method_120(var11, var5.m, var5.n, Integer.parseInt(var2[2]), Integer.parseInt(var2[3]), Integer.parseInt(var2[4]), Integer.parseInt(var2[5]));
         }
      }

   }

   // $FF: renamed from: s () void
   private void method_202() {
      if (this.field_506 == 0) {
         if (this.field_450 <= 0 || --this.field_450 <= 0) {
            if (this.field_463) {
               if (this.field_458 == 0) {
                  this.field_298 = true;
                  this.field_299 = 0;
                  this.field_249 = 11;
                  this.field_458 = 1;
                  return;
               }
            } else if (this.field_198 == 1) {
               if (this.field_458 == 100) {
                  class_15.method_270(field_217[this.field_270[this.field_272]], 0);
                  this.field_320.method_87((byte)0, true);
                  this.field_320.method_88((class_11)null);
                  class_11.field_92.method_253(this.field_320);
                  ++this.field_458;
                  return;
               }

               if (this.field_458 == 101) {
                  byte var2 = -1;
                  int var1 = -1;
                  boolean var3 = true;

                  for(int var4 = 0; var4 < this.field_268; ++var4) {
                     if (this.field_279[var4] != 2 && (this.field_274[var4] != null && this.field_274[var4].field_55 != 3 || this.method_183(var4) != 0)) {
                        var1 = var4;
                        if (var2 != -1 && var2 != this.field_271[var4]) {
                           var3 = false;
                           break;
                        }

                        var2 = this.field_271[var4];
                     }
                  }

                  if (var3) {
                     this.field_337 = false;
                     this.field_262 = false;
                     String var36 = class_11.method_82(38, "" + (this.field_271[var1] + 1));
                     String var13 = class_11.method_82(81, var36) + "\n(";

                     for(int var28 = 0; var28 < this.field_268; ++var28) {
                        if (this.field_279[var28] != 2 && this.field_271[var28] == this.field_271[var1]) {
                           var13 = var13 + " " + class_11.method_79(88 + this.field_270[var28]) + " ";
                        }
                     }

                     var13 = var13 + ")";
                     class_12 var29;
                     (var29 = this.method_198((String)null, var13, this.field_187, this.field_189, -1)).method_88(this);
                     class_11.field_92.method_253(var29);
                     if (this.field_279[var1] == 1) {
                        class_15.method_270(6, 1);
                     } else {
                        class_15.method_270(7, 1);
                     }

                     this.method_195(15);
                     ++this.field_458;
                     return;
                  }

                  return;
               }

               if (this.field_458 == 102) {
                  this.field_298 = true;
                  this.field_299 = 0;
                  this.field_249 = 11;
                  ++this.field_458;
                  return;
               }
            } else if (this.field_292 == 1 && this.field_198 == 0 && this.field_458 != -1) {
               if (this.field_464 != -1) {
                  if (!this.method_150(this.field_464, this.field_465)) {
                     return;
                  }

                  this.field_464 = -1;
                  this.field_465 = -1;
               }

               if (this.field_249 != 11) {
                  boolean var15 = true;

                  for(int var5 = 0; var5 < this.field_276[0]; ++var5) {
                     if (this.field_275[0][var5].field_55 != 3) {
                        var15 = false;
                        break;
                     }
                  }

                  if (var15 && this.method_183(0) == 0) {
                     this.method_206();
                     return;
                  }
               }

               if (this.field_459 != null) {
                  this.method_201();
               } else if (this.field_252 == 0) {
                  switch (this.field_458) {
                     case 0:
                        ++this.field_458;
                        break;
                     case 1:
                        class_11.field_92.method_253(this.field_451);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 2:
                        this.field_355 = true;
                        this.method_168(0, 8, (byte)0).method_48(3, 8, false);
                        this.method_168(1, 9, (byte)0).method_48(4, 9, false);
                        this.method_168(0, 10, (byte)0).method_48(3, 10, false);
                        this.method_204(5, 9);
                        break;
                     case 3:
                        this.method_204(9, 3);
                        this.field_262 = true;
                        break;
                     case 4:
                        this.method_195(10);
                        this.field_406 = 12;
                        class_2.field_35 = class_2.field_34;
                        ++this.field_458;
                        break;
                     case 5:
                        this.method_196(class_11.method_79(221), (byte)2, (byte)4);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 6:
                        class_2 var35 = this.method_168(9, 3, (byte)0);
                        class_15.method_267(100);
                        var35.method_23(400);
                        this.method_120(this.field_242, var35.m, var35.n, 0, 0, 2, 50);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 7:
                        this.method_196(class_11.method_79(222), (byte)2, (byte)4);
                        ++this.field_458;
                        break;
                     case 8:
                        class_2 var27 = this.method_168(9, 3, (byte)0);
                        this.method_120(this.field_241, var27.m, var27.n, 0, 0, 1, 50);
                        this.method_120(this.field_240, var27.m, var27.n, 0, -3, 1, 100);
                        var27.method_27();
                        this.method_195(20);
                        ++this.field_458;
                        break;
                     case 9:
                        this.field_262 = false;
                        this.method_204(this.field_274[0].field_46, this.field_274[0].field_47);
                        break;
                     case 10:
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 11:
                        this.method_196(class_11.method_79(223), (byte)0, (byte)4);
                        this.method_195(5);
                        ++this.field_458;
                        break;
                     case 12:
                        this.method_196(class_11.method_79(224), (byte)5, (byte)4);
                        ++this.field_458;
                        break;
                     case 13:
                        this.method_203();
                        ++this.field_458;
                        break;
                     case 14:
                        if (this.field_338 == 0) {
                           this.field_316 = 0;
                           ++this.field_458;
                        }
                        break;
                     case 15:
                        if (this.field_272 == 0 && this.field_249 == 1 && this.field_409 == 0) {
                           this.field_316 = 1;
                           ++this.field_458;
                        }
                        break;
                     case 16:
                        if (this.field_272 == 0 && this.field_457 != null) {
                           this.field_316 = 2;
                           ++this.field_458;
                        }
                        break;
                     case 17:
                        if (this.field_272 == 0 && this.field_457 != null) {
                           this.field_316 = 3;
                           ++this.field_458;
                        }
                        break;
                     case 18:
                        if (this.method_184(-1, 2, (byte)0) >= 3) {
                           this.field_316 = 4;
                           ++this.field_458;
                        } else if (this.field_273 > 0) {
                           ++this.field_458;
                        }
                        break;
                     case 19:
                        if (this.field_273 >= 2) {
                           this.field_316 = 5;
                           ++this.field_458;
                        }
                        break;
                     case 20:
                        this.field_316 = 6;
                        ++this.field_458;
                        break;
                     case 21:
                        if (this.field_249 == 1 && this.field_272 == 0) {
                           this.field_316 = 7;
                           ++this.field_458;
                        }
                        break;
                     case 22:
                        if (this.method_184(-1, -1, (byte)1) == 0) {
                           this.field_337 = false;
                           this.method_195(20);
                           ++this.field_458;
                        }
                        break;
                     case 23:
                        this.method_204(1, 1);
                        break;
                     case 24:
                        class_2.method_24((byte)1, (byte)1, 1, 1).method_49(1, 2, false, true);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 25:
                        this.method_204(10, 10);
                        break;
                     case 26:
                        class_2.method_24((byte)0, (byte)1, 10, 10).method_49(10, 9, false, true);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 27:
                        this.method_204(this.field_274[0].field_46, this.field_274[0].field_47);
                        break;
                     case 28:
                        this.method_196(class_11.method_79(225), (byte)5, (byte)4);
                        this.method_195(5);
                        ++this.field_458;
                        break;
                     case 29:
                        this.method_196(class_11.method_79(226), (byte)0, (byte)4);
                        this.field_337 = true;
                        ++this.field_458;
                        break;
                     case 30:
                        if (this.method_184(-1, -1, (byte)1) == 0 && this.field_249 == 0) {
                           this.field_337 = false;
                           this.field_262 = false;
                           this.method_195(30);
                           ++this.field_458;
                        }
                        break;
                     case 31:
                        this.field_353 = true;
                        this.field_352 = 0;
                        this.method_195(20);
                        ++this.field_458;
                        break;
                     case 32:
                        this.method_196(class_11.method_79(227), (byte)2, (byte)4);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 33:
                        this.method_196(class_11.method_79(228), (byte)0, (byte)4);
                        this.method_195(5);
                        ++this.field_458;
                        break;
                     case 34:
                        this.method_196(class_11.method_79(229), (byte)2, (byte)4);
                        this.method_195(5);
                        ++this.field_458;
                        break;
                     case 35:
                        this.method_196(class_11.method_79(230), (byte)0, (byte)4);
                        this.method_195(15);
                        ++this.field_458;
                        break;
                     case 36:
                        this.method_205();
                  }
               } else if (this.field_252 == 1) {
                  switch (this.field_458) {
                     case 1:
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 2:
                        this.field_406 = 4;
                        this.method_204(12, 3);
                        break;
                     case 3:
                        this.method_196(class_11.method_79(231), (byte)1, (byte)4);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 4:
                        this.method_196(class_11.method_79(232), (byte)3, (byte)4);
                        this.method_204(this.field_274[0].field_46, this.field_274[0].field_47);
                        break;
                     case 5:
                        this.method_196(class_11.method_79(233), (byte)5, (byte)4);
                        this.method_204(7, 3);
                        break;
                     case 6:
                        class_2.field_35 = 2;
                        this.field_452 = class_2.method_24((byte)0, (byte)1, 7, 3);
                        this.field_453 = class_2.method_24((byte)11, (byte)1, 7, 3);
                        this.field_454 = class_2.method_24((byte)0, (byte)1, 7, 3);
                        this.field_452.field_67 = this.field_453;
                        this.field_453.field_67 = this.field_454;
                        this.field_452.method_48(6, -2, false);
                        this.method_195(30);
                        ++this.field_458;
                        break;
                     case 7:
                        if (this.field_454.field_46 == 6 && this.field_454.field_47 == 1) {
                           class_2.field_35 = 4;
                           this.field_455 = class_2.method_24((byte)0, (byte)0, 7, 3);
                           this.field_455.method_48(6, 2, false);
                           this.field_453.field_67 = null;
                           this.method_196(class_11.method_79(234), (byte)2, (byte)4);
                           ++this.field_458;
                        }
                        break;
                     case 8:
                        if (this.field_455.field_55 != 1) {
                           class_15.method_267(100);
                           this.field_455.method_23(400);
                           this.method_120(this.field_242, this.field_455.m, this.field_455.n, 0, 0, 2, 50);
                           this.method_195(10);
                           ++this.field_458;
                        }
                        break;
                     case 9:
                        this.method_196(class_11.method_79(235), (byte)2, (byte)4);
                        this.method_195(5);
                        ++this.field_458;
                        break;
                     case 10:
                        this.method_120(this.field_240, this.field_455.m, this.field_455.n, 0, -3, 1, 100);
                        this.method_120(this.field_241, this.field_455.m, this.field_455.n, 0, 0, 1, 50);
                        this.field_455.method_27();
                        this.field_455 = null;
                        this.method_195(15);
                        ++this.field_458;
                        break;
                     case 11:
                        class_2.field_35 = class_2.field_34;
                        this.field_452.method_27();
                        this.field_453.method_27();
                        this.field_452 = null;
                        this.field_453 = null;
                        this.field_454 = null;
                        this.field_406 = 12;
                        this.method_204(this.field_274[0].field_46, this.field_274[0].field_47);
                        break;
                     case 12:
                        this.method_196(class_11.method_79(236), (byte)5, (byte)4);
                        ++this.field_458;
                        break;
                     case 13:
                        this.method_196(class_11.method_79(237), (byte)1, (byte)4);
                        ++this.field_458;
                        break;
                     case 14:
                        this.method_204(3, 5);
                        break;
                     case 15:
                        this.method_196(class_11.method_79(238), (byte)5, (byte)4);
                        ++this.field_458;
                        break;
                     case 16:
                        this.method_204(this.field_274[0].field_46, this.field_274[0].field_47);
                        break;
                     case 17:
                        this.field_316 = 8;
                        ++this.field_458;
                        break;
                     case 18:
                        this.field_316 = 9;
                        ++this.field_458;
                        break;
                     case 19:
                        this.method_203();
                        ++this.field_458;
                        break;
                     case 20:
                        if (this.field_249 == 9 && this.field_272 == 0) {
                           this.field_316 = 10;
                           ++this.field_458;
                        }
                        break;
                     case 21:
                        this.field_316 = 11;
                        ++this.field_458;
                        break;
                     case 22:
                        if (this.field_274[0].field_55 == 3 || this.field_274[1].field_55 == 3) {
                           this.field_316 = 12;
                           ++this.field_458;
                        }
                        break;
                     case 23:
                        if (this.method_184(-1, -1, (byte)1) == 0 && this.method_183(1) == 0) {
                           this.field_337 = false;
                           this.method_195(20);
                           ++this.field_458;
                        }
                        break;
                     case 24:
                        this.method_204(this.field_274[0].field_46, this.field_274[0].field_47);
                        break;
                     case 25:
                        this.method_196(class_11.method_79(239), (byte)1, (byte)4);
                        ++this.field_458;
                        break;
                     case 26:
                        this.method_196(class_11.method_79(240), (byte)5, (byte)4);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 27:
                        this.method_205();
                  }
               } else if (this.field_252 == 2) {
                  label527:
                  switch (this.field_458) {
                     case 0:
                        if (this.field_452.field_55 != 1) {
                           this.field_452.method_48(7, 14, false);
                           ++this.field_458;
                        }
                        break;
                     case 1:
                        if (this.field_453.field_55 != 1) {
                           this.field_453.method_48(7, 15, false);
                           this.method_195(20);
                           ++this.field_458;
                        }
                        break;
                     case 2:
                        class_2.field_35 = class_2.field_34;
                        this.field_452 = null;
                        this.field_453 = null;
                        this.field_454 = null;
                        this.method_196(class_11.method_79(241), (byte)5, (byte)4);
                        this.method_195(5);
                        ++this.field_458;
                        break;
                     case 3:
                        this.method_196(class_11.method_79(242), (byte)0, (byte)4);
                        this.method_195(5);
                        ++this.field_458;
                        break;
                     case 4:
                        this.method_196(class_11.method_79(243), (byte)5, (byte)4);
                        this.method_195(5);
                        ++this.field_458;
                        break;
                     case 5:
                        this.field_316 = 14;
                        ++this.field_458;
                        break;
                     case 6:
                        this.method_203();
                        ++this.field_458;
                        break;
                     case 7:
                     case 8:
                        if (this.field_458 == 7 && this.field_272 == 0 && this.field_338 == 0 && this.method_184(-1, 3, (byte)-1) > 0) {
                           this.field_316 = 15;
                           ++this.field_458;
                        }

                        class_2[] var26 = this.method_185(-1, 2, (byte)0);

                        for(int var12 = 0; var12 < var26.length; ++var12) {
                           if (var26[var12].field_46 <= 4 || var26[var12].field_47 <= 10) {
                              this.method_195(10);
                              this.field_337 = false;
                              this.field_262 = false;
                              this.field_458 = 9;
                              break label527;
                           }
                        }
                        break;
                     case 9:
                        this.method_204(0, 8);
                        class_2.method_24((byte)5, (byte)1, -1, 8).method_48(0, 8, false);
                        class_2.method_24((byte)5, (byte)1, -2, 7).method_48(1, 7, false);
                        this.method_195(20);
                        break;
                     case 10:
                        this.method_204(8, 6);
                        class_2.method_24((byte)5, (byte)1, 12, 6).method_48(8, 6, false);
                        this.method_195(20);
                        break;
                     case 11:
                        this.method_204(2, 1);
                        class_2.method_24((byte)5, (byte)1, 1, -2).method_48(1, 2, false);
                        class_2.method_24((byte)5, (byte)1, 3, -2).method_48(3, 2, false);
                        class_2.method_24((byte)4, (byte)1, 2, -1).method_48(2, 1, false);
                        this.method_195(20);
                        break;
                     case 12:
                        this.method_196(class_11.method_79(244), (byte)5, (byte)4);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 13:
                        this.method_204(4, 8);
                        this.method_195(15);
                        break;
                     case 14:
                        class_2 var25 = class_2.method_24((byte)2, (byte)1, 3, 8);
                        class_2 var34 = class_2.method_24((byte)2, (byte)1, 4, 7);
                        class_2 var39 = class_2.method_24((byte)2, (byte)1, 5, 8);
                        this.method_120(this.field_241, var25.m, var25.n, 0, 0, 1, 50);
                        this.method_120(this.field_241, var34.m, var34.n, 0, 0, 1, 50);
                        this.method_120(this.field_241, var39.m, var39.n, 0, 0, 1, 50);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 15:
                        this.method_196(class_11.method_79(245), (byte)5, (byte)4);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 16:
                        this.method_196(class_11.method_79(246), (byte)-1, (byte)4);
                        this.method_195(5);
                        ++this.field_458;
                        break;
                     case 17:
                        this.method_196(class_11.method_79(247), (byte)0, (byte)4);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 18:
                        class_11.field_92.method_253(this.method_197((String)null, class_11.method_79(248), this.field_187, 1500));
                        ++this.field_458;
                        break;
                     case 19:
                        this.method_168(3, 8, (byte)0).method_27();
                        this.method_168(4, 7, (byte)0).method_27();
                        this.method_168(5, 8, (byte)0).method_27();
                        class_2 var24 = class_2.method_24((byte)2, (byte)0, 3, 8);
                        class_2 var33 = class_2.method_24((byte)2, (byte)0, 4, 7);
                        class_2 var38 = class_2.method_24((byte)2, (byte)0, 5, 8);
                        this.method_120(this.field_241, var24.m, var24.n, 0, 0, 1, 50);
                        this.method_120(this.field_241, var33.m, var33.n, 0, 0, 1, 50);
                        this.method_120(this.field_241, var38.m, var38.n, 0, 0, 1, 50);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 20:
                        this.field_337 = true;
                        this.field_262 = true;
                        this.field_316 = 13;
                        ++this.field_458;
                        break;
                     case 21:
                        if (this.method_184(-1, -1, (byte)1) == 0) {
                           this.field_337 = false;
                           this.method_195(10);
                           ++this.field_458;
                        }
                        break;
                     case 22:
                        this.method_205();
                  }
               } else if (this.field_252 == 3) {
                  switch (this.field_458) {
                     case 0:
                        class_11.field_92.method_253(this.field_451);
                        this.field_355 = true;
                        ++this.field_458;
                        break;
                     case 1:
                        if (this.field_274[0].field_55 != 1) {
                           this.field_405 = null;
                           this.method_195(20);
                           ++this.field_458;
                        }
                        break;
                     case 2:
                        this.field_353 = true;
                        this.field_355 = true;
                        this.field_352 = 0;
                        this.method_195(20);
                        ++this.field_458;
                        break;
                     case 3:
                        this.method_196(class_11.method_79(249), (byte)2, (byte)4);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 4:
                        this.method_196(class_11.method_79(250), (byte)0, (byte)4);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 5:
                        this.method_196(class_11.method_79(251), (byte)5, (byte)4);
                        this.method_156(13, 3);
                        this.method_153(312, 72);
                        this.field_274[0].method_41(7, 1);
                        this.field_452.method_41(5, 4);
                        this.field_453.method_41(7, 5);
                        this.field_454.method_41(3, 3);
                        this.field_452 = null;
                        this.field_453 = null;
                        this.field_454 = null;
                        this.field_406 = 2;
                        this.method_195(5);
                        ++this.field_458;
                        break;
                     case 6:
                        this.field_353 = false;
                        this.field_354 = true;
                        this.field_352 = 0;
                        this.method_195(5);
                        ++this.field_458;
                        break;
                     case 7:
                        this.method_204(13, 10);
                        break;
                     case 8:
                        class_2.field_35 = 2;
                        this.field_406 = 4;
                        this.field_452 = this.method_168(10, 10, (byte)0);
                        this.field_452.method_48(6, 10, false);
                        this.method_156(6, 10);
                        this.method_204(6, 10);
                        break;
                     case 9:
                        if (this.field_452.field_55 != 1) {
                           this.field_452 = null;
                           this.field_262 = true;
                           this.method_195(10);
                           ++this.field_458;
                        }
                        break;
                     case 10:
                        this.method_156(4, 9);
                        this.method_204(4, 9);
                        this.method_120(this.field_242, 96, 216, 0, 0, 1, 50);
                        this.method_195(15);
                        break;
                     case 11:
                        this.field_284 = class_2.method_25((byte)0, (byte)0, 4, 9, false);
                        this.field_284.field_44 = -1;
                        this.field_284.field_55 = 4;
                        this.field_289 = 6;
                        this.method_195(20);
                        ++this.field_458;
                        break;
                     case 12:
                        this.method_196(class_11.method_79(252), (byte)5, (byte)4);
                        this.method_204(7, 1);
                        break;
                     case 13:
                        this.method_196(class_11.method_79(253), (byte)0, (byte)4);
                        this.method_195(5);
                        ++this.field_458;
                        break;
                     case 14:
                        this.field_316 = 17;
                        ++this.field_458;
                        break;
                     case 15:
                        this.method_203();
                        ++this.field_458;
                        break;
                     case 16:
                        if (this.method_184(-1, -1, (byte)1) == 0 && this.method_183(1) == 0) {
                           this.method_195(15);
                           this.field_337 = false;
                           ++this.field_458;
                        }
                        break;
                     case 17:
                        this.method_196(class_11.method_79(254), (byte)5, (byte)4);
                        this.method_195(5);
                        ++this.field_458;
                        break;
                     case 18:
                        this.method_196(class_11.method_79(255), (byte)0, (byte)4);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 19:
                        this.method_205();
                  }
               } else if (this.field_252 == 4) {
                  if (this.field_456 == null) {
                     this.field_456 = this.method_185(11, -1, (byte)0)[0];
                  }

                  if (this.field_458 == 25 && this.field_456.field_46 >= 15 && this.field_456.field_47 >= 11 && this.field_456.field_55 == 2) {
                     this.method_195(10);
                     this.field_337 = false;
                     this.field_262 = false;
                     this.field_458 = 26;
                     return;
                  }

                  if (this.field_456.field_55 == 3) {
                     this.field_456 = null;
                     this.method_206();
                     return;
                  }

                  label560:
                  switch (this.field_458) {
                     case 0:
                        this.method_195(50);
                        ++this.field_458;
                        break;
                     case 1:
                        this.method_196(class_11.method_79(256), (byte)5, (byte)4);
                        this.method_195(5);
                        ++this.field_458;
                        break;
                     case 2:
                        this.method_196(class_11.method_79(257), (byte)0, (byte)4);
                        this.method_195(5);
                        ++this.field_458;
                        break;
                     case 3:
                        this.method_203();
                        ++this.field_458;
                        break;
                     case 4:
                        class_2[] var23 = this.method_185(-1, -1, (byte)0);

                        for(int var11 = 0; var11 < var23.length; ++var11) {
                           if (var23[var11].field_55 == 2 && var23[var11].field_46 <= 8) {
                              this.field_337 = false;
                              this.method_195(5);
                              ++this.field_458;
                              break label560;
                           }
                        }
                        break;
                     case 5:
                        this.field_262 = false;
                        this.method_204(4, 4);
                        break;
                     case 6:
                        class_2.method_24((byte)10, (byte)1, 4, 4).method_49(4, 1, false, true);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 7:
                        class_2.method_24((byte)1, (byte)1, 4, 4).method_49(5, 2, false, true);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 8:
                        class_2.method_24((byte)10, (byte)1, 4, 4).method_49(4, 3, false, true);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 9:
                        this.method_196(class_11.method_79(258), (byte)5, (byte)4);
                        this.method_204(this.field_274[0].field_46, this.field_274[0].field_47);
                        break;
                     case 10:
                        this.field_337 = true;
                        this.field_262 = true;
                        ++this.field_458;
                        break;
                     case 11:
                        class_2[] var22 = this.method_185(-1, -1, (byte)0);

                        for(int var10 = 0; var10 < var22.length; ++var10) {
                           if (var22[var10].field_55 == 2 && var22[var10].field_47 >= 7) {
                              this.field_337 = false;
                              this.field_262 = false;
                              this.method_204(6, 10);
                              break label560;
                           }
                        }
                        break;
                     case 12:
                        class_2.method_24((byte)1, (byte)1, 6, 10).method_49(5, 10, false, true);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 13:
                        class_2.method_24((byte)5, (byte)1, 6, 10).method_49(7, 8, false, true);
                        this.method_195(15);
                        ++this.field_458;
                        break;
                     case 14:
                        class_2.method_24((byte)5, (byte)1, 6, 10).method_49(7, 9, false, true);
                        this.field_337 = true;
                        this.field_262 = true;
                        ++this.field_458;
                        break;
                     case 15:
                        class_2[] var21 = this.method_185(-1, -1, (byte)0);

                        for(int var9 = 0; var9 < var21.length; ++var9) {
                           if (var21[var9].field_55 == 2 && var21[var9].field_46 >= 8 && var21[var9].field_47 >= 6) {
                              this.field_337 = false;
                              this.field_262 = false;
                              this.method_204(12, 5);
                              break label560;
                           }
                        }
                        break;
                     case 16:
                        class_2.method_24((byte)5, (byte)1, 12, 5).method_49(12, 7, false, true);
                        this.method_195(15);
                        ++this.field_458;
                        break;
                     case 17:
                        class_2.method_24((byte)6, (byte)1, 12, 5).method_49(12, 6, false, true);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 18:
                        class_2.method_24((byte)5, (byte)1, 12, 5).method_49(12, 5, false, true);
                        this.field_337 = true;
                        this.field_262 = true;
                        ++this.field_458;
                        break;
                     case 19:
                        class_2[] var20 = this.method_185(-1, -1, (byte)0);

                        for(int var8 = 0; var8 < var20.length; ++var8) {
                           if (var20[var8].field_55 == 2 && var20[var8].field_46 >= 15 && var20[var8].field_47 >= 8) {
                              this.field_337 = false;
                              this.field_262 = false;
                              this.method_204(18, 8);
                              break label560;
                           }
                        }
                        break;
                     case 20:
                        class_2.method_24((byte)5, (byte)1, 18, 8).method_49(16, 10, false, true);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 21:
                        class_2.method_24((byte)6, (byte)1, 18, 8).method_49(17, 10, false, true);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 22:
                        class_2.method_24((byte)5, (byte)1, 18, 8).method_49(18, 10, false, true);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 23:
                        class_2.method_24((byte)1, (byte)1, 18, 8).method_49(18, 9, false, true);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 24:
                        this.method_196(class_11.method_79(259), (byte)0, (byte)4);
                        this.field_337 = true;
                        this.field_262 = true;
                        ++this.field_458;
                        break;
                     case 25:
                        if (this.method_184(-1, -1, (byte)1) == 0) {
                           this.field_337 = false;
                           this.field_262 = false;
                           this.method_195(10);
                           ++this.field_458;
                        }
                        break;
                     case 26:
                        this.method_205();
                        ++this.field_458;
                  }
               } else if (this.field_252 == 5) {
                  switch (this.field_458) {
                     case 0:
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 1:
                        this.method_196(class_11.method_79(260), (byte)1, (byte)4);
                        ++this.field_458;
                        break;
                     case 2:
                        this.method_203();
                        ++this.field_458;
                        break;
                     case 3:
                        if (this.method_184(-1, -1, (byte)1) == 0 && this.method_183(1) == 0) {
                           this.field_337 = false;
                           this.field_262 = false;
                           this.method_195(15);
                           ++this.field_458;
                        }
                        break;
                     case 4:
                        this.method_204(this.field_274[0].field_46, this.field_274[0].field_47);
                        break;
                     case 5:
                        this.method_196(class_11.method_79(261), (byte)0, (byte)4);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 6:
                        this.method_205();
                  }
               } else if (this.field_252 != 6) {
                  if (this.field_252 == 7) {
                     switch (this.field_458) {
                        case 0:
                           class_11.field_92.method_253(this.field_451);
                           this.field_355 = true;
                           ++this.field_458;
                           break;
                        case 1:
                           if (!this.field_354) {
                              this.method_196(class_11.method_79(271), (byte)0, (byte)4);
                              ++this.field_458;
                           }
                           break;
                        case 2:
                           this.method_204(this.field_274[1].field_46, this.field_274[1].field_47);
                           break;
                        case 3:
                           this.method_196(class_11.method_79(272), (byte)4, (byte)4);
                           this.method_195(10);
                           ++this.field_458;
                           break;
                        case 4:
                           this.method_196(class_11.method_79(273), (byte)1, (byte)4);
                           this.method_195(10);
                           ++this.field_458;
                           break;
                        case 5:
                           this.method_196(class_11.method_79(274), (byte)4, (byte)4);
                           ++this.field_458;
                           break;
                        case 6:
                           this.field_452 = this.method_168(5, 2, (byte)0);
                           this.field_452.method_48(7, 2, false);
                           ++this.field_458;
                           break;
                        case 7:
                           if (this.field_452.field_55 != 1) {
                              this.field_452.method_27();
                              this.field_452 = null;
                              this.field_453 = this.method_168(7, 3, (byte)0);
                              this.field_453.method_48(7, 2, false);
                              ++this.field_458;
                           }
                           break;
                        case 8:
                           if (this.field_453.field_55 != 1) {
                              this.field_453.method_27();
                              this.field_453 = null;
                              this.field_454 = this.method_168(9, 2, (byte)0);
                              this.field_454.method_48(7, 2, false);
                              ++this.field_458;
                           }
                           break;
                        case 9:
                           if (this.field_454.field_55 != 1) {
                              this.field_454.method_27();
                              this.field_454 = null;
                              this.field_274[1].method_48(7, 2, false);
                              this.method_195(20);
                              ++this.field_458;
                           }
                           break;
                        case 10:
                           this.method_204(9, 15);
                           this.field_262 = true;
                           break;
                        case 11:
                           class_12 var32;
                           (var32 = this.method_197((String)null, class_11.method_79(279), this.field_187, 2000)).method_92(this.field_188, 2, 17);
                           class_11.field_92.method_253(var32);
                           ++this.field_458;
                           break;
                        case 12:
                           this.field_368 = false;
                           this.method_117(this.method_168(9, 15, (byte)0));
                           ++this.field_458;
                           break;
                        case 13:
                           if (this.field_367 >= 2) {
                              this.method_196(class_11.method_79(275), (byte)0, (byte)4);
                              this.method_168(9, 15, (byte)0).method_27();
                              this.field_368 = true;
                              this.method_195(20);
                              ++this.field_458;
                           }
                           break;
                        case 14:
                           this.field_262 = false;
                           this.method_196(class_11.method_79(276), (byte)4, (byte)4);
                           ++this.field_458;
                           break;
                        case 15:
                           this.method_196(class_11.method_79(277), (byte)1, (byte)4);
                           this.method_195(10);
                           ++this.field_458;
                           break;
                        case 16:
                           this.field_262 = true;
                           this.field_406 = 4;
                           this.method_204(3, 9);
                           break;
                        case 17:
                           this.method_204(13, 4);
                           break;
                        case 18:
                           this.field_406 = 12;
                           this.method_196(class_11.method_79(278), (byte)5, (byte)4);
                           this.method_204(this.field_274[0].field_46, this.field_274[0].field_47);
                           break;
                        case 19:
                           this.method_203();
                           ++this.field_458;
                           break;
                        case 20:
                           if (this.field_369) {
                              this.field_262 = false;
                              this.field_337 = false;
                              this.method_195(20);
                              ++this.field_458;
                           }
                           break;
                        case 21:
                           this.field_353 = true;
                           this.field_352 = 0;
                           this.field_355 = true;
                           ++this.field_458;
                           break;
                        case 22:
                           if (this.field_352 >= 16) {
                              this.method_154(7, 2);
                              this.method_156(7, 2);
                              this.method_136();
                              this.field_274[1] = class_2.method_24((byte)9, (byte)1, 7, 2);
                              this.field_274[1].method_28(3);
                              class_2.method_24((byte)9, (byte)0, 6, 3);
                              class_2.method_24((byte)9, (byte)0, 8, 3).method_28(2);
                              class_2.method_24((byte)0, (byte)0, 6, 1);
                              class_2.method_24((byte)0, (byte)0, 8, 1);
                              this.method_195(10);
                              ++this.field_458;
                              class_15.method_270(8, 0);
                           }
                           break;
                        case 23:
                           this.field_353 = false;
                           this.field_354 = true;
                           this.field_352 = 0;
                           ++this.field_458;
                           break;
                        case 24:
                           if (this.field_352 >= 16) {
                              this.method_196(class_11.method_79(281), (byte)4, (byte)4);
                              this.method_195(15);
                              ++this.field_458;
                           }
                           break;
                        case 25:
                           this.method_196(class_11.method_79(282), (byte)0, (byte)4);
                           this.method_195(8);
                           ++this.field_458;
                           break;
                        case 26:
                           this.method_196(class_11.method_79(283), (byte)4, (byte)4);
                           this.method_195(15);
                           ++this.field_458;
                           break;
                        case 27:
                           this.method_120(this.field_241, 168, 48, 0, 0, 1, 50);
                           this.field_263.removeElement(this.field_274[1]);
                           this.method_195(15);
                           ++this.field_458;
                           break;
                        case 28:
                           class_15.method_267(400);
                           this.method_210(5000);
                           this.method_195(10);
                           ++this.field_458;
                           break;
                        case 29:
                           this.method_196(class_11.method_79(284), (byte)5, (byte)4);
                           this.method_195(5);
                           ++this.field_458;
                           break;
                        case 30:
                           this.method_196(class_11.method_79(285), (byte)2, (byte)4);
                           this.method_195(5);
                           ++this.field_458;
                           break;
                        case 31:
                           this.method_196(class_11.method_79(286), (byte)1, (byte)4);
                           this.method_195(5);
                           ++this.field_458;
                           break;
                        case 32:
                           this.method_196(class_11.method_79(287), (byte)0, (byte)4);
                           this.field_353 = true;
                           this.field_352 = 0;
                           ++this.field_458;
                           break;
                        case 33:
                           if (this.field_352 >= 16) {
                              this.method_195(10);
                              this.field_472 = false;
                              ++this.field_458;
                           }
                           break;
                        case 34:
                           this.method_160(5, 2, 2);
                           ++this.field_458;
                           break;
                        case 35:
                           class_15.method_270(0, 0);
                           this.method_161(class_11.method_79(288));
                           ++this.field_458;
                           break;
                        case 36:
                           this.field_466 = 0;
                           this.field_249 = 14;
                           ++this.field_458;
                     }
                  }
               } else {
                  if (this.field_458 <= 10) {
                     if (this.field_456 == null) {
                        this.field_456 = this.method_185(11, -1, (byte)0)[0];
                     }

                     if (this.field_456.field_55 == 3) {
                        this.field_456 = null;
                        this.method_206();
                        return;
                     }
                  }

                  switch (this.field_458) {
                     case 0:
                        if (this.field_274[0].field_55 != 1) {
                           this.field_405 = null;
                           this.method_195(10);
                           ++this.field_458;
                        }
                        break;
                     case 1:
                        this.method_196(class_11.method_79(262), (byte)5, (byte)4);
                        this.method_195(5);
                        ++this.field_458;
                        break;
                     case 2:
                        this.method_196(class_11.method_79(263), (byte)0, (byte)4);
                        this.method_195(5);
                        ++this.field_458;
                        break;
                     case 3:
                        class_2[] var19 = this.method_185(-1, -1, (byte)0);

                        for(int var7 = 0; var7 < var19.length; ++var7) {
                           var19[var7].field_67 = null;
                        }

                        this.method_203();
                        ++this.field_458;
                        break;
                     case 4:
                        if (this.field_273 >= 2) {
                           this.method_195(15);
                           this.field_337 = false;
                           this.field_262 = false;
                           this.method_204(11, 7);
                        }
                        break;
                     case 5:
                        class_2 var18;
                        (var18 = class_2.method_24((byte)5, (byte)1, 11, 8)).method_51(this.field_259);
                        var18.method_48(14, 7, true);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 6:
                        class_2 var31;
                        (var31 = class_2.method_24((byte)0, (byte)1, 11, 8)).method_51(this.field_259);
                        var31.method_48(13, 7, true);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 7:
                        class_2 var37;
                        (var37 = class_2.method_24((byte)3, (byte)1, 11, 8)).method_51(this.field_259);
                        var37.method_48(12, 7, true);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 8:
                        class_2.method_24((byte)1, (byte)1, 11, 8).method_48(13, 8, false);
                        this.method_195(20);
                        ++this.field_458;
                        break;
                     case 9:
                        this.method_196(class_11.method_79(264), (byte)5, (byte)4);
                        this.field_337 = true;
                        this.field_262 = true;
                        this.method_204(this.field_274[0].field_46, this.field_274[0].field_47);
                        break;
                     case 10:
                        boolean var30 = false;
                        class_2[] var6 = this.method_185(-1, 2, (byte)0);

                        for(int var17 = 0; var17 < var6.length; ++var17) {
                           if (var6[var17].field_46 <= 9 || var6[var17].field_47 >= 10) {
                              var30 = true;
                              break;
                           }
                        }

                        if (var30 || this.method_184(-1, -1, (byte)1) == 0) {
                           this.field_262 = false;
                           this.field_337 = false;
                           this.method_195(10);
                           ++this.field_458;
                        }
                        break;
                     case 11:
                        this.field_452 = this.method_185(11, -1, (byte)0)[0];
                        this.field_453 = class_2.method_24((byte)8, (byte)1, this.field_230, this.field_452.field_47);
                        this.method_204(this.field_230 - 1, this.field_452.field_47);
                        this.field_262 = false;
                        break;
                     case 12:
                        this.field_453.method_48(this.field_452.field_46, this.field_452.field_47, false);
                        this.method_195(5);
                        ++this.field_458;
                        break;
                     case 13:
                        this.method_196(class_11.method_79(265), (byte)5, (byte)4);
                        this.field_405 = this.field_453;
                        ++this.field_458;
                        break;
                     case 14:
                        if (this.field_453.field_55 != 1) {
                           this.method_196(class_11.method_79(266), (byte)0, (byte)4);
                           this.field_453.method_48(-1, this.field_453.field_47, false);
                           this.method_195(3);
                           ++this.field_458;
                        }
                        break;
                     case 15:
                        this.field_452.method_48(-1, this.field_453.field_47, false);
                        ++this.field_458;
                        break;
                     case 16:
                        if (this.field_453.field_55 != 1) {
                           this.method_195(10);
                           this.field_452.method_27();
                           this.field_453.method_27();
                           this.method_156(0, this.field_453.field_47);
                           this.field_452 = null;
                           this.field_453 = null;
                           this.field_405 = null;
                           ++this.field_458;
                        }
                        break;
                     case 17:
                        this.method_204(1, 9);
                        break;
                     case 18:
                        this.field_274[1] = class_2.method_24((byte)9, (byte)1, -2, 8);
                        this.field_274[1].method_48(0, 8, false);
                        class_2.method_24((byte)0, (byte)1, -1, 8).method_48(3, 8, false);
                        class_2.method_24((byte)0, (byte)1, -1, 10).method_48(1, 10, false);
                        class_2.method_24((byte)8, (byte)1, -3, 7).method_48(4, 8, false);
                        class_2.method_24((byte)8, (byte)1, -3, 11).method_48(2, 10, false);
                        class_2.method_24((byte)4, (byte)1, -2, 9).method_48(2, 9, false);
                        class_2.method_24((byte)6, (byte)1, -4, 9).method_48(4, 9, false);
                        class_2.method_24((byte)6, (byte)1, -6, 9).method_48(5, 10, false);
                        this.method_195(50);
                        ++this.field_458;
                        break;
                     case 19:
                        this.method_196(class_11.method_79(267), (byte)3, (byte)4);
                        ++this.field_458;
                        break;
                     case 20:
                        this.method_204(13, 14);
                        break;
                     case 21:
                        class_2.method_24((byte)0, (byte)1, 13, 14).method_48(12, 14, false);
                        this.method_195(5);
                        ++this.field_458;
                        break;
                     case 22:
                        class_2.method_24((byte)6, (byte)1, 13, 14).method_48(14, 14, false);
                        this.method_195(5);
                        ++this.field_458;
                        break;
                     case 23:
                        class_2.method_24((byte)2, (byte)1, 13, 14).method_48(13, 12, false);
                        this.method_195(5);
                        ++this.field_458;
                        break;
                     case 24:
                        class_2.method_24((byte)3, (byte)1, 13, 14).method_48(13, 15, false);
                        this.method_195(15);
                        ++this.field_458;
                        break;
                     case 25:
                        this.method_196(class_11.method_79(268), (byte)5, (byte)4);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 26:
                        this.method_196(class_11.method_79(269), (byte)0, (byte)4);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 27:
                        this.method_204(13, 17);
                        break;
                     case 28:
                        class_2 var16;
                        (var16 = class_2.method_24((byte)9, (byte)0, 13, 18)).method_28(2);
                        var16.method_48(13, 16, false);
                        class_2.method_24((byte)6, (byte)0, 12, 18).method_48(12, 16, false);
                        class_2.method_24((byte)8, (byte)0, 14, 19).method_48(14, 16, false);
                        class_2.method_24((byte)4, (byte)0, 13, 19).method_48(13, 17, false);
                        class_2.method_24((byte)1, (byte)0, 12, 19).method_48(12, 17, false);
                        this.method_195(20);
                        ++this.field_458;
                        break;
                     case 29:
                        this.method_196(class_11.method_79(270), (byte)1, (byte)4);
                        this.method_195(10);
                        ++this.field_458;
                        break;
                     case 30:
                        this.method_204(this.field_274[0].field_46, this.field_274[0].field_47);
                        break;
                     case 31:
                        this.field_316 = 18;
                        ++this.field_458;
                        break;
                     case 32:
                        this.field_337 = true;
                        this.field_262 = true;
                        this.field_320 = this.method_197(class_11.method_79(121 + this.field_252), class_11.method_79(138), this.field_187, -1);
                        this.field_320.method_87((byte)0, true);
                        this.field_320.method_88((class_11)null);
                        class_11.field_92.method_253(this.field_320);
                        ++this.field_458;
                        break;
                     case 33:
                        if (this.method_184(-1, -1, (byte)1) == 0 && this.method_183(1) == 0) {
                           this.field_337 = false;
                           this.field_262 = false;
                           this.method_195(10);
                           ++this.field_458;
                        }
                        break;
                     case 34:
                        this.method_205();
                  }
               }

               this.field_457 = null;
            }
         }

      }
   }

   // $FF: renamed from: t () void
   private void method_203() {
      this.field_451 = null;
      this.field_406 = 12;
      class_2.field_35 = class_2.field_34;
      this.field_320.method_87((byte)0, true);
      this.field_320.method_87((byte)1, false);
      class_11.field_92.method_253(this.field_320);
      this.field_262 = true;
      this.field_337 = true;
      this.field_339 = false;
      class_15.method_270(2, 0);
   }

   // $FF: renamed from: e (int, int) void
   private void method_204(int var1, int var2) {
      this.field_464 = var1;
      this.field_465 = var2;
      this.method_156(var1, var2);
      ++this.field_458;
   }

   // $FF: renamed from: u () void
   private void method_205() {
      class_15.method_271();
      class_15.method_272(6, 1);
      class_11.field_92.method_253(this.method_197((String)null, class_11.method_79(72), this.field_187, 3000));
      this.field_267 = this.field_251;
      this.field_458 = -1;
      this.field_466 = 0;
      this.field_249 = 10;
   }

   // $FF: renamed from: v () void
   private void method_206() {
      this.field_463 = true;
      this.field_458 = 0;
      this.method_195(20);
      class_15.method_271();
      class_15.method_272(7, 1);
   }

   // $FF: renamed from: b (aeii.a, aeii.a) void
   private void method_207(class_2 var1, class_2 var2) {
      try {
         System.gc();
         this.field_479 = this.field_187 - this.field_423;
         this.field_476 = true;
         this.field_299 = 0;
         this.field_470 = false;
         this.field_303 = var1;
         this.field_304 = var2;
         class_15.method_274();
         this.field_467 = new class_14(this, var1, (class_14)null);
         this.field_468 = new class_14(this, var2, this.field_467);
         this.field_467.field_568 = this.field_468;
         var1.method_33(var2);
         if (var2.method_37(var1.field_46, var1.field_47)) {
            var2.method_33(var1);
            this.field_475 = true;
         } else {
            this.field_475 = false;
         }

         this.field_467.field_549 = (byte)var1.field_54;
         this.field_467.field_551 = (byte)var1.method_42();
         this.field_468.field_549 = (byte)var2.field_54;
         this.field_468.field_551 = (byte)var2.method_42();
         class_15.method_272(field_218[this.field_270[this.field_272]], 0);
         this.field_292 = 2;
      } catch (IOException var3) {
         var3.printStackTrace();
      }
   }

   // $FF: renamed from: a (a.a.a.g) void
   public final void method_208(class_1 var1) {
      this.field_471.addElement(var1);
   }

   // $FF: renamed from: b (a.a.a.g) void
   public final void method_209(class_1 var1) {
      this.field_471.removeElement(var1);
   }

   // $FF: renamed from: a (int) void
   public final void method_210(int var1) {
      this.field_472 = true;
      this.field_473 = (long)var1;
      this.field_474 = this.field_251;
   }

   // $FF: renamed from: b (aeii.f) aeii.e
   private class_12 method_211(class_11 var1) {
      class_12 var2;
      if (this.field_482.length > 0) {
         class_12 var3;
         (var3 = (var2 = new class_12((byte)11, 0)).method_89(class_11.method_79(46))).field_160 = this.field_205[6];
         var2.method_100(this.field_482, this.field_186 / 2, (this.field_187 + var3.field_114) / 2, this.field_186, this.field_187 - var3.field_114, 3, 4);
      } else {
         (var2 = new class_12((byte)10, 0)).method_89(class_11.method_79(46)).field_160 = this.field_205[6];
         var2.method_95((String)null, class_11.method_79(52), this.field_186, -1);
      }

      var2.method_88(var1);
      class_11.field_92.method_253(var2);
      return var2;
   }

   // $FF: renamed from: c (byte[]) void
   private void method_212(byte[] var1) {
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
               if (this.field_496 == 0) {
                  var2 = var6.readInt() / 2;
                  this.field_482 = new String[var2];
                  this.field_483 = new String[var2];
                  this.field_484 = new String[var2];

                  for(int var13 = 0; var13 < var2; ++var13) {
                     this.field_482[var13] = var6.readUTF();
                     this.field_483[var13] = var6.readUTF();
                  }

                  this.field_390 = this.method_211(this.field_495);
               } else if (this.field_496 == 1) {
                  var6.readInt();
                  this.field_484[this.field_480] = var6.readUTF();
                  class_12 var12;
                  (var12 = this.method_198(this.field_482[this.field_480], this.field_484[this.field_480], this.field_187, this.field_187 / 2, -1)).method_88(this.field_495);
                  class_11.field_92.method_253(var12);
               } else if (this.field_496 != 2) {
                  if (this.field_496 == 3) {
                     String var11 = this.field_485[this.field_481];
                     byte[] var4 = new byte[var2];
                     var6.readFully(var4);
                     this.method_215(var11, var4);
                     this.method_125(this.field_393.field_141);
                     class_12 var7;
                     (var7 = this.method_197((String)null, class_11.method_82(45, var11), this.field_187, 2000)).method_88(this.field_393);
                     class_11.field_92.method_253(var7);
                  }
               } else {
                  var2 = var6.readInt() / 3;
                  this.field_485 = new String[var2];
                  this.field_486 = new String[var2];
                  this.field_487 = new int[var2];

                  for(int var3 = 0; var3 < var2; ++var3) {
                     this.field_485[var3] = var6.readUTF();
                     this.field_486[var3] = var6.readUTF();
                     this.field_487[var3] = Integer.parseInt(var6.readUTF());
                  }

                  this.method_125(this.field_495);
                  class_11.field_92.method_253(this.field_393);
               }

               this.field_495 = null;
               return;
            default:
               var6.close();
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      class_12 var8;
      (var8 = this.method_197((String)null, class_11.method_79(44), this.field_187, -1)).method_88(this.field_495);
      this.field_495 = null;
      class_11.field_92.method_253(var8);
   }

   // $FF: renamed from: a (int, java.lang.String, java.lang.String, aeii.f) void
   private void method_213(int var1, String var2, String var3, class_11 var4) {
      try {
         this.field_496 = var1;
         this.field_495 = var4;
         this.field_494 = false;
         this.field_491 = new ByteArrayOutputStream();
         this.field_492 = new DataOutputStream(this.field_491);
         this.field_492.writeInt(10001);
         this.field_492.writeUTF(this.field_488);
         this.field_492.writeUTF(this.field_489);
         this.field_493 = this.field_491.size();
         this.field_492.writeUTF("resourceName");
         this.field_492.writeUTF(var2);
         this.field_492.writeUTF("languageCode");
         this.field_492.writeUTF(var3);
         this.field_492.writeUTF("maxChunkSize");
         this.field_492.writeUTF("1024");
         this.field_492.writeUTF("chunk");
         this.field_492.writeUTF("0");
         this.field_492.writeUTF("requestId");
         this.field_492.writeUTF("0");
         class_12 var6;
         (var6 = this.method_197((String)null, class_11.method_79(43), this.field_187, -1)).method_88(var4);
         class_11.field_92.method_253(var6);
         (new Thread(this)).start();
      } catch (IOException var5) {
         var5.printStackTrace();
      }
   }

   public final void run() {
      byte[] var1 = null;
      HttpConnection var2 = null;

      try {
         byte[] var3 = this.field_491.toByteArray();
         this.field_491.close();
         this.field_492.close();
         var2 = (HttpConnection)Connector.open(this.field_490);
         if ("J4d28g1Kl490".length() > 0) {
            byte[] var4 = "J4d28g1Kl490".getBytes();

            for(int var5 = this.field_493; var5 < var3.length; ++var5) {
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

      if (!this.field_494) {
         this.method_212(var1);
      }

   }

   // $FF: renamed from: f (int) void
   private void method_214(int var1) {
      int var2 = this.field_385[var1];
      --this.field_383;
      String[] var3 = new String[this.field_383];
      int[] var4 = new int[this.field_383];
      System.arraycopy(this.field_384, 0, var3, 0, var1);
      System.arraycopy(this.field_384, var1 + 1, var3, var1, this.field_383 - var1);
      System.arraycopy(this.field_385, 0, var4, 0, var1);
      System.arraycopy(this.field_385, var1 + 1, var4, var1, this.field_383 - var1);
      this.field_384 = var3;
      this.field_385 = var4;
      class_15.method_243("download", var2);
      this.field_398 = class_15.method_244("download");
      this.method_216();

      for(int var5 = 0; var5 < 3; ++var5) {
         if (this.field_373[var5] == var2 + field_191.length) {
            this.field_372[var5] = -1;
            this.field_373[var5] = -1;
            this.field_371[var5] = "\n" + class_11.method_79(79) + "\n ";
            class_15.method_241("save", var5, new byte[0]);
         }
      }

   }

   // $FF: renamed from: a (java.lang.String, byte[]) void
   private void method_215(String var1, byte[] var2) {
      String[] var3 = new String[this.field_383 + 1];
      int[] var4 = new int[this.field_383 + 1];
      System.arraycopy(this.field_384, 0, var3, 0, this.field_383);
      System.arraycopy(this.field_385, 0, var4, 0, this.field_383);
      this.field_384 = var3;
      this.field_385 = var4;
      this.field_385[this.field_383] = class_15.method_242("download", var2);
      this.field_384[this.field_383] = var1;
      ++this.field_383;
      this.field_398 = class_15.method_244("download");
      this.method_216();
   }

   // $FF: renamed from: w () void
   private void method_216() {
      try {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         DataOutputStream var2;
         (var2 = new DataOutputStream(var1)).writeInt(this.field_383);

         for(int var3 = 0; var3 < this.field_383; ++var3) {
            var2.writeInt(this.field_385[var3]);
            var2.writeUTF(this.field_384[var3]);
         }

         class_15.method_241("settings", 2, var1.toByteArray());
         var2.close();
      } catch (IOException var4) {
         var4.printStackTrace();
      }
   }

   public final void commandAction(Command var1, Displayable var2) {
      if (var2 == this.field_514) {
         if (var1 != this.field_533) {
            if (var1 == this.field_537) {
               System.arraycopy(this.field_511, 0, this.field_512, 0, this.field_511.length);
               this.itemStateChanged(this.field_525);
               class_15.method_252(this.field_518);
               return;
            }

            if (var1 == this.field_535) {
               for(int var16 = 0; var16 < this.field_513.length; ++var16) {
                  this.field_513[var16] = true;
               }

               this.field_523.setSelectedFlags(this.field_513);
               this.itemStateChanged(this.field_523);
               return;
            }

            if (var1 != this.field_536) {
               class_15.method_252(class_11.field_92);
               class_11.field_92.method_253(this);
               this.method_222();
               return;
            }

            for(int var15 = 0; var15 < this.field_513.length; ++var15) {
               this.field_513[var15] = false;
            }

            this.field_523.setSelectedFlags(this.field_513);
            this.itemStateChanged(this.field_523);
            return;
         }

         if (this.field_520.size() == 0 || this.field_521.size() == 0 || this.field_522.size() == 0) {
            return;
         }

         int var13 = Integer.parseInt(this.field_521.getString());
         int var23 = Integer.parseInt(this.field_522.getString());
         if (var13 <= 0 || var23 <= 0) {
            return;
         }

         this.field_523.getSelectedFlags(this.field_513);
         int var3 = 0;

         for(int var4 = 0; var4 < this.field_513.length; ++var4) {
            if (this.field_513[var4]) {
               ++var3;
            }
         }

         if (var3 == 0) {
            return;
         }

         try {
            class_15.method_252(class_11.field_92);
            class_11.field_92.method_253(this);
            this.field_198 = 1;
            this.field_506 = 1;
            this.field_312 = true;
            class_11.field_92.method_254();
            this.method_112();
            String var10001 = this.field_520.getString();
            int[] var5 = this.field_511;
            int var56 = var23;
            var3 = var13;
            String var24 = var10001;
            class_13 var14 = this;

            try {
               class_15.method_271();
               var14.field_281 = new Vector();
               var14.field_353 = false;
               var14.field_262 = true;
               var14.field_298 = false;
               var14.field_369 = false;
               var14.field_264 = null;
               var14.field_283 = null;
               var14.field_366 = null;
               var14.field_284 = null;
               var14.field_286 = null;
               var14.field_356.removeAllElements();
               var14.field_273 = 0;
               var14.field_272 = 0;
               var14.field_272 = 0;
               var14.field_458 = 0;
               var14.method_136();
               var14.field_274 = null;
               var14.field_248 = null;
               var14.field_259 = null;
               var14.field_268 = 0;

               for(int var6 = 0; var6 < 5; ++var6) {
                  var14.field_269[var6] = -1;
               }

               var14.field_307 = true;
               class_15.method_274();
               var14.field_370 = var24;
               var14.field_230 = var3;
               var14.field_231 = var56;
               var14.field_248 = new byte[var14.field_230][var14.field_231];
               var14.field_259 = new byte[var14.field_230][var14.field_231];
               var14.field_442 = new byte[var14.field_230][var14.field_231];
               var14.field_443 = 0;
               int var61 = 0;
               Vector var25 = new Vector();
               Vector var45 = new Vector();
               class_15.method_237(var5, true);

               for(short var7 = 0; var7 < var14.field_230; ++var7) {
                  for(short var8 = 0; var8 < var14.field_231; ++var8) {
                     var14.field_248[var7][var8] = (byte)class_15.method_236(var5);
                     var14.field_259[var7][var8] = 0;
                     if (method_176(var14.field_248[var7][var8])) {
                        var56 = var14.method_178(var7, var8);
                        byte[] var9;
                        (var9 = new byte[3])[0] = (byte)var7;
                        var9[1] = (byte)var8;
                        var9[2] = (byte)var56;
                        var25.addElement(var9);
                        ++var61;
                        if (var14.method_169(var7, var8) == 9) {
                           if (var14.field_198 == 1 && var56 != 0 && var14.field_269[var56] == -1) {
                              var14.field_270[var14.field_268] = (byte)var56;
                              byte[] var10000 = var14.field_269;
                              byte var10004 = var14.field_268;
                              var14.field_268 = (byte)(var10004 + 1);
                              var10000[var56] = var10004;
                           }

                           byte[] var58;
                           (var58 = new byte[2])[0] = (byte)var7;
                           var58[1] = (byte)var8;
                           var45.addElement(var58);
                           ++var14.field_443;
                        }
                     }
                  }
               }

               class_15.method_237(var5, false);
               var14.field_440 = new int[var61];
               var14.field_327 = new byte[var61][];
               var25.copyInto(var14.field_327);
               var14.field_328 = new byte[var14.field_443][];
               var45.copyInto(var14.field_328);
               var14.field_226 = var14.field_230 * 24;
               var14.field_227 = var14.field_231 * 24;
               var14.field_463 = false;
               var14.field_456 = null;
               var14.field_452 = null;
               var14.field_453 = null;
               var14.field_454 = null;
               var14.field_455 = null;
               var14.field_457 = null;
               if (var14.field_198 == 1) {
                  var14.field_458 = 100;
                  var14.field_337 = true;
               }

               var14.field_437 = new class_2[var14.field_327.length];
               var14.field_438 = new byte[var14.field_327.length];
               var14.field_341 = new class_1[var14.field_327.length];

               for(short var62 = 0; var62 < var14.field_327.length; ++var62) {
                  if (var14.method_169(var14.field_327[var62][0], var14.field_327[var62][1]) == 8) {
                     var14.field_341[var62] = class_1.method_19(var14.field_342, 0, -1, 0, 1, 250, (byte)0);
                     var14.field_341[var62].field_17 = false;
                  }
               }

               class_15.method_270(field_217[1], 0);
               var14.field_228 = 0;
               var14.field_229 = 0;
               var14.field_246 = 0;
               var14.field_247 = 0;
               var14.field_234.method_11(var14.field_246 * 24, var14.field_247 * 24);
            } catch (Exception var11) {
               var11.printStackTrace();
            }

            this.field_312 = false;
            this.field_249 = 0;
            this.method_222();
         } catch (Exception var12) {
            var12.printStackTrace();
            return;
         }
      } else {
         if (var2 == this.field_515) {
            if (var1 == this.field_533) {
               this.field_523.getSelectedFlags(this.field_513);
               int var22 = 0;

               for(int var43 = 0; var43 < this.field_513.length; ++var43) {
                  if (this.field_513[var43]) {
                     ++var22;
                  }
               }

               if (var22 == 0) {
                  return;
               }

               this.itemStateChanged(this.field_523);
               class_15.method_252(class_11.field_92);
               class_11.field_92.method_253(this);
               return;
            }

            if (var1 == this.field_537) {
               System.arraycopy(this.field_511, 0, this.field_512, 0, this.field_511.length);
               this.itemStateChanged(this.field_525);
               class_15.method_252(this.field_518);
               return;
            }

            if (var1 == this.field_535) {
               for(int var21 = 0; var21 < this.field_513.length; ++var21) {
                  this.field_513[var21] = true;
               }

               this.field_523.setSelectedFlags(this.field_513);
               this.itemStateChanged(this.field_523);
               return;
            }

            if (var1 != this.field_536) {
               class_15.method_252(class_11.field_92);
               class_11.field_92.method_253(this);
               return;
            }

            for(int var20 = 0; var20 < this.field_513.length; ++var20) {
               this.field_513[var20] = false;
            }

            this.field_523.setSelectedFlags(this.field_513);
            this.itemStateChanged(this.field_523);
            return;
         }

         if (var2 == this.field_516) {
            if (var1 == this.field_533) {
               if (this.field_520.size() == 0) {
                  return;
               }

               class_15.method_252(class_11.field_92);
               class_11.field_92.method_253(this);
               this.field_370 = this.field_520.getString();
               return;
            }

            if (var1 == this.field_538) {
               this.field_520.setString(Long.toString(System.currentTimeMillis(), 36).toUpperCase());
               return;
            }

            class_15.method_252(class_11.field_92);
            class_11.field_92.method_253(this);
            return;
         }

         if (var2 == this.field_517) {
            if (var1 == this.field_533) {
               if (this.field_527.size() == 0 || this.field_528.size() == 0 || this.field_529.size() == 0 || this.field_530.size() == 0) {
                  return;
               }

               this.method_139(Integer.parseInt(this.field_527.getString()), Integer.parseInt(this.field_528.getString()), Integer.parseInt(this.field_529.getString()), Integer.parseInt(this.field_530.getString()), this.field_511);
            }

            class_15.method_252(class_11.field_92);
            class_11.field_92.method_253(this);
            return;
         }

         if (var2 == this.field_518) {
            if (var1 != this.field_533) {
               System.arraycopy(this.field_512, 0, this.field_511, 0, this.field_511.length);
            } else {
               this.field_523.getSelectedFlags(this.field_513);

               for(int var17 = 0; var17 < this.field_511.length; ++var17) {
                  this.field_513[var17] = this.field_511[var17] > 0;
               }

               this.field_523.setSelectedFlags(this.field_513);
               this.itemStateChanged(this.field_523);
            }

            if (this.field_515 != null) {
               class_15.method_252(this.field_515);
               return;
            }

            if (this.field_514 != null) {
               class_15.method_252(this.field_514);
               return;
            }
         } else {
            if (var2 == this.field_519) {
               if (var1 == this.field_533) {
                  int[] var19 = new int[]{100 - this.field_532.getValue(), 100};

                  for(int var42 = 0; var42 < this.field_230; ++var42) {
                     for(int var55 = 0; var55 < this.field_231; ++var55) {
                        if (this.field_259[var42][var55] > 0) {
                           this.field_259[var42][var55] = (byte)class_15.method_236(var19);
                        }
                     }
                  }
               }

               class_15.method_252(class_11.field_92);
               class_11.field_92.method_253(this);
               return;
            }

            if (var2 == this.field_531) {
               if (var1 == this.field_533) {
                  try {
                     String[] var18;
                     if ((var18 = class_15.method_239(this.field_531.getString().toLowerCase(), ' '))[0].equals("win")) {
                        if (this.field_252 == 7) {
                           this.field_369 = true;
                        } else {
                           this.method_205();
                        }
                     } else if (var18[0].equals("fps")) {
                        class_11.field_92.method_266(Integer.parseInt(var18[1]));
                     } else if (var18[0].equals("money")) {
                        if (var18[1].equals("add")) {
                           if (var18[2].equals("player")) {
                              int[] var68 = this.field_277;
                              byte var70 = this.field_272;
                              var68[var70] += Integer.parseInt(var18[3]);
                           } else if (var18[2].equals("enemy")) {
                              for(int var41 = 0; var41 < 4; ++var41) {
                                 if (this.field_279[var41] == 0) {
                                    int[] var67 = this.field_277;
                                    var67[var41] += Integer.parseInt(var18[3]);
                                 }
                              }
                           } else if (var18[2].equals("all")) {
                              for(int var40 = 0; var40 < 4; ++var40) {
                                 int[] var66 = this.field_277;
                                 var66[var40] += Integer.parseInt(var18[3]);
                              }
                           }
                        } else if (var18[1].equals("remove")) {
                           if (var18[2].equals("player")) {
                              int[] var65 = this.field_277;
                              byte var69 = this.field_272;
                              var65[var69] -= Integer.parseInt(var18[3]);
                           } else if (var18[2].equals("enemy")) {
                              for(int var39 = 0; var39 < 4; ++var39) {
                                 if (this.field_279[var39] == 0) {
                                    int[] var64 = this.field_277;
                                    var64[var39] -= Integer.parseInt(var18[3]);
                                 }
                              }
                           } else if (var18[2].equals("all")) {
                              for(int var38 = 0; var38 < 4; ++var38) {
                                 int[] var63 = this.field_277;
                                 var63[var38] -= Integer.parseInt(var18[3]);
                              }
                           }
                        }
                     } else if (!var18[0].equals("occupy")) {
                        if (var18[0].equals("unit")) {
                           if (var18[1].equals("reset")) {
                              if (var18[2].equals("current")) {
                                 class_2 var37;
                                 if ((var37 = this.method_168(this.field_246, this.field_247, (byte)0)) != null) {
                                    var37.field_55 = 0;
                                 }
                              } else if (var18[2].equals("player")) {
                                 for(int var36 = 0; var36 < this.field_263.size(); ++var36) {
                                    class_2 var54;
                                    if ((var54 = (class_2)this.field_263.elementAt(var36)).field_45 == this.field_272) {
                                       var54.field_55 = 0;
                                    }
                                 }
                              } else if (var18[2].equals("enemy")) {
                                 for(int var35 = 0; var35 < this.field_263.size(); ++var35) {
                                    class_2 var53;
                                    if ((var53 = (class_2)this.field_263.elementAt(var35)).field_45 != this.field_272) {
                                       var53.field_55 = 0;
                                    }
                                 }
                              } else if (var18[2].equals("all")) {
                                 for(int var34 = 0; var34 < this.field_263.size(); ++var34) {
                                    ((class_2)this.field_263.elementAt(var34)).field_55 = 0;
                                 }
                              }
                           } else if (var18[1].equals("promote")) {
                              if (var18[2].equals("current")) {
                                 class_2 var33;
                                 if ((var33 = this.method_168(this.field_246, this.field_247, (byte)0)) != null) {
                                    var33.method_22((byte)Integer.parseInt(var18[3]));
                                 }
                              } else if (var18[2].equals("player")) {
                                 byte var32 = (byte)Integer.parseInt(var18[3]);

                                 for(int var52 = 0; var52 < this.field_263.size(); ++var52) {
                                    class_2 var60;
                                    if ((var60 = (class_2)this.field_263.elementAt(var52)).field_45 == this.field_272) {
                                       var60.method_22(var32);
                                    }
                                 }
                              } else if (var18[2].equals("enemy")) {
                                 byte var31 = (byte)Integer.parseInt(var18[3]);

                                 for(int var51 = 0; var51 < this.field_263.size(); ++var51) {
                                    class_2 var59;
                                    if ((var59 = (class_2)this.field_263.elementAt(var51)).field_45 != this.field_272) {
                                       var59.method_22(var31);
                                    }
                                 }
                              } else if (var18[2].equals("all")) {
                                 byte var30 = (byte)Integer.parseInt(var18[3]);

                                 for(int var50 = 0; var50 < this.field_263.size(); ++var50) {
                                    ((class_2)this.field_263.elementAt(var50)).method_22(var30);
                                 }
                              }
                           }
                        }
                     } else if (var18[1].equals("house")) {
                        if (var18[2].equals("civilian")) {
                           for(int var29 = 0; var29 < this.field_230; ++var29) {
                              for(int var49 = 0; var49 < this.field_231; ++var49) {
                                 if (this.method_169(var29, var49) == 8 && this.method_178(var29, var49) == 0) {
                                    this.method_175(var29, var49, this.field_270[this.field_272]);
                                 }
                              }
                           }
                        } else if (var18[2].equals("all")) {
                           for(int var28 = 0; var28 < this.field_230; ++var28) {
                              for(int var48 = 0; var48 < this.field_231; ++var48) {
                                 if (this.method_169(var28, var48) == 8) {
                                    this.method_175(var28, var48, this.field_270[this.field_272]);
                                 }
                              }
                           }
                        }
                     } else if (var18[1].equals("castle")) {
                        if (var18[2].equals("civilian")) {
                           for(int var27 = 0; var27 < this.field_230; ++var27) {
                              for(int var47 = 0; var47 < this.field_231; ++var47) {
                                 if (this.method_169(var27, var47) == 9 && this.method_178(var27, var47) == 0) {
                                    this.method_175(var27, var47, this.field_270[this.field_272]);
                                 }
                              }
                           }
                        } else if (var18[2].equals("all")) {
                           for(int var26 = 0; var26 < this.field_230; ++var26) {
                              for(int var46 = 0; var46 < this.field_231; ++var46) {
                                 if (this.method_169(var26, var46) == 9) {
                                    this.method_175(var26, var46, this.field_270[this.field_272]);
                                 }
                              }
                           }
                        }
                     }
                  } catch (Exception var10) {
                     return;
                  }
               }

               class_15.method_252(class_11.field_92);
               class_11.field_92.method_253(this);
            }
         }
      }

   }

   public final void itemStateChanged(Item var1) {
      if (var1 == this.field_523) {
         this.field_523.getSelectedFlags(this.field_513);
         int var3 = 0;

         for(int var2 = 0; var2 < this.field_511.length; ++var2) {
            if (this.field_511[var2] > var3) {
               var3 = this.field_511[var2];
            }
         }

         if (var3 == 0) {
            var3 = 100;
         }

         for(int var4 = 0; var4 < this.field_513.length; ++var4) {
            if (this.field_513[var4]) {
               if (this.field_511[var4] <= 0) {
                  this.field_511[var4] = var3;
               }
            } else {
               this.field_511[var4] = 0;
            }
         }

      } else if (var1 == this.field_525) {
         this.field_525.setLabel(class_11.method_82(308, Integer.toString(this.field_525.getValue() + 1)));
         this.field_526.setImage(this.field_523.getImage(this.field_525.getValue()));
         this.field_524.setString(Integer.toString(this.field_511[this.field_525.getValue()]));
      } else {
         if (var1 == this.field_524) {
            if (this.field_524.size() > 0) {
               this.field_511[this.field_525.getValue()] = Integer.parseInt(this.field_524.getString());
               return;
            }
         } else if (var1 == this.field_532) {
            this.field_532.setLabel(class_11.method_82(315, Integer.toString(this.field_532.getValue()) + "%"));
         }

      }
   }

   // $FF: renamed from: x () void
   private void method_217() {
      if (this.field_516 == null) {
         this.field_516 = new Form(class_11.method_79(304));
         this.field_520 = new TextField(class_11.method_79(294), this.field_370, 100, 0);
         this.field_516.append(this.field_520);
         this.field_516.addCommand(this.field_533);
         this.field_516.addCommand(this.field_538);
         this.field_516.addCommand(this.field_534);
         this.field_516.setCommandListener(this);
      }
   }

   // $FF: renamed from: y () void
   private void method_218() {
      if (this.field_517 == null) {
         this.field_517 = new Form(class_11.method_79(306));
         this.field_527 = new TextField(class_11.method_79(310), "0", 20, 2);
         this.field_528 = new TextField(class_11.method_79(311), "0", 20, 2);
         this.field_529 = new TextField(class_11.method_79(312), "0", 20, 2);
         this.field_530 = new TextField(class_11.method_79(313), "0", 20, 2);
         this.field_517.append(this.field_527);
         this.field_517.append(this.field_528);
         this.field_517.append(this.field_529);
         this.field_517.append(this.field_530);
         this.field_517.addCommand(this.field_533);
         this.field_517.addCommand(this.field_534);
         this.field_517.setCommandListener(this);
      }
   }

   // $FF: renamed from: z () void
   private void method_219() {
      if (this.field_519 == null) {
         this.field_519 = new Form(class_11.method_79(307));
         this.field_532 = new Gauge("", true, 100, 100);
         this.field_532.setLayout(2049);
         this.itemStateChanged(this.field_532);
         this.field_519.append(this.field_532);
         this.field_519.addCommand(this.field_533);
         this.field_519.addCommand(this.field_534);
         this.field_519.setCommandListener(this);
         this.field_519.setItemStateListener(this);
      }
   }

   // $FF: renamed from: A () void
   private void method_220() {
      if (this.field_515 == null) {
         this.field_515 = new Form(class_11.method_79(303));
         this.field_523 = new ChoiceGroup(class_11.method_79(297), 2);
         if (this.field_511 == null) {
            this.field_511 = new int[this.field_233.length];

            for(int var1 = 0; var1 < this.field_511.length; ++var1) {
               this.field_511[var1] = 100;
            }
         }

         this.field_512 = new int[this.field_233.length];
         this.field_513 = new boolean[this.field_233.length];

         for(int var2 = 0; var2 < this.field_233.length; ++var2) {
            this.field_523.append(" " + class_11.method_82(308, Integer.toString(var2 + 1)) + " ", this.field_233[var2].method_283());
            this.field_523.setSelectedIndex(var2, this.field_511[var2] > 0);
         }

         this.itemStateChanged(this.field_523);
         this.field_515.append(this.field_523);
         this.field_515.addCommand(this.field_533);
         this.field_515.addCommand(this.field_537);
         this.field_515.addCommand(this.field_535);
         this.field_515.addCommand(this.field_536);
         this.field_515.addCommand(this.field_534);
         this.field_515.setCommandListener(this);
         this.field_515.setItemStateListener(this);
         this.method_223();
      }
   }

   // $FF: renamed from: B () void
   private void method_221() {
      this.field_514 = new Form(class_11.method_79(292));
      this.field_520 = new TextField(class_11.method_79(294), Long.toString(System.currentTimeMillis(), 36).toUpperCase(), 100, 0);
      int var1 = (super.field_93 + 23) / 24;
      int var2 = (super.field_94 - field_185 + 23) / 24;
      this.field_521 = new TextField(class_11.method_79(295), Integer.toString(var1), 3, 2);
      this.field_522 = new TextField(class_11.method_79(296), Integer.toString(var2), 3, 2);
      this.field_523 = new ChoiceGroup(class_11.method_79(297), 2);
      if (this.field_511 == null) {
         this.field_511 = new int[this.field_233.length];

         for(int var3 = 0; var3 < this.field_511.length; ++var3) {
            this.field_511[var3] = 100;
         }
      }

      this.field_512 = new int[this.field_233.length];
      this.field_513 = new boolean[this.field_233.length];

      for(int var4 = 0; var4 < this.field_233.length; ++var4) {
         this.field_523.append(" " + class_11.method_82(308, Integer.toString(var4 + 1)) + " ", this.field_233[var4].method_283());
         this.field_523.setSelectedIndex(var4, this.field_511[var4] > 0);
      }

      this.itemStateChanged(this.field_523);
      this.field_514.append(this.field_520);
      this.field_514.append(this.field_521);
      this.field_514.append(this.field_522);
      this.field_514.append(this.field_523);
      this.field_514.addCommand(this.field_533);
      this.field_514.addCommand(this.field_537);
      this.field_514.addCommand(this.field_535);
      this.field_514.addCommand(this.field_536);
      this.field_514.addCommand(this.field_534);
      this.field_514.setCommandListener(this);
      this.field_514.setItemStateListener(this);
      this.method_223();
   }

   // $FF: renamed from: C () void
   private void method_222() {
      this.field_514 = null;
      this.field_520 = null;
      this.field_521 = null;
      this.field_522 = null;
      this.field_523 = null;
   }

   // $FF: renamed from: D () void
   private void method_223() {
      if (this.field_518 == null) {
         this.field_518 = new Form(class_11.method_79(304));
         this.field_526 = new ImageItem((String)null, (Image)null, 1, (String)null);
         this.field_526.setLayout(2049);
         this.field_525 = new Gauge("", true, this.field_233.length - 1, 0);
         this.field_525.setLayout(2049);
         this.field_524 = new TextField(class_11.method_79(309), "", 20, 2);
         this.field_524.setLayout(2049);
         this.field_518.append(this.field_526);
         this.field_518.append(this.field_525);
         this.field_518.append(this.field_524);
         this.field_518.addCommand(this.field_533);
         this.field_518.addCommand(this.field_534);
         this.field_518.setCommandListener(this);
         this.field_518.setItemStateListener(this);
      }
   }

   // $FF: renamed from: E () void
   private void method_224() {
      this.field_515 = null;
      this.field_523 = null;
      this.field_511 = null;
      this.field_512 = null;
      this.field_518 = null;
      this.field_524 = null;
      this.field_525 = null;
      this.field_526 = null;
      this.field_516 = null;
      this.field_520 = null;
      this.field_517 = null;
      this.field_527 = null;
      this.field_528 = null;
      this.field_529 = null;
      this.field_530 = null;
      this.field_519 = null;
      this.field_532 = null;
      this.field_506 = 0;
   }

   // $FF: renamed from: F () void
   private void method_225() {
      if (this.field_531 == null) {
         this.field_531 = new TextBox(class_11.method_79(314), "", 100, 0);
         this.field_531.addCommand(this.field_533);
         this.field_531.addCommand(this.field_534);
         this.field_531.setCommandListener(this);
      }
   }

   static {
      int[][][] var10000 = new int[][][]{new int[0][], {{150, 217, 244}, {65, 149, 233}, {0, 100, 198}, {12, 53, 112}}, {{244, 158, 156}, {219, 36, 113}, {161, 0, 112}, {95, 5, 120}}, {{171, 237, 90}, {99, 190, 37}, {0, 153, 55}, {0, 85, 82}}, {{0, 118, 150}, {0, 65, 114}, {0, 43, 75}, {0, 22, 48}}};
      field_215 = new String[]{"blue", "red", "green", "black"};
      field_216 = new int[]{10526880, 26054, 15204434, 39473, 16754};
      field_217 = new int[]{-1, 2, 3, 2, 3};
      field_218 = new int[]{-1, 4, 5, 4, 5};
      field_220 = new byte[]{1, 2};
      field_221 = new byte[]{0, 5, 10, 10, 15, 0, 5, 15, 15, 15};
      field_222 = new byte[]{1, 1, 2, 2, 3, 3, 1, 1, 1, 1};
      field_314 = new int[]{85, 83, 83, 83, 83, 83, 83, 83, 83, 175, 84, 84, 84, 175, 147, 159, 151, 155, 167, 171};
      field_315 = new int[]{15, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214};
      field_427 = new byte[]{0, 2, 3, 3, 1, 3, 3, 3, 3, 3, 3, 3};
   }
}
