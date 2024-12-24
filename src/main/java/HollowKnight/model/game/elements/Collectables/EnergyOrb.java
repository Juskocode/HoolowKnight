package HollowKnight.model.game.elements.Collectables;

import HollowKnight.model.game.elements.Knight.Knight;

public class EnergyOrb extends Collectables{
    private int energy;

    private final char symbol;
    public EnergyOrb(int x, int y, int energy, char symbol){
        super(x,y);
        this.energy = energy;
        this.symbol = symbol;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public void benefit(Knight knight){
        knight.setEnergy(knight.getEnergy()+ this.energy);
    }

    @Override
    public char getChar() {
        return symbol;
    }

}
