package HollowKnight.model.game.elements.Knight;
import HollowKnight.model.Vector;

public abstract class AttackState extends KnightState {
    public AttackState(Knight knight) {
        super(knight);
    }

    public abstract void Attack();



    @Override
    public Vector jump(){

        getKnight().setJumpCounter(getKnight().getJumpCounter() + 1);
        Vector newVelocity = new Vector(
            getKnight().getVelocity().x(),
            getKnight().getVelocity().y() - getKnight().getJumpBoost()
        );

        return updateVelocity(newVelocity);

    }




}
