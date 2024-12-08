package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Vector;

public class RunningState extends KnightState{

    public static double MIN_VELOCITY = 1.75;
    public static double MAX_VELOCITY = 2.0;

    public RunningState(Knight knight){
        super(knight);
    }

    @Override
    public Vector jump() {
        getKnight().setJumpCounter(getKnight().getJumpCounter() + 1);
        Vector newVelocity = new Vector(
                getKnight().getVelocity().x(),
                getKnight().getVelocity().y() - getKnight().getJumpBoost()
        );
        getKnight().getScene().setJumpParticles(getKnight().createParticlesJump(10));

        return updateVelocity(newVelocity);
    }
    @Override
    public Vector dash() {
        Vector newVelocity = new Vector(
                 (getKnight().isFacingRight() ? getKnight().getDashBoost():
                        -getKnight().getDashBoost()),
                getKnight().getVelocity().y()
        );
        getKnight().getScene().setDashParticles(getKnight().createDashParticles(10));
        return applyCollisions(newVelocity);
    }
    @Override
    public Vector updateVelocity(Vector velocity) {
        tickParticles();
        Vector newVelocity = new Vector(
                velocity.x() * getKnight().getAcceleration(),
                velocity.y()
        );
        return limitVelocity(applyCollisions(newVelocity));
    }

    @Override
    public KnightState getNextState() {
        if (getParticlesTimer() == 0)
        {
            getKnight().getScene().setRespawnParticles(getKnight().createRespawnParticles(0));
            resetParticlesTimer();
        }
        if (!getKnight().isOnGround())
            return getNextOnAirState();
        getKnight().setJumpCounter(0);
        if (getKnight().isOverMaxXVelocity())
            return new DashState(getKnight());
        if (Math.abs(getKnight().getVelocity().x()) >= RunningState.MAX_VELOCITY)
            return new MaxVelocityState(getKnight());
        if (Math.abs(getKnight().getVelocity().x()) < RunningState.MIN_VELOCITY)
            return new WalkingState(getKnight());
        return this;
    }
}
