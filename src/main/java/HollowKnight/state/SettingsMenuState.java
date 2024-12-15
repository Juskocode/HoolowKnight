package HollowKnight.state;

import HollowKnight.controller.Controller;
import HollowKnight.model.menu.Menu;
import HollowKnight.model.menu.SettingsMenu;
import HollowKnight.view.states.ScreenViewer;

import java.io.IOException;

public class SettingsMenuState extends State<SettingsMenu>{
    public SettingsMenuState(SettingsMenu model) throws IOException {
        super(model);
    }

    @Override
    protected ScreenViewer<SettingsMenu> getScreenViewer() throws IOException {
        return null;
    }

    @Override
    protected Controller<SettingsMenu> getController() {
        return null;
    }
}
