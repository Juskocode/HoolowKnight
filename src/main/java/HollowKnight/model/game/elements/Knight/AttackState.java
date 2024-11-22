package HollowKnight.model.game.elements.Knight;

public abstract class AttackState extends KnightState {
    public AttackState(Knight knight) {
        super(knight);
    }

    public abstract void Attack();



}
