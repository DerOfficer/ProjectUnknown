package Model.Physics;

import Model.Abstraction.ICanvas;
import Model.Abstraction.IDrawableObject;
import Model.Physics.Entity.Creature;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Oussama on 07.01.2017.
 */
public class Projectile implements IDrawableObject {

    public enum Type{
        TEST("star.png",1.00,2.00,0);

        String imageName;
        double speedModifier,attackModifier;
        int stamina;

        Type(String imageName,double speedModifier, double attackModifier, int stamina){
            this.imageName = imageName;
            this.speedModifier = speedModifier;
            this.attackModifier = attackModifier;
            this.stamina = stamina;
        }

        public BufferedImage getImage() {
            try {
                return ImageIO.read(new File("Images/Projectile/"+imageName));
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

        public int getStamina() {
            return stamina;
        }
    }

    private Type type;
    private Creature creature;
    private ICanvas canvas;
    private double x,y,movement;

    public Projectile(Type type, Creature creature){
        this.type = type;
        this.creature = creature;
        x = creature.getX();
        y = creature.getY();
        movement = (int)(creature.getSideWayVelocity()*type.getSpeedModifier());
    }

    @Override
    public void draw() {
        Graphics g = canvas.getPencil();
        g.drawImage(type.getImage(),(int) x,(int) y,null);
    }

    @Override
    public void update(double dt) {
        x = x + movement;
    }

    @Override
    public void provideCanvas(ICanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public Shape getBounds() {
        return new Rectangle((int)x,(int)y,type.getImage().getWidth(),type.getImage().getHeight());
    }
}
