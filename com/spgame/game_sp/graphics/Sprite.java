package com.spgame.game_sp.graphics;

public class Sprite {
    public final int SIZE;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;

    public static Sprite[] grass = spritesList(16, 0 , 0, 4, SpriteSheet.tiles);
    public static Sprite voidTile = new Sprite(16,1,1,SpriteSheet.tiles);
    public static Sprite blue = new Sprite(16,0,1,SpriteSheet.tiles);
    public static Sprite[] playerUP = spritesList(32, 0 , 7, 4, SpriteSheet.tiles);

    public static Sprite[] playerDOWN = spritesList(32, 0 , 6, 4, SpriteSheet.tiles);

    public static Sprite[] playerLEFT = spritesList(32, 0 , 5, 4, SpriteSheet.tiles);

    public static Sprite[] playerRIGHT = spritesList(32, 0 , 4, 4, SpriteSheet.tiles);

    public static Sprite[] playerIDLE = spritesList(32, 0 , 3, 4, SpriteSheet.tiles);


    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        SIZE = size;
        this.x = x * size;
        this.y = y * size;
        pixels = new int[SIZE * SIZE];
        this.sheet = sheet;
        load();
    }

    public static Sprite[] spritesList(int size, int x, int y, int listLength, SpriteSheet sheet){
        //read from left to right
        Sprite[] list = new Sprite[listLength];
        for (int i = 0; i < listLength; i++){
            list[i] = new Sprite(size,x + i ,y,sheet);
        }
        return list;
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = sheet.pixels[(x+this.x)+(y+this.y)*sheet.SIZE];
            }
        }
    }

}
