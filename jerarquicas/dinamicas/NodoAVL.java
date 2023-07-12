package jerarquicas.dinamicas;

public class NodoAVL {
    private Comparable elem;
    private int altura;
    private NodoAVL izquierdo;
    private NodoAVL derecho;

    public NodoAVL (Comparable elemento, NodoAVL hijoIzq, NodoAVL hijoDer){
        this.elem = elemento;
        this.izquierdo = hijoIzq;
        this.derecho = hijoDer;
    }

    public int getAltura() {
        recalcularAltura();
        return this.altura;
    }

    public void recalcularAltura(){
        if(izquierdo!=null && derecho!=null){
            this.altura = izquierdo.getAltura() - derecho.getAltura();
        } else if(izquierdo!=null && derecho==null){
            this.altura = izquierdo.getAltura() - (-1);
        } else if(izquierdo==null && derecho!=null){
            this.altura = -1 - derecho.getAltura();
        } else {
            this.altura = 0;
        }
    }

    public Comparable getElem(){
        return this.elem;
    }

    public void setElem(Comparable elemento){
        this.elem = elemento;
    }

    public NodoAVL getIzquierdo(){
        return this.izquierdo;
    }

    public NodoAVL getDerecho(){
        return this.derecho;
    }

    public void setIzquierdo(NodoAVL hijoIzq){
        this.izquierdo = hijoIzq;
    }

    public void setDerecho(NodoAVL hijoDer){

    }

}