package Model.UI;

import Model.Abstraction.AbstractEventInteractionObject;
import Model.Abstraction.ICanvas;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Button extends AbstractEventInteractionObject {

    private String text;

    private int x;
    private int y;
    private int width;
    private int height;

    private Rectangle2D bounds = new Rectangle2D.Double(0, 0, 0, 0);

    private ICanvas canvas;

    private Color backgroundColor;
    private Color foregroundColor;

    private Image img;
    private Font font;

    public Button(int x, int y, String text, Font font) {
        this.font = font;
        this.backgroundColor = new Color(0, 0, 0, 0);

        setLocation(x, y);
        setText(text);
    }

    public Button(int x, int y, int width, int height, String text, Font font) {
        this(x, y, text, font);

        this.width = width;
        this.height = height;
    }

    public Button(int x, int y, Image img) {
        this.img = img;
        this.backgroundColor = new Color(0, 0, 0, 0);
        this.bounds = new Rectangle2D.Double(x, y, img.getWidth(null), img.getHeight(null));

        setLocation(x, y);
    }

    public Button(int x, int y, int width, int height, Color color) {
        this.width = width;
        this.height = height;
        this.backgroundColor = color;

        setLocation(x, y);
    }


    @Override
    public void draw() {
        Graphics2D g2d = canvas.getPencil();
        int yOffset = 0;
        g2d.setColor(backgroundColor);
        g2d.fillRect(x, y, width, height);
        g2d.setColor(foregroundColor);
        int realWidth = width;
        int realHeight = height;
        if (img != null) {
            g2d.drawImage(img, x - img.getWidth(null) / 2 + width / 2, y, null);
            yOffset = img.getHeight(null);
            realWidth = Math.max(realWidth, img.getWidth(null));
            realHeight = Math.max(realHeight, img.getHeight(null));
        }
        if (text != null) {
            g2d.setFont(font);
            FontMetrics metrics = g2d.getFontMetrics();
            int stringWidth = metrics.stringWidth(text);
            int stringHeight = metrics.getHeight();
            g2d.drawString(text, x - stringWidth / 2 + width / 2, y + stringHeight / 2 + yOffset + height / 2);
            realWidth = Math.max(realWidth, stringWidth);
            realHeight = Math.max(realHeight, yOffset + stringHeight + metrics.getDescent());
        }
        bounds = new Rectangle2D.Double(x - realWidth / 2, y, realWidth, realHeight);
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setText(String newS) {
        this.text = newS;
        if (newS.equals(" ")) {
            this.text = "Space";
        }
    }

    public void setLocation(int x, int y) {
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
}
