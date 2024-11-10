package HollowKnight.view.states;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.map.Scene;

public class GameViewer extends ScreenViewer<Scene> {
    public GameViewer(GUI gui, Scene map) {
        super(gui, map);
    }

    @Override
    public void draw() {

    }
}