package lineales.dinamicas;
/*
 *  Autores
 *  Jeremias Ezequiel Herrera, FAI 3297
 *  Analia Maylen Gomez, FAI 3362
 *  Giulianna Vicentino, FAI 3234
 */
public class Pila {
    private Nodo tope;

    public Pila(){
        this.tope = null;
    }

    public boolean apilar(Object unElem){
        Nodo nuevoLocal = new Nodo(unElem, tope);
        this.tope = nuevoLocal;
        return true;
    }

    public boolean desapilar(){
        boolean exito;
        if(this.tope==null){
            exito = false;
        } else {
            this.tope = this.tope.getEnlace();
            exito = true;
        }
        return exito;
    }

    public Object obtenerTope(){
        Object elem;
        if(this.tope == null){
            elem = null;
        } else {
            elem = this.tope.getElem();
        }
        return elem;
    }

    public boolean esVacia(){
        boolean exito=false;
        if(this.tope==null){
            exito = true;
        }
        return exito;
    }

    public void vaciar(){
        this.tope = null;
    }

    public String toString(){
        String acum="";
        Nodo aux = this.tope;
        if(this.tope!=null){
            do{
                acum = aux.getElem().toString() + acum;
                aux = aux.getEnlace();
                if(aux!=null){
                    acum = "," + acum;
                }
            }while(aux!=null);
            acum = "[" + acum + "]";
        }
        return acum;
    }

    private void clonarRecur (Pila pilaAux, Nodo nodoAux){
        if(nodoAux!=null){
            clonarRecur(pilaAux, nodoAux.getEnlace());
            Nodo nodoTemporal = new Nodo(nodoAux.getElem(),pilaAux.tope);
            pilaAux.tope = nodoTemporal;
        }
    }

    public Pila clone (){
        Pila pilaNueva = new Pila();
        clonarRecur(pilaNueva, this.tope);
        return pilaNueva;

    }

}
