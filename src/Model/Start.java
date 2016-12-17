package Model;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import Model.Physics.GrassBlock;
import Model.Physics.Level;
import View.DrawingPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class Start extends DrawingPanel {
    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int x = (screenWidth / 2) - (300 / 2);
    private Button startButton = new Button(x, 300, 300, 30, "Start");
    private Button settingsButton = new Button(x, 400, 300, 30, "Settings");
    private Button exitButton = new Button(x, 500, 300, 30, "Exit");
    private Background background = new Background();


    private Label label = new Label(404,100,"Planet sun = new Button()", 20);

    public Start(ProjectUnknownProperties properties) {
        super(properties);
        //addObject(background);
        addObject(startButton);
        addObject(settingsButton);
        addObject(exitButton);
        addObject(label);

        startButton.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            Level level = new Level(9.81, properties);
            level.addObject(new GrassBlock(100, 100, 100, 100));
            properties.getFrame().setDrawingPanel(level.getRenderer());
            level.scrollTo(400, 400);
        });

        settingsButton.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) ->
                properties.getFrame().setDrawingPanel(properties.getFrame().getSettings())
        );

        exitButton.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) ->
                System.exit(0)
        );

        exitButton.addEventHandler(IEventInteractableObject.EventType.KEY_RELEASED, (event) -> {
            if(event.getSrcKey() == KeyEvent.VK_ESCAPE)
                System.exit(0);
        });

    }
}


/**
            ____
            |  | --|   BAUMWOLLE mit einer IF-Schleife
            |__| --|

              |_____
 **/
