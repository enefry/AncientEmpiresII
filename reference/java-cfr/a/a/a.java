/*
 * Decompiled with CFR 0.152.
 */
package a.a;

import a.a.b;
import java.util.Enumeration;
import java.util.NoSuchElementException;

final class a
implements Enumeration {
    a(b b2) {
    }

    public final boolean hasMoreElements() {
        return false;
    }

    public final Object nextElement() {
        throw new NoSuchElementException();
    }
}

