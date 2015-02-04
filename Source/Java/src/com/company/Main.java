package com.company;

import com.company.Helpers.Toolbox;

import javax.tools.Tool;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("-- Diablo --");
        System.out.println();
        System.out.println();

        System.out.println("Let's start by creating a Player.");

        // Create new player
        Player player = new Player();
        System.out.print("What's your name? ");
        player.name = input.next();
        System.out.print("How old are you? ");
        player.age = input.nextInt();

        Toolbox.clear();

        ArrayList<Character> characters = player.getCharacters();

        if (characters.size() == 0) {
            System.out.println("  NEW CHARACTER  ");
            System.out.println();

            player.addCharacter();
        }

        Toolbox.clear();

        // Main Menu
        System.out.println("Select Character:");
        Character character;
        for (int i = 0; i < characters.size(); i++) {
            character = characters.get(i);
            System.out.println(i + ") " + character.getName());
        }
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
