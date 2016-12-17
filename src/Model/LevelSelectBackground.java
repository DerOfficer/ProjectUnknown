package Model;

import Model.Abstraction.ICanvas;
import Model.Abstraction.IDrawableObject;

import java.awt.*;

/**
 * Created by Max on 17.12.2016.
 */
public class LevelSelectBackground implements IDrawableObject {

    private ICanvas canvas;
    private Image image;

    public LevelSelectBackground(Image image){
        this.image = image;
    }

    @Override
    public void draw() {
        Graphics2D g2d = canvas.getPencil();
        g2d.drawImage(image,(int)(canvas.getBounds().getWidth()/2 - image.getWidth(null)/2),(int)(canvas.getBounds().getHeight()/2 - image.getHeight(null)/2),null);
    }

    @Override
    public Shape getBounds() {
        return new Rectangle((int)(canvas.getBounds().getWidth()/2 - image.getWidth(null)/2),(int)(canvas.getBounds().getHeight()/2 - image.getHeight(null)/2), image.getWidth(null), image.getHeight(null));
    }

    @Override
    public void update(double dt) {
    }

    @Override
    public void provideCanvas(ICanvas canvas) {
        this.canvas = canvas;
    }

}
