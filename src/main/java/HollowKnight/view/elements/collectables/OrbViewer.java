package HollowKnight.view.elements.collectables;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Collectables.Collectables;
import HollowKnight.view.sprites.Sprite;
import HollowKnight.view.sprites.SpriteLoader;
import HollowKnight.view.elements.ElementViewer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrbViewer implements ElementViewer<Collectables> {
    private final Map<Character, Sprite> orbMap;

    public OrbViewer(SpriteLoader spriteLoader) throws IOException {
        orbMap = new HashMap<>();
        orbMap.put('h', spriteLoader.get("sprites/collectables/health.png"));
        orbMap.put('e', spriteLoader.get("sprites/collectables/energy.png"));
        orbMap.put('s', spriteLoader.get("sprites/collectables/speed.png"));
    }

    @Override
    public void draw(Collectables model, GUI gui, long time, int offsetX, int offsetY) throws IOException {
        Sprite sprite = orbMap.get(model.getChar());
        //System.out.println(model.getChar());
        if (sprite == null) {
            throw new IllegalArgumentException("No sprite for character: " + model.getChar());
        }
        sprite.draw(gui, (int) model.getPosition().x(), (int) model.getPosition().y());
    }
}
