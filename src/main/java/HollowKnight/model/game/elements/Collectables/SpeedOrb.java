package HollowKnight.model.game.elements.Collectables;

import HollowKnight.model.dataStructs.Vector;
import HollowKnight.model.game.elements.Knight.Knight;

public class SpeedOrb extends Collectables{
    private double speed_boost = 1.1;
    private final char symbol;
    public SpeedOrb(int x, int y, double boost, char symbol){
        super(x,y);
        this.speed_boost = boost;
        this.symbol = symbol;
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
                knight.getMaxVelocity().y()));
    }

    @Override
    public char getChar() {
        return symbol;
    }
}
