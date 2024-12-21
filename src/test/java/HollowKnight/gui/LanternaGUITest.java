package timelessodyssey.gui;

import HollowKnight.gui.GUI;
import HollowKnight.gui.LanternaGUI;
import HollowKnight.gui.RescalableGUI;
import HollowKnight.gui.ScreenGenerator;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import net.jqwik.api.*;
import net.jqwik.api.lifecycle.BeforeTry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static java.awt.event.KeyEvent.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class LanternaGUITest {
    private static final int SCREEN_WIDTH = 160;
    private static final int SCREEN_HEIGHT = 160;

    private ScreenGenerator screenGenerator;
    private Screen screen;
    private TextGraphics tg;

    @BeforeTry
    @BeforeEach
    public void setup() throws IOException, URISyntaxException, FontFormatException {
        this.screenGenerator = mock(ScreenGenerator.class);
        this.screen = mock(Screen.class);
        this.tg = mock(TextGraphics.class);

        when(screenGenerator.createScreen(any(), any(), any())).thenReturn(screen);
        when(screenGenerator.getWidth()).thenReturn(SCREEN_WIDTH);
        when(screenGenerator.getHeight()).thenReturn(SCREEN_HEIGHT);

        when(screen.newTextGraphics()).thenReturn(tg);
    }

    @Test
    public void constructor() throws IOException, URISyntaxException, FontFormatException {
        String title = "constructor test";
        LanternaGUI gui = new LanternaGUI(screenGenerator, title);
        RescalableGUI.ResolutionScale resolution = gui.getResolutionScale();
        int width = gui.getWidth();
        int height = gui.getHeight();

        assertNull(resolution);
        assertEquals(SCREEN_WIDTH, width);
        assertEquals(SCREEN_HEIGHT, height);
        verify(screen, times(1)).setCursorPosition(null);
        verify(screen, times(1)).startScreen();
        verify(screenGenerator, times(1)).createScreen(null, title, gui.getKeyAdapter());
    }



    @Test
    public void setResolution() throws IOException, URISyntaxException, FontFormatException {
        String title = "setResolution test";
        LanternaGUI gui = new LanternaGUI(screenGenerator, title);
        RescalableGUI.ResolutionScale resolutionScale = RescalableGUI.ResolutionScale.FOUR_K;

        gui.setResolutionScale(resolutionScale);

        verify(screen, times(1)).close();
        assertEquals(resolutionScale, gui.getResolutionScale());
        verify(screenGenerator, times(1)).createScreen(resolutionScale, title, gui.getKeyAdapter());
    }

    @Property
    public void drawPixel(@ForAll int x, @ForAll int y, @ForAll @From("color") TextColor.RGB color) throws IOException, URISyntaxException, FontFormatException {
        GUI gui = new LanternaGUI(screenGenerator, "drawPixel test");

        gui.drawPixel(x, y,color );

        verify(tg, Mockito.times(1)).setBackgroundColor(color);
        verify(tg, Mockito.times(1)).setCharacter(x, y, ' ');
        verifyNoMoreInteractions(tg);
    }

    @Property
    public void drawRectangle(
            @ForAll int x,
            @ForAll int y,
            @ForAll int width,
            @ForAll int height,
            @ForAll @From("color") TextColor.RGB color
    ) throws IOException, URISyntaxException, FontFormatException {
        GUI gui = new LanternaGUI(screenGenerator, "drawRectangle test");

        gui.drawRectangle(x, y, width, height, color);

        if (width > 0 && height > 0) {
            verify(tg, Mockito.times(1)).setBackgroundColor(color);
            verify(tg, Mockito.times(1))
                    .fillRectangle(new TerminalPosition(x, y), new TerminalSize(width, height), ' ');
        }
        else {
            verify(tg, Mockito.times(0)).setBackgroundColor(any(TextColor.class));
            verify(tg, Mockito.times(0))
                    .fillRectangle(any(TerminalPosition.class), any(TerminalSize.class), anyChar());
        }
        verifyNoMoreInteractions(tg);
    }

    @Test
    public void cls() throws IOException, URISyntaxException, FontFormatException {
        LanternaGUI gui = new LanternaGUI(screenGenerator, "clear test");

        gui.cls();

        verify(screen, atLeastOnce()).clear();
    }

    @Test
    public void flush() throws IOException, URISyntaxException, FontFormatException {
        LanternaGUI gui = new LanternaGUI(screenGenerator, "refresh test");
        gui.flush();
        verify(screen, atLeastOnce()).refresh();
    }

    @Test
    public void close() throws IOException, URISyntaxException, FontFormatException {
        LanternaGUI gui = new LanternaGUI(screenGenerator, "close test");
        gui.close();
        verify(screen, times(1)).close();
    }

    @Provide
    public Arbitrary<TextColor> color() {
        return Combinators.combine(
                Arbitraries.integers().between(0, 255),
                Arbitraries.integers().between(0, 255),
                Arbitraries.integers().between(0, 255)
        ).as(TextColor.RGB::new);
    }
}