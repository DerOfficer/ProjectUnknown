package Model;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import View.DrawingPanel;

import java.awt.*;

/**
 * Created by 204g03 on 12.12.2016.
 */
public class Settings extends DrawingPanel{

    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int x = (screenWidth/2)-(150/2);

    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;


    private Button jump = new Button(x,screenHeight/10*4-150,150,150,"W");
    private Button crouch = new Button(x,screenHeight/10*5,150,150,"S");
    private Button left = new Button(x-150-screenHeight/10,screenHeight/10*5,150,150,"A");
    private Button right = new Button(x+150+screenHeight/10,screenHeight/10*5,150,150,"D");

    private Button minus = new Button(screenWidth/8*5,screenHeight/10*9,75,75,"-");
    private Button plus = new Button(screenWidth/8*7,screenHeight/10*9,75,75,"+");

    private Button back = new Button(x/4,screenHeight/10*9,75,75,"← Back");

    public Settings(ProjectUnknownProperties properties){
        super(properties);
        addObject(jump);
        addObject(crouch);
        addObject(left);
        addObject(right);
        addObject(minus);
        addObject(plus);
        addObject(back);

        back.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            properties.getFrame().addNewDrawingPanel(properties.getFrame().getStart());
        });

    }


}
