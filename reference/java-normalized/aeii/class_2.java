package aeii;

import a.a.a.class_1;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

// $FF: renamed from: aeii.a
public final class class_2 extends class_1 {
   // $FF: renamed from: a byte
   public static byte field_33 = 12;
   // $FF: renamed from: b byte
   public static byte field_34 = 6;
   // $FF: renamed from: c byte
   public static byte field_35 = 6;
   // $FF: renamed from: a aeii.g
   private static class_13 field_36;
   // $FF: renamed from: a java.lang.String
   public String field_37;
   // $FF: renamed from: a short
   public short field_38;
   // $FF: renamed from: a int
   public int field_39;
   // $FF: renamed from: a byte[][]
   public byte[][] field_40;
   // $FF: renamed from: a java.util.Vector
   private Vector field_41;
   // $FF: renamed from: d short
   private short field_42;
   // $FF: renamed from: a long
   private long field_43;
   // $FF: renamed from: d byte
   public byte field_44;
   // $FF: renamed from: e byte
   public byte field_45;
   // $FF: renamed from: b short
   public short field_46;
   // $FF: renamed from: c short
   public short field_47;
   // $FF: renamed from: b int
   public int field_48;
   // $FF: renamed from: c int
   public int field_49;
   // $FF: renamed from: d int
   public int field_50;
   // $FF: renamed from: e int
   public int field_51;
   // $FF: renamed from: f int
   public int field_52;
   // $FF: renamed from: g int
   public int field_53;
   // $FF: renamed from: h int
   public int field_54;
   // $FF: renamed from: f byte
   public byte field_55;
   // $FF: renamed from: g byte
   public byte field_56;
   // $FF: renamed from: e short
   private short field_57;
   // $FF: renamed from: f short
   private short field_58;
   // $FF: renamed from: g short
   private short field_59;
   // $FF: renamed from: f boolean
   private boolean field_60;
   // $FF: renamed from: g boolean
   private boolean field_61 = true;
   // $FF: renamed from: B int
   private int field_62;
   // $FF: renamed from: b long
   private long field_63;
   // $FF: renamed from: h byte
   public byte field_64;
   // $FF: renamed from: i byte
   public byte field_65;
   // $FF: renamed from: C int
   private int field_66;
   // $FF: renamed from: a aeii.a
   public class_2 field_67;
   // $FF: renamed from: j byte
   public byte field_68 = 0;
   // $FF: renamed from: i int
   public int field_69;
   // $FF: renamed from: j int
   public int field_70;
   // $FF: renamed from: k int
   public int field_71;
   // $FF: renamed from: m byte
   private byte field_72;
   // $FF: renamed from: a java.lang.String[]
   private static String[] field_73 = new String[]{"soldier", "archer", "lizard", "wizard", "wisp", "spider", "golem", "catapult", "wyvern", "king", "skeleton", "crystall"};
   // $FF: renamed from: a byte[]
   public static byte[] field_74 = new byte[12];
   // $FF: renamed from: c byte[][]
   private static byte[][] field_75 = new byte[12][2];
   // $FF: renamed from: c byte[]
   private static byte[] field_76 = new byte[12];
   // $FF: renamed from: d byte[]
   private static byte[] field_77 = new byte[12];
   // $FF: renamed from: e byte[]
   private static byte[] field_78 = new byte[12];
   // $FF: renamed from: a byte[][][]
   public static byte[][][] field_79 = new byte[12][][];
   // $FF: renamed from: a short[]
   public static short[] field_80 = new short[12];
   // $FF: renamed from: b short[]
   private static short[] field_81 = new short[12];

   private class_2(byte var1, byte var2, int var3, int var4, boolean var5) {
      super(field_36.field_219[var2 - 1 << 1][var1]);
      this.field_72 = var2;
      this.field_44 = var1;
      this.field_55 = 0;
      this.field_46 = (short)var3;
      this.field_47 = (short)var4;
      this.b(var3 * 24, var4 * 24);
      this.method_22((byte)0);
      if (var5) {
         field_36.field_263.addElement(this);
      }

   }

   // $FF: renamed from: a (byte) void
   public final void method_22(byte var1) {
      this.field_38 = (short)var1;
      var1 <<= 1;
      this.field_50 = field_75[this.field_44][0] + var1;
      this.field_51 = field_75[this.field_44][1] + var1;
      this.field_52 = field_76[this.field_44] + var1;
      this.field_53 = field_74[this.field_44];
      if (this.field_44 != 9) {
         if ((var1 = this.field_38 / 2) > 3) {
            var1 = 3;
         }

         this.field_37 = class_11.method_79(139 + (this.field_44 << 2) + var1);
      }

   }

