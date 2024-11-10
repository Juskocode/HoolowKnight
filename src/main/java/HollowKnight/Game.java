package HollowKnight;


import HollowKnight.gui.LanternaGUI;
import HollowKnight.model.menu.Menu;
import HollowKnight.state.MenuState;
import HollowKnight.state.State;

import java.io.IOException;

public class Game {
    private final LanternaGUI gui;
    private State state;

    public Game(LanternaGUI gui) throws IOException {
        this.gui = new LanternaGUI(80, 40);
        this.state = new MenuState(new Menu());
    }

    public static void main(String[] args) throws IOException{
            Game game = new Game(new LanternaGUI());
            game.start();
    }

    public void setState(MenuState state) {
        this.state = state;
    }

    public void start() throws IOException{
        int FPS = 60;
        int TBF = 1000/60;

        while (this.state.isRuning()) {
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