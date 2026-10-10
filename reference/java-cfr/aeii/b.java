/*
 * Decompiled with CFR 0.152.
 */
package aeii;

import a.a.a.a;
import a.a.a.c;
import a.a.a.g;
import java.util.Enumeration;
import java.util.Vector;

public final class b {
    private String a = null;

    public final Enumeration a(String string, boolean bl) {
        try {
            if (this.a == null) {
                return a.a.a.a.java_util_Enumeration_a();
            }
            Object object = (c)g.javax_microedition_io_Connection_a("file:///" + this.a, 1);
            Enumeration enumeration = object.java_util_Enumeration_a();
            object.close();
            object = new Vector();
            if (string == null) {
                string = "";
            }
            while (enumeration.hasMoreElements()) {
                String string2 = (String)enumeration.nextElement();
                if (!string2.endsWith("/") && !string2.toLowerCase().endsWith(string)) continue;
                ((Vector)object).addElement(string2);
            }
            return ((Vector)object).elements();
        }
        catch (Exception exception) {
            return null;
        }
    }

    public final void void_a(String string) {
        if (string != null && !string.equals("..")) {
            if (this.a != null) {
                this.a = this.a + string;
                return;
            }
            this.a = string;
            return;
        }
        if (this.a != null) {
            this.a = this.a.substring(0, this.a.length() - 1);
            int n = this.a.lastIndexOf(47);
            if (n >= 0) {
                this.a = this.a.substring(0, n + 1);
                return;
            }
            this.a = null;
        }
    }

    public final c a_a_a_c_a(String string) {
        try {
            return (c)g.a("file:///" + this.a + string);
        }
        catch (Exception exception) {
            return null;
        }
    }

    public final String a() {
        return this.a;
    }

    public static Enumeration a(Enumeration enumeration) {
        if (enumeration == null) {
            return null;
        }
        Vector<String> vector = new Vector<String>();
        Vector<String> vector2 = new Vector<String>();
        while (enumeration.hasMoreElements()) {
            String string = (String)enumeration.nextElement();
            if (string.endsWith("/")) {
                vector.addElement(string);
                continue;
            }
            vector2.addElement(string);
        }
        if (vector.size() > 0) {
            b.a(vector, 0, vector.size() - 1);
        }
        if (vector2.size() > 0) {
            b.a(vector2, 0, vector2.size() - 1);
        }
        enumeration = new a.a.b();
        ((a.a.b)enumeration).a(vector.elements());
        ((a.a.b)enumeration).a(vector2.elements());
        return enumeration;
    }

    private static void a(Vector vector, int n, int n2) {
        while (true) {
            String string = ((String)vector.elementAt((n2 - n) / 2 + n)).toLowerCase();
            int n3 = n;
            int n4 = n2;
            while (n3 <= n4) {
                while (((String)vector.elementAt(n3)).toLowerCase().compareTo(string) < 0 && n3 <= n2) {
                    ++n3;
                }
                while (((String)vector.elementAt(n4)).toLowerCase().compareTo(string) > 0 && n4 >= n) {
                    --n4;
                }
                if (n3 > n4) continue;
                String string2 = (String)vector.elementAt(n3);
                vector.setElementAt(vector.elementAt(n4), n3);
                vector.setElementAt(string2, n4);
                ++n3;
                --n4;
            }
            if (n4 > n) {
                b.a(vector, n, n4);
            }
            if (n3 >= n2) break;
            n = n3;
        }
    }

    public static String[] a(Enumeration enumeration, String[] stringArray) {
        Vector<String> vector = new Vector<String>();
        if (stringArray != null) {
            for (int i = 0; i < stringArray.length; ++i) {
                vector.addElement(stringArray[i]);
            }
        }
        while (enumeration.hasMoreElements()) {
            vector.addElement((String)enumeration.nextElement());
        }
        Object[] objectArray = new String[vector.size()];
        vector.copyInto(objectArray);
        return objectArray;
    }
}

