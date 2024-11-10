package HollowKnight.controller.game;

import HollowKnight.Game;
import HollowKnight.gui.GUI;
import HollowKnight.model.Position;
import HollowKnight.model.game.elements.map.Scene;

public class PlayerController extends GameController {
    public PlayerController(Scene scene) {
        super(scene);
    }

    public void movePlayerLeft() {
        movePlayer(this.getModel().getPlayer().getPosition().getLeft());
    }

    public void movePlayerRight() {
        movePlayer(this.getModel().getPlayer().getPosition().getRight());
    }

    public void movePlayerUp() {
        movePlayer(this.getModel().getPlayer().getPosition().getUp());
    }

    public void movePlayerDown() {
        movePlayer(this.getModel().getPlayer().getPosition().getDown());
    }

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
