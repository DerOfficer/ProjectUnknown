package Model;

import Model.Abstraction.AbstractEventInteractionObject;
import Model.Abstraction.ICanvas;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by 204g03 on 16.12.2016.
 */
public class Planet extends AbstractEventInteractionObject {

    private int x;
    private int y;
    private int radius;
    private Color color;

    private Ellipse2D world;
    private ICanvas canvas;

    public Planet(int x, int y, int radius, Color color){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        world = new Ellipse2D.Double(this.x,this.y,this.radius,this.radius);
    }

    @Override
    public void draw() {
        Graphics2D g2d = canvas.getPencil();
        g2d.setColor(color);
        g2d.fill(world);

    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void provideCanvas(ICanvas canvas) {

    }

    @Override
    public Shape getBounds() {
        return null;
    }
}
