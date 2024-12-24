package HollowKnight.model.game.scene;

import HollowKnight.model.dataStructs.Position;
import HollowKnight.model.game.elements.Collectables.Collectables;
import HollowKnight.model.game.elements.Collectables.SpeedOrb;
import HollowKnight.model.game.elements.Knight.Knight;
import HollowKnight.model.game.elements.tile.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.security.Key;

import static org.junit.jupiter.api.Assertions.*;

class SceneTest {
    private Scene scene;
    private Knight player;
    private Position playerSize;

    @BeforeEach
    public void setup() {
        this.scene = new Scene(20, 16, 0);
        this.player = new Knight(0,0, 60,0,0);
        this.scene.setPlayer(player);
        this.playerSize = new Position(player.getWidth(), player.getHeight());
    }

    @Test
    public void CheckGravity() {
        assertEquals(0.25, scene.getGravity());
    }

    @Nested
    class SceneTestCollisions {
        @BeforeEach
        public void setup() {
            scene = new Scene(5, 5, 0);
            Tile[][] tileSet = {{null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, new Tile(16,16, 'G'), null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null}};
            scene.setTiles(tileSet);
            player = new Knight(0,0, 50,0,0);
            scene.setPlayer(player);
        }

        @Test
        public void checkCollisionLeft() {
            Position playerPosition1 = new Position(31, 20);
            Position playerPosition2 = new Position(24, 20);
            Position playerPosition3 = new Position(23, 20);
            Position playerPosition4 = new Position(19, 20);

            assertTrue(scene.collidesLeft(playerPosition1, playerSize));
            assertTrue(scene.collidesLeft(playerPosition2, playerSize));
            assertTrue(scene.collidesLeft(playerPosition3, playerSize));
            assertTrue(scene.collidesLeft(playerPosition4, playerSize));
        }

        @Test
        public void checkCollisionRight() {
            Position playerPosition1 = new Position(1, 12);
            Position playerPosition2 = new Position(10, 12);
            Position playerPosition3 = new Position(11, 12);
            Position playerPosition4 = new Position(15, 12);

            assertTrue(scene.collidesRight(playerPosition1, playerSize));
            assertTrue(scene.collidesRight(playerPosition2, playerSize));
            assertTrue(scene.collidesRight(playerPosition3, playerSize));
            assertTrue(scene.collidesRight(playerPosition4, playerSize));
        }

        @Test
        public void checkCollisionUp() {
            Position playerPosition1 = new Position(20, 31);
            Position playerPosition2 = new Position(20, 24);
            Position playerPosition3 = new Position(20, 23);
            Position playerPosition4 = new Position(20, 18);

            assertTrue(scene.collidesUp(playerPosition1, playerSize));
            assertTrue(scene.collidesUp(playerPosition2, playerSize));
            assertTrue(scene.collidesUp(playerPosition3, playerSize));
            assertTrue(scene.collidesUp(playerPosition4, playerSize));
        }

        @Test
        public void checkCollisionDown() {
            Position playerPosition1 = new Position(12, 1);
            Position playerPosition2 = new Position(12, 8);
            Position playerPosition3 = new Position(12, 9);
            Position playerPosition4 = new Position(12, 14);

            assertTrue(scene.collidesDown(playerPosition1, playerSize));
            assertTrue(scene.collidesDown(playerPosition2, playerSize));
            assertTrue(scene.collidesDown(playerPosition3, playerSize));
            assertTrue(scene.collidesDown(playerPosition4, playerSize));
        }
    }

    @Nested
    class SceneTestOrbs{
        @BeforeEach
        public void setup() {
            scene = new Scene(5, 5, 0);
            Collectables[][] orbs = {{null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, new SpeedOrb(8,8,1.1,'x'), null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null}};
            scene.setOrbs(orbs);
            player = new Knight(0,0, 50,0,0);
            scene.setPlayer(player);
        }

        @Test
        public void collectOrb(){
            player.setPosition(new Position(31,16));
            scene.collectOrbs(scene.getOrbs());

            assertNotNull(scene.getOrbs()[2][2]);
        }
    }

}