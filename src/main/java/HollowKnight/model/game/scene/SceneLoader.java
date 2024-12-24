package HollowKnight.model.game.scene;

import HollowKnight.model.dataStructs.Position;
import HollowKnight.model.game.elements.Collectables.*;
import HollowKnight.model.game.elements.Element;
import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.model.game.elements.Particle.Particle;
import HollowKnight.model.game.elements.Particle.RainParticle;
import HollowKnight.model.game.elements.Spike;
import HollowKnight.model.game.elements.Tree;
import HollowKnight.model.game.elements.enemies.*;
import HollowKnight.model.game.elements.rocks.Rock;
import HollowKnight.model.game.elements.tile.Tile;
import com.googlecode.lanterna.TextColor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Character.isLetterOrDigit;
import static java.lang.Character.isSpaceChar;
import static java.nio.charset.StandardCharsets.UTF_8;

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
        Path path = Paths.get(new File(resource.getFile()).toURI());
        BufferedReader br = Files.newBufferedReader(path, UTF_8);


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

        scene.setSpikes(createSpikes(scene));
        scene.setTrees(createTrees(scene));
        scene.setRocks(createRocks(scene));
        scene.setOrbs(createOrbs(scene));

        scene.setMonsters(createMonsters(scene));
        scene.setParticles(createParticles(15, scene));


        return scene;
    }

    public void setOrbs(Scene scene) {
        scene.setOrbs(createOrbs(scene));
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

    private Spike[][] createSpikes(Scene scene) {
        Spike[][] spikes = new Spike[scene.getHeight()][scene.getWidth()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (!isLetterOrDigit(line.charAt(x)) && !isSpaceChar(line.charAt(x)) && line.charAt(x) != '*')
                    spikes[y][x] = new Spike(x * TILE_SIZE, y * TILE_SIZE, line.charAt(x));
                else {
                    spikes[y][x] = null;
                }
            }
        }
        return spikes;
    }
    private Tree[][] createTrees(Scene scene) {
        Tree[][] trees = new Tree[scene.getHeight()][scene.getWidth()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 't' || line.charAt(x) == 'T')
                    trees[y][x] = new Tree(x * TILE_SIZE, y * TILE_SIZE, line.charAt(x));
                else {
                    trees[y][x] = null;
                }
            }
        }
        return trees;
    }

    private Rock[][] createRocks(Scene scene) {
        Rock[][] rocks = new Rock[scene.getHeight()][scene.getWidth()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'R' || line.charAt(x) == 'r') {
                    rocks[y][x] = new Rock(x * TILE_SIZE, y * TILE_SIZE, line.charAt(x));
                }
            }
        }
        return rocks;
    }


    public Collectables[][] createOrbs(Scene scene) {
        Collectables[][] orbs = new Collectables[scene.getHeight()][scene.getWidth()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                char type = line.charAt(x);
                orbs[y][x] = OrbFactory.createOrb(type, x * TILE_SIZE, y * TILE_SIZE);
            }
        }
        return orbs;
    }

    private List<Enemies> createMonsters(Scene scene) {
        List<Enemies> monsters = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                char type = line.charAt(x);
                Enemies monster = MonsterFactory.createMonster(x * TILE_SIZE, y * TILE_SIZE, scene, type);
                if (monster != null) {
                    monsters.add(monster);
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
