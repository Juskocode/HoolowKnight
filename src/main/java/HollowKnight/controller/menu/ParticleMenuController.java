package HollowKnight.controller.menu;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.model.Position;
import HollowKnight.model.game.scene.Scene;
import HollowKnight.model.menu.Menu;
import HollowKnight.model.menu.Particle;

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
        int new_x = (int)particle.getPosition().x() + 2;
        int new_y = (int)particle.getPosition().y() + 2;
        if (new_x < 0)
            new_x = 160;
        else if (new_x >= 160) {
            new_x = 1;
        }
        if (new_y >= 80)
            new_y = 0;
        return new Position(new_x,new_y);
    }
}
