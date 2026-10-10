package a.a;

import java.util.Enumeration;
import java.util.NoSuchElementException;

final class a implements Enumeration {
   a(b var1) {
      super();
   }

   public final boolean hasMoreElements() {
      return false;
   }

   public final Object nextElement() {
      throw new NoSuchElementException();
   }
}
