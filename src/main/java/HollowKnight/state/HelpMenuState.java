package HollowKnight.state;

import HollowKnight.controller.Controller;
import HollowKnight.model.menu.HelpMenu;
import HollowKnight.model.menu.SettingsMenu;
import HollowKnight.view.states.ScreenViewer;

import java.io.IOException;

public class HelpMenuState extends State<HelpMenu>{

    public HelpMenuState(HelpMenu model) throws IOException {
        super(model);
    }
    @Override
    protected ScreenViewer<HelpMenu> getScreenViewer() throws IOException {
        return null;
    }
    @Override
    protected Controller<HelpMenu> getController() {
            return null;
        }
}
