package HollowKnight.state;

import HollowKnight.controller.Controller;
import HollowKnight.controller.menu.MenuController;
import HollowKnight.model.menu.Menu;
import HollowKnight.view.states.MenuViewer;
import HollowKnight.view.states.ScreenViewer;

import java.io.IOException;

public class MenuState extends State<Menu> {

    public MenuState(Menu model) throws IOException {
        super(model);
    }

    @Override
    protected ScreenViewer<Menu> getScreenViewer() {
        return new MenuViewer(model);
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(model);
    }

}
