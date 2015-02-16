package com.company.DiabloTests;

import com.company.Diablo.BaseCharacter;
import com.company.Diablo.Combat.Attack;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BaseCharacterTest {

    BaseCharacter baseCharacter = new BaseCharacter() {
        @Override
        public float baseDamage() {
            return 0;
        }

        @Override
        public float baseHealth() {
            return 0;
        }

        @Override
        public float defend(Attack attack) {
            return 0;
        }

        @Override
        public boolean isPassable() {
            return false;
        }

        @Override
        public boolean isAttackable() {
            return false;
        }
    };

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testIsDead() throws Exception {
        this.baseCharacter.getHealth().health = 10;
        assertFalse(this.baseCharacter.isDead());

        this.baseCharacter.getHealth().health = 0;
        assertTrue(this.baseCharacter.isDead());
    }
}