package Model.Physics.Entity;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IDrawableObject;
import Model.Physics.Projectile;
import com.Physics2D.Entity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Oussama on 07.01.2017.
 */
public abstract class Creature extends Entity implements  IDrawableObject {

    private int maxHealth,actHealth, maxMana,actMana,counter;
    protected ProjectUnknownProperties properties;
    private final int MANA_COOL_DOWN = 3;
    private final int MANA_REGENERATION = 1;
    private boolean manaReady;
    
    public Creature(int x, int y, int width, int height, int health, int mana, ProjectUnknownProperties properties) {
        super(x, y, width, height);
        this.maxHealth = health;
        this.actHealth = health;
        this.maxMana = mana;
        this.actMana = mana;
        this.properties = properties;
        this.counter = 0;

        manaReady = true;

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runEverySecond();
            }
        };
        timer.scheduleAtFixedRate(timerTask,1000,1000);
    }
    
    private void runEverySecond() {
        if(!manaReady){
            counter++;
            if(counter >= MANA_COOL_DOWN){
                manaReady = true;
                counter = 0;
            }
        }
        if(actMana <= maxMana){
            actMana++;
        }
    }

    protected void conjure(Projectile.Type type){
        if(manaReady) {
            if (actMana >= type.getMana()) {
                actMana = actMana - type.getMana();
                properties.getCurrentWorld().addObject(new Projectile(type, this));
                manaReady = false;
            }
        }
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
        return (double) actMana/maxMana;
    }

    public double getHealthInPercent() {

        return (double) actHealth/maxHealth;
    }

    public int getDirection(){
        if(getSideWayVelocity() >= 0){
            return 1;
        }else{
            return -1;
        }
    }
}
