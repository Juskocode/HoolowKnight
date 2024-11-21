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

    private Game() throws IOException, URISyntaxException, FontFormatException {
        int SCREEN_WIDTH = 200;
        int SCREEN_HEIGHT = 88;

        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
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
        int tick = 0;
        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            state.move(this, gui, tick);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            if (sleepTime > 0) Thread.sleep(sleepTime);
            tick++;
        }

        gui.close();
    }

    public void setKeySpam(boolean keySpam) {
        gui.setKeySpam(keySpam);
    }

    public Object getGUI() {
        return this.gui;
    }
}