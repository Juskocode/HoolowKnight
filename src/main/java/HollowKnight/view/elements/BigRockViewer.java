package HollowKnight.view.elements;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.rocks.BigRock;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;

public class BigRockViewer implements ElementViewer<BigRock> {
    private final Sprite bigRockSprite;

    public BigRockViewer() throws IOException {
        bigRockSprite = new Sprite("sprites/Ambient/bigrock.png");
    }
    @Override
    public void draw(BigRock model, GUI gui) throws IOException {
        bigRockSprite.draw(gui, model.getPosition().x(), model.getPosition().y());
    }
}
