package com.spgame.game_sp.level;

import com.spgame.game_sp.graphics.Screen;

import java.util.Random;

public class RandomLevel extends Level {

    private static final Random random = new Random();
    public int[] renderDir;

    public RandomLevel(int width, int height) {
        super(width, height);
    }

    protected void generateLevel() {
        renderDir = new int[64 * 64];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x + y * width] = random.nextInt(4);
                renderDir[x + y * width] = random.nextInt(1, 9);
            }
        }
    }

    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        int x_0 = xScroll >> 4;
        int x_1 = (xScroll + screen.width) >> 4;
        int y_0 = yScroll >> 4;
        int y_1 = (yScroll + screen.height) >> 4;// all divide by 16

        for (int y = y_0 - 1; y < y_1 + 1; y++) {
            for (int x = x_0 - 1; x < x_1 + 1; x++) {
                getTile(x, y).render(x, y, screen, this.get_renderDir(x, y));
            }
        }
    }

    private int get_renderDir(int x, int y) {
        if (x + y * 64 >= 4096 || x + y * 64 < 0) return 1;
        else return this.renderDir[x + y * 64];
    }
}
