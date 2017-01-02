package Model.Event;

import java.io.IOException;

/**
 * Interface defining an EventHandler to be used with an {@link Model.Abstraction.IEventInteractableObject}
 */
@FunctionalInterface
public interface IEventHandler {

    /**
     * Callback method that gets called everytime the event this handler got registered for is fired
     * @param eventObject an {@link InteractionEvent} object containing a subset of information about this event
     */
    void onEvent(InteractionEvent eventObject) throws IOException;
}
