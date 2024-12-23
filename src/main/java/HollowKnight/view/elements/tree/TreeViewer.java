package HollowKnight.view.elements.tree;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Tree;
import HollowKnight.view.elements.ElementViewer;
import HollowKnight.view.sprites.Sprite;
import HollowKnight.view.sprites.SpriteLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TreeViewer implements ElementViewer<Tree> {
    private final Map<Character, Sprite> treeMap;

    public TreeViewer(SpriteLoader spriteLoader) throws IOException {
        treeMap = new HashMap<>();
        treeMap.put('t', spriteLoader.get("sprites/Ambient/SmallTree.png"));
        treeMap.put('T', spriteLoader.get("sprites/Ambient/MediumTree.png"));
    }

    @Override
    public void draw(Tree model, GUI gui, long time, int offsetX, int offsetY) throws IOException {
        Sprite sprite = treeMap.get(model.getChar());
        sprite.draw(gui, (int) model.getPosition().x(), (int) model.getPosition().y());
    }
}