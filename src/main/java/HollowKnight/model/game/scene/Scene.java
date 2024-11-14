package HollowKnight.model.game.scene;

import HollowKnight.model.Position;
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
    private List<Particle> particles;
    private Knight player;

    private List<Tile> tiles;

    private List<SmallTree> smallTrees;
    private List<MediumTree> mediumTrees;

    private List<BigRock> bigRocks;
    private List<SmallRock> SmallRocks;

    private List<SwordMonster> swordMonsters;
    private List<PurpleMonster> purpleMonsters;
    private List<MinhoteMonster> minhoteMonsters;


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

    public List<SmallTree> getSmallTrees() {
        return smallTrees;
    }

    public void setSmallTrees(List<SmallTree> trees) {
        this.smallTrees = trees;
    }

    public List<MediumTree> getMediumTrees() {
        return mediumTrees;
    }
    public void setMediumTrees(List<MediumTree> trees) {
        this.mediumTrees = trees;
    }

    public List<BigRock> getBigRocks() {
        return bigRocks;
    }

    public void setBigRocks(List<BigRock> bigRocks) {
        this.bigRocks = bigRocks;
    }

    public List<SmallRock> getSmallRocks() {
        return SmallRocks;
    }
    public void setSmallRocks(List<SmallRock> smallRocks) {
        this.SmallRocks = smallRocks;
    }

    public List<SwordMonster> getSwordMonstersEnemies() {
        return swordMonsters;
    }

    public void setSwordMonsters(List<SwordMonster> enemies) {
        this.swordMonsters = enemies;
    }

    public List<PurpleMonster> getPurpleMonsters() {
        return purpleMonsters;
    }

    public void setPurpleMonsters(List<PurpleMonster> purpleMonsters) {
        this.purpleMonsters = purpleMonsters;
    }

    public List<MinhoteMonster> getMinhoteMonsters() {
        return minhoteMonsters;
    }

    public void setMinhoteMonsters(List<MinhoteMonster> minhoteMonsters) {
        this.minhoteMonsters = minhoteMonsters;
    }

    public boolean isEmpty(Position position) {
        for (Tile tile : tiles) {
            int tileX = tile.getPosition().x();
            int tileY = tile.getPosition().y();

            // Check if the position is within the bounds of the 8x8 tile
            if (position.x() >= tileX && position.x() < tileX + 8 &&
                    position.y() >= tileY && position.y() < tileY + 8) {
                return false; // Position is within the tile, so it's not empty
            }
        }
        return true; // Position is not within any tile
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

    public boolean checkColisionDown(){
        for(Tile tile: tiles){
            if(tile.getPosition().equals(player.getPosition())){
                return true;
            }
        }
        return false;
    }

}