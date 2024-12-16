package HollowKnight.model.game.elements.enemies;

import HollowKnight.model.Position;
import HollowKnight.model.Vector;
import HollowKnight.model.game.scene.Scene;

import static java.lang.Math.max;

import java.util.Random;

import java.util.Random;

public class MinhoteMonster extends Enemies {

    private final double amplitude;      // Controls the height of the wave
    private final double frequency;      // Controls how fast the wave oscillates
    private final double horizontalSpeed; // Fixed horizontal speed
    private double elapsedTime;          // Time tracker for the wave movement
    private final int screenWidth;       // Width of the game screen
    private final int screenHeight;      // Height of the game screen

    public MinhoteMonster(int x, int y, int HP, Scene scene, int damage) {
        super(x, y, HP, scene, damage);
        Random random = new Random();

        // Randomize amplitude (height of sine wave) and frequency
        this.amplitude = 1.5 + random.nextDouble(); // 1.5 to 2.5
        this.frequency = 0.05 + (random.nextDouble() * 0.05); // 0.05 to 0.1 (wave speed)
        this.horizontalSpeed = 1.25 + (0.75 * random.nextDouble()); // Speed: 1.25 to 2
        this.elapsedTime = 0.0;

        // Set screen boundaries (replace with your scene dimensions)
        this.screenWidth = scene.getWidth();
        this.screenHeight = scene.getHeight();

        // Set initial velocity
        setVelocity(new Vector(horizontalSpeed, 0));
    }

    @Override
    public Position updatePosition() {
        // Increment time for sine wave calculations
        elapsedTime += 1;

        // Horizontal movement
        double newX = getPosition().x() + horizontalSpeed;

        // Vertical movement: sine wave function
        double newY = getPosition().y() + amplitude * Math.sin(frequency * elapsedTime);

        // Screen wrapping: If the enemy moves off the screen, wrap around
        if (newX > screenWidth) newX = 0; // Wrap from right to left
        if (newX < 0) newX = screenWidth; // Wrap from left to right
        if (newY > screenHeight) newY = 0; // Wrap from bottom to top
        if (newY < 0) newY = screenHeight; // Wrap from top to bottom

        return new Position(newX, newY);
    }

    @Override
    public Position moveMonster() {
        Position newPosition = updatePosition();
        setPosition(newPosition);
        return newPosition;
    }

    @Override
    protected Vector applyCollisions(Vector velocity) {
        // Disable collision logic for wave-like movement
        return velocity;
    }
}
