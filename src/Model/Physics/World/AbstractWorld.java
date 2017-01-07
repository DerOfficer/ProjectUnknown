package Model.Physics.World;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IDrawableObject;
import View.StaticDrawingPanel;
import com.Physics2D.PhysicsObject;
import com.SideScroller.SideScrollingPhysicsWorld;

import java.awt.*;
import java.awt.List;
import java.util.*;

public abstract class AbstractWorld extends SideScrollingPhysicsWorld {

    public static final int PIXEL_TO_METER = 50;

    private LevelRenderer renderer;

    public AbstractWorld(double gravitationalConstant, ProjectUnknownProperties properties) {
        super(gravitationalConstant * PIXEL_TO_METER);
        renderer = new LevelRenderer(properties);
    }

    @Override
    public void addObject(PhysicsObject o) {
        super.addObject(o);
        if(o instanceof IDrawableObject){
            IDrawableObject drawableObject = (IDrawableObject)o;
            renderer.addObject(drawableObject);
        }
        o.addMovementListener((event) ->{
            renderer.forceRepaint();
            System.out.println("y-offset: "+renderer.getRenderingOffset().y+ " y position: "+o.getY());
        });
    }

    public LevelRenderer getRenderer() {
        return renderer;
    }

    private class LevelRenderer extends StaticDrawingPanel {

        @Override
        protected Point getRenderingOffset(){
            return new Point(-getRendererXOffset(), -getRendererYOffset());
        }

        public LevelRenderer(ProjectUnknownProperties properties) {
            super(properties);
        }
    }
}
