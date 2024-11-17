package HollowKnight.controller.game;

import HollowKnight.Game;
import HollowKnight.gui.GUI;
import HollowKnight.model.Position;
import HollowKnight.model.game.elements.Particle.Particle;
import HollowKnight.model.game.scene.Scene;
import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.Assertions;
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
        // Mock the Particle class
        Particle rain = Mockito.mock(Particle.class);

        // Use a real Position instance
        Position position = new Position(5, 5);
        when(rain.getPosition()).thenReturn(position);

        // Mock a list of particles
        List<Particle> rainParticles = Arrays.asList(rain, rain);
        when(scene.getParticles()).thenReturn(rainParticles);

        // Invoke the move method
        particleController.move(game, GUI.ACTION.NULL, 0);

        // Verify that setPosition was called twice
        verify(rain, times(2)).setPosition(Mockito.any());
    }

    @Test
    public void ParticleMove(){
        TextColor.RGB color =Mockito.mock(TextColor.RGB.class);
        Particle particle = new Particle(10,10,color);
        Position distinct = particleController.ParticleMove(particle);
        Assertions.assertNotEquals(particle.getPosition(), distinct);
    }

}