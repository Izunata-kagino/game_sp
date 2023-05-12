package com.spgame.game_sp.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class WarriorSheet {
    private String path;
    public int[] pixels;
    public int width, height,wLength,hLength,totalWidth,totalHeight;

    public static WarriorSheet tiles = new WarriorSheet("/texture/Warrior.png", 6, 17);

    public WarriorSheet(String path, int width, int height){
        this.path = path;
        this.width = width;
        this.height = height;
        load();
    }

    private void load(){
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            totalWidth = image.getWidth();
            totalHeight = image.getHeight();
            wLength = totalWidth / width;
            hLength = totalHeight / height;
            pixels = new int[totalWidth * totalHeight];
            image.getRGB(0, 0, totalWidth, totalHeight, pixels, 0, totalWidth);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}