package HollowKnight.model.game.elements.Particle;

import HollowKnight.model.Position;
import HollowKnight.model.game.elements.Element;
import HollowKnight.model.menu.Menu;
import com.googlecode.lanterna.TextColor;

public class Particle extends Element {
    private int lifetime; // Tracks the number of ticks this particle has been alive
    private long lastRegenerationTime; // Tracks when the particle last stopped regenerating

    // Constructor and other methods...


    public int getLifetime() {
        return lifetime;
    }
    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }
    public long getLastRegenerationTime() {
        return lastRegenerationTime;
    }
    public void setLastRegenerationTime(long lastRegenerationTime) {
        this.lastRegenerationTime = lastRegenerationTime;
    }

    // Random delay time for regenerating the particle (in ticks)
    private static final int MIN_REGEN_DELAY = 200;
    private static final int MAX_REGEN_DELAY = 600;

    private TextColor.RGB color;
    public Particle(int x, int y, TextColor.RGB color){
        super(x, y);
        this.color = color;
    }

    public TextColor.RGB getColor() {
        return color;
    }

    public void setColor(TextColor.RGB color) {
        this.color = color;
    }

    // Random regeneration delay between MIN_REGEN_DELAY and MAX_REGEN_DELAY ticks
    public long getRandomRegenerationDelay() {
        return MIN_REGEN_DELAY + (int)(Math.random() * (MAX_REGEN_DELAY - MIN_REGEN_DELAY));
    }
}
