package HollowKnight.model.menu;

import HollowKnight.model.dataStructs.Position;
import HollowKnight.state.particle.ParticleState;
import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParticleTest {

    private Particle particle;
    private Position mockPosition;
    private ParticleState mockState;
    private TextColor.RGB mockColor;

    @BeforeEach
    public void setUp() {
        mockPosition = new Position(10, 20); // Assuming Position has a constructor with x, y
        mockColor = new TextColor.RGB(255, 0, 0); // Red color (RGB: 255,0,0)

        // Create particle instance
        particle = new Particle(mockPosition, mockState, mockColor);
    }

    // Constructor test
    @Test
    public void testConstructor() {
        assertNotNull(particle);
        assertEquals(mockPosition, particle.getPosition());
        assertEquals(mockState, particle.getState());
    }

    // Test getter for position
    @Test
    public void testGetPosition() {
        assertEquals(mockPosition, particle.getPosition());
    }

    // Test setter for position
    @Test
    public void testSetPosition() {
        Position newPosition = new Position(30, 40);
        particle.setPosition(newPosition);
        assertEquals(newPosition, particle.getPosition());
    }


    // Test setter for color
    @Test
    public void testSetColor() {
        TextColor.RGB newColor = new TextColor.RGB(0, 255, 0); // Green color
        particle.setColor(newColor);
        assertEquals(newColor, particle.getColor());
    }

    // Test getter for state
    @Test
    public void testGetState() {
        assertEquals(mockState, particle.getState());
    }


    // Test null state handling
    @Test
    public void testSetNullState() {
        ParticleState nullState = null;
        particle.setState(nullState);
        assertNull(particle.getState());
    }

    // Test setting position to null (if applicable to the implementation)
    @Test
    public void testSetNullPosition() {
        Position nullPosition = null;
        particle.setPosition(nullPosition);
        assertNull(particle.getPosition());
    }

    // Test setting color to null
    @Test
    public void testSetNullColor() {
        TextColor.RGB nullColor = null;
        particle.setColor(nullColor);
        assertNull(particle.getColor());
    }

}
