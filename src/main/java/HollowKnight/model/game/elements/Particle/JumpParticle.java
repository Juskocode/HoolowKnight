package HollowKnight.model.game.elements.Particle;

import HollowKnight.model.Position;
import HollowKnight.model.game.scene.Scene;
import com.googlecode.lanterna.TextColor;

import java.awt.*;

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

        // Apply gravity to y-velocity
        Position velocity = new Position(getVelocity().x(), getVelocity().y() + 1);

        // Reduce opacity to fade out
        this.setOpacity(Math.max(0, getOpacity() - getFadeRate() * getVelocity().y()));


        return new Position(position.x() + velocity.x(),
                               position.y() + velocity.y());
    }
}
