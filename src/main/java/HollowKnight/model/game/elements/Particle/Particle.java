package HollowKnight.model.game.elements.Particle;

import HollowKnight.model.Position;
import HollowKnight.model.game.elements.Element;
import HollowKnight.model.menu.Menu;
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
