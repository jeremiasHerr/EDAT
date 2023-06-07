package jerarquicas.dinamicas;

import lineales.dinamicas.*;

public class ArbolGen {
    private NodoGen raiz;

    public ArbolGen() {
        this.raiz = null;
    }

    public boolean eliminar(Object elem) {
        boolean exito = true;
        if (this.raiz != null) {
            if (this.raiz.getElemento().equals(elem)) {
                this.raiz = null;
            } else {
                exito = eliminarPR(null, this.raiz, elem);
            }
        }
        return exito;
    }

    private boolean eliminarPR(NodoGen padre, NodoGen nodo, Object elem) {
        boolean exito = false;
        if (nodo != null) {
            if (nodo.getElemento().equals(elem)) {
                if (nodo.getHermanoDer() == null) {
                    padre.setHijoIzq(null);
                } else {
                    padre.setHijoIzq(nodo.getHermanoDer());
                }
            } else {
                NodoGen papa = nodo;
                NodoGen aux = nodo.getHermanoDer();
                while (aux != null && !exito) {
                    if (aux.getElemento().equals(elem)) {
                        papa.setHermanoDer(aux.getHermanoDer());
                        exito = true;
                    } else {
                        papa = aux;
                        aux = aux.getHermanoDer();
                    }
                }
                if (!exito) {
                    System.out.println("tuki");
                    exito = eliminarPR(nodo, nodo.getHijoIzq(), elem);
                }
            }
        }
        return exito;
    }

    public boolean insertar(Object elem, Object padre) {
        boolean exito = false;
        if (this.raiz == null) {
            this.raiz = new NodoGen(elem, null, null);
            exito = true;
        } else {
            NodoGen nPadre = obtenerNodo(this.raiz, padre);
            if (nPadre != null) {
                if (nPadre.getHijoIzq() == null) {
                    nPadre.setHijoIzq(new NodoGen(elem, null, null));
                } else {
                    NodoGen nAux = nPadre.getHijoIzq();
                    while (nAux.getHermanoDer() != null) {
                        nAux = nAux.getHermanoDer();
                    }
                    nAux.setHermanoDer(new NodoGen(elem, null, null));
                }
                exito = true;
            }
        }
        return exito;
    }

    private NodoGen obtenerNodo(NodoGen nodo, Object buscado) {
        NodoGen resultado = null;
        if (nodo != null) {
            if (nodo.getElemento().equals(buscado)) {
                resultado = nodo;
            } else {
                if (nodo.getHijoIzq() != null) {
                    NodoGen aux = nodo.getHijoIzq();
                    while (aux != null && resultado == null) {
                        if (aux.getElemento().equals(buscado)) {
                            resultado = aux;
                        } else {
                            if (aux.getHijoIzq() != null) {
                                resultado = obtenerNodo(aux, buscado);
                            }
                        }
                        aux = aux.getHermanoDer();
                    }
                }
            }
        }
        return resultado;
    }

    @Override
    public String toString() {
        return toStringPR(this.raiz);
    }

    private String toStringPR(NodoGen nodo) {
        String s = "";
        if (nodo != null) {
            s = s + nodo.getElemento().toString() + "->";
            NodoGen hijo = nodo.getHijoIzq();
            while (hijo != null) {
                s = s + hijo.getElemento().toString() + ", ";
                hijo = hijo.getHermanoDer();
            }

            hijo = nodo.getHijoIzq();
            while (hijo != null) {
                s = s + "\n" + toStringPR(hijo);
                hijo = hijo.getHermanoDer();
            }
        }
        return s;
    }

    public boolean pertenece(Object elem) {
        boolean resultado = false;

        if (this.raiz != null) {
            resultado = perteneceAux(elem, this.raiz);
        }
        return resultado;
    }

