import lineales.dinamicas.Lista;
public class TestLista {
    public static void main(String[] args) {
        Lista listaOriginal = new Lista();
        Lista listaDos = new Lista();
        cargarLista(listaDos);
        cargarLista(listaOriginal);
        System.out.println("Original: " + listaOriginal.toString());
        System.out.println("Clon:     " + listaOriginal.clone().toString());
        System.out.println(listaOriginal.recuperar(9));
    }

    public static void cargarLista(Lista laLista){
        for (int i = 1; i < 10; i++) {
            laLista.insertar(i, i);
        }
    }

}
