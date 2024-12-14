package HollowKnight.view.elements.monsters;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.enemies.PurpleMonster;
import HollowKnight.view.elements.ElementViewer;
import HollowKnight.view.sprites.Sprite;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;

public class PurpleMonsterViewer implements ElementViewer<PurpleMonster> {
    private final Sprite purpleMonsterSprite;

    public PurpleMonsterViewer() throws IOException {
        this.purpleMonsterSprite = new Sprite("sprites/Enemies/PurpleMonster.png");
    }

    @Override
    public void draw(PurpleMonster model, GUI gui, long time, int offsetX, int offsetY) throws IOException {
        purpleMonsterSprite.draw(gui, (int)model.getPosition().x() - 4, (int)model.getPosition().y() - 1);

        gui.drawHitBox((int)model.getPosition().x(), (int)model.getPosition().y(), 10,8,
                    new TextColor.RGB(25,25,100));

        gui.drawPixel((int)model.getPosition().x(), (int)model.getPosition().y(),
                    new TextColor.RGB(200,105,150));
    }
}
