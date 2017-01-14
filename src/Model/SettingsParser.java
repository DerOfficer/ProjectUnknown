package Model;

import Control.ProjectUnknownProperties;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SettingsParser {

    private HashMap<String, String> map;

    private Path path;

    public SettingsParser(@NotNull Path path) {
        this.path = path;
        List<String> lines = null;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            ProjectUnknownProperties.raiseException(e);
        }
        map = new HashMap<>();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> writeSettings()));
        parseLines(lines);
    }

    private void parseLines(@NotNull List<String> lines) {
        for (String line : lines) {
            String[] temp = line.split(":");
            map.put(temp[0], temp[1]);
        }
    }

    public HashMap<String, String> getMap() {
        return map;
    }

    public void addSetting(String key, String value) {
        map.put(key, value);
    }

    public String getSetting(String key) {
        return map.get(key);
    }

    public void overrideSetting(String key, String newValue) {
        addSetting(key, newValue);
    }

    public void writeSettings() {
        try {
            List<String> tempMap = new ArrayList<>();
            for (String tempKey : map.keySet()) {
                tempMap.add(tempKey + ":" + map.get(tempKey));
            }

            Files.write(path, tempMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
