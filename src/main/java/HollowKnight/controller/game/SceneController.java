package HollowKnight.controller.game;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.model.game.scene.Scene;
import HollowKnight.model.game.scene.SceneLoader;
import HollowKnight.model.menu.MainMenu;
import HollowKnight.state.GameState;
import HollowKnight.state.MainMenuState;

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
        Knight knight = getModel().getPlayer();
        if (action == GUI.ACTION.QUIT)
            game.setState(new MainMenuState(new MainMenu()));
        else {

            playerController.move(game, action, time);

            if (getModel().isAtEndPosition()) {
                if (getModel().getSceneID() + 1 >= game.getNumberOfLevels()) {
                    //Credits credits = new Credits(player);
                    //game.setState(new CreditsState(credits, game.getSpriteLoader()));
                    //if (getModel().isAtEndPosition())
                    game.setState(new MainMenuState(new MainMenu()));

                } else {
                    SceneLoader sceneLoader = new SceneLoader((getModel().getSceneID() + 1));
                    Scene newScene = sceneLoader.createScene(new Knight(0, 0, knight.getHP(), 1, 10));
                    game.setState(new GameState(newScene));
                }
            }
            else{
                getModel().collectOrbs(getModel().getEnergyOrbs());
                getModel().collectOrbs(getModel().getHealthOrbs());
                getModel().collectOrbs(getModel().getSpeedOrbs());

                getModel().collideMonsters(getModel().getGhostMonsters());
                getModel().collideMonsters(getModel().getPurpleMonsters());
                getModel().collideMonsters(getModel().getSwordMonsters());

                particleController.move(game, action,time);
                enemieController.move(game,action,time);
            }
        }
    }


}
