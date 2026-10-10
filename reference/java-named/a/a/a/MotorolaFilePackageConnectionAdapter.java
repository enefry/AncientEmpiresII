package a.a.a;

import com.motorola.io.file.FileConnection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import javax.microedition.io.Connection;

// $FF: renamed from: a.a.a.f
final class MotorolaFilePackageConnectionAdapter implements GameFileConnection {
   // $FF: renamed from: a com.motorola.io.file.FileConnection
   private FileConnection field_82;

   public MotorolaFilePackageConnectionAdapter(Connection var1) {
      super();
      this.field_82 = (FileConnection)var1;
   }

   public final void close() {
      this.field_82.close();
   }

   public final InputStream openInputStream() {
      return this.field_82.openInputStream();
   }

   public final DataInputStream openDataInputStream() {
      return this.field_82.openDataInputStream();
   }

   public final OutputStream openOutputStream() {
      return this.field_82.openOutputStream();
   }

   public final DataOutputStream openDataOutputStream() {
      return this.field_82.openDataOutputStream();
   }

   // $FF: renamed from: a () java.util.Enumeration
   public final Enumeration method_0() {
      return this.field_82.list();
   }

   // $FF: renamed from: a () void
   public final void method_1() {
      this.field_82.create();
   }

   // $FF: renamed from: a () boolean
   public final boolean method_2() {
      return this.field_82.exists();
   }

   // $FF: renamed from: a (long) void
   public final void method_3(long var1) {
      this.field_82.truncate(0L);
   }
}
