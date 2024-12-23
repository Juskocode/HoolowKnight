package HollowKnight.view.elements.knight.knightStates;

import HollowKnight.model.dataStructs.PairList;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;
import java.util.List;

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

    public Sprite getSprite(long tick, boolean facingRight) {
        if (frames == 0) return null;
        int animationFrameTime = 30 / frames; // Assuming 30 ticks per second
        List<Sprite> sprites = facingRight ? animation.getFirstList() : animation.getSecondList();
        int frameIndex = (int) ((tick / animationFrameTime) % sprites.size());
        return sprites.get(frameIndex);
    }

    public abstract void loadAnimation(String path) throws IOException;
}
