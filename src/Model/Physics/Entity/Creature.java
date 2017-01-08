package Model.Physics.Entity;

import Model.Abstraction.IDrawableObject;
import com.Physics2D.Entity;

/**
 * Created by Oussama on 07.01.2017.
 */
public abstract class Creature extends Entity implements IDrawableObject {

    private int maxHealth,actHealth, maxMana,actMana;

    public Creature(int x, int y, int width, int height, int health, int mana) {
        super(x, y, width, height);
        this.maxHealth = health;
        this.actHealth = health;
        this.maxMana = mana;
        this.actMana = mana;
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
    
    public int getMaximumMana(){
        return maxMana;
    }

    public int getActualMana(){
        return actMana;
    }

    public void setMaximumMana(int maxMana){
        this.maxMana = maxMana;
    }

    public void setActualMana(int actMana){
        this.actMana = actMana;
    }

    public double getManaInPercent(){
        return actMana/maxMana;
    }

    public double getHealthInPercent() {
        if(maxHealth == 0){
            return 0.5;
        }
        return actHealth/maxHealth;
    }
}
