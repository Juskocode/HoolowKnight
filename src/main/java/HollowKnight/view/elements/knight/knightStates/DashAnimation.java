package HollowKnight.view.elements.knight.knightStates;

import HollowKnight.view.elements.PairList;

public class DashAnimation extends StateAnimation{
    public DashAnimation(Class state, int frames) {
        super(state, frames);
    }

    @Override
    public void loadAnimation(String path) {
        setState(DashAnimation.class);
        setAnimation(new PairList<>(null, null));
        setFrames(1);
    }
}
