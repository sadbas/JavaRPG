package com.company;

import com.company.BaseCharacter;
import com.company.Enumerations.CharacterClass;
import com.company.Enumerations.Direction;

/**
 * Created by Sadik on 02-02-2015.
 */

public class Character extends BaseCharacter {

    private CharacterClass characterClass;
    private Inventory inventory;
    private Map<GearPosition, Item> gear;

    public void setName(String name) {
        this.name = name;
    }

    public CharacterClass getCharacterClass() {
        return this.characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public void move(Direction direction) {
    }

    public boolean attack() {
    }

    public void useSkill(Skill skill) {
    }

    public void drinkPotion() {
    }
}
