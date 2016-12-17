package Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SettingsParser {
    private HashMap<String, String> map;
    private Path path;
    public SettingsParser(Path path) throws IOException {
        this.path = path;
        List<String> lines = Files.readAllLines(path);
        map = new HashMap<>();

        parseLines(lines);
    }

    private void parseLines(List<String> lines){
        for(String line: lines){
            String[]temp = line.split(":");
            map.put(temp[0],temp[1]);
        }
    }

    public HashMap getMap(){
        return map;

    }

    public void writeSetting(String key, String value){
        map.put(key, value);

        try {
            List<String> temp = Files.readAllLines(path);
            temp.addAll(Arrays.asList(new String[] {key+":"+value}));
            Files.write(path,temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
