package HollowKnight.gui;

import HollowKnight.model.Position;
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
    private GUI gui;
    @BeforeEach
    void setUp(){
        this.screen = Mockito.mock(Screen.class);
        this.tg = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(tg);
        //this.gui = new LanternaGUI(screen);

    }
    @Test
    void drawText() throws IOException, URISyntaxException, FontFormatException {
        /*TextColor.RGB color = new TextColor.RGB(0,0,0);
        gui.drawText(1,1, color ,"Hello World");

        Mockito.verify(tg, Mockito.times(1))
                .setBackgroundColor(new TextColor.RGB(0, 0, 0));
        Mockito.verify(tg, Mockito.times(1))
                .putString(1, 1, "Hello World");*/
    }
}