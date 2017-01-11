package Model.Physics;

import Model.Abstraction.ICanvas;
import Model.Abstraction.IDrawableObject;
import Model.Physics.Entity.Creature;
import com.Physics2D.Entity;
import com.Physics2D.GravitationalObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

/**
 * Created by Oussama on 07.01.2017.
 */
public class ManaCast extends Entity implements IDrawableObject {

    public enum Type{
        TEST("fireball.png",1.00,1,50);

        String imageName;
        double speedModifier,attackModifier;
        int mana;

        Type(String imageName,double speedModifier, double attackModifier, int mana){
            this.imageName = imageName;
            this.speedModifier = speedModifier;
            this.attackModifier = attackModifier;
            this.mana = mana;
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
    }

    @Override
    public void draw() {
        Graphics g = canvas.getPencil();
        g.drawImage(img,(int)getX(),(int)getY(),null);
    }

    @Override
    public void update(double dt) {
        accelerate(movement);
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
