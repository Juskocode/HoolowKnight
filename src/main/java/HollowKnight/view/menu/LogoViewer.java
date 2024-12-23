package HollowKnight.view.menu;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.enemies.GhostMonster;
import HollowKnight.view.sprites.Sprite;
import HollowKnight.view.sprites.SpriteLoader;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;

public class LogoViewer {
    private final Sprite logoSprite;

    public LogoViewer(SpriteLoader spriteLoader) throws IOException {
        this.logoSprite = spriteLoader.get("icon/gameIcon.png");
    }

    public void draw(GUI gui, int x, int y) throws IOException {
        logoSprite.draw(gui, x, y);
    }
}
