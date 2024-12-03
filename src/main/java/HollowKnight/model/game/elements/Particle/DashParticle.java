package HollowKnight.model.game.elements.Particle;

import HollowKnight.model.Position;
import HollowKnight.model.Vector;
import HollowKnight.model.game.scene.Scene;
import com.googlecode.lanterna.TextColor;

import static java.lang.Math.max;

public class DashParticle extends Particle{

    public DashParticle(int x, int y, Position velocity, TextColor.RGB color){
        super( x, y,velocity, color);

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

    @Override
    public Position moveParticle(Scene scene, long time) {
        // Update position based on velocity
        Position position = new Position(
                getPosition().x() + getVelocity().x(),
                getPosition().y() + getVelocity().y()
        );

        // Apply gravity to y-velocity
        Position velocity = new Position(getVelocity().x(), getVelocity().y() + 0.1);
        this.setVelocity(velocity);
        // Reduce opacity to fade out
        this.setOpacity(Math.max(0, getOpacity() - getFadeRate() * getVelocity().y()));



        return new Position(position.x() + velocity.x(),
                position.y() + velocity.y());
    }
}
