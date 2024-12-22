package HollowKnight.controller.menu;

import HollowKnight.Game;
import HollowKnight.model.menu.MainMenu;
import HollowKnight.model.menu.SettingsMenu;
import HollowKnight.state.MainMenuState;

import java.io.IOException;

public class SettingsMenuController extends MenuController<SettingsMenu> {
    public SettingsMenuController(SettingsMenu menu,ParticleMenuController particleMenuController,
                                  OptionController optionController)
    {
        super(menu, particleMenuController, optionController);
    }

    @Override
    protected void onQuit(Game game) throws IOException {
        game.setState(new MainMenuState(new MainMenu()));
    }
}
