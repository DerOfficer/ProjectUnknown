package Model.Physics.Entity;

import com.Physics2D.PhysicsObject;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IInteractableObject;
import Model.Abstraction.IPlayerInteractable;
import Model.KeyManager;
import Model.Physics.Projectile;
import Model.Physics.World.AbstractWorld;

import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

/**
 * Created by jardu on 12/17/2016.
 */
public class Player extends Human implements IInteractableObject{

    private ProjectUnknownProperties properties;
    private AbstractWorld abstractWorld;
    private int level;

    public Player(ProjectUnknownProperties properties) throws IOException {
        super(0, 0, 15, 36, ImageIO.read(new File("Images/character_sprite.png")),100,100,properties);
        this.properties = properties;
        setActualHealth(50);
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
            accelerateUpward(-6 * AbstractWorld.PIXEL_TO_METER);
        }
        if(KeyManager.isKeyPressed(properties.getFrame().getSettings().getSetting("shoot"))){
            conjure(Projectile.Type.TEST);
        }
    }


    public double getHealthInPercent() {
        return 0.6;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public void keyPressed(int key) {
        if(KeyManager.isKeyPressed(properties.getFrame().getSettings().getSetting("interact"))){
            for(PhysicsObject o : world.getIntersecting(new Rectangle2D.Double(getX() - 10, getY() - 10, getWidth() + 20, getHeight() + 20))){
                if(o instanceof IPlayerInteractable){
                    ((IPlayerInteractable) o).onInteractWith(this);
                }
            }
        }
    }

    @Override
    public void keyReleased(int key) {

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
