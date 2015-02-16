package com.company.DiabloTests;

import com.company.Diablo.Character;
import com.company.Diablo.Combat.Attack;
import com.company.Diablo.Enumerations.CharacterClass;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTests {

    private static final String NAME = "testName";
    private static final CharacterClass ROGUE = CharacterClass.Rogue;
    private static final float DAMAGE = 4.0f;
    private static final float HEALTH = 70.0f;

    Character character;

    @Before
    public void setUp() throws Exception {
        this.character = new Character(ROGUE, NAME);
    }

    @Test
    public void testConstructor() {
        assertEquals(NAME, this.character.getName());
        assertEquals(ROGUE, this.character.getCharacterClass());
        assertEquals(1, this.character.getLevel());
        assertEquals(0, this.character.getGold());
        assertEquals(DAMAGE, this.character.getDamage(), 0f);
        assertEquals(0, this.character.getExperience());
        assertEquals(HEALTH, this.character.getHealth().health, 0f);
        assertEquals(HEALTH, this.character.getHealth().maxHealth, 0f);

        // IInteraction
        assertFalse(this.character.isAttackable());
        assertFalse(this.character.isPassable());
    }

    @Test
    public void testAddGold() {
        int gold = 123;
        this.character.addGold(gold);
        assertEquals(123, this.character.getGold());
    }

    @Test
    public void testAddExperience() {
        int experience = 123;
        this.character.addExperience(experience);
        assertEquals(experience, this.character.getExperience());

        // Test Level up
        int level = this.character.getLevel();
        boolean levelUp = this.character.addExperience(1200);
        assertTrue(levelUp);
        assertEquals(level + 1, this.character.getLevel());

        //TODO: Ask Michael
        // Test new attributes
        // Damage: Base Damage * level * 1.6
        float damage = this.character.baseDamage() * this.character.getLevel() * 1.6f;
        assertEquals(damage, this.character.getDamage(), 0f);

        // Health: Base Health * level * 1.3
        float health = this.character.baseHealth() * this.character.getLevel() * 1.3f;
        assertEquals(health, this.character.getHealth().health, 0f);
        assertEquals(health, this.character.getHealth().maxHealth, 0f);
    }

    @Test
    public void testDefend() {
        float health = this.character.getHealth().health;
        float damage = 10f;
        Attack attack = new Attack(damage, false);
        float damageTaken = this.character.defend(attack);
        assertEquals(health - damage, this.character.getHealth().health, 0f);
        assertEquals(damage, damageTaken, 0f);
    }

    @Test
    public void testDrinkPotion() {
        float previousHealth = 50;
        this.character.getHealth().health = previousHealth;
        this.character.getHealth().maxHealth = 100;
        float replenishedHealth = this.character.drinkPotion();
        assertEquals(80f, this.character.getHealth().health, 0f);
        assertEquals(30, replenishedHealth, 0f);

        // Test value higher than max health
        this.character.drinkPotion();
        assertTrue("Health must not be higher than max health", (this.character.getHealth().health <= this.character.getHealth().maxHealth));

    }

    @Test
    public void testDie() {
        float health = this.character.getHealth().health;
        float maxHealth = this.character.getHealth().maxHealth;
        assertEquals(maxHealth, health, 0f);
    }

    @Test
    public void testIsDead() {
        this.character.getHealth().health = 0;
        assertTrue(this.character.isDead());
    }
}