package com.company;

import com.company.Enumerations.CharacterClass;
import com.company.Helpers.Console;

import javax.tools.Tool;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Console.println("-- Diablo --");
        Console.newln();
        Console.newln();

        // Create new player
        Console.println("NEW PLAYER");
        Player player = new Player();
        Console.print("What's your name? ");
        player.name = input.next();
        Console.print("How old are you? ");
        player.age = input.nextInt();

        Console.clear();

        ArrayList<Character> characters = player.getCharacters();

        if (characters.size() == 0) {
            Console.println("NEW CHARACTER");
            Console.newln();

            // Character Class
            Console.println("Select Class:");
            Console.println("[R]ogue");
            Console.println("[S]orcerer");
            Console.println("[W]arrior");

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

            player.addCharacter(new Character(characterClass, name));
        }

        Console.clear();

        // Main Menu
        //TODO Store characters in file
        //TODO Make it possible to select between all created characters
        //Console.println("Select Character:");
        Character character;
        /*for (int i = 0; i < characters.size(); i++) {
            character = characters.get(i);
            Console.println(i + ") " + character.getName());
        }*/
        //System.out.println("[A]dd Character | [D]elete Character");

        character = player.getCharacters().get(0);

        GameScene gameScene = new GameScene(character);

        /*if (input.next().equals("a")) {
            // Add new character
            player.addCharacter();
        } else if (input.next().equals("d") && character) {
            // Delete selected character
            player.deleteCharacter(character);
        }*/
    }
}
