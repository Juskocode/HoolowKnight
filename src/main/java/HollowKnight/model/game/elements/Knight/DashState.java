package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Vector;

import static HollowKnight.model.game.elements.Knight.RunningState.MIN_VELOCITY;

public class DashState extends KnightState{


    public DashState(Knight knight) {
        super(knight);
    }

    @Override
    public Vector jump() {
        return updateVelocity(getKnight().getVelocity());
    }

    @Override
    public Vector dash() {
        return updateVelocity(getKnight().getVelocity());
    }

    @Override
    public Vector updateVelocity(Vector velocity) {
        tickParticles();
        Vector newVelocity = new Vector(
                velocity.x() * getKnight().getAcceleration(),
                velocity.y() + getKnight().getScene().getGravity()
        );
        return applyCollisions(newVelocity);
    }
    @Override
    public KnightState getNextState() {
        if (getKnight().getScene().collideSpike())
            return new RespawnState(getKnight(), 10);
        if (getParticlesTimer() == 0)
        {
            getKnight().getScene().setRespawnParticles(getKnight().createRespawnParticles(0));
            resetParticlesTimer();
        }
        if (Math.abs(getKnight().getVelocity().x()) < MIN_VELOCITY)
            return new AfterDashState(getKnight());
        return this;
    }
}
