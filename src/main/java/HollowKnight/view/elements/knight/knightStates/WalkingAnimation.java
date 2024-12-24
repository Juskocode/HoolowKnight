package HollowKnight.view.elements.knight.knightStates;

import HollowKnight.model.game.elements.Knight.WalkingState;
import HollowKnight.model.dataStructs.PairList;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WalkingAnimation extends StateAnimation {
    public WalkingAnimation(Class state, int frames) {
        super(state, frames);
    }

    @Override
    public void loadAnimation(String path) throws IOException {
        List<Sprite> walkingSpriteRight = new ArrayList<>();
        List<Sprite> walkingSpriteLeft = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            walkingSpriteLeft.add(new Sprite(path + "/movement/walking/running-intermediate-" + i + ".png"));
            walkingSpriteRight.add(new Sprite(path + "/movement/walking/running-intermediate-" + i + "-reversed.png"));
        }
        setState(WalkingState.class);
        setAnimation(new PairList<>(walkingSpriteLeft, walkingSpriteRight));
        setFrames(5);
    }
}
