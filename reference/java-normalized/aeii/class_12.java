package aeii;

import a.a.a.class_1;
import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

// $FF: renamed from: aeii.e
public final class class_12 extends class_11 {
   // $FF: renamed from: a boolean[]
   private boolean[] field_102 = new boolean[]{false, false};
   // $FF: renamed from: a int
   public int field_103 = 13553358;
   // $FF: renamed from: n int
   private static int field_104;
   // $FF: renamed from: o int
   private static int field_105;
   // $FF: renamed from: b byte
   private byte field_106 = 2;
   // $FF: renamed from: a short
   private short field_107 = 3;
   // $FF: renamed from: a aeii.g
   public static class_13 field_108;
   // $FF: renamed from: a java.lang.String[]
   public String[] field_109;
   // $FF: renamed from: a aeii.d[]
   private class_16[] field_110;
   // $FF: renamed from: p int
   private int field_111;
   // $FF: renamed from: q int
   private int field_112;
   // $FF: renamed from: b int
   public int field_113;
   // $FF: renamed from: c int
   public int field_114;
   // $FF: renamed from: r int
   private int field_115;
   // $FF: renamed from: a javax.microedition.lcdui.Font
   private Font field_116;
   // $FF: renamed from: d int
   public int field_117;
   // $FF: renamed from: s int
   private int field_118;
   // $FF: renamed from: t int
   private int field_119;
   // $FF: renamed from: u int
   private int field_120;
   // $FF: renamed from: v int
   private int field_121;
   // $FF: renamed from: w int
   private int field_122;
   // $FF: renamed from: a byte
   public byte field_123;
   // $FF: renamed from: x int
   private int field_124;
   // $FF: renamed from: c boolean
   private boolean field_125;
   // $FF: renamed from: d boolean
   private boolean field_126;
   // $FF: renamed from: a aeii.a[]
   public class_2[] field_127;
   // $FF: renamed from: y int
   private int field_128;
   // $FF: renamed from: z int
   private int field_129;
   // $FF: renamed from: c byte
   private byte field_130;
   // $FF: renamed from: A int
   private int field_131;
   // $FF: renamed from: B int
   private int field_132;
   // $FF: renamed from: C int
   private int field_133;
   // $FF: renamed from: D int
   private int field_134;
   // $FF: renamed from: b java.lang.String[]
   private String[] field_135;
   // $FF: renamed from: e int
   public int field_136;
   // $FF: renamed from: e boolean
   private boolean field_137;
   // $FF: renamed from: E int
   private int field_138;
   // $FF: renamed from: a aeii.a
   public class_2 field_139;
   // $FF: renamed from: f boolean
   private boolean field_140;
   // $FF: renamed from: a aeii.f
   public class_11 field_141;
   // $FF: renamed from: F int
   private int field_142;
   // $FF: renamed from: a a.a.a.g[]
   private class_1[] field_143;
   // $FF: renamed from: G int
   private int field_144;
   // $FF: renamed from: g boolean
   private boolean field_145;
   // $FF: renamed from: a java.util.Vector
   private Vector field_146;
   // $FF: renamed from: f int
   public int field_147;
   // $FF: renamed from: a boolean
   public boolean field_148;
   // $FF: renamed from: b boolean
   public boolean field_149;
   // $FF: renamed from: H int
   private int field_150;
   // $FF: renamed from: I int
   private int field_151;
   // $FF: renamed from: J int
   private int field_152;
   // $FF: renamed from: K int
   private int field_153;
   // $FF: renamed from: a byte[][]
   private byte[][] field_154;
   // $FF: renamed from: b java.util.Vector
   private Vector field_155;
   // $FF: renamed from: L int
   private int field_156;
   // $FF: renamed from: M int
   private int field_157;
   // $FF: renamed from: g int
   public int field_158;
   // $FF: renamed from: h int
   public int field_159;
   // $FF: renamed from: a aeii.d
   public class_16 field_160;
   // $FF: renamed from: a aeii.e
   private class_12 field_161;
   // $FF: renamed from: a int[]
   public int[] field_162;
   // $FF: renamed from: N int
   private int field_163;
   // $FF: renamed from: O int
   private int field_164;
   // $FF: renamed from: b int[]
   private int[] field_165;
   // $FF: renamed from: i int
   public int field_166;
   // $FF: renamed from: P int
   private int field_167;
   // $FF: renamed from: a short[]
   private short[] field_168;
   // $FF: renamed from: Q int
   private int field_169;
   // $FF: renamed from: R int
   private int field_170;
   // $FF: renamed from: S int
   private int field_171;
   // $FF: renamed from: T int
   private int field_172;
   // $FF: renamed from: U int
   private int field_173;
   // $FF: renamed from: V int
   private int field_174;
   // $FF: renamed from: W int
   private int field_175;
   // $FF: renamed from: X int
   private int field_176;
   // $FF: renamed from: Y int
   private int field_177;
   // $FF: renamed from: Z int
   private int field_178;
   // $FF: renamed from: d byte
   private byte field_179;
   // $FF: renamed from: a a.a.a.g
   private class_1 field_180;

   // $FF: renamed from: a () void
   public final void method_72() {
      this.field_176 = 0;
      if (this.field_143 != null) {
         this.method_97();
      }

      this.field_144 = 4;
      this.field_125 = true;
      this.field_126 = true;
      if (field_108 != null) {
         field_108.method_72();
      }

      if (this.field_161 != null) {
         this.field_161.field_125 = true;
      }

      if (this.field_123 == 15) {
         for(int var1 = 0; var1 < this.field_146.size(); ++var1) {
            class_12 var2;
            (var2 = (class_12)this.field_146.elementAt(var1)).method_72();
            var2.field_126 = false;
         }
      }

   }

   // $FF: renamed from: a (byte, boolean) void
   public final void method_87(byte var1, boolean var2) {
      this.field_102[var1] = var2;
   }

   // $FF: renamed from: a (aeii.f) void
   public final void method_88(class_11 var1) {
      this.field_141 = var1;
      this.field_102[1] = var1 != null;
   }

   public class_12(byte var1, int var2) {
      super();
      this.field_116 = class_15.field_591;
      this.field_125 = false;
      this.field_126 = false;
      this.field_130 = -1;
      this.field_136 = -1;
      this.field_137 = true;
      this.field_140 = true;
      this.field_145 = false;
      this.field_147 = -1;
      this.field_158 = 2370117;
      this.field_159 = 2370117;
      this.field_167 = -1;
      this.field_123 = (byte)var1;
      this.field_124 = var2;
      if (var1 == 15) {
         this.field_166 = field_108.field_187 - field_108.field_238.field_7;
         this.field_137 = true;
      } else if (var1 != 0 && var1 != 11) {
         if (var1 == 3) {
            this.field_180 = field_108.field_329;
            this.method_96();
            this.field_137 = false;
            this.field_148 = true;
            this.field_128 = class_15.field_593 - class_15.field_592;
            this.field_113 = field_108.field_186;
            this.field_114 = field_108.field_329.field_7 + field_105;
            if ((var2 & 2) == 0) {
               this.field_114 += 5;
            }

            this.field_127 = class_2.method_57(field_108.field_272);
            this.field_118 = this.field_127.length;
            var1 = this.field_113 - (field_108.field_236.field_6 << 1);
            if ((var2 & 4) == 0) {
               var1 -= 8;
            }

            if ((var2 & 8) == 0) {
               var1 -= 8;
            }

            this.field_132 = var1 / (field_108.field_329.field_6 + 3);
            if (this.field_132 > this.field_118) {
               this.field_132 = this.field_118;
            }

            this.field_115 = var1 / this.field_132;
            this.field_142 = (var1 - this.field_115 * this.field_132) / 2;
            this.field_106 = 2;
         } else if (var1 != 2 && var1 != 5) {
            if (var1 != 7 && var1 == 8) {
               this.field_107 = 8;
               this.field_102[0] = true;
            }
         } else {
            this.field_137 = false;
            this.field_114 = 5 + field_104 + 24 + field_105 + (field_108.field_330.field_7 << 1) + field_104 + field_104 + 1;
            if (var1 == 5) {
               this.field_114 += field_105 + class_15.field_592;
               this.field_139 = field_108.method_168(field_108.field_246, field_108.field_247, (byte)0);
               this.field_117 = this.field_139.field_44;
               this.field_113 = field_108.field_186;
            } else {
               this.field_113 = field_108.field_186;
            }
         }
      } else {
         this.field_102[0] = true;
         this.field_102[1] = true;
      }

      this.field_125 = true;
   }

