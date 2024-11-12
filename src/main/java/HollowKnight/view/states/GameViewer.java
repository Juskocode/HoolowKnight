package HollowKnight.view.states;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Element;
import HollowKnight.model.game.scene.Scene;
import HollowKnight.view.elements.ElementViewer;
import HollowKnight.view.elements.KnightViewer;
import HollowKnight.view.elements.TileViewer;
import HollowKnight.view.sprites.Sprite;
import HollowKnight.view.sprites.SpriteLoader;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;
import java.util.List;

public class GameViewer extends ScreenViewer<Scene> {
    public GameViewer(Scene model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.cls();
        gradientLoader(gui);
        //drawElements(gui, getModel().getTiles(), new TileViewer());
        drawElement(gui, getModel().getPlayer(), new KnightViewer());

        gui.flush();
    }
    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) throws IOException {
        viewer.draw(element, gui);
    }
    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) throws IOException {
        for (T element : elements)
            drawElement(gui, element, viewer);
    }
    private void setBackgroundColor(GUI gui, TextColor.RGB color) {
        // BACKGROUND (NOT SCENE RELATED)
        for (int w = 0; w < 160; w++) {
            for (int h = 0; h < 90; h++) {
                gui.drawPixel(w, h, color);
            }
        }
    }
    private void gradientLoader(GUI gui){
        // COLOR 1: 255  205  178
        // COLOR 2: 109  104  117
        TextColor.RGB color1 = new TextColor.RGB(34, 87, 122);
        TextColor.RGB color2 = new TextColor.RGB(128, 237, 153);

        // Outer background
        for (int w = 0; w < 80; w++) {
            for (int h = 0; h < 40; h++) {
                gui.drawPixel(w, h, new TextColor.RGB(28, 28, 28));
            }
        }

        // Inner rectangle with gradient
        for (int w = 5; w < 75; w++) {
            for (int h = 5; h < 35; h++) {
                gui.drawPixel(w, h, new TextColor.RGB(55, 55, 55));
            }
        }

        // Top and bottom gradients
        for (int w = 0; w < 80; w++) {
            TextColor.RGB current = new TextColor.RGB(
                    color1.getRed() + (color2.getRed() - color1.getRed()) * w / 80,
                    color1.getGreen() + (color2.getGreen() - color1.getGreen()) * w / 80,
                    color1.getBlue() + (color2.getBlue() - color1.getBlue()) * w / 80
            );
            TextColor.RGB opposite = new TextColor.RGB(
                    color2.getRed() + (color1.getRed() - color2.getRed()) * w / 80,
                    color2.getGreen() + (color1.getGreen() - color2.getGreen()) * w / 80,
                    color2.getBlue() + (color1.getBlue() - color2.getBlue()) * w / 80
            );
            gui.drawPixel(w, 0, current);
            gui.drawPixel(w, 39, opposite);
        }

        for (int w = 5; w < 75; w++) {
            TextColor.RGB current = new TextColor.RGB(
                    color1.getRed() + (color2.getRed() - color1.getRed()) * (w - 5) / 70,
                    color1.getGreen() + (color2.getGreen() - color1.getGreen()) * (w - 5) / 70,
                    color1.getBlue() + (color2.getBlue() - color1.getBlue()) * (w - 5) / 70
            );
            TextColor.RGB opposite = new TextColor.RGB(
                    color2.getRed() + (color1.getRed() - color2.getRed()) * (w - 5) / 70,
                    color2.getGreen() + (color1.getGreen() - color2.getGreen()) * (w - 5) / 70,
                    color2.getBlue() + (color1.getBlue() - color2.getBlue()) * (w - 5) / 70
            );
            gui.drawPixel(w, 5, opposite);
            gui.drawPixel(w, 34, current);
        }

        // Left and right gradients
        for (int h = 1; h < 39; h++) {
            TextColor.RGB current = new TextColor.RGB(
                    color1.getRed() + (color2.getRed() - color1.getRed()) * h / 40,
                    color1.getGreen() + (color2.getGreen() - color1.getGreen()) * h / 40,
                    color1.getBlue() + (color2.getBlue() - color1.getBlue()) * h / 40
            );
            TextColor.RGB opposite = new TextColor.RGB(
                    color2.getRed() + (color1.getRed() - color2.getRed()) * h / 40,
                    color2.getGreen() + (color1.getGreen() - color2.getGreen()) * h / 40,
                    color2.getBlue() + (color1.getBlue() - color2.getBlue()) * h / 40
            );
            gui.drawPixel(0, h, current);
            gui.drawPixel(79, h, opposite);
        }

        for (int h = 5; h < 34; h++) {
            TextColor.RGB current = new TextColor.RGB(
                    color1.getRed() + (color2.getRed() - color1.getRed()) * (h - 5) / 30,
                    color1.getGreen() + (color2.getGreen() - color1.getGreen()) * (h - 5) / 30,
                    color1.getBlue() + (color2.getBlue() - color1.getBlue()) * (h - 5) / 30
            );
            TextColor.RGB opposite = new TextColor.RGB(
                    color2.getRed() + (color1.getRed() - color2.getRed()) * (h - 5) / 30,
                    color2.getGreen() + (color1.getGreen() - color2.getGreen()) * (h - 5) / 30,
                    color2.getBlue() + (color1.getBlue() - color2.getBlue()) * (h - 5) / 30
            );
            gui.drawPixel(5, h, opposite);
            gui.drawPixel(74, h, current);
        }

    }
}