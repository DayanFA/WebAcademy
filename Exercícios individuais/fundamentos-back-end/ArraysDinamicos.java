import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArraysDinamicos {

    public static void main(String[] args) {

        List<Integer> numeros = new ArrayList<>();
        numeros.add(5);
        numeros.add(2);
        numeros.add(9);
        numeros.add(7);
        numeros.add(1);

        System.out.println("Lista de números:");
        for (Integer numero : numeros) {
            System.out.println(numero);
        }

        System.out.print("Lista de números ordenada: ");
        Collections.sort(numeros);
        System.out.println(numeros);

        System.out.println("Busca binária: " + Collections.binarySearch(numeros, 4));

        List<Integer> maiores = numeros.subList(numeros.size() - 3, numeros.size());
        System.out.println("Três maiores números: " + maiores);

        System.out.println("Listas iguais? " + numeros.equals(maiores));

        Collections.reverse(numeros);
        System.out.println("Lista de números em ordem descrescente: " + numeros);
        
    }
    
}
