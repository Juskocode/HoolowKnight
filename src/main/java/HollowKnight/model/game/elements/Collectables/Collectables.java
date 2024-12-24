package HollowKnight.model.game.elements.Collectables;

import HollowKnight.model.game.elements.Element;
import HollowKnight.model.game.elements.Knight.Knight;

public abstract class Collectables extends Element {

    public Collectables(int x, int y){
        super(x, y);
    }
    public abstract void benefit(Knight knight);

    public abstract char getChar();
}
