package com.spgame.game_sp.level.tile;

import com.spgame.game_sp.graphics.Screen;
import com.spgame.game_sp.graphics.Sprite;

public class Tile {

    public int x,y;
    public Sprite sprite;

    public static Tile grass1 = new GrassTile(Sprite.grass0);
    public static Tile grass2 = new GrassTile(Sprite.grass1);
    public static Tile grass3 = new GrassTile(Sprite.grass2);
    public static Tile voidTile = new VoidTile(Sprite.voidTile);
    public static Tile blue = new Tile(Sprite.blue);

    public Tile(Sprite sprite){
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen){

    }

    public boolean solid(){
        return false;
    }
}
