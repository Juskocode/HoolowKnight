package HollowKnight.gui;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public interface RescalableGUI extends GUI {
    enum ResolutionScale {
        // Standard definitions
        WXGA(1280, 720);

        private final int width;
        private final int height;

        ResolutionScale(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }

    ResolutionScale getResolutionScale();
    void setResolutionScale(ResolutionScale resolution) throws IOException, URISyntaxException, FontFormatException;
}
