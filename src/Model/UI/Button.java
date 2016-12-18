package Model.UI;

import Model.Abstraction.AbstractEventInteractionObject;
import Model.Abstraction.ICanvas;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class  Button extends AbstractEventInteractionObject{

    private String s;

    private int width;
    private int height;
    private int x;
    private int y;

    private Rectangle2D bounds;

    private ICanvas canvas;

    public Button(int x, int y, int width, int height,String s){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.s = s;
        bounds = new Rectangle2D.Double(this.x, this.y, this.width, this.height);
    }

    @Override
    public void draw() {
        Graphics2D g2d = canvas.getPencil();
        g2d.setColor(new Color(255, 181, 101));
        g2d.fill(bounds);
        g2d.setColor(new Color(0,0,0));
        int textWidth = g2d.getFontMetrics().stringWidth(s);
        int textHeight = g2d.getFontMetrics().getHeight();
        g2d.drawString(s,this.x+(this.width/2)-(textWidth/2),this.y+(this.height/2)+(textHeight/2)-2);
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
        return bounds;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void setText(String s){
        this.s = s;
    }
}
