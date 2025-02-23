package HollowKnight.model.game.elements.tile;

import HollowKnight.model.game.elements.Element;

public class Tile extends Element {
    public static final int SIZE = 8;
    private final char character;

    public Tile(int x, int y, char character) {
        super(x, y);
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}
