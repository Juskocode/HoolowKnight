package HollowKnight.controller.menu;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.model.game.scene.SceneLoader;
import HollowKnight.model.menu.HelpMenu;
import HollowKnight.state.GameState;
import HollowKnight.state.MenuState;
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
                if (time >= 80) {
                    if (this.getModel().isSelectedExit()) {
                        game.setState(null);
                    } else if (this.getModel().isSelectedStart()) {
                        this.getModel().setInGame(true); //signal the transition state

                        game.setState(new GameState(new SceneLoader().createScene()));
                    }else if (this.getModel().isSelected(2)){
                        game.setState(new MenuState.HelpMenuState(new HelpMenu()));
                    }
                }
                break;
            default:
        }
        particleMenuController.move(game, action, time);
    }
}
