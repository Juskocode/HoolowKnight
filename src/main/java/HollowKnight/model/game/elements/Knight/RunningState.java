package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Vector;

public class RunningState extends KnightState{

    public static double MIN_VELOCITY = 1.75;
    public static double MAX_VELOCITY = 2.00;

    public RunningState(Knight knight){
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
        if (Math.abs(getKnight().getVelocity().x()) == RunningState.MAX_VELOCITY)
            return new MaxVelocityState(getKnight());
        if (Math.abs(getKnight().getVelocity().x()) < RunningState.MIN_VELOCITY)
            return new WalkingState(getKnight());
        return this;
    }
}
