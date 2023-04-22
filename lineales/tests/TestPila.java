import java.util.Scanner;
import lineales.estaticas.Pila;


public class TestPila {
    public static void main(String[] args) {
        Pila pilaTest = new Pila();
        //llenarPilaManualEnteros(pilaTest);
        //llenarPilaManualCadena(pilaTest);
        llenarPila(pilaTest);
        System.out.println("Pila original: "+pilaTest.toString());
        System.out.println("Pila invertida: "+crearPilaInvertida(pilaTest).toString());
        //System.out.println("La pila esta vacia: "+pilaTest.esVacia());
        //probarDesapilar(pilaTest);
        //pilaTest.desapilar();
        System.out.println("Es capicua: "+esCapicua(pilaTest));
        //pilaTest.vaciar();
        //System.out.println("Pila vaciada: "+pilaTest.esVacia());
        //System.out.println(pilaTest.toString() + "pila original desapilada");

        //System.out.println(probarClonar(pilaTest).toString() + "pila nueva");
    }

    public static void llenarPilaManualCadena (Pila pilaTest){
        Scanner sc = new Scanner(System.in);
        String cadena;
        do{
            System.out.println("Ingrese una cadena para apilar");
            cadena = sc.next();
        }while(pilaTest.apilar(cadena));
        sc.close();
    }

    public static void llenarPila(Pila pilaTest){
        for (int i = 0; i <= 20; i++) {
            pilaTest.apilar(i);
        }
    }
    public static Pila crearPilaInvertida (Pila pilaOriginal){
        Pila pilaNueva = new Pila();
        Pila pilaAux = new Pila();
        pilaAux = pilaOriginal.clone();
        do{
            pilaNueva.apilar(pilaAux.obtenerTope());
            pilaAux.desapilar();
        }while(!pilaAux.esVacia());
        return pilaNueva;
    }

    public static boolean esCapicua(Pila pilaOriginal){
        Pila pilaInversa = crearPilaInvertida(pilaOriginal);
        Pila pilaNueva = new Pila();
        boolean esIgual=true;
        pilaNueva = pilaOriginal.clone();
        String var1,var2;
        while(esIgual && !pilaInversa.esVacia() && !pilaNueva.esVacia()){
            var1 = pilaNueva.obtenerTope().toString();
            var2 = pilaInversa.obtenerTope().toString();
            if(!var1.equals(var2)){
                esIgual = false;
            }
            pilaNueva.desapilar();
            pilaInversa.desapilar();
        }
        return esIgual;
    }

    public static void llenarPilaManualEnteros(Pila pilaTest){
        Scanner sc = new Scanner(System.in);
        int numero=0;
        do{
            System.out.println("Ingrese un numero para apilar");
            numero = sc.nextInt();
        }while(pilaTest.apilar(numero));
        sc.close();
    }
}