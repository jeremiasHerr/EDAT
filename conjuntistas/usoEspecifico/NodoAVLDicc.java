package conjuntistas.usoEspecifico;

public class NodoAVLDicc {
    Comparable clave;
    Object dato;
    int altura;
    NodoAVLDicc hijoIzquierdo;
    NodoAVLDicc hijoDerecho;

    public NodoAVLDicc(Comparable unaClave, Object unDato, NodoAVLDicc unHijoIzq, NodoAVLDicc unHijoDer){
        this.clave = unaClave;
        this.dato = unDato;
        this.hijoIzquierdo = unHijoIzq;
        this.hijoDerecho = unHijoDer;
        recalcularAltura();
    }

    public void setDerecho(NodoAVLDicc unHijoDer){
        this.hijoDerecho = unHijoDer;
    }

    public void setIzquierdo(NodoAVLDicc unHijoIzq){
        this.hijoIzquierdo = unHijoIzq;
    }

    public NodoAVLDicc getDerecho(){
        return this.hijoDerecho;
    }

    public NodoAVLDicc getIzquierdo(){
        return this.hijoIzquierdo;
    }

    public Object getDato(){
        return this.dato;
    }

    public Comparable getClave(){
        return this.clave;
    }

    public void setDato(Object unDato){
        this.dato = unDato;
    }
    
     public void setClave(Comparable unaClave){
            this.clave = unaClave;
    }
    
    public int getAltura() {
        return this.altura;
    }

    public void recalcularAltura(){
        int alturaIzq,alturaDer;
        if(hijoIzquierdo==null){
            alturaIzq=-1;
        } else {
            alturaIzq = hijoIzquierdo.getAltura();
        }
        if(hijoDerecho==null){
            alturaDer = -1;
        } else {
            alturaDer = hijoDerecho.getAltura();
        }
        altura = Math.max(alturaIzq, alturaDer)+1;
    }

}
