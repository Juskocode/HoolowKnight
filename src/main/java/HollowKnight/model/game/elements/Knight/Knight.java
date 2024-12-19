package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Position;
import HollowKnight.model.Vector;
import HollowKnight.model.game.elements.Element;
import HollowKnight.model.game.elements.Particle.*;
import HollowKnight.model.game.scene.Scene;
import com.googlecode.lanterna.TextColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Knight extends Element {

    private static final int WIDTH = 7;
    private static final int HEIGHT = 8;
    private KnightState state;
    private int jumpCounter;
    private int HP;
    private float Damage_multiplier;
    private int Energy;
    private Vector velocity;
    private Vector maxVelocity;
    private double acceleration;
    private Scene scene;
    private boolean isFacingRight;
    private double jumpBoost;
    private double dashBoost;
    private final int offSetX = 4;
    private final int offSetY = 1;
    private boolean gotHit;

    private Position startingPosition;

    //General Knight's attributes
    public Knight(int x, int y, int HP, float Damage_multiplier, int Energy){
        super(x, y); //calls the constructor of the Element class, supplying the position in coordinates
        this.setPosition(new Position(x + offSetX, y + offSetY));
        this.HP=HP;
        this.Damage_multiplier = Damage_multiplier;
        this.Energy = Energy;
        this.velocity = new Vector(0,0);
        this.maxVelocity = new Vector(2.0,4.0);
        this.jumpBoost = Math.PI;
        this.acceleration = 0.75;
        this.state = new IdleState(this);
        this.isFacingRight = true;
        this.jumpCounter = 0;
        this.dashBoost = 6;
        this.gotHit = false;
        this.startingPosition= new Position(x,y);
        //assigns the supplied values (and some other default values) to the Knight's attributes
    }

    //GETTERS
    public KnightState getNextState() {
        return state.getNextState();
    }

    public double getJumpBoost() {
        return jumpBoost;
    }

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

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getJumpCounter() {
        return jumpCounter;
    }

    public double getDashBoost() {
        return dashBoost;
    }

    public Position getStartingPosition() {
        return startingPosition;
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

    public void setJumpCounter(int jumpCounter) {
        this.jumpCounter = jumpCounter;
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

    public void setDashBoost(double dashBoost) {
        this.dashBoost = dashBoost;
    }
    public void setJumpBoost(double jumpBoost) {
        this.jumpBoost = jumpBoost;
    }

    public void setState(KnightState state) {
        this.state = state;
    }

    public void multiplyDamage(float damage) {
        this.Damage_multiplier = this.Damage_multiplier * damage;
    } // Used for collectables that multiply damage.

    public void setGotHit(boolean gotHit) {
        this.gotHit = gotHit;
    }

    public void setStartingPosition(Position startingPosition) {
        this.startingPosition = startingPosition;
    }

    public Vector updateVelocity() {
        return state.updateVelocity(velocity);
    }

    public Position updatePosition() {
        Vector resolvedVelocity = state.applyCollisions(velocity);

        // Update position with resolved velocity
        double newX = getPosition().x() + resolvedVelocity.x();
        double newY = getPosition().y() + resolvedVelocity.y();

        //Position newPosition = new Position(newX, newY);


        return new Position(newX, newY);
    }

    //ACTIONS

    public Vector moveLeft() {
        return state.moveKnightLeft();
    }

    public Vector moveRight() {
        return state.moveKnightRight();
    }

    public Vector jump() {
        return state.jump();
    }
    public Vector dash(){return state.dash();}

    //public Vector attack(){return state.attack();}


    ////////////Create Knight Particles ///////////////////////////////////////////////

    public List<Particle> createParticlesDoubleJump(int size, Scene scene) {
        List<Particle> particles = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            double angle;
            if (random.nextBoolean()) {
                // Generate angle in the range [180°, 225°] (converted to radians)
                angle = Math.toRadians(155 + random.nextDouble() * 45);
            } else if (random.nextBoolean()) {
                // Generate angle in the range [315°, 360°] (converted to radians)
                angle = Math.toRadians(345 + random.nextDouble() * 45);
            }
            else
                angle = Math.toRadians(45 + random.nextDouble() * 90);


            double speed = random.nextDouble() + 1; // Narrowed speed range [1.5, 2.5]
            Position velocity = new Position(
                    Math.cos(angle) * speed,  // Horizontal velocity
                    Math.sin(angle) * speed   // Ensure Y velocity is positive (downward)
            );

            particles.add(new DoubleJumpParticle(
                    random.nextInt((int) this.getPosition().x(), (int) this.getPosition().x() + getWidth()),
                    random.nextInt((int) this.getPosition().y() - 4 + getHeight(), (int) this.getPosition().y() + getHeight()),
                    velocity,
                    new TextColor.RGB(0, 0, 0) // Default black color for now
            ));
        }

        return particles;
    }

    public List<Particle> createParticlesJump(int size) {
        List<Particle> particles = new ArrayList<>();
        Random random = new Random();

        double coneAngle = Math.toRadians(90); // Total cone spread (45 degrees)
        double baseSpeed = -jumpBoost / 2.0;     // Base speed magnitude

        // Starting position (centered at the entity's position)
        double startX = this.getPosition().x() + getWidth() / 2.0;
        double startY = this.getPosition().y() + getHeight();

        for (int i = 0; i < size; i++) {
            // Randomize a position within the cone
            double factor = random.nextDouble(); // 0.0 to 1.0 (distance from the center)
            double angle = (random.nextDouble() - 0.5) * coneAngle; // Random angle within the cone

            // Calculate speedX and speedY based on the ^ shape
            double speedX = baseSpeed * factor * Math.sin(angle) - (getVelocity().x() / 1.10); // Increases with factor
            double speedY = baseSpeed * (1 - factor); // High upward speed at the center, reduces outward

            Position velocity = new Position(speedX, speedY);

            // Add the particle with calculated properties
            particles.add(new JumpParticle(
                    (int)startX, // All particles start from the same X position
                    (int)startY, // All particles start from the same Y position
                    velocity,
                    new TextColor.RGB(150, 150, 225))); // Adjust color as needed
        }
        return particles;
    }

    public List<Particle> createRespawnParticles(int size) {
        List<Particle> particles = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            double angle;
            angle = Math.toRadians(180 + random.nextDouble() * 180);
            double speed = random.nextDouble() + 1; // Narrowed speed range [1.5, 2.5]
            Position velocity = new Position(
                    Math.cos(angle) * (speed / 2.5),  // Horizontal velocity
                    Math.sin(angle) * speed - jumpBoost / 4  // Ensure Y velocity is positive (downward)
            );

            particles.add(new RespawnParticle(
                    random.nextInt((int) this.getPosition().x(), (int) this.getPosition().x() + getWidth()),
                    random.nextInt((int) this.getPosition().y() - 8 + getHeight(), (int) this.getPosition().y() + getHeight()),
                    velocity,
                    new TextColor.RGB(255, 0, 0) // Default black color for now
            ));
        }

        return particles;
    }

    public List<Particle> createDashParticles(int size){
        List<Particle> particles = new ArrayList<>();
        Random random = new Random();

        double coneAngle = Math.toRadians(90); // Total cone spread (45 degrees)
        double baseSpeed = -jumpBoost / 2.0;     // Base speed magnitude

        // Starting position (centered at the entity's position)
        double startX = this.getPosition().x() + getWidth() / 2.0;
        double startY = this.getPosition().y() + getHeight();

        for (int i = 0; i < size; i++) {
            // Randomize a position within the cone
            double factor = random.nextDouble(); // 0.0 to 1.0 (distance from the center)
            double angle = (random.nextDouble() - 0.5) * coneAngle; // Random angle within the cone

            // Calculate speedX and speedY based on the ^ shape
            double speedX = baseSpeed * factor * Math.sin(angle) - (getVelocity().x() / 1.10); // Increases with factor
            double speedY = baseSpeed * (1 - factor); // High upward speed at the center, reduces outward

            Position velocity = new Position(speedX, speedY);

            // Add the particle with calculated properties
            particles.add(new DashParticle(
                    (int)startX, // All particles start from the same X position
                    (int)startY, // All particles start from the same Y position
                    velocity,
                    new TextColor.RGB(0, 0, 0))); // Adjust color as needed
        }
        return particles;
    }

    public void resetValues(){
        this.isFacingRight = true;
        this.state = new FallingState(this);
    }

    //BOOLS

    public boolean isOverMaxXVelocity() {
        return Math.abs(velocity.x()) > maxVelocity.x();
    }

    public boolean isOnGround() {
        Position positionBelow = new Position(
                getPosition().x(),
                 getPosition().y() + 1
        );
        Position playerSize = new Position(WIDTH, HEIGHT);
        return scene.collidesDown(positionBelow, playerSize);
    }

    public void PlayerHit(int damage){
        if(gotHit) return;  // works as a timeout to prevent multiple collisions on the player almost instantly
        if (this.HP == 0)
            this.HP = 1;
        //simply formula that translates more particles when low hp and high dmg hit
        double ratio = 1.0 - (this.HP / 50.0);
        double blood = 10.0 * ratio;
        setState(new DamagedState(this, (int)blood + (damage / 5)));
        setHP(this.HP - damage);
        setGotHit(true);
    }
}
