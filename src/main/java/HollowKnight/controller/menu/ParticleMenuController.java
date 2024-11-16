package HollowKnight.controller.menu;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.model.Position;
import HollowKnight.model.game.scene.Scene;
import HollowKnight.model.menu.Menu;
import HollowKnight.model.menu.Particle;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;
import java.util.Random;

public class ParticleMenuController extends Controller<Menu> {

    public ParticleMenuController(Menu model) {
        super(model);
    }
    @Override
    public void move(Game game, GUI.ACTION action, long time) throws IOException {
        for(Particle particle : getModel().getParticles())
            particle.setPosition(ParticleMove(particle));
    }

    public Position ParticleMove(Particle particle) {
        int new_x = (int) particle.getPosition().x() + 2;
        int new_y = (int) particle.getPosition().y() + 2;

        // Wrap around logic for X and Y positions
        if (new_x < 0) new_x = 160;
        else if (new_x >= 160) new_x = 1;
        if (new_y >= 80) new_y = 0;

        // Calculate gradient color based on X position
        int screenWidth = 160; // Assuming the screen width is 160
        int greenIntensity = (int) ((1 - (new_x / (float) screenWidth)) * 255); // More green on the left
        int blueIntensity = (int) ((new_x / (float) screenWidth) * 255);       // More blue on the right

        // Update the particle's color
        particle.setColor(new TextColor.RGB(0, blueIntensity, greenIntensity));

        return new Position(new_x, new_y);
    }

}
