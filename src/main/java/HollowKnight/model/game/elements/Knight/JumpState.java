package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Vector;

public class JumpState extends KnightState {

    public JumpState(Knight knight) {
        super(knight);
    }

    @Override
    public Vector jump() {
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
