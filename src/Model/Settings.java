package Model;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import View.DrawingPanel;
import Model.VolumeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by 204g03 on 12.12.2016.
 */
public class Settings extends DrawingPanel{

    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int x = (screenWidth/2);

    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;


    private Button jump = new Button(x-75,screenHeight/10*4-150,150,150,"W");
    private Button crouch = new Button(x-75,screenHeight/10*5,150,150,"S");
    private Button left = new Button(x-225-screenHeight/10,screenHeight/10*5,150,150,"A");
    private Button right = new Button(x+75+screenHeight/10,screenHeight/10*5,150,150,"D");

    private Button minus = new Button(screenWidth/8*5,screenHeight/10*9,75,75,"-");
    private Button plus = new Button(screenWidth/8*7,screenHeight/10*9,75,75,"+");

    private Button back = new Button(x/5,screenHeight/10*9,75,75,"← Back");
    private Label headline = new Label(x,screenHeight/10*2,"SETTINGS",150);

    private Button doTheFlop = new Button(screenWidth-125, screenHeight/10*5,80,50,"Easter Egg");

    boolean turned = false;


    public Settings(ProjectUnknownProperties properties){
        super(properties);
        addObject(jump);
        addObject(crouch);
        addObject(left);
        addObject(right);
        addObject(minus);
        addObject(plus);
        addObject(back);
        addObject(headline);
        addObject(doTheFlop);


        back.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            properties.getFrame().addNewDrawingPanel(properties.getFrame().getStart());
        });

        back.addEventHandler(IEventInteractableObject.EventType.KEY_RELEASED, (event) -> {
            if(event.getSrcKey() == KeyEvent.VK_ESCAPE)
                properties.getFrame().addNewDrawingPanel(properties.getFrame().getStart());
        });



        minus.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            properties.getVolumeManager().decrease();
        });
        plus.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            properties.getVolumeManager().increase();
        });

        doTheFlop.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            SwingUtilities.invokeLater(() -> {
                if(!turned) {
                    removeObject(headline);
                    headline = new Label(x, screenHeight / 10 * 2, "IF-SCHLEIFE", 150);
                    addObject(headline);
                }else{
                    removeObject(headline);
                    headline = new Label(x, screenHeight / 10 * 2, "SETTINGS", 150);
                    addObject(headline);
                }
                turned = !turned;
            });
        });



    }


}
