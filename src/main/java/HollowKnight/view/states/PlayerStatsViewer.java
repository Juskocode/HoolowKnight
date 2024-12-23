package HollowKnight.view.states;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.scene.Scene;
import HollowKnight.view.text.TextViewer;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;

public class PlayerStatsViewer{
    public static void drawPlayerStats(GUI gui, long time, Scene scene, TextViewer textViewer) throws IOException {
        // Fetch the player details
        var player = scene.getPlayer();

        // Format position and velocity strings
        String pos = formatVector("pos", player.getPosition().x(), player.getPosition().y());
        String vel = formatVector("vel", player.getVelocity().x(), player.getVelocity().y());

        // Fetch the player's state class name
        String state = "state " + player.getState().getClass().getSimpleName();

        // Fetch player collision
        String colides = "ground " + player.isOnGround();

        // Fetch player jumpBoost
        String jumpBoost = "jumpBoost " + String.format("%.2f", player.getJumpBoost());

        String hp = "hp " + player.getHP();

        String fps = "fps " + gui.getFPS();

        String orbs = "Orbs " + player.getOrbs();

        // Define a common color for all text
        TextColor.RGB color = new TextColor.RGB(0, 225, 75);

        // Draw each piece of information
        //this.textViewer.draw(pos, 4, 0, color, gui);
        textViewer.draw(state, 4, 8, color, gui);
        textViewer.draw(hp, 4, 16, color, gui);
        textViewer.draw(fps, 4, 24, color, gui);
        textViewer.draw(orbs, 4, 32, color, gui);

    }


    // Helper method to format position or velocity with truncated decimal values
    static String formatVector(String label, double x, double y) {
        return String.format("%s %.2fx%.2fy", label, x, y);
    }
}
