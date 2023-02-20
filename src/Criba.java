// Tercera rama: código documentado

import java.util.Scanner;

/**
 * Clase Criba:
 * Esta clase estática permite generar vectores de números primos mediante la criba de Erastótenes.
 * @author Jordi G.G. (1º DAM/DAW)
 * @version 1.0
 * @see <a href="https://es.wikipedia.org/wiki/Criba_de_Erat%C3%B3stenes">Criba de Erastótenes</a>
 */
public class Criba {

    /**
     * Principal y única función pública de la clase. Genera un vector de números primos.
     * @param max Parámetro para indicar qué número es el límite para la generación de números primos.
     * @return Devuelve el vector de números primos generado.
     */
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

    /**
     * Función privada que crea un vector de booleanos para poder hallar los números primos.
     * @param dim Tamaño del vector de booleanos que se va a crear para la criba.
     * @return Devuelve el vector de booleanos, con los números primos aún sin hallar.
     */
    private static boolean[] crearBooleanos(int dim) {
        boolean[] esPrimo = new boolean[dim];
        for (int i = 0; i < dim; i++) {
            esPrimo[i] = i >= 2;
        }
        return esPrimo;
    }

    /**
     * Función privada que tacha los números no primos, a partir de un vector de booleanos.
     * @param esPrimo El vector de booleanos a partir del cual hallar los números primos.
     */
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

    /**
     * Función privada que cuenta la cantidad de números primos, en base a un vector de booleanos.
     * @param esPrimo El vector de booleanos en el cual realizar la cuenta.
     * @return Un valor int con el resultado de la cuenta.
     */
    private static int contarPrimos(boolean[] esPrimo) {
        int cuenta = 0;
        for (boolean b : esPrimo) {
            cuenta += b ? 1 : 0;
        }
        return cuenta;
    }

    /**
     * Función privada que devuelve el resultado de la criba, mediante un vector con los números primos hallados.
     * @param esPrimo El vector de booleanos en base al cual se creará el vector.
     * @return Devuelve el vector de números primos hallados.
     */
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

    /**
     * Función principal del programa, que pide al usuario un número tope para el algoritmo de generación.
     * @param args Argumentos del programa.
     */
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

    /**
     * Función privada que ayuda a la impresión de líneas para el programa.
     * @param i Índice de iteración en un bucle.
     */
    private static void imprimirLinea(int i) {
        if (i % 10 == 0) {
            System.out.println();
        }
    }


}