package HollowKnight.controller.game;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.model.game.scene.Scene;
import HollowKnight.model.menu.Menu;
import HollowKnight.state.MenuState;

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
            game.setState(new MenuState(new Menu()));
        else {

            playerController.move(game, action, time);
            if (getModel().isAtEndPosition()){
                game.setState(new MenuState(new Menu()));
            }else{
                getModel().collectOrbs(getModel().getEnergyOrbs());
                getModel().collectOrbs(getModel().getHealthOrbs());
                getModel().collectOrbs(getModel().getSpeedOrbs());

                getModel().collideMonsters(getModel().getMinhoteMonsters());
                getModel().collideMonsters(getModel().getPurpleMonsters());
                getModel().collideMonsters(getModel().getSwordMonsters());

                particleController.move(game, action,time);
                enemieController.move(game,action,time);
            }
        }
    }


}
