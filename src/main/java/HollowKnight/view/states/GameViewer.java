package HollowKnight.view.states;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Element;
import HollowKnight.model.game.elements.map.Scene;
import HollowKnight.view.elements.ElementViewer;
import HollowKnight.view.elements.KnightViewer;

import java.io.IOException;

public class GameViewer extends ScreenViewer<Scene> {
    public GameViewer(Scene model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.cls();
        drawElement(gui, getModel().getPlayer(), new KnightViewer());
        gui.flush();
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) {
        viewer.draw(element, gui);
    }
}