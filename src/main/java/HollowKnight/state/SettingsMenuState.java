package HollowKnight.state;

import HollowKnight.controller.Controller;
import HollowKnight.controller.menu.OptionController;
import HollowKnight.controller.menu.ParticleMenuController;
import HollowKnight.controller.menu.SettingsMenuController;
import HollowKnight.model.menu.Menu;
import HollowKnight.model.menu.SettingsMenu;
import HollowKnight.view.sprites.SpriteLoader;
import HollowKnight.view.sprites.ViewerProvider;
import HollowKnight.view.states.MenuViewer;
import HollowKnight.view.states.ScreenViewer;

import java.io.IOException;

public class SettingsMenuState extends State<SettingsMenu> {
    public SettingsMenuState(SettingsMenu menu, SpriteLoader spriteLoader) throws IOException {
        super(menu, spriteLoader);
    }

    @Override
    protected Controller<SettingsMenu> createController() {
        return new SettingsMenuController(
                getModel(),
                new ParticleMenuController(getModel()),
                new OptionController(getModel()));
    }

    @Override
    protected ScreenViewer<SettingsMenu> createScreenViewer(ViewerProvider viewerProvider) throws IOException {
        return new MenuViewer<>(getModel(), viewerProvider);
    }

}

