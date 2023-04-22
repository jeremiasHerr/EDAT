import lineales.dinamicas.Pila;
public class TestPilaDinamica {
    public static void main(String[] args) {
        Pila pilaOriginal = new Pila();
        cargarPila(pilaOriginal);
        System.out.println("Original: "+pilaOriginal.toString());
        Pila pilaClon = pilaOriginal.clone();
        System.out.println("Copia: "+pilaClon.toString());
    }

    public static void cargarPila(Pila pila){
        for (int i = 0; i < 9; i++) {
            pila.apilar(i);
        }        
    }

}