   // $FF: renamed from: a (int) void
   public final void method_23(int var1) {
      this.field_60 = true;
      this.field_63 = field_36.field_251;
      this.field_62 = var1;
   }

   // $FF: renamed from: a (byte, byte, int, int) aeii.a
   public static final class_2 method_24(byte var0, byte var1, int var2, int var3) {
      return method_25(var0, var1, var2, var3, true);
   }

   // $FF: renamed from: a (byte, byte, int, int, boolean) aeii.a
   public static final class_2 method_25(byte var0, byte var1, int var2, int var3, boolean var4) {
      if (var0 == 9 && field_36.field_276[var1] >= field_36.field_275[var1].length) {
         return null;
      } else {
         class_2 var5;
         (var5 = new class_2(var0, field_36.field_270[var1], var2, var3, var4)).field_44 = var0;
         var5.field_45 = var1;
         var5.field_54 = 100;
         var5.field_40 = field_79[var0];
         var5.field_70 = field_80[var0];
         if (var0 == 9) {
            var5.method_28(field_36.field_270[var1] - 1);
            var5.field_69 = field_36.field_276[var1];
            field_36.field_275[var1][var5.field_69] = var5;
            int var10002 = field_36.field_276[var1]++;
         }

         return var5;
      }
   }

   // $FF: renamed from: b (byte, byte, int, int, boolean) aeii.a
   public static final class_2 method_26(byte var0, byte var1, int var2, int var3, boolean var4) {
      class_2 var5;
      (var5 = new class_2(var0, field_36.field_270[var1], var2, var3, var4)).field_44 = var0;
      var5.field_45 = var1;
      var5.field_54 = 100;
      var5.field_40 = field_79[var0];
      var5.field_70 = field_80[var0];
      if (var0 == 9) {
         var5.method_28(field_36.field_270[var1] - 1);
      }

      return var5;
   }

   // $FF: renamed from: a () void
   public final void method_27() {
      field_36.field_263.removeElement(this);
   }

   // $FF: renamed from: b (int) void
   public final void method_28(int var1) {
      this.field_68 = (byte)var1;
      this.field_37 = class_11.method_79(var1 + 93);
   }

   // $FF: renamed from: a (aeii.a) int
   public final int method_29(class_2 var1) {
      return this.method_30(var1, this.field_46, this.field_47);
   }

   // $FF: renamed from: a (aeii.a, int, int) int
   private int method_30(class_2 var1, int var2, int var3) {
      int var4 = this.field_58;
      if (var1 != null) {
         if (this.method_55((short)64) && var1.method_55((short)1)) {
            var4 += 15;
         }

         if (this.field_44 == 4 && var1.field_44 == 10) {
            var4 += 15;
         }
      }

      if (this.method_55((short)2) && field_36.method_169(var2, var3) == 5) {
         var4 += 10;
      }

      if (field_36.field_248[var2][var3] == 34) {
         var4 += 25;
      }

      return var4;
   }

   // $FF: renamed from: b (aeii.a) int
   public final int method_31(class_2 var1) {
      return this.method_32(this.field_46, this.field_47);
   }

   // $FF: renamed from: a (int, int) int
   private int method_32(int var1, int var2) {
      byte var3 = field_36.method_169(var1, var2);
      int var4 = this.field_59 + class_13.field_221[var3];
      if (this.method_55((short)2) && var3 == 5) {
         var4 += 15;
      }

      if (field_36.field_248[var1][var2] == 34) {
         var4 += 15;
      }

      return var4;
   }

   // $FF: renamed from: c (aeii.a) int
   public final int method_33(class_2 var1) {
      int var2 = class_15.method_234(this.field_50, this.field_51) + this.method_29(var1);
      int var3 = var1.field_52 + var1.method_31(this);
      if ((var2 = (var2 - var3) * this.field_54 / 100) < 0) {
         var2 = 0;
      } else if (var2 > var1.field_54) {
         var2 = var1.field_54;
      }

      var1.field_54 -= var2;
      this.field_39 += var1.method_34() * var2;
      return var2;
   }

   // $FF: renamed from: e () int
   private int method_34() {
      return this.field_50 + this.field_51 + this.field_52;
   }

