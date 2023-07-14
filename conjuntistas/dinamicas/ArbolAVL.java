package conjuntistas.dinamicas;

import lineales.dinamicas.Lista;

public class ArbolAVL {
    private NodoAVL raiz;

    public Comparable maximoElem() {
        Comparable resultado = null;
        if (this.raiz != null) {
            NodoAVL aux = this.raiz;
            while (aux.getDerecho() != null) {
                aux = aux.getDerecho();
            }
            resultado = aux.getElem();
        }
        return resultado;
    }

    public Comparable minimoElem(){
        Comparable resultado = null;
        if (this.raiz != null) {
            NodoAVL aux = this.raiz;
            while (aux.getIzquierdo() != null) {
                aux = aux.getIzquierdo();
            }
            resultado = aux.getElem();
        }
        return resultado;
    }

    public void vaciar(){
        this.raiz=null;
    }

    public boolean esVacio(){
        return this.raiz!=null;
    }

    public ArbolAVL (){
        this.raiz = null;
    }

    public Lista listarRango(Comparable minimo, Comparable maximo){
        Lista resultado = new Lista();
        if(this.raiz!=null){
            listarRangoAux(this.raiz, minimo, maximo, resultado);
        }
        return resultado;
    }
@SuppressWarnings("unchecked")
    private void listarRangoAux(NodoAVL nAux, Comparable minimo, Comparable maximo, Lista resultado){
        if(nAux!=null){
            if(nAux.getElem().compareTo(minimo)>=0 && nAux.getElem().compareTo(maximo)<=0){
                resultado.insertar(nAux.getElem(), resultado.longitud() + 1);
            }
            if(nAux.getElem().compareTo(minimo)>=0){
                listarRangoAux(nAux.getIzquierdo(), minimo, maximo, resultado);
            }
            if(nAux.getElem().compareTo(maximo)<=0){
                listarRangoAux(nAux.getDerecho(), minimo, maximo, resultado);
            }
        }
    }

    public Lista listar() {
        Lista lista = new Lista();
        listarAux(this.raiz, lista);
        return lista;
    }

    private void listarAux(NodoAVL nAux, Lista resultado){
        if (nAux != null) {
            listarAux(nAux.getIzquierdo(), resultado);
            resultado.insertar(nAux.getElem(), resultado.longitud() + 1);
            listarAux(nAux.getDerecho(), resultado);
        }
    }

    public boolean pertenece(Comparable elem) {
        boolean pertenece = false;
        if (this.raiz != null) {
            pertenece = perteneceAux(this.raiz, elem);
        }
        return pertenece;
    }

    private boolean perteneceAux(NodoAVL nAux, Comparable elem) {
        boolean pertenece = false;
        if (nAux != null) {
            if (nAux.getElem().compareTo(elem) == 0) {
                pertenece = true;
            } else {
                if (nAux.getElem().compareTo(elem) < 0) {
                    pertenece = perteneceAux(nAux.getDerecho(), elem);
                } else {
                    pertenece = perteneceAux(nAux.getIzquierdo(), elem);
                }
            }
        }
        return pertenece;
    }

    public boolean eliminar(Comparable elemento){
        boolean exito=false;
        if(this.raiz!=null){
            exito = eliminarAux(null, this.raiz, elemento);
        }
        return exito;
    }
@SuppressWarnings("unchecked")
    private boolean eliminarAux(NodoAVL padre, NodoAVL nAux, Comparable elemento){
        boolean exito=false;
        if(nAux!=null){
            if(elemento.compareTo(nAux.getElem())==0){
                eliminarCaso(padre, nAux);
                exito=true;
            } else if(elemento.compareTo(nAux.getElem())<0){
                exito = eliminarAux(nAux,nAux.getIzquierdo(),elemento);
            } else {
                exito = eliminarAux(nAux,nAux.getDerecho(),elemento);
            }
        }
        return exito;
    }
@SuppressWarnings("unchecked")
    private void eliminarCaso(NodoAVL padre, NodoAVL nAux){
        switch(determinarCaso(nAux)){
            case 1:
                NodoAVL aux = nAux.getDerecho();
                while(aux.getIzquierdo()!=null){
                    aux = aux.getIzquierdo();
                }
                NodoAVL hijoIzq = nAux.getIzquierdo();
                NodoAVL hijoDer = nAux.getDerecho();
                hijoDer.setIzquierdo(null);
                if(nAux.getElem().compareTo(padre.getElem())<0){
                    padre.setIzquierdo(aux);
                } else {
                    padre.setDerecho(aux);
                }
                aux.setIzquierdo(hijoIzq);
                if(hijoDer!=aux){
                    aux.setDerecho(hijoDer);
                }
                aux.recalcularAltura();
                balancear(padre, aux, balance(aux));
            break;
            case 2:
                if(nAux.getElem().compareTo(padre.getElem())<0){
                    padre.setIzquierdo(nAux.getDerecho());
                } else {
                    padre.setDerecho(nAux.getDerecho());
                }
            break;
            case 3:
                if(nAux.getElem().compareTo(padre.getElem())<0){
                    padre.setIzquierdo(nAux.getIzquierdo());
                } else {
                    padre.setDerecho(nAux.getIzquierdo());
                }
            break;
            case 4:
                if(nAux.getElem().compareTo(padre.getElem())<0){
                    padre.setIzquierdo(null);
                } else {
                    padre.setDerecho(null);
                }
            break;
            default:
            break;
        }
        nAux.recalcularAltura();
        padre.recalcularAltura();
        
    }

