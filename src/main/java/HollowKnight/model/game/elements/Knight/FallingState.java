package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Vector;

public class FallingState extends KnightState{
    public FallingState(Knight knight){
        super(knight);
    }

    @Override
    public Vector jump() {
        return updateVelocity(getKnight().getVelocity());
    }

    @Override
    public Vector updateVelocity(Vector newVelocity) {
        Vector velocity = new Vector(
                 newVelocity.x() * getKnight().getAcceleration(),
                newVelocity.y() + getKnight().getScene().getGravity()
        );
        return limitVelocity(velocity);
    }

    @Override
    public KnightState getNextState() {
        if (getKnight().isOnGround())
            return getNextGroundState();
        return this;
    }

}