   // $FF: renamed from: a () int
   public final int method_35() {
      return (this.method_34() * 100 << 1) / 3;
   }

   // $FF: renamed from: a () boolean
   public final boolean method_36() {
      int var1 = this.method_35();
      if (this.field_39 >= var1) {
         this.field_39 -= var1;
         this.method_22((byte)(this.field_38 + 1));
         return true;
      } else {
         return false;
      }
   }

   // $FF: renamed from: a (int, int) boolean
   public final boolean method_37(int var1, int var2) {
      return this.field_55 != 4 && this.field_54 > 0 && Math.abs(this.field_46 - var1) + Math.abs(this.field_47 - var2) == 1 && field_78[this.field_44] == 1;
   }

   // $FF: renamed from: b (byte) void
   public final void method_38(byte var1) {
      this.field_56 |= var1;
      this.method_40();
      if (var1 == 1) {
         this.field_65 = field_36.field_272;
      }

   }

   // $FF: renamed from: c (byte) void
   public final void method_39(byte var1) {
      this.field_56 = (byte)(this.field_56 & ~var1);
      this.method_40();
   }

   // $FF: renamed from: b () void
   public final void method_40() {
      this.field_57 = 0;
      this.field_58 = 0;
      this.field_59 = 0;
      if ((this.field_56 & 1) != 0) {
         this.field_58 = (short)(this.field_58 - 10);
         this.field_59 = (short)(this.field_59 - 10);
      }

      if ((this.field_56 & 2) != 0) {
         this.field_58 = (short)(this.field_58 + 10);
      }

   }

   // $FF: renamed from: a (int, int) void
   public final void method_41(int var1, int var2) {
      this.field_46 = (short)var1;
      this.field_47 = (short)var2;
      super.field_3 = (short)(var1 * 24);
      super.field_4 = (short)(var2 * 24);
   }

   // $FF: renamed from: b () int
   public final int method_42() {
      int var1 = 100 / this.field_40.length;
      int var2 = this.field_54 / var1;
      if (this.field_54 != 100 && this.field_54 % var1 > 0) {
         ++var2;
      }

      return var2;
   }

   // $FF: renamed from: a (int, int, aeii.a) int
   public final int method_43(int var1, int var2, class_2 var3) {
      return (this.field_50 + this.field_51 + this.field_52 + this.method_30(var3, var1, var2) + this.method_32(var1, var2)) * this.field_54 / 100;
   }

   // $FF: renamed from: a (byte[][], int, int) void
   public final void method_44(byte[][] var1, int var2, int var3) {
      byte var4 = field_78[this.field_44];
      byte var5 = field_77[this.field_44];
      int var6;
      if ((var6 = var2 - var5) < 0) {
         var6 = 0;
      }

      int var7;
      if ((var7 = var3 - var5) < 0) {
         var7 = 0;
      }

      int var8;
      if ((var8 = var2 + var5) >= field_36.field_230) {
         var8 = field_36.field_230 - 1;
      }

      int var9;
      if ((var9 = var3 + var5) >= field_36.field_231) {
         var9 = field_36.field_231 - 1;
      }

      for(int var12 = var6; var12 <= var8; ++var12) {
         for(int var10 = var7; var10 <= var9; ++var10) {
            int var11;
            if ((var11 = Math.abs(var12 - var2) + Math.abs(var10 - var3)) >= var4 && var11 <= var5 && var1[var12][var10] <= 0) {
               var1[var12][var10] = 127;
            }
         }
      }

   }

   // $FF: renamed from: a (byte[][]) void
   public final void method_45(byte[][] var1) {
      if (this.method_55((short)512)) {
         this.method_44(var1, this.field_46, this.field_47);
      } else {
         this.method_51(var1);

         for(int var2 = 0; var2 < field_36.field_230; ++var2) {
            for(int var3 = 0; var3 < field_36.field_231; ++var3) {
               if (var1[var2][var3] > 0 && var1[var2][var3] != 127) {
                  this.method_44(var1, var2, var3);
               }
            }
         }

      }
   }

   // $FF: renamed from: a (int, int, byte) aeii.a[]
   public final class_2[] method_46(int var1, int var2, byte var3) {
      return this.method_47(var1, var2, field_78[this.field_44], field_77[this.field_44], var3);
   }

