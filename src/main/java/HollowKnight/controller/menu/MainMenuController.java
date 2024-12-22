package HollowKnight.controller.menu;

import HollowKnight.Game;
import HollowKnight.model.menu.MainMenu;
import HollowKnight.model.menu.Menu;
import HollowKnight.model.menu.SettingsMenu;
import com.sun.tools.javac.Main;

public class MainMenuController extends MenuController<MainMenu> {
    public MainMenuController(MainMenu menu, ParticleMenuController particleMenuController,
                              OptionController optionController)
    {
        super(menu, particleMenuController, optionController);
    }

    @Override
    protected void onQuit(Game game) {
        game.setState(null);
    }
}

