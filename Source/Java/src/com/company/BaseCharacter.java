package com.company;

import com.company.Combat.ICombat;
import com.company.GameObjects.GameObject;
import com.company.Skills.Skill;

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
        this.level = 1;
        this.experience = new Experience();
        //this.skills = {new NormalAttack(), null, null, null};
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
