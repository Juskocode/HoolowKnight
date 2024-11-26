package HollowKnight.model.game.elements.Collectables;

import HollowKnight.model.Vector;
import HollowKnight.model.game.elements.Knight.Knight;

public class SpeedOrb extends Collectables{
    private double speed_boost = 1.1;
    public SpeedOrb(int x, int y, double boost){
        super(x,y,"Speed");
        this.speed_boost = boost;
    }

    public double getSpeed_boost() {
        return speed_boost;
    }

    public void setSpeed_boost(double speed_boost) {
        this.speed_boost = speed_boost;
    }

    @Override
    public void benefit(Knight knight){
        knight.setMaxVelocity(new Vector(knight.getMaxVelocity().x()*speed_boost,
                knight.getMaxVelocity().y()*speed_boost));
    }
}
