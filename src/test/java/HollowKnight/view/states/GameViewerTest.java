package HollowKnight.view.states;

import HollowKnight.gui.RescalableGUI;
import HollowKnight.model.dataStructs.Position;
import HollowKnight.model.game.elements.Collectables.Collectables;
import HollowKnight.model.game.elements.Collectables.SpeedOrb;
import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.model.game.elements.Spike;
import HollowKnight.model.game.elements.enemies.Enemies;
import HollowKnight.model.game.elements.enemies.PurpleMonster;
import HollowKnight.model.game.elements.tile.Tile;
import HollowKnight.model.game.scene.Scene;
import HollowKnight.view.elements.collectables.OrbViewer;
import HollowKnight.view.elements.monsters.MonsterViewer;
import HollowKnight.view.elements.particle.ParticleViewer;
import HollowKnight.view.elements.knight.KnightViewer;
import HollowKnight.view.elements.rocks.RockViewer;
import HollowKnight.view.elements.spike.SpikeViewer;
import HollowKnight.view.elements.tile.TileViewer;
import HollowKnight.view.elements.tree.TreeViewer;
import HollowKnight.view.sprites.ViewerProvider;
import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class GameViewerTest {

    private Scene scene;
    private ViewerProvider viewerProvider;
    private ParticleViewer particleViewer;
    private KnightViewer playerViewer;
    private SpikeViewer spikeViewer;
    private OrbViewer orbViewer;
    private TileViewer tileViewer;
    private RescalableGUI gui;
    private TreeViewer treeViewer;
    private RockViewer rockViewer;
    private MonsterViewer monsterViewer;

    @BeforeEach
    public void setup() {
        this.scene = new Scene(10, 10, 0);
        scene.setPlayer(new Knight(0, 0, 50,0,0));
        scene.getPlayer().setScene(scene);
        scene.setTiles(new Tile[][] { { null, null },
                { new Tile(0, 8, 'T') }});

        scene.setSpikes(new Spike[][] { { null, null },
                { null, new Spike(8, 8, '^') }});
        scene.setOrbs(new Collectables[][] { { null, new SpeedOrb(8, 0,1.1,'s') }, { null, null }});
        List<Enemies> monsters = new ArrayList<>();
        monsters.add(new PurpleMonster(0,0,50,scene,0,new Position(0,0),'p'));
        scene.setMonsters(monsters);

        this.viewerProvider = mock(ViewerProvider.class);
        this.particleViewer = mock(ParticleViewer.class);
        this.playerViewer = mock(KnightViewer.class);
        this.spikeViewer = mock(SpikeViewer.class);
        this.orbViewer = mock(OrbViewer.class);
        this.tileViewer = mock(TileViewer.class);
        this.treeViewer = mock(TreeViewer.class);
        this.rockViewer = mock(RockViewer.class);
        this.gui = mock(RescalableGUI.class);

        when(viewerProvider.getParticleViewer()).thenReturn(particleViewer);
        when(viewerProvider.getPlayerViewer()).thenReturn(playerViewer);
        when(viewerProvider.getSpikeViewer()).thenReturn(spikeViewer);
        when(viewerProvider.getOrbViewer()).thenReturn(orbViewer);
        when(viewerProvider.getTileViewer()).thenReturn(tileViewer);
        when(viewerProvider.getMonsterViewer()).thenReturn(monsterViewer);
        when(viewerProvider.getTreeViewer()).thenReturn(treeViewer);
        when(viewerProvider.getRockViewer()).thenReturn(rockViewer);

        scene.setParticles(new ArrayList<>());
        scene.setRespawnParticles(new ArrayList<>());
        scene.setDashParticles(new ArrayList<>());
        scene.setJumpParticles(new ArrayList<>());
        scene.setDoubleJumpParticles(new ArrayList<>());
    }

    /*@Test
    public void draw() throws IOException {
        GameViewer gameViewer = new GameViewer(scene, viewerProvider);
        long frameCount = 100;
        int screenWidth = 16, screenHeight = 16;
        when(gui.getWidth()).thenReturn(screenWidth);
        when(gui.getHeight()).thenReturn(screenHeight);

        gameViewer.draw(gui, frameCount);

        verify(gui, times(1)).cls();
        verify(gui, times(1))
                .drawRectangle(0, 0, screenWidth, screenHeight, any(TextColor.RGB.class));
        verify(playerViewer, times(1)).draw(scene.getPlayer(), gui, frameCount, any(),any());
        verify(monsterViewer,times(1)).draw(scene.getMonsters().get(0),gui,frameCount,any(),any());
        verify(tileViewer, times(1)).draw(scene.getTiles()[1][0], gui, frameCount,any(),any());
        verify(spikeViewer, times(1)).draw(scene.getSpikes()[1][1], gui, frameCount,any(),any());
        verify(orbViewer, times(1)).draw(scene.getOrbs()[0][1], gui, frameCount,any(),any());
        verify(gui, times(1)).flush();
    }*/

}