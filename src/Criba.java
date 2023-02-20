// Segunda rama: código refactorizado

import java.util.Scanner;

public class Criba {

    // Generar números primos de 1 a max
    public static int[] generarPrimos (int max) {
        if (max >= 2) {
            boolean[] esPrimo = crearBooleanos(max + 1);
            hallarPrimos(esPrimo);
            return crearVectorDePrimos(esPrimo);
        } else { // max < 2
            return new int[0];
            // Vector vacío
        }
    }

    private static boolean[] crearBooleanos(int dim) {
        boolean[] esPrimo = new boolean[dim];
        for (int i = 0; i < dim; i++) {
            esPrimo[i] = i >= 2;
        }
        return esPrimo;
    }

    private static void hallarPrimos(boolean[] esPrimo) {
        for (int i = 2; i < (Math.sqrt(esPrimo.length) + 1); i++) {
            if (esPrimo[i]) {
                // Eliminar los múltiplos de i
                for (int j = 2 * i; j < esPrimo.length; j += i) {
                    esPrimo[j] = false;
                }
            }
        }
    }

    private static int contarPrimos(boolean[] esPrimo) {
        int cuenta = 0;
        for (boolean b : esPrimo) {
            cuenta += b ? 1 : 0;
        }
        return cuenta;
    }

    private static int[] crearVectorDePrimos(boolean[] esPrimo) {
        // Rellenar el vector de números primos
        int[] primos = new int[contarPrimos(esPrimo)];
        for (int i = 0, j = 0; i < esPrimo.length; i++) {
            if (esPrimo[i]) {
                primos[j++] = i;
            }
        }
        return primos;
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce el número para la criba de Erastótenes: ");
        int dato = teclado.nextInt();
        int[] vector = new int[dato];
        System.out.println("\nVector inicial hasta: " + dato);
        for (int i = 0; i < vector.length; i++) {
            imprimirLinea(i);
            System.out.print(i + 1 + "\t");
        }
        vector = generarPrimos(dato);
        System.out.println("\nVector de primos hasta: " + dato);
        for (int i = 0; i < vector.length; i++) {
            imprimirLinea(i);
            System.out.print(vector[i] + "\t");
        }
    }

    private static void imprimirLinea(int i) {
        if (i % 10 == 0) {
            System.out.println();
        }
    }


}