package jerarquicas.dinamicas;
import lineales.dinamicas.Lista;
public class ArbolBin {
    private NodoArbol raiz;

    public ArbolBin() {
        raiz = null;
    }

    public Lista listarPosorden(){
        Lista listaPosorden = new Lista();
        listarPosordenRecur(this.raiz, listaPosorden);
        return listaPosorden;
    }

    private void listarPosordenRecur(NodoArbol nAux, Lista unaLista){
        if(nAux!=null){
            listarInordenRecur(nAux.getIzquierdo(), unaLista);
            listarInordenRecur(nAux.getDerecho(), unaLista);
            unaLista.insertar(nAux.getElemento(), unaLista.longitud()+1);
        }
    }

    public Lista listarInorden(){
        Lista listaInorden = new Lista();
        listarInordenRecur(this.raiz, listaInorden);
        return listaInorden;
    }

    private void listarInordenRecur(NodoArbol nAux, Lista unaLista){
        if(nAux!=null){
            listarInordenRecur(nAux.getIzquierdo(), unaLista);
            unaLista.insertar(nAux.getElemento(), unaLista.longitud()+1);
            listarInordenRecur(nAux.getDerecho(), unaLista);
        }
    }

    public Lista listarPreorden(){
        Lista listaPreorden = new Lista();
        listarPreordenRecur(this.raiz, listaPreorden);
        return listaPreorden;
    }
    
    private void listarPreordenRecur(NodoArbol nAux, Lista unaLista){
        if(nAux!=null){
            unaLista.insertar(nAux.getElemento(), unaLista.longitud()+1);
            listarPreordenRecur(nAux.getIzquierdo(), unaLista);
            listarPreordenRecur(nAux.getDerecho(), unaLista);
        }
    }

    public int altura() {
        int resultado = -1;
        if (this.raiz != null) {
            resultado = calcAltura(raiz);
        }
        return resultado;
    }

    public int nivel(Object elemento) {
        int resultado = -1;
        resultado = buscarNivelElemento(this.raiz, elemento, 0);
        return resultado;
    }

    private int buscarNivelElemento(NodoArbol nAux, Object buscado, int nivel) {
        int resultado = -1;
        if (nAux != null) {
            if (nAux.getElemento()!=buscado) {
                resultado = buscarNivelElemento(nAux.getIzquierdo(), buscado, nivel + 1);
                if (resultado==-1) {
                    resultado = buscarNivelElemento(nAux.getDerecho(), buscado, nivel + 1);
                }
            } else {
                resultado = nivel;
            }
        }
        return resultado;
    }

    private int calcAltura(NodoArbol nAux) {
        int altura = 0, izq = 0, der = 0;
        if (nAux.getIzquierdo() != null) {
            izq = calcAltura(nAux.getIzquierdo()) + 1;
        }
        if (nAux.getDerecho() != null) {
            der = calcAltura(nAux.getDerecho()) + 1;
        }
        altura = Math.max(der, izq);
        return altura;
    }

    public boolean esVacio() {
        return raiz == null;
    }

    public Object padre(Object buscado) {
        // busca el elemento padre del elemento pasado por parametro
        Object resultado = null;
        if (this.raiz != null) {
            if (this.raiz.getElemento() != buscado) {
                resultado = buscarPadre(this.raiz, buscado);
            }
        }
        return resultado;
    }

    private Object buscarPadre(NodoArbol nAux, Object buscado) {
        Object resultado = null;
        if (nAux != null) {
            if (nAux.getIzquierdo() != null) {
                if (nAux.getIzquierdo().getElemento() == buscado) {
                    resultado = nAux.getElemento();
                }
            }
            if (nAux.getDerecho() != null && resultado == null) {
                if (nAux.getDerecho().getElemento() == buscado) {
                    resultado = nAux.getElemento();
                }
            }
            if (resultado == null) {
                resultado = buscarPadre(nAux.getIzquierdo(), buscado);
                if (resultado == null) {
                    resultado = buscarPadre(nAux.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    public boolean insertar(Object unElemento, Object elemPadre, boolean posicion) {
        // posicion = true, izquierdo
        // posicion = false, derecho
        boolean exito = true;
        if (raiz == null) {
            this.raiz = new NodoArbol(unElemento, null, null);
        } else {
            NodoArbol nPadre = obtenerNodo(this.raiz, elemPadre);
            if (nPadre != null) {
                if (posicion && nPadre.getIzquierdo() == null) {
                    nPadre.setIzquierdo(new NodoArbol(unElemento, null, null));
                } else if (!posicion && nPadre.getDerecho() == null) {
                    nPadre.setDerecho(new NodoArbol(unElemento, null, null));
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoArbol obtenerNodo(NodoArbol nAux, Object buscado) {
        NodoArbol resultado = null;
        if (nAux != null) {
            if (nAux.getElemento() == buscado) {
                resultado = nAux;
            } else {
                resultado = obtenerNodo(nAux.getIzquierdo(), buscado);
                if (resultado == null) {
                    resultado = obtenerNodo(nAux.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

}
