/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.io.StreamConnection
 */
package a.a.a;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import javax.microedition.io.StreamConnection;

public interface c
extends StreamConnection {
    public InputStream openInputStream();

    public OutputStream openOutputStream();

    public Enumeration java_util_Enumeration_a();

    public void void_a();

    public boolean boolean_a();

    public void a(long var1);
}

