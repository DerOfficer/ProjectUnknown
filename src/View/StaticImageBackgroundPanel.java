package View;

import Control.ProjectUnknownProperties;
import Model.Abstraction.ICanvas;
import Model.Abstraction.IDrawableObject;
import View.StaticDrawingPanel;

import java.awt.*;

/**
 * Created by Max on 17.12.2016.
 */
public class StaticImageBackgroundPanel extends StaticDrawingPanel {

    protected Image image;

    public StaticImageBackgroundPanel(ProjectUnknownProperties properties, Image image){
        super(properties);
        this.image = image;
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(image, screenWidth/2-image.getWidth(this)/2, screenHeight/2-image.getHeight(this)/2, this);
    }
    /*@Override
    public void draw() {
        Graphics2D g2d = canvas.getPencil();
        g2d.drawImage(image,(int)(canvas.getBounds().getWidth()/2 - image.getWidth(null)/2),(int)(canvas.getBounds().getHeight()/2 - image.getHeight(null)/2),null);
    }

    @Override
    public Shape getBounds() {
        return new Rectangle((int)(canvas.getBounds().getWidth()/2 - image.getWidth(null)/2),(int)(canvas.getBounds().getHeight()/2 - image.getHeight(null)/2), image.getWidth(null), image.getHeight(null));
    }

    @Override
    public void update(double dt) { }

    @Override
    public void provideCanvas(ICanvas canvas) {
        this.canvas = canvas;
    }
*/
}
