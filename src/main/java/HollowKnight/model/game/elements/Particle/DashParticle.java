package HollowKnight.model.game.elements.Particle;

import HollowKnight.model.dataStructs.Position;
import HollowKnight.model.dataStructs.Vector;
import HollowKnight.model.game.scene.Scene;
import com.googlecode.lanterna.TextColor;

import static java.lang.Math.max;

public class DashParticle extends Particle{

    public DashParticle(int x, int y, Position velocity, TextColor.RGB color){
        super( x, y,velocity, color);

    }

    @Override
    protected Vector applyCollisions(Vector velocity) {
        return velocity;
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

        return  new Position(position.x() + velocity.x(),
                position.y() + velocity.y());
    }
}
