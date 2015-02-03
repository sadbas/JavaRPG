package com.company.Map;

/**
 * Created by Christian on 02/02/15.
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
