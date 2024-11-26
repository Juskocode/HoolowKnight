package HollowKnight.view.elements.collectables;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Collectables.HealthOrb;
import HollowKnight.model.game.elements.Collectables.SpeedOrb;
import HollowKnight.view.elements.ElementViewer;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;

public class SpeedOrbViewer implements ElementViewer<SpeedOrb> {
    private final Sprite speedOrbSprite;

    public SpeedOrbViewer() throws IOException {
        this.speedOrbSprite = new Sprite("sprites/Collectables/speed.png");
    }
    @Override
    public void draw(SpeedOrb model, GUI gui, long time) throws IOException {
        speedOrbSprite.draw(gui, (int)model.getPosition().x(),(int) model.getPosition().y());
    }
}
