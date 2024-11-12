package HollowKnight.view.states;

import HollowKnight.gui.GUI;
import HollowKnight.model.menu.Menu;
import HollowKnight.model.menu.Option;
import HollowKnight.view.elements.ParticleViewer;
import HollowKnight.view.menu.OptionViewer;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;
import java.util.List;

public class MenuViewer extends ScreenViewer<Menu> {
    public MenuViewer(Menu model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.cls();
        drawBackGround(gui);
        this.drawOptions(gui, getModel().getOptions(), new OptionViewer());
        gui.flush();
    }

    private void drawOptions(GUI gui, List<Option> options, OptionViewer viewer) throws IOException {
        TextColor.RGB deselected = new TextColor.RGB(25, 25, 25); // Deselected color
        int boxWidth = 10;  // Width of the selection box
        int boxHeight = 2; // Height of the selection box
        boolean select = false;
        int screenWidth = 160;
        int screenHeight = 90;
        int startX = screenWidth / 2 - boxWidth / 2;
        int startY = screenHeight / 4;

        // Iterate through the options
        for (int idx = 0; idx < options.size(); idx++) {
            TextColor.RGB selected = calculateGradient(idx, options.size());

            // Draw a thicker line for the selected option with gradient
            for (int y = 0; y < boxHeight; y++) {
                for (int x = 0; x < boxWidth; x++) {
                    int posX = startX + x;
                    int posY = startY + (5 * idx) + y;
                    if (getModel().isSelected(idx)) {
                        gui.drawPixel(posX, posY, selected); // Draw selected with gradient
                        select = true;
                    } else {
                        gui.drawPixel(posX, posY, deselected); // Draw deselected
                        select = false;
                    }
                }
            }
            if (select)
                gui.drawText(startX, startY + (idx * 5) + 1, selected, options.get(idx).getText());
            else
                gui.drawText(startX, startY + (idx * 5) + 1, deselected, options.get(idx).getText());
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

}