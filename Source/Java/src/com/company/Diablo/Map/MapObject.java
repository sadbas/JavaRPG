package com.company.Diablo.Map;

/**
 * Created by christian on 03/02/15.
 */

/**
 * This class represents an object on the map,
 * and contains the map-character shown on the map,
 * as well as the object the character is representing.
 */
public class MapObject {

    private String key;
    private Object obj;

    /**
     * Constructor for creating an object holding a GameObject and it's corresponding map key.
     * 
     * @param key The key (or character) that is shown on the map.
     * @param obj An instance of GameObject that the key represents.
     */
    public MapObject(String key, Object obj) {
        this.key = key;
        this.obj = obj;
    }

    /**
     * Returns the key of the MapObject.
     *
     * @return The key as a string.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Returns the object of the MapObject.
     *
     * @return The object represented by the MapObject.
     */
    public Object getObject() {
        return this.obj;
    }
}
