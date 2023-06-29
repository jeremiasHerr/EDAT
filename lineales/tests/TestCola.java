import lineales.dinamicas.Cola;
public class TestCola {
    public static void main(String[] args) {
        Cola colaOriginal = new Cola();
        cargarCola(colaOriginal);
        System.out.println(colaOriginal.toString());
        System.out.println(colaOriginal.clone().toString());
    }

    public static void cargarCola(Cola unaCola){
        for (int i = 0; i < 4; i++) {
            unaCola.poner(i);
        }
    }

}
