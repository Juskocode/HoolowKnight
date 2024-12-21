package HollowKnight.model.game.scene;

import HollowKnight.model.Position;
import HollowKnight.model.game.elements.Collectables.EnergyOrb;
import HollowKnight.model.game.elements.Collectables.HealthOrb;
import HollowKnight.model.game.elements.Collectables.SpeedOrb;
import HollowKnight.model.game.elements.Element;
import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.model.game.elements.Particle.Particle;
import HollowKnight.model.game.elements.Particle.RainParticle;
import HollowKnight.model.game.elements.Tree.MediumTree;
import HollowKnight.model.game.elements.Tree.SmallTree;
import HollowKnight.model.game.elements.enemies.GhostMonster;
import HollowKnight.model.game.elements.enemies.PurpleMonster;
import HollowKnight.model.game.elements.enemies.SwordMonster;
import HollowKnight.model.game.elements.rocks.BigRock;
import HollowKnight.model.game.elements.rocks.SmallRock;
import HollowKnight.model.game.elements.tile.Tile;
import com.googlecode.lanterna.TextColor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SceneLoader {
    private final List<String> lines;
    private final int sceneID;

    private final int TILE_SIZE = 8;

    public SceneLoader(int id) throws IOException {
        this.sceneID = id;
        URL resource = getClass().getClassLoader().getResource("levels/level" + id + ".lvl");
        if (resource == null){
            throw new FileNotFoundException("Level file not found!");
        }
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));

        lines = readLines(br);
    }

    private List<String> readLines(BufferedReader br) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; )
            lines.add(line);
        return lines;
    }

    public Scene createScene(Knight knight) {
        Scene scene = new Scene(230, 130, sceneID);

        scene.setPlayer(createPlayer(scene, knight));

        scene.setStartPosition(scene.getPlayer().getPosition());


        scene.setMap(createMap(scene));
        scene.setTiles(createWalls(scene));
        scene.setSmallTrees(createSmallTrees(scene));
        scene.setMediumTrees(createMediumTrees(scene));
        scene.setBigRocks(createBigRocks(scene));
        scene.setSmallRocks(createSmallRocks(scene));
        scene.setSwordMonsters(createSwordMonsters(scene));
        scene.setPurpleMonsters(createPurpleMonsters(scene));
        scene.setMinhoteMonsters(createMinhoteMonsters(scene));
        scene.setParticles(createParticles(5, scene));
        scene.setEnergyOrbs(createEnergyOrbs(scene));
        scene.setHealthOrbs(createHealthOrbs(scene));
        scene.setSpeedOrbs(createSpeedOrbs(scene));

        return scene;
    }

    protected int getWidth() {
        int width = 0;
        for (String line : lines)
            width = Math.max(width, line.length());
        return width;
    }

    protected int getHeight() {
        return lines.size();
    }

    private Element[][] createMap(Scene scene) {
        Element[][] map = new Element[scene.getHeight()][scene.getWidth()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'x' || line.charAt(x) == 'M' || line.charAt(x) == 'G'|| line.charAt(x) == 'L') {
                    map[y][x] = new Tile(x * TILE_SIZE, y * TILE_SIZE, line.charAt(x));
                }
                else if (line.charAt(x) == 'u') {
                    scene.setEndPosition(new Position(x*TILE_SIZE,y*TILE_SIZE));
                    System.out.println(scene.getEndPosition().x());
                }
                else
                    map[y][x] = null;
            }
        }

        return map;
    }

    private Tile[][] createWalls(Scene scene) {
        Tile[][] walls = new Tile[scene.getHeight()][scene.getWidth()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'x' || line.charAt(x) == 'M' || line.charAt(x) == 'G' || line.charAt(x) == 'L') {
                    walls[y][x] = new Tile(x * TILE_SIZE, y * TILE_SIZE, line.charAt(x));
                }
                else
                    walls[y][x] = null;
            }
        }

        return walls;
    }

    private SmallTree[][] createSmallTrees(Scene scene) {
        SmallTree[][] trees = new SmallTree[scene.getHeight()][scene.getWidth()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 't') {
                    trees[y][x] = new SmallTree(x * TILE_SIZE, y * TILE_SIZE);
                }
            }
        }

        return trees;
    }

    private MediumTree[][] createMediumTrees(Scene scene) {
        MediumTree[][] trees = new MediumTree[scene.getHeight()][scene.getWidth()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'T') {
                    trees[y][x] = new MediumTree(x * TILE_SIZE, y * TILE_SIZE);
                }
            }
        }

        return trees;
    }

    private BigRock[][] createBigRocks(Scene scene) {
        BigRock[][] rocks = new BigRock[scene.getHeight()][scene.getWidth()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'R') {
                    rocks[y][x] = new BigRock(x * TILE_SIZE, y * TILE_SIZE);
                }
            }
        }

        return rocks;
    }

    private SmallRock[][] createSmallRocks(Scene scene) {
        SmallRock[][] rocks = new SmallRock[scene.getHeight()][scene.getWidth()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'r') {
                    rocks[y][x] = new SmallRock(x * TILE_SIZE, y * TILE_SIZE);
                }
            }
        }

        return rocks;
    }

    private EnergyOrb[][] createEnergyOrbs(Scene scene){
        EnergyOrb[][] energyOrbs = new EnergyOrb[scene.getHeight()][scene.getWidth()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'e') {
                    energyOrbs[y][x] = new EnergyOrb(x * TILE_SIZE, y * TILE_SIZE, 10);
                }
            }
        }
        return energyOrbs;
    }

    private HealthOrb[][] createHealthOrbs(Scene scene){
        HealthOrb[][] healthOrbs = new HealthOrb[scene.getHeight()][scene.getWidth()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'h') {
                    healthOrbs[y][x] = new HealthOrb(x * TILE_SIZE, y * TILE_SIZE, 10);
                }
            }
        }
        return healthOrbs;
    }


    private SpeedOrb[][] createSpeedOrbs(Scene scene) {
        SpeedOrb[][] speedOrbs = new SpeedOrb[scene.getHeight()][scene.getWidth()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 's') {
                    speedOrbs[y][x] = new SpeedOrb(x * TILE_SIZE, y * TILE_SIZE, 1.2);
                }
            }
        }
        return speedOrbs;
    }

    private SwordMonster[][] createSwordMonsters(Scene scene) {
        SwordMonster[][] monsters = new SwordMonster[scene.getHeight()][scene.getWidth()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'E') {
                    monsters[y][x] = new SwordMonster(x * TILE_SIZE, y * TILE_SIZE,10,scene,15);
                }
            }
        }

        return monsters;
    }

    private PurpleMonster[][] createPurpleMonsters(Scene scene) {
        PurpleMonster[][] monsters = new PurpleMonster[scene.getHeight()][scene.getWidth()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'l') {
                    monsters[y][x] = new PurpleMonster(x * TILE_SIZE, y * TILE_SIZE,10,scene,15);
                }
            }
        }

        return monsters;
    }

    private GhostMonster[][] createMinhoteMonsters(Scene scene) {
        GhostMonster[][] monsters = new GhostMonster[scene.getHeight()][scene.getWidth()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'm') {
                    monsters[y][x] = new GhostMonster(x * TILE_SIZE, y * TILE_SIZE, 10, scene, 15);
                }
            }
        }
        return monsters;
    }

    private Knight createPlayer(Scene scene, Knight knight) {
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'P') {
                    knight.setPosition(new Position(x * TILE_SIZE, y * TILE_SIZE - 2));
                    knight.setScene(scene);
                    return knight;
                }
            }
        }
        throw new IllegalStateException("Knight not found within the level file!");

    }

    private List<Particle> createParticles(int size, Scene scene) {
        List<Particle> particles = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {

            particles.add(new RainParticle(
                    random.nextInt(scene.getWidth()),
                    random.nextInt(scene.getHeight()),
                    new Position(0, 0),
                    new TextColor.RGB(0, 0, random.nextInt(100, 255))
            ));
        }
        return particles;
    }
}
