package HollowKnight.view.elements.tile;

import HollowKnight.view.sprites.Sprite;

import java.io.IOException;

public class GrassTexture extends TileTexture{
    @Override
    public void loadSprite() throws IOException {
        Sprite texture = new Sprite("sprites/Tiles/ground_grass.png");
        setTexture(texture);
    }
}
