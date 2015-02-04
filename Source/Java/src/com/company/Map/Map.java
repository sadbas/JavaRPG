package com.company.Map;

import com.company.Character;
import com.company.Enumerations.Direction;
import com.company.GameObjects.GameObject;
import com.company.GameObjects.GameObjectFactory;

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
                            this.map[x][y] = new MapObject(s, character);
                        } else {
                            MapSymbol symbol = MapSymbol.fromString(s);
                            Object obj = null;
                            try {
                                obj = GameObjectFactory.objectFromMapSymbol(symbol);
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

    public Map(String name, com.company.Character character) {
        loadMap(name, character);
    }

    public int numberOfRows() {
        return this.map.length;
    }

    public int numberOfColumns() {
        return this.map[0].length;
    }

    public MapObject objectAtPoint(Point point) {
        return (MapObject) this.map[point.x][point.y];
    }

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
    public Object move(Object obj, Direction direction) {
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

        // Move (swap) objects
        this.map[point.x][point.y] = destinationMapObject;
        this.map[newPoint.x][newPoint.y] = movingMapObject;

        return destinationMapObject.getObject();
    }
}
