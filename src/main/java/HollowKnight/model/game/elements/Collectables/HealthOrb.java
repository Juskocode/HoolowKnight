package HollowKnight.model.game.elements.Collectables;

import HollowKnight.model.game.elements.Knight.Knight;

public class HealthOrb extends Collectables{
    private int health;
    public HealthOrb(int x, int y, int health){
        super(x,y,"Health");
        this.health = health;
    }

    @Override
    public void benefit(Knight knight){
        knight.setHP(knight.getHP()+ this.health);
    }
}
