package Model.Managing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jardu on 12/18/2016.
 */
public class KeyManager implements KeyListener {

    private static final KeyManager instance;

    static {
        instance = new KeyManager();
    }

    private List<String> pressedKeys;

    private KeyManager() {
        pressedKeys = new ArrayList<>();
    }

    public static boolean isKeyPressed(String key) {
        return key != null && instance.isKeyPressed0(key.toLowerCase());
    }

    public static KeyManager getInstance() {
        return instance;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!pressedKeys.contains(keyCodeToString(e.getKeyCode()))) {
            pressedKeys.add(keyCodeToString(e.getKeyCode()));
        }
    }

    private String keyCodeToString(int keyCode) {
        return String.valueOf((char) keyCode).toLowerCase();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(keyCodeToString(e.getKeyCode()));
    }

    private boolean isKeyPressed0(String key) {
        return pressedKeys.contains(key);
    }
}
