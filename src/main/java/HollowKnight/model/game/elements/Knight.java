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
}
