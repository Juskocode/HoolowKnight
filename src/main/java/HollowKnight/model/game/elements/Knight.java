package HollowKnight.model.game.elements;

import HollowKnight.model.Position;

public class Knight extends Element{
    private int HP;
    private int Damage;
    private int Energy;
    public Knight(int x, int y, int HP, int Damage, int Energy){
        super(x,y);
        this.HP=HP;
        this.Damage = Damage;
        this.Energy = Energy;
    }
}
