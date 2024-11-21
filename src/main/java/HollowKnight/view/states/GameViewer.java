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
import HollowKnight.view.text.GameTextViewer;
import HollowKnight.view.text.TextViewer;
import com.googlecode.lanterna.TextColor;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class GameViewer extends ScreenViewer<Scene> {

    private final TextViewer textViewer;


    private final ParticleViewer particleViewer;
    private final KnightViewer knightViewer;
    private final TileViewer tileViewer;

    private final MediumTreeViewer mediumTreeViewer;
    private final SmallTreeViewer smallTreeViewer;

    private final BigRockViewer bigRockViewer;
    private final SmallRockViewer smallRockViewer;

    private final SwordMonsterViewer swordMonsterViewer;
    private final PurpleMonsterViewer purpleMonsterViewer;
    private final MinhoteMonsterViewer minhoteMonsterViewer;


    public GameViewer(Scene model) throws IOException {

        super(model);

        this.textViewer = new GameTextViewer();

        this.particleViewer = new ParticleViewer();

        this.knightViewer = new KnightViewer();

        this.tileViewer = new TileViewer();

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

        dynamicGradientBackground(gui, time);
        drawElements(gui, getModel().getTiles(), this.tileViewer, time);
        drawElement(gui, getModel().getPlayer(), this.knightViewer, time);
        drawElements(gui, getModel().getParticles(), this.particleViewer, time);
        drawElements(gui, getModel().getJumpParticles(), this.particleViewer, time);


        drawElements(gui, getModel().getMediumTrees(), this.mediumTreeViewer, time);
        drawElements(gui, getModel().getSmallTrees(), this.smallTreeViewer, time);

        drawElements(gui, getModel().getBigRocks(), this.bigRockViewer, time);
        drawElements(gui, getModel().getSmallRocks(), this.smallRockViewer, time);

        drawElements(gui, getModel().getSwordMonsters(), this.swordMonsterViewer, time);
        drawElements(gui, getModel().getPurpleMonsters(), this.purpleMonsterViewer, time);
        drawElements(gui, getModel().getMinhoteMonsters(), this.minhoteMonsterViewer, time);

        drawPlayerStats(gui, time);

        gui.flush();
    }


    private  <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer, long time) throws IOException {
        for (T element : elements)
            drawElement(gui, element, viewer, time);
    }

    private  <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer, long time) throws IOException {
        viewer.draw(element, gui, time);
    }

    private <T extends Element> void drawElements(GUI gui, T[][] elements, ElementViewer<T> viewer, long frameCount) throws IOException {
        for (T[] elementLine : elements) {
            for (T element : elementLine) {
                if (element != null)
                    drawElement(gui, element, viewer, frameCount);
            }
        }
    }


    private void dynamicGradientBackground(GUI gui, long time) {
        int width = getModel().getWidth();
        int height = getModel().getHeight();

        double changeRate = 0.04;
        // Calculate dynamic colors based on time
        int baseRed1 = (int) (128 + 127 * Math.sin(time * changeRate));
        int baseGreen1 = (int) (128 + 127 * Math.sin(time * changeRate + Math.PI / 3));
        int baseBlue1 = (int) (128 + 127 * Math.sin(time * changeRate + 2 * Math.PI / 3));

        int baseRed2 = (int) (128 + 127 * Math.sin(time * changeRate + Math.PI));
        int baseGreen2 = (int) (128 + 127 * Math.sin(time * changeRate + Math.PI + Math.PI / 3));
        int baseBlue2 = (int) (128 + 127 * Math.sin(time * changeRate + Math.PI + 2 * Math.PI / 3));

        TextColor.RGB color1 = new TextColor.RGB(baseRed1, baseGreen1, baseBlue1);
        TextColor.RGB color2 = new TextColor.RGB(baseRed2, baseGreen2, baseBlue2);

        // Draw dynamic gradient background
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                double interpolationX = (double) w / (width - 1);
                double interpolationY = (double) h / (height - 1);

                int red = (int) ((1 - interpolationX) * color1.getRed() + interpolationX * color2.getRed());
                int green = (int) ((1 - interpolationY) * color1.getGreen() + interpolationY * color2.getGreen());
                int blue = (int) ((1 - interpolationX) * color1.getBlue() + interpolationX * color2.getBlue());

                gui.drawPixel(w, h, new TextColor.RGB(red, green, blue));
            }
        }
    }

    private void drawPlayerStats(GUI gui, long time) throws IOException {
        // Fetch the player details
        var player = getModel().getPlayer();

        // Format position and velocity strings
        String pos = formatVector("pos", player.getPosition().x(), player.getPosition().y());
        String vel = formatVector("vel", player.getVelocity().x(), player.getVelocity().y());

        // Fetch the player's state class name
        String state = "state " + player.getState().getClass().getSimpleName();

        // Fetch player collision
        String colides = "ground " + player.isOnGround();

        // Fetch player jumpBoost
        String jumpBoost = "jumpBoost " + String.format("%.2f", player.getJumpBoost());

        // Define a common color for all text
        TextColor.RGB color = new TextColor.RGB(0, 25, 25);

        // Draw each piece of information
        this.textViewer.draw(pos, 4, 0, color, gui);
        this.textViewer.draw(vel, 4, 8, color, gui);
        this.textViewer.draw(state, 4, 16, color, gui);
        this.textViewer.draw(colides, 4, 24, color, gui);
        this.textViewer.draw(jumpBoost, 4, 32, color, gui);


    }


    // Helper method to format position or velocity with truncated decimal values
    String formatVector(String label, double x, double y) {
        return String.format("%s %.2fx%.2fy", label, x, y);
    }
}