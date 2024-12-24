package HollowKnight.state;

import HollowKnight.Game;
import HollowKnight.controller.Controller;
import HollowKnight.controller.menu.MainMenuController;
import HollowKnight.controller.menu.MenuController;
import HollowKnight.controller.menu.OptionController;
import HollowKnight.controller.menu.ParticleMenuController;
import HollowKnight.model.menu.MainMenu;
import HollowKnight.model.menu.Menu;
import HollowKnight.model.menu.SettingsMenu;
import HollowKnight.view.sprites.SpriteLoader;
import HollowKnight.view.sprites.ViewerProvider;
import HollowKnight.view.states.MenuViewer;
import HollowKnight.view.states.ScreenViewer;

import java.io.IOException;


public class MainMenuState extends State<MainMenu> {

    public MainMenuState(MainMenu model, SpriteLoader spriteLoader) throws IOException {
        super(model, spriteLoader);
    }

    @Override
    protected ScreenViewer<MainMenu> createScreenViewer(ViewerProvider viewerProvider) throws IOException {
        return new MenuViewer<>(getModel(), viewerProvider);
    }

    @Override
    protected Controller<MainMenu> createController() {
        return new MainMenuController(
                getModel(),
                new ParticleMenuController(getModel()),
                new OptionController(getModel())
        );
    }

}
