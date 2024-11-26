package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Vector;
import HollowKnight.model.game.scene.Scene;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class WalkingStateTest {
    private Knight knight;
    private Scene mockedScene;
    private WalkingState walkingState;
    @BeforeEach
    void setUp() {
        this.mockedScene = Mockito.mock(Scene.class);
        knight = new Knight(0, 0, 0,0,0);
        this.walkingState = new WalkingState(knight);
        knight.setState(walkingState);
        knight.setVelocity(new Vector(0.8, 0));
        knight.setScene(mockedScene);
    }

    @Test
    void updateVelocity() {
        when(mockedScene.collidesRight(Mockito.any(), Mockito.any())).thenReturn(false);
        knight.setVelocity(new Vector(1, 0));
        Vector result = knight.updateVelocity();
        assertEquals(0.75, result.x());
        assertEquals(0.0, result.y());
    }

    @Test
    void getNextStateIdle() {
        when(mockedScene.collidesDown(Mockito.any(), Mockito.any())).thenReturn(true);
        knight.setVelocity(new Vector(0.5, 0));

        KnightState nextState = knight.getNextState();

        assertInstanceOf(IdleState.class, nextState);
    }

    @Test
    void getNextStateStay(){
        when(mockedScene.collidesDown(Mockito.any(), Mockito.any())).thenReturn(true);
        KnightState nextState = knight.getNextState();
        assertInstanceOf(WalkingState.class, nextState);
    }

}