   // $FF: renamed from: a (java.lang.String) aeii.e
   public final class_12 method_89(String var1) {
      this.field_161 = new class_12((byte)10, 0);
      this.field_161.method_95((String)null, var1, field_108.field_186, -1);
      return this.field_161;
   }

   // $FF: renamed from: a (aeii.e, int, int, int) void
   public final void method_90(class_12 var1, int var2, int var3, int var4) {
      if (this.field_146 == null) {
         this.field_146 = new Vector();
      }

      if (this.field_165 == null) {
         this.field_165 = new int[5];

         for(int var5 = 0; var5 < 5; ++var5) {
            this.field_165[var5] = this.field_166;
            if (var5 > 0) {
               int[] var10000 = this.field_165;
               var10000[var5] -= field_108.field_238.field_7;
            }
         }
      }

      var1.method_92(var2, var3, var4);
      int var11 = var1.field_112;

      for(int var6 = 0; var6 < 5; ++var6) {
         if (var11 < this.field_165[var6]) {
            if (var11 + var1.field_114 > this.field_165[var6]) {
               this.field_165[var6] = var11;
               if (var6 + 1 > this.field_164) {
                  this.field_164 = var6 + 1;
               }
            }
            break;
         }

         var11 -= this.field_165[var6];
      }

      var4 = 0;
      var3 = 0;
      var1.field_102[var3] = (boolean)var4;
      var4 = 0;
      var3 = 1;
      var1.field_102[var3] = (boolean)var4;
      this.field_146.addElement(var1);
   }

   // $FF: renamed from: a (int, int, byte[][], java.util.Vector) void
   public final void method_91(int var1, int var2, byte[][] var3, Vector var4) {
      this.field_124 = 15;
      this.field_154 = var3;
      this.field_155 = var4;
      this.field_107 = 8;
      this.field_102[0] = true;
      this.field_137 = true;
      this.field_156 = var3.length;
      this.field_157 = var3[0].length;
      this.field_113 = this.field_156 * field_108.field_223[0].field_634 + 8;
      this.field_114 = this.field_157 * field_108.field_223[0].field_635 + 8;
      if (this.field_113 > var1) {
         int var5 = field_108.field_223[0].field_634;
         this.field_152 = (var1 - 8) / var5;
         this.field_113 = var5 * this.field_152 + 8;
      } else {
         this.field_152 = this.field_156;
      }

      if (this.field_114 > var2) {
         int var6 = field_108.field_223[0].field_635;
         this.field_153 = (var2 - 8) / var6;
         this.field_114 = var6 * this.field_153 + 8;
      } else {
         this.field_153 = this.field_157;
      }

      this.field_123 = 8;
   }

   // $FF: renamed from: a (int, int, int) void
   public final void method_92(int var1, int var2, int var3) {
      this.field_111 = var1;
      this.field_112 = var2;
      if ((var3 & 1) != 0) {
         this.field_111 -= this.field_113 >> 1;
      } else if ((var3 & 8) != 0) {
         this.field_111 -= this.field_113;
      }

      if ((var3 & 2) != 0) {
         this.field_112 -= this.field_114 >> 1;
      } else if ((var3 & 32) != 0) {
         this.field_112 -= this.field_114;
      }

      this.field_121 = this.field_111;
      this.field_122 = this.field_112;
   }

   // $FF: renamed from: a (java.lang.String, int, int, byte) void
   public final void method_93(String var1, int var2, int var3, byte var4) {
      this.field_130 = (byte)var4;
      if (var4 == -1) {
         this.field_124 = 14;
      } else {
         this.field_133 = field_108.field_245.field_6 - 8;
      }

      var4 = var2 - this.field_133 - 16;
      this.field_109 = class_11.method_75(var1, var4, class_15.field_591);
      this.method_94((String)null, this.field_109, var2, var3);
      this.field_145 = false;
      this.field_123 = 7;
   }

   // $FF: renamed from: a (java.lang.String, java.lang.String[], int, int) void
   private void method_94(String var1, String[] var2, int var3, int var4) {
      this.field_137 = false;
      this.field_113 = var3;
      this.field_114 = var4;
      this.field_118 = var2.length;
      this.field_117 = 0;
      this.field_131 = 0;
      this.field_134 = 0;
      this.field_145 = false;
      var3 = var3 - this.field_133 - 16;
      if (var1 != null) {
         this.field_135 = class_11.method_75(var1, var3, class_15.field_591);
      }

      this.field_109 = var2;
      this.field_115 = class_15.field_593;
      this.field_128 = class_15.field_593 - class_15.field_592;
      this.field_129 = this.field_128 / 2;
      int var5;
      if (var4 <= 0) {
         var5 = super.field_94;
      } else {
         var5 = var4;
      }

      if ((this.field_124 & 1) == 0) {
         var5 -= 5;
      }

      if ((this.field_124 & 2) == 0) {
         var5 -= 5;
      }

      if (var1 != null) {
         var5 -= this.field_135.length * this.field_115;
      }

      this.field_132 = (var5 - 2) / this.field_115;
      if (this.field_132 > this.field_109.length) {
         this.field_132 = this.field_109.length;
      } else if (this.field_132 < this.field_109.length) {
         this.field_145 = true;
      }

      if (var4 < 0) {
         if (this.field_135 != null) {
            this.field_114 = this.field_135.length * this.field_115;
         }

         this.field_114 += this.field_132 * this.field_115;
         if ((this.field_124 & 1) == 0) {
            this.field_114 += 5;
         }

         if ((this.field_124 & 2) == 0) {
            this.field_114 += 5;
         }
      } else {
         this.field_142 = (var5 - this.field_132 * this.field_115) / 2;
      }

      this.field_123 = 10;
      this.field_106 = 2;
   }

   // $FF: renamed from: a (java.lang.String, java.lang.String, int, int) void
   public final void method_95(String var1, String var2, int var3, int var4) {
      int var5 = var3 - this.field_133;
      if ((this.field_124 & 4) == 0) {
         var5 -= 8;
      }

      if ((this.field_124 & 8) == 0) {
         var5 -= 8;
      }

      this.field_109 = class_11.method_75(var2, var5, class_15.field_591);
      this.method_94(var1, this.field_109, var3, var4);
      if (this.field_145) {
         var5 -= field_108.field_237.field_6;
         this.field_109 = class_11.method_75(var2, var5, class_15.field_591);
         this.method_94(var1, this.field_109, var3, var4);
      }

   }

