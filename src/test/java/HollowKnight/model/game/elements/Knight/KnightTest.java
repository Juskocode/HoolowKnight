package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
    private Knight knight;
    @BeforeEach
    void setup(){
        this.knight = new Knight(0,0,10, 1.5F,50);
    }
    @Test
    void setHP() {
        int hp = 100;
        knight.setHP(hp);
        Assertions.assertEquals(knight.getHP(),hp);
    }

    @Test
    void setDamage() {
        float damage = 2.0F;
        knight.setDamage(damage);
        Assertions.assertEquals(knight.getDamage(), damage);
    }

    @Test
    void setEnergy() {
        int energy = 100;
        knight.setEnergy(energy);
        Assertions.assertEquals(knight.getEnergy(), energy);
    }

    @Test
    void setMaxVelocity() {
        Vector newVelocity = new Vector(5.0,5.0);
        knight.setMaxVelocity(newVelocity);
        Assertions.assertEquals(newVelocity, knight.getMaxVelocity());
    }


    @Test
    void setState() {

        knight.setState(new WalkingState(knight));
        Assertions.assertInstanceOf(WalkingState.class, knight.getState());
    }
}