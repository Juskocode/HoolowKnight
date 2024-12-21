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
        Vector result = runningState.dash();

        assertEquals(6.0, result.x());
        assertTrue(knight.isFacingRight());
    }

    @Test
    void dashLeft() {
        knight.setFacingRight(false);
        knight.setVelocity(new Vector(-1, 0));
        Vector result = runningState.dash();

        assertEquals(-6.0, result.x());
        assertFalse(knight.isFacingRight());
    }

    @Test
    void updateVelocity() {
        when(mockedScene.collidesRight(Mockito.any(), Mockito.any())).thenReturn(false);
        knight.setVelocity(new Vector(1, 0));
        Vector result = runningState.updateVelocity(knight.getVelocity());
        assertEquals(0.75, result.x());
        assertEquals(0.0, result.y());
    }

    @Test
    void getNextStateStay() {
        when(mockedScene.collidesDown(Mockito.any(), Mockito.any())).thenReturn(true);
        KnightState nextState = runningState.getNextState();
        assertInstanceOf(RunningState.class, nextState);
    }

    @Test
    void getNextStateWalking() {
        when(mockedScene.collidesDown(Mockito.any(), Mockito.any())).thenReturn(true);
        knight.setVelocity(new Vector(0.5, 0));

        KnightState nextState = runningState.getNextState();

        assertInstanceOf(WalkingState.class, nextState);
    }

    @Test
    void getNextStateDashing() {
        when(knight.isOnGround()).thenReturn(true);
        knight.setVelocity(new Vector(10, knight.getVelocity().y()));

        KnightState nextState = runningState.getNextState();

        assertInstanceOf(DashState.class, nextState);
    }

    @Test
    void getNextStateJumping() {
        when(knight.isOnGround()).thenReturn(false);
        knight.setVelocity(new Vector(knight.getVelocity().x(), -10));

        KnightState nextState = runningState.getNextState();

        assertInstanceOf(JumpState.class, nextState);
    }
}