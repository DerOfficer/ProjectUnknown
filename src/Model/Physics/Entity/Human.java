package Model.Physics.Entity;

import Model.Abstraction.ICanvas;
import Model.Abstraction.IInteractableObject;
import com.Physics2D.Entity;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Created by Amasso on 16.12.2016.
 */

public abstract class Human extends Entity implements IInteractableObject {
    private BufferedImage[]humanModel;
    private final int[]GAPS = {0,41,38,31,25};
    private ICanvas canvas;

    public Human(int x, int y, int width, int height, BufferedImage image){
        super(x, y, width, height);
        initImageModel(image);
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
    public void keyPressed(int key) {

    }

    @Override
    public void keyReleased(int key) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

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
}
