package Model.Physics.Block;

import Model.Abstraction.ICanvas;
import Model.Abstraction.IDrawableObject;
import Model.Planet;
import com.Physics2D.PhysicsObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Amasso on 25.12.2016.
 */
public class PlanetBlock extends PhysicsObject implements IDrawableObject {

    private ICanvas canvas;
    private Planet planet;

    public PlanetBlock(int x, int y, int width, int height, Planet planet) {
        super(x, y, width, height);
        this.planet = planet;
    }


    @Override
    public void draw() {
        Graphics2D g = canvas.getPencil();
        g.setColor(planet.getTopColor());
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        g.setColor(planet.getInnerColor());
        g.fillRect(getX(),getY() + (int) (getHeight()*0.05), getWidth(), (int)(getHeight()*0.95));
        g.setColor(new Color(88, 93, 106));
        for (int i = 0; i < 20; i++) {
            g.fillOval(getX()+ (int)(getWidth()*Math.random()), getY()+((20/getHeight())*i),(int)(getWidth()*0.05),(int)(getHeight()*0.08));
        }
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
        return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public double getFrictionConstant() {
        return 0.1;
    }
}
