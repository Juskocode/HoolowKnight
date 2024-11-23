package HollowKnight.model.game.elements.Particle;

import HollowKnight.model.Position;
import HollowKnight.model.game.scene.Scene;
import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class DoubleJumpParticleTest {
    private Scene scene;
    private int time;
    private DoubleJumpParticle doubleJumpParticle;
    @BeforeEach
    void setUp() {
        this.scene = Mockito.mock(Scene.class);
        this.time=0;
        this.doubleJumpParticle = new DoubleJumpParticle(1,1, new Position(1,1)
                ,new TextColor.RGB(0,0,0));
    }

    @Test
    void moveParticle() {
        Position position = doubleJumpParticle.moveParticle(scene,time);
        Assertions.assertNotEquals(position, doubleJumpParticle.getPosition());

    }
}