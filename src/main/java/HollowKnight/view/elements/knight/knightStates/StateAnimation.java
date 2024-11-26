package HollowKnight.view.elements.knight.knightStates;

import HollowKnight.view.elements.PairList;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;

public abstract class StateAnimation {
    private PairList<Sprite> animation;
    private int frames;
    private Class state;

    public StateAnimation(Class state, int frames) {
        this.state = state;
        this.frames = frames;
    }

    public int getFrames() {
        return frames;
    }
    public Class getState(){
        return state;
    }
    public PairList<Sprite> getAnimation() {
        return animation;
    }

    public void setFrames(int frames) {
        this.frames = frames;
    }

    public void setAnimation(PairList<Sprite> animation) {
        this.animation = animation;
    }

    public void setState(Class state) {
        this.state = state;
    }

    protected abstract void loadAnimation(String path) throws IOException;
}
