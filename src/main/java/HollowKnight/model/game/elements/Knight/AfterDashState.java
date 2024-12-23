package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.dataStructs.Vector;

public class AfterDashState extends KnightState{

    public AfterDashState(Knight knight) {
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
        Vector newVelocity = new Vector(
                velocity.x() * getKnight().getAcceleration(),
                velocity.y() + getKnight().getScene().getGravity()
        );
        return limitVelocity(applyCollisions(newVelocity));
    }

    @Override
    public KnightState getNextState() {
        if (getKnight().getScene().collideSpike())
            return new RespawnState(getKnight(), 10);
        if (getKnight().isOnGround())
            return getNextGroundState();
        return this;

    }
}
