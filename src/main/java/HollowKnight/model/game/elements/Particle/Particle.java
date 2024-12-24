package HollowKnight.model.game.elements.Particle;

import HollowKnight.model.dataStructs.Position;
import HollowKnight.model.dataStructs.Vector;
import HollowKnight.model.game.elements.Element;
import HollowKnight.model.game.scene.Scene;
import com.googlecode.lanterna.TextColor;

import static java.lang.Math.max;

public abstract class Particle extends Element {

    private Scene scene;
    // Random delay time for regenerating the particle (in ticks)
    private static final int MIN_REGEN_DELAY = 200;
    private static final int MAX_REGEN_DELAY = 600;
    private int lifetime; // Tracks the number of ticks this particle has been alive
    private long lastRegenerationTime; // Tracks when the particle last stopped regenerating

    private TextColor.RGB color;
    private Position velocity; // Includes x and y velocities
    private double opacity;    // Controls particle transparency
    private final double fadeRate = 0.00675; // Rate at which particles fade

    // Constructor and other methods...

    public Particle(int x, int y, Position velocity, TextColor.RGB color){
        super(x, y);
        this.color = color;
        this.opacity = 1.0;
        this.velocity = velocity;
    }

    public double getOpacity() {
        return opacity;
    }

    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    public double getFadeRate() {
        return fadeRate;
    }

    public Position getVelocity() {
        return velocity;
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setVelocity(Position velocity) {
        this.velocity = velocity;
    }

    public void setLastRegenerationTime(long lastRegenerationTime) {
        this.lastRegenerationTime = lastRegenerationTime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }
    public long getLastRegenerationTime() {
        return lastRegenerationTime;
    }

    public TextColor.RGB getColor() {
        return color;
    }

    public void setColor(TextColor.RGB color) {
        this.color = color;
    }

    // Random regeneration delay between MIN_REGEN_DELAY and MAX_REGEN_DELAY ticks
    public long getRandomRegenerationDelay() {
        return (long) MIN_REGEN_DELAY + (int)(Math.random() * (MAX_REGEN_DELAY - MIN_REGEN_DELAY));
    }

    protected abstract Vector applyCollisions(Vector velocity);

    public abstract Position moveParticle(Scene scene, long time);

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }


}
