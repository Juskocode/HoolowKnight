package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Vector;
import HollowKnight.state.GameState;

public class IdleState extends KnightState {

    public IdleState(Knight knight) {
        super(knight);
    }

    @Override
    public Vector jump() {
        getKnight().setJumpCounter(getKnight().getJumpCounter() + 1);
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
        getKnight().setJumpCounter(0);
        if (Math.abs(getKnight().getVelocity().x()) >= WalkingState.MIN_VELOCITY)
            return new WalkingState(getKnight());
        return this;
    }
}
