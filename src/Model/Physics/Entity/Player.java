package Model.Physics.Entity;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IInteractableObject;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Created by jardu on 12/17/2016.
 */
public class Player extends Human implements IInteractableObject{

    private ProjectUnknownProperties properties;

    public Player(int x, int y, int width, int height, BufferedImage image, ProjectUnknownProperties properties) {
        super(x, y, width, height, image);
        this.properties = properties;
    }

    @Override
    public void keyPressed(int key) {
        char cKey = (char) key;
        String sKey = Character.toString(cKey);
    }

    @Override
    public void keyReleased(int key) {
        char cKey = (char) key;
        if(Character.toString(cKey).equals(properties.getFrame().getSettings().getSetting("jump"))){
            if(downwardVelocity == 0) {
                downwardVelocity = -20;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
}