   // $FF: renamed from: a (int, int, int, int, byte) aeii.a[]
   public final class_2[] method_47(int var1, int var2, int var3, int var4, byte var5) {
      Vector var6 = new Vector();
      int var7;
      if ((var7 = var1 - var4) < 0) {
         var7 = 0;
      }

      int var8;
      if ((var8 = var2 - var4) < 0) {
         var8 = 0;
      }

      int var9;
      if ((var9 = var1 + var4) >= field_36.field_230) {
         var9 = field_36.field_230 - 1;
      }

      int var10;
      if ((var10 = var2 + var4) >= field_36.field_231) {
         var10 = field_36.field_231 - 1;
      }

      for(int var13 = var7; var13 <= var9; ++var13) {
         for(int var11 = var8; var11 <= var10; ++var11) {
            int var12;
            if ((var12 = Math.abs(var13 - var1) + Math.abs(var11 - var2)) >= var3 && var12 <= var4) {
               if (var5 == 0) {
                  class_2 var15;
                  if ((var15 = field_36.method_168(var13, var11, (byte)0)) != null) {
                     if (field_36.field_271[var15.field_45] != field_36.field_271[this.field_45]) {
                        var6.addElement(var15);
                     }
                  } else if (this.field_44 == 7 && field_36.method_169(var13, var11) == 8 && class_13.method_177(field_36.field_248[var13][var11]) && !field_36.method_181(var13, var11, field_36.field_271[this.field_45])) {
                     (var15 = method_25((byte)0, (byte)0, var13, var11, false)).field_44 = -1;
                     var15.field_55 = 4;
                     var6.addElement(var15);
                  }
               } else if (var5 == 1) {
                  class_2 var17;
                  if ((var17 = field_36.method_168(var13, var11, (byte)1)) != null) {
                     var6.addElement(var17);
                  }
               } else {
                  class_2 var18;
                  if (var5 == 2 && (var18 = field_36.method_168(var13, var11, (byte)0)) != null && field_36.field_271[var18.field_45] == field_36.field_271[this.field_45]) {
                     var6.addElement(var18);
                  }
               }
            }
         }
      }

      class_2[] var14 = new class_2[var6.size()];
      var6.copyInto(var14);
      return var14;
   }

   // $FF: renamed from: a (int, int, boolean) void
   public final void method_48(int var1, int var2, boolean var3) {
      this.method_49(var1, var2, var3, false);
   }

   // $FF: renamed from: a (int, int, boolean, boolean) void
   public final void method_49(int var1, int var2, boolean var3, boolean var4) {
      if (var3) {
         this.field_41 = this.method_50(this.field_46, this.field_47, var1, var2);
      } else {
         if (var4 && field_36.method_168(var1, var2, (byte)0) != null) {
            var4 = false;

            for(int var5 = var1 - 1; var5 <= var1 + 1; ++var5) {
               for(int var9 = var2 - 1; var9 <= var2 + 1; ++var9) {
                  if (((var5 != var1 || var9 != var2) && var5 == var1 || var9 == var2) && field_36.method_168(var5, var9, (byte)0) == null) {
                     var1 = var5;
                     var2 = var9;
                     var4 = true;
                     break;
                  }
               }

               if (var4) {
                  break;
               }
            }
         }

         this.field_41 = new Vector();
         short[] var13 = new short[]{this.field_46, this.field_47};
         this.field_41.addElement(var13);
         short var16 = this.field_46;
         if ((var3 = Math.abs(var1 - this.field_46)) > 0) {
            int var6 = (var1 - this.field_46) / var3;

            for(int var14 = 0; var14 < var3; ++var14) {
               var16 = (short)(var16 + var6);
               short[] var7 = new short[]{var16, this.field_47};
               this.field_41.addElement(var7);
            }
         }

         short var17 = this.field_47;
         if ((var3 = Math.abs(var2 - this.field_47)) > 0) {
            int var15 = (var2 - this.field_47) / var3;

            for(int var18 = 0; var18 < var3; ++var18) {
               var17 = (short)(var17 + var15);
               short[] var8 = new short[]{var16, var17};
               this.field_41.addElement(var8);
            }
         }
      }

      this.field_48 = var1;
      this.field_49 = var2;
      this.field_42 = 1;
      this.field_55 = 1;
   }

