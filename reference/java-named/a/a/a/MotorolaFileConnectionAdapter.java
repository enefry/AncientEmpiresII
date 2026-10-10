package a.a.a;

import com.motorola.io.FileConnection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import javax.microedition.io.Connection;

// $FF: renamed from: a.a.a.b
final class MotorolaFileConnectionAdapter implements GameFileConnection {
   // $FF: renamed from: a com.motorola.io.FileConnection
   private FileConnection field_83;

   public MotorolaFileConnectionAdapter(Connection var1) {
      super();
      this.field_83 = (FileConnection)var1;
   }

   public final void close() {
      this.field_83.close();
   }

   public final InputStream openInputStream() {
      return this.field_83.openInputStream();
   }

   public final DataInputStream openDataInputStream() {
      return this.field_83.openDataInputStream();
   }

   public final OutputStream openOutputStream() {
      return this.field_83.openOutputStream();
   }

   public final DataOutputStream openDataOutputStream() {
      return this.field_83.openDataOutputStream();
   }

   // $FF: renamed from: a () java.util.Enumeration
   public final Enumeration method_0() {
      return this.field_83.list();
   }

   // $FF: renamed from: a () void
   public final void method_1() {
      this.field_83.create();
   }

   // $FF: renamed from: a () boolean
   public final boolean method_2() {
      return this.field_83.exists();
   }

   // $FF: renamed from: a (long) void
   public final void method_3(long var1) {
      this.field_83.truncate(0L);
   }
}
