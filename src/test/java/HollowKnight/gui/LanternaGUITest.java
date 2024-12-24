package HollowKnight.gui;

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
        RescalableGUI.ResolutionScale resolutionScale = RescalableGUI.ResolutionScale.WXGA;

        gui.setResolutionScale(resolutionScale);

        verify(screen, times(1)).close();
        assertEquals(resolutionScale, gui.getResolutionScale());
        verify(screenGenerator, times(1)).createScreen(resolutionScale, title, gui.getKeyAdapter());
    }

    @Property
    public void drawPixel(@ForAll int x, @ForAll int y, @ForAll @From("color") TextColor.RGB color) throws IOException, URISyntaxException, FontFormatException {
        GUI gui = new LanternaGUI(screenGenerator, "drawPixel test");

        gui.drawPixel(x, y,color);

        verify(tg, Mockito.times(1)).setBackgroundColor(color);
        verify(tg, Mockito.times(1)).putString(x, y, " ");
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

    @Test
    public void drawText() throws IOException, URISyntaxException, FontFormatException {
        LanternaGUI gui = new LanternaGUI(screenGenerator, "draw text test");
        when(screen.newTextGraphics()).thenReturn(tg);
        gui.drawText(1,1,new TextColor.RGB(0,0,0),"teste");
        verify(tg, times(1)).putString(1,1,"teste");
    }


    @Test
    public void getNextActionWithoutKeySpam() throws IOException, URISyntaxException, FontFormatException {
        LanternaGUI gui = new LanternaGUI(screenGenerator, "getNextAction without key spam test");
        KeyAdapter keyAdapter = gui.getKeyAdapter();

        Map<Integer, GUI.ACTION> keyToAction = new HashMap<>();
        keyToAction.put(VK_LEFT, GUI.ACTION.LEFT);
        keyToAction.put(VK_RIGHT, GUI.ACTION.RIGHT);
        keyToAction.put(VK_UP, GUI.ACTION.UP);
        keyToAction.put(VK_DOWN, GUI.ACTION.DOWN);
        keyToAction.put(VK_ESCAPE, GUI.ACTION.QUIT);
        keyToAction.put(VK_ENTER, GUI.ACTION.SELECT);
        keyToAction.put(VK_SPACE, GUI.ACTION.JUMP);
        keyToAction.put(VK_X, GUI.ACTION.DASH);
        keyToAction.put(VK_T, GUI.ACTION.NULL);

        gui.getACTION();
        GUI.ACTION action1 = gui.getACTION();
        assertEquals(GUI.ACTION.NULL, action1);

        for (Map.Entry<Integer, GUI.ACTION> entry: keyToAction.entrySet()) {
            KeyEvent event = mock(KeyEvent.class);
            when(event.getKeyCode()).thenReturn(entry.getKey());

            keyAdapter.keyPressed(event);
            GUI.ACTION action2 = gui.getACTION();
            GUI.ACTION action3 = gui.getACTION();
            keyAdapter.keyReleased(event);
            keyAdapter.keyPressed(event);
            keyAdapter.keyReleased(event);
            GUI.ACTION action4 = gui.getACTION();

            assertEquals(entry.getValue(), action2);
            assertEquals(GUI.ACTION.NULL, action4);
        }
    }

}