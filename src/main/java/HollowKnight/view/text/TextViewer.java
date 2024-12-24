package HollowKnight.view.text;

import HollowKnight.gui.GUI;
import com.googlecode.lanterna.TextColor;

public interface TextViewer {
    void draw(char character, double x, double y, TextColor foregroundColor, GUI gui);
    void draw(String string, double x, double y, TextColor foregroundColor, GUI gui);
}
