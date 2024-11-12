package HollowKnight.view.states;

import HollowKnight.gui.GUI;
import HollowKnight.model.menu.Menu;
import HollowKnight.model.menu.Option;
import HollowKnight.view.menu.OptionViewer;
import com.googlecode.lanterna.TextColor;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

public class MenuViewer extends ScreenViewer<Menu> {
    public MenuViewer(Menu model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.cls();
        this.drawOptions(gui, getModel().getOptions(), new OptionViewer());
        gui.flush();
    }

    private void drawOptions(GUI gui, List<Option> options, OptionViewer viewer) throws IOException {
        TextColor.RGB deselected = new TextColor.RGB(255, 255, 255); // Deselected color (white)
        int boxWidth = 10;  // Width of the selection box (fat line)
        int boxHeight = 2; // Height of the selection box (thickness)
        boolean select= false;
        // Iterate through the options
        for (int idx = 0; idx < options.size(); idx++) {
            // Calculate gradient color based on the index
            TextColor.RGB selected = calculateGradient(idx, options.size());

            // Draw a thicker line for the selected option with gradient
            for (int y = 0; y < boxHeight; y++) {
                for (int x = 0; x < boxWidth; x++) {
                    if (getModel().isSelected(idx)) {
                        gui.drawPixel(35 + x, 15 + (5 * idx) + y, selected); // Draw selected with gradient
                        select = true;
                    } else {
                        gui.drawPixel(35 + x, 15 + (5 * idx) + y, deselected); // Draw deselected
                        select = false;
                    }
                }
            }
            if(select)
                gui.drawText(35,15+(idx*5)+1, selected, options.get(idx).getText());
            else{
                gui.drawText(35,15+(idx*5)+1, new TextColor.RGB(255, 255,255),options.get(idx).getText());
            }
        }
    }
    // Gradient calculation for the selected option (from green to red)
    private TextColor.RGB calculateGradient(int idx, int totalOptions) {
        // Linear gradient from green (255, 255, 0) to red (255, 0, 0)
        int r = (int)(255 * (idx / (float) totalOptions)); // Red increases as idx increases
        int g = (int)(255 * (1 - (idx / (float) totalOptions))); // Green decreases as idx increases
        return new TextColor.RGB(r, g, 0); // Green to red gradient
    }
}