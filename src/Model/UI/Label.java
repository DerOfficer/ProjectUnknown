package Model.UI;

import Model.Abstraction.ICanvas;
import Model.Abstraction.IDrawableObject;
import java.awt.*;

public class Label implements IDrawableObject{

    private int x;
    private int y;
    private String label;
    private ICanvas canvas;
    private int length;
    private int fontSize;

    public Label(int x, int y,String label, int fontSize) {
        this.label = label;
        this.x = x;
        this.y = y;

    }

    @Override
    public void draw() {
        Graphics2D g2d = canvas.getPencil();
        g2d.setFont(new Font(g2d.getFont().getName(), Font.BOLD, fontSize));
        g2d.setColor(Color.WHITE);
        length = g2d.getFontMetrics().stringWidth(label);
        g2d.drawString(label,(x-(length/2)),y);
        g2d.setFont(new Font(g2d.getFont().getName(), Font.PLAIN, 11));
    }

    @Override
    public void update(double dt) {
    }

    @Override
    public void provideCanvas(ICanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public Shape getBounds() {
        return null;
    }
}








