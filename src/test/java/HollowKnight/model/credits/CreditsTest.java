package HollowKnight.model.credits;

import HollowKnight.model.game.elements.Knight.Knight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditsTest {
    private Credits credits;
    private Knight player;

    @BeforeEach
    public void setup() {
        player = new Knight(0, 0, 10, 1, 10);
        while (player.getNumberOfDeaths() != 15)
            player.increaseDeaths();
        player.setBirthTime(System.currentTimeMillis() - 333000);
        this.credits = new Credits(player);
    }

    @Test
    public void equals(){
        assertEquals(15, credits.getDeaths());
        assertEquals(-5, credits.getScore());
        assertEquals(5, credits.getMinutes());
        assertEquals(33, credits.getSeconds());

        credits.setScore(18);

        String[] names = new String[1];
        names[0] = "name test 1";
        credits.setNames(names);

        String[] messages = new String[1];
        messages[0] = "message test 1";
        credits.setMessages(messages);

        assertEquals(18, credits.getScore());
        assertEquals(names, credits.getNames());
        assertEquals(messages, credits.getMessages());
    }

}