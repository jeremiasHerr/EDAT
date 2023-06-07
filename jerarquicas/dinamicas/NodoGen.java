package jerarquicas.dinamicas;
public class NodoGen {
    private Object elem;
    private NodoGen hijoIzq;
    private NodoGen hermanoDer;

   public NodoGen(Object elem, NodoGen hijoIzq, NodoGen hermanoDer) {
       this.elem = elem;
       this.hijoIzq = hijoIzq;
       this.hermanoDer = hermanoDer;
   }

   public Object getElemento() {
       return elem;
   }

   public void setElem(Object elem) {
       this.elem = elem;
   }

   public NodoGen getHijoIzq() {
       return hijoIzq;
   }

   public void setHijoIzq(NodoGen hijoIzq) {
       this.hijoIzq = hijoIzq;
   }
   public NodoGen getHermanoDer() {
       return hermanoDer;
   }

   public void setHermanoDer(NodoGen hermanoDer) {
       this.hermanoDer = hermanoDer;
   }  
}
