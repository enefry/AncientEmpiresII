package a.a;

import java.util.Enumeration;
import java.util.NoSuchElementException;

// $FF: renamed from: a.a.a
final class EmptyEnumeration implements Enumeration {
   EmptyEnumeration(ChainedEnumeration var1) {
      super();
   }

   public final boolean hasMoreElements() {
      return false;
   }

   public final Object nextElement() {
      throw new NoSuchElementException();
   }
}
