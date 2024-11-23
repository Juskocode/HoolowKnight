package HollowKnight.controller.game;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Particle.Particle;
import HollowKnight.model.game.scene.Scene;

import java.io.IOException;

public class ParticleController extends Controller<Scene>{

    public ParticleController(Scene scene) {
        super(scene);
    }

    @Override
    public void move(Game game, GUI.ACTION action, long time) throws IOException {
        for(Particle particle: getModel().getParticles()){
            particle.setScene(getModel());
            System.out.println("rain " + particle.getScene().getClass().getSimpleName());
            particle.setPosition(particle.moveParticle(getModel(), time));

        }
        for(Particle particle: getModel().getDoubleJumpParticles()){
            particle.setScene(getModel());
            System.out.println("Double jump " + particle.getScene().getClass().getSimpleName());
            particle.setPosition(particle.moveParticle(getModel(), time));

        }
        for(Particle particle: getModel().getJumpParticles()){
            particle.setScene(getModel());
            System.out.println("jump " + particle.getScene().getClass().getSimpleName());
            particle.setPosition(particle.moveParticle(getModel(), time));
        }
        for(Particle particle: getModel().getRespawnParticles()){
            particle.setScene(getModel());
            System.out.println("blood " + particle.getScene().getClass().getSimpleName());
            particle.setPosition(particle.moveParticle(getModel(), time));
        }
    }
}
