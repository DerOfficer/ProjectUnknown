package Model;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import View.DrawingPanel;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by 204g03 on 09.12.2016.
 */
public class Start extends DrawingPanel {
    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int x = (screenWidth/2)-(300/2);
    private Button startButton = new Button(x, 300,300,30,"Start");
    private Button settingsButton = new Button(x, 400,300,30,"Settings");
    private Button exitButton = new Button(x, 500,300,30,"Exit");
    private Background background = new Background();




    public Start(ProjectUnknownProperties properties){
        super(properties);
       // addObject(background);
        addObject(startButton);
        addObject(settingsButton);
        addObject(exitButton);

        startButton.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {

        });

        settingsButton.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            properties.getFrame().addNewDrawingPanel(properties.getFrame().getSettings());
        });

        exitButton.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            System.exit(0);
        });

    }
}
