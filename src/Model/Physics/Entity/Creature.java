package Model.Physics.Entity;

import Model.Abstraction.IDrawableObject;
import com.Physics2D.Entity;

/**
 * Created by Oussama on 07.01.2017.
 */
public abstract class Creature extends Entity implements IDrawableObject {

    private int maxHealth,actHealth;

    public Creature(int x, int y, int width, int height) {
        super(x, y, width, height);

    }

    public int getMaximumHealth() {
        return maxHealth;
    }

    public void setMaximumHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getActualHealth() {
        return actHealth;
    }

    public void setActualHealth(int actHealth) {
        this.actHealth = actHealth;
    }

    public double getHealthInPercent() {
        if(maxHealth == 0){
            return 0.5;
        }
        return actHealth/maxHealth;
    }
}
