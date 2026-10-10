/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.motorola.io.FileSystemRegistry
 *  com.motorola.io.file.FileSystemRegistry
 *  com.siemens.mp.io.file.FileSystemRegistry
 *  javax.microedition.io.file.FileSystemRegistry
 */
package a.a.a;

import com.siemens.mp.io.file.FileSystemRegistry;
import java.util.Enumeration;

public final class a {
    private static int a = -1;

    static int int_a() {
        if (a < 0) {
            a = a.a.a.a.a("javax.microedition.io.file.FileConnection") ? 1 : (a.a.a.a.a("com.siemens.mp.io.file.FileConnection") ? 2 : (a.a.a.a.a("com.motorola.io.FileConnection") ? 3 : (a.a.a.a.a("com.motorola.io.file.FileConnection") ? 4 : 0)));
        }
        return a;
    }

    private static boolean a(String string) {
        try {
            return Class.forName(string) != null;
        }
        catch (Throwable throwable) {
            return false;
        }
    }

    public static Enumeration java_util_Enumeration_a() {
        switch (a.a.a.a.int_a()) {
            case 1: {
                return javax.microedition.io.file.FileSystemRegistry.listRoots();
            }
            case 2: {
                return FileSystemRegistry.listRoots();
            }
            case 3: {
                return com.motorola.io.FileSystemRegistry.listRoots();
            }
            case 4: {
                return com.motorola.io.file.FileSystemRegistry.listRoots();
            }
        }
        return null;
    }
}

