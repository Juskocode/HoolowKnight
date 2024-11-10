package HollowKnight.view.elements;

import HollowKnight.gui.GUI;

public class KnightViewer implements ElementViewer<Knight>{
    @Override
    public void draw(Knight model, GUI gui) {
        gui.drawPixel(model.getPosition().x(), model.getPosition().y(), "#FF0000");
        gui.drawPixel(model.getPosition().x() + 1, model.getPosition().y(), "#FF0000");
        gui.drawPixel(model.getPosition().x() - 1, model.getPosition().y(), "#FF0000");
        gui.drawPixel(model.getPosition().x(), model.getPosition().y() + 1, "#FF0000");
        gui.drawPixel(model.getPosition().x(), model.getPosition().y() - 1, "#FF0000");
    }
}
