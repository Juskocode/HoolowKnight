package HollowKnight.view.elements;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.enemies.PurpleMonster;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;

public class PurpleMonsterViewer implements ElementViewer<PurpleMonster> {
    private final Sprite purpleMonsterSprite;

    public PurpleMonsterViewer() throws IOException {
        this.purpleMonsterSprite = new Sprite("sprites/Enemies/Enemy2.png");
    }

    @Override
    public void draw(PurpleMonster model, GUI gui) throws IOException {
        purpleMonsterSprite.draw(gui, model.getPosition().x(), model.getPosition().y());
    }
}
