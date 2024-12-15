package HollowKnight.view.elements.knight.knightStates;

import HollowKnight.model.game.elements.Knight.DashState;
import HollowKnight.view.elements.PairList;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DashAnimation extends StateAnimation{
    public DashAnimation(Class state, int frames) {
        super(state, frames);
    }

    @Override
    public void loadAnimation(String path) throws IOException {
        List<Sprite> DashRight = new ArrayList<>();
        List<Sprite> DashLeft = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            DashRight.add(new Sprite(path + "/movement/dashing/dashing-" + i + ".png"));
            DashLeft.add(new Sprite(path + "/movement/dashing/dashing-" + i + "-reversed.png"));
        }
        setState(DashState.class);
        setAnimation(new PairList<>(DashRight, DashLeft));
        setFrames(10);
    }
}
