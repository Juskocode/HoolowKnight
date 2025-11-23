package HollowKnight.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class LanternaScreenGenerator implements ScreenGenerator {
    private final DefaultTerminalFactory terminalFactory;
    private final TerminalSize terminalSize;
    private final Rectangle defaultBounds;

    public LanternaScreenGenerator(DefaultTerminalFactory terminalFactory, TerminalSize terminalSize, Rectangle defaultBounds) {
        this.terminalFactory = terminalFactory;
        this.terminalSize = terminalSize;
        this.defaultBounds = defaultBounds;
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setInitialTerminalSize(terminalSize);
    }

    @Override
    public Screen createScreen(RescalableGUI.ResolutionScale resolutionScale, String title, KeyListener keyListener)
            throws IOException, URISyntaxException, FontFormatException {
        try {
            Rectangle terminalBounds = getTerminalBounds(resolutionScale);
            int fontSize = getBestFontSize(terminalBounds);
            AWTTerminalFontConfiguration fontConfig = loadFont(fontSize);
            terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);

            // Try to explicitly create an AWT terminal to avoid falling back to a TTY (/dev/tty)
            if (!GraphicsEnvironment.isHeadless()) {
                AWTTerminalFrame awtTerminal = null;
                try {
                    awtTerminal = terminalFactory.createAWTTerminal();
                } catch (Throwable ignored) {
                    // If not supported in this environment or mocked tests, fall back below
                }
                if (awtTerminal != null) {
                    awtTerminal.getComponent(0).addKeyListener(keyListener);
                    awtTerminal.setTitle(title);
                    // Ensure the window is realized and shown on the AWT Event Dispatch Thread
                    final AWTTerminalFrame finalAwtTerminal = awtTerminal;
                    EventQueue.invokeLater(() -> {
                        try {
                            finalAwtTerminal.pack();
                        } catch (Throwable ignored) { /* safe on mocks */ }
                        finalAwtTerminal.setLocationByPlatform(true);
                        finalAwtTerminal.setVisible(true);
                        try {
                            finalAwtTerminal.toFront();
                            finalAwtTerminal.requestFocus();
                        } catch (Throwable ignored) { /* focus ops may be ignored in tests */ }
                    });
                    return new TerminalScreen(awtTerminal);
                }
            }

            // Fallback: use factory default screen creation (used by tests via mocks)
            TerminalScreen screen = terminalFactory.createScreen();
            // In headless/TTY environments, the terminal may NOT be an AWTTerminalFrame.
            // Only perform AWT operations if that's the case; otherwise just return the screen.
            if (screen.getTerminal() instanceof AWTTerminalFrame terminal) {
                terminal.getComponent(0).addKeyListener(keyListener);
                terminal.setTitle(title);
                // Make sure the terminal window is visible in case the factory didn't show it yet — run on EDT
                final AWTTerminalFrame finalTerminal = terminal;
                EventQueue.invokeLater(() -> {
                    try {
                        finalTerminal.pack();
                    } catch (Throwable ignored) { /* safe on mocks */ }
                    finalTerminal.setLocationByPlatform(true);
                    finalTerminal.setVisible(true);
                    try {
                        finalTerminal.toFront();
                        finalTerminal.requestFocus();
                    } catch (Throwable ignored) { /* focus ops may be ignored in tests */ }
                });
            }
            return screen;
        } catch (HeadlessException e) {
            // Provide a clearer message when running in a true headless environment
            throw new IOException("Cannot create AWT terminal in headless environment. Run from a normal macOS session (Terminal/iTerm or IntelliJ) and ensure java.awt.headless=false.", e);
        }
    }

    private AWTTerminalFontConfiguration loadFont(int fontSize) throws URISyntaxException, IOException, FontFormatException {
        URL resource = getClass().getClassLoader().getResource("fonts/pixel.ttf");
        File fontFile = new File(Objects.requireNonNull(resource).toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.PLAIN, fontSize);
        return AWTTerminalFontConfiguration.newInstance(font);
    }

    private int getBestFontSize(Rectangle terminalBounds) {
        double maxFontWidth = terminalBounds.getWidth() / terminalSize.getColumns();
        double maxFontHeight = terminalBounds.getHeight() / terminalSize.getRows();
        return (int) Math.min(maxFontWidth, maxFontHeight);
    }

    private Rectangle getTerminalBounds(RescalableGUI.ResolutionScale resolutionScale) {
        if (resolutionScale == null)
            return defaultBounds;
        return new Rectangle(resolutionScale.getWidth(), resolutionScale.getHeight());
    }

    @Override
    public int getWidth() {
        return terminalSize.getColumns();
    }

    @Override
    public int getHeight() {
        return terminalSize.getRows();
    }
}
