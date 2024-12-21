package HollowKnight;


import HollowKnight.gui.GUI;
import HollowKnight.gui.LanternaGUI;
import HollowKnight.gui.LanternaScreenGenerator;
import HollowKnight.gui.ScreenGenerator;
import HollowKnight.model.game.scene.SceneLoader;
import HollowKnight.model.menu.Menu;
import HollowKnight.sound.MenuSoundPlayer;
import HollowKnight.sound.SoundLoader;
import HollowKnight.state.GameState;
import HollowKnight.state.MenuState;
import HollowKnight.state.State;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {
    public static final int PIXEL_WIDTH = 230;
    public static final int PIXEL_HEIGHT = 130;
    private final MenuSoundPlayer menuSoundPlayer;

    private long fpsLastUpdate = System.currentTimeMillis();
    private int frames = 0;
    private int currentFps = 0;

    private final LanternaGUI gui;
    private State<?> state;

    private Game() throws Exception {
        ScreenGenerator screenCreator = new LanternaScreenGenerator(
                new DefaultTerminalFactory(),
                new TerminalSize(PIXEL_WIDTH, PIXEL_HEIGHT),
                GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()
        );
        this.gui = new LanternaGUI(screenCreator, "Soul Knight");
        this.menuSoundPlayer = new MenuSoundPlayer(new SoundLoader().loadSound(AudioSystem
                .getAudioInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("sound/demo.wav"))), AudioSystem.getClip()));
        this.state = new MenuState(new Menu());
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        Logger logger = Logger.getLogger(Game.class.getName());
        try {
            new Game().start();
        } catch (Exception e) {
            logger.log(Level.INFO, "An error occurred while running Game.start()", e);
        }
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getNumberOfLevels() {
        return 2;
    }

    private void start() throws IOException, InterruptedException {
        int FPS = 30;
        int frameTime = 1000 / FPS;
        int tick = 0;

        Thread.sleep(100);
        menuSoundPlayer.start();
        while (this.state != null) {    // Game loop
            long startTime = System.currentTimeMillis();

            if (tick == 0) {
                Thread.sleep(100);
            }

            state.move(this, gui, tick);

            // Update the FPS counter
            frames++;
            long currentTime = System.currentTimeMillis();
            if (currentTime - fpsLastUpdate >= 1000) {
                currentFps = frames;
                frames = 0;
                fpsLastUpdate = currentTime;
            }

            gui.setFPS(currentFps);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            if (sleepTime > 0) Thread.sleep(sleepTime);
            tick++;
        }

        gui.close();
    }


    public Object getGUI() {
        return this.gui;
    }
}