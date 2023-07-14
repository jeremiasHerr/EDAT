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
            if (this.raiz.getElemento().equals(buscado.recuperar(1))) {
                buscado.eliminar(1);
                resultado = verificarCaminoAux(this.raiz, buscado);
            }

        }
        return resultado;
    }

    private boolean verificarCaminoAux(NodoGen nAux, Lista buscado) {
        boolean resultado = true;
        if (nAux != null) {
            if (!buscado.esVacia()) {
                NodoGen aux = nAux.getHijoIzq();
                while (aux != null && resultado) {
                    if (aux.getElemento().equals(buscado.recuperar(1))) {
                        buscado.eliminar(1);
                        resultado = verificarCaminoAux(aux, buscado);
                    } else {
                        aux = aux.getHermanoDer();
                    }
                    if (aux == null && !buscado.esVacia()) {
                        resultado = false;
                    }
                }
            }

        }
        return resultado;
    }

    public boolean eliminarDescendientes(Object elem) {
        boolean exito;

        if (elem == this.raiz.getElemento()) {
            this.raiz = null;
            exito = true;
        } else {
            exito = eliminarDescendientesAux(elem, this.raiz, null);
        }
        return exito;
    }

    private boolean eliminarDescendientesAux(Object elem, NodoGen nAux, NodoGen anterior) {
        boolean exito = false;

        if (nAux != null) {

            if (nAux.getElemento().equals(elem)) {
                if (nAux.getHermanoDer() == null) {
                    anterior.setHermanoDer(null);
                } else if (anterior.getHermanoDer() != null) {
                    if (anterior.getHermanoDer().getElemento().equals(nAux.getElemento())) {
                        anterior.setHermanoDer(nAux.getHermanoDer());
                    }
                }
                if (anterior.getHijoIzq() != null) {
                    if (anterior.getHijoIzq().getElemento().equals(nAux.getElemento())) {
                        anterior.setHijoIzq(nAux.getHermanoDer());
                    }
                }
                exito = true;
            } else {
                NodoGen aux = nAux.getHijoIzq();
                anterior = nAux;
                while (aux != null && !exito) {
                    eliminarDescendientesAux(elem, aux, anterior);
                    anterior = aux;
                    aux = aux.getHermanoDer();
                }
            }

        }
        return exito;
    }

    public Lista listarEntreNiveles(int nivel1, int nivel2) {
        Lista resultado = new Lista();
        if (this.raiz != null) {
            listarEntreNivelesAux(this.raiz, resultado, nivel1, nivel2, 0);
        }
        return resultado;
    }

    public void listarEntreNivelesAux(NodoGen nAux, Lista lista, int nivel1, int nivel2, int nivel) {
        if (nAux != null) {
            if (nivel <= nivel2) {
                if (nAux.getHijoIzq() != null) {
                    listarEntreNivelesAux(nAux.getHijoIzq(), lista, nivel1, nivel2, nivel + 1);
                }
                if (nivel >= nivel1 && nivel <= nivel2) {
                    lista.insertar(nAux.getElemento(), lista.longitud() + 1);
                }
                if (nAux.getHijoIzq() != null) {
                    NodoGen aux = nAux.getHijoIzq().getHermanoDer();
                    nivel++;
                    while (aux != null) {
                        listarEntreNivelesAux(aux, lista, nivel1, nivel2, nivel);
                        aux = aux.getHermanoDer();
                    }
                }
            }
        }
    }

    public boolean insertarEnPos(Object elem, Object padre, int pos) {
        boolean exito = false;
        if (this.raiz != null) {
            exito = insertarEnPosAux(elem, padre, pos, this.raiz);
        }
        return exito;
    }

    private boolean insertarEnPosAux(Object elem, Object padre, int pos, NodoGen nAux) {
        boolean exito = false;

        if (nAux != null) {
            if (nAux.getElemento().equals(padre)) {
                if (pos == 1) {
                    NodoGen aux4 = nAux.getHijoIzq();
                    nAux.setHijoIzq(new NodoGen(elem, null, null));
                    nAux.getHijoIzq().setHermanoDer(aux4);
                } else if (nAux.getHijoIzq() != null) {
                    NodoGen aux = nAux.getHijoIzq();
                    int i = 1;
                    while (aux != null && !exito) {
                        if (i + 1 == pos) {
                            NodoGen aux2 = aux.getHermanoDer();
                            aux.setHermanoDer(new NodoGen(elem, null, null));
                            aux = aux.getHermanoDer();
                            aux.setHermanoDer(aux2);
                            exito = true;
                        } else if (aux.getHermanoDer() == null) {
                            aux.setHermanoDer(new NodoGen(elem, null, null));
                            exito = true;
                        }
                        aux = aux.getHermanoDer();
                        i = i + 1;
                    }
                }
            } else {
                if (nAux.getHijoIzq() != null) {
                    NodoGen aux3 = nAux.getHijoIzq();
                    while (aux3 != null && !exito) {
                        exito = insertarEnPosAux(elem, padre, pos, aux3);
                        aux3 = aux3.getHermanoDer();
                    }
                }
            }
        }
        return exito;
    }

    public Lista obtenerNodosNivelPar() {
        Lista resultado = new Lista();
        int[] nivel = { 0 };
        if (this.raiz != null) {
            obtenerNodosNivelParAux(this.raiz, resultado, 0);
        }
        return resultado;
    }

    private void obtenerNodosNivelParAux(NodoGen nAux, Lista resultado, int nivel) {
        if (nAux != null) {
            if (nivel % 2 == 0) {
                resultado.insertar(nAux.getElemento(), resultado.longitud() + 1);
            }
            NodoGen aux = nAux.getHijoIzq();
            nivel++;
            while (aux != null) {
                obtenerNodosNivelParAux(aux, resultado, nivel);
                aux = aux.getHermanoDer();
            }
        }
    }

    public void eliminarNodosDeValor(Object elem) {
        if (this.raiz != null) {
            eliminarNodosDeValorAux(this.raiz, elem, false);
        }
    }

    private void eliminarNodosDeValorAux(NodoGen nAux, Object valor, boolean encontrado) {
        if (nAux != null) {
            if (nAux.getHijoIzq() != null) {
                if (nAux.getHijoIzq().equals(valor)) {
                    encontrado = true;
                    NodoGen hijoNodoEliminar = nAux.getHijoIzq().getHijoIzq();
                    // Me fijo si el nodo a eliminar tiene hijo izquierdo
                    if (hijoNodoEliminar == null) {
                        if (nAux.getHijoIzq().getHermanoDer() == null) {
                            nAux.setHijoIzq(null);
                        } else {
                            nAux.setHijoIzq(nAux.getHijoIzq().getHermanoDer());
                        }
                    } else {
                        if (nAux.getHijoIzq().getHermanoDer() == null) {
                            nAux.setHijoIzq(hijoNodoEliminar);
                        } else {
                            NodoGen aux1 = nAux.getHijoIzq().getHermanoDer();
                            nAux.setHijoIzq(nAux.getHijoIzq().getHijoIzq());
                            nAux.getHijoIzq().setHermanoDer(aux1);
                        }

                    }
                } else if (nAux.getHermanoDer() != null) {
                    if (nAux.getHermanoDer().equals(valor)) {
                        encontrado = true;
                        // Me vuelvo a fijar si tiene hijo izquierdo
                        if (nAux.getHermanoDer().getHijoIzq() == null) {
                            nAux.setHermanoDer(nAux.getHermanoDer().getHermanoDer());
                        } else {
                            NodoGen aux2 = nAux.getHermanoDer().getHermanoDer();
                            nAux.setHermanoDer(nAux.getHermanoDer().getHijoIzq());
                            nAux.getHermanoDer().setHermanoDer(aux2);
                        }
                    }

                }
            }

            NodoGen aux3 = nAux.getHijoIzq();
            while (aux3 != null && !encontrado) {
                eliminarNodosDeValorAux(nAux, valor, encontrado);
                aux3 = aux3.getHermanoDer();
            }
        }
    }

    public int obtenerCantidadNodosNivel(int nivel) {
        int resultado = 0;
        if (this.raiz != null) {
            resultado = obtenerCantidadNodosNivelAux(this.raiz, nivel, 0);
        }
        return resultado;
    }

    private int obtenerCantidadNodosNivelAux(NodoGen nAux, int nivel, int nivelActual) {
        int resultado = 0;
        if (nAux != null) {
            if (nivelActual == nivel) {
                resultado++;
            }
            NodoGen aux = nAux.getHijoIzq();
            nivelActual++;
            while (aux != null) {
                resultado += obtenerCantidadNodosNivelAux(aux, nivel, nivelActual);
                aux = aux.getHermanoDer();
            }
        }
        return resultado;
    }

    public void insertarEnPosicion(Object elem, Object padre, int pos) {
        if (this.raiz != null) {
            insertarEnPosicionAux(raiz, elem, padre, pos);
        }
    }

    private boolean insertarEnPosicionAux(NodoGen nAux, Object elem, Object padre, int pos) {
        boolean encontrado = false;
        if (nAux != null) {
            NodoGen padreAux = null;
            if (nAux.getElemento().equals(padre)) {
                padreAux = nAux;
                encontrado = true;
            }
            if (encontrado) {
                int i = 1;
                NodoGen hijo = padreAux.getHijoIzq();
                while (i < pos - 1 && hijo.getHermanoDer() != null) {
                    hijo = hijo.getHermanoDer();
                    i++;
                }
                if (hijo.getHermanoDer() != null) {
                    NodoGen nuevo = new NodoGen(elem, null, hijo.getHermanoDer());
                    hijo.setHermanoDer(nuevo);
                } else {
                    hijo.setHermanoDer(new NodoGen(elem, null, null));
                }

            }
            NodoGen aux = nAux.getHijoIzq();
            while (aux != null && !encontrado) {
                encontrado = insertarEnPosicionAux(aux, elem, padre, pos);
                aux = aux.getHijoIzq();
            }
        }
        return encontrado;
    }
}
