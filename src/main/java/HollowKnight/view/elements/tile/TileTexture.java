package HollowKnight.view.elements.tile;

import HollowKnight.view.sprites.Sprite;

public abstract class TileTexture {
    private Sprite texture;

    public void setTexture(Sprite texture) {
        this.texture = texture;
    }

    public Sprite getTexture() {
        return texture;
    }

    public abstract Sprite loadSprite(String path);
}
