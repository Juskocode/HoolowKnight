package HollowKnight.model.game.elements.Collectables;

public class OrbFactory {
    public static final char ENERGY_ORB = 'e';
    public static final char SPEED_ORB = 's';
    public static final char HEALTH_ORB = 'h';

    public static Collectables createOrb(char type, int x, int y) {
        return switch (type) {
            case ENERGY_ORB -> new EnergyOrb(x, y, 10, ENERGY_ORB);
            case SPEED_ORB -> new SpeedOrb(x, y, 1.1, SPEED_ORB);
            case HEALTH_ORB -> new HealthOrb(x, y, 25, HEALTH_ORB);
            default -> null;
        };
    }
}

