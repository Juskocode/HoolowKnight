package HollowKnight.view.elements.monsters;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.enemies.PurpleMonster;
import HollowKnight.view.elements.ElementViewer;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;

public class PurpleMonsterViewer implements ElementViewer<PurpleMonster> {
    private final Sprite purpleMonsterSprite;

    public PurpleMonsterViewer() throws IOException {
        this.purpleMonsterSprite = new Sprite("sprites/Enemies/PurpleMonster.png");
    }

    @Override
    public void draw(PurpleMonster model, GUI gui, long time) throws IOException {
        purpleMonsterSprite.draw(gui, (int)model.getPosition().x(), (int)model.getPosition().y());
    }
}
