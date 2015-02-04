package com.company;

import com.company.Enumerations.Direction;
import com.company.GameObjects.GameObject;
import com.company.Helpers.Toolbox;
import com.company.Map.Map;
import com.company.Map.MapObject;

import java.awt.*;
import java.lang.*;
import java.util.Scanner;

/**
 * Created by christian on 02/02/15.
 */

public class GameScene {

    private static final Scanner input = new Scanner(System.in);

    private Character character;
    private Map map;

    /**
     * Start a game with the provided character.
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

    private void update() {

        // Update map from last turn
        Toolbox.clear();
        renderMap();

        // Players Turn
        System.out.println();
        System.out.println("Use WASD-keys to move around");
        System.out.println("What's your move?: ");
        String action = input.next();

        Point newPoint = new Point();
        // TODO Use Switch (Not available on OS X - Java SDK 1.6)
        if (action.equals(GameAction.MoveUp.toString())) {
            this.map.move(character, Direction.North);
        }
        else if (action.equals(GameAction.MoveLeft.toString())) {
            this.map.move(character, Direction.West);
        }
        else if (action.equals(GameAction.MoveDown.toString())) {
            this.map.move(character, Direction.South);
        }
        else if (action.equals(GameAction.MoveRight.toString())) {
            this.map.move(character, Direction.East);
        }

        // Enter Combat-mode if destination point contains Monster
        GameObject obj = (GameObject)this.map.objectAtPoint(newPoint).getObject();
        /*if (obj.getClass() == Monster.getClass()) {
            // Initialize Combat Scene
            CombatScene combatScene = new CombatScene(character, obj);
            combatScene.fight();
        }*/
    }

    private void renderMap() {
        for (int y = 0; y < this.map.numberOfRows(); y++) {
            String row = "";
            for (int x = 0; x < this.map.numberOfColumns(); x++) {
                Point p = new Point(x, y);
                MapObject obj = this.map.objectAtPoint(p);
                row += obj.getKey();
            }
            System.out.println(row);
        }
    }
}
