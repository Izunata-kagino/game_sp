package com.spgame.game_sp.entity.mob;

import com.spgame.game_sp.entity.Entity;
import com.spgame.game_sp.graphics.Sprite;

public class Mob extends Entity{
    protected Sprite sprite;
    protected int dir = 0;
    protected boolean moving = false;

    public void move(){

    }

    public void update(){

    }

    private boolean collision(){
        return false;
    }
}
