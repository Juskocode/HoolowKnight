package HollowKnight.view.elements.knight.knightStates;

import HollowKnight.model.game.elements.Knight.MaxVelocityState;
import HollowKnight.model.dataStructs.PairList;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MaxVelocityAnimation extends StateAnimation{
    public MaxVelocityAnimation(Class state, int frames) {
        super(state, frames);
    }

    @Override
    public void loadAnimation(String path) throws IOException {
        List<Sprite> maxVelocityRight = new ArrayList<>();
        List<Sprite> maxVelocityLeft = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            maxVelocityRight.add(new Sprite(path + "/movement/maxVelocity/frame-" + i + ".png"));
            maxVelocityLeft.add(new Sprite(path + "/movement/maxVelocity/frame-" + i + "-reversed.png"));
        }
        setState(MaxVelocityState.class);
        setAnimation(new PairList<>(maxVelocityRight, maxVelocityLeft));
        setFrames(10);
    }
}
