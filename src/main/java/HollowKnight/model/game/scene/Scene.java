package HollowKnight.model.game.scene;

import HollowKnight.model.Position;
import HollowKnight.model.game.elements.Collectables.Collectables;
import HollowKnight.model.game.elements.Collectables.EnergyOrb;
import HollowKnight.model.game.elements.Collectables.HealthOrb;
import HollowKnight.model.game.elements.Collectables.SpeedOrb;
import HollowKnight.model.game.elements.Element;
import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.model.game.elements.Particle.Particle;
import HollowKnight.model.game.elements.Tree.MediumTree;
import HollowKnight.model.game.elements.Tree.SmallTree;
import HollowKnight.model.game.elements.enemies.Enemies;
import HollowKnight.model.game.elements.enemies.GhostMonster;
import HollowKnight.model.game.elements.enemies.PurpleMonster;
import HollowKnight.model.game.elements.enemies.SwordMonster;
import HollowKnight.model.game.elements.rocks.BigRock;
import HollowKnight.model.game.elements.rocks.SmallRock;
import HollowKnight.model.game.elements.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private final int width;
    private final int height;

    private Element[][] map;
    private Tile[][] tiles;
    private SmallTree[][] smallTrees;
    private MediumTree[][] mediumTrees;

    private EnergyOrb[][] energyOrbs;
    private HealthOrb[][] healthOrbs;
    private SpeedOrb[][] speedOrbs;

    private BigRock[][] bigRocks;
    private SmallRock[][] smallRocks;

    private SwordMonster[][] swordMonsters;
    private PurpleMonster[][] purpleMonsters;
    private GhostMonster[][] ghostMonsters;

    private double gravity = 0.25;

    private Knight player;
    private Particle particle;
    private List<Particle> particles;
    private List<Particle> doubleJumpParticles;
    private List<Particle> jumpParticles;
    private List<Particle> respawnParticles;
    private List<Particle> dashParticles;
    private Position EndPosition;

    private Position transitionPositionBegin;
    private Position transitionPositionEnd;

    public Scene(int width, int height) {
        this.width = width;
        this.height = height;

        this.doubleJumpParticles = new ArrayList<>();
        this.jumpParticles = new ArrayList<>();
        this.particles = new ArrayList<>();
        this.respawnParticles = new ArrayList<>();
        this.dashParticles = new ArrayList<>();

        this.map = new Element[height][width];
        this.tiles = new Tile[height][width];
        this.smallTrees = new SmallTree[height][width];
        this.mediumTrees = new MediumTree[height][width];
        this.bigRocks = new BigRock[height][width];
        this.smallRocks = new SmallRock[height][width];
        this.swordMonsters = new SwordMonster[height][width];
        this.purpleMonsters = new PurpleMonster[height][width];
        this.ghostMonsters = new GhostMonster[height][width];
        this.energyOrbs = new EnergyOrb[height][width];
        this.healthOrbs = new HealthOrb[height][width];
        this.speedOrbs = new SpeedOrb[height][width];
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

    public Element[][] getMap() {return map;}

    public void setMap(Element[][] map) {this.map = map;}

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

    public GhostMonster[][] getMinhoteMonsters() {
        return ghostMonsters;
    }

    public void setMinhoteMonsters(GhostMonster[][] ghostMonsters) {
        this.ghostMonsters = ghostMonsters;
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

    public EnergyOrb[][] getEnergyOrbs() {
        return energyOrbs;
    }

    public void setEnergyOrbs(EnergyOrb[][] energyOrbs) {
        this.energyOrbs = energyOrbs;
    }

    public HealthOrb[][] getHealthOrbs() {
        return healthOrbs;
    }

    public void setHealthOrbs(HealthOrb[][] healthOrbs) {
        this.healthOrbs = healthOrbs;
    }

    public Position getEndPosition() {
        return EndPosition;
    }

    public void setEndPosition(Position endPosition) {
        EndPosition = endPosition;
    }

    public Position getTransitionPositionBegin() {
        return transitionPositionBegin;
    }

    public void setTransitionPositionBegin(Position transitionPositionBegin) {
        this.transitionPositionBegin = transitionPositionBegin;
    }

    public Position getTransitionPositionEnd() {
        return transitionPositionEnd;
    }

    public void setTransitionPositionEnd(Position transitionPositionEnd) {
        this.transitionPositionEnd = transitionPositionEnd;
    }


    private boolean isOutSideScene(double x1, double x2, double y1, double y2) {
        return x1 < 0 || x2 >= this.width || y1 < 0 || y2 >= this.height;
    }

    private boolean checkCollision(double x1, double x2, double y1, double y2, Element[][] layer) {
        if (isOutSideScene(x1, x2, y1, y2))
            return true;

        for (int tileY: List.of(((int)y1 / Tile.SIZE), ((int)y2 / Tile.SIZE))) {
            for (int tileX: List.of((int)x1 / Tile.SIZE, (int)x2 / Tile.SIZE)) {
                if (layer[tileY][tileX] != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean collidesLeft(Position position, Position size) {
        double x = position.x(), y = position.y();
        return checkCollision(x, x + 1, y, y + size.y() - 1, map);
    }

    public boolean collidesRight(Position position, Position size) {
        double x = position.x(), y = position.y();
        return checkCollision(x + size.x() - 1, x + size.x() - 1, y, y + size.y() - 1, map);
    }

    public boolean collidesUp(Position position, Position size) {
        double x = position.x(), y = position.y();
        return checkCollision(x, x + size.x() - 1, y, y + 1, map);
    }

    public boolean collidesDown(Position position, Position size) {
        double x = position.x(), y = position.y();
        return checkCollision(x, x + size.x() - 1, y + size.y() - 2, y + size.y() - 1, map);
    }

    public List<Particle> getDoubleJumpParticles() {
        return doubleJumpParticles;
    }

    public void setDoubleJumpParticles(List<Particle> jumpParticles) {
        this.doubleJumpParticles = jumpParticles;
    }

    public List<Particle> getJumpParticles() {
        return jumpParticles;
    }

    public void setJumpParticles(List<Particle> jumpParticles) {
        this.jumpParticles = jumpParticles;
    }

    public List<Particle> getDashParticles() {
        return dashParticles;
    }

    public void setDashParticles(List<Particle> dashParticles) {
        this.dashParticles = dashParticles;
    }

    public void collectOrbs(Collectables[][] Orbs){
        double x = getPlayer().getPosition().x();
        double y = getPlayer().getPosition().y();
        double width = player.getWidth(), height = player.getHeight();

        for (int tileY: List.of((int)y / Tile.SIZE, (int)(y + height - 1) / Tile.SIZE)) {
            for (int tileX: List.of((int)x / Tile.SIZE, (int)(x + width - 1) / Tile.SIZE)) {
                if (Orbs[tileY][tileX] != null) {
                    Orbs[tileY][tileX].benefit(getPlayer());
                    Orbs[tileY][tileX] = null;
                }
            }
        }
    }

    public void collideMonsters(Enemies[][] enemies){
        double x = getPlayer().getPosition().x();
        double y = getPlayer().getPosition().y();
        double width = player.getWidth(), height = player.getHeight();

        for (int tileY: List.of((int)y / Tile.SIZE, (int)(y + height - 1) / Tile.SIZE)) {
            for (int tileX: List.of((int)x / Tile.SIZE, (int)(x + width - 1) / Tile.SIZE)) {
                //System.out.println("x :" + tileX + "y :" + tileY);
                if (enemies[tileY][tileX] != null) {
                    getPlayer().PlayerHit(enemies[tileY][tileX].getDamage());
                }
            }
        }
    }

    public boolean isAtEndPosition() {
        double x1 = player.getPosition().x();
        return x1 >= EndPosition.x();
    }


    public SpeedOrb[][] getSpeedOrbs() {
        return speedOrbs;
    }

    public void setSpeedOrbs(SpeedOrb[][] speedOrbs) {
        this.speedOrbs = speedOrbs;
    }

    public List<Particle> getRespawnParticles() {
        return respawnParticles;
    }

    public void setRespawnParticles(List<Particle> respawnParticles) {
        this.respawnParticles = respawnParticles;
    }

    public Particle getParticle() {
        return particle;
    }

    public void setParticle(Particle particle) {
        this.particle = particle;
    }
}
