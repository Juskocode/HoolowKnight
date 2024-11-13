package HollowKnight.view.elements.monsters;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.enemies.SwordMonster;
import HollowKnight.view.elements.ElementViewer;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;

public class SwordMonsterViewer implements ElementViewer<SwordMonster> {
    private final Sprite EnemySprite;

    public SwordMonsterViewer() throws IOException {
        EnemySprite = new Sprite("sprites/Enemies/enemy1-swordup.png");
    }
    @Override
    public void draw(SwordMonster model, GUI gui) throws IOException {
        EnemySprite.draw(gui, model.getPosition().x(), model.getPosition().y());
    }
}
