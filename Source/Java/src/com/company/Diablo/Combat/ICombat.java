package com.company.Diablo.Combat;

/**
 * Created by christian on 04/02/15.
 */

/**
 * The Combat-interface contains methods that defines how the object implementing this interface, can react to attacks in combat.
 */
public interface ICombat {

    /**
     * The implementation of this method describes how the character reacts to,
     * and defends an attack, and returns the real damage actually dealt.
     *
     * @param attack The attack to be defended.
     * @return A float-value of real damage dealt, which is also the health lost.
     */
    public float defend(Attack attack);
}
