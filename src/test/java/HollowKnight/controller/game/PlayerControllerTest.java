package HollowKnight.controller.game;

import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.model.game.elements.map.Scene;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControllerTest {
    private PlayerController playerController;
    private Scene scene;
    @BeforeEach
    public void setUp() {
        scene = new Scene(40,40);
        playerController = new PlayerController(scene);
    }

    @Test
    void movePlayerLeft() {
        Knight KnightMock = Mockito.mock(Knight.class);
        scene.
    }

    @Test
    void movePlayerRight() {
    }

    @Test
    void move() {
    }
}