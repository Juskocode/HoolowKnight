package HollowKnight.view.elements;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Particle.Particle;
import HollowKnight.view.sprites.Sprite;
import HollowKnight.view.sprites.SpriteLoader;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;

public class ParticleViewer implements ElementViewer<Particle>{
    @Override
    public void draw(Particle model, GUI gui) throws IOException {
        gui.drawPixel(model.getPosition().x(), model.getPosition().y(), model.getColor());
    }
}
