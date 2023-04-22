package lineales.estaticas;
/*
 *  Autores
 *  Jeremias Ezequiel Herrera, FAI 3297
 *  Analia Maylen Gomez, FAI 3362
 *  Giulianna Vicentino, FAI 3234
 */
public class Pila {
    private Object[] arreglo;
    private int tope;
    private static final int TAMANIO=10;


    public Pila(){
        this.arreglo = new Object[TAMANIO];
        this.tope = -1;
    }

    public boolean apilar (Object nuevoElemento) {
        boolean exito;
   
        if (this.tope+1>=TAMANIO) {
            exito=false;
        } else {
            this.tope++;
            this.arreglo[tope]=nuevoElemento;
            exito=true;
        }
        return exito;
    } 

    public boolean desapilar(){
        boolean exito;
        if(this.tope>=0){
            this.arreglo[tope] = null;
            this.tope--;
            exito = true;
        } else {
            exito = false;
        }
        return exito;
    }

    public Object obtenerTope(){
        Object elemento;
        if(tope>=0){
            elemento = this.arreglo[tope];
        } else {
            elemento = null;
        }
        return elemento;
    }

    public boolean esVacia(){
        boolean vacia = false;
        if(tope<0){
            vacia = true;
        }
        return vacia;
    }

    public void vaciar(){
        //this.arreglo = null;
        while(tope>-1){
            this.arreglo[tope] = null;
            tope--;
        }
    }

    public Pila clone(){
        Pila nuevaPila = new Pila();
        for (int i = 0; i <= this.tope; i++) {
            nuevaPila.arreglo[i] = this.arreglo[i];
        }
        nuevaPila.tope = this.tope;
        return nuevaPila;
    }

    public String toString() {
        String cadena="";

        for (int i=0; i<=tope; i++) {
            cadena=cadena+arreglo[i];
        }
        return cadena;
    }
}