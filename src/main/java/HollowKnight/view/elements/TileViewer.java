package HollowKnight.view.elements;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.tile.Tile;
import com.googlecode.lanterna.TextColor;

public class TileViewer implements ElementViewer<Tile>  {

    @Override
    public void draw(Tile model, GUI gui) {
        gui.drawPixel(model.getPosition().x(), model.getPosition().y(), new TextColor.RGB(255, 0, 0));
    }
}
