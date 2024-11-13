package HollowKnight.view.elements.rocks;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.rocks.BigRock;
import HollowKnight.view.elements.ElementViewer;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;

public class BigRockViewer implements ElementViewer<BigRock> {
    private final Sprite bigRockSprite;

    public BigRockViewer() throws IOException {
        bigRockSprite = new Sprite("sprites/Ambient/Bigrock.png");
    }
    @Override
    public void draw(BigRock model, GUI gui, long time) throws IOException {
        bigRockSprite.draw(gui, model.getPosition().x(), model.getPosition().y());
    }
}
