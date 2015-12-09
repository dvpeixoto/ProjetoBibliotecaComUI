package util;

import java.util.Scanner;

/**
 * Classe para recebimento de dados enviados pelo usuário através do teclado
 *
 * @author Tainara Specht
 * @author Diego Peixoto
 */
public class Console {

    /**
     * Método que recebe dados do tipo String
     *
     * @param out recebe uma string
     * @return retorna o valor digitado
     */
    public static String scanString(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextLine());
    }

    /**
     * Método que recebe dados do tipo Int
     *
     * @param out recebe um valor inteiro
     * @return retorna o valor digitado
     */
    public static int scanInt(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextInt());
    }

    /**
     * Método que recebe dados do tipo long
     *
     * @param out recebe um valor inteiro muito maior
     * @return retorna o valor digitado
     */
    public static long scanLong(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextLong());
    }

    /**
     * Método que recebe dados do tipo double
     *
     * @param out recebe um valor "64-bit precision IEEE 754 floating point"
     * @return retorna o valor digitado
     */
    public static double scanDouble(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextDouble());
    }

    /**
     * Método que recebe dados do tipo float
     *
     * @param out recebe um valor "32-bit precision IEEE 754 floating point"
     * @return retorna o valor digitado
     */
    public static float scanFloat(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextFloat());
    }

    /**
     * Método que recebe dados do tipo boolean
     *
     * @param out recebe um valor true' ou 'false"
     * @return retorna o valor digitado
     */
    public static boolean scanBoolean(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.nextBoolean());
    }

    /**
     * Método que recebe dados do tipo objeto
     *
     * @param out recebe um objeto
     * @return retorna o objeto informado
     */
    public static char scanChar(Object out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return (scanner.next().charAt(0));
    }

}
