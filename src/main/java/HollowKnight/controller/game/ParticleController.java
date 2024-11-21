package HollowKnight.controller.game;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.model.Position;
import HollowKnight.model.game.elements.Particle.Particle;
import HollowKnight.model.game.scene.Scene;

import java.io.IOException;
import java.util.Random;

public class ParticleController extends Controller<Scene>{

    public ParticleController(Scene model) {
        super(model);
    }

    @Override
    public void move(Game game, GUI.ACTION action, long time) throws IOException {
        for(Particle particle: getModel().getParticles()){
            particle.setPosition(particle.moveParticle(getModel(), time));
        }
        for(Particle particle: getModel().getJumpParticles()){
            particle.setPosition(particle.moveParticle(getModel(), time));
        }
    }
}
