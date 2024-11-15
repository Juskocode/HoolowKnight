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
import java.util.List;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;

public class LanternaGUI implements GUI{
    private final Screen screen;

    private final int width;

    private final int height;

    private boolean keySpam;
    private KeyEvent priorityKeyPressed;
    private static final List<Integer> SPAM_KEYS = List.of(VK_LEFT, VK_RIGHT);

    public LanternaGUI(Screen screen) {
        this.screen = screen;
        this.width = screen.getTerminalSize().getColumns();
        this.height = screen.getTerminalSize().getRows();
    }
    public LanternaGUI(int width, int height, int fontSize) throws IOException, URISyntaxException, FontFormatException {
        Terminal terminal = createTerminal(width, height, fontSize);
        this.screen = createScreen(terminal);
        this.height = height;
        this.width = width;
        this.keySpam = false;
        this.priorityKeyPressed = null;
    }

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
    public void drawText(int x, int y, TextColor.RGB color, String Text) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(color);
        tg.putString(x, y, Text);
    }

    @Override
    public ACTION getACTION() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke == null)
            return ACTION.NULL;

        return switch (keyStroke.getKeyType()) {
            case ArrowUp -> ACTION.UP;
            case ArrowDown -> ACTION.DOWN;
            case ArrowLeft -> ACTION.LEFT;
            case ArrowRight -> ACTION.RIGHT;
            case Character ->  keyStroke.getCharacter() == 'q' ? ACTION.QUIT : ACTION.NULL;
            case Enter -> ACTION.SELECT;
            case EOF -> ACTION.QUIT;
            default -> ACTION.NULL;
        };
    }
    @Override
    public void close() throws IOException {
        screen.close();
    }
    public void setKeySpam(boolean keySpam) {
        if (!keySpam)
            priorityKeyPressed = null;
        this.keySpam = keySpam;
    }

}
