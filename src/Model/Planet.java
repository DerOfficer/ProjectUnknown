package Model;

import Model.Abstraction.AbstractEventInteractionObject;
import Model.Abstraction.ICanvas;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by 204g03 on 16.12.2016.
 */
public class Planet extends AbstractEventInteractionObject {

    private String name;
    private int x;
    private int y;

    private Image earth = Toolkit.getDefaultToolkit().getImage("earth.png");
    private Image jupiter = Toolkit.getDefaultToolkit().getImage("jupiter.png");
    private Image mars = Toolkit.getDefaultToolkit().getImage("mars.png");
    private Image mercury = Toolkit.getDefaultToolkit().getImage("mercury.png");
    private Image neptune = Toolkit.getDefaultToolkit().getImage("neptune.png");
    private Image saturn = Toolkit.getDefaultToolkit().getImage("saturn.png");
    private Image uranus = Toolkit.getDefaultToolkit().getImage("uranus.png");
    private Image venus = Toolkit.getDefaultToolkit().getImage("venus.png");



    private ICanvas canvas;

    public Planet(String name,int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;

    }

    @Override
    public void draw() {
        Graphics2D g2d = canvas.getPencil();
        if (name.equals("earth")){
            g2d.drawImage(earth,x,y,null);
        }
        if (name.equals("jupiter")){
            g2d.drawImage(jupiter,x,y,null);
        }
        if (name.equals("mars")){
            g2d.drawImage(mars,x,y,null);
        }
        if (name.equals("mercury")){
            g2d.drawImage(mercury,x,y,null);
        }
        if (name.equals("neptune")){
            g2d.drawImage(neptune,x,y,null);
        }
        if (name.equals("saturn")){
            g2d.drawImage(saturn,x,y,null);
        }
        if (name.equals("uranus")){
            g2d.drawImage(uranus,x,y,null);
        }
        if (name.equals("venus")){
            g2d.drawImage(venus,x,y,null);
        }
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
