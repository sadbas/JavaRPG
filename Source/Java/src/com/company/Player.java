package com.company;

import com.company.Enumerations.CharacterClass;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Sadik on 02-02-2015.
 */

public class Player {

    private static Scanner input = new Scanner(System.in);

    public String name;
    public int age;
    private ArrayList<Character> characters;

    public Player() {
        this.characters = new ArrayList<Character>();
    }

    public ArrayList<Character> getCharacters() {
        return this.characters;
    }

    public void addCharacter() {
        System.out.println("New Character:");

        // Character Class
        System.out.println("Select Class:");
        System.out.println("[R]ogue");
        System.out.println("[S]orcerer");
        System.out.println("[W]arrior");

        String c = input.next();
        CharacterClass characterClass = CharacterClass.Warrior;
        if (c.equals("r")) {
            characterClass = CharacterClass.Rogue;
        } else if (c.equals("s")) {
            characterClass = CharacterClass.Sorcerer;
        } else if (c.equals("w")) {
            characterClass = CharacterClass.Warrior;
        }

        // Name
        System.out.print("Name: ");
        String name = input.next();

        // Create character and add to players list of characters
        this.characters.add(new Character(characterClass, name));
    }

    public void deleteCharacter(Character character) {
        for (Character c : this.characters) {
            if (c.name.equals(character.getName())) {
                this.characters.remove(c);
                break;
            }
        }
    }
}