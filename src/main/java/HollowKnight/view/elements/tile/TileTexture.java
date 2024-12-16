package HollowKnight.view.elements.tile;

import HollowKnight.view.sprites.Sprite;

import java.io.IOException;

public abstract class TileTexture {
    private Sprite texture;

    public void setTexture(Sprite texture) {
        this.texture = texture;
    }

    public Sprite getTexture() {
        return texture;
    }

    public abstract void loadSprite() throws IOException;
}
