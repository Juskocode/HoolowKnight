package HollowKnight.controller.game;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.model.Position;
import HollowKnight.model.game.elements.Particle.Particle;
import HollowKnight.model.game.scene.Scene;

import java.io.IOException;
import java.util.Random;

public class ParticleController extends Controller<Scene>{

    public ParticleController(Scene model) {
        super(model);
    }

    @Override
    public void move(Game game, GUI.ACTION action, long time) throws IOException {
        for(Particle particle: getModel().getParticles()){
            particle.setPosition(ParticleMove(particle, time));
        }
    }
    public Position ParticleMove(Particle particle, long time) {
        Position currentPos = particle.getPosition();
        if (currentPos == null) {
            throw new IllegalStateException("Particle position is null");
        }

        // Handle wind simulation
        int windEffect = (int) (Math.random() * 3) - 1; // Wind effect range: -1 to 1
        int new_x = (int) particle.getPosition().x() + 2 + windEffect;  // Adds wind effect to horizontal movement
        int new_y = (int) particle.getPosition().y() + 2;  // Vertical movement remains constant

        // Handle boundary conditions for x and y
        if (new_x < 0) {
            new_x = getModel().getWidth() - 1;
        } else if (new_x >= getModel().getWidth()) {
            new_x = 1;
        }

        if (new_y >= getModel().getHeight()) {
            new_y = 0;  // Particle reaches bottom and is "destroyed" (restarts from top)
            particle.setLifetime(particle.getLifetime() + 1);  // Increment the lifetime

            // If the particle has lived for 900 ticks, stop regenerating
            if (particle.getLifetime() >= 900) {
                if (particle.getLastRegenerationTime() == 0 || (time - particle.getLastRegenerationTime()) >= particle.getRandomRegenerationDelay()) {
                    // Particle starts regenerating again after a random range of ticks
                    particle.setLifetime(0); // Reset lifetime
                    particle.setLastRegenerationTime(time); // Reset last regeneration time
                }
            }
        }

        return new Position(new_x, new_y);
    }

}
