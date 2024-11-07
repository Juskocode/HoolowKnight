package HollowKnight.controller.menu;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.state.GameState;

import java.io.IOException;


public class MenuController extends Controller<Menu> {

    protected MenuController(Menu menu) {
        super(menu);
    }

    @Override
    public void move(Game game, GUI action, long time) throws IOException {
        switch (action) {
            case UP:
                this.getModel().moveUp();
                break;
            case DOWN:
                this.getModel().moveDown();
                break;
            case SELECT:
                if (this.getMode().isExit()) {
                    game.setState(null);
                }
                else {
                    State nextState = new GameState();
                    game.setState(nextState);
                }
        }
    }
}
