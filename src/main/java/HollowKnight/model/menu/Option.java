package HollowKnight.model.menu;

import HollowKnight.model.Position;

public class Option {
    private Position position;
    private String text;

    public Option(int x, int y, String text) {
        this.position = new Position(x, y);
        this.text = text;
    }

    public Position getPosition() {
        return position;
    }

    public String getText() {
        return text;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setText(String text) {
        this.text = text;
    }
}
