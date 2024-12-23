package HollowKnight.state.particle;


import HollowKnight.controller.menu.ParticleMenuController;
import HollowKnight.model.dataStructs.Position;
import HollowKnight.model.menu.Particle;

import java.util.Random;

public class CalmState implements ParticleState {
    private final Random random = new Random();

    @Override
    public Position move(Particle particle, long tick, ParticleMenuController controller) {
        int newX = (int) (particle.getPosition().x() + random.nextInt(3) - 1); // -1, 0, or +1
        int newY = (int) (particle.getPosition().y() + 1); // Slowly drifting downward

        return controller.wrapPosition(newX, newY);
    }
}
