package Model.Physics.Entity.Mobs;

import Control.ProjectUnknownProperties;
import Model.Physics.Entity.Human;
import Model.Physics.Entity.Player;
import Model.Physics.ManaCast;
import Model.Physics.World.AbstractWorld;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Oussama on 11.01.2017.
 */
public class Enemy extends Human{

    public enum Type{
        ZOMBIE("zombie_sprite.png", 1, ManaCast.Type.FIREBALL, 0.08);

        private String spriteName;
        private int level;
        private ManaCast.Type manaSpell;
        private double speed;

        Type(String spriteName, int level, ManaCast.Type manaSpell,double speed){
            this.spriteName = spriteName;
            this.level = level;
            this.manaSpell = manaSpell;
            this.speed = speed;
        }

        public BufferedImage getSpriteImage() {
            try {
                return ImageIO.read(new File("Images/"+ spriteName));
            }catch (IOException e) {
                return null;
            }
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
    private Player player;

    public Enemy(int x, int y,Type type, ProjectUnknownProperties properties) {
        super(x, y,type.getSpriteImage(), type.getLevel(), properties);
        this.type = type;
        this.player = properties.getPlayer();
    }


    @Override
    public void update(double dt){
        super.update(dt);
        int distance = (int) (getX() - player.getX());
        if(distance < 0){
            accelerate(type.getSpeed() * AbstractWorld.PIXEL_TO_METER);
        }
        if(distance > 0) {
            accelerate(-type.getSpeed() * AbstractWorld.PIXEL_TO_METER);
        }
        if(getDownwardVelocity() == 0) {
            accelerateUpward(-3 * AbstractWorld.PIXEL_TO_METER);
        }

        conjure(type.getManaSpell());

        if(isDead()){
            player.earnExp(type.getLevel());
            world.removeObject(this);
            System.out.println("Enemy health: "+getActualHealth());
        }
    }
}
