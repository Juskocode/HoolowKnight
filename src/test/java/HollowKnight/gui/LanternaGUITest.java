package HollowKnight.gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import net.jqwik.api.ForAll;
import net.jqwik.api.From;
import net.jqwik.api.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class LanternaGUITest {
    private Screen screen;
    private TextGraphics tg;
    @BeforeEach
    void setUp(){
        this.screen = Mockito.mock(Screen.class);
        this.tg = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(tg);
    }
    @Test
    void drawPixel() throws IOException, URISyntaxException, FontFormatException {
        GUI gui = new LanternaGUI(40, 80);
        gui.drawPixel(10,10,"#FF5733");
    }
}