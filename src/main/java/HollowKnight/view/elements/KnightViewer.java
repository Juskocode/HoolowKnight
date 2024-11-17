package HollowKnight.view.elements;

import HollowKnight.gui.GUI;

import HollowKnight.model.game.elements.Knight.*;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class KnightViewer implements ElementViewer<Knight>{
    private final List<Sprite> idleSpriteRight;
    private final List<Sprite> idleSpriteLeft;
    private final List<Sprite> maxVelocityRight;
    private final List<Sprite> maxVelocityLeft;
    private final List<Sprite> runningSpriteRight;
    private final List<Sprite> runningSpriteLeft;
    private final List<Sprite> walkingSpriteRight;
    private final List<Sprite> walkingSpriteLeft;
    private final Map<Class<?>, PairList<Sprite>> spriteMap;

    public KnightViewer() throws IOException {

        this.spriteMap = new HashMap<>();

        idleSpriteRight = new ArrayList<>();
        idleSpriteLeft = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            idleSpriteRight.add(new Sprite("sprites/Knight/Idle/pixil-frame-" + i + ".png"));
            idleSpriteLeft.add(new Sprite("sprites/Knight/Idle/pixil-frame-" + i + "-reversed.png"));
        }
        spriteMap.put(IdleState.class, new PairList<>(idleSpriteRight,idleSpriteLeft));

        maxVelocityRight = new ArrayList<>();
        maxVelocityLeft = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            maxVelocityRight.add(new Sprite("sprites/Knight/movement/maxVelocity/frame-" + i + ".png"));
            maxVelocityLeft.add(new Sprite("sprites/Knight/movement/maxVelocity/frame-" + i + "-reversed.png"));
        }
        spriteMap.put(MaxVelocityState.class, new PairList<>(maxVelocityRight,maxVelocityLeft));

        runningSpriteRight = new ArrayList<>();
        runningSpriteLeft = new ArrayList<>();

        for (int i = 1; i < 6; i++) {
            runningSpriteRight.add(new Sprite("sprites/Knight/movement/running-final-" + i + ".png"));
            runningSpriteLeft.add(new Sprite("sprites/Knight/movement/running-final-" + i + "-reversed.png"));
        }
        spriteMap.put(RunningState.class, new PairList<>(runningSpriteRight,runningSpriteLeft));

        walkingSpriteRight = new ArrayList<>();
        walkingSpriteLeft = new ArrayList<>();

        for (int i = 1; i < 6; i++) {
            walkingSpriteLeft.add(new Sprite("sprites/Knight/movement/running-intermediate-" + i + ".png"));
            walkingSpriteRight.add(new Sprite("sprites/Knight/movement/running-intermediate-" + i + "-reversed.png"));
        }
        spriteMap.put(WalkingState.class, new PairList<>(walkingSpriteLeft,walkingSpriteRight));

    }
    @Override
    public void draw(Knight model, GUI gui, long time) throws IOException {
        Sprite sprite = getSprite(time,model);
        System.out.println(model.getPosition().x() + " " + model.getPosition().y());
        sprite.draw(gui, (int) model.getPosition().x(), (int)model.getPosition().y());
    }

    private Sprite getSprite(long tick, Knight model) {
        int animationFPS = 8; // Animation updates at 10 FPS
        int animationFrameTime = 30 / animationFPS; // Frames per tick at game FPS = 30
        System.out.println("Current state : " + model.getState().getClass().getSimpleName());
        PairList<Sprite> animations = spriteMap.get(model.getState().getClass());
        List<Sprite> animationsDirection = (model.isFacingRight()) ? animations.getFirstList() : animations.getSecondList() ;
        int frameIndex = (int) ((tick / animationFrameTime) % animationsDirection.size());
        return animationsDirection.get(frameIndex);
    }

    /*
        gui.drawPixel(model.getPosition().x(), model.getPosition().y(), new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x() + 1, model.getPosition().y(), new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x() - 1, model.getPosition().y(), new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x(), model.getPosition().y() + 1, new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x(), model.getPosition().y() - 1, new TextColor.RGB(0, 0, 255));
     */
}
