package com.company.Diablo.Map;

import com.company.Diablo.Character;
import com.company.Diablo.Enumerations.Direction;
import com.company.Diablo.GameObjects.GameObjectFactory;
import com.company.Diablo.Enumerations.Direction;
import com.company.Diablo.GameObjects.GameObject;
import com.company.Diablo.GameObjects.GameObjectFactory;
import com.company.Diablo.Monster;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.rmi.activation.UnknownObjectException;
import java.util.ArrayList;

/**
 * Created by christian on 02/02/15.
 */

/**
 * This class represents a real-world map (in the game), and has the responsibility for
 * loading a map from a file, populating it with Game-objects, and keeping track of all objects on the map.
 * It also provides methods for moving objects around on the map.
 * <p/>
 * A map is essentially a 2-dimensional array consisting of instances of MapObject,
 * which holds the character shown on the map, and the Game Object that this character represents.
 */
public class Map {

    private MapObject[][] map;

    private MapObject characterObject;
    private Point characterSpawnOrigin = new Point();
    private Point characterOrigin = new Point();
    private Point previousCharacterOrigin = new Point();
    private MapObject lastAffectedObject;

    /**
     * Designated constructor for a map.
     * <p>
     * This method instantiates and loads a new map of the specified name,
     * and populates it with the specified character, along with other objects on the map.
     *
     * @param name The name of the map.
     * @param character The players character.
     */
    public Map(String name, Character character) {
        loadMap(name, character);
    }

    /**
     * Loads and populates a map from file.
     *
     * @param name The name of the map.
     * @param character The players character.
     */
    private void loadMap(String name, Character character) {
        try {
            URL location = Map.class.getProtectionDomain().getCodeSource().getLocation();
            File file = new File(location.getFile() + "/com/company/Resources/Maps/" + name + ".map");

            if (!file.exists() || !file.canRead()) {
                System.out.println("ERROR. File not readable");
            }

            try {
                // Get lines in file
                ArrayList<String> lines = new ArrayList<String>();
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }

                // Initialize map
                int rows = lines.size();
                int columns = lines.get(0).length();
                this.map = new MapObject[rows][columns];

                // Parse rows and columns
                for (int y = 0; y < rows; y++) {
                    char[] row = lines.get(y).toCharArray();
                    for (int x = 0; x < columns; x++) {

                        // Populate map
                        String s = String.valueOf(row[x]);
                        if (s.equals((MapSymbol.Character.toString()))) {
                            this.characterObject = new MapObject(s, character);
                            this.map[x][y] = this.characterObject;

                            this.characterSpawnOrigin = new Point(x, y);
                            this.characterOrigin = new Point(x, y);
                        } else {
                            MapSymbol symbol = MapSymbol.fromString(s);
                            Object obj = null;
                            try {
                                obj = GameObjectFactory.objectFromMapSymbol(symbol);
                                System.out.println(x + " " + y);
                                this.map[x][y] = new MapObject(s, obj);
                            } catch (UnknownObjectException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the number of rows on the map.
     *
     * @return Number of rows on the map.
     */
    public int numberOfRows() {
        return this.map.length;
    }

    /**
     * Returns the number of columns on the map.
     *
     * @return Number of columns on the map.
     */
    public int numberOfColumns() {
        return this.map[0].length;
    }

    /**
     * Returns the object at the specified point on the map.
     *
     * @param point The point where the object resides.
     * @return The object at the specified point.
     */
    public MapObject objectAtPoint(Point point) {
        return (MapObject) this.map[point.x][point.y];
    }

    /**
     * Returns the point for a specified object on the map, if found.
     *
     * @param obj The object to find.
     * @return The point on the map where the object is located, or null if object is not found.
     */
    private Point pointForObject(Object obj) {
        for (int y = 0; y < numberOfRows(); y++) {
            for (int x = 0; x < numberOfColumns(); x++) {
                MapObject mapObject = (MapObject) this.map[x][y];
                if (mapObject.getObject() == obj) {
                    return new Point(x, y);
                }
            }
        }

        return null;
    }

    /**
     * Moves the given object one point in the specified direction,
     * and returns the object at the destination before moving,
     * or null if a move was not possible e.g. the object was not found,
     * or there is something blocking the path.
     *
     * @param obj       The object to move. This will usually be the Character.
     * @param direction The direction the object should be moved.
     * @return The object that was at the destination point before moving, or null if the object could not move.
     */
    public GameObject move(Object obj, Direction direction) {
        Point point = pointForObject(obj);
        MapObject movingMapObject = objectAtPoint(point);
        Point newPoint = new Point();

        // Calculate new point
        switch (direction) {
            case North:
                newPoint = new Point(point.x, point.y - 1);
                break;
            case West:
                newPoint = new Point(point.x - 1, point.y);
                break;
            case South:
                newPoint = new Point(point.x, point.y + 1);
                break;
            case East:
                newPoint = new Point(point.x + 1, point.y);
                break;
        }

        // Get map object at destination point
        MapObject destinationMapObject = (MapObject) objectAtPoint(newPoint);
        if (!((GameObject) destinationMapObject.getObject()).isPassable()) {
            return null;
        }

        // Check if new point will be out of bounds
        if (newPoint.x < 0 || newPoint.x > numberOfColumns() ||
                newPoint.y < 0 || newPoint.y > numberOfRows()) {
            return null;
        }

        // Don't move if monster
        if (!((GameObject) destinationMapObject.getObject() instanceof Monster)) {
            // Move (swap) objects
            this.map[point.x][point.y] = destinationMapObject;
            this.map[newPoint.x][newPoint.y] = movingMapObject;
            lastAffectedObject = destinationMapObject;

            updateCharacterOrigin();
        }

        return (GameObject) destinationMapObject.getObject();
    }

    /**
     * Removes an object from the map, and replaces it with a Ground-object.
     * This method is almost exclusively used to remove monsters when they die.
     *
     * @param obj The object to remove.
     */
    public void removeObject(Object obj) {
        Point p = pointForObject(obj);
        try {
            Object ground = GameObjectFactory.objectFromMapSymbol(MapSymbol.Ground);
            this.map[p.x][p.y] = new MapObject(MapSymbol.Ground.toString(), ground);
        } catch (UnknownObjectException e) {
            e.printStackTrace();
        }
    }

    /**
     * Swaps two objects on the map.
     *
     * @param obj1 First object.
     * @param obj2 Seconds object.
     */
    private void swapObjects(MapObject obj1, MapObject obj2) {
        Point obj1Point = pointForObject(obj1);
        Point obj2Point = pointForObject(obj2);

        this.map[obj1Point.x][obj1Point.y] = obj2;
        this.map[obj2Point.x][obj2Point.y] = obj1;

        updateCharacterOrigin();
    }

    /**
     * Updates properties holding character positions.
     * Used in <code>resetCharacter()</code>.
     */
    private void updateCharacterOrigin() {
        this.previousCharacterOrigin = this.characterOrigin;
        this.characterOrigin = pointForObject(this.characterObject.getObject());
    }

    /**
     * Moves the character to the spawn point (in town).
     * @warning This method must only be invoked when a character dies.
     */
    public void resetCharacter() {
        this.map[characterSpawnOrigin.x][characterSpawnOrigin.y] = this.characterObject;
        this.map[characterOrigin.x][characterOrigin.y] = this.lastAffectedObject;
        updateCharacterOrigin();
    }
}
