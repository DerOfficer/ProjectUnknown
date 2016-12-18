package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import Model.Physics.Entity.Human;
import Model.UI.Button;
import Model.UI.ImageButton;
import View.DrawingPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Start extends DrawingPanel {

    private Button startButton;
    private Button settingsButton;
    private Button exitButton;

    public Start(ProjectUnknownProperties properties) {
        super(properties);

        int buttonX = (screenWidth / 2) - (300 / 2);

        startButton = new Button(buttonX, 300, 300, 30, "Start");
        settingsButton = new Button(buttonX, 400, 300, 30, "Settings");
        exitButton = new Button(buttonX, 500, 300, 30, "Exit");

        addObject(startButton);
        addObject(settingsButton);
        addObject(exitButton);

        initEventHandler();
        drawStars();
    }

    private void drawStars() {
        try {
            BufferedImage img = ImageIO.read(new File("Images/star.png"));
            int amount = 30;
            for (int i = 0; i < amount; i++) {
                int x = (int) (screenWidth/30)*i;
                int y = (int) (screenHeight*Math.random());
                addObject(new ImageButton(img,x,y));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initEventHandler() {
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