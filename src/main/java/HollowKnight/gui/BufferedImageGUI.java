package HollowKnight.gui;

import java.awt.image.BufferedImage;
import java.awt.Color;
import com.googlecode.lanterna.TextColor;

public class BufferedImageGUI implements GUI {
    private final BufferedImage buffer;

    public BufferedImageGUI(BufferedImage buffer) {
        this.buffer = buffer;
    }

    @Override
    public int getWidth() {
        return buffer.getWidth();
    }

    @Override
    public int getHeight() {
        return buffer.getHeight();
    }

    @Override
    public void drawPixel(int x, int y, TextColor.RGB color) {
        if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
            buffer.setRGB(x, y, new Color(color.getRed(), color.getGreen(), color.getBlue()).getRGB());
        }
    }

    @Override
    public void drawRectangle(int x, int y, int width, int height, TextColor.RGB color) {
        Color awtColor = new Color(color.getRed(), color.getGreen(), color.getBlue());
        java.awt.Graphics2D g = buffer.createGraphics();
        g.setColor(awtColor);
        g.fillRect(x, y, width, height);
        g.dispose();
    }

    @Override
    public void cls() {
        java.awt.Graphics2D g = buffer.createGraphics();
        g.setColor(new Color(0, 0, 0, 0)); // Transparent or black background
        g.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
        g.dispose();
    }

    @Override
    public void flush() {
        // No-op for a static buffer
    }

    @Override
    public void close() {
        // No-op for a static buffer
    }

    // Unused methods in this context
    @Override
    public ACTION getACTION() {
        throw new UnsupportedOperationException("Not supported for BufferedImageGUI");
    }

    @Override
    public GUI getGUI() {
        throw new UnsupportedOperationException("Not supported for BufferedImageGUI");
    }

    @Override
    public int getFPS() {
        throw new UnsupportedOperationException("Not supported for BufferedImageGUI");
    }

    @Override
    public void setFPS(int fps) {
        throw new UnsupportedOperationException("Not supported for BufferedImageGUI");
    }

    @Override
    public void drawHitBox(int x, int y, int width, int height, TextColor.RGB color) {
        throw new UnsupportedOperationException("Not implemented for BufferedImageGUI");
    }

    @Override
    public void drawText(int x, int y, TextColor.RGB color, String Text) {
        java.awt.Graphics2D g = buffer.createGraphics();
        g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue()));
        g.drawString(Text, x, y);
        g.dispose();
    }
}
