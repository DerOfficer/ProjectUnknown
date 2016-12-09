package Model.Event;

/**
 * Created by jardu on 11/24/2016.
 */
@FunctionalInterface
public interface IEventHandler {
    void onEvent(InteractionEvent eventObject);
}
