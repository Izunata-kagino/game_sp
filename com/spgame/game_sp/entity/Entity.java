package com.spgame.game_sp.entity;

import com.spgame.game_sp.graphics.Screen;
import com.spgame.game_sp.level.Level;

import java.util.Random;

public abstract class Entity {

    public int x ,y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();

    public void update(){

    }

    public void render(Screen screen){

    }

    public void remove(){

    }

    public boolean isRemoved(){
        return removed;
    }
}
