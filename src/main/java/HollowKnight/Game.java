package HollowKnight;


import HollowKnight.gui.LanternaGUI;
import HollowKnight.model.menu.Menu;
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
        this.gui = new LanternaGUI(80, 40);
        this.state = new MenuState(new Menu());
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {        Logger logger = Logger.getLogger(Game.class.getName());
        try {
            new Game().start();
        } catch (Exception e) {
            logger.log(Level.INFO, "An error occurred", e);
        }
    }

    public void setState(MenuState state) {
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