package HollowKnight.view.elements;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.enemies.MinhoteMonster;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;

public class MinhoteMonsterViewer implements ElementViewer<MinhoteMonster> {
    private final Sprite minhoteMonsterSprite;

    public MinhoteMonsterViewer() throws IOException {
        this.minhoteMonsterSprite = new Sprite("sprites/Enemies/enemy3.png");
    }

    @Override
    public void draw(MinhoteMonster model, GUI gui) throws IOException {
        minhoteMonsterSprite.draw(gui, model.getPosition().x(), model.getPosition().y());
    }
}
