package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Sadik on 02-02-2015.
 */

/**
 * The Player class is used to represent a human player.
 * <p>
 * This class provides methods for adding and deleting game characters.
 */
public class Player {

    /** The name of the player. */
    public String name;

    /** The players age. */
    public int age;

    private static Scanner input = new Scanner(System.in);
    private ArrayList<Character> characters;

    /**
     * Constructor.
     */
    public Player() {
        this.characters = new ArrayList<Character>();
    }

    /**
     * Returns the players characters.
     * <p>
     * The contents of this property can be modified
     * using the <code>addCharacter</code> and <code>deleteCharacter</code> methods.
     *
     * @return An ArrayList containing the players characters.
     */
    public ArrayList<Character> getCharacters() {
        return this.characters;
    }

    /**
     * Add character to players list of characters.
     *
     * @param character An instance of Character which will be added.
     */
    public void addCharacter(Character character) {
        this.characters.add(character);
    }

    /**
     * Delete character from players list of characters, provided it exists.
     * Objects are compared based on the characters name.
     * <p>
     * @warning The Character will be permanently deleted. This action cannot be undone.
     *
     * @param character A Character from the players list of characters.
     */
    public void deleteCharacter(Character character) {
        for (Character c : this.characters) {
            if (c.name.equals(character.getName())) {
                this.characters.remove(c);
                break;
            }
        }
    }
}