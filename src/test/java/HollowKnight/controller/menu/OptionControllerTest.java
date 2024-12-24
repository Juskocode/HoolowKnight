package HollowKnight.controller.menu;

import HollowKnight.Game;
import HollowKnight.gui.GUI;
import HollowKnight.gui.RescalableGUI;
import HollowKnight.model.menu.Menu;
import HollowKnight.model.menu.Option;
import HollowKnight.state.GameState;
import HollowKnight.state.MainMenuState;
import HollowKnight.state.SettingsMenuState;
import HollowKnight.state.State;
import HollowKnight.view.sprites.GameSpriteLoader;
import HollowKnight.view.sprites.SpriteLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

class OptionControllerTest {
    private Game game;
    private Menu menu;
    private OptionController optionController;
    private GameSpriteLoader gameSpriteLoader;

    @BeforeEach
    public void setup(){
        this.game = Mockito.mock(Game.class);
        this.menu = Mockito.mock(Menu.class);
        doNothing().when(game).setState(isA(State.class));
        this.gameSpriteLoader = new GameSpriteLoader();

        this.optionController = new OptionController(menu);
    }

    @Test
    public void StartGame() throws IOException, URISyntaxException, FontFormatException {
        Option e = new Option(0,0, Option.Type.START_GAME);
        when(menu.getCurrentOption()).thenReturn(e);
        when(game.getSpriteLoader()).thenReturn(gameSpriteLoader);

        optionController.move(game, GUI.ACTION.NULL, 0);
        verify(game, Mockito.times(0))
                .setState(Mockito.any(State.class));

        optionController.move(game, GUI.ACTION.SELECT, 0);
        verify(game, Mockito.times(1))
                .setState(Mockito.any(GameState.class));
    }

    @Test
    public void stepSettings() throws IOException, URISyntaxException, FontFormatException {
        Option e = new Option(0,0, Option.Type.SETTINGS);
        when(menu.getCurrentOption()).thenReturn(e);
        when(game.getSpriteLoader()).thenReturn(gameSpriteLoader);

        optionController.move(game, GUI.ACTION.NULL, 0);
        verify(game, Mockito.times(0))
                .setState(Mockito.any(State.class));

        optionController.move(game, GUI.ACTION.SELECT, 0);
        verify(game, Mockito.times(1))
                .setState(Mockito.any(SettingsMenuState.class));
    }

    @Test
    public void stepExit() throws IOException, URISyntaxException, FontFormatException {
        Option e = new Option(0,0, Option.Type.EXIT);
        when(menu.getCurrentOption()).thenReturn(e);

        optionController.move(game, GUI.ACTION.NULL, 0);
        verify(game, Mockito.times(0))
                .setState(null);

        optionController.move(game, GUI.ACTION.SELECT, 0);
        verify(game, Mockito.times(1))
                .setState(null);
    }

    @Test
    public void stepMainMenu() throws IOException, URISyntaxException, FontFormatException {
        Option e = new Option(0,0, Option.Type.TO_MAIN_MENU);
        when(menu.getCurrentOption()).thenReturn(e);
        when(game.getSpriteLoader()).thenReturn(gameSpriteLoader);

        optionController.move(game, GUI.ACTION.NULL, 0);
        verify(game, Mockito.times(0))
                .setState(Mockito.any(State.class));

        optionController.move(game, GUI.ACTION.SELECT, 0);
        verify(game, Mockito.times(1))
                .setState(Mockito.any(MainMenuState.class));
    }

}