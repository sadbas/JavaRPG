package com.company;

import com.company.BaseCharacter;
import com.company.Enumerations.CharacterClass;
import com.company.Enumerations.Direction;

/**
 * Created by Sadik on 02-02-2015.
 */

public class Character extends BaseCharacter {

    private CharacterClass characterClass;
    //private Inventory inventory;
    //private Map<GearPosition, Item> gear;

    public void setName(String name) {
        this.name = name;
    }

    public CharacterClass getCharacterClass() {
        return this.characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
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

    public void drinkPotion() {
    }
}
