package com.company;

import java.util.Random;

/**
 * Created by Sadik on 03-02-2015.
 */
public class Gold {
    private int amount;

    public Gold() {
        Random r = new Random();
        int min = 1;
        int max = 100;

        this.amount = r.nextInt(max - min + 1) + min;
    }

    public int getAmount() {
        return this.amount;
    }
}
