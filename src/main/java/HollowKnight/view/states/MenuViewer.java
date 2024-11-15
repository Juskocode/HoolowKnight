package HollowKnight.view.states;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Element;
import HollowKnight.model.menu.Menu;
import HollowKnight.model.menu.Option;
import HollowKnight.view.elements.ElementViewer;
import HollowKnight.view.menu.OptionViewer;
import HollowKnight.view.menu.ParticleViewer;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;
import java.util.List;

public class MenuViewer extends ScreenViewer<Menu> {
    private final ParticleViewer particleViewer;
    private static final TextColor.RGB unselectedColor = new TextColor.RGB(234,234,234);
    private static final TextColor.RGB selectedColor = new TextColor.RGB(255,234,69);
    private final OptionViewer optionViewer;
    public MenuViewer(Menu model) throws IOException {
        super(model);
        this.optionViewer = new OptionViewer();
        this.particleViewer = new ParticleViewer();
    }

    @Override
    public void draw(GUI gui, long time) throws IOException {
        gui.cls();
        drawBackGround(gui);
        this.drawOptions(gui, getModel().getOptions(), optionViewer);
        drawElements(gui, getModel().getParticles(), this.particleViewer, time);
        gui.flush();
    }

    private void drawOptions(GUI gui, List<Option> options, OptionViewer viewer) throws IOException {
        for (int idx = 0; idx < options.size(); idx++){
            viewer.draw(options.get(idx), gui, getModel().isSelected(idx) ? selectedColor : unselectedColor);
        }
    }

    // Gradient calculation for the selected option (from green to red)
    private TextColor.RGB calculateGradient(int idx, int totalOptions) {
        int r = (int) (255 * (idx / (float) totalOptions));
        int g = (int) (255 * (1 - (idx / (float) totalOptions)));
        return new TextColor.RGB(r, g, 0);
    }

    private void drawBackGround(GUI gui) throws IOException {
        int screenWidth = 160;
        int screenHeight = 90;

        // Background gradient color
        for (int w = 0; w < screenWidth; w++) {
            int greenValue = 255 - (int) (255 * w / (screenWidth - 1.0));
            int blueValue = (int) (255 * w / (screenWidth - 1.0));
            TextColor.RGB color = new TextColor.RGB(0, greenValue, blueValue);

            for (int h = 0; h < screenHeight; h++) {
                gui.drawPixel(w, h, color);
            }
        }

        // Border color
        TextColor.RGB borderColor = new TextColor.RGB(25, 25, 25);
        for (int w = 0; w < screenWidth; w++) {
            gui.drawPixel(w, 0, borderColor);
            gui.drawPixel(w, screenHeight - 1, borderColor);
        }
        for (int h = 1; h < screenHeight - 1; h++) {
            gui.drawPixel(0, h, borderColor);
            gui.drawPixel(screenWidth - 1, h, borderColor);
        }
    }
    private  <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer, long time) throws IOException {
        for (T element : elements)
            drawElement(gui, element, viewer, time);
    }

    private  <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer, long time) throws IOException {
        viewer.draw(element, gui, time);
    }
}