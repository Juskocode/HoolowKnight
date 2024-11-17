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
    //General Knight's attributes
    public Knight(int x, int y, int HP, float Damage_multiplier, int Energy){
        super(x,y); //calls the constructor of the Element class, supplying the position in coordinates
        this.HP=HP;
        this.Damage_multiplier = Damage_multiplier;
        this.Energy = Energy;
        this.velocity = new Vector(0,0);
        this.maxVelocity = new Vector(2.0,3.0);
        this.acceleration =0.85;
        this.state = new IdleState(this);
        this.isFacingRight = true;
        //assigns the supplied values (and some other default values) to the Knight's attributes
    }

    //GETTERS
    public int getHP() {
        return HP;
    }

    public boolean isFacingRight() {
        return isFacingRight;
    }

    public float getDamage() {
        return Damage_multiplier;
    }

    public int getEnergy() {
        return Energy;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public Vector getMaxVelocity() {
        return maxVelocity;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public Scene getScene() {
        return scene;
    }

    public KnightState getState() {
        return state;
    }

    //SETTERS

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setFacingRight(boolean facingRight) {
        isFacingRight = facingRight;
    }

    public void setDamage(float damage) {
        this.Damage_multiplier = damage;
    }

    public void setEnergy(int energy) {
        Energy = energy;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public void setMaxVelocity(Vector maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setState(KnightState state) {
        this.state = state;
    }

    public void multiplyDamage(float damage) {
        this.Damage_multiplier = this.Damage_multiplier * damage;
    } // Used for collectables that multiply damage.

    public Vector updateVelocity() {
        return state.updateVelocity(velocity);
    }

    public Position updatePosition() {
        return new Position(getPosition().x() + velocity.x(), getPosition().y() + velocity.y());
    }

    //ACTIONS
    public KnightState getNextState() {
        return state.getNextState();
    }

    public Vector moveLeft() {
        return state.moveKnightLeft();
    }

    public Vector moveRight() {
        return state.moveKnightRight();
    }

    //BOOLS

    public boolean isOverMaxXVelocity() {
        return Math.abs(velocity.x()) > maxVelocity.x();
    }

}
