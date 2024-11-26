package HollowKnight.view.elements.knight.knightStates;

import HollowKnight.view.elements.PairList;

public class RespawnAnimation extends StateAnimation{
    public RespawnAnimation(Class state, int frames) {
        super(state, frames);
    }

    @Override
    public void loadAnimation(String path) {
        setState(RunningAnimation.class);
        setAnimation(new PairList<>(null, null));
        setFrames(1);
    }
}
