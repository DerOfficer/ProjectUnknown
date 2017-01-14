package Model.Physics.Entity;

import Model.SpriteSheetHandler;
import com.SSA.Animation.Animation;
import com.SSA.Animation.AnimationObject;
import com.SSA.Annotation.Animatable;
import com.SSA.Parsing.AnimParser;
import com.SSA.Parsing.AnimationList;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Paths;

/**
 * Created by Amasso on 16.12.2016.
 */

public class Humanoid extends Creature {
    private BufferedImage[]humanModel;

    private Animation legMovement;
    private Animation armMovement;

    private HumanAnimationObject animationObject;

    public Humanoid(int x, int y, BufferedImage image, int level){
        super(x, y, 20, 82,level);

        this.humanModel = new SpriteSheetHandler(new int[]{0, 41/2, 38/2, 32/2, 26/2}, image).getSprites();

        AnimationList animList = AnimParser.parseAnimFile(Paths.get("player.anim"));

        this.legMovement = animList.getAnimationByName("walk");
        this.armMovement = animList.getAnimationByName("armWalk");

        this.animationObject = new HumanAnimationObject("human");

        animationObject.animate(armMovement);
        animationObject.animate(legMovement);
    }

    @Override
    public double getMass() {
        return 60;
    }

    @Override
    public void draw() {
        Graphics2D g2d = canvas.getPencil();
        if (sideWayVelocity < 0) {
            g2d.translate(getX() + getWidth() / 2, 0);
            g2d.scale(-1, 1);
            g2d.translate(-getX() - getWidth() / 2, 0);
        }

        int bodyOffset = 25;
        int headOffset = 20;

        //head
        g2d.drawImage(humanModel[1], (int) getX() - 3, (int) getY(), null);
        //leg in the background
        g2d.drawImage(humanModel[3], (int) getX() + animationObject.leg2x, (int) getY() + headOffset + bodyOffset, null);
        //arm in the background
        g2d.drawImage(humanModel[4], (int) getX() + animationObject.arm2x, (int) getY() + headOffset, null);
        //body
        g2d.drawImage(humanModel[2], (int) getX() - 3, (int) getY() + headOffset, null);
        //leg in the foreground
        g2d.drawImage(humanModel[3], (int) getX() + animationObject.leg1x, (int) getY() + headOffset + bodyOffset, null);
        //arm in the foreground
        g2d.drawImage(humanModel[4], (int) getX() + animationObject.arm1x, (int) getY() + headOffset, null);

        if (sideWayVelocity < 0) {
            g2d.translate(getX() + getWidth() / 2, 0);
            g2d.scale(-1, 1);
            g2d.translate(-getX() - getWidth() / 2, 0);
        }
    }

    @Override
    public void update(double dt) {
        if(sideWayVelocity == 0){
            animationObject.walking = 0;
        }else{
            animationObject.walking = 1;
        }
    }

    private class HumanAnimationObject extends AnimationObject{

        @Animatable
        private int leg1x;

        @Animatable
        private int leg2x;

        @Animatable
        private int arm1x;

        @Animatable
        private int arm2x;

        private int walking;

        public HumanAnimationObject(String name) {
            super(name);
        }
    }
}
