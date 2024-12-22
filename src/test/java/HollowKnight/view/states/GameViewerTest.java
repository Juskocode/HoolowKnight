package HollowKnight.view.states;

import HollowKnight.gui.RescalableGUI;
import HollowKnight.model.game.elements.Collectables.SpeedOrb;
import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.model.game.elements.tile.Tile;
import HollowKnight.model.game.scene.Scene;
import HollowKnight.view.elements.ParticleViewer;
import HollowKnight.view.elements.collectables.SpeedOrbViewer;
import HollowKnight.view.elements.knight.KnightViewer;
import HollowKnight.view.elements.tile.TileViewer;
import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameViewerTest {
    private Scene scene;
    //private ViewerProvider viewerProvider;
    private ParticleViewer particleViewer;
    private KnightViewer playerViewer;
    private SpeedOrbViewer speedOrbViewer;
    private TileViewer tileViewer;
    private RescalableGUI rescalableGUI;

    @BeforeEach
    public void setup() {
        this.scene = new Scene(2, 2, 0);
        scene.setPlayer(new Knight(0, 0, 50,0,0));
        scene.setTiles(new Tile[][] { { null, null },
                { new Tile(0, 8, 'T') }});
        scene.setSpeedOrbs(new SpeedOrb[][] { { null, new SpeedOrb(8, 0,60) }, { null, null }});

        //this.viewerProvider = mock(ViewerProvider.class);
        this.particleViewer = mock(ParticleViewer.class);
        this.playerViewer = mock(KnightViewer.class);
        //this.spikeViewer = mock(SpikeViewer.class);
        this.speedOrbViewer = mock(SpeedOrbViewer.class);
        this.tileViewer = mock(TileViewer.class);
        this.rescalableGUI = mock(RescalableGUI.class);

        /*when(viewerProvider.getParticleViewer()).thenReturn(particleViewer);
        when(viewerProvider.getPlayerViewer()).thenReturn(playerViewer);
        when(viewerProvider.getSpikeViewer()).thenReturn(spikeViewer);
        when(viewerProvider.getStarViewer()).thenReturn(starViewer);
        when(viewerProvider.getTileViewer()).thenReturn(tileViewer);*/
    }

    @Test
    public void draw() throws IOException {
        GameViewer gameViewer = new GameViewer(scene/*,viewerProvider*/);
        long frameCount = 100;
        int screenWidth = 16, screenHeight = 16;
        when(rescalableGUI.getWidth()).thenReturn(screenWidth);
        when(rescalableGUI.getHeight()).thenReturn(screenHeight);

        gameViewer.draw(rescalableGUI, frameCount);

        verify(rescalableGUI, times(1)).cls();
        verify(rescalableGUI, times(1))
                .drawRectangle(0, 0, screenWidth, screenHeight, new TextColor.RGB(0,0,0));
        verify(playerViewer, times(1)).draw(scene.getPlayer(), rescalableGUI, frameCount,0,0);
        verify(tileViewer, times(1)).draw(scene.getTiles()[1][0], rescalableGUI, frameCount,0,0);
        //verify(spikeViewer, times(1)).draw(scene.getSpikes()[1][1], gui, frameCount);
        verify(speedOrbViewer, times(1)).draw(scene.getSpeedOrbs()[0][1], rescalableGUI, frameCount,0,0);
        verify(rescalableGUI, times(1)).flush();
    }

}