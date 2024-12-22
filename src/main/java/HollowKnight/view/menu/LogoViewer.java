package HollowKnight.view.menu;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.enemies.GhostMonster;
import HollowKnight.view.sprites.Sprite;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;

public class LogoViewer {
    private final Sprite logoSprite;

    public LogoViewer() throws IOException {
        this.logoSprite = new Sprite("icon/gameIcon.png");
    }

    public void draw(GUI gui, int x, int y) throws IOException {
        logoSprite.draw(gui, x, y);
    }
}
