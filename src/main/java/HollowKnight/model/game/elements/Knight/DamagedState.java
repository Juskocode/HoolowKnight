package HollowKnight.model.game.elements.Knight;

import HollowKnight.model.Vector;

public class DamagedState extends KnightState{
    private int ticks;
    public DamagedState(Knight knight) {
        super(knight);
        this.ticks =0;
        getKnight().getScene().setRespawnParticles(getKnight().createRespawnParticles(2));
    }

    @Override
    public Vector jump() {
        getKnight().setJumpCounter(getKnight().getJumpCounter() + 1);
        Vector newVelocity = new Vector(
                getKnight().getVelocity().x(),
                getKnight().getVelocity().y() - getKnight().getJumpBoost()
        );
        getKnight().getScene().setJumpParticles(getKnight().createParticlesJump(10));
        return updateVelocity(newVelocity);
    }

    @Override
    public Vector dash() {
        Vector newVelocity = new Vector(
                (getKnight().isFacingRight() ? getKnight().getDashBoost():
                        -getKnight().getDashBoost()),
                getKnight().getVelocity().y()
        );
        getKnight().getScene().setDashParticles(getKnight().createDashParticles(10));
        return applyCollisions(newVelocity);
    }

    @Override
    public Vector updateVelocity(Vector newVelocity) {
        tickParticles();
        //System.out.println(getParticlesTimer());
        Vector newvelocity = new Vector(
                newVelocity.x() * getKnight().getAcceleration(),
                newVelocity.y() + getKnight().getScene().getGravity()
        );
        return limitVelocity(applyCollisions(newvelocity));
    }

    @Override
    public KnightState getNextState() {
        if (getKnight().getHP() <=0) return new RespawnState(getKnight(),5);
        if(ticks < 10){
            this.ticks++;
            return this;
        }
        getKnight().setGotHit(false);
        if (!getKnight().isOnGround())
            return getNextOnAirState();
        getKnight().setJumpCounter(0);
        if (getKnight().isOverMaxXVelocity())
            return new DashState(getKnight());
        if (Math.abs(getKnight().getVelocity().x()) >= WalkingState.MIN_VELOCITY)
            return new WalkingState(getKnight());
        return new IdleState(getKnight());
    }
}
