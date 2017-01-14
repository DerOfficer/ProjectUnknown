package Model.Physics;

import Model.Abstraction.ICanvas;
import Model.Abstraction.IDrawableObject;
import Model.Managing.SpriteManager;
import Model.Physics.Entity.Creature;
import com.Physics2D.Entity;
import com.Physics2D.PhysicsObject;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import static Model.Managing.SpriteManager.MANA_CAST;


/**
 * Created by Oussama on 07.01.2017.
 */
public class ManaCast extends Entity implements IDrawableObject {

    private double movement;

    private Type type;
    private Creature creature;
    private ICanvas canvas;
    private BufferedImage sprite;
    /**
     * constructs new mana casts and get instantly shoot from the creatures position
     *
     * @param type
     * @param creature
     */
    public ManaCast(@NotNull Type type, @NotNull Creature creature) {
        super(creature.getX(), creature.getY(), type.getImage().getWidth(), type.getImage().getHeight());
        this.type = type;
        this.creature = creature;
        this.sprite = type.getImage();
        this.movement = type.getSpeedModifier() * creature.getDirection() * 15;
        Timer timer = new Timer();

        setGravityAffection(false);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (world != null) {
                    world.removeObject(ManaCast.this);
                }
            }
        };

        timer.schedule(timerTask, type.getTimeSpell() * 1000, 1000);
    }

    @Override
    public void draw() {
        Graphics g = canvas.getPencil();
        g.drawImage(sprite, (int) getX(), (int) getY(), null);
    }

    @Override
    public void update(double dt) {
        accelerate(movement);
        if (world.getIntersecting(this) != null) {
            for (PhysicsObject object : world.getIntersecting(this)) {
                if (object instanceof Creature && object != creature && !creature.getClass().isInstance(object)) {
                    //We prevent mobs from killing their own kind by checking that their mana orbs cant hit instances of their own class
                    Creature enemy = (Creature) object;
                    enemy.setActualHealth(enemy.getActualHealth() - (int) (type.getAttackModifier() * enemy.getAttack()));
                    world.removeObject(this);
                }
            }
        }
    }

    @Override
    public void provideCanvas(ICanvas canvas) {
        this.canvas = canvas;
    }

    @NotNull
    @Override
    public Shape getBounds() {
        return new Rectangle((int) getX(), (int) getY(), type.getImage().getWidth(), type.getImage().getHeight());
    }

    @Override
    public double getMass() {
        return 0;
    }

    /**
     * defines different mana types
     */
    public enum Type {
        FIRE_BALL(1.00, 1.00, 20, 2, 3),
        LIGHT_BALL(2.00, 0.9, 10, 5, 1),
        METAL_BALL(0.5, 2.00, 40, 5, 6),
        RAINBOW_BALL(3.00, 1.5, 80, 10, 10);

        private int mana;
        private int timeSpell;
        private int timeCoolDown;

        private double speedModifier;
        private double attackModifier;

        private BufferedImage image;

        Type(double speedModifier, double attackModifier, int mana, int timeSpell, int timeCoolDown) {
            this.speedModifier = speedModifier;
            this.attackModifier = attackModifier;
            this.mana = mana;
            this.timeSpell = timeSpell;
            this.timeCoolDown = timeCoolDown;
            this.image = SpriteManager.SPRITES[MANA_CAST][ordinal()];
        }

        public BufferedImage getImage() {
            return image;
        }

        public double getSpeedModifier() {
            return speedModifier;
        }

        public double getAttackModifier() {
            return attackModifier;
        }

        public int getMana() {
            return mana;
        }

        public int getTimeSpell() {
            return timeSpell;
        }

        public int getSpellCoolDown() {
            return timeCoolDown;
        }
    }
}
