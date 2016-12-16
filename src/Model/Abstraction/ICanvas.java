package Model.Abstraction;

import java.awt.*;

public interface ICanvas {
    Graphics2D getPencil();
    Rectangle getBounds();
    void addObject(IDrawableObject object);
    void removeObject(IDrawableObject object);
    void scheduleRemoveObject(IDrawableObject object);
    boolean canDraw();
}
