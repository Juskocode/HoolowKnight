package HollowKnight.view.elements.knight.knightStates;

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
        List<Sprite> maxVelocityRight = new ArrayList<>();
        List<Sprite> maxVelocityLeft = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            maxVelocityRight.add(new Sprite(path + "/movement/maxVelocity/frame-" + i + ".png"));
            maxVelocityLeft.add(new Sprite(path + "/movement/maxVelocity/frame-" + i + "-reversed.png"));
        }
        setState(DashAnimation.class);
        setAnimation(new PairList<>(maxVelocityLeft, maxVelocityRight));
        setFrames(10);
    }
}
