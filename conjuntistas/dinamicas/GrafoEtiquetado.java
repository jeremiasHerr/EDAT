package conjuntistas.dinamicas;
import lineales.dinamicas.Lista;
public class GrafoEtiquetado {
    NodoVert inicio;

    public GrafoEtiquetado() {
        this.inicio = null;
    }

    public boolean existeCamino(Object origen, Object destino){
        boolean exito = false;
        if (!origen.equals(destino) && this.inicio != null) {
             NodoVert nOrigen = ubicarVertice(origen);
            NodoVert nDestino = ubicarVertice(destino);
            if (nOrigen != null && nDestino != null) {
                Lista visitados = new Lista();
                exito = existeCaminoAux(nOrigen, destino, visitados);
            }
        }
       return exito;
    }

    private boolean existeCaminoAux (NodoVert nAux, Object destino, Lista visitados){
        boolean exito = false;
        if(nAux!=null){
            if(nAux.getElem().equals(destino)){
                exito = true;
            } else {
                visitados.insertar(nAux.getElem(), visitados.longitud()+1);
                NodoAdy aux = nAux.getPrimerAdy();
                while(!exito && aux!=null){
                    if(visitados.localizar(aux.getVertice().getElem())<0){
                        exito = existeCaminoAux(aux.getVertice(), destino, visitados);
                    }
                    aux = aux.getSigAdyacente();
                }
            }
        }
        return exito;
    }

    public void vaciar(){
        this.inicio = null;
    }

    public boolean esVacio(){
        return this.inicio!=null;
    }

    public boolean existeArco (Object origen, Object destino){
        boolean encontrado = false;
        if (!origen.equals(destino) && this.inicio != null) {
            NodoVert nOrigen = ubicarVertice(origen);
            NodoVert nDestino = ubicarVertice(destino);
            if (nOrigen != null && nDestino != null) {
                if (nOrigen.getPrimerAdy().getVertice() == nDestino) {
                    nOrigen.setPrimerAdy(nOrigen.getPrimerAdy().getSigAdyacente());
                    encontrado = true;
                } else {
                    NodoAdy aux = nOrigen.getPrimerAdy();
                    while(aux!=null && !encontrado){
                        encontrado = aux.getVertice().getElem().equals(nDestino.getElem());
                        aux = aux.getSigAdyacente();
                    }
                }
            }
        }
        return encontrado;
    }

    public boolean eliminarArco(Object origen, Object destino) {
        boolean exito = false, encontrado = false;
        if (!origen.equals(destino) && this.inicio != null) {
            NodoVert nOrigen = ubicarVertice(origen);
            NodoVert nDestino = ubicarVertice(destino);
            if (nOrigen != null && nDestino != null) {
                // Si es el primer adyacente lo borro, sino busco en todos los adyacentes si
                // existe el arco
                if (nOrigen.getPrimerAdy().getVertice() == nDestino) {
                    nOrigen.setPrimerAdy(nOrigen.getPrimerAdy().getSigAdyacente());
                    encontrado = true;
                } else {
                    NodoAdy aux = nOrigen.getPrimerAdy();
                    boolean salir = false;
                    while (aux != null && !salir) {
                        if (aux.getSigAdyacente().getVertice() == nDestino) {
                            salir = true;
                        } else {
                            aux = aux.getSigAdyacente();
                        }
                    }
                    // Si aux es distinto de null significa que lo encontró al adyacente
                    if (aux != null) {
                        aux.setSigAdyacente(aux.getSigAdyacente().getSigAdyacente());
                        encontrado = true;
                    }
                }
                // Ahora queda eliminarlo desde el destino al origen, ya que es un grafo no dirigido y los
                // arcos van en ambos sentidos
                NodoAdy aux2 = nDestino.getPrimerAdy();
                boolean salir2 = false;
                if(encontrado){
                    if (nDestino.getPrimerAdy().getVertice() == nOrigen) {
                        nDestino.setPrimerAdy(nDestino.getPrimerAdy().getSigAdyacente());
                    } else {
                        while (aux2 != null && !salir2) {
                            if (aux2.getSigAdyacente().getVertice() == nOrigen) {
                                salir2 = true;
                            } else {
                                aux2 = aux2.getSigAdyacente();
                            }
                        }
                        // Si aux es distinto de null significa que lo encontró al adyacente
                        if (aux2 != null) {
                            aux2.setSigAdyacente(aux2.getSigAdyacente().getSigAdyacente());
                            exito = true;
                        }
                    }
                }
                
            }
        }
        return exito;
    }

    public boolean insertarArco(Object origen, Object destino, double unaEtiqueta) {
        boolean exito = false;
        if (!origen.equals(destino) && this.inicio != null) {
            NodoVert nOrigen = ubicarVertice(origen);
            NodoVert nDestino = ubicarVertice(destino);
            if (nOrigen != null && nDestino != null) {
                nOrigen.setPrimerAdy(new NodoAdy(nDestino, nOrigen.getPrimerAdy(), unaEtiqueta));
                nDestino.setPrimerAdy(new NodoAdy(nOrigen, nDestino.getPrimerAdy(), unaEtiqueta));
                exito = true;
            }
        }
        return exito;
    }

    public boolean existeVertice(Object unVertice) {
        boolean exito = false;
        if (inicio != null) {
            NodoVert aux = this.inicio;
            while (aux != null && !exito) {
                aux = aux.getSigVertice();
                exito = aux.getElem().equals(unVertice);
            }
        }
        return exito;
    }

    public boolean eliminarVertice(Object unVertice) {
        boolean exito = false;
        if (inicio != null) {
            if (inicio.getElem().equals(unVertice)) {
                this.inicio = inicio.getSigVertice();
            } else {
                NodoVert aux = this.inicio;
                while (aux != null && !exito) {
                    if (aux.getSigVertice().getElem().equals(unVertice)) {
                        aux.setSigVertice(aux.getSigVertice().getSigVertice());
                        exito = true;
                    } else {
                        aux = aux.getSigVertice();
                    }
                }
            }
        }
        return exito;
    }

    private NodoVert ubicarVertice(Object buscado) {
        // aux que se usa para ver si ya existe el nuevo vertice a ingresar
        NodoVert aux = this.inicio;
        while (aux != null && !aux.getElem().equals(buscado)) {
            aux = aux.getSigVertice();
        }
        return aux;
    }

    public boolean insertarVertice(Object nuevoVertice) {
        boolean exito = false;
        NodoVert aux = this.ubicarVertice(nuevoVertice);
        if (aux == null) {
            this.inicio = new NodoVert(nuevoVertice, this.inicio);
            exito = true;
        }
        return exito;
    }

}
