package HollowKnight.controller.game;

import HollowKnight.Game;
import HollowKnight.model.game.elements.Knight.IdleState;
import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.model.game.elements.Knight.KnightState;
import HollowKnight.model.game.elements.Knight.RespawnState;
import HollowKnight.model.game.scene.Scene;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static HollowKnight.gui.GUI.ACTION.*;
import static org.mockito.Mockito.*;

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
    public void move() throws IOException {
        playerController.move(game, NULL, 0);

        verify(knight, times(1)).setVelocity(knight.updateVelocity());
        verify(knight, times(1)).updateVelocity();
        verify(knight, times(1)).setPosition(knight.updatePosition());
        verify(knight, times(1)).getNextState();
        verify(knight, times(1)).setState(Mockito.any());

        // Verify IdleState assignment when getState returns null
        when(knight.getState()).thenReturn(null);
        playerController.move(game, NULL, 0);
        verify(knight, times(1)).setState(any(IdleState.class));
    }

    @Test
    public void moveLeft() throws IOException {
        playerController.move(game, LEFT, 0);

        verify(knight, times(1)).setVelocity(knight.moveLeft());
        verify(knight, times(1)).moveLeft();
        verify(knight, times(1)).setFacingRight(false);

        verify(knight, times(1)).setPosition(knight.updatePosition());
        verify(knight, times(1)).getNextState();
        verify(knight, times(1)).setState(Mockito.any());
    }

    @Test
    public void moveRight() throws IOException {
        playerController.move(game, RIGHT, 0);

        verify(knight, times(1)).setVelocity(knight.moveRight());
        verify(knight, times(1)).moveRight();
        verify(knight, times(1)).setFacingRight(true);

        verify(knight, times(1)).setPosition(knight.updatePosition());
        verify(knight, times(1)).getNextState();
        verify(knight, times(1)).setState(Mockito.any());
    }

    @Test
    public void jump() throws IOException {
        playerController.move(game, JUMP, 0);

        verify(knight, times(1)).setVelocity(knight.jump());
        verify(knight, never()).setFacingRight(anyBoolean()); // Jump shouldn't affect facing direction

        verify(knight, times(1)).setPosition(knight.updatePosition());
        verify(knight, times(1)).getNextState();
        verify(knight, times(1)).setState(Mockito.any());
    }

    @Test
    public void kill() throws IOException {
        playerController.move(game, KILL, 0);

        verify(knight, times(1)).setState(any(RespawnState.class)); // Verify state changes to RespawnState

        verify(knight, times(1)).setPosition(knight.updatePosition());
        verify(knight, times(1)).getNextState();
    }

    @Test
    public void dash() throws IOException {
        playerController.move(game,DASH,0);

        verify(knight, times(1)).setVelocity(knight.dash());
        verify(knight, never()).setFacingRight(anyBoolean()); // Dash shouldn't affect facing direction

        verify(knight, times(1)).setPosition(knight.updatePosition());
        verify(knight, times(1)).getNextState();
        verify(knight, times(1)).setState(Mockito.any());
    }
}
