package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Vector;
import HollowKnight.state.GameState;

public class MaxVelocityState extends KnightState {

    public MaxVelocityState(Knight knight) {
        super(knight);
    }

    @Override
    public Vector jump() {
        Vector newVelocity = new Vector(
                getKnight().getVelocity().x(),
                getKnight().getVelocity().y() - getKnight().getJumpBoost()
        );
        return updateVelocity(newVelocity);
    }

    @Override
    public Vector updateVelocity(Vector velocity) {
        Vector newVelocity = new Vector(
                velocity.x() * getKnight().getAcceleration(),
                velocity.y()
        );
        return limitVelocity(applyCollisions(newVelocity));
    }

    @Override
    public KnightState getNextState() {
        if (!getKnight().isOnGround())
            return getNextOnAirState();
        if (Math.abs(getKnight().getVelocity().x()) < RunningState.MAX_VELOCITY)
            return new RunningState(getKnight());
        return this;
    }
}
