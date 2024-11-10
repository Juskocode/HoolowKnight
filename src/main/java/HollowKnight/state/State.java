package HollowKnight.state;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.gui.LanternaGUI;
import HollowKnight.view.states.ScreenViewer;

import java.io.IOException;

public abstract class State<T> {
    protected final T model;
    protected final Controller<T> controller;
    protected final ScreenViewer<T> screenViewer;

    public State(T model) {
        this.model = model;
        this.screenViewer = getScreenViewer();
        this.controller = getController();
    }

    protected abstract ScreenViewer<T> getScreenViewer();
    protected abstract Controller<T> getController();

    public T getModel() {
        return model;
    }

    public void step(Game game, GUI gui, long time) throws IOException {
        GUI.ACTION action = gui.getACTION();
        controller.move(game, action, time);
        screenViewer.draw(gui);
    }
}
