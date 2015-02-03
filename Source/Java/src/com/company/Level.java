package com.company;

/**
 * Created by Sadik on 03-02-2015.
 */
public class Level {
    private static final int[] levels = {1200, 2700, 4500, 6600, 9000, 11700, 14700, 18750, 23200, 28050};

    public int levelFromExperience(int experience) {
        for (int i=0 ;i < levels.length; i++){
            if (experience <levels[i]){
                return i+1;
            }
        }
        return 1;
    }
}
