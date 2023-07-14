package conjuntistas.dinamicas.tests;

import conjuntistas.dinamicas.ArbolAVL;
import jerarquicas.dinamicas.*;

public class testArbolAVL {
    public static void main(String[] args) {
        ArbolAVL pino = new ArbolAVL();
        pino.insertar(20);
        pino.insertar(12);
        pino.insertar(28);
        pino.insertar(7);
        pino.insertar(14);
        pino.insertar(25);
        pino.insertar(33);
        pino.insertar(31);
        pino.insertar(3);
        pino.insertar(10);
        pino.insertar(17);

        System.out.println(pino.toString());

        System.out.println(pino.pertenece(33));
        System.out.println(pino.listar().toString());
        System.out.println(pino.listarRango(12, 100).toString());
        System.out.println(pino.minimoElem());
        System.out.println(pino.maximoElem());
    }
}
