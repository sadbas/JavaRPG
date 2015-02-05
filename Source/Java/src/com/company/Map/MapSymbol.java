package com.company.Map;

/**
 * Created by Christian on 02/02/15.
 */

/**
 * The MapSymbol-enum contains the characters representing objects,
 * used on the map, and methods for getting the character from an object.
 */
public enum MapSymbol {

    /** Character. */
    Character("C"),
    /** Stash. */
    Stash("S"),
    /** Pet. */
    Pet("P"),
    /** NPC. */
    NPC("N"),
    /** Mercenary. */
    Mercenary("M"),
    /** Monster. */
    Monster("m"),
    /** Rock. */
    Rock("#"),
    /** Wall. */
    Wall("*"),
    /** Entry Point. */
    EntryPoint("="),
    /** Ground. */
    Ground(" ");

    private final String stringValue;

    private MapSymbol(final String s) {
        stringValue = s;
    }

    /**
     * Returns the string value of the enum.
     *
     * @return The character represented by the enum-value.
     */
    public String toString() {
        return stringValue;
    }

    /**
     * Returns the enum constant (MapSymbol) that represents the specified string.
     *
     * @param string A string from which to get a symbol.
     * @return The corresponding MapSymbol, or null if no enum-value represents the specified string.
     */
    public static MapSymbol fromString(String string) {
        for (MapSymbol symbol : MapSymbol.values()) {
            if (symbol.toString().equals(string)) {
                return symbol;
            }
        }

        return null;
    }
}
