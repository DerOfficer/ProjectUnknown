package Model.Physics.Block;

import Model.Abstraction.IDrawableObject;
import Model.Abstraction.IPlayerInteractable;
import Model.Managing.SpriteManager;
import com.Physics2D.Entity;

import java.awt.*;
import java.util.function.Consumer;

import static Model.Managing.SpriteManager.BLOCK;
import static Model.Managing.SpriteManager.BLOCK_LEVER_OFF;
import static Model.Managing.SpriteManager.BLOCK_LEVER_ON;

/**
 * Created by jardu on 1/12/2017.
 */
public class Lever extends AbstractBlock implements IPlayerInteractable, IDrawableObject{

    private Consumer<Boolean> onToggle;

    private boolean on;

    /**
     * Creates a new physics object with the given position and dimensions
     *
     * @param x      The x-position of the physics object
     * @param y      The y-position of the physics object
     */
    public Lever(double x, double y, String id, boolean on) {
        super(x, y, 50,50, id);
        this.on = on;
    }

    @Override
    public void draw() {
        Graphics2D g = canvas.getPencil();
        if(on){
            g.drawImage(SpriteManager.SPRITES[BLOCK][BLOCK_LEVER_ON], (int)getX(), (int)getY(), null);
        }else{
            g.drawImage(SpriteManager.SPRITES[BLOCK][BLOCK_LEVER_OFF], (int)getX(), (int)getY(), null);
        }
    }

    @Override
    public void update(double dt) {
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public double getFrictionConstant() {
        return 0;
    }

    @Override
    public void onInteractWith(Entity actor) {
        on = !on;
        onToggle.accept(on);
    }

    public void setOnToggle(Consumer<Boolean> onToggle) {
        this.onToggle = onToggle;
    }

    public Consumer<Boolean> getOnToggle() {
        return onToggle;
    }
}
