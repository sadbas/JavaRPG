package com.company;

/**
 * Created by WIN4 on 03-02-2015.
 */
public class Monster extends BaseCharacter{
    //private Item[] loot;
    private Gold gold;

    public Monster(){
        this.name="Monster"
    this.gold=new Gold();
        this.health=new Health(100,100);
        this.damage=5

    }
}
