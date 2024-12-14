package HollowKnight.view.elements.knight.knightStates;

import HollowKnight.model.game.elements.Knight.DamagedState;
import HollowKnight.model.game.elements.Knight.MaxVelocityState;
import HollowKnight.view.elements.PairList;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DamagedAnimation extends StateAnimation{
    public DamagedAnimation(Class state, int frames) {
        super(state, frames);
    }

    @Override
    public void loadAnimation(String path) throws IOException {
        List<Sprite> DamagedRight = new ArrayList<>();
        List<Sprite> DamagedLeft = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            DamagedRight.add(new Sprite(path + "/damaged/Idle/damaged-idle-1.png"));
            DamagedLeft.add(new Sprite(path + "/damaged/Idle/damaged-idle-2.png"));
        }
        setState(DamagedState.class);
        setAnimation(new PairList<>(DamagedRight, DamagedLeft));
        setFrames(3);
    }
}
