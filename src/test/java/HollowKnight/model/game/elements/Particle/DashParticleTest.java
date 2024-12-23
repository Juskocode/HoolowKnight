package HollowKnight.model.game.elements.Particle;

import HollowKnight.model.dataStructs.Position;
import HollowKnight.model.game.scene.Scene;
import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DashParticleTest {
    private Scene scene;
    private int time;
    private DashParticle dashParticle;
    @BeforeEach
    void setUp() {
        this.scene = Mockito.mock(Scene.class);
        this.time=0;
        this.dashParticle = new DashParticle(1,1, new Position(1,1)
                ,new TextColor.RGB(0,0,0));
    }

    @Test
    void moveParticle() {
        Position position = dashParticle.moveParticle(scene,time);
        Assertions.assertNotEquals(position.x(), dashParticle.getPosition().x());
        Assertions.assertNotEquals(position.y(), dashParticle.getPosition().y());
    }
}