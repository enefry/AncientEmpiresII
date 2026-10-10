package a.a;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Vector;

// $FF: renamed from: a.a.b
public final class ChainedEnumeration implements Enumeration {
   // $FF: renamed from: a java.util.Enumeration
   private Enumeration field_87;
   // $FF: renamed from: a java.util.Vector
   private Vector field_88 = new Vector();
   // $FF: renamed from: a int
   private int field_89;
   // $FF: renamed from: b int
   private int field_90;

   public ChainedEnumeration() {
      super();
      EmptyEnumeration var1 = new EmptyEnumeration(this);
      this.field_88.addElement(var1);
      this.field_87 = var1;
      this.field_90 = 0;
      this.field_89 = 0;
   }

   // $FF: renamed from: a (java.util.Enumeration) void
   public final void method_64(Enumeration var1) {
      if (var1 != null && var1.hasMoreElements()) {
         this.field_88.addElement(var1);
         this.field_90 = this.field_88.size() - 1;
      }
   }

   public final boolean hasMoreElements() {
      return this.field_89 < this.field_90 ? true : this.field_87.hasMoreElements();
   }

   public final Object nextElement() {
      while(!this.field_87.hasMoreElements()) {
         if (++this.field_89 > this.field_90) {
            throw new NoSuchElementException();
         }

         this.field_87 = (Enumeration)this.field_88.elementAt(this.field_89);
         this = this;
      }

      return this.field_87.nextElement();
   }
}
