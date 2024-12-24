package HollowKnight.view.states;

import HollowKnight.gui.GUI;
import HollowKnight.gui.RescalableGUI;
import HollowKnight.model.credits.Credits;
import HollowKnight.view.menu.LogoViewer;
import HollowKnight.view.sprites.ViewerProvider;
import HollowKnight.view.text.TextViewer;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;

import static HollowKnight.view.text.GameTextViewer.*;

public class CreditsViewer extends ScreenViewer<Credits> {
    private final TextViewer textViewer;
    private final LogoViewer logoViewer;

    public CreditsViewer(Credits model, ViewerProvider viewerProvider) {
        super(model);
        this.textViewer = viewerProvider.getTextViewer();
        this.logoViewer = viewerProvider.getLogoViewer();
    }

    public static final TextColor messageColor = new TextColor.RGB(234, 234, 234);
    public static final TextColor nameColor = new TextColor.RGB(155,173,183);
    public static final TextColor scoreColor = new TextColor.RGB(91,110,225);
    public static final TextColor deathColor = new TextColor.RGB(95,133,240);
    public static final TextColor timeColor = new TextColor.RGB(99,155,255);

    @Override
    public void draw(GUI gui, long frameCount) throws IOException {
        gui.cls();
        drawSmoothColorfulBackground(gui, frameCount);
        drawMessages(gui);
        drawNames(gui);
        drawScore(gui);
        drawDeaths(gui);
        drawDuration(gui);
        logoViewer.draw(gui, 44, 16);
        gui.flush();
    }

    private void drawMessages(GUI gui) {
        int yAlignment = 6;
        int spacing = getCharHeight() * 8;
        for (int idx = 0; idx < getModel().getMessages().length ; idx++){
            String message = getModel().getMessages()[idx];
            int messageLength = message.length() * getCharWidth() + (message.length() - 1) * getSpacing();
            textViewer.draw(message,
                    ((double) gui.getWidth() / 2) - ((double) messageLength / 2),
                    yAlignment + spacing * idx,
                    messageColor, gui);
        }

    }

    private void drawNames(GUI gui) {
        int xAlignment = 95;
        int yAlignment = 60;
        int spacing = getCharHeight() * 2;
        for (int idx = 0; idx < getModel().getNames().length ; idx++){
            textViewer.draw(getModel().getNames()[idx],
                    xAlignment,
                    yAlignment + spacing * idx,
                    nameColor, gui);
        }
    }

    private void drawScore(GUI gui) {
        int xAlignment = 10;
        int yAlignment = 60;
        textViewer.draw("Score:  " + String.format("%1$" + 2 + "s", getModel().getScore()).replace(' ', '0'),
                xAlignment,
                yAlignment,
                scoreColor, gui);
    }

    private void drawDeaths(GUI gui) {
        int xAlignment = 10;
        int yAlignment = 70;
        textViewer.draw("Deaths: " + String.format("%1$" + 2 + "s", getModel().getDeaths()).replace(' ', '0'),
                xAlignment,
                yAlignment,
                deathColor, gui);
    }


    private void drawDuration(GUI gui) {
        int xAlignment = 10;
        int yAlignment = 80;
        textViewer.draw(
                "Time:   "
                        + String.format("%1$" + 2 + "s", getModel().getMinutes()).replace(' ', '0')
                        + ":" + String.format("%1$" + 2 + "s", getModel().getSeconds()).replace(' ', '0'),
                xAlignment,
                yAlignment,
                timeColor, gui
        );
    }

    private void drawSmoothColorfulBackground(GUI gui, long time) throws IOException {
        int screenWidth = 184;
        int screenHeight = 112;
        double changeRate = 0.05;

        // Smooth gradient generation with grayscale tone and hints of color
        for (int w = 0; w < screenWidth; w++) {
            for (int h = 0; h < screenHeight; h++) {
                // Create a base grayscale value
                double distance = Math.sqrt(Math.pow((double) w / screenWidth - 0.5, 2) + Math.pow((double) h / screenHeight - 0.5, 2));
                int gray = (int) (180 + 75 * Math.sin(time * changeRate + distance * Math.PI * 4));

                // Add subtle color variations
                int red = (int) (gray + 20 * Math.sin((double) w / screenWidth * 2 * Math.PI + time * changeRate));
                int green = (int) (gray + 15 * Math.sin((double) h / screenHeight * 2 * Math.PI + time * changeRate + Math.PI / 3));
                int blue = (int) (gray + 25 * Math.sin((double) w / screenWidth * 2 * Math.PI + time * changeRate + 2 * Math.PI / 3));

                // Clamp values to ensure RGB stays within bounds
                red = Math.min(255, Math.max(0, red));
                green = Math.min(255, Math.max(0, green));
                blue = Math.min(255, Math.max(0, blue));

                TextColor.RGB smoothGradientColor = new TextColor.RGB(red, green, blue);

                // Draw each pixel
                gui.drawPixel(w, h, smoothGradientColor);
            }
        }

        // Add a subtle border effect to make the visuals stand out
        TextColor.RGB borderColor = new TextColor.RGB(80, 80, 80); // Darker grayscale border
        for (int w = 0; w < screenWidth; w++) {
            gui.drawPixel(w, 0, borderColor);
            gui.drawPixel(w, screenHeight - 1, borderColor);
        }
        for (int h = 1; h < screenHeight - 1; h++) {
            gui.drawPixel(0, h, borderColor);
            gui.drawPixel(screenWidth - 1, h, borderColor);
        }
    }

}