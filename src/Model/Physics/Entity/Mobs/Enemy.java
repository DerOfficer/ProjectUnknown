package Model.Physics.Entity.Mobs;

import Control.ProjectUnknownProperties;
import Model.KeyManager;
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
        TEST("character_sprite.png",1,ManaCast.Type.TEST);

        private String spriteName;
        private int level;
        private ManaCast.Type manaSpell;

        Type(String spriteName, int level, ManaCast.Type manaSpell){
            this.spriteName = spriteName;
            this.level = level;
            this.manaSpell = manaSpell;
        }

        public BufferedImage getSpriteImage() {
            try {
                return ImageIO.read(new File("Images/"+ spriteName));
            }catch (IOException e) {
                return null;
            }
        }

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
            accelerate(.1 * AbstractWorld.PIXEL_TO_METER);
        }
        if(distance > 0) {
            accelerate(-.1 * AbstractWorld.PIXEL_TO_METER);
        }
        if(getDownwardVelocity() == 0) {
            accelerateUpward(-3 * AbstractWorld.PIXEL_TO_METER);
        }

        conjure(type.getManaSpell());
    }
}
