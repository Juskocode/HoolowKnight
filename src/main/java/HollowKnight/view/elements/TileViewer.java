package HollowKnight.view.elements;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.tile.Tile;
import com.googlecode.lanterna.TextColor;

public class TileViewer implements ElementViewer<Tile>  {

    @Override
    public void draw(Tile model, GUI gui, long time, int offsetX, int offsetY) {
        gui.drawRectangle((int)model.getPosition().x() - offsetX, (int)model.getPosition().y() - offsetX, 8, 8, new TextColor.RGB(200, 22, 33));
        gui.drawPixel((int)model.getPosition().x()  - offsetX, (int)model.getPosition().y() - offsetX, new TextColor.RGB(102, 51, 0));
    }
}
