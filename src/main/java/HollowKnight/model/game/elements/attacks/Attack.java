package HollowKnight.model.game.elements.attacks;

public abstract class Attack {
    private int hp_loss;
    private int energy_loss;
    private int range;

    public Attack(int hp, int energy_loss, int range){
        this.hp_loss = hp;
        this.energy_loss = energy_loss;
        this.range = range;
    }

    public int getHp_loss(){
        return this.hp_loss;
    }

    public void setHp_loss(int hpLoss){
        this.hp_loss = hpLoss;
    }

    public int getEnergy_loss(){
        return this.energy_loss;
    }

    public void setEnergy_loss(int Energy_Loss){
        this.energy_loss = Energy_Loss;
    }

    public int getRange(){
        return this.range;
    }

    public void setRange(int Range){
        this.range = Range;
    }



}
