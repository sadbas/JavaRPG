package com.company.Diablo.Combat;

import com.company.*;
import com.company.Diablo.Character;
import com.company.Diablo.GameAction;
import com.company.Diablo.Helpers.Console;
import com.company.Diablo.Monster;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by christian on 02/02/15.
 */

/**
 * The Combat Scene controls combat between the character and a monster.
 * <p/>
 * When an instance of combat is created, the scene will be loaded,
 * and the properties of the monster will be adjusted according to the characters level.
 * <p/>
 * After instantiating this class you must call <code>fight()</code> to enter combat, and render the scene.
 */
public class CombatScene {

    //region Properties
    private static final Scanner input = new Scanner(System.in);

    private Character character;
    private Monster monster;
    //endregion

    /**
     * Designated Constructor for creating an CombatScene with a character and an opponent monster.
     *
     * @param character The instance of the players character.
     * @param monster The instance of the enemy monster to fight against.
     */
    public CombatScene(Character character, Monster monster) {
        this.character = character;
        this.monster = monster;

        loadScene();

        // Calculate attributes for monster
        monster.setLevel(character.getLevel());
    }

    /**
     * Loads the scene, and prepares all participants.
     */
    private void loadScene() {

        // Calculate attributes for monster
        monster.setLevel(character.getLevel());
    }

    /**
     * Start the fight.
     * <p>
     * This enters combat, renders the scene and handles each participants turn based on user input.
     * A fight will go on until one of the participants dies.
     *
     * @return True if character wins the fight i.e. the monster dies, or false if character dies.
     */
    public boolean fight() {

        while (character.getHealth().health > 0) {

            renderScene();

            // Handle effects

            // If stunned, there's a 80% chance it will be removed
            /*if (character.isStunned) {
                Random ran = new Random();
                if (ran.nextFloat() <= 0.80) {
                    character.isStunned = false;
                    Console.println("You are no longer stunned.");
                }
            }*/
            if (monster.isStunned) {
                Random ran = new Random();
                if (ran.nextFloat() <= 0.80) {
                    monster.isStunned = false;
                    Console.println(monster.getName() + " is no longer stunned.");
                }
            }

            // Players Turn
            if (!character.isStunned) {
                Console.print("Your turn: ");
                String action = input.next();

                // TODO Use Switch (Not available on OS X - Java SDK 1.6)
                if (action.equals(GameAction.Skill1.toString())) {
                    Attack attack = new Attack(character.getDamage(), false);
                    float damageTaken = monster.defend(attack);
                    Console.println("Your attack did " + damageTaken + " damage.");
                } else if (action.equals(GameAction.Skill2.toString())) {
                    //TODO Skill skill = character.skills[0]
                    Attack attack = new Attack(character.getDamage() * 2.5f, false);
                    float damageTaken = monster.defend(attack);
                    Console.println("Your attack did " + damageTaken + " damage.");

                    // Enemy has 30% chance to reflect some damage
                    Random ran = new Random();
                    if (ran.nextFloat() <= 0.30) {
                        float reflectedDamage = character.defend(new Attack(damageTaken * 0.5f, false));
                        Console.println(monster.getName() + " reflected " + reflectedDamage + " damage.");
                    }
                } else if (action.equals(GameAction.Skill3.toString())) {
                    // Enemy has 20% chance to dodge the attack
                    Random ran = new Random();
                    if (ran.nextFloat() <= 0.20) {
                        Console.println(monster.getName() + " dodged the attack!");
                    } else {
                        Attack attack = new Attack(character.getDamage() * 1.5f, true);
                        float damageTaken = monster.defend(attack);
                        Console.println("Your attack did " + damageTaken + " damage.");
                        monster.isStunned = true;
                        Console.println("Monster was stunned.");
                    }
                } else if (action.equals(GameAction.DrinkPotion.toString())) {
                    //TODO Search inventory to see if character has potion.
                    //TODO Can only heal if character has potion
                    float health = character.drinkPotion();
                    Console.println("You regained " + health + " health.");
                }
            } else {
                Console.println("You're unable to move.");
            }

            if (monster.isDead()) {
                Console.newln();
                Console.newln();
                Console.println("You defeated " + monster.getName() + "!");

                // Rewards

                // You receive experience for killing a monster
                // as long as your own level is below the monster lvl + 10
                if (character.getLevel() < (monster.getLevel() + 10)) {
                    int experienceGained = monster.experienceGiven();
                    boolean levelUp = character.addExperience(experienceGained);
                    Console.println("You gained " + experienceGained + " experience.");

                    if (levelUp) {
                        Console.println("You reached level  " + character.getLevel() + "!");
                    }
                    sleep(1000);
                }

                // There is a 30% possibility that the monster will drop an amount of gold
                Random ran = new Random();
                if (ran.nextFloat() <= 0.30) {
                    int goldDropped = monster.goldDropped();
                    character.addGold(goldDropped);
                    Console.println("The monster dropped " + goldDropped + " gold.");
                }
                sleep(2000);

                return true;
            } else {
                // Monsters turn
                if (!monster.isStunned) {
                    Attack attack = new Attack(monster.getDamage(), false);
                    float damageTaken = character.defend(attack);
                    Console.println(monster.getName() + " attacked you with " + damageTaken + " damage.");
                } else {
                    Console.println(monster.getName() + " cannot move!");
                }

                if (character.isDead()) {
                    return false;
                }
            }

            // Sleep so we can read event before the scene is rendered again.
            sleep(3000);
        }

        return false;
    }

    /**
     * Renders and displays the scene.
     */
    private void renderScene() {
        Console.clear();

        Console.println(" *---------------------------------------------------------------*");
        Console.println(" |                                                               |");
        Console.println(" *                                                               *");

        // Render Monster
        Console.println("     " + this.monster.getName());
        Console.println("     " + "Level: " + this.monster.getLevel());
        Console.println("     " + "Health: " + (int) this.monster.getHealth().health + "/" + (int) this.monster.getHealth().maxHealth);
        Console.newln();
        Console.newln();

        // Render Character
        Console.println("                                       " + this.character.getName());
        Console.println("                                       Level: " + this.character.getLevel());
        Console.println("                                       Health: " + (int) this.character.getHealth().health + "/" + (int) this.character.getHealth().maxHealth);
        Console.newln();
        Console.newln();

        // Render Actions
        Console.println("                    *-----------------------*                     ");
        Console.println("                    | [1] Attack | [2] Bash |                     ");
        Console.println("                    | [3] Stun   | [Q] Heal |                     ");
        Console.println(" *                  *-----------------------*                    *");
        Console.println(" |                                                               |");
        Console.println(" *---------------------------------------------------------------*");
        Console.newln();
    }

    /**
     * Convenience-method for sleeping (waiting) for a specified number of milliseconds.
     *
     * @param ms A number of milliseconds to sleep.
     */
    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
