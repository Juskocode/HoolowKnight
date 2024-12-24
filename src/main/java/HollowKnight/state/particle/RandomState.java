package HollowKnight.state.particle;

import HollowKnight.controller.menu.ParticleMenuController;
import HollowKnight.model.dataStructs.Position;
import HollowKnight.model.menu.Particle;

import java.util.Random;

public class RandomState implements ParticleState {
    private final Random random = new Random();

    @Override
    public Position move(Particle particle, long tick, ParticleMenuController controller) {
        int newX = (int) (particle.getPosition().x() + random.nextInt(3) - 1);
        int newY = (int) (particle.getPosition().y() + random.nextInt(3) + 1);
        return controller.wrapPosition(newX, newY);
    }
}
