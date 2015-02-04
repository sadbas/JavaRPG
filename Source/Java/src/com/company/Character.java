package com.company;

import com.company.Enumerations.CharacterClass;
import com.company.Enumerations.Direction;

/**
 * Created by Sadik on 02-02-2015.
 */
public class Character extends BaseCharacter {

    private CharacterClass characterClass;
    //private Inventory inventory;
    //private Map<GearPosition, Item> gear;

    public Character(CharacterClass characterClass, String name) {
        this.characterClass = characterClass;
        this.name = name;
        this.level = 1;
        this.experience = new Experience();
        this.baseDamage = 4;
    }

    /**
     * Perform a basic attack on the opponent.
     *
     * @return A boolean value indicating if the opponent was hit.
     */
    public boolean attack() {
        return true;
    }

    public void useSkill(Skill skill) {
    }

    /**
     * Drink potion to replenish 50% of current health, or no more than maximum health.
     */
    public void drinkPotion() {

        //TODO Search inventory to see if character has potion.
        //TODO Can only heal if character has potion

        this.health.health *= 1.5f;

        if (this.health.health > this.health.maxHealth) {
            this.health.health = this.health.maxHealth;
        }
    }
}
