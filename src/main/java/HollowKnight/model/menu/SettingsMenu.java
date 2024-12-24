package HollowKnight.model.menu;

import HollowKnight.model.dataStructs.Position;
import HollowKnight.state.particle.RandomState;
import com.googlecode.lanterna.TextColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SettingsMenu extends Menu{
    private final int size = 100;
    @Override
    protected List<Option> createEntries() {
        Option resolution = new Option(30, 30, Option.Type.RESOLUTION);
        Option exit = new Option(30, 40, Option.Type.TO_MAIN_MENU);
        return List.of(resolution, exit);
    }

    @Override
    public List<Particle> createParticles() {
        List<Particle> particles = new ArrayList<>();
        Random random = new Random();
        int width = 160; // Assuming screen width is 160
        int height = 90; // Assuming screen height is 90

        for (int i = 0; i < size; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);

            // Calculate green intensity based on the x position, limiting the maximum value for calmer colors
            int greenIntensity = (int) ((x / (float) width) * 150); // Softer green intensity [0, 150]

            // Introduce a subtle blue tint for calmness
            int blueIntensity = 100 + (int) ((y / (float) height) * 50); // Shades of blue [100, 150]

            // Create particle with a subdued green-blue tone
            Particle new_particle = new Particle(
                    new Position(x, y),
                    new RandomState(),
                    new TextColor.RGB(0, greenIntensity, blueIntensity) // Muted green-blue tones
            );

            particles.add(new_particle);
        }
        return particles;
    }
}
