package aeii;

import a.a.a.class_1;
import javax.microedition.lcdui.Graphics;

// $FF: renamed from: aeii.h
public final class class_14 {
   // $FF: renamed from: a java.lang.String[]
   public static String[] field_539 = new String[]{"road", "grass", "woods", "hill", "mountain", "water", "bridge", "town", "town", "town"};
   // $FF: renamed from: b java.lang.String[]
   public static String[] field_540 = new String[]{"road", "grass", "grass", "grass", "mountain", "water", "bridge", "town", "town", "town"};
   // $FF: renamed from: c java.lang.String[]
   private static String[] field_541 = new String[]{"soldier", "archer", "lizard", "wizard", "wisp", "spider", "golem", "catapult", "wyvern", "king", "skeleton"};
   // $FF: renamed from: a aeii.g
   private class_13 field_542;
   // $FF: renamed from: a aeii.a
   public class_2 field_543;
   // $FF: renamed from: c byte
   private byte field_544;
   // $FF: renamed from: a boolean
   public boolean field_545 = false;
   // $FF: renamed from: b boolean
   public boolean field_546 = false;
   // $FF: renamed from: c boolean
   private boolean field_547;
   // $FF: renamed from: d byte
   private byte field_548;
   // $FF: renamed from: a byte
   public byte field_549;
   // $FF: renamed from: e byte
   private byte field_550;
   // $FF: renamed from: b byte
   public byte field_551;
   // $FF: renamed from: f byte
   private byte field_552;
   // $FF: renamed from: g byte
   private byte field_553;
   // $FF: renamed from: d boolean
   private boolean field_554;
   // $FF: renamed from: a int
   private int field_555 = 0;
   // $FF: renamed from: a byte[]
   private static byte[] field_556;
   // $FF: renamed from: a a.a.a.g
   private class_1 field_557;
   // $FF: renamed from: b a.a.a.g
   private class_1 field_558;
   // $FF: renamed from: c a.a.a.g
   private class_1 field_559;
   // $FF: renamed from: d a.a.a.g
   private class_1 field_560;
   // $FF: renamed from: b int
   private int field_561;
   // $FF: renamed from: c int
   private int field_562;
   // $FF: renamed from: a long
   private long field_563;
   // $FF: renamed from: b long
   private long field_564;
   // $FF: renamed from: a a.a.a.g[]
   private class_1[] field_565;
   // $FF: renamed from: a aeii.d[]
   private class_16[] field_566;
   // $FF: renamed from: a aeii.d
   private class_16 field_567;
   // $FF: renamed from: a aeii.h
   public class_14 field_568;
   // $FF: renamed from: d int
   private int field_569;
   // $FF: renamed from: e int
   private int field_570;
   // $FF: renamed from: f int
   private int field_571;
   // $FF: renamed from: g int
   private int field_572;
   // $FF: renamed from: e boolean
   private boolean field_573 = false;
   // $FF: renamed from: h int
   private int field_574;
   // $FF: renamed from: i int
   private int field_575;
   // $FF: renamed from: a int[][]
   private int[][] field_576;
   // $FF: renamed from: a int[]
   private int[] field_577;
   // $FF: renamed from: j int
   private int field_578;
   // $FF: renamed from: k int
   private int field_579 = 20;
   // $FF: renamed from: l int
   private int field_580;
   // $FF: renamed from: m int
   private int field_581;
   // $FF: renamed from: a byte[][]
   private byte[][] field_582;
   // $FF: renamed from: e a.a.a.g
   private class_1 field_583;
   // $FF: renamed from: f a.a.a.g
   private class_1 field_584;
   // $FF: renamed from: b a.a.a.g[]
   private class_1[] field_585;
   // $FF: renamed from: n int
   private int field_586;
   // $FF: renamed from: o int
   private int field_587;

