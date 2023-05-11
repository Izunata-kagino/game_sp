package com.spgame.game_sp.level;

import com.spgame.game_sp.graphics.Screen;
import com.spgame.game_sp.graphics.Sprite;
import com.spgame.game_sp.level.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class SpawnLevel extends Level{

    private Tile[] tiles;
    private int[] levelPixels;
    private Random random;

    private int w,h;

    public SpawnLevel(String path) {
        super(path);
    }

    protected void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
            w = image.getWidth();
            h = image.getHeight();
            random = new Random();

            this.width = w;
            this.height = h;

            tiles = new Tile[w * h];
            levelPixels = new int[w * h];
            image.getRGB(0,0,w,h,levelPixels,0,w);
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Exception! Could not load level file!");
        }
    }
@Override
    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        int x_0 = xScroll >> 4;
        int x_1 = (xScroll + screen.width) >> 4;
        int y_0 = yScroll >> 4;
        int y_1 = (yScroll + screen.height) >> 4;// all divide by 16

        for (int y = y_0 - 1; y < y_1 + 1; y++){
            for (int x = x_0 - 1; x < x_1 + 1; x++){

                if (x < 0 || x >= width || y < 0 || y>= height){
                    Tile.voidTile.render(x, y, screen);
                    continue;
                }
                else tiles[x + y * w].render(x, y, screen);
                // is map width
            }
        }
    }


    // Grass = #00FF00 0,255,0
    // Flower = #FFFF00 255,255,0
    // Rock = 	#808080 128,128,128
    protected void generateLevel(){
        for (int i = 0; i < levelPixels.length; i++){
            if (levelPixels[i] == 0xff00FF00) {
                tiles[i] = Tile.grass[random.nextInt(0,4)];
            }
            if (levelPixels[i] == 0xffFFFF00) {
                tiles[i] = Tile.flower;
            }
            if (levelPixels[i] == 0xff808080) {
                tiles[i] = Tile.rock;
            }
        }
    }
}
