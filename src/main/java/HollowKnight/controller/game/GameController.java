package HollowKnight.controller.game;

import HollowKnight.controller.Controller;
import HollowKnight.model.game.scene.Scene;

public abstract class GameController extends Controller<Scene> {
    public GameController(Scene scene) {super(scene);}
}
