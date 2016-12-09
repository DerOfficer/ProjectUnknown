package Model.Abstraction;

import Model.Event.IEventHandler;

/**
 * Created by jardu on 11/24/2016.
 */
public interface IEventInteractableObject extends IInteractableObject{
    enum EventType{
        KEY_PRESSED,
        KEY_RELEASED,

        MOUSE_PRESSED,
        MOUSE_RELEASED,
        MOUSE_CLICKED,
    }

    void addEventHandler(EventType t, IEventHandler handler);
    void removeEventHandler(EventType t, IEventHandler handler);
}
