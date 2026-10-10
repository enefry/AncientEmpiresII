package a.a.a;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import javax.microedition.io.Connection;
import javax.microedition.io.file.FileConnection;

// $FF: renamed from: a.a.a.e
final class class_5 implements class_0 {
   // $FF: renamed from: a javax.microedition.io.file.FileConnection
   private FileConnection field_84;

   public class_5(Connection var1) {
      super();
      this.field_84 = (FileConnection)var1;
   }

   public final void close() {
      this.field_84.close();
   }

   public final InputStream openInputStream() {
      return this.field_84.openInputStream();
   }

   public final DataInputStream openDataInputStream() {
      return this.field_84.openDataInputStream();
   }

   public final OutputStream openOutputStream() {
      return this.field_84.openOutputStream();
   }

   public final DataOutputStream openDataOutputStream() {
      return this.field_84.openDataOutputStream();
   }

   // $FF: renamed from: a () java.util.Enumeration
   public final Enumeration method_0() {
      return this.field_84.list();
   }

   // $FF: renamed from: a () void
   public final void method_1() {
      this.field_84.create();
   }

   // $FF: renamed from: a () boolean
   public final boolean method_2() {
      return this.field_84.exists();
   }

   // $FF: renamed from: a (long) void
   public final void method_3(long var1) {
      this.field_84.truncate(0L);
   }
}
