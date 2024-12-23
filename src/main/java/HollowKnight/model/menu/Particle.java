package HollowKnight.model.menu;

import HollowKnight.model.dataStructs.Position;

import HollowKnight.state.particle.ParticleState;
import com.googlecode.lanterna.TextColor;

public class Particle {
    private Position position;
    private TextColor.RGB color;
    private ParticleState state;

    public Particle(Position position, ParticleState state, TextColor.RGB color) {
        this.position = position;
        this.state = state;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public TextColor.RGB getColor() {
        return color;
    }

    public void setColor(TextColor.RGB color) {
        this.color = color;
    }

    public ParticleState getState() {
        return state;
    }

    public void setState(ParticleState state) {
        this.state = state;
    }
}

