package HollowKnight.controller;

import HollowKnight.Game;
import HollowKnight.gui.GUI;

public abstract class Controller<T> {
    private final T model;

    protected Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void move(Game game, GUI action, long time);
}
