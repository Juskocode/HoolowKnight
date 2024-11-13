package HollowKnight.view.elements;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Tree.SmallTree;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;

public class SmallTreeViewer implements ElementViewer<SmallTree> {
    private final Sprite TreeSprite;

    public SmallTreeViewer() throws IOException {
        TreeSprite = new Sprite("sprites/Ambient/tree2.png");
    }
    @Override
    public void draw(SmallTree model, GUI gui) throws IOException {
        TreeSprite.draw(gui, model.getPosition().x(), model.getPosition().y());
    }
}
