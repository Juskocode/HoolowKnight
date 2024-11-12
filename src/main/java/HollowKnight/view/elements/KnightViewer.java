package HollowKnight.view.elements;

import HollowKnight.gui.GUI;

import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.view.sprites.Sprite;
import HollowKnight.view.sprites.SpriteLoader;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;


public class KnightViewer implements ElementViewer<Knight>{
    @Override
    public void draw(Knight model, GUI gui) throws IOException {
        Sprite sprite = new SpriteLoader().createSprite("sprites/Knight/Idle/pixil-frame-0.png");
        sprite.draw(gui, model.getPosition().x(), model.getPosition().y());
    }
    /*
        gui.drawPixel(model.getPosition().x(), model.getPosition().y(), new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x() + 1, model.getPosition().y(), new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x() - 1, model.getPosition().y(), new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x(), model.getPosition().y() + 1, new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x(), model.getPosition().y() - 1, new TextColor.RGB(0, 0, 255));
     */
}
