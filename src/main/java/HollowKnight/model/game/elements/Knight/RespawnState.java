package HollowKnight.model.game.elements.Knight;


import HollowKnight.model.Position;
import HollowKnight.model.Vector;

public class RespawnState extends KnightState {

    private long deathTimer;

    public RespawnState(Knight knight, long deathTimer) {
        super(knight);
        this.deathTimer = deathTimer;
        knight.getScene().setRespawnParticles(getKnight().createRespawnParticles(45));
    }

    @Override
    public Vector jump() {
        return updateVelocity(getKnight().getVelocity());
    }

    @Override
    public Vector updateVelocity(Vector newVelocity) {
        deathTimer--;
        tickParticles();
        return new Vector(0, 0);
    }

    @Override
    public KnightState getNextState()
    {
        if (deathTimer <= 0) {
            Position respawn = new Position(getKnight().getPosition().x(), 0);
            getKnight().setPosition(respawn);
            return new FallingState(getKnight());
        }
        return this;
    }
}