   // $FF: renamed from: d () void
   private final void method_96() {
      this.field_143 = new class_1[3];

      for(int var1 = 0; var1 < this.field_143.length; ++var1) {
         this.field_143[var1] = new class_1(field_108.field_244);
      }

      this.method_97();
   }

   // $FF: renamed from: e () void
   private void method_97() {
      for(int var1 = 0; var1 < this.field_143.length; ++var1) {
         this.field_143[var1].field_5 = true;
         this.field_143[var1].method_11(class_15.method_233(this.field_180.field_6), class_15.method_233(this.field_180.field_7));
         this.field_143[var1].method_10(class_15.method_233(this.field_143[var1].method_9()));
      }

   }

   // $FF: renamed from: a (java.lang.String[], aeii.d[], int, int, int) void
   public final void method_98(String[] var1, class_16[] var2, int var3, int var4, int var5) {
      this.field_124 = 15;
      this.field_109 = var1;
      this.field_110 = var2;
      this.field_118 = this.field_109.length;
      this.field_113 = 0;

      for(int var6 = 0; var6 < this.field_118; ++var6) {
         int var7;
         if ((var7 = class_15.field_591.stringWidth(this.field_109[var6])) > this.field_113) {
            this.field_113 = var7;
         }
      }

      this.field_128 = class_15.field_593 - class_15.field_592;
      this.field_129 = this.field_128 / 2;
      this.field_177 = field_108.field_330.field_6;
      this.field_115 = this.field_177 + this.field_128;
      this.field_113 += this.field_118 * this.field_115;
      this.field_113 += 32;
      if (this.field_113 > super.field_93) {
         this.field_113 = super.field_93;
      }

      this.field_114 = this.field_177;
      this.method_92(var3, var4, var5);
      this.field_123 = 13;
      this.field_106 = 2;
   }

   // $FF: renamed from: a (java.lang.String[], int, int) void
   public final void method_99(String[] var1, int var2, int var3) {
      this.field_109 = var1;
      this.field_118 = this.field_109.length;
      this.field_148 = true;
      this.field_137 = false;
      this.field_114 = var3;
      int var5 = 0;

      for(int var6 = 0; var6 < this.field_109.length; ++var6) {
         int var4;
         if ((var4 = class_15.field_591.stringWidth(this.field_109[var6])) > var5) {
            var5 = var4;
         }
      }

      this.field_113 = var5 + 16 + (field_108.field_236.field_6 << 1);
      if (this.field_113 < var2) {
         this.field_113 = var2;
      }

      if (this.field_114 < 0) {
         this.field_114 = class_15.field_593;
         if (field_108.field_236.field_7 > this.field_114) {
            this.field_114 = field_108.field_236.field_7;
         }

         if ((this.field_124 & 1) == 0) {
            this.field_114 += 5;
         }

         if ((this.field_124 & 2) == 0) {
            this.field_114 += 5;
         }
      }

      this.field_123 = 14;
      this.field_106 = 2;
   }

   // $FF: renamed from: a (java.lang.String[], int, int, int, int, int, int) void
   public final void method_100(String[] var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      this.field_109 = var1;
      this.field_118 = this.field_109.length;
      this.field_128 = class_15.field_593 - class_15.field_592;
      this.field_115 = class_15.field_593;
      int var10 = 0;

      for(int var8 = 0; var8 < this.field_109.length; ++var8) {
         int var9;
         if ((var9 = class_15.field_591.stringWidth(this.field_109[var8])) > var10) {
            var10 = var9;
         }
      }

      this.field_113 = var10 + 4 + 16;
      if (this.field_113 > super.field_93) {
         this.field_113 = super.field_93;
      } else if (this.field_113 < var4) {
         if (var7 == 4) {
            this.field_167 = (var4 - this.field_113) / 2;
         }

         this.field_113 = var4;
      }

      this.field_114 = this.field_115 * this.field_109.length + this.field_128 + 16;
      if (this.field_114 > var5) {
         this.field_114 = var5;
      }

      this.method_94((String)null, this.field_109, this.field_113, this.field_114);
      if (this.field_113 < super.field_93 && this.field_145) {
         this.field_113 += field_108.field_237.field_6;
      }

      this.field_123 = 11;
      this.method_92(var2, var3, var6);
   }

   // $FF: renamed from: a (java.lang.String[], aeii.d[], int, int, int, int, byte) void
   public final void method_101(String[] var1, class_16[] var2, int var3, int var4, int var5, int var6, byte var7) {
      this.field_179 = 1;
      this.field_109 = var1;
      this.field_110 = var2;
      this.field_118 = this.field_109.length;
      this.field_180 = field_108.field_329;
      this.field_128 = class_15.field_593 - class_15.field_592;
      this.field_124 = 15;
      this.field_177 = this.field_180.field_6;
      this.field_178 = this.field_177 >> 1;
      this.method_96();
      this.field_168 = new short[this.field_118];
      this.field_174 = 360 / this.field_118;
      this.field_173 = this.field_174 / 2;
      this.field_172 = this.field_173;

      for(int var8 = 0; var8 < this.field_118; ++var8) {
         this.field_168[var8] = (short)(this.field_174 * var8);
      }

      if (this.field_118 == 1) {
         this.field_170 = 0;
      } else if (var5 <= 0) {
         this.field_170 = (this.field_180.field_6 << 10) / (2 * class_11.method_85(45));
         this.field_171 = this.field_170 + this.field_180.field_6 / 2;
         var5 = (this.field_171 << 1) + class_15.field_593 + 2;
      } else {
         int var9 = (this.field_180.field_6 << 10) / class_11.method_85(this.field_174 / 2) + this.field_180.field_7 / 2;
         this.field_171 = (var5 - class_15.field_593) / 2 - 2;
         if (this.field_171 > var9) {
            this.field_171 = var9;
         }

         this.field_170 = this.field_171 - this.field_180.field_7 / 2;
      }

      this.field_169 = 0;
      this.field_113 = this.field_171 << 1;
      this.field_114 = var5;
      this.field_106 = 0;
      this.method_92(var3, var4, 3);
   }

   // $FF: renamed from: a (int) int
   private int method_102(int var1) {
      int var2 = this.field_147;
      int var3 = this.field_147;
      int var4 = this.field_146.size();

      do {
         if ((var2 += var1) < 0) {
            var2 = var4 - 1;
         } else if (var2 >= this.field_146.size()) {
            if (var3 < 0) {
               return -1;
            }

            var2 = 0;
         }
      } while(!((class_12)this.field_146.elementAt(var2)).field_148);

      return var2;
   }

   // $FF: renamed from: b () void
   public final void method_73() {
      this.method_103(true);
   }

