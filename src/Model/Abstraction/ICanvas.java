package Model.Abstraction;

import java.awt.*;

/**
 * Created by jardu on 11/24/2016.
 */
public interface ICanvas {
    Graphics2D getPencil();
    Rectangle getBounds();
    void addObject(IDrawableObject object);
    void removeObject(IDrawableObject object);
    void scheduleRemoveObject(IDrawableObject object);
    boolean canDraw();
}
