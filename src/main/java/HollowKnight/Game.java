package HollowKnight;


import HollowKnight.gui.LanternaGUI;
import HollowKnight.model.game.scene.SceneLoader;
import HollowKnight.state.GameState;
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
        int SCREEN_WIDTH = 80;
        int SCREEN_HEIGHT = 40;

        this.gui = new LanternaGUI(SCREEN_WIDTH*10, SCREEN_HEIGHT*10);
        this.state = new GameState(new SceneLoader().createScene());
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
        int FPS = 60;
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