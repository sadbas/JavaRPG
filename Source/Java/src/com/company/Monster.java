package com.company;

import com.company.Combat.Attack;

import java.util.Random;

/**
 * Created by Sadik on 03-02-2015.
 */

/**
 * The Monster-class represents a monster that the player can fight.
 */
public class Monster extends BaseCharacter {

    private static final float BASE_HEALTH = 24.0f;

    //region Properties
    private int experienceGiven;
    //private Item[] loot;
    //endregion

    //region Constructors

    /**
     * Designated constructor for creating a monster.
     */
    public Monster() {
        super();

        this.name = "Monster";
        this.level = 1;
        this.damage = baseDamage();

        // TODO Make constructor
        this.health = new Health();
        this.health.health = baseHealth();
        this.health.maxHealth = baseHealth();

        this.gold = goldDropped();
        this.experienceGiven = experienceGiven();
    }
    //endregion

    //region Accessors and Modifiers
    /**
     * Returns the damage of the monster.
     *
     * @return The damage as a float value.
     */
    //TODO Move to BaseCharacter
    public float getDamage() {
        return this.damage;
    }

    /**
     * Returns the health of the monster.
     *
     * @return An instance of <code>Health</code>.
     */
    //TODO Move to BaseCharacter
    public Health getHealth() {
        return this.health;
    }

    /**
     * Returns the number of experience points rewarded to the character if the monster is defeated.
     *
     * @return The experience given.
     */
    public int experienceGiven() {
        return 650;
    }

    /**
     * Returns a random amount of gold dropped if the monster is defeated.
     *
     * @return An integer-value representing the amount of gold dropped.
     */
    public int goldDropped() {
        //TODO Move to Gold-constructor
        Random r = new Random();
        int min = 1;
        int max = 30;

        return (r.nextInt(max - min + 1) + min * level);
    }

    /**
     * Sets the level of the monster, and scales it's attributes accordingly.
     *
     * @param level An integer-value representing the level the monster will be.
     */
    public void setLevel(int level) {
        this.level = level;

        this.damage = baseDamage() * level * 1.7f;

        float health = baseHealth() * level * 2.8f;
        this.health.health = health;
        this.health.maxHealth = health;
    }
    //endregion

    //region IBaseCharacter
    public float baseHealth() {
        return 20.0f;
    }

    public float baseDamage() {
        return 5.0f;
    }
    //endregion

    //region IIteraction
    public boolean isPassable() {
        return true;
    }

    public boolean isAttackable() {
        return true;
    }
    //endregion

    //region ICombat
    public float defend(Attack attack) {
        float damageTaken = attack.getDamage();

        this.health.health -= damageTaken;

        return damageTaken;
    }
    //endregion
}
