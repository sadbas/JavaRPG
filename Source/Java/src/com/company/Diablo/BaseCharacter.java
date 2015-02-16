package com.company.Diablo;

import com.company.Diablo.Combat.ICombat;
import com.company.Diablo.Skills.Skill;
import com.company.Diablo.GameObjects.GameObject;
import com.company.Diablo.Skills.Skill;

/**
 * Created by christian on 02/02/15.
 */

public abstract class BaseCharacter extends GameObject implements IBaseCharacter, ICombat {

    protected String name;
    protected int level;
    protected Experience experience;
    protected int gold;
    protected float damage;
    protected Health health;
    protected Skill[] skills;
    public boolean isStunned;

    protected BaseCharacter() {
        this.name = "";
        this.experience = new Experience();
        this.health = new Health();
    }

    //region Accessors
    /**
     * Returns the characters name.
     *
     * @return The name of the character.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the characters level.
     *
     * @return The level of the character.
     */
    public int getLevel() {
        return this.level;
    }

    public int getExperience() {
        return this.experience.experience;
    }

    /**
     * Returns the characters health.
     *
     * @return An instance of <code>Health</code>.
     */
    public Health getHealth() {
        return this.health;
    }
    //endregion

    /**
     * Returns a boolean value determining if the character is dead.
     *
     * @return True if the character is dead, or false.
     */
    public boolean isDead() {
        return health.health <= 0 ? true : false;
    }
}
