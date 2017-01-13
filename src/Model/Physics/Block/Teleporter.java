package Model.Physics.Block;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IPlayerInteractable;
import Model.Notification;
import com.Physics2D.Entity;

/**
 * Class representing Teleporters which allow Entities to teleport to a different Teleporter object via interaction
 * Created by jardu on 1/10/2017.
 */
public class Teleporter extends Block implements IPlayerInteractable{

    /**
     * The Teleporter we teleport to
     */
    private Teleporter link;
    private ProjectUnknownProperties properties;

    /**
     * Constructs a new Teleporter object. Linking is not done in the constructor, as 2 teleporters might want to
     * link against each other.
     * @param properties
     * @param x
     * @param y
     *
     * @see Teleporter#link(Teleporter)
     */
    public Teleporter(ProjectUnknownProperties properties, int x, int y) {
        super(x, y, BlockType.CRYSTAL_STONE_BRICK);
        this.properties = properties;
    }

    /**
     * Links this {@link Teleporter} against the given one. Entities interacting with this Teleporter will be teleported to given one
     * from now on
     * @param against The Teleporter to link against
     */
    public void link(Teleporter against){
        this.link = against;
    }

    /**
     * Checks whether this teleporter has a destination
     * @return {@code true} if so, {@code false} otherwise
     */
    public boolean isLinked(){
        return link != null;
    }

    /**
     * If the {@link Teleporter} is linked, the {@link Entity} that interacted with this Teleporter will be teleported to the
     * Teleporter this one is linked against
     * @param actor The Entity that interacted with the Teleporter
     */
    @Override
    public void onInteractWith(Entity actor) {
        if (isLinked()) {
            actor.setPosition(link.getX() + link.getWidth() / 2 - actor.getWidth() / 2, link.getY() - actor.getHeight());
        } else {
            properties.getNotificationArea().addNotification(
                    new Notification("This Teleporter doesnt work!", "Maybe there's something you can do about this?"));
        }
    }
}
