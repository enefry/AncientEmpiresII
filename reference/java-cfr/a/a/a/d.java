/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.siemens.mp.io.file.FileConnection
 *  javax.microedition.io.Connection
 */
package a.a.a;

import a.a.a.c;
import com.siemens.mp.io.file.FileConnection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import javax.microedition.io.Connection;

final class d
implements c {
    private FileConnection a;

    public d(Connection connection) {
        this.a = (FileConnection)connection;
    }

    public final void close() {
        this.a.close();
    }

    public final InputStream openInputStream() {
        return this.a.openInputStream();
    }

    public final DataInputStream openDataInputStream() {
        return this.a.openDataInputStream();
    }

    public final OutputStream openOutputStream() {
        return this.a.openOutputStream();
    }

    public final DataOutputStream openDataOutputStream() {
        return this.a.openDataOutputStream();
    }

    public final Enumeration java_util_Enumeration_a() {
        return this.a.list();
    }

    public final void void_a() {
        this.a.create();
    }

    public final boolean boolean_a() {
        return this.a.exists();
    }

    public final void a(long l) {
        this.a.truncate(0L);
    }
}