   public class_14(class_13 var1, class_2 var2, class_14 var3) {
      super();
      this.field_542 = var1;
      this.field_543 = var2;
      this.field_544 = var2.field_44;
      this.field_568 = var3;
      this.field_548 = (byte)var2.field_54;
      this.field_574 = this.field_548;
      this.field_550 = (byte)var2.method_42();
      this.field_552 = this.field_550;
      int var4 = 0;
      if (var3 == null) {
         this.field_553 = 0;
         this.field_561 = 0;
         this.field_554 = true;
      } else {
         var4 = var1.field_188;
         this.field_553 = 1;
         this.field_555 = 0;
      }

      this.field_569 = var1.method_169(var2.field_46, var2.field_47);
      String var5 = field_539[this.field_569];
      String var6 = field_540[this.field_569];
      if (var3 != null && var6.equals(field_540[var3.field_569])) {
         this.field_566 = new class_16[var3.field_566.length];
         System.arraycopy(var3.field_566, 0, this.field_566, 0, this.field_566.length);
      } else {
         this.field_566 = (new class_1(var6)).field_0;
      }

      if (this.field_553 == 1) {
         for(int var9 = 0; var9 < this.field_566.length; ++var9) {
            this.field_566[var9] = new class_16(this.field_566[var9], 1);
         }
      }

      try {
         if (var3 != null && var5.equals(field_539[var3.field_569])) {
            this.field_567 = var3.field_567;
         } else {
            this.field_567 = new class_16(var5 + "_bg");
         }

         if (this.field_553 == 1) {
            this.field_567 = new class_16(this.field_567, 1);
         }
      } catch (Exception var7) {
      }

      if (this.field_567 != null) {
         this.field_570 = this.field_567.field_635;
      }

      this.field_571 = var1.field_188 / this.field_566[0].field_634;
      if (var1.field_188 % this.field_566[0].field_634 != 0) {
         ++this.field_571;
      }

      this.field_572 = (var1.field_479 - this.field_570) / this.field_566[0].field_635;
      if ((var1.field_479 - this.field_570) % this.field_566[0].field_635 != 0) {
         ++this.field_572;
      }

      this.field_582 = new byte[this.field_571][this.field_572];

      for(int var10 = 0; var10 < this.field_571; ++var10) {
         for(int var8 = 0; var8 < this.field_572; ++var8) {
            this.field_582[var10][var8] = (byte)Math.abs(class_15.field_611.nextInt() % this.field_566.length);
         }
      }

      this.field_557 = new class_1(field_541[this.field_544], (byte)(var1.field_270[var2.field_45] - 1 << 1));
      if (var3 != null && var3.field_544 == this.field_544) {
         if (var3.field_560 != null) {
            this.field_560 = new class_1(var3.field_560);
         }

         if (var3.field_558 != null) {
            this.field_558 = new class_1(var3.field_558);
         }

         if (var3.field_559 != null) {
            this.field_559 = new class_1(var3.field_559);
         }

         if (var3.field_583 != null) {
            this.field_583 = new class_1(var3.field_583);
            this.field_584 = new class_1(var3.field_584);
         }
      } else if (this.field_544 != 0 && this.field_544 != 10 && this.field_544 != 5) {
         if (this.field_544 == 1) {
            this.field_559 = new class_1("archer_arrow");
         } else if (this.field_544 == 9) {
            this.field_558 = new class_1("kingwave");
            this.field_560 = new class_1("kingslash");
            this.field_583 = new class_1("king_heads");
            this.field_584 = new class_1("king_heads_back");
         } else if (this.field_544 == 2) {
            this.field_558 = new class_1("watermagic");
            this.field_559 = new class_1("fish");
         } else if (this.field_544 != 7 && this.field_544 != 6) {
            if (this.field_544 == 3) {
               this.field_558 = new class_1("spell");
               this.field_559 = this.field_558;
            }
         } else {
            this.field_559 = new class_1("crater");
         }
      } else {
         this.field_560 = new class_1("slash");
      }

      if (this.field_558 != null) {
         this.field_558.method_14(0, this.field_554);
      }

      if (this.field_559 != null) {
         this.field_559.method_14(0, this.field_554);
      }

      if (this.field_560 != null) {
         this.field_560.method_14(0, this.field_554);
      }

      if (this.field_583 != null) {
         this.field_583.method_14(var2.field_68, this.field_554);
         this.field_583.method_10(var2.field_68);
         this.field_584.method_14(0, this.field_554);
         this.field_584.method_10(var2.field_68);
      }

      this.field_576 = new int[var2.field_40.length][2];

      for(int var11 = 0; var11 < this.field_576.length; ++var11) {
         this.field_576[var11][0] = var2.field_40[var11][0] * var1.j / 128;
         if (this.field_553 == 1) {
            this.field_576[var11][0] = var1.field_188 - this.field_576[var11][0] - this.field_557.field_6 + var4;
         }

         this.field_576[var11][1] = var2.field_40[var11][1] * var1.field_479 / 128 - this.field_557.field_7;
      }

      this.field_565 = new class_1[this.field_550];
      if (this.field_544 == 4 || this.field_544 == 6) {
         this.field_577 = new int[this.field_550];
      }

      for(int var12 = 0; var12 < this.field_550; ++var12) {
         this.field_565[var12] = new class_1(this.field_557);
         var1.method_208(this.field_565[var12]);
         this.field_565[var12].method_11(this.field_576[var12][0], this.field_576[var12][1]);
         this.field_565[var12].method_14(0, this.field_554);
         this.field_565[var12].field_18 = false;
         this.field_565[var12].field_15 = 0;
         if (this.field_544 == 6) {
            this.field_565[var12].field_22 = true;
            this.field_577[var12] = class_15.method_233(360);
            this.field_565[var12].field_9 = -6 + 4 * class_11.method_85(this.field_577[var12]) >> 10;
         } else if (this.field_544 == 4) {
            this.field_565[var12].field_22 = true;
            this.field_565[var12].field_9 = -5 - class_15.method_233(10);
            this.field_577[var12] = class_15.method_233(360);
         } else if (this.field_544 == 9) {
            this.field_565[var12].field_26 = this.field_583;
            this.field_565[var12].field_27 = this.field_584;
         }
      }

   }

   // $FF: renamed from: a (a.a.a.g, int) int
   private int method_226(class_1 var1, int var2) {
      return this.field_553 == 1 ? this.field_557.field_6 - var2 - var1.field_6 : var2;
   }

   // $FF: renamed from: a () void
   public final void method_227() {
      this.field_545 = true;
      this.field_555 = 1;
      this.field_564 = this.field_542.field_251;
   }

