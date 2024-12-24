package HollowKnight.model.game.elements.Knight;


import HollowKnight.model.dataStructs.Vector;
import HollowKnight.model.game.elements.Particle.Particle;
import HollowKnight.model.game.elements.Particle.RespawnParticle;
import HollowKnight.model.game.scene.Scene;
import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RespawnStateTest {
    private Knight knight;
    private RespawnState respawnState;
    private Scene mockedScene;

    @BeforeEach
    void setUp() {
        this.mockedScene = Mockito.mock(Scene.class);
        this.knight = spy(new Knight(0, 0, 0,0,0));
        this.respawnState = new RespawnState(knight,10);
        this.knight.setState(respawnState);
        this.knight.setVelocity(new Vector(0, 0));
        this.knight.setScene(mockedScene);
    }

    @Test
    void jump() {
        Vector result = respawnState.jump();

        assertEquals(0, result.x());
        assertEquals(0, result.y());
    }

    @Test
    void dash() {
        Vector result = respawnState.dash();

        assertEquals(0, result.x());
        assertEquals(0, result.y());
        assertTrue(knight.isFacingRight());
    }

    @Test
    void updateVelocity() {
        Vector result = respawnState.updateVelocity(knight.getVelocity());

        assertEquals(0, result.x());
        assertEquals(0, result.y());
    }

    @Test
    void getNextState() {

    }

    @Test
    void getNextStateStay() throws IOException {
        KnightState nextState = respawnState.getNextState();

        assertSame(respawnState, nextState);
    }
}