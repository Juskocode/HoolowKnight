package HollowKnight.controller.menu;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.model.Position;
import HollowKnight.model.game.scene.Scene;
import HollowKnight.model.menu.Menu;
import HollowKnight.model.menu.Particle;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;
import java.util.Random;

public class ParticleMenuController extends Controller<Menu> {

    private double windAngle = 0; // Current wind direction in radians
    private double windSpeed = 2; // Current wind speed
    private int screenWidth = 159;
    private int screenHeight = 89;
    private Random random = new Random();

    // Colors for the gradient
    private TextColor.RGB currentStartColor = randomColor();
    private TextColor.RGB currentEndColor = randomColor();
    private TextColor.RGB nextStartColor = randomColor();
    private TextColor.RGB nextEndColor = randomColor();
    private int gradientChangeInterval = 500; // Change gradient every 500 ticks
    private int gradientTransitionFrames = 100; // Number of frames for smooth transition
    private int transitionStartTick = -1; // Tick when the transition begins

    public ParticleMenuController(Menu model) {
        super(model);
    }

    @Override
    public void move(Game game, GUI.ACTION action, long time) throws IOException {
        for (Particle particle : getModel().getParticles())
            particle.setPosition(ParticleMove(particle, time));
    }

    public Position ParticleMove(Particle particle, long tick) {
        int modeDuration = 800; // Number of ticks before switching mode
        int mode = (int) ((tick / modeDuration) % 4); // Cycle through 4 modes (0: random, 1: windy, 2: calm, 3: dispersing)

        double currentWindAngle = windAngle; // Default wind angle for calculations
        double currentWindSpeed = windSpeed;

        // Trigger gradient change periodically
        if (tick % gradientChangeInterval == 0) {
            transitionStartTick = (int) tick; // Start the gradient transition
            nextStartColor = randomColor();
            nextEndColor = randomColor();
        }

        // Smoothly interpolate the gradient during the transition period
        TextColor.RGB startColor = currentStartColor;
        TextColor.RGB endColor = currentEndColor;
        if (transitionStartTick >= 0 && tick < transitionStartTick + gradientTransitionFrames) {
            float transitionFactor = (float) (tick - transitionStartTick) / gradientTransitionFrames;
            startColor = interpolateColor(currentStartColor, nextStartColor, transitionFactor);
            endColor = interpolateColor(currentEndColor, nextEndColor, transitionFactor);
        } else if (transitionStartTick >= 0 && tick >= transitionStartTick + gradientTransitionFrames) {
            // Finalize the transition
            currentStartColor = nextStartColor;
            currentEndColor = nextEndColor;
            transitionStartTick = -1; // Reset transition
        }

        // Update wind properties occasionally during windy or dispersing modes
        if (tick % 100 == 0 && (mode == 1 || mode == 3)) {
            windAngle = random.nextDouble() * Math.PI * 2; // Random angle (0 to 2π)
            windSpeed = 1 + random.nextDouble() * 3; // Random speed (1 to 4)
        }

        int new_x = (int) particle.getPosition().x();
        int new_y = (int) particle.getPosition().y();

        switch (mode) {
            case 0: // Random movement (like rain)
                new_x += random.nextInt(3) - 1; // Move -1, 0, or +1 randomly
                new_y += random.nextInt(3) + 1; // Mostly downward
                break;

            case 1: // Windy movement
                currentWindAngle = windAngle; // Use updated wind angle
                currentWindSpeed = windSpeed;
                new_x += currentWindSpeed * Math.cos(currentWindAngle);
                new_y += currentWindSpeed * Math.sin(currentWindAngle);
                break;

            case 2: // Calm state
                // Minimal movement to simulate a pause
                new_x += random.nextInt(2) - 1; // Very slight random horizontal movement
                new_y += 1; // Slowly drifting downward
                break;

            case 3: // Dispersing state
                // Stronger random movement with directional drift
                currentWindAngle = windAngle; // Use updated wind angle
                currentWindSpeed = windSpeed * 1.5; // Increased wind strength for dispersal
                new_x += (int) (currentWindSpeed * Math.cos(currentWindAngle)) + random.nextInt(3) - 1;
                new_y += (int) (currentWindSpeed * Math.sin(currentWindAngle)) + random.nextInt(3) - 1;
                break;
        }

        // Wrap around logic for X and Y positions
        if (new_x < 0) new_x = screenWidth - 1;
        else if (new_x >= screenWidth) new_x = 1;
        if (new_y < 0) new_y = screenHeight - 1;
        else if (new_y >= screenHeight) new_y = 1;

        // Interpolate the gradient based on the Y position
        TextColor.RGB interpolatedColor = interpolateColor(startColor, endColor, new_y / (float) screenHeight);
        particle.setColor(interpolatedColor);

        return new Position(new_x, new_y);
    }

    // Interpolates between two colors based on a factor (0.0 to 1.0)
    private TextColor.RGB interpolateColor(TextColor.RGB start, TextColor.RGB end, float factor) {
        int r = (int) (start.getRed() + factor * (end.getRed() - start.getRed()));
        int g = (int) (start.getGreen() + factor * (end.getGreen() - start.getGreen()));
        int b = (int) (start.getBlue() + factor * (end.getBlue() - start.getBlue()));
        return new TextColor.RGB(r, g, b);
    }

    // Generates a random RGB color
    private TextColor.RGB randomColor() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new TextColor.RGB(r, g, b);
    }
}
