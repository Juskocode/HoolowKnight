package HollowKnight.state;

import HollowKnight.controller.Controller;
import HollowKnight.controller.credits.CreditsController;
import HollowKnight.model.credits.Credits;
import HollowKnight.view.sprites.SpriteLoader;
import HollowKnight.view.sprites.ViewerProvider;
import HollowKnight.view.states.CreditsViewer;
import HollowKnight.view.states.ScreenViewer;

import java.io.IOException;

public class CreditsState extends State<Credits>{
    public CreditsState(Credits model, SpriteLoader spriteLoader) throws IOException {
        super(model, spriteLoader);
    }

    @Override
    protected ScreenViewer<Credits> createScreenViewer(ViewerProvider viewerProvider) {
        return new CreditsViewer(getModel(), viewerProvider);
    }

    @Override
    protected Controller<Credits> createController() {
        return new CreditsController(getModel());
    }
}
