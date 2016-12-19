package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import Model.UI.ImageButton;
import Model.UI.TextButton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Start extends ScreenPanel {

    private TextButton startTextButton;
    private TextButton settingsTextButton;
    private TextButton exitTextButton;

    public Start(ProjectUnknownProperties properties) {
        super(properties);

        int buttonX = (screenWidth / 2);

        startTextButton = new TextButton(buttonX,((int) (screenHeight*.45)),300,80,40f, "Start");
        settingsTextButton = new TextButton(buttonX, ((int) (screenHeight*0.65)),300,80,40f, "Settings");
        exitTextButton = new TextButton(buttonX, ((int) (screenHeight*0.85)),300,80,40f, "Exit");


        addObject(startTextButton);
        addObject(settingsTextButton);
        addObject(exitTextButton);

        try {
            addObject(new ImageButton(ImageIO.read(new File("Images/logo.png")),(int) (screenWidth / 2 - 275),(int) (screenHeight*0.1)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        initEventHandler();
    }



    private void initEventHandler() {
        startTextButton.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) ->
                properties.getFrame().setDrawingPanel(properties.getFrame().getLevelSelect())
        );

        settingsTextButton.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) ->
                properties.getFrame().setDrawingPanel(properties.getFrame().getSettings())
        );

        exitTextButton.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) ->
                System.exit(0)
        );

        exitTextButton.addEventHandler(IEventInteractableObject.EventType.KEY_RELEASED, (event) -> {
            if(event.getSrcKey() == KeyEvent.VK_ESCAPE)
                System.exit(0);
        });
    }
}