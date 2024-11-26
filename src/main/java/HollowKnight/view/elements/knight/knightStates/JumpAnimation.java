package HollowKnight.view.elements.knight.knightStates;

import HollowKnight.model.game.elements.Knight.JumpState;
import HollowKnight.view.elements.PairList;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JumpAnimation extends StateAnimation {
    public JumpAnimation(Class state, int frames) {
        super(state, frames);
    }

    @Override
    public void loadAnimation(String path) throws IOException {
        List<Sprite> jumpingSpriteLeft = new ArrayList<>();
        List<Sprite> jumpingSpriteRight = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            jumpingSpriteLeft.add(new Sprite(path + "/movement/jumping/pixil-frame-" + i + ".png"));
            jumpingSpriteRight.add(new Sprite(path + "/movement/jumping/pixil-frame-" + i + "-reversed.png"));
        }
        setState(JumpState.class);
        setAnimation(new PairList<>(jumpingSpriteLeft, jumpingSpriteRight));
        setFrames(6);
    }
}
