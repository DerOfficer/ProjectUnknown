package Model.Physics;

import Model.Abstraction.ICanvas;
import Model.Abstraction.IDrawableObject;
import Model.Managing.SpriteManager;
import Model.Physics.Entity.Creature;
import com.Physics2D.Entity;
import com.Physics2D.PhysicsObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import static Model.Managing.SpriteManager.MANA_CAST;


/**
 * Created by Oussama on 07.01.2017.
 */
public class ManaCast extends Entity implements IDrawableObject {

    public enum Type{
        FIRE_BALL("fireball.png",1.00,1.00,20, 3,3),
        LIGHT_BALL("lightball.png",2.00,0.9,10,5,1);

        private String imageName;
        private double speedModifier, attackModifier;
        private int mana, timeSpell, timeCoolDown;
        private BufferedImage image;

        Type(String imageName,double speedModifier, double attackModifier, int mana, int timeSpell, int timeCoolDown){
            this.imageName = imageName;
            this.speedModifier = speedModifier;
            this.attackModifier = attackModifier;
            this.mana = mana;
            this.timeSpell = timeSpell;
            this.timeCoolDown = timeCoolDown;
            this.image = SpriteManager.SPRITES[MANA_CAST][ordinal()];
        }

        public BufferedImage getImage() {
            return image;
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

        public int getTimeSpell(){return timeSpell;}

        public int getSpellCoolDown(){ return timeCoolDown; }
    }

    private Type type;
    private Creature creature;
    private ICanvas canvas;
    private double movement;
    private BufferedImage img;
    private Timer timer;

    public ManaCast(Type type, Creature creature){
        super(creature.getX(),creature.getY(),type.getImage().getWidth(),type.getImage().getHeight());
        this.type = type;
        this.creature = creature;
        img = type.getImage();
        movement = type.getSpeedModifier()*creature.getDirection()*15;
        setGravityAffection(false);

        timer = new Timer();
        Entity entity = this;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                world.removeObject(entity);
                timer.cancel();
            }
        };

        timer.scheduleAtFixedRate(timerTask,type.getTimeSpell()*1000,1000);
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
                timer.cancel();
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
