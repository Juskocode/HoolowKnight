package HollowKnight.model.menu;

import com.googlecode.lanterna.TextColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Menu {
    private final List<Option> options;
    private List<Particle> particles;
    private int currentOption = 0;
    public Menu() {
        Option start = new Option(30, 15, "Start");
        Option settings = new Option(30, 20, "Settings");
        Option scoreboard = new Option(30, 25, "ScoreBoard");
        Option exit = new Option(30, 30, "Exit");
        this.options = Arrays.asList(start, settings, scoreboard, exit);
        this.particles = new ArrayList<>();
        setParticles(createParticles(60));

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

    public List<Particle> createParticles(int size){
        List<Particle> particles = new ArrayList<>();
        Random random = new Random();
        for(int i =0; i < size; i++){
                    Particle new_particle = new Particle(
                    random.nextInt(160),
                    random.nextInt(90),
                    new TextColor.RGB(0, 0, random.nextInt(100, 255))
            );
            particles.add(new_particle);
        }
        return particles;
    }
}
