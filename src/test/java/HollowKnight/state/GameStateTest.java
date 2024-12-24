package HollowKnight.state;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.gui.RescalableGUI;
import HollowKnight.model.game.scene.Scene;
import HollowKnight.view.sprites.GameSpriteLoader;
import HollowKnight.view.sprites.ViewerProvider;
import HollowKnight.view.states.ScreenViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {
    private Scene model;
    private GameSpriteLoader spriteLoader;
    private Game game;
    private RescalableGUI gui;
    private Controller<Scene> stateController;
    private ScreenViewer<Scene> stateScreenViewer;
    private GameState state;

    @SuppressWarnings("unchecked")
    private void mockControllerAndViewer() {
        this.stateController = Mockito.mock(Controller.class);
        this.stateScreenViewer = Mockito.mock(ScreenViewer.class);
        this.spriteLoader = Mockito.mock(GameSpriteLoader.class);
    }

    @BeforeEach
    public void setup() {
        this.model = Mockito.mock(Scene.class);
        this.game = Mockito.mock(Game.class);
        this.gui = Mockito.mock(RescalableGUI.class);
        mockControllerAndViewer();
    }

    @Test
    public void gameStateStep() throws IOException, URISyntaxException, FontFormatException {
        long frameCount = 0;
        Mockito.when(gui.getACTION()).thenReturn(GUI.ACTION.NULL);
        this.state = new GameState(model, spriteLoader){
            @Override
            protected ScreenViewer<Scene> createScreenViewer(ViewerProvider viewerProvider) {
                return stateScreenViewer;
            }
            @Override
            protected Controller<Scene> createController() {
                return stateController;
            }

        };
        state.move(game, gui, frameCount);
        Mockito.verify(gui, Mockito.times(1)).getACTION();
        Mockito.verify(stateController, Mockito.times(1))
                .move(game, GUI.ACTION.NULL, frameCount);
        Mockito.verify(stateScreenViewer, Mockito.times(1)).draw(gui, frameCount);
    }
}