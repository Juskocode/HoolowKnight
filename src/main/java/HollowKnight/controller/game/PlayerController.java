package HollowKnight.controller.game;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Knight.IdleState;
import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.model.game.elements.Knight.RespawnState;
import HollowKnight.model.game.scene.Scene;

public class PlayerController extends Controller<Scene> {
    public PlayerController(Scene scene) {
        super(scene);
    }

    @Override
    public void move(Game game, GUI.ACTION action, long time) {
        Knight knight = getModel().getPlayer();

        switch (action) {
            case LEFT:
                knight.setVelocity(knight.moveLeft());
                knight.setFacingRight(false);
                break;
            case RIGHT:
                knight.setVelocity(knight.moveRight());
                knight.setFacingRight(true);
                break;
            case JUMP:
                /*
                if (gui.isJumpHeld()) { // Process jump only if the key is being held

                    Vector velocity = knight.jump();
                    long duration = gui.getDuration();

                    // Clamp duration between 80 and 250
                    if (duration > 250) duration = 250;
                    else if (duration < 80) duration = 80;

                    double jumpMultiplier = duration / 250.0;
                    System.out.println("JumpBoost " + jumpMultiplier);

                    knight.setVelocity(velocity);
                }*/
                knight.setVelocity(knight.jump());
                break;
            case KILL:
                knight.setState(new RespawnState(knight, 30));
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
