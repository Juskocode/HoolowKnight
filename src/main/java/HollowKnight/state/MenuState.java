package HollowKnight.state;

import HollowKnight.controller.Controller;
import HollowKnight.controller.menu.MenuController;
import HollowKnight.controller.menu.ParticleMenuController;
import HollowKnight.model.menu.Menu;
import HollowKnight.view.states.MenuViewer;
import HollowKnight.view.states.ScreenViewer;

import java.io.IOException;

public class MenuState extends State<Menu> {

    public MenuState(Menu model) throws IOException {
        super(model);
    }

    @Override
    protected ScreenViewer<Menu> getScreenViewer() throws IOException {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel(), new ParticleMenuController(getModel()));
    }

}
