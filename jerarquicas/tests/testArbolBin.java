package jerarquicas.tests;
import jerarquicas.dinamicas.ArbolBin;
public class testArbolBin{
    public static void main(String[] args) {
        ArbolBin arbolOriginal = new ArbolBin();
        /* 
        arbolOriginal.insertar(1, null, true);
        arbolOriginal.insertar(2, 1, true);
        arbolOriginal.insertar(4, 2, true);
        arbolOriginal.insertar(5, 2, false);
        arbolOriginal.insertar(6, 4, false);
        arbolOriginal.insertar(7, 6, false);
        arbolOriginal.insertar(3, 1, false);
        arbolOriginal.insertar(8, 3, false);
        arbolOriginal.insertar(9, 8, true);
        arbolOriginal.insertar(13, 9, true);
        arbolOriginal.insertar(15, 13, true);
        arbolOriginal.insertar(16, 13, false);
        arbolOriginal.insertar(17, 16, true);
        */
        arbolOriginal.insertar('A', null, 'I');
        arbolOriginal.insertar('B', 'A', 'I');
        arbolOriginal.insertar('C', 'A', 'D');
        arbolOriginal.insertar('D', 'B', 'I');
        arbolOriginal.insertar('E', 'C', 'I');
        arbolOriginal.insertar('F', 'C', 'D');
        arbolOriginal.insertar('G', 'E', 'I');
        arbolOriginal.insertar('H', 'E', 'D');
        //System.out.println(arbolOriginal.padre(8));
        System.out.println("Arbol en Preorden: "+arbolOriginal.listarPreorden().toString());
        System.out.println("Arbol en Inorden: "+arbolOriginal.listarInorden().toString());
        System.out.println("Arbol en Posorden: "+arbolOriginal.listarPosorden().toString());
        System.out.println("Arbol por niveles: "+arbolOriginal.listarNiveles().toString());
        System.out.println("Nivel del elemento buscado: "+arbolOriginal.nivel(99));
        System.out.println("Altura del arbol: " + arbolOriginal.altura());
    }
}