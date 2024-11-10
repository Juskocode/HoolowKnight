package HollowKnight.controller.game;

import HollowKnight.Game;
import HollowKnight.controller.menu.Menu;
import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.map.Scene;
import HollowKnight.state.MenuState;

import java.io.IOException;

public class SceneController extends GameController {
    private final PlayerController playerController;

    public SceneController(Scene scene) {
        super(scene);
        this.playerController = new PlayerController(scene);
    }

    public void move(Game game, GUI.ACTION action, long time) throws IOException {
        if (action == GUI.ACTION.QUIT)
            game.setState(new MenuState(new Menu()));
        else {
            playerController.move(game, action, time);
        }
    }
}
