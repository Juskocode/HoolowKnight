package HollowKnight.view.elements;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Spike;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SpikeViewer implements ElementViewer<Spike> {
    private final Map<Character, Sprite> spikeMap;

    public SpikeViewer() throws IOException {
        spikeMap = new HashMap<>();
        spikeMap.put('^', new Sprite("sprites/spikes/spike_ground.png"));
        spikeMap.put('+', new Sprite("sprites/spikes/spike_brown.png"));
        //spikeMap.put('-', new Sprite("sprites/spikes/cave/Bottom_Spike2.png"));
    }
    @Override
    public void draw(Spike model, GUI gui, long time, int offsetX, int offsetY) throws IOException {
        Sprite sprite = spikeMap.get(model.getCharacter());
        sprite.draw(gui, (int) model.getPosition().x(), (int) model.getPosition().y());
    }
}