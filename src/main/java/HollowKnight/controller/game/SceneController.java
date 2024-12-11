package HollowKnight.controller.game;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.model.game.scene.Scene;

import java.io.IOException;

public class SceneController extends Controller<Scene> {
    private final PlayerController playerController;
    private final ParticleController particleController;
    private final EnemieController enemieController;

    public SceneController(Scene scene, PlayerController playerController,
                           ParticleController particleController, EnemieController enemieController) {
        super(scene);
        this.playerController = playerController;
        this.particleController = particleController;
        this.enemieController = enemieController;
    }

    public void move(Game game, GUI.ACTION action, long time) throws IOException {
        if (action == GUI.ACTION.QUIT)
            game.setState(null);
        else {

            playerController.move(game, action, time);

            getModel().collectOrbs(getModel().getEnergyOrbs());
            getModel().collectOrbs(getModel().getHealthOrbs());
            getModel().collectOrbs(getModel().getSpeedOrbs());

            particleController.move(game, action,time);
            enemieController.move(game,action,time);
        }
    }
}
