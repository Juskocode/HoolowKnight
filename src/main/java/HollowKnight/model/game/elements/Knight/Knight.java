package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.game.elements.Collectables.Collectables;
import HollowKnight.model.game.elements.Element;

public class Knight extends Element {
    private int HP;
    private int Damage;
    private int Energy;
    private double velocity;
    public Knight(int x, int y, int HP, int Damage, int Energy){
        super(x,y);
        this.HP=HP;
        this.Damage = Damage;
        this.Energy = Energy;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getHP() {
        return HP;
    }

    public void setDamage(int damage) {
        this.Damage = damage;
    }

    public int getDamage() {
        return Damage;
    }

    public void setEnergy(int energy) {
        Energy = energy;
    }

    public int getEnergy() {
        return Energy;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getVelocity() {
        return velocity;
    }

}
