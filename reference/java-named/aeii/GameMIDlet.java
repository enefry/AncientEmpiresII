package aeii;

import javax.microedition.midlet.MIDlet;

public class GameMIDlet extends MIDlet {
   // $FF: renamed from: a aeii.Main
   public static GameMIDlet field_628;
   // $FF: renamed from: a aeii.c
   private static GameCanvas field_629;

   public GameMIDlet() {
      super();
   }

   public final void startApp() {
      if (field_628 == null) {
         field_628 = this;
         field_629 = new GameCanvas(this);
      }

   }

   public final void destroyApp(boolean var1) {
      if (field_629 != null) {
         field_629.method_264();
      }

      field_629 = null;
      field_628 = null;
   }

   public final void pauseApp() {
   }
}
