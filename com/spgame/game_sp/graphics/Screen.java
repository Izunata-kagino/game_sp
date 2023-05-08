package com.spgame.game_sp.graphics;

import java.util.Arrays;
import java.util.Random;

public class Screen {

    private int width, height;
    private int tilemap_width = 64;
    private int tilemap_height = 64;
    public int[] pixels;

    public int[] tiles = new int[tilemap_width * tilemap_height];
    public int[] tiles2;
    private int tiles_size = 32;

    public int render_mod = 0;

    boolean change = true;

    private Random random = new Random();

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

    }

    public void clear() {
        Arrays.fill(pixels, 0);
    }

    public void render() {


        // go through every pixel
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //draw background
                pixels[x + y * width] = 0xffffff;
                if( x<=15 && y<=15 ) pixels[x + y * width] = Sprite.grass.pixels[x + y * Sprite.grass.SIZE];
            }
        }

        //render mod 1 : random tilemap in random place
        if (render_mod == 1) {
            for (int i = 0; i < 64 * 64; i++) {
                tiles[i] = random.nextInt(0xffffff);
            }
            int start_tile_x = random.nextInt(width - 64 * tiles_size);
            int start_tile_y = random.nextInt(height - 64 * tiles_size);
            //go through a certain area of pixels
            for (int y = 0; y < 64; y++) {
                for (int x = 0; x < 64; x++) {
                    pixels[(start_tile_x + x * tiles_size) +
                            (start_tile_y + y * tiles_size) * width] = tiles[x + y * 64];
                }
            }
        }

        //render mod 2 : whole screen file with tilemap
        if (render_mod == 2) {
            if (change) {
                tiles2 = new int[(width / tiles_size + 1) * (height / tiles_size + 1)];
                change = false;
            }
            //draw tile map
            for (int i = 0; i < (width / tiles_size + 1) * (height / tiles_size + 1); i++) {
                tiles2[i] = random.nextInt(0xffffff);
            }
            //draw every pixel
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    pixels[x + y * width] = tiles2[x / tiles_size + (y / tiles_size) * (width / tiles_size)];
                }
            }
        }

    }

    //for render mod 1
    public void change_tile_size(int size) {
        if (size >= width / 3 || size >= height / 3) return;
        else {
            tiles_size = size;
            change = true;
        }
    }
}
