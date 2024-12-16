package HollowKnight.view.elements.tile;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.tile.Tile;
import HollowKnight.view.elements.ElementViewer;
import HollowKnight.view.sprites.Sprite;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TileViewer implements ElementViewer<Tile> {
    private final Map<Character,TileTexture> tileMap;

    public TileViewer() throws IOException {
        this.tileMap = new HashMap<>();
        tileMap.put('M', new MetalTexture());
        tileMap.put('G', new GrassTexture());

        for(Map.Entry<Character, TileTexture> entry : tileMap.entrySet()){
            entry.getValue().loadSprite();
        }
    }

    @Override
    public void draw(Tile model, GUI gui, long time, int offsetX, int offsetY) {
        gui.drawRectangle((int)model.getPosition().x() - offsetX, (int)model.getPosition().y() - offsetX, 8, 8, new TextColor.RGB(200, 22, 33));
        gui.drawPixel((int)model.getPosition().x()  - offsetX, (int)model.getPosition().y() - offsetX, new TextColor.RGB(102, 51, 0));
    }
}
