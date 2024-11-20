package HollowKnight.model.game.elements.Collectables;

import HollowKnight.model.Vector;
import HollowKnight.model.game.elements.Knight.Knight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpeedOrbTest {
    private SpeedOrb speedOrb;
    private Knight knight;
    @BeforeEach
    void setUp() {
        this.knight = new Knight(0,0,10,1,50);
        this.speedOrb = new SpeedOrb(0,0,1.25);
    }

    @Test
    void setSpeed_boost() {
        double newspeed = 1.4;
        speedOrb.setSpeed_boost(newspeed);
        Assertions.assertEquals(newspeed, speedOrb.getSpeed_boost());
    }

    @Test
    void benefit() {
        Vector expected = new Vector(knight.getMaxVelocity().x()*speedOrb.getSpeed_boost(),
                        knight.getMaxVelocity().y()* speedOrb.getSpeed_boost());
        speedOrb.benefit(knight);
        Assertions.assertEquals(expected, knight.getMaxVelocity());
    }
}