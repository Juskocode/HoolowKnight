package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Position;
import HollowKnight.model.Vector;
import HollowKnight.model.game.elements.Collectables.Collectables;
import HollowKnight.model.game.elements.Element;
import HollowKnight.model.game.scene.Scene;

public class Knight extends Element {
    private KnightState state;
    private int HP;
    private float Damage_multiplier;
    private int Energy;
    private Vector velocity;
    private Vector maxVelocity;
    private double acceleration;
    private Scene scene;
    private boolean isFacingRight;
    public Knight(int x, int y, int HP, float Damage_multiplier, int Energy){
        super(x,y);
        this.HP=HP;
        this.Damage_multiplier = Damage_multiplier;
        this.Energy = Energy;
        this.velocity = new Vector(0,0);
        this.maxVelocity = new Vector(2.0,3.0);
        this.acceleration =0.75;
        this.state = new IdleState(this);
        this.isFacingRight = true;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getHP() {
        return HP;
    }

    public boolean isFacingRight() {
        return isFacingRight;
    }

    public void setFacingRight(boolean facingRight) {
        isFacingRight = facingRight;
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

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public Vector getMaxVelocity() {
        return maxVelocity;
    }

    public void setMaxVelocity(Vector maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public Vector updateVelocity(){
        return state.updateVelocity(velocity);
    }

    public boolean isOverMaxXVelocity() {
        return Math.abs(velocity.x()) > maxVelocity.x();
    }

    public Vector moveLeft(){
        return state.moveKnightLeft();
    }

    public Vector moveRight(){
        return state.moveKnightRight();
    }
  
    public Position updatePosition() {
        return new Position(getPosition().x() + velocity.x(), getPosition().y() + velocity.y());
    }
  
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    public void setState(KnightState state) {
        this.state = state;
    }

    public KnightState getState() {
        return state;
    }
}
