package HollowKnight.state.particle;

import HollowKnight.controller.menu.ParticleMenuController;
import HollowKnight.model.dataStructs.Position;
import HollowKnight.model.menu.Particle;

import java.util.Random;

public class DispersingState implements ParticleState {
    private final Random random = new Random();

    @Override
    public Position move(Particle particle, long tick, ParticleMenuController controller) {
        double windAngle = controller.getWindAngle(); // Stronger wind
        double windSpeed = controller.getWindSpeed() * 1.5; // Increased wind speed

        int newX = (int) (particle.getPosition().x() +
                        (int) (windSpeed * Math.cos(windAngle)) + random.nextInt(3) - 1); // Add randomness
        int newY = (int) (particle.getPosition().y() +
                        (int) (windSpeed * Math.sin(windAngle)) + random.nextInt(3) - 1); // Add randomness

        return controller.wrapPosition(newX, newY);
    }
}
