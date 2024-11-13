package HollowKnight.state;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.view.states.ScreenViewer;

import java.io.IOException;

public abstract class State<T> {
    private final T model;
    private final Controller<T> controller;
    private final ScreenViewer<T> screenViewer;

    public State(T model) throws IOException {
        this.model = model;
        this.screenViewer = getScreenViewer();
        this.controller = getController();
    }

    protected abstract ScreenViewer<T> getScreenViewer() throws IOException;
    protected abstract Controller<T> getController();

    public T getModel() {
        return model;
    }

    public void move(Game game, GUI gui, long time) throws IOException {
        GUI.ACTION action = gui.getACTION();
        controller.move(game, action, time);
        screenViewer.draw(gui);
    }
}
