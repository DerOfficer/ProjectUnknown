package Model.Physics.Entity;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IInteractableObject;
import Model.Abstraction.IPlayerInteractable;
import Model.Managing.KeyManager;
import Model.Managing.SpriteManager;
import Model.Physics.ManaCast;
import Model.Physics.World.World;
import com.Physics2D.PhysicsObject;

import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import static Model.Managing.SpriteManager.ENTITY;
import static Model.Managing.SpriteManager.ENTITY_PLAYER;

/**
 * Created by jardu on 12/17/2016.
 */
public class Player extends Humanoid implements IInteractableObject{

    private ProjectUnknownProperties properties;
    private int level, exp, maxExp;
    private ManaCast.Type currentCast;

    /**
     * constructs player
     * @param properties
     */
    public Player(ProjectUnknownProperties properties){
        super(0, 0, SpriteManager.SPRITES[ENTITY][ENTITY_PLAYER],1,properties);
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

    /**
     * earns xp and calculate max exp if level up
     * @param enemyLevel
     */
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
        if(getVelocity() > -5 * World.PIXEL_TO_METER) {
            if (KeyManager.isKeyPressed(properties.getFrame().getSettings().getSetting("left"))) {
                accelerate(-.4 * World.PIXEL_TO_METER);
            }
        }
        if(getVelocity() < 5 * World.PIXEL_TO_METER){
            if (KeyManager.isKeyPressed(properties.getFrame().getSettings().getSetting("right"))) {
                accelerate(.4 * World.PIXEL_TO_METER);
            }
        }
        if(KeyManager.isKeyPressed(properties.getFrame().getSettings().getSetting("jump")) && getDownwardVelocity() == 0 && world.getObjectBelow(this) != null){
            accelerateUpward(-6 * World.PIXEL_TO_METER);
        }
        if(KeyManager.isKeyPressed(properties.getFrame().getSettings().getSetting("shoot"))){
            conjure(currentCast);
        }
        for (int i = 0; i < 8; i++) {
            if(KeyManager.isKeyPressed(String.valueOf(i+1))){
                if(i <= ManaCast.Type.values().length) {
                    currentCast = ManaCast.Type.values()[i];
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
