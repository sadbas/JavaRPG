package com.company.Diablo.Combat;

/**
 * Created by christian on 04/02/15.
 */

/**
 * The Attack-class defines an attack, and contains properties for damage and maybe a number of effects.
 */
public class Attack {

    private float damage;
    private boolean willStun;

    /**
     * Designated Constructor for an Attack.
     *
     * @param damage The damage the attacks deals.
     * @param stun A boolean value determining if the attack has a chance to stun the opponent.
     */
    public Attack(float damage, boolean stun) {
        this.damage = damage;
        this.willStun = stun;
    }

    /**
     * Returns the damage dealt by the attack.
     *
     * @return A float-value representing the amount of damage dealt.
     */
    public float getDamage() {
        return this.damage;
    }

    /**
     * Returns a boolean value determining if the attack has a chance to stun.
     *
     * @return True if the opponent has a chance to be stunned, or false.
     */
    public boolean getWillStun() {
        return this.willStun;
    }
}
