package Model.Physics.Entity.Mobs;

import Model.Managing.SpriteManager;
import Model.Physics.Entity.Humanoid;
import Model.Physics.Entity.Player;
import Model.Physics.ManaCast;
import Model.Physics.World.World;

import java.awt.image.BufferedImage;

import static Model.Managing.SpriteManager.ENTITY;
import static Model.Managing.SpriteManager.ENTITY_ZOMBIE;

/**
 * Created by Oussama on 11.01.2017.
 */
public class Enemy extends Humanoid {

    public enum Type{
        ZOMBIE(1, ManaCast.Type.FIRE_BALL, 0.08),
        MEGA_ZOMBIE(4, ManaCast.Type.METAL_BALL , 0.04);

        private int level;
        private double speed;

        private ManaCast.Type manaSpell;

        private BufferedImage image;

        Type(int level, ManaCast.Type manaSpell,double speed){
            this.level = level;
            this.manaSpell = manaSpell;
            this.speed = speed;
            this.image = SpriteManager.SPRITES[ENTITY][ENTITY_ZOMBIE];
        }

        public BufferedImage getSpriteImage() {
            return image;
        }

        public double getSpeed(){ return speed; }

        public int getLevel() {
            return level;
        }

        public ManaCast.Type getManaSpell() {
            return manaSpell;
        }
    }

    private Type type;

    public Enemy(int x, int y, Type type) {
        super(x, y,type.getSpriteImage(), type.getLevel());
        this.type = type;
    }

    @Override
    public void update(double dt){
        super.update(dt);
        int distance = (int) (getX() - getPlayer().getX());
        if (distance < 0) {
            accelerate(type.getSpeed() * World.PIXEL_TO_METER);
        }
        if (distance > 0) {
            accelerate(-type.getSpeed() * World.PIXEL_TO_METER);
        }
        if (getDownwardVelocity() == 0) {
            accelerateUpward(-3 * World.PIXEL_TO_METER);
        }

        conjure(type.getManaSpell());

        if(isDead()){
            getPlayer().earnExp(type.getLevel());
            world.removeObject(this);
        }
    }

    private Player getPlayer(){
        return ((World) world).getPlayer();
    }
}
