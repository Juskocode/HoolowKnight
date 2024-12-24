package HollowKnight;


import HollowKnight.gui.*;
import HollowKnight.model.menu.MainMenu;
import HollowKnight.sound.MenuSoundPlayer;
import HollowKnight.sound.SoundLoader;
import HollowKnight.state.MainMenuState;
import HollowKnight.state.State;
import HollowKnight.view.sprites.GameSpriteLoader;
import HollowKnight.view.sprites.SpriteLoader;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import javax.sound.sampled.AudioSystem;
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
    private final SpriteLoader spriteLoader;

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
        this.spriteLoader = new GameSpriteLoader();
        this.state = new MainMenuState(new MainMenu(), spriteLoader);
    }

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Game.class.getName());
        try {
            new Game().start();
        } catch (Exception e) {
            logger.log(Level.INFO, "An error occurred while running Game.start()", e);
        }
    }

    public void setState(State<?> state) {
        this.state = state;
    }

    public RescalableGUI.ResolutionScale getResolution() {
        return gui.getResolutionScale();
    }

    public void setResolution(RescalableGUI.ResolutionScale resolution)
            throws IOException, URISyntaxException, FontFormatException {
        gui.setResolutionScale(resolution);
    }

    public int getNumberOfLevels() {
        return 4;
    }
    public SpriteLoader getSpriteLoader() {
        return spriteLoader;
    }

    private void start() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
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