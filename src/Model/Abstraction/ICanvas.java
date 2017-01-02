package Model.Abstraction;

import java.awt.*;

/**
 * Base interface for all Drawing contexts that want to support {@link IDrawableObject}s
 */
public interface ICanvas {
    /**
     * Gets the Graphics2D object used to draw on this Canvas. If none is available atm, an Exception is thrown
     * @return the currently valid Graphics2D object
     */
    Graphics2D getPencil();

    /**
     * Gets the bounds of this canvas. When used with subclasses of {@link java.awt.Component}, implementing this method is not necessary, as Component already defines a getBounds
     * @return the bounds of this canvas.
     */
    Rectangle getBounds();

    /**
     * Adds the given {@link IDrawableObject} to this Canvas
     * @param object the DrawableObject to add
     */
    void addObject(IDrawableObject object);

    /**
     * Removes the given {@link IDrawableObject} from this Canvas
     * @param object the DrawableObject to remove
     */
    void removeObject(IDrawableObject object);

    /**
     * Schedules the removal of the given {@link IDrawableObject} into the java.awt.EventLoop
     * @param object the DrawableObject to remove
     */
    void scheduleRemoveObject(IDrawableObject object);

    /**
     * check if a call to {@link ICanvas#getPencil()} is currently valid
     * @return {@code true} if so, {@code false} otherwise
     */
    boolean canDraw();
}
