package HollowKnight.state;

import HollowKnight.controller.Controller;
import HollowKnight.controller.credits.CreditsController;
import HollowKnight.model.credits.Credits;
import HollowKnight.view.sprites.SpriteLoader;
import HollowKnight.view.sprites.ViewerProvider;
import HollowKnight.view.states.CreditsViewer;
import HollowKnight.view.states.ScreenViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class CreditsStateTest {
    @Mock
    private Credits model;

    @Mock
    private SpriteLoader spriteLoader;

    @Mock
    private ViewerProvider viewerProvider;

    private CreditsState creditsState;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        creditsState = new CreditsState(model, spriteLoader);
    }

    @Test
    void testCreateScreenViewer_shouldReturnCreditsViewer() {
        // Act
        ScreenViewer<Credits> screenViewer = creditsState.createScreenViewer(viewerProvider);

        // Assert: Ensure it returns the correct type of ScreenViewer
        assertNotNull(screenViewer);
        assertInstanceOf(CreditsViewer.class, screenViewer);
    }

    @Test
    void testCreateController_shouldReturnCreditsController() {
        // Act
        Controller<Credits> controller = creditsState.createController();

        // Assert: Ensure it returns the correct type of Controller
        assertNotNull(controller);
        assertInstanceOf(CreditsController.class, controller);
    }

    @Test
    void testCreditsStateInitialization() {
        // Act & Assert: Ensure that CreditsState is properly initialized
        assertNotNull(creditsState);
        assertEquals(model, creditsState.getModel());  // Ensure the model is set correctly
    }
}