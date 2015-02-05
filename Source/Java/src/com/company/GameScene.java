package com.company;

import com.company.Combat.CombatScene;
import com.company.Enumerations.Direction;
import com.company.GameObjects.GameObject;
import com.company.Helpers.Console;
import com.company.Map.Map;
import com.company.Map.MapObject;

import java.awt.*;
import java.lang.*;
import java.util.Scanner;

/**
 * Created by christian on 02/02/15.
 */

/**
 * The Game scene controls the main flow in the game, and is responsible for keeping track of the map and rendering it. It also handles movement and other user actions.
 */
public class GameScene {

    private static final Scanner input = new Scanner(System.in);

    private Character character;
    private Map map;

    /**
     * Start a new game with the provided character.
     *
     * @param character A character to be played.
     */
    public GameScene(Character character) {
        this.character = character;

        // Initialize Map
        this.map = new Map("Town", character);

        // Game Run Loop
        while (true) {
            update();
        }
    }

    /**
     * This is the main game loop, which renders the map, and performs actions based on player input.
     */
    private void update() {

        // Update map from last turn
        Console.clear();
        renderMap();

        // Players Turn
        Console.newln();
        Console.println("Use WASD-keys to move around");
        Console.println("What's your move?: ");
        String action = input.next();

        GameObject destinationObject = null;

        // Move
        // TODO Use Switch (Not available on OS X - Java SDK 1.6)
        if (action.equals(GameAction.MoveUp.toString())) {
            destinationObject = this.map.move(character, Direction.North);
        } else if (action.equals(GameAction.MoveLeft.toString())) {
            destinationObject = this.map.move(character, Direction.West);
        } else if (action.equals(GameAction.MoveDown.toString())) {
            destinationObject = this.map.move(character, Direction.South);
        } else if (action.equals(GameAction.MoveRight.toString())) {
            destinationObject = this.map.move(character, Direction.East);
        }

        // Enter Combat-mode if destination point contains Monster
        if (destinationObject instanceof Monster) {
            Console.newln();
            Console.println("A wild monster appeared!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Monster monster = (Monster) destinationObject;

            // Initialize Combat Scene
            CombatScene combatScene = new CombatScene(character, monster);
            boolean won = combatScene.fight();

            if (won) {
                // Remove monster
                this.map.removeObject(monster);
            } else {
                // Die
                character.die();
                this.map.resetCharacter();

                Console.clear();
                Console.println("      You Died.");
                Console.newln();
                Console.newln();
                Console.println("Press any key + Enter to continue..");

                // Wait for input
                input.next();
            }
        }
    }

    /**
     * Renders the map, and prints it to the console.
     */
    private void renderMap() {
        for (int y = 0; y < this.map.numberOfRows(); y++) {
            String row = "";
            for (int x = 0; x < this.map.numberOfColumns(); x++) {
                Point p = new Point(x, y);
                MapObject obj = this.map.objectAtPoint(p);
                row += obj.getKey();
            }
            Console.println(row);
        }
    }
}
