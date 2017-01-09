package Model.Physics.Entity.Mobs;

import Model.Abstraction.ICanvas;
import Model.Abstraction.IDrawableObject;
import com.Physics2D.Entity;

import java.awt.*;

/**
 * Created by Oussama on 07.01.2017.
 */
public class Enemy extends Entity implements IDrawableObject {
    public Enemy(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw() {

    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void provideCanvas(ICanvas canvas) {

    }

    @Override
    public Shape getBounds() {
        return null;
    }

    @Override
    public double getMass() {
        return 0;
    }
}
