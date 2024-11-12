package HollowKnight.state;

import HollowKnight.controller.Controller;
import HollowKnight.controller.game.ParticleController;
import HollowKnight.controller.game.PlayerController;
import HollowKnight.controller.game.SceneController;
import HollowKnight.model.game.scene.Scene;
import HollowKnight.view.states.GameViewer;
import HollowKnight.view.states.ScreenViewer;

public class GameState extends State<Scene> {
    public GameState(Scene model) {
        super(model);
    }

    @Override
    protected Controller<Scene> getController() {
        return new SceneController(getModel(), new PlayerController(getModel()), new ParticleController(getModel()));
    }

    @Override
    protected ScreenViewer<Scene> getScreenViewer() {
        return new GameViewer(getModel());
    }
}
