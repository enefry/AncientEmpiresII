/*
 * Decompiled with CFR 0.152.
 */
package a.a;

import a.a.a;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Vector;

public final class b
implements Enumeration {
    private Enumeration var_java_util_Enumeration_a;
    private Vector var_java_util_Vector_a = new Vector();
    private int var_int_a;
    private int b;

    public b() {
        a a2 = new a(this);
        this.var_java_util_Vector_a.addElement(a2);
        this.var_java_util_Enumeration_a = a2;
        this.b = 0;
        this.var_int_a = 0;
    }

    public final void a(Enumeration enumeration) {
        if (enumeration == null || !enumeration.hasMoreElements()) {
            return;
        }
        this.var_java_util_Vector_a.addElement(enumeration);
        this.b = this.var_java_util_Vector_a.size() - 1;
    }

    public final boolean hasMoreElements() {
        if (this.var_int_a < this.b) {
            return true;
        }
        return this.var_java_util_Enumeration_a.hasMoreElements();
    }

    public final Object nextElement() {
        while (true) {
            if (this.var_java_util_Enumeration_a.hasMoreElements()) {
                return this.var_java_util_Enumeration_a.nextElement();
            }
            if (++this.var_int_a > this.b) break;
            this.var_java_util_Enumeration_a = (Enumeration)this.var_java_util_Vector_a.elementAt(this.var_int_a);
        }
        throw new NoSuchElementException();
    }
}

