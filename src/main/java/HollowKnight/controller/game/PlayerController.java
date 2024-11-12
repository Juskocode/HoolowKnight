package HollowKnight.controller.game;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.model.Position;
import HollowKnight.model.game.scene.Scene;

public class PlayerController extends Controller<Scene> {
    public PlayerController(Scene scene) {
        super(scene);
    }

    private void movePlayerLeft() {
        movePlayer(this.getModel().getPlayer().getPosition().getLeft());
    }

    private void movePlayerRight() {
        movePlayer(this.getModel().getPlayer().getPosition().getRight());
    }

    private void movePlayerUp() {
        movePlayer(this.getModel().getPlayer().getPosition().getUp());
    } // nao testar

    private void movePlayerDown() {
        movePlayer(this.getModel().getPlayer().getPosition().getDown());
    } //não testar

    private void movePlayer(Position position) {
        this.getModel().getPlayer().setPosition(position);
    }

    @Override
    public void move(Game game, GUI.ACTION action, long time) {
        switch (action){
            case UP:
                movePlayerUp();
                break;
            case DOWN:
                movePlayerDown();
                break;
            case LEFT:
                movePlayerLeft();
                break;
            case RIGHT:
                movePlayerRight();
                break;
            default:
        }
    }
}
