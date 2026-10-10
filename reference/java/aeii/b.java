package aeii;

import java.util.Enumeration;
import java.util.Vector;

public final class b {
   private String a = null;

   public b() {
      super();
   }

   public final Enumeration a(String var1, boolean var2) {
      try {
         if (this.a == null) {
            return a.a.a.a.a();
         } else {
            a.a.a.c var6;
            Enumeration var3 = (var6 = (a.a.a.c)a.a.a.g.a("file:///" + this.a, 1)).a();
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

   public final void a(String var1) {
      if (var1 != null && !var1.equals("..")) {
         if (this.a != null) {
            this.a = this.a + var1;
         } else {
            this.a = var1;
         }
      } else {
         if (this.a != null) {
            this.a = this.a.substring(0, this.a.length() - 1);
            int var2;
            if ((var2 = this.a.lastIndexOf(47)) >= 0) {
               this.a = this.a.substring(0, var2 + 1);
               return;
            }

            this.a = null;
         }

      }
   }

   public final a.a.a.c a(String var1) {
      try {
         return (a.a.a.c)a.a.a.g.a("file:///" + this.a + var1);
      } catch (Exception var2) {
         return null;
      }
   }

   public final String a() {
      return this.a;
   }

   public static Enumeration a(Enumeration var0) {
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
            a(var1, 0, var1.size() - 1);
         }

         if (var2.size() > 0) {
            a(var2, 0, var2.size() - 1);
         }

         (var0 = new a.a.b()).a(var1.elements());
         var0.a(var2.elements());
         return var0;
      }
   }

   private static void a(Vector var0, int var1, int var2) {
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
            a(var0, var1, var6);
         }

         if (var5 >= var2) {
            return;
         }

         var1 = var5;
         var0 = var0;
      }
   }

   public static String[] a(Enumeration var0, String[] var1) {
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
