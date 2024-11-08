package HollowKnight.model.game.elements;

public abstract class Enemies extends Element{
    private int HP;
    private int Damage;

    public Enemies(int HP, int Damage) {
        this.HP = HP;
        this.Damage = Damage;
    }
    public boolean isAlive(){
        return this.HP >0;
    }

    public int getHP() {
        return HP;
    }

    public int getDamage() {
        return Damage;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

}
