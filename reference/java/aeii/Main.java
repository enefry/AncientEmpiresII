package aeii;

import javax.microedition.midlet.MIDlet;

public class Main extends MIDlet {
   public static Main a;
   private static c a;

   public Main() {
      super();
   }

   public final void startApp() {
      if (a == null) {
         a = this;
         a = new c(this);
      }

   }

   public final void destroyApp(boolean var1) {
      if (a != null) {
         a.c();
      }

      a = null;
      a = null;
   }

   public final void pauseApp() {
   }
}
