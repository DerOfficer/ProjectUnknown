package Model.UI;

import Model.Abstraction.AbstractEventInteractionObject;
import Model.Abstraction.ICanvas;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

public class TextButton extends AbstractEventInteractionObject{

    private String s;

    private int width;
    private int height;
    private int x;
    private int y;

    private Rectangle2D bounds;
    private float fontSize;
    private ICanvas canvas;
    private Font customFont;

    public TextButton(int x, int y, int width, int height, float fontSize, String s){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fontSize = fontSize;
        this.s = s;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts\\galaxy-font.ttf")).deriveFont(fontSize);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts\\galaxy-font.ttf")));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void draw() {
        Graphics2D g2d = canvas.getPencil();
        g2d.setColor(new Color(255, 246, 252));
        g2d.setFont(customFont);

        FontMetrics fontMetrics = g2d.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(s);
        int textHeight = fontMetrics.getHeight();
        int offset = fontMetrics.getHeight() - fontMetrics.getDescent();
        bounds = new Rectangle2D.Double(this.x - textWidth / 2, this.y - offset, textWidth, textHeight);

        g2d.drawString(s, this.x - (textWidth / 2), this.y);
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
