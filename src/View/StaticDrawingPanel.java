package View;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IDrawableObject;

/**
 * Created by jardu on 12/19/2016.
 */
public class StaticDrawingPanel extends DrawingPanel {
    public StaticDrawingPanel(ProjectUnknownProperties properties) {
        super(properties);
        timer.stop();
    }

    @Override
    public void addObject(IDrawableObject object){
        super.addObject(object);
        repaint();
    }
}
