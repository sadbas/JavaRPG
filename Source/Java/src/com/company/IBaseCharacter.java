package com.company;

/**
 * Created by christian on 04/02/15.
 */

/**
 * The BaseCharacter-interface contains methods that must be implemented by any Character-object,
 * for calculating and retrieving certain properties.
 */
public interface IBaseCharacter {

    /**
     * Returns the base damage of the character.
     *
     * @return The base damage as a float value.
     */
    public float baseDamage();

    /**
     * Returns the base health of the character.
     *
     * @return The base health as a float value.
     */
    public float baseHealth();
}
