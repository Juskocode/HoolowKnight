package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Vector;

public class FallingState extends KnightState{
    public FallingState(Knight knight){
        super(knight);
    }

    @Override
    public Vector jump() {
        if (getKnight().getVelocity().y() >= 0 &&  getKnight().getVelocity().y() <= 1.0) {
            if (getKnight().getJumpCounter() < 2) {
                getKnight().setJumpCounter(getKnight().getJumpCounter() + 1);
                Vector newVelocity = new Vector(
                        getKnight().getVelocity().x(),
                        getKnight().getVelocity().y() - (getKnight().getJumpBoost())
                );
                getKnight().getScene().setDoubleJumpParticles(getKnight().createParticlesDoubleJump(20, getKnight().getScene()));
                //System.out.println(getKnight().getScene().getDoubleJumpParticles().size() + " jump particles");

                return updateVelocity(newVelocity);
            }
        }
        return updateVelocity(getKnight().getVelocity());
    }
    @Override
    public Vector dash() {
        Vector newVelocity = new Vector(
                getKnight().getVelocity().x() + (getKnight().isFacingRight() ? getKnight().getDashBoost():
                        -getKnight().getDashBoost()),
                getKnight().getVelocity().y()
        );
        return applyCollisions(newVelocity);
    }
    @Override
    public Vector updateVelocity(Vector newVelocity) {
        tickParticles();
        //System.out.println(getKnight().getScene().getDoubleJumpParticles().size() + " jump particles");
        if (getKnight().getVelocity().y() >= 0 && getKnight().getVelocity().y() <= 0.5) {

            Vector velocity = new Vector(
                    newVelocity.x() * getKnight().getAcceleration(),
                    newVelocity.y() + getKnight().getScene().getGravity() * 0.5
            );

            return limitVelocity(velocity);

        }

        Vector velocity = new Vector(
                 newVelocity.x() * getKnight().getAcceleration(),
                newVelocity.y() + getKnight().getScene().getGravity() * 1.75
        );

        return limitVelocity(velocity);
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
        if (getKnight().isOnGround())
            return getNextGroundState();
        if (getKnight().getJumpCounter() == 2)
            return getNextOnAirState();
        return this;
    }

}
