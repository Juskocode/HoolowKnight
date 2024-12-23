package HollowKnight.state.particle;

import HollowKnight.controller.menu.ParticleMenuController;
import HollowKnight.model.dataStructs.Position;
import HollowKnight.model.menu.Particle;

public class WindyState implements ParticleState {
    @Override
    public Position move(Particle particle, long tick, ParticleMenuController controller) {
        double windAngle = controller.getWindAngle(); // Obtain wind properties from the controller
        double windSpeed = controller.getWindSpeed();

        int newX = (int) (particle.getPosition().x() + (int) (windSpeed * Math.cos(windAngle)));
        int newY = (int) (particle.getPosition().y() + (int) (windSpeed * Math.sin(windAngle)));

        return controller.wrapPosition(newX, newY);
    }
}
