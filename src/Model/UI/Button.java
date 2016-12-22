package Model.UI;

import Model.Abstraction.AbstractEventInteractionObject;
import Model.Abstraction.ICanvas;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

public class  Button extends AbstractEventInteractionObject{

    private String s;

    private int x;
    private int y;

    private Rectangle2D bounds;

    private ICanvas canvas;

    private Font font;

    private Image img;

    private int width;
    private int height;

    private Color backgroundColor;
    private Color foregroundColor;

    public Button(int x, int y, String s, Font font){
        setLocation(x,y);
        setText(s);
        this.font = font;
        backgroundColor = new Color(0,0,0,0);

    }

    public Button(int x, int y, int width, int height, String s, Font f){
        this(x,y,s,f);
        this.width = width;
        this.height = height;
    }

    public Button(int x, int y, Image img){
        setLocation(x,y);
        this.img = img;
        backgroundColor = new Color(0,0,0,0);
    }

    public Button(int x, int y, int width, int height, Color color){
        setLocation(x,y);
        this.width = width;
        this.height = height;
        this.backgroundColor = color;
    }

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    @Override
    public void draw() {
        Graphics2D g2d = canvas.getPencil();
        int yOffset = 0;
        g2d.setColor(backgroundColor);
        g2d.fillRect(x,y, width,height);
        g2d.setColor(foregroundColor);
        int realWidth = width;
        int realHeight = height;
        if(img != null){
            g2d.drawImage(img, x - img.getWidth(null)/2 + width/2, y, null);
            yOffset = img.getHeight(null);
            realWidth = Math.max(realWidth, img.getWidth(null));
            realHeight = Math.max(realHeight, img.getHeight(null));
        }
        if(s != null){
            g2d.setFont(font);
            FontMetrics metrics = g2d.getFontMetrics();
            int stringWidth = metrics.stringWidth(s);
            int stringHeight = metrics.getHeight();
            g2d.drawString(s, x - stringWidth/2 + width/2, y + stringHeight/2 + yOffset + height/2);
            realWidth = Math.max(realWidth, stringWidth);
            realHeight = Math.max(realHeight, yOffset + stringHeight + metrics.getDescent());
        }
        bounds = new Rectangle2D.Double(x-realWidth/2,y,realWidth, realHeight);
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

    public void setText(String newS){
        this.s = newS;
        if(newS.equals(" ")){
            this.s = "Space";
        }
    }
}
