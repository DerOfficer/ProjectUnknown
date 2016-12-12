package Model.Abstraction;

import Model.Event.IEventHandler;
import Model.Event.InteractionEvent;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jardu on 11/24/2016.
 */
public abstract class AbstractEventInteractionObject implements IEventInteractableObject {

    private Map<EventType, List<IEventHandler>> eventHandlerMapping;

    protected  AbstractEventInteractionObject(){
        eventHandlerMapping = new HashMap<>();
        for(EventType t : EventType.values()){
            eventHandlerMapping.put(t, new ArrayList<>());
        }
    }

    @Override
    public void keyPressed(int key) {
        pushEvent(EventType.KEY_PRESSED, new InteractionEvent(key, null, this));
    }

    @Override
    public void keyReleased(int key){
        pushEvent(EventType.KEY_RELEASED, new InteractionEvent(key, null, this));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        pushEvent(EventType.MOUSE_CLICKED, new InteractionEvent(-1, e, this));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pushEvent(EventType.MOUSE_RELEASED, new InteractionEvent(-1, e, this));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pushEvent(EventType.MOUSE_PRESSED, new InteractionEvent(-1, e, this));
    }

    @Override
    public void addEventHandler(EventType t, IEventHandler handler) {
        eventHandlerMapping.get(t).add(handler);
    }

    @Override
    public void removeEventHandler(EventType t, IEventHandler handler) {
        eventHandlerMapping.get(t).remove(handler);
    }

    protected void pushEvent(EventType t, InteractionEvent eventObject){
        for(IEventHandler handler : eventHandlerMapping.get(t)){
            handler.onEvent(eventObject);
        }
    }
}