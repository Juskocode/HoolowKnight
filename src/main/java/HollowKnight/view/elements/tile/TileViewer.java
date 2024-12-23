package HollowKnight.view.elements.tile;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.tile.Tile;
import HollowKnight.view.elements.ElementViewer;
import HollowKnight.view.sprites.Sprite;
import HollowKnight.view.sprites.SpriteLoader;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TileViewer implements ElementViewer<Tile> {
    private final Map<Character, Sprite> tileMap;

    public TileViewer(SpriteLoader spriteLoader) throws IOException {
        this.tileMap = new HashMap<>();
        tileMap.put('M', spriteLoader.get("sprites/Tiles/ground_metal.png"));
        tileMap.put('G', spriteLoader.get("sprites/Tiles/ground_grass.png"));
        tileMap.put('L', spriteLoader.get("sprites/Tiles/ground_rocky_lava.png"));

    }

    @Override
    public void draw(Tile model, GUI gui, long time, int offsetX, int offsetY) {
        Sprite sprite = tileMap.get(model.getCharacter());
        sprite.draw(gui, (int) model.getPosition().x(), (int) model.getPosition().y());

    }
}