    private int determinarCaso(NodoAVL nAux){
        int caso;
        if(nAux.getDerecho()!=null){
            if(nAux.getIzquierdo()!=null){
                //hijo izquierdo y derecho
                caso = 1;
            } else {
                //hijo derecho
                caso = 2;
            }
        } else {
            if(nAux.getIzquierdo()!=null){
                //hijo izquierdo
                caso = 3;
            } else {
                //sin hijos
                caso = 4;
            }
        }
        return caso;
    }

    public String toString(){
        String resultado = toStringAux(this.raiz);
        return resultado;
    }

    private String toStringAux(NodoAVL nodo) {
        String toString = "Arbol vacio";
        if (nodo != null) {
            toString = nodo.getElem().toString() + " | " + "altura: " + nodo.getAltura();
            NodoAVL hijoIzq = nodo.getIzquierdo();
            NodoAVL hijoDer = nodo.getDerecho();
            if (hijoIzq != null) {
                toString = toString + ", H.I: " + hijoIzq.getElem().toString();

            } else {
                toString = toString + ", H.I: -";
            }
            if (hijoDer != null) {
                toString = toString + ", H.D: " + hijoDer.getElem().toString() + "\n";
            } else {
                toString = toString + ", H.D: -\n";
            }

            if (hijoIzq != null) {
                toString = toString + toStringAux(hijoIzq);
            }

            if (hijoDer != null) {
                toString = toString + toStringAux(hijoDer);
            }
        }
        return toString;
    }
    
    public boolean insertar (Comparable elemento){
        boolean exito=false;
        if(this.raiz!=null){
            exito = insertarAux(null, this.raiz, elemento);
        } else {
            exito = true;
            raiz = new NodoAVL(elemento, null,null);
        }
        return exito;
    }
@SuppressWarnings("unchecked")
    private boolean insertarAux(NodoAVL padre, NodoAVL nAux, Comparable elemento){
        boolean exito=false;
        if(nAux!=null){     
            if(nAux.getElem().compareTo(elemento)==0){
                exito = false;
            } else if(nAux.getElem().compareTo(elemento)<0){
                if(nAux.getDerecho()!=null){
                    exito = insertarAux(nAux, nAux.getDerecho(), elemento);
                } else {
                    exito = true;
                    nAux.setDerecho(new NodoAVL(elemento,null,null));
                }
            } else {
                if(nAux.getIzquierdo()!=null){
                    exito = insertarAux(nAux, nAux.getIzquierdo(), elemento);
                } else {
                    exito = true;
                    nAux.setIzquierdo(new NodoAVL(elemento,null,null));
                }
            }
            if(exito){
                nAux.recalcularAltura();
                balancear(padre, nAux, balance(nAux));
            }
        }
        return exito;
    }

    private void balancear(NodoAVL padre, NodoAVL nAux, int balance){
        switch(balance){
            case 2:
                if(balance(nAux.getIzquierdo())==1){
                    padre.setIzquierdo(rotacionDer(padre, nAux));   
                } else {
                    //rotacion doble izquierda derecha
                    nAux.setDerecho(rotacionIzq(nAux, nAux.getIzquierdo()));
                    rotacionDer(padre, nAux);
                }
            break;
            case -2:
                if(balance(nAux.getDerecho())==-1){
                    padre.setDerecho(rotacionIzq(padre, nAux));
                } else {
                    //rotacion doble derecha izquierda
                    nAux.setIzquierdo(rotacionDer(nAux, nAux.getDerecho()));
                    rotacionIzq(padre, nAux);
                }
            break;
            default:
            break;
        }
    }

    private NodoAVL rotacionIzq(NodoAVL padre, NodoAVL r) {
        NodoAVL h;
        if (padre != null) {
            h = r.getDerecho();
            NodoAVL temp = h.getIzquierdo();
            h.setIzquierdo(r);
            r.setDerecho(temp);
            h.recalcularAltura();
            r.recalcularAltura();
        } else {
            h = r.getDerecho();
            NodoAVL temp = h.getIzquierdo();
            h.setIzquierdo(r);
            r.setDerecho(temp);
            this.raiz = h;
            h.recalcularAltura();
            r.recalcularAltura();
        }
        return h;
    }

    private NodoAVL rotacionDer(NodoAVL padre, NodoAVL r) {
        NodoAVL h;
        if (padre != null) {
            h = r.getIzquierdo();
            NodoAVL temp = h.getDerecho();
            h.setDerecho(r);
            r.setIzquierdo(temp);
            h.recalcularAltura();
            r.recalcularAltura();
        } else {
            h = r.getIzquierdo();
            NodoAVL temp = h.getDerecho();
            h.setDerecho(r);
            r.setIzquierdo(temp);
            this.raiz = h;
            h.recalcularAltura();
            r.recalcularAltura();
        }
        return h;
    }

    public int balance(NodoAVL nodo) {
        int altIzq = -1;
        int altDer = -1;
        if (nodo.getIzquierdo() != null) {
            altIzq = nodo.getIzquierdo().getAltura();
        }
        if (nodo.getDerecho() != null) {
            altDer = nodo.getDerecho().getAltura();
        }
        return altIzq - altDer;
    }


}
