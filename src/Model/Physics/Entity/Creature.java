package Model.Physics.Entity;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IDrawableObject;
import Model.Physics.ManaCast;
import com.Physics2D.Entity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Oussama on 07.01.2017.
 */
public abstract class Creature extends Entity implements IDrawableObject {

    private int maxHealth,actHealth, maxMana,actMana,counter,level,manaCoolDown;
    protected ProjectUnknownProperties properties;
    private boolean manaReady;
    private Timer timer;

    private final int MANA_REGENERATION = 1;
    
    public Creature(int x, int y, int width, int height, int health, int mana, ProjectUnknownProperties properties) {
        super(x, y, width, height);
        this.maxHealth = health;
        this.actHealth = health;
        this.maxMana = mana;
        this.actMana = mana;
        this.properties = properties;
        this.level = interpretStats();

        startUp();
    }


    public Creature(int x, int y, int width, int height, int level, ProjectUnknownProperties properties) {
        super(x, y, width, height);
        this.level = level;
        this.properties = properties;
        setStats();
        startUp();
    }

    protected void setStats(){
        this.maxHealth = level*25+75;
        this.actHealth = maxHealth;
        this.maxMana = level*25+75;
        this.actMana = maxMana;
    }

    protected int interpretStats(){
        double temp = (double)maxHealth-75;
        temp = temp/(double)25;
        return (int)temp;
    }

    private void startUp(){
        manaReady = true;
        this.counter = 0;


        timer = new Timer();
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
            if(counter >= manaCoolDown){
                manaReady = true;
                counter = 0;
            }
        }
        if(actMana <= maxMana){
            actMana = actMana + MANA_REGENERATION;
        }
    }

    protected void conjure(ManaCast.Type type){
        if(manaReady) {
            if (actMana >= type.getMana()) {
                actMana = actMana - type.getMana();
                world.addObject(new ManaCast(type, this));
                manaCoolDown = type.getSpellCoolDown();
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
        if(getVelocity() >= 0){
            return 1;
        }else{
            return -1;
        }
    }

    protected boolean isDead(){
        return(actHealth <= 0);
    }

    public double getAttack() {
        return 50;
    }
}
