package com.spgame.game_sp.graphics;

public class Sprite {
    public final int SIZE;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;

    public static Sprite[] grass = spritesList(16, 0 , 0, 4, SpriteSheet.tiles);
    public static Sprite flower = new Sprite(16,4,0,SpriteSheet.tiles);
    public static Sprite rock = new Sprite(16,5,0,SpriteSheet.tiles);
    public static Sprite voidTile = new Sprite(16,1,1,SpriteSheet.tiles);
    public static Sprite blue = new Sprite(16,0,1,SpriteSheet.tiles);
    public static Sprite[] playerUP = spritesList(32, 0 , 7, 4, SpriteSheet.tiles);

    public static Sprite[] playerDOWN = spritesList(32, 0 , 6, 4, SpriteSheet.tiles);

    public static Sprite[] playerLEFT = spritesList(32, 0 , 5, 4, SpriteSheet.tiles);

    public static Sprite[] playerRIGHT = spritesList(32, 0 , 4, 4, SpriteSheet.tiles);

    public static Sprite[] playerIDLE = spritesList(32, 0 , 3, 4, SpriteSheet.tiles);

    public static Sprite[] warrior = Warrior_spritesList(WarriorSheet.tiles);


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



    public WarriorSheet warriorSheet;
    public Sprite(int x, int y, WarriorSheet sheet){
        SIZE = 0;
        this.x = x * sheet.wLength;
        this.y = y * sheet.hLength;
        pixels = new int[sheet.wLength * sheet.hLength];
        this.warriorSheet = sheet;
        loadWarrior();
    }

    private void loadWarrior(){
        for (int y = 0; y < 748 / 17; y++) {
            for (int x = 0; x < 414 / 6; x++) {
                if (warriorSheet.pixels[(x+this.x)+(y+this.y)* warriorSheet.width] == 0xff560b28){
                    System.out.println("?");
                }
                pixels[x + y * warriorSheet.width] = warriorSheet.pixels[(x+this.x)+(y+this.y)* warriorSheet.width];
            }
        }
    }

    public static Sprite[] Warrior_spritesList(WarriorSheet sheet){
        //read all
        Sprite[] list = new Sprite[sheet.width * sheet.height];
        for (int j = 0; j < sheet.height; j++){
            for (int i = 0; i < sheet.width; i++){
                list[i + j * sheet.width] = new Sprite(i ,j ,sheet);
                System.out.println(j* sheet.width + i);
            }
        }
        return list;
    }



}
