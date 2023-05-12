package com.spgame.game_sp.entity.mob;

import com.spgame.game_sp.graphics.Screen;
import com.spgame.game_sp.graphics.Sprite;

public class Warrior extends Mob{

    private int tick = 0;

    private Sprite[] sprites;


    public Warrior(int x , int y){
        this.x = x;
        this.y = y;
        sprites = Sprite.warrior;
    }

    public void update(){
    }

    public void render(Screen screen){
        sprites = Sprite.warrior;
        tick ++;
        if (tick == 1024*5) tick = 0;
//        System.out.println(tick >> 9);
        screen.renderWarrior(
                x - sprites[0].warriorSheet.wLength/2,
                y - sprites[0].warriorSheet.hLength/2,
                sprites[83]);

    }

}