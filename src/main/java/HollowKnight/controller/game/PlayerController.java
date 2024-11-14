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

    private void movePlayerDown(){movePlayer(this.getModel().getPlayer().getPosition().getDown());}
    private void movePlayer(Position position) {
        // Set player hitbox relative to the center position
        Position topRight = new Position(position.x() + 4, position.y() - 4);
        Position topLeft = new Position(position.x() - 4, position.y() - 4);
        Position bottomRight = new Position(position.x() + 4, position.y() + 3);
        Position bottomLeft = new Position(position.x() - 4, position.y() + 3);

        // Check if all corners of the hitbox are within empty space
        if (this.getModel().isEmpty(position) &&
                this.getModel().isEmpty(topLeft) &&
                this.getModel().isEmpty(topRight) &&
                this.getModel().isEmpty(bottomLeft) &&
                this.getModel().isEmpty(bottomRight)) {
            // Move the player to the new position
            this.getModel().getPlayer().setPosition(position);
        }
    }


    @Override
    public void move(Game game, GUI.ACTION action, long time) {
        switch (action){
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
