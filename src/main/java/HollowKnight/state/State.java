package HollowKnight.state;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.view.sprites.SpriteLoader;
import HollowKnight.view.sprites.ViewerProvider;
import HollowKnight.view.states.ScreenViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class State<T> {
    private final T model;
    private final Controller<T> controller;
    private final ScreenViewer<T> screenViewer;

    public State(T model, SpriteLoader spriteLoader) throws IOException {
        this.model = model;
        this.screenViewer = createScreenViewer(new ViewerProvider(spriteLoader));
        this.controller = createController();
    }

    protected abstract ScreenViewer<T> createScreenViewer(ViewerProvider viewerProvider) throws IOException;
    protected abstract Controller<T> createController();
    public T getModel() {
        return model;
    }

    public void move(Game game, GUI gui, long time) throws IOException, URISyntaxException, FontFormatException {
        GUI.ACTION action = gui.getACTION();
        controller.move(game, action, time);
        screenViewer.draw(gui, time);
    }
}
