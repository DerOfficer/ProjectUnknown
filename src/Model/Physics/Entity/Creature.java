package Model.Physics.Entity;

import Control.ProjectUnknownProperties;
import Model.Abstraction.ICanvas;
import Model.Abstraction.IDrawableObject;
import Model.Physics.ManaCast;
import com.Physics2D.Entity;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Oussama on 07.01.2017.
 */
public abstract class Creature extends Entity implements IDrawableObject {

    private static final int MANA_REGENERATION = 1;

    private int maxHealth;
    private int actHealth;
    private int maxMana;
    private int actMana;
    private int counter;
    private int level;
    private int manaCoolDown;

    private boolean manaReady;

    private Timer timer;

    protected ICanvas canvas;

    /**
     * standard creature contains x position, y position, health and mana.
     * @param x
     * @param y
     * @param width
     * @param height
     * @param health
     * @param mana
     * @param properties
     */
    public Creature(int x, int y, int width, int height, int health, int mana, ProjectUnknownProperties properties) {
        super(x, y, width, height);
        this.maxHealth = health;
        this.actHealth = health;
        this.maxMana = mana;
        this.actMana = mana;
        this.level = interpretStats();

        startUp();
    }


    /**
     * standard which gets define by his level
     * @param x
     * @param y
     * @param width
     * @param height
     * @param level
     */
    public Creature(int x, int y, int width, int height, int level) {
        super(x, y, width, height);
        this.level = level;
        setStats();
        startUp();
    }

    /**
     * set stats depended on the current level
     */
    protected void setStats(){
        this.maxHealth = level*25+75;
        this.actHealth = maxHealth;
        this.maxMana = level*25+75;
        this.actMana = maxMana;
    }

    /**
     * interpret all stats and returns a level
     * @return level of creature
     */
    protected int interpretStats(){
        double temp = (double)maxHealth-75;
        temp = temp/(double)25;
        return (int)temp;
    }

    /**
     * start up for different constructors
     */
    private void startUp(){
        this.manaReady = true;
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

    /**
     * is running every second
     */
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

    /**
     * conjures a mana cast and subtracts the mana cost
     * @param type
     */
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
        return (level*20)+20;
    }

    @Override
    public void provideCanvas(ICanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public Shape getBounds() {
        return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
    }
}
