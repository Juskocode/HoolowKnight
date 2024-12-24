package HollowKnight.view.elements.rocks;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Collectables.Collectables;
import HollowKnight.model.game.elements.rocks.Rock;
import HollowKnight.view.elements.ElementViewer;
import HollowKnight.view.sprites.Sprite;
import HollowKnight.view.sprites.SpriteLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RockViewer implements ElementViewer<Rock> {

    private final Map<Character, Sprite> rockMap;

    public RockViewer(SpriteLoader spriteLoader) throws IOException {
        rockMap = new HashMap<>();
        rockMap.put('r', spriteLoader.get("sprites/Ambient/Smallrock.png"));
        rockMap.put('R', spriteLoader.get("sprites/Ambient/Bigrock.png"));
    }
    @Override
    public void draw(Rock model, GUI gui, long time, int offsetX, int offsetY) throws IOException {
        Sprite sprite = rockMap.get(model.getChar());
        if (sprite == null) {
            throw new IllegalArgumentException("No sprite for character: " + model.getChar());
        }
        sprite.draw(gui, (int) model.getPosition().x(), (int) model.getPosition().y());
    }
}
