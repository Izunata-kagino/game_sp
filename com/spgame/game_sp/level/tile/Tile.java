package com.spgame.game_sp.level.tile;

import com.spgame.game_sp.graphics.Screen;
import com.spgame.game_sp.graphics.Sprite;

public class Tile {

    public int x, y;
    public Sprite sprite;

    public static Tile grass1 = new GrassTile(Sprite.grass[0]);
    public static Tile grass2 = new GrassTile(Sprite.grass[1]);
    public static Tile grass3 = new GrassTile(Sprite.grass[2]);
    public static Tile grass4 = new GrassTile(Sprite.grass[3]);
    public static Tile[] grass = {grass1, grass2, grass3, grass4};
    public static Tile flower = new FlowerTile(Sprite.flower);
    public static Tile rock = new RockTile(Sprite.rock);
    public static Tile voidTile = new VoidTile(Sprite.voidTile);
    public static Tile blue = new Tile(Sprite.blue);

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }

    public void render(int x, int y, Screen screen, int renderDir) {
        screen.renderTile(x << 4, y << 4, this);
    }

    public int getPixels(int xTmp, int yTmp, int renderMod) {
        int[] positionFinal = {xTmp, yTmp};//0 is x and 1 is y
        if (renderMod == 2) {
            positionFinal = turn(positionFinal, 1);
        } else if (renderMod == 3) {
            positionFinal = turn(positionFinal, 2);
        } else if (renderMod == 4) {
            positionFinal = turn(positionFinal, 3);
        } else if (renderMod == 5) {
            positionFinal[0] = this.sprite.SIZE - positionFinal[0] - 1;
        } else if (renderMod == 6) {
            positionFinal[0] = this.sprite.SIZE - positionFinal[0] - 1;
            positionFinal = turn(positionFinal, 1);
        } else if (renderMod == 7) {
            positionFinal[0] = this.sprite.SIZE - positionFinal[0] - 1;
            positionFinal = turn(positionFinal, 2);
        } else if (renderMod == 8) {
            positionFinal[0] = this.sprite.SIZE - positionFinal[0] - 1;
            positionFinal = turn(positionFinal, 3);
        }
        return this.sprite.pixels[positionFinal[0] + positionFinal[1] * this.sprite.SIZE];
    }

    public boolean solid() {
        return false;
    }

    private int[] turn(int[] xyTmp, int angle) {
        int[] tmp = new int[2];
        tmp[0] = xyTmp[0];
        tmp[1] = xyTmp[1];
        if (angle == 1) {
            tmp[0] = this.sprite.SIZE - xyTmp[1] - 1;
            tmp[1] = xyTmp[0];
        }
        ;
        if (angle == 2) {
            tmp[0] = this.sprite.SIZE - xyTmp[0] - 1;
            tmp[1] = this.sprite.SIZE - xyTmp[1] - 1;
        }
        ;
        if (angle == 3) {
            tmp[0] = xyTmp[1];
            tmp[1] = this.sprite.SIZE - xyTmp[0] - 1;
        }
        ;

        return tmp;
    }
}
