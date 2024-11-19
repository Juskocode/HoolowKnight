package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Position;
import HollowKnight.model.Vector;

import static java.lang.Math.max;

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

    protected Vector applyCollisions(Vector velocity) {
        double x = knight.getPosition().x(), y = knight.getPosition().y();
        double vx = velocity.x(), vy = velocity.y();
        Position knightSize = new Position(knight.getWidth(), knight.getHeight());

        while (vy > 0 && knight.getScene().collidesDown(new Position(x, y + vy), knightSize))
            vy = Math.max(vy - 1, 0);

        while (vy < 0 && knight.getScene().collidesUp(new Position(x, y + vy), knightSize))
            vy = Math.min(vy + 1, 0);

        while (vx < 0 && knight.getScene().collidesLeft(new Position(x + vx, y + vy), knightSize))
            vx = Math.min(vx + 1, 0);

        while (vx > 0 && knight.getScene().collidesRight(new Position(x + vx, y + vy), knightSize))
            vx = max(vx - 1, 0);

        return new Vector(vx, vy);
    }

    protected KnightState getNextGroundState() {
        if (Math.abs(getKnight().getVelocity().x()) >= RunningState.MIN_VELOCITY)
            return new RunningState(getKnight());
        if (Math.abs(getKnight().getVelocity().x()) >= WalkingState.MIN_VELOCITY)
            return new WalkingState(getKnight());
        return new IdleState(getKnight());
    }

    protected KnightState getNextOnAirState() {
        if (getKnight().getVelocity().y() < 0)
            return new JumpState(getKnight());
        return new FallingState(getKnight());
    }

    public abstract Vector jump();
    public abstract Vector updateVelocity(Vector newVelocity);
    public abstract KnightState getNextState();
}
