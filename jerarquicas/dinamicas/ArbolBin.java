package jerarquicas.dinamicas;
public class ArbolBin {
    private NodoArbol raiz;

    public ArbolBin(){
        raiz = null;
    }

    public int altura(){
        int resultado = -1;
        if(this.raiz!=null){
            resultado = calcAltura(raiz);
        }
        return resultado;
    }

    private int calcAltura(NodoArbol nAux){
        int altura=0,izq=0,der=0;
            if(nAux.getIzquierdo()!=null){
                izq = calcAltura(nAux.getIzquierdo()) + 1;
            }
            if(nAux.getDerecho()!=null){
                der = calcAltura(nAux.getDerecho()) + 1;
            }
            altura = Math.max(der,izq);
        return altura;
    }

    public boolean esVacio(){
        return raiz==null;
    }

    public Object padre(Object buscado){
        Object resultado=null;
        if(this.raiz!=null){
            if(this.raiz.getElemento()!=buscado){
                resultado = buscarPadre(this.raiz, buscado);
            }
        }
        return resultado;
    }
    
    private Object buscarPadre(NodoArbol nAux, Object buscado){
        Object resultado = null;
        if(nAux!=null){
            if(nAux.getIzquierdo()!=null){
                if(nAux.getIzquierdo().getElemento()==buscado){
                    resultado = nAux.getElemento();
                }
            }
            if(nAux.getDerecho()!=null && resultado==null){
                if(nAux.getDerecho().getElemento()==buscado){
                    resultado = nAux.getElemento();
                }
            }   
            if(resultado==null){
                resultado = buscarPadre(nAux.getIzquierdo(), buscado);
                if(resultado==null){
                    resultado = buscarPadre(nAux.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }
    public boolean insertar(Object unElemento, Object elemPadre, boolean posicion){
        //posicion = true, izquierdo
        //posicion = false, derecho
        boolean exito = true;
        if(raiz == null){
            this.raiz = new NodoArbol(unElemento, null, null);
        } else {
            NodoArbol nPadre = obtenerNodo(this.raiz, elemPadre);
            if(nPadre!=null){
                if(posicion && nPadre.getIzquierdo()==null){
                    nPadre.setIzquierdo(new NodoArbol(unElemento, null, null));
                } else if (!posicion && nPadre.getDerecho()==null){
                    nPadre.setDerecho(new NodoArbol(unElemento, null, null));
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoArbol obtenerNodo(NodoArbol nAux, Object buscado){
        NodoArbol resultado = null;
        if(nAux!=null){
            if(nAux.getElemento() == buscado){
                resultado = nAux;
            } else {
                resultado = obtenerNodo(nAux.getIzquierdo(), buscado);
                if(resultado==null){
                    resultado = obtenerNodo(nAux.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

}
