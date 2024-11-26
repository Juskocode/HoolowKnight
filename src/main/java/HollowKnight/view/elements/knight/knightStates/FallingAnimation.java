package HollowKnight.view.elements.knight.knightStates;

import HollowKnight.model.game.elements.Knight.FallingState;
import HollowKnight.view.elements.PairList;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FallingAnimation extends StateAnimation{
    public FallingAnimation(Class state, int frames) {
        super(state, frames);
    }

    @Override
    public void loadAnimation(String path) throws IOException {
        List<Sprite> fallingSpriteRight = new ArrayList<>();
        List<Sprite> fallingSpriteLeft = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            fallingSpriteLeft.add(new Sprite(path + "/movement/falling/pixil-frame-" + i +".png"));
            fallingSpriteRight.add(new Sprite(path + "/movement/falling/pixil-frame-"+ i + "-reversed.png"));
        }
        setState(FallingState.class);
        setAnimation(new PairList<>(fallingSpriteLeft, fallingSpriteRight));
        setFrames(10);
    }
}
