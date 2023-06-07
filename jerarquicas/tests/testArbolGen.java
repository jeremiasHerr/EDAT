package jerarquicas.tests;
import jerarquicas.dinamicas.*;
import lineales.dinamicas.*;
public class testArbolGen {
    public static void main(String[] args) {
        ArbolGen pino = new ArbolGen();
        pino.insertar(20, null);
        pino.insertar(13, 20);
        pino.insertar(54, 20);
        pino.insertar(15, 13);
        pino.insertar(12, 13);
        pino.insertar(11, 54);
        pino.insertar(27, 54);
        pino.insertar(4, 54);
        pino.insertar(17, 27);
        pino.insertar(22,20);
        pino.insertar(10, 20);
        //pino.insertar(40, 20);
        pino.insertar(18, 54);
        pino.insertar(19, 54);

        Lista listita = new Lista();
        listita.insertar(20,1);
        listita.insertar(13,2);
        listita.insertar(12,3);
        listita.insertar(12,4);
        //listita.insertar(175,5);

        System.out.println(pino.verificarCamino(listita));
        //pino.orden();
        //System.out.println(pino.orden());
    }
}
