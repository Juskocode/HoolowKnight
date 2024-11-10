package HollowKnight.controller;

import HollowKnight.Game;
import HollowKnight.gui.GUI;

import java.io.IOException;

public abstract class Controller<T> {
    private final T model;

    protected Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void move(Game game, GUI.ACTION action, long time);
}
