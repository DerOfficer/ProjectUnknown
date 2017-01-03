package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import Model.Notification;
import Model.UI.Button;
import View.DrawingPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class Start extends DrawingPanel {

    private Button startButton;
    private Button settingsButton;
    private Button exitButton;

    public Start(ProjectUnknownProperties properties) {
        super(properties);
        int buttonX = (screenWidth / 2);

        Font menuFont = properties.getGameFont().deriveFont(40f);

        startButton = new Button(buttonX,((int) (screenHeight*0.45)), "Start", menuFont);
        settingsButton = new Button(buttonX, ((int) (screenHeight*0.65)), "Settings", menuFont);
        exitButton = new Button(buttonX, ((int) (screenHeight*0.85)), "Exit", menuFont);

        startButton.setForegroundColor(Color.white);
        settingsButton.setForegroundColor(Color.white);
        exitButton.setForegroundColor(Color.white);

        setSize(screenWidth, screenHeight);

        addObject(startButton);
        addObject(settingsButton);
        addObject(exitButton);

        setBackground(new Color(0,0,0,0));

        try {
            addObject(new Button((screenWidth / 2),(int) (screenHeight*0.1),ImageIO.read(new File("Images/logo.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        initEventHandler();
    }

    private void initEventHandler() {
        startButton.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            properties.getFrame().setContentPanel(properties.getFrame().getLevelSelect());
            properties.getFrame().setBackgroundPanel(properties.getFrame().getLevelSelectBackground());
            properties.getNotificationArea().addNotification(new Notification("Congratulations", "You clicked 'Start'"));
            properties.getNotificationArea().addNotification(new Notification("Yes", "Very Notification\n in 2 lines"));
            properties.getNotificationArea().addNotification(new Notification("Queueing", "All Notifications were registered at the same time"));
        });

        settingsButton.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            properties.getFrame().setContentPanel(properties.getFrame().getSettings());
        });

        exitButton.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
                System.exit(0);
        });

        exitButton.addEventHandler(IEventInteractableObject.EventType.KEY_RELEASED, (event) -> {
            if(event.getSrcKey() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }

            if(event.getSrcKey() == KeyEvent.VK_INSERT) {
                properties.getFrame().setContentPanel(properties.getFrame().getWorldEditor());
            }
        });
    }
}