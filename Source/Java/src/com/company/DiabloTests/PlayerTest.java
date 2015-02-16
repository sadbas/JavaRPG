package com.company.DiabloTests;

import com.company.Diablo.Character;
import com.company.Diablo.Enumerations.CharacterClass;
import com.company.Diablo.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    Player player;

    @Before
    public void setUp() {
        this.player = new Player();
    }

    @Test
    public void testPlayer() {
        assertNotNull(this.player.getCharacters());
    }

    @Test
    public void testAddCharacter() {
        Character character = new Character(CharacterClass.Warrior, "");
        this.player.addCharacter(character);

        boolean contains = this.player.getCharacters().contains(character);
        assertTrue(contains);
    }

    @Test
    public void testDeleteCharacter() {
        Character character = new Character(CharacterClass.Warrior, "");
        this.player.addCharacter(character);

        this.player.deleteCharacter(character);

        boolean contains = this.player.getCharacters().contains(character);
        assertFalse(contains);
    }
}