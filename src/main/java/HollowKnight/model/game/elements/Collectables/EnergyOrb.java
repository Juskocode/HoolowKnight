package HollowKnight.model.game.elements.Collectables;

import HollowKnight.model.game.elements.Knight.Knight;

public class EnergyOrb extends Collectables{
    private int energy;
    public EnergyOrb(int x, int y, int energy){
        super(x,y,"Health");
        this.energy = energy;
    }

    @Override
    public void benefit(Knight knight){
        knight.setEnergy(knight.getEnergy()+ this.energy);
    }
}
