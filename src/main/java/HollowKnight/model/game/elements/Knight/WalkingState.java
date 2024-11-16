package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Vector;

public class WalkingState extends KnightState{
    public static double MIN_Velocity = 0.1;

    public WalkingState(Knight knight) {
        super(knight);
    }

    @Override
    public Vector updateVelocity(Vector newVelocity) {
        return null;
    }

    @Override
    public KnightState getNextState() {
        return null;
    }
}
