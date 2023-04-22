package lineales.estaticas;

public class Cola {
    private static final int TAMANIO = 10;
    private Object[] arreglo;
    private int frente;
    private int fin;

    public Cola(){
        this.arreglo = new Object[TAMANIO];
        this.frente = 0;
        this.fin = 0;
    }

    public boolean poner(Object elemento){
        boolean exito=false;
        if(!((fin+1)==frente)){
            this.arreglo[fin] = elemento;
            fin = (fin+1) % TAMANIO;
            exito = true;
        }
        return exito;
    }

    public boolean sacar(){
        boolean exito = false;
        if(!this.esVacia()){
            this.arreglo[frente] = null;
            frente = (frente + 1) % TAMANIO;
            exito = true;
        }
        return exito;
    }

    public Object obtenerFrente(){
        Object elemento=null;
        if(!this.esVacia()){
            elemento = this.arreglo[frente];
        }
        return elemento;
    }

    public void vaciar(){
        while(!this.esVacia()){
            this.arreglo[frente] = null;
            frente = (frente + 1) % TAMANIO;
        }
    }

    public boolean esVacia(){
        return this.frente == this.fin;
    }

    public String toString (){
        String resultado="";
        int i=this.frente;
        while(i!=this.fin){
            if (((i+1)%TAMANIO)==fin) {
                resultado = resultado + this.arreglo[i];
            } else {
                resultado = resultado + this.arreglo[i] + ", ";
            }
            i = (i+1) % TAMANIO;
        }
        resultado = "[" + resultado + "]";
        return resultado;
    }

    public Cola clone(){
        Cola colaNueva = new Cola();
        colaNueva.frente = this.frente;
        while(colaNueva.fin!=this.fin){
            colaNueva.arreglo[colaNueva.fin] = this.arreglo[colaNueva.fin];
            colaNueva.fin = (colaNueva.fin + 1) % TAMANIO;
        }
        return colaNueva;
    }

}
