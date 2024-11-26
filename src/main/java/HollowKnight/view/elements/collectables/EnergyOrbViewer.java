package HollowKnight.view.elements.collectables;

import HollowKnight.gui.GUI;
import HollowKnight.model.game.elements.Collectables.EnergyOrb;
import HollowKnight.model.game.elements.Collectables.HealthOrb;
import HollowKnight.view.elements.ElementViewer;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;

public class EnergyOrbViewer implements ElementViewer<EnergyOrb> {
    private final Sprite energyOrbSprite;

    public EnergyOrbViewer() throws IOException {
        this.energyOrbSprite = new Sprite("sprites/Collectables/energy.png");
    }
    @Override
    public void draw(EnergyOrb model, GUI gui, long time) throws IOException {
        energyOrbSprite.draw(gui, (int)model.getPosition().x(),(int) model.getPosition().y());
    }
}
