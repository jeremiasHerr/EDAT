package jerarquicas.dinamicas;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;
public class ArbolBin {

    private NodoArbol raiz;

    public ArbolBin() {
        this.raiz = null;
    }

    public Lista listarNiveles(){
        Lista listaPorNiveles = new Lista();
        if(this.raiz!=null){
            Cola colaAux = new Cola();
            colaAux.poner(this.raiz);
            while(!colaAux.esVacia()){
                NodoArbol nodoActual;
                nodoActual = (NodoArbol) colaAux.obtenerFrente();
                listaPorNiveles.insertar(nodoActual.getElem(), listaPorNiveles.longitud()+1);
                colaAux.sacar();
                if(nodoActual.getIzquierdo()!=null){
                    colaAux.poner(nodoActual.getIzquierdo());
                }
                if(nodoActual.getDerecho()!=null){
                    colaAux.poner(nodoActual.getDerecho());
                }
            }
        }
        return listaPorNiveles;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            NodoArbol nPadre = obtenerNodo(this.raiz, elemPadre);
            if (nPadre != null) {
                if ((lugar == 'I' || lugar == 'i') && nPadre.getIzquierdo() == null) {
                    nPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else if ((lugar == 'D' || lugar == 'd') && nPadre.getDerecho() == null) {
                    nPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                } else {
                    exito = false;
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    public Object padre(Object buscado) {
        Object elemento = null;
        if (this.raiz != null) {
            if (this.raiz.getElem() != buscado) {
                elemento = obtenerPadre(this.raiz, buscado);
            }
        }
        return elemento;
    }

    private Object obtenerPadre(NodoArbol n, Object buscado) {
        Object resultado = null;
        if (n != null) {
            if (n.getIzquierdo() != null && resultado == null) {
                if (n.getIzquierdo().getElem() == (buscado)) {
                    resultado = n.getElem();
                }
            }
            if (n.getDerecho() != null && resultado == null) {
                if (n.getDerecho().getElem() == (buscado)) {
                    resultado = n.getElem();
                }
            }
            if (resultado == null) {
                resultado = obtenerPadre(n.getDerecho(), buscado);
                if (resultado == null) {
                    resultado = obtenerPadre(n.getIzquierdo(), buscado);
                }
            }
        }
        return resultado;
    }

    public Lista listarPreorden() {
        Lista listaPre = new Lista();
        listarPreOrdenAux(this.raiz, listaPre);
        return listaPre;
    }

    private void listarPreOrdenAux(NodoArbol nodo, Lista lis) {
        if (nodo != null) {
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
            listarPreOrdenAux(nodo.getIzquierdo(), lis);
            listarPreOrdenAux(nodo.getDerecho(), lis);
        }
    }

    private NodoArbol obtenerNodo(NodoArbol n, Object buscado) {
        NodoArbol resultado = null;
        if (n != null) {
            if (n.getElem().equals(buscado)) {
                resultado = n;
            } else {
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
                if (resultado == null) {
                    resultado = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    public int altura() {
        int resultado = -1;
        if (this.raiz != null) {
            resultado = calcAltura(this.raiz);
        }
        return resultado;
    }

    private int calcAltura(NodoArbol nAux) {
        int altura = 0, izq = 0, der = 0;
        if (nAux != null) {
            if (nAux.getIzquierdo() != null) {
                izq = calcAltura(nAux.getIzquierdo()) + 1;
            }
            if (nAux.getDerecho() != null) {
                der = calcAltura(nAux.getDerecho()) + 1;
            }
            altura = Math.max(izq, der);
        }
        return altura;
    }

    public void vaciar(){
        this.raiz = null;
    }

    public int nivel(Object elemento) {
        int resultado = -1;
        resultado = buscarNivelElemento(this.raiz, elemento, 0);
        return resultado;
    }

    private int buscarNivelElemento(NodoArbol nAux, Object buscado, int nivel) {
        int resultado = -1;
        if (nAux != null) {
            if (nAux.getElem() != buscado) {
                resultado = buscarNivelElemento(nAux.getIzquierdo(), buscado, nivel + 1);
                if (resultado == -1) {
                    resultado = buscarNivelElemento(nAux.getDerecho(), buscado, nivel + 1);
                }
            } else {
                resultado = nivel;
            }
        }
        return resultado;
    }

    public Lista listarPosorden() {
        Lista lista = new Lista();
        recorrerPosorden(this.raiz, lista);
        return lista;
    }

    private void recorrerPosorden(NodoArbol nodo, Lista lista) {
        if (nodo != null) {
            recorrerPosorden(nodo.getIzquierdo(), lista);
            recorrerPosorden(nodo.getDerecho(), lista);
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
        }
    }

    public Lista listarInorden() {
        Lista lista = new Lista();
        recorrerInorden(this.raiz, lista);
        return lista;
    }

    private void recorrerInorden(NodoArbol nodo, Lista lista) {
        if (nodo != null) {
            recorrerInorden(nodo.getIzquierdo(), lista);
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            recorrerInorden(nodo.getDerecho(), lista);
        }
    }
}