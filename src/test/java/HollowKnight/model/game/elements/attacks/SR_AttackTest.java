package HollowKnight.model.game.elements.attacks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

class SR_AttackTest {

    @Test
    void testConstructorValues() {
        SR_Attack attack = new SR_Attack(80, 30, 15);
        assertEquals(80, attack.getHp_loss());
        assertEquals(30, attack.getEnergy_loss());
        assertEquals(15, attack.getRange());
    }

    @Test
    void testInheritance() {
        SR_Attack attack = Mockito.mock(SR_Attack.class, Mockito.CALLS_REAL_METHODS);
        attack.setEnergy_loss(25);
        assertEquals(25, attack.getEnergy_loss());
    }
}
