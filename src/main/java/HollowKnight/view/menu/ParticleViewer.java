package HollowKnight.view.menu;

import HollowKnight.gui.GUI;
import HollowKnight.model.menu.Particle;
import HollowKnight.view.elements.ElementViewer;

import java.io.IOException;

public class ParticleViewer implements ElementViewer<Particle> {
    @Override
    public void draw(Particle model, GUI gui, long time) throws IOException {
        gui.drawPixel((int)model.getPosition().x(), (int)model.getPosition().y(), model.getColor());
    }
}
