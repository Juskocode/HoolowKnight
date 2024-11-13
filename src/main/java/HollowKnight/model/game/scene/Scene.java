package HollowKnight.model.game.scene;

import HollowKnight.model.Position;
import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.model.game.elements.Particle.Particle;
import HollowKnight.model.game.elements.Tree.Tree;
import HollowKnight.model.game.elements.rocks.BigRock;
import HollowKnight.model.game.elements.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private final int width;
    private final int height;
    private List<Particle> particles;
    private Knight player;

    private List<Tile> tiles;
    private List<Tree> trees;
    private List<BigRock> bigRocks;

    public Scene(int width, int height) {
        this.width = width;
        this.height = height;
        this.player = new Knight(width / 2, height / 2, 100, 10, 5);
        this.particles = new ArrayList<>();
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

    public List<Tree> getTrees() {
        return trees;
    }

    public void setTrees(List<Tree> trees) {
        this.trees = trees;
    }

    public List<BigRock> getBigRocks() {
        return bigRocks;
    }

    public void setBigRocks(List<BigRock> bigRocks) {
        this.bigRocks = bigRocks;
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
    public void setParticles(List<Particle> particles) {
        this.particles = particles;
    }

    public List<Particle> getParticles() {
        return particles;
    }
}