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

    private String id;

    protected ICanvas canvas;

    /**
     * Creates a new physics object with the given position and dimensions
     *
     * @param x      The x-position of the physics object
     * @param y      The y-position of the physics object
     * @param width  the width of the physics object
     * @param height the height of the physics object
     */

    protected AbstractBlock(double x, double y, double width, double height, String id) {
        super(x, y, width, height);

        this.id = id;
    }

    public World getWorld(){
        return (World)world;
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
