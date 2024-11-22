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
    public Vector updateVelocity(Vector velocity) {
        Vector newVelocity = new Vector(
                velocity.x() * getKnight().getAcceleration(),
                velocity.y() + getKnight().getScene().getGravity()
        );
        return limitVelocity(applyCollisions(newVelocity));
    }

    @Override
    public KnightState getNextState() {
        if (getKnight().getVelocity().y() >= 0)
            return new FallingState(getKnight());

        return this;
    }
}
