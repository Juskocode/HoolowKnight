package HollowKnight.controller.game;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.model.Position;
import HollowKnight.model.Vector;
import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.model.game.scene.Scene;

public class PlayerController extends Controller<Scene> {
    public PlayerController(Scene scene) {
        super(scene);
    }
  
    @Override
    public void move(Game game, GUI.ACTION action, long time) {
        Knight knight = getModel().getPlayer();

        switch (action){
            case LEFT:
                knight.setVelocity(knight.moveLeft());
                break;
            case RIGHT:
                knight.setVelocity(knight.moveRight());
                break;
            default:
                getModel().getPlayer().setVelocity(knight.updateVelocity());
        }
        knight.setPosition(knight.updatePosition());
        knight.setScene(getModel());
    }
}
