package a.a.a;

import java.util.Enumeration;
import javax.microedition.io.file.FileSystemRegistry;

// $FF: renamed from: a.a.a.a
public final class FileSystemSupport {
   // $FF: renamed from: a int
   private static int field_86 = -1;

   public FileSystemSupport() {
      super();
   }

   // $FF: renamed from: a () int
   static int method_61() {
      if (field_86 < 0) {
         if (method_62("javax.microedition.io.file.FileConnection")) {
            field_86 = 1;
         } else if (method_62("com.siemens.mp.io.file.FileConnection")) {
            field_86 = 2;
         } else if (method_62("com.motorola.io.FileConnection")) {
            field_86 = 3;
         } else if (method_62("com.motorola.io.file.FileConnection")) {
            field_86 = 4;
         } else {
            field_86 = 0;
         }
      }

      return field_86;
   }

   // $FF: renamed from: a (java.lang.String) boolean
   private static boolean method_62(String var0) {
      try {
         return Class.forName(var0) != null;
      } catch (Throwable var1) {
         return false;
      }
   }

   // $FF: renamed from: a () java.util.Enumeration
   public static Enumeration method_63() {
      switch (method_61()) {
         case 1:
            return FileSystemRegistry.listRoots();
         case 2:
            return com.siemens.mp.io.file.FileSystemRegistry.listRoots();
         case 3:
            return com.motorola.io.FileSystemRegistry.listRoots();
         case 4:
            return com.motorola.io.file.FileSystemRegistry.listRoots();
         default:
            return null;
      }
   }
}
