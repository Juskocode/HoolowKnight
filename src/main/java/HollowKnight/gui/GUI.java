package HollowKnight.gui;

import java.io.IOException;

public interface GUI {
    enum ACTION{UP, DOWN, RIGHT, LEFT, QUIT, SELECT, NULL};
    void cls();
    void flush() throws IOException;
    void close() throws IOException;
    ACTION getACTION() throws IOException;
    void drawPixel(int x, int y, String color);

}
