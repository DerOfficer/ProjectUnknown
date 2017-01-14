package Model.Physics.Block;

import Model.Abstraction.ICanvas;
import Model.Abstraction.IDrawableObject;
import Model.Physics.World.World;
import com.Physics2D.PhysicsObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by jardu on 1/12/2017.
 */
public abstract class AbstractBlock extends PhysicsObject implements IDrawableObject {

    protected ICanvas canvas;
    private String id;

    /**
     * Creates a new physics object with the given position and dimensions
     * @param x      The x-position of the physics object
     * @param y      The y-position of the physics object
     */

    protected AbstractBlock(double x, double y, String id) {
        super(x, y, 50d, 50d);

        this.id = id;
    }

    public World getWorld() {
        return (World) world;
    }

    @Override
    public void provideCanvas(ICanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public Shape getBounds() {
        return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
    }

    public String getId() {
        return id;
    }
}
