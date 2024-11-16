package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Vector;

public class FallingState extends KnightState{
    public FallingState(Knight knight){
        super(knight);
    }
    @Override
    public Vector updateVelocity(Vector newVelocity) {
        Vector Velocity = new Vector(
                 newVelocity.x(),
                newVelocity.y() * getKnight().getScene().getGravity()
        );
        return limitVelocity(newVelocity);
    }

    @Override
    public KnightState getNextState() {
        return null;
    }

}
