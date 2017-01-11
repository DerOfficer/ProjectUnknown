package Model.Physics.World;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IDrawableObject;
import Model.Abstraction.IEventInteractableObject;
import View.DrawingPanel;
import View.StaticDrawingPanel;
import com.Physics2D.PhysicsObject;
import com.Physics2D.event.MovementEvent;
import com.Physics2D.event.MovementListener;
import com.SideScroller.SideScrollingPhysicsWorld;

import java.awt.*;
import java.awt.List;
import java.awt.event.KeyEvent;
import java.util.*;

public abstract class AbstractWorld extends SideScrollingPhysicsWorld{

    public static final int PIXEL_TO_METER = 50;

    private LevelRenderer renderer;

    public AbstractWorld(double gravitationalConstant, ProjectUnknownProperties properties) {
        super(gravitationalConstant * PIXEL_TO_METER);
        renderer = new LevelRenderer(properties);
        Model.UI.Button butt = new Model.UI.Button(0,0,0,0,Color.DARK_GRAY);
        butt.addEventHandler(IEventInteractableObject.EventType.KEY_RELEASED, (eventObject -> {
            if(eventObject.getSrcKey() == KeyEvent.VK_ESCAPE){
                properties.getFrame().setContentPanel(properties.getFrame().getLevelSelect());
                properties.getFrame().setForegroundPanel(new DrawingPanel(properties));
            }
        }));
        renderer.addObject(butt);
    }

    @Override
    public void addObject(PhysicsObject o) {
        super.addObject(o);
        if(o instanceof IDrawableObject){
            IDrawableObject drawableObject = (IDrawableObject)o;
            renderer.addObject(drawableObject);
        }
        o.addMovementListener(this);
    }

    @Override
    public void removeObject(PhysicsObject o){
        super.removeObject(o);
        if(o instanceof IDrawableObject){
            IDrawableObject drawableObject = (IDrawableObject)o;
            renderer.removeObject(drawableObject);
        }
        o.removeMovementListener(this);
    }

    @Override
    public void onMovement(MovementEvent event){
        super.onMovement(event);
        renderer.forceRepaint();
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
