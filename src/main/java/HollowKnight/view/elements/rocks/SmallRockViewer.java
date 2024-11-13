package HollowKnight.view.elements.rocks;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.rocks.SmallRock;
import HollowKnight.view.elements.ElementViewer;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;

public class SmallRockViewer implements ElementViewer<SmallRock> {
    private final Sprite smallRockSprite;

    public SmallRockViewer() throws IOException {
        this.smallRockSprite = new Sprite("sprites/Ambient/smallRock.png");
    }

    @Override
    public void draw(SmallRock model, GUI gui, long time) throws IOException {
        smallRockSprite.draw(gui, model.getPosition().x(), model.getPosition().y());
    }
}
