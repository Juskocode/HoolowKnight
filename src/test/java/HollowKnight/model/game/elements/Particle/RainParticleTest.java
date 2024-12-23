package HollowKnight.model.game.elements.Particle;

import HollowKnight.model.dataStructs.Position;
import HollowKnight.model.game.scene.Scene;
import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RainParticleTest {
    private Scene scene;
    private int time;
    private RainParticle rainParticle;
    @BeforeEach
    void setUp() {
        this.scene = Mockito.mock(Scene.class);
        this.time=0;
        this.rainParticle = new RainParticle(1,1, new Position(1,1)
                        ,new TextColor.RGB(0,0,0));
    }

    @Test
    void moveParticle() {
        Mockito.when(scene.getHeight()).thenReturn(40);
        Mockito.when(scene.getWidth()).thenReturn(40);
        Position newPosition = rainParticle.moveParticle(scene,time);
        Assertions.assertNotEquals(rainParticle.getPosition(), newPosition);
    }
}