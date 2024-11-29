package HollowKnight.gui;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public interface RescalableGUI extends GUI {
    enum ResolutionScale {
        // Standard definitions
        SD(640, 480),         // Standard Definition (4:3)
        HD(1280, 720),        // High Definition (16:9)
        FHD(1920, 1080),      // Full HD (16:9)
        WQHD(2560, 1440),     // Wide Quad HD (16:9)
        FOUR_K(3840, 2160),   // 4K Ultra HD (16:9)

        // Smaller resolutions
        QQVGA(160, 120),      // Quarter Quarter VGA (4:3)
        HQVGA(240, 160),      // Half Quarter VGA (3:2)
        QVGA(320, 240),       // Quarter VGA (4:3)
        VGA(640, 480),        // VGA Standard (4:3)
        WVGA(800, 480),       // Wide VGA (5:3)
        FWVGA(854, 480),      // Full Wide VGA (16:9)

        // Intermediate resolutions
        SVGA(800, 600),       // Super VGA (4:3)
        DVGA(960, 640),       // Double-size VGA (3:2)
        WSVGA(1024, 576),     // Wide SVGA (16:9)
        XGA(1024, 768),       // eXtended Graphics Array (4:3)
        WXGA(1280, 800),      // Wide XGA (16:10)

        // Additional widescreen resolutions
        HD_PLUS(1600, 900),   // HD+ (16:9)
        UXGA(1600, 1200),     // Ultra XGA (4:3)
        WXGA_PLUS(1440, 900), // Wide XGA+ (16:10)
        WSXGA_PLUS(1680, 1050), // Wide SXGA+ (16:10)
        UHD_PLUS(3200, 1800); // Ultra HD+ (16:9)


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
