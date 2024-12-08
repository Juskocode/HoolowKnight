package HollowKnight.model.game.elements.enemies;

import HollowKnight.model.Position;
import HollowKnight.model.Vector;
import HollowKnight.model.game.elements.Element;
import HollowKnight.model.game.scene.Scene;

import static java.lang.Math.max;

public class PurpleMonster extends Enemies {
    private int damage;
    public PurpleMonster(int x, int y, int HP, Scene scene, int damage) {
        super(x,y,HP,scene);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    protected Vector applyCollisions(Vector velocity) {
        double x = getPosition().x(), y = getPosition().y();
        double vx = velocity.x(), vy = velocity.y();
        Position knightSize = new Position(1, 1);

        while (vy > 0 && getScene().collidesDown(new Position(x, y + vy), knightSize))
            vy = Math.max(vy - 1, 0);

        while (vy < 0 && getScene().collidesUp(new Position(x, y + vy), knightSize))
            vy = Math.min(vy + 1, 0);

        while (vx < 0 && getScene().collidesLeft(new Position(x + vx, y + vy), knightSize))
            vx = Math.min(vx + 1, 0);

        while (vx > 0 && getScene().collidesRight(new Position(x + vx, y + vy), knightSize))
            vx = max(vx - 1, 0);

        return new Vector(vx, vy);
    }
}
