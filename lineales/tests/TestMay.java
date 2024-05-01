import lineales.dinamicas.*;
public class TestMay {
    public static void main(String[] args) {
        Lista lis=new Lista();
        lis.insertar('A', 1);
        lis.insertar('B', 2);
        lis.insertar('*', 3);
        lis.insertar('C', 4);
        lis.insertar('*', 5);
        lis.insertar('D', 6);
        lis.insertar('E', 7);
        lis.insertar('F', 8);
        System.out.println(generarOtraLista(lis).toString());
        



    }
    public static Lista generarOtraLista(Lista lis) {
        Lista resultado = new Lista();
        Lista clon = lis.clone();
        Cola original = new Cola();
        Pila invertida = new Pila();
        int longitud = lis.longitud(), i=1, j=1;
        
        clon.insertar('*', clon.longitud()+1);
        while(j<=longitud+1) {
            Object elemento=clon.recuperar(j);
            //System.out.println(elemento);
            j++;
            if((char)elemento!='*') {
                original.poner(elemento);
                invertida.apilar(elemento);
            } else {
                Cola clonCola = original.clone();
                while(!original.esVacia()) {
                    resultado.insertar(original.obtenerFrente(), i);
                    i++;
                    original.sacar();
                }
                while(!invertida.esVacia()) {
                    resultado.insertar(invertida.obtenerTope(), i);
                    i++;
                    invertida.desapilar();
                }
                while(!clonCola.esVacia()) {
                    resultado.insertar(clonCola.obtenerFrente(), i);
                    i++;
                    clonCola.sacar();
                }
                resultado.insertar('*', i);
                i++;
                original.vaciar();
                invertida.vaciar();
                clonCola.vaciar();
            }
        }
        return resultado;
    }
}