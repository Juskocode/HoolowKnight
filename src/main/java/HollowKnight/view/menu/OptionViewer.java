package HollowKnight.view.menu;

import HollowKnight.gui.GUI;
import HollowKnight.gui.RescalableGUI;
import HollowKnight.model.menu.Option;
import HollowKnight.view.text.GameTextViewer;
import HollowKnight.view.text.TextViewer;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;

public class OptionViewer {
    private final TextViewer textViewer;

    public OptionViewer(TextViewer textViewer) {
        this.textViewer = textViewer;
    }

    public void draw(Option model, RescalableGUI gui, TextColor color) {
        String OptionText = switch (model.getType()) {
            case START_GAME -> "Start";
            case SETTINGS -> "Settings";
            case EXIT -> "Exit";
            case RESOLUTION -> getResolutionLabel(gui);
            case TO_MAIN_MENU -> "Go Back";
        };
        textViewer.draw(OptionText, (int) model.getPosition().x(), (int) model.getPosition().y(), (TextColor.RGB) color, gui);
    }

    private String getResolutionLabel(RescalableGUI gui) {
        final RescalableGUI.ResolutionScale[] resolutions = RescalableGUI.ResolutionScale.values();
        if (gui.getResolutionScale() == null)
            return "Resolution:   Automatic >";

        return String.format(
                "Resolution: < %dX%d %c ",
                gui.getResolutionScale().getWidth(),
                gui.getResolutionScale().getHeight(),
                resolutions[resolutions.length - 1] == gui.getResolutionScale() ? ' ' : '>'
        );
    }
}