package HollowKnight;


import HollowKnight.gui.LanternaGUI;
import HollowKnight.model.game.scene.SceneLoader;
import HollowKnight.model.menu.Menu;
import HollowKnight.state.GameState;
import HollowKnight.state.MenuState;
import HollowKnight.state.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {
    private final LanternaGUI gui;
    private State state;

    public Game() throws IOException, URISyntaxException, FontFormatException {
        int SCREEN_WIDTH = 200;
        int SCREEN_HEIGHT = 90;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        double maxFontWidth = width / SCREEN_WIDTH;
        double maxFontHeight = height / SCREEN_HEIGHT;
        int fontSize = (int) Math.min(maxFontWidth, maxFontHeight);

        this.gui = new LanternaGUI(SCREEN_WIDTH, SCREEN_HEIGHT, 10);
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

        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            state.move(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            if (sleepTime > 0) Thread.sleep(sleepTime);
        }

        gui.close();
    }
}