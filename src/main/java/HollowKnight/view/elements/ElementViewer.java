package HollowKnight.view.elements;

import HollowKnight.gui.GUI;

public interface ElementViewer<T> {
    void draw(T model, GUI gui);
}