   // $FF: renamed from: a (int, int, int, int) java.util.Vector
   public final Vector method_50(int var1, int var2, int var3, int var4) {
      Vector var5 = null;
      short[] var6 = new short[]{(short)var3, (short)var4};
      if (var1 == var3 && var2 == var4) {
         (var5 = new Vector()).addElement(var6);
      } else {
         byte var7 = 0;
         byte var8 = 0;
         byte var9 = 0;
         byte var10 = 0;
         if (var4 > 0) {
            var7 = field_36.field_259[var3][var4 - 1];
         }

         if (var4 < field_36.field_231 - 1) {
            var8 = field_36.field_259[var3][var4 + 1];
         }

         if (var3 > 0) {
            var9 = field_36.field_259[var3 - 1][var4];
         }

         if (var3 < field_36.field_230 - 1) {
            var10 = field_36.field_259[var3 + 1][var4];
         }

         int var11;
         if ((var11 = Math.max(Math.max(var7, var8), Math.max(var9, var10))) == var7) {
            var5 = this.method_50(var1, var2, var3, var4 - 1);
         } else if (var11 == var8) {
            var5 = this.method_50(var1, var2, var3, var4 + 1);
         } else if (var11 == var9) {
            var5 = this.method_50(var1, var2, var3 - 1, var4);
         } else if (var11 == var10) {
            var5 = this.method_50(var1, var2, var3 + 1, var4);
         }

         var5.addElement(var6);
      }

      return var5;
   }

   // $FF: renamed from: b (byte[][]) void
   public final void method_51(byte[][] var1) {
      method_52(var1, this.field_46, this.field_47, this.field_53, -1, this.field_44, this.field_45, false);
   }

   // $FF: renamed from: a (byte[][], int, int, int, int, byte, byte, boolean) boolean
   public static final boolean method_52(byte[][] var0, int var1, int var2, int var3, int var4, byte var5, byte var6, boolean var7) {
      if (var3 > var0[var1][var2]) {
         var0[var1][var2] = (byte)var3;
         if (var7 && field_36.method_168(var1, var2, (byte)0) == null) {
            return true;
         } else {
            int var8;
            if (var4 != 1 && (var8 = var3 - method_53(var1, var2 - 1, var5, var6)) >= 0 && method_52(var0, var1, var2 - 1, var8, 2, var5, var6, var7) && var7) {
               return true;
            } else if (var4 != 2 && (var8 = var3 - method_53(var1, var2 + 1, var5, var6)) >= 0 && method_52(var0, var1, var2 + 1, var8, 1, var5, var6, var7) && var7) {
               return true;
            } else if (var4 != 4 && (var8 = var3 - method_53(var1 - 1, var2, var5, var6)) >= 0 && method_52(var0, var1 - 1, var2, var8, 8, var5, var6, var7) && var7) {
               return true;
            } else {
               return var4 != 8 && (var8 = var3 - method_53(var1 + 1, var2, var5, var6)) >= 0 && method_52(var0, var1 + 1, var2, var8, 4, var5, var6, var7) && var7;
            }
         }
      } else {
         return false;
      }
   }

   // $FF: renamed from: a (int, int, byte, byte) int
   private static int method_53(int var0, int var1, byte var2, byte var3) {
      if (var0 >= 0 && var1 >= 0 && var0 < field_36.field_230 && var1 < field_36.field_231) {
         class_2 var4;
         if ((var4 = field_36.method_168(var0, var1, (byte)0)) != null && field_36.field_271[var4.field_45] != field_36.field_271[var3]) {
            return 1000;
         } else {
            var0 = field_36.method_169(var0, var1);
            if (var2 == 11) {
               if (var0 == 4) {
                  return 1000;
               }
            } else {
               if (method_54(var2, (short)1)) {
                  return 1;
               }

               if (method_54(var2, (short)2) && var0 == 5) {
                  return 1;
               }
            }

            return class_13.field_222[var0];
         }
      } else {
         return 10000;
      }
   }

