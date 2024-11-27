package HollowKnight.state.particle;

import HollowKnight.controller.menu.ParticleMenuController;
import HollowKnight.model.Position;
import HollowKnight.model.menu.Particle;

public interface ParticleState {
    Position move(Particle particle, long tick, ParticleMenuController controller);
}