    private boolean perteneceAux(Object elem, NodoGen nAux) {
        boolean resultado = false;

        if (nAux != null) {
            if (nAux.getElemento().equals(elem)) {
                resultado = true;
            } else {
                if (nAux.getHijoIzq() != null) {
                    if (nAux.getHijoIzq().getElemento().equals(elem)) {
                        resultado = true;
                    } else {
                        NodoGen aux = nAux.getHijoIzq();
                        while (aux != null && !resultado) {
                            if (aux.getElemento().equals(elem)) {
                                resultado = true;
                            } else {
                                if (aux.getHijoIzq() != null) {
                                    resultado = perteneceAux(elem, aux);
                                }
                            }
                            aux = aux.getHermanoDer();
                        }
                    }
                }
            }
        }
        return resultado;
    }

    public Lista listarPorNiveles() {
        Lista resultado = new Lista();
        if (this.raiz != null) {
            listarPorNivelesAux(this.raiz, resultado);
        }
        return resultado;
    }

    private void listarPorNivelesAux(NodoGen nAux, Lista resultado) {
        Cola colaQ = new Cola();
        if (nAux != null) {
            colaQ.poner(nAux);
            while (!colaQ.esVacia()) {
                NodoGen nodo = (NodoGen) colaQ.obtenerFrente();
                colaQ.sacar();
                resultado.insertar(nodo.getElemento(), resultado.longitud() + 1);
                if (nodo.getHijoIzq() != null) {
                    NodoGen aux = nodo.getHijoIzq();
                    while (aux != null) {
                        colaQ.poner(aux);
                        aux = aux.getHermanoDer();
                    }
                }
            }
        }
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public Object padre(Object elem) {
        Object resultado = null;
        if (this.raiz != null) {
            if (!this.raiz.getElemento().equals(elem)) {
                resultado = padreAux(this.raiz, elem);
            }
        }
        return resultado;
    }

    private Object padreAux(NodoGen nAux, Object elem) {
        Object resultado = null;
        if (nAux != null) {
            if (nAux.getHijoIzq().getElemento().equals(elem)) {
                resultado = nAux.getElemento();
            } else {
                NodoGen aux = nAux.getHijoIzq();
                while (resultado == (null) && aux != null) {
                    if (aux.getElemento().equals(elem)) {
                        resultado = nAux.getElemento();
                    } else {
                        if (aux.getHijoIzq() != null) {
                            resultado = padreAux(aux, elem);
                        }
                    }
                    aux = aux.getHermanoDer();
                }
            }
        }
        return resultado;
    }

    public int nivel(Object elem) {
        int resultado = 0;
        if (!this.raiz.getElemento().equals(elem)) {
            resultado = nivelAux(this.raiz, elem, 0);
        }
        return resultado;
    }

    private int nivelAux(NodoGen nAux, Object elem, int nivel) {
        int resultado = -1;
        boolean encontrado;
        if (nAux != null) {
            if (nAux.getElemento().equals(elem)) {
                resultado = nivel;
            } else {
                NodoGen aux = nAux.getHijoIzq();
                encontrado = false;
                while (aux != null && !encontrado) {
                    resultado = nivelAux(aux, elem, nivel + 1);
                    encontrado = resultado != -1;
                    aux = aux.getHermanoDer();
                }
            }
        }
        return resultado;
    }

    public Lista ancestros(Object buscado) {
        Lista ls = new Lista();
        if (this.raiz != null) {
            ancestrosAux(this.raiz, ls, buscado);
        }
        return ls;
    }

    private boolean ancestrosAux(NodoGen nodo, Lista ls, Object buscado) {
        // metodo recursivo que retorna verdadero o falso dependiendo de si encuentra el
        // elemento buscado
        boolean encontro = false;
        if (nodo != null) {
            if (nodo.getElemento().equals(buscado)) {
                encontro = true;
            } else {
                NodoGen aux = nodo.getHijoIzq();
                while (aux != null && !encontro) {
                    encontro = encontro || ancestrosAux(aux, ls, buscado);
                    aux = aux.getHermanoDer();
                }
                if (encontro) {
                    // si se encontro el elemento, se insertan en la lista los ancestros
                    ls.insertar(nodo.getElemento(), ls.longitud() + 1);
                }
            }
        }
        return encontro;
    }

    public boolean sonFrontera(Lista unaLista) {
        // para no modificar la lista original
        Lista clon;
        clon = unaLista.clone();
        return sonFronteraAux(this.raiz, clon);
    }

    private boolean sonFronteraAux(NodoGen nAux, Lista unaLista) {
        boolean esFrontera = true;
        int pos;
        while (nAux != null && esFrontera && !unaLista.esVacia()) {
            if (nAux.getHijoIzq() == null) {
                pos = unaLista.localizar(nAux.getElemento());
                unaLista.eliminar(pos);
            } else {
                sonFronteraAux(nAux.getHijoIzq(), unaLista);
            }
            nAux = nAux.getHermanoDer();
        }
        if (!unaLista.esVacia()) {
            esFrontera = false;
        }
        return esFrontera;
    }

    public int altura() {
        int resultado = -1;
        if (this.raiz != null) {
            resultado = alturaAux(this.raiz);
        }
        return resultado;
    }

    private int alturaAux(NodoGen nAux) {
        int contador = 0, masGrande = 0;

        if (nAux.getHijoIzq() != null) {
            NodoGen aux = nAux.getHijoIzq();
            while (aux != null) {
                contador = alturaAux(aux);
                if (contador > masGrande) {
                    masGrande = contador;
                }
                aux = aux.getHermanoDer();
            }
            contador = masGrande + 1;
        }
        return contador;
    }

    public boolean equals(ArbolGen otroArbol) {
        boolean resultado = false;
        if (this.raiz != null) {
            resultado = equalsAux(this.raiz, otroArbol.raiz);
        }
        return resultado;
    }

    private boolean equalsAux(NodoGen nOriginal, NodoGen nOtro) {
        boolean resultado = true;
        if (nOriginal != null) {
            if (nOtro != null) {
                if (!nOriginal.getElemento().equals(nOtro.getElemento())) {
                    resultado = false;
                } else {
                    NodoGen aux1 = nOriginal.getHijoIzq();
                    NodoGen aux2 = nOtro.getHijoIzq();
                    if ((aux1 != null && aux2 == null) || (aux1 == null && aux2 != null)) {
                        resultado = false;
                    }
                    while (resultado && aux1 != null && aux2 != null) {
                        if (nOriginal.getElemento().equals(nOtro.getElemento())) {
                            resultado = equalsAux(aux1, aux2);
                        } else {
                            resultado = false;
                        }
                        aux1 = aux1.getHermanoDer();
                        aux2 = aux2.getHermanoDer();
                    }
                    if ((aux1 != null && aux2 == null) || (aux1 == null && aux2 != null)) {
                        resultado = false;
                    }
                }
            } else {
                resultado = false;
            }
        } else {
            resultado = nOtro == null;
        }
        return resultado;
    }

    public Lista listarInorden() {
        Lista listaInorden = new Lista();
        listarInordenAux(this.raiz, listaInorden);
        return listaInorden;
    }

    private void listarInordenAux(NodoGen nodo, Lista lista) {
        if (nodo != null) {
            // primero visito los hijos izquierdos
            if (nodo.getHijoIzq() != null) {
                listarInordenAux(nodo.getHijoIzq(), lista);
            }
            // luego al padre
            lista.insertar(nodo.getElemento(), lista.longitud() + 1);
            // luego a los hermanos
            if (nodo.getHijoIzq() != null) {
                NodoGen aux = nodo.getHijoIzq().getHermanoDer();
                while (aux != null) {
                    listarInordenAux(aux, lista);
                    aux = aux.getHermanoDer();
                }
            }

        }
    }

    public Lista listarPreorden() {
        Lista listaPreorden = new Lista();
        listarPreordenAux(this.raiz, listaPreorden);
        return listaPreorden;
    }

    private void listarPreordenAux(NodoGen nAux, Lista lista) {
        if (nAux != null) {
            lista.insertar(nAux.getElemento(), lista.longitud() + 1);
            if (nAux.getHijoIzq() != null) {
                listarPreordenAux(nAux.getHijoIzq(), lista);
                NodoGen aux = nAux.getHijoIzq().getHermanoDer();
                while (aux != null) {
                    listarPreordenAux(aux, lista);
                    aux = aux.getHermanoDer();
                }
            }
        }
    }

    public Lista listarPosorden() {
        Lista listaPostorden = new Lista();
        listarPosordenAux(this.raiz, listaPostorden);
        return listaPostorden;
    }

    private void listarPosordenAux(NodoGen nAux, Lista lista) {
        if (nAux != null) {
            if (nAux.getHijoIzq() != null) {
                listarPosordenAux(nAux.getHijoIzq(), lista);
                NodoGen aux = nAux.getHijoIzq().getHermanoDer();
                while (aux != null) {
                    listarPreordenAux(aux, lista);
                    aux = aux.getHermanoDer();
                }
            }
            lista.insertar(nAux.getElemento(), lista.longitud() + 1);
        }
    }

    public ArbolGen clone() {
        ArbolGen clon = new ArbolGen();

        if (this.raiz != null) {
            clon.raiz = new NodoGen(null, null, null);
            cloneAux(this.raiz, clon.raiz);
        }

        return clon;
    }

    private void cloneAux(NodoGen nAux, NodoGen nClon) {
        while (nAux != null) {
            nClon.setElem(nAux.getElemento());
            if (nAux.getHijoIzq() != null) {
                nClon.setHijoIzq(new NodoGen(nAux.getHijoIzq(), null, null));
                cloneAux(nAux.getHijoIzq(), nClon.getHijoIzq());
            }
            if (nAux.getHermanoDer() != null) {
                nClon.setHermanoDer(new NodoGen(nAux.getElemento(), null, null));
            }
            nAux = nAux.getHermanoDer();
            nClon = nClon.getHermanoDer();
        }
    }

    public boolean verificarCamino(Lista buscado) {
        boolean resultado = false;
        if (this.raiz != null) {
            if(this.raiz.getElemento().equals(buscado.recuperar(1))){
                buscado.eliminar(1);
                resultado = verificarCaminoAux(this.raiz, buscado);
            }
            
        }
        return resultado;
    }

    private boolean verificarCaminoAux(NodoGen nAux, Lista buscado) {
        boolean resultado = true;
        if(nAux!=null){
            if(!buscado.esVacia()){
                NodoGen aux = nAux.getHijoIzq();
                while(aux!=null){
                    if(aux.getElemento().equals(buscado.recuperar(1))){
                        buscado.eliminar(1);
                        System.out.println(aux.getElemento());
                        verificarCaminoAux(aux,buscado);
                    } else {
                        aux = aux.getHermanoDer();
                    }
                }
                if(aux==null && !buscado.esVacia()){
                    resultado = false;
                }
            }
        }
        return resultado;
    }

    public int orden() {
        int contador = 0;
        if (this.raiz != null) {
            contador = ordenAux(this.raiz);
        }
        return contador;
    }

    private int ordenAux(NodoGen nAux) {
        int contador = 0,mayor;

        if (nAux != null) {
            //System.out.println(nAux.getElemento());
            if (nAux.getHijoIzq() != null) {
                NodoGen aux = nAux;
                while (aux != null) {
                    contador++;
                    aux = aux.getHermanoDer();
                    
                }
            }
            NodoGen aux2 = nAux.getHijoIzq();
            while(aux2!=null){
                mayor = ordenAux(aux2);
                if(contador<mayor){
                    contador = mayor;
                }
                aux2 = aux2.getHermanoDer();
            }
        }
        return contador;
    }
}