   // $FF: renamed from: b () void
   public final void method_228() {
      if (this.field_547 && this.field_574 > this.field_549) {
         this.field_574 -= 2;
         if (this.field_574 < this.field_549) {
            this.field_574 = this.field_549;
         }

         this.field_542.field_478 = true;
      }

      if (this.field_544 != 8 && this.field_544 != 9 && this.field_544 != 7 && this.field_544 != 1 && this.field_544 != 3 && this.field_544 != 2 && this.field_544 != 6) {
         if (this.field_544 == 4) {
            class_14 var7 = this;
            switch (this.field_555) {
               case 1:
                  if (this.field_580 == 0) {
                     if (this.field_542.field_251 - this.field_564 >= 200L) {
                        this.field_580 = 1;
                        this.field_564 = this.field_542.field_251;
                     }
                  } else {
                     if (this.field_580 == 1) {
                        this.field_579 += 5;
                        if (this.field_579 >= 90) {
                           ++this.field_580;
                           this.field_564 = this.field_542.field_251;
                        }

                        if ((this.field_579 - 20) % 15 == 0) {
                           for(int var15 = 0; var15 < var7.field_565.length; ++var15) {
                              class_1 var27;
                              (var27 = class_1.method_19(var7.field_542.field_342, class_15.method_234(-1, 2), 0, 0, 1, 100, (byte)0)).method_11(var7.field_565[var15].field_3 + class_15.method_233(var7.field_557.field_6 - var27.field_6), var7.field_565[var15].field_4 + var7.field_557.field_7 - var27.field_7 + 1);
                              var7.field_542.method_208(var27);
                           }
                        }
                     } else if (this.field_580 == 2 && this.field_542.field_251 - this.field_564 >= 400L) {
                        this.field_579 = 20;
                        this.field_568.method_229();
                        this.field_555 = 4;
                        this.field_568.field_573 = false;
                        this.field_564 = this.field_542.field_251;
                     }

                     if (++var7.field_578 < 2) {
                        break;
                     }

                     for(int var16 = 0; var16 < var7.field_568.field_565.length; ++var16) {
                        class_1 var28;
                        (var28 = class_1.method_19(var7.field_542.field_242, 0, 0, 0, 1, 50, (byte)0)).method_11(var7.field_568.field_565[var16].field_3 + class_15.method_233(var7.field_568.field_565[var16].field_6 - var28.field_6), var7.field_568.field_565[var16].field_4 + class_15.method_233(var7.field_568.field_565[var16].field_7 - var28.field_7));
                        var28.field_19 = true;
                        var7.field_542.method_208(var28);
                     }

                     var7.field_578 = 0;
                     class_14 var29 = var7.field_568;

                     for(int var43 = 0; var43 < var29.field_552; ++var43) {
                        var29.field_565[var43].field_20 = 6;
                     }
                  }
                  break;
               case 4:
                  if (this.field_542.field_251 - this.field_564 >= 800L) {
                     this.field_546 = true;
                     this.field_555 = 0;
                  }
            }

            boolean var17 = false;
            if (var7.field_542.field_251 - var7.field_563 >= 300L) {
               var17 = true;
               var7.field_563 = var7.field_542.field_251;
            }

            for(int var30 = 0; var30 < var7.field_565.length; ++var30) {
               if (var17) {
                  class_1 var44;
                  (var44 = class_1.method_19((class_1)null, 0, 0, 0, 1, 500, (byte)4)).method_11(var7.field_565[var30].field_3 + (var7.field_565[var30].field_6 >> 1), var7.field_565[var30].field_4 + (var7.field_565[var30].field_7 >> 1) + var7.field_562);
                  var7.field_542.method_208(var44);
               }

               int var45 = var7.field_565[var30].field_6 / 3;
               var7.field_565[var30].method_11(var7.field_576[var30][0] + (var45 * class_11.method_86(var7.field_577[var30]) >> 10), var7.field_576[var30][1] + (var45 * class_11.method_85(var7.field_577[var30]) / 3 >> 10));
               var7.field_577[var30] = (var7.field_577[var30] + var7.field_579) % 360;
            }
         } else {
            switch (this.field_555) {
               case 1:
                  if (this.field_542.field_251 - this.field_564 >= 200L) {
                     this.field_555 = 3;
                  }
               case 2:
               case 5:
               default:
                  break;
               case 3:
                  boolean var9 = true;
                  if (this.field_580 < this.field_552) {
                     if (this.field_544 == 0 || this.field_544 == 5) {
                        this.field_565[this.field_580].field_16 = -6;
                     }

                     if (this.field_544 == 5) {
                        this.field_565[this.field_580].field_14 = 2 * field_556[this.field_553];

                        for(int var19 = 0; var19 < 3; ++var19) {
                           class_1 var31;
                           (var31 = class_1.method_19(this.field_542.field_342, class_15.method_234(-1, 2), 0, -1, 1, 100, (byte)0)).method_11(this.field_565[this.field_580].field_3 + class_15.method_233(this.field_557.field_6 - var31.field_6), this.field_565[this.field_580].field_4 + this.field_557.field_7 - var31.field_7 + 1);
                           this.field_542.method_208(var31);
                        }
                     } else {
                        this.field_565[this.field_580].field_14 = field_556[this.field_553];
                     }

                     this.field_565[this.field_580].method_14(1, this.field_554);
                     ++this.field_580;
                     var9 = false;
                  }

                  for(int var20 = 0; var20 < this.field_580; ++var20) {
                     if (this.field_544 == 10) {
                        if (this.field_565[var20].field_23 != -1) {
                           ++this.field_565[var20].field_23;
                           if (this.field_565[var20].field_23 >= 16) {
                              this.field_565[var20].method_14(2, this.field_554);
                              this.field_565[var20].field_14 = 0;
                              this.field_565[var20].field_23 = -1;
                              class_1 var32;
                              (var32 = class_1.method_19((class_1)null, 0, 0, 0, 1, 800, (byte)2)).method_11(this.field_565[var20].field_3 + this.method_226(var32, this.field_557.field_6), this.field_565[var20].field_4 + this.field_557.field_7);
                              this.field_542.method_208(var32);
                              (var32 = class_1.method_19(this.field_560, 0, 0, 0, 1, 150, (byte)0)).method_11(this.field_565[var20].field_3 + this.method_226(var32, 24), this.field_565[var20].field_4 + 3);
                              this.field_542.method_208(var32);
                           } else {
                              var9 = false;
                           }
                        }
                     } else if (this.field_565[var20].field_9 < 0) {
                        ++this.field_565[var20].field_16;
                        var9 = false;
                     } else if (this.field_565[var20].field_16 >= 6) {
                        this.field_565[var20].field_9 = 0;
                        this.field_565[var20].field_16 = 0;
                        this.field_565[var20].field_14 = 0;
                        if (this.field_544 == 0 || this.field_544 == 5) {
                           this.field_565[var20].method_14(2, this.field_554);
                           if (this.field_544 == 0) {
                              class_1 var34;
                              (var34 = class_1.method_19(this.field_560, 0, 0, 0, 1, 150, (byte)0)).method_11(this.field_565[var20].field_3 + this.method_226(var34, 14), this.field_565[var20].field_4 + this.field_565[var20].field_7);
                              var34.field_9 = 4 - this.field_565[var20].field_7;
                              this.field_542.method_208(var34);
                           } else if (this.field_544 == 5) {
                              class_1 var35;
                              (var35 = class_1.method_19(this.field_542.field_242, 0, 0, 0, 1, 50, (byte)0)).method_11(this.field_565[var20].field_3 + this.method_226(var35, this.field_557.field_6 * 3 / 4), this.field_565[var20].field_4 + this.field_565[var20].field_7);
                              var35.field_9 = -var35.field_7;
                              this.field_542.method_208(var35);
                           }
                        }
                     }
                  }

                  if (var9) {
                     this.field_580 = 0;
                     this.field_555 = 6;
                     this.field_568.method_229();
                     this.field_542.method_210(200);
                     class_15.method_267(200);
                     class_15.method_272(14, 1);
                     this.field_564 = this.field_542.field_251;
                  }
                  break;
               case 4:
                  boolean var8 = true;
                  if (this.field_580 < this.field_552) {
                     if (this.field_544 != 0 && this.field_544 != 2 && this.field_544 != 5) {
                        if (this.field_544 == 10) {
                           this.field_565[this.field_580].field_23 = 0;
                        }
                     } else {
                        this.field_565[this.field_580].field_16 = -6;
                     }

                     if (this.field_544 == 5) {
                        this.field_565[this.field_580].field_14 = -2 * field_556[this.field_553];
                     } else {
                        this.field_565[this.field_580].field_14 = -field_556[this.field_553];
                     }

                     this.field_565[this.field_580].method_14(3, this.field_554);
                     ++this.field_580;
                     var8 = false;
                  }

                  for(int var18 = 0; var18 < this.field_580; ++var18) {
                     if (this.field_544 == 10) {
                        if (this.field_565[var18].field_23 != -1) {
                           ++this.field_565[var18].field_23;
                           if (this.field_565[var18].field_23 >= 16) {
                              this.field_565[var18].method_14(0, this.field_554);
                              this.field_565[var18].field_14 = 0;
                              this.field_565[var18].field_23 = -1;
                           } else {
                              var8 = false;
                           }
                        }
                     } else if (this.field_565[var18].field_9 < 0) {
                        ++this.field_565[var18].field_16;
                        var8 = false;
                     } else if (this.field_565[var18].field_16 >= 6) {
                        this.field_565[var18].field_16 = 0;
                        this.field_565[var18].field_14 = 0;
                        this.field_565[var18].field_9 = 0;
                        this.field_565[var18].method_14(0, this.field_554);
                     }
                  }

                  if (var8) {
                     this.field_546 = true;
                     this.field_555 = 0;
                     this.field_564 = this.field_542.field_251;
                  }
                  break;
               case 6:
                  if (this.field_544 == 10 && this.field_542.field_251 - this.field_564 >= 400L || (this.field_544 == 0 || this.field_544 == 2 || this.field_544 == 5) && this.field_542.field_251 - this.field_564 >= 50L) {
                     this.field_555 = 4;
                  }
            }
         }
      } else {
         class_14 var1 = this;
         switch (this.field_555) {
            case 1:
               if (this.field_544 == 6) {
                  if (this.field_580 == 0) {
                     if (this.field_542.field_251 - this.field_564 >= 200L) {
                        if (this.field_587 < this.field_552) {
                           this.field_565[this.field_587].field_23 = -1;
                           this.field_565[this.field_587].field_22 = false;
                           this.field_565[this.field_587].field_16 = 0;
                        }

                        if (++this.field_587 >= this.field_552) {
                           this.field_587 = 0;
                           this.field_580 = 1;
                           this.field_564 = this.field_542.field_251;
                        }
                     }
                  } else if (this.field_580 == 1) {
                     if (this.field_542.field_251 - this.field_564 >= 200L) {
                        if (this.field_587 < this.field_552) {
                           this.field_565[this.field_587].field_16 = -1;
                        }

                        if (++this.field_587 >= this.field_552) {
                           this.field_587 = 0;
                           this.field_580 = 2;
                           this.field_564 = this.field_542.field_251;
                        }
                     }
                  } else if (this.field_580 == 2) {
                     boolean var12;
                     label435: {
                        var12 = true;
                        if (this.field_542.field_251 - this.field_564 >= 200L) {
                           if (this.field_587 < this.field_552) {
                              this.field_565[this.field_587].field_23 = 0;
                              this.field_565[this.field_587].field_16 = 0;
                              this.field_565[this.field_587].method_14(2, this.field_554);
                              this.field_565[this.field_587].field_11 = 1;
                           }

                           if (++this.field_587 >= this.field_552) {
                              break label435;
                           }
                        }

                        var12 = false;
                     }

                     for(int var23 = 0; var23 < var1.field_552; ++var23) {
                        if (var1.field_565[var23].field_23 != 0) {
                           if (var1.field_565[var23].field_23 != -1) {
                              if (var1.field_565[var23].field_11 > 0) {
                                 var12 = false;
                              } else if (var1.field_565[var23].field_11 != -1) {
                                 if (var1.field_565[var23].field_23 == 1) {
                                    var1.field_565[var23].method_14(3, var1.field_554);
                                    var1.field_565[var23].field_11 = 1;
                                    var1.field_565[var23].field_23 = 2;
                                    var12 = false;
                                 } else if (var1.field_565[var23].field_23 == 2) {
                                    var1.field_565[var23].field_9 = -6;
                                    var1.field_565[var23].field_22 = true;
                                    var1.field_565[var23].field_16 = class_15.method_234(-2, 3);
                                    var1.field_565[var23].method_14(0, var1.field_554);
                                    var1.field_565[var23].field_11 = -1;
                                    var1.field_565[var23].field_23 = 3;
                                    var12 = false;
                                 }
                              }
                           }
                        } else {
                           if (var1.field_565[var23].field_2 == 1) {
                              var1.field_565[var23].field_9 = 0;
                              var1.field_565[var23].field_23 = 1;
                              class_15.method_267(200);
                              var1.field_542.method_210(1200);
                              class_15.method_272(14, 1);

                              for(int var37 = 0; var37 < 2; ++var37) {
                                 class_1 var13;
                                 (var13 = class_1.method_19(var1.field_542.field_240, 0, 0, -1, 1, class_15.method_233(4) * 50, (byte)0)).method_11(var1.field_565[var23].field_3 + class_15.method_233(var1.field_557.field_6 - var13.field_6), var1.field_565[var23].field_4 + var1.field_557.field_7 - var13.field_7 + 2);
                                 var13.field_19 = true;
                                 var1.field_542.method_208(var13);
                              }

                              class_1 var38;
                              (var38 = class_1.method_19(var1.field_542.field_240, -1, 0, -1, 1, class_15.method_233(4) * 50, (byte)0)).method_11(var1.field_565[var23].field_3, var1.field_565[var23].field_4 + var1.field_557.field_7 - var38.field_7 + 2);
                              var38.field_19 = true;
                              var1.field_542.method_208(var38);
                              (var38 = class_1.method_19(var1.field_542.field_240, 1, 0, -1, 1, class_15.method_233(4) * 50, (byte)0)).method_11(var1.field_565[var23].field_3 + var1.field_557.field_6 - var38.field_6, var1.field_565[var23].field_4 + var1.field_557.field_7 - var38.field_7 + 2);
                              var38.field_19 = true;
                              var1.field_542.method_208(var38);
                           }

                           var12 = false;
                        }
                     }

                     if (var12) {
                        var1.field_580 = 0;
                        var1.field_564 = var1.field_542.field_251;
                        var1.field_555 = 4;
                     }
                  }
               } else if (this.field_580 == 0) {
                  if (this.field_542.field_251 - this.field_564 >= 200L) {
                     if (this.field_544 == 9) {
                        this.field_585 = new class_1[this.field_552 << 1];
                     } else if (this.field_544 != 7 && this.field_544 != 1) {
                        this.field_585 = new class_1[this.field_552];
                     }

                     this.field_580 = 1;
                     this.field_587 = 0;
                     this.field_564 = this.field_542.field_251;
                  }
               } else if (this.field_580 == 1) {
                  boolean var14 = true;
                  if (this.field_587 < this.field_552) {
                     this.field_565[this.field_587].method_14(2, this.field_554);
                     this.field_565[this.field_587].field_11 = 1;
                     if (this.field_544 != 3 && this.field_544 != 2) {
                        if (this.field_544 == 1) {
                           class_1 var47;
                           (var47 = class_1.method_19(this.field_559, 0, 0, 0, 1, 0, (byte)0)).method_14(1, this.field_554);
                           var47.method_11(this.field_565[this.field_587].field_3 + this.method_226(var47, this.field_565[this.field_587].field_6), this.field_565[this.field_587].field_4);
                           this.field_542.method_208(var47);
                        } else if (this.field_544 == 7) {
                           this.field_565[this.field_587].field_20 = 5;

                           for(int var25 = 0; var25 < 3; ++var25) {
                              class_1 var41;
                              (var41 = class_1.method_19(var1.field_542.field_342, class_15.method_234(-1, 2), 0, 0, 1, class_15.method_233(4) * 50, (byte)0)).method_11(var1.field_565[var1.field_587].field_3 + var1.field_557.field_6 / 2, var1.field_565[var1.field_587].field_4);
                              var41.field_19 = true;
                              var1.field_542.method_208(var41);
                           }
                        } else if (this.field_544 == 9) {
                           class_1 var46;
                           (var46 = class_1.method_19(this.field_560, 0, 0, 0, 1, 200, (byte)0)).method_11(this.field_565[0].field_3, this.field_565[0].field_4 + this.field_557.field_7);
                           var46.field_9 = -this.field_557.field_7;
                           this.field_542.method_208(var46);
                           this.field_585[0] = class_1.method_19(this.field_558, field_556[this.field_553] * 3, -2, 0, -1, 100, (byte)0);
                           int var40 = this.field_565[this.field_587].field_3 + this.method_226(this.field_585[this.field_587], this.field_565[this.field_587].field_6 / 2);
                           int var24 = this.field_565[this.field_587].field_4 + this.field_557.field_7 - this.field_585[this.field_587].field_7 + 2;
                           this.field_585[0].method_11(var40, var24);
                           this.field_585[1] = class_1.method_19(this.field_558, field_556[this.field_553] * 3, 1, 0, -1, 100, (byte)0);
                           this.field_585[1].method_11(var40, var24);
                           this.field_542.method_208(this.field_585[1]);
                           this.field_585[1].field_21 = this.field_553;
                           this.field_542.method_208(this.field_585[0]);
                           this.field_585[0].field_21 = this.field_553;
                        } else if (this.field_544 == 8) {
                           this.field_585[this.field_587] = class_1.method_19((class_1)null, field_556[this.field_553], 0, 0, -1, 2000, (byte)6);
                           this.field_585[this.field_587].method_11(this.field_565[this.field_587].field_3 + this.method_226(this.field_585[this.field_587], this.field_565[this.field_587].field_6 + 2), this.field_565[this.field_587].field_4 + 30);
                           this.field_585[this.field_587].field_19 = true;
                           class_15.method_267(200);
                           this.field_542.method_210(1200);
                           class_15.method_272(14, 1);
                           this.field_542.method_208(this.field_585[this.field_587]);
                        }
                     } else {
                        this.field_585[this.field_587] = class_1.method_19(this.field_558, 0, 0, 0, 1, 50, (byte)0);
                        this.field_585[this.field_587].method_11(this.field_565[this.field_587].field_3 + this.method_226(this.field_585[this.field_587], this.field_565[this.field_587].field_6), this.field_565[this.field_587].field_4);
                        this.field_542.method_208(this.field_585[this.field_587]);
                     }
                  }

                  if (++var1.field_587 < var1.field_552) {
                     var14 = false;
                  }

                  for(int var26 = 0; var26 < var1.field_552; ++var26) {
                     if (var1.field_565[var26].field_11 > 0) {
                        var14 = false;
                     } else if (var1.field_544 != 7 && var1.field_565[var26].field_11 != -1) {
                        if (var1.field_565[var26].field_23 == 0) {
                           var1.field_565[var26].method_14(3, var1.field_554);
                           var1.field_565[var26].field_11 = 1;
                           var1.field_565[var26].field_23 = 1;
                           if (var1.field_544 == 8) {
                              var1.field_585[var26].field_17 = false;
                           }

                           var14 = false;
                        } else if (var1.field_565[var26].field_23 == 1) {
                           var1.field_565[var26].method_14(0, var1.field_554);
                           var1.field_565[var26].field_11 = -1;
                           var1.field_565[var26].field_23 = 2;
                           var14 = false;
                        }
                     }

                     if (var1.field_544 == 8 && var1.field_585[var26] != null && var1.field_585[var26].field_17) {
                        class_1 var42;
                        (var42 = class_1.method_19(var1.field_542.field_342, field_556[var1.field_553] * class_15.method_234(1, 4), class_15.method_234(-2, 3), 0, 1, 50 * class_15.method_233(4), (byte)0)).method_11(var1.field_565[var26].field_3 + var1.method_226(var42, var1.field_557.field_6), var1.field_585[var26].field_4 + class_15.method_233(30 - var1.field_585[var26].field_7) - 15);
                        var42.field_19 = true;
                        var1.field_542.method_208(var42);
                     }
                  }

                  if (var14) {
                     if (var1.field_543.field_44 == 9) {
                        var1.field_555 = 6;
                     } else {
                        var1.field_555 = 4;
                     }

                     var1.field_581 = 400;
                     if (var1.field_543.field_44 == 8) {
                        var1.field_581 = 0;
                     }

                     var1.field_564 = var1.field_542.field_251;
                     var1.field_580 = 0;
                  }
               }
            case 2:
            case 3:
            case 5:
            default:
               break;
            case 4:
               if (this.field_580 == 0) {
                  if (this.field_542.field_251 - this.field_564 >= (long)this.field_581) {
                     this.field_568.method_229();
                     if (this.field_544 != 1) {
                        this.field_542.method_210(200);
                     }

                     class_15.method_267(200);
                     class_15.method_272(14, 1);
                     if (this.field_559 != null) {
                        this.field_586 = class_2.field_79[this.field_544].length;
                     }

                     this.field_580 = 1;
                  }
               } else if (this.field_580 == 1) {
                  if (--this.field_586 < 0) {
                     this.field_555 = 7;
                     this.field_564 = this.field_542.field_251;
                  } else {
                     class_1 var5;
                     if (this.field_544 != 3 && this.field_544 != 2 && this.field_544 != 1) {
                        var5 = class_1.method_19(this.field_559, 0, 0, 0, -1, 0, (byte)0);
                     } else {
                        var5 = class_1.method_19(this.field_559, 0, 0, 0, 1, 0, (byte)0);
                     }

                     if (this.field_544 == 2 || this.field_544 == 1) {
                        var5.field_18 = false;
                     }

                     int var36 = class_15.method_233(this.field_542.field_186 / 2 - var5.field_6);
                     int var21 = 0;
                     if (this.field_568.field_567 != null) {
                        var21 = 0 + (this.field_568.field_567.field_635 - var5.field_7);
                     }

                     int var11 = (this.field_542.field_479 - var21) * ((this.field_586 << 1) + 1) / (class_2.field_79[this.field_544].length << 1) - var5.field_7 / 2 + var21;
                     if (this.field_553 == 0) {
                        var36 += this.field_542.field_188;
                     }

                     if (this.field_544 == 7 || this.field_544 == 6) {
                        var5.field_9 = var11;
                        var11 = 0;
                     }

                     var5.method_11(var36, var11);
                     this.field_542.method_208(var5);

                     for(int var22 = 0; var22 < 3; ++var22) {
                        class_1 var6;
                        if (var1.field_544 != 7 && var1.field_544 != 6) {
                           (var6 = class_1.method_19(var1.field_542.field_342, class_15.method_234(-1, 2), 0, -1, 1, 100, (byte)0)).method_11(var36 + class_15.method_233(var5.field_6 - var6.field_6), var11 + var5.field_7 - var6.field_7 + 1);
                        } else {
                           (var6 = class_1.method_19(var1.field_542.field_240, class_15.method_234(-1, 2), 0, class_15.method_234(-2, 0), 1, class_15.method_233(4) * 50, (byte)0)).method_11(var36 + class_15.method_233(var5.field_6 - var6.field_6), var5.field_9 + var5.field_7 - var6.field_7 + 1);
                           var6.field_9 = -var5.field_7 / 2;
                        }

                        var1.field_542.method_208(var6);
                     }
                  }
               }
               break;
            case 6:
               boolean var2 = true;

               for(int var3 = 0; var3 < var1.field_585.length; ++var3) {
                  if (var1.field_544 == 9 && class_15.method_233(2) == 0) {
                     class_1 var4;
                     (var4 = class_1.method_19(var1.field_542.field_342, class_15.method_234(-2, 1), 0, -1, 1, 100, (byte)0)).method_11(var1.field_585[var3].field_3 + var1.method_226(var1.field_585[var3], 0), var1.field_585[var3].field_4 + var1.field_585[var3].field_7 - var4.field_7);
                     if (var1.field_585[var3].field_23 == 1) {
                        var4.field_21 = var1.field_568.field_553;
                     } else {
                        var4.field_21 = var1.field_553;
                     }

                     var1.field_542.method_208(var4);
                  }

                  if (var1.field_544 == 9) {
                     if (var1.field_553 == 0) {
                        if (var1.field_585[var3].field_3 < var1.field_542.field_186) {
                           var2 = false;
                           continue;
                        }

                        if (var1.field_585[var3].field_23 != 0) {
                           if (var1.field_585[var3].field_23 == 1) {
                              var1.field_542.method_209(var1.field_585[var3]);
                              var1.field_585[var3].field_23 = 2;
                           }
                           continue;
                        }

                        var1.field_585[var3].method_11(var1.field_542.field_188 - var1.field_585[var3].field_6, var1.field_585[var3].field_4);
                        var1.field_585[var3].field_21 = var1.field_568.field_553;
                        var1.field_585[var3].field_23 = 1;
                     } else {
                        if (var1.field_553 != 1) {
                           continue;
                        }

                        if (var1.field_585[var3].field_3 + var1.field_585[var3].field_6 < 0) {
                           if (var1.field_585[var3].field_23 != 0) {
                              if (var1.field_585[var3].field_23 == 1) {
                                 var1.field_542.method_209(var1.field_585[var3]);
                                 var1.field_585[var3].field_23 = 2;
                              }
                              continue;
                           }

                           var1.field_585[var3].method_11(var1.field_542.field_188, var1.field_585[var3].field_4);
                           var1.field_585[var3].field_21 = var1.field_568.field_553;
                           var1.field_585[var3].field_23 = 1;
                        }
                     }

                     var2 = false;
                  }
               }

               if (var2) {
                  var1.field_580 = 0;
                  var1.field_555 = 4;
                  var1.field_564 = var1.field_542.field_251;
               }
               break;
            case 7:
               if (this.field_542.field_251 - this.field_564 >= 1000L) {
                  this.field_555 = 0;
                  this.field_546 = true;
               }
         }
      }

      if (this.field_544 == 6) {
         for(int var10 = 0; var10 < this.field_565.length; ++var10) {
            if (this.field_565[var10].field_22) {
               this.field_565[var10].field_9 = -6 + 4 * class_11.method_85(this.field_577[var10]) >> 10;
               this.field_577[var10] = (this.field_577[var10] + 10) % 360;
            }
         }
      }

   }

