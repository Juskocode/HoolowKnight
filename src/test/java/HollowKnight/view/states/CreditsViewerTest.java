package HollowKnight.view.states;

import HollowKnight.gui.RescalableGUI;
import HollowKnight.model.credits.Credits;
import HollowKnight.view.menu.LogoViewer;
import HollowKnight.view.sprites.ViewerProvider;
import HollowKnight.view.text.TextViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static HollowKnight.view.states.CreditsViewer.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class CreditsViewerTest {
    private CreditsViewer creditsViewer;
    private RescalableGUI gui;
    private LogoViewer logoViewer;
    private TextViewer textViewer;

    @BeforeEach
    public void setup() {
        this.gui = mock(RescalableGUI.class);

        Credits credits = mock(Credits.class);
        when(credits.getDeaths()).thenReturn(0);
        when(credits.getScore()).thenReturn(0);
        when(credits.getMinutes()).thenReturn(0);
        when(credits.getSeconds()).thenReturn(0);
        String[] names = new String[1];
        names[0] = "name test 1";
        when(credits.getNames()).thenReturn(names);
        String[] messages = new String[1];
        messages[0] = "message test 1";
        when(credits.getMessages()).thenReturn(messages);

        ViewerProvider viewerProvider = mock(ViewerProvider.class);
        this.textViewer = mock(TextViewer.class);
        this.logoViewer = mock(LogoViewer.class);
        when(viewerProvider.getTextViewer()).thenReturn(textViewer);
        when(viewerProvider.getLogoViewer()).thenReturn(logoViewer);

        this.creditsViewer = new CreditsViewer(credits, viewerProvider);
    }


    @Test
    public void draw() throws IOException {
        creditsViewer.draw(gui,0);

        verify(gui, times(1)).cls();


        // Drawing Logo
        verify(logoViewer, times(1)).draw(same(gui), anyInt(), anyInt());

        // Drawing Messages
        verify(textViewer, times(1))
                .draw(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble(),
                        eq(messageColor), eq(gui));

        // Drawing Names
        verify(textViewer, times(1))
                .draw(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble(),
                        eq(nameColor), eq(gui));

        // Drawing Score
        verify(textViewer, times(1))
                .draw(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble(),
                        eq(scoreColor), eq(gui));

        // Drawing Deaths
        verify(textViewer, times(1))
                .draw(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble(),
                        eq(deathColor), eq(gui));

        // Drawing Duration
        verify(textViewer, times(1))
                .draw(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble(),
                        eq(timeColor), eq(gui));
        verify(gui, times(1)).flush();
    }
}