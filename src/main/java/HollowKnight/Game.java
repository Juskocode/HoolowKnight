package HollowKnight;


import HollowKnight.gui.LanternaGUI;
import HollowKnight.state.MenuState;
import HollowKnight.state.State;

public class Game {
    private final LanternaGUI gui;
    private State state;

    public Game(LanternaGUI gui) {
        this.gui = new LanternaGUI();
        //this.state = new MenuState();
    }

    public static void main(String[] args) {

    }

    public void setState(State state) {
        this.state = state;
    }

    public void start() {
        int FPS = 60;
        int TBF = 1000/60;

        while (this.state) {
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
