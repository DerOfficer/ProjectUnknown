package Model;

import Model.Abstraction.AbstractEventInteractionObject;
import Model.Abstraction.ICanvas;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by 204g03 on 16.12.2016.
 */
public class  PlanetButton extends AbstractEventInteractionObject {

    private Image image;
    private int x;
    private int y;

    private ICanvas canvas;

    public PlanetButton(Image image, int x, int y){
        this.image = image;
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw() {
        Graphics2D g2d = canvas.getPencil();
        g2d.drawImage(image,x,y,null);
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void provideCanvas(ICanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public Shape getBounds() {
        return new Rectangle(x,y, image.getWidth(null), image.getHeight(null));
    }
}
