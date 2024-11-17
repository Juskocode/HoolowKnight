package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Vector;

public class WalkingState extends KnightState{
    public static double MIN_VELOCITY = 0.75;

    public WalkingState(Knight knight) {
        super(knight);
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
        if (Math.abs(getKnight().getVelocity().x()) >= RunningState.MIN_VELOCITY)
            return new RunningState(getKnight());
        if (Math.abs(getKnight().getVelocity().x()) < WalkingState.MIN_VELOCITY)
            return new IdleState(getKnight());
        return this;
    }
}
