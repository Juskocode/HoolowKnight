package HollowKnight.state;

import HollowKnight.controller.Controller;
import HollowKnight.controller.menu.MenuController;
import HollowKnight.model.menu.Menu;
import HollowKnight.view.states.MenuViewer;
import HollowKnight.view.states.ScreenViewer;

public class MenuState extends State<Menu> {

    public MenuState(Menu model) {
        super(model);
    }

    @Override
    protected ScreenViewer<Menu> getScreenViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel());
    }

}
