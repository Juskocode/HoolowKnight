package HollowKnight.model.game.elements.Particle;

import HollowKnight.model.dataStructs.Position;
import HollowKnight.model.dataStructs.Vector;
import HollowKnight.model.game.scene.Scene;
import com.googlecode.lanterna.TextColor;

import static java.lang.Math.max;

public class RespawnParticle extends Particle {

    private double stickTimer = 0.1; // Time remaining to stick to the ceiling


    public RespawnParticle(int x, int y, Position velocity, TextColor.RGB color) {
        super(x, y, velocity, color);
    }

    @Override
    protected Vector applyCollisions(Vector velocity) {
        double x = getPosition().x();
        double y = getPosition().y();
        double vx = velocity.x();
        double vy = velocity.y();
        Position size = new Position(1, 1);

        // Ceiling collision
        if (vy < 0 && getScene().collidesUp(new Position(x, y + vy), size)) {
            vy = -vy; // Stop upward movement
            stickTimer = 0.1; // Set stick time (adjust as needed)
        }

        // Downward collision (falling)
        if (vy > 0) {
            while (vy > 0 && getScene().collidesDown(new Position(x, y + vy), size)) {
                vy -= 0.1; // Reduce incrementally for precise alignment
            }
            if (vy < 0.1) {
                vy = 0; // Stop falling if close enough
            }
        }

        // Horizontal collisions (optional, depending on requirements)
        if (vx < 0 && getScene().collidesLeft(new Position(x + vx, y), size)) {
            vx = 0;
        } else if (vx > 0 && getScene().collidesRight(new Position(x + vx, y), size)) {
            vx = 0;
        }

        return new Vector(vx, vy);
    }

    @Override
    public Position moveParticle(Scene scene, long time) {
        // Current position and velocity
        double x = getPosition().x();
        double y = getPosition().y();
        double vx = getVelocity().x();
        double vy = getVelocity().y();

        // Apply gravity only if not sticking to the ceiling
        if (stickTimer > 0) {
            stickTimer -= 0.1; // Decrement stick timer (adjust rate as needed)
            vy = 0; // No vertical movement while sticking
        } else {
            vy += 0.2; // Gravity pulls down
        }
        //System.out.println(stickTimer);
        // Apply collisions to adjust velocity
        Vector adjustedVelocity = applyCollisions(new Vector(vx, vy));
        vx = adjustedVelocity.x();
        vy = adjustedVelocity.y();

        // Update particle position with adjusted velocity
        x += vx;
        y += vy;

        // Update particle opacity (simulate fading)
        this.setOpacity(Math.max(0, getOpacity() - getFadeRate()));

        // Set new position and velocity
        this.setPosition(new Position(x, y));
        this.setVelocity(new Position(vx, vy));

        return new Position(x, y);
    }
}