package Model.Physics.Block;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IPlayerInteractable;
import Model.Notification;
import com.Physics2D.Entity;

import java.awt.*;

/**
 * Created by jardu on 1/10/2017.
 */
public class Teleporter extends SolidTerrainBlock implements IPlayerInteractable{

    private Teleporter link;
    private ProjectUnknownProperties properties;

    public Teleporter(ProjectUnknownProperties properties, int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        this.properties = properties;
    }

    public Teleporter(ProjectUnknownProperties properties, int x, int y, BlockType blockType) {
        super(x, y, blockType);
        this.properties = properties;
    }

    public Teleporter(ProjectUnknownProperties properties, int x, int y, int width, int height, Color topColor, Color innerColor) {
        super(x, y, width, height, topColor, innerColor);
        this.properties = properties;
    }

    public void link(Teleporter against){
        this.link = against;
    }

    public boolean isLinked(){
        return link != null;
    }

    @Override
    public void onInteractWith(Entity actor) {
        if(!isLinked()){
            properties.getNotificationArea().addNotification(new Notification("This Teleporter doesnt work!", "Maybe there's something you can do about this?"));
            return;
        }
        actor.setPosition(link.getWidth() / 2 - actor.getWidth() / 2, link.getY() - actor.getHeight());
    }
}
