package HollowKnight.view.elements.particle;

import HollowKnight.gui.GUI;
import HollowKnight.model.dataStructs.Position;
import HollowKnight.model.dataStructs.Vector;
import HollowKnight.model.game.elements.Particle.Particle;
import HollowKnight.model.game.scene.Scene;
import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ParticleViewerTest {
    private ParticleViewer particleViewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        this.gui = mock(GUI.class);
        this.particleViewer = new ParticleViewer();
    }

    @Test
    public void draw() throws IOException {
        int size = 4;
        int x = 72, y = 88;
        long frameCount = 0;
        TextColor.RGB color = new TextColor.RGB(0,0,0);
        Particle particle = new Particle(x, y, new Position(1,1), color) {
            @Override
            protected Vector applyCollisions(Vector velocity) {
                return null;
            }

            @Override
            public Position moveParticle(Scene scene, long time) {
                return null;
            }
        };

        particleViewer.draw(particle, gui, frameCount,0,0);

        verify(gui).drawPixel(x, y, color);
    }
}