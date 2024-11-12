package HollowKnight.model.menu;

import HollowKnight.model.game.elements.Particle.Particle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Menu {
    private final List<Option> options;
    private int currentOption = 0;
    public Menu() {
        Option start = new Option(30, 15, "Start");
        Option settings = new Option(30, 20, "Settings");
        Option scoreboard = new Option(30, 25, "ScoreBoard");
        Option exit = new Option(30, 30, "Exit");
        this.options = Arrays.asList(start, settings, scoreboard, exit);

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


}
