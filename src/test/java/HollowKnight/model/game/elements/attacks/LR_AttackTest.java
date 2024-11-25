package HollowKnight.model.game.elements.attacks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

class LR_AttackTest {

    @Test
    void testGetProj_speed() {
        LR_Attack attack = new LR_Attack(100, 50, 20, 10);
        assertEquals(10, attack.getProj_speed());
    }

    @Test
    void testSetProj_speed() {
        LR_Attack attack = new LR_Attack(100, 50, 20, 10);
        attack.setProj_speed(15);
        assertEquals(15, attack.getProj_speed());
    }

    @Test
    void testInheritance() {
        LR_Attack attack = Mockito.mock(LR_Attack.class, Mockito.CALLS_REAL_METHODS);
        attack.setHp_loss(90);
        assertEquals(90, attack.getHp_loss());
    }

    @Test
    void testConstructorValues() {
        LR_Attack attack = new LR_Attack(100, 50, 20, 10);
        assertEquals(100, attack.getHp_loss());
        assertEquals(50, attack.getEnergy_loss());
        assertEquals(20, attack.getRange());
        assertEquals(10, attack.getProj_speed());
    }
}
