package com.spgame.game_sp.level.tile;

import com.spgame.game_sp.graphics.Screen;
import com.spgame.game_sp.graphics.Sprite;

public class GrassTile extends Tile {
    public GrassTile(Sprite sprite) {
        super(sprite);
    }
    @Override
    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }
    public void render(int x, int y, Screen screen,int renderDir) {
        screen.renderTile(x << 4, y << 4, this,renderDir);
    }
}
