package HollowKnight.controller.menu;

import HollowKnight.Game;
import HollowKnight.gui.GUI;
import HollowKnight.model.menu.Menu;
import HollowKnight.state.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

class MenuControllerTest {
    private Game game;
    private Menu menu;
    private ParticleMenuController particleMenuController;
    private MenuController menuController;

    @BeforeEach
    void setUp() {
        this.game = Mockito.mock(Game.class);
        this.menu = Mockito.mock(Menu.class);
        this.particleMenuController = Mockito.mock(ParticleMenuController.class);
        this.menuController = new MenuController(menu, particleMenuController);
    }

    @Test
    void moveUp() throws IOException {
        // Call the move method with the UP action
        menuController.move(game, GUI.ACTION.UP, 0);

        // Verify that previousOption() was called
        verify(menu, times(1)).previousOption();
        verifyNoInteractions(game);
        //verifyNoInteractions(particleMenuController);
    }

    @Test
    void moveDown() throws IOException {
        // Call the move method with the DOWN action
        menuController.move(game, GUI.ACTION.DOWN, 0);

        // Verify that nextOption() was called
        verify(menu, times(1)).nextOption();
        verifyNoInteractions(game);
        //verifyNoInteractions(particleMenuController);
    }

    @Test
    void selectStart() throws IOException {
        // Mock the behavior for menu selection
        when(menu.isSelectedStart()).thenReturn(true);
        when(menu.isSelectedExit()).thenReturn(false);

        // Call the move method with the SELECT action and a valid time
        menuController.move(game, GUI.ACTION.SELECT, 100);

        // Verify that setInGame(true) and game.setState(...) were called
        verify(menu, times(1)).setInGame(true);
        verify(game, times(1)).setState(any(GameState.class));
        //verifyNoInteractions(particleMenuController);
    }

    @Test
    void selectExit() throws IOException {
        // Mock the behavior for menu selection
        when(menu.isSelectedExit()).thenReturn(true);
        when(menu.isSelectedStart()).thenReturn(false);

        // Call the move method with the SELECT action and a valid time
        menuController.move(game, GUI.ACTION.SELECT, 100);

        // Verify that game.setState(null) was called
        verify(game, times(1)).setState(null);
        //verifyNoInteractions(particleMenuController);
    }

    @Test
    void delegateToParticleController() throws IOException {
        // Call the move method and check that ParticleMenuController's move is invoked
        menuController.move(game, GUI.ACTION.NULL, 0);

        verify(particleMenuController, times(1)).move(game, GUI.ACTION.NULL, 0);
        verifyNoInteractions(menu);
        verifyNoInteractions(game);
    }
}
