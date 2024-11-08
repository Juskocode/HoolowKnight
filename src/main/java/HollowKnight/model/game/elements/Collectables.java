package HollowKnight.model.game.elements;

public abstract class Collectables extends Element{
    private String Type;

    public Collectables(int x, int y, String Type){
        super(x, y);
        this.Type = Type;
    }

    public String getType() {
        return Type;
    }

}
