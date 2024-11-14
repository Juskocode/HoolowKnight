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

    public abstract Vector updateVelocity(Vector newVelocity);
}
