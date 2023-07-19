package grafos.test;

import grafos.dinamicas.GrafoEtiquetado;

public class testGrafo {
    public static void main(String[] args) {
        GrafoEtiquetado grafito = new GrafoEtiquetado();
        grafito.insertarVertice("A");
        grafito.insertarVertice("B");
        grafito.insertarVertice("C");
        grafito.insertarVertice("D");
        grafito.insertarVertice("E");
        grafito.insertarVertice("F");
        grafito.insertarArco("A", "B", 2);
        grafito.insertarArco("A", "C", 4);
        grafito.insertarArco("B", "C", 3);
        grafito.eliminarVertice("F");
        //grafito.eliminarArco("B", "C");
        System.out.println(grafito.toString());
        //System.out.println(grafito.listarEnProfundidad().toString());
        //System.out.println(grafito.listarEnAnchura().toString());
        System.out.println(grafito.caminoMasLargo("B", "C").toString());
    }
}
