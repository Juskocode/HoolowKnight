package HollowKnight.model.game.elements;

public class Tree extends Element {

    private final char symbol;
    public Tree(int x, int y, char symbol) {
        super(x, y);
        this.symbol = symbol;
    }

    public char getChar() {return symbol;}
}
