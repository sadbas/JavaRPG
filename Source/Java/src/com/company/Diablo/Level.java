package com.company.Diablo;

/**
 * Created by Sadik on 03-02-2015.
 */

/**
 * The Level-class describes experience requirements for each level,
 * and provides methods for getting a level from a number of experience points,
 * and for getting the required experience to reach a certain level.
 */
public class Level {

    private static final int[] levels = {1200, 2700, 4500, 6600, 9000, 11700, 14700, 18750, 23200, 28050};

    /**
     * Returns a level from a given number of experience points.
     *
     * @param experience The amount of experience to be translated into a level.
     * @return The level as an integer-value.
     */
    public static int levelFromExperience(int experience) {

        for (int i = 0; i < levels.length; i++) {
            if (experience < levels[i]) {
                return i + 1;
            }
        }

        return 1;
    }

    /**
     * Returns the experience required to reach a given level.
     *
     * @param level The level from which to get the experience point required.
     * @return An integer-value describing the required amount of experience to reach the level, or -1 if the level provided is not a valid value.
     */
    public static int requiredExperienceForLevel(int level) {

        if (level < 1 || level > levels.length - 1) {
            return -1;
        }

        return levels[level - 1];
    }
}
