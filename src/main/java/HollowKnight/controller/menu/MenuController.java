package HollowKnight.controller.menu;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.model.game.scene.SceneLoader;
import HollowKnight.state.GameState;
import HollowKnight.state.State;
import HollowKnight.model.menu.Menu;

import java.io.IOException;


public class MenuController extends Controller<Menu> {

    private final ParticleMenuController particleMenuController;
    public MenuController(Menu menu, ParticleMenuController particleMenuController) {
        super(menu);
        this.particleMenuController = particleMenuController;
    }

    @Override
    public void move(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP:
                this.getModel().previousOption();
                break;
            case DOWN:
                this.getModel().nextOption();
                break;
            case SELECT:
                if (this.getModel().isSelectedExit()) {
                    game.setState(null);
                } else if (this.getModel().isSelectedStart()) {
                    game.setState(new GameState(new SceneLoader().createScene()));
                }
                break;
            default:
        }
        particleMenuController.move(game, action, time);
    }
}
