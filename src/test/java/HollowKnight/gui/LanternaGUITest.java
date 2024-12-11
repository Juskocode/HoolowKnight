package HollowKnight.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class LanternaGUITest {
    private Screen screen;
    private TextGraphics tg;
    private Terminal terminal;
    private LanternaGUI gui;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        this.terminal = Mockito.mock(Terminal.class);
        this.screen = Mockito.mock(Screen.class); // Mocking the Screen object
        this.tg = Mockito.mock(TextGraphics.class); // Mocking the TextGraphics object

        // Mock the TerminalSize
        TerminalSize terminalSize = Mockito.mock(TerminalSize.class);
        Mockito.when(terminalSize.getColumns()).thenReturn(80); // Example width
        Mockito.when(terminalSize.getRows()).thenReturn(24); // Example height

        // Link mocked TerminalSize to Screen
        Mockito.when(screen.getTerminalSize()).thenReturn(terminalSize);
        Mockito.when(screen.newTextGraphics()).thenReturn(tg); // Link mocked TextGraphics

        this.gui = new LanternaGUI(screen); // Initialize LanternaGUI with the mocked screen
    }
    /*
    @Test
    void testCreateTerminal() throws Exception {
        // Mock dependencies for creating terminal
        DefaultTerminalFactory terminalFactory = Mockito.mock(DefaultTerminalFactory.class);
        AWTTerminalFontConfiguration fontConfig = Mockito.mock((AWTTerminalFontConfiguration.class));

        Mockito.when(terminalFactory.createTerminal()).thenReturn(terminal);
        Mockito.when(gui.createTerminal(80, 24, 12)).thenReturn(terminal);

        // Mock loadFont
        Mockito.when(gui.loadFont(12)).thenReturn(fontConfig);

        // Create the terminal using the method under test
        Terminal createdTerminal = gui.createTerminal(80, 24, 12);

        assertNotNull(createdTerminal);
        Mockito.verify(terminalFactory, Mockito.times(1)).setInitialTerminalSize(new TerminalSize(80, 24));
        Mockito.verify(terminalFactory, Mockito.times(1)).setTerminalEmulatorFontConfiguration(fontConfig);
        Mockito.verify(terminalFactory, Mockito.times(1)).createTerminal();
    }

    @Test
    void testLoadFont() throws Exception {
        // Mock font loading
        URL mockURL = Mockito.mock(URL.class);
        File mockFile = Mockito.mock(File.class);
        Font mockFont = Mockito.mock(Font.class);
        AWTTerminalFontConfiguration fontConfig = Mockito.mock(AWTTerminalFontConfiguration.class);

        Mockito.when(getClass().getClassLoader().getResource("fonts/pixel.ttf")).thenReturn(mockURL);
        Mockito.when(mockURL.toURI()).thenReturn(mockFile.toURI());
        Mockito.when(Font.createFont(Font.TRUETYPE_FONT, mockFile)).thenReturn(mockFont);
        Mockito.when(mockFont.deriveFont(Font.PLAIN, 12)).thenReturn(mockFont);
        Mockito.when(AWTTerminalFontConfiguration.newInstance(mockFont)).thenReturn(fontConfig);

        // Call method
        AWTTerminalFontConfiguration result = gui.loadFont(12);

        // Verify that the font loading process works as expected
        assertNotNull(result);
        Mockito.verify(mockURL, Mockito.times(1)).toURI();
        //Mockito.verify(Font.class, Mockito.times(1)).createFont(Font.TRUETYPE_FONT, mockFile);
    }

    @Test
    void testCreateScreen() throws IOException {
        // Mock screen creation
        Mockito.when(gui.createScreen(terminal)).thenReturn(screen);
        Screen createdScreen = gui.createScreen(terminal);

        assertNotNull(createdScreen);
        Mockito.verify(screen, Mockito.times(1)).startScreen();
        Mockito.verify(screen, Mockito.times(1)).doResizeIfNecessary();
    }
*/
    @Test
    void testDrawText() {
        TextColor.RGB color = new TextColor.RGB(255, 0, 0); // Example red color
        gui.drawText(5, 10, color, "Test Message");

        // Verifying behavior on TextGraphics
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(color);
        Mockito.verify(tg, Mockito.times(1)).putString(5, 10, "Test Message");
    }

    @Test
    void testDrawPixel() {
        TextColor.RGB color = new TextColor.RGB(0, 255, 0); // Example green color
        gui.drawPixel(3, 7, color);

        // Verifying behavior on TextGraphics
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(color);
        Mockito.verify(tg, Mockito.times(1)).putString(3, 7, " ");
    }

    @Test
    void testDrawRectangle() {
        TextColor.RGB color = new TextColor.RGB(0, 0, 255); // Example blue color
        gui.drawRectangle(2, 4, 3, 2, color);

        // Verifying behavior for each pixel in the rectangle
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(color);
        Mockito.verify(tg, Mockito.times(1)).putString(2, 4, " ");
        Mockito.verify(tg, Mockito.times(1)).putString(3, 4, " ");
        Mockito.verify(tg, Mockito.times(1)).putString(4, 4, " ");
        Mockito.verify(tg, Mockito.times(1)).putString(2, 5, " ");
        Mockito.verify(tg, Mockito.times(1)).putString(3, 5, " ");
        Mockito.verify(tg, Mockito.times(1)).putString(4, 5, " ");
    }

    @Test
    void testDrawHitBox() {
        TextColor.RGB color = new TextColor.RGB(128, 128, 128); // Example gray color
        int x = 2, y = 3, width = 4, height = 3;

        gui.drawHitBox(x, y, width, height, color);

        // Verify the top and bottom edges
        for (int dx = 0; dx < width; dx++) {
            Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(color);
            Mockito.verify(tg, Mockito.times(1)).putString(x + dx, y, " "); // Top edge
            Mockito.verify(tg, Mockito.times(1)).putString(x + dx, y + height - 1, " "); // Bottom edge
        }

        // Verify the left and right edges, excluding corners
        for (int dy = 1; dy < height - 1; dy++) {
            Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(color);
            Mockito.verify(tg, Mockito.times(1)).putString(x, y + dy, " "); // Left edge
            Mockito.verify(tg, Mockito.times(1)).putString(x + width - 1, y + dy, " "); // Right edge
        }
    }


    @Test
    void testGetWidth() {
        assertEquals(80, gui.getWidth()); // Check that the mocked width is returned
    }

    @Test
    void testGetHeight() {
        assertEquals(24, gui.getHeight()); // Check that the mocked height is returned
    }


    @Test
    void testCls() throws IOException {
        gui.cls();

        // Verifying that the screen's clear method was called
        Mockito.verify(screen, Mockito.times(1)).clear();
    }

    @Test
    void testFlush() throws IOException {
        gui.flush();

        // Verifying that the screen's refresh method was called
        Mockito.verify(screen, Mockito.times(1)).refresh();
    }

    @Test
    void testClose() throws IOException {
        gui.close();

        // Verifying that the screen's close method was called
        Mockito.verify(screen, Mockito.times(1)).close();
    }

    @Test
    void testGetDuration() {
        gui.setDuration(150L); // Set duration
        assertEquals(150L, gui.getDuration()); // Verify duration
    }

    @Test
    void testJumpHandling() {
        gui.setDuration(200L); // Simulate jump duration
        assertEquals(200L, gui.getDuration()); // Verify duration

        // Simulate jump key press and release
        assertFalse(gui.isJumpHeld());
    }

    @Test
    void testGetActionNull() throws IOException {
        // Verify that NULL action is returned when no key is pressed
        assertEquals(GUI.ACTION.NULL, gui.getACTION());
    }

    @Test
    void testGetActionLeft() throws IOException {
        KeyEvent leftKeyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(leftKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_LEFT);

        gui.handleKeyPressed(leftKeyEvent);
        assertEquals(GUI.ACTION.LEFT, gui.getACTION());
    }

    @Test
    void testGetActionRight() throws IOException {
        KeyEvent rightKeyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(rightKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_RIGHT);

        gui.handleKeyPressed(rightKeyEvent); // Simulate pressing the RIGHT key
        assertEquals(GUI.ACTION.RIGHT, gui.getACTION()); // Verify the action is RIGHT
    }

    @Test
    void testGetActionUp() throws IOException {
        KeyEvent upKeyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(upKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_UP);

        gui.handleKeyPressed(upKeyEvent); // Simulate pressing the UP key
        assertEquals(GUI.ACTION.UP, gui.getACTION()); // Verify the action is UP
    }

    @Test
    void testGetActionDown() throws IOException {
        KeyEvent downKeyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(downKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_DOWN);

        gui.handleKeyPressed(downKeyEvent); // Simulate pressing the DOWN key
        assertEquals(GUI.ACTION.DOWN, gui.getACTION()); // Verify the action is DOWN
    }

    @Test
    void testGetActionQuit() throws IOException {
        KeyEvent escapeKeyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(escapeKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_ESCAPE);

        gui.handleKeyPressed(escapeKeyEvent); // Simulate pressing the ESC key
        assertEquals(GUI.ACTION.QUIT, gui.getACTION()); // Verify the action is QUIT
    }

    @Test
    void testGetActionKill() throws IOException {
        KeyEvent qKeyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(qKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_Q);

        gui.handleKeyPressed(qKeyEvent); // Simulate pressing the Q key
        assertEquals(GUI.ACTION.KILL, gui.getACTION()); // Verify the action is KILL
    }

    @Test
    void testGetActionJump() throws IOException {
        KeyEvent spaceKeyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(spaceKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_SPACE);

        gui.handleKeyPressed(spaceKeyEvent); // Simulate pressing the SPACE key
        assertEquals(GUI.ACTION.JUMP, gui.getACTION()); // Verify the action is JUMP
    }

    @Test
    void testHandleKeyRelease() {
        KeyEvent spaceKeyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(spaceKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_SPACE);

        // Simulate pressing and releasing the SPACE key
        gui.handleKeyPressed(spaceKeyEvent);
        assertTrue(gui.isJumpHeld()); // Verify the jump key is held

        gui.handleKeyReleased(spaceKeyEvent);
        assertFalse(gui.isJumpHeld()); // Verify the jump key is no longer held
    }

    @Test
    void testKeySpamHandling() throws IOException {
        // Simulate repeated pressing of spam keys (LEFT and RIGHT)
        KeyEvent leftKeyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(leftKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_LEFT);

        KeyEvent rightKeyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(rightKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_RIGHT);

        gui.handleKeyPressed(leftKeyEvent);
        gui.handleKeyPressed(rightKeyEvent);

        // Ensure priority key is set to the most recent spam key
        assertEquals(GUI.ACTION.RIGHT, gui.getACTION());

        gui.handleKeyReleased(rightKeyEvent);
        // After releasing RIGHT, priority returns to LEFT
        //TODO fix key handler spam
        //assertEquals(GUI.ACTION.LEFT, gui.getACTION());
    }

    @Test
    void testPriorityKeyHandling() throws IOException {
        // Simulate pressing LEFT and SPACE together
        KeyEvent leftKeyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(leftKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_LEFT);

        KeyEvent spaceKeyEvent = Mockito.mock(KeyEvent.class);
        Mockito.when(spaceKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_SPACE);

        gui.handleKeyPressed(leftKeyEvent);
        gui.handleKeyPressed(spaceKeyEvent);

        // Verify that SPACE (JUMP) takes priority
        assertEquals(GUI.ACTION.JUMP, gui.getACTION());
    }
}
