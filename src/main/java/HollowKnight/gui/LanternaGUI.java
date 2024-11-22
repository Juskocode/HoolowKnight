package HollowKnight.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.awt.event.KeyEvent.*;

public class LanternaGUI implements GUI{
    private final Screen screen;

    private final int width;
    private final int height;

    // Track active keys
    private final Set<Integer> activeKeys;
    private boolean keySpam;
    private int priorityKeyPressed;
    private static final List<Integer> SPAM_KEYS = List.of(VK_LEFT, VK_RIGHT);

    public LanternaGUI(Screen screen) {
        this.screen = screen;
        this.width = screen.getTerminalSize().getColumns();
        this.height = screen.getTerminalSize().getRows();
        this.activeKeys = new HashSet<>();
        this.priorityKeyPressed = 0;
    }

    public LanternaGUI(int width, int height, int fontSize) throws IOException, URISyntaxException, FontFormatException {
        Terminal terminal = createTerminal(width, height, fontSize);
        this.screen = createScreen(terminal);
        this.height = height;
        this.width = width;
        this.keySpam = false;
        this.priorityKeyPressed = 0;
        this.activeKeys = new HashSet<>();

        // Add KeyAdapter to handle multiple key presses
        setupKeyAdapter();
    }

    private void setupKeyAdapter() {
        Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
            if (event instanceof KeyEvent keyEvent) {
                synchronized (activeKeys) {
                    if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {
                        handleKeyPressed(keyEvent);
                    } else if (keyEvent.getID() == KeyEvent.KEY_RELEASED) {
                        handleKeyReleased(keyEvent);
                    }
                }
            }
        }, AWTEvent.KEY_EVENT_MASK);
    }

    private void handleKeyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (SPAM_KEYS.contains(keyCode)) {
            activeKeys.add(keyCode);
            priorityKeyPressed = keyCode;
        }
    }

    private void handleKeyReleased(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        activeKeys.remove(keyCode);
        if (priorityKeyPressed == keyCode) {
            // If the released key was the priority, update to another active key
            priorityKeyPressed = activeKeys.isEmpty() ? 0 : activeKeys.iterator().next();
        }
    }

    @Override
    public ACTION getACTION() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke == null && priorityKeyPressed != 0) {
            return priorityKeyPressed == VK_LEFT ? ACTION.LEFT : ACTION.RIGHT;
        } else if (keyStroke == null) {
            return ACTION.NULL;
        }

        // Handle non-priority keys as usual
        return switch (keyStroke.getKeyType()) {
            case ArrowUp -> ACTION.UP;
            case ArrowDown -> ACTION.DOWN;
            case Character -> {
                char character = keyStroke.getCharacter();
                if (character == ' ') {
                    yield ACTION.JUMP;
                } else if (character == 'q') {
                    yield ACTION.QUIT;
                } else {
                    yield ACTION.NULL;
                }
            }
            case Enter -> ACTION.SELECT;
            case EOF -> ACTION.QUIT;
            default -> ACTION.NULL;
        };
    }

    public void setKeySpam(boolean keySpam) {
        if (!keySpam) priorityKeyPressed = 0;
        this.keySpam = keySpam;
    }

    /*
    public double getJumpBoost() {
        synchronized (this) {
            // Ensure both times are valid
            if (jumpKeyPressedTime == 0 || jumpKeyReleasedTime == 0) {
                resetJumpTiming();
                return 2.0; // Default minimum boost
            }

            long holdDuration = jumpKeyReleasedTime - jumpKeyPressedTime;

            if (holdDuration < 0) { // Handle invalid timing
                resetJumpTiming();
                return 2.0; // Default boost
            }

            // Scale duration to range [2.0, 4.0], clamped at 350ms
            double boost = 2.0 + (Math.min(holdDuration, 350) / 350.0) * (4.0 - 2.0);

            resetJumpTiming(); // Reset after use
            return boost;
        }
    }
    // Reset method to clear timing variables
    private void resetJumpTiming() {
        jumpKeyPressedTime = 0;
        jumpKeyReleasedTime = 0;
    }
*/

    private Terminal createTerminal(int width, int height, int fontSize) throws IOException, URISyntaxException, FontFormatException {
        TerminalSize size = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(size);

        AWTTerminalFontConfiguration fontConfig = loadFont(fontSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        return terminalFactory.createTerminal();
    }

    private AWTTerminalFontConfiguration loadFont(int fontSize) throws URISyntaxException, IOException, FontFormatException {
        URL resource = getClass().getClassLoader().getResource("fonts/pixel.ttf");
        assert resource != null;
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.PLAIN, fontSize);
        return AWTTerminalFontConfiguration.newInstance(font);
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        return screen;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void cls() {
        screen.clear();
    }

    @Override
    public void flush() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }

    @Override
    public void drawPixel(int x, int y, TextColor.RGB color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(color);
        tg.putString(x, y, " ");
    }

    @Override
    public void drawRectangle(int x, int y, int width, int height, TextColor.RGB color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(color);
        for (int dy = 0; dy < height; dy++) {
            for (int dx = 0; dx < width; dx++) {
                tg.putString(x + dx, y + dy, " ");
            }
        }
    }

    @Override
    public void drawHitBox(int x, int y, int width, int height, TextColor.RGB color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(color);

        // Draw the top and bottom edges
        for (int dx = 0; dx < width; dx++) {
            tg.putString(x + dx, y, " "); // Top edge
            tg.putString(x + dx, y + height - 1, " "); // Bottom edge
        }

        // Draw the left and right edges
        for (int dy = 0; dy < height; dy++) {
            tg.putString(x, y + dy, " "); // Left edge
            tg.putString(x + width - 1, y + dy, " "); // Right edge
        }
    }

    @Override
    public void drawText(int x, int y, TextColor.RGB color, String Text) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(color);
        tg.putString(x, y, Text);
    }
}
