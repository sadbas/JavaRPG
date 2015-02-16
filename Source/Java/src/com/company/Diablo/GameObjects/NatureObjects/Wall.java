package com.company.Diablo.GameObjects.NatureObjects;

/**
 * Created by christian on 03/02/15.
 */

/**
 * This class represents a Nature object in the form of a Wall.
 */
public class Wall extends NatureObject {

    public boolean isPassable() {
        return false;
    }

    public boolean isAttackable() {
        return false;
    }
}
