package HollowKnight.model.menu;

import HollowKnight.model.Position;

public class Option {
    private Position position;
    public enum Type { START_GAME, SETTINGS, EXIT, RESOLUTION, TO_MAIN_MENU };

    private final Type type;


    public Option(int x, int y, Type type) {
        this.position = new Position(x, y);
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public Type getType() {
        return type;
    }

}
