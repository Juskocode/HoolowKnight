package HollowKnight.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    private Position position;
    @BeforeEach
    void setUp() {
        position = new Position(10,10);
    }

    @Test
    void getLeft() {
        Position distinct = new Position(9,10);
        position = position.getLeft();
        Assertions.assertEquals(position,distinct);
    }

    @Test
    void getRight() {
        Position distinct = new Position(11,10);
        position = position.getRight();
        Assertions.assertEquals(position,distinct);
    }

    @Test
    void getUp() {
        Position distinct = new Position(10,9);
        position = position.getUp();
        Assertions.assertEquals(position,distinct);
    }

    @Test
    void getDown() {
        Position distinct = new Position(10,11);
        position = position.getDown();
        Assertions.assertEquals(position,distinct);
    }
}