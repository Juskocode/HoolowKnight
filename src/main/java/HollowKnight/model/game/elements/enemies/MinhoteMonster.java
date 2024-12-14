package HollowKnight.model.game.elements.enemies;

import HollowKnight.model.Position;
import HollowKnight.model.Vector;
import HollowKnight.model.game.scene.Scene;

import static java.lang.Math.max;

public class MinhoteMonster extends Enemies {

    public MinhoteMonster(int x, int y,int HP, Scene scene, int damage) {
        super(x,y,HP,scene,damage);
        setVelocity(new Vector(0,-1.5));
    }

    @Override
    public Position updatePosition() {
        Vector resolvedVelocity = applyCollisions(getVelocity());

        // Update position with resolved velocity
        double newX = getPosition().x() + resolvedVelocity.x();
        double newY = getPosition().y() + resolvedVelocity.y();

        //Position newPosition = new Position(newX, newY);


        return new Position(newX, newY);
    }

    @Override
    protected Vector applyCollisions(Vector velocity) {
        double x = getPosition().x(), y = getPosition().y();
        double vx = velocity.x(), vy = velocity.y();
        Position knightSize = new Position(3, 3);

        while (vy > 0 && getScene().collidesDown(new Position(x, y + vy), knightSize))
            vy = Math.max(vy - 1, 0);

        while (vy < 0 && getScene().collidesUp(new Position(x, y + vy), knightSize))
            vy = Math.min(vy + 1, 0);
        return new Vector(vx, vy);
    }

    @Override
    public Position moveMonster() {
        return updatePosition();
    }
}
