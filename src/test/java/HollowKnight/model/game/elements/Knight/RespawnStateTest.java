package HollowKnight.model.game.elements.Knight;

import static org.mockito.ArgumentMatchers.any;

class RespawnStateTest {
    /*private Knight knight;
    private RespawnState respawnState;
    private Scene mockedScene;
    private List<Particle> respawnParticles;

    @BeforeEach
    void setUp() {
        this.mockedScene = Mockito.mock(Scene.class);
        this.knight = new Knight(0, 0, 0,0,0);
        this.respawnState = new RespawnState(knight,10);
        this.knight.setState(respawnState);
        this.knight.setVelocity(new Vector(0, 0));
        this.knight.setScene(mockedScene);
        this.respawnParticles = knight.createRespawnParticles(450);
        doAnswer(invocation -> {
            ArrayList<Particle> input = invocation.getArgument(0);
            input.add(new RespawnParticle(0,0,new Position(0,0), new TextColor.RGB(0,0,0)));
            return null;
        }).when(mockedScene).setRespawnParticles(any(ArrayList.class));
    }

    @Test
    void jump() {
        Vector result = respawnState.jump();

        assertEquals(0, result.x());
        assertEquals(0, result.y());
    }

    @Test
    void dash() {
        Vector result = respawnState.dash();

        assertEquals(0, result.x());
        assertEquals(0, result.y());
        assertTrue(knight.isFacingRight());
    }

    @Test
    void updateVelocity() {
        Vector result = respawnState.updateVelocity(knight.getVelocity());

        assertEquals(0, result.x());
        assertEquals(0, result.y());
    }

    @Test
    void getNextState() {

    }

    @Test
    void getNextStateStay() {
        KnightState nextState = respawnState.getNextState();

        assertSame(respawnState, nextState);
    }*/
}