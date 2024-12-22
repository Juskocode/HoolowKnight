package HollowKnight.model.game.elements.Particle;

import HollowKnight.model.Position;
import HollowKnight.model.game.scene.Scene;
import com.googlecode.lanterna.TextColor;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.DoubleRange;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.lifecycle.BeforeTry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class JumpParticleTest {

    private static final int WIDTH = 160;
    private static final int HEIGHT = 90;

    private Scene scene;

    @BeforeTry
    public void setup() {
        this.scene = mock(Scene.class);
    }

    @Property
    public void move(
            @ForAll @IntRange(max = WIDTH) int x,
            @ForAll @IntRange(max = HEIGHT) int y
    ) {
        JumpParticle jumpParticle = new JumpParticle(x, y, new Position(0,0), new TextColor.RGB(0,0,0));

        Position newPosition = jumpParticle.moveParticle(scene,25);

        assertTrue(newPosition.x() != jumpParticle.getPosition().x());
        assertTrue(newPosition.y() != jumpParticle.getPosition().y());
    }
}