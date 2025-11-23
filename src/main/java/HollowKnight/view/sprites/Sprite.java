package HollowKnight.view.sprites;

import HollowKnight.gui.GUI;
import com.googlecode.lanterna.TextColor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class Sprite {
    private final BufferedImage image;

    public Sprite(String spritePath) throws IOException {
        try (InputStream stream = Objects.requireNonNull(
                getClass().getClassLoader().getResourceAsStream(spritePath),
                "Resource not found on classpath: " + spritePath
        )) {
            image = ImageIO.read(stream);
        }
    }
    public BufferedImage getImage() {return image;}

    public void draw(GUI gui, int x, int y) {
        for (int dx = 0; dx < image.getWidth(); dx++) {
            for (int dy = 0; dy < image.getHeight(); dy++) {
                int ARGB = image.getRGB(dx, dy);
                if (getTransparency(ARGB) == 0)
                    continue;
                gui.drawPixel(x + dx, y + dy, getRGB(ARGB));
            }
        }
    }

    private int getTransparency(int ARGB) {
        return ARGB >> 24;
    }

    private TextColor.RGB getRGB(int ARGB) {
        int red = ARGB >> 16 & 0xFF;
        int green = ARGB >> 8 & 0xFF;
        int blue = ARGB & 0xFF;
        return new TextColor.RGB(red, green, blue);
    }
}