   // $FF: renamed from: c () void
   public final void method_20() {
      if (this.field_60) {
         if (field_36.field_251 - this.field_63 >= (long)this.field_62) {
            this.field_60 = false;
         } else {
            this.field_61 = !this.field_61;
         }
      }

      if (this.field_55 == 1) {
         if (this.field_42 >= this.field_41.size()) {
            this.field_55 = 0;
            this.field_46 = (short)(super.field_3 / 24);
            this.field_47 = (short)(super.field_4 / 24);
            this.field_41 = null;
            this.field_42 = 0;
         } else {
            if (this.field_67 != null && super.field_3 % 24 == 0 && super.field_4 % 24 == 0) {
               this.field_67.method_48(this.field_46, this.field_47, false);
            }

            short[] var1;
            int var2 = (var1 = (short[])this.field_41.elementAt(this.field_42))[0] * 24;
            int var3 = var1[1] * 24;
            class_1 var4 = null;
            if (this.field_67 == null && ++this.field_66 >= 24 / field_35 / 2) {
               var4 = field_36.method_120(field_36.field_342, super.field_3, super.field_4, 0, 0, 1, class_15.method_234(1, 4) * 50);
               this.field_66 = 0;
            }

            if (var2 < super.field_3) {
               super.field_3 -= field_35;
               if (var4 != null) {
                  var4.method_11(super.field_3 + super.field_6, super.field_4 + super.field_7 - var4.field_7);
               }
            } else if (var2 > super.field_3) {
               super.field_3 += field_35;
               if (var4 != null) {
                  var4.method_11(super.field_3 - var4.field_6, super.field_4 + super.field_7 - var4.field_7);
               }
            } else if (var3 < super.field_4) {
               super.field_4 -= field_35;
               if (var4 != null) {
                  var4.method_11(super.field_3 + (super.field_6 - var4.field_6) / 2, super.field_4 + super.field_7);
               }
            } else if (var3 > super.field_4) {
               super.field_4 += field_35;
               if (var4 != null) {
                  var4.method_11(super.field_3 + (super.field_6 - var4.field_6) / 2, super.field_4 - var4.field_7);
               }
            }

            if (super.field_3 == var2 && super.field_4 == var3) {
               this.field_46 = var1[0];
               this.field_47 = var1[1];
               ++this.field_42;
            }
         }

         super.method_11(super.field_3, super.field_4);
         this.e();
      } else {
         if (this.field_55 == 0 && field_36.field_251 - this.field_43 >= 200L) {
            this.e();
            this.field_43 = field_36.field_251;
         }

      }
   }

   // $FF: renamed from: a (byte, short) boolean
   private static boolean method_54(byte var0, short var1) {
      return (field_81[var0] & var1) != 0;
   }

   // $FF: renamed from: a (short) boolean
   public final boolean method_55(short var1) {
      return method_54(this.field_44, var1);
   }

   // $FF: renamed from: d () void
   public final void method_56() {
      this.field_55 = 2;
      class_2 var1;
      if ((var1 = field_36.method_168(this.field_46, this.field_47, (byte)1)) != null) {
         ((class_2)var1).method_27();
      }

      if (this.method_55((short)256)) {
         var1 = this.method_47(this.field_46, this.field_47, 1, 2, (byte)2);

         for(int var2 = 0; var2 < var1.length; ++var2) {
            var1[var2].method_38((byte)2);
            field_36.method_120(field_36.field_241, var1[var2].m, var1[var2].n, 0, 0, 1, 50);
         }
      }

      field_36.field_457 = this;
   }

   // $FF: renamed from: a (byte) aeii.a[]
   public static final class_2[] method_57(byte var0) {
      class_2[] var1 = new class_2[field_36.field_276[var0]];
      int var2 = 0;

      for(int var3 = 0; var3 < var1.length; ++var3) {
         if (field_36.field_275[field_36.field_272][var3] != null && field_36.field_275[field_36.field_272][var3].field_55 == 3) {
            var1[var2++] = field_36.field_275[field_36.field_272][var3];
         }
      }

      class_2[] var5 = new class_2[field_36.field_321 + 1 + var2];

      for(byte var4 = 0; var4 < var5.length; ++var4) {
         if (var4 < var2) {
            var5[var4] = var1[var4];
         } else {
            var5[var4] = method_25((byte)(var4 - var2), var0, 0, 0, false);
         }
      }

      return var5;
   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics, int, int) void
   public final void method_17(Graphics var1, int var2, int var3) {
      this.method_58(var1, var2, var3, false);
   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics, int, int, boolean) void
   public final void method_58(Graphics var1, int var2, int var3, boolean var4) {
      if (this.field_55 != 4) {
         if (this.field_60) {
            byte var6;
            if (this.field_61) {
               var6 = -2;
            } else {
               var6 = 2;
            }

            class_15.method_235();
            byte var5 = 0;
            super.method_17(var1, var2 + var6, var3 + var5);
         } else if (!var4 && this.field_55 != 2) {
            super.method_17(var1, var2, var3);
         } else {
            field_36.field_219[(this.field_72 - 1 << 1) + 1][this.field_44].method_17(var1, super.field_3 + var2, super.field_4 + var3);
         }

         if (this.field_44 == 9) {
            int var8 = super.field_3 + var2;
            int var7 = super.field_4 + var3;
            if (var4 || this.field_55 == 2) {
               field_36.field_359[1].method_15(var1, (this.field_68 << 1) + super.field_2, var8, var7, 0);
               return;
            }

            field_36.field_359[0].method_15(var1, (this.field_68 << 1) + super.field_2, var8, var7, 0);
         }
      }

   }

