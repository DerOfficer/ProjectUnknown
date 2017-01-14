package View;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IDrawableObject;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by jardu on 12/19/2016.
 */
public class StaticDrawingPanel extends DrawingPanel {

    private boolean repaintForced;

    public StaticDrawingPanel(ProjectUnknownProperties properties) {
        super(properties);
    }

    @Override
    public void addObject(IDrawableObject object){
        super.addObject(object);
        forceRepaint();
    }

    public void forceRepaint(){
        repaintForced = true;
        repaint();
    }

    @Override
    public void update(Graphics g){
        if(repaintForced){
            super.update(g);
            repaintForced = false;
        }
    }

    //We no longer want the timer to repaint. We only want it to call update(double) now
    @Override
    public void actionPerformed(ActionEvent event){
        elapsedTime = System.nanoTime() - lastLoop;
        lastLoop = System.nanoTime();
        int dt = (int) ((elapsedTime / 1000000L)+0.5);
        if ( dt == 0 ) dt = 1;
        for (IDrawableObject drawableObject : drawableObjects) {
            drawableObject.update((double) dt / 1000);
        }
    }
}
