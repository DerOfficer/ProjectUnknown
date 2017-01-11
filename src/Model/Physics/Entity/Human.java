package Model.Physics.Entity;

import Control.ProjectUnknownProperties;
import Model.Abstraction.ICanvas;
import Model.Abstraction.IDrawableObject;
import Model.SpriteSheetHandler;
import com.Physics2D.Entity;
import com.SSA.Animation.Animation;
import com.SSA.Animation.AnimationObject;
import com.SSA.Annotation.Animatable;
import com.SSA.Parsing.AnimParser;
import com.SSA.Parsing.AnimationList;
import com.SideScroller.SideScrollingPhysicsWorld;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Paths;

/**
 * Created by Amasso on 16.12.2016.
 */

public class Human extends Creature {
    private BufferedImage[]humanModel;
    protected ICanvas canvas;

    private Animation walk;
    private Animation armWalk;

    private HumanAnimationObject animationObject;

    public Human(int x, int y, int width, int height, BufferedImage image, int health, int mana, ProjectUnknownProperties properties){
        super(x, y, width, 82, health, mana,properties);
        humanModel = new SpriteSheetHandler(new int[]{0, 41/2, 38/2, 32/2, 26/2}, image).getSprites();
        /*humanModel = new SpriteSheetHandler(new Dimension[]{new Dimension(41/2,22),
                                                            new Dimension(38/2,31),
                                                            new Dimension(32/2,39),
                                                            new Dimension(26/2,29)}, image).getSprites();*/
        AnimationList animList = AnimParser.parseAnimFile(Paths.get("player.anim"));
        walk = animList.getAnimationByName("walk");
        armWalk = animList.getAnimationByName("armWalk");
        animationObject = new HumanAnimationObject("human");
        animationObject.animate(armWalk);
        animationObject.animate(walk);
    }

    @Override
    public double getMass() {
        return 60;
    }

    @Override
    public void draw() {
        Graphics2D g2d = canvas.getPencil();
        if(sideWayVelocity < 0){
            g2d.translate(getX()+getWidth()/2, 0);
            g2d.scale(-1,1);
            g2d.translate(-getX()-getWidth()/2, 0);
        }
        //g2d.fillRect(getX(), getY(), getWidth(), getHeight());
        int wholeOffset = 10;
        int bodyOffset = 25;
        int headOffset = 20;

        //head
        g2d.drawImage(humanModel[1], (int)getX()-3, (int)getY(), null);
        //g2d.drawImage(humanModel[1], getX()-3, getY() - humanModel[1].getHeight()-humanModel[2].getHeight() - humanModel[3].getHeight() + wholeOffset + bodyOffset + headOffset, null);
        //leg in the background
        g2d.drawImage(humanModel[3], (int)getX()+animationObject.leg2x, (int)getY() + headOffset + bodyOffset, null);
        //g2d.drawImage(humanModel[3], getX()+animationObject.leg2x, getY() + humanModel[1].getHeight() - 2, null);
        //g2d.drawImage(humanModel[3], getX()+animationObject.leg2x, getY() - humanModel[3].getHeight() + wholeOffset, null);
        //arm in the background
        g2d.drawImage(humanModel[4], (int)getX() + animationObject.arm2x, (int)getY() + headOffset, null);
        //g2d.drawImage(humanModel[4], getX() + animationObject.arm2x, getY() - humanModel[2].getHeight() - humanModel[3].getHeight() + wholeOffset + bodyOffset, null);
        //body
        g2d.drawImage(humanModel[2], (int) getX()-3, (int)getY() + headOffset, null);
        //g2d.drawImage(humanModel[2], getX()-3, getY() - humanModel[2].getHeight() - humanModel[3].getHeight() + wholeOffset + bodyOffset, null);
        //leg in the foreground
        g2d.drawImage(humanModel[3], (int)getX()+animationObject.leg1x, (int)getY() + headOffset + bodyOffset, null);
        //g2d.drawImage(humanModel[3], getX()+animationObject.leg1x, getY() - humanModel[3].getHeight() + wholeOffset, null);
        //arm in the foreground
        g2d.drawImage(humanModel[4], (int)getX() + animationObject.arm1x, (int)getY() + headOffset, null);
        //g2d.drawImage(humanModel[4], getX() + animationObject.arm1x, getY() - humanModel[2].getHeight() - humanModel[3].getHeight() + wholeOffset + bodyOffset, null);
        //System.out.println("x: "+getX()+" xOffseT: "+((SideScrollingPhysicsWorld)world).getRendererXOffset() + " actual position: "+(getX() - ((SideScrollingPhysicsWorld)world).getRendererXOffset()));
        //System.out.println("y: "+getY()+" yOffset: "+((SideScrollingPhysicsWorld)world).getRendererYOffset() + " actual position: "+(getY() - ((SideScrollingPhysicsWorld)world).getRendererYOffset()));
        canvas.getPencil().drawImage(humanModel[0], (int)getX(), (int)getY(), null);
        /*for (int i = 0; i < humanModel.length; i++) {
            canvas.getPencil().drawImage(humanModel[i],null,i*50,100);
        }*/

        //g2d.setColor(Color.RED);
        //g2d.drawRect(getX(), getY(), getWidth(), getHeight());

        if(sideWayVelocity < 0){
            g2d.translate(getX()+getWidth()/2, 0);
            g2d.scale(-1,1);
            g2d.translate(-getX()-getWidth()/2, 0);
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

    @Override
    public void provideCanvas(ICanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public Shape getBounds() {
        return null;
    }

    private class HumanAnimationObject extends AnimationObject{

        @Animatable(notificationMethod = "dummy")
        private int leg1x;

        @Animatable(notificationMethod = "dummy")
        private int leg2x;

        @Animatable(notificationMethod = "dummy")
        private int arm1x;

        @Animatable(notificationMethod = "dummy")
        private int arm2x;

        private int walking;

        public HumanAnimationObject(String name) {
            super(name);
        }

        private void dummy(){ }
    }
}
