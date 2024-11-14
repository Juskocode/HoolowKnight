package HollowKnight.model.game.elements.Knight;

public abstract class KnightState {
    private Knight knight;

    public KnightState(Knight knight){
        this.knight = knight;
    }
    public Knight getKnight() {
        return knight;
    }

}
