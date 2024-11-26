package HollowKnight.view.menu;

import HollowKnight.gui.GUI;
import HollowKnight.model.menu.Option;
import HollowKnight.view.text.GameTextViewer;
import HollowKnight.view.text.TextViewer;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;

public class OptionViewer {
    private final TextViewer textViewer;

    public OptionViewer() throws IOException{
        this.textViewer = new GameTextViewer();
    }
    public void draw(Option model, GUI gui, TextColor.RGB color) throws IOException {
        textViewer.draw(model.getText(), (int)model.getPosition().x(), (int)model.getPosition().y(), color, gui);

    }
}
