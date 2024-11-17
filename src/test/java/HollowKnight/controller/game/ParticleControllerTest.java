package HollowKnight.controller.game;

import HollowKnight.Game;
import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Particle.Particle;
import HollowKnight.model.game.scene.Scene;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParticleControllerTest {
    private Game game;
    private ParticleController particleController;
    private Scene scene;
    @BeforeEach
    void setUp() {
         this.game = Mockito.mock(Game.class);
         this.scene = Mockito.mock(Scene.class);
         this.particleController = new ParticleController(scene);
    }

    @Test
    void move() throws IOException {
        Particle rain = Mockito.mock(Particle.class);
        List<Particle> rainParticles = Arrays.asList(rain,rain);
        when(scene.getParticles()).thenReturn(rainParticles);

        particleController.move(game, GUI.ACTION.NULL, 0);

        verify(rain, times(2))
                .setPosition(Mockito.any());
    }


}