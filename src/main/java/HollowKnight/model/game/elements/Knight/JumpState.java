package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Vector;

public class JumpState extends KnightState {

    public JumpState(Knight knight) {
        super(knight);
    }

    @Override
    public Vector jump() {
        if (getKnight().getJumpCounter() < 2) {
            getKnight().setJumpCounter(getKnight().getJumpCounter() + 1);
            Vector newVelocity = new Vector(
                    getKnight().getVelocity().x(),
                    getKnight().getVelocity().y() - (getKnight().getJumpBoost() / 3)
            );
            //getKnight().setVelocity();
            getKnight().getScene().setDoubleJumpParticles(getKnight().createParticlesDoubleJump(5, getKnight().getScene()));

            return updateVelocity(newVelocity);
        }

        return updateVelocity(getKnight().getVelocity());
    }
    @Override
    public Vector dash() {

        Vector newVelocity = new Vector(
                  (getKnight().isFacingRight() ? getKnight().getDashBoost():
                        -getKnight().getDashBoost()),
                getKnight().getVelocity().y()
        );
        return applyCollisions(newVelocity);
    }

    @Override
    public Vector updateVelocity(Vector newvelocity) {
        tickParticles();
        if (getKnight().getVelocity().y() < 0 && getKnight().getVelocity().y() >= -0.5) {

            Vector velocity = new Vector(
                    newvelocity.x() * getKnight().getAcceleration(),
                    newvelocity.y() + getKnight().getScene().getGravity() * 0.5
            );

            return limitVelocity(velocity);

        }

        Vector velocity = new Vector(
                newvelocity.x() * getKnight().getAcceleration(),
                newvelocity.y() + getKnight().getScene().getGravity()
        );
        return limitVelocity(applyCollisions(velocity));
    }

    @Override
    public KnightState getNextState() {
        if (getParticlesTimer() == 0)
        {
            getKnight().getScene().setRespawnParticles(getKnight().createRespawnParticles(0));
            resetParticlesTimer();
        }
        if (getKnight().isOverMaxXVelocity())
            return new DashState(getKnight());
        if (getKnight().getVelocity().y() >= 0)
            return new FallingState(getKnight());

        return this;
    }
}
