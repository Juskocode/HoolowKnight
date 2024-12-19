package HollowKnight.model.game.elements.enemies;

import HollowKnight.model.Position;
import HollowKnight.model.game.elements.Element;
import HollowKnight.model.game.scene.Scene;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class GhostMonsterTest {
    private GhostMonster ghostMonster;
    private Scene scene;
    @BeforeEach
    void setUp() {
        this.scene = Mockito.mock(Scene.class);
        ghostMonster = new GhostMonster(10, 20, 10, scene, 15);
    }

    @Test
    void testMinhoteMonsterInitialization() {
        assertEquals(10, ghostMonster.getPosition().x());
        assertEquals(20, ghostMonster.getPosition().y());
    }

    @Test
    void testInheritanceFromElement() {
        assertInstanceOf(Element.class, ghostMonster);
    }

    @Test
    void testMinhoteMonsterWithMock() {
        GhostMonster mockGhostMonster = Mockito.mock(GhostMonster.class);

        Position mockPosition = new Position(23, 12);
        Mockito.when(mockGhostMonster.getPosition()).thenReturn(mockPosition);

        Position position = mockGhostMonster.getPosition();
        assertNotNull(position);

        assertEquals(23, mockGhostMonster.getPosition().x());
        assertEquals(12, mockGhostMonster.getPosition().y());
    }
}
