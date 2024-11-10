package HollowKnight.view.states;

import HollowKnight.gui.GUI;
import HollowKnight.model.Model;

public abstract class ScreenViewer<T> {
    final protected T model;

    public ScreenViewer(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void draw(GUI gui);
}
