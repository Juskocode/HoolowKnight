package HollowKnight.view.elements;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.tile.Tile;
import com.googlecode.lanterna.TextColor;

public class TileViewer implements ElementViewer<Tile>  {

    @Override
    public void draw(Tile model, GUI gui, long time) {
        gui.drawRectangle((int)model.getPosition().x(), (int)model.getPosition().y(), 8, 8, new TextColor.RGB(200, 22, 33));
        gui.drawPixel((int)model.getPosition().x() , (int)model.getPosition().y(), new TextColor.RGB(102, 51, 0));
    }
}
