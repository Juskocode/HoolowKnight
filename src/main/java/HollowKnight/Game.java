package HollowKnight;


import HollowKnight.gui.LanternaGUI;
import HollowKnight.model.game.elements.map.Scene;
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
        int SCREEN_WIDTH = 80;
        int SCREEN_HEIGHT = 40;

        this.gui = new LanternaGUI(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.state = new GameState(new Scene(SCREEN_WIDTH, SCREEN_HEIGHT));
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

    public void start() throws IOException{
        int FPS = 60;
        int TBF = 1000/60;

        while (this.state != null) {
            long startTime = System.currentTimeMillis();
            state.move(this, gui, startTime);
            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = TBF - elapsedTime;
            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Error handling thread");
            }
        }
    }
}