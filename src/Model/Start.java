package Model;

import View.DrawingPanel;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by 204g03 on 09.12.2016.
 */
public class Start extends DrawingPanel {
    private Button startButton = new Button(790,300,300,30,"Start");
    private Button settingsButton = new Button(790,400,300,30,"Settings");
    private Button exitButton = new Button(790,500,300,30,"Exit");
    private Background background = new Background();




    public Start(){
       // addObject(background);
        addObject(startButton);
        addObject(settingsButton);
        addObject(exitButton);

    }
}
