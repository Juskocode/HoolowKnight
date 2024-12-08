package HollowKnight.model.game.elements.enemies;

import HollowKnight.model.game.elements.enemies.Enemies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class EnemiesTest {
    private Enemies enemy;

    // A concrete implementation of Enemies for testing purposes
    /*static class TestEnemy extends Enemies {
        public TestEnemy(int HP, int Damage) {
            super(HP, Damage);
        }
    }

    @BeforeEach
    void setUp() {
        this.enemy = new TestEnemy(100, 20);
    }

    @Test
    void testIsAlive() {
        assertTrue(enemy.isAlive());
        enemy.setHP(0);
        assertFalse(enemy.isAlive());
    }

    @Test
    void testGetHP() {
        assertEquals(100, enemy.getHP());
    }



    @Test
    void testSetHP() {
        enemy.setHP(50);
        assertEquals(50, enemy.getHP());
    }

    @Test
    void testEnemyWithMock() {
        // Using Mockito to create a mock of Enemies
        Enemies mockEnemy = Mockito.mock(Enemies.class);

        Mockito.when(mockEnemy.getHP()).thenReturn(80);

        Mockito.when(mockEnemy.isAlive()).thenReturn(true);

        assertEquals(80, mockEnemy.getHP());
        assertTrue(mockEnemy.isAlive());
    }*/
}
