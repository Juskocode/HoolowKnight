package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.dataStructs.Vector;
import HollowKnight.model.game.scene.Scene;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DashStateTest {
    private Knight knight;
    private Scene mockedScene;
    private DashState dashState;
    @BeforeEach
    void setup() {
        mockedScene = mock(Scene.class);
        knight = new Knight(0, 0, 50,0,0);
        knight.setScene(mockedScene);
        dashState = new DashState(knight);
        knight.setState(dashState);
        knight.setFacingRight(true);
        knight.setVelocity(new Vector(5.0, 2.0));
        when(mockedScene.getGravity()).thenReturn(0.25);
    }

    @Test
    void jump() {
        Vector result = dashState.jump();

        assertEquals(3.75, result.x());
        assertEquals(2.25, result.y());
    }

    @Test
    void dash() {
        Vector result = dashState.dash();

        assertEquals(3.75, result.x());
        assertEquals(2.25, result.y());
        assertTrue(knight.isFacingRight());
    }

    @Test
    void updateVelocity() {
        Vector result = dashState.updateVelocity(knight.getVelocity());

        assertEquals(3.75, result.x());
        assertEquals(2.25, result.y());

    }


    @Test
    void getNextStateAfterDash() {
        knight.setVelocity(new Vector(1.6, knight.getVelocity().y()));

        KnightState nextState = dashState.getNextState();

        assertTrue(nextState instanceof AfterDashState);
    }

    @Test
    void getNextStateStay() {
        KnightState nextState = dashState.getNextState();

        assertSame(dashState, nextState);
    }
}