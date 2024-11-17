package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Vector;
import HollowKnight.state.GameState;

public class MaxVelocityState extends KnightState {

    public MaxVelocityState(Knight knight) {
        super(knight);
    }

    @Override
    public Vector updateVelocity(Vector velocity) {
        Vector newVelocity = new Vector(
                velocity.x() * getKnight().getAcceleration(),
                velocity.y()
        );
        return limitVelocity(newVelocity);
    }

    @Override
    public KnightState getNextState() {
        if (Math.abs(getKnight().getVelocity().x()) < RunningState.MAX_VELOCITY)
            return new RunningState(getKnight());
        return this;
    }
}
