package HollowKnight.view.elements.monsters;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.model.game.elements.enemies.SwordMonster;
import HollowKnight.view.elements.ElementViewer;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SwordMonsterViewer implements ElementViewer<SwordMonster> {
    private List<Sprite> swordMonsterAttackSprite;

    public SwordMonsterViewer() throws IOException {
        swordMonsterAttackSprite = new ArrayList<>();

        for (int i = 0; i < 2; i++)
            swordMonsterAttackSprite.add(new Sprite("sprites/Enemies/swordMonster-Attack" + i + ".png"));
    }

    @Override
    public void draw(SwordMonster model, GUI gui, long time) throws IOException {
        Sprite sprite = getSprite(time);
        sprite.draw(gui, (int)model.getPosition().x() - 4, (int)model.getPosition().y());
    }

    private Sprite getSprite(long tick) {
        int animationFPS = 6; // Animation updates at 10 FPS
        int animationFrameTime = 30 / animationFPS; // Frames per tick at game FPS = 30

        int frameIndex = (int) ((tick / animationFrameTime) % swordMonsterAttackSprite.size());
        return swordMonsterAttackSprite.get(frameIndex);
    }
}
