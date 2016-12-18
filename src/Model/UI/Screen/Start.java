package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import Model.BackgroundRenderer;
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

    private BackgroundRenderer bgRenderer;

    public Start(ProjectUnknownProperties properties) {
        super(properties);

        int buttonX = (screenWidth / 2) - (300 / 2);

        drawGalaxy();

        startButton = new Button(buttonX, 300, 300, 30, "Start");
        settingsButton = new Button(buttonX, 400, 300, 30, "Settings");
        exitButton = new Button(buttonX, 500, 300, 30, "Exit");

        addObject(bgRenderer);
        addObject(startButton);
        addObject(settingsButton);
        addObject(exitButton);

        initEventHandler();
    }

    private void drawGalaxy() {
        try {
            BufferedImage img = ImageIO.read(new File("Images/star-50px.png"));
            BufferedImage backgroundImg = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics g = backgroundImg.getGraphics();
            int amount = screenWidth/100;
            for (int i = 0; i < amount; i++) {
                int x = (int) (screenWidth/amount)*i;
                int y = (int) (screenHeight*Math.random());
                g.drawImage(img, x, y, this);
                //addObject(new ImageButton(img,x,y));
            }
            bgRenderer = new BackgroundRenderer(backgroundImg);
            setBackground(new Color(0, 0, 0));
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