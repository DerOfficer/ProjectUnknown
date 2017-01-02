package Model.Abstraction;

import Model.Event.IEventHandler;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Interface defining methods for {@link IInteractableObject}s that support {@link IEventHandler}s
 */
public interface IEventInteractableObject extends IInteractableObject{
    /**
     * Enum defining the type of even a specific {@link IEventHandler} should be notified of
     */
    enum EventType{
        /**
         * @see java.awt.event.KeyListener#keyPressed(KeyEvent)
         */
        KEY_PRESSED,
        /**
         * @see java.awt.event.KeyListener#keyReleased(KeyEvent)
         */
        KEY_RELEASED,

        /**
         * @see java.awt.event.MouseListener#mousePressed(MouseEvent)
         */
        MOUSE_PRESSED,
        /**
         * @see java.awt.event.MouseListener#mouseReleased(MouseEvent)
         */
        MOUSE_RELEASED,
        /**
         * @see java.awt.event.MouseListener#mouseClicked(MouseEvent)
         */
        MOUSE_CLICKED,
    }

    /**
     * Adds an event handler to handle events of the given type to this {@link IEventInteractableObject}
     * @param t the type of even to listen too
     * @param handler the EventHandler to handle the events
     */
    void addEventHandler(EventType t, IEventHandler handler);

    /**
     * Removes the given EventHandler from this {@link IEventInteractableObject}
     * @param t the type of even to listen too
     * @param handler the EventHandler to handle the events
     */
    void removeEventHandler(EventType t, IEventHandler handler);
}
