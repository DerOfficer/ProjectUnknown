package Model.Physics.Entity;

import Model.Abstraction.ICanvas;
import Model.Abstraction.IDrawableObject;
import Model.Abstraction.IInteractableObject;
import com.Physics2D.Entity;
import com.SSA.Animation.Animation;
import com.SSA.Animation.AnimationObject;
import com.SSA.Annotation.Animatable;
import com.SSA.Parsing.AnimParser;
import com.SSA.Parsing.AnimationList;

import java.awt.*;
import java.awt.event.MouseEvent;
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
        //initImageModel(image);
        AnimationList animList = AnimParser.parseAnimFile(Paths.get("player.anim"));
        walk = animList.getAnimationByName("walk");
        armWalk = animList.getAnimationByName("armWalk");
        animationObject = new HumanAnimationObject("human");
        animationObject.animate(walk);
        animationObject.animate(armWalk);
    }

    private void initImageModel(BufferedImage image) {
        if (image != null){
            humanModel = new BufferedImage[GAPS.length+1];
            int temp = 0;
            for (int i = 1; i < humanModel.length+1; i++){
                temp = temp + GAPS[i-1];
                humanModel[i] = image.getSubimage(temp,0,GAPS[i], image.getHeight());
            }
        }
    }

    @Override
    public double getMass() {
        return 70;
    }

    @Override
    public void draw() {

    }

    @Override
    public void update(double dt) {
        for (int i = 0; i < humanModel.length; i++) {
            canvas.getPencil().drawImage(humanModel[i],null,i*50,100);
        }
    }

    @Override
    public void provideCanvas(ICanvas canvas) {
        canvas = canvas;
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
            System.out.println("this is a test");
        }
    }
}
