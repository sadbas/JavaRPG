package com.company.Diablo.GameObjects;

/**
 * Created by christian on 03/02/15.
 */

/**
 * The Interaction-interface contains methods that defines how the object implementing this interface, must react to certain interactions or events, that can occur when playing the game.
 */
public interface IInteraction {

    /**
     * Returns a boolean value determining if the object can be passed.
     *
     * @return True if object can be passed, or false.
     */
    public boolean isPassable();

    /**
     * Returns a boolean value determining if the object can be attacked.
     *
     * @return True if the object can be attacked, or false.
     */
    public boolean isAttackable();
}
