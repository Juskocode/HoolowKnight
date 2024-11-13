package HollowKnight.view.elements;

import HollowKnight.gui.GUI;

import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class KnightViewer implements ElementViewer<Knight>{
    private final List<Sprite> idleSprite;
    public KnightViewer() throws IOException {
        idleSprite = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            idleSprite.add(new Sprite("sprites/Knight/Idle/pixil-frame-" + i + ".png"));
        }

    }
    @Override
    public void draw(Knight model, GUI gui, long time) throws IOException {
        Sprite sprite = getSprite(time);
        sprite.draw(gui, model.getPosition().x(), model.getPosition().y());
    }

    private Sprite getSprite(long frameCount) {
        System.out.println((frameCount) + " frame");
        return idleSprite.get((int) (frameCount % idleSprite.size()));
    }
    /*
        gui.drawPixel(model.getPosition().x(), model.getPosition().y(), new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x() + 1, model.getPosition().y(), new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x() - 1, model.getPosition().y(), new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x(), model.getPosition().y() + 1, new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x(), model.getPosition().y() - 1, new TextColor.RGB(0, 0, 255));
     */
}
