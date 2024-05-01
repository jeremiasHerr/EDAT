import lineales.dinamicas.*;
public class PruebaLista {
    public static void main(String[] args) {
        Lista listaOriginal = new Lista();
        Lista listaDos = new Lista();
        cargarLista(listaDos);
        cargarLista(listaOriginal);
        //System.out.println("Original: " + listaOriginal.toString());
        //System.out.println("Clon:     " + listaOriginal.clone().toString());
        Lista listaAux = concatenar(listaOriginal, listaDos);
        System.out.println("Lista concatenada: " + listaAux.toString());
    }

    public static void cargarLista(Lista laLista){
        for (int i = 1; i < 10; i++) {
            laLista.insertar(i, i);
        }
    }

    public static Lista concatenar(Lista listaUno, Lista listaDos){
        Lista listaConca = new Lista();
        int totalLongitud = listaUno.longitud() + listaDos.longitud();
        int i=1,j=1;
        while(i<=totalLongitud){
            if(i>listaUno.longitud()){
                listaConca.insertar(listaDos.recuperar(j), i);
                i++;
                j++;
            } else {
                listaConca.insertar(listaUno.recuperar(i), i);
                i++;
            }
            
        }
    
        return listaConca;
    }
    
}
