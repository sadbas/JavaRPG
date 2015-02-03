package com.company;

import com.company.Helpers.Origin;

/**
 * Created by christian on 02/02/15.
 */

public abstract class BaseCharacter {

    protected String name;
    private int level;
    private Experience experience;
    private Health health;
    private Skill[] skills;
    private Origin origin;

    public String getName() {
        return this.name;
    }

    public int getLevel() {
        return this.level;
    }

    public Experience getExperience() {
        return this.experience;
    }

    public Health getHealth() {
        return this.health;
    }
}
