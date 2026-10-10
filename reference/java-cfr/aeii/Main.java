/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.midlet.MIDlet
 */
package aeii;

import aeii.c;
import javax.microedition.midlet.MIDlet;

public class Main
extends MIDlet {
    public static Main var_aeii_Main_a;
    private static c var_aeii_c_a;

    public final void startApp() {
        if (var_aeii_Main_a == null) {
            var_aeii_Main_a = this;
            var_aeii_c_a = new c(this);
        }
    }

    public final void destroyApp(boolean bl) {
        if (var_aeii_c_a != null) {
            var_aeii_c_a.c();
        }
        var_aeii_c_a = null;
        var_aeii_Main_a = null;
    }

    public final void pauseApp() {
    }
}

