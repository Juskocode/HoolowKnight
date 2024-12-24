package HollowKnight.model.menu;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {
    private final List<Option> options;
    private List<Particle> particles;
    private Boolean inGame = false;
    private int currentOption = 0;

    public Menu() {
        this.options = createEntries();
        this.particles = new ArrayList<>();
        setParticles(createParticles());
    }

    protected abstract List<Option> createEntries();


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
    public Option getCurrentOption() {
        return options.get(currentOption);
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

    public abstract List<Particle> createParticles();

    public Boolean getInGame() {
        return inGame;
    }

    public void setInGame(Boolean inGame) {
        this.inGame = inGame;
    }
}
