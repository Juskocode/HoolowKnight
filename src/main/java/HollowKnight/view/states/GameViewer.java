package HollowKnight.view.states;

import HollowKnight.gui.BufferedImageGUI;
import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Element;
import HollowKnight.model.game.scene.Scene;
import HollowKnight.view.elements.*;
import HollowKnight.view.elements.knight.KnightViewer;
import HollowKnight.view.elements.ParticleViewer;
import HollowKnight.view.elements.collectables.EnergyOrbViewer;
import HollowKnight.view.elements.collectables.HealthOrbViewer;
import HollowKnight.view.elements.collectables.SpeedOrbViewer;
import HollowKnight.view.elements.monsters.MinhoteMonsterViewer;
import HollowKnight.view.elements.monsters.PurpleMonsterViewer;
import HollowKnight.view.elements.monsters.SwordMonsterViewer;
import HollowKnight.view.elements.rocks.BigRockViewer;
import HollowKnight.view.elements.rocks.SmallRockViewer;
import HollowKnight.view.elements.tile.TileViewer;
import HollowKnight.view.elements.trees.MediumTreeViewer;
import HollowKnight.view.elements.trees.SmallTreeViewer;
import HollowKnight.view.text.GameTextViewer;
import HollowKnight.view.text.TextViewer;
import com.googlecode.lanterna.TextColor;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class GameViewer extends ScreenViewer<Scene> {

    private final TextViewer textViewer;

    private static final int CAMERA_WIDTH = 220;
    private static final int CAMERA_HEIGHT = 100;
    private final EnergyOrbViewer energyOrbViewer;
    private final HealthOrbViewer healthOrbViewer;
    private final SpeedOrbViewer speedOrbViewer;

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

    private BufferedImage staticLayer;
    private boolean staticLayerUpdated;
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
        this.energyOrbViewer = new EnergyOrbViewer();
        this.healthOrbViewer = new HealthOrbViewer();
        this.speedOrbViewer = new SpeedOrbViewer();

        // Initialize the static layer and set it to be updated initially
        this.staticLayer = null;
        this.staticLayerUpdated = true;
    }

    @Override
    public void draw(GUI gui, long time) throws IOException {
        gui.cls();
        // Get camera bounds
        int[] cameraBounds = calculateCameraBounds();
        dynamicGradientBackground(gui, time);

        // Draw static layer (tiles) clipped to the camera
        if (staticLayerUpdated || staticLayer == null) {
            updateStaticLayer(cameraBounds);
        }
        drawStaticLayer(gui, cameraBounds);

        // Draw dynamic elements
        drawElements(gui, getModel().getParticles(), this.particleViewer, time, cameraBounds);
        drawElements(gui, getModel().getDoubleJumpParticles(), this.particleViewer, time, cameraBounds);
        drawElements(gui, getModel().getJumpParticles(), this.particleViewer, time, cameraBounds);
        drawElements(gui, getModel().getRespawnParticles(), this.particleViewer, time, cameraBounds);
        drawElements(gui, getModel().getDashParticles(), this.particleViewer, time, cameraBounds);

        drawElements(gui, getModel().getMediumTrees(), this.mediumTreeViewer, time, cameraBounds);
        drawElements(gui, getModel().getSmallTrees(), this.smallTreeViewer, time, cameraBounds);

        drawElements(gui, getModel().getBigRocks(), this.bigRockViewer, time, cameraBounds);
        drawElements(gui, getModel().getSmallRocks(), this.smallRockViewer, time, cameraBounds);

        drawElements(gui, getModel().getSwordMonsters(), this.swordMonsterViewer, time, cameraBounds);
        drawElements(gui, getModel().getPurpleMonsters(), this.purpleMonsterViewer, time, cameraBounds);
        drawElements(gui, getModel().getMinhoteMonsters(), this.minhoteMonsterViewer, time, cameraBounds);

        drawElements(gui, getModel().getEnergyOrbs(), this.energyOrbViewer, time, cameraBounds);
        drawElements(gui, getModel().getHealthOrbs(), this.healthOrbViewer, time, cameraBounds);
        drawElements(gui, getModel().getSpeedOrbs(), this.speedOrbViewer, time, cameraBounds);

        drawElement(gui, this.knightViewer, getModel().getPlayer(), time, cameraBounds);

        drawPlayerStats(gui, time);

        gui.flush();
    }

    private int[] calculateCameraBounds() {
        int playerX = (int) getModel().getPlayer().getPosition().x();
        int playerY = (int) getModel().getPlayer().getPosition().y();

        // Center the camera around the player, ensuring it doesn't exceed map boundaries
        int left = Math.max(0, playerX - CAMERA_WIDTH / 2);
        int top = Math.max(0, playerY - CAMERA_HEIGHT / 2);
        int right = Math.min(getModel().getWidth(), left + CAMERA_WIDTH);
        int bottom = Math.min(getModel().getHeight(), top + CAMERA_HEIGHT);

        return new int[]{left, top, right, bottom};
    }

    private void updateStaticLayer(int[] cameraBounds) throws IOException {
        if (staticLayer == null || staticLayer.getWidth() != CAMERA_WIDTH || staticLayer.getHeight() != CAMERA_HEIGHT) {
            staticLayer = new BufferedImage(CAMERA_WIDTH, CAMERA_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        }

        java.awt.Graphics2D g = staticLayer.createGraphics();
        g.setColor(new java.awt.Color(0, 0, 0, 0));
        g.fillRect(0, 0, staticLayer.getWidth(), staticLayer.getHeight());
        g.dispose();

        GUI tempGUI = new BufferedImageGUI(staticLayer);
        drawElements(tempGUI, getModel().getTiles(), this.tileViewer, 0, cameraBounds);
        staticLayerUpdated = false;
    }


    private void drawStaticLayer(GUI gui, int[] cameraBounds) {
        for (int x = 0; x < CAMERA_WIDTH; x++) {
            for (int y = 0; y < CAMERA_HEIGHT; y++) {
                int cameraX = cameraBounds[0] + x;
                int cameraY = cameraBounds[1] + y;

                if (cameraX >= 0 && cameraX < staticLayer.getWidth() && cameraY >= 0 && cameraY < staticLayer.getHeight()) {
                    int argb = staticLayer.getRGB(cameraX, cameraY);
                    int alpha = (argb >> 24) & 0xff;

                    if (alpha != 0) {
                        int red = (argb >> 16) & 0xff;
                        int green = (argb >> 8) & 0xff;
                        int blue = argb & 0xff;

                        gui.drawPixel(x, y, new TextColor.RGB(red, green, blue));
                    }
                }
            }
        }
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer, long time, int[] cameraBounds) throws IOException {
        for (T element : elements) {
            if (isElementInCamera(element, cameraBounds)) {
                drawElement(gui, viewer, element, time, cameraBounds);
            }
        }
    }

    private <T extends Element> void drawElement(GUI gui, ElementViewer<T> viewer, T element,  long time, int[] cameraBounds) throws IOException {
        int offsetX = cameraBounds[0];
        int offsetY = cameraBounds[1];
        viewer.draw(element, gui, time, offsetX, offsetY);
    }

    private <T extends Element> void drawElements(GUI gui, T[][] elements, ElementViewer<T> viewer, long frameCount, int[] cameraBounds) throws IOException {
        for (T[] elementLine : elements) {
            for (T element : elementLine) {
                if (element != null)
                    drawElement(gui, viewer, element, frameCount, cameraBounds);
            }
        }
    }


    private boolean isElementInCamera(Element element, int[] cameraBounds) {
        int x = (int) element.getPosition().x();
        int y = (int) element.getPosition().y();
        return x >= cameraBounds[0] && x < cameraBounds[2] && y >= cameraBounds[1] && y < cameraBounds[3];
    }


    private void dynamicGradientBackground(GUI gui, long time) {
        int width = 400;//getModel().getWidth();
        int height = 120;//getModel().getHeight();

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

        String hp = "hp " + player.getHP();

        String fps = "fps " + gui.getFPS();

        // Define a common color for all text
        TextColor.RGB color = new TextColor.RGB(0, 25, 25);

        // Draw each piece of information
        //this.textViewer.draw(pos, 4, 0, color, gui);
        this.textViewer.draw(state, 4, 8, color, gui);
        this.textViewer.draw(hp, 4, 16, color, gui);
        this.textViewer.draw(fps, 4, 24, color, gui);
        //this.textViewer.draw(hp, 4, 32, color, gui);

    }


    // Helper method to format position or velocity with truncated decimal values
    String formatVector(String label, double x, double y) {
        return String.format("%s %.2fx%.2fy", label, x, y);
    }
}