package HollowKnight.model.menu;

import HollowKnight.model.Position;
import HollowKnight.state.particle.RandomState;
import com.googlecode.lanterna.TextColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainMenu extends Menu {
    private final int size = 250;
    @Override
    protected List<Option> createEntries() {
        Option start = new Option(30, 25, Option.Type.START_GAME);
        Option settings = new Option(30, 31, Option.Type.SETTINGS);
        Option exit = new Option(30, 37, Option.Type.EXIT);
        return Arrays.asList(start, settings, exit);
    }

    @Override
    public List<Particle> createParticles() {
        List<Particle> particles = new ArrayList<>();
        Random random = new Random();
        int width = 160; // Assuming screen width is 160

        for (int i = 0; i < size; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(90); // Assuming screen height is 90

            // Calculate green intensity based on the x position
            int greenIntensity = (int) ((x / (float) width) * 255); // Map x to range [0, 255]

            Particle new_particle = new Particle(
                    new Position(x, y),
                    new RandomState(),
                    new TextColor.RGB(0, greenIntensity, 0) // Shades of green
            );

            particles.add(new_particle);
        }
        return particles;
    }
}
