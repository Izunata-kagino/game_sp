package com.spgame.game_sp.graphics;

import com.spgame.game_sp.level.tile.Tile;

import java.util.Arrays;
import java.util.Random;

public class Screen {

    public int width, height;
    private int tilemap_width = 64;
    private int tilemap_height = 64;

    private int xOffset , yOffset;
    public int[] pixels;

    public int[] tiles = new int[tilemap_width * tilemap_height];
    public int[] tiles2;
    private int tiles_size = 32;

    public int render_mod = 0;

    private Random random = new Random();

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

    }

    public void clear() {
        Arrays.fill(pixels, 0);
    }


    public void renderTile(int xPosition, int yPosition, Tile tile){
        xPosition -= xOffset;
        yPosition -= yOffset;
        for (int y = 0 ; y<tile.sprite.SIZE; y++){
            int ya = y + yPosition;
            for (int x = 0 ; x<tile.sprite.SIZE; x++){
                int xa = x + xPosition;
                if (xa < 0 || xa >= width || ya<0 ||ya >= height) continue;
                pixels[xa+ya*width] = tile.sprite.pixels[x+y*tile.sprite.SIZE];
                //System.out.println(xp +" || "+yp);
            }
        }
    }

    public void renderTile(int xPosition, int yPosition, Tile tile,int renderDir){
        xPosition -= xOffset;
        yPosition -= yOffset;
        for (int y = 0 ; y<tile.sprite.SIZE; y++){
            int ya = y + yPosition;
            for (int x = 0 ; x<tile.sprite.SIZE; x++){
                int xa = x + xPosition;
                if (xa < 0 || xa >= width || ya<0 ||ya >= height) continue;
                pixels[xa+ya*width] = tile.getPixels(x,y,renderDir);
                //System.out.println(xp +" || "+yp);
            }
        }
    }

    public void renderPlayer(int xPosition, int yPosition, Sprite sprite){
        xPosition -= xOffset;
        yPosition -= yOffset;
        for (int y = 0 ; y<sprite.SIZE; y++){
            int ya = y + yPosition;
            for (int x = 0 ; x<sprite.SIZE; x++){
                int xa = x + xPosition;
                if (xa < 0 || xa >= width || ya<0 ||ya >= height) continue;
                if (sprite.pixels[x + y * sprite.SIZE] == 0xffFFFFFF) continue;
                pixels[xa+ya*width] = sprite.pixels[x+y*sprite.SIZE];
                //System.out.println(xp +" || "+yp);
            }
        }
    }

    public void renderWarrior(int xPosition, int yPosition, Sprite sprite){
        xPosition -= xOffset;
        yPosition -= yOffset;
        for (int y = 0 ; y<sprite.warriorSheet.hLength; y++){
            int ya = y + yPosition;
            for (int x = 0 ; x<sprite.warriorSheet.wLength; x++){
                int xa = x + xPosition;
                if (xa < 0 || xa >= width || ya<0 ||ya >= height) continue;
                if (sprite.pixels[x+y*sprite.warriorSheet.wLength] == 0xff560b28){
                    System.out.println("?");
                }
                pixels[xa+ya*width] = sprite.pixels[x+y*sprite.warriorSheet.wLength];
                //System.out.println(xp +" || "+yp);
            }
        }
    }

    public void setOffset(int xOffset, int yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }


}
