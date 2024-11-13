package HollowKnight.view.elements;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.model.game.elements.Tree.Tree;
import HollowKnight.view.sprites.Sprite;
import HollowKnight.view.sprites.SpriteLoader;

import java.io.IOException;

public class TreeViewer implements ElementViewer<Tree> {
    private Sprite TreeSprite;

    public TreeViewer() throws IOException {
        TreeSprite = new SpriteLoader().createSprite("sprites/Ambient/tree2.png");
    }
    @Override
    public void draw(Tree model, GUI gui) throws IOException {
        TreeSprite.draw(gui, model.getPosition().x(), model.getPosition().y());
    }
}
