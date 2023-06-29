import lineales.dinamicas.Lista;
public class TestLista {
    public static void main(String[] args) {
       // Lista listaOriginal = new Lista();
        Lista listaxd = new Lista();
        
        listaxd.insertar(1, 1);
        listaxd.insertar(2, 2);
        listaxd.insertar(1, 3);
        listaxd.insertar(4, 4);
        listaxd.insertar(4, 5);
        listaxd.insertar(4, 6);
        listaxd.insertar(4, 7);
        listaxd.insertar(4, 8);

        System.out.println(listaxd.cabecera.getElem());
        listaxd.eliminarOcurrencias(4);
        System.out.println("xd "+listaxd.toString());

        

        //System.out.println("Original: " + listaOriginal.toString());
       // System.out.println("Clon:     " + listaOriginal.clone().toString());
       // System.out.println(listaOriginal.recuperar(9));
    }

    public static void cargarLista(Lista laLista){
        for (int i = 1; i < 10; i++) {
            laLista.insertar(i, i);
        }
    }

}
