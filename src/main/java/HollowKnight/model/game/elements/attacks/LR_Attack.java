package HollowKnight.model.game.elements.attacks;
//ataques "Long Range"
public class LR_Attack extends Attack {
    private int proj_speed;

    public LR_Attack(int hp, int energy_loss, int range, int proj_speed) {
        super(hp, energy_loss, range);
        this.proj_speed = proj_speed;
    }
    //proj_speed ---> velocidade dos projeteis disparados

    public int getProj_speed(){
        return proj_speed;
    }

    public void setProj_speed(int speed){
        this.proj_speed = speed;
    }
}
