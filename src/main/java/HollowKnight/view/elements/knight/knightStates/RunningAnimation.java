package HollowKnight.view.elements.knight.knightStates;

import HollowKnight.model.game.elements.Knight.RunningState;
import HollowKnight.model.dataStructs.PairList;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RunningAnimation extends StateAnimation{
    public RunningAnimation(Class state, int frames) {
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
        setState(RunningState.class);
        setAnimation(new PairList<>(runningSpriteRight, runningSpriteLeft));
        setFrames(5);
    }
}
