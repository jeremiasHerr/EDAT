package jerarquicas.tests;
import jerarquicas.dinamicas.ArbolBin;
public class testArbolBin{
    public static void main(String[] args) {
        ArbolBin arbolOriginal = new ArbolBin();
        /* 
        arbolOriginal.insertar(5, 1 , true);
        arbolOriginal.insertar(3, 5 , true);
        arbolOriginal.insertar(2, 5 , false);
        arbolOriginal.insertar(7, 2 , true);
        arbolOriginal.insertar(8, 2 , false);
        */
        arbolOriginal.insertar(1, null, true);
        arbolOriginal.insertar(2, 1, true);
        arbolOriginal.insertar(4, 2, true);
        arbolOriginal.insertar(5, 2, false);
        arbolOriginal.insertar(6, 4, false);
        arbolOriginal.insertar(3, 1, false);
        arbolOriginal.insertar(8, 3, false);
        arbolOriginal.insertar(9, 8, true);

        System.out.println(arbolOriginal.padre(8));
    }
}