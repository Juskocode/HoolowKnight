package HollowKnight.controller.menu;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.state.GameState;
import HollowKnight.state.State;
import HollowKnight.model.menu.Menu;

import java.io.IOException;


public class MenuController extends Controller<Menu> {

    protected MenuController(Menu menu) {
        super(menu);
    }

    @Override
    public void move(Game game, GUI.ACTION action, long time) {
        switch (action) {
            case UP:
                this.getModel().nextOption();
                break;
            case DOWN:
                this.getModel().previousOption();
                break;
            case SELECT:
                if (this.getModel().isSelectedExit()) {
                    game.setState(null);
                }
                break;
            default:
        }
    }
}
