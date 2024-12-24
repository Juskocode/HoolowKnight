package HollowKnight.controller.credits;

import HollowKnight.Game;
import HollowKnight.gui.GUI;
import HollowKnight.model.credits.Credits;
import HollowKnight.view.sprites.SpriteLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CreditsControllerTest {
    private Game game;
    private CreditsController creditsController;

    @BeforeEach
    public void setup() {
        this.game = Mockito.mock(Game.class);
        Credits credits = Mockito.mock(Credits.class);
        SpriteLoader spriteLoader = Mockito.mock(SpriteLoader.class);
        when(game.getSpriteLoader()).thenReturn(spriteLoader);

        this.creditsController = new CreditsController(credits);
    }

    @Test
    public void stepWithoutQuit() throws IOException {
        GUI.ACTION action = GUI.ACTION.NULL;
        long frameCount = 0;

        creditsController.move(game, action, frameCount);
        Mockito.verify(game, Mockito.times(0))
                .setState(Mockito.any());
    }

    @Test
    public void stepWithQuit() throws IOException {
        GUI.ACTION action = GUI.ACTION.QUIT;
        long frameCount = 0;

        creditsController.move(game, action, frameCount);
        Mockito.verify(game, Mockito.times(1))
                .setState(Mockito.any());
    }

}