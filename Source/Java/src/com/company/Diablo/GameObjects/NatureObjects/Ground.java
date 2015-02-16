package com.company.Diablo.GameObjects.NatureObjects;

/**
 * Created by christian on 03/02/15.
 */

/**
 * This class represents the ground.
 */
public class Ground extends NatureObject {

    public boolean isPassable() {
        return true;
    }

    public boolean isAttackable() {
        return false;
    }
}
