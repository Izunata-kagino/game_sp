package com.spgame.game_sp.entity.mob;

import com.spgame.game_sp.graphics.Screen;
import com.spgame.game_sp.graphics.Sprite;
import com.spgame.game_sp.input.Keyboard;

import java.security.Key;

public class Player extends Mob{

    private int tick = 0;
    public boolean isWalking = false, cachedWalking = false;
    private Keyboard input;

    private Sprite[] sprites;
    public Player(Keyboard input){
        this.input = input;
    }

    public Player(int x , int y, Keyboard input){
        this.x = x;
        this.y = y;
        this.input = input;
    }

    public void update(){
        int xa = 0, ya = 0;
        if (input.up)ya--;
        if (input.down)ya++;
        if (input.left)xa--;
        if (input.right)xa++;

        if (xa!=0 || ya != 0) {
            move(xa, ya);
            isWalking = true;
        }
        else isWalking = false;
    }

    public void render(Screen screen){
        if (cachedDir != dir || cachedWalking != isWalking) tick = 0;
        if (!isWalking) {
            sprites = Sprite.playerIDLE;
        }
        else {
            if (dir == 0) sprites = Sprite.playerUP;
            else if (dir == 2) sprites = Sprite.playerDOWN;
            else if (dir == 1) sprites = Sprite.playerRIGHT;
            else if (dir == 3) sprites = Sprite.playerLEFT;
        }
        tick ++;
        if (tick == 1024*8) tick = 0;
        screen.renderPlayer(
                x - Sprite.playerUP[0].SIZE/2,
                y - Sprite.playerUP[0].SIZE/2,
                sprites[tick >> 11]);
        cachedDir = dir;
        cachedWalking = isWalking;

    }

}
