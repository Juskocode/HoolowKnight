package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Vector;
import HollowKnight.model.game.scene.Scene;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RunningStateTest {
    private Knight knight;
    private RunningState runningState;
    private Scene mockedScene;

    @BeforeEach
    void setUp() {
        this.mockedScene = Mockito.mock(Scene.class);
        knight = new Knight(0, 0, 0,0,0);
        this.runningState = new RunningState(knight);
        knight.setState(runningState);
        knight.setVelocity(new Vector(1.8, 0));
        knight.setScene(mockedScene);
    }

    @Test
    void jump() {
        Vector result = runningState.jump();
        assertEquals(-Math.PI, result.y());
    }

    @Test
    void dashRight() {
        knight.setFacingRight(true);
        knight.setVelocity(new Vector(1, 0));
        Vector result = knight.dash();

        assertEquals(6.0, result.x());
        assertTrue(knight.isFacingRight());
    }

    @Test
    void dashLeft() {
        knight.setFacingRight(false);
        knight.setVelocity(new Vector(-1, 0));
        Vector result = knight.dash();

        assertEquals(-6.0, result.x());
        assertFalse(knight.isFacingRight());
    }

    @Test
    void updateVelocity() {
        when(mockedScene.collidesRight(Mockito.any(), Mockito.any())).thenReturn(false);
        knight.setVelocity(new Vector(1, 0));
        Vector result = knight.updateVelocity();
        assertEquals(1.35, result.x());
        assertEquals(0.0, result.y());
    }

    @Test
    void getNextState() {
    }
}