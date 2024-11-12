package HollowKnight.view.states;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Element;
import HollowKnight.model.game.elements.Particle.Particle;
import HollowKnight.model.game.scene.Scene;
import HollowKnight.view.elements.ElementViewer;
import HollowKnight.view.elements.KnightViewer;
import HollowKnight.view.elements.ParticleViewer;
import HollowKnight.view.elements.TileViewer;
import HollowKnight.view.sprites.Sprite;
import HollowKnight.view.sprites.SpriteLoader;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;
import java.util.List;

public class GameViewer extends ScreenViewer<Scene> {
    private final ParticleViewer particleViewer;
    private final KnightViewer knightViewer;
    public GameViewer(Scene model) {
        super(model);
        this.particleViewer = new ParticleViewer();
        this.knightViewer = new KnightViewer();
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.cls();
        gradientLoader(gui);

        //drawElements(gui, getModel().getTiles(), new TileViewer());
        drawElement(gui, getModel().getPlayer(), this.knightViewer);
        drawElements(gui, getModel().getParticles(), this.particleViewer);
        gui.flush();
    }


    private void setBackgroundColor(GUI gui, TextColor.RGB color) {
        // BACKGROUND (NOT SCENE RELATED)
        for (int w = 0; w < 160; w++) {
            for (int h = 0; h < 90; h++) {
                gui.drawPixel(w, h, color);
            }
        }


    }

    private  <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) throws IOException {
        for (T element : elements)
            drawElement(gui, element, viewer);
    }
    private  <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) throws IOException {
        viewer.draw(element, gui);
    }
    private void gradientLoader(GUI gui) {
        int width = getModel().getWidth();
        int height = getModel().getHeight();

        // Define colors
        TextColor.RGB color1 = new TextColor.RGB(34, 87, 122);
        TextColor.RGB color2 = new TextColor.RGB(128, 237, 153);

        // Outer background
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                gui.drawPixel(w, h, new TextColor.RGB(28, 28, 28));
            }
        }

        // Inner rectangle with gradient (proportional dimensions)
        int innerPadding = 5;
        int innerWidthStart = innerPadding;
        int innerWidthEnd = width - innerPadding;
        int innerHeightStart = innerPadding;
        int innerHeightEnd = height - innerPadding;

        for (int w = innerWidthStart; w < innerWidthEnd; w++) {
            for (int h = innerHeightStart; h < innerHeightEnd; h++) {
                gui.drawPixel(w, h, new TextColor.RGB(55, 55, 55));
            }
        }

        // Top and bottom gradients
        for (int w = 0; w < width; w++) {
            TextColor.RGB current = new TextColor.RGB(
                    color1.getRed() + (color2.getRed() - color1.getRed()) * w / width,
                    color1.getGreen() + (color2.getGreen() - color1.getGreen()) * w / width,
                    color1.getBlue() + (color2.getBlue() - color1.getBlue()) * w / width
            );
            TextColor.RGB opposite = new TextColor.RGB(
                    color2.getRed() + (color1.getRed() - color2.getRed()) * w / width,
                    color2.getGreen() + (color1.getGreen() - color2.getGreen()) * w / width,
                    color2.getBlue() + (color1.getBlue() - color2.getBlue()) * w / width
            );
            gui.drawPixel(w, 0, current);
            gui.drawPixel(w, height - 1, opposite);
        }

        // Inner top and bottom gradients
        for (int w = innerWidthStart; w < innerWidthEnd; w++) {
            TextColor.RGB current = new TextColor.RGB(
                    color1.getRed() + (color2.getRed() - color1.getRed()) * (w - innerWidthStart) / (innerWidthEnd - innerWidthStart),
                    color1.getGreen() + (color2.getGreen() - color1.getGreen()) * (w - innerWidthStart) / (innerWidthEnd - innerWidthStart),
                    color1.getBlue() + (color2.getBlue() - color1.getBlue()) * (w - innerWidthStart) / (innerWidthEnd - innerWidthStart)
            );
            TextColor.RGB opposite = new TextColor.RGB(
                    color2.getRed() + (color1.getRed() - color2.getRed()) * (w - innerWidthStart) / (innerWidthEnd - innerWidthStart),
                    color2.getGreen() + (color1.getGreen() - color2.getGreen()) * (w - innerWidthStart) / (innerWidthEnd - innerWidthStart),
                    color2.getBlue() + (color1.getBlue() - color2.getBlue()) * (w - innerWidthStart) / (innerWidthEnd - innerWidthStart)
            );
            gui.drawPixel(w, innerHeightStart, opposite);
            gui.drawPixel(w, innerHeightEnd - 1, current);
        }

        // Left and right gradients
        for (int h = 1; h < height - 1; h++) {
            TextColor.RGB current = new TextColor.RGB(
                    color1.getRed() + (color2.getRed() - color1.getRed()) * h / height,
                    color1.getGreen() + (color2.getGreen() - color1.getGreen()) * h / height,
                    color1.getBlue() + (color2.getBlue() - color1.getBlue()) * h / height
            );
            TextColor.RGB opposite = new TextColor.RGB(
                    color2.getRed() + (color1.getRed() - color2.getRed()) * h / height,
                    color2.getGreen() + (color1.getGreen() - color2.getGreen()) * h / height,
                    color2.getBlue() + (color1.getBlue() - color2.getBlue()) * h / height
            );
            gui.drawPixel(0, h, current);
            gui.drawPixel(width - 1, h, opposite);
        }

        // Inner left and right gradients
        for (int h = innerHeightStart; h < innerHeightEnd; h++) {
            TextColor.RGB current = new TextColor.RGB(
                    color1.getRed() + (color2.getRed() - color1.getRed()) * (h - innerHeightStart) / (innerHeightEnd - innerHeightStart),
                    color1.getGreen() + (color2.getGreen() - color1.getGreen()) * (h - innerHeightStart) / (innerHeightEnd - innerHeightStart),
                    color1.getBlue() + (color2.getBlue() - color1.getBlue()) * (h - innerHeightStart) / (innerHeightEnd - innerHeightStart)
            );
            TextColor.RGB opposite = new TextColor.RGB(
                    color2.getRed() + (color1.getRed() - color2.getRed()) * (h - innerHeightStart) / (innerHeightEnd - innerHeightStart),
                    color2.getGreen() + (color1.getGreen() - color2.getGreen()) * (h - innerHeightStart) / (innerHeightEnd - innerHeightStart),
                    color2.getBlue() + (color1.getBlue() - color2.getBlue()) * (h - innerHeightStart) / (innerHeightEnd - innerHeightStart)
            );
            gui.drawPixel(innerWidthStart, h, opposite);
            gui.drawPixel(innerWidthEnd - 1, h, current);
        }
    }

}