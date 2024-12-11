package HollowKnight.view.elements;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Particle.Particle;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;

public class ParticleViewer implements ElementViewer<Particle>{
    @Override
    public void draw(Particle model, GUI gui, long time, int offsetX, int offsetY) throws IOException {
        // Get the particle's base color
        TextColor.RGB baseColor = model.getColor();
        double opacity = model.getOpacity();

        // Ensure opacity stays within valid bounds [0, 1]
        opacity = Math.max(0, Math.min(1, opacity));

        // Calculate faded RGB values
        int red = (int) (baseColor.getRed() * opacity);
        int green = (int) (baseColor.getGreen() * opacity);
        int blue = (int) (baseColor.getBlue() * opacity);

        // Create the faded color
        TextColor.RGB fadedColor = new TextColor.RGB(red, green, blue);

        // Draw the particle with the faded color
        gui.drawPixel(
                (int) model.getPosition().x(),
                (int) model.getPosition().y(),
                fadedColor
        );
    }
}
