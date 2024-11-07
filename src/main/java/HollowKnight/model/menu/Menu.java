package HollowKnight.model.menu;

import java.util.Arrays;
import java.util.List;
public class Menu {
    private final List<String> options;
    private int currentOption = 0;
    public Menu() {
        this.options = Arrays.asList("Start", "Settings", "ScoreBoard", "Exit");
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
    public String getOption(int i) {
        return options.get(i);
    }
    public boolean isSelected(int i) {
        return currentOption == i;
    }
    public boolean isSelectedExit() {
        return isSelected(1);
    }
    public boolean isSelectedStart() {
        return isSelected(0);
    }
}
