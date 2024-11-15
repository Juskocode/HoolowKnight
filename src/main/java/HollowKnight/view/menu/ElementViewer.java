package HollowKnight.view.menu;

import HollowKnight.gui.GUI;

import java.io.IOException;

public interface ElementViewer<T> {
    void draw(T model, GUI gui, long time) throws IOException;
}