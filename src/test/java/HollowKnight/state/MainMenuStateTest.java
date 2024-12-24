package HollowKnight.state;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.gui.RescalableGUI;
import HollowKnight.model.menu.MainMenu;
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

class MainMenuStateTest {
    private MainMenu model;
    private GameSpriteLoader spriteLoader;
    private Game game;
    private RescalableGUI gui;
    private Controller<MainMenu> stateController;
    private ScreenViewer<MainMenu> stateScreenViewer;
    private MainMenuState state;

    @SuppressWarnings("unchecked")
    private void mockControllerAndViewer() {
        this.stateController = Mockito.mock(Controller.class);
        this.stateScreenViewer = Mockito.mock(ScreenViewer.class);
    }

    @BeforeEach
    public void setup() {
        this.model = Mockito.mock(MainMenu.class);
        this.spriteLoader = Mockito.mock(GameSpriteLoader.class);
        this.game = Mockito.mock(Game.class);
        this.gui = Mockito.mock(RescalableGUI.class);
        mockControllerAndViewer();
    }

    @Test
    public void mainMenuStep() throws IOException, URISyntaxException, FontFormatException {
        long frameCount = 0;
        Mockito.when(gui.getACTION()).thenReturn(GUI.ACTION.NULL);
        this.state = new MainMenuState(model, spriteLoader){
            @Override
            protected ScreenViewer<MainMenu> createScreenViewer(ViewerProvider viewerProvider) {
                return stateScreenViewer;
            }
            @Override
            protected Controller<MainMenu> createController() {
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