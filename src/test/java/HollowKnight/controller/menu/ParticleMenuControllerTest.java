package HollowKnight.controller.menu;

class ParticleMenuControllerTest {
    /*
    private ParticleMenuController particleMenuController;
    private MainMenu mainMenu;
    private Game game;
    private List<Particle> particles;

    @BeforeEach
    void setUp() {
        this.mainMenu = Mockito.mock(MainMenu.class);
        this.game = Mockito.mock(Game.class);
        this.particleMenuController = new ParticleMenuController(mainMenu);
        this.particles = new ArrayList<>();
        particles.add(new Particle(new Position(10,10), null, new TextColor.RGB(0,0,0)));
        when(mainMenu.getParticles()).thenReturn(particles);
    }

    @Test
    void testMove() throws IOException {
        // Mock particles
        Particle particle1 = Mockito.mock(Particle.class);
        Particle particle2 = Mockito.mock(Particle.class);
        List<Particle> particles = new ArrayList<>();
        particles.add(particle1);
        particles.add(particle2);

        // Mock behavior for particles in the menu
        when(mainMenu.getParticles()).thenReturn(particles);

        // Mock initial positions
        when(particle1.getPosition()).thenReturn(new Position(10, 10));
        when(particle2.getPosition()).thenReturn(new Position(20, 20));

        // Invoke the move method
        particleMenuController.move(game, GUI.ACTION.NULL, 100);

        // Verify that ParticleMove was called for each particle
        verify(particle1, times(1)).setPosition(any(Position.class));
        verify(particle2, times(1)).setPosition(any(Position.class));
    }

    @Test
    void testParticleMoveRandom() throws IOException {
        // Create a particle with a mocked position
        Particle particle = Mockito.mock(Particle.class);
        when(particle.getPosition()).thenReturn(new Position(5, 5));

        // Simulate tick within random mode
        particleMenuController.move(game, GUI.ACTION.NULL,100);

        // Validate the new position is within expected bounds
        assertTrue(newPosition.x() >= 4 && newPosition.x() <= 6); // Random movement
        assertTrue(newPosition.y() >= 6 && newPosition.y() <= 8); // Mostly downward
    }

    //TODO

    @Test
    void testParticleMoveWindy() {
        // Create a particle with a mocked position
        Particle particle = Mockito.mock(Particle.class);

        // Provide a valid Position object for the particle
        Position initialPosition = new Position(50, 50);
        when(particle.getPosition()).thenReturn(initialPosition);

        // Simulate tick within windy mode
        Position newPosition = particleMenuController.ParticleMove(particle, 800);

        // Validate that the new position changes due to wind
        assertNotEquals(initialPosition.x(), newPosition.x());
        assertNotEquals(initialPosition.y(), newPosition.y());
    }


    @Test
    void testColorInterpolation() {
        // Test the interpolateColor method
        TextColor.RGB start = new TextColor.RGB(0, 0, 0);
        TextColor.RGB end = new TextColor.RGB(255, 255, 255);

        // 50% transition
        TextColor.RGB result = particleMenuController.interpolateColor(start, end, 0.5f);

        assertEquals(127, result.getRed());
        assertEquals(127, result.getGreen());
        assertEquals(127, result.getBlue());
    }

    @Test
    void testGradientChange() {
        // Mock a particle with a valid position
        Particle particle = Mockito.mock(Particle.class);
        Position initialPosition = new Position(10, 10);
        when(particle.getPosition()).thenReturn(initialPosition);

        // Simulate gradient change trigger
        Position newPosition = particleMenuController.ParticleMove(particle, 500);

        // Ensure the returned position is not null
        assertNotNull(newPosition);

        // Ensure that the particle controller is functioning correctly
        assertNotNull(particleMenuController);
    }

    @Test
    void testWrapAround() {
        // Create a particle at the edge of the screen
        Particle particle = Mockito.mock(Particle.class);

        // Starting position near the edge
        Position initialPosition = new Position(-1, -1); // Out of bounds
        when(particle.getPosition()).thenReturn(initialPosition);

        // Simulate tick that results in wrap-around
        Position newPosition = particleMenuController.ParticleMove(particle, 100);

        // Validate wrap-around logic
        assertEquals(158, newPosition.x()); // screenWidth - 1
        assertEquals(88, newPosition.y()); // screenHeight - 1
    }
    */

}
