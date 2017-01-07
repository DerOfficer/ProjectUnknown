package Model.Physics.Entity;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IInteractableObject;
import Model.KeyManager;
import Model.Physics.Projectile;

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

    public Player(int x, int y, ProjectUnknownProperties properties) throws IOException {
        super(x, y, 15, 36, ImageIO.read(new File("Images/character_sprite.png")));
        this.properties = properties;
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
        if(getVelocity() > -10) {
            if (KeyManager.isKeyPressed(properties.getFrame().getSettings().getSetting("left"))) {
                accelerate(-1);
            }
        }
        if(getVelocity() < 10){
            if (KeyManager.isKeyPressed(properties.getFrame().getSettings().getSetting("right"))) {
                accelerate(1);
            }
        }
        if(KeyManager.isKeyPressed(properties.getFrame().getSettings().getSetting("jump")) && getDownwardVelocity() == 0){
            accelerateUpward(-20d);
        }
        if (KeyManager.isKeyPressed("l")) {
            doStuff();
        }
    }

    private void doStuff(){
        canvas.addObject(new Projectile(Projectile.Type.TEST,this));
    }

}
