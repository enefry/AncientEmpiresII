package a.a.a;

import java.util.Enumeration;
import javax.microedition.io.file.FileSystemRegistry;

public final class a {
   private static int a = -1;

   public a() {
      super();
   }

   static int a() {
      if (a < 0) {
         if (a("javax.microedition.io.file.FileConnection")) {
            a = 1;
         } else if (a("com.siemens.mp.io.file.FileConnection")) {
            a = 2;
         } else if (a("com.motorola.io.FileConnection")) {
            a = 3;
         } else if (a("com.motorola.io.file.FileConnection")) {
            a = 4;
         } else {
            a = 0;
         }
      }

      return a;
   }

   private static boolean a(String var0) {
      try {
         return Class.forName(var0) != null;
      } catch (Throwable var1) {
         return false;
      }
   }

   public static Enumeration a() {
      switch (a()) {
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
