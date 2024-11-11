package HollowKnight.controller.game;

import HollowKnight.Game;
import HollowKnight.model.Position;
import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.model.game.scene.Scene;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static HollowKnight.gui.GUI.ACTION.*;

class PlayerControllerTest {
    private Scene scene;
    private Knight knight;
    private PlayerController playerController;
    private Game game;
    @BeforeEach
    public void setUp() {
        this.scene = Mockito.mock(Scene.class);
        this.knight = Mockito.mock(Knight.class);
        Mockito.when(scene.getPlayer()).thenReturn(knight);
        this.game = Mockito.mock(Game.class);
        this.playerController = new PlayerController(scene);
    }

    @Test
    void movePlayerLeft(){
        Position position = new Position(10,10);
        Mockito.when(knight.getPosition()).thenReturn(position);
        long time = 0;
        playerController.move(game, LEFT, time);
        Mockito.verify(knight, Mockito.times(1)).setPosition(position.getLeft());
    }
    @Test
    void movePlayerRight(){
        Position position = new Position(20,10);
        Mockito.when(knight.getPosition()).thenReturn(position);
        playerController.move(game, RIGHT, 0);
        Mockito.verify(knight, Mockito.times(1)).setPosition(position.getRight());
    }
}