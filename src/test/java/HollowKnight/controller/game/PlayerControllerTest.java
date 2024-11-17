package HollowKnight.controller.game;

import HollowKnight.Game;
import HollowKnight.gui.GUI;
import HollowKnight.model.Position;
import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.model.game.elements.Knight.KnightState;
import HollowKnight.model.game.scene.Scene;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static HollowKnight.gui.GUI.ACTION.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PlayerControllerTest {
    private Scene scene;
    private Knight knight;
    private PlayerController playerController;
    private Game game;
    private KnightState knightState;

    @BeforeEach
    public void setUp() {
        this.scene = Mockito.mock(Scene.class);
        this.knight = Mockito.mock(Knight.class);
        Mockito.when(scene.getPlayer()).thenReturn(knight);
        Mockito.when(knight.getScene()).thenReturn(scene);
        this.game = Mockito.mock(Game.class);
        this.playerController = new PlayerController(scene);
        this.knightState = Mockito.mock(KnightState.class);
        Mockito.when(knight.getState()).thenReturn(knightState);
    }

    @Test
    public void move(){
        playerController.move(game, NULL, 0);
        verify(knight, times(1))
                .setVelocity(Mockito.any());
        verify(knight, times(1))
                .updateVelocity();
        verify(knight, times(1))
                .setPosition(Mockito.any());
        verify(knight, times(1))
                .getNextState();
        verify(knight, times(1))
                .setState(Mockito.any());
    }

    @Test
    public void moveLeft(){
        playerController.move(game, LEFT, 0);
        verify(knight, times(1))
                .setVelocity(Mockito.any());
        verify(knight, times(1))
                .moveLeft();
        verify(knight, times(1))
                .setFacingRight(false);

        verify(knight, times(1))
                .setPosition(Mockito.any());
        verify(knight, times(1))
                .getNextState();
        verify(knight, times(1))
                .setState(Mockito.any());
    }

    @Test
    public void moveRight(){
        playerController.move(game, RIGHT, 0);
        verify(knight, times(1))
                .setVelocity(Mockito.any());
        verify(knight, times(1))
                .moveRight();
        verify(knight, times(1))
                .setFacingRight(true);

        verify(knight, times(1))
                .setPosition(Mockito.any());
        verify(knight, times(1))
                .getNextState();
        verify(knight, times(1))
                .setState(Mockito.any());
    }
}