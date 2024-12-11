package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Vector;

public class DamagedState extends KnightState{

    public DamagedState(Knight knight) {
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
    public Vector updateVelocity(Vector newVelocity) {
        return limitVelocity(applyCollisions(newVelocity));
    }

    @Override
    public KnightState getNextState() {
        return null;
    }
}
