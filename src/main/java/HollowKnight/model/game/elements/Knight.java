package HollowKnight.model.game.elements;

import HollowKnight.model.Position;

public class Knight extends Element{
    private int HP;
    private float Damage_multiplier;
    private int Energy;
    public Knight(int x, int y, int HP, float Damage_multiplier, int Energy){
        super(x,y);
        this.HP=HP;
        this.Damage_multiplier = Damage_multiplier;
        this.Energy = Energy;
    }

    public int getHP(){
        return this.HP;
    }
    public void setHP(int HP){
        this.HP = HP;
    }
    public float getDamage_multiplier(){
        return this.Damage_multiplier;
    }
    public void setDamage_multiplier(float Damage){
        this.Damage_multiplier = Damage;
    }
    public void multiplyDamage(float Damage){
        this.Damage_multiplier = this.Damage_multiplier * Damage;
    } // para ser usado quando tivermos collectables que multiplicam a damage
    public int getEnergy(){
        return this.Energy;
    }
    public void setEnergy(int Energy){
        this.Energy = Energy;
    }
}
