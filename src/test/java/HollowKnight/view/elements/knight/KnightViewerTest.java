package HollowKnight.view.elements.knight;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Knight.FallingState;
import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.model.game.scene.Scene;
import HollowKnight.view.sprites.Sprite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KnightViewerTest {

    private Scene scene;
    private GUI gui;

    @BeforeEach
    public void setup() {
        this.scene = mock(Scene.class);
        this.gui = mock(GUI.class);
    }

    @Test
    public void draw() throws IOException, IOException {
        Sprite sprite1 = mock(Sprite.class);
        Knight player = new Knight(20,30,50,3f, 50);

        KnightViewer knightViewer = new KnightViewer();
        long frameCount = 1000;

        player.setFacingRight(false);
        player.setState(new FallingState(player));
        knightViewer.draw(player, gui, frameCount,0,0);

        verify(sprite1, times(1))
                .draw(gui, (int)player.getPosition().x(), (int)player.getPosition().y());

    }
}