package a.a;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Vector;

public final class b implements Enumeration {
   private Enumeration a;
   private Vector a = new Vector();
   private int a;
   private int b;

   public b() {
      super();
      a var1 = new a(this);
      this.a.addElement(var1);
      this.a = var1;
      this.b = 0;
      this.a = 0;
   }

   public final void a(Enumeration var1) {
      if (var1 != null && var1.hasMoreElements()) {
         this.a.addElement(var1);
         this.b = this.a.size() - 1;
      }
   }

   public final boolean hasMoreElements() {
      return this.a < this.b ? true : this.a.hasMoreElements();
   }

   public final Object nextElement() {
      while(!this.a.hasMoreElements()) {
         if (++this.a > this.b) {
            throw new NoSuchElementException();
         }

         this.a = (Enumeration)this.a.elementAt(this.a);
         this = this;
      }

      return this.a.nextElement();
   }
}
