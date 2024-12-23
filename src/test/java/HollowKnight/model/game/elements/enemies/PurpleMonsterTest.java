package HollowKnight.model.game.elements.enemies;

import HollowKnight.model.dataStructs.Position;
import HollowKnight.model.game.elements.Element;
import HollowKnight.model.game.scene.Scene;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PurpleMonsterTest {
    private PurpleMonster purpleMonster;

    @BeforeEach
    void setUp() {
        purpleMonster = new PurpleMonster(30, 40, 30,
                new Scene(10,10, 0),
                40, new Position(8, 9));
    }

    @Test
    void testPurpleMonsterInitialization() {
        assertEquals(30, purpleMonster.getPosition().x());
        assertEquals(40, purpleMonster.getPosition().y());
    }

    @Test
    void testInheritanceFromElement() {
        assertInstanceOf(Element.class, purpleMonster);
    }

    @Test
    void testPurpleMonsterWithMock() {
        PurpleMonster mockPurpleMonster = Mockito.mock(PurpleMonster.class);

        Position mockPosition = new Position(35, 45);
        Mockito.when(mockPurpleMonster.getPosition()).thenReturn(mockPosition);

        Position position = mockPurpleMonster.getPosition();
        assertNotNull(position);

        assertEquals(35, mockPurpleMonster.getPosition().x());
        assertEquals(45, mockPurpleMonster.getPosition().y());
    }
}
