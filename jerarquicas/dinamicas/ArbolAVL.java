package jerarquicas.dinamicas;

public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL (){
        this.raiz = null;
    }

    public boolean insertar (Comparable elemento){
        boolean exito=false;
        if(this.raiz!=null){
            exito = insertarAux(this.raiz, elemento);
        } else {
            exito = true;
            raiz = new NodoAVL(elemento, null,null);
        }
        return exito;
    }

    private boolean insertarAux(NodoAVL nAux, Comparable elemento){
        boolean exito;
        if(nAux.getElem().compareTo(elemento)==0){
            exito = false;
        } else if(nAux.getElem().compareTo(elemento)<0){
            if(nAux.getDerecho()!=null){
                exito = insertarAux(nAux.getDerecho(),elemento);
            } else {
                nAux.setDerecho(new NodoAVL(elemento,null,null));
                exito = true;
            }
        } else {
            if(nAux.getIzquierdo()!=null){
                exito = insertarAux(nAux.getIzquierdo(),elemento);
            } else {
                nAux.setIzquierdo(new NodoAVL(elemento,null,null));
                exito = true;
            }
        }
        if(balance(nAux)==-2 || balance(nAux)==2){

        }
        return exito;
    }

    public int balance(NodoAVL nodo){
        return nodo.getIzquierdo().getAltura() - nodo.getDerecho().getAltura();
    }

    public void balancear(NodoAVL nodo){

    }


}
