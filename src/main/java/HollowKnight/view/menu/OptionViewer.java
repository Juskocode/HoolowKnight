package HollowKnight.view.menu;

import HollowKnight.gui.GUI;
import HollowKnight.model.menu.Option;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;

public class OptionViewer {
    public void draw(Option model, GUI gui, TextColor.RGB color) throws IOException {
        for (int oidx = 0; oidx < 8; oidx++){
            for (int idx = 0; idx < 6; idx++) {
                gui.drawPixel((int)model.getPosition().x() + idx + oidx*8, (int)model.getPosition().y(), color);
                gui.drawPixel((int)model.getPosition().x() + idx + oidx*8, (int)model.getPosition().y() + 1, color);
                gui.drawPixel((int)model.getPosition().x() + idx + oidx*8, (int)model.getPosition().y() + 2, color);
                gui.drawPixel((int)model.getPosition().x() + idx + oidx*8, (int)model.getPosition().y() + 3, color);
                gui.drawPixel((int)model.getPosition().x() + idx + oidx*8, (int)model.getPosition().y() + 4, color);
                gui.drawPixel((int)model.getPosition().x() + idx + oidx*8, (int)model.getPosition().y() + 5, color);
                gui.drawPixel((int)model.getPosition().x() + idx + oidx*8, (int)model.getPosition().y() + 6, color);
                gui.drawPixel((int)model.getPosition().x() + idx + oidx*8, (int)model.getPosition().y() + 7, color);
            }
        }
    }
}
