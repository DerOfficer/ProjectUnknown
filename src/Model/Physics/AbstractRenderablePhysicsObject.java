package Model.Physics;

import Model.Abstraction.IDrawableObject;
import com.Physics2D.PhysicsObject;
import com.SideScroller.SideScrollingPhysicsWorld;

public abstract class AbstractRenderablePhysicsObject extends PhysicsObject {
    protected AbstractRenderablePhysicsObject(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public abstract IPhysicsObjectRenderer getRenderer();

    private SideScrollingPhysicsWorld getSideScrollingWorld() {
        return (SideScrollingPhysicsWorld) world;
    }

    @Override
    public int getX() {
        return super.getX() - getSideScrollingWorld().getRendererXOffset();
    }

    @Override
    public int getY() {
        return super.getY() - getSideScrollingWorld().getRendererYOffset();
    }

    public interface IPhysicsObjectRenderer extends IDrawableObject {

    }
}
