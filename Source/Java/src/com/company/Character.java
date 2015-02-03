package com.company;

import com.company.Enumerations.Direction;

/**
 * Created by Sadik on 02-02-2015.
 */
public class Character extends BaseCharacter {
    private Inventory inventory;
    private gear Map<GearPosition, Item>;

    public void setName(String name) {
        this.name =name;
    }

public void move(Direction) {

    }

    public boolean attack() {
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
