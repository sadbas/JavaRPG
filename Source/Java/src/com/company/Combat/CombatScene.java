package com.company.Combat;

import com.company.*;
import com.company.Character;
import com.company.Helpers.Console;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by christian on 02/02/15.
 */

public class CombatScene {

    //region Properties
    private static final Scanner input = new Scanner(System.in);

    private com.company.Character character;
    private Monster monster;
    //endregion

    //region Constructors
    public CombatScene(Character character, Monster monster) {
        this.character = character;
        this.monster = monster;

        loadScene();

        // Calculate attributes for monster
        monster.setLevel(character.getLevel());
    }
    //endregion

    private void loadScene() {

        // Calculate attributes for monster
        monster.setLevel(character.getLevel());
    }

    public boolean fight() {

        while (character.getHealth().health > 0) {

            renderScene();

            // Handle effects

            // If stunned, there's a 90% chance it will be removed
            /*if (character.isStunned) {
                Random ran = new Random();
                if (ran.nextFloat() <= 0.90) {
                    character.isStunned = false;
                    Console.println("You are no longer stunned.");
                }
            }*/

            if (monster.isStunned) {
                Random ran = new Random();
                if (ran.nextFloat() <= 0.90) {
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
            }

            Console.newln();
            sleep(500);

            // Monsters turn
            if (!monster.isStunned) {
                Attack attack = new Attack(monster.getDamage(), false);
                float damageTaken = character.defend(attack);
                Console.println(monster.getName() + " attacked you with " + damageTaken + " damage.");
            } else {
                Console.println(monster.getName() + " is stunned!");
            }

            if (character.isDead()) {
                return false;
            }

            // Sleep so we can read event before the scene is rendered again.
            sleep(3000);
        }

        return false;
    }

    private void renderScene() {
        Console.clear();

        Console.println(" *---------------------------------------------------------------*");
        Console.println(" |                                                               |");
        Console.println(" *                                                               *");

        // Render Monster
        Console.println("     " + this.monster.getName());
        Console.println("     " + "Level: " + this.monster.getLevel());
        Console.println("     " + "Health: " + (int)this.monster.getHealth().health + "/" + (int)this.monster.getHealth().maxHealth);
        Console.newln();
        Console.newln();

        // Render Character
        Console.println("                                       " + this.character.getName());
        Console.println("                                       Level: " + this.character.getLevel());
        Console.println("                                       Health: " + (int)this.character.getHealth().health + "/" + (int)this.character.getHealth().maxHealth);
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

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
