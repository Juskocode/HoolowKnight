package HollowKnight.model.game.scene;

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
    public Scene createScene() {
        Scene scene = new Scene(160, 90);
        scene.setParticles(createParticles(60,scene));
        scene.setPlayer(createPlayer());
        scene.setTiles(createWalls());
        scene.setSmallTrees(createSmallTrees());
        scene.setMediumTrees(createMediumTrees());
        scene.setBigRocks(createBigRocks());
        scene.setSmallRocks(createSmallRocks());
        scene.setSwordMonsters(createSwordMonsters());
        scene.setPurpleMonsters(createPurpleMonsters());
        scene.setMinhoteMonsters(createMinhoteMonsters());
        return scene;
    }

    public SceneLoader() throws IOException {
        URL resource = getClass().getClassLoader().getResource("levels/level0.lvl");
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

    protected int getWidth() {
        int width = 0;
        for (String line : lines)
            width = Math.max(width, line.length());
        return width;
    }

    protected int getHeight() {
        return lines.size();
    }

    protected List<Tile> createWalls() {
        List<Tile> walls = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'x') walls.add(new Tile(x * TILE_SIZE, y * TILE_SIZE));
        }

        return walls;
    }

    protected Knight createPlayer() {
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'P') {
                    System.out.println("Found Player " + x + " - "+ y);
                    return new Knight(x * TILE_SIZE + 4, y * TILE_SIZE + 4, 50, 10, 5);
                }
        }
        return null;
    }

    protected List<SmallTree> createSmallTrees() {
        List<SmallTree> trees = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 't') {
                    System.out.println("Found small Tree " + x + " - "+ y);
                    trees.add(new SmallTree(x * TILE_SIZE, y * TILE_SIZE + 4));
                }
        }
        return trees;
    }

    protected List<MediumTree> createMediumTrees() {
        List<MediumTree> trees = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'T') {
                    System.out.println("Found MediumTree " + x + " - "+ y);
                    trees.add(new MediumTree(x * TILE_SIZE, y * TILE_SIZE + 4));
                }
        }
        return trees;
    }

    protected List<BigRock> createBigRocks() {
        List<BigRock> rocks = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'R') {
                    System.out.println("Found BigRock " + x + " - "+ y);
                    rocks.add(new BigRock(x * TILE_SIZE, y * TILE_SIZE + 4));
                }
        }
        return rocks;
    }

    protected List<SmallRock> createSmallRocks() {
        List<SmallRock> rocks = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'r') {
                    System.out.println("Found SmallRock " + x + " - "+ y);
                    rocks.add(new SmallRock(x * TILE_SIZE, y * TILE_SIZE + 4));
                }
        }
        return rocks;
    }

    protected List<SwordMonster> createSwordMonsters() {
        List<SwordMonster> enemies = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'E') {
                    System.out.println("Found Enemy  " + x + " - "+ y);
                    enemies.add(new SwordMonster(x * TILE_SIZE, y * TILE_SIZE + 4));
                }
        }
        return enemies;
    }

    protected List<PurpleMonster> createPurpleMonsters() {
        List<PurpleMonster> enemies = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'l') {
                    System.out.println("Found PurpleMonster  " + x + " - "+ y);
                    enemies.add(new PurpleMonster(x * TILE_SIZE, y * TILE_SIZE + 4));
                }
        }
        return enemies;
    }

    protected List<MinhoteMonster> createMinhoteMonsters() {
        List<MinhoteMonster> enemies = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'M') {
                    System.out.println("Found MinhoteMonster  " + x + " - "+ y);
                    enemies.add(new MinhoteMonster(x * TILE_SIZE, y * TILE_SIZE + 4));
                }
        }
        return enemies;
    }

    private List<Particle> createParticles(int size, Scene scene){
        List<Particle> particles = new ArrayList<>();
        Random random = new Random();
        for(int i =0; i < size; i++){
            Particle new_particle = new Particle(
                    random.nextInt(scene.getWidth()),
                    random.nextInt(scene.getHeight()),
                    new TextColor.RGB(0, 0, random.nextInt(100, 255))
            );
            particles.add(new_particle);
        }
        return particles;
    }
}
