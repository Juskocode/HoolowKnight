package HollowKnight.view.text;

import HollowKnight.gui.GUI;
import com.googlecode.lanterna.TextColor;

public interface TextViewer {
    void draw(char character, int x, int y, TextColor.RGB foregroundColor, GUI gui);
    void draw(String string, int x, int y, TextColor.RGB foregroundColor, GUI gui);
}
