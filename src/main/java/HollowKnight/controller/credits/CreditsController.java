package HollowKnight.controller.credits;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.model.credits.Credits;
import HollowKnight.model.menu.MainMenu;
import HollowKnight.state.MainMenuState;

import java.io.IOException;

import static HollowKnight.gui.GUI.ACTION.QUIT;

public class CreditsController extends Controller<Credits> {
    public CreditsController(Credits model) {
        super(model);
    }

    @Override
    public void move(Game game, GUI.ACTION action, long frameCount) throws IOException {
        if (action == QUIT) {
            game.setState(new MainMenuState(new MainMenu(), game.getSpriteLoader()));
        }
    }
}
