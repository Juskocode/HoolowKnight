package HollowKnight.view.elements;

import HollowKnight.gui.GUI;
import HollowKnight.model.Model;

public abstract class ElementViewer<T> {
    final protected GUI gui;

    public ElementViewer(GUI gui) {
        this.gui = gui;
    }

    public abstract void draw(T model);
}