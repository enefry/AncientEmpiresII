package a.a.a;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import javax.microedition.io.Connection;
import javax.microedition.io.file.FileConnection;

final class e implements c {
   private FileConnection a;

   public e(Connection var1) {
      super();
      this.a = (FileConnection)var1;
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

   public final Enumeration a() {
      return this.a.list();
   }

   public final void a() {
      this.a.create();
   }

   public final boolean a() {
      return this.a.exists();
   }

   public final void a(long var1) {
      this.a.truncate(0L);
   }
}
