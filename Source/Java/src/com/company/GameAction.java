package com.company;

/**
 * Created by christian on 03/02/15.
 */

/**
 * This enum contains all possible actions that a character can perform during the game.
 * <p/>
 * Use the toString() method on a constant to get the corresponding key-press to perform that action.
 */
public enum GameAction {

    // Movement
    MoveUp("w"),
    MoveLeft("a"),
    MoveDown("s"),
    MoveRight("d"),

    // Misc.
    DrinkPotion("q"),

    // Combat
    Attack("a"),
    Skill1("1"),
    Skill2("2"),
    Skill3("3"),
    Skill4("4");

    private final String stringValue;

    private GameAction(final String s) {
        stringValue = s;
    }

    public String toString() {
        return stringValue;
    }
}
