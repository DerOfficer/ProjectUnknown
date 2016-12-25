package Model.Physics.Block;

import Model.Abstraction.ICanvas;
import Model.Abstraction.IDrawableObject;
import com.Physics2D.PhysicsObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GrassBlock extends PhysicsObject implements IDrawableObject {

    //private GrassBlockRenderer renderer;


    private ICanvas canvas;

    public GrassBlock(int x, int y, int width, int height) {
        super(x, y, width, height);
        //renderer = new GrassBlockRenderer();
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public double getFrictionConstant() {
        return 0.1;
    }

    @Override
    public void draw() {
        Graphics2D g = canvas.getPencil();
        g.setColor(new Color(45, 117, 44));
        g.fillRect(getX(), getY(), getWidth(), getHeight());
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

    /*public GrassBlockRenderer getRenderer() {
        return renderer;
    }*/
    /*public class GrassBlockRenderer implements AbstractRenderablePhysicsObject.IPhysicsObjectRenderer {

        private ICanvas canvas;

        @Override
        public void draw() {
            Graphics2D g = canvas.getPencil();
            g.fillRect(getX(), getY(), getWidth(), getHeight());
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
    }*/
}
