package HollowKnight.model.game.scene;

import HollowKnight.model.Position;
import HollowKnight.model.game.elements.Element;
import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.model.game.elements.Particle.Particle;
import HollowKnight.model.game.elements.Tree.MediumTree;
import HollowKnight.model.game.elements.Tree.SmallTree;
import HollowKnight.model.game.elements.enemies.MinhoteMonster;
import HollowKnight.model.game.elements.enemies.PurpleMonster;
import HollowKnight.model.game.elements.enemies.SwordMonster;
import HollowKnight.model.game.elements.rocks.BigRock;
import HollowKnight.model.game.elements.rocks.SmallRock;
import HollowKnight.model.game.elements.tile.Tile;
import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private final int width;
    private final int height;

    private Tile[][] tiles;

    private SmallTree[][] smallTrees;
    private MediumTree[][] mediumTrees;

    private BigRock[][] bigRocks;
    private SmallRock[][] smallRocks;

    private SwordMonster[][] swordMonsters;
    private PurpleMonster[][] purpleMonsters;
    private MinhoteMonster[][] minhoteMonsters;

    private double gravity = 0.2;

    private Knight player;
    private List<Particle> particles;

    public Scene(int width, int height) {
        this.width = width;
        this.height = height;

        this.particles = new ArrayList<>();

        this.tiles = new Tile[height][width];
        this.smallTrees = new SmallTree[height][width];
        this.mediumTrees = new MediumTree[height][width];
        this.bigRocks = new BigRock[height][width];
        this.smallRocks = new SmallRock[height][width];
        this.swordMonsters = new SwordMonster[height][width];
        this.purpleMonsters = new PurpleMonster[height][width];
        this.minhoteMonsters = new MinhoteMonster[height][width];
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

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public SmallTree[][] getSmallTrees() {
        return smallTrees;
    }

    public void setSmallTrees(SmallTree[][] smallTrees) {
        this.smallTrees = smallTrees;
    }

    public MediumTree[][] getMediumTrees() {
        return mediumTrees;
    }

    public void setMediumTrees(MediumTree[][] mediumTrees) {
        this.mediumTrees = mediumTrees;
    }

    public BigRock[][] getBigRocks() {
        return bigRocks;
    }

    public void setBigRocks(BigRock[][] bigRocks) {
        this.bigRocks = bigRocks;
    }

    public SmallRock[][] getSmallRocks() {
        return smallRocks;
    }

    public void setSmallRocks(SmallRock[][] smallRocks) {
        this.smallRocks = smallRocks;
    }

    public SwordMonster[][] getSwordMonsters() {
        return swordMonsters;
    }

    public void setSwordMonsters(SwordMonster[][] swordMonsters) {
        this.swordMonsters = swordMonsters;
    }

    public PurpleMonster[][] getPurpleMonsters() {
        return purpleMonsters;
    }

    public void setPurpleMonsters(PurpleMonster[][] purpleMonsters) {
        this.purpleMonsters = purpleMonsters;
    }

    public MinhoteMonster[][] getMinhoteMonsters() {
        return minhoteMonsters;
    }

    public void setMinhoteMonsters(MinhoteMonster[][] minhoteMonsters) {
        this.minhoteMonsters = minhoteMonsters;
    }

    public List<Particle> getParticles() {
        return particles;
    }

    public void setParticles(List<Particle> particles) {
        this.particles = particles;
    }

    public double getGravity() {
        return gravity;
    }

    private boolean isOutSideScene(double x1, double x2, double y1, double y2) {
        return x1 < 0 || x2 >= this.width || y1 < 0 || y2 >= this.height;
    }

    private boolean checkCollision(double x1, double x2, double y1, double y2, Element[][] layer) {
        if (isOutSideScene(x1, x2, y1, y2))
            return true;
        for (int tileY: List.of((int)y1 / Tile.SIZE, (int)y2 / Tile.SIZE)) {
            for (int tileX: List.of((int)x1 / Tile.SIZE, (int)x2 / Tile.SIZE)) {
                if (layer[tileY][tileX] != null)
                    return true;
            }
        }
        return false;
    }


    public boolean collidesLeft(Position position, Position size) {
        double x = position.x(), y = position.y();
        return checkCollision(x, x + 1, y, y + size.y() - 1, tiles);
    }

    public boolean collidesRight(Position position, Position size) {
        double x = position.x(), y = position.y();
        return checkCollision(x + size.x() - 1, x + size.x() - 1, y, y + size.y() - 1, tiles);
    }

    public boolean collidesUp(Position position, Position size) {
        double x = position.x(), y = position.y();
        return checkCollision(x, x + size.x() - 1, y, y + 1, tiles);
    }

    public boolean collidesDown(Position position, Position size) {
        double x = position.x(), y = position.y();

        // Check only the bottom edge of the player to detect ground collision
        return checkCollision(x, x + size.x() - 1, y + size.y(), y + size.y() + 1, tiles);
    }

}