   // $FF: renamed from: c () void
   private void method_229() {
      this.field_547 = true;
      this.field_575 = this.field_550 - this.field_551;
      this.field_552 = this.field_551;

      for(int var3 = 0; var3 < this.field_575; ++var3) {
         this.field_542.method_209(this.field_565[var3]);
         class_1 var1;
         (var1 = class_1.method_19(this.field_542.field_242, 0, 0, 0, 1, 0, (byte)0)).method_11(this.field_565[var3].field_3 + (this.field_565[var3].field_6 - var1.field_6) / 2, this.field_542.k);
         var1.field_9 = this.field_565[var3].field_4 + (this.field_565[var3].field_7 - var1.field_7) / 2 - this.field_542.k;
         this.field_542.method_208(var1);

         for(int var2 = 0; var2 < 3; ++var2) {
            class_1 var4;
            (var4 = class_1.method_19(this.field_542.field_342, var2 + -1, 0, class_15.method_234(-4, -1), 1, class_15.method_233(4) * 50, (byte)0)).method_11(this.field_565[var3].field_3 + (this.field_565[var3].field_6 - var4.field_6) / 2, this.field_565[var3].field_4 + this.field_565[var3].field_7 - var4.field_7 + 3);
            this.field_542.method_208(var4);
         }

         class_1 var8;
         (var8 = class_1.method_19(this.field_542.field_240, 0, 0, -1, 1, 200, (byte)0)).method_11(this.field_565[var3].field_3 + (this.field_565[var3].field_6 - var8.field_6) / 2, this.field_565[var3].field_4 + this.field_565[var3].field_7 - var8.field_7 + 3);
         this.field_542.method_208(var8);
      }

      class_1[] var7 = new class_1[this.field_552];
      System.arraycopy(this.field_565, this.field_575, var7, 0, this.field_552);
      this.field_565 = var7;
      class_1 var5 = class_1.method_18("" + (this.field_549 - this.field_548), 0, -4, (byte)1);
      int var6;
      int var9;
      if (this.field_551 == 1) {
         var9 = this.field_565[0].field_3 + this.field_565[0].field_6 / 2;
         var6 = this.field_565[0].field_4 + this.field_565[0].field_7 + 1;
      } else {
         var9 = this.field_542.field_188 / 2;
         if (this.field_553 == 1) {
            var9 += this.field_542.field_188;
         }

         var6 = (this.field_542.field_479 + this.field_570) / 2;
      }

      var5.method_11(var9, var6);
      var5.field_19 = true;
      this.field_542.method_208(var5);
   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics, int, int) void
   public final void method_230(Graphics var1, int var2, int var3) {
      var1.translate(var2, var3);
      int var4 = 0;
      int var5 = 0;

      for(int var6 = this.field_571; var5 < var6; ++var5) {
         int var8 = this.field_570 + var3;
         int var7 = 0;

         for(int var9 = this.field_572; var7 < var9; ++var7) {
            this.field_566[this.field_582[var5][var7]].method_281(var1, var4, var8);
            var8 += 24;
         }

         var4 += 24;
      }

      if (this.field_567 != null) {
         var5 = this.field_567.field_634;
         var4 = 0;
         int var12 = 0;

         for(int var13 = (this.field_542.field_188 + var5 - 1) / var5; var12 < var13; ++var12) {
            this.field_567.method_281(var1, var4, 0);
            var4 += var5;
         }
      }

      var1.translate(-var2, -var3);
   }

   // $FF: renamed from: a (javax.microedition.lcdui.Graphics) void
   public final void method_231(Graphics var1) {
      int var2 = this.field_542.k - class_13.field_185 / 2;
      class_15.method_249(var1, this.field_574 + "/" + 100, this.field_542.field_188 / 2, var2, 1, 3);
   }

   // $FF: renamed from: b (javax.microedition.lcdui.Graphics) void
   public final void method_232(Graphics var1) {
      var1.setColor(4210752);

      for(int var2 = 0; var2 < this.field_552; ++var2) {
         class_1 var3 = this.field_565[var2];
         if (this.field_544 == 0 || this.field_544 == 4 || this.field_544 == 5 || this.field_544 == 6) {
            var1.fillArc(var3.field_3, var3.field_4 + (var3.field_7 << 2) / 5, var3.field_6, var3.field_7 / 4, 0, 360);
         }
      }

   }

   static {
      byte[] var10000 = new byte[]{18, -18};
      field_556 = new byte[]{3, -3};
   }
}
