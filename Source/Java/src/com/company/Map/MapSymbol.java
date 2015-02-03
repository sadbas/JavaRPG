package com.company.Map;

/**
 * Created by Christian on 02/02/15.
 */

/**
 * The MapSymbol-enum contains the characters representing objects,
 * used on the map, and methods for getting the character from an object.
 */
public enum MapSymbol {

    Character("C"),
    Stash("S"),
    Pet("P"),
    NPC("N"),
    Mercenary("M"),
    Monster("m"),
    Rock("#"),
    Wall("*"),
    EntryPoint("="),
    Ground(" ");

    private final String stringValue;

    private MapSymbol(final String s) {
        stringValue = s;
    }

    public String toString() {
        return stringValue;
    }

    public static MapSymbol fromString(String string) {
        for (MapSymbol symbol : MapSymbol.values()) {
            if (symbol.toString().equals(string)) {
                return symbol;
            }
        }

        return null;
    }
}
