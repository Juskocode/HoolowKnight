package HollowKnight.view.states;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Element;
import HollowKnight.view.elements.ElementViewer;

import java.io.IOException;
import java.util.List;

public abstract class ScreenViewer<T> {
    private final T model;

    public ScreenViewer(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void draw(GUI gui) throws IOException;
    public  <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) throws IOException {
        for (T element : elements)
            drawElement(gui, element, viewer);
    }
    public  <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) throws IOException {
        viewer.draw(element, gui);
    }
}
