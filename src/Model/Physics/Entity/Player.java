package Model.Physics.Entity;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IInteractableObject;
import Model.KeyManager;
import Model.Physics.World.AbstractWorld;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by jardu on 12/17/2016.
 */
public class Player extends Human {

    private ProjectUnknownProperties properties;
    private int level;

    public Player(ProjectUnknownProperties properties) throws IOException {
        super(0, 0, 15, 36, ImageIO.read(new File("Images/character_sprite.png")),100,100);
        this.properties = properties;
        level = 1;
    }

    public void setX(int x){
        super.setX(x);
    }

    public void setY(int y){
        super.setY(y);
    }

    @Override
    public void update(double dt){
        super.update(dt);
        if(getVelocity() > -5 * AbstractWorld.PIXEL_TO_METER) {
            if (KeyManager.isKeyPressed(properties.getFrame().getSettings().getSetting("left"))) {
                accelerate(-.4 * AbstractWorld.PIXEL_TO_METER);
            }
        }
        if(getVelocity() < 5 * AbstractWorld.PIXEL_TO_METER){
            if (KeyManager.isKeyPressed(properties.getFrame().getSettings().getSetting("right"))) {
                accelerate(.4 * AbstractWorld.PIXEL_TO_METER);
            }
        }
        if(KeyManager.isKeyPressed(properties.getFrame().getSettings().getSetting("jump")) && getDownwardVelocity() == 0){
            accelerateUpward(-10 * AbstractWorld.PIXEL_TO_METER);
        }
    }

    public double getHealthInPercent() {
        return 0.6;
    }

    public int getLevel() {
        return level;
    }
}
