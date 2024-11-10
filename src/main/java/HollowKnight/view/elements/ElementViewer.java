package HollowKnight.view.elements;

import HollowKnight.gui.GUI;
import HollowKnight.model.Model;

public interface ElementViewer<T> {
    void draw(T model, GUI gui);
}