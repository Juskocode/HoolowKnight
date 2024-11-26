package HollowKnight.view.elements.knight.knightStates;

import HollowKnight.view.elements.PairList;

import java.io.IOException;

public class AfterDashAnimation extends StateAnimation{
    public AfterDashAnimation(Class state, int frames) {
        super(state, frames);
    }

    @Override
    public void loadAnimation(String path) throws IOException {
        setState(AfterDashAnimation.class);
        setAnimation(new PairList<>(null, null));
        setFrames(1);
    }
}
