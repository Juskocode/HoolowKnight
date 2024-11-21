package HollowKnight.view.elements;

import HollowKnight.gui.GUI;

import HollowKnight.model.game.elements.Knight.*;
import HollowKnight.view.sprites.Sprite;
import com.googlecode.lanterna.TextColor;

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
    private final List<Sprite> fallingSpriteRight;
    private final List<Sprite> fallingSpriteLeft;
    private final List<Sprite> jumpingSpriteRight;
    private final List<Sprite> jumpingSpriteLeft;
    private final Map<Class<?>, PairList<Sprite>> spriteMap;
    private final Map<Class<?>, Integer> animationFPSMap;
    private final ParticleViewer particleViewer;

    public KnightViewer() throws IOException {
        this.spriteMap = new HashMap<>();
        this.animationFPSMap = new HashMap<>();

        this.particleViewer = new ParticleViewer();

        //IDLE

        idleSpriteRight = new ArrayList<>();
        idleSpriteLeft = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            idleSpriteRight.add(new Sprite("sprites/Knight/Idle/pixil-frame-" + i + ".png"));
            idleSpriteLeft.add(new Sprite("sprites/Knight/Idle/pixil-frame-" + i + "-reversed.png"));
        }
        spriteMap.put(IdleState.class, new PairList<>(idleSpriteRight, idleSpriteLeft));
        animationFPSMap.put(IdleState.class, 8); // 8 FPS for idle animation

        // MAXVELOCITY

        maxVelocityRight = new ArrayList<>();
        maxVelocityLeft = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            maxVelocityRight.add(new Sprite("sprites/Knight/movement/maxVelocity/frame-" + i + ".png"));
            maxVelocityLeft.add(new Sprite("sprites/Knight/movement/maxVelocity/frame-" + i + "-reversed.png"));
        }
        spriteMap.put(MaxVelocityState.class, new PairList<>(maxVelocityRight, maxVelocityLeft));
        animationFPSMap.put(MaxVelocityState.class, 10); // 12 FPS for max velocity animation

        //RUNNING

        runningSpriteRight = new ArrayList<>();
        runningSpriteLeft = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            runningSpriteRight.add(new Sprite("sprites/Knight/movement/running/running-final-" + i + ".png"));
            runningSpriteLeft.add(new Sprite("sprites/Knight/movement/running/running-final-" + i + "-reversed.png"));
        }
        spriteMap.put(RunningState.class, new PairList<>(runningSpriteRight, runningSpriteLeft));
        animationFPSMap.put(RunningState.class, 5); // 10 FPS for running animation

        // WALKING

        walkingSpriteRight = new ArrayList<>();
        walkingSpriteLeft = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            walkingSpriteLeft.add(new Sprite("sprites/Knight/movement/walking/running-intermediate-" + i + ".png"));
            walkingSpriteRight.add(new Sprite("sprites/Knight/movement/walking/running-intermediate-" + i + "-reversed.png"));
        }
        spriteMap.put(WalkingState.class, new PairList<>(walkingSpriteLeft, walkingSpriteRight));
        animationFPSMap.put(WalkingState.class, 5); // 6 FPS for walking animation

        // FALLING

        fallingSpriteRight = new ArrayList<>();
        fallingSpriteLeft = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            fallingSpriteLeft.add(new Sprite("sprites/Knight/movement/falling/pixil-frame-" + i +".png"));
            fallingSpriteRight.add(new Sprite("sprites/Knight/movement/falling/pixil-frame-"+ i + "-reversed.png"));
        }
        spriteMap.put(FallingState.class, new PairList<>(fallingSpriteLeft, fallingSpriteRight));
        animationFPSMap.put(FallingState.class, 10);

        // JUMPING

        jumpingSpriteLeft = new ArrayList<>();
        jumpingSpriteRight = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            jumpingSpriteLeft.add(new Sprite("sprites/Knight/movement/jumping/pixil-frame-" + i + ".png"));
            jumpingSpriteRight.add(new Sprite("sprites/Knight/movement/jumping/pixil-frame-" + i + "-reversed.png"));
        }
        spriteMap.put(JumpState.class, new PairList<>(jumpingSpriteLeft, jumpingSpriteRight));
        animationFPSMap.put(JumpState.class, 6);
    }

    @Override
    public void draw(Knight model, GUI gui, long time) throws IOException {
        Sprite sprite = getSprite(time, model);
        int offSetX = 4;
        int offSetY = 1;
        sprite.draw(gui, (int) model.getPosition().x() - offSetX, (int) model.getPosition().y() - offSetY);
        gui.drawHitBox((int) model.getPosition().x(), (int) model.getPosition().y(), 7, 9, new TextColor.RGB(50, 200, 50));
        gui.drawPixel((int) model.getPosition().x(), (int) model.getPosition().y(), new TextColor.RGB(200, 200, 200));
    }

    private Sprite getSprite(long tick, Knight model) {
       /* if (model.getState().getClass() != IdleState.class) {
            System.out.println("Knight current state: " + model.getState().getClass().getSimpleName());
            System.out.println("Knight position: " + model.getPosition());
            System.out.println("Knight velocity: " + model.getVelocity());
        }*/

        int animationFPS = animationFPSMap.get(model.getState().getClass());
        int animationFrameTime = 30 / animationFPS; // Assuming 60 ticks per second
        PairList<Sprite> animations = spriteMap.get(model.getState().getClass());
        List<Sprite> animationsDirection = model.isFacingRight() ? animations.getFirstList() : animations.getSecondList();
        int frameIndex = (int) ((tick / animationFrameTime) % animationsDirection.size());
        return animationsDirection.get(frameIndex);
    }
}
