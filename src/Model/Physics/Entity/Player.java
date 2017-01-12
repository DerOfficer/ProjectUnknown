package Model.Physics.Entity;

import com.Physics2D.PhysicsObject;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IInteractableObject;
import Model.Abstraction.IPlayerInteractable;
import Model.KeyManager;
import Model.Physics.ManaCast;
import Model.Physics.World.AbstractWorld;

import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

/**
 * Created by jardu on 12/17/2016.
 */
public class Player extends Humanoid implements IInteractableObject{

    private ProjectUnknownProperties properties;
    private int level,exp,maxExp;
    private ManaCast.Type currentCast;

    public Player(ProjectUnknownProperties properties) throws IOException {
        super(0, 0, ImageIO.read(new File("Images/character_sprite.png")),1,properties);
        this.properties = properties;
        level = 1;
        exp = 0;
        maxExp = 100;
        currentCast = ManaCast.Type.FIRE_BALL;
    }

    public void setX(int x){
        super.setX(x);
    }

    public void setY(int y){
        super.setY(y);
    }

    public void earnExp(int enemyLevel){
        int earnedExp = 30 +((enemyLevel)*10);
        exp = exp + earnedExp;

        while(exp >= maxExp){
            exp = exp - maxExp;
            maxExp = 100 + (level*10);
            level++;
        }

        setStats();
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
            conjure(currentCast);
        }
        for (int i = 0; i < 8; i++) {
            if(KeyManager.isKeyPressed(String.valueOf(i+1))){
                if(i <= ManaCast.Type.values().length) {
                    currentCast = ManaCast.Type.values()[i];
                    System.out.println(currentCast);
                }
            }
        }

    }

    public int getLevel() {
        return level;
    }

    public double getExperienceInPercent() {
        return (double) exp/maxExp;
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
