package HollowKnight.gui;

import com.googlecode.lanterna.TextColor;

import java.io.IOException;

public interface GUI {

    enum ACTION{UP, DOWN, RIGHT, LEFT, JUMP, DASH ,KILL, QUIT, SELECT, NULL};

    int getWidth();
    int getHeight();
    void cls();
    void flush() throws IOException;
    void close() throws IOException;

    ACTION getACTION() throws IOException;
    void drawPixel(int x, int y, TextColor.RGB color);
    void drawRectangle(int x, int y, int width, int height, TextColor.RGB color);
    void drawHitBox(int x, int y, int width, int height, TextColor.RGB color);
    GUI getGUI();
    int getFPS();
    void setFPS(int fps);
    void drawText(int x, int y, TextColor.RGB color, String Text);
}
