package aeii;

import a.a.ChainedEnumeration;
import a.a.a.GameFileConnection;
import a.a.a.Sprite;
import a.a.a.FileSystemSupport;
import java.util.Enumeration;
import java.util.Vector;

// $FF: renamed from: aeii.b
public final class FileBrowser {
   // $FF: renamed from: a java.lang.String
   private String field_91 = null;

   public FileBrowser() {
      super();
   }

   // $FF: renamed from: a (java.lang.String, boolean) java.util.Enumeration
   public final Enumeration method_65(String var1, boolean var2) {
      try {
         if (this.field_91 == null) {
            return FileSystemSupport.method_63();
         } else {
            GameFileConnection var6;
            Enumeration var3 = (var6 = (GameFileConnection)Sprite.method_5("file:///" + this.field_91, 1)).method_0();
            var6.close();
            Vector var7 = new Vector();
            if (var1 == null) {
               var1 = "";
            }

            while(var3.hasMoreElements()) {
               String var4;
               if ((var4 = (String)var3.nextElement()).endsWith("/") || var4.toLowerCase().endsWith(var1)) {
                  var7.addElement(var4);
               }
            }

            return var7.elements();
         }
      } catch (Exception var5) {
         return null;
      }
   }

   // $FF: renamed from: a (java.lang.String) void
   public final void method_66(String var1) {
      if (var1 != null && !var1.equals("..")) {
         if (this.field_91 != null) {
            this.field_91 = this.field_91 + var1;
         } else {
            this.field_91 = var1;
         }
      } else {
         if (this.field_91 != null) {
            this.field_91 = this.field_91.substring(0, this.field_91.length() - 1);
            int var2;
            if ((var2 = this.field_91.lastIndexOf(47)) >= 0) {
               this.field_91 = this.field_91.substring(0, var2 + 1);
               return;
            }

            this.field_91 = null;
         }

      }
   }

   // $FF: renamed from: a (java.lang.String) a.a.a.c
   public final GameFileConnection method_67(String var1) {
      try {
         return (GameFileConnection)Sprite.method_4("file:///" + this.field_91 + var1);
      } catch (Exception var2) {
         return null;
      }
   }

   // $FF: renamed from: a () java.lang.String
   public final String method_68() {
      return this.field_91;
   }

   // $FF: renamed from: a (java.util.Enumeration) java.util.Enumeration
   public static Enumeration method_69(Enumeration var0) {
      if (var0 == null) {
         return null;
      } else {
         Vector var1 = new Vector();
         Vector var2 = new Vector();

         while(var0.hasMoreElements()) {
            String var3;
            if ((var3 = (String)var0.nextElement()).endsWith("/")) {
               var1.addElement(var3);
            } else {
               var2.addElement(var3);
            }
         }

         if (var1.size() > 0) {
            method_70(var1, 0, var1.size() - 1);
         }

         if (var2.size() > 0) {
            method_70(var2, 0, var2.size() - 1);
         }

         (var0 = new ChainedEnumeration()).method_64(var1.elements());
         var0.method_64(var2.elements());
         return var0;
      }
   }

   // $FF: renamed from: a (java.util.Vector, int, int) void
   private static void method_70(Vector var0, int var1, int var2) {
      while(true) {
         String var3 = ((String)var0.elementAt((var2 - var1) / 2 + var1)).toLowerCase();
         int var5 = var1;
         int var6 = var2;

         while(var5 <= var6) {
            while(((String)var0.elementAt(var5)).toLowerCase().compareTo(var3) < 0 && var5 <= var2) {
               ++var5;
            }

            while(((String)var0.elementAt(var6)).toLowerCase().compareTo(var3) > 0 && var6 >= var1) {
               --var6;
            }

            if (var5 <= var6) {
               String var4 = (String)var0.elementAt(var5);
               var0.setElementAt(var0.elementAt(var6), var5);
               var0.setElementAt(var4, var6);
               ++var5;
               --var6;
            }
         }

         if (var6 > var1) {
            method_70(var0, var1, var6);
         }

         if (var5 >= var2) {
            return;
         }

         var1 = var5;
         var0 = var0;
      }
   }

   // $FF: renamed from: a (java.util.Enumeration, java.lang.String[]) java.lang.String[]
   public static String[] method_71(Enumeration var0, String[] var1) {
      Vector var2 = new Vector();
      if (var1 != null) {
         for(int var3 = 0; var3 < var1.length; ++var3) {
            var2.addElement(var1[var3]);
         }
      }

      while(var0.hasMoreElements()) {
         var2.addElement(var0.nextElement());
      }

      String[] var4 = new String[var2.size()];
      var2.copyInto(var4);
      return var4;
   }
}
