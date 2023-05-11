package com.spgame.game_sp.level;

import com.spgame.game_sp.graphics.Screen;
import com.spgame.game_sp.level.tile.Tile;

public class Level {

    protected int width, height;
    protected int[] tiles;
    public int[] renderDir;


    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        renderDir = new int[64 * 64];
        generateLevel();
    }

    public Level(String path) {
        loadLevel(path);
    }

    protected void generateLevel() {

    }

    private void loadLevel(String path) {
    }

    public void update() {

    }

    public void time() {

    }

    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        int x_0 = xScroll >> 4;
        int x_1 = (xScroll + screen.width) >> 4;
        int y_0 = yScroll >> 4;
        int y_1 = (yScroll + screen.height) >> 4;// all divide by 16

        for (int y = y_0 - 1; y < y_1 + 1; y++){
            for (int x = x_0 - 1; x < x_1 + 1; x++){
                getTile(x, y).render(x, y, screen);
            }
        }
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y>= height) return Tile.voidTile;
        if (tiles[x + y * width] == 0) return Tile.grass1;
        else if(tiles[x + y * width] == 1) return Tile.grass2;
        else if(tiles[x + y * width] == 2) return Tile.grass3;
        else if(tiles[x + y * width] == 3) return Tile.grass4;
        return Tile.voidTile;
    }


}
