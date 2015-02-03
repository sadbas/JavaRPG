package com.company;

/**
 * Created by christian on 02/02/15.
 */

public class Health {
    private int health;
    private int maxHealth;

    public Health(int health, int maxHealth) {
        this.health = health;
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return this.health;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }
}
