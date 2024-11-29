package HollowKnight.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.List;

import static java.awt.event.KeyEvent.*;

public class LanternaGUI implements RescalableGUI {
    private  Screen screen;
    private final ScreenGenerator screenGenerator;

    private long duration;

    String title;

    // Track active keys
    private ResolutionScale resolutionScale;
    private Long jumpPressStartTime = null; // Tracks when the jump key was pressed
    private KeyEvent priorityKeyPressed;
    private KeyAdapter keyAdapter;
    private KeyEvent keyPressed;
    private static final List<Integer> SPAM_KEYS = List.of(VK_LEFT, VK_RIGHT);

    public LanternaGUI(ScreenGenerator screenGenerator, String title) throws IOException, URISyntaxException, FontFormatException {
        this.screenGenerator = screenGenerator;
        this.title = title;
        this.priorityKeyPressed = null;
        this.keyAdapter = createKeyAdapter();
        this.keyPressed = null;
        setResolutionScale(null);
    }

    private Screen createScreen(ResolutionScale resolutionScale) throws IOException, URISyntaxException, FontFormatException {
        Screen screen = screenGenerator.createScreen(resolutionScale, title, getKeyAdapter());

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    private KeyAdapter createKeyAdapter() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (SPAM_KEYS.contains(e.getKeyCode()))
                    keyPressed = priorityKeyPressed = e;
                else
                    keyPressed = e;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (SPAM_KEYS.contains(e.getKeyCode()))
                    keyPressed = priorityKeyPressed = null;
                else
                    keyPressed = priorityKeyPressed;
            }
        };
    }

    private void setupKeyAdapter() {
        Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
            if (event instanceof KeyEvent keyEvent) {
                synchronized (SPAM_KEYS) {
                    if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {
                        handleKeyPressed(keyEvent);
                    } else if (keyEvent.getID() == KeyEvent.KEY_RELEASED) {
                        handleKeyReleased(keyEvent);
                    }
                }
            }
        }, AWTEvent.KEY_EVENT_MASK);
    }

    private boolean isJumpKeyHeld = false; // Tracks if the jump key is currently held
    private long lastJumpEndTime = 0;

    void handleKeyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();

        if (keyCode == KeyEvent.VK_SPACE && jumpPressStartTime == null) {
            long currentTime = System.currentTimeMillis();

            if (currentTime - lastJumpEndTime < 50) {
                System.out.println("Jump ignored due to cooldown");
                return;
            }

            jumpPressStartTime = currentTime;
            isJumpKeyHeld = true; // Mark jump key as held
            System.out.println("Jump key pressed");
        }

        if (SPAM_KEYS.contains(keyCode))
            keyPressed = priorityKeyPressed = keyEvent;
        else
            keyPressed = keyEvent;
    }

    void handleKeyReleased(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();

        if (keyCode == KeyEvent.VK_SPACE && jumpPressStartTime != null) {
            long currentTime = System.currentTimeMillis();

            long duration = currentTime - jumpPressStartTime;
            jumpPressStartTime = null;
            lastJumpEndTime = currentTime;
            isJumpKeyHeld = false; // Mark jump key as no longer held

            System.out.println("Jump key released (Duration: " + duration + " ms)");
            handleJump(duration);
            setDuration(duration);
        }

        if (SPAM_KEYS.contains(keyCode))
            keyPressed = priorityKeyPressed = null;
        else
            keyPressed = priorityKeyPressed;
    }

    @Override
    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public ACTION getACTION() throws IOException {
        if (keyPressed == null)
            return ACTION.NULL;
        int keyCode = keyPressed.getKeyCode();

        keyPressed = priorityKeyPressed;
        // Handle other keys
        return switch (keyCode) {
            case VK_LEFT -> ACTION.LEFT;
            case VK_RIGHT -> ACTION.RIGHT;
            case VK_UP -> ACTION.UP;
            case VK_DOWN -> ACTION.DOWN;
            case VK_X -> ACTION.DASH;
            case VK_ESCAPE -> ACTION.QUIT;
            case VK_Q -> ACTION.KILL;
            case VK_ENTER -> ACTION.SELECT;
            case VK_SPACE -> ACTION.JUMP;
            default -> ACTION.NULL;
        };
    }

    public KeyAdapter getKeyAdapter() {
        return keyAdapter;
    }

    @Override
    public int getWidth() {
        return screenGenerator.getWidth();
    }

    @Override
    public int getHeight() {
        return screenGenerator.getWidth();
    }

    @Override
    public GUI getGUI() {
        return this;
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
    public ResolutionScale getResolutionScale() {
        return resolutionScale;
    }

    public boolean isJumpHeld() {
        return isJumpKeyHeld;
    }

    private void handleJump(long duration) {
        // Example: Adjust jump height or gravity based on duration
        if (duration < 140) {
            System.out.println("Small jump");
            // Apply small jump logic (e.g., low gravity or small boost)
        } else if (duration < 200) {
            System.out.println("Medium jump");
            // Apply medium jump logic
        } else {
            System.out.println("Big jump");
            // Apply big jump logic (e.g., high boost or stronger gravity reduction)
        }
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

        // Draw the left and right edges, excluding corners to avoid duplication
        for (int dy = 1; dy < height - 1; dy++) {
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

    @Override
    public void setResolutionScale(ResolutionScale resolutionScale) throws IOException, URISyntaxException, FontFormatException {
        if (screen != null)
            screen.close();
        this.resolutionScale = resolutionScale;
        this.screen = createScreen(resolutionScale);
    }
}
