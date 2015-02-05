package com.company.Combat;

/**
 * Created by christian on 04/02/15.
 */

import java.util.Random;

/**
 * The Damage-class acts as a data type, and represents damage as two floating-point values: minimum damage and maximum damage.
 */
public class Damage {

    private float minDamage;
    private float maxDamage;

    /**
     * Constructor for creating an instance of Damage with a minimum and maximum damage.
     *
     * @param minDamage Minimum damage. This must be above 0 and less than <code>maxDamage</code>.
     * @param maxDamage Maximum damage. This must be above 0 and more than <code>minDamage</code>.
     */
    public Damage(float minDamage, float maxDamage) {
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    /**
     * Returns a random value being the damage dealt.
     *
     * @return A random float-value ranging from minDamage to maxDamage.
     */
    public float getDamage() {
        Random r = new Random((long)this.maxDamage);

        return r.nextFloat() + this.minDamage;
    }
}
