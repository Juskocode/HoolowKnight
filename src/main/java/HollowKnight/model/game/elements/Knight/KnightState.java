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

    public abstract Vector updateVelocity(Vector newVelocity);
}
