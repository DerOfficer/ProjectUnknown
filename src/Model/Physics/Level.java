package Model.Physics;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IDrawableObject;
import View.DrawingPanel;
import com.Physics2D.PhysicsObject;
import com.SideScroller.SideScrollingPhysicsWorld;

public class Level extends SideScrollingPhysicsWorld {

    private LevelRenderer renderer;

    public Level(double gravitationalConstant, ProjectUnknownProperties properties) {
        super(gravitationalConstant);
        renderer = new LevelRenderer(properties);
    }

    @Override
    public void addObject(PhysicsObject o) {
        super.addObject(o);
        if (o instanceof AbstractRenderablePhysicsObject) {
            AbstractRenderablePhysicsObject renderablePhysicsObject = (AbstractRenderablePhysicsObject) o;
            AbstractRenderablePhysicsObject.IPhysicsObjectRenderer objectRenderer = renderablePhysicsObject.getRenderer();
            renderer.addObject(objectRenderer);
        }
    }

    public LevelRenderer getRenderer() {
        return renderer;
    }

    private class LevelRenderer extends DrawingPanel {

        public LevelRenderer(ProjectUnknownProperties properties) {
            super(properties);
        }
    }
}
