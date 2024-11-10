package HollowKnight.model.game.elements.map;

import HollowKnight.model.Position;
import HollowKnight.model.game.elements.knight;
import HollowKnight.model.game.elements.tile.Tile;

import java.util.List;

public class Scene {
    private final int width;
    private final int height;

    private Knight player;

    private List<Tile> tiles;

    public Scene(int width, int height) {
        this.width = width;
        this.height = height;
        this.player = new Knight(width / 2, height / 2, 100, 10, 5);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Knight getPlayer() {
        return player;
    }

    public void setPlayer(Knight player) {
        this.player = player;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public boolean isEmpty(Position position) {
        for (Tile tile : tiles)
            if (tile.getPosition().equals(position))
                return false;
        return true;
    }
    public boolean isTile(Position position) {
        for (Tile tile : tiles)
            if (tile.getPosition().equals(position))
                return true;
        return false;
    }
}