package Model.Physics.Entity;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IInteractableObject;
import Model.KeyManager;

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
        /*char cKey = (char) key;
        if(key == KeyEvent.VK_D){
            accelerate(1);
            //sideWayVelocity = 2;
        }else if(key == KeyEvent.VK_A){
            accelerate(-1);
            //sideWayVelocity = -2;
        }
        System.out.println(sideWayVelocity);
        String sKey = Character.toString(cKey);*/
    }

    @Override
    public void keyReleased(int key) {
        /*char cKey = (char) key;
        if(key == KeyEvent.VK_W){
            if(downwardVelocity == 0)
                downwardVelocity = -5;
        }
        if(Character.toString(cKey).equals(properties.getFrame().getSettings().getSetting("jump"))){
            if(downwardVelocity == 0) {
                downwardVelocity = -20;
            }
        }*/
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

    @Override
    public void update(double dt){
        super.update(dt);
        if(Math.abs(sideWayVelocity) < 3) {
            if (KeyManager.isKeyPressed(properties.getFrame().getSettings().getSetting("left"))) {
                accelerate(-0.5);
            }
            if (KeyManager.isKeyPressed(properties.getFrame().getSettings().getSetting("right"))) {
                accelerate(0.5);
            }
        }
        if(KeyManager.isKeyPressed(properties.getFrame().getSettings().getSetting("jump")) && downwardVelocity == 0){
            downwardVelocity = -2;
        }
    }
}
