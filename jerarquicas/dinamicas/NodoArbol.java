package jerarquicas.dinamicas;

public class NodoArbol {
    private Object elemento;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    public NodoArbol (Object elemento, NodoArbol izquierdo, NodoArbol derecho){
        this.elemento = elemento;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }
    
    public Object getElem(){
        return this.elemento;
    }

    public NodoArbol getIzquierdo(){
        return this.izquierdo;
    }

    public NodoArbol getDerecho(){
        return this.derecho;
    }

    public void setElemento(Object elemento){
        this.elemento = elemento;
    }

    public void setIzquierdo(NodoArbol nuevoIzquierdo){
        this.izquierdo = nuevoIzquierdo;
    }

    public void setDerecho(NodoArbol nuevoDerecho){
        this.derecho = nuevoDerecho;
    }

}
