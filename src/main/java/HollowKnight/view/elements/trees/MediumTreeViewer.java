package HollowKnight.view.elements.trees;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Tree.MediumTree;
import HollowKnight.view.elements.ElementViewer;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;

public class MediumTreeViewer implements ElementViewer<MediumTree> {
    private final Sprite mediumTreeSprite;
    public MediumTreeViewer() throws IOException {
        this.mediumTreeSprite = new Sprite("sprites/Ambient/MediumTree.png");
    }

    @Override
    public void draw(MediumTree model, GUI gui, long time) throws IOException {
        mediumTreeSprite.draw(gui, (int)model.getPosition().x(), (int)model.getPosition().y());
    }
}