   // $FF: renamed from: a (boolean) void
   private void method_103(boolean var1) {
      if (this.field_106 != 3) {
         if (this.field_123 == 10 && this.field_136 > 0 && this.field_136 <= 250) {
            ++this.field_144;
            this.field_125 = true;
         } else if (this.field_144 > 0) {
            --this.field_144;
            this.field_125 = true;
         }

         if (var1 && this.field_106 == 2) {
            var1 = (boolean)0;
            if (this.field_102[0] && (class_11.field_92.method_258(class_13.field_199) || class_11.field_92.method_258(16))) {
               var1 = (boolean)1;
               class_11.field_92.method_262(class_13.field_199);
               class_11.field_92.method_262(16);
            }

            if (this.field_123 == 0 || this.field_123 == 3) {
               for(int var2 = 0; var2 < this.field_143.length; ++var2) {
                  if (this.field_143[var2].field_2 == this.field_143[var2].method_9() - 1) {
                     if (this.field_176 == 0) {
                        this.field_143[var2].method_11(class_15.method_233(this.field_180.field_6 - this.field_143[var2].field_6), class_15.method_233(this.field_180.field_7 - this.field_143[var2].field_7));
                     } else {
                        this.field_143[var2].field_5 = false;
                     }
                  }

                  this.field_143[var2].method_12();
               }

               this.field_125 = true;
            }

            if (this.field_123 == 15) {
               if (!this.field_149 && this.field_147 >= 0) {
                  if (class_11.field_92.method_258(1)) {
                     ((class_12)this.field_146.elementAt(this.field_147)).field_125 = true;
                     this.field_147 = this.method_102(-1);
                     class_12 var11;
                     (var11 = (class_12)this.field_146.elementAt(this.field_147)).field_125 = true;
                     int var15 = var11.field_112;

                     for(int var12 = 0; var12 < 5; ++var12) {
                        if (var15 < this.field_165[var12]) {
                           if (this.field_163 != var12) {
                              this.method_72();
                              this.field_163 = var12;
                           }
                           break;
                        }

                        var15 -= this.field_165[var12];
                     }
                  } else if (class_11.field_92.method_258(2)) {
                     ((class_12)this.field_146.elementAt(this.field_147)).field_125 = true;
                     this.field_147 = this.method_102(1);
                     class_12 var9;
                     (var9 = (class_12)this.field_146.elementAt(this.field_147)).field_125 = true;
                     int var3 = var9.field_112;

                     for(int var10 = 0; var10 < 5; ++var10) {
                        if (var3 < this.field_165[var10]) {
                           if (this.field_163 != var10) {
                              this.method_72();
                              this.field_163 = var10;
                           }
                           break;
                        }

                        var3 -= this.field_165[var10];
                     }
                  }
               }

               if (var1) {
                  field_108.method_133(this, this.field_147, "", (byte)0);
                  return;
               }

               for(int var13 = 0; var13 < this.field_146.size(); ++var13) {
                  class_12 var16 = (class_12)this.field_146.elementAt(var13);
                  class_12 var10000;
                  boolean var10001;
                  if (this.field_149) {
                     var10000 = var16;
                     var10001 = true;
                  } else {
                     var10000 = var16;
                     var10001 = var13 == this.field_147;
                  }

                  var10000.method_103(var10001);
               }

               this.field_125 = true;
            } else if (this.field_123 == 0) {
               if (this.field_106 == 2) {
                  this.field_125 = true;
                  if (class_11.field_92.method_258(4)) {
                     this.field_176 -= this.field_174;
                     this.field_175 += this.field_174;
                     --this.field_117;
                     if (this.field_117 < 0) {
                        this.field_117 = this.field_118 - 1;
                     }
                  } else if (class_11.field_92.method_258(8)) {
                     this.field_176 += this.field_174;
                     this.field_175 -= this.field_174;
                     if (this.field_175 < 0) {
                        this.field_175 += 360;
                     }

                     ++this.field_117;
                     if (this.field_117 >= this.field_118) {
                        this.field_117 = 0;
                     }
                  }

                  if (this.field_176 != 0) {
                     int var14;
                     if ((var14 = -this.field_176 / 2) == 0) {
                        this.field_176 = 0;
                     } else {
                        this.field_176 += var14;
                     }

                     if (this.field_176 == 0) {
                        this.method_97();
                     }
                  } else if (class_11.field_92.method_260(4)) {
                     this.field_176 -= this.field_174;
                     this.field_175 += this.field_174;
                     --this.field_117;
                     if (this.field_117 < 0) {
                        this.field_117 = this.field_118 - 1;
                     }
                  } else if (class_11.field_92.method_260(8)) {
                     this.field_176 += this.field_174;
                     this.field_175 -= this.field_174;
                     if (this.field_175 < 0) {
                        this.field_175 += 360;
                     }

                     ++this.field_117;
                     if (this.field_117 >= this.field_118) {
                        this.field_117 = 0;
                     }
                  }

                  if (var1) {
                     field_108.method_133(this, this.field_117, this.field_109[this.field_117], (byte)0);
                     return;
                  }
               }
            } else if (this.field_123 != 13 && this.field_123 != 14) {
               if (this.field_123 == 3) {
                  if (this.field_134 != 0) {
                     if (Math.abs(this.field_134) < 2) {
                        this.field_134 = 0;
                     } else {
                        this.field_134 -= this.field_134 / 2;
                     }

                     this.field_125 = true;
                  }

                  if (var1) {
                     field_108.method_133(this, this.field_117, this.field_109[this.field_117], (byte)0);
                     return;
                  }

                  if (class_11.field_92.method_258(4)) {
                     if (this.field_117 < this.field_131) {
                        this.field_117 += this.field_118;
                     }

                     --this.field_117;
                     if (this.field_117 < this.field_131) {
                        if (this.field_117 < 0) {
                           this.field_117 += this.field_118;
                        }

                        this.field_131 = this.field_117;
                        this.field_134 = -this.field_115;
                     }

                     this.field_117 %= this.field_118;
                     field_108.method_133(this, this.field_117, (String)null, (byte)2);
                     this.method_97();
                     this.field_125 = true;
                  } else if (class_11.field_92.method_258(8)) {
                     if (this.field_117 < this.field_131) {
                        this.field_117 += this.field_118;
                     }

                     ++this.field_117;
                     if (this.field_117 >= this.field_131 + this.field_132) {
                        this.field_134 = this.field_115;
                        this.field_131 = (this.field_131 + 1) % this.field_118;
                     }

                     this.field_117 %= this.field_118;
                     field_108.method_133(this, this.field_117, (String)null, (byte)3);
                     this.method_97();
                     this.field_125 = true;
                  }
               } else if (this.field_123 != 10 && this.field_123 != 7 && this.field_123 != 11) {
                  if (this.field_123 == 8) {
                     if (class_11.field_92.method_258(1)) {
                        if (this.field_151 > 0) {
                           --this.field_151;
                           this.field_125 = true;
                        }
                     } else if (class_11.field_92.method_258(2) && this.field_151 + this.field_153 < this.field_157) {
                        ++this.field_151;
                        this.field_125 = true;
                     }

                     if (class_11.field_92.method_258(4)) {
                        if (this.field_150 > 0) {
                           --this.field_150;
                           this.field_125 = true;
                        }
                     } else if (class_11.field_92.method_258(8) && this.field_150 + this.field_152 < this.field_156) {
                        ++this.field_150;
                        this.field_125 = true;
                     }
                  }
               } else {
                  if (this.field_136 != -1) {
                     if (this.field_136 > 0) {
                        this.field_136 -= 50;
                     } else {
                        class_11.field_92.method_253(this.field_141);
                     }
                  }

                  if (this.field_134 > 0) {
                     this.field_134 -= this.field_115 / 3 + 1;
                     if (this.field_134 < 0) {
                        this.field_134 = 0;
                     }

                     this.field_125 = true;
                  } else if (this.field_134 < 0) {
                     this.field_134 += this.field_115 / 3 + 1;
                     if (this.field_134 > 0) {
                        this.field_134 = 0;
                     }

                     this.field_125 = true;
                  }

                  if (this.field_134 == 0) {
                     if ((this.field_123 == 11 || this.field_123 == 10) && var1) {
                        field_108.method_133(this, this.field_117, this.field_109[this.field_117], (byte)0);
                        return;
                     }

                     if (this.field_123 != 7 && class_11.field_92.method_258(1)) {
                        if (this.field_123 == 11) {
                           --this.field_117;
                           if (this.field_117 < 0) {
                              this.field_117 = this.field_118 - 1;
                              this.field_131 = this.field_118 - this.field_132;
                              if (this.field_123 == 3) {
                                 this.field_131 = this.field_117;
                              }
                           } else if (this.field_117 < this.field_131) {
                              this.field_134 = -this.field_115;
                              --this.field_131;
                           }

                           field_108.method_133(this, this.field_117, (String)null, (byte)2);
                           this.field_125 = true;
                        } else if (this.field_131 > 0) {
                           this.field_134 = -this.field_115;
                           --this.field_131;
                           this.field_125 = true;
                        }

                        class_11.field_92.method_257();
                     }

                     if (class_11.field_92.method_258(2) || this.field_123 == 7 && class_11.field_92.method_258(2048)) {
                        if (this.field_123 == 11) {
                           ++this.field_117;
                           if (this.field_117 >= this.field_118) {
                              this.field_117 = 0;
                              this.field_131 = 0;
                           } else if (this.field_117 >= this.field_131 + this.field_132) {
                              this.field_134 = this.field_115;
                              ++this.field_131;
                           }

                           field_108.method_133(this, this.field_117, (String)null, (byte)3);
                           this.field_125 = true;
                        } else if (this.field_131 + this.field_132 < this.field_109.length) {
                           this.field_134 = this.field_115;
                           ++this.field_131;
                           this.field_125 = true;
                        } else if (this.field_123 == 7) {
                           field_108.method_133(this, 0, (String)null, (byte)0);
                           return;
                        }

                        class_11.field_92.method_257();
                     }
                  }
               }
            } else if (class_11.field_92.method_258(4)) {
               --this.field_117;
               if (this.field_117 < 0) {
                  this.field_117 = this.field_118 - 1;
               }

               if (this.field_123 == 14) {
                  field_108.method_133(this, this.field_117, (String)null, (byte)2);
               }

               this.field_125 = true;
            } else if (class_11.field_92.method_258(8)) {
               ++this.field_117;
               if (this.field_117 >= this.field_118) {
                  this.field_117 = 0;
               }

               if (this.field_123 == 14) {
                  field_108.method_133(this, this.field_117, (String)null, (byte)2);
               }

               this.field_125 = true;
            } else if (var1) {
               field_108.method_133(this, this.field_117, this.field_109[this.field_117], (byte)0);
               return;
            }

            if (this.field_106 == 2 && this.field_102[1] && class_11.field_92.method_258(class_13.field_200)) {
               class_11.field_92.method_262(class_13.field_200);
               class_11.field_92.method_257();
               if (this.field_141 != null) {
                  class_11.field_92.method_253(this.field_141);
               }

               if (this.field_109 != null) {
                  field_108.method_133(this, this.field_117, this.field_109[this.field_117], (byte)1);
                  return;
               }

               field_108.method_133(this, -1, (String)null, (byte)1);
               return;
            }
         }

         if (this.field_137 && ++this.field_120 >= this.field_107) {
            if (this.field_119 == 0) {
               this.field_119 = 2;
            } else {
               this.field_119 = 0;
            }

            this.field_120 = 0;
            this.field_125 = true;
         }

         switch (this.field_106) {
            case 0:
               if (this.field_123 == 0) {
                  if (this.field_169 < this.field_170) {
                     if (this.field_179 == 2) {
                        var1 = this.field_170 / 2;
                     } else {
                        var1 = this.field_170 / 5;
                     }

                     if (var1 <= 0) {
                        var1 = 1;
                     }

                     this.field_169 += var1;
                     if (this.field_169 > this.field_170) {
                        this.field_169 = this.field_170;
                     }
                  } else {
                     this.field_172 = Math.abs(360 - this.field_168[0]) / 2;
                     if (this.field_172 <= 0) {
                        this.field_172 = 1;
                     } else if (this.field_172 > this.field_173) {
                        this.field_172 = this.field_173;
                     }
                  }

                  if (this.field_179 == 1) {
                     for(int var6 = 0; var6 < this.field_168.length; ++var6) {
                        this.field_168[var6] = (short)((this.field_168[var6] + this.field_172) % 360);
                     }
                  }

                  if (class_11.field_92.method_256() || this.field_169 >= this.field_170 && this.field_168[0] == 0) {
                     this.field_169 = this.field_170;

                     for(int var7 = 0; var7 < this.field_168.length; ++var7) {
                        this.field_168[var7] = (short)(this.field_174 * var7);
                     }

                     this.field_106 = 2;
                     if (this.field_140) {
                        class_11.field_92.method_257();
                     }
                  }
               } else if (this.field_123 == 13) {
                  this.field_121 += (this.field_111 - this.field_121) / 2;
                  ++this.field_138;
                  if (this.field_138 == 2) {
                     this.field_106 = 2;
                     this.field_121 = this.field_111;
                  }
               } else {
                  if ((var1 = (this.field_111 - this.field_121) / 4) <= 0) {
                     var1 = 1;
                  }

                  this.field_121 += var1;
                  if (this.field_121 == this.field_111) {
                     this.field_106 = 2;
                  }
               }

               this.field_125 = true;
               return;
            case 1:
               this.field_106 = 3;
         }
      }

   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics, int, int, int, int) void
   public static final void method_104(Graphics var0, int var1, int var2, int var3, int var4) {
      if (var4 <= 2) {
         var0.fillRect(var1, var2, var3, var4);
      } else {
         var0.fillRect(var1, var2 + 1, var3, var4 - 2);
         var0.fillRect(var1 + 1, var2, var3 - 2, var4);
      }
   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics) void
   public final void method_74(Graphics var1) {
      this.method_105(var1, 0, 0, false);
   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics, int, int, boolean) void
   private void method_105(Graphics var1, int var2, int var3, boolean var4) {
      if (this.field_106 != 3 && this.field_125) {
         this.field_125 = false;
         if (class_11.field_92.field_603 == this && this.field_126 || this.field_123 == 0) {
            field_108.method_74(var1);
         }

         this.field_126 = false;
         var1.setClip(0, 0, super.field_93, super.field_94);
         if (this.field_161 != null) {
            this.field_161.method_74(var1);
         }

         var2 = this.field_121;
         var3 = this.field_122 + var3;
         if (this.field_123 != 0 && this.field_123 != 13) {
            method_107(var1, var2, var3, this.field_113, this.field_114, this.field_124, this.field_159, this.field_158, this.field_144, 5);
            var1.setClip(0, 0, super.field_93, super.field_94);
         }

         int var5 = this.field_113;
         int var6 = this.field_114;
         if ((this.field_124 & 1) == 0) {
            var6 -= 5;
            var3 += 5;
         }

         if ((this.field_124 & 2) == 0) {
            var6 -= 5;
         }

         if ((this.field_124 & 4) == 0) {
            var2 += 8;
            var5 -= 8;
         }

         if ((this.field_124 & 8) == 0) {
            var5 -= 8;
         }

         var1.translate(var2, var3);
         var1.setFont(this.field_116);
         if (this.field_160 != null && (this.field_109 == null || this.field_116.stringWidth(this.field_109[0]) < var5 - (this.field_160.field_634 << 1))) {
            this.field_160.method_282(var1, 0, var6 / 2, 6);
         }

         if (var4) {
            var1.setColor(5594742);
            method_104(var1, 0, 0, var5, var6);
         }

         if (this.field_123 == 0) {
            var1.setColor(16777215);
            int var8 = this.field_175 + this.field_176;

            for(int var9 = this.field_168.length - 1; var9 >= 0; --var9) {
               int var7;
               if ((var7 = (this.field_168[var9] + var8) % 360) < 0) {
                  var7 += 360;
               }

               var4 = this.field_171 + (class_11.method_85(var7) * this.field_169 >> 10);
               var7 = class_15.field_593 + this.field_171 + 2 - (class_11.method_86(var7) * this.field_169 >> 10);
               if (this.field_106 == 2 && var9 == this.field_117) {
                  this.field_180.method_15(var1, 1, var4, var7, 3);
                  if (this.field_176 == 0) {
                     for(int var10 = 0; var10 < this.field_143.length; ++var10) {
                        this.field_143[var10].method_16(var1, var4 - this.field_178, var7 - this.field_178, 20);
                     }
                  }
               } else {
                  this.field_180.method_15(var1, 0, var4, var7, 3);
               }

               if (this.field_109[var9] != null && this.field_110 != null && this.field_110[var9] != null) {
                  this.field_110[var9].method_282(var1, var4, var7, 3);
               }
            }

            if (this.field_106 == 2) {
               for(int var60 = 0; var60 < this.field_143.length; ++var60) {
                  this.field_143[var60].method_16(var1, (this.field_113 - this.field_177) / 2, class_15.field_593, 3);
               }
            }
         }

         switch (this.field_123) {
            case 0:
               if (this.field_106 == 2) {
                  var1.setColor(1645370);
                  if (this.field_179 == 2) {
                     var6 = this.field_113;
                     if (this.field_109[this.field_117] != null) {
                        int var96 = class_15.field_591.stringWidth(this.field_109[this.field_117]) + 2;
                        if (var6 < var96) {
                           var6 = var96;
                        }
                     }

                     method_104(var1, (this.field_113 - var6) / 2, 1, var6, class_15.field_593);
                  } else {
                     method_104(var1, 2 - this.field_111, 1, super.field_93 - 4, class_15.field_593);
                  }

                  if (this.field_109[this.field_117] != null) {
                     var1.setColor(16777215);
                     class_15.method_251(var1, this.field_109[this.field_117], this.field_171, (this.field_128 >> 1) + 1, 17);
                  }
               }
            case 1:
            case 4:
            case 6:
            case 9:
            case 12:
            default:
               break;
            case 2:
            case 5:
               var1.setClip(0, 0, var5, var6);
               var6 = field_104;
               this.field_139.method_17(var1, -this.field_139.m + var6, -this.field_139.n + var6);
               int var57 = var6 + this.field_139.p / 2;
               var1.setFont(class_15.field_591);
               var1.setColor(this.field_103);
               class_15.method_251(var1, this.field_139.field_37, var6 + this.field_139.o + var6, var57 - class_15.field_592 / 2, 20);
               String var104;
               if (this.field_123 == 2) {
                  var104 = "" + this.field_139.field_70;
                  field_108.field_333.method_15(var1, 1, var5 - var6 - class_15.method_245((byte)1, var104), var57, 10);
               } else {
                  var104 = "" + this.field_139.field_54;
               }

               class_15.method_249(var1, var104, var5 - var6, var57, 1, 10);
               this.field_128 = class_15.field_593 - class_15.field_592;
               int var47 = var6 + this.field_139.p + field_104;
               var1.setColor(this.field_103);
               var1.drawLine(var6, var47, var5 - var6 - var6, var47);
               var47 += 1 + field_104;
               if (this.field_123 == 5) {
                  int var76 = class_15.field_592;
                  var57 = var47 + var76 / 2;
                  class_15.method_251(var1, class_11.method_79(97), var6, var47, 20);
                  int var67 = class_15.field_591.stringWidth(class_11.method_79(97));
                  var4 = var6 + var67 + var6;
                  String var100 = "" + this.field_139.field_38;
                  int var90 = class_15.method_245((byte)0, var100);
                  int var18 = var5 - var4 - var6 - field_108.field_332.field_6 - var90 - var6;
                  var1.setColor(this.field_103);
                  method_104(var1, var4, var47, var18, var76);
                  var1.setColor(2370117);
                  int var81;
                  if ((var81 = var18 * this.field_139.field_39 / this.field_139.method_35()) <= 0) {
                     var81 = 1;
                  }

                  var1.fillRect(var4 + 1, var47 + 1, var81, var76 - 2);
                  var4 = var5 - var6 - var90;
                  field_108.field_332.method_15(var1, 2, var4, var57, 10);
                  class_15.method_249(var1, var100, var4, var57, 0, 6);
                  var47 += var76 + field_104;
                  var1.setColor(this.field_103);
                  var1.drawLine(var6, var47, var5 - var6 - var6, var47);
                  var47 += 1 + field_104;
               }

               int var77 = (var5 - var6 * 3) / 2;
               int var68 = field_108.field_332.field_7;
               int var105 = field_108.field_330.field_7;
               int var82 = field_108.field_330.field_7 / 2;

               for(int var59 = 0; var59 < 2; ++var59) {
                  int var86 = var47 + var82 - var68 / 2;
                  var4 = var6;

                  for(int var93 = 0; var93 < 2; ++var93) {
                     if (var59 == 0 || var93 == 0) {
                        int var91 = var4 + var82;
                        method_104(var1, var91, var86, var77 - var82, var68);
                        field_108.field_330.method_17(var1, var4, var47);
                        int var101;
                        if ((var101 = (var59 << 1) + var93) == 0 || var101 == 1) {
                           field_108.field_332.method_15(var1, var101, var91, var47 + var82, 3);
                        }

                        int var95 = 0;
                        if (var101 == 0) {
                           if (this.field_123 == 5) {
                              var95 = this.field_139.method_29((class_2)null);
                           }

                           var104 = this.field_139.field_50 + var95 + "-" + (this.field_139.field_51 + var95);
                        } else if (var101 == 1) {
                           if (this.field_123 == 5) {
                              var95 = this.field_139.method_31((class_2)null);
                           }

                           var104 = "" + (this.field_139.field_52 + var95);
                        } else if (var101 == 2) {
                           field_108.field_203[5].method_282(var1, var91, var47 + var82, 3);
                           var104 = "" + this.field_139.field_53;
                        }

                        class_15.method_249(var1, var104, var4 + var105 + 1, var47 + var82, 0, 6);
                        if (var95 > 0) {
                           field_108.field_364.method_15(var1, 1, var91 + var77 - var82 - 1, var47 + var82, 10);
                        } else if (var95 < 0) {
                           field_108.field_364.method_15(var1, 2, var91 + var77 - var82 - 1, var47 + var82, 10);
                        }

                        var4 += var77 + field_104;
                     }
                  }

                  var47 += var105;
               }

               var47 += field_104;
               var1.setColor(this.field_103);
               var1.drawLine(var6, var47, var5 - var6 - var6, var47);
               break;
            case 3:
               var1.setClip(0, 0, var5, var6);
               int var45 = field_104;
               var1.setColor(11515819);
               var1.drawLine(field_104, var45, var5 - (field_104 << 1), var45);
               var45 = var45 + 1 + field_104 + field_108.field_329.field_7 / 2;
               var4 = field_108.field_236.field_6 + this.field_134 + this.field_142;
               var6 = this.field_131;
               int var94 = this.field_131 + this.field_132;
               if (this.field_134 > 0) {
                  --var6;
                  var4 -= this.field_115;
               } else if (this.field_134 < 0) {
                  ++var94;
               }

               for(int var99 = var6; var99 < var94; ++var99) {
                  int var75;
                  if ((var75 = var99 % this.field_118) < 0) {
                     var75 += this.field_118;
                  }

                  int var66 = var4 + this.field_115 / 2;
                  if (var75 == this.field_117) {
                     field_108.field_329.method_15(var1, 1, var66, var45, 3);
                  } else {
                     field_108.field_329.method_15(var1, 0, var66, var45, 3);
                  }

                  class_2 var88 = this.field_127[var75];
                  int var80 = var66 - var88.m - var88.o / 2;
                  int var85 = var45 - var88.n - var88.p / 2;
                  var88.method_58(var1, var80, var85, var88.field_70 > field_108.field_277[field_108.field_272]);
                  if (var75 == this.field_117) {
                     int var56 = var66 - this.field_180.field_6 / 2;
                     int var92 = var45 - this.field_180.field_6 / 2;

                     for(int var89 = 0; var89 < this.field_143.length; ++var89) {
                        this.field_143[var89].method_16(var1, var56, var92, 20);
                     }
                  }

                  var4 += this.field_115;
               }

               field_108.field_236.method_15(var1, 0, 0, var45, 6);
               field_108.field_236.method_15(var1, 1, var5, var45, 10);
               break;
            case 7:
            case 10:
            case 11:
               var1.setFont(class_15.field_591);
               if (this.field_130 != -1) {
                  field_108.field_245.method_15(var1, this.field_130, -8, var6, 36);
               }

               var5 -= this.field_133;
               var1.setClip(0, 0, var5, this.field_114 - 10);
               var4 = 0;
               int var42 = 0;
               if (this.field_135 != null) {
                  var1.setColor(class_13.method_159(16777215, this.field_159, this.field_144, 5));

                  for(int var97 = 0; var97 < this.field_135.length; ++var97) {
                     class_15.method_251(var1, this.field_135[var97], this.field_133 + var5 / 2, var42 + this.field_129, 17);
                     var42 += this.field_115;
                  }

                  var1.setColor(10463131);
                  var1.drawLine(0, var42, var5 - 1, var42);
                  var4 = var42;
               }

               int var98 = var42 + this.field_142;
               var1.setColor(this.field_103);
               int var87 = this.field_131;
               int var55 = this.field_131 + this.field_132;
               if (this.field_134 > 0) {
                  --var87;
                  var42 -= this.field_115;
               } else if (this.field_134 < 0) {
                  ++var55;
               }

               var42 += this.field_134 + this.field_142;
               var1.setClip(this.field_133, var4, var5, var6 - var4);
               var4 = var5;
               if (this.field_145) {
                  var4 = var5 - field_108.field_237.field_6;
               }

               int var65 = this.field_133 + var4 / 2;

               for(int var12 = var87; var12 < var55; ++var12) {
                  int var73 = 0;
                  if (var42 < var98) {
                     var73 = var98 - var42;
                  } else if (var42 + this.field_115 > var6 - this.field_142) {
                     var73 = var42 + this.field_115 - var6 + this.field_142;
                  }

                  int var11;
                  if (this.field_123 == 11 && var12 == this.field_117) {
                     var1.setColor(5594742);
                     method_104(var1, 0, var42, var4, this.field_115);
                     var11 = class_13.method_159(this.field_159, 16777215, this.field_115 - var73, this.field_115);
                  } else {
                     var11 = class_13.method_159(this.field_159, this.field_103, this.field_115 - var73, this.field_115);
                  }

                  var11 = class_13.method_159(var11, this.field_159, this.field_144, 5);
                  var1.setColor(var11);
                  if (this.field_167 >= 0) {
                     class_15.method_251(var1, this.field_109[var12], this.field_167, var42 + this.field_129, 20);
                  } else {
                     class_15.method_251(var1, this.field_109[var12], var65, var42 + this.field_129, 17);
                  }

                  var42 += this.field_115;
               }

               if (this.field_145) {
                  int var83 = field_108.field_237.field_7;
                  int var74 = field_108.field_237.field_6;
                  int var79 = field_108.field_237.field_6 / 2;
                  int var103 = var6 - (var83 << 1) - 2;
                  var4 = var5 - (var74 + var79) / 2;
                  if (var103 > 2) {
                     var1.setColor(this.field_103);
                     method_104(var1, var4, var83 + 1, var79, var103);
                     if ((var42 = (var103 - 2) * this.field_132 / this.field_118) <= 0) {
                        var42 = 1;
                     }

                     var1.setColor(2370117);
                     method_104(var1, var4 + 1, var83 + (var103 - 2) * this.field_131 / this.field_118 + 2, var79 - 2, var42);
                     field_108.field_237.method_15(var1, 0, var5 - var74, 0, 20);
                     field_108.field_237.method_15(var1, 1, var5 - var74, var6, 36);
                  } else {
                     if (this.field_131 > 0) {
                        field_108.field_237.method_15(var1, 0, var5 - var74, 0, 20);
                     }

                     if (this.field_131 + this.field_132 < this.field_118) {
                        field_108.field_237.method_15(var1, 1, var5 - var74, var6, 36);
                     }
                  }
               }

               if (this.field_123 == 7) {
                  var1.setClip(0, 0, super.field_93, super.field_94);
                  int var84 = var6;
                  if ((this.field_124 & 2) == 0) {
                     var84 = var6 + 5;
                  }

                  field_108.field_237.method_15(var1, 1, var5 + this.field_133, var84, 40);
               }
               break;
            case 8:
               class_13.method_165(var1, 0, 0, this.field_113, this.field_114);
               int var14 = field_108.field_223[0].field_634;
               int var13 = field_108.field_223[0].field_635;
               int var16 = this.field_153 + this.field_151;
               int var15 = this.field_152 + this.field_150;
               int var41 = 4;

               for(int var63 = this.field_151; var63 < var16; ++var63) {
                  var4 = 4;

                  for(int var53 = this.field_150; var53 < var15; ++var53) {
                     byte var71 = field_108.field_225[this.field_154[var53][var63]];
                     field_108.field_223[var71].method_281(var1, var4, var41);
                     var4 += var14;
                  }

                  var41 += var13;
               }

               if (this.field_155 != null && this.field_119 == 0) {
                  int var64 = -this.field_150 * var14 + 4;
                  int var54 = -this.field_151 * var13 + 4;
                  int var72 = 0;

                  for(int var24 = field_108.field_263.size(); var72 < var24; ++var72) {
                     class_2 var102;
                     if ((var102 = (class_2)field_108.field_263.elementAt(var72)).field_46 >= this.field_150 && var102.field_46 < var15 && var102.field_47 >= this.field_151 && var102.field_47 < var16) {
                        field_108.field_340.method_15(var1, field_108.field_270[var102.field_45] - 1, var102.field_46 * var14 + var64, var102.field_47 * var13 + var54, 0);
                     }
                  }
               }

               if (this.field_119 == 0) {
                  if (this.field_151 > 0) {
                     field_108.field_237.method_15(var1, 0, var5 / 2, 0, 17);
                  }

                  if (this.field_151 + this.field_153 < this.field_157) {
                     field_108.field_237.method_15(var1, 1, var5 / 2, var6, 33);
                  }

                  if (this.field_150 > 0) {
                     field_108.field_236.method_15(var1, 0, 0, var6 / 2, 6);
                  }

                  if (this.field_150 + this.field_152 < this.field_156) {
                     field_108.field_236.method_15(var1, 1, var5, var6 / 2, 10);
                  }
               }
               break;
            case 13:
               int var62 = class_15.field_593;
               int var52 = (this.field_114 - var62) / 2;
               var1.setColor(class_13.method_159(1645370, 16777215, this.field_144, 5));
               method_104(var1, 0, var52, this.field_113, var62);
               var1.setFont(class_15.field_591);
               var1.setColor(16777215);
               class_15.method_251(var1, this.field_109[this.field_117], 16, var52 + this.field_129, 20);
               int var70 = this.field_113 - this.field_115;

               for(int var22 = this.field_118 - 1; var22 >= 0; --var22) {
                  if (var22 == this.field_117) {
                     field_108.field_330.method_15(var1, 1, var70, 0, 20);
                  } else {
                     field_108.field_330.method_15(var1, 0, var70, 0, 20);
                  }

                  this.field_110[var22].method_282(var1, var70 + field_108.field_330.field_6 / 2, this.field_114 / 2, 3);
                  var70 -= this.field_115;
               }
               break;
            case 14:
               var1.setFont(class_15.field_591);
               var1.setColor(class_13.method_159(16777215, 1645370, this.field_144, 5));
               int var51 = var6 / 2;
               class_15.method_251(var1, this.field_109[this.field_117], var5 / 2, (var6 - class_15.field_592) / 2, 17);
               field_108.field_236.method_15(var1, 0, 0, var51, 6);
               field_108.field_236.method_15(var1, 1, var5, var51, 10);
               break;
            case 15:
               int var61 = 0;
               int var40 = 0;
               int var69 = this.field_165[0];

               for(int var33 = 1; var33 <= this.field_163; ++var33) {
                  var61 = var69;
                  var69 += this.field_165[var33];
               }

               if (this.field_163 > 0) {
                  var40 = -var61 + field_108.field_238.field_7;
               }

               for(int var34 = 0; var34 < this.field_146.size(); ++var34) {
                  class_12 var17;
                  if ((var17 = (class_12)this.field_146.elementAt(var34)).field_112 >= var61 && var17.field_112 < var69) {
                     var17.method_105(var1, 0, var40, var34 == this.field_147);
                  }
               }

               var1.setClip(0, 0, field_108.field_186, field_108.field_187);
               if (this.field_163 > 0) {
                  var1.setColor(2370117);
                  var1.fillRect(0, 0, super.field_93, field_108.field_238.field_7);
                  field_108.field_237.method_15(var1, 0, field_108.field_186 / 2, -this.field_119, 17);
               }

               if (this.field_163 < this.field_164) {
                  var1.setColor(2370117);
                  var6 = this.field_165[this.field_163];
                  if (this.field_163 > 0) {
                     var6 += field_108.field_238.field_7;
                  }

                  var1.fillRect(0, var6, super.field_93, field_108.field_187 - var6);
                  field_108.field_237.method_15(var1, 1, field_108.field_186 / 2, field_108.field_187 + this.field_119, 33);
               }
         }

         var1.translate(-var2, -var3);
         var1.setClip(0, 0, super.field_93, super.field_94);
         if (class_11.field_92.field_603 == this && this.field_106 == 2) {
            if (this.field_102[0]) {
               field_108.method_167(var1, class_13.field_199, 0, field_108.field_187);
            }

            if (this.field_102[1]) {
               field_108.method_167(var1, class_13.field_200, 1, field_108.field_187);
            }
         }
      }

   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics, int, int, int, int, int) void
   public static final void method_106(Graphics var0, int var1, int var2, int var3, int var4, int var5) {
      method_107(var0, 0, var2, var3, var4, var5, 2370117, 2370117, 0, 0);
   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics, int, int, int, int, int, int, int, int, int) void
   public static final void method_107(Graphics var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9) {
      class_1 var10 = field_108.field_239;
      var0.setClip(var1, var2, var3, var4);
      var0.setColor(var6);
      var0.fillRect(var1, var2, var3, var4);
      if (var7 != var6) {
         int var11 = var4 / 4;
         int var12 = var2 + 5;

         for(int var13 = 0; var13 < var11; ++var13) {
            int var14 = class_13.method_159(var7, var6, var13, var11);
            var0.setColor(class_13.method_159(var14, var6, var8, var9));
            var0.fillRect(var1, var12, var3, 1);
            ++var12;
         }
      }

      if (var5 != 15) {
         boolean var23 = (var5 & 4) == 0;
         boolean var24 = (var5 & 8) == 0;
         boolean var25 = (var5 & 1) == 0;
         boolean var26 = (var5 & 2) == 0;
         var5 = var3 / var10.field_6 - 2;
         if (var3 % var10.field_6 != 0) {
            ++var5;
         }

         if (!var23) {
            ++var5;
         }

         if (!var24) {
            ++var5;
         }

         var6 = var4 / var10.field_7 - 2;
         if (var4 % var10.field_7 != 0) {
            ++var6;
         }

         if (!var25) {
            ++var6;
         }

         if (!var26) {
            ++var6;
         }

         var7 = var1;
         if (var23) {
            var7 = var1 + var10.field_6;
         }

         var4 = var2 + var4 - var10.field_7;

         for(int var21 = 0; var21 < var5; ++var21) {
            if (var25) {
               var10.method_15(var0, 1, var7, var2, 0);
            }

            if (var26) {
               var10.method_15(var0, 6, var7, var4, 0);
            }

            var7 += var10.field_6;
         }

         var8 = var2;
         if (var25) {
            var8 = var2 + var10.field_7;
         }

         var3 = var1 + var3 - var10.field_6;

         for(int var18 = 0; var18 < var6; ++var18) {
            if (var23) {
               var10.method_15(var0, 3, var1, var8, 0);
            }

            if (var24) {
               var10.method_15(var0, 4, var3, var8, 0);
            }

            var8 += var10.field_7;
         }

         if (var23 && var25) {
            var10.method_15(var0, 0, var1, var2, 0);
         }

         if (var24 && var25) {
            var10.method_15(var0, 2, var3, var2, 0);
         }

         if (var23 && var26) {
            var10.method_15(var0, 5, var1, var4, 0);
         }

         if (var24 && var26) {
            var10.method_15(var0, 7, var3, var4, 0);
         }
      }

   }

   static {
      field_105 = ((field_104 = class_15.field_605 <= 143 ? 1 : 2) << 1) + 1;
   }
}
