package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Vector;

public class RunningState extends KnightState{
    public static double MIN_Velocity = 1.0;
    public RunningState(Knight knight){
        super(knight);
    }

    @Override
    public Vector updateVelocity(Vector velocity) {
        Vector newVelocity = new Vector(
                velocity.x() * getKnight().getAcceleration(),
                velocity.y()
        );
        return limitVelocity(newVelocity);
    }

    @Override
    public KnightState getNextState() {
        return null;
    }
}
