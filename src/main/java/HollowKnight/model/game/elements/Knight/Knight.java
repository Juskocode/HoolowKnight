package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.game.elements.Collectables.Collectables;
import HollowKnight.model.game.elements.Element;

public class Knight extends Element {
    private int HP;
    private float Damage_multiplier;
    private int Energy;
    private double velocity;
    public Knight(int x, int y, int HP, float Damage_multiplier, int Energy){
        super(x,y);
        this.HP=HP;
        this.Damage_multiplier = Damage_multiplier;
        this.Energy = Energy;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getHP() {
        return HP;
    }

    public void setDamage(float damage) {
        this.Damage_multiplier = damage;
    }

    public float getDamage() {
        return Damage_multiplier;
    }

    public void multiplyDamage(float damage){
        this.Damage_multiplier = this.Damage_multiplier * damage;
    } // para ser usado quando tivermos collectables que multiplicam a damage

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
