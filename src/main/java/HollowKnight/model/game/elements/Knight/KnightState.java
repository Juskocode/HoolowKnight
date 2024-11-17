package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Position;
import HollowKnight.model.Vector;

public abstract class KnightState {
    private final Knight knight;

    public KnightState(Knight knight){
        this.knight = knight;
    }
    public Knight getKnight() {
        return knight;
    }

    public Vector moveKnightLeft() {
        Vector newVelocity = new Vector(
                knight.getVelocity().x() - knight.getAcceleration(),
                knight.getVelocity().y()
        );
        return updateVelocity(newVelocity);
    }

    public Vector moveKnightRight() {
        Vector newVelocity = new Vector(
                knight.getVelocity().x() + knight.getAcceleration(),
                knight.getVelocity().y()
        );

        return updateVelocity(newVelocity);
    }

    protected Vector limitVelocity(Vector velocity) {
        double vx = Math.min(knight.getMaxVelocity().x(), Math.max(-knight.getMaxVelocity().x(), velocity.x()));
        double vy = Math.min(knight.getMaxVelocity().y(), velocity.y());
        if (Math.abs(vx) < 0.2)
            vx = 0;
        return new Vector(vx, vy);
    }

    protected KnightState getNextGroundState() {
        if (Math.abs(getKnight().getVelocity().x()) >= RunningState.MIN_VELOCITY)
            return new RunningState(getKnight());
        if (Math.abs(getKnight().getVelocity().x()) >= WalkingState.MIN_VELOCITY)
            return new WalkingState(getKnight());
        return new IdleState(getKnight());
    }

    public abstract Vector updateVelocity(Vector newVelocity);
    public abstract KnightState getNextState();
}
