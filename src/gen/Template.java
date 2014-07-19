package gen;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Template {
    
    private List<String> lines;
    
    public Template() {
        lines = new ArrayList<>();
    }
    
    public void load(String path) throws IOException {
        lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
    }
    
    public List<String> getLines() {
        return this.lines;
    }
    
}
