package HollowKnight;


import HollowKnight.gui.LanternaGUI;
import HollowKnight.gui.LanternaScreenGenerator;
import HollowKnight.gui.ScreenGenerator;
import HollowKnight.model.game.scene.SceneLoader;
import HollowKnight.model.menu.Menu;
import HollowKnight.state.GameState;
import HollowKnight.state.MenuState;
import HollowKnight.state.State;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {
    public static final int PIXEL_WIDTH = 160;
    public static final int PIXEL_HEIGHT = 90;
    private final LanternaGUI gui;
    private State<?> state;

    private Game() throws IOException, URISyntaxException, FontFormatException {
        ScreenGenerator screenCreator = new LanternaScreenGenerator(
                new DefaultTerminalFactory(),
                new TerminalSize(PIXEL_WIDTH, PIXEL_HEIGHT),
                GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()
        );
        this.gui = new LanternaGUI(screenCreator, "Soul Knight");
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

    private void start() throws IOException, InterruptedException {
        int FPS = 30;
        int frameTime = 1000 / FPS;
        int tick = 0;
        while (this.state != null) {    //Game loop
            long startTime = System.currentTimeMillis();

            state.move(this, gui, tick);

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