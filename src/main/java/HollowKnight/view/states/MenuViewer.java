package HollowKnight.view.states;

import HollowKnight.gui.GUI;
import HollowKnight.gui.RescalableGUI;
import HollowKnight.model.game.elements.Element;
import HollowKnight.model.menu.Menu;
import HollowKnight.model.menu.Option;
import HollowKnight.model.menu.Particle;
import HollowKnight.view.Viewer;
import HollowKnight.view.elements.ElementViewer;
import HollowKnight.view.menu.LogoViewer;
import HollowKnight.view.menu.OptionViewer;
import HollowKnight.view.menu.ParticleViewer;
import HollowKnight.view.text.GameTextViewer;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;
import java.util.List;

public class MenuViewer<T extends Menu> extends ScreenViewer<T>{

    private final ParticleViewer particleViewer;
    private static final TextColor.RGB unselectedColor = new TextColor.RGB(26, 62, 108);
    private static final TextColor.RGB selectedColor = new TextColor.RGB(219, 219, 48);
    private final OptionViewer optionViewer;
    private final LogoViewer logoViewer;


    public MenuViewer(T model) throws IOException {
        super(model);
        this.optionViewer = new OptionViewer(new GameTextViewer());
        this.particleViewer = new ParticleViewer();
        this.logoViewer = new LogoViewer();
    }

    @Override
    public void draw(GUI gui, long time) throws IOException {
        gui.cls();
        drawRetroDynamicBackground(gui, time);
        logoViewer.draw(gui, 90, 30);
        drawParticles(gui, getModel().getParticles(), particleViewer, time);
        this.drawOptions((RescalableGUI) gui, getModel().getOptions(), optionViewer, time);
        gui.flush();
    }

    private void drawParticles(GUI gui, List<Particle> particles, ParticleViewer viewer, long time) throws IOException {
        for (Particle particle : particles) {
            viewer.draw(particle, gui, time, 0, 0);
        }
    }

    private void drawOptions(RescalableGUI gui, List<Option> options, OptionViewer viewer, long time) throws IOException {
        int animationDuration = 20; // Number of ticks for the animation
        int maxOffsetX = 40; // Maximum horizontal movement (how far right to start the animation)

        // Calculate the start time for drawing to begin (when the first option reaches its initial position)
        int firstOptionStartTime = 0;

        // Calculate the time when each option should start sliding (based on its index)
        for (int idx = 0; idx < options.size(); idx++) {
            Option option = options.get(idx);

            // Determine the start time for the first option to begin drawing
            int optionStartTime = idx * animationDuration;  // Each option starts after the previous one finishes
            firstOptionStartTime = optionStartTime;

            // Wait until the first option reaches its initial position
            if (time < firstOptionStartTime) {
                continue; // Skip drawing until the first option reaches its start time
            }

            // Calculate the new x position for the animation (moving from right to left)
            int startPositionX = (int) option.getPosition().x(); // Initial x position
            int currentPositionX = startPositionX;

            if (time >= optionStartTime && time < optionStartTime + animationDuration) {
                // Calculate the movement for the animation: gradually decrease x position
                int movementOffset = (int) (maxOffsetX * (1 - (time - optionStartTime) / (float) animationDuration)); // Moves from right to left
                currentPositionX += movementOffset;
            }

            // Update the option's position with the new x value
            Option updatedOption = new Option(currentPositionX, (int) option.getPosition().y(), option.getType());

            // Determine if the option is selected
            boolean isSelected = getModel().isSelected(idx);
            // Apply blink effect for the selected option
            if (isSelected && time >= 80) {
                boolean isVisible = (time / 8) % 2 == 0; // Toggle visibility every 10 ticks
                if (getModel().getInGame()) {
                    isVisible = (time / 4) % 2 == 0;
                }
                if (isVisible) {
                    viewer.draw(updatedOption, gui, selectedColor); // Draw when visible
                }
            }
            else if (time < 80) {
                viewer.draw(updatedOption, gui, unselectedColor);
            }
            else {
                // Draw normally for unselected or non-blinking options
                viewer.draw(updatedOption, gui, isSelected ? selectedColor : unselectedColor);
            }

            // Debug output for option position
            //System.out.println("Option pos : " + currentPositionX + ", " + (int) option.getPosition().y() + " " + option.getText());
        }
    }



    private void drawRetroDynamicBackground(GUI gui, long time) throws IOException {
        int screenWidth = 230;
        int screenHeight = 110;
        double changeRate = 0.05;
        // Generate a retro gradient background
        for (int w = 0; w < screenWidth; w++) {
            int colorValue = (int) (128 + 127 * Math.sin((double) w / screenWidth * 2 * Math.PI + time * changeRate)); // Oscillating gray shades
            TextColor.RGB gradientColor = new TextColor.RGB(colorValue, colorValue, colorValue);

            for (int h = 0; h < screenHeight; h++) {
                gui.drawPixel(w, h, gradientColor);
            }
        }

        // Add a retro-style border
        TextColor.RGB borderColor = new TextColor.RGB(25, 25, 25);
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