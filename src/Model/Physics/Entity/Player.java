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
public class Player extends Human {

    private ProjectUnknownProperties properties;

    public Player(int x, int y, int width, int height, BufferedImage image, ProjectUnknownProperties properties) {
        super(x, y, width, height, image);
        this.properties = properties;
    }

    @Override
    public void update(double dt){
        super.update(dt);
        if(sideWayVelocity > -3) {
            if (KeyManager.isKeyPressed(properties.getFrame().getSettings().getSetting("left"))) {
                accelerate(-0.5);
            }
        }
        if(sideWayVelocity < 3){
            if (KeyManager.isKeyPressed(properties.getFrame().getSettings().getSetting("right"))) {
                accelerate(0.5);
            }
        }
        if(KeyManager.isKeyPressed(properties.getFrame().getSettings().getSetting("jump")) && downwardVelocity == 0){
            downwardVelocity = -5;
        }
    }
}
