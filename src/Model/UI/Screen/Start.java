package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import Model.UI.Button;
import View.DrawingPanel;

import java.awt.event.KeyEvent;

public class Start extends DrawingPanel {
    private int buttonX;

    private Button startButton;
    private Button settingsButton;
    private Button exitButton;

    public Start(ProjectUnknownProperties properties) {
        super(properties);

        buttonX = (screenWidth / 2) - (300 / 2);

        startButton = new Button(buttonX, 300, 300, 30, "Start");
        settingsButton = new Button(buttonX, 400, 300, 30, "Settings");
        exitButton = new Button(buttonX, 500, 300, 30, "Exit");

        addObject(startButton);
        addObject(settingsButton);
        addObject(exitButton);

        startButton.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) ->
            properties.getFrame().setDrawingPanel(properties.getFrame().getLevelSelect())
        );

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