package jerarquicas.tests;
import jerarquicas.dinamicas.*;
public class testArbolGen {
    public static void main(String[] args) {
        ArbolGen pino = new ArbolGen();
        pino.insertar(1,1);
        pino.insertar(2,1);
        pino.insertar(3,1);
        pino.insertar(4,1);
        pino.insertar(5,2);
        pino.insertar(6, 2);
        pino.insertar(7, 2);
        pino.insertar(8, 3);
        pino.insertar(9, 8);
        pino.insertar(10, 6);
        pino.insertar(11, 5);
        pino.insertar(12, 10);
        pino.insertar(20, 8);
        //System.out.println(pino.obtenerNodosNivelPar());
        //System.out.println(pino.toString());
        //pino.eliminarNodosDeValor(20);
        System.out.println(pino.toString());
        //System.out.println(pino.obtenerCantidadNodosNivel(4));
        pino.insertarEnPosicion(30,2,2);
        System.out.println(pino.toString());
    }
}
