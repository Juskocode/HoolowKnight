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

    private static final int CAMERA_WIDTH = 230;
    private static final int CAMERA_HEIGHT = 120;
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

        this.staticLayer = null;
        this.staticLayerUpdated = true;
    }

    @Override
    public void draw(GUI gui, long time) throws IOException {
        gui.cls();
        int[] cameraBounds = calculateCameraBounds();
        dynamicGradientBackground(gui, time);

        if (staticLayerUpdated || staticLayer == null) {
            updateStaticLayer(cameraBounds);
        }
        drawStaticLayer(gui);

        drawElements(gui, getModel().getParticles(), this.particleViewer, time, cameraBounds);
        drawElements(gui, getModel().getDoubleJumpParticles(), this.particleViewer, time, cameraBounds);
        drawElements(gui, getModel().getJumpParticles(), this.particleViewer, time, cameraBounds);
        drawElements(gui, getModel().getRespawnParticles(), this.particleViewer, time, cameraBounds);
        drawElements(gui, getModel().getDashParticles(), this.particleViewer, time, cameraBounds);
        drawElements(gui, getModel().getSwordMonsters(), this.swordMonsterViewer, time, cameraBounds);
        drawElements(gui, getModel().getPurpleMonsters(), this.purpleMonsterViewer, time, cameraBounds);
        drawElements(gui, getModel().getMinhoteMonsters(), this.minhoteMonsterViewer, time, cameraBounds);

        drawElement(gui, this.knightViewer, getModel().getPlayer(), time, cameraBounds);

        drawPlayerStats(gui, time);

        gui.flush();
    }

    private int[] calculateCameraBounds() {
        int playerX = (int) getModel().getPlayer().getPosition().x();
        int playerY = (int) getModel().getPlayer().getPosition().y();

        // Center the camera on the player
        int left = playerX - CAMERA_WIDTH / 2;
        int top = playerY - CAMERA_HEIGHT / 2;

        // Prevent the camera from moving out of map bounds
        left = Math.max(0, Math.min(left, 340 - CAMERA_WIDTH));
        top = Math.max(0, Math.min(top, 120 - CAMERA_HEIGHT));

        return new int[]{left, top, left + CAMERA_WIDTH, top + CAMERA_HEIGHT};
    }



    private void updateStaticLayer(int[] cameraBounds) throws IOException {
        // Resize the static layer if needed
        if (staticLayer == null || staticLayer.getWidth() != CAMERA_WIDTH || staticLayer.getHeight() != CAMERA_HEIGHT) {
            staticLayer = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
        }

        // Calculate world offset for rendering static layer
        int offsetX = cameraBounds[0]; // Camera's top-left corner (world coordinates)
        int offsetY = cameraBounds[1];

        // Clear the static layer
        java.awt.Graphics2D g = staticLayer.createGraphics();
        g.setColor(new java.awt.Color(0, 0, 0, 0)); // Transparent background
        g.fillRect(0, 0, staticLayer.getWidth(), staticLayer.getHeight());

        // Create a temporary GUI for rendering the static layer tiles
        GUI tempGUI = new BufferedImageGUI(staticLayer);

        // Draw only visible static elements (within the camera bounds)
        drawElements(tempGUI, getModel().getTiles(), this.tileViewer, 0, new int[]{offsetX, offsetY, offsetX + CAMERA_WIDTH, offsetY + CAMERA_HEIGHT});
        drawElements(tempGUI, getModel().getMediumTrees(), this.mediumTreeViewer, 0, new int[]{offsetX, offsetY, offsetX + CAMERA_WIDTH, offsetY + CAMERA_HEIGHT});
        drawElements(tempGUI, getModel().getSmallTrees(), this.smallTreeViewer, 0, new int[]{offsetX, offsetY, offsetX + CAMERA_WIDTH, offsetY + CAMERA_HEIGHT});
        drawElements(tempGUI, getModel().getBigRocks(), this.bigRockViewer, 0, new int[]{offsetX, offsetY, offsetX + CAMERA_WIDTH, offsetY + CAMERA_HEIGHT});
        drawElements(tempGUI, getModel().getSmallRocks(), this.smallRockViewer, 0, new int[]{offsetX, offsetY, offsetX + CAMERA_WIDTH, offsetY + CAMERA_HEIGHT});
        drawElements(tempGUI, getModel().getEnergyOrbs(), this.energyOrbViewer, 0, new int[]{offsetX, offsetY, offsetX + CAMERA_WIDTH, offsetY + CAMERA_HEIGHT});
        drawElements(tempGUI, getModel().getHealthOrbs(), this.healthOrbViewer, 0, new int[]{offsetX, offsetY, offsetX + CAMERA_WIDTH, offsetY + CAMERA_HEIGHT});
        drawElements(tempGUI, getModel().getSpeedOrbs(), this.speedOrbViewer, 0, new int[]{offsetX, offsetY, offsetX + CAMERA_WIDTH, offsetY + CAMERA_HEIGHT});

        g.dispose(); // Release graphics resources
        staticLayerUpdated = false;
    }

    private void drawStaticLayer(GUI gui) {
        // Calculate the camera offset (start of visible region)
        int offsetX = calculateCameraBounds()[0];
        int offsetY = calculateCameraBounds()[1];

        // Render static layer directly onto GUI
        for (int x = 0; x < CAMERA_WIDTH; x++) {
            for (int y = 0; y < CAMERA_HEIGHT; y++) {
                // Map screen coordinates back to world coordinates
                int worldX = x + offsetX;
                int worldY = y + offsetY;

                // Skip out-of-bounds rendering
                if (worldX < 0 || worldY < 0 || worldX >= 1000 || worldY >= 1000) continue;

                // Retrieve pixel data from staticLayer
                int argb = staticLayer.getRGB(x, y);
                int alpha = (argb >> 24) & 0xff;

                if (alpha != 0) {
                    int red = (argb >> 16) & 0xff;
                    int green = (argb >> 8) & 0xff;
                    int blue = argb & 0xff;

                    // Render pixel to the GUI
                    gui.drawPixel(x, y, new TextColor.RGB(red, green, blue));
                }
            }
        }
    }




    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer, long time, int[] cameraBounds) throws IOException {
        elements.stream()
                .filter(element -> isElementInCamera(element, cameraBounds))
                .forEach(element -> {
                    try {
                        drawElement(gui, viewer, element, time, cameraBounds);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private <T extends Element> void drawElement(GUI gui, ElementViewer<T> viewer, T element, long time, int[] cameraBounds) throws IOException {
        int adjustedX = (int) (element.getPosition().x() - cameraBounds[0]);
        int adjustedY = (int) (element.getPosition().y() - cameraBounds[1]);
        viewer.draw(element, gui, time, adjustedX, adjustedY);
    }

    private boolean isElementInCamera(Element element, int[] cameraBounds) {
        int x = (int) element.getPosition().x();
        int y = (int) element.getPosition().y();
        return x >= cameraBounds[0] && x < cameraBounds[2] && y >= cameraBounds[1] && y < cameraBounds[3];
    }

    private <T extends Element> void drawElements(GUI gui, T[][] elements, ElementViewer<T> viewer, long frameCount, int[] cameraBounds) throws IOException {
        for (T[] elementLine : elements) {
            for (T element : elementLine) {
                if (element != null)
                    drawElement(gui, viewer, element, frameCount, cameraBounds);
            }
        }
    }

    private void dynamicGradientBackground(GUI gui, long time) {
        int width = 240;//getModel().getWidth();
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