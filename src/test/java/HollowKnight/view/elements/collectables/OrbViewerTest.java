package HollowKnight.view.elements.collectables;

import HollowKnight.Game;
import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Collectables.SpeedOrb;
import HollowKnight.view.sprites.GameSpriteLoader;
import HollowKnight.view.sprites.Sprite;
import HollowKnight.view.sprites.SpriteLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrbViewerTest {
    private OrbViewer orbViewer;
    private Sprite sprite;
    private GameSpriteLoader gameSpriteLoader;
    private GUI gui;
    @BeforeEach
    void setUp() throws IOException {
        this.gameSpriteLoader = mock(GameSpriteLoader.class);
        this.sprite = mock(Sprite.class);
        this.gui = mock(GUI.class);
        this.orbViewer = new OrbViewer(gameSpriteLoader);

        when(gameSpriteLoader.get("sprites/collectables/speed.png")).thenReturn(sprite);
    }

    @Test
    void draw() throws IOException {
        SpeedOrb speedOrb = new SpeedOrb(56,8,1.1,'s');
        long frameCount = 0;

        /*orbViewer.draw(speedOrb, gui, frameCount,0,0);

        verify(sprite, times(1)).draw(gui, (int)speedOrb.getPosition().x(), (int)speedOrb.getPosition().y());*/
    }
}