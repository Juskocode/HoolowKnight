package HollowKnight.view.states;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Element;
import HollowKnight.model.game.scene.Scene;
import HollowKnight.view.elements.*;
import HollowKnight.view.elements.KnightViewer;
import HollowKnight.view.elements.ParticleViewer;
import HollowKnight.view.elements.monsters.MinhoteMonsterViewer;
import HollowKnight.view.elements.monsters.PurpleMonsterViewer;
import HollowKnight.view.elements.monsters.SwordMonsterViewer;
import HollowKnight.view.elements.rocks.BigRockViewer;
import HollowKnight.view.elements.rocks.SmallRockViewer;
import HollowKnight.view.elements.trees.MediumTreeViewer;
import HollowKnight.view.elements.trees.SmallTreeViewer;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;
import java.util.List;

public class GameViewer extends ScreenViewer<Scene> {
    private final ParticleViewer particleViewer;
    private final KnightViewer knightViewer;

    private final MediumTreeViewer mediumTreeViewer;
    private final SmallTreeViewer smallTreeViewer;

    private final BigRockViewer bigRockViewer;
    private final SmallRockViewer smallRockViewer;

    private final SwordMonsterViewer swordMonsterViewer;
    private final PurpleMonsterViewer purpleMonsterViewer;
    private final MinhoteMonsterViewer minhoteMonsterViewer;


    public GameViewer(Scene model) throws IOException {
        super(model);
        this.particleViewer = new ParticleViewer();

        this.knightViewer = new KnightViewer();

        this.mediumTreeViewer = new MediumTreeViewer();
        this.smallTreeViewer = new SmallTreeViewer();

        this.bigRockViewer = new BigRockViewer();
        this.smallRockViewer = new SmallRockViewer();

        this.swordMonsterViewer = new SwordMonsterViewer();
        this.purpleMonsterViewer = new PurpleMonsterViewer();
        this.minhoteMonsterViewer = new MinhoteMonsterViewer();
    }

    @Override
    public void draw(GUI gui, long time) throws IOException {
        gui.cls();
        gradientLoader(gui);
        drawElements(gui, getModel().getTiles(), new TileViewer(), time);
        drawElement(gui, getModel().getPlayer(), this.knightViewer, time);
        drawElements(gui, getModel().getParticles(), this.particleViewer, time);

        drawElements(gui, getModel().getMediumTrees(), this.mediumTreeViewer, time);
        drawElements(gui, getModel().getSmallTrees(), this.smallTreeViewer, time);

        drawElements(gui, getModel().getBigRocks(), this.bigRockViewer, time);
        drawElements(gui, getModel().getSmallRocks(), this.smallRockViewer, time);

        drawElements(gui, getModel().getSwordMonstersEnemies(), this.swordMonsterViewer, time);
        drawElements(gui, getModel().getPurpleMonsters(), this.purpleMonsterViewer, time);
        drawElements(gui, getModel().getMinhoteMonsters(), this.minhoteMonsterViewer, time);


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

    private  <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer, long time) throws IOException {
        for (T element : elements)
            drawElement(gui, element, viewer, time);
    }

    private  <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer, long time) throws IOException {
        viewer.draw(element, gui, time);
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
        int innerPadding = 4;
        int innerWidthStart = innerPadding - 1;
        int innerWidthEnd = width - innerPadding + 1;
        int innerHeightStart = innerPadding;
        int innerHeightEnd = height - innerPadding;

        for (int w = innerWidthStart; w < innerWidthEnd; w++) {
            for (int h = innerHeightStart; h < innerHeightEnd; h++) {
                gui.drawPixel(w, h, new TextColor.RGB(12, 12, 12));
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