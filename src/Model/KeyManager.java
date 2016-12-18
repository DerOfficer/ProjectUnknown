package Model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jardu on 12/18/2016.
 */
public class KeyManager implements KeyListener {

    private static KeyManager instance;

    private List<String> pressedKeys;

    static{
        instance = new KeyManager();
    }

    public static boolean isKeyPressed(String key){
        return instance.isKeyPressed0(key);
    }

    public static KeyManager getInstance() {
        return instance;
    }

    private KeyManager(){
        pressedKeys = new ArrayList<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(!pressedKeys.contains(keyCodeToString(e.getKeyCode()))){
            pressedKeys.add(keyCodeToString(e.getKeyCode()));
        }
    }

    private String keyCodeToString(int keyCode){
        return String.valueOf((char)keyCode).toLowerCase();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(keyCodeToString(e.getKeyCode()));
    }

    private boolean isKeyPressed0(String key){
        return pressedKeys.contains(key);
    }
}
