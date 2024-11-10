package HollowKnight.gui;

public interface GUI {
    enum ACTION{UP, DOWN, RIGHT, LEFT, QUIT, SELECT};

    void flush();
    void drawPixel();
    void cleanUp();
    void close();

}
