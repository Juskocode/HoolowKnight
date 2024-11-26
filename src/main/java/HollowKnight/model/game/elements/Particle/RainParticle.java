package HollowKnight.model.game.elements.Particle;

import HollowKnight.model.Position;
import HollowKnight.model.Vector;
import HollowKnight.model.game.scene.Scene;
import com.googlecode.lanterna.TextColor;

import static java.lang.Math.max;

public class RainParticle extends Particle {

    public RainParticle(int x, int y, Position velocity, TextColor.RGB color) {
        super(x, y, velocity, color);
    }

    @Override
    protected Vector applyCollisions(Vector velocity)
    {
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
        Position currentPos = getPosition();
        if (currentPos == null) {
            throw new IllegalStateException("Particle position is null");
        }

        // Handle wind simulation
        int windEffect = (int) (Math.random() * 3) - 1; // Wind effect range: -1 to 1
        int new_x = (int) getPosition().x() + 2 + windEffect;  // Adds wind effect to horizontal movement
        int new_y = (int) getPosition().y() + 2;  // Vertical movement remains constant

        // Handle boundary conditions for x and y
        if (new_x < 0) {
            new_x = scene.getWidth() - 1;
        } else if (new_x >= scene.getWidth()) {
            new_x = 1;
        }

        if (new_y >= scene.getHeight()) {
            new_y = 0;  // Particle reaches bottom and is "destroyed" (restarts from top)
            setLifetime(getLifetime() + 1);  // Increment the lifetime

            // If the particle has lived for 900 ticks, stop regenerating
            if (getLifetime() >= 900) {
                if (getLastRegenerationTime() == 0 || (time - getLastRegenerationTime()) >= getRandomRegenerationDelay()) {
                    // Particle starts regenerating again after a random range of ticks
                    setLifetime(0); // Reset lifetime
                    setLastRegenerationTime(time); // Reset last regeneration time
                }
            }
        }
        return new Position(new_x, new_y);
    }
}