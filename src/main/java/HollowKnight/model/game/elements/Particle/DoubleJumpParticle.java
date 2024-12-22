package HollowKnight.model.game.elements.Particle;

import HollowKnight.model.Position;
import HollowKnight.model.Vector;
import HollowKnight.model.game.scene.Scene;
import com.googlecode.lanterna.TextColor;

import static java.lang.Math.max;

public class DoubleJumpParticle extends Particle {
    public DoubleJumpParticle(int x, int y, Position velocity, TextColor.RGB color) {
        super(x, y, velocity, color);
    }

    @Override
    protected Vector applyCollisions(Vector velocity)
    {
        return velocity;
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
