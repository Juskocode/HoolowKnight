package HollowKnight.model.game.elements.enemies;

import HollowKnight.model.Position;
import HollowKnight.model.game.elements.enemies.MinhoteMonster;
import HollowKnight.model.game.elements.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class MinhoteMonsterTest {
    private MinhoteMonster minhoteMonster;

    @BeforeEach
    void setUp() {
        minhoteMonster = new MinhoteMonster(10, 20);
    }

    @Test
    void testMinhoteMonsterInitialization() {
        assertEquals(10, minhoteMonster.getPosition().x());
        assertEquals(20, minhoteMonster.getPosition().y());
    }

    @Test
    void testInheritanceFromElement() {
        assertInstanceOf(Element.class, minhoteMonster);
    }

    @Test
    void testMinhoteMonsterWithMock() {
        MinhoteMonster mockMinhoteMonster = Mockito.mock(MinhoteMonster.class);

        Position mockPosition = new Position(23, 12);
        Mockito.when(mockMinhoteMonster.getPosition()).thenReturn(mockPosition);

        Position position = mockMinhoteMonster.getPosition();
        assertNotNull(position);

        assertEquals(23, mockMinhoteMonster.getPosition().x());
        assertEquals(12, mockMinhoteMonster.getPosition().y());
    }
}
