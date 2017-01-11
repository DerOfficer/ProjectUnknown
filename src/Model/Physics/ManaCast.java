package Model.Physics;

import Model.Abstraction.ICanvas;
import Model.Abstraction.IDrawableObject;
import Model.Physics.Entity.Creature;
import Model.Physics.World.AbstractWorld;
import com.Physics2D.Entity;
import com.Physics2D.GravitationalObject;
import com.Physics2D.PhysicsObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.*;
import java.util.List;

/**
 * Created by Oussama on 07.01.2017.
 */
public class ManaCast extends Entity implements IDrawableObject {

    public enum Type{
        FIREBALL("fireball.png",1.00,1.00,20, 3.0);

        String imageName;
        double speedModifier,attackModifier,time;
        int mana;

        Type(String imageName,double speedModifier, double attackModifier, int mana, double time){
            this.imageName = imageName;
            this.speedModifier = speedModifier;
            this.attackModifier = attackModifier;
            this.mana = mana;
            this.time = time;
        }

        public BufferedImage getImage() {
            try {
                return ImageIO.read(new File("Images/ManaCast/"+imageName));
            }catch (IOException e) {
                return null;
            }
        }

        public double getSpeedModifier() {
            return speedModifier;
        }

        public double getAttackModifier() {
            return attackModifier;
        }

        public int getMana() {
            return mana;
        }

        public double getTime(){return time;}
    }

    private Type type;
    private Creature creature;
    private ICanvas canvas;
    private double movement;
    private BufferedImage img;

    public ManaCast(Type type, Creature creature){
        super(creature.getX(),creature.getY(),type.getImage().getWidth(),type.getImage().getHeight());
        this.type = type;
        this.creature = creature;
        img = type.getImage();
        movement = type.getSpeedModifier()*creature.getDirection()*15;
        setGravityAffection(false);

        Timer timer = new Timer();
        Entity entity = this;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                world.removeObject(entity);
            }
        };

        timer.scheduleAtFixedRate(timerTask,(int)(type.getTime()*1000),1000);
    }

    @Override
    public void draw() {
        Graphics g = canvas.getPencil();
        g.drawImage(img,(int)getX(),(int)getY(),null);
    }

    @Override
    public void update(double dt) {
        accelerate(movement);
        for(PhysicsObject object: world.getIntersecting(this)){
            if(object instanceof Creature && object != creature){
                Creature enemy = (Creature) object;
                enemy.setActualHealth(enemy.getActualHealth() - (int) (type.getAttackModifier() * enemy.getAttack()));
                world.removeObject(this);
            }
        }
    }

    @Override
    public void provideCanvas(ICanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public Shape getBounds() {
        return new Rectangle((int)getX(),(int)getY(),type.getImage().getWidth(),type.getImage().getHeight());
    }

    @Override
    public double getMass() {
        return 1000;
    }
}
