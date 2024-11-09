package HollowKnight.model.game.elements.Collectables;

import HollowKnight.model.game.elements.Element;
import HollowKnight.model.game.elements.Knight.Knight;

public abstract class Collectables extends Element {
    private String Type;

    public Collectables(int x, int y, String Type){
        super(x, y);
        this.Type = Type;
    }

    public String getType() {
        return Type;
    }
    public abstract void benefit(Knight knight);
}
