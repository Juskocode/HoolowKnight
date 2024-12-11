package HollowKnight.state;

import HollowKnight.controller.Controller;
import HollowKnight.controller.game.EnemieController;
import HollowKnight.controller.game.ParticleController;
import HollowKnight.controller.game.PlayerController;
import HollowKnight.controller.game.SceneController;
import HollowKnight.model.game.scene.Scene;
import HollowKnight.view.states.GameViewer;
import HollowKnight.view.states.ScreenViewer;

import java.io.IOException;

public class GameState extends State<Scene> {
    public GameState(Scene model) throws IOException {
        super(model);
    }

    @Override
    protected Controller<Scene> getController() {
        return new SceneController(getModel(), new PlayerController(getModel()),
                new ParticleController(getModel()), new EnemieController(getModel()));
    }

    @Override
    protected ScreenViewer<Scene> getScreenViewer() throws IOException {
        return new GameViewer(getModel());
    }
}
