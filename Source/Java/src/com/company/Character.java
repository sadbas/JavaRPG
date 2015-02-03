package com.company;

import com.company.Enumerations.Direction;

/**
 * Created by Sadik on 02-02-2015.
 */
public class Character extends BaseCharacter {

    private CharacterClass characterClass;
    //private Inventory inventory;
    //private Map<GearPosition, Item> gear;

    public void setName(String name) {
        this.name =name;
    }

public void move(Direction) {

    }

    /**
     * Perform a basic attack on the opponent.
     *
     * @return A boolean value indicating if the opponent was hit.
     */
    public boolean attack() {
        return true;
    }


    public void useSkill(Skill skill){

}

    /**
     * /Livet ganges med 1,5
     */ Livet må ikke være mere end maxHealth
     */
    public void drinkPotion() {
        this.health *= 1.5f;

        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
}
}
