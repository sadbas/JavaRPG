package com.company.GameObjects;

/**
 * Created by christian on 03/02/15.
 */

import com.company.GameObjects.NatureObjects.Ground;
import com.company.GameObjects.NatureObjects.Rock;
import com.company.GameObjects.NatureObjects.Wall;
import com.company.Map.MapSymbol;

import java.rmi.activation.UnknownObjectException;

/**
 * The GameObjectFactory class provides methods to create new Game objects.
 * Instances of GameObject and it's subclasses must be created using the methods provided within this class.
 */
public abstract class GameObjectFactory {

    /**
     * Creates an instance of a GameObject (this class is abstract -
     * the created object will be an instance of a GameObject subclass)
     * from a MapSymbol representing the type of object to create.
     *
     * @param symbol The MapSymbol representing the type of object to create.
     * @return An instance of GameObject (a subclass).
     * @throws UnknownObjectException if the symbol is not associated with an object.
     */
    public static Object objectFromMapSymbol(MapSymbol symbol) throws UnknownObjectException {
        switch (symbol) {
            case Character: {
                // Character must not be instantiated this way
                throw new IllegalArgumentException();
            }
            case Monster: {
                return new Monster();
            }
            // Nature objects
            case Rock: {
                return new Rock();
            }
            case Wall: {
                return new Wall();
            }
            case Ground: {
                return new Ground();
            }

            default: {
                throw new UnknownObjectException("Could not create object of this type.");
            }
        }
    }
}
