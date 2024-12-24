package HollowKnight.controller.menu;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.gui.RescalableGUI;
import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.model.game.scene.SceneLoader;
import HollowKnight.model.menu.MainMenu;
import HollowKnight.model.menu.Menu;
import HollowKnight.model.menu.SettingsMenu;
import HollowKnight.state.GameState;
import HollowKnight.state.MainMenuState;
import HollowKnight.state.SettingsMenuState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class OptionController extends Controller<Menu> {
    private static final RescalableGUI.ResolutionScale[] resolutions = RescalableGUI.ResolutionScale.values();

    public OptionController(Menu menu) {
        super(menu);
    }

    @Override
    public void move(Game game, GUI.ACTION action, long frameCount) throws IOException, URISyntaxException, FontFormatException {
        switch (getModel().getCurrentOption().getType()) {
            case START_GAME:
                if (action == GUI.ACTION.SELECT) {
                    game.setState(new GameState(
                            new SceneLoader(0)
                                    .createScene(new Knight(0, 0, 50, 10, 1)),
                            game.getSpriteLoader()
                    ));
                }
                break;
            case SETTINGS:
                if (action == GUI.ACTION.SELECT)
                    game.setState(new SettingsMenuState(new SettingsMenu(), game.getSpriteLoader()));
                break;
            case EXIT:
                if (action == GUI.ACTION.SELECT)
                    game.setState(null);
                break;
            case RESOLUTION:
                if (action == GUI.ACTION.RIGHT) {
                    int index = getResolutionIndex(game.getResolution());
                    if (index < resolutions.length - 1) {
                        RescalableGUI.ResolutionScale newResolution = resolutions[index + 1];
                        game.setResolution(newResolution);
                    }
                } else if (action == GUI.ACTION.LEFT) {
                    int index = getResolutionIndex(game.getResolution());
                    if (index > -1) {
                        RescalableGUI.ResolutionScale newResolution = index != 0 ? resolutions[index - 1] : null;
                        game.setResolution(newResolution);
                    }
                }
                break;
            case TO_MAIN_MENU:
                if (action == GUI.ACTION.SELECT)
                    game.setState(new MainMenuState(new MainMenu(), game.getSpriteLoader()));
        }
    }

    private Integer getResolutionIndex(RescalableGUI.ResolutionScale resolution) {
        for (int i = 0; i < resolutions.length; i++) {
            if (resolutions[i] == resolution)
                return i;
        }
        return -1;
    }
}
