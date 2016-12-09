package Model.Event;

import Model.Abstraction.IEventInteractableObject;

import java.awt.event.MouseEvent;

/**
 * Created by jardu on 11/24/2016.
 */
public class InteractionEvent {
    private int srcKey;
    private MouseEvent srcMouseEvent;

    private IEventInteractableObject srcObject;

    public InteractionEvent(int srcKey, MouseEvent srcMouseEvent, IEventInteractableObject srcObject){
        this.srcKey = srcKey;
        this.srcMouseEvent = srcMouseEvent;
        this.srcObject = srcObject;
    }

    public MouseEvent getSrcMouseEvent() {
        return srcMouseEvent;
    }

    public int getSrcKey() {
        return srcKey;
    }

    public IEventInteractableObject getSrcObject() {
        return srcObject;
    }
}
