package Model;

import Model.Abstraction.AbstractEventInteractionObject;
import Model.Abstraction.ICanvas;

import java.awt.*;


/**
 * Created by 204g03 on 09.12.2016.
 */
public class Background extends AbstractEventInteractionObject{


    private ICanvas canvas;
    private Image background = Toolkit.getDefaultToolkit().getImage("super-mario-world-wallpaper.png");

    public Background(){
    }


    @Override
    public void draw() {
        Graphics2D g2d = canvas.getPencil();
        g2d.scale( 0.15, 0.15);
        g2d.drawImage(background, 0,0 , null);
        g2d.scale(1,1);
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
        return canvas.getBounds();
    }

}
