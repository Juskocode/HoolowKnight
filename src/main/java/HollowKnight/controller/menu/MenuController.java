package HollowKnight.controller.menu;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.gui.GUI;
import HollowKnight.model.menu.Menu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public abstract class MenuController<T extends Menu> extends Controller<T> {

    private final OptionController optionController;
    private final ParticleMenuController particleMenuController;
    public MenuController(T menu, ParticleMenuController particleMenuController, OptionController optionController) {
        super(menu);
        this.particleMenuController = particleMenuController;
        this.optionController = optionController;
    }

    @Override
    public void move(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException, FontFormatException {
        switch (action) {
            case UP:
                this.getModel().previousOption();
                break;
            case DOWN:
                this.getModel().nextOption();
                break;
            case QUIT:
                onQuit(game);
                break;
            default:
                optionController.move(game, action, time);
        }
        particleMenuController.move(game, action, time);
    }

    protected abstract void onQuit(Game game) throws IOException;
}
