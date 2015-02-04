package com.company.Helpers;

/**
 * Created by christian on 02/02/15.
 */

public abstract class Console {

    //region Constants
    private final static int ROWS = 30;
    //endregion

    public static void print(String s) {
        System.out.print(s);
    }

    public static void println(String s) {
        System.out.println(s);
    }

    public static void newln() {
        System.out.println();
    }

    public static void clear() {
        for (int i = 0; i < ROWS; i++) {
            System.out.println();
        }
    }

    public static void black() {
        for (int i = 0; i < ROWS; i++) {
            System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        }
    }
}
