package HollowKnight.view.elements;

import HollowKnight.gui.GUI;

import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;


public class KnightViewer implements ElementViewer<Knight>{
    private final Sprite knightSprite;
    public KnightViewer() throws IOException {
        knightSprite = new Sprite("sprites/Knight/Idle/pixil-frame-0.png");

    }
    @Override
    public void draw(Knight model, GUI gui) throws IOException {
        knightSprite.draw(gui, model.getPosition().x(), model.getPosition().y());
    }
    /*
        gui.drawPixel(model.getPosition().x(), model.getPosition().y(), new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x() + 1, model.getPosition().y(), new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x() - 1, model.getPosition().y(), new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x(), model.getPosition().y() + 1, new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x(), model.getPosition().y() - 1, new TextColor.RGB(0, 0, 255));
     */
}
