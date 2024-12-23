package HollowKnight.model.game.elements.Collectables;

import HollowKnight.model.game.elements.Knight.Knight;

public class HealthOrb extends Collectables{
    private int health;
    private final char symbol;
    public HealthOrb(int x, int y, int health, char symbol){
        super(x,y);
        this.health = health;
        this.symbol = symbol;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void benefit(Knight knight){
        knight.setHP(knight.getHP()+ this.health);
    }

    @Override
    public char getChar() {
        return symbol;
    }
}
