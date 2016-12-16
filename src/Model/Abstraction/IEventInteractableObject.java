package Model.Abstraction;

import Model.Event.IEventHandler;

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
