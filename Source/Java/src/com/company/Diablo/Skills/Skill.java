package com.company.Diablo.Skills;

/**
 * Created by christian on 02/02/15.
 */


public abstract class Skill {

    /** The name of the skill. */
    private String name;
    /** The damage is defined as a percentage of the characters damage. */
    private float damage;

    public String getName() {
        return this.name;
    }

    public float getDamage() {
        return this.damage;
    }
}
