package Model.Event;

@FunctionalInterface
public interface IEventHandler {
    void onEvent(InteractionEvent eventObject);
}
