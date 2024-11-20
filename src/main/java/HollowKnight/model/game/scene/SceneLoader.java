package HollowKnight.model.game.scene;

import HollowKnight.model.game.elements.Collectables.Collectables;
import HollowKnight.model.game.elements.Collectables.EnergyOrb;
import HollowKnight.model.game.elements.Collectables.HealthOrb;
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
import com.googlecode.lanterna.TextColor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SceneLoader {
    private final List<String> lines;
    private final int TILE_SIZE = 8;

    public SceneLoader() throws IOException {
        URL resource = getClass().getClassLoader().getResource("levels/level1.lvl");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));

        lines = readLines(br);
    }

    private List<String> readLines(BufferedReader br) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; )
            lines.add(line);
        return lines;
    }

    public Scene createScene() {
        Scene scene = new Scene(160, 88);

        scene.setTiles(createWalls(scene));
        scene.setSmallTrees(createSmallTrees(scene));
        scene.setMediumTrees(createMediumTrees(scene));
        scene.setBigRocks(createBigRocks(scene));
        scene.setSmallRocks(createSmallRocks(scene));
        scene.setSwordMonsters(createSwordMonsters(scene));
        scene.setPurpleMonsters(createPurpleMonsters(scene));
        scene.setMinhoteMonsters(createMinhoteMonsters(scene));
        scene.setPlayer(createPlayer());
        scene.setParticles(createParticles(60, scene));
        scene.setCollectables(createCollectables(scene));

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

    private Tile[][] createWalls(Scene scene) {
        Tile[][] walls = new Tile[scene.getHeight()][scene.getWidth()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'x') {
                    walls[y][x] = new Tile(x * TILE_SIZE, y * TILE_SIZE);
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

    private Collectables[][] createCollectables(Scene scene){
        Collectables[][] collectables = new Collectables[scene.getHeight()][scene.getWidth()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'e') {
                    collectables[y][x] = new EnergyOrb(x * TILE_SIZE, y * TILE_SIZE, 10);
                }
                else if(line.charAt(x)=='h'){
                    collectables[y][x] = new HealthOrb(x * TILE_SIZE, y * TILE_SIZE, 50);
                }
            }
        }
        return collectables;
    }

    private SwordMonster[][] createSwordMonsters(Scene scene) {
        SwordMonster[][] monsters = new SwordMonster[scene.getHeight()][scene.getWidth()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'E') {
                    monsters[y][x] = new SwordMonster(x * TILE_SIZE, y * TILE_SIZE);
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
                    monsters[y][x] = new PurpleMonster(x * TILE_SIZE, y * TILE_SIZE);
                }
            }
        }

        return monsters;
    }

    private MinhoteMonster[][] createMinhoteMonsters(Scene scene) {
        MinhoteMonster[][] monsters = new MinhoteMonster[scene.getHeight()][scene.getWidth()];

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'M') {
                    monsters[y][x] = new MinhoteMonster(x * TILE_SIZE, y * TILE_SIZE);
                }
            }
        }

        return monsters;
    }

    private Knight createPlayer() {
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'P') {
                    return new Knight(x * TILE_SIZE, y * TILE_SIZE - 2, 50, 10, 5);
                }
            }
        }
        return null;
    }

    private List<Particle> createParticles(int size, Scene scene) {
        List<Particle> particles = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            particles.add(new Particle(
                    random.nextInt(scene.getWidth()),
                    random.nextInt(scene.getHeight()),
                    new TextColor.RGB(0, 0, random.nextInt(100, 255))
            ));
        }
        return particles;
    }


}