   // $FF: renamed from: b (javax.microedition.lcdui.Graphics, int, int) void
   public final void method_59(Graphics var1, int var2, int var3) {
      var2 = super.field_3 + var2;
      var3 = super.field_4 + var3;
      if (this.field_55 != 3 && this.field_54 < 100) {
         class_15.method_250(var1, "" + this.field_54, var2, var3 + super.field_7 - 7, 0);
      }

   }

   // $FF: renamed from: a (aeii.g) void
   public static final void method_60(class_13 var0) {
      field_36 = var0;

      try {
         for(int var7 = 0; var7 < field_73.length; ++var7) {
            InputStream var8 = class_15.method_276(field_73[var7] + ".unit");

            String var11;
            while((var11 = class_15.method_238(var8)) != null) {
               int var14;
               if ((var14 = var11.indexOf(59)) >= 0) {
                  var11 = var11.substring(0, var14);
               }

               if ((var11 = var11.trim()).length() != 0) {
                  String[] var9;
                  if ((var9 = class_15.method_239(var11, ' '))[0].equalsIgnoreCase("MoveRange")) {
                     field_74[var7] = (byte)Integer.parseInt(var9[1]);
                  } else if (var9[0].equalsIgnoreCase("Attack")) {
                     field_75[var7][0] = (byte)Integer.parseInt(var9[1]);
                     field_75[var7][1] = (byte)Integer.parseInt(var9[2]);
                  } else if (var9[0].equalsIgnoreCase("Defence")) {
                     field_76[var7] = (byte)Integer.parseInt(var9[1]);
                  } else if (var9[0].equalsIgnoreCase("AttackRange")) {
                     field_77[var7] = (byte)Integer.parseInt(var9[1]);
                     field_78[var7] = (byte)Integer.parseInt(var9[2]);
                  } else if (var9[0].equalsIgnoreCase("Cost")) {
                     field_80[var7] = (short)Integer.parseInt(var9[1]);
                  } else if (var9[0].equalsIgnoreCase("CharCount")) {
                     field_79[var7] = new byte[Integer.parseInt(var9[1])][2];
                  } else if (var9[0].equalsIgnoreCase("CharPos")) {
                     var14 = Integer.parseInt(var9[1]);
                     field_79[var7][var14][0] = (byte)Integer.parseInt(var9[2]);
                     field_79[var7][var14][1] = (byte)Integer.parseInt(var9[3]);
                  } else if (var9[0].equalsIgnoreCase("HasProperty")) {
                     field_81[var7] = (short)(field_81[var7] | 1 << Integer.parseInt(var9[1]));
                  }
               }
            }
         }

      } catch (Exception var6) {
         try {
            DataInputStream var1 = new DataInputStream(class_15.method_276("units.bin"));

            for(int var2 = 0; var2 < 12; ++var2) {
               field_74[var2] = var1.readByte();
               field_75[var2][0] = var1.readByte();
               field_75[var2][1] = var1.readByte();
               field_76[var2] = var1.readByte();
               field_77[var2] = var1.readByte();
               field_78[var2] = var1.readByte();
               field_80[var2] = var1.readShort();
               byte var3 = var1.readByte();
               field_79[var2] = new byte[var3][2];

               for(int var4 = 0; var4 < var3; ++var4) {
                  field_79[var2][var4][0] = var1.readByte();
                  field_79[var2][var4][1] = var1.readByte();
               }

               byte var13 = var1.readByte();

               for(int var10 = 0; var10 < var13; ++var10) {
                  field_81[var2] = (short)(field_81[var2] | 1 << var1.readByte());
               }
            }

            var1.close();
         } catch (Exception var5) {
            var6.printStackTrace();
            var5.printStackTrace();
         }
      }
   }
}
