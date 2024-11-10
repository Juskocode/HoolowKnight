package HollowKnight.controller.game;

import HollowKnight.controller.Controller;
import HollowKnight.model.game.elements.map.Scene;

public abstract class GameController extends Controller<Scene> {
    public GameController(Scene scene) {super(scene);}
}
