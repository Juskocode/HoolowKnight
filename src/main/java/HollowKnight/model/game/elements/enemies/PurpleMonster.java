package HollowKnight.model.game.elements.enemies;

import HollowKnight.model.Position;
import HollowKnight.model.Vector;
import HollowKnight.model.game.scene.Scene;

import static java.lang.Math.max;

public class PurpleMonster extends Enemies {

    public PurpleMonster(int x, int y, int HP, Scene scene, int damage, Position size) {
        super(x,y,HP,scene,damage, size);
        setVelocity(new Vector(-1.5,0));
    }

    @Override
    public Position updatePosition() {
        Vector resolvedVelocity = applyCollisions(getVelocity());

        double newX = getPosition().x() + resolvedVelocity.x();
        double newY = getPosition().y() + resolvedVelocity.y();


        return new Position(newX, newY);
    }


    @Override
    protected Vector applyCollisions(Vector velocity) {
        double x = getPosition().x(), y = getPosition().y();
        double vx = velocity.x(), vy = velocity.y();
        Position knightSize = new Position(10, 9);

        if (vx < 0 && getScene().collidesLeft(new Position(x + vx, y + vy), knightSize)) {
            vx = Math.min(vx + 1, 0);
            setVelocity(new Vector(-velocity.x(), 0));
        }

        if (vx > 0 && getScene().collidesRight(new Position(x + vx, y + vy), knightSize)) {
            vx = Math.min(vx - 1, 0);
            setVelocity(new Vector(-velocity.x(), 0));
        }
        return new Vector(vx, vy);
    }

    @Override
    public Position moveMonster() {
        return updatePosition();
    }
}
