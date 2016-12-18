package Model.Physics;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IDrawableObject;
import View.DrawingPanel;
import com.Physics2D.PhysicsObject;
import com.SideScroller.SideScrollingPhysicsWorld;

import java.awt.*;

public class Level extends SideScrollingPhysicsWorld {

    private LevelRenderer renderer;

    public Level(double gravitationalConstant, ProjectUnknownProperties properties) {
        super(gravitationalConstant);
        renderer = new LevelRenderer(properties);
    }

    @Override
    public void addObject(PhysicsObject o) {
        super.addObject(o);
        if(o instanceof IDrawableObject){
            IDrawableObject drawableObject = (IDrawableObject)o;
            renderer.addObject(drawableObject);
        }
    }

    public LevelRenderer getRenderer() {
        return renderer;
    }

    private class LevelRenderer extends DrawingPanel {


        @Override
        public void paint(Graphics g){
            super.paint(g);
        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
        }

        @Override
        protected Point getRenderingOffset(){
            return new Point(-getRendererXOffset(), -getRendererYOffset());
        }

        public LevelRenderer(ProjectUnknownProperties properties) {
            super(properties);
        }
    }
}
