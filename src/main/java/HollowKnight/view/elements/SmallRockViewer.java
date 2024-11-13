package HollowKnight.view.elements;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.rocks.SmallRock;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;

public class SmallRockViewer implements ElementViewer<SmallRock> {
    private final Sprite smallRockSprite;

    public SmallRockViewer() throws IOException {
        this.smallRockSprite = new Sprite("sprites/Ambient/smallRock.png");
    }

    @Override
    public void draw(SmallRock model, GUI gui) throws IOException {
        smallRockSprite.draw(gui, model.getPosition().x(), model.getPosition().y());
    }
}
