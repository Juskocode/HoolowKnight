package HollowKnight.view.elements.knight.knightStates;

import HollowKnight.view.elements.PairList;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AfterDashAnimation extends StateAnimation{
    public AfterDashAnimation(Class state, int frames) {
        super(state, frames);
    }

    @Override
    public void loadAnimation(String path) throws IOException {
        List<Sprite> runningSpriteRight = new ArrayList<>();
        List<Sprite> runningSpriteLeft = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            runningSpriteRight.add(new Sprite(path + "/movement/running/running-final-" + i + ".png"));
            runningSpriteLeft.add(new Sprite(path + "/movement/running/running-final-" + i + "-reversed.png"));
        }
        setState(AfterDashAnimation.class);
        setAnimation(new PairList<>(runningSpriteLeft, runningSpriteRight));
        setFrames(5);
    }
}
