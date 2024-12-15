package HollowKnight.model.menu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HelpMenu extends Menu{
    private final List<String> lines;

    public HelpMenu() throws IOException {
        super();
        URL resource = getClass().getClassLoader().getResource("levels/level0.lvl");
        assert resource != null;
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));
        lines = readLines(br);
    }

    private List<String> readLines(BufferedReader br) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; )
            lines.add(line);
        return lines;
    }
}
