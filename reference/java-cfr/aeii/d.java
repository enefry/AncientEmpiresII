/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
package aeii;

import aeii.c;
import aeii.g;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class d {
    public Image var_javax_microedition_lcdui_Image_a;
    private boolean var_boolean_a;
    private int d;
    private int e;
    public int var_int_a;
    public int b;
    private int f;
    private int g;
    public int c;

    public d() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static Image a(Image image, int n, int n2, boolean n3) {
        if (image == null) {
            return null;
        }
        try {
            int n4;
            int n5;
            int n6;
            int n7;
            int[] nArray;
            int[] nArray2;
            n3 = image.getWidth();
            int n8 = image.getHeight();
            if (n3 == n && n8 == n2) {
                return image;
            }
            if (n < 0) {
                if (n2 < 0) {
                    return image;
                }
                n = n3 * n2 / n8;
            } else if (n2 < 0) {
                n2 = n8 * n / n3;
            }
            int n9 = 1;
            do {
                n7 = n3 / n9;
                n6 = n8 / n9;
                n5 = n / n9;
                n4 = n2 / n9;
                try {
                    nArray2 = new int[n7 * n6];
                    nArray = new int[n5 * n4];
                }
                catch (OutOfMemoryError outOfMemoryError) {
                    nArray2 = null;
                    nArray = null;
                    ++n9;
                }
            } while (nArray2 == null || nArray == null);
            if (n9 == 1) {
                image.getRGB(nArray2, 0, n3, 0, 0, n3, n8);
                aeii.d.a(nArray2, nArray, n3, n8, n, n2, true);
                return Image.createRGBImage((int[])nArray, (int)n, (int)n2, (boolean)true);
            }
            Image image2 = Image.createImage((int)n, (int)n2);
            Graphics graphics = image2.getGraphics();
            n3 = 0;
            while (true) {
                if (n3 >= n9) {
                    return image2;
                }
                for (n8 = 0; n8 < n9; ++n8) {
                    image.getRGB(nArray2, 0, n7, n3 * n7, n8 * n6, n7, n6);
                    aeii.d.a(nArray2, nArray, n7, n6, n5, n4, false);
                    graphics.drawRGB(nArray, 0, n5, n3 * n5, n8 * n4, n5, n4, false);
                }
                ++n3;
            }
        }
        catch (Throwable throwable) {
            return image;
        }
    }

    private static void a(int[] nArray, int[] nArray2, int n, int n2, int n3, int n4, boolean n5) {
        int n6 = (n << 16) / n3;
        int n7 = (n2 << 16) / n4;
        n2 = n6 >>> 16;
        int n8 = n7 >>> 16;
        if (n2 == 0) {
            n2 = 1;
        }
        if (n8 == 0) {
            n8 = 1;
        }
        int n9 = n2 * n8;
        try {
            if (n5 != 0) {
                for (n5 = 0; n5 < n3; ++n5) {
                    for (int i = 0; i < n4; ++i) {
                        int n10 = n5 * n6 >>> 16;
                        int n11 = i * n7 >>> 16;
                        int n12 = 0;
                        int n13 = 0;
                        int n14 = 0;
                        int n15 = 0;
                        for (int j = 0; j < n2; ++j) {
                            for (int k = 0; k < n8; ++k) {
                                int n16 = n10 + j + (n11 + k) * n;
                                n15 += nArray[n16] >>> 24;
                                n14 += nArray[n16] >> 16 & 0xFF;
                                n13 += nArray[n16] >> 8 & 0xFF;
                                n12 += nArray[n16] & 0xFF;
                            }
                        }
                        nArray2[n5 + i * n3] = (n15 /= n9) << 24 | (n14 /= n9) << 16 | (n13 /= n9) << 8 | (n12 /= n9);
                    }
                }
            } else {
                for (n5 = 0; n5 < n3; ++n5) {
                    for (int i = 0; i < n4; ++i) {
                        int n17 = n5 * n6 >>> 16;
                        int n18 = i * n7 >>> 16;
                        int n19 = 0;
                        int n20 = 0;
                        int n21 = 0;
                        for (int j = 0; j < n2; ++j) {
                            for (int k = 0; k < n8; ++k) {
                                int n22 = n17 + j + (n18 + k) * n;
                                n21 += nArray[n22] >> 16 & 0xFF;
                                n20 += nArray[n22] >> 8 & 0xFF;
                                n19 += nArray[n22] & 0xFF;
                            }
                        }
                        nArray2[n5 + i * n3] = (n21 /= n9) << 16 | (n20 /= n9) << 8 | (n19 /= n9);
                    }
                }
            }
            return;
        }
        catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            return;
        }
    }

    public d(d d2, int n, int n2, int n3, int n4) {
        this.var_boolean_a = false;
        this.c = 0;
        this.var_javax_microedition_lcdui_Image_a = d2.var_javax_microedition_lcdui_Image_a;
        this.var_int_a = n3;
        this.b = n4;
        this.d = n * n3 + d2.d;
        this.e = n2 * n4 + d2.e;
        this.var_boolean_a = true;
    }

    public d(d d2, int n) {
        this.var_boolean_a = false;
        this.c = 0;
        this.var_javax_microedition_lcdui_Image_a = d2.var_javax_microedition_lcdui_Image_a;
        this.var_int_a = d2.var_int_a;
        this.b = d2.b;
        this.d = d2.d;
        this.e = d2.e;
        this.f = d2.f;
        this.g = d2.g;
        this.var_boolean_a = d2.var_boolean_a;
        if ((n & 1) != 0) {
            this.c = 2;
            return;
        }
        if ((n & 2) != 0) {
            this.c = 1;
            return;
        }
        if ((n & 4) != 0) {
            this.c = 6;
            return;
        }
        if ((n & 8) != 0) {
            this.c = 3;
            return;
        }
        if ((n & 0x10) != 0) {
            this.c = 5;
        }
    }

    public d(String object, int n, int n2) {
        this.var_boolean_a = false;
        this.c = 0;
        object = aeii.c.byte_arr_a((String)object + ".png");
        this.var_javax_microedition_lcdui_Image_a = aeii.d.a(Image.createImage((byte[])object, (int)0, (int)((Object)object).length), n, -1, true);
        this.var_int_a = (short)this.var_javax_microedition_lcdui_Image_a.getWidth();
        this.b = (short)this.var_javax_microedition_lcdui_Image_a.getHeight();
        this.var_boolean_a = false;
    }

    public d(String string) {
        this(string, -1, -1);
    }

    public void a(int n, int n2, int n3) {
        if (!this.var_boolean_a) {
            int n4 = n & 0xD;
            n &= 0x32;
            if (this.c == 2) {
                if ((n4 & 4) != 0) {
                    n4 = 8;
                } else if ((n4 & 8) != 0) {
                    n4 = 4;
                }
            } else if (this.c == 1) {
                if ((n & 0x10) != 0) {
                    n4 = 32;
                } else if ((n4 & 0x20) != 0) {
                    n4 = 16;
                }
            }
            n = n4 | n;
            if ((n & 8) != 0) {
                this.f = n2 - this.var_int_a;
            } else if ((n & 1) != 0) {
                this.f = n2 - this.var_int_a >> 1;
            }
            if ((n & 0x20) != 0) {
                this.g = n3 - this.b;
                return;
            }
            if ((n & 2) != 0) {
                this.g = n3 - this.b >> 1;
            }
        }
    }

    public void a(int n, int n2) {
        this.f += n;
        this.g += n2;
    }

    public void a(Graphics graphics, int n, int n2) {
        this.a(graphics, n, n2, 20);
    }

    public void a(Graphics graphics, int n, int n2, int n3) {
        if (!this.var_boolean_a && this.c == 0) {
            graphics.drawImage(this.var_javax_microedition_lcdui_Image_a, n + this.f, n2 + this.g, n3);
            return;
        }
        graphics.drawRegion(this.var_javax_microedition_lcdui_Image_a, this.d, this.e, this.var_int_a, this.b, this.c, n + this.f, n2 + this.g, n3);
    }

    public Image a() {
        return Image.createImage((Image)this.var_javax_microedition_lcdui_Image_a, (int)this.d, (int)this.e, (int)this.var_int_a, (int)this.b, (int)this.c);
    }

    public d(String object, int n) {
        this.var_boolean_a = false;
        this.c = 0;
        Object object2 = null;
        if (n >= 0) {
            object2 = aeii.c.byte_arr_a(aeii.g.var_java_lang_String_arr_a[n / 2] + "/" + (String)object + ".png");
        }
        if (object2 == null) {
            object2 = aeii.c.byte_arr_a((String)object + ".png");
        }
        if (n >= 0 && (n & 1) == 1) {
            object = new byte[(object2).length];
            System.arraycopy(object2, 0, object, 0, ((byte[])object2).length);
            aeii.d.a((byte[])object);
            object2 = object;
        }
        this.var_javax_microedition_lcdui_Image_a = Image.createImage(object2, (int)0, (int)(object2).length);
        this.var_int_a = (short)this.var_javax_microedition_lcdui_Image_a.getWidth();
        this.b = (short)this.var_javax_microedition_lcdui_Image_a.getHeight();
    }

    public static void a(byte[] byArray) {
        try {
            int n;
            int n2;
            int n3 = 33;
            int n4 = byArray.length - 3;
            for (n2 = 0; n2 < n4; ++n2) {
                if (byArray[n2] != 80 || byArray[n2 + 1] != 76 || byArray[n2 + 2] != 84) continue;
                n3 = n2 - 4;
                break;
            }
            n4 = (byArray[n3] & 0xFF) << 24 | (byArray[n3 + 1] & 0xFF) << 16 | (byArray[n3 + 2] & 0xFF) << 8 | byArray[n3 + 3] & 0xFF;
            n2 = n3 + 4;
            int n5 = -1;
            for (n = 0; n < 4; ++n) {
                n5 = aeii.d.a(byArray[n2 + n], n5);
            }
            for (n = n2 += 4; n < n2 + n4; n += 3) {
                int n6 = byArray[n] & 0xFF;
                int n7 = byArray[n + 1] & 0xFF;
                int n8 = byArray[n + 2] & 0xFF;
                if (n6 != 244 || n7 != 244 || n8 != 230) {
                    n6 = n7 = (n6 + n7 + n8) / 3;
                    byArray[n] = (byte)n6;
                    byArray[n + 1] = (byte)n7;
                    byArray[n + 2] = (byte)n7;
                }
                n5 = aeii.d.a(byArray[n], n5);
                n5 = aeii.d.a(byArray[n + 1], n5);
                n5 = aeii.d.a(byArray[n + 2], n5);
            }
            n = n3 + 8 + n4;
            byArray[n] = (n5 ^= 0xFFFFFFFF) >> 24;
            byArray[n + 1] = (byte)(n5 >> 16);
            byArray[n + 2] = (byte)(n5 >> 8);
            byArray[n + 3] = (byte)n5;
            return;
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            return;
        }
    }

    public static int a(byte by, int n) {
        by = (byte)(by & 0xFF);
        n ^= by;
        for (by = 0; by < 8; by = (byte)(by + 1)) {
            if ((n & 1) != 0) {
                n = n >>> 1 ^ 0xEDB88320;
                continue;
            }
            n >>>= 1;
        }
        return n;
    }
}

