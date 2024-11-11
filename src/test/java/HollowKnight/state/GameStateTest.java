package HollowKnight.state;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.map.Scene;
import HollowKnight.view.states.ScreenViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {
    private   Scene model;
    private   Controller<Scene> controller;
    private   ScreenViewer<Scene> screenViewer;
    private Game game;
    private GUI gui;
    private GameState state;
    @BeforeEach
    void setup(){
        this.model = Mockito.mock(Scene.class);
        this.game = Mockito.mock(Game.class);
        this.gui = Mockito.mock(GUI.class);
        this.state = new GameState(model) {
            @Override
            protected ScreenViewer<Scene> getScreenViewer() {
                return screenViewer;
            }

            @Override
            protected Controller<Scene> getController() {
                return controller;
            }
        };
        this.controller = Mockito.mock(Controller.class);
    }

    @Test
    void GameStatemove() throws IOException {
        long frameCount = 0;
        Mockito.when(gui.getACTION()).thenReturn(GUI.ACTION.NULL);
    }

}