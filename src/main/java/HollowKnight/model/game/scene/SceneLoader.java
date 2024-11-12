package HollowKnight.model.game.scene;

import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.model.game.elements.Particle.Particle;
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

    public Scene createScene() {
        Scene scene = new Scene(160, 90);
        scene.setParticles(createParticles(40,scene));
        scene.setPlayer(createPlayer());
        scene.setTiles(createWalls());

        return scene;
    }

    public SceneLoader() throws IOException {
        URL resource = getClass().getClassLoader().getResource("levels/level0.lvl");
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
                if (line.charAt(x) == '#') walls.add(new Tile(x, y));
        }

        return walls;
    }

    protected Knight createPlayer() {
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'P') {
                    System.out.println("Found Player " + x + " - "+ y);
                    return new Knight(x, y, 50, 10, 5);
                }
        }
        return null;
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
