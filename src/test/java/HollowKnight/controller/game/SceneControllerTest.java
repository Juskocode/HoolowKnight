package HollowKnight.controller.game;

import HollowKnight.Game;
import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.model.game.scene.Scene;
import HollowKnight.model.game.scene.SceneLoader;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

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
                .setState(any());
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

    @Property
    void allArenasAreClosed(@ForAll @IntRange(min = 3, max = 50) int width, @ForAll @IntRange(min = 3, max = 50) int height,
                                @ForAll List<GUI.@From("moveActions") ACTION> actions) throws IOException
    {
        SceneLoader sceneLoader = new SceneLoader(0);
        Scene newScene = sceneLoader.createScene(any()); //TODO add knight
        PlayerController controller = new PlayerController(newScene);

        for (GUI.ACTION action : actions) {
            controller.move(null, action, 100);
            assert (controller.getModel().getPlayer().getPosition().x() > 0);
            assert (controller.getModel().getPlayer().getPosition().y() > 0);
            assert (controller.getModel().getPlayer().getPosition().x() < width - 1);
            assert (controller.getModel().getPlayer().getPosition().y() < height - 1);
        }
    }

    @Provide
    Arbitrary<GUI.ACTION> moveActions() {
        return Arbitraries.of(GUI.ACTION.UP, GUI.ACTION.RIGHT, GUI.ACTION.DOWN, GUI.ACTION.LEFT);
    }
}