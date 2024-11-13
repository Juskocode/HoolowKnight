package HollowKnight.view.elements;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.tile.Tile;
import com.googlecode.lanterna.TextColor;

public class TileViewer implements ElementViewer<Tile>  {

    @Override
    public void draw(Tile model, GUI gui, long time) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                    gui.drawPixel(model.getPosition().x() + i + 4, model.getPosition().y() + j + 5, new TextColor.RGB(102, 51, 0));
    }
}
