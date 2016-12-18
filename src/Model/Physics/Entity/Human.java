package Model.Physics.Entity;

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

public class Human extends Entity implements IDrawableObject {
    private BufferedImage[]humanModel;
    private final int[]GAPS = {0,41,38,31,25};
    private ICanvas canvas;

    private Animation walk;
    private Animation armWalk;

    private HumanAnimationObject animationObject;

    public Human(int x, int y, int width, int height, BufferedImage image){
        super(x, y, width, height);
        humanModel = new SpriteSheetHandler(new int[]{0, 41/2, 38/2, 32/2, 26/2}, image).getSprites();
        //initImageModel(image);
        AnimationList animList = AnimParser.parseAnimFile(Paths.get("player.anim"));
        walk = animList.getAnimationByName("walk");
        armWalk = animList.getAnimationByName("armWalk");
        animationObject = new HumanAnimationObject("human");
    }

    /*private void initImageModel(BufferedImage image) {
        if (image != null){
            humanModel = new BufferedImage[GAPS.length+1];
            int temp = 0;
            for (int i = 1; i < humanModel.length+1; i++){
                temp = temp + GAPS[i-1];
                humanModel[i] = image.getSubimage(temp,0,GAPS[i], image.getHeight());
            }
        }
    }*/

    @Override
    public double getMass() {
        return 70;
    }

    @Override
    public void draw() {
        Graphics2D g2d = canvas.getPencil();
        if(sideWayVelocity < 0){
            g2d.translate(getX(), 0);
            g2d.scale(-1,1);
            g2d.translate(-getX(), 0);
        }
        //g2d.fillRect(getX(), getY(), getWidth(), getHeight());
        int wholeOffset = 10;
        int bodyOffset = 10;
        int headOffset = 18;
        //head
        g2d.drawImage(humanModel[1], getX()-3, getY() - humanModel[1].getHeight()-humanModel[2].getHeight() - humanModel[3].getHeight() + wholeOffset + bodyOffset + headOffset, null);
        //leg in the background
        g2d.drawImage(humanModel[3], getX()+animationObject.leg2x, getY() - humanModel[3].getHeight() + wholeOffset, null);
        //arm in the background
        g2d.drawImage(humanModel[4], getX() + animationObject.arm2x, getY() - humanModel[2].getHeight() - humanModel[3].getHeight() + wholeOffset + bodyOffset, null);
        //body
        g2d.drawImage(humanModel[2], getX()-3, getY() - humanModel[2].getHeight() - humanModel[3].getHeight() + wholeOffset + bodyOffset, null);
        //leg in the foreground
        g2d.drawImage(humanModel[3], getX()+animationObject.leg1x, getY() - humanModel[3].getHeight() + wholeOffset, null);
        //arm in the foreground
        g2d.drawImage(humanModel[4], getX() + animationObject.arm1x, getY() - humanModel[2].getHeight() - humanModel[3].getHeight() + wholeOffset + bodyOffset, null);
        //System.out.println("x: "+getX()+" xOffseT: "+((SideScrollingPhysicsWorld)world).getRendererXOffset() + " actual position: "+(getX() - ((SideScrollingPhysicsWorld)world).getRendererXOffset()));
        //System.out.println("y: "+getY()+" yOffset: "+((SideScrollingPhysicsWorld)world).getRendererYOffset() + " actual position: "+(getY() - ((SideScrollingPhysicsWorld)world).getRendererYOffset()));
        canvas.getPencil().drawImage(humanModel[0], getX(), getY(), null);
        /*for (int i = 0; i < humanModel.length; i++) {
            canvas.getPencil().drawImage(humanModel[i],null,i*50,100);
        }*/
        if(sideWayVelocity < 0){
            g2d.scale(-1,1);
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

        private int walking = 1;

        public HumanAnimationObject(String name) {
            super(name);
        }

        private void dummy(){

        }
    }
}
