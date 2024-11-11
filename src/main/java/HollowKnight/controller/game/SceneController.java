package HollowKnight.controller.game;

import HollowKnight.Game;
import HollowKnight.gui.GUI;
import HollowKnight.model.game.scene.Scene;

public class SceneController extends GameController {
    private final PlayerController playerController;

    public SceneController(Scene scene) {
        super(scene);
        this.playerController = new PlayerController(scene);
    }

    public void move(Game game, GUI.ACTION action, long time){
        if (action == GUI.ACTION.QUIT)
            game.setState(null);
        else {
            playerController.move(game, action, time);
        }
    }
}
