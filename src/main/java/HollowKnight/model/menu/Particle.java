package HollowKnight.model.menu;

import HollowKnight.model.game.elements.Element;
import com.googlecode.lanterna.TextColor;

public class Particle extends Element {
    private TextColor.RGB color;
    public Particle(int x, int y, TextColor.RGB color){
        super(x, y);
        this.color = color;
    }

    public TextColor.RGB getColor() {
        return color;
    }

    public void setColor(TextColor.RGB color) {
        this.color = color;
    }
}
