package HollowKnight.view.elements;

import HollowKnight.gui.GUI;

import HollowKnight.model.game.elements.Knight.Knight;
import com.googlecode.lanterna.TextColor;


public class KnightViewer implements ElementViewer<Knight>{
    @Override
    public void draw(Knight model, GUI gui) {
        gui.drawPixel(model.getPosition().x(), model.getPosition().y(), new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x() + 1, model.getPosition().y(), new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x() - 1, model.getPosition().y(), new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x(), model.getPosition().y() + 1, new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x(), model.getPosition().y() - 1, new TextColor.RGB(0, 0, 255));

    }
}
