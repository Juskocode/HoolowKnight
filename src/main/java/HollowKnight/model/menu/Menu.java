package HollowKnight.model.menu;

import com.googlecode.lanterna.TextColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Menu {
    private final List<Option> options;
    private List<Particle> particles;
    private Boolean inGame = false;
    private int currentOption = 0;
    public Menu() {
        Option start = new Option(30, 15, "Start");
        Option settings = new Option(30, 21, "Settings");
        Option scoreboard = new Option(30, 27, "ScoreBoard");
        Option exit = new Option(30, 33, "Exit");
        this.options = Arrays.asList(start, settings, scoreboard, exit);
        this.particles = new ArrayList<>();
        setParticles(createParticles(200));

    }

    public List<Option> getOptions() {
        return options;
    }

    public int getNumberOptions() {
        return this.options.size();
    }
    public void nextOption() {
        if (++currentOption >= getNumberOptions())
            currentOption = 0;
    }
    public void previousOption() {
        if (--currentOption < 0)
            currentOption = getNumberOptions() - 1;
    }
    public Option getOption(int i) {
        return options.get(i);
    }
    public boolean isSelected(int i) {
        return currentOption == i;
    }
    public boolean isSelectedExit() {
        return isSelected(getNumberOptions() - 1);
    }
    public boolean isSelectedStart() {
        return isSelected(0);
    }

    public List<Particle> getParticles() {
        return particles;
    }

    public void setParticles(List<Particle> particles) {
        this.particles = particles;
    }

    public List<Particle> createParticles(int size) {
        List<Particle> particles = new ArrayList<>();
        Random random = new Random();
        int width = 160; // Assuming screen width is 160

        for (int i = 0; i < size; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(90); // Assuming screen height is 90

            // Calculate green intensity based on the x position
            int greenIntensity = (int) ((x / (float) width) * 255); // Map x to range [0, 255]

            Particle new_particle = new Particle(
                    x,
                    y,
                    new TextColor.RGB(0, greenIntensity, 0) // Shades of green
            );

            particles.add(new_particle);
        }
        return particles;
    }

    public Boolean getInGame() {
        return inGame;
    }

    public void setInGame(Boolean inGame) {
        this.inGame = inGame;
    }
}
