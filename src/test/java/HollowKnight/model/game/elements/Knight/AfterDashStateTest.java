package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Vector;
import HollowKnight.model.game.scene.Scene;
import HollowKnight.view.elements.knight.KnightViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AfterDashStateTest {
    private Knight knight;
    private AfterDashState afterDashState;
    private Scene mockedScene;

    @BeforeEach
    void setup() {
        mockedScene = mock(Scene.class);
        knight = new Knight(0, 0, 50,0,0);
        knight.setScene(mockedScene);
        afterDashState = new AfterDashState(knight);
        knight.setState(afterDashState);
        knight.setFacingRight(true);
        knight.setVelocity(new Vector(1.8, 2.0));
        when(mockedScene.getGravity()).thenReturn(0.25);
    }

    @Test
    void jump() {
        Vector result = afterDashState.jump();

        assertEquals(1.35, result.x());
        assertEquals(2.25, result.y());
    }

    @Test
    void dash() {
        Vector result = afterDashState.dash();

        assertEquals(1.35, result.x());
        assertEquals(2.25, result.y());
        assertTrue(knight.isFacingRight());
    }

    @Test
    void updateVelocity() {
        Vector result = afterDashState.updateVelocity(knight.getVelocity());

        assertEquals(1.35, result.x());
        assertEquals(2.25, result.y());

    }


    @Test
    void getNextStateIdle() {
        when(knight.isOnGround()).thenReturn(true);
        knight.setVelocity(new Vector(0.0, 0.0));

        KnightState nextState = afterDashState.getNextState();

        assertInstanceOf(IdleState.class, nextState);
    }

    @Test
    void getNextStateWalking() {
        when(knight.isOnGround()).thenReturn(true);
        knight.setVelocity(new Vector(1.0, 0.0));

        KnightState nextState = afterDashState.getNextState();

        assertInstanceOf(WalkingState.class, nextState);
    }

    @Test
    void getNextStateRunning() {
        when(knight.isOnGround()).thenReturn(true);
        knight.setVelocity(new Vector(2.0, 0.0));

        KnightState nextState = afterDashState.getNextState();

        assertInstanceOf(RunningState.class, nextState);
    }

    @Test
    void getNextStateStay() {
        KnightState nextState = afterDashState.getNextState();

        assertSame(afterDashState, nextState);
    }
}