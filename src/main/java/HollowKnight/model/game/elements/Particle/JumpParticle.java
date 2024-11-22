package HollowKnight.model.game.elements.Particle;

import HollowKnight.model.Position;
import HollowKnight.model.Vector;
import HollowKnight.model.game.scene.Scene;
import com.googlecode.lanterna.TextColor;

public class JumpParticle extends Particle {

    public JumpParticle(int x, int y, Position velocity, TextColor.RGB color) {
        super(x, y, velocity, color);
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
