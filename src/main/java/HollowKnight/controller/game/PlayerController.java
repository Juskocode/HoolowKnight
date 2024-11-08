package HollowKnight.controller.game;

import HollowKnight.Game;
import HollowKnight.gui.GUI;
import HollowKnight.model.Position;

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

    private void movePlayer(Posit   ion position) {
        this.getModel().getPlayer().setPosition(position);
    }

    @Override
    public void move(Game game, GUI.ACTION action, long time) {
        switch (action){
            case GUI.ACTION.UP:
                movePlayerUp();
                break;
            case GUI.ACTION.DOWN:
                movePlayerDown();
                break;
            case GUI.ACTION.LEFT:
                movePlayerLeft();
                break;
            case GUI.ACTION.RIGHT:
                movePlayerRight();
                break;
        }
    }
}
