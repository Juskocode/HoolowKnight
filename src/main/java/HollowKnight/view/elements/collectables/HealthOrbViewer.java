package HollowKnight.view.elements.collectables;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Collectables.HealthOrb;
import HollowKnight.view.elements.ElementViewer;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;

public class HealthOrbViewer implements ElementViewer<HealthOrb> {
    private final Sprite healthOrbSprite;

    public HealthOrbViewer() throws IOException {
        this.healthOrbSprite = new Sprite("sprites/collectables/health.png");
    }
    @Override
    public void draw(HealthOrb model, GUI gui, long time) throws IOException {
        healthOrbSprite.draw(gui, (int)model.getPosition().x(),(int) model.getPosition().y());
    }
}
