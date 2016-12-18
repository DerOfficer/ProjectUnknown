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

    private List<Integer> pressedKeys;

    static{
        instance = new KeyManager();
    }

    public static boolean isKeyPressed(int keyCode){
        return instance.isKeyPressed0(keyCode);
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
        if(!pressedKeys.contains(e.getKeyCode())){
            pressedKeys.add(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove((Integer)e.getKeyCode());
    }

    private boolean isKeyPressed0(int code){
        return pressedKeys.contains(code);
    }
}
