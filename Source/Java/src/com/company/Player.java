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

        Character character = new Character();

        // Set Character Class
        System.out.println("Select Class:");
        System.out.println("[R]ogue");
        System.out.println("[S]orcerer");
        System.out.println("[W]arrior");

        String c = input.next();
        if (c.equals("r")) {
            character.setCharacterClass(CharacterClass.Rogue);
        } else if (c.equals("s")) {
            character.setCharacterClass(CharacterClass.Sorcerer);
        } else if (c.equals("w")) {
            character.setCharacterClass(CharacterClass.Warrior);
        }

        // Set name
        System.out.print("Name: ");
        character.setName(input.next());

        // Add to players list of characters
        this.characters.add(character);
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