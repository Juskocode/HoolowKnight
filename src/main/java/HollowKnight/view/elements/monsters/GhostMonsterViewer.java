package HollowKnight.view.elements.monsters;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.enemies.GhostMonster;
import HollowKnight.view.elements.ElementViewer;
import HollowKnight.view.sprites.Sprite;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;

public class GhostMonsterViewer implements ElementViewer<GhostMonster> {
    private final Sprite minhoteMonsterSprite;

    public GhostMonsterViewer() throws IOException {
        this.minhoteMonsterSprite = new Sprite("sprites/Enemies/MinhoteMonster.png");
    }

    @Override
    public void draw(GhostMonster model, GUI gui, long time, int offsetX, int offsetY) throws IOException {
        minhoteMonsterSprite.draw(gui, (int)model.getPosition().x()-4,(int) model.getPosition().y()-6);

        gui.drawHitBox((int)model.getPosition().x(), (int)model.getPosition().y(), 4,4,
                new TextColor.RGB(25,25,100));
        gui.drawPixel((int)model.getPosition().x(), (int)model.getPosition().y(),
                new TextColor.RGB(200,105,150));
    }
}
