package com.company.Helpers;

/**
 * Created by christian on 02/02/15.
 */

/**
 * The Console-class provides methods for writing and reading from the console.
 */
public abstract class Console {

    //region Constants
    private final static int ROWS = 30;
    //endregion

    /**
     * Prints a string.
     *
     * @param s The string to print.
     */
    public static void print(String s) {
        System.out.print(s);
    }

    /**
     * Prints a string followed by a linebreak.
     *
     * @param s The string to print.
     */
    public static void println(String s) {
        System.out.println(s);
    }

    /**
     * Prints an empty line.
     */
    public static void newln() {
        System.out.println();
    }

    /**
     * Imitates clearing the console, by writing blank lines.
     */
    public static void clear() {
        for (int i = 0; i < ROWS; i++) {
            System.out.println();
        }
    }
}
