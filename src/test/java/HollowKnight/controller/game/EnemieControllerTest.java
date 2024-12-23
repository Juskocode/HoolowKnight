package HollowKnight.controller.game;

import HollowKnight.Game;
import HollowKnight.gui.GUI;
import HollowKnight.model.dataStructs.Position;
import HollowKnight.model.game.elements.enemies.GhostMonster;
import HollowKnight.model.game.elements.enemies.PurpleMonster;
import HollowKnight.model.game.elements.enemies.SwordMonster;
import HollowKnight.model.game.scene.Scene;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class EnemieControllerTest {
    private Game game;
    private EnemieController enemieController;
    private Scene scene;
    @BeforeEach
    void setUp() {
        this.game = Mockito.mock(Game.class);
        this.scene = Mockito.mock(Scene.class);
        this.enemieController = new EnemieController(scene);
    }

    @Test
    void move() throws IOException {
        List<PurpleMonster> purpleMonsters = new ArrayList<>();
        List<SwordMonster> swordMonsters = new ArrayList<>();
        List<GhostMonster> ghostMonsters = new ArrayList<>();

        SwordMonster swordMonster = mock(SwordMonster.class);
        GhostMonster ghostMonster = mock(GhostMonster.class);
        PurpleMonster purpleMonster = mock(PurpleMonster.class);


        purpleMonsters.add(purpleMonster);
        swordMonsters.add(swordMonster);
        ghostMonsters.add(ghostMonster);

        when(purpleMonster.moveMonster()).thenReturn(new Position(5, 6));
        when(ghostMonster.moveMonster()).thenReturn(new Position(6, 7));
        when(swordMonster.moveMonster()).thenReturn(new Position(6,6));

        when(scene.getPurpleMonsters()).thenReturn(purpleMonsters);
        when(scene.getSwordMonsters()).thenReturn(swordMonsters);
        when(scene.getGhostMonsters()).thenReturn(ghostMonsters);

        enemieController.move(game, GUI.ACTION.NULL,20);

        verify(purpleMonster,times(1)).setPosition(new Position(5,6));
        verify(ghostMonster, times(1)).setPosition(new Position(6,7));
        verify(swordMonster,times(1)).setPosition(new Position(6,6));

    }
}