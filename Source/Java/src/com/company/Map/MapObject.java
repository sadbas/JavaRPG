package com.company.Map;

/**
 * Created by christian on 03/02/15.
 */

/**
 * This class represents an object on the map,
 * and contains the map-character shown on the map,
 * as well as the object the character is representing.
 */
public class MapObject extends Object {
    private String key;
    private Object obj;

    public MapObject(String key, Object obj) {
        this.key = key;
        this.obj = obj;
    }

    public String getKey() {
        return this.key;
    }

    public Object getObject() {
        return this.obj;
    }
}
