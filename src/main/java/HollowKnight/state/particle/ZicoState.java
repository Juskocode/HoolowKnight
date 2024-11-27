package HollowKnight.state.particle;

import HollowKnight.controller.menu.ParticleMenuController;
import HollowKnight.model.Position;
import HollowKnight.model.menu.Particle;

public class ZicoState implements ParticleState {
    @Override
    public Position move(Particle particle, long tick, ParticleMenuController controller) {

        return controller.wrapPosition((int) particle.getPosition().x(), (int) (particle.getPosition().y() + 10));
    }
}
