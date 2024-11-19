package HollowKnight.controller.game;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.model.Position;
import HollowKnight.model.Vector;
import HollowKnight.model.game.elements.Knight.IdleState;
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
                knight.setFacingRight(false);
                break;
            case RIGHT:
                knight.setVelocity(knight.moveRight());
                knight.setFacingRight(true);
                break;
            case JUMP:
                knight.setVelocity(knight.jump());
                break;
            default:
                knight.setVelocity(knight.updateVelocity());
        }

        knight.setPosition(knight.updatePosition());
        knight.setScene(getModel());
        knight.setState(knight.getNextState());

        if (knight.getState() == null) {
            knight.setState(new IdleState(knight));
        }
    }
}
