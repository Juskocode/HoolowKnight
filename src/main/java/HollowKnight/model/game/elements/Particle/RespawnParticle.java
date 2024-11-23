package HollowKnight.model.game.elements.Particle;

import HollowKnight.model.Position;
import HollowKnight.model.Vector;
import HollowKnight.model.game.scene.Scene;
import com.googlecode.lanterna.TextColor;

import static java.lang.Math.max;

public class RespawnParticle extends Particle {

    public RespawnParticle(int x, int y, Position velocity, TextColor.RGB color) {
        super(x, y, velocity, color);
    }

    @Override
    protected Vector applyCollisions(Vector velocity)
    {
        double x = getPosition().x(), y = getPosition().y();
        double vx = velocity.x(), vy = velocity.y();
        Position size = new Position(1, 1);

        while (vy > 0 && getScene().collidesDown(new Position(x, y + vy), size))
            vy = Math.max(vy - 1, 0);
        /*
        while (vy < 0 && getScene().collidesUp(new Position(x, y + vy), size))
            vy = Math.min(vy + 1, 0);

        while (vx < 0 && getScene().collidesLeft(new Position(x + vx, y + vy), size))
            vx = Math.min(vx + 1, 0);

        while (vx > 0 && getScene().collidesRight(new Position(x + vx, y + vy), size))
            vx = max(vx - 1, 0);
        */
        return new Vector(vx, vy);
    }


    @Override
    public Position moveParticle(Scene scene, long time) {
        // Update position based on velocity
        Position position = new Position(
                getPosition().x() + getVelocity().x(),
                getPosition().y() + getVelocity().y()
        );

        Position velocity = new Position(
                getVelocity().x(),
                getVelocity().y() + 0.2
        );
        this.setVelocity(velocity);

        this.setOpacity(Math.max(0, getOpacity() - getFadeRate() ));

        Position next = new Position(position.x() + velocity.x(),
                position.y() + velocity.y());

        Vector v = new Vector(next.x(), next.y());
        //Vector vdt = applyCollisions(v);

        return new Position(v.x(), v.y());
    }


}