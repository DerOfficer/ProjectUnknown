package Model;

import Model.Abstraction.ICanvas;
import Model.Abstraction.IDrawableObject;

import java.awt.*;

/**
 * Created by 204g03 on 12.12.2016.
 */
public class Label implements IDrawableObject{

    private int x;
    private int y;
    private String label;
    private ICanvas canvas;
    private

    public Label(int x, int y,String label){
        this.label = label;
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw() {
        Graphics2D g2d = canvas.getPencil();
        (int) FontMetrics length = g2d.getFontMetrics();
        g2d.drawString(label,x-length,y);

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
}
