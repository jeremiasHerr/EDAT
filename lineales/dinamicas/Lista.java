package lineales.dinamicas;
public class Lista {
    private Nodo cabecera;
    private int laLongitud=0;

    public Lista(){
        cabecera = null;
    }

    public boolean insertar(Object elemento, int posicion){
        boolean exito=false;
        if(1<=posicion && posicion<=laLongitud+1){
            exito=true;
            laLongitud++;
            if(posicion==1){
                this.cabecera = new Nodo(elemento, this.cabecera);
            } else {
                Nodo aux = this.cabecera;
                int i=1;
                while(i<posicion-1){
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo nuevo = new Nodo(elemento, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
        }
        return exito;
    }

    public boolean eliminar(int posicion){
        boolean exito=false;
        if(!this.esVacia() && 1<=posicion && posicion<laLongitud){
            exito = true;
            if(posicion == 1){
                this.cabecera = this.cabecera.getEnlace();
            } else {
                Nodo aux = this.cabecera;
                int i=1;
                while(i<posicion-1){
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
        }
        return exito;
    }

    public Object recuperar(int posicion){
        Object elemPos=null;
        if(1<=posicion && posicion<=laLongitud){
            Nodo aux = this.cabecera;
            int i=1;
            while(i<posicion){
                aux = aux.getEnlace();
                i++;
            }
            elemPos = aux.getElem();
        }
        return elemPos;
    }

    public void vaciar(){
        this.cabecera = null;
    }

    public int localizar(Object elemBuscado){
        int posResultado = -1;
        Nodo aux = this.cabecera;
        int i=1;
        while(i<=laLongitud){
            if(aux.getElem()==elemBuscado){
                posResultado=i;
                i = laLongitud + 1;
            }
            i++;
        }
        return posResultado;
    }

    public String toString(){
        String acumulador="";
        if(!this.esVacia()){
            Nodo aux = this.cabecera;
            acumulador = aux.getElem().toString();
            aux = aux.getEnlace();
            for (int i = 2; i <= laLongitud; i++) {
                if(aux.getElem()==null){
                    acumulador = acumulador + ", " +"null";
                } else {
                    acumulador = acumulador + ", " +aux.getElem().toString();
                }
                aux = aux.getEnlace();
            }
            acumulador = "[" + acumulador + "]";
        }
        return acumulador;
    }

    public int longitud(){
        return laLongitud;
    }

    public Lista clone(){
        Lista listaClon = new Lista();
        if(!this.esVacia()){
            listaClon.laLongitud = this.laLongitud;
            Nodo aux = this.cabecera;
            listaClon.cabecera = new Nodo(aux.getElem(), listaClon.cabecera);
            aux = aux.getEnlace();
            Nodo aux2 = listaClon.cabecera;
            for (int j = 1; j < laLongitud; j++) {
                aux2.setEnlace(new Nodo(aux.getElem(), null));
                aux = aux.getEnlace();
                aux2 = aux2.getEnlace();
            }
            
        }
        return listaClon;
    }

    public boolean esVacia(){
        return cabecera == null;
    }

}
