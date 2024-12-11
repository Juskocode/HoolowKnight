package HollowKnight.controller.game;

import HollowKnight.Game;
import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.model.game.scene.Scene;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SceneControllerTest {
    private Game game;
    private SceneController sceneController;
    private Scene scene;
    private PlayerController playerController;
    private ParticleController particleController;
    private EnemieController enemieController;
    @BeforeEach
    void setUp() {
        this.game = Mockito.mock(Game.class);
        this.scene = Mockito.mock(Scene.class);
        Knight knight = Mockito.mock(Knight.class);
        Mockito.when(scene.getPlayer()).thenReturn(knight);
        this.playerController = Mockito.mock(PlayerController.class);
        this.particleController = Mockito.mock(ParticleController.class);
        this.enemieController = Mockito.mock(EnemieController.class);
        this.sceneController = new SceneController(scene, playerController, particleController, enemieController);
    }

    @Test
    void move() throws IOException {
        sceneController.move(game, GUI.ACTION.NULL, 0);
        Mockito.verify(playerController, Mockito.times(1))
                .move(game, GUI.ACTION.NULL, 0);
        Mockito.verify(game, Mockito.times(0))
                .setState(Mockito.any());
        Mockito.verify(particleController, Mockito.times(1))
                .move(game, GUI.ACTION.NULL, 0);
    }

    @Test
    void moveWithQuit() throws IOException {
        sceneController.move(game, GUI.ACTION.QUIT, 0);
        Mockito.verify(playerController, Mockito.times(0))
                .move(game, GUI.ACTION.NULL, 0);
        Mockito.verify(game, Mockito.times(1))
                .setState(null);
        Mockito.verify(particleController, Mockito.times(0))
                .move(game, GUI.ACTION.NULL, 0);
    }


}