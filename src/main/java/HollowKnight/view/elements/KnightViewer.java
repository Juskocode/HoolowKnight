package HollowKnight.view.elements;

import HollowKnight.gui.GUI;

import HollowKnight.model.game.elements.Knight.IdleState;
import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class KnightViewer implements ElementViewer<Knight>{
    private final List<Sprite> idleSpriteRight;
    private final Map<Class<?>, PairList<Sprite>> spriteMap;
    public KnightViewer() throws IOException {
        this.spriteMap = new HashMap<>();
        idleSpriteRight = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            idleSpriteRight.add(new Sprite("sprites/Knight/Idle/pixil-frame-" + i + ".png"));
        }

        //spriteMap.put(IdleState.class, idleSprite);
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

        PairList<Sprite> animations = spriteMap.get(model.getState().getClass());
        int frameIndex = (int) ((tick / animationFrameTime) % animations.size());
        return animations.get(frameIndex);
    }

    /*
        gui.drawPixel(model.getPosition().x(), model.getPosition().y(), new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x() + 1, model.getPosition().y(), new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x() - 1, model.getPosition().y(), new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x(), model.getPosition().y() + 1, new TextColor.RGB(0, 0, 255));
        gui.drawPixel(model.getPosition().x(), model.getPosition().y() - 1, new TextColor.RGB(0, 0, 255));
     */
}
