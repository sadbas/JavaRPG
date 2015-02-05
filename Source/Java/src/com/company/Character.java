package com.company;

import com.company.Combat.Attack;
import com.company.Enumerations.CharacterClass;

/**
 * Created by Sadik on 02-02-2015.
 */

/**
 * The Character-class represents a players character.
 */
public class Character extends BaseCharacter {

    //region Properties
    private CharacterClass characterClass;
    //private Inventory inventory;
    //private Map<EquipmentPosition, Item> Equipment;
    //endregion

    //region Constructors

    /**
     * Designated constructor for creating a character with a class and a name.
     *
     * @param characterClass The characters class. See <code>CharacterClass</code>.
     * @param name The name of the character.
     */
    public Character(CharacterClass characterClass, String name) {
        super();

        this.characterClass = characterClass;
        this.name = name;
        this.level = 1;
        this.damage = baseDamage();
        this.experience = new Experience();
        this.health = new Health();
        this.health.health = baseHealth();
        this.health.maxHealth = baseHealth();
    }
    //endregion

    //region Accessors
    /**
     * Returns the characters damage.
     *
     * @return The damage of the character.
     */
    public float getDamage() {
        return this.damage;
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

    //region Attribute modifiers
    /**
     * Add an amount of gold to the characters inventory.
     *
     * @param gold An amount of gold to be added.
     */
    public void addGold(int gold) {
        this.gold += gold;
    }

    /**
     * Add an amount of experience to the character, and level up if needed.
     * If the character reaches a new level, it's attributes, damage, health, etc. are also increased.
     * Returns a boolean value indicating if the character reached next level.
     *
     * @param experience An amount of experience to be added.
     * @return True if character reached a new level, or false.
     */
    public boolean addExperience(int experience) {
        this.experience.experience += experience;

        if (Level.levelFromExperience(this.experience.experience) > this.level) {
            levelUp();

            return true;
        }

        return false;
    }

    /**
     * Level up the character and increase it's attributes accordingly.
     */
    private void levelUp() {
        this.level++;

        // Increase attributes
        // TODO Add attributes (Strength, Dexterity, Intelligence, Energy)

        // Increase damage
        this.damage = baseDamage() * level * 1.6f;

        // Increase health
        float health = baseHealth() * level * 1.3f;
        this.health.health = health;
        this.health.maxHealth = health;
    }
    //endregion

    //region IBaseCharacter
    public float baseHealth() {
        return 70.0f;
    }

    public float baseDamage() {
        return 4.0f;
    }
    //endregion

    //region IInteraction
    public boolean isPassable() {
        return false;
    }

    public boolean isAttackable() {
        return false;
    }
    //endregion

    //region ICombat
    public float defend(Attack attack) {
        float damageTaken = attack.getDamage();

        this.health.health -= damageTaken;

        return damageTaken;
    }
    //endregion

    //region Actions
    /**
     * Drink potion to replenish 50% of maximum health, or no more than maximum health.
     *
     * @return A float-value indicating how much health was regained.
     */
    public float drinkPotion() {
        float previousHealth = this.health.health;

        this.health.health += this.health.maxHealth * 0.5f;

        if (this.health.health > this.health.maxHealth) {
            this.health.health = this.health.maxHealth;
        }

        return this.health.health - previousHealth;
    }
    //endregion

    /**
     * Kills the character, effectively resetting it's resources (health and mana).
     */
    public void die() {
        this.health.health = this.health.maxHealth;
        //this.mana = this.mana.maxMana;
    }
}
