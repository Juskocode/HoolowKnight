package HollowKnight.model.game.elements.enemies;

import HollowKnight.model.Position;
import HollowKnight.model.game.elements.Element;
import HollowKnight.model.game.scene.Scene;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class SwordMonsterTest {
    private SwordMonster swordMonster;

    @BeforeEach
    void setUp() {
        swordMonster = new SwordMonster(50, 60, 40, new Scene(10,10, 0), 50);
    }

    @Test
    void testSwordMonsterInitialization() {
        assertEquals(50, swordMonster.getPosition().x());
        assertEquals(60, swordMonster.getPosition().y());
    }

    @Test
    void testInheritanceFromElement() {
        assertInstanceOf(Element.class, swordMonster);
    }

    @Test
    void testSwordMonsterWithMock() {
        SwordMonster mockSwordMonster = Mockito.mock(SwordMonster.class);

        Position mockPosition = new Position(15, 25);
        Mockito.when(mockSwordMonster.getPosition()).thenReturn(mockPosition);

        Position position = mockSwordMonster.getPosition();
        assertNotNull(position);

        assertEquals(15, mockSwordMonster.getPosition().x());
        assertEquals(25, mockSwordMonster.getPosition().y());
    }
